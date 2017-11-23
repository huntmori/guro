package com.storm.crawlUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Set;

import com.storm.CrawlVO.KeywordVO;

public class KeywordCounter 
{
	public HashMap<String, Integer> map;
	
	public KeywordCounter(){
		map = new HashMap<String, Integer>();
	}
	
	public void inputKeywords(ArrayList<String> keywords)
	{
		int length = keywords.size();
		for(int i=0;i<length; i++){
			String key = keywords.get(i);
			inputKeyword(key);
		}
	}
	
	public	void	inputKeyword(String	keyword)
	{
		if(map.containsKey(keyword))
		{
			int value = map.remove(keyword);
			System.out.print(value+"===>");
			map.put(keyword, value+1);
			System.out.println(map.get(keyword));
		}
		else
		{
			map.put(keyword, 1);
		}
		System.out.println(keyword);
	}
	
	public ArrayList<KeywordVO>	getSortedKeywordsList(){
		Set<String>	keys1 = map.keySet();
		
		ArrayList<KeywordVO>	sort1=new ArrayList<KeywordVO>();
		
		for(String str : keys1)
		{
			if(str.length()!=1)
				sort1.add(new KeywordVO(str, map.get(str)));
		}
		
		return sort1;
	}
}
