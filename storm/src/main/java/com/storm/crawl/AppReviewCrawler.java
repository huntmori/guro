package com.storm.crawl;

import java.awt.AWTException;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import com.storm.VO.ReviewVO;


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
			
			System.out.println(driver.getPageSource());
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
	
	
	public static void main(String[] args) throws AWTException, InterruptedException
	{
		long start = System.currentTimeMillis();
		AppReviewCrawler	temp = new AppReviewCrawler();
		temp.appid="491710";
		temp.getReviews();
		long end;
		for(ReviewVO vo : temp.positiveReviews){
			System.out.println("\n"+vo);
			end	=System.currentTimeMillis();
			System.out.println((end-start)/1000.0f);
		}
			
		
		for(ReviewVO vo : temp.negativeReviews)
		{
			System.out.println("\n"+vo);
			end	=System.currentTimeMillis();
			System.out.println((end-start)/1000.0f);
		}
			
		
		
		end	=System.currentTimeMillis();
		
		System.out.println((end-start)/1000.0f);
	}
}
//https://partner.steam-api.com/ISteamApps/GetAppBetas/v1/?key=B45C6883EC5C4506EBFE09C125E7B758&appid=47780
//https://api.steampowered.com/ISteamApps/GetAppList/v2/?key=B45C6883EC5C4506EBFE09C125E7B758
