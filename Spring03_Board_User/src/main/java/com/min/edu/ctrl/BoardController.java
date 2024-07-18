package com.min.edu.ctrl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.min.edu.model.service.IBoardService;
import com.min.edu.vo.BoardVo;
import com.min.edu.vo.UserVo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class BoardController {
	
	private final IBoardService service;
	
	@GetMapping(value = "/home.do")
	public String home() {
		log.info("BoardController home.do GET 요청");
		return "home";
	}
	
	@GetMapping(value="/boardList.do")
	public String boardList(Model model, HttpServletResponse response) {
		log.info("BoardController boardList.do GET 요청");
		
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate"); 
		response.setHeader("Expires", "0");
		
		List<BoardVo> boardList=service.userBoardList();
		for(BoardVo vo:boardList) {
			String title=vo.getTitle();
			//title=title.replaceAll("<", "&lt;");
			//title=title.replaceAll(">", "&gt;");
			
			title=title.replace("~", "&nbsp;");
			title=title.replace("&lt;img&gt;", "<img src='./img/reply.png'>");
			
			vo.setTitle(title); //다시 넣어줌

		}

		model.addAttribute("boardList",boardList);
		return "boardList";
	}
	
	
	@GetMapping(value="/multiDelete.do")
	@PostMapping(value="/multiDelete.do")
	public String multiDelete(@RequestParam List<String> chkVal,
								@RequestParam("chkVal") String[] seqs,
								@RequestParam Map<String,String> mapSeqs) { //map은 하나만 가져옴.. -> 그래서 map에 list를 넣어야 됨/ map은 일대일 대응일 때 사용하기
								//위의 세 방법 다 가능
		log.info("BoardController multiDelete.do 다중삭제 요청");
		
		log.info("List {}",chkVal);
		log.info("String[] {}",Arrays.toString(seqs));
		log.info("Map {}",mapSeqs);
		
		int n=service.delflagBoard(chkVal);
		log.info("다중삭제된 결과:{}",n);
		
		return "redirect:/boardList.do";
	}
	
	@GetMapping(value="/insertBoard.do")
	public String insertBoard() {
		log.info("BoardController insertBoard.do 새글 작성");
		return "insertBoard";
	}
	
	@PostMapping(value="/insertBoard.do")
	public String insertBoard(BoardVo vo, HttpSession session) {
		log.info("BoardController insertBoard.do 새글 작성 입력");
		
		String id=((UserVo)session.getAttribute("loginVo")).getId();
		vo.setId(id);
		
		int n=service.writeBoard(vo);
		return (n>0)?"redirect:/detailBoard.do?seq="+vo.getSeq():"redirect:/logout.do";
	}
	
	@GetMapping(value="/detailBoard.do")
	public String detailBoard(String seq, Model model) {
		log.info("BoardController detailBoard.do 게시글 상세보기");
		BoardVo boardOne=service.getOneBoard(seq);
		model.addAttribute("boardOne",boardOne);
		return "detailBoard";
	}
	
	@GetMapping(value="/replyInsert.do")
	public String replyInsert(String seq, Model model) {
		log.info("BoardController replyInsert.do 답글 화면");
		
		BoardVo boardOne= service.getOneBoard(seq);
		model.addAttribute("boardOne",boardOne);
		
		return "replyInsert";
	}
	
	@PostMapping(value="/replyInsert.do")
	public String replyInsert(BoardVo vo,HttpSession session) {
		log.info("BoardController replyInsert.do 답글 입력");
		String id=((UserVo)session.getAttribute("loginVo")).getId();
		vo.setId(id);
		int n=service.reply(vo);
		
		return (n!=0)?"redirect:/boardList.do":"redirect:/replyInsert.do?seq="+vo.getSeq();
	}
	
	@GetMapping(value="/restoreBoard.do")
	public String restoreBoard(Model model) {	
		log.info("BoardController restoreBoard.do 삭제된 글 리스트");
		
		List<BoardVo> restoreList=service.restoreBoard();
		model.addAttribute("restoreList",restoreList);
		
		return "restoreBoard";
	}
	
	
	
}
