package com.storm.VO;

public class UserManagerVO {
	
	public int userKey;
	public int nowPage;

	private int 	user_key;
	private String user_email;
	private String user_nickname;
	private String user_password;
	private String user_enable;
	
	// VO가 준비할 변수들
	// 파라메터로 전달된 데이터를 기억할 변수(setXxx())
	// SELECT 질의 명령의 결과를 기억할 변수(setXxx())
	public String kind;
	public String word;
	
	public int getUserKey() {
		return userKey;
	}

	public void setUserKey(int userKey) {
		this.userKey = userKey;
	}

	public int getNowPage() {
		return nowPage;
	}

	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
	}
	
	public int getUser_key() {
		return user_key;
	}
	
	public void setUser_key(int user_key) {
		this.user_key = user_key;
	}
	
	public String getUser_email() {
		return user_email;
	}
	
	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}
	
	public String getUser_nickname() {
		return user_nickname;
	}
	
	public void setUser_nickname(String user_nickname) {
		this.user_nickname = user_nickname;
	}
	
	public String getUser_password() {
		return user_password;
	}
	
	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}
	
	public String getUser_enable() {
		return user_enable;
	}
	
	public void setUser_enable(String user_enable) {
		this.user_enable = user_enable;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}
	
	
}
