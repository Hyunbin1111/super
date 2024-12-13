package com.kh.hyper.member.model.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.hyper.member.model.dao.MemberDao;
import com.kh.hyper.member.model.vo.Member;



// @Component == Bean으로 등록하겠다.
@Service // Component 보다 더 구체적으로 ServiceBean으로 등록하겠다.
public class MemberServiceImpl implements MemberService {

	private MemberDao memberDao;
	private SqlSessionTemplate sqlSession; // 기존의 MyBatis sql문
	
	@Autowired
	public MemberServiceImpl(MemberDao memberDao, SqlSessionTemplate sqlSession) {
		this.memberDao = memberDao;
		this.sqlSession = sqlSession;
	}
	//private MemberDao memberDao;
	
	@Override
	public Member login(Member member) {
	
		/* SqlSession sqlSession = getSqlSession();
		 * 
		 * Member member = new MemberDao().login(sqlSession, member);
		 * 
		 * sqlSession.close();
		 * 
		 * retrun member;
		 */
		
		//Member loginUser = memberDao.login(sqlSession, member);
		// 사용이 끝나면 스프링이 자동으로 자원반납을 진행해주기때문에 따로 close를 호출하지 않는다.
		return memberDao.login(sqlSession, member);
	}

	
	@Override
	public int join(Member member) {
		
		
		return 0;
	}

	@Override
	public int updateMember(Member member) {
		
		
		return 0;
	}

	@Override
	public int deleteMember(Member member) {
		
		
		return 0;
	}

	
	
	
}
