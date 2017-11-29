package com.storm.crawl;

import java.awt.AWTException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;
import java.util.concurrent.TimeUnit;

import org.apache.bcel.generic.NEW;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.storm.CrawlVO.KeywordVO;
import com.storm.CrawlVO.ReviewVO;
import com.storm.crawlUtil.KeywordCounter;
import com.storm.crawlUtil.WordExtractor;


//view-source:
@SuppressWarnings("serial")
public class AppReviewCrawler implements	Serializable
{
	public	String	appid;
	public	String[]	monthNames={
			"January","February","March","April","May","June","July","August","September","October","November","December"
	};
	
	public AppReviewCrawler(String app_id) throws IOException, ClassNotFoundException{
		
		this.appid="35140";
		this.getReviews();// 리뷰 크롤링
		
		File	test	=	new File(".\\dat\\"+this.appid+"\\");
		if(!test.exists())
		{
			test.mkdirs();
		}		
		
		String path=".\\dat\\"+this.appid+"\\"+this.appid+"_";
		writeObjectFile(path+"positiveReview.dat", this.positiveReviews);
		writeObjectFile(path+"negativeReview.dat", this.negativeReviews);
		
		//키워드 추출
		WordExtractor	we	=	new WordExtractor();
		ArrayList<String>	positive_keywords = we.getKeywordsArrayList(this.positiveReviews);
		ArrayList<String>	negative_keywords = we.getKeywordsArrayList(this.negativeReviews);
		
		writeObjectFile(path+"positiveKeywords.dat", positive_keywords);
		writeObjectFile(path+"negativeKeywords.dat", negative_keywords);
				
		
		// word count util		
		KeywordCounter	count = new KeywordCounter();
		count.inputKeywords(positive_keywords);
		ArrayList<KeywordVO> positiveWordCount = count.getSortedKeywordsList();
		Collections.sort(positiveWordCount);
		writeObjectFile(path+"positiveWordCount.dat", positiveWordCount);
		
		PrintWriter	pw = new PrintWriter(new File(path+"positiveKeywordInsertSQL.sql"));
		System.out.println("Positive=======================================================================");
		for(KeywordVO vo : positiveWordCount){
			vo.app_id = Integer.parseInt(this.appid);
			System.out.println(vo.getKeyword()+"\t"+vo.getCount());
			pw.println(vo.getPositiveSQLFormat());
		}
		pw.flush();
		pw.close();
		
		count = new KeywordCounter();
		count.inputKeywords(negative_keywords);
		ArrayList<KeywordVO>	negativeWordCount = count.getSortedKeywordsList();
		Collections.sort(negative_keywords);
		writeObjectFile(path+"negativeWordCount.dat", negativeWordCount);
		
		pw= new PrintWriter(new File(path+"negativeKeywordInsertSQL.sql"));
		System.out.println("Negative=======================================================================");
		for(KeywordVO vo : negativeWordCount){
			vo.app_id = Integer.parseInt(this.appid);
			System.out.println(vo.getKeyword()+"\t"+vo.getCount());
			pw.println(vo.getNegativeSQLFormat());
		}
		pw.flush();
		pw.close();
		exportSQLFile(this.appid);
	}
	ArrayList<ReviewVO>	positiveReviews;
	ArrayList<ReviewVO>	negativeReviews;
	
	public String	getPositivePageURL(){
		return "http://steamcommunity.com/app/"+this.appid+"/positivereviews/?browsefilter=toprated&snr=1_5_reviews_&filterLanguage=koreana";
	}
	public String getNegativePageURL(){
		return "http://steamcommunity.com/app/"+this.appid+"/negativereviews/?browsefilter=toprated&snr=1_5_reviews_&filterLanguage=koreana";
	}
	
	
	@SuppressWarnings("unused")
	public ArrayList<ReviewVO>	getReviews(boolean isPositive)
	{
		ArrayList<ReviewVO>	result = new ArrayList<>();
		
		String CHROMEDRIVER_FILE_PATH = ".\\chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", CHROMEDRIVER_FILE_PATH);

		WebDriver	driver	=	new ChromeDriver();
		
		String url = isPositive ? this.getPositivePageURL():this.getNegativePageURL();
		try{
			driver.get(url);
			
			//System.out.println(driver.getPageSource());
		}catch (Exception e) {
			e.printStackTrace();
			driver.quit();
		}	
		
		JavascriptExecutor jse = ((JavascriptExecutor) driver);
	
		WebElement nomore = driver.findElement(By.className("apphub_NoMoreContent"));
		
		do
		{
			
			jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
			
			nomore = driver.findElement(By.className("apphub_NoMoreContent"));
			
			jse.executeScript("window.scrollTo(0, 0)");
		}while(!nomore.isDisplayed());
		jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		
		
		List<WebElement>	reviews = driver.findElements(By.className("apphub_Card"));
		//System.out.println(reviews.size());
		
