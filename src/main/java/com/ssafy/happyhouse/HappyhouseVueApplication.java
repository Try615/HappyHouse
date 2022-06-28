package com.ssafy.happyhouse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ssafy.happyhouse.interceptor.JwtInterceptor;

@SpringBootApplication
public class HappyhouseVueApplication implements WebMvcConfigurer{

	public static void main(String[] args) {
		SpringApplication.run(HappyhouseVueApplication.class, args);
	}

	@Autowired
	private JwtInterceptor jwtInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(jwtInterceptor).addPathPatterns("/token")
				.addPathPatterns("/notice/**")
				.addPathPatterns("/qna/**")
				.addPathPatterns("/member/**")
				//.addPathPatterns("/interest/**")
//				.excludePathPatterns("/member/**")
				.excludePathPatterns("/notice/list")
				.excludePathPatterns("/qna/list")
				.excludePathPatterns("/member/login")
				.excludePathPatterns("/member/signup")
				.excludePathPatterns("/corona/**");
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
				.allowedOrigins("*")
				.allowedMethods("*")
				.allowedHeaders("*")
				.exposedHeaders("jwt-auth-token");
	}
	
	
}
