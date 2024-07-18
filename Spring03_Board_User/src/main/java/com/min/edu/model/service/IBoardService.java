package com.min.edu.model.service;

import java.util.List;

import com.min.edu.vo.BoardVo;

public interface IBoardService {
	public List<BoardVo> userBoardList();

	public int delflagBoard(List<String> list);

	public int writeBoard(BoardVo vo);

	public BoardVo getOneBoard(String seq);
	
	//답글 작성 트랜잭션
	public int reply(BoardVo vo);

	public List<BoardVo> restoreBoard();

	public int restoreDelflag(List<String> list);
}
