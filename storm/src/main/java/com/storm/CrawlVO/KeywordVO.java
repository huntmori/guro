package com.storm.CrawlVO;

import java.io.Serializable;

public class KeywordVO	implements	Comparable<KeywordVO> , Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2697468983828334843L;
	public int		app_id;
	String	keyword;
	int		count;
	
	public KeywordVO(String key, int cnt){
		this.keyword=key;
		this.count	=	cnt;
		
	}
	public KeywordVO(int app_id, String key, int cnt){
		this.app_id	=	app_id;
		this.keyword=key;
		this.count	=	cnt;
		
	}
	public KeywordVO(){
		this("",0);
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public int compareTo(KeywordVO o) {
		// TODO Auto-generated method stub
		return Integer.compare(o.count, this.count);
	}
	
	public String	getPositiveSQLFormat(){
		StringBuilder	sb	=	new StringBuilder();
		
		sb.append("INSERT INTO Positive_Keywords_table VALUES ( ");
		sb.append(this.app_id+", '"+this.keyword+"' ,"+this.count + " ) ");
				
		return sb.toString();
	}
	
	public String	getNegativeSQLFormat(){
		StringBuilder	sb	=	new StringBuilder();
		sb.append("INSERT INTO Negative_Keywords_table VALUES ( ");
		sb.append(this.app_id+", '"+this.keyword.replaceAll("'", "''")+"' ,"+this.count + " ) ");
				
		return sb.toString();
	}
}
