package com.ssafy.happyhouse.model;

public class MemberDto {

	private int idx;
	private String userid;
	private String username;
	private String userpwd;
	private String usertell;
	private String joindate;
	private String isAdmin;

	@Override
	public String toString() {
		return "MemberDto [idx=" + idx + ", userid=" + userid + ", username=" + username + ", userpwd=" + userpwd
				+ ", usertell=" + usertell + ", joindate=" + joindate + ", isAdmin=" + isAdmin + "]";
	}

	public String getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(String isAdmin) {
		this.isAdmin = isAdmin;
	}

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserpwd() {
		return userpwd;
	}

	public void setUserpwd(String userpwd) {
		this.userpwd = userpwd;
	}

	public String getUsertell() {
		return usertell;
	}

	public void setUsertell(String usertell) {
		this.usertell = usertell;
	}

	public String getJoindate() {
		return joindate;
	}

	public void setJoindate(String joindate) {
		this.joindate = joindate;
	}

}
