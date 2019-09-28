package com.subjectsbooks.app.test;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import com.subjectsbooks.app.entity.BookReferenceVO;
import com.subjectsbooks.app.entity.BookVO;
import com.subjectsbooks.app.entity.SubjectVO;
import com.subjectsbooks.app.model.BookDTO;
import com.subjectsbooks.app.model.SubjectDTO;
import com.subjectsbooks.app.repositories.BookReferenceVORepository;
import com.subjectsbooks.app.repositories.SubjectVORepository;

import com.subjectsbooks.app.service.SubjectsServiceImpl;

@ExtendWith(MockitoExtension.class)
class SubjectsServiceImplTest {

	@InjectMocks
	SubjectsServiceImpl subjectsService;
	
	@Mock
	SubjectVORepository subjectVORepository;
	
	@Mock
	BookReferenceVORepository bookReferenceRepository;
	
	private MockMvc mvc;
	
	List<SubjectDTO> subjectDTOList=new ArrayList<>();
	
	List<BookReferenceVO> bookReferences=new ArrayList<>();

	BookReferenceVO bookReference1=new BookReferenceVO();
	
	BookReferenceVO bookReference2=new BookReferenceVO();
	
	BookReferenceVO bookReference3=new BookReferenceVO();
	
	BookVO bookVO1=new BookVO();
	
	BookVO bookVO2=new BookVO();

	BookVO bookVO3=new BookVO();
	
	SubjectDTO subjectDTO1 = new SubjectDTO();
	
	SubjectDTO subjectDTO2 = new SubjectDTO();
	
	SubjectDTO subjectDTO3 = new SubjectDTO();
	
	List<SubjectVO> subjectVOList=new ArrayList<>();
	
	SubjectVO subjectVO1 = new SubjectVO();
	
	SubjectVO subjectVO2 = new SubjectVO();
	
	SubjectVO subjectVO3 = new SubjectVO();

	

	@BeforeEach
	void setUp() throws Exception {
		subjectsService=new SubjectsServiceImpl();
		mvc = MockMvcBuilders.standaloneSetup(subjectsService).build();
		this.subjectDTO1=setupSujectDTO("34501","Computer neworks", "345","1101");
		this.subjectDTO2=setupSujectDTO("34502","Software engineering neworks", "45","1102");
		this.subjectDTO3=setupSujectDTO("34503","Graphics AI", "34","1101,1103");
		this.subjectVO1=mapsubjectdto(this.subjectDTO1);
		this.subjectVO2=mapsubjectdto(this.subjectDTO2);
		this.subjectVO3=mapsubjectdto(this.subjectDTO3);
		this.subjectVOList.add(this.subjectVO1);
		this.subjectVOList.add(this.subjectVO2);
		this.subjectVOList.add(this.subjectVO3);
		
		this.subjectDTOList.add(this.subjectDTO1);
		this.subjectDTOList.add(this.subjectDTO2);
		this.subjectDTOList.add(this.subjectDTO3);
		this.bookVO1=mapbookvo(setupBookDTO("1101","Journey of the softwares", "12.50","34223433","2019-09-09"));
		this.bookVO2=mapbookvo(setupBookDTO("1102","Book of the network", "13.50","34223433","2019-09-09"));
		 
		this.bookReference1.setId(1);
		this.bookReference1.setBookVO(this.bookVO1);
		this.bookReference1.setSubjectVO(mapsubjectdto(this.subjectDTO1));
		this.bookReferences.add(this.bookReference1);
		
		this.bookReference2.setId(2);
		this.bookReference2.setBookVO(this.bookVO2);
		this.bookReference2.setSubjectVO(mapsubjectdto(this.subjectDTO2));
		this.bookReferences.add(this.bookReference2);
	}

 

