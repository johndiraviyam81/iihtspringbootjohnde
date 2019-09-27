package com.subjectsbooks.app.entity;

import java.sql.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name = "subject")
public class SubjectVO {
	
	@Id
	@Column(name="subjectid")
	private	long subjectId;

	@Column(name="subtitle")
	private String subTitle;

	@Column(name="duration")
	private double duration;
	

	

	public SubjectVO() {
		
	}

	
	public long getSubjectId() {
		return subjectId;
	}



	public void setSubjectId(long subjectId) {
		this.subjectId = subjectId;
	}



	public String getSubTitle() {
		return subTitle;
	}



	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}



	public double getDuration() {
		return duration;
	}



	public void setDuration(double duration) {
		this.duration = duration;
	}






	@Override
	public String toString() {
		return "SubjectVO [subjectId=" + subjectId + ", subTitle=" + subTitle +  ", duration=" + duration + "]";
	}

}
