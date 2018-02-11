package io.public_library.book;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.public_library.auth.service.UserServiceImpl;
import io.public_library.person.Person;
import io.public_library.person.PersonService;

@Controller
public class BookController {

	@Autowired
	private BookService bookService;
	
	@Autowired
	private UserServiceImpl userServiceImpl;
	
	@CrossOrigin
	@RequestMapping(value="/apis/allbooks", method=RequestMethod.GET)
	@ResponseBody
	public List<Book> allBooksApi() {
		return bookService.getAllBooks();
	}
	
	@CrossOrigin
	@RequestMapping(value="/apis/allpersons", method=RequestMethod.GET)
	@ResponseBody
	//@JsonIgnoreProperties("password")
	public List<Person> allPersonsApi() {
		return userServiceImpl.getAllPersons();
	}
	
	@CrossOrigin
	@RequestMapping("/apis/books/{bookName}")
	@ResponseBody
	public Book getBook(@PathVariable String bookName) {
		return bookService.getBook(bookName);
	}
	
	@CrossOrigin
	@RequestMapping("/apis/persons/{username}")
	@ResponseBody
	public Person getPerson(@PathVariable String username) {
		return userServiceImpl.findOne(username);
	}
	
	/*@RequestMapping(value="/", method=RequestMethod.GET)
	public String homePage() {
		return "allbooks";
	}*/
	
	@RequestMapping(value="/books", method=RequestMethod.GET)
	public String getBook() {
		return "bookdetails";
	}
	
	@RequestMapping(value="/allpersons", method=RequestMethod.GET)
	public String allPersons() {
		return "allpersons";
	}
	
	@RequestMapping(value="/persons", method=RequestMethod.GET)
	public String getPerson() {
		return "persondetails";
	}
	
	@RequestMapping(value="/borrowbook", method=RequestMethod.GET)
	public String borrowBook(Model model) {
		model.addAttribute("book", new Book());
		return "borrowbook";
	}
	
	@RequestMapping(value="/borrowbook", method=RequestMethod.POST)
	public String borrowBook(@ModelAttribute Book book, Principal principal, RedirectAttributes redirectAttrs) {
		
		if(principal == null) {
			redirectAttrs.addFlashAttribute("message", "logintoborrow");
			return "redirect:/login";
		}
		
		String borrowStatus = bookService.borrowBook(book, principal.getName());
		
		if(borrowStatus.equals("success")) {
			redirectAttrs.addFlashAttribute("message", "successfully borrowed");
			return "redirect:/persons?person="+principal.getName();
		}
		else if(borrowStatus.equals("same book already borrowed")) {
			redirectAttrs.addFlashAttribute("message", "same book already borrowed");
			return "redirect:/borrowbook";
		}
		else {
			redirectAttrs.addFlashAttribute("message", "book not available");
			return "redirect:/borrowbook";
		}
				
	}
	
	@RequestMapping(value="/returnbook", method=RequestMethod.GET)
	public String returnBook(Model model) {
		model.addAttribute("book", new Book());
		return "returnbook";
	}
	
	@RequestMapping(value="/returnbook", method=RequestMethod.POST)
	public String returnBook(@ModelAttribute Book book, Principal principal, RedirectAttributes redirectAttrs) {
		
		if(principal == null) {
			redirectAttrs.addFlashAttribute("message", "logintoreturn");
			return "redirect:/login";
		}
		
		String returnStatus = bookService.returnBook(book, principal.getName());
		
		if(returnStatus.equals("success")) {
			redirectAttrs.addFlashAttribute("message", "successfully returned");
			return "redirect:/persons?person="+principal.getName();
		}
		else {
			redirectAttrs.addFlashAttribute("message", "book not borrowed");
			return "redirect:/returnbook";
		}
		
	}

}
