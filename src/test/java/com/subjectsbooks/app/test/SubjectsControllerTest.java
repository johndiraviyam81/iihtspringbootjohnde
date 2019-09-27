package com.subjectsbooks.app.test;

import static org.junit.jupiter.api.Assertions.*;

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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import com.subjectsbooks.app.controllers.BooksController;
import com.subjectsbooks.app.controllers.SubjectsController;
import com.subjectsbooks.app.entity.BookReferenceVO;
import com.subjectsbooks.app.model.BookDTO;
import com.subjectsbooks.app.model.SubjectDTO;
import com.subjectsbooks.app.service.SubjectsService;

@ExtendWith(MockitoExtension.class)
class SubjectsControllerTest {

	@InjectMocks
	SubjectsController subjectsController;
	
	@Mock
	private	SubjectsService subjectService;
	
	private MockMvc mvc;

	List<SubjectDTO> subjectDTOList=new ArrayList<>();

	
	SubjectDTO subjectDTO1 = new SubjectDTO();
	
	SubjectDTO subjectDTO2 = new SubjectDTO();
	
	SubjectDTO subjectDTO3 = new SubjectDTO();
	
	@BeforeEach
	void setUp() throws Exception {
		
		subjectsController=new SubjectsController();
		mvc = MockMvcBuilders.standaloneSetup(subjectsController).build();
		this.subjectDTO1=setupSujectDTO("34501","Computer neworks", "345","1101");
		this.subjectDTO2=setupSujectDTO("34502","Software engineering neworks", "45","1102");
		this.subjectDTO3=setupSujectDTO("34503","Graphics AI", "34","1101,1103");
		this.subjectDTOList.add(this.subjectDTO1);
		this.subjectDTOList.add(this.subjectDTO2);
		this.subjectDTOList.add(this.subjectDTO3);
		
	}


	@Test
	final void testDeleteaSubject() {

		when(subjectService.getAllSubjects()).thenReturn(this.subjectDTOList);
	 
	
				
				try {
					Model model = null;
					MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/subjects/deleteaSubject");
					
					mvc.perform(requestBuilder)
					.andExpect(status().isOk())
					.andExpect(view().name("deleteaSubject"))
					.andExpect(forwardedUrl("/WEB-INF/jsp/deleteaSubject.jsp"))
					.andExpect(model().attribute("subjects", this.subjectDTOList));
		          
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}


	@Test
	final void testDeleteaSubjectDelete() {

		when(subjectService.getAllSubjects()).thenReturn(this.subjectDTOList);
		long subjectId=Long.parseLong("34501");
		when(subjectService.delete(subjectId)).thenReturn(true);

		String message="Record has been successfully deleted";
				
				try {
					Model model = null;
					MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/subjects/deleteaSubjectDelete/{subjectIdStr}","34501");
					
					mvc.perform(requestBuilder)
					.andExpect(status().isOk())
					.andExpect(view().name("deleteaSubject"))
					.andExpect(forwardedUrl("/WEB-INF/jsp/deleteaSubject.jsp"))
					.andExpect(model().attribute("subjects", this.subjectDTOList))
					.andExpect(model().attribute("message", message));
		          
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
	}

	

	@Test
	final void testSearchForaSubject() {

		when(subjectService.getAllSubjects()).thenReturn(this.subjectDTOList);
		 
		
		
		try {
			Model model = null;
			MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/subjects/searchForaSubject");
			
			mvc.perform(requestBuilder)
			.andExpect(status().isOk())
			.andExpect(view().name("searchForASubject"))
			.andExpect(forwardedUrl("/WEB-INF/jsp/searchForASubject.jsp"))
			.andExpect(model().attribute("subjects", this.subjectDTOList));
          
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Test
	final void testSearchasubject() {
		
		List<SubjectDTO> subjectRecordset=new ArrayList<>();
		subjectRecordset.add(subjectDTO1);		
		String title="Computer neworks";		 
		when(subjectService.search(title)).thenReturn(subjectRecordset);		
	
		assertEquals(1,subjectRecordset.size());
		
	}

	@Test
	final void testAddaSubjectSave() {
		SubjectDTO subjectrecord=subjectDTO3;
		
		when(subjectService.save(subjectrecord)).thenReturn(true);		
				assertTrue(true);
	}

	@Test
	final void testAddaSubject() {
		SubjectDTO subjectrecord=new SubjectDTO();
		try {
			Model model = null;
			MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/subjects/addaSubject");
			
			mvc.perform(requestBuilder)
			.andExpect(status().isOk())
			.andExpect(view().name("addaSubject"))
			.andExpect(forwardedUrl("addaSubject"));
          
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
