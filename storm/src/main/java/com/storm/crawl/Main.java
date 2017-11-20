package com.storm.crawl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale.Category;
import java.util.Set;
import java.util.logging.Level;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.web.util.HtmlUtils;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.CookieManager;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.util.Cookie;
import  com.storm.VO.AppVO;
import com.storm.VO.CategoryVO;
import com.storm.VO.CompanyVO;
import com.storm.VO.GenreVO;
import com.storm.VO.LanguageVO;
import  com.storm.VO.TagVO;
import com.storm.crawlUtil.HtmlUnitUtil;
import com.storm.crawlUtil.JSoupUtil;
import com.storm.crawlUtil.KeyGenerator;

public class Main 
{
	String[]	fileNames={
		APP_INFO_FILE, 
		CATEGORY_FILE, 
		GENRE_FILE,
		COMPANY_FILE,
		LANGUAGE_FILE
	};	
	
	HashMap<Integer,AppVO>			appListMap;
	HashMap<Integer, AppVO> 		appInfoMap;
	HashMap<Integer, TagVO>			tagMap;
	HashMap<Integer,CategoryVO>	categoryMap;
	HashMap<Integer,GenreVO>		genreMap;
	HashMap<Integer,CompanyVO>	companyMap;
	HashMap<Integer,LanguageVO>	languageMap;
		
	final static String	APP_INFO_FILE ="APP_INFO_LIST",
					CATEGORY_FILE="CATEGORY_LIST",
					GENRE_FILE="GENRE_LIST",
					COMPANY_FILE="COMPANY_LIST",
					LANGUAGE_FILE="LANGUAGE_LIST",
					TAG_FILE="TAG_LIST",
					APP_KEY_LIST="APP_KEY_LIST";
	//List URL
	String url = "http://store.steampowered.com/search/?category1=998&page=";
	String soma= ("http://store.steampowered.com/app/282140");
	String pubg="http://store.steampowered.com/app/578080/agecheck";
	String dyinglight="http://store.steampowered.com/agecheck/app/239140/";
	String beyoneta="http://store.steampowered.com/app/460790/agecheck";
	String rocketLeague = "http://store.steampowered.com/app/252950/Rocket_League/";
	String mordorGOTY = "http://store.steampowered.com/app/318550/Middleearth_Shadow_of_Mordor__GOTY_Edition_Upgrade/";
	String mordor="http://store.steampowered.com/app/241930/Middleearth_Shadow_of_Mordor/";
	String temp = "http://steamcommunity.com/app/379720/positivereviews/?browsefilter=toprated&snr=1_5_reviews_&filterLanguage=koreana&p=1";
	
	//Document doc = ConnectionJsoup(mordorGOTY);
	String osiris="http://store.steampowered.com/app/402710/Osiris_New_Dawn/";
	String deusEx = "http://store.steampowered.com/app/337000/Deus_Ex_Mankind_Divided/?snr=1_5_9__412";
	//ReconnectAgecheck(deusEx);
	String pubgInfo= "http://store.steampowered.com/app/578080/PLAYERUNKNOWNS_BATTLEGROUNDS/";
	String broforce="http://store.steampowered.com/app/274190/Broforce/";
	//Document doc = ReconnectAgecheck(broforce);
	String l4d="http://store.steampowered.com/app/550/Left_4_Dead_2/";
	
	public static final int APP_VIEW = 1,AGE_CHECK=2,WARNNING=3;
	
	HashMap<String, String> cookies;
	public WebClient webClient;
		
