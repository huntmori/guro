package com.storm.crawlUtil;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.storm.CrawlVO.ReviewVO;

public class JSoupUtil 
{	
	public static Document ConnectionJsoup(String url) throws IOException
	{
		Document result	=	(Document)Jsoup.connect(url).get();
		
		System.out.println(result.location());
		
		return result;
	}
	
	public static void main(String[] args) throws IOException, ClassNotFoundException
	{
		String app_id = "264710";
		FileInputStream	fis = new FileInputStream(".\\dat\\"+app_id+"\\"+app_id+"_positiveReview.dat");
		ObjectInputStream	ois = new ObjectInputStream(fis);
		
		ArrayList<ReviewVO> negatives = (ArrayList<ReviewVO>) ois.readObject();
		ois.close();
		fis.close();
		
		PrintWriter output=new PrintWriter(".\\dat\\"+app_id+"\\"+app_id+"_sql.sql");
		for(ReviewVO vo : negatives){
			output.println(vo.getInsertSqlStatement());
		}
		
		fis = new FileInputStream(".\\dat\\"+app_id+"\\"+app_id+"_negativeReview.dat");
		ois = new ObjectInputStream(fis);
		
		ArrayList<ReviewVO> positives = (ArrayList<ReviewVO>)ois.readObject();
		for(ReviewVO vo : positives){
			output.println(vo.getInsertSqlStatement());
		}
		output.flush();
		output.close();
	}
}
