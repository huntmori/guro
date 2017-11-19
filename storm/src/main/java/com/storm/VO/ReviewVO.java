package com.storm.VO;

import java.io.Serializable;
import java.util.Date;

public class ReviewVO implements Serializable
{
	Date	writeDate;
	String 	text;
	String	writer;
	
	protected Date getWriteDate() {
		return writeDate;
	}
	protected void setWriteDate(Date writeDate) {
		this.writeDate = writeDate;
	}
	protected String getText() {
		return text;
	}
	protected void setText(String text) {
		this.text = text;
	}
	protected String getWriter() {
		return writer;
	}
	protected void setWriter(String writer) {
		this.writer = writer;
	}
}
