package com.storm.VO;

public class MemberVO {

	private String email;
	private String pw;
	private String nick;
	private int key;

	public int    communo; 		//�Ϸù�ȣ
	public String communame; 	//Ŀ�´�Ƽ �̸�

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public int getKey() {
		return key;
	}
	public void setKey(int key) {
		this.key = key;
	}
	public int getCommuno() {
		return communo;
	}
	public void setCommuno(int communo) {
		this.communo = communo;
	}
	public String getCommuname() {
		return communame;
	}
	public void setCommuname(String communame) {
		this.communame = communame;
	}
	public void	setId(String id){
		this.email = id;
	}
	public String getId(){
		return this.email;
	}

}
