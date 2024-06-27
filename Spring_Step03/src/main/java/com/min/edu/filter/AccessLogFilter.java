package com.min.edu.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import com.google.common.base.Strings;

import lombok.extern.slf4j.Slf4j;


//TODO 012 Filter를 통한 사용자 접속 정보 출력
@Slf4j
public class AccessLogFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		log.info("~~~~~~~필터 들어옴~~~~~~~");

	}

	// 전자 결제 때 사용, 출근 했다 했는데 아이피가 지하철일 때 추적!
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// 요청 정보 request와 header에 들어가 있음
		HttpServletRequest req = (HttpServletRequest) request;

		/*
		 * request 정보: 요청 주소(remoteAddr), 요청 param(queryString) 
		 * header 정보: 요청된 사용자의 어플리케이션타입, 운영체제, 브라우저 정보 User_Agent 이전 페이지의 주소, 유입경로 Referer Exipres,
		 * Cache-Controller, Pragma: 캐시 정보
		 */

		// 접근을 시도하는 사용자의 주소 출력
		String remoteAddr = req.getRemoteAddr();
		String url = req.getRequestURL().toString(); // http:// 부터 시작하는 전체 주소
		String uri = req.getRequestURI(); // 프로젝트 명부터 시작

		// 쿼리 스트링
		// String queryString=req.getQueryString();
		// queryString=(queryString==null||queryString=="")?"":queryString; //빡셈 -> 그래서
		// 나온 라이브러리: commons lang3 , guava

		String queryString = StringUtils.defaultIfEmpty(req.getQueryString(), ""); // commons: req.getQueryString()가 없으면
																					// ""로 바꿔~
		String guavaQueryString = Strings.nullToEmpty(req.getQueryString()); // guava, 위와 같음

		// header 정보
		String referer = StringUtils.defaultIfEmpty(req.getHeader("Referer"), "");
		String userAgent = StringUtils.defaultIfEmpty(req.getHeader("User-Agent"), "");

		log.info("이전 페이지: {}", referer);
		log.info("client 접속 정보: {}", userAgent);

		String fullUrl = url;
		fullUrl += StringUtils.isNoneEmpty(queryString) ? "?" + guavaQueryString : queryString;

		StringBuffer sb = new StringBuffer();
		sb.append(remoteAddr).append(":").append(fullUrl)
		.append(":").append(referer)
		.append(":").append(userAgent);

		log.info("[Filter Logger] {}",sb.toString());
	
		chain.doFilter(request, response);

	}

	@Override
	public void destroy() {
		log.info("~~~~~~~필터 나감~~~~~~~");

	}

}
