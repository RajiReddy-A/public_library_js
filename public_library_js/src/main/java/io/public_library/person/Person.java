package io.public_library.person;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.JoinColumn;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.public_library.book.Book;

@Entity
@Table(name="person")
public class Person {
	
	@Id
	@Column(name="personName")
	private String personName;
	
	@Column(name="mobile")
	private String mobile;
	
	@JsonIgnore
	@Column(name="password")
	private String password;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name="bookandperson",
		joinColumns=@JoinColumn(name="personName"),
		inverseJoinColumns=@JoinColumn(name="bookName"))
	private List<Book> listOfBooks = new ArrayList<Book>();
	
	public Person() {
		
	}
	
	public Person(String personName, String mobile, String password) {
		super();
		this.personName = personName;
		this.mobile = mobile;
		this.password = password;
		
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@JsonIgnoreProperties("listOfPersons")
	public List<Book> getListOfBooks() {
		return listOfBooks;
	}

	public void setListOfBooks(List<Book> listOfBooks) {
		this.listOfBooks = listOfBooks;
	}

	
	
}
