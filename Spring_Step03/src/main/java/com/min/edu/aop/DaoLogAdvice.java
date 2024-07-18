package com.min.edu.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//어드바이스에서 사용할 메소드 생성
//org.aspect.lang.JoinPoint
public class DaoLogAdvice {
	// 메소드 실행 전(before)
	public void before(JoinPoint j) {
		Logger logger = LoggerFactory.getLogger(j.getTarget() + "");
		logger.info("메소드 시작");
		Object[] obj = j.getArgs();

		if (obj != null && obj.length != 0) {
			logger.info("method \t {}", j.getSignature().getName());

			for (int i = 0; i < obj.length; i++) {
				logger.info(i + "번째"+obj[i].toString());
			}
			logger.info("method \t {}", j.getSignature().getName());
		}
	}

	// 메소드 실행 후(return 값 있을 때/ afterReturn)
	public void afterReturning(JoinPoint j) throws Throwable {
		Logger logger = LoggerFactory.getLogger(j.getTarget() + "");
		ProceedingJoinPoint jp = (ProceedingJoinPoint) j;
		logger.info("반환 종료: {}", jp.proceed());
	}

	// 메소드 예외(afterThrowing)
	public void error(JoinPoint j, Exception e) {

		Logger logger = LoggerFactory.getLogger(j.getTarget() + "");
		logger.info("에러 \t{}", j.getArgs());
		logger.info("에러 \t{}", e.toString());
	}
}
