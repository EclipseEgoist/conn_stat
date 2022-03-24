package com.example.demo.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Deprecated
//@Configuration
public class ContextCommon {
	private static final Logger log = LoggerFactory.getLogger(ContextCommon.class);
	
	private Environment env;
	
	public ContextCommon(Environment env) {
		super();
		this.env = env;
	}
	
	@Bean(destroyMethod = "close")
	public DataSource dataSource() {
		String url = env.getProperty("spring.datasource.url");
		String user = env.getProperty("spring.datasource.username");
		String pass = env.getProperty("spring.datasource.password");
		String driverClass = env.getProperty("spring.datasource.driverClassName");
		
		String validationQuery = "SELECT 1";
		
		BasicDataSource source = new BasicDataSource();
		
		source.setDriverClassName(driverClass);
		source.setUrl(url);
		source.setUsername(user);
		source.setPassword(pass);
		source.setValidationQuery(validationQuery);
		log.info("DB 주소 : " + url);
		log.info("사용자명 : " + user);
		
		return source;
	}

}
