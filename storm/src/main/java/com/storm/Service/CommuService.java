package com.storm.Service;

import java.util.ArrayList;   
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;

import com.storm.DAO.CommuDAO;
import com.storm.util.PageUtil;
import com.storm.VO.CommuVO;
import com.storm.VO.MemberVO;


public class CommuService {
// 이 서비스는 주로 DAO를 이용해서 DB 처릴 할 예정이다.
@Autowired
	private	CommuDAO CDAO;
	
	

/*
* 	총 데이터 개수 처리 함수
* 	==>	총 데이터 개수를 구하는 목적은 페이지 정보를 알기 위함이다.
* 		아예 여기서 페이지 정보까지 만들어서 제공하자.
* 	페이지 정보를 구하기 위해서는 현재 페이지, 총 데이터 개수가 필요하므로
* 	그중에서 현재 알고있는 nowPage는 알려주도록 한다.
*/
	public PageUtil getCommuPageInfo(int nowPage) {
		int	total = CDAO.getcommuTotal();
		
		PageUtil pInfo = new PageUtil(nowPage, total);
		return pInfo;
	}

//	게시물 목록 꺼내기 처리를 위한 서비스 함수
	public ArrayList getCommuList(int nowPage, PageUtil pInfo) {
		//	질의 명령에서 그 페이지에 필요한 내용만 꺼내는 조건식을 위한
		//	데이터를 준비해야 한다.
		
		//	우리는 그 페이지에 필요한 데이터만 꺼내도록 질의 명령을 만들었으므로
		//	꺼낼 데이터의 시작 번호와 종료 번호를 알려주어야 한다.
		//	이론적인 내용(한 페이지에 10개씩 보이도록 약속했다면)
		//		시작 위치는		1페이지이면		1~
		//							2페이지이면		11~
		//							3페이지이면		21~
		int	start = (nowPage - 1) * (pInfo.listCount) + 1;
		//		종료 위치			시작 페이지 + 9
		//							1페이지이면		~10
		//							2페이지이면		~20
		int	end = start + (pInfo.listCount - 1);
		
		HashMap	map = new HashMap();
		map.put("start", start);
		map.put("end", end);
		System.out.println("%%%%%%%%%%%%% end : " + end);
		System.out.println("%%%%%%%%%%%%% start : " + start);
		ArrayList	list = CDAO.getCommuList(map); 
				
		System.out.println("$$$$$$$$ 여기는 ?"+list.toString());
		
		return list;
		//	근데 오늘은 아직 실행하면 안된다.
		//	왜냐하면 PageInfo를 new 시키지 않았기 때문이다.
	}
	
	//상세보기 처리를 보조할 함수
		public HashMap commuView(int communo){
		//상세보기를 처리할 경우에는
		//상세보기 내용을 꺼낸다.
			CommuVO vo=CDAO.getCommuView(communo);
			
		//먼저 그룹을 알아낸다.
//			int group=CDAO.getGroup(oriNo);
			
		//답글 목록을 꺼낸다.
			
			HashMap map=new HashMap();
			map.put("VIEW", vo);
			System.out.println("뭐가 들었을까요?"+map.toString());
			
			return map;
		}

	

//총 데이터 개수 구하기 질의 명령 실행 함수
	public int getcommuTotal(){
		int total=CDAO.getcommuTotal();
		return total;
	}	

	public void insertF(CommuVO CVO){
		CDAO.insertF(CVO);
	}
	
	public void updateF(String whatdo,CommuVO CVO){
		if(whatdo.equals("follow")){
			CDAO.updateF(CVO);
		}
		else if(whatdo.equals("unfollow")){
			CDAO.updateUF(CVO);
		}
	}
	
	public String selectshow(CommuVO CVO){
		System.out.println("서비스에서 커뮤니티 넘버 ="+CVO.communo);
		System.out.println("서비스에서 유저 키= "+CVO.usrKey);
		String show=CDAO.selectshow(CVO);
		System.out.println("팔로우가 검색이 되나요 ?= "+show);
		return show;
	}
		
	//게시물 등록
	public void binsert(CommuVO CVO) {
		CDAO.binsert(CVO);
	}
	
	public int getboardTotal(){
		int total=CDAO.getboardTotal();
		return total;
	}	
	
	public PageUtil getBoardPageInfo(int nowPage) {
		int	total = CDAO.getboardTotal();
		
		PageUtil pInfo = new PageUtil(nowPage, total);
		return pInfo;
	}
	
	public ArrayList getBlist(int nowPage, PageUtil pInfo, CommuVO CVO) {
		//	질의 명령에서 그 페이지에 필요한 내용만 꺼내는 조건식을 위한
		//	데이터를 준비해야 한다.
		
		//	우리는 그 페이지에 필요한 데이터만 꺼내도록 질의 명령을 만들었으므로
		//	꺼낼 데이터의 시작 번호와 종료 번호를 알려주어야 한다.
		//	이론적인 내용(한 페이지에 10개씩 보이도록 약속했다면)
		//		시작 위치는		1페이지이면		1~
		//							2페이지이면		11~
		//							3페이지이면		21~
		int	start = (nowPage - 1) * (pInfo.listCount) + 1;
		//		종료 위치			시작 페이지 + 9
		//							1페이지이면		~10
		//							2페이지이면		~20
		int	end = start + (pInfo.listCount - 1);
		
		HashMap	map = new HashMap();
		map.put("start", start);
		map.put("end", end);
		map.put("communo", CVO.communo);
		System.out.println("%%%%%%%%%%%%% end : " + end);
		System.out.println("%%%%%%%%%%%%% start : " + start);
		ArrayList	list = CDAO.Blist(map); 
				
		System.out.println("$$$$$$$$ 여기는 ?"+list.toString());
		
		return list;
		//	근데 오늘은 아직 실행하면 안된다.
		//	왜냐하면 PageInfo를 new 시키지 않았기 때문이다.
	}


/*
 * 
 * 커뮤니티 게시판 댓글 처리
 * 
 */

	//글쓴이에 쓰기위해 디비에서닉네임가져온다
	public MemberVO getMember(String id) {
		MemberVO vo = CDAO.getMember(id);
		System.out.println("커뮤서비스 :getMember : " +id);
		return vo;
	}

}

