package com.ssafy.happyhouse.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.happyhouse.model.QnADto;

public interface QnAService {
	public List<QnADto> retrieveQnA();
	public QnADto detailQnA(int no);
	public int writeQnA(QnADto board);
	public boolean updateQnA(QnADto board);
	public boolean deleteQnA(int no);
	public int updateAnswer(Map<String, String> map);
}
