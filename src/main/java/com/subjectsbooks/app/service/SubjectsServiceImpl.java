package com.subjectsbooks.app.service;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.subjectsbooks.app.entity.BookReferenceVO;
import com.subjectsbooks.app.entity.BookVO;
import com.subjectsbooks.app.repositories.BookReferenceVORepository;
import com.subjectsbooks.app.repositories.SubjectVORepository;
import com.subjectsbooks.app.model.BookDTO;
import com.subjectsbooks.app.model.SubjectDTO;
import com.subjectsbooks.app.entity.SubjectVO;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



	
@Service
public class SubjectsServiceImpl implements SubjectsService {

		@Autowired
		SubjectVORepository subjectVORepository;
		
		@Autowired
		BookReferenceVORepository bookReferenceRepository;
		
		@Autowired
		BooksService books;


	public boolean save(SubjectDTO subjectrecord) {
		boolean saveFlag=false;
		if(subjectVORepository.save(mapsubjectdto(subjectrecord))!=null)
						saveFlag=true;
		List<BookReferenceVO> bookReferences= mapBookReferencedto(subjectrecord);
		bookReferenceRepository.saveAll(bookReferences);		
		return saveFlag;
	}

	public List<SubjectDTO> getAllSubjects() {
		List<SubjectDTO> subjects= new ArrayList<SubjectDTO>();		
		List<SubjectVO> subjectVOs= subjectVORepository.findAll();
		System.out.println("******* subjectVOs.to string **********\n"+subjectVOs.toString());
		for(SubjectVO subjectvo : subjectVOs) {
			
			subjects.add(mapsubjectdto(subjectvo));				
		}
		
		return subjects;
	}

 
 

	public SubjectDTO find(long subjectId) {
		return mapsubjectdto(subjectVORepository.findBySubjectId(subjectId));
	}

	public boolean delete(long subjectId) {
		boolean deleteFlag=false;
		SubjectVO subjectVOid = subjectVORepository.findBySubjectId(subjectId);
		System.out.println("******* foreign key record is deleted **********\n");
		if(bookReferenceRepository.deleteBySubjectVO(subjectVOid))
			deleteFlag=true;
		System.out.println("******* foreign key record is selected **********\n");
		subjectVORepository.delete(subjectVOid);	
		return deleteFlag;
	}

	public List<SubjectDTO> search(String title) {
		List<SubjectDTO> subjects= new ArrayList<SubjectDTO>();		
		List<SubjectVO> subjectVOs= subjectVORepository.findAllBySubTitle(title);
		for(SubjectVO subjectvo : subjectVOs) {
			
			subjects.add(mapsubjectdto(subjectvo));				
		}
		
		return subjects;
	}
	
	private SubjectVO mapsubjectdto(SubjectDTO subjectrecord)
	{
		SubjectVO subject=new SubjectVO();
		
		subject.setSubjectId(Long.parseLong(subjectrecord.getSubjectId()));
		subject.setDuration(Integer.parseInt(subjectrecord.getDurationInHours()));
		subject.setSubTitle(subjectrecord.getSubtitle());
	
		return subject;
	}
	
	private List<BookReferenceVO> mapBookReferencedto(SubjectDTO subjectrecord)
	{
		SubjectVO subject=new SubjectVO();
		subject.setSubjectId(Long.parseLong(subjectrecord.getSubjectId()));
		subject.setDuration(Integer.parseInt(subjectrecord.getDurationInHours()));
		subject.setSubTitle(subjectrecord.getSubtitle());
		
		List<BookReferenceVO> bookreference = new ArrayList<BookReferenceVO>();
		
				if(!subjectrecord.getBookId().isEmpty())
		{
			List<String> bookIds = Arrays.asList(subjectrecord.getBookId().split(","));
			System.out.println("******* bookIds.to string **********\n"+bookIds.toString());
			for (String bookIdStr : bookIds) {
				System.out.println("******* bookIds **********\n"+bookIdStr);	
					long bookId = Long.parseLong(bookIdStr);
			BookVO bookVO=books.findVO(bookId);
			if(bookVO!=null)
			{
				BookReferenceVO bookrecord=new BookReferenceVO();
				bookrecord.setSubjectVO(subject);
				bookrecord.setBookVO(bookVO);
				System.out.println("******* bookrecord **********\n"+bookrecord.toString());	
				bookreference.add(bookrecord);
			}
				 
		}
		}
		System.out.println("******* bookreference **********\n"+bookreference.toString());
	
		return bookreference;
	}
	
	private SubjectDTO mapsubjectdto(SubjectVO subjectvo)
	{
		SubjectDTO subjectdto= new SubjectDTO();
		String bookIdString="";
		if(subjectvo!=null)
		{

		List<BookReferenceVO> bookReferences= bookReferenceRepository.findAllBySubjectVO(subjectvo);
		
	
		
		for(BookReferenceVO bookrecord: bookReferences)
		{
			bookIdString+=bookrecord.getBookVO().getBookId()+"<br>";
		}
		subjectdto.setBookId(bookIdString);
		subjectdto.setDurationInHours(String.valueOf(subjectvo.getDuration()));
		subjectdto.setSubjectId(String.valueOf(subjectvo.getSubjectId()));
		subjectdto.setSubtitle(subjectvo.getSubTitle());
		}
		return subjectdto;
	}

	
}
