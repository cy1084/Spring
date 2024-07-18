package com.test.edu;

import static org.junit.Assert.assertNotNull;

import org.apache.commons.dbcp.BasicDataSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.min.edu.aop.DaoLogAop_Anno;
import com.min.edu.model.mapper.UserDaoImpl;
import com.min.edu.vo.UserVo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class Bean_JUnitTest {

	@Autowired
	private ApplicationContext context;
	
	@Autowired
	private SqlSessionTemplate session;
	
	@Test
	public void test() {
		BasicDataSource d = context.getBean("dataSource", BasicDataSource.class);
		System.out.println(d.getDriverClassName());
		System.err.println(d.getMaxActive());
		assertNotNull(d);
		assertNotNull(session);
		
		DaoLogAop_Anno daoLogAop_Anno = 
				context.getBean("daoLogAop_Anno", DaoLogAop_Anno.class);
		assertNotNull(daoLogAop_Anno);
	}

}
