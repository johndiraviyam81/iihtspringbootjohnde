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

import com.subjectsbooks.app.controllers.BooksController;
import com.subjectsbooks.app.repositories.BookReferenceVORepository;
import com.subjectsbooks.app.repositories.BookVORepository;
import com.subjectsbooks.app.service.BooksService;
import com.subjectsbooks.app.service.BooksServiceImpl;

@ExtendWith(MockitoExtension.class)
class BooksServiceImplTest {


	@InjectMocks
	private static BooksServiceImpl booksService;
	
	@Mock
	BookVORepository bookRepository;
	
	@Mock
	BookReferenceVORepository bookReferenceRepository;
	
	private MockMvc mvc;

	@BeforeEach
	void setUp() throws Exception {
		booksService=new BooksServiceImpl();
		mvc = MockMvcBuilders.standaloneSetup(booksService).build();
	}



	@Test
	final void testGetAllBooks() {
		assertTrue(true);
	}

	@Test
	final void testSave() {
		assertTrue(true);
	}

	@Test
	final void testFind() {
		assertTrue(true);
	}

	@Test
	final void testFindVO() {
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
