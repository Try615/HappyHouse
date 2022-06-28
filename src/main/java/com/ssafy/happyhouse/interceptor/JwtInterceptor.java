package com.ssafy.happyhouse.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.ssafy.happyhouse.model.service.JwtService;

import lombok.extern.slf4j.Slf4j;


@Component
@Slf4j
public class JwtInterceptor implements HandlerInterceptor{
	
	@Autowired
	private JwtService jwtService;
	
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
		System.out.println(request.getMethod() + " : " + request.getServletPath());

		// option 요청은 바로 통과.
		if("OPTIONS".equals(request.getMethod())) {
			return true;
		}else {
			// request의 parameter에서 auth_token으로 넘어온 것을 찾음.
			// String token = request.getParameter("auth_token");
			String token = request.getHeader("jwt-auth-token");
			System.out.println(token);
//			if(token == null) {
//				System.out.println("널");
//			}else {
//				System.out.println("널 아님");
//			}
			if(token != null && token.length() > 0) {
				System.out.println("들어옴");
				// 유효한 토큰이면 진행, 그렇지 않으면 예외 발생.
				jwtService.checkValid(token, response);
				System.out.println("검증 성공");
				return true;
			}else {
				throw new RuntimeException("인증 토큰이 없습니다.");
			}
		}
	}
}
