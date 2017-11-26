package com.storm.VO;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AppVO 
{
	private	int		id;
	private	String	title;
	private	String	text;
	private	String	imgURL;
	
	private	Date	releaseDate;
	private	String	realDate;
	private	int		price;
	
	
	public int getId() {
		return (this.id);
	}
	public void setId(int id) {
		this.id =	id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getImgURL() {
		return imgURL;
	}
	public void setImgURL(String imgURL) {
		this.imgURL = imgURL;
	}
	
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public Date getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}
	public String getRealDate() {
		SimpleDateFormat	format	=	new SimpleDateFormat("YYYY-MM-dd", Locale.KOREA);
		realDate=format.format(releaseDate);		
		return realDate;
	}
	public void setRealDate(String realDate) {
		this.realDate = realDate;
	}
	
	
}
