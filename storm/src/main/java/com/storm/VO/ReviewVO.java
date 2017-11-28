package com.storm.VO;

import java.io.Serializable;
import java.util.Date;

public class ReviewVO implements	Serializable
{
	private	String	wirter,
								isRecommended,
								text;
	private	Date	wdate;
	private	int		app_id;
	private	int		userPlayTime;
	
	public String getWirter() {
		return wirter;
	}
	public void setWirter(String wirter) {
		this.wirter = wirter;
	}
	public String getIsRecommended() {
		return isRecommended;
	}
	public void setIsRecommended(String isRecommended) {
		this.isRecommended = isRecommended;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Date getWdate() {
		return wdate;
	}
	public void setWdate(Date wdate) {
		this.wdate = wdate;
	}
	public int getApp_id() {
		return app_id;
	}
	public void setApp_id(int app_id) {
		this.app_id = app_id;
	}
	public int getUserPlayTime() {
		return userPlayTime;
	}
	public void setUserPlayTime(int userPlayTime) {
		this.userPlayTime = userPlayTime;
	}
	
	
}
