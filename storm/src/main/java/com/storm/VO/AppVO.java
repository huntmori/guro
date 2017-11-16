package com.storm.VO;

import java.util.ArrayList;
import java.util.HashMap;

public class AppVO implements Comparable<AppVO>
{
	String id;
	String title;
	String url;
	
	boolean		isDiscount;
	public int	price;
	public int discountedPrice;
	
	public ArrayList<String>	genre;
	public ArrayList<String>	tagList;
	public ArrayList<String>	developList;
	public ArrayList<String>	publisherList;
	public ArrayList<String>	categories;
	
	public static final int 	INTERFACE=1,	VOICE=2,	SUBTITLE=3;
	
	public HashMap<String, boolean[]>	langueges;
	
	public	String	releaseDate;
	public	String	description;
	
	
	public AppVO(String id, String title, String url)
	{
		this.id = id;
		this.title = title;
		this.url = url;
		
		langueges = new HashMap<String, boolean[]>();
	}
	
	public  AppVO() {
		langueges = new HashMap<String, boolean[]>();
	}
	
	public int compareTo(AppVO o) {
		int temp1 = Integer.parseInt(this.id);
		int	temp2 = Integer.parseInt(o.id);
		return Integer.compare(temp1, temp2);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
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
