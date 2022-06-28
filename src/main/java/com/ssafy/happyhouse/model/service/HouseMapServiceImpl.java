package com.ssafy.happyhouse.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.*;
import com.ssafy.happyhouse.model.mapper.HouseMapMapper;

@Service
public class HouseMapServiceImpl implements HouseMapService {
	private static final Logger logger = LoggerFactory.getLogger(HouseMapServiceImpl.class);

	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<SidoGugunCodeDto> getSido() throws Exception {
		return sqlSession.getMapper(HouseMapMapper.class).getSido();
	}

	@Override
	public List<SidoGugunCodeDto> getGugunInSido(String sido) throws Exception {
		return sqlSession.getMapper(HouseMapMapper.class).getGugunInSido(sido);
	}

	@Override
	public List<HouseInfoDto> getDongInGugun(String gugun) throws Exception {
		return sqlSession.getMapper(HouseMapMapper.class).getDongInGugun(gugun);
	}

	@Override
	public List<HouseInfoDto> getAptInDong(String dong) throws Exception {
		return sqlSession.getMapper(HouseMapMapper.class).getAptInDong(dong);
	}

	@Override
	public List<HouseDealDto> getDeal(String dong, String aptName) throws Exception {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("dong", dong);
		map.put("aptName", aptName);
		return sqlSession.getMapper(HouseMapMapper.class).getDeal(map);
	}

	@Override
	public List<String> getRank() {
		return sqlSession.getMapper(HouseMapMapper.class).getRank();
	}

}
