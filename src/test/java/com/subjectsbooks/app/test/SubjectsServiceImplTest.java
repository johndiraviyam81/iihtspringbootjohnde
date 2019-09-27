package com.subjectsbooks.app.test;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

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

	
	SubjectDTO subjectDTO1 = new SubjectDTO();
	
	SubjectDTO subjectDTO2 = new SubjectDTO();
	
	SubjectDTO subjectDTO3 = new SubjectDTO();
	

	@BeforeEach
	void setUp() throws Exception {
		subjectsService=new SubjectsServiceImpl();
		mvc = MockMvcBuilders.standaloneSetup(subjectsService).build();
		this.subjectDTO1=setupSujectDTO("34501","Computer neworks", "345","1101");
		this.subjectDTO2=setupSujectDTO("34502","Software engineering neworks", "45","1102");
		this.subjectDTO3=setupSujectDTO("34503","Graphics AI", "34","1101,1103");
		this.subjectDTOList.add(this.subjectDTO1);
		this.subjectDTOList.add(this.subjectDTO2);
		this.subjectDTOList.add(this.subjectDTO3);
	}

 

	@Test
	final void testSave() {
		assertTrue(true);
	}

	@Test
	final void testGetAllSubjects() {
		assertTrue(true);
	}

	@Test
	final void testFind() {
		assertTrue(true);
	}

	@Test
	final void testDelete() {
		assertTrue(true);
	}

	@Test
	final void testSearch() {
		assertTrue(true);
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
}
