package com.storm.VO;

public class CategoryVO implements	Comparable<CategoryVO>{
	private	int		categoryNo;
	private	String	categoryName;
	
	
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
