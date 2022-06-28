package com.ssafy.happyhouse.model.service;

import java.util.Map;

import com.ssafy.happyhouse.model.MemberDto;

public interface MemberService {
	MemberDto login(Map<String, String> map);
	MemberDto select(String id);
	int signUp(MemberDto memberDto);
	int modifyMem(MemberDto memberDto);
	int deleteMem(String id);
}
