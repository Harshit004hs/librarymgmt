package com.librarymgmt.service;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.librarymgmt.model.Patron;


public interface PatronService {

	public Boolean savePatron(Patron patron);

	public List<Patron> findAll();
	
	public Boolean removePatron(String userId);
}
