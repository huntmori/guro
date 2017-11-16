package com.storm.crawlUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.CookieManager;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.util.Cookie;

public class HtmlUnitUtil 
{
		public static final int APP_VIEW = 1,	AGE_CHECK=2,	WARNNING=3;
		
		public static WebClient	InitWebClient()
		{
			java.util.logging.Logger.getLogger("com.gargoylesoftware").setLevel(java.util.logging.Level.OFF);
			WebClient webClient = new WebClient(BrowserVersion.CHROME);
			webClient.waitForBackgroundJavaScript(5000);
	 
			webClient.addRequestHeader("Accept-Language", "ko-KR,ko;q=0.8,en-US;q=0.6,en;q=0.4, value"); 
			webClient.addRequestHeader("Accept-Charset", "windows-949,utf-8;q=0.7,*;q=0.3"); 
			webClient.getCookieManager().setCookiesEnabled(true); 
			webClient.getOptions().setThrowExceptionOnScriptError(false);
			webClient.getOptions().setJavaScriptEnabled(true);
			webClient.getOptions().setCssEnabled(false);
		    
		
			return webClient;
		}
	
		public static HtmlPage ConnectByWebClient(String url, WebClient webClient)
		{
			//�� Ŭ���̾�Ʈ ���� �� ����
		
			webClient = InitWebClient();
		 
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
	
		public static int	checkPageType(String url)
		{
			int testAgeCheck = url.indexOf("agecheck");
			/*System.out.println(testAgeCheck);
			System.out.println(url.length());
			System.out.println(url.length()-testAgeCheck);*/
		
			if(testAgeCheck==-1){//��ǰ view��
			System.out.println("APP VIEW");
				return APP_VIEW;
			}		
			else if(url.length()-testAgeCheck==8){// warning page 8 
				System.out.println("WarnningPage");
				return WARNNING;
			}
			else	{//	AgeCheckPage
				System.out.println("ageCheckPage");
					return AGE_CHECK;
			}
		}
		
		//��� �������� �Ѿ�� ���� �Լ�..
		public static Document ReconnectWarnningPage(String url, WebClient webClient)
		{
			HtmlPage page = ConnectByWebClient(url, webClient);
			HtmlAnchor button = page.getAnchorByText("View Page");
		
			HtmlPage result = null;
			try {
				result = (HtmlPage)button.click();			
			} catch (Exception e) {
			// TODO: handle exception
			}
			checkPageType(result.getUrl().toString());
		
			HashMap<String, String> cookies = new HashMap<String, String>();
			CookieManager cookieManager = webClient.getCookieManager();
			java.util.Set<Cookie> cookieSet = cookieManager.getCookies();
			
			for(Cookie c : cookieSet)	{
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
		public static Document ReconnectAgecheck(String url, WebClient webClient)
		{
				// �� Ŭ���̾�Ʈ �ʱ�ȭ
			HtmlPage page = ConnectByWebClient(url, webClient);
			
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
			
			HashMap<String, String> cookies = new HashMap<String, String>();
			CookieManager cookieManager = webClient.getCookieManager();
			java.util.Set<Cookie> cookieSet = cookieManager.getCookies();
			
			for(Cookie c : cookieSet)	{
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
		
		
		public static Document	SteamConnect(String url) throws IOException
		{
			Document document = null;
			java.util.logging.Logger.getLogger("com.gargoylesoftware.htmlunit").setLevel(Level.OFF); 
			final	WebClient webClient	=	InitWebClient();
			final	HtmlPage test	=	ConnectByWebClient(url, webClient);
			
			int check = checkPageType(test.getUrl().toString());
			if(check==APP_VIEW){
				return JSoupUtil.ConnectionJsoup(url);
			}
			else if(check==AGE_CHECK)
				return	ReconnectAgecheck(url, webClient);
			else if(check==WARNNING)
				return	ReconnectWarnningPage(url, webClient);
			
			return document;
		}
}
