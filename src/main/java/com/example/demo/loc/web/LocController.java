package com.example.demo.loc.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("loc")
public class LocController {

	public LocController() {
		super();
	}
	
	@GetMapping("")
	public String getLoc() {
		return "loc/loc";
	}

}
