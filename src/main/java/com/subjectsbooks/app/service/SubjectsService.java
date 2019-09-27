package com.subjectsbooks.app.service;

import java.util.List;

import com.subjectsbooks.app.entity.SubjectVO;
import com.subjectsbooks.app.model.SubjectDTO;

public interface SubjectsService 
{

	
	public boolean save(SubjectDTO subjectrecord);

	public List<SubjectDTO> getAllSubjects();



	public SubjectDTO find(long subjectId);

	public boolean delete(long subjectId);

	public List<SubjectDTO> search(String title);

}
