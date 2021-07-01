package com.librarymgmt.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.librarymgmt.model.Books;
import com.librarymgmt.model.Patron;

@Repository
public interface PatronRepo extends JpaRepository<Patron, String>{

	public List<Patron> findAll();
	
	@Query(value = "DELETE * FROM PATRON WHERE USER_ID in :userId",nativeQuery = true)
	public Boolean removePatronForUserId(@Param("userId") String userId);
	
	//public Patron saveByUserId(String userId, Patron patron);

	public boolean findByUserId(String userId);
	
}
