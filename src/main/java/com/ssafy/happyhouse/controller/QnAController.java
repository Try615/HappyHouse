package com.ssafy.happyhouse.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.NoticeDto;
import com.ssafy.happyhouse.model.QnADto;
import com.ssafy.happyhouse.model.service.JwtService;
import com.ssafy.happyhouse.model.service.QnAService;

@CrossOrigin("*")
@RestController
@RequestMapping("/qna")
public class QnAController {

	private static final Logger logger = LoggerFactory.getLogger(QnAController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private QnAService qnaService;

	@GetMapping("/list")
	public ResponseEntity<List<QnADto>> retrieveBoard() {
		List<QnADto> list = qnaService.retrieveQnA();
		if(list.size() == 0) {
			return new ResponseEntity<List<QnADto>>(list, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<QnADto>>(list, HttpStatus.OK);
	}
    
	@GetMapping("{no}")
	public ResponseEntity<QnADto> detailBoard(@PathVariable int no, HttpServletRequest request, HttpServletResponse response) {
		String token = request.getHeader("jwt-auth-token");
		jwtService.checkValid(token, response);
		response.setHeader("jwt-auth-token", token);
		return new ResponseEntity<QnADto>(qnaService.detailQnA(no), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<String> writeQnA(@RequestBody QnADto qnaDto) {
		System.out.println("qnaㄷ르어옴");
		System.out.println(qnaDto);
		if (qnaService.writeQnA(qnaDto) == 1) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@PostMapping("/answer")
	public ResponseEntity<String> writeAnswer(@RequestBody Map<String, String> map) {
		System.out.println("qnaㄷ르어옴");
		System.out.println(map);
		if (qnaService.updateAnswer(map) == 1) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	@PutMapping("{no}")
	public ResponseEntity<String> updateBoard(@RequestBody QnADto qna) {		
		if (qnaService.updateQnA(qna)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	@DeleteMapping("{no}")
	public ResponseEntity<String> deleteBoard(@PathVariable int no) {
		if (qnaService.deleteQnA(no)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
}