package com.storm.DAO;

import java.util.ArrayList;
import java.util.HashMap;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;

import com.storm.VO.UserManagerVO;

public class UserManagerDAO extends SqlSessionDaoSupport {
	// 이 클래스는 질의 명령을 실행하기 위한 클래스이다.
	// 이 클래스에서 가장 중요한 것은 컨넥션 풀에 있는 컨넥션을 이용하는 것이다.
	// 참고
	// myBatis에선 컨넥션을 "세션"이라고 부른다.
	// 세션을 관리하는 변수를 만들어야 하는데... 우리는 이미 myBatis 등록을 할때
	// 세션 관리를 위한 SqlSessionTemplate를 <bean> 처리해 놓았으므로
	// DI 기법으로 불러오면 될 것이다.
	@Autowired
	public SqlSessionTemplate sSession;

	// 필요한 질의 명령을 수행할 함수를 만들자.

	// 사용가 리스트 꺼내기 질의 실행 명령
	public ArrayList getUserList(HashMap map) {
		// myBatis의 SQL에서 resultType은 한줄만 고려해서 설정을 하지만...
		// DAO는 실제로 실행한 결과를 받기 때문에 여려줄을 고려해서 받아야 한다.
		// 만약 여려줄이 나올 염려가 있다면 ArrayList로 받아야 한다.

		// ()안에는 이 질의 명령을 수행할 때 필요한 데이터를 지정하는 것이므로
		// 우리는 데이터를 Map으로 알려주기로 했으므로....

		ArrayList list = (ArrayList) sSession.selectList("userManager.userList", map);
		return list;
	}

	// 총 데이터 개수 구하기 질의 명령 실행 함수
	public int getTotal() {
		return sSession.selectOne("userManager.getTotal");

		// 참고 DAO는 반드시 특정 질의를 위한 함수가 따로 존재해야 하는 것은 아니다.
		// 한개의 함수를 이용해서 다른 여러개의 질의를 실행할 수도 있다.

		/*
		 * 예> public void ????(String code) { sSesseion.?????(code); }
		 * 
		 * 실행시 sDao.?????("userManager.getTotal") <== 총 데이터 개수 구하기
		 * sDao.?????("userManager.getTotal1") <== 다른 질의 명령이 실행 될 수 있다. 의 방식을
		 * 이용해서 실행할 질의 코드를 다르게 알려주면 그때 그때마다 다른 질의 명령을 실행하도록 할 수도 있다.
		 */
	}

	// 사용자 상세보기 질의를 실행할 함수
	public UserManagerVO getUserView(int userKey) {
		// 파라메터가 일반 데이터이면 #{키값}의 내용과 변수의 이름이 동일해야한다.
		// ★★★
		// 질의 명령의 resultType은 질의 실행 결과의 한줄만 가지고 생각해라
		// DAO의 반환값은 실제 나올 수 있는 경우를 대비해서 처리해야 한다.
		return sSession.selectOne("userManager.userView", userKey);
	}

	// 검색 질의 실행 함수
	public ArrayList userSearch(HashMap map) {
		return (ArrayList) sSession.selectList("userManager.searchUser", map);
	}

	// 수정 질의 실행 함수
	public int updateUser(UserManagerVO umVO) {
		// update, delete를 수행하면 그 명령에 의해서 변경된 데이터의 개수를
		// 알 수 있다.
		// 이 개수가 0이면 변경되지 않은것이고
		// 이 개수가 1이면 변경된 것이다.
		return sSession.update("userManager.updateUser", umVO);
	}

	// 삭제 질의 실행함수
	public int deleteUser(UserManagerVO umVO) {
		return sSession.update("userManager.deleteUser", umVO);
	}
}
