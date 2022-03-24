package com.example.demo.visit.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.domain.Visitor;
import com.example.demo.visit.dto.VisitorDto;

@Mapper
public interface VisitorMapper {
	
	void saveVisitor(Visitor bean);
	
	List<VisitorDto> getList();
	
	List<VisitorDto> getChartByTime(VisitorDto dto);
	
	Long getVisitSeq();
	
}
