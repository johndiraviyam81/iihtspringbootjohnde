package com.subjectsbooks.app.test;

import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;
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
import org.springframework.http.MediaType;
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

				try {
				
					MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/subjects/deleteaSubject");
					when(subjectService.getAllSubjects()).thenReturn(subjectDTOList);
					mvc.perform(requestBuilder)
					.andExpect(status().isOk())
					.andExpect(view().name("deleteaSubject"))
					.andExpect(forwardedUrl("/WEB-INF/jsp/deleteaSubject.jsp"))
					.andExpect(model().attribute("subjects", subjectDTOList));
						 verify(subjectService, times(1)).getAllSubjects();
			        verifyNoMoreInteractions(subjectService);		
			    	assertEquals(3,subjectDTOList.size());	
		          
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
	}


	@Test
	final void testDeleteaSubjectDelete() {		
				
				try {
				
					long subjectId=Long.parseLong("34503");
					MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/subjects/deleteaSubjectDelete/{subjectIdStr}","34503");
								
					when(subjectService.delete(subjectId)).thenReturn(true);
					this.subjectDTOList.remove(2);
					when(subjectService.getAllSubjects()).thenReturn(this.subjectDTOList);
					assertEquals(2,this.subjectDTOList.size());
					String message="Record has been successfully deleted";
					
					mvc.perform(requestBuilder)
					.andExpect(status().isOk())
					.andExpect(view().name("deleteaSubject"))
					.andExpect(forwardedUrl("/WEB-INF/jsp/deleteaSubject.jsp"))
					.andExpect(model().attribute("subjects", this.subjectDTOList))
					.andExpect(model().attribute("message", message));
					 verify(subjectService, times(1)).delete(subjectId);
				        verifyNoMoreInteractions(subjectService);
		          
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			
	}

	

	@Test
	final void testSearchForaSubject() {
	
		try {
		
			MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/subjects/searchForaSubject");
			when(subjectService.getAllSubjects()).thenReturn(this.subjectDTOList);
			
			mvc.perform(requestBuilder)
			.andExpect(status().isOk())
			.andExpect(view().name("searchForASubject"))
			.andExpect(forwardedUrl("/WEB-INF/jsp/searchForASubject.jsp"))
			.andExpect(model().attribute("subjects", this.subjectDTOList));
			 verify(subjectService, times(1)).getAllSubjects();
		        verifyNoMoreInteractions(subjectService);
		        assertEquals(2,this.subjectDTOList.size());
          
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}

	@Test
	final void testSearchasubject() {		

		try {
			
			List<SubjectDTO> subjectRecordset=new ArrayList<>();
			subjectRecordset.add(subjectDTO1);		
			String title="Computer neworks";	
			MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/subjects/searchasubject")
					.contentType(MediaType.APPLICATION_FORM_URLENCODED)
			        .param("title",title)		      
			        .sessionAttr("subjectRecord", new SubjectDTO());
		 
			 
			when(subjectService.search(title)).thenReturn(subjectRecordset);
			mvc.perform(requestBuilder)
			.andExpect(status().isAccepted())
			.andExpect(view().name("searchForASubject"))
			.andExpect(forwardedUrl("/WEB-INF/jsp/searchForASubject.jsp"))
			.andExpect(model().attribute("subjects", subjectRecordset));
			 verify(subjectService, times(1)).search(title);
		        verifyNoMoreInteractions(subjectService);
		        assertEquals(1,subjectRecordset.size());
          
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}

	@Test
	final void testAddaSubjectSave() {
	 
		
				
				
				try {

					MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/subjects/addaSubjectSave")
							.contentType(MediaType.APPLICATION_FORM_URLENCODED)
							.contentType(MediaType.APPLICATION_FORM_URLENCODED)
					        .param("subjectId",subjectDTO3.getSubjectId())	
					        .param("subTitle",subjectDTO3.getSubtitle())	
					        .param("durationInHours",subjectDTO3.getDurationInHours())	
					        .param("bookId",subjectDTO3.getBookId())	
					        .sessionAttr("subjectRecord", subjectDTO3);
					when(subjectService.save(subjectDTO3)).thenReturn(true);
					this.subjectDTOList.add(subjectDTO3);
					mvc.perform(requestBuilder)
					.andExpect(status().isOk())
					.andExpect(view().name("addaSubject"))
					.andExpect(forwardedUrl("/WEB-INF/jsp/addaSubject.jsp"))
					.andExpect(model().attribute("subjectrecord", subjectDTO3));
					 
					verifyZeroInteractions(subjectService);
			        assertEquals(3,this.subjectDTOList.size());
					 
				 
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}

	@Test
	final void testAddaSubject() {
		
		try {
			SubjectDTO subjectrecord=new SubjectDTO();
			MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/subjects/addaSubject");
			
			mvc.perform(requestBuilder)
			.andExpect(status().isOk())
			.andExpect(view().name("addaSubject"))
			.andExpect(forwardedUrl("/WEB-INF/jsp/addaSubject.jsp"))
			.andExpect(model().attribute("bookrecord", subjectrecord));
			verifyZeroInteractions(subjectService);
			assertTrue(true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
