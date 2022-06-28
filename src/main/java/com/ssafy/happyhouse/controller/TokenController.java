package com.ssafy.happyhouse.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/token")
@CrossOrigin("*")
@Slf4j
public class TokenController {

	@GetMapping
	public ResponseEntity<String> check(HttpServletResponse response) throws Exception {
		System.out.println("token check");
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
}
