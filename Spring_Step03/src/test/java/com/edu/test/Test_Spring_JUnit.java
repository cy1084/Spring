package com.edu.test;

import static org.junit.Assert.assertNotEquals;

import java.util.List;

import org.apache.commons.dbcp.BasicDataSource;
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
public class Test_Spring_JUnit {
	
//junit은 자바만 테스트 할 수 있는데, 테스트 할 때 spring에 만들어진 bean들을 다 끌어오기 위한 라이브러리 추가해줘야 함
	
	@Autowired
	private ApplicationContext context;
	
	@Autowired
	private SqlSessionTemplate template;
	//SqlSessionTemplate이 예전의 SqlSessionFactory+SqlSession의 역할!
	
	@Test
	public void test() {
		System.out.println(context);
		
		//내가 입력한 정보 콘솔에 찍어보기
		BasicDataSource dataSource=context.getBean("dataSource",BasicDataSource.class);
		System.out.println("driver: "+dataSource.getDriverClassName());
		System.out.println("url: "+dataSource.getUrl());
		System.out.println("username: "+dataSource.getUsername());
		System.out.println("password: "+dataSource.getPassword());
		
	}
	
	public void mybatis_test() {
		List<TestDto> list= template.selectList("com.min.edu.model.TestDaoImpl.selectAllTest");
		assertNotEquals(0, list.size());
	}

}
