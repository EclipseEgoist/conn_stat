package com.example.demo.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

//@Component
//@Aspect
class LogAspect {
	private static final Logger log = LoggerFactory.getLogger(LogAspect.class);
	
	//@Around("@annotation(LogExecutionTime)")
	public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		
		Object proceed = joinPoint.proceed();
		stopWatch.stop();
		log.info(stopWatch.prettyPrint());
		return proceed;
	}

}
