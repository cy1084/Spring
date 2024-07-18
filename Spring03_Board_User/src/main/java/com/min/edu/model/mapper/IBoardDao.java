package com.min.edu.model.mapper;

import java.util.List;

import com.min.edu.vo.BoardVo;

public interface IBoardDao {
	/**
	 * 게시판 리스트 userBoardList
	 * 
	 * @return List<BoardVo>
	 */
	public List<BoardVo> userBoardList();

	/**
	 * 게시글 삭제하기 delflagBoard
	 * 
	 * @param list
	 * @return 0 또는 1
	 */
	public int delflagBoard(List<String> list);

	/**
	 * 게시글 쓰기 writeBoard
	 * 
	 * @param vo
	 * @return 0 또는 1
	 */
	public int writeBoard(BoardVo vo);

	/**
	 * 게시글 상세보기 getOneBoard
	 * 
	 * @param seq
	 * @return List<BoardVo>
	 */
	public BoardVo getOneBoard(String seq);

	/**
	 * 답글 업데이트 replyUpdate
	 * 
	 * @param seq
	 * @return 0 또는 1
	 */
	public int replyUpdate(BoardVo vo);

	/**
	 * 답글 입력 replyInsert
	 * 
	 * @param vo
	 * @return 0 또는 1
	 */
	public int replyInsert(BoardVo vo);

	/**
	 * 삭제된 게시글 확인 restoreBoard
	 * 
	 * @return List<BoardVo>
	 */
	public List<BoardVo> restoreBoard();

	/**
	 * 게시글 복구 restoreDelflag
	 * 
	 * @param list
	 * @return 0 또는 1
	 */
	public int restoreDelflag(List<String> list);
}
