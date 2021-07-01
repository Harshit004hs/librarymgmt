package com.librarymgmt.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.librarymgmt.beans.GenericResponse;


@Entity
@Table(name = "BOOKS")
public class Books extends GenericResponse{

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "BOOKS_SEQUENCE")
	@SequenceGenerator(name = "BOOKS_SEQUENCE",sequenceName = "BOOKS_SEQ",allocationSize = 0)
	@Column(name = "ISBN")
	private String isbn;
	
	@Column(name = "TITLE")
	private String title;
	
	@Column(name = "COST")
	private String cost;
	
	@Column(name = "AUTHOR")
	private String author;
    
	@Column(name = "PUBLISHER")
    private String publisher;
    
	@Column(name = "STATUS")
    private String status;
    
	@Column(name = "BORROWER")
    private String borrower;
    
	@Column(name = "BORROW_DATE")
    private String borrowDate;
    
	@Column(name = "RETURN_DATE")
    private String returnDate;

	@Override
	public String toString() {
		return "Books [isbn=" + isbn + ", title=" + title + ", cost=" + cost + "]";
	}

	public Books(String isbn, @NotBlank String title, String cost, String author, String publisher, String status,
			String borrower, String borrowDate, String returnDate) {
		super();
		this.isbn = isbn;
		this.title = title;
		this.cost = cost;
		this.author = author;
		this.publisher = publisher;
		this.status = status;
		this.borrower = borrower;
		this.borrowDate = borrowDate;
		this.returnDate = returnDate;
	}

	public Books() {
		// TODO Auto-generated constructor stub
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCost() {
		return cost;
	}

	public void setCost(String cost) {
		this.cost = cost;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getBorrower() {
		return borrower;
	}

	public void setBorrower(String borrower) {
		this.borrower = borrower;
	}

	public String getBorrowDate() {
		return borrowDate;
	}

	public void setBorrowDate(String borrowDate) {
		this.borrowDate = borrowDate;
	}

	public String getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}

}
