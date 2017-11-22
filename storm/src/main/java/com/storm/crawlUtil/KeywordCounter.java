package com.storm.crawlUtil;

import java.util.ArrayList;
import java.util.HashMap;

public class KeywordCounter 
{
	HashMap<String, Integer> map;
	
	public void inputKeywords(ArrayList<String> keywords)
	{
		int length = keywords.size();
		for(int i=0;i<length; i++){
			String key = keywords.get(i);
			if(map.containsKey(key)){
				//해당 키워드가 존재
				//해당 키의 벨류를 1 증가시킨다.
				int value = map.remove(key);
				map.put(key, value+1);
			}
			else	{//해당 키워드가 존재치 않음
				//벨류를 1로 하여 삽입
				map.put(key, 1);
			}
		}
	}
}
