package com.ssafy.happyhouse.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.BikeDto;
import com.ssafy.happyhouse.model.CctvDto;
import com.ssafy.happyhouse.model.EschoolDto;
import com.ssafy.happyhouse.model.ParkDto;
import com.ssafy.happyhouse.model.service.FacilityService;

@RestController
@RequestMapping("/facility")
@CrossOrigin("*")
public class FacilityController {

	@Autowired
	private FacilityService facilityService;
	
	@GetMapping("/cctv")
	public ResponseEntity<List<CctvDto>> getcctv(){
		List<CctvDto> list = facilityService.getcctv();
		if(list != null)
			return new ResponseEntity<List<CctvDto>>(list, HttpStatus.OK);
		return new ResponseEntity<List<CctvDto>>(list, HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/eschool")
	public ResponseEntity<List<EschoolDto>> getEschool(){
		List<EschoolDto> list = facilityService.getEschool();
		if(list.size() != 0) {
			return new ResponseEntity<List<EschoolDto>>(list, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<EschoolDto>>(list, HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping("/bike")
	public ResponseEntity<List<BikeDto>> getBike(){
		System.out.println("bike entered");
		List<BikeDto> list = facilityService.getBike();
		if(list.size() != 0) {
			return new ResponseEntity<List<BikeDto>>(list, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<BikeDto>>(list, HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping("/park")
	public ResponseEntity<List<ParkDto>> getPark(){
		System.out.println("park entered");
		List<ParkDto> list = facilityService.getPark();
		System.out.println(list.toString());
		if(list.size() != 0) {
			return new ResponseEntity<List<ParkDto>>(list, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<ParkDto>>(list, HttpStatus.NO_CONTENT);
		}
	}
}
