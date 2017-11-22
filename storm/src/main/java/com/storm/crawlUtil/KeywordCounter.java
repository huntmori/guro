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
				//�ش� Ű���尡 ����
				//�ش� Ű�� ������ 1 ������Ų��.
				int value = map.remove(key);
				map.put(key, value+1);
			}
			else	{//�ش� Ű���尡 ����ġ ����
				//������ 1�� �Ͽ� ����
				map.put(key, 1);
			}
		}
	}
}
