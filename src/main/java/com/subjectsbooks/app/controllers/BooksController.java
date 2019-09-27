package com.subjectsbooks.app.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.subjectsbooks.app.model.BookDTO;
import com.subjectsbooks.app.service.BooksService;
import com.subjectsbooks.app.service.SubjectsServiceImpl;
import com.subjectsbooks.app.entity.BookVO;
import com.subjectsbooks.app.model.SubjectDTO;
import com.subjectsbooks.app.service.SubjectsService;



// TODO: Auto-generated Javadoc
/**
 * The Class BooksController.
 */
@Controller
@RequestMapping("/books")
public class BooksController {


	
	/** The books. */
	@Autowired
	private	BooksService books;
	
	
	
	/**
	 * Search for abook.
	 *
	 * @param model the model
	 * @return the string
	 */
	@RequestMapping(value = "/searchForAbook", method = RequestMethod.GET)
	public String searchForAbook(Model model)
	{
		
		model.addAttribute("books",books.getAllBooks() );
		return "searchForAbook";
	}
	
	/**
	 * Delete A book.
	 *
	 * @param model the model
	 * @return the string
	 */
	@RequestMapping(value = "/deleteABook", method = RequestMethod.GET)
	public String deleteABook(Model model)
	{
		
		model.addAttribute("books",books.getAllBooks() );
		return "deleteABook";
	}
	
	/**
	 * Delete A book delete.
	 *
	 * @param bookIdStr the book id str
	 * @param model the model
	 * @return the string
	 */
	@RequestMapping(value = "/deleteABookDelete/{bookIdStr}", method = RequestMethod.GET)
	public String deleteABookDelete(@PathVariable("bookIdStr")String bookIdStr,Model model)
	{
		String message="Record is not deleted";
		if(!bookIdStr.isEmpty())
		{
		long bookId=Long.parseLong(bookIdStr);
		books.delete(bookId);
		message="Record is deleted successfully";
		}
		model.addAttribute("books",books.getAllBooks() );
		model.addAttribute("message",message );
		
		
		return "deleteABook";
	}
	
	 
	
	
	/**
	 * Adda book save.
	 *
	 * @param bookrecord the bookrecord
	 * @param model the model
	 * @return the string
	 */
	@RequestMapping(value = "/addaBookSave", method = RequestMethod.POST)
	public String addaBookSave(@ModelAttribute("bookrecord") BookDTO bookrecord,Model model)
	{
		
		if(bookrecord!=null)
		if(books.save(bookrecord))		
		model.addAttribute("message","Book has been added successfully" );
		
		BookDTO bookrecord1=new BookDTO();
		model.addAttribute("bookrecord",bookrecord1 );
		
		return "addabook";
	}
	
	/**
	 * Searchabook.
	 *
	 * @param bookId the book id
	 * @param model the model
	 * @return the string
	 */
	@RequestMapping(value = "/searchabook", method = RequestMethod.POST)
	public String searchabook(String bookId,Model model)
	{
		
		List<BookDTO> booksrecordset=new ArrayList<>();
		BookDTO bookrecord=books.find(Long.parseLong(bookId));
		booksrecordset.add(bookrecord);
				model.addAttribute("books",booksrecordset );
		
		return "searchForAbook";
	}
	
	/**
	 * Adda book.
	 *
	 * @param model the model
	 * @return the string
	 */
	@RequestMapping(value = "/addaBook", method = RequestMethod.GET)
	public String addaBook(Model model)
	{
		BookDTO bookrecord=new BookDTO();
		model.addAttribute("bookrecord",bookrecord );
		
		return "addabook";
	}
	


}
