package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.demo.interceptor.service.ConnInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	
	@Autowired
	private ConnInterceptor connInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(connInterceptor).addPathPatterns("/**");
		WebMvcConfigurer.super.addInterceptors(registry);
	}
	
	
}
