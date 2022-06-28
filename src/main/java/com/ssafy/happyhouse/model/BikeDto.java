package com.ssafy.happyhouse.model;

public class BikeDto {
	String gu;
	int cnt;

	public String getGu() {
		return gu;
	}

	public void setGu(String gu) {
		this.gu = gu;
	}

	public int getCnt() {
		return cnt;
	}

	public void setCnt(int cnt) {
		this.cnt = cnt;
	}

	@Override
	public String toString() {
		return "CctvDto [gu=" + gu + ", cnt=" + cnt + "]";
	}

}
