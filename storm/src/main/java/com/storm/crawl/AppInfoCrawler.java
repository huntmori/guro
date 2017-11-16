package com.storm.crawl;

import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import  com.storm.VO.AppVO;
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
		System.out.println(document.title());
		
		appInfo = new AppVO();
		ps	=	System.out;
	}
	
	
	// 카테고리에 대한 ArrayList반환
	public	ArrayList<String>getCategoryList(Document document)
	{
		ArrayList<String>	result	=	new ArrayList<String>();
		
		//Categories	
		System.out.println("\nCategories");
		//Spec Detial 요소를 검색
		Elements specDetail	=	document.getElementsByClass("game_area_details_specs");
		
		
		for(Element e : specDetail)	{
			//anchor 태그를 찾아온다.
			String category = e.select("a[class=name]").text();
			System.out.print(category+"     ");
			result.add(category);
		}
		System.out.println();
		
		return result;
	}
	
	//태그리스트를 얻어오는 함수
	public	ArrayList<String>getAppTagList(Document document)
	{
		ArrayList<String>	result	=	new ArrayList<String>();

		//TagList 요소 선택
		Elements tagList = document.getElementsByClass("app_tag");
		
		//System.out.println(tagList.size());
		System.out.print("tags : ");
		for(Element e : tagList)	{
			//+ 문자열을 제외 한 모든 택스트를 리스트에 추가한다.
			String text = e.text();
			if(text.indexOf('+')==-1){
				result.add(text);
				System.out.print(text+"    ");
			}
		}
		System.out.println();
		
		return result;
	}
	
	// 상품 설명을 반환하는 함수
	public	String	getAppDescription(Document document)
	{
		// Description
		Element discription = document.select("div.game_description_snippet").first();
		if(discription!=null){
			System.out.print(discription.text());
			discription.text();
		}
		else{
			return "";
		}
		System.out.println();
		
		return discription.text();
	}
	
	//상품 출시일을 반환하는 함수
	public	String	getReleaseDate(Document document)
	{
		//ReleaseDate
		Elements details = document.getElementsByClass("details_block");
		//Element detail = details.get(0);
		
		Element date = document.select("div.date").first();
		if(date==null)
			return "";
		System.out.println("ReleaseDate : "+date.text());
		
		return date.text();
	}
	
	//할인률을 가져오는 함수
	public	float	getDiscountRate(Document document)
	{
		Element discountRate = document.getElementsByClass("discount_pct").get(0);
		String strDiscount = discountRate.text();
		strDiscount = strDiscount.replaceAll("-", "");
		strDiscount = strDiscount.replaceAll("%", "");
		System.out.println("Discounted rate : "+(Integer.parseInt(strDiscount) / 100.0f));
		return Integer.parseInt(strDiscount) / 100.0f;
	}
	
	public	boolean		getIsDiscounted(Document	document)
	{
		Elements price = document.getElementsByClass("game_purchase_price price");
		if(price.size()==0)
		{
			System.out.println("discounted now");
			return true;
		}
		System.out.println("not discounted");
		return false;
	}
	
	public int	getDiscountedPrice(Document document)
	{
		Element discounted = document.getElementsByClass("discount_final_price").get(0);
		
		String strPrice = discounted.text();
		strPrice = strPrice.replaceAll(",", "");
		strPrice = strPrice.replaceAll(" ", "");
		
		int result = Integer.parseInt(strPrice);
		System.out.println("Discounted Price : "+result);
		return result;
	}
	
	//가격을 가져오는 함수
	public int	getPrice(Document document)
	{
		//Price
		Elements price = document.getElementsByClass("game_purchase_price price");
		
		if(price.size()==0)//할인 가격 인 경우 처리
		{
			price = document.getElementsByClass("discount_original_price");
		}
		
		//단위 표시를 잘라낸다
		String strPrice = price.get(0).text().substring(1);
		
		//콤마와 공백을 모두 제거
		strPrice = strPrice.replaceAll(",", "");
		strPrice = strPrice.replaceAll(" ", "");
		System.out.println("Origin PRICE : "+strPrice);
		//appInfo.price = Integer.parseInt(strPrice);
		
		return Integer.parseInt(strPrice);
	}
	
	// 개발사 목록을 가져오는 함수
	public ArrayList<String>getAppDevelopers(Document document)
	{
		ArrayList<String>	result	=	new ArrayList<String>(); 

		Elements details = document.getElementsByClass("details_block");
		Element detail = details.get(0);
		
		//Div, publisher, genre
		Elements devs = detail.select("a[href]");
		for(Element e : devs){
			String href = e.attr("href");
			
			System.out.print("Developer : ");
			if(href.indexOf("developer")!=-1)	{
				System.out.print(e.text()+"    ");
				result.add(e.text());
			}
		}
		System.out.println();
		return result;
	}
	
	public ArrayList<String>getAppPublishers(Document document)
	{
		ArrayList<String>	result	=	new ArrayList<String>(); 

		Elements details = document.getElementsByClass("details_block");
		Element detail = details.get(0);
		
		//Div, publisher, genre
		Elements devs = detail.select("a[href]");
		for(Element e : devs){
			String href = e.attr("href");
			
			if(href.indexOf("publisher")!=-1)	{
				System.out.println("publisher : "+e.text()+" "+e.attr("href"));
				result.add(e.text());
			}
		}
		return result;
	}
	
	// 장르 목록을 가져오는 함수
	public ArrayList<String>getAppGenres(Document document)
	{
		ArrayList<String>	result	=	new ArrayList<String>(); 

		Elements details = document.getElementsByClass("details_block");
		if(details.size()==0)
			return null;
		Element detail = details.get(0);
		
		//Div, publisher, genre
		System.out.print("Genre : ");
		Elements devs = detail.select("a[href]");
		for(Element e : devs){
			String href = e.attr("href");
			
			if(href.indexOf("genre")!=-1)	{
				System.out.print(e.text()+"    ");
				result.add(e.text());
			}
		}
		System.out.println();
		
		return result;
	}
	
	public HashMap<String, boolean[]>	getSupportedLanguages(Document document)
	{
		HashMap<String, boolean[]>	result	=	new HashMap<String, boolean[]>();
		
		//Languege
		Elements 	langTable		= 	document.getElementsByClass("game_language_options");
		Element		tbody			=	langTable.get(0);
		Elements		langRows		= 	tbody.getElementsByTag("tr");
		
		for(Element h : langRows)	{			
			Elements tDetails = h.getElementsByTag("td");
			
			if(tDetails.size()==0)
				continue;
			
			String languege = tDetails.get(0).text();
				
			boolean[] temp = new boolean[3];
			for(int i=1;i<=3;i++)
			{
				boolean test = tDetails.get(i).hasAttr("colspan");
				
				if(test)
				{
					System.out.println("지원안함");
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
		
		System.out.println("languege\tinterface\tvoice\tsubtitle");
		for(String key : result.keySet()){
			boolean[] supp = result.get(key);
			System.out.print(key);
			
			for(int i=0; i<supp.length; i++) {
				System.out.print("\t"+supp[i]);
			}
			System.out.println();
		}
		
		return result;
	}
	private	void	oldProc()
	{
		//appInfo.tagList = new ArrayList<String>();
		
				//TagList
				/*Elements tagList = document.getElementsByClass("app_tag");
				System.out.println(tagList.size());
				for(Element e : tagList)
				{
					String text = e.text();
					if(text.indexOf('+')==-1){
						appInfo.tagList.add(text);
						System.out.println(text);
					}
				}*/
		/*
		// Description
		Element discription = document.select("div.game_description_snippet").first();
		if(discription!=null){
			System.out.println(discription.text());
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
			System.out.println(date.text());
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
					System.out.println("Developer : "+e.text()+" "+e.attr("href"));
					appInfo.developList.add(e.text());
				}
					
				else if(href.indexOf("publisher")!=-1)
				{
					System.out.println("Publisher : "+e.text()+" "+e.attr("href"));
					appInfo.publisherList.add(e.text());
				}
				else if(href.indexOf("genre")!=-1)
				{
					System.out.println("Genre : "+e.text());
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
			System.out.println("PRICE : "+strPrice);
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
						System.out.println("지원안함");
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
			
		System.out.println("languege\tinterface\tvoice\tsubtitle");
		for(String key : appInfo.langueges.keySet()){
			boolean[] supp = appInfo.langueges.get(key);
			System.out.print(key);
			
			for(int i=0; i<supp.length; i++) {
				System.out.print("\t"+supp[i]);
			}
			System.out.println();
		}
		
		//Categories
			System.out.println("\nCategories");
			Elements specDetail	=	document.getElementsByClass("game_area_details_specs");
			appInfo.categories=new ArrayList<String>();
			for(Element e : specDetail){
				String category = e.select("a[class=name]").text();
				System.out.println(category);
				appInfo.categories.add(category);
			}*/
	}
	public void ProccessCrawl(Document document)
	{		
		appInfo.tagList 			=	getAppTagList(document);
		appInfo.description		=	getAppDescription(document);
		appInfo.releaseDate	=	getReleaseDate(document);
		appInfo.genre			=	getAppGenres(document);
		appInfo.developList	=	getAppDevelopers(document);
		appInfo.publisherList	=	getAppPublishers(document);
		
	}
	
	
	public void	ProccessCrawl()
	{
		//System.out.println(this.document.toString());
		ProccessCrawl(this.document);
		//getDiscountRate(this.document);
	}
}