	//현재 페이지 타입을 확인한다.
	public int	checkPageType(String url)
	{
		int testAgeCheck = url.indexOf("agecheck");
		/*System.out.println(testAgeCheck);
		System.out.println(url.length());
		System.out.println(url.length()-testAgeCheck);*/
		
		if(testAgeCheck==-1)
		{//상품 view임
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
	
	//웹 클라이언트 연결 함수
	public HtmlPage ConnectByWebClient(String url)
	{
		//웹 클라이언트 생성 및 설정
		java.util.logging.Logger.getLogger("com.gargoylesoftware").setLevel(java.util.logging.Level.OFF);
		webClient = new WebClient(BrowserVersion.CHROME);
		webClient.waitForBackgroundJavaScript(1);
 
		webClient.addRequestHeader("Accept-Language", "ko-KR,ko;q=0.8,en-US;q=0.6,en;q=0.4, value"); 
		webClient.addRequestHeader("Accept-Charset", "windows-949,utf-8;q=0.7,*;q=0.3"); 
		webClient.getCookieManager().setCookiesEnabled(true); 
		webClient.getOptions().setThrowExceptionOnScriptError(false);
		webClient.getOptions().setJavaScriptEnabled(true);
		webClient.getOptions().setCssEnabled(false);
		
		// page를 받아옴
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
	
	//경고 페이지를 넘어가기 위한 함수..
	public Document ReconnectWarnningPage(String url) throws FailingHttpStatusCodeException, MalformedURLException, IOException
	{
		java.util.logging.Logger.getLogger("com.gargoylesoftware").setLevel(java.util.logging.Level.OFF);
		HtmlPage page = //HtmlUnitUtil.InitWebClient()
				webClient.getPage(url);
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
	
	// 연령체크 페이지를 넘어가기 위한 함수...
	public Document ReconnectAgecheck(String url) throws FailingHttpStatusCodeException, MalformedURLException, IOException
	{
		// 웹 클라이언트 초기화
		java.util.logging.Logger.getLogger("com.gargoylesoftware").setLevel(java.util.logging.Level.OFF);
		HtmlPage page = //HtmlUnitUtil.InitWebClient()
				webClient.getPage(url);
		
		// 페이지 상의 모든 폼을 받아온다.
		List<HtmlForm> formlist = page.getForms();
		
		//연령체크폼 초기화
		HtmlForm ageCheckForm = null;
		
		for(HtmlForm frm : formlist){
			//페이지 상의 폼 중에서 id가 agecheck_form인 요소를 할당
			if(frm.getId().equals("agecheck_form"))
				ageCheckForm = frm;
		}
		
		// POST방식 전송 데이터 할당
		ageCheckForm.getSelectByName("ageDay").setDefaultValue("31");
		ageCheckForm.getSelectByName("ageMonth").setDefaultValue("May");
		ageCheckForm.getSelectByName("ageYear").setDefaultValue("1989");
		
		System.out.println(ageCheckForm.getSelectByName("ageDay").getDefaultValue());
		System.out.println(ageCheckForm.getSelectByName("ageMonth").getDefaultValue());
		System.out.println(ageCheckForm.getSelectByName("ageYear").getDefaultValue());

		//Submit 버튼을 받아옴
		HtmlAnchor button = page.getAnchorByText("Enter");
		HtmlPage result = null;
		System.out.println(button.getHrefAttribute());
		 
		try{	// 버튼 클릭을 하여 다음 페이지를 받아온다
			System.out.println(button.getOnClickAttribute());
			result = button.click();
			//System.out.println(result.getWebResponse());
		}
		catch(Exception e){
			System.out.println(e.toString());
		}
				
		cookies = new HashMap<String, String>();
		CookieManager cookieManager = webClient.getCookieManager();
		java.util.Set<Cookie> cookieSet = cookieManager.getCookies();
		
		for(Cookie c : cookieSet){
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
		Document result	=	(Document)Jsoup.connect(url).get();
		
		System.out.println(result.location());
		
		return result;
	}
		
	/*HtmlPage	test	=	HtmlUnitUtil.ConnectByWebClient(l4d, webClient);
	System.out.println(test.getUrl().toString());
	System.out.println(checkPageType(test.getUrl().toString()));
	
	if(checkPageType(test.getUrl().toString())==2){
		Document	doc	=	ReconnectAgecheck(l4d);
		new AppInfoCrawler(doc).ProccessCrawl();
	}*/
	
	//Document re = HtmlUnitUtil.ReconnectAgecheck(l4d,webClient);
	//new AppInfoCrawler(re).ProccessCrawl();
	
	public Main(String mode) throws IOException 
	{
		if(mode.equals("NEW"))
		{
			java.util.logging.Logger.getLogger("com.gargoylesoftware").setLevel(java.util.logging.Level.OFF);
			webClient = new WebClient(BrowserVersion.CHROME);
			webClient.waitForBackgroundJavaScript(1);
	 
			webClient.addRequestHeader("Accept-Language", "ko-KR,ko;q=0.8,en-US;q=0.6,en;q=0.4, value"); 
			webClient.addRequestHeader("Accept-Charset", "windows-949,utf-8;q=0.7,*;q=0.3"); 
			webClient.getCookieManager().setCookiesEnabled(true); 
			webClient.getOptions().setThrowExceptionOnScriptError(false);
			webClient.getOptions().setJavaScriptEnabled(true);
			webClient.getOptions().setCssEnabled(false);
			

			System.out.println("before the crawl");
			AppListCrawler	appList	=	new AppListCrawler(url);
			appList.ProccessCrawl(System.out);
			System.out.println("after crawl");
			
			@SuppressWarnings("unchecked")
			HashMap<Integer, AppVO>	map = (HashMap<Integer, AppVO>) appList.app_list.clone();
				
			FileOutputStream	listFos	=	new FileOutputStream(APP_KEY_LIST+".dat");
			ObjectOutputStream	listStream	=	new ObjectOutputStream(listFos);
			listStream.writeObject(map);
			listStream.flush();		listStream.close();
			listFos.flush();			listFos.close();
			
			Set<Integer>	keys	=	map.keySet();
			ArrayList<Integer> sortedkeys = new ArrayList<Integer>(keys);
			
			for(TagVO vo : appList.tag_list){
				tagMap.put(vo.getTag_id(), vo);
			}
			listFos		=	new FileOutputStream(TAG_FILE+".dat");
			listStream	=	new ObjectOutputStream(listFos);
			listStream.writeObject(tagMap);		
			listStream.flush();		listStream.close();
			listFos.flush();			listFos.close();
			
			
			appList = null;
			System.gc();
			
			Collections.sort(sortedkeys);
			PrintStream	log	=	new PrintStream(new File("2017-11-17_log.txt"));
			
			int cnt=0;
			for(Integer i : sortedkeys){	
				System.out.println("==============================="+cnt++);
				AppVO	vo	=	map.get(i);
				System.out.println(vo.getUrl());
				System.out.print(i+"\t"+vo.getTitle()+"\t");
				//Document _try = HtmlUnitUtil.SteamConnect(vo.getUrl());
				
				HtmlPage	_try	=	webClient.getPage(vo.getUrl());
				int pageType	=	HtmlUnitUtil.checkPageType(_try.getUrl().toString());
				
				String tempUrl = _try.getUrl().toString();
				
				Document target = null;
				if(pageType==APP_VIEW){
					target = ConnectionJsoup(tempUrl);
				}
				else if(pageType==AGE_CHECK){
					target	=	ReconnectAgecheck(tempUrl);
				}
				else if(pageType==WARNNING){
					target = ReconnectWarnningPage(tempUrl);
				}
		
				AppInfoCrawler	ac	=	new AppInfoCrawler(target);
				ac.appInfo = vo;
				ac.ps = log;
				ac.ProccessCrawl();
			}
			log.flush();
			log.close();
			
			listFos	=	new FileOutputStream(APP_INFO_FILE+".dat");
			listStream	=	new ObjectOutputStream(listFos);
			listStream.writeObject(map);
			listStream.flush();		listStream.close();
			listFos.flush();			listFos.close();
			
			KeyGenerator<String>		categoryGen	=	new	KeyGenerator<String>();
			KeyGenerator	<String>	companyGen	=	new	KeyGenerator<String>();
			KeyGenerator<String> 	genreGen	=	new	KeyGenerator<String>();
			KeyGenerator<String> 	langGen		=	new	KeyGenerator<String>();
			
			for(Integer i : sortedkeys)	{
				AppVO tmp = map.get(i);
				ArrayList<String>	langs = new ArrayList<String>(tmp.langueges.keySet());
				categoryGen.insertArrayList(tmp.categories);
				companyGen.insertArrayList(tmp.developList);
				companyGen.insertArrayList(tmp.developList);
				genreGen.insertArrayList(tmp.genre);
				langGen.insertArrayList(langs);			
			}
			
			HashMap<Integer,CategoryVO>	categoryMap	=	new HashMap<Integer,CategoryVO>();
			Set<Integer>	cateKeyset = categoryGen.map.keySet();
			for(Integer i : cateKeyset){
				categoryMap.put(i,new CategoryVO(i, categoryGen.map.get(i)));
			}
			
			listFos	=	new FileOutputStream(CATEGORY_FILE+".dat");
			listStream	=	new ObjectOutputStream(listFos);
			listStream.writeObject(categoryMap);
			listStream.flush();		listStream.close();
			listFos.flush();			listFos.close();
			
			HashMap<Integer,GenreVO> genreMap=new HashMap<Integer,GenreVO>();
			Set<Integer>	genreKeySet	=	genreGen.map.keySet();
			for(Integer i: genreKeySet){
				genreMap.put(i,new GenreVO(i, genreGen.map.get(i)));
			}
			listFos	=	new FileOutputStream(GENRE_FILE+".dat");
			listStream	=	new ObjectOutputStream(listFos);
			listStream.writeObject(genreMap);
			listStream.flush();		listStream.close();
			listFos.flush();			listFos.close();
			
			HashMap<Integer,CompanyVO> companyMap=new HashMap<Integer,CompanyVO>();
			Set<Integer>	companyKeySet	=	companyGen.map.keySet();
			for(Integer i: companyKeySet){
				companyMap.put(i,new CompanyVO(i, companyGen.map.get(i)));
			}
			listFos	=	new FileOutputStream(COMPANY_FILE+".dat");
			listStream	=	new ObjectOutputStream(listFos);
			listStream.writeObject(companyMap);
			listStream.flush();		listStream.close();
			listFos.flush();			listFos.close();
			
			HashMap<Integer,LanguageVO> languageMap=new HashMap<Integer,LanguageVO>();
			Set<Integer>	languageKeySet	=	langGen.map.keySet();
			for(Integer i: languageKeySet){
				languageMap.put(i, new LanguageVO(i, companyGen.map.get(i),null));
			}
			listFos	=	new FileOutputStream(LANGUAGE_FILE+".dat");
			listStream	=	new ObjectOutputStream(listFos);
			listStream.writeObject(languageMap);
			listStream.flush();		listStream.close();
			listFos.flush();			listFos.close();
		}
		else if(mode.equals("LOAD")){
			loadAppListMap();
			loadAppInfoMap();
			//loadTagMap();
			loadCategoryMap();
			loadLanguageMap();
			loadGenreMap();
		}
	}
	
	@SuppressWarnings("unchecked")
	public  void 	loadAppInfoMap(){
		FileInputStream	fis = null;
		ObjectInputStream	ois	=	null;
		
		try {		fis	=	new FileInputStream(new File(APP_INFO_FILE+".dat"));	}
		catch (FileNotFoundException e) {	e.printStackTrace();			}
		
		if(fis==null)
			return;	
		
		try {		ois	=	new ObjectInputStream(fis);	}
		catch (IOException e) {		e.printStackTrace();	}
		
		if(ois==null)
			return;
		
		try {		this.appInfoMap	=	(HashMap<Integer, AppVO>) ois.readObject();	} 
		catch (ClassNotFoundException e) {	e.printStackTrace();	}
		catch (IOException e) {				e.printStackTrace();	}
	}
	@SuppressWarnings("unchecked")
	public  void 	loadTagMap(){
		FileInputStream	fis = null;
		ObjectInputStream	ois	=	null;
		
		try {		fis	=	new FileInputStream(new File(TAG_FILE+".dat"));	}
		catch (FileNotFoundException e) {	e.printStackTrace();			}
		
		if(fis==null)
			return;	
		
		try {		ois	=	new ObjectInputStream(fis);	}
		catch (IOException e) {		e.printStackTrace();	}
		
		if(ois==null)
			return;
		
		try {		this.tagMap	=	(HashMap<Integer, TagVO>) ois.readObject();	} 
		catch (ClassNotFoundException e) {	e.printStackTrace();	}
		catch (IOException e) {				e.printStackTrace();	}
	}
	@SuppressWarnings("unchecked")
	public  void 	loadAppListMap(){
		FileInputStream	fis = null;
		ObjectInputStream	ois	=	null;
		
		try {		fis	=	new FileInputStream(new File(APP_KEY_LIST+".dat"));	}
		catch (FileNotFoundException e) {	e.printStackTrace();			}
		
		if(fis==null)
			return;	
		
		try {		ois	=	new ObjectInputStream(fis);	}
		catch (IOException e) {		e.printStackTrace();	}
		
		if(ois==null)
			return;
		
		try {		this.appListMap=	(HashMap<Integer, AppVO>) ois.readObject();	} 
		catch (ClassNotFoundException e) {	e.printStackTrace();	}
		catch (IOException e) {				e.printStackTrace();	}
	}
	@SuppressWarnings("unchecked")
	public  void 	loadCategoryMap(){
		FileInputStream	fis = null;
		ObjectInputStream	ois	=	null;
		
		try {		fis	=	new FileInputStream(new File(APP_KEY_LIST+".dat"));	}
		catch (FileNotFoundException e) {	e.printStackTrace();			}
		
		if(fis==null)
			return;	
		
		try {		ois	=	new ObjectInputStream(fis);	}
		catch (IOException e) {		e.printStackTrace();	}
		
		if(ois==null)
			return;
		
		try {		this.categoryMap=	(HashMap<Integer, CategoryVO>) ois.readObject();	} 
		catch (ClassNotFoundException e) {	e.printStackTrace();	}
		catch (IOException e) {				e.printStackTrace();	}
	}
	@SuppressWarnings("unchecked")
	public  void 	loadLanguageMap(){
		FileInputStream	fis = null;
		ObjectInputStream	ois	=	null;
		
		try {		fis	=	new FileInputStream(new File(LANGUAGE_FILE+".dat"));	}
		catch (FileNotFoundException e) {	e.printStackTrace();			}
		
		if(fis==null)
			return;	
		
		try {		ois	=	new ObjectInputStream(fis);	}
		catch (IOException e) {		e.printStackTrace();	}
		
		if(ois==null)
			return;
		
		try {		this.languageMap=	(HashMap<Integer, LanguageVO>) ois.readObject();	} 
		catch (ClassNotFoundException e) {	e.printStackTrace();	}
		catch (IOException e) {				e.printStackTrace();	}
	}
	public  void 	loadGenreMap(){
		FileInputStream	fis = null;
		ObjectInputStream	ois	=	null;
		
		try {		fis	=	new FileInputStream(new File(GENRE_FILE+".dat"));	}
		catch (FileNotFoundException e) {	e.printStackTrace();			}
		
		if(fis==null)
			return;	
		
		try {		ois	=	new ObjectInputStream(fis);	}
		catch (IOException e) {		e.printStackTrace();	}
		
		if(ois==null)
			return;
		
		try {		this.genreMap=	(HashMap<Integer, GenreVO>) ois.readObject();	} 
		catch (ClassNotFoundException e) {	e.printStackTrace();	}
		catch (IOException e) {				e.printStackTrace();	}
	}
		
	public static void main(String[] args) throws IOException
	{
		java.util.logging.Logger.getLogger("com.gargoylesoftware").setLevel(Level.OFF);
		new Main("LOAD");
	}
}

/*		
for(int i=0;i<list.size();i++)
{
	System.out.println("i :"+i+"\tlist.size():"+list.size());
	AppVO	v	=	list.get(i);
	
	String check = v.getUrl();
	System.out.println(check);
	HtmlPage	page = HtmlUnitUtil.ConnectByWebClient(check, webClient);
	System.out.println(page.getUrl().toString());
	int test	=	checkPageType(page.getUrl().toString());
	System.out.println(test);
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
*/		/*
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