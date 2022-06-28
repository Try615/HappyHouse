package com.ssafy.happyhouse.model.service;

import java.util.List;

import com.ssafy.happyhouse.model.*;

public interface HouseMapService {
	List<SidoGugunCodeDto> getSido() throws Exception;
	List<SidoGugunCodeDto> getGugunInSido(String sido) throws Exception;
	List<HouseInfoDto> getDongInGugun(String gugun) throws Exception;
	List<HouseInfoDto> getAptInDong(String dong) throws Exception;
	
	List<HouseDealDto> getDeal(String dong, String aptName) throws Exception;
	List<String> getRank();
}
