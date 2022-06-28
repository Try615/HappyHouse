package com.ssafy.happyhouse.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.happyhouse.model.QnADto;
import com.ssafy.happyhouse.model.mapper.QnAMapper;

@Service
public class QnAServiceImpl implements QnAService {
	
    @Autowired
	private QnAMapper qnaMapper;

    @Override
	public List<QnADto> retrieveQnA() {
		return qnaMapper.selectQnA();
	}
    
  	@Override
	public int writeQnA(QnADto qna) {
		return qnaMapper.insertQnA(qna);
	}

	@Override
	public QnADto detailQnA(int no) {
		return qnaMapper.selectQnAByNo(no);
	}

	@Override
	@Transactional
	public boolean updateQnA(QnADto qna) {
		return qnaMapper.updateQnA(qna) == 1;
	}

	@Override
	@Transactional
	public boolean deleteQnA(int no) {
		return qnaMapper.deleteQnA(no) == 1;
	}

	@Override
	public int updateAnswer(Map<String, String> map) {
		return qnaMapper.updateAnswer(map);
	}
}