package com.storm.Service;

import java.util.ArrayList;   
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

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
	public PageUtil getPageInfo(int nowPage) {
		int	total = CDAO.getTotal();
		
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
		public HashMap commuView(int oriNo){
		//�󼼺��⸦ ó���� ��쿡��
		//�󼼺��� ������ ������.
			CommuVO vo=CDAO.getCommuView(oriNo);
			
		//���� �׷��� �˾Ƴ���.
//			int group=CDAO.getGroup(oriNo);
			
		//��� ����� ������.
			
						HashMap map=new HashMap();
			map.put("VIEW", vo);
			System.out.println("���� ��������?"+map.toString());
			
			return map;
		}

	

//�� ������ ���� ���ϱ� ���� ��� ���� �Լ�
	public int getTotal(){
		int total=CDAO.getTotal();
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

/*
 * 
 * Ŀ�´�Ƽ �Խ��� ��� ó��
 * 
 */
	public ArrayList getBoardList(int nowPage, PageUtil pInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	//�۾��̿� �������� ��񿡼��г��Ӱ����´�
	public MemberVO getMember(String id) {
		MemberVO vo = CDAO.getMember(id);
		System.out.println("Ŀ�¼��� :getMember : " +id);
		return vo;
	}

	public void oriInsert(CommuVO cVO) {
		// TODO Auto-generated method stub
		
	}


}