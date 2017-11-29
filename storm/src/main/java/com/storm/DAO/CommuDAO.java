package com.storm.DAO;

import java.util.ArrayList;  
import java.util.HashMap;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;

import com.storm.VO.CommuVO;
import com.storm.VO.MemberVO;

public class CommuDAO extends SqlSessionDaoSupport {
/*	이 클래스는 질의 명령을 실행하기 위한 클래스이다.
	이 클래스에서 가장 중요한 것은 컨넥션 풀에 있는 컨넥션을 이용하는 것이다.
	참고
	myBatis에선 컨넥션을 "세션"이라고 부른다.
	세션을 관리하는 변수를 만들어야 하는데 우리는 이미 myBatis 등록을 할때
	세션 관리를 위한 SqlSessionTemplate를 <bean> 처리해 놓았으므로
	DI 기법으로 불러오면 될 것이다.
*/
	@Autowired
	public SqlSessionTemplate	sSession;
	
//	필요한 질의 명령을 수행할 함수를 만들자.
	
	
//	게시물 목록 꺼내기 질의 실행 명령
	public ArrayList getCommuList(HashMap map) {
/*	myBatis의 SQL에서 resultType은 한줄만 고려해서 설정을 하지만
	DAO는 실제로 실행한 결과를 받기 때문에 여려줄을 고려해서 받아야 한다.
	만약 여려줄이 나올 염려가 있다면 ArrayList로 받아야 한다.
		
	()안에는 이 질의 명령을 수행할 때 필요한 데이터를 지정하는 것이므로
	우리는 데이터를 Map으로 알려주기로 했으므로
*/		
		ArrayList list = 
			(ArrayList) sSession.selectList("commu.commuList", map);
		
		return list;
	}
	
//	총 데이터 개수 구하기 질의 명령 실행 함수
	public int getcommuTotal() {
		return sSession.selectOne("commu.getCommuTotal");
	}
	
	
//상세보기 질의를 실행할 함수
	public CommuVO getCommuView(int communo){
/*	
 * 	파라메터가 일반 데이터면 #{키값}의 내용과 변수의 이름이 동일해야 한다.
	★★★
	질의 명령의 resultType은 질의 실행결과의 한줄만 가지고 생각한다.
	DAO의 반환값은 실제 나올 수 있는 경우를 대비해서 처리해야 한다.
	*/
		return sSession.selectOne("commu.commuView",communo);
	}

//글에 대한 그룹을 알아내기 위한 질의명령 실행함수
	public int getGroup(int oriNo){
		return (Integer)sSession.selectOne("commu.getGroup",oriNo);
	}	
	
//답글을 꺼낼 질의를 실행할 함수
	public ArrayList getAnList(int bgroup){
		return (ArrayList)sSession.selectList("commu.anList",bgroup);
	}
	
/*//댓글 입력질의 실행함수
	public void insertAn(CommuVO vo){
		sSession.insert("commu.anInsert",vo);
	}
	*/

//댓글 입력시 오더조절 실행함수
	public void updateOrder(HashMap map){
		sSession.update("commu.orderSet",map);
	}

//	검색 질의 실행 함수
	public ArrayList boardSearch(HashMap map) {
		return (ArrayList) sSession.selectList("commu.search", map);
	}
	
/*//	수정 질의 실행 함수
	public int updateBoard(CommuVO CVO) {
	//	update, delete를 수행하면 그 명령에 의해서 변경된 데이터의 개수를
	//	알 수 있다.
	//	이 개수가 0이면 변경되지 않은것이고
	//	이 개수가 1이면 변경된 것이다.
		return sSession.update("commu.updateBoard", CVO);
	}*/
	
/*//삭제 질의 실행 함수
	public int deleteBoard(CommuVO CVO){
		return sSession.update("commu.delete",CVO);
	}
*/
	
	//팔로우 등록
	public void insertF(CommuVO CVO){
		sSession.insert("commu.Finsert", CVO);
	}
	
	//팔로우 업데이트
	public void updateF(CommuVO CVO){
		sSession.update("commu.Fupdate", CVO);
	}
	
	//언팔로우 업데이트
	public void updateUF(CommuVO CVO){
		sSession.update("commu.UFupdate", CVO);
	}

	
	//팔로우 확인
	public String selectshow(CommuVO CVO){
		System.out.println("가장처음 실행함수= "+(String)sSession.selectOne("commu.selectShow", CVO));
		return (String)sSession.selectOne("commu.selectShow", CVO);
		
	}
	
	
	//게시물 등록
	public void binsert(CommuVO CVO) {
		sSession.insert("commu.BInsert", CVO);
/*		모든 질의 실행함수의 첫번째 파라메터는 실행 질의 명령을 찾아올
		질의 코드값을 입력한다.
		질의 코드값		SQL파일의 namespace.질의의 id
		
		두번째 파라메터 부터는 질의 실행에 필요한 데이터를 입력하는 부분이다.
		
		myBatis를 사용하면 스테이트먼트도 자동 생성되고
		사용후 close도 자동 처리된다.
*/
	}
	
	public int getboardTotal(){
		return sSession.selectOne("commu.getBoardTotal");
	}
	
	public ArrayList Blist(HashMap map) {	
		ArrayList list = 
				(ArrayList) sSession.selectList("commu.BList", map);
		return list;
	}
	
	
	
/*
 * 
 * 커뮤니티 게시판 댓글 처리
 * 	
 */
	
	
	public MemberVO getMember(String id) {
		MemberVO vo = (MemberVO)sSession.selectOne("commu.getMember", id);
		System.out.println("dao : getMember : "+id);
		return vo;

	}
}





