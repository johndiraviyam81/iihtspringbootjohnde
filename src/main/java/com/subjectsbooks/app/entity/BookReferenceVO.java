package com.subjectsbooks.app.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "bookreference")
public class BookReferenceVO {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;

	@ManyToOne
	@JoinColumn(name="bookid")
	private BookVO bookVO;

	@ManyToOne
	@JoinColumn(name="subjectid")
	private SubjectVO subjectVO;

	

	public BookReferenceVO() {
		
	}
	
	public int getId() {
		return id;
	}




	public void setId(int id) {
		this.id = id;
	}




	public BookVO getBookVO() {
		return bookVO;
	}




	public void setBookVO(BookVO bookVO) {
		this.bookVO = bookVO;
	}




	public SubjectVO getSubjectVO() {
		return subjectVO;
	}




	public void setSubjectVO(SubjectVO subjectVO) {
		this.subjectVO = subjectVO;
	}




	@Override
	public String toString() {
		return "BookReferenceVO [id=" + id + ", bookId=" + bookVO.toString() +  ", subjectId=" + subjectVO.toString() + "]";
	}

}
