package com.min.edu.aop;

import org.aspectj.lang.JoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DaoLogAop_XML {
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

	public void afterReturing(JoinPoint j, Object returnValue) {
		Logger log = LoggerFactory.getLogger(j.getTarget() + "");
		log.info("[AOP Logger] : \t {}", returnValue);
	}

	public void afterThrowing(JoinPoint j, Exception error) {
		Logger log = LoggerFactory.getLogger(j.getTarget() + "");
		log.info("[AOP Logger 오류] : \t {}", error.getMessage());
	}
}
