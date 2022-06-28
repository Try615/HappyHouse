package com.ssafy.happyhouse.model.service;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.MemberDto;
import com.ssafy.happyhouse.model.mapper.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService {

	private static final Logger logger = LoggerFactory.getLogger(MemberServiceImpl.class);

	@Autowired
	private SqlSession sqlSession;

	@Override
	public MemberDto login(Map<String, String> map) {
		System.out.println("Service: " + map);
		if(map.get("userid") == null || map.get("userpwd") == null)
			return null;
		return sqlSession.getMapper(MemberMapper.class).login(map);
	}

	@Override
	public MemberDto select(String id) {
		return sqlSession.getMapper(MemberMapper.class).select(id);
	}

	@Override
	public int modifyMem(MemberDto memberDto) {
		return sqlSession.getMapper(MemberMapper.class).modifyMem(memberDto);
	}

	@Override
	public int deleteMem(String id) {
		return sqlSession.getMapper(MemberMapper.class).deleteMem(id);
	}

	@Override
	public int signUp(MemberDto memberDto) {
		return sqlSession.getMapper(MemberMapper.class).signUp(memberDto);
	}

}
