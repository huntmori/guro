package com.storm.util;
/*
 * 	���ڿ� ó���� �� �� �ʿ��� ����� �˷��ִ� ��ƿ��Ƽ Ŭ����
 */
public class StringUtil {
	//	��>		\r\n  -->  <br>
	//			���ڿ��� �� ��� ������ ���̰� "..."�� ��ߵǴ� ���
	
	/*
	 * 	��Ʈ���� �����Ͱ� �����ϴ��� ���θ� �˷��ִ� ����� ����� �Լ�
	 */
	public static boolean isNull(String data) {
		if(data == null || data.length() == 0) {
			return true;
		}
		else {
			return false;
		}
	}
} 
