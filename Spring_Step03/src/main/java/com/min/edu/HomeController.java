package com.min.edu;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.extern.slf4j.Slf4j;

/*
 * TODO 004 Spring에서 관리하는 bean, @Controller를 통해 Front Controller(앞에서 분기해주는 애)를 구현
 */

@Slf4j
@Controller
public class HomeController {

	//TODO 005 처음 요청 시 실행되는 Mapping 메소드를 작성
	@GetMapping(value="/home.do")
	public String home(String name, Model model) {
		log.info("HomeController /home.do GET 요청");
		log.info("요청받은 전달 값: {}", name);
		
		model.addAttribute("name",name+"님 안녕하세요");
		
		return "home";
	}
	
	//TODO 009 index.jsp에서 호출된 /info.do name age의 값을 전송
	@PostMapping(value="/info.do")
	public String info(String name, int age) {
		log.info("HomeController /info.do POST 요청");
		log.info("전달받은 요청 값 {} / {}" , name,age);
		return null;
	}
}
