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
 * The Class SubjectsController.
 */
@Controller
@RequestMapping("/subjects")
public class SubjectsController {



	
	/** The subjects. */
	@Autowired
	private	SubjectsService subjects;
	
	
	/**
	 * Deletea subject.
	 *
	 * @param model the model
	 * @return the string
	 */
	@RequestMapping(value = "/deleteaSubject", method = RequestMethod.GET)
	public String deleteaSubject(Model model)
	{
		
		model.addAttribute("subjects",subjects.getAllSubjects() );
	
		
		return "deleteaSubject";
	}
	
	/**
	 * Deletea subject delete.
	 *
	 * @param subjectIdStr the subject id str
	 * @param model the model
	 * @return the string
	 */
	@RequestMapping(value = "/deleteaSubjectDelete/{subjectIdStr}", method = RequestMethod.GET)
	public String deleteaSubjectDelete(@PathVariable("subjectIdStr")String subjectIdStr,Model model)
	{
		String message="Record is not deleted";
		if(!subjectIdStr.isEmpty())
		{
		long subjectId=Long.parseLong(subjectIdStr);
		subjects.delete(subjectId);
		message="Record is deleted successfully";
		}
		model.addAttribute("subjects",subjects.getAllSubjects() );
		model.addAttribute("message",message );
		
		
		return "deleteaSubject";
	}
	
	/**
	 * Search fora subject.
	 *
	 * @param model the model
	 * @return the string
	 */
	@RequestMapping(value = "/searchForaSubject", method = RequestMethod.GET)
	public String searchForaSubject(Model model)
	{
		
		model.addAttribute("subjects",subjects.getAllSubjects() );
		return "searchForASubject";
	}
	
	/**
	 * Searchasubject.
	 *
	 * @param title the title
	 * @param model the model
	 * @return the string
	 */
	@RequestMapping(value = "/searchasubject", method = RequestMethod.POST)
	public String searchasubject(String title,Model model)
	{
		
		List<SubjectDTO> subjectRecordset=new ArrayList<>();
		subjectRecordset=subjects.search(title); 
		
				model.addAttribute("subjects",subjectRecordset );
		
		return "searchForASubject";
	}
	
	
	



	
	/**
	 * Adda subject save.
	 *
	 * @param subjectrecord the subjectrecord
	 * @param model the model
	 * @return the string
	 */
	@RequestMapping(value = "/addaSubjectSave", method = RequestMethod.POST)
	public String addaSubjectSave(@ModelAttribute("subjectrecord") SubjectDTO subjectrecord,Model model)
	{
		
		if(subjectrecord!=null)
			subjects.save(subjectrecord);	
		model.addAttribute("message","Subject has been added successfully" );
		
		SubjectDTO subjectrecord1=new SubjectDTO();
		model.addAttribute("subjectrecord",subjectrecord1 );
		
		return "addaSubject";
	}
	
	/**
	 * Adda subject.
	 *
	 * @param model the model
	 * @return the string
	 */
	@RequestMapping(value = "/addaSubject", method = RequestMethod.GET)
	public String addaSubject(Model model)
	{
		SubjectDTO subjectrecord=new SubjectDTO();
		model.addAttribute("subjectrecord",subjectrecord );
		
		return "addaSubject";
	}
}
