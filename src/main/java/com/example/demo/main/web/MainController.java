package com.example.demo.main.web;

import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.visit.service.VisitorService;

@Controller
@RequestMapping("main")
public class MainController {

	private VisitorService vService;
	
	public MainController(VisitorService vService) {
		super();
		this.vService = vService;
	}
	
	@GetMapping
	public String main(ModelMap map, HttpSession session, HttpServletRequest request) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		System.out.println("main 컨트롤러====================");
		// 브라우저마다 값이 달라진다.
		System.out.println("session.getId() : " + session.getId());
		// 접근 날짜
		System.out.println("session.getCreationTime() : " + sdf.format(session.getCreationTime()));
		System.out.println("session.getLastAccessedTime() : " + sdf.format(session.getLastAccessedTime()));
		System.out.println("main 컨트롤러====================");
		//vService.insertVisitor(session);
		return "main/main";
	}
	
}
