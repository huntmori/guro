package com.storm.DAO;

import java.util.ArrayList; 
import java.util.HashMap;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;

import com.storm.VO.CommuVO;

public class CommuDAO extends SqlSessionDaoSupport {
/*	�� Ŭ������ ���� ����� �����ϱ� ���� Ŭ�����̴�.
	�� Ŭ�������� ���� �߿��� ���� ���ؼ� Ǯ�� �ִ� ���ؼ��� �̿��ϴ� ���̴�.
	����
	myBatis���� ���ؼ��� "����"�̶�� �θ���.
	������ �����ϴ� ������ ������ �ϴµ� �츮�� �̹� myBatis ����� �Ҷ�
	���� ������ ���� SqlSessionTemplate�� <bean> ó���� �������Ƿ�
	DI ������� �ҷ����� �� ���̴�.
*/
	@Autowired
	public SqlSessionTemplate	sSession;
	
//	�ʿ��� ���� ����� ������ �Լ��� ������.
	
	
//	�Խù� ��� ������ ���� ���� ���
	public ArrayList getCommuList(HashMap map) {
/*	myBatis�� SQL���� resultType�� ���ٸ� ����ؼ� ������ ������
	DAO�� ������ ������ ����� �ޱ� ������ �������� ����ؼ� �޾ƾ� �Ѵ�.
	���� �������� ���� ������ �ִٸ� ArrayList�� �޾ƾ� �Ѵ�.
		
	()�ȿ��� �� ���� ����� ������ �� �ʿ��� �����͸� �����ϴ� ���̹Ƿ�
	�츮�� �����͸� Map���� �˷��ֱ�� �����Ƿ�
*/		
		ArrayList list = 
			(ArrayList) sSession.selectList("commu.commuList", map);
		
		return list;
	}
	
//	�� ������ ���� ���ϱ� ���� ��� ���� �Լ�
	public int getTotal() {
		return sSession.selectOne("commu.getTotal");
	}
	
//�󼼺��� ���Ǹ� ������ �Լ�
	public CommuVO getCommuView(int communo){
/*	
 * 	�Ķ���Ͱ� �Ϲ� �����͸� #{Ű��}�� ����� ������ �̸��� �����ؾ� �Ѵ�.
	�ڡڡ�
	���� ����� resultType�� ���� �������� ���ٸ� ������ �����Ѵ�.
	DAO�� ��ȯ���� ���� ���� �� �ִ� ��츦 ����ؼ� ó���ؾ� �Ѵ�.
	*/
		return sSession.selectOne("commu.commuView",communo);
	}

//�ۿ� ���� �׷��� �˾Ƴ��� ���� ���Ǹ�� �����Լ�
	public int getGroup(int oriNo){
		return (Integer)sSession.selectOne("commu.getGroup",oriNo);
	}	
	
//����� ���� ���Ǹ� ������ �Լ�
	public ArrayList getAnList(int bgroup){
		return (ArrayList)sSession.selectList("commu.anList",bgroup);
	}
	
/*//��� �Է����� �����Լ�
	public void insertAn(CommuVO vo){
		sSession.insert("commu.anInsert",vo);
	}
	*/

//��� �Է½� �������� �����Լ�
	public void updateOrder(HashMap map){
		sSession.update("commu.orderSet",map);
	}

//	�˻� ���� ���� �Լ�
	public ArrayList boardSearch(HashMap map) {
		return (ArrayList) sSession.selectList("commu.search", map);
	}
	
/*//	���� ���� ���� �Լ�
	public int updateBoard(CommuVO CVO) {
	//	update, delete�� �����ϸ� �� ��ɿ� ���ؼ� ����� �������� ������
	//	�� �� �ִ�.
	//	�� ������ 0�̸� ������� �������̰�
	//	�� ������ 1�̸� ����� ���̴�.
		return sSession.update("commu.updateBoard", CVO);
	}*/
	
/*//���� ���� ���� �Լ�
	public int deleteBoard(CommuVO CVO){
		return sSession.update("commu.delete",CVO);
	}
*/
	
	//�ȷο� ���
	public void insertF(CommuVO CVO){
		sSession.insert("commu.Finsert", CVO);
	}
	
	//�ȷο� ������Ʈ
	public void updateF(CommuVO CVO){
		sSession.update("commu.Fupdate", CVO);
	}
	
	//���ȷο� ������Ʈ
	public void updateUF(CommuVO CVO){
		sSession.update("commu.UFupdate", CVO);
	}
	
	//�ȷο� Ȯ��
	public String selectshow(CommuVO CVO){
		System.out.println("����ó�� �����Լ�= "+(String)sSession.selectOne("commu.selectShow", CVO));
		return (String)sSession.selectOne("commu.selectShow", CVO);
		
		
		
	}
}





