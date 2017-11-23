package com.storm.crawl;

import java.awt.AWTException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.storm.CrawlVO.KeywordVO;
import com.storm.CrawlVO.ReviewVO;
import com.storm.crawlUtil.KeywordCounter;
import com.storm.crawlUtil.WordExtractor;


//view-source:
public class AppReviewCrawler 
{
	public	String	appid;
	
	ArrayList<ReviewVO>	positiveReviews;
	ArrayList<ReviewVO>	negativeReviews;
	
	public String	getPositivePageURL(){
		return "http://steamcommunity.com/app/"+this.appid+"/positivereviews/?browsefilter=toprated&snr=1_5_reviews_&filterLanguage=koreana";
	}
	public String getNegativePageURL(){
		return "http://steamcommunity.com/app/"+this.appid+"/negativereviews/?browsefilter=toprated&snr=1_5_reviews_&filterLanguage=koreana";
	}
	
	
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
		
		WebElement nomore = driver.findElement(By.className("apphub_NoMoreContent"));
		JavascriptExecutor jse = ((JavascriptExecutor) driver);
		
		while(!nomore.isDisplayed())
		{
			
			jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
			
			nomore = driver.findElement(By.className("apphub_NoMoreContent"));
			
			jse.executeScript("window.scrollTo(0, 0)");
		}jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		
		
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
			
			WebElement txt = temp.findElement(By.className("apphub_CardTextContent"));
			String text = txt.getText();
			text = text.replaceAll(wDate, "");
			
			ReviewVO	vo	=	new ReviewVO(this.appid,writer.toString(),	wDate,	text,	isPositive) ;
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
	
	
	public static void main(String[] args) throws AWTException, InterruptedException, FileNotFoundException
	{
		AppReviewCrawler	temp = new AppReviewCrawler();
		temp.appid="286000";
		temp.getReviews();// 리뷰 크롤링
		
		//키워드 추출
		WordExtractor	we	=	new WordExtractor();
		ArrayList<String>	positive_keywords = we.getKeywordsArrayList(temp.positiveReviews);
		ArrayList<String>	negative_keywords = we.getKeywordsArrayList(temp.negativeReviews);
		
		// word count util		
		KeywordCounter	count = new KeywordCounter();
		count.inputKeywords(positive_keywords);
		ArrayList<KeywordVO> positiveWordCount = count.getSortedKeywordsList();
		Collections.sort(positiveWordCount);
		
		System.out.println("Positive=======================================================================");
		for(KeywordVO vo : positiveWordCount){
			System.out.println(vo.getKeyword()+"\t"+vo.getCount());
		}
		
		count = new KeywordCounter();
		count.inputKeywords(negative_keywords);
		ArrayList<KeywordVO>	negativeWordCount = count.getSortedKeywordsList();
		Collections.sort(negative_keywords);
		
		
		System.out.println("Negative=======================================================================");
		for(KeywordVO vo : negativeWordCount){
			System.out.println(vo.getKeyword()+"\t"+vo.getCount());
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
		
	}
}
//https://partner.steam-api.com/ISteamApps/GetAppBetas/v1/?key=B45C6883EC5C4506EBFE09C125E7B758&appid=47780
//https://api.steampowered.com/ISteamApps/GetAppList/v2/?key=B45C6883EC5C4506EBFE09C125E7B758
