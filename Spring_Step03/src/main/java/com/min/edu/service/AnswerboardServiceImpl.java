package com.min.edu.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.min.edu.dto.AnswerboardDto;
import com.min.edu.model.IAnswerboardDao;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AnswerboardServiceImpl implements IAnswerboardService {

	@Autowired
	private IAnswerboardDao dao;
	
	@Override
	public List<AnswerboardDto> selectAllBoard() {
		log.info("전체 글 보기");
		return dao.selectAllBoard();
	}

	@Override
	public AnswerboardDto selectDetailBoard(String seq) {
		log.info("상세 글 보기");
		return dao.selectDetailBoard(seq);
	}

	@Override
	public boolean modifyBoard(Map<String, Object> map) {
		log.info("글 수정하기");
		return dao.modifyBoard(map);
	}

	@Override
	public boolean multiDeleteBoard(List<String> list) {
		log.info("글 다중 삭제하기");
		return multiDeleteBoard(list);
	}

	@Override
	public boolean insertBoard(AnswerboardDto dto) {
		log.info("새글 입력하기");
		return dao.insertBoard(dto);
	}

	@Override
	public boolean deleteBoard(String seq) {
		log.info("DB 삭제하기");
		return dao.deleteBoard(seq);
	}

	@Override
	@Transactional(readOnly=true)
	public boolean reply(AnswerboardDto dto) {
		log.info("답글 업데이트 및 입력");
		int n=dao.replyInsert(dto);
		int m=dao.replyUpdate(dto);
		return (n+m)>0?true:false;
	}

}
