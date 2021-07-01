package com.librarymgmt.serviceImpl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.librarymgmt.model.Patron;
import com.librarymgmt.repo.PatronRepo;
import com.librarymgmt.service.PatronService;
import com.librarymgmt.utility.LibrarymgmtConst;

@Service
public class PatronServiceImpl implements PatronService {

public static final Logger logger = LogManager.getLogger(BookServiceImpl.class);
	
	@Autowired
	private PatronRepo patronRepo;

	
	public Boolean savePatron(Patron patron) {
		
		boolean result = Boolean.FALSE;
		try {
			result = patronRepo.save(patron) != null;
			
			if(result) {
				result = Boolean.TRUE;
				logger.info(LibrarymgmtConst.BOOKS_SAVE_SUCCESS);
			}
		}catch(Exception e) {
			logger.error(LibrarymgmtConst.BOOKS_SAVED_FAILED + "Exception Occurred: " + e.getMessage());
		}
		return result;
	}

	
	public List<Patron> findAll() {
		
		return patronRepo.findAll();
	}

	public Boolean removePatron(String userId) {
		if (userId != null || !userId.isEmpty()){
			patronRepo.removePatronForUserId(userId);
            return true;
        }else{
            return false;
        }

	}
}