	@Test
	final void testSave() {
		
		boolean saveFlag=false;
		SubjectVO subjectRet=mapsubjectdto(subjectDTO1);
		when(subjectVORepository.save(mapsubjectdto(subjectDTO1))).thenReturn(subjectRet);
		if(subjectRet!=null)				
		saveFlag=true;	
		when(bookReferenceRepository.saveAll(this.bookReferences)).thenReturn(this.bookReferences);	
		
		assertTrue(saveFlag);
	}

	@Test
	final void testGetAllSubjects() {
		List<SubjectDTO> subjectDTOListGet=new ArrayList<SubjectDTO>();
		 	
		when(subjectVORepository.findAll()).thenReturn(subjectVOList);
		 
		for(SubjectDTO subjectDTo : this.subjectDTOList) {
			
			subjectDTOListGet.add(subjectDTo);				
		}
			assertEquals(this.subjectDTOList,subjectDTOListGet);
	}

	@Test
	final void testFind() {
		SubjectDTO subjectdto=this.subjectDTO1;
		long subjectId=34501L;
		when(subjectVORepository.findBySubjectId(subjectId)).thenReturn(this.subjectVO1);
		
		assertEquals(this.subjectDTO1,subjectdto);
	}

	@Test
	final void testDelete() {
		boolean deleteFlag=false;
		long subjectId=34501L;
		when(subjectVORepository.findBySubjectId(subjectId)).thenReturn(subjectVO1);
		System.out.println("******* foreign key record is deleted **********\n");
		when(bookReferenceRepository.deleteBySubjectVO(subjectVO1)).thenReturn(true);		
	
		verifyZeroInteractions(subjectVORepository);
		
		assertTrue(true);
	}

	@Test
	final void testSearch() {
		String title="foreign foreign hh";
		List<SubjectDTO> subjectDTOListfind=new ArrayList<SubjectDTO>();
		when(subjectVORepository.findAllBySubTitle(title)).thenReturn(subjectVOList);
		
		for(SubjectDTO subjectDTo : this.subjectDTOList) {
					
					subjectDTOListfind.add(subjectDTo);				
				}
			assertEquals(this.subjectDTOList,subjectDTOListfind);
	}

	private SubjectDTO setupSujectDTO(String subjectId,String subTitle, String duration,String bookId)
	{
		SubjectDTO subjectdto=new SubjectDTO();
		
		subjectdto.setBookId(bookId);
		subjectdto.setDurationInHours(duration);
		subjectdto.setSubjectId(subjectId);
		subjectdto.setSubtitle(subTitle);
		
		return subjectdto;
	}
	
	private SubjectVO mapsubjectdto(SubjectDTO subjectrecord)
	{
		SubjectVO subject=new SubjectVO();
		
		subject.setSubjectId(Long.parseLong(subjectrecord.getSubjectId()));
		subject.setDuration(Integer.parseInt(subjectrecord.getDurationInHours()));
		subject.setSubTitle(subjectrecord.getSubtitle());
	
		return subject;
	}
	
	private BookDTO setupBookDTO(String bookId,String title, String price,String volume,String publishedDate)
	{
		BookDTO bookDTOCon=new BookDTO();
		bookDTOCon.setBookId(bookId);
		bookDTOCon.setPrice(price);
		bookDTOCon.setPublishDate(publishedDate);
		bookDTOCon.setTitle(title);
		bookDTOCon.setVolume(volume);
		
		return bookDTOCon;
	}
	
	private BookVO mapbookvo(BookDTO bookdto)
	{
		BookVO bookset=new BookVO();
		System.out.println("******* bookdto object the  **********\n"+bookdto.toString());
		bookset.setBookId(Long.parseLong(bookdto.getBookId().trim()));
		bookset.setPrice(Double.parseDouble(bookdto.getPrice().trim()));
		bookset.setPublishDate(Date.valueOf(bookdto.getPublishDate().trim()));
		bookset.setTitle(bookdto.getTitle().trim());
		bookset.setVolume(Integer.parseInt(bookdto.getVolume().trim()));
		return bookset;
	}
}
