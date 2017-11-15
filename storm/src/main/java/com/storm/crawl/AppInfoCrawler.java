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
	
	
	// ī�װ��� ���� ArrayList��ȯ
	public	ArrayList<String>getCategoryList(Document document)
	{
		ArrayList<String>	result	=	new ArrayList<String>();
		
		//Categories	
		System.out.println("\nCategories");
		//Spec Detial ��Ҹ� �˻�
		Elements specDetail	=	document.getElementsByClass("game_area_details_specs");
		
		
		for(Element e : specDetail)	{
			//anchor �±׸� ã�ƿ´�.
			String category = e.select("a[class=name]").text();
			System.out.println(category);
			result.add(category);
		}
		
		return result;
	}
	
	//�±׸���Ʈ�� ������ �Լ�
	public	ArrayList<String>getAppTagList(Document document)
	{
		ArrayList<String>	result	=	new ArrayList<String>();

		//TagList ��� ����
		Elements tagList = document.getElementsByClass("app_tag");
		
		System.out.println(tagList.size());
		for(Element e : tagList)	{
			//+ ���ڿ��� ���� �� ��� �ý�Ʈ�� ����Ʈ�� �߰��Ѵ�.
			String text = e.text();
			if(text.indexOf('+')==-1){
				result.add(text);
				//System.out.println(text);
			}
		}
		
		return result;
	}
	
	// ��ǰ ������ ��ȯ�ϴ� �Լ�
	public	String	getAppDescription(Document document)
	{
		// Description
		Element discription = document.select("div.game_description_snippet").first();
		if(discription!=null){
			System.out.println(discription.text());
			discription.text();
		}
		else{
			return "";
		}
		
		return discription.text();
	}
	
	//��ǰ ������� ��ȯ�ϴ� �Լ�
	public	String	getReleaseDate(Document document)
	{
		//ReleaseDate
		Elements details = document.getElementsByClass("details_block");
		//Element detail = details.get(0);
		
		Element date = document.select("div.date").first();
		System.out.println(date.text());
		
		return date.text();
	}
	
	//���η��� �������� �Լ�
	public	float	getDiscountRate(Document document)
	{
		Element discountRate = document.getElementsByClass("discount_pct").get(0);
		String strDiscount = discountRate.text();
		strDiscount = strDiscount.replaceAll("-", "");
		strDiscount = strDiscount.replaceAll("%", "");
		System.out.println((Integer.parseInt(strDiscount) / 100.0f));
		return Integer.parseInt(strDiscount) / 100.0f;
	}
	
	public	boolean		getIsDiscounted(Document	document)
	{
		Elements price = document.getElementsByClass("game_purchase_price price");
		if(price.size()==0)
		{
			return true;
		}

		return false;
	}
	
	public int	getDiscountedPrice(Document document)
	{
		Element discounted = document.getElementsByClass("discount_final_price").get(0);
		
		String strPrice = discounted.text();
		strPrice = strPrice.replaceAll(",", "");
		strPrice = strPrice.replaceAll(" ", "");
		
		return Integer.parseInt(strPrice);
	}
	
	//������ �������� �Լ�
	public int	getPrice(Document document)
	{
		//Price
		Elements price = document.getElementsByClass("game_purchase_price price");
		
		if(price.size()==0)//���� ���� �� ��� ó��
		{
			price = document.getElementsByClass("discount_original_price");
		}
		
		//���� ǥ�ø� �߶󳽴�
		String strPrice = price.get(0).text().substring(1);
		
		//�޸��� ������ ��� ����
		strPrice = strPrice.replaceAll(",", "");
		strPrice = strPrice.replaceAll(" ", "");
		System.out.println("PRICE : "+strPrice);
		//appInfo.price = Integer.parseInt(strPrice);
		
		return Integer.parseInt(strPrice);
	}
	
	// ���߻� ����� �������� �Լ�
	public ArrayList<String>getAppDevelopers(Document document)
	{
		ArrayList<String>	result	=	new ArrayList<String>(); 

		Elements details = document.getElementsByClass("details_block");
		Element detail = details.get(0);
		
		//Div, publisher, genre
		Elements devs = detail.select("a[href]");
		for(Element e : devs){
			String href = e.attr("href");
			
			if(href.indexOf("developer")!=-1)	{
				System.out.println("Developer : "+e.text()+" "+e.attr("href"));
				result.add(e.text());
			}
		}
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
	
	// �帣 ����� �������� �Լ�
	public ArrayList<String>getAppGenres(Document document)
	{
		ArrayList<String>	result	=	new ArrayList<String>(); 

		Elements details = document.getElementsByClass("details_block");
		Element detail = details.get(0);
		
		//Div, publisher, genre
		Elements devs = detail.select("a[href]");
		for(Element e : devs){
			String href = e.attr("href");
			
			if(href.indexOf("genre")!=-1)	{
				System.out.println("genre : "+e.text()+" "+e.attr("href"));
				result.add(e.text());
			}
		}
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
					System.out.println("��������");
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
		
		//System.out.println("languege\tinterface\tvoice\tsubtitle");
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
						System.out.println("��������");
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
		ProccessCrawl(this.document);
		getDiscountRate(this.document);
	}
}

