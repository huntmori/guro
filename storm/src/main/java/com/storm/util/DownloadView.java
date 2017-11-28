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
	// 	AbstractView 클래스 추상 클래스이다,
	//	고로 그 안에는 추상 함수가 한개 있는데.....
	//		renderMergedOutputModel 이다.
	
	@Override
	public void renderMergedOutputModel(Map model, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		//	이 함수의 매개변수의 역활
		//	1	컨트롤러에서 알려주는 정보를 기억할 변수
		//	2	용청 내용을 관리하는 객체
		//	3	응답 방식을 관리하는 객체
		
		//	다운로드해줄 파일의 정보를 알아내자.
		File		file = (File) model.get("downloadFile");
		
		
		//	이 안에서 이 뷰가 내부적으로 해야할 일을 처리해 주면 된다.
		//	==>		우리는 JSP 시간에 배운 파일 다운로드 처리
		//			(스트림 방식으로 다운로드 해주는 기법)		을 작성하면 된다.
		
		//	1.	응답 방식을 다운로드 방식으로 변환시킨다.
		//		(일반 응답 방식은 text/html 방식이었다.)
		setContentType("application/download; UTF-8");
		//	2.	지정한 응답 방식을 실제 응답 방식으로 적용한다.
		response.setContentType(getContentType());
		//	3.	꼭 필요하지 않지만 응답 문서의 크기를 지정한다.
		//		우리의 응답 문서의 크기는 다운로드 해줄 파일의 크기가 될것이다.
		response.setContentLength((int)file.length());
		
		
		//	4.	꼭 필요하지 않지만 파일의 이름에 한글이 들어있을 경우
		//		웹 브라오저의 특성을 탈 수 있다.
		
		//		사용 웹 브라우저를 알아낸다.
		String userAgent = request.getHeader("User-Agent");
		//		이 내용 중에서 MSIE 단어가 포함되면 이 웹 브라우저는 IE이고
		//		이 단어가 없으면 다른 웹 브라우저이다.
		 boolean ie = userAgent.indexOf("MSIE") > -1;

		 //	파일의 이름이 한글이 포함된 경우 한글 처리를 한다.
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
		
		 //	다운로드 대화상자를 만들어주자
		 response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\";");
		 //	이때 파일의 이름은 다운로드할 실제 파일 이름이 아니어도 관계가 없다.
		 //	다만 여기서 지정한 이름으로 클라이언트 시스템에 그 파일이 저장될 것이다.
		 //	엔코딩 방식을 지정한다.
		 response.setHeader("Content-Transfer-Encoding", "binary");
		 
		 //	스트림 방식을 이용해서 서버의 파일의 정보를 강제로 클라이언트에게 보낸다.
		 //		나가는 스트림
		 OutputStream out = response.getOutputStream();
		 //		파일에서 읽을 스트림
		 FileInputStream	fin = new FileInputStream(file);
		 try {
			 //	복사를 한다.
			 //		옛날 방식으로 스트림으로 읽어서 스트림을 쓰면 된다.
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
			 System.out.println("다운로드 에러 = " + e);
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






