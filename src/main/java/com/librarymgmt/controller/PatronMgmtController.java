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
import com.librarymgmt.model.Patron;
import com.librarymgmt.repo.PatronRepo;
import com.librarymgmt.service.PatronService;
import com.librarymgmt.utility.LibrarymgmtConst;

@RestController  
@RequestMapping("/api")
public class PatronMgmtController {

	@Autowired
	PatronService patronSevice;
	
	@Autowired
	private PatronRepo patronRepo;
	
	public static final Logger logger = LogManager.getLogger(BooksMgmtController.class);
	
	@PostMapping(value = "/patron",produces = APPLICATION_JSON_VALUE,consumes = APPLICATION_JSON_VALUE)
	public GenericResponse addPatron(@Valid @RequestBody Patron patron) {
		Boolean result = Boolean.FALSE;
		GenericResponse genericResponse = new GenericResponse();
		
		try {
			result = patronSevice.savePatron(patron);
			
			if(result) {
				genericResponse.setSuccessFlag(Boolean.TRUE);
				genericResponse.setSuccessMessage(LibrarymgmtConst.PATRON_SUCCESS);
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
	
	@GetMapping(value = "/patronDetails",produces = APPLICATION_JSON_VALUE)
	public Books getPatron(@RequestHeader String isbn) {
		
		Books books = new Books();
		
		try {
			List<Patron> bookList = patronSevice.findAll();
		
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
	
	@DeleteMapping(value = "/patron/{userId}",produces = APPLICATION_JSON_VALUE)
	public Books removePatronByIsbn(@RequestHeader String userId) {
		
		Books books = new Books();
		
		try {
		
			if(patronSevice.removePatron(userId)){
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
	
	@PutMapping(value = "/patron",produces = APPLICATION_JSON_VALUE,consumes = APPLICATION_JSON_VALUE)
	public GenericResponse updatePatron(@Valid @RequestBody Patron patron,@RequestHeader String userId) {
		GenericResponse genericResponse = new GenericResponse();
		
		try {
	
			if(patronRepo.findByUserId(userId)==true) {
				patronSevice.savePatron(patron);
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
	
}
