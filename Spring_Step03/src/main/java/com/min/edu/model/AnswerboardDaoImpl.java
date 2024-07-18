package com.min.edu.model;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.min.edu.dto.AnswerboardDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class AnswerboardDaoImpl implements IAnswerboardDao {

	@Autowired
	private SqlSessionTemplate session;
	
	private final String NS = "com.min.edu.model.AnswerboardDaoImpl.";

	@Override
	public List<AnswerboardDto> selectAllBoard() {
		//log.info("AnswerboardDaoImpl selectAllBoard"); -> aop에 의해 필요 없게 됨
		
		return session.selectList(NS + "selectAllBoard");
	}

	@Override
	public AnswerboardDto selectDetailBoard(String seq) {
		//log.info("AnswerboardDaoImpl selectDetailBoard");
		//log.info("전달받은 값: {}", seq);
		

		return session.selectOne(NS + "selectDetailBoard", seq);
	}

	@Override
	public boolean insertBoard(AnswerboardDto dto) {
		//log.info("AnswerboardDaoImpl insertBoard");
		//log.info("전달받은 값: {}", dto);
		
		int n = session.insert(NS + "insertBoard", dto);

		return (n == 1) ? true : false;
	}

	@Override
	public boolean modifyBoard(Map<String, Object> map) {
		//log.info("AnswerboardDaoImpl modifyBoard");
		//log.info("전달받은 값: {}", map);
		
		int n = session.update(NS + "modifyBoard", map);

		return (n == 1) ? true : false;
	}

	@Override
	public boolean deleteBoard(String seq) {
		//log.info("AnswerboardDaoImpl deleteBoard");
		//log.info("전달받은 값: {}", seq);
		
		int n = session.delete(NS + "deleteBoard", seq);

		return (n == 1) ? true : false;
	}

	@Override
	public boolean multiDeleteBoard(List<String> list) {
		//log.info("AnswerboardDaoImpl multiDeleteBoard");
		//log.info("전달받은 값: {}", list);
		
		int n = session.update(NS + "multiDeleteBoard", list);

		return (n > 0) ? true : false;
	}

	
//  이걸 트랜잭션으로!!
//		try {
//			cnt += session.update(NS + "replyUpdate", dto);
//			cnt += session.insert(NS + "replyInsert", dto);
//		} catch (Exception e) {
//			session.rollback();
//			e.printStackTrace();
//		}
//		return (cnt>0)?true:false;
		
	

	@Override
	public int replyInsert(AnswerboardDto dto) {
		//log.info("AnswerboardDaoImpl replyInsert");
		//log.info("전달받은 값: {}", dto);
		
		int n = session.update(NS + "replyInsert", dto);

		return n;
	}

	@Override
	public int replyUpdate(AnswerboardDto dto) {
		//log.info("AnswerboardDaoImpl replyUpdate");
		//log.info("전달받은 값: {}", dto);
		
		int n = session.update(NS + "replyUpdate", dto);

		return n;
	}

}
