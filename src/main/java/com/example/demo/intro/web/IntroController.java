package com.example.demo.intro.web;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("intro")
public class IntroController {

	public IntroController() {
		super();
	}
	
	@GetMapping("")
	public String getIntro(HttpSession session, HttpServletRequest request) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		System.out.println("intro 컨트롤러====================");
		System.out.println("date : " + sdf.format(date));
		// 접근 날짜
		System.out.println("session.getCreationTime() : " + sdf.format(session.getCreationTime()));
		System.out.println("session.getLastAccessedTime() : " + sdf.format(session.getLastAccessedTime()));
		System.out.println("intro 컨트롤러====================");
		return "intro/intro";
	}

}
