package com.storm.CrawlVO;

import java.io.Serializable;

public class TagVO implements	Serializable, Comparable<TagVO>
{
	int	tag_id;
	
	String	tag_name;
	
	public	TagVO()
	{
		this("","");
	}
	public TagVO(String id, String name)
	{
		set_id(id);
		this.tag_name = name;
	}
	
	public TagVO(int id, String name){
		this.tag_id=id;
		this.tag_name=name;
	}	
	
	public int getTag_id() {
		return tag_id;
	}
	public void setTag_id(int _id) {
		this.tag_id = _id;
	}
	public void set_id(String _id) {
		this.tag_id = Integer.parseInt(_id);
	}
	public String getTag_name() {
		return tag_name;
	}
	public void setTag_name(String _name) {
		this.tag_name = _name;
	}
	@Override
	public int compareTo(TagVO o) {
		// TODO Auto-generated method stub
		return Integer.compare(this.tag_id, o.tag_id);
	}
}
