package com.subjectsbooks.app.service;

import java.util.List;

import com.subjectsbooks.app.entity.BookVO;
import com.subjectsbooks.app.model.BookDTO;





public interface BooksService 
{
	public boolean save(BookDTO bookdto);

	public List<BookDTO> getAllBooks();


	public BookDTO find(long bookId);
	
	public BookVO findVO(long bookId);

	public boolean delete(long bookId);

	public List<BookDTO> search(String title);
}
