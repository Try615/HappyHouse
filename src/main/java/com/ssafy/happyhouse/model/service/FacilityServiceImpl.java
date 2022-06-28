package com.ssafy.happyhouse.model.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.BikeDto;
import com.ssafy.happyhouse.model.CctvDto;
import com.ssafy.happyhouse.model.EschoolDto;
import com.ssafy.happyhouse.model.ParkDto;
import com.ssafy.happyhouse.model.mapper.FacilityMapper;

@Service
public class FacilityServiceImpl implements FacilityService {

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<CctvDto> getcctv() {
		return sqlSession.getMapper(FacilityMapper.class).getcctv();
	}

	@Override
	public List<EschoolDto> getEschool() {
		return sqlSession.getMapper(FacilityMapper.class).getEschool();
	}

	@Override
	public List<BikeDto> getBike() {
		return sqlSession.getMapper(FacilityMapper.class).getBike();
	}

	@Override
	public List<ParkDto> getPark() {
		return sqlSession.getMapper(FacilityMapper.class).getPark();
	}

}
