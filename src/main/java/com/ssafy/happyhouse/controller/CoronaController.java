package com.ssafy.happyhouse.controller;

import java.util.List;
import java.util.Map;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.HospitalDto;
import com.ssafy.happyhouse.model.OfficeDto;
import com.ssafy.happyhouse.model.service.CoronaService;

@RestController
@RequestMapping("/corona")
@CrossOrigin("*")
public class CoronaController {

	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

	@Autowired
	private CoronaService coronaService;
	
	@GetMapping("/getHospital/{sidoName}&{gugunName}")
	public ResponseEntity<List<HospitalDto>> getHospital(@PathVariable("sidoName") String sidoName, @PathVariable("gugunName") String gugunName){
		try {
			List<HospitalDto> list = coronaService.getHospitalList(sidoName, gugunName);
			if(list != null) {
				return new ResponseEntity<List<HospitalDto>>(list, HttpStatus.OK);
			}else {
				return new ResponseEntity(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return new ResponseEntity(HttpStatus.UNAUTHORIZED);
		}
	}
	
	@GetMapping("/getOffice/{sidoName}&{gugunName}")
	public ResponseEntity<List<OfficeDto>> getOffice(@PathVariable("sidoName") String sidoName, @PathVariable("gugunName") String gugunName){
		logger.debug(sidoName + " " + gugunName);
		try {
			List<OfficeDto> list = coronaService.getOfficeList(sidoName, gugunName);
			if(list != null) {
				return new ResponseEntity<List<OfficeDto>>(list, HttpStatus.OK);
			}else {
				return new ResponseEntity(HttpStatus.NO_CONTENT);
			}
		}catch (Exception e) {
			return new ResponseEntity(HttpStatus.UNAUTHORIZED);
		}
	}
}
