package com.storm.VO;

import java.sql.Date;

public class CommuVO {

	public int    communo; 		//�Ϸù�ȣ
	public String communame; 	//Ŀ�´�Ƽ �̸�
	public String commutext; 	//Ŀ�´�Ƽ �Ұ�
	public String commuimgName;	//�̹��� �����̸�
	public Date commudate;		//�����
	public int commuprice;		//����
	
	public int usrKey; //������ȣ
	public int gNo; // ��ǰ�Ϸù�ȣ
	
	public int oriNo;
	public int nowPage;
	
	
	public String content;	//��۳���
	public String nickname;	//�����г���
	public String gname; 	//��ǰ�̸� 
	
	
	
	
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
	public String getCommutext() {
		return commutext;
	}
	public void setCommutext(String commutext) {
		this.commutext = commutext;
	}
	public String getCommuimgName() {
		return commuimgName;
	}
	public void setCommuimgName(String commuimgName) {
		this.commuimgName = commuimgName;
	}
	public Date getCommudate() {
		return commudate;
	}
	public void setCommudate(Date commudate) {
		this.commudate = commudate;
	}
	public int getCommuprice() {
		return commuprice;
	}
	public void setCommuprice(int commuprice) {
		this.commuprice = commuprice;
	}
	public int getUsrKey() {
		return usrKey;
	}
	public void setUsrKey(int usrKey) {
		this.usrKey = usrKey;
	}
	public int getgNo() {
		return gNo;
	}
	public void setgNo(int gNo) {
		this.gNo = gNo;
	}
	public int getOriNo() {
		return oriNo;
	}
	public void setOriNo(int oriNo) {
		this.oriNo = oriNo;
	}
	public int getNowPage() {
		return nowPage;
	}
	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getGname() {
		return gname;
	}
	public void setGname(String gname) {
		this.gname = gname;
	}
	
	
	
	
	
	
	
}
