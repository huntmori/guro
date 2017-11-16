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
		 * 1.	����Ʈ ��ȸ
		 * 2. ������ �ִ��� �˻�
		 * 3. ���� �� cnt�� 1�������� �ʿ� ����
		 * 4. ���� �� ��ŵ
		 */
		for(int i=0;	i<list.size();	i++)
		{
			boolean test = map.containsKey(list.get(i));
			
			if(test){//�ش� ���� map�� ���� ��
				continue;
			}
			else	{
				cnt++;
				map.put(cnt, list.get(i));
			}
		}
	}
}
