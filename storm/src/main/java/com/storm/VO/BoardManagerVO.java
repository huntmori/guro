package com.storm.VO;

import java.sql.Date;

public class BoardManagerVO {

	public int			boardNo;
	public int 			nowPage;

	private int 		board_app_id;
	private int 		board_user_key;
	private int 		board_no;
	private String 		board_title;
	private Date 		board_wdate;
	private String 		board_text;
	private String 		board_isshow;
	private int 		board_hit;

	// VO가 준비할 변수들
	// 파라메터로 전달된 데이터를 기억할 변수(setXxx())
	// SELECT 질의 명령의 결과를 기억할 변수(setXxx())
	public String kind;
	public String word;
	
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	public int getNowPage() {
		return nowPage;
	}
	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
	}
	public int getBoard_app_id() {
		return board_app_id;
	}
	public void setBoard_app_id(int board_app_id) {
		this.board_app_id = board_app_id;
	}
	public int getBoard_user_key() {
		return board_user_key;
	}
	public void setBoard_user_key(int board_user_key) {
		this.board_user_key = board_user_key;
	}
	public int getBoard_no() {
		return board_no;
	}
	public void setBoard_no(int board_no) {
		this.board_no = board_no;
	}
	public String getBoard_title() {
		return board_title;
	}
	public void setBoard_title(String board_title) {
		this.board_title = board_title;
	}
	public Date getBoard_wdate() {
		return board_wdate;
	}
	public void setBoard_wdate(Date board_wdate) {
		this.board_wdate = board_wdate;
	}
	public String getBoard_text() {
		return board_text;
	}
	public void setBoard_text(String board_text) {
		this.board_text = board_text;
	}
	public String getBoard_isshow() {
		return board_isshow;
	}
	public void setBoard_isShow(String board_isshow) {
		this.board_isshow = board_isshow;
	}
	public int getBoard_hit() {
		return board_hit;
	}
	public void setBoard_hit(int board_hit) {
		this.board_hit = board_hit;
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