		int maxCount = reviews.size(); 
		for(int i=0; i<maxCount; i++){
			WebElement temp = reviews.get(i);
			
			WebElement	writerDiv = temp.findElement(By.className("apphub_CardContentAuthorName"));
			List<WebElement> inOfWriter = writerDiv.findElements(By.tagName("a"));
			StringBuilder writer = new StringBuilder();
			
			for(WebElement e : inOfWriter){
				writer.append(e.getText());
			}
			
			WebElement dateDiv	=	temp.findElement(By.className("date_posted"));		
			
			String wDate = dateDiv.getText();
			wDate = wDate.replaceAll("Posted: ", "");
			System.out.println(wDate);
			StringTokenizer	st	=	new StringTokenizer(wDate, " ,");
			String strDate = st.nextToken().replaceAll(" ", "");
			int date=-1;
			int month = -1;
			int year = -1;
			try{
				date = Integer.parseInt(strDate);
				
				String tempMonth = st.nextToken().replaceAll(" ", "");
				for(int m=0;m<monthNames.length; m++){
					if(tempMonth.equals(monthNames[m]))
						month = m+1;
				}
			}
			catch (Exception e) {
				for(int m=0;m<monthNames.length; m++){
					if(strDate.equals(monthNames[m]))
						month = m+1;
				}
				
				 date = Integer.parseInt(st.nextToken().replaceAll(" ", ""));				
			}
			
			if(!st.hasMoreTokens()){
				long time = System.currentTimeMillis();
				SimpleDateFormat	dayTime = new SimpleDateFormat("yyyy");
				String strYear	=	dayTime.format(new Date());
				
				year = Integer.parseInt(strYear);
			}
			else{
				year = Integer.parseInt(st.nextToken().replaceAll(" ", ""));
			}
				
			String tempDate = year+"-"+month+"-"+date;
			
			
			WebElement txt = temp.findElement(By.className("apphub_CardTextContent"));
			String text = txt.getText();
			text = text.replaceAll(wDate, "");
			
			ReviewVO	vo	=	new ReviewVO(this.appid,writer.toString(),	tempDate,	text,	isPositive) ;
			result.add(vo);
			System.out.println("리뷰가 추가되었습니다"+vo);
		}
		
		driver.close();
		return result;
	}
	
	public void getReviews()
	{
		this.positiveReviews	=	getReviews(true);
		this.negativeReviews=getReviews(false);
	}
	
	
	public void	writeObjectFile(String fullPath, Serializable target) throws IOException
	{
		FileOutputStream fos	=	new FileOutputStream(fullPath);
		ObjectOutputStream	listStream	=	new ObjectOutputStream(fos);
		listStream.writeObject(target);
		listStream.flush();
		listStream.close();
	}
	
	public	Object	readObjectFile(String fullPath) throws IOException, ClassNotFoundException
	{
		Object target  = null;
		FileInputStream fos	=	new FileInputStream(fullPath);
		ObjectInputStream	listStream	=	new ObjectInputStream(fos);
		target = listStream.readObject();
		
		listStream.close();
		
		return target;
	}
		
	@SuppressWarnings({ "unchecked", "resource" })
	public  void exportSQLFile(String app_id) throws IOException, ClassNotFoundException{
		
		FileInputStream	fis = new FileInputStream(".\\dat\\"+app_id+"\\"+app_id+"_positiveReview.dat");
		ObjectInputStream	ois = new ObjectInputStream(fis);
		
		ArrayList<ReviewVO> negatives = (ArrayList<ReviewVO>) ois.readObject();
		ois.close();
		fis.close();
		
		PrintWriter output=new PrintWriter(".\\dat\\"+app_id+"\\"+app_id+"_sql.sql");
		for(ReviewVO vo : negatives){
			output.println(vo.getInsertSqlStatement());
		}
		
		fis = new FileInputStream(".\\dat\\"+app_id+"\\"+app_id+"_negativeReview.dat");
		ois = new ObjectInputStream(fis);
		
		ArrayList<ReviewVO> positives = (ArrayList<ReviewVO>)ois.readObject();
		for(ReviewVO vo : positives){
			output.println(vo.getInsertSqlStatement());
		}
		output.flush();
		output.close();
	}
	
	public static void main(String[] arsgs){
		
	}
}








/*keywords = new ArrayList<String>();
for(ReviewVO vo:temp.negativeReviews){
	@SuppressWarnings("unchecked")
	List<List<Pair<String,String>>>	result1 = komoran_full.analyze(vo.getText());
	System.out.println("==========================================================================");
	for(List<Pair<String, String>>search : result1){
		for(Pair<String, String> wordMorph : search){
			if(	wordMorph.getSecond().equals("NNG")||
					wordMorph.getSecond().equals("NNP")||
					wordMorph.getSecond().equals("XR"))
				{
					System.out.println(wordMorph);
					keywords.add(wordMorph.getFirst());
				}
		}
	}
}

count = new KeywordCounter();
count.inputKeywords(keywords);

HashMap<String, Integer>result1 = count.map;
Set<String>	keys1 = result1.keySet();
ArrayList<KeywordVO>	sort1=new ArrayList<KeywordVO>();
for(String str : keys1){
	if(str.length()!=1)
		sort1.add(new KeywordVO(str, result1.get(str)));
}

PrintWriter	negative = new PrintWriter(new File(temp.appid+"_negative.txt"));
Collections.sort(sort1);
for(int i=0; i<sort1.size();i++){
	
	negative.println(sort1.get(i).getKeyword()+"\t"+sort1.get(i).getCount());
}
negative.close();*/
