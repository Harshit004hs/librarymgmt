package com.librarymgmt.serviceImpl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.librarymgmt.model.Books;
import com.librarymgmt.repo.BooksRepo;
import com.librarymgmt.service.BookService;
import com.librarymgmt.utility.LibrarymgmtConst;

@Service
public class BookServiceImpl implements BookService{
	
	public static final Logger logger = LogManager.getLogger(BookServiceImpl.class);
	
	@Autowired
	private BooksRepo booksRepo;

	@Override
	public Boolean saveBooks(Books books) {
		
		boolean result = Boolean.FALSE;
		try {
			result = booksRepo.save(books) != null;
			
			if(result) {
				result = Boolean.TRUE;
				logger.info(LibrarymgmtConst.BOOKS_SAVE_SUCCESS);
			}
		}catch(Exception e) {
			logger.error(LibrarymgmtConst.BOOKS_SAVED_FAILED + "Exception Occurred: " + e.getMessage());
		}
		return result;
	}

	@Override
	public List<Books> findAll() {
		
		return booksRepo.findAll();
	}

	@Override
	public Boolean removeBook(String isbn) {
		if (isbn != null || !isbn.isEmpty()){
			booksRepo.removeBooksForIsbn(isbn);
            return true;
        }else{
            return false;
        }

	}

}
