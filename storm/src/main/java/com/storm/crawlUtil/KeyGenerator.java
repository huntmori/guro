package com.storm.crawlUtil;

import java.util.ArrayList;
import java.util.HashMap;

public class KeyGenerator<T>
{
	public HashMap<Integer, T>	map;
	int cnt=0;
	
	public KeyGenerator()
	{	
		map	=	new HashMap<Integer, T>();
	}
	
	public void	insertArrayList(ArrayList<T> list)
	{
		/*
		 * 1.	리스트 순회
		 * 2. 벨류가 있는지 검사
		 * 3. 없을 시 cnt를 1증가시켜 맵에 삽입
		 * 4. 있을 시 스킵
		 */
		for(int i=0;	i<list.size();	i++)
		{
			boolean test = map.containsKey(list.get(i));
			
			if(test){//해당 값이 map에 존재 함
				continue;
			}
			else	{
				cnt++;
				map.put(cnt, list.get(i));
			}
		}
	}
}
