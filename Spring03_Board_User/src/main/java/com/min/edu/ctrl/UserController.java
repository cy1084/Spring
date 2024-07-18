package com.min.edu.ctrl;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.min.edu.model.service.IUserService;
import com.min.edu.vo.UserVo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class UserController {
	private final IUserService service;
	
	@GetMapping(value = "/loginForm.do")
	public String loginForm() {
		log.info("UserController loginForm.do 로그인 화면 이동");
		return "loginForm";
	}
	
	@GetMapping(value = "/signupForm.do")
	public String signupForm() {
		log.info("UserController signupForm.do 회원가입 화면 이동");
		return "signupForm";
	}
	
	@GetMapping(value = "/duplication.do")
	public String duplication() {
		log.info("UserController duplication.do 아이디 중복검사 팝업");
		return "duplication";
	}
	
	@PostMapping(value = "/signUp.do")
	public String signUp(UserVo vo, HttpServletResponse response) throws IOException {
		log.info("UserController signUp.do 회원가입 완료 이동");
		int n = service.signupMember(vo);
		if(n == 1) {
			return "redirect:/loginForm.do";
		}else {
			response.setContentType("text/html; charset=UTF-8");
			response.getWriter().print("<script>alert('회원가입 실패'); location.href='./signupForm.do'</script>");
			return null;
		}
	}
	
	@GetMapping(value="/findIdWindow.do")
	public String findIdWindow() {
		log.info("UserController findIdWindow.do 아이디 찾기 이동");
		return "findId";
	}
	
	@PostMapping(value="/login.do")
	public String login(@RequestParam Map<String,Object> map, HttpSession session, HttpServletResponse response) throws IOException {
		log.info("UserController login.do 로그인 이동");
		
		UserVo loginVo=service.getLogin(map);
		
		response.setContentType("text/html; charset=UTF-8;");
		
		if(loginVo!=null) {
			session.setAttribute("loginVo", loginVo);
			session.setMaxInactiveInterval(60*10*5);
			response.getWriter().print("<script>alert('"+loginVo.getName()+"님 반갑습니다');location.href='./boardList.do';</script>");
		}//리스트로 받은 게 아니므로(맵으로 받음) 사이즈로 받는 게 아님
		else {
			response.getWriter().print("<script>alert('로그인 정보가 없습니다');location.href='./loginForm.do';</script>");
		}
		return null;
	}
	
	@GetMapping(value="/logout.do")
	public String logout(HttpSession session, HttpServletResponse response) {
		log.info("UserController logout.do 로그아웃 이동");
		
		//로그아웃 하는 다른 방법- 헤더 정보를 바꿔줌
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate"); //캐시도 사용하지 않고 저장도 하지 않음
		response.setHeader("Expires", "0");
		
		session.invalidate(); //세션 지움
		return "redirect:/loginForm.do";
	}
	
	@GetMapping(value="/managementUser.do")
	public String managementUser(Model model) {
		log.info("UserController managementUser.do 회원관리 이동");
		
		List<UserVo> userList=service.getAllUser();
		model.addAttribute("userList",userList);
		
		return "managementUser";
	}
	
	@GetMapping(value="/userSelectAll.do")
	public String userSelectAll(Model model) {
		log.info("UserController userSelectAll.do [관리자] 회원 전체 조회");
		
		List<UserVo> userList=service.userSelectAll();
		model.addAttribute("userList",userList);
		
		return "userSelectAll";
	}
}
