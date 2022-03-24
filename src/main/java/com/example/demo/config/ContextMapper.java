package com.example.demo.config;

import java.io.IOException;

import javax.sql.DataSource;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

@Configuration
@MapperScan(basePackages = {"com.example.demo"}, annotationClass = Mapper.class, sqlSessionFactoryRef = "sqlSession")
public class ContextMapper {
	
	@Bean
	public SqlSessionFactoryBean sqlSession(DataSource dataSource) throws IOException {
		SqlSessionFactoryBean obj = new SqlSessionFactoryBean();
		obj.setDataSource(dataSource);
		obj.setConfigLocation(new ClassPathResource("mapper-config.xml"));
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		obj.setMapperLocations(resolver.getResources("../classes/**/*-h2.xml"));
		return obj;
	}
	
	@Bean
	public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactoryBean session) throws Exception {
		return new SqlSessionTemplate(session.getObject());
	}

}
