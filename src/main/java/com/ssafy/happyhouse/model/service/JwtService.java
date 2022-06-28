package com.ssafy.happyhouse.model.service;

import java.lang.reflect.Member;
import java.time.Clock;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;

import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParser;
import com.ssafy.happyhouse.model.MemberDto;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JwtService {
	
	@Value("${jwt.salt}")
	private String salt;
	
	@Value("${jwt.expmin}")
	private Long expireMin;
	
	// 로그인 성공 시, 사용자 정보를 기반으로 JWT Token 생성해서 반환.
	public String create(final MemberDto member) {
		final JwtBuilder builder = Jwts.builder();
		
		// JWT Token = Header + Payload + Signature.
		// Header 설정
		builder.setHeaderParam("typ", "JWT"); // 토큰의 타입으로 고정 값.
		
		// Payload 설정 - claim 정보 포함.
		builder.setSubject("loginToken")
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * expireMin))
				.claim("Member", member); // 담고 싶은 정보 설정.
		
		// signature - secret key를 이용한 암호화.
		builder.signWith(SignatureAlgorithm.HS256, salt.getBytes());
		
		// 마지막 직렬화 처리.
		final String jwt = builder.compact();
		return jwt;
	}
	
	// jwt 토큰을 분석해서 필요한 정보를 반환.
	public void checkValid(final String jwt, HttpServletResponse response) {
		// 문제 없었는지, 즉 예외가 발생하지 않았으면 OK
		System.out.println("jwt : " + jwt);
		Claims claims = Jwts.parser().setSigningKey(salt.getBytes()).parseClaimsJws(jwt).getBody();
		claims.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * expireMin));		

//		LinkedHashMap<String, String> map = (LinkedHashMap<String, String>) claims.get("Member");
//		System.out.println(map.get("userid"));
		
		response.setHeader("jwt-auth-token", Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS256, salt.getBytes()).compact());
	}

	
	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		Claims claims = Jwts.parser().setSigningKey(salt.getBytes()).parseClaimsJws(token).getBody();
		return claimsResolver.apply(claims);
	}
		
	// jwt 토큰을 분석해서 필요한 정보를 반환
	public Map<String, Object> get(final String jwt){
		Jws<Claims> claims = null;
		try {
			claims = Jwts.parser().setSigningKey(salt.getBytes()).parseClaimsJws(jwt);
		} catch (Exception e) {
			throw new RuntimeException();
		}
		// claims는 Map의 구현체.
		return claims.getBody();
	}
}
