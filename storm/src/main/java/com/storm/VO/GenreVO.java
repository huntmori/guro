package com.storm.VO;

public class GenreVO implements	Comparable<GenreVO>
{
	private	String	genreName;
	private	int		genreNo;
	
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
