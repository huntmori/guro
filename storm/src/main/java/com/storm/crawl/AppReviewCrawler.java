package com.storm.crawl;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


//view-source:
public class AppReviewCrawler 
{
	public	String	appid;
	public	Document	document;
	
	public void test() throws IOException
	{
		String url = "http://steamcommunity.com/app/282140/positivereviews/?browsefilter=toprated&snr=1_5_reviews_&filterLanguage=koreana#scrollTop=0";
		document = Jsoup.connect(url).get();
		
		Elements content = document.getElementsByClass("apphub_CardTextContent");
		
		for(Element e : content)
		{
			System.out.println(e.text());
		}
		
	}
	public void test(String url) throws IOException
	{
		document = Jsoup.connect(url).get();
		
		Elements content = document.getElementsByClass("apphub_CardTextContent");
		
		for(Element e : content)
		{
			System.out.println(e.text());
		}
		
	}
	public AppReviewCrawler(String appid) throws IOException
	{
		this.appid=appid;
		test();
		
	}
	public AppReviewCrawler() throws IOException
	{
		this("");
	}
	
	public String getPositiveURL()
	{
		return "http://steamcommunity.com/app/"+
				appid+
				"/negativereviews/"+
				"?browsefilter=toprated&"+
				"snr=1_5_reviews_&filterLanguage=koreana";
	}
	public String getNegativeURL()
	{
		return "http://steamcommunity.com/app/"+
				appid+
				"/positivereviews/"+
				"?browsefilter=toprated&"+
				"snr=1_5_reviews_&filterLanguage=koreana";
	}
	
	public String getNextNegativePageUrl(int i)
	{
		return "http://steamcommunity.com/app/"
				+ appid+"/homecontent/?userreviewsoffset=10&"+
				"p="+i
				+"&workshopitemspage="+i
				+"&readytouseitemspage="+i
				+"&mtxitemspage="+i
				+"&itemspage="+i
				+"&screenshotspage="+i
				+"&videospage="+i
				+"&artpage="+i
				+"&allguidepage="+i
				+"&webguidepage="+i
				+"&integratedguidepage="+i
				+"&discussionspage="+i
				+ "&numperpage=10"
				+ "&browsefilter=toprated"
				+ "&browsefilter=toprated"
				+ "&l=koreana"
				+ "&appHubSubSection=10"
				+ "&filterLanguage=koreana"
				+ "&searchText="
				+ "&forceanon=1";
	}
	public String getNextPositivePageUrl(int i)
	{
		return "http://steamcommunity.com/app/"
				+ appid+"/homecontent/?userreviewsoffset=10&"+
				"p="+i
				+"&workshopitemspage="+i
				+"&readytouseitemspage="+i
				+"&mtxitemspage="+i
				+"&itemspage="+i
				+"&screenshotspage="+i
				+"&videospage="+i
				+"&artpage="+i
				+"&allguidepage="+i
				+"&webguidepage="+i
				+"&integratedguidepage="+i
				+"&discussionspage="+i
				+ "&numperpage=10"
				+ "&browsefilter=toprated"
				+ "&browsefilter=toprated"
				+ "&l=koreana"
				+ "&appHubSubSection=16"
				+ "&filterLanguage=koreana"
				+ "&searchText="
				+ "&forceanon=1";
	}
}
