package com.ssafy.happyhouse.model.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.happyhouse.model.InterestDto;

public interface InterestService {

	int registerInterest(InterestDto interestDto) throws SQLException;
	List<InterestDto> getInterestList(String userid) throws Exception;
	int deleteInterest(InterestDto interestDto) throws SQLException;
}
