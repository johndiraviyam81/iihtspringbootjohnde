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
	

	@BeforeEach
	void setUp() throws Exception {
		subjectsService=new SubjectsServiceImpl();
		mvc = MockMvcBuilders.standaloneSetup(subjectsService).build();
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

}
