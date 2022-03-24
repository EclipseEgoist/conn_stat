package com.example.demo.interceptor.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.uap.UserAgentParserService;

@Component
public class ConnInterceptor implements HandlerInterceptor {
	private static final Logger log = LoggerFactory.getLogger(ConnInterceptor.class);
	private UserAgentParserService uapService;
	
	public ConnInterceptor(UserAgentParserService uapService) {
		super();
		this.uapService = uapService;
	}

	// 컨트롤러로 보내기 전에 처리하는 인터셉터
	// 반환이 false라면 컨트롤러로 요청을 안함.
	// 매개변수 object는 핸들러 정보를 의미한다. 
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		System.out.println("====================");
		System.out.println("preHandle");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		log.info("date : {}", sdf.format(date));
		log.info("url : {}", request.getRequestURI());
		// 접속 아이피
		log.info("req.getRemoteAddr() : {}", request.getRemoteAddr());
		log.info("req.getRemoteHost() : {}", request.getRemoteHost());
		// 무엇인지 아직은 모르겠음.
		log.info("req.getRemoteUser() : {}", request.getRemoteUser());
		// 접속포트
		log.info("req.getRemotePort() : {}", request.getRemotePort());
		// user-agent
		log.info("req.getHeader(\"User-Agent\") : {}", request.getHeader("User-Agent"));
		// 이전 사이트 정보, 현재는 로컬에서 돌고 있는 주소만 노출, 네이버 페이지에서 직접 주소를 치고 들어오면 null로 들어옴.
		// null 인지 아닌지로 판단해서 최초 접근인지 사이트 내 이동인지 파악해야 할듯 함.
		log.info("req.getHeader(\"referer\") : {}", request.getHeader("referer"));
		
		// data parsing 
		uapService.parseRequest(request);
		
		System.out.println("====================");
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}

	// 컨트롤러의 handler가 끝나면 처리됨
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("====================");
		System.out.println("postHandle");
		log.info("response status : {}", response.getStatus());
		System.out.println("====================");
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}

	// view까지 처리가 끝난 후에 처리됨.
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}
	
	
	
}
