package com.storm.CrawlVO;

import java.io.Serializable;

public class CategoryVO implements	Comparable<CategoryVO>,	Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5189199836403649507L;
	private	int		categoryNo;
	private	String	categoryName;
	
	public CategoryVO(int no, String name){
		this.categoryNo=no;
		this.categoryName	=	name;
	}
	public	CategoryVO(){
		this(-1,"");
	}
	
	public int getCategoryNo() {
		return categoryNo;
	}
	
	public void setCategoryNo(int categoryNo) {
		this.categoryNo = categoryNo;
	}
	
	public String getCategoryName() {
		return categoryName;
	}
	
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	@Override
	public int compareTo(CategoryVO o) {
		// TODO Auto-generated method stub
		return Integer.compare(this.categoryNo, o.categoryNo);
	}
}
