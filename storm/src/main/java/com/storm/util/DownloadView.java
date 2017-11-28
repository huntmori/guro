package com.storm.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

public class DownloadView extends AbstractView {
	// 	AbstractView Ŭ���� �߻� Ŭ�����̴�,
	//	��� �� �ȿ��� �߻� �Լ��� �Ѱ� �ִµ�.....
	//		renderMergedOutputModel �̴�.
	
	@Override
	public void renderMergedOutputModel(Map model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		//	�� �Լ��� �Ű������� ��Ȱ
		//	1	��Ʈ�ѷ����� �˷��ִ� ������ ����� ����
		//	2	��û ������ �����ϴ� ��ü
		//	3	���� ����� �����ϴ� ��ü
		
		//	�ٿ�ε����� ������ ������ �˾Ƴ���.
		File		file = (File) model.get("downloadFile");
		
		
		//	�� �ȿ��� �� �䰡 ���������� �ؾ��� ���� ó���� �ָ� �ȴ�.
		//	==>		�츮�� JSP �ð��� ��� ���� �ٿ�ε� ó��
		//			(��Ʈ�� ������� �ٿ�ε� ���ִ� ���)		�� �ۼ��ϸ� �ȴ�.
		
		//	1.	���� ����� �ٿ�ε� ������� ��ȯ��Ų��.
		//		(�Ϲ� ���� ����� text/html ����̾���.)
		setContentType("application/download; UTF-8");
		//	2.	������ ���� ����� ���� ���� ������� �����Ѵ�.
		response.setContentType(getContentType());
		//	3.	�� �ʿ����� ������ ���� ������ ũ�⸦ �����Ѵ�.
		//		�츮�� ���� ������ ũ��� �ٿ�ε� ���� ������ ũ�Ⱑ �ɰ��̴�.
		response.setContentLength((int)file.length());
		
		
		//	4.	�� �ʿ����� ������ ������ �̸��� �ѱ��� ������� ���
		//		�� �������� Ư���� Ż �� �ִ�.
		
		//		��� �� �������� �˾Ƴ���.
		String userAgent = request.getHeader("User-Agent");
		//		�� ���� �߿��� MSIE �ܾ ���ԵǸ� �� �� �������� IE�̰�
		//		�� �ܾ ������ �ٸ� �� �������̴�.
		 boolean ie = userAgent.indexOf("MSIE") > -1;

		 //	������ �̸��� �ѱ��� ���Ե� ��� �ѱ� ó���� �Ѵ�.
		 String		fileName = file.getName();
		 if(ie == true) {
			 fileName = URLEncoder.encode(file.getName(), "UTF-8");
		 }
		 else {
			 System.out.println(fileName);
			 fileName = URLEncoder.encode(file.getName(), "UTF-8").replaceAll("\\+", "%20");
//			 fileName = new String(file.getName().getBytes("EUC-KR"), "UTF-8");
			 System.out.println(fileName);
		 }
		
		 //	�ٿ�ε� ��ȭ���ڸ� ���������
		 response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\";");
		 //	�̶� ������ �̸��� �ٿ�ε��� ���� ���� �̸��� �ƴϾ ���谡 ����.
		 //	�ٸ� ���⼭ ������ �̸����� Ŭ���̾�Ʈ �ý��ۿ� �� ������ ����� ���̴�.
		 //	���ڵ� ����� �����Ѵ�.
		 response.setHeader("Content-Transfer-Encoding", "binary");
		 
		 //	��Ʈ�� ����� �̿��ؼ� ������ ������ ������ ������ Ŭ���̾�Ʈ���� ������.
		 //		������ ��Ʈ��
		 OutputStream out = response.getOutputStream();
		 //		���Ͽ��� ���� ��Ʈ��
		 FileInputStream	fin = new FileInputStream(file);
		 try {
			 //	���縦 �Ѵ�.
			 //		���� ������� ��Ʈ������ �о ��Ʈ���� ���� �ȴ�.
//			 while(true) {
//				 byte[] buff = new byte[1024];
//				 int 	len = fin.read(buff);
//				 if(len < 0) {
//					 break;
//				 }
//				 out.write(buff, 0, len);
//			 }
			 FileCopyUtils.copy(fin, out);
		 }
		 catch(Exception e) {
			 System.out.println("�ٿ�ε� ���� = " + e);
		 }
		 finally {
			 try {
				 fin.close();
				 out.close();
			 }
			 catch(Exception e) {}
		 }
	}
	
}






