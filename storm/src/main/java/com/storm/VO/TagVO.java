package com.storm.VO;

public class TagVO {
	String name;
	int		id;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	int	tag_id;
	
	String	tag_name;
		
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
}
