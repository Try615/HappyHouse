package com.ssafy.happyhouse.model;

public class NoticeDto {
	int no;
	String title;
	String authorId;
	String content;
	int views;
	String date;
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthorId() {
		return authorId;
	}
	public void setAuthorId(String authorId) {
		this.authorId = authorId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getViews() {
		return views;
	}
	public void setViews(int views) {
		this.views = views;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "NoticeDto [no=" + no + ", title=" + title + ", authorId=" + authorId + ", content=" + content
				+ ", views=" + views + ", date=" + date + "]";
	}
	
}
