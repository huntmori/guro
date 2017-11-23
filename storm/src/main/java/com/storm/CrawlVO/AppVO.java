package com.storm.CrawlVO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class AppVO implements Comparable<AppVO>, Serializable
{
	
	private static final long serialVersionUID = 1L;
	int id;
	String title;
	String url;
	
	boolean		isDiscount;
	public int	price;
	public int discountedPrice;
	
	public ArrayList	genre;
	public ArrayList	tagList;
	public ArrayList	developList;
	public ArrayList	publisherList;
	public ArrayList	categories;
	
	public static final int 	INTERFACE=0,	VOICE=1,	SUBTITLE=2;
	
	public HashMap<String, boolean[]>	langueges;
	public	String	realDate;
	public	String	releaseDate;
	public	String	description;
	
	public AppVO(int id, String title, String url)
	{
		this.id = id;
		this.title = title;
		this.url = url;
		
		langueges = new HashMap<String, boolean[]>();
	}
	public AppVO(String id, String title, String url)
	{
		this.id = Integer.parseInt(id);
		this.title = title;
		this.url = url;
		
		langueges = new HashMap<String, boolean[]>();
	}
	
	public  AppVO() {
		langueges = new HashMap<String, boolean[]>();
	}
	
	public int compareTo(AppVO o) {
		return Integer.compare(this.id, o.id);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public boolean isDiscount() {
		return isDiscount;
	}

	public void setDiscount(boolean isDiscount) {
		this.isDiscount = isDiscount;
	}

	public int getDiscountedPrice() {
		return discountedPrice;
	}

	public void setDiscountedPrice(int discountedPrice) {
		this.discountedPrice = discountedPrice;
	}
}
