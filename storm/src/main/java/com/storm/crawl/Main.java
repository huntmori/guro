package com.storm.crawl;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.CookieManager;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.util.Cookie;
import  com.storm.VO.AppVO;
import  com.storm.VO.TagVO;
import com.storm.crawlUtil.HtmlUnitUtil;
import com.storm.crawlUtil.JSoupUtil;

public class Main 
{
	public static final int APP_VIEW = 1,
											AGE_CHECK=2,
											WARNNING=3;
	
	HashMap<String, String> cookies;
	public WebClient webClient;
	
	public ArrayList<TagVO> getAppTagList(Document doc, ArrayList<TagVO> allTags)
	{
		ArrayList<TagVO> result = new ArrayList<TagVO>();
		
		Elements tags= doc.getElementsByClass("glance_tags_label");
		System.out.println(tags.size());
		tags = tags.select("a[href][class]");
		
		for(Element e : tags)
		{
			if(e.className().equals("app_tag"))
			{
				String tagName = e.text();
				//System.out.println(tagName);
				result.add(new TagVO());
			}
		}
		
		
		return result;
	}
	
	/*public ArrayList getAppInfo(String url) throws IOException
	{
		ArrayList	result	=	new ArrayList<AppVO>();
		
		Document document = (Document)Jsoup.connect(url).get();
		
		Elements metas = document.select("meta");
		
		System.out.println(metas.size());
		//metas = metas.select("meta");
		for(Element e:metas)
		{
			System.out.println(e.text());
			System.out.println(e.attr("itemprop")+"\t"+e.attr("content"));
		}
			
		return result;
	}*/
	
	public void getListInfo(String url) throws IOException
	{
		int page=1;
				
		Document doc = (Document) Jsoup.connect(url).get();
		//Document doc = (Document)Jsoup.connect("./Steam.html");
/*		String title = doc.title();
		
		
		ArrayList<String>	titleList	= new ArrayList<String>();
		ArrayList<String>	appURLList	= new ArrayList<String>();
		ArrayList<String>	appIdList	= new ArrayList<String>();*/
		
		ArrayList<AppVO>	infoList	=	new ArrayList<AppVO>();
		
		PrintWriter output = new PrintWriter(new File("app_list.txt"));
		output.println("title\tappid\turl");
		//set max page numb
		
			
			/*for(Element e : ele )
			{
				String temp = e.text().replaceAll(" ","_");
				//System.out.println(temp);
				titleList.add(temp);
			}
			
			
			for(Element e:linkList)
			{
				appURLList.add(e.attr("href"));
			}
			
			
			for(Element e : links)
			{
				String href = e.attr("data-ds-appid");
				appIdList.add(href);
			}*/
		
		
		
		
		/*for(int i=0; i<titleList.size(); i++)
		{
			String appid = (String)appIdList.get(i);
			int test = appid.indexOf(",");
			
			String strurl = (String)appURLList.get(i);
			int test2 = strurl.indexOf("app");
			
			if(test==-1 && test2!=-1)
			{
				System.out.println(titleList.get(i)+"\t"+appIdList.get(i)+"\t"+appURLList.get(i));
				output.println(titleList.get(i)+"\t"+appIdList.get(i)+"\t"+appURLList.get(i));
			}				
		}		*/
		
	}
	
	
	//���� ������ Ÿ���� Ȯ���Ѵ�.
	public int	checkPageType(String url)
	{
		int testAgeCheck = url.indexOf("agecheck");
		/*System.out.println(testAgeCheck);
		System.out.println(url.length());
		System.out.println(url.length()-testAgeCheck);*/
		
		if(testAgeCheck==-1)
		{//��ǰ view��
			System.out.println("APP VIEW");
			return APP_VIEW;
		}
		
		else if(url.length()-testAgeCheck==8)
		{// warning page 8 
			System.out.println("WarnningPage");
			return WARNNING;
		}
		else
		{//	AgeCheckPage
			System.out.println("ageCheckPage");
			return AGE_CHECK;
		}
	}
	
