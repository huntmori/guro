package com.storm.VO;

public class TagVO
{
	String	_id;
	
	String	_name;
	
	public	TagVO()
	{
		this("","");
	}
	public TagVO(String id, String name)
	{
		this._id = id;
		this._name = name;
	}
	
	
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public String get_name() {
		return _name;
	}
	public void set_name(String _name) {
		this._name = _name;
	}
}
