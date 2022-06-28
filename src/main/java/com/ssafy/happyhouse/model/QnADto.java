package com.ssafy.happyhouse.model;

import java.util.Date;

public class QnADto {
	private int no;
	private String title;
	private String content;
	private String authorId;
	private Date date;
	private String isAnswered;
	private String answer;

	public String getIsAnswered() {
		return isAnswered;
	}

	public void setIsAnswered(String isAnswered) {
		this.isAnswered = isAnswered;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAuthorId() {
		return authorId;
	}

	public void setAuthorId(String authorId) {
		this.authorId = authorId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "QnADto [no=" + no + ", title=" + title + ", content=" + content + ", authorId=" + authorId + ", date="
				+ date + ", isAnswered=" + isAnswered + ", answer=" + answer + "]";
	}

}