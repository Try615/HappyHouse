package com.ssafy.happyhouse.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.QnADto;
@Mapper
public interface QnAMapper {
	public List<QnADto> selectQnA();
	public QnADto selectQnAByNo(int no);
	public int insertQnA(QnADto board);
	public int updateQnA(QnADto board);
	public int deleteQnA(int no);
	public int updateAnswer(Map<String, String> map);
}