package com.storm.util;

import java.io.File;

import org.springframework.web.multipart.MultipartFile;

public class FileUtil {
	public static String upload(MultipartFile oriFile, String name, String path) {
		name = renameFile(name, path);
		
		File		copyFile = new File(path + "\\" + name);
		try {
			oriFile.transferTo(copyFile);
		}
		catch(Exception e) {
			System.out.println("�뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�떥�벝�삕 �뜝�룞�삕�뜝�룞�삕 = " + e);
		}
		return name;
	}
	/*
	 * 	�뜝�룞�삕�뜝�떥�벝�삕 �뜝�룞�삕 �뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕�뜝�룞�삕 �뜝�떛紐뚯삕�뜝�룞�삕 �뜝�뙥釉앹삕�뜝�떎�뙋�삕 �뜝�룞�삕�뜝占� 泥섇뜝�룞�삕�뜝�룞�삕 �뜝�뙃�눦�삕
	 * 	�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�떥�벝�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕�뜝�룞�삕 �뜝�떛紐뚯삕�뜝�룞�삕 �뜝�떛諭꾩삕 �뜝�룞�삕�뜝�룞�삕�뜝�떦紐뚯삕 �뜝�떛紐뚯삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕�뜝�뙏�눦�삕 �뜝�룞�삕�뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕�뜝�룞�삕�뜝�룞�삕
	 * 	�뜝�떛紐뚯삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕
	 */
	public static String renameFile(String name, String path) {
		//	�뜝�룞�삕�솚�뜝�룞�삕			�뜝�룞�삕�뜝�룞�삕�뜝占� �뜝�룞�삕�뜝�룞�삕 �뜝�떛紐뚯삕
		//	�뜝�떇�씛�삕�뜝�룞�삕�뜝占�		�뜝�룞�삕�뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕�뜝�룞�삕 �뜝�떛紐뚯삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝占�
		
		//	�뜝�룞�삕移�		�뜝�떛紐뚯삕�뜝�룞�삕 �뜝�뙥釉앹삕�뜝�떎紐뚯삕 �뜝�떛紐뚯삕 �뜝�뙓�슱�삕 _�뜝�룞�삕�샇�뜝�룞�삕 �뜝�떛�슱�삕�뜝�뙏�눦�삕 �뜝�떛紐뚯삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕�뜝�떦�벝�삕�뜝�룞�삕 �뜝�떬�뙋�삕.
		//				�뜝�룞�삕>		hong.txt		hong_1.txt�뜝�룞�삕 �뜝�뙐�먯삕 �뜝�룞�삕�뜝�룞�삕�뜝�떛�뙋�삕.
		int		count = 0;			//	�뜝�뙓�슱�삕 �뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�샇�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕�뜝�떦源띿삕 �뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕
		String	oriName = name;
		File		file = new File(path + "\\" + oriName);
		while(file.exists()) {
			//	�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕�뜝�룞�삕 �뜝�떛紐뚯삕�뜝�룞�삕 �뜝�뙐袁몄뼱�꽌 �뜝�떙琉꾩삕�뜝�뙇�뼲�삕�뜝占� �뜝�떬�뙋�삕.
			//	�뜝�룞�삕�뜝占�	1	.�뜝�룞�삕 �뜝�떛�슱�삕�뜝�뙏�눦�삕 �뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕�뜝占� �뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕�뜝�룞�삕 �뜝�떩紐뚯삕�뜝�떬�뙋�삕.
			//			hong.txt		hong		txt		�뜝�떩紐뚯삕�뜝�떬�뙋�삕.
			int	pos = name.lastIndexOf(".");
			String	first = name.substring(0, pos);
			String	last = name.substring(pos + 1);
			
			//			2.	�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕�뜝占� _�뜝�룞�삕�샇�뜝�룞�삕 �뜝�뙐�슱�삕�뜝�룞�삕 �뜝�뙐�룞�삕 �뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕�뜝�떬�뙋�삕.
			count++;		//	1
			first = first + "_" + count;			//	hong_1
			oriName = first + "." + last;			//	hong_1.txt
			
			//	�뜝�뙐�벝�삕
			//	�뜝�룞�삕 �뜝�떛紐뚯삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕 �뜝�뙇�뙋�삕.
			//	�뜝�뙎琉꾩삕�뜝�떎琉꾩삕 �뜝�뙐�룞�삕 �뜝�떙�궪�삕�뜝�떦�벝�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕.
			file = new File(path + "\\" + oriName);
		}
		return oriName;
	}
}
