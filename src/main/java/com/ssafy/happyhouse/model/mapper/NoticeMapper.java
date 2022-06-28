package com.ssafy.happyhouse.model.mapper;

import java.util.HashMap;
import java.util.List;

import com.ssafy.happyhouse.model.NoticeDto;

public interface NoticeMapper {

	int getTotalCount(String word) throws Exception;
	//List<NoticeDto> listNotice(HashMap<String,String> map) throws Exception;
	List<NoticeDto> listNotice();
	NoticeDto getArticle(String no) throws Exception;
	int postNotice(NoticeDto noticeDto) throws Exception;
	int modifyArt(NoticeDto noticeDto);
	int deleteArt(int no);
	void updateViews(String no);
}
