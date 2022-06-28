package com.ssafy.happyhouse.model.service;

import java.util.List;

import com.ssafy.happyhouse.model.NoticeDto;
import com.ssafy.happyhouse.util.PageNavigation;


public interface NoticeService {
	PageNavigation makePageNavigation(int currentPage, int sizePerPage,String word) throws Exception;	
	//List<NoticeDto> listNotice(int currentPage, int sizePerPage,String word) throws Exception;
	List<NoticeDto> listNotice() throws Exception;
	NoticeDto getArticle(String no) throws Exception;
	int postNotice(NoticeDto noticeDto) throws Exception;
	int modifyArt(String content, String title, int no) throws Exception;
	int deleteArt(int no) throws Exception;
}
