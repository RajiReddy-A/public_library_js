package io.public_library.book;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.public_library.person.Person;
import io.public_library.person.PersonService;

@Controller
public class BookController {

	@Autowired
	private BookService bookService;
	
	@Autowired
	private PersonService personService;
	
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
		return personService.getAllPersons();
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
		return personService.getPerson(username);
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
	
	/*@RequestMapping(value="/signup", method=RequestMethod.GET)
	public String signup() {
		return "signup";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String loginPage() {
		return "login";
	}*/

}
