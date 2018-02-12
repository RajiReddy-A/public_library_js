package io.public_library.book;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.public_library.person.Person;

@Entity
@Table(name="book")
public class Book {
	
	@Id
	@Column(name="bookName")
	private String bookName;
	
	@Column(name="author")
	private String author;
	
	@Column(name="copiesTotal")
	private int    copiesTotal;
	
	@Column(name="copiesAvailable")
	private int    copiesAvailable;
	
	@ManyToMany(mappedBy="listOfBooks", cascade = CascadeType.ALL)
	private List<Person> listOfPersons = new ArrayList<Person>();
	
	public Book() {
		
	}
	
	public Book(String bookName, String author, int copiesTotal, int copiesAvailable) {
		super();
		this.bookName = bookName;
		this.author = author;
		this.copiesTotal = copiesTotal;
		this.copiesAvailable = copiesAvailable;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getCopiesTotal() {
		return copiesTotal;
	}

	public void setCopiesTotal(int copiesTotal) {
		this.copiesTotal = copiesTotal;
	}

	public int getCopiesAvailable() {
		return copiesAvailable;
	}

	public void setCopiesAvailable(int copiesAvailable) {
		this.copiesAvailable = copiesAvailable;
	}

	@JsonIgnoreProperties("listOfBooks")
	public List<Person> getListOfPersons() {
		return listOfPersons;
	}

	public void setListOfPersons(List<Person> listOfPersons) {
		this.listOfPersons = listOfPersons;
	}	
	
}
