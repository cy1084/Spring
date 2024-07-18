package com.min.edu.model;

import java.util.Map;


import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import com.min.edu.dto.UserInfoDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class UserInfoDaoImpl implements IUserInfoDao {

	@Autowired
	private SqlSessionTemplate session;
	
	private final String NS = "com.min.edu.model.UserInfoDaoImpl.";

	@Override
	public UserInfoDto login(Map<String, Object> map) {
		log.info("UserInfoDaoImpl 로그인: "+map);
		
		return session.selectOne(NS+"login",map);
	}
}
