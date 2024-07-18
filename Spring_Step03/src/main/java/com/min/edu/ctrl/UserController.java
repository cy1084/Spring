package com.min.edu.ctrl;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.min.edu.dto.UserInfoDto;
import com.min.edu.service.IUserInfoService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class UserController {

	@Autowired
	private IUserInfoService service;
	
	@PostMapping(value="/loginServlet.do")
	public String login(@RequestParam Map<String,Object> map, HttpServletRequest request) {
		log.info("로그인: {}",map);
		UserInfoDto loginDto= service.login(map);
		HttpSession session=request.getSession();
		session.setAttribute("loginDto",loginDto);
		return "redirect:/boardList.do";
	}
	
	@GetMapping(value="/loginServlet.do")
	public String logout(HttpServletRequest request) {
		HttpSession session=request.getSession();
		session.invalidate();
		return "loginForm"; 
		//리다이렉트와 그냥의 차이
	}
}
