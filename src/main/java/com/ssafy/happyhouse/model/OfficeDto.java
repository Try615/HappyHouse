package com.ssafy.happyhouse.model;

public class OfficeDto {
	private String city;
	private String gu;
	private String institutionname;
	private String address;
	private String weekdayhours;
	private String saturdayhours;
	private String sundayhours;
	private String phone;

	public String getSido() {
		return city;
	}

	public void setSido(String sido) {
		this.city = sido;
	}

	public String getGugun() {
		return gu;
	}

	public void setGugun(String gugun) {
		this.gu = gugun;
	}

	public String getOfficename() {
		return institutionname;
	}

	public void setOfficename(String officename) {
		this.institutionname = officename;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDaytime() {
		return weekdayhours;
	}

	public void setDaytime(String daytime) {
		this.weekdayhours = daytime;
	}

	public String getSattime() {
		return saturdayhours;
	}

	public void setSattime(String sattime) {
		this.saturdayhours = sattime;
	}

	public String getSuntime() {
		return sundayhours;
	}

	public void setSuntime(String suntime) {
		this.sundayhours = suntime;
	}

	public String getTel() {
		return phone;
	}

	public void setTell(String tel) {
		this.phone = tel;
	}

}
