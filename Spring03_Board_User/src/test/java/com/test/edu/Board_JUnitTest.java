package com.test.edu;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.min.edu.model.mapper.IBoardDao;
import com.min.edu.vo.BoardVo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class Board_JUnitTest {
	
	@Autowired
	IBoardDao dao;
	
	@Test
	public void boardTest() {
		
		//전체 리스트 조회
		BoardVo vo = new BoardVo();
		List<BoardVo> all = dao.userBoardList();
		assertNotNull(all);
		
		/*
		//게시글 삭제하기
		BoardVo dvo1 = new BoardVo();
		BoardVo dvo2 = new BoardVo();
		BoardVo dvo3 = new BoardVo();
		dvo1.setSeq(30);
		dvo2.setSeq(31);
		dvo3.setSeq(45);
		
		List<String> dvos = new ArrayList<String>();
		dvos.add(dvo1.getSeq()+"");
		dvos.add(dvo2.getSeq()+"");
		dvos.add(dvo3.getSeq()+"");
		
		int dnum = dao.delflagBoard(dvos);
		assertNotEquals(0, dnum);
		*/
		
		//게시글 쓰기
		/*
		BoardVo wvo = BoardVo.builder().id("A001").title("비내리는 호남선").content("남행열차에").build();

		int wnum = dao.writeBoard(wvo);
		assertEquals(1, wnum);
		*/
		BoardVo cvo = dao.getOneBoard("120");
		assertNotNull(cvo);
		
		List<BoardVo> rlist = dao.restoreBoard();
		assertNotEquals(0, rlist.size());
		
		int rcheck = dao.restoreDelflag(List.of("30", "31", "45"));
		assertNotEquals(0, rcheck);
	}

}
