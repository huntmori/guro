package com.storm.CrawlVO;

import java.io.Serializable;

public class CompanyVO implements Serializable
{
	private	int	company_id;
	private	String	company_name;
	
	public	CompanyVO()
	{
		this(-1,"");
	}
	public	CompanyVO(int id, String name)
	{
		this.company_id	=	id;
		this.company_name=	name;
	}
	
	public int getCompany_id() {
		return company_id;
	}
	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}
	public String getCompany_name() {
		return company_name;
	}
	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}
}
