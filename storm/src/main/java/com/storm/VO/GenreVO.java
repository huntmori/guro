package com.storm.VO;

public class GenreVO 
{
	private	int		id;
	private	String	name;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	private	String	genreName;
	private	int		genreNo;
	private int cnt;
	
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
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
}
