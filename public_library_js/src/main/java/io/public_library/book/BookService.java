package io.public_library.book;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.public_library.person.Person;
import io.public_library.person.PersonRepository;

@Service
public class BookService {
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private PersonRepository personRepository;

	public List<Book> getAllBooks(){
		List<Book> books = new ArrayList<>();
		bookRepository.findAll().forEach(books::add);
		return books;
	}
	
	public Book getBook(String bookName) {
		return bookRepository.findOne(bookName);
	}
	
	@Transactional
	public String borrowBook(Book book, String username) {
		Book dbBook = bookRepository.findOne(book.getBookName());
		Person dbPerson = personRepository.findOne(username);
		
		List<Book> booksList = dbPerson.getListOfBooks();
		List<Person> personsList = dbBook.getListOfPersons();
		Boolean isItBorrowed = booksList.contains(dbBook);
		if(isItBorrowed) {
			return "same book already borrowed";
		}
		else if(dbBook.getCopiesAvailable() >= 1) {
			booksList.add(dbBook);
			dbPerson.setListOfBooks(booksList);
			personsList.add(dbPerson);
			dbBook.setListOfPersons(personsList);
			dbBook.setCopiesAvailable(dbBook.getCopiesAvailable()-1);
			bookRepository.save(dbBook);
			return "success";
		}
		else {
			return "book not available";
		}
		
	}
}
