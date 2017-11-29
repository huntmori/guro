package com.storm.crawl;

import java.io.IOException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.StringTokenizer;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.storm.CrawlVO.AppVO;
import com.storm.crawlUtil.JSoupUtil;

public class AppInfoCrawler 
{
	Document 	document;
	String			url;
	PrintStream	ps;
	
	AppVO		appInfo;
	public AppInfoCrawler(Document doc)
	{
		this.document=doc;
		appInfo = new AppVO();
		
		ps = System.out;
	}
	public AppInfoCrawler(String url) throws IOException
	{
		this.url = url;
		
		document = JSoupUtil.ConnectionJsoup(url);
		ps.println(document.title());
		
		appInfo = new AppVO();
		ps	=	System.out;
	}
	
	public void setPrintStream(PrintStream ps)
	{
		this.ps = ps;
	}
	public String	getFullHeaderImage(Document document){
		String	result = new String();
		
		Element image = null;
		
		try{
			image = document.getElementsByClass("game_header_image_full").get(0);
			result = image.attr("src");
		}catch (Exception e) {
			
		}
		
		return result;
	}
	// 카테고리에 대한 ArrayList반환
	public	ArrayList<String>getCategoryList(Document document)
	{
		ArrayList<String>	result	=	new ArrayList<String>();
		
		//Categories	
		ps.println("\nCategories");
		//Spec Detial 요소를 검색
		Elements specDetail	=	null;
		try{
			specDetail = document.getElementsByClass("game_area_details_specs");
		}
		catch (Exception e) {
			return null;
		}
		
		
		for(Element e : specDetail)	{
			//anchor 태그를 찾아온다.
			String category = e.select("a[class=name]").text();
			ps.print(category+"     ");
			result.add(category);
		}
		ps.println();
		
		return result;
	}
	
	//태그리스트를 얻어오는 함수
	public	ArrayList<String>getAppTagList(Document document)
	{
		ArrayList<String>	result	=	new ArrayList<String>();

		//TagList 요소 선택
		Elements tagList =null;
		try{
			tagList=	document.getElementsByClass("app_tag");
		}
		catch (Exception e) {
			// TODO: handle exception
			return null;
		}
			
		
		//ps.println(tagList.size());
		ps.print("tags : [");
		for(Element e : tagList)	{
			//+ 문자열을 제외 한 모든 택스트를 리스트에 추가한다.
			String text = e.text();
			if(text.indexOf('+')==-1){
				result.add(text);
				ps.print(text+"    ");
			}
		}
		ps.println(" ] ");
		
		return result;
	}
	
	// 상품 설명을 반환하는 함수
	public	String	getAppDescription(Document document)
	{
		// Description
		Element discription = null;
		try{
			discription	=	document.select("div.game_description_snippet").first();
		}catch (Exception e) {
			return "";
		}
		
		if(discription!=null){
			ps.print(discription.text());
			discription.text();
		}
		else{
			return "";
		}
		ps.println();
		
		return discription.text();
	}
	
