package com.subjectsbooks.app.model;

import java.io.Serializable;
import java.util.Set;

public class SubjectDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	String subjectId;
	String subtitle;
	String durationInHours;
	String bookId;


	public String getSubjectId() {
		return subjectId;
	}


	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}


	public String getSubtitle() {
		return subtitle;
	}


	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}


	public String getDurationInHours() {
		return durationInHours;
	}


	public void setDurationInHours(String durationInHours) {
		this.durationInHours = durationInHours;
	}


	public String getBookId() {
		return bookId;
	}


	public void setBookId(String bookId) {
		this.bookId = bookId;
	}


	@Override
	public String toString() {
		return "Subject [subjectId=" + subjectId + ", subtitle=" + subtitle + ", durationInHours=" + durationInHours
				+ ", bookId=" + bookId + "]";
	}

}
