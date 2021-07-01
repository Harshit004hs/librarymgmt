package com.librarymgmt.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.librarymgmt.model.Books;

@Repository
public interface BooksRepo extends JpaRepository<Books, String>{

	public List<Books> findAll();
	
	@Query(value = "DELETE * FROM BOOKS WHERE ISBN in :isbn",nativeQuery = true)
	public Boolean removeBooksForIsbn(@Param("isbn") String isbn);
	
	//public Books saveByIsbn(String isbn, Books book);

	public boolean findByIsbn(String isbn);
	
	@Query(value = "SELECT COUNT(title) FROM BOOKS WHERE TITLE in :title", nativeQuery = true)
	public int findByTitle(String title);
}
