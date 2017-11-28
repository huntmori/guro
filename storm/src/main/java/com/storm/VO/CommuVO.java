package com.storm.VO;

import java.sql.Date;

public class CommuVO {

	public int    communo; 		//일련번호
	public String communame; 	//커뮤니티 이름
	public String commutext; 	//커뮤니티 소개
	public String commuimgName;	//이미지 파일이름
	public Date commudate;		//출시일
	public int commuprice;		//가격
	
	public int usrKey; //유저번호
	
	public int boardno; //게시글 번호
	public String boardtext; //게시글내용
	
	public int oriNo;
	public int nowPage;
	
	
	public String content;	//댓글내용
	public String nickname;	//유저닉네임
	public String gname; 	//상품이름 
	
	
	
	
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

	public int getBoardno() {
		return boardno;
	}
	public void setBoardno(int boardno) {
		this.boardno = boardno;
	}
	public String getBoardtext() {
		return boardtext;
	}
	public void setBoardtext(String boardtext) {
		this.boardtext = boardtext;
	}
	
	
	
	
	
	
}
