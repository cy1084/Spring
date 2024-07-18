package com.min.edu.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class DaoLogAop_Anno {
	
	@Pointcut("execution(public * com.min.edu.model.mapper.*Dao*.*(..))")
	public void pointCut() {}
	
	@Before("pointCut()")
	public void before(JoinPoint j) {
		Logger log = LoggerFactory.getLogger(j.getTarget() + "");
		log.info("[AOP Logger] Dao 메소드 실행 전");
		Object[] args = j.getArgs();
		if (j.getArgs() != null) {
			log.info("[AOP Logger] : \t {}", j.getSignature().getName());
			for (int i = 0; i < args.length; i++) {
				log.info(i + "번째" + args[i]);
			}
			log.info("[AOP Logger] : \t {}", j.getSignature().getName());
		}
	}
	
	@AfterReturning(pointcut = "pointCut()", returning = "returnValue")
	public void afterReturing(JoinPoint j, Object returnValue) {
		Logger log = LoggerFactory.getLogger(j.getTarget() + "");
		log.info("[AOP Logger 오류] : \t {}", returnValue);
	}
	
	@AfterThrowing(pointcut = "pointCut()", throwing = "error")
	public void afterThrowing(JoinPoint j, Exception error) {
		Logger log = LoggerFactory.getLogger(j.getTarget() + "");
		log.info("[AOP Logger 오류] : \t {}", error.getMessage());
	}
}
