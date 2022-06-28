package com.ssafy.happyhouse.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import com.ssafy.happyhouse.model.service.JwtService;
import com.ssafy.happyhouse.model.service.NoticeService;

@RestController
@RequestMapping("/notice")
@CrossOrigin("*")
public class NoticeController {
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private NoticeService noticeService;

	@GetMapping("/list")
	public ResponseEntity<List<NoticeDto>> list(){
		List<NoticeDto> list = null;
		try {
			list = noticeService.listNotice();	
			if(list.size() == 0) {
				return new ResponseEntity<List<NoticeDto>>(list, HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<List<NoticeDto>>(list, HttpStatus.OK);
		} catch (Exception e1) {
			return new ResponseEntity<List<NoticeDto>>(list, HttpStatus.NO_CONTENT);
		}
		
	}
	
	@GetMapping("{no}")
	public ResponseEntity<NoticeDto> article(@PathVariable String no, HttpServletRequest request, HttpServletResponse response) throws Exception{
		String token = request.getHeader("jwt-auth-token");
		jwtService.checkValid(token, response);
		response.setHeader("jwt-auth-token", token);
		return new ResponseEntity<NoticeDto>(noticeService.getArticle(no), HttpStatus.OK);
	}
	
	@PutMapping("{no}")
	public ResponseEntity<String> modify(@RequestBody NoticeDto noticeDto) throws Exception{
		int res = noticeService.modifyArt(noticeDto.getContent(), noticeDto.getTitle(), noticeDto.getNo());
		if (res == 1) {
			return new ResponseEntity<String>("success", HttpStatus.OK);
		}
		return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping("{no}")
	public ResponseEntity<String> delete(@PathVariable int no) throws Exception{
		System.out.println(no);
		int res = noticeService.deleteArt(no);
		if(res == 1)
			return new ResponseEntity<String>("success", HttpStatus.OK);
		return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT);
	}
	
	@PostMapping()
	public ResponseEntity<String> post(@RequestBody NoticeDto noticeDto) throws Exception{
		System.out.println(noticeDto.toString());
		System.out.println("notice post");
		int res = noticeService.postNotice(noticeDto);
		if (res == 1) {
			return new ResponseEntity<String>("success", HttpStatus.OK);
		}
		return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT);
	}
//	@GetMapping("/list")
//	public String list(@RequestParam("pg") String pg, @RequestParam("word") String word, Model model) {
//		if (pg.equals(""))
//			pg = "1";
//
//		String spp = (String) model.getAttribute("spp");
//		int sizePerPage = spp == null ? 10 : Integer.parseInt(spp);// sizePerPage
//
//		try {
//			model.addAttribute("list",
//					noticeService.listNotice((Integer.parseInt(pg) - 1) * sizePerPage, sizePerPage, word));
//			PageNavigation pageNavigation = noticeService.makePageNavigation(Integer.parseInt(pg), sizePerPage, word);
//			model.addAttribute("navigation", pageNavigation);
//			model.addAttribute("pg", pg);
//			model.addAttribute("word", word);
//		} catch (NumberFormatException e) {
//			e.printStackTrace();
//			model.addAttribute("msg", "글조회 처리 중 문제가 발생했습니다.");
//			return "error";
//		} catch (Exception e) {
//			e.printStackTrace();
//			model.addAttribute("msg", "글조회 처리 중 문제가 발생했습니다.");
//			return "error";
//		}
//
//		return "noticeList";
//	}

//	@GetMapping("/article/no={no}&pg={pg}&word={word}")
//	public String article(@PathVariable String no, @PathVariable("pg") String pg, @PathVariable("word") String word,
//			Model model) {
//
//		if (pg.equals(""))
//			pg = "1";
//		String spp = (String) model.getAttribute("spp");
//		int sizePerPage = spp == null ? 10 : Integer.parseInt(spp);// sizePerPage
//
//		NoticeDto dto;
//		try {
//			dto = noticeService.getArticle(no);
//			model.addAttribute("noticeArticle", dto);
//			model.addAttribute("pg", pg);
//			model.addAttribute("word", word);
//		} catch (Exception e) {
//			e.printStackTrace();
//			model.addAttribute("msg", "글조회 처리 중 문제가 발생했습니다.");
//			return "error";
//		}
//
//		return "noticeArticle";
//	}
//
//	@GetMapping("/modify")
//	public String mvmodify(@RequestParam String no, Model model) {
//		try {
//			NoticeDto noticeDto = noticeService.getArticle(no);
//			model.addAttribute("article", noticeDto);
//		} catch (Exception e) {
//			e.printStackTrace();
//			model.addAttribute("msg", "글수정 처리 중 문제가 발생했습니다.");
//			return "error";
//		}
//
//		return "noticeModify";
//	}
//
//	@PostMapping("/modify/no={no}")
//	public String modify(@PathVariable String no, @RequestParam String content, @RequestParam String title,
//			Model model) {
//		try {
//			noticeService.modifyArt(content, title, Integer.parseInt(no));
//			return "redirect:/notice/list/pg=1&word=";
//		} catch (NumberFormatException e) {
//			e.printStackTrace();
//			model.addAttribute("msg", "글조회 처리 중 문제가 발생했습니다.");
//			return "error";
//		} catch (Exception e) {
//			e.printStackTrace();
//			model.addAttribute("msg", "글조회 처리 중 문제가 발생했습니다.");
//			return "error";
//		}
//	}
//
//	@GetMapping("/delete/no={no}")
//	public String delete(@PathVariable String no, Model model) {
//		try {
//			noticeService.deleteArt(Integer.parseInt(no));
//		} catch (NumberFormatException e) {
//			e.printStackTrace();
//			model.addAttribute("msg", "글조회 처리 중 문제가 발생했습니다.");
//			return "error";
//		} catch (Exception e) {
//			e.printStackTrace();
//			model.addAttribute("msg", "글조회 처리 중 문제가 발생했습니다.");
//			return "error";
//		}
//
//		return "redirect:/notice/list?pg=1&word=";
//	}
//
//	@GetMapping("/post")
//	public String mvpost() {
//		return "noticePost";
//	}
//
//	@PostMapping("/post")
//	public String post(@RequestParam String title, @RequestParam String content, HttpSession session, Model model) {
//		MemberDto memberDto = (MemberDto) session.getAttribute("userinfo");
//		if (memberDto != null) {
//			NoticeDto noticeDto = new NoticeDto();
//			noticeDto.setTitle(title);
//			noticeDto.setContent(content);
//			noticeDto.setViews(0);
//			noticeDto.setAuthorId(memberDto.getUserid());
//
//			try {
//				noticeService.postNotice(noticeDto);
//
//			} catch (Exception e) {
//				e.printStackTrace();
//				model.addAttribute("msg", "글작성 처리 중 문제가 발생했습니다.");
//				return "error";
//			}
//		}
//		return "redirect:/notice/list?pg=1&word=";
//	}

}
