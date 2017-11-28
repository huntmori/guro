package com.storm.Service;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;

import com.storm.DAO.BoardManagerDAO;
import com.storm.util.PageUtil;
import com.storm.VO.BoardManagerVO;

public class BoardManagerService {
	// 서비스 클래스는 비지니스 로직을 담당하고
	// 혹시 데이터베이스 처리가 필요하면 DAO를 이용할 것이다.
	// 그러므로 DAO 클래스가 필요할 예정인데 이것 역시 <bean> 처리 해 놓았다.
	@Autowired
	public BoardManagerDAO bmDAO;

	// 사용자 리스트 꺼내기 처리를 위한 서비스 함수
	public ArrayList getBoardList(int nowPage, PageUtil pInfo) {
		// 질의 명령에서 그 페이지에 필요한 내용만 꺼내는 조건식을 위한
		// 데이터를 준비해야 한다.

		// 우리는 그 페이지에 필요한 데이터만 꺼내도록 질의 명령을 만들었으므로
		// 꺼낼 데이터의 시작 번호와 종료 번호를 알려주어야 한다.
		// 이론적인 내용(한 페이지에 10개씩 보이도록 약속했다면)
		// 시작 위치는 1페이지이면 1~
		// 2페이지이면 11~
		// 3페이지이면 21~
		int start = (nowPage - 1) * (pInfo.listCount) + 1;
		// 종료 위치 시작 페이지 + 9
		// 1페이지이면 ~10
		// 2페이지이면 ~20
		int end = start + (pInfo.listCount - 1);

		HashMap map = new HashMap();
		map.put("start", start);
		map.put("end", end);

		ArrayList list = bmDAO.getBoardList(map);

		return list;
		// 근데 오늘은 아직 실행하면 안된다.
		// 왜냐하면 PageInfo를 new 시키지 않았기 때문이다.
	}

	// 총 데이터 개수 구하기 질의 명령 실행 함수
	public int getTotal() {
		// 할일
		// 디비를 이용해서 데이터 개수 구해서 알려준다.

		int total = bmDAO.getTotal();
		return total;
	}


	// 상세보기 처리를 보조할 함수
	public HashMap boardView(int boardNo) {
		// 상세보기를 처리할 경우에는 우리는
		// 상세보기 내용을 꺼내야 하고
		BoardManagerVO vo = bmDAO.getBoardView(boardNo);

		HashMap map = new HashMap();
		map.put("VIEW", vo);

		return map;
	}

	// 검색 처리 서비스 함수
	public ArrayList boardSearch(BoardManagerVO bmVO) {
		// 검색할라면 vo 중에서 kind, word만 Map으로 만들어 주기로 했으므로
		HashMap map = new HashMap();
		map.put("kind", bmVO.kind);
		map.put("word", bmVO.word);
		
		ArrayList list = bmDAO.boardSearch(map);
		return list;
	}

	// 수정을 위한 게시급 내용 꺼내기 처리 함수
	public BoardManagerVO getModifyView(int boardNo) {
		BoardManagerVO vo = bmDAO.getBoardView(boardNo);
		return vo;
	}

	// 수정 처리를 위한 함수
	public int updateBoard(BoardManagerVO bmVO) {
		return bmDAO.updateBoard(bmVO);
	}

	// 삭제 처리를 위함 함수
	public int deleteBoard(BoardManagerVO bmVO) {
		return bmDAO.deleteBoard(bmVO);
	}
}
