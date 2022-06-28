package com.ssafy.happyhouse.model;

public class InterestDto {

	@Override
	public String toString() {
		return "InterestDto [userid=" + userid + ", sido=" + sido + ", gugun=" + gugun + ", dong=" + dong + "]";
	}

	private String userid;
	private String sido;
	private String gugun;
	private String dong;

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getSido() {
		return sido;
	}

	public void setSido(String sido) {
		this.sido = sido;
	}

	public String getGugun() {
		return gugun;
	}

	public void setGugun(String gugun) {
		this.gugun = gugun;
	}

	public String getDong() {
		return dong;
	}

	public void setDong(String dong) {
		this.dong = dong;
	}

}
