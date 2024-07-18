package com.min.edu.model.mapper;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.min.edu.vo.BoardVo;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class BoardDaoImpl implements IBoardDao {
	
	@Autowired
	private final SqlSessionTemplate session;
	
	private final String NS = "com.min.edu.model.mapper.BoardDaoImpl.";
	
	@Override
	public List<BoardVo> userBoardList() {
		return session.selectList(NS+"userBoardList");
	}

	@Override
	public int delflagBoard(List<String> list) {
		return session.update(NS+"delflagBoard", list);
	}

	@Override
	public int writeBoard(BoardVo vo) {
		return session.update(NS+"writeBoard", vo);
	}

	@Override
	public BoardVo getOneBoard(String seq) {
		return session.selectOne(NS+"getOneBoard", seq);
	}

	@Override
	public int replyUpdate(BoardVo vo) {
		return session.update(NS+"replyUpdate", vo);
	}

	@Override
	public int replyInsert(BoardVo vo) {
		return session.insert(NS+"replyInsert", vo);
	}

	@Override
	public List<BoardVo> restoreBoard() {
		return session.selectList(NS+"restoreBoard");
	}

	@Override
	public int restoreDelflag(List<String> list) {
		return session.update(NS+"restoreDelflag", list);
	}

}
