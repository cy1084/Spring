package com.min.edu.model.mapper;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.min.edu.vo.UserVo;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UserDaoImpl implements IUserDao {
	
	private final SqlSessionTemplate session;
	private final String NS = "com.min.edu.model.mapper.UserDaoImpl.";
	
	@Override
	public UserVo getLogin(Map<String, Object> map) {
		return session.selectOne(NS+"getLogin", map);
	}

	@Override
	public int isDuplicateCheck(String id) {
		return session.selectOne(NS+"isDuplicateCheck", id);
	}

	@Override
	public int signupMember(UserVo vo) {
		return session.insert(NS+"signupMember", vo);
	}

	@Override
	public List<UserVo> userSelectAll() {
		return session.selectList(NS+"userSelectAll");
	}

	@Override
	public List<UserVo> getSearchUser(Map<String, Object> map) {
		return session.selectList(NS+"getSearchUser", map);
	}

	@Override
	public String findId(Map<String, Object> map) {
		return session.selectOne(NS+"findId", map);
	}
	
	@Override
	public List<UserVo> getAllUser() {
		return session.selectList(NS+"getAllUser");
	}
	
	@Override
	public int setChangeAuth(Map<String, Object> map) {
		return session.update(NS+"setChangeAuth");
	}

}
