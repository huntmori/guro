package com.storm.crawl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.web.util.HtmlUtils;

import com.gargoylesoftware.htmlunit.CookieManager;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlButton;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.util.Cookie;
import com.storm.crawlUtil.HtmlUnitUtil;


//view-source:
public class AppReviewCrawler 
{
	public	String	appid;
	public	Document	document;
	
	public void test() throws IOException
	{
		String url = "http://steamcommunity.com/app/282140/positivereviews/?browsefilter=toprated&snr=1_5_reviews_&filterLanguage=koreana#scrollTop=0";
		document = Jsoup.connect(url).get();
		
		Elements forms = document.getElementsByTag("form");
		forms.get(0);
		
		WebClient	webClient = HtmlUnitUtil.InitWebClient();
		
		webClient.getCurrentWindow().setInnerHeight(Integer.MAX_VALUE);
		HtmlPage page = HtmlUnitUtil.ConnectByWebClient(url, webClient);
		
		System.out.println(page.getForms().get(0).getOnSubmitAttribute());;
		
		
		HtmlForm	form = page.getFormByName("MoreContentForm1");
		HtmlAnchor button = page.getAnchorByText("See More Content");
		System.out.println(button.asText());
		webClient.getCurrentWindow().setInnerHeight(Integer.MAX_VALUE);
		HtmlPage result = button.click();
		
		HashMap<String, String>cookieMap = new HashMap<String,String>();
		CookieManager 	cookieManger = webClient.getCookieManager();
		
		Set<Cookie>	cookieSet	=	cookieManger.getCookies();
		for(Cookie c:cookieSet)
		{
			cookieMap.put(c.getName(), c.getValue());
		}
		
		Document	doc = null;
		try
		{
			doc = Jsoup.connect(url).cookies(cookieMap).get();
			
		}catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.toString());
		}
		
		System.out.println(doc.toString());
		
		
		System.out.println(form.getActionAttribute());
		
		
		HtmlAnchor	next = page.getAnchorByHref("javascript:CheckForMoreContent();");
		System.out.println(next.asText());
		
		//document.getElementById(id)
		
		
		
		
		
		
		/*
		for(int i=1;i<100;i++)
		{
			Elements content = document.getElementsByClass("apphub_CardTextContent");
			
			for(int j =0;j<content.size(); j++)
			{
				System.out.println(i+"page\tNO:"+j);
				System.out.println(content.get(j).text());
			}
			System.out.println(getNextPositivePageUrl(2));
			document = (Document) Jsoup.connect(getNextPositivePageUrl(i));
		}	*/
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
		//test();
		
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

	//public 
}
