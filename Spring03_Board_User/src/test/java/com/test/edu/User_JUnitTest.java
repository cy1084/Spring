package com.test.edu;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.min.edu.model.mapper.IUserDao;
import com.min.edu.vo.UserVo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class User_JUnitTest {
	
	@Autowired
	private IUserDao dao;
	
	@Test
	public void test() {
		
		UserVo loginVo = 
		dao.getLogin(new HashMap<String, Object>(){{
			put("id", "A001");
			put("password", "A001");
		}});
		
		assertNotNull(loginVo);
		
		int n = dao.isDuplicateCheck("A001");
		assertNotEquals(0, n);
		
		
		/*
		 * UserVo signupVo = new UserVo(); signupVo.setPassword("A9999");
		 * signupVo.setName("A9999"); signupVo.setEmail("A9999"); int s =
		 * dao.signupMember(signupVo); assertNotEquals(0, s);
		 */
		
		List<UserVo> u = dao.userSelectAll();
		assertNotEquals(0, u.size());
		
		List<UserVo> sear = dao.getSearchUser(new HashMap<String, Object>(){{
			put("opt", "name");
			put("keyword", "윈기니");
		}});
		
		assertNotEquals(0, sear.size());
		
		String find = dao.findId(new HashMap<String, Object>(){{
			put("name", "A9999");
			put("email", "A9999");
		}});
		
		assertNotNull(find);
		
	}

}
