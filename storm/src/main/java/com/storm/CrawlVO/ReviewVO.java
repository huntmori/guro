package com.storm.CrawlVO;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReviewVO implements Serializable
{
	int		appid;
	String tempDate;
	Date	realDate;
	String 	text;
	String	writer;
	boolean isPositive;
	public ReviewVO(){
		this("-1","", "", "", false);
	}
	public ReviewVO(String appid, String id, String date, String text, boolean isPositive){
		this.setAppid(appid);
		this.writer	=	id;
		this.tempDate	=	date;
		this.text	=	text;
		this.isPositive	=	isPositive;
	}
	public ReviewVO(int appid, String id, String date, String text, boolean isPositive){
		this.appid=appid;
		this.writer	=	id;
		this.tempDate	=	date;
		this.text	=	text;
		this.isPositive	=	isPositive;
	}
	
	public String getTempDate() {
		return tempDate;
	}
	public void setTempDate(String tempDate) {
		this.tempDate = tempDate;
	}
	public Date getRealDate() {
		return realDate;
	}
	public void setRealDate(Date realDate) {
		this.realDate = realDate;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String toString(){
		StringBuilder	sb	=	new StringBuilder();
		sb.append("�ۼ���:"+this.writer+"\n");
		sb.append("�ۼ���:"+this.tempDate+"\n");
		sb.append("����:"+this.text+"\n");
		String vote = isPositive? "��õ":"����õ";
		sb.append("��õ:"+vote+"\n");
		
		return sb.toString();
	}
	public int getAppid() {
		return appid;
	}
	public void setAppid(int appid) {
		this.appid = appid;
	}
	public void setAppid(String appid) {
		Integer result = null;
		
		try{
			result = Integer.parseInt(appid);
		}catch (Exception e) {
			System.out.println("���������� ��ȯ �Ұ����� ���ڿ� �Դϴ�.");
		}
		
		this.appid  =result;
	}
	
	public void setRealDate()
	{
		String temp = this.tempDate;
		
		Date	result = new Date();
		temp = temp.replace("Posted:", "");
		String[] tokens = null;
		
		String	year;
		String	month;
		String	date;
		
		try
		{
			tokens = temp.split(",");
			year = tokens[tokens.length-1];
		}
		catch (Exception e)
		{
			// TODO: handle exception
			long	time	=	System.currentTimeMillis();
			SimpleDateFormat	dayTime	=	new SimpleDateFormat("yyyy");
			year = dayTime.format(new Date(time));
		}
		tokens = tokens[0].split(" ");
	}
	
	
	public String getInsertSqlStatement(){
		StringBuilder sb= new StringBuilder();
		
		sb.append("INSERT INTO REVIEW_TABLE VALUES ( ");
		String vote = isPositive ? "Y" : "N";
		
		this.writer = this.writer.replaceAll("'", "''");
		
		sb.append(" '"+this.writer+"' , '"+vote+"', 0 , SYSDATE , '"+this.text+"' ,"+this.appid+");");
				
		return sb.toString();
	}
}
