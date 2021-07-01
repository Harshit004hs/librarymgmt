package com.librarymgmt.service;

import java.util.List;

import com.librarymgmt.model.Books;

public interface BookService {

	public Boolean saveBooks(Books books);

	public List<Books> findAll();
	
	public Boolean removeBook(String isbn);

}
