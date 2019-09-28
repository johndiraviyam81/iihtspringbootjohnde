package com.subjectsbooks.app.test;


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
			try {
			
				MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/books/searchForAbook");
				when(booksService.getAllBooks()).thenReturn(bookDTOList);
				mvc.perform(requestBuilder)
				.andExpect(status().isOk())
				.andExpect(view().name("searchForAbook"))
				.andExpect(forwardedUrl("/WEB-INF/jsp/searchForAbook.jsp"))
				.andExpect(model().attribute("books", bookDTOList));
				
				 verify(booksService, times(1)).getAllBooks();
			        verifyNoMoreInteractions(booksService);		
			    	assertEquals(4,bookDTOList.size());	
	          
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
		
	}





	@Test
	final void testDeleteABook() {
	
	try {
		when(booksService.getAllBooks()).thenReturn(bookDTOList);
			MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/books/deleteABook");
			
			mvc.perform(requestBuilder)
			.andExpect(status().isOk())
			.andExpect(view().name("deleteABook"))
			.andExpect(forwardedUrl("/WEB-INF/jsp/deleteABook.jsp"))
			.andExpect(model().attribute("books", this.bookDTOList));
			 verify(booksService, times(1)).getAllBooks();
		        verifyNoMoreInteractions(booksService);	
			assertTrue(true);
          
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

	@Test
	final void testDeleteABookDelete() {
		

		String message="Record has been successfully deleted";
				
				try {
					when(booksService.getAllBooks()).thenReturn(bookDTOList);
					this.bookDTOList.remove(4);
					long bookId=Long.parseLong("1104");
					when(booksService.delete(bookId)).thenReturn(true);
					Model model = null;
					MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/books/deleteABookDelete/{bookIdStr}","1101");
					
					mvc.perform(requestBuilder)
					.andExpect(status().isOk())
					.andExpect(view().name("deleteABook"))
					.andExpect(forwardedUrl("/WEB-INF/jsp/deleteABook.jsp"))
					.andExpect(model().attribute("books", bookDTOList))
					.andExpect(model().attribute("message", message));
					assertEquals(3,bookDTOList);
					 verify(booksService, times(1)).getAllBooks();
				        verifyNoMoreInteractions(booksService);
		          
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		 
	}

	@Test
	final void testAddaBookSave() {
			
		
		try {
			BookDTO bookrecord=bookDTO4;
			when(booksService.save(bookrecord)).thenReturn(this.bookDTOList.add(bookrecord));
					
			MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/books/addaBookSave")
					.contentType(MediaType.APPLICATION_FORM_URLENCODED)
			        .param("bookId", bookrecord.getBookId())
			        .param("price", bookrecord.getPrice())
			        .param("publishedDate", bookrecord.getPublishDate())
			        .param("title", bookrecord.getPrice())
			        .param("volume", bookrecord.getVolume())
			        .sessionAttr("bookrecord", bookrecord);	
			mvc.perform(requestBuilder)
			.andExpect(status().isAccepted())
			.andExpect(view().name("addabook"))
			.andExpect(forwardedUrl("/WEB-INF/jsp/addabook.jsp"))
			.andExpect(model().attribute("bookrecord", bookrecord))
			.andExpect(model().attribute("message", "Book has been added successfully"));
			verifyZeroInteractions(booksService);
			assertEquals(4,bookDTOList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	@Test
	final void testSearchabook() {
		
		try {
		long bookId=Long.parseLong("1101");
		 
		when(booksService.find(bookId)).thenReturn(bookDTO1);
		List<BookDTO> bookDTOListfindrecord = new ArrayList<>();
		bookDTOListfindrecord.add(bookDTO1);
	
		
		
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/books/searchabook")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
		        .param("bookId","1101")		      
		        .sessionAttr("bookrecord", new BookDTO());
		
		when(booksService.getAllBooks()).thenReturn(bookDTOList);
		
			mvc.perform(requestBuilder)
			.andExpect(status().isOk())
			.andExpect(view().name("searchForAbook"))
			.andExpect(forwardedUrl("/WEB-INF/jsp/searchForAbook.jsp"))
			.andExpect(model().attribute("books", bookDTOListfindrecord));
		
		
		 verify(booksService, times(1)).find(bookId);
	        verifyNoMoreInteractions(booksService);		
	        assertEquals(1,bookDTOListfindrecord.size());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    	
	}

	@Test
	final void testAddaBook() {
	
		try {
		assertTrue(true);
		
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/books/addaBook");	
		
			mvc.perform(requestBuilder)
			.andExpect(status().isOk())
			.andExpect(view().name("addaBook"))
			.andExpect(forwardedUrl("/WEB-INF/jsp/addaBook.jsp"))
			.andExpect(model().attribute("bookrecord", new BookDTO()));
			verifyZeroInteractions(booksService);
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
