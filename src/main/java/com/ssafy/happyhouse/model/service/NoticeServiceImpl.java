package com.ssafy.happyhouse.model.service;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.NoticeDto;
import com.ssafy.happyhouse.model.mapper.NoticeMapper;
import com.ssafy.happyhouse.util.PageNavigation;

@Service
public class NoticeServiceImpl implements NoticeService{

	private static final Logger logger = LoggerFactory.getLogger(HouseMapServiceImpl.class);

	@Autowired
	private SqlSession sqlSession;

	
	@Override
	public NoticeDto getArticle(String no) throws Exception {
		return sqlSession.getMapper(NoticeMapper.class).getArticle(no);
	}

	@Override
	public int postNotice(NoticeDto noticeDto) throws Exception {
		return sqlSession.getMapper(NoticeMapper.class).postNotice(noticeDto);
	}

//	@Override
//	public List<NoticeDto> listNotice(int currentPage, int sizePerPage,String word) throws Exception {
//		HashMap<String, String> map =new HashMap<String, String>();
//		map.put("currentPage", Integer.toString(currentPage));
//		map.put("sizePerPage", Integer.toString(sizePerPage));
//		map.put("word", word);
//		
//		return sqlSession.getMapper(NoticeMapper.class).listNotice(map);
//	}
	@Override
	public List<NoticeDto> listNotice() throws Exception {
		return sqlSession.getMapper(NoticeMapper.class).listNotice();
	}

	@Override
	public int modifyArt(String content, String title, int no) throws Exception{
		NoticeDto dto = new NoticeDto();
		dto.setContent(content);
		dto.setTitle(title);
		dto.setNo(no);
		
		return sqlSession.getMapper(NoticeMapper.class).modifyArt(dto);
	}

	@Override
	public int deleteArt(int no) throws Exception{
		return sqlSession.getMapper(NoticeMapper.class).deleteArt(no);
	}

	@Override
	public PageNavigation makePageNavigation(int currentPage, int sizePerPage,  String word)
			throws Exception {
		int naviSize = 5;
		PageNavigation pageNavigation = new PageNavigation();
		pageNavigation.setCurrentPage(currentPage);
		pageNavigation.setNaviSize(naviSize);
		int totalCount = sqlSession.getMapper(NoticeMapper.class).getTotalCount(word);//총글갯수  269
		pageNavigation.setTotalCount(totalCount);  
		int totalPageCount = (totalCount - 1) / sizePerPage + 1;//27
		pageNavigation.setTotalPageCount(totalPageCount);
		boolean startRange = currentPage <= naviSize;
		pageNavigation.setStartRange(startRange);
		boolean endRange = (totalPageCount - 1) / naviSize * naviSize < currentPage;
		pageNavigation.setEndRange(endRange);
		pageNavigation.makeNavigator();
		return pageNavigation;
	}

}
