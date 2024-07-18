package com.min.edu.ctrl;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.min.edu.dto.AnswerboardDto;
import com.min.edu.dto.UserInfoDto;
import com.min.edu.service.IAnswerboardService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class BoardController {
	@Autowired
	private IAnswerboardService service;
	
	@GetMapping(value="/boardList.do")
	public String boardList(Model model) {
		log.info("글 전체 조회");
		List<AnswerboardDto> lists=service.selectAllBoard();
		model.addAttribute("lists",lists);
		
		return "boardList";
	}
	
	
	@PostMapping(value="/multiDelete.do")
	public String multiDelete(@RequestParam List<String> ch) {
		log.info("다중 삭제: {}",ch);
		
		boolean isc=service.multiDeleteBoard(ch);
		
		if(isc) {
			return "redirect:/boardList.do";
		}else {
			return "error";
		}
	}
	
	//새글 입력 창
	@GetMapping(value="/writeBoard.do")
	public String writeBoardForm() {
		return "insertBoardForm";
	}
	
	@PostMapping(value="/writeBoard.do")
	public String writeBoard(AnswerboardDto inDto, HttpSession session) {
		UserInfoDto loginDto=(UserInfoDto)session.getAttribute("loginDto");
		//자동 형변환 x..?
		inDto.setId(loginDto.getId()); 
		
		boolean isc=service.insertBoard(inDto);
		
		if(isc) {
			return "redirect:/boardList.do";
		}else {
			return "redirect:/writeBoard.do";
		}
	}
	
	@GetMapping(value="/detailBoard.do")
	public String detailBoard(String seq, Model model) {
		AnswerboardDto dto=service.selectDetailBoard(seq);
		model.addAttribute("dto", dto); //값 넘겨주기 위해 model 객체 사용
		
		return "boardDetail";
	}
	
	@PostMapping(value="/realDelete.do")
	public String realDelete(String seq, HttpSession session) {
		UserInfoDto loginDto=(UserInfoDto)session.getAttribute("loginDto");
		
		if(loginDto.getAuth().equalsIgnoreCase("A")) {
			boolean isc=service.deleteBoard(seq);
			if(isc) {
				return "redirect:/boardList.do";
			}else {
				return "error";
			}
		}else {
			return "redirect:/boardList.do";
		}
	}
	
	//수정 창
	@GetMapping(value="/modifyBoard.do")
	public String modifyBoardForm(String seq, HttpSession session, Model model) {
		UserInfoDto loginDto=(UserInfoDto)session.getAttribute("loginDto");
		AnswerboardDto dto=service.selectDetailBoard(seq);
		
		if(dto.getId().equals(loginDto.getId())) {
			model.addAttribute("dto",dto);
			return "modifyBoardForm";
		}else {
			return "redirect:/loginServlet.do";
		}
	}

	@PostMapping(value="/modifyBoard.do")
	public String modifyBoard(@RequestParam Map<String,Object> inMap,HttpSession session) {
		UserInfoDto loginDto=(UserInfoDto)session.getAttribute("loginDto");
		
		inMap.put("id", loginDto.getId());
		boolean isc=service.modifyBoard(inMap);
	
		if(isc) {
			return "redirect:/boardList.do?seq="+inMap.get("seq");
		}else {
			return "redirect:/modifyBoard.do?seq="+inMap.get("seq");
		}
	}
	
	//답글 창
	@GetMapping(value="/replyBoard.do")
	public String replyForm(String seq, Model model) {
		AnswerboardDto dto= service.selectDetailBoard(seq);
		model.addAttribute("dto",dto);
		
		return "replyBoardForm";
	}
	
	@PostMapping(value="/replyBoard.do")
	public String reply(AnswerboardDto inDto, HttpSession session) {
		UserInfoDto loginDto=(UserInfoDto)session.getAttribute("loginDto");
	
		inDto.setId(loginDto.getId());
		boolean isc=service.reply(inDto);
		
		if(isc) {
			return "redirect:/boardList.do";
		}else {
			return "redirect:/replyBoard.do?seq="+inDto.getSeq();
		}
	}
	
}
