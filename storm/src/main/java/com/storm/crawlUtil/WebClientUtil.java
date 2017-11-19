package com.storm.crawlUtil;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;

public class WebClientUtil extends WebClient {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String url;
	
	public WebClientUtil(BrowserVersion version)
	{
		super(version);
		java.util.logging.Logger.getLogger("com.gargoylesoftware").setLevel(java.util.logging.Level.OFF);
		
		this.waitForBackgroundJavaScript(1); 
		this.addRequestHeader("Accept-Language", "ko-KR,ko;q=0.8,en-US;q=0.6,en;q=0.4, value"); 
		this.addRequestHeader("Accept-Charset", "windows-949,utf-8;q=0.7,*;q=0.3"); 
		this.getCookieManager().setCookiesEnabled(true); 
		this.getOptions().setThrowExceptionOnScriptError(false);
		this.getOptions().setJavaScriptEnabled(true);
		this.getOptions().setCssEnabled(false);
	}
}
