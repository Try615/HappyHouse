package com.ssafy.happyhouse.model.mapper;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.happyhouse.model.InterestDto;

public interface InterestMapper {

	int registerInterest(InterestDto interestDto)throws SQLException;
	int deleteInterest(InterestDto interestDto)throws SQLException;
	List<InterestDto> getInterestList(String userid) throws SQLException;

}
