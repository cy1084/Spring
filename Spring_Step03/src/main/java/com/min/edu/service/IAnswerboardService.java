package com.min.edu.service;

import java.util.List;
import java.util.Map;

import com.min.edu.dto.AnswerboardDto;

public interface IAnswerboardService {

	public List<AnswerboardDto> selectAllBoard();

	public AnswerboardDto selectDetailBoard(String seq);

	public boolean modifyBoard(Map<String, Object> map);
	
	public boolean multiDeleteBoard(List<String> list);
	
	public boolean insertBoard(AnswerboardDto dto);
	
	public boolean deleteBoard(String seq);
	
	//답글 작성 reply
	public boolean reply(AnswerboardDto dto);
}
