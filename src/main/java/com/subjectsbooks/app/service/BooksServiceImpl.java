package com.subjectsbooks.app.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import com.subjectsbooks.app.entity.BookVO;
import com.subjectsbooks.app.repositories.BookReferenceVORepository;
import com.subjectsbooks.app.repositories.BookVORepository;
import com.subjectsbooks.app.model.BookDTO;






@Service
public class BooksServiceImpl implements BooksService {

	@Autowired
	BookVORepository bookRepository;
	
	@Autowired
	BookReferenceVORepository bookReferenceRepository;
	
	@Override
	@Transactional
	public List<BookDTO> getAllBooks() 
	{
		
		List<BookDTO> allBooks=new ArrayList<BookDTO>();
		
		List<BookVO> bookRecords=new ArrayList<BookVO>();
		try
		{
		 bookRecords= bookRepository.findAll();
		 if(bookRecords!=null && bookRecords.size()>0)
		 {
			for(BookVO bookVO : bookRecords) 
			{
				allBooks.add(mapbookdto(bookVO));				
			}
		 }
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
					
					
			return allBooks;
	}

	@Override
	@Transactional
	public boolean save(BookDTO bookdto) {
	
	boolean saveFlag=false;
	
		if(bookRepository.save(mapbookvo(bookdto))!=null)
			saveFlag=true;
	return saveFlag;
	}



	@Override
	@Transactional
	public BookDTO find(long bookId) {
		// TODO Auto-generated method stub
		
		return mapbookdto(bookRepository.findByBookId(bookId));
	}
	
	@Override
	@Transactional
	public BookVO findVO(long bookId) {
		// TODO Auto-generated method stub
		
		return bookRepository.findByBookId(bookId);
	}

	@Override
	@Transactional
	public boolean delete(long bookId) {
		// TODO Auto-generated method stub
		boolean deleteFlag=false;
		BookVO bookVO=bookRepository.findByBookId(bookId);
		if(bookReferenceRepository.deleteByBookVO(bookVO))
			deleteFlag=true;
		bookRepository.deleteByBookId(bookId);
		return deleteFlag;
	}

	@Override
	@Transactional
	public List<BookDTO> search(String title) {
		List<BookDTO> searchBooks=new ArrayList<BookDTO>();
		List<BookVO> bookRecords= bookRepository.findAllByTitle(title);
		for(BookVO bookVO : bookRecords) {
			
			searchBooks.add(mapbookdto(bookVO));				
		}
		// TODO Auto-generated method stub
		return searchBooks;
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
