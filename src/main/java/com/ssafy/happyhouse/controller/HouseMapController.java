package com.ssafy.happyhouse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.HouseDealDto;
import com.ssafy.happyhouse.model.HouseInfoDto;
import com.ssafy.happyhouse.model.SidoGugunCodeDto;
import com.ssafy.happyhouse.model.service.HouseMapService;

@RestController
@RequestMapping("/apart")
@CrossOrigin("*")
public class HouseMapController {

	@Autowired
	private HouseMapService houseMapService;

	@GetMapping("/sido")
	public ResponseEntity<List<SidoGugunCodeDto>> sido() {
		try {
			List<SidoGugunCodeDto> list = houseMapService.getSido();
			return new ResponseEntity<List<SidoGugunCodeDto>>(list, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping("/gugun/{sido}")
	public ResponseEntity<List<SidoGugunCodeDto>> gugun(@PathVariable("sido") String sido) {
		try {
			List<SidoGugunCodeDto> list = houseMapService.getGugunInSido(sido);
			return new ResponseEntity<List<SidoGugunCodeDto>>(list, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping("/dong/{gugun}")
	public ResponseEntity<List<HouseInfoDto>> dong(@PathVariable("gugun") String gugun) {
		try {
			List<HouseInfoDto> list = houseMapService.getDongInGugun(gugun);
			return new ResponseEntity<List<HouseInfoDto>>(list, HttpStatus.OK); 
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
	}
	
	
	@GetMapping("/apt/{dong}")
	public ResponseEntity<List<HouseInfoDto>> apt(@PathVariable("dong") String dong) {
		try {
			List<HouseInfoDto> list = houseMapService.getAptInDong(dong);
			return new ResponseEntity<List<HouseInfoDto>>(list, HttpStatus.OK); 
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping("/deal/{dong}&{aptName}")
	public ResponseEntity<List<HouseDealDto>> apt(@PathVariable("dong") String dong,@PathVariable("aptName") String aptName) {
		try {
			List<HouseDealDto> list = houseMapService.getDeal(dong, aptName);
			return new ResponseEntity<List<HouseDealDto>>(list, HttpStatus.OK); 
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping("/dongrank")
	public ResponseEntity<List<String>> getRank(){
		List<String> list = houseMapService.getRank();
		if(list != null) {
			return new ResponseEntity<List<String>>(list, HttpStatus.OK);
		}
		return new ResponseEntity<List<String>>(list, HttpStatus.NO_CONTENT);
	}
}
