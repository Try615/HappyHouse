package com.ssafy.happyhouse.model;

public class HospitalDto {
	private String city;
	private String gu;
	private String hospitalname;
	private String address;
	private String type;
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

	public String getHospitalname() {
		return hospitalname;
	}

	public void setHospitalname(String hospitalname) {
		this.hospitalname = hospitalname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTell() {
		return phone;
	}

	public void setTell(String tell) {
		this.phone = tell;
	}

}
