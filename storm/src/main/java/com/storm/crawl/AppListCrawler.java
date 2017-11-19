package com.storm.crawl;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import  com.storm.VO.AppVO;
import  com.storm.VO.TagVO;

public class AppListCrawler 
{
	//ArrayList<AppVO>	app_list;
	ArrayList<TagVO>	tag_list;
	
	HashMap<Integer, AppVO>app_list;
	
	Document	document;
	String		url;
	
		
		
	public AppListCrawler(String url, HashMap<String, String> cookies) throws IOException
	{
		this.url = url;
		//app_list = new ArrayList<AppVO>();
		app_list	=	new HashMap<Integer,AppVO>();
		tag_list = new ArrayList<TagVO>();
		
		if(cookies!=null)
			document = Jsoup.connect(url).cookies(cookies).get();
		else
			document=Jsoup.connect(url).get();
	}
	
	public AppListCrawler(String url) throws IOException
	{
		this(url, null);
	}
	
	public void ProccessCrawl(PrintStream output) throws IOException
	{
		//페이지 시작값
		int page=1;
		output.println("title\tappid\turl");
		
		Elements pages = document.getElementsByClass("search_pagination_right");
		Elements realPages = pages.get(0).select("a[href]");
		
		for(Element element : realPages)
		{
			System.out.print(element.text()+"\t");
		}System.out.println();
				
		// 최대 페이지 값
		int maxPage = Integer.parseInt(realPages.get(realPages.size()-2).text());
				
		//TagList
		Element popupMenuItem = document.getElementById("TagFilter_Container");		
		Elements divlist = popupMenuItem.select("div[data-value]");
		Elements list = popupMenuItem.getElementsByTag("span");
				
		PrintWriter tagList = new PrintWriter(new File("tag_list.txt"));
		tagList.println("Key\tType\tTagName");
		for(Element e : divlist)
		{	
			String key	=	e.attr("data-value");
			String name	=	e.attr("data-loc");
			tagList.print(e.attr("data-value")+"\t");	//key
			tagList.print(e.attr("data-param")+"\t");	//type
			tagList.println(e.attr("data-loc"));		//tagname
			tag_list.add(new TagVO(key,name));
		}
		tagList.close();
		Collections.sort(tag_list);
		
		for(int p=page;p<=maxPage; p++)
		{
			document = Jsoup.connect(url+p).get();
			//title			
			Elements ele = document.getElementsByClass("title");
			
			//URL
			Elements linkList = document.select("a[href][data-ds-appid]");
			Elements links = document.select("a[data-ds-appid]");
			
			for(int cnt=0;cnt<ele.size(); cnt++)
			{				
				
				String	tempTitle 	=	ele.get(cnt).text().replaceAll(" ","_");
				String	tempId		=	links.get(cnt).attr("data-ds-appid");
				String	tempUrl		=	linkList.get(cnt).attr("href");
				
				
				if(tempUrl.indexOf("app")==-1)
					continue;
				if(tempId.indexOf(",")==-1)
				{
					app_list.put(Integer.parseInt(tempId), new AppVO(tempId, tempTitle, tempUrl));
					//add(new AppVO(tempId, tempTitle, tempUrl));
					//output.println(tempTitle+"\t"+tempId+"\t"+tempUrl);
					System.out.print(p+"/"+maxPage+"=="+cnt+"\t\tMap Size : " +app_list.size()+"\t");
					System.out.println(tempTitle+"\t"+tempId+"\t"+tempUrl);
				}
					
				
			}	
		}
		
		
		/*for(AppVO a : app_list)
		{
			output.println(a.getId()+"\t"+a.getTitle()+"\t"+a.getUrl());
		}*/
		//output.flush();
		//output.close();
	}
}
