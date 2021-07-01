package com.librarymgmt.controller;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.List;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.librarymgmt.beans.GenericResponse;
import com.librarymgmt.model.Books;
import com.librarymgmt.repo.BooksRepo;
import com.librarymgmt.service.BookService;
import com.librarymgmt.utility.LibrarymgmtConst;

@RestController  
@RequestMapping("/api")
public class BooksMgmtController {

	@Autowired
	private BookService bookSevice;
	
	@Autowired
	private BooksRepo booksRepo;
	
	public static final Logger logger = LogManager.getLogger(BooksMgmtController.class);
	
	@PostMapping(value = "/book",produces = APPLICATION_JSON_VALUE,consumes = APPLICATION_JSON_VALUE)
	public GenericResponse addBook(@Valid @RequestBody Books books) {
		Boolean result = Boolean.FALSE;
		GenericResponse genericResponse = new GenericResponse();
		
		try {
			result = bookSevice.saveBooks(books);
			
			if(result) {
				genericResponse.setSuccessFlag(Boolean.TRUE);
				genericResponse.setSuccessMessage(LibrarymgmtConst.BOOKS_SUCCESS);
			}else {
				genericResponse.setSuccessFlag(Boolean.FALSE);
				genericResponse.setErrorMessage(LibrarymgmtConst.RECORD_NOT_EXIST);
			}
		}catch(Exception e) {
			genericResponse.setSuccessFlag(Boolean.FALSE);
			genericResponse.setErrorMessage(LibrarymgmtConst.EXCEPTION_OCCURED);
		}
		return genericResponse;
	}
	
	@GetMapping(value = "/book",produces = APPLICATION_JSON_VALUE)
	public Books getBooks(@RequestHeader String isbn) {
		
		Books books = new Books();
		
		try {
			List<Books> bookList = bookSevice.findAll();
		
			if(bookList != null){
				books.setSuccessFlag(Boolean.TRUE);
				books.setSuccessMessage(LibrarymgmtConst.SUCCESS);
			}else {
				books.setSuccessFlag(Boolean.FALSE);
				books.setErrorMessage(LibrarymgmtConst.RECORD_NOT_EXIST);
			}
		}catch(Exception e) {
			books.setSuccessFlag(Boolean.FALSE);
			books.setErrorMessage(LibrarymgmtConst.EXCEPTION_OCCURED);
		}
		return books;
		
	}
	
	@DeleteMapping(value = "/book/{isbn}",produces = APPLICATION_JSON_VALUE)
	public Books removeBookByIsbn(@RequestHeader String isbn) {
		
		Books books = new Books();
		
		try {
		
			if(bookSevice.removeBook(isbn)){
				books.setSuccessFlag(Boolean.TRUE);
				books.setSuccessMessage(LibrarymgmtConst.SUCCESS);
			}else {
				books.setSuccessFlag(Boolean.FALSE);
				books.setErrorMessage(LibrarymgmtConst.RECORD_NOT_EXIST);
			}
		}catch(Exception e) {
			books.setSuccessFlag(Boolean.FALSE);
			books.setErrorMessage(LibrarymgmtConst.EXCEPTION_OCCURED);
		}
		return books;
		
	}
	
	@PutMapping(value = "/book",produces = APPLICATION_JSON_VALUE,consumes = APPLICATION_JSON_VALUE)
	public GenericResponse updateBook(@Valid @RequestBody Books books,@RequestHeader String isbn) {
		GenericResponse genericResponse = new GenericResponse();
		
		try {
	
			if(booksRepo.findByIsbn(isbn)==true) {
				bookSevice.saveBooks(books);
				genericResponse.setSuccessFlag(Boolean.TRUE);
				genericResponse.setSuccessMessage(LibrarymgmtConst.BOOKS_UPDATE_SUCCESS);
			}else {
				genericResponse.setSuccessFlag(Boolean.FALSE);
				genericResponse.setErrorMessage(LibrarymgmtConst.RECORD_NOT_EXIST);
			}
		}catch(Exception e) {
			genericResponse.setSuccessFlag(Boolean.FALSE);
			genericResponse.setErrorMessage(LibrarymgmtConst.EXCEPTION_OCCURED);
		}
		return genericResponse;
	}
	
	@GetMapping(value = "/book/{title}",produces = APPLICATION_JSON_VALUE)
	public Integer getBookByTitle(@RequestHeader String title) {
		
		Integer bookList = null;
		
		try {
			if(!title.isEmpty() || title != null)
			bookList = booksRepo.findByTitle(title);
			logger.info(LibrarymgmtConst.BOOKS_SAVE_SUCCESS);
		}catch(Exception e) {
			logger.info(LibrarymgmtConst.EXCEPTION_OCCURED);
		}
		return bookList;
		
	}
}
