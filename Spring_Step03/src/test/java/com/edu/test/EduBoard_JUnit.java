package com.edu.test;

import static org.junit.Assert.assertNotEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.min.edu.dto.TestDto;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="file:src/main/webapp/WEB-INF/spring/appServlet/root-context.xml")
public class EduBoard_JUnit {
	
			
		@Autowired
		private ApplicationContext context;
		
		@Autowired
		private SqlSessionTemplate template;
		
		@Test
		public void mybatis_test() {
			List<TestDto> list= template.selectList("com.min.edu.model.EduBoardDaoImpl.selectAllBoard");
			assertNotEquals(0, list.size());
		}

	}



