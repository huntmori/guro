package com.storm.crawlUtil;

import java.util.ArrayList;
import java.util.HashMap;

@SuppressWarnings("hiding")
public class KeyGenerator<String>
{
	public HashMap<Integer, String>	map;
	int cnt=0;
	
	public KeyGenerator()
	{	
		map	=	new HashMap<Integer, String>();
	}
	
	public void	insertArrayList(ArrayList<String> list)
	{
		/*
		 * 1.	����Ʈ ��ȸ
		 * 2. ������ �ִ��� �˻�
		 * 3. ���� �� cnt�� 1�������� �ʿ� ����
		 * 4. ���� �� ��ŵ
		 */
		if(list==null)
			return;
		if(list.size()==0)
			return;
		
		for(int i=0;	i<list.size();	i++)
		{
			boolean test = map.containsValue(list.get(i));
			
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
