package com.ssafy.happyhouse.model.service;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.InterestDto;
import com.ssafy.happyhouse.model.mapper.HouseMapMapper;
import com.ssafy.happyhouse.model.mapper.InterestMapper;

@Service
public class InterestServiceImpl implements InterestService {

	@Autowired
	private SqlSession sqlSession;

	@Override
	public int registerInterest(InterestDto interestDto) throws SQLException {
		return sqlSession.getMapper(InterestMapper.class).registerInterest(interestDto);
	}

	@Override
	public List<InterestDto> getInterestList(String userid) throws Exception {
		return sqlSession.getMapper(InterestMapper.class).getInterestList(userid);
	}

	@Override
	public int deleteInterest(InterestDto interestDto) throws SQLException {
		return sqlSession.getMapper(InterestMapper.class).deleteInterest(interestDto);
	}

}
