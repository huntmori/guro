package com.storm.Service;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;

import com.storm.VO.UserManagerVO;
import com.storm.DAO.UserManagerDAO;
import com.storm.util.PageUtil;

public class UserManagerService {
	// ���� Ŭ������ �����Ͻ� ������ ����ϰ�
	// Ȥ�� �����ͺ��̽� ó���� �ʿ��ϸ� DAO�� �̿��� ���̴�.
	// �׷��Ƿ� DAO Ŭ������ �ʿ��� �����ε� �̰� ���� <bean> ó�� �� ���Ҵ�.
	@Autowired
	public UserManagerDAO umDAO;

	// ����� ����Ʈ ������ ó���� ���� ���� �Լ�
	public ArrayList getUserList(int nowPage, PageUtil pInfo) {
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

		ArrayList list = umDAO.getUserList(map);

		return list;
		// �ٵ� ������ ���� �����ϸ� �ȵȴ�.
		// �ֳ��ϸ� PageInfo�� new ��Ű�� �ʾұ� �����̴�.
	}

	// �� ������ ���� ���ϱ� ���� ��� ���� �Լ�
	public int getTotal() {
		// ����
		// ��� �̿��ؼ� ������ ���� ���ؼ� �˷��ش�.

		int total = umDAO.getTotal();
		return total;
	}


	// �󼼺��� ó���� ������ �Լ�
	public HashMap userView(int userKey) {
		// �󼼺��⸦ ó���� ��쿡�� �츮��
		// �󼼺��� ������ ������ �ϰ�
		UserManagerVO vo = umDAO.getUserView(userKey);

		HashMap map = new HashMap();
		map.put("VIEW", vo);

		return map;
	}

	// �˻� ó�� ���� �Լ�
	public ArrayList userSearch(UserManagerVO umVO) {
		// �˻��Ҷ�� vo �߿��� kind, word�� Map���� ����� �ֱ�� �����Ƿ�
		HashMap map = new HashMap();
		map.put("kind", umVO.kind);
		map.put("word", umVO.word);

		ArrayList list = umDAO.userSearch(map);
		return list;
	}

	// ������ ���� ���� ���� ������ ó�� �Լ�
	public UserManagerVO getModifyView(int userKey) {
		UserManagerVO vo = umDAO.getUserView(userKey);
		return vo;
	}

	// ���� ó���� ���� �Լ�
	public int updateUser(UserManagerVO umVO) {
		return umDAO.updateUser(umVO);
	}

	// ���� ó���� ���� �Լ�
	public int deleteUser(UserManagerVO umVO) {
		return umDAO.deleteUser(umVO);
	}
}
