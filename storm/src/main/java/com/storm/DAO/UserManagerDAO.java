package com.storm.DAO;

import java.util.ArrayList;
import java.util.HashMap;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;

import com.storm.VO.UserManagerVO;

public class UserManagerDAO extends SqlSessionDaoSupport {
	// �� Ŭ������ ���� ����� �����ϱ� ���� Ŭ�����̴�.
	// �� Ŭ�������� ���� �߿��� ���� ���ؼ� Ǯ�� �ִ� ���ؼ��� �̿��ϴ� ���̴�.
	// ����
	// myBatis���� ���ؼ��� "����"�̶�� �θ���.
	// ������ �����ϴ� ������ ������ �ϴµ�... �츮�� �̹� myBatis ����� �Ҷ�
	// ���� ������ ���� SqlSessionTemplate�� <bean> ó���� �������Ƿ�
	// DI ������� �ҷ����� �� ���̴�.
	@Autowired
	public SqlSessionTemplate sSession;

	// �ʿ��� ���� ����� ������ �Լ��� ������.

	// ��밡 ����Ʈ ������ ���� ���� ���
	public ArrayList getUserList(HashMap map) {
		// myBatis�� SQL���� resultType�� ���ٸ� ����ؼ� ������ ������...
		// DAO�� ������ ������ ����� �ޱ� ������ �������� ����ؼ� �޾ƾ� �Ѵ�.
		// ���� �������� ���� ������ �ִٸ� ArrayList�� �޾ƾ� �Ѵ�.

		// ()�ȿ��� �� ���� ����� ������ �� �ʿ��� �����͸� �����ϴ� ���̹Ƿ�
		// �츮�� �����͸� Map���� �˷��ֱ�� �����Ƿ�....

		ArrayList list = (ArrayList) sSession.selectList("userManager.userList", map);
		return list;
	}

	// �� ������ ���� ���ϱ� ���� ��� ���� �Լ�
	public int getTotal() {
		return sSession.selectOne("userManager.getTotal");

		// ���� DAO�� �ݵ�� Ư�� ���Ǹ� ���� �Լ��� ���� �����ؾ� �ϴ� ���� �ƴϴ�.
		// �Ѱ��� �Լ��� �̿��ؼ� �ٸ� �������� ���Ǹ� ������ ���� �ִ�.

		/*
		 * ��> public void ????(String code) { sSesseion.?????(code); }
		 * 
		 * ����� sDao.?????("userManager.getTotal") <== �� ������ ���� ���ϱ�
		 * sDao.?????("userManager.getTotal1") <== �ٸ� ���� ����� ���� �� �� �ִ�. �� �����
		 * �̿��ؼ� ������ ���� �ڵ带 �ٸ��� �˷��ָ� �׶� �׶����� �ٸ� ���� ����� �����ϵ��� �� ���� �ִ�.
		 */
	}

	// ����� �󼼺��� ���Ǹ� ������ �Լ�
	public UserManagerVO getUserView(int userKey) {
		// �Ķ���Ͱ� �Ϲ� �������̸� #{Ű��}�� ����� ������ �̸��� �����ؾ��Ѵ�.
		// �ڡڡ�
		// ���� ����� resultType�� ���� ���� ����� ���ٸ� ������ �����ض�
		// DAO�� ��ȯ���� ���� ���� �� �ִ� ��츦 ����ؼ� ó���ؾ� �Ѵ�.
		return sSession.selectOne("userManager.userView", userKey);
	}

	// �˻� ���� ���� �Լ�
	public ArrayList userSearch(HashMap map) {
		return (ArrayList) sSession.selectList("userManager.searchUser", map);
	}

	// ���� ���� ���� �Լ�
	public int updateUser(UserManagerVO umVO) {
		// update, delete�� �����ϸ� �� ��ɿ� ���ؼ� ����� �������� ������
		// �� �� �ִ�.
		// �� ������ 0�̸� ������� �������̰�
		// �� ������ 1�̸� ����� ���̴�.
		return sSession.update("userManager.updateUser", umVO);
	}

	// ���� ���� �����Լ�
	public int deleteUser(UserManagerVO umVO) {
		return sSession.update("userManager.deleteUser", umVO);
	}
}
