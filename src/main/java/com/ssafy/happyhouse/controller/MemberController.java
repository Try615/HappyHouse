package com.ssafy.happyhouse.controller;

import java.util.Map;
import javax.servlet.http.*;
import javax.xml.ws.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ssafy.happyhouse.model.MemberDto;
import com.ssafy.happyhouse.model.service.JwtService;
import com.ssafy.happyhouse.model.service.MemberService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/member")
@CrossOrigin("*")
@Slf4j
public class MemberController {

	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private MemberService memberService;

	@PostMapping("/login")
	public ResponseEntity<MemberDto> login(@RequestBody Map<String, String> map, HttpServletResponse response) {
		try {
			MemberDto memberDto = memberService.login(map);
			
			if(memberDto != null) {
				String token = jwtService.create(memberDto);
				response.setHeader("jwt-auth-token", token);
				return new ResponseEntity<MemberDto>(memberDto, HttpStatus.OK);
			}else {
				return new ResponseEntity<MemberDto>(memberDto, HttpStatus.NO_CONTENT);
			}
		}catch (Exception e) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping("/profile/{id}")
	public ResponseEntity<MemberDto> select(@PathVariable String id){
		//System.out.println("profile: " + id);
		MemberDto memberDto = memberService.select(id);
		if(memberDto != null) {
			return new ResponseEntity<MemberDto>(memberDto, HttpStatus.OK);
		}else {
			return new ResponseEntity<MemberDto>(memberDto, HttpStatus.NO_CONTENT);
		}
	}
	
	@PostMapping("/profile")
	public ResponseEntity<String> updateMember(@RequestBody MemberDto memberDto){
		if(memberService.modifyMem(memberDto) == 1)
			return new ResponseEntity<String>(HttpStatus.OK);
		else
			return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteMember(@PathVariable String id){
		if(memberService.deleteMem(id) == 1)
			return new ResponseEntity<String>(HttpStatus.OK);
		else
			return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
	}
	
	@PostMapping("/signup")
	public ResponseEntity<String> signup(@RequestBody MemberDto memberDto){
		if(memberService.signUp(memberDto) != 1)
			return new ResponseEntity<String>(HttpStatus.OK);
		else
			return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
	}
	
}
