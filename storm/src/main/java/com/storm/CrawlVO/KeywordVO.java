package com.storm.CrawlVO;

public class KeywordVO	implements	Comparable<KeywordVO> 
{
	String	keyword;
	int		count;
	
	public KeywordVO(String key, int cnt){
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
}
