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
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.public_library.book.Book;

@Entity
@Table(name="person")
public class Person {
	
	@Id
	@Column(name="username")
	private String username;
	
	@Column(name="mobile")
	private String mobile;
	
	//@JsonIgnore
	@Column(name="password")
	private String password;
	
	@Transient
	@JsonIgnore
	private String passwordConfirm;
	
	/*@Column(name="enabled")
	private int enabled;*/
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name="bookandperson",
		joinColumns=@JoinColumn(name="username"),
		inverseJoinColumns=@JoinColumn(name="bookName"))
	private List<Book> listOfBooks = new ArrayList<Book>();
	
	public Person() {
		
	}
	
	public Person(String username, String mobile, String password) {
		super();
		this.username = username;
		this.mobile = mobile;
		this.password = password;
		//this.enabled = enabled;
		
	}

	public String getUsername() {
		return username;
	}

	public void setPersonName(String username) {
		this.username = username;
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

	/*public int getEnabled() {
		return enabled;
	}

	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}*/

	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}

	@JsonIgnoreProperties("listOfPersons")
	public List<Book> getListOfBooks() {
		return listOfBooks;
	}

	public void setListOfBooks(List<Book> listOfBooks) {
		this.listOfBooks = listOfBooks;
	}

	
	
}