	//상품 출시일을 반환하는 함수
	public	String	getReleaseDate(Document document)
	{
		//ReleaseDate
		Elements details = null;
		String[]	monthNames={
				"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"
		};
		try{
			details = document.getElementsByClass("details_block");
		}
		catch (Exception e) {
			return "";
		}
		
		//Element detail = details.get(0);
		
		Element date = document.select("div.date").first();
		if(date==null)
			return "";
		ps.println("ReleaseDate : "+date.text());
		
		String wDate = date.text();
		wDate = wDate.replaceAll("Posted: ", "");
		System.out.println(wDate);
		StringTokenizer	st	=	new StringTokenizer(wDate, " ,");
		String strDate = st.nextToken().replaceAll(" ", "");
		System.out.println("strDate:\t"+strDate);
		int day=-1;
		int month = -1;
		int year = -1;
		try{
			day = Integer.parseInt(strDate);
			System.out.println("day:\t"+day);
			String tempMonth = st.nextToken().replaceAll(" ", "");
			System.out.println("tempMonth:\t"+tempMonth);
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
			String dayToken =st.nextToken().replaceAll(" ", "");
			 day = Integer.parseInt(dayToken.replaceAll("th", ""));
			 System.out.println("day:\t"+day);
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
			
		String tempDate = year+"-"+month+"-"+day;
		System.out.println("TEMP DATE:\t"+tempDate);
		
		return tempDate;
	}
	
	//할인률을 가져오는 함수
	public	float	getDiscountRate(Document document)
	{
		Element discountRate = document.getElementsByClass("discount_pct").get(0);
		String strDiscount = discountRate.text();
		strDiscount = strDiscount.replaceAll("-", "");
		strDiscount = strDiscount.replaceAll("%", "");
		ps.println("Discounted rate : "+(Integer.parseInt(strDiscount) / 100.0f));
		return Integer.parseInt(strDiscount) / 100.0f;
	}
	
	public	boolean		getIsDiscounted(Document	document)
	{
		Elements price = document.getElementsByClass("game_purchase_price price");
		if(price.size()==0)
		{
			ps.println("discounted now");
			return true;
		}
		ps.println("not discounted");
		return false;
	}
	
	public int	getDiscountedPrice(Document document)
	{
		Element discounted = document.getElementsByClass("discount_final_price").get(0);
		
		String strPrice = discounted.text();
		strPrice = strPrice.replaceAll(",", "");
		strPrice = strPrice.replaceAll(" ", "");
		
		int result = Integer.parseInt(strPrice);
		ps.println("Discounted Price : "+result);
		return result;
	}
	
	//가격을 가져오는 함수
	public int	getPrice(Document document)
	{
		//Price
		Elements price = null;
		try{
			price = document.getElementsByClass("game_purchase_price price");
			
			if(price==null)//할인 가격 인 경우 처리
			{
				try{
					price = document.getElementsByClass("discount_original_price");
				}catch (Exception e) {
					return -1;
				}
			}
			if(price.size()==0){
				return -1;
			}
			
			String strPrice = price.get(0).text();
			System.out.println("STR_PRICE : \t"+strPrice);
			//단위 표시를 잘라낸다
			if(strPrice.indexOf("demo")!=-1){
				strPrice	=	price.get(1).text();
			}
				
			System.out.println(strPrice);
			if(strPrice.indexOf("Free")!=-1){
				return 0;
			}
			if(strPrice.indexOf("Third")!=-1){
				return -1;
			}
			strPrice	=	strPrice.substring(1);
			//콤마와 공백을 모두 제거
			strPrice = strPrice.replaceAll(",", "");
			strPrice = strPrice.replaceAll(" ", "");
			ps.println("Origin PRICE : "+strPrice);
			//appInfo.price = Integer.parseInt(strPrice);
			
			int result = 0;
			try{
				result = Integer.parseInt(strPrice);
			}catch (Exception e) {
				return -1;
			}
			System.out.println("result PRICE : " +result);
			return result;
		}
		catch (Exception e) {	
			return -1;
		}
		
		
	/*	if(price==null)//할인 가격 인 경우 처리
		{
			try{
				price = document.getElementsByClass("discount_original_price");
			}catch (Exception e) {
				return -1;
			}
		}
		if(price.size()==0){
			return -1;
		}*/
		/*String strPrice = price.get(0).text();
		System.out.println("STR_PRICE : \t"+strPrice);
		//단위 표시를 잘라낸다
		if(strPrice.indexOf("demo")!=-1){
			strPrice	=	price.get(1).text();
		}
			
		System.out.println(strPrice);
		if(strPrice.indexOf("Free")!=-1){
			return 0;
		}
		if(strPrice.indexOf("Third")!=-1){
			return -1;
		}
		strPrice	=	strPrice.substring(1);
		//콤마와 공백을 모두 제거
		strPrice = strPrice.replaceAll(",", "");
		strPrice = strPrice.replaceAll(" ", "");
		ps.println("Origin PRICE : "+strPrice);
		//appInfo.price = Integer.parseInt(strPrice);
		
		int result = 0;
		try{
			result = Integer.parseInt(strPrice);
		}catch (Exception e) {
			return -1;
		}
		System.out.println("result PRICE : " +result);
		return result;*/
	}
	
	// 개발사 목록을 가져오는 함수
	public ArrayList<String>getAppDevelopers(Document document)
	{
		ArrayList<String>	result	=	new ArrayList<String>(); 

		Elements details = null;
		try{
			details = document.getElementsByClass("details_block");
		}
		catch (Exception e) {
			return null;
		}
				
		//Element detail = details.get(0);
		
		//Div, publisher, genre
		ps.print("Developer : ");
		for(Element detail : details){
			Elements devs = detail.select("a[href]");
			for(Element e : devs){
				String href = e.attr("href");
							
				if(href.indexOf("developer")!=-1)	{
					ps.print(e.text()+"    ");
					result.add(e.text());
				}
			}
		}
		
		ps.println();
		return result;
	}
	
	public ArrayList<String>getAppPublishers(Document document)
	{
		ArrayList<String>	result	=	new ArrayList<String>(); 

		Elements details = null;
		try{
			details = document.getElementsByClass("details_block");
			
		}
		catch (Exception e) {
			return null;
		}
		//Element detail = details.get(0);
		
		//Div, publisher, genre
		ps.print("Publishers : ");
		for(Element detail : details)
		{
			Elements devs = detail.select("a[href]");
			for(Element e : devs){
				String href = e.attr("href");
				
				if(href.indexOf("publisher")!=-1)	{
					ps.print (e.text()+"   ");
					result.add(e.text());
				}
			}
		}
		ps.println();
		return result;
	}
	
	// 장르 목록을 가져오는 함수
	public ArrayList<String>getAppGenres(Document document)
	{
		ArrayList<String>	result	=	new ArrayList<String>(); 

		Elements details = null;
		try{
			details = document.getElementsByClass("details_block");
		}catch (Exception e) {
			return null;
		}
		//Element detail = details.get(0);
		
		//Div, publisher, genre
		ps.print("Genre : ");
		for(Element detail : details)
		{
			Elements devs = detail.select("a[href]");
			for(Element e : devs){
				String href = e.attr("href");
				
				if(href.indexOf("genre")!=-1)	{
					ps.print(e.text()+"    ");
					result.add(e.text());
				}
			}
		}
		
		ps.println();
		
		return result;
	}
	
	public HashMap<String, boolean[]>	getSupportedLanguages(Document document)
	{
		HashMap<String, boolean[]>	result	=	new HashMap<String, boolean[]>();
		
		//Languege
		Elements 	langTable		= 	null;
		try{
			langTable = document.getElementsByClass("game_language_options");
		}
		catch (Exception e) {
			return null;
		}
		
		if(langTable==null)
			return null;
		if(langTable.size()==0)
			return null;
		Element		tbody			=	langTable.get(0);
		Elements		langRows		= 	tbody.getElementsByTag("tr");
		
		for(Element h : langRows)	{			
			Elements tDetails = h.getElementsByTag("td");
			
			if(tDetails.size()==0)
				continue;
			
			String languege = tDetails.get(0).text();
				
			boolean[] temp = new boolean[3];
			for(int i=1;i<=3&&i<tDetails.size();i++)
			{
				boolean test = tDetails.get(i).hasAttr("colspan");
				
				if(test)
				{
					ps.println("지원안함");
					continue;
				}
				else
				{
					Elements check = tDetails.get(i).getElementsByTag("img");
					boolean support = check.size()==1 ? true	:	false;
					
					temp[i-1] = support;
				}
			}			
			result.put(languege, temp);				
		}
		
		ps.println("languege\tinterface\tvoice\tsubtitle");
		for(String key : result.keySet()){
			boolean[] supp = result.get(key);
			ps.print(key);
			
			for(int i=0; i<supp.length; i++) {
				ps.print("\t"+supp[i]);
			}
			ps.println();
		}
		
		return result;
	}
	private	void	oldProc()
	{
		//appInfo.tagList = new ArrayList<String>();
		
				//TagList
				/*Elements tagList = document.getElementsByClass("app_tag");
				ps.println(tagList.size());
				for(Element e : tagList)
				{
					String text = e.text();
					if(text.indexOf('+')==-1){
						appInfo.tagList.add(text);
						ps.println(text);
					}
				}*/
		/*
		// Description
		Element discription = document.select("div.game_description_snippet").first();
		if(discription!=null){
			ps.println(discription.text());
			appInfo.description=discription.text();
		}
		else{
			appInfo.description="";
		}*/
	/*		
	
		//ReleaseDate
			Elements details = document.getElementsByClass("details_block");
			Element detail = details.get(0);
			
			Element date = document.select("div.date").first();
			ps.println(date.text());
			appInfo.releaseDate = date.text();
			appInfo.developList = new ArrayList<String>();
			appInfo.publisherList = new ArrayList<String>();
			appInfo.genre		= new ArrayList<String>();
		//Div, publisher, genre
			Elements devs = detail.select("a[href]");
			for(Element e : devs){
				String href = e.attr("href");
				
				if(href.indexOf("developer")!=-1)
				{
					ps.println("Developer : "+e.text()+" "+e.attr("href"));
					appInfo.developList.add(e.text());
				}
					
				else if(href.indexOf("publisher")!=-1)
				{
					ps.println("Publisher : "+e.text()+" "+e.attr("href"));
					appInfo.publisherList.add(e.text());
				}
				else if(href.indexOf("genre")!=-1)
				{
					ps.println("Genre : "+e.text());
					appInfo.genre.add(e.text());
				}
			}
		
		//Price
			Elements price = document.getElementsByClass("game_purchase_price price");
			if(price.size()==0)
			{
				price = document.getElementsByClass("discount_original_price");
			}
			
			String strPrice = price.get(0).text().substring(1);
			strPrice = strPrice.replaceAll(",", "");
			strPrice = strPrice.replaceAll(" ", "");
			ps.println("PRICE : "+strPrice);
			appInfo.price = Integer.parseInt(strPrice);
		
		
		//Languege
			Elements 	langTable 	= 	document.getElementsByClass("game_language_options");
			Element		tbody		=	langTable.get(0);
			Elements	langRows	= 	tbody.getElementsByTag("tr");
			
			for(Element h : langRows)
			{			
				Elements tDetails = h.getElementsByTag("td");
				
				if(tDetails.size()==0)
					continue;
				
				String languege = tDetails.get(0).text();
					
				boolean[] temp = new boolean[3];
				for(int i=1;i<=3;i++){
					boolean test = tDetails.get(i).hasAttr("colspan");
					
					if(test){
						ps.println("지원안함");
						continue;
					}
					else{
						Elements check = tDetails.get(i).getElementsByTag("img");
						boolean support = check.size()==1 ? true	:	false;
						
						temp[i-1] = support;
					}
				}			
				appInfo.langueges.put(languege, temp);				
			}
			
		ps.println("languege\tinterface\tvoice\tsubtitle");
		for(String key : appInfo.langueges.keySet()){
			boolean[] supp = appInfo.langueges.get(key);
			ps.print(key);
			
			for(int i=0; i<supp.length; i++) {
				ps.print("\t"+supp[i]);
			}
			ps.println();
		}
		
		//Categories
			ps.println("\nCategories");
			Elements specDetail	=	document.getElementsByClass("game_area_details_specs");
			appInfo.categories=new ArrayList<String>();
			for(Element e : specDetail){
				String category = e.select("a[class=name]").text();
				ps.println(category);
				appInfo.categories.add(category);
			}*/
	}
	public void ProccessCrawl(Document document)
	{		
		appInfo.imgUrl			=	getFullHeaderImage(document);
		System.out.println(appInfo.imgUrl);
		appInfo.tagList 			=		getAppTagList(document);
		appInfo.description		=	getAppDescription(document);
		appInfo.releaseDate	=		getReleaseDate(document);
		appInfo.genre			=		getAppGenres(document);
		appInfo.developList	=		getAppDevelopers(document);
		appInfo.publisherList	=	getAppPublishers(document);
		appInfo.langueges	=	getSupportedLanguages(document);
		appInfo.categories	=	getCategoryList(document);
		appInfo.price	=	getPrice(document);
	}
	
	
	public void	ProccessCrawl()
	{
		ps.println("============================================================");
		ps.println("Title: "+appInfo.getTitle());
		ps.println("APP_ID:" +appInfo.getId());
		//ps.println(this.document.toString());
		ProccessCrawl(this.document);
		//getDiscountRate(this.document);
		
	}
}

