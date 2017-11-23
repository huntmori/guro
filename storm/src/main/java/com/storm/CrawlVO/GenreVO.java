package com.storm.CrawlVO;

import java.io.Serializable;

public class GenreVO implements	Comparable<GenreVO>, Serializable
{
	private	String	genreName;
	private	int		genreNo;
	
	public GenreVO(){
		this(-1,"");
	}
	public GenreVO(int no, String name){
		this.genreNo=no;
		this.genreName = name;
	}
	public GenreVO(String no, String name){
		this(Integer.parseInt(no), name);
	}
	
	public String getGenreName() {
		return genreName;
	}
	public void setGenreName(String genreName) {
		this.genreName = genreName;
	}
	public int getGenreNo() {
		return genreNo;
	}
	public void setGenreNo(int genreNo) {
		this.genreNo = genreNo;
	}
	@Override
	public int compareTo(GenreVO arg0) {
		// TODO Auto-generated method stub
		return	Integer.compare(this.genreNo, arg0.genreNo);
	}
}