	//�� Ŭ���̾�Ʈ ���� �Լ�
	public HtmlPage ConnectByWebClient(String url)
	{
		//�� Ŭ���̾�Ʈ ���� �� ����
		
		webClient = new WebClient(BrowserVersion.CHROME);
	    webClient.waitForBackgroundJavaScript(5000);
 
		webClient.addRequestHeader("Accept-Language", "ko-KR,ko;q=0.8,en-US;q=0.6,en;q=0.4, value"); 
		webClient.addRequestHeader("Accept-Charset", "windows-949,utf-8;q=0.7,*;q=0.3"); 
		webClient.getCookieManager().setCookiesEnabled(true); 
		webClient.getOptions().setThrowExceptionOnScriptError(false);
		webClient.getOptions().setJavaScriptEnabled(true);
		webClient.getOptions().setCssEnabled(true);
		java.util.logging.Logger.getLogger("com.gargoylesoftware").setLevel(java.util.logging.Level.OFF);
		 
		//URL ���ڵ�
		/*try {
			System.out.println(url);
			System.out.println(URLEncoder.encode(url,"UTF-8"));
			System.out.println(url);
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			//e1.printStackTrace();
		}*/
		
		// page�� �޾ƿ�
		HtmlPage page = null;
		try {
			page = webClient.getPage(url);
		}
		catch (FailingHttpStatusCodeException | IOException e1){
			// TODO Auto-generated catch block
			//e1.printStackTrace();
		}
		return page;
	}
	
