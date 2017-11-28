package com.storm.Service;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;

import com.storm.DAO.BoardManagerDAO;
import com.storm.util.PageUtil;
import com.storm.VO.BoardManagerVO;

public class BoardManagerService {
	// ���� Ŭ������ �����Ͻ� ������ ����ϰ�
	// Ȥ�� �����ͺ��̽� ó���� �ʿ��ϸ� DAO�� �̿��� ���̴�.
	// �׷��Ƿ� DAO Ŭ������ �ʿ��� �����ε� �̰� ���� <bean> ó�� �� ���Ҵ�.
	@Autowired
	public BoardManagerDAO bmDAO;

	// ����� ����Ʈ ������ ó���� ���� ���� �Լ�
	public ArrayList getBoardList(int nowPage, PageUtil pInfo) {
		// ���� ��ɿ��� �� �������� �ʿ��� ���븸 ������ ���ǽ��� ����
		// �����͸� �غ��ؾ� �Ѵ�.

		// �츮�� �� �������� �ʿ��� �����͸� �������� ���� ����� ��������Ƿ�
		// ���� �������� ���� ��ȣ�� ���� ��ȣ�� �˷��־�� �Ѵ�.
		// �̷����� ����(�� �������� 10���� ���̵��� ����ߴٸ�)
		// ���� ��ġ�� 1�������̸� 1~
		// 2�������̸� 11~
		// 3�������̸� 21~
		int start = (nowPage - 1) * (pInfo.listCount) + 1;
		// ���� ��ġ ���� ������ + 9
		// 1�������̸� ~10
		// 2�������̸� ~20
		int end = start + (pInfo.listCount - 1);

		HashMap map = new HashMap();
		map.put("start", start);
		map.put("end", end);

		ArrayList list = bmDAO.getBoardList(map);

		return list;
		// �ٵ� ������ ���� �����ϸ� �ȵȴ�.
		// �ֳ��ϸ� PageInfo�� new ��Ű�� �ʾұ� �����̴�.
	}

	// �� ������ ���� ���ϱ� ���� ��� ���� �Լ�
	public int getTotal() {
		// ����
		// ��� �̿��ؼ� ������ ���� ���ؼ� �˷��ش�.

		int total = bmDAO.getTotal();
		return total;
	}


	// �󼼺��� ó���� ������ �Լ�
	public HashMap boardView(int boardNo) {
		// �󼼺��⸦ ó���� ��쿡�� �츮��
		// �󼼺��� ������ ������ �ϰ�
		BoardManagerVO vo = bmDAO.getBoardView(boardNo);

		HashMap map = new HashMap();
		map.put("VIEW", vo);

		return map;
	}

	// �˻� ó�� ���� �Լ�
	public ArrayList boardSearch(BoardManagerVO bmVO) {
		// �˻��Ҷ�� vo �߿��� kind, word�� Map���� ����� �ֱ�� �����Ƿ�
		HashMap map = new HashMap();
		map.put("kind", bmVO.kind);
		map.put("word", bmVO.word);
		
		ArrayList list = bmDAO.boardSearch(map);
		return list;
	}

	// ������ ���� �Խñ� ���� ������ ó�� �Լ�
	public BoardManagerVO getModifyView(int boardNo) {
		BoardManagerVO vo = bmDAO.getBoardView(boardNo);
		return vo;
	}

	// ���� ó���� ���� �Լ�
	public int updateBoard(BoardManagerVO bmVO) {
		return bmDAO.updateBoard(bmVO);
	}

	// ���� ó���� ���� �Լ�
	public int deleteBoard(BoardManagerVO bmVO) {
		return bmDAO.deleteBoard(bmVO);
	}
}
