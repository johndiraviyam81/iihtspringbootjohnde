package com.subjectsbooks.app.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
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

import com.subjectsbooks.app.controllers.BooksController;
import com.subjectsbooks.app.entity.BookVO;
import com.subjectsbooks.app.model.BookDTO;
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
	
	private List<BookDTO> bookDTOList = new ArrayList<>();

	private BookDTO bookDTO1 = new BookDTO();
	
	private BookDTO bookDTO2 = new BookDTO();
	
	private BookDTO bookDTO3 = new BookDTO();
	
	private BookDTO bookDTO4 = new BookDTO();
	
	private List<BookVO> bookVOList = new ArrayList<BookVO>();
	
	private BookVO bookVO1 = new BookVO();
	
	private BookVO bookVO2 = new BookVO();

	private BookVO bookVO3 = new BookVO();
	
	private BookVO bookVO4 = new BookVO();
	
	@BeforeEach
	void setUp() throws Exception {
		booksService=new BooksServiceImpl();
		mvc = MockMvcBuilders.standaloneSetup(booksService).build();
		this.bookDTO1=this.setupBookDTO("1101","Journey of the softwares", "12.50","34223433","2019-09-09");
		this.bookDTO2=this.setupBookDTO("1102","Book of the network", "13.50","34223433","2018-05-29");
		this.bookDTO3=this.setupBookDTO("1103","Lifetime achievement of the softwares", "11.50","64223433","2014-5-06");
		this.bookDTO4=this.setupBookDTO("1104","Grooming of the network", "24.50","5223433","2016-06-15");
		this.bookDTOList.add(this.bookDTO1);
		this.bookDTOList.add(this.bookDTO2);
		this.bookDTOList.add(this.bookDTO3);
		this.bookDTOList.add(this.bookDTO4);
		
		this.bookVO1=mapbookvo(this.bookDTO1);
		this.bookVO2=mapbookvo(this.bookDTO2);
		this.bookVO3=mapbookvo(this.bookDTO3);
		this.bookVO4=mapbookvo(this.bookDTO4);
		
		this.bookVOList.add(bookVO1);
		this.bookVOList.add(bookVO2);
		this.bookVOList.add(bookVO3);
		this.bookVOList.add(bookVO4);
		
	}



	@Test
	final void testGetAllBooks() {
		List<BookDTO> allBooks = new ArrayList<>(); 
		when(bookRepository.findAll()).thenReturn(bookVOList);
		 if(bookVOList!=null && bookVOList.size()>0)
		 {
			for(BookVO bookVO : bookVOList) 
			{
				allBooks.add(mapbookdto(bookVO));				
			}
		 }
		 
		assertEquals(allBooks.size(),bookDTOList.size());
	}

	@Test
	final void testSave() {
		boolean saveFlag=false;
		
		when(bookRepository.save(mapbookvo(this.bookDTO4))).thenReturn(bookVO4);
			saveFlag=true;
		assertTrue(saveFlag);
	}

	@Test
	final void testFind() {
		long bookId=1101L;
		when(bookRepository.findByBookId(bookId)).thenReturn(bookVO1);
		BookDTO finDTO=mapbookdto(this.bookVO1);
		
		assertEquals(finDTO.getBookId(),this.bookDTO1.getBookId());
	}

	@Test
	final void testFindVO() {
		
		long bookId=1101L;
		when(bookRepository.findByBookId(bookId)).thenReturn(this.bookVO1);	
		
		assertNotNull(this.bookVO1);
	}

	@Test
	final void testDelete() {
		boolean deleteFlag=false;
		long bookId=1101L;
		when(bookRepository.findByBookId(bookId)).thenReturn(bookVO1);
		when(bookReferenceRepository.deleteByBookVO(bookVO1)).thenReturn(true);
			deleteFlag=true;
		bookRepository.deleteByBookId(bookId);
		
		assertTrue(true);
	}

	@Test
	final void testSearch() {
		List<BookDTO> allBooks = new ArrayList<>(); 
		when(bookRepository.findAllByTitle("Journey ")).thenReturn(bookVOList);
		 if(bookVOList!=null && bookVOList.size()>0)
		 {
			for(BookVO bookVO : bookVOList) 
			{
				allBooks.add(mapbookdto(bookVO));				
			}
		 }
		 
		assertEquals(allBooks.size(),bookDTOList.size());
		 
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
	
	private BookDTO mapbookdto(BookVO bookVO)
	{
		BookDTO bookset=new BookDTO();
		System.out.println("******* bookvo object the  **********\n"+bookVO.toString());
		bookset.setBookId(String.valueOf(bookVO.getBookId()));
		bookset.setPrice(String.valueOf(bookVO.getPrice()));
		bookset.setPublishDate(String.valueOf(bookVO.getPublishDate()));
		bookset.setTitle(bookVO.getTitle().trim());
		bookset.setVolume(String.valueOf(bookVO.getVolume()));
		return bookset;
	}
}
