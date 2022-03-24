package com.example.demo.uap;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface UserAgentParserService {
	
	void parseRequest(HttpServletRequest request) throws Exception;
	
	Map<String, String> parseUA(String ua);
	
}
