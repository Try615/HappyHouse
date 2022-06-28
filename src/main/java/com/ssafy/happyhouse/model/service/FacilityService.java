package com.ssafy.happyhouse.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.happyhouse.model.BikeDto;
import com.ssafy.happyhouse.model.CctvDto;
import com.ssafy.happyhouse.model.EschoolDto;
import com.ssafy.happyhouse.model.ParkDto;

public interface FacilityService {
	List<CctvDto> getcctv();
	List<EschoolDto> getEschool();
	List<BikeDto> getBike();
	List<ParkDto> getPark();
}
