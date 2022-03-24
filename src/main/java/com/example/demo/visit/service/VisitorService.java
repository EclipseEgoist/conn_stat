package com.example.demo.visit.service;

import org.json.JSONArray;

import com.example.demo.domain.Visitor;
import com.example.demo.visit.dto.VisitorDto;

public interface VisitorService {
	
	void saveVisitor(Visitor bean);
	
	JSONArray getList(VisitorDto dto) throws Exception;
	

}
