package com.ssafy.happyhouse.model.mapper;

import java.util.List;

import com.ssafy.happyhouse.model.HospitalDto;
import com.ssafy.happyhouse.model.OfficeDto;

public interface CoronaMapper {
	List<HospitalDto> getHospitalList(String sido, String gugun);

	List<OfficeDto> getOfficeList(String sido, String gugun);
}
