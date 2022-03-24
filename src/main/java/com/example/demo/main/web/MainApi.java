package com.example.demo.main.web;

import java.lang.reflect.InvocationTargetException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.visit.dto.VisitorDto;
import com.example.demo.visit.service.VisitorService;

@RestController
@RequestMapping("main")
public class MainApi {
	
	private VisitorService vService;
	
	public MainApi(VisitorService vService) {
		super();
		this.vService = vService;
	}
	
	@GetMapping("chart")
	public String getData(@ModelAttribute VisitorDto dto) throws Exception, InvocationTargetException, NoSuchMethodException {
		return vService.getList(dto).toString();
	}
	
}
