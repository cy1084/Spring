package com.min.edu.model.mapper;

import java.util.List;
import java.util.Map;

import com.min.edu.vo.UserVo;

public interface IUserDao {
	/**
	 * 로그인 getLogin
	 * 
	 * @param map id password
	 * @return null 혹은 UserVo 객체
	 */
	public UserVo getLogin(Map<String, Object> map);

	/**
	 * 중복검사 isDuplicateCheck
	 * 
	 * @param id
	 * @return 0 혹은 1
	 */
	public int isDuplicateCheck(String id);

	/**
	 * 회원가입 signupMember
	 * 
	 * @param vo (UserVo)
	 * @return 0 혹은 1
	 */
	public int signupMember(UserVo vo);

	/**
	 * 회원전체조회 userSelectAll
	 * 
	 * @return List<UserVo>
	 */
	public List<UserVo> userSelectAll();

	/**
	 * 회원검색 getSearchUser
	 * 
	 * @param map opt keyword
	 * @return List<UserVo>
	 */
	public List<UserVo> getSearchUser(Map<String, Object> map);

	/**
	 * 아이디찾기 findId
	 * 
	 * @param map name email
	 * @return null 혹은 String
	 */
	public String findId(Map<String, Object> map);

	/**
	 * 전체사용자 조회
	 * @return List<UserVo>
	 */
	public List<UserVo> getAllUser();
	
	/**
	 * 회원 권한 변경
	 * @param map checkid authValue
	 * @return 0 혹은 1
	 */
	public int setChangeAuth(Map<String, Object> map);
}
