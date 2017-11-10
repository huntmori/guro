package com.storm.crawlUtil;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class JSoupUtil 
{	
	public static Document ConnectionJsoup(String url) throws IOException
	{
		Document result	=	(Document)Jsoup.connect(url).get();
		
		System.out.println(result.location());
		
		return result;
	}
}
