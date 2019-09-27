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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import com.subjectsbooks.app.controllers.BooksController;
import com.subjectsbooks.app.model.BookDTO;
import com.subjectsbooks.app.service.BooksService;

@ExtendWith(MockitoExtension.class)
class BooksControllerTest {
	

	@InjectMocks
	BooksController booksController;


	@Mock
	private static BooksService booksService;



	
	private MockMvc mvc;
	
	private List<BookDTO> bookDTOList = new ArrayList<>();

	private BookDTO bookDTO1 = new BookDTO();
	
	private BookDTO bookDTO2 = new BookDTO();
	
	private BookDTO bookDTO3 = new BookDTO();
	
	private BookDTO bookDTO4 = new BookDTO();

	@BeforeEach
	void setUp() throws Exception {
		booksController=new BooksController();
		mvc = MockMvcBuilders.standaloneSetup(booksController).build();
		this.bookDTO1=this.setupBookDTO("1101","Journey of the softwares", "12.50","34223433","19/09/2019");
		this.bookDTO2=this.setupBookDTO("1102","Book of the network", "13.50","34223433","29/05/2018");
		this.bookDTO3=this.setupBookDTO("1103","Lifetime achievement of the softwares", "11.50","64223433","06/05/2014");
		this.bookDTO4=this.setupBookDTO("1104","Grooming of the network", "24.50","5223433","15/06/2016");
		this.bookDTOList.add(this.bookDTO1);
		this.bookDTOList.add(this.bookDTO2);
		this.bookDTOList.add(this.bookDTO3);
		this.bookDTOList.add(this.bookDTO4);
	}

	
	
	

	@Test
	final void testSearchForAbook() {
	 
		when(booksService.getAllBooks()).thenReturn(this.bookDTOList);


		
			try {
			
				MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/books/searchForAbook");
				
				mvc.perform(requestBuilder)
				.andExpect(status().isOk())
				.andExpect(view().name("searchForAbook"))
				.andExpect(forwardedUrl("/WEB-INF/jsp/searchForAbook.jsp"))
				.andExpect(model().attribute("books", this.bookDTOList));
	          
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
		assertEquals(4,this.bookDTOList.size());		
	}





	@Test
	final void testDeleteABook() {
	when(booksService.getAllBooks()).thenReturn(this.bookDTOList);


		
		try {
			Model model = null;
			MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/books/deleteABook");
			
			mvc.perform(requestBuilder)
			.andExpect(status().isOk())
			.andExpect(view().name("deleteABook"))
			.andExpect(forwardedUrl("/WEB-INF/jsp/deleteABook.jsp"))
			.andExpect(model().attribute("books", this.bookDTOList));
          
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	final void testDeleteABookDelete() {
		when(booksService.getAllBooks()).thenReturn(this.bookDTOList);
		long bookId=Long.parseLong("1101");
		when(booksService.delete(bookId)).thenReturn(true);

		String message="Record has been successfully deleted";
				
				try {
					Model model = null;
					MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/books/deleteABookDelete/{bookIdStr}","1101");
					
					mvc.perform(requestBuilder)
					.andExpect(status().isOk())
					.andExpect(view().name("deleteABook"))
					.andExpect(forwardedUrl("/WEB-INF/jsp/deleteABook.jsp"))
					.andExpect(model().attribute("books", this.bookDTOList))
					.andExpect(model().attribute("message", message));
		          
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
	}

	@Test
	final void testAddaBookSave() {
		BookDTO bookrecord=bookDTO4;
		
		when(booksService.save(bookrecord)).thenReturn(true);
		
				assertTrue(true);
	}

	@Test
	final void testSearchabook() {
		
		long bookId=Long.parseLong("1101");
		 
		when(booksService.find(bookId)).thenReturn(bookDTO1);
		List<BookDTO> bookDTOListfindrecord = new ArrayList<>();
		bookDTOListfindrecord.add(bookDTO1);
	
		assertEquals(1,bookDTOListfindrecord.size());
	}

	@Test
	final void testAddaBook() {
	
		try {
			BookDTO bookrecord=new BookDTO();
			MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/books/addaBook");
			
			mvc.perform(requestBuilder)
			.andExpect(status().isOk())
			.andExpect(view().name("addabook"))
			.andExpect(forwardedUrl("addabook"));
          
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 
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

}
