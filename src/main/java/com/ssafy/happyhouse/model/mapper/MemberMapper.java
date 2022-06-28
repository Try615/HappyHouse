package com.ssafy.happyhouse.model.mapper;

import java.util.Map;

import com.ssafy.happyhouse.model.MemberDto;

public interface MemberMapper {
	MemberDto login(Map<String, String> map);
	MemberDto select(String id);
	int signUp(MemberDto memberDto);
	int modifyMem(MemberDto memberDto);
	int deleteMem(String id);
}
