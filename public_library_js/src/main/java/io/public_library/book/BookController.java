package io.public_library.book;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BookController {

	@RequestMapping(value="/", method=RequestMethod.GET)
	public String homePage() {
		return "allbooks";
	}
	
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

}
