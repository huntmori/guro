package com.storm.Service;

import java.util.ArrayList;   
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;

import com.storm.DAO.CommuDAO;
import com.storm.util.PageUtil;
import com.storm.VO.CommuVO;
import com.storm.VO.MemberVO;


public class CommuService {
// �� ���񽺴� �ַ� DAO�� �̿��ؼ� DB ó�� �� �����̴�.
@Autowired
	private	CommuDAO CDAO;
	
	

/*
* 	�� ������ ���� ó�� �Լ�
* 	==>	�� ������ ������ ���ϴ� ������ ������ ������ �˱� �����̴�.
* 		�ƿ� ���⼭ ������ �������� ���� ��������.
* 	������ ������ ���ϱ� ���ؼ��� ���� ������, �� ������ ������ �ʿ��ϹǷ�
* 	���߿��� ���� �˰��ִ� nowPage�� �˷��ֵ��� �Ѵ�.
*/
	public PageUtil getCommuPageInfo(int nowPage) {
		int	total = CDAO.getcommuTotal();
		
		PageUtil pInfo = new PageUtil(nowPage, total);
		return pInfo;
	}

//	�Խù� ��� ������ ó���� ���� ���� �Լ�
	public ArrayList getCommuList(int nowPage, PageUtil pInfo) {
		//	���� ��ɿ��� �� �������� �ʿ��� ���븸 ������ ���ǽ��� ����
		//	�����͸� �غ��ؾ� �Ѵ�.
		
		//	�츮�� �� �������� �ʿ��� �����͸� �������� ���� ����� ��������Ƿ�
		//	���� �������� ���� ��ȣ�� ���� ��ȣ�� �˷��־�� �Ѵ�.
		//	�̷����� ����(�� �������� 10���� ���̵��� ����ߴٸ�)
		//		���� ��ġ��		1�������̸�		1~
		//							2�������̸�		11~
		//							3�������̸�		21~
		int	start = (nowPage - 1) * (pInfo.listCount) + 1;
		//		���� ��ġ			���� ������ + 9
		//							1�������̸�		~10
		//							2�������̸�		~20
		int	end = start + (pInfo.listCount - 1);
		
		HashMap	map = new HashMap();
		map.put("start", start);
		map.put("end", end);
		System.out.println("%%%%%%%%%%%%% end : " + end);
		System.out.println("%%%%%%%%%%%%% start : " + start);
		ArrayList	list = CDAO.getCommuList(map); 
				
		System.out.println("$$$$$$$$ ����� ?"+list.toString());
		
		return list;
		//	�ٵ� ������ ���� �����ϸ� �ȵȴ�.
		//	�ֳ��ϸ� PageInfo�� new ��Ű�� �ʾұ� �����̴�.
	}
	
	//�󼼺��� ó���� ������ �Լ�
		public HashMap commuView(int communo){
		//�󼼺��⸦ ó���� ��쿡��
		//�󼼺��� ������ ������.
			CommuVO vo=CDAO.getCommuView(communo);
			
		//���� �׷��� �˾Ƴ���.
//			int group=CDAO.getGroup(oriNo);
			
		//��� ����� ������.
			
			HashMap map=new HashMap();
			map.put("VIEW", vo);
			System.out.println("���� ��������?"+map.toString());
			
			return map;
		}

	

//�� ������ ���� ���ϱ� ���� ��� ���� �Լ�
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
		System.out.println("���񽺿��� Ŀ�´�Ƽ �ѹ� ="+CVO.communo);
		System.out.println("���񽺿��� ���� Ű= "+CVO.usrKey);
		String show=CDAO.selectshow(CVO);
		System.out.println("�ȷο찡 �˻��� �ǳ��� ?= "+show);
		return show;
	}
		
	//�Խù� ���
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
		//	���� ��ɿ��� �� �������� �ʿ��� ���븸 ������ ���ǽ��� ����
		//	�����͸� �غ��ؾ� �Ѵ�.
		
		//	�츮�� �� �������� �ʿ��� �����͸� �������� ���� ����� ��������Ƿ�
		//	���� �������� ���� ��ȣ�� ���� ��ȣ�� �˷��־�� �Ѵ�.
		//	�̷����� ����(�� �������� 10���� ���̵��� ����ߴٸ�)
		//		���� ��ġ��		1�������̸�		1~
		//							2�������̸�		11~
		//							3�������̸�		21~
		int	start = (nowPage - 1) * (pInfo.listCount) + 1;
		//		���� ��ġ			���� ������ + 9
		//							1�������̸�		~10
		//							2�������̸�		~20
		int	end = start + (pInfo.listCount - 1);
		
		HashMap	map = new HashMap();
		map.put("start", start);
		map.put("end", end);
		map.put("communo", CVO.communo);
		System.out.println("%%%%%%%%%%%%% end : " + end);
		System.out.println("%%%%%%%%%%%%% start : " + start);
		ArrayList	list = CDAO.Blist(map); 
				
		System.out.println("$$$$$$$$ ����� ?"+list.toString());
		
		return list;
		//	�ٵ� ������ ���� �����ϸ� �ȵȴ�.
		//	�ֳ��ϸ� PageInfo�� new ��Ű�� �ʾұ� �����̴�.
	}


/*
 * 
 * Ŀ�´�Ƽ �Խ��� ��� ó��
 * 
 */

	//�۾��̿� �������� ��񿡼��г��Ӱ����´�
	public MemberVO getMember(String id) {
		MemberVO vo = CDAO.getMember(id);
		System.out.println("Ŀ�¼��� :getMember : " +id);
		return vo;
	}

}

