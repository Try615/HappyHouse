package com.ssafy.happyhouse.model.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.HospitalDto;
import com.ssafy.happyhouse.model.InterestDto;
import com.ssafy.happyhouse.model.OfficeDto;
import com.ssafy.happyhouse.model.mapper.CoronaMapper;

@Service
public class CoronaServiceImpl implements CoronaService{
	
	private static final Logger logger = LoggerFactory.getLogger(MemberServiceImpl.class);
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<HospitalDto> getHospitalList(String sido, String gugun) {
		return sqlSession.getMapper(CoronaMapper.class).getHospitalList(sido, gugun);
	}

	@Override
	public List<OfficeDto> getOfficeList(String sido, String gugun) {
		return sqlSession.getMapper(CoronaMapper.class).getOfficeList(sido, gugun);
	}

}
