package com.ssafy.happyhouse.model.service;

import java.util.List;

import com.ssafy.happyhouse.model.HospitalDto;
import com.ssafy.happyhouse.model.InterestDto;
import com.ssafy.happyhouse.model.OfficeDto;

public interface CoronaService {
	List<HospitalDto> getHospitalList(String sido, String gugun);
	List<OfficeDto> getOfficeList(String sido, String gugun);
}
