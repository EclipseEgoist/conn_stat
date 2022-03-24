package com.example.demo.visit.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Visitor;
import com.example.demo.visit.dao.VisitorMapper;
import com.example.demo.visit.dto.VisitorDto;

@Service
public class VisitorServiceImpl implements VisitorService {
	
	private VisitorMapper vMapper;
	
	public VisitorServiceImpl(VisitorMapper vMapper) {
		super();
		this.vMapper = vMapper;
	}

	@Override
	public void saveVisitor(Visitor bean) {
		if(bean.getVisitSeq() == null) {
			bean.setVisitSeq(vMapper.getVisitSeq());
		}
		vMapper.saveVisitor(bean);
	}

	@Override
	public JSONArray getList(VisitorDto dto) throws Exception {
		System.out.println("dto : " + BeanUtils.describe(dto));
		if(dto != null) {
			if(StringUtils.isNotEmpty(dto.getEndDate())) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
				SimpleDateFormat sdfTime = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				Date date = sdf.parse(dto.getEndDate());
				Calendar cal = Calendar.getInstance();
				cal.setTime(date);
				cal.add(Calendar.DATE, 1);
				cal.set(Calendar.HOUR_OF_DAY, 0);
				cal.set(Calendar.MINUTE, 0);
				cal.set(Calendar.SECOND, 0);
				dto.setEndDate(sdfTime.format( new Date(cal.getTimeInMillis()) ));
			}
			List<VisitorDto> dtoList = vMapper.getChartByTime(dto);
			JSONArray jArr = new JSONArray(dtoList);
			return jArr;
		} else {
			List<VisitorDto> dtoList = vMapper.getList();
			if(dtoList.size() != 0) {
				JSONArray jArr = new JSONArray(dtoList);
				return jArr;
			} else {
				return null;
			}
		}
	}
	
	
	
}