	//��� �������� �Ѿ�� ���� �Լ�..
	public Document ReconnectWarnningPage(String url)
	{
		HtmlPage page = ConnectByWebClient(url);
		HtmlAnchor button = page.getAnchorByText("View Page");
		
		HtmlPage result = null;
		try {
			result = (HtmlPage)button.click();
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		checkPageType(result.getUrl().toString());
		
		cookies = new HashMap<String, String>();
		CookieManager cookieManager = webClient.getCookieManager();
		java.util.Set<Cookie> cookieSet = cookieManager.getCookies();
		
		for(Cookie c : cookieSet)
		{
			cookies.put(c.getName(), c.getValue());
		}
		
		Document doc = null;
		try {
			doc = Jsoup.connect(url).cookies(cookies).get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		//System.out.println(doc.toString());
		return doc;
	}
	
	// ����üũ �������� �Ѿ�� ���� �Լ�...
	public Document ReconnectAgecheck(String url)
	{
		// �� Ŭ���̾�Ʈ �ʱ�ȭ
		HtmlPage page = ConnectByWebClient(url);
		
		// ������ ���� ��� ���� �޾ƿ´�.
		List<HtmlForm> formlist = page.getForms();
		
		//����üũ�� �ʱ�ȭ
		HtmlForm ageCheckForm = null;
		
		for(HtmlForm frm : formlist){
			//������ ���� �� �߿��� id�� agecheck_form�� ��Ҹ� �Ҵ�
			if(frm.getId().equals("agecheck_form"))
				ageCheckForm = frm;
		}
		
		// POST��� ���� ������ �Ҵ�
		ageCheckForm.getSelectByName("ageDay").setDefaultValue("31");
		ageCheckForm.getSelectByName("ageMonth").setDefaultValue("May");
		ageCheckForm.getSelectByName("ageYear").setDefaultValue("1989");
		
		System.out.println(ageCheckForm.getSelectByName("ageDay").getDefaultValue());
		System.out.println(ageCheckForm.getSelectByName("ageMonth").getDefaultValue());
		System.out.println(ageCheckForm.getSelectByName("ageYear").getDefaultValue());

		//Submit ��ư�� �޾ƿ�
		HtmlAnchor button = page.getAnchorByText("Enter");
		HtmlPage result = null;
		System.out.println(button.getHrefAttribute());
		 
		try
		{
			// ��ư Ŭ���� �Ͽ� ���� �������� �޾ƿ´�
			System.out.println(button.getOnClickAttribute());
			result = button.click();
			//System.out.println(result.getWebResponse());
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
		}
		
		checkPageType(result.getUrl().toString());
		
		cookies = new HashMap<String, String>();
		CookieManager cookieManager = webClient.getCookieManager();
		java.util.Set<Cookie> cookieSet = cookieManager.getCookies();
		
		for(Cookie c : cookieSet)
		{
			cookies.put(c.getName(), c.getValue());
		}
		
		Document doc = null;
		try {
			doc = Jsoup.connect(url).cookies(cookies).get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		//System.out.println(doc.toString());
		return doc;
	}
	
	public void resetObject()
	{
		this.cookies	=	null;
		this.webClient	=	null;
	}
	
	public static Document ConnectionJsoup(String url) throws IOException
	{
		//birthtime=-63190799; path=/
		//lastagecheckage=1-January-1968;
		//expires=Sat, 27-Oct-2018 14:59:26 GMT; Max-Age=31536000; path=/
		Document result	=	(Document)Jsoup.connect(url).get();
		
		System.out.println(result.location());
		
		return result;
	}
		
	public Main() throws IOException 
	{
		//List URL
		String url = "http://store.steampowered.com/search/?category1=998&page=";
		String soma= ("http://store.steampowered.com/app/282140");
		String pubg="http://store.steampowered.com/app/578080/agecheck";
		String dyinglight="http://store.steampowered.com/agecheck/app/239140/";
		String beyoneta="http://store.steampowered.com/app/460790/agecheck";
		String rocketLeague = "http://store.steampowered.com/app/252950/Rocket_League/";
		String mordorGOTY = "http://store.steampowered.com/app/318550/Middleearth_Shadow_of_Mordor__GOTY_Edition_Upgrade/";
		String	mordor="http://store.steampowered.com/app/241930/Middleearth_Shadow_of_Mordor/";
		String temp = "http://steamcommunity.com/app/379720/positivereviews/?browsefilter=toprated&snr=1_5_reviews_&filterLanguage=koreana&p=1";
		
		//Document doc = ConnectionJsoup(mordorGOTY);
		String osiris="http://store.steampowered.com/app/402710/Osiris_New_Dawn/";
		String deusEx = "http://store.steampowered.com/app/337000/Deus_Ex_Mankind_Divided/?snr=1_5_9__412";
		//ReconnectAgecheck(deusEx);
		String pubgInfo= "http://store.steampowered.com/app/578080/PLAYERUNKNOWNS_BATTLEGROUNDS/";
		String broforce="http://store.steampowered.com/app/274190/Broforce/";
		//Document doc = ReconnectAgecheck(broforce);
		
		AppListCrawler	appList	=	new AppListCrawler(url);
		appList.ProccessCrawl(System.out);
		
		ArrayList<AppVO>	list	=	appList.app_list;
		for(AppVO v:list)
		{
			String check = v.getUrl();
			int test	=	checkPageType(check);
			
			Document doc = null;
			if(test==APP_VIEW){
				doc = JSoupUtil.ConnectionJsoup(check);
			}
			else if(test==WARNNING){
				doc = ReconnectWarnningPage(check);
			}
			else if(test==AGE_CHECK){
				doc = ReconnectAgecheck(check);
			}
			AppInfoCrawler	info	=	new AppInfoCrawler(doc);	
			info.ProccessCrawl();
		}
		/*
		AppReviewCrawler rv = new AppReviewCrawler("578080");
		System.out.println(rv.getNextPositivePageUrl(2));
		rv.test();
		*/
		
		/*Document doc = JSoupUtil.ConnectionJsoup(broforce);
		new AppInfoCrawler(doc).ProccessCrawl();*/
		//ReconnectWarnningPage(pubg);
		
		/*
		AppReviewCrawler rv = new AppReviewCrawler();
		rv.appid = "402710";
		System.out.println("Positive");
		rv.test(rv.getPositiveURL());
		System.out.println("============================");
		System.out.println("Negative");
		rv.test(rv.getNegativeURL());
		System.out.println("============================");
		getAppTagList(doc, null);
		String assassin = "http://store.steampowered.com/app/582160/Assassins_Creed_Origins/";
		AppInfoCrawler app=new AppInfoCrawler(osiris);
		app.ProccessCrawl();*/
		/*Map<String, String> ageCheckData = new HashMap<String, String>();
		
		ageCheckData.put("ageDay", "23");
		ageCheckData.put("ageMonth", "May");
		ageCheckData.put("ageYear", "1989");
		
		java.util.logging.Logger.getLogger("com.gargoylesoftware.htmlunit").setLevel(java.util.logging.Level.OFF); 
		java.util.logging.Logger.getLogger("org.apache.http").setLevel(java.util.logging.Level.OFF);

		
		
		
		String listurl = "http://store.steampowered.com/search/?category1=998&page=";
		AppListCrawler appList = new AppListCrawler(listurl);
		appList.ProccessCrawl(new PrintStream(new File("app_list.txt")));
		
		//System.out.println();
		
		//System.out.println(deus.getElementsByClass("app_tag").get(0).text());*/
	}
	public static void main(String[] args) throws IOException
	{
		new Main();
	}
}