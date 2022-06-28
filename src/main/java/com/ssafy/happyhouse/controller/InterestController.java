package com.ssafy.happyhouse.controller;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.InterestDto;
import com.ssafy.happyhouse.model.MemberDto;
import com.ssafy.happyhouse.model.service.InterestService;
import com.ssafy.happyhouse.model.service.JwtService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/interest")
@CrossOrigin("*")
@Slf4j
public class InterestController {

	@Autowired
	InterestService interestService;
	
	@GetMapping("/{id}")
	public ResponseEntity<List<InterestDto>> list(@PathVariable String id) throws Exception{
		System.out.println("interest : " + id);
		List<InterestDto> list = interestService.getInterestList(id);
		if(list != null) {
			return new ResponseEntity<List<InterestDto>>(list, HttpStatus.OK);
		}else {
			return new ResponseEntity<List<InterestDto>>(list, HttpStatus.NO_CONTENT);
		}
	}
	
	@DeleteMapping("/{sido}/{gugun}/{dong}/{id}")
	public ResponseEntity<List<InterestDto>> delete(@PathVariable String sido, @PathVariable String gugun, @PathVariable String dong, @PathVariable String id) throws SQLException{
//		System.out.println("interest delete : " + sido);
//		System.out.println("interest delete : " + gugun);
//		System.out.println("interest delete : " + dong);
		InterestDto interestDto = new InterestDto();
		interestDto.setUserid(id);
		interestDto.setSido(sido);
		interestDto.setGugun(gugun);
		interestDto.setDong(dong);
		if(interestService.deleteInterest(interestDto) == 1) {
			List<InterestDto> list;
			try {
				list = interestService.getInterestList(id);
				return new ResponseEntity<List<InterestDto>>(list, HttpStatus.OK);
			} catch (Exception e) {
				e.printStackTrace();
				return new ResponseEntity(HttpStatus.UNAUTHORIZED);
			}
		}else {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
	}
	
	@PostMapping("/add")
	public ResponseEntity<List<InterestDto>> add(@RequestBody InterestDto interestDto){
		try {
			if(interestService.registerInterest(interestDto) == 1) {
				List<InterestDto> list = interestService.getInterestList(interestDto.getUserid());
				return new ResponseEntity<List<InterestDto>>(list, HttpStatus.OK);
			}else
				return new ResponseEntity(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity(HttpStatus.UNAUTHORIZED);
		}
	}
//	@GetMapping("")
//	public String interest(Model model, HttpSession session) {
//		MemberDto memberDto = (MemberDto) session.getAttribute("userinfo");
//		String userid = memberDto.getUserid();
//	
//		try {
//			List<InterestDto> list = interestService.getInterestList(userid);
//			model.addAttribute("list", list);
//		} catch (Exception e) {
//			e.printStackTrace();
//			model.addAttribute("msg", "처리 과정 중 실패!");
//			return "error";
//		}
//		return "interest";
//	}
//	
//	@GetMapping("/list")
//	public ResponseEntity<List<InterestDto>> list(String userid) {
//		try {
//			List<InterestDto> list = interestService.getInterestList(userid);
//			return new ResponseEntity<List<InterestDto>>(list, HttpStatus.OK);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return new ResponseEntity(HttpStatus.NO_CONTENT);
//		}
//	}
//	
//	@PostMapping("/register")
//	public String register(InterestDto interestDto, Model model) {
//		try {
//			interestService.registerInterest(interestDto);
//			return "redirect:/interest";
//		} catch (Exception e) {
//			e.printStackTrace();
//			model.addAttribute("msg", "처리 과정 중 실패!");
//			return "error";
//		}
//	}
//	
//	@PostMapping("/delete")
//	public String delete(InterestDto interestDto, Model model) {
//		try {
//			interestService.deleteInterest(interestDto);
//			return "redirect:/interest";
//		} catch (Exception e) {
//			e.printStackTrace();
//			model.addAttribute("msg", "처리 과정 중 실패!");
//			return "error";
//		}
//	}
	
	
}
