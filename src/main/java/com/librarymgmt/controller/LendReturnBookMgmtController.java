package com.librarymgmt.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.librarymgmt.model.Books;
import com.librarymgmt.model.Patron;
import com.librarymgmt.repo.BooksRepo;
import com.librarymgmt.repo.PatronRepo;

@RestController  
@RequestMapping("/api")
public class LendReturnBookMgmtController {
	
	@Autowired
	private BooksRepo booksRepo;
	
	@Autowired
	private PatronRepo patronRepo;
	
	public static final Logger logger = LogManager.getLogger(BooksMgmtController.class);
	
	@GetMapping(value = "/lendbook/{userid}/{isbn}",produces = APPLICATION_JSON_VALUE)
	public Patron lendBook(@RequestHeader String  userid,@RequestHeader String bookid) {
		List<Patron> users = patronRepo.findAll();
		List<Books> books = booksRepo.findAll();
 
		Patron borroweruser = new Patron();
		borroweruser.setUserId(userid);
	 
		books.forEach(book -> {
            if(book.getIsbn().equals(bookid)
            		&& book.getStatus().equals("Avilable")
            		&& book.getBorrower().equals("none"))
            		{
            			book.setBorrower(userid);
            			book.setStatus("Not Avilable");
            			if(borroweruser.getBookIssue().equals("none"))
            				borroweruser.setBookIssue(book.getTitle());
            			else
            				 borroweruser.setStatus("You [user id ]"+userid +"have used total limit of books can be borrowed by per user");
            				
            		}
           });
		
        users.forEach(user -> {
        	 if(user.getUserId().equals(userid))
             		{
             			 
             			borroweruser.setUserName(user.getUserName());
             			borroweruser.setStatus(user.getStatus());
             		}
        	 
        });
		return borroweruser;

	}
	
	@GetMapping(value = "/returnBook/{userid}/{isbn}",produces = APPLICATION_JSON_VALUE)
	public List<Books> returnBook(@RequestHeader String  userid,@RequestHeader String bookid) {
		List<Patron> users = patronRepo.findAll();
		List<Books> books = booksRepo.findAll();
		 
		Patron borroweruser = new Patron();
		books.forEach(book -> {
            if(book.getIsbn().equals(bookid)
            		&& book.getStatus().equals("Not Avilable")
            		&& book.getBorrower().equals(userid))
            		{
            			borroweruser.setBookReturn(book.getTitle());
            			book.setBorrower("none");
            			book.setStatus("Avilable");
            			 
            		}
           });
		
        users.forEach(user -> {
        	 if(user.getUserId().equals(userid))
             		{
        		 		if(user.getBookIssue().equals(borroweruser.getBookIssue()))
        		 			user.setBookIssue("none");
        		 		else
        		 		{
        		 			logger.info("Do Nothing!!!!");
        		 		}
        		 			
             			 
             		}
        	 
        });
		return books;


	}
}
