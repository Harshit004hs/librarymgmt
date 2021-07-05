package com.librarymgmt;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.librarymgmt.model.Books;
import com.librarymgmt.repo.BooksRepo;
import com.librarymgmt.serviceImpl.BookServiceImpl;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@WebAppConfiguration
class LibrarymgmtApplicationTests {

	

	    private MockMvc mockMvc;

	    @Autowired
	    private BooksRepo booksRepo;

	    @Autowired
	    private ObjectMapper mapper;

	    private BookServiceImpl booksResource;

	    @Before
	    public void setup(){
	        MockitoAnnotations.initMocks(this);
	        booksResource = new BookServiceImpl();
	        mockMvc = MockMvcBuilders.standaloneSetup(booksResource).build();
	        ReflectionTestUtils.setField(booksResource, "booksRepo", booksRepo);
	    }

	    @Test
	    public void addBookTest() throws Exception{
	        Books book = new Books();
	        
	        String body = mapper.writeValueAsString(book);
	        mockMvc.perform(post("/api/book")
	            .contentType(MediaType.APPLICATION_JSON)
	            .accept(MediaType.APPLICATION_JSON)
	            .content(body))
	                .andExpect(status().isCreated());
	    }

	    @Test
	    public void getBookByIsbnTest() throws Exception {
	        Books book = new Books();
	        booksRepo.save(book);
	        MvcResult result = mockMvc.perform(get("/api/book/isbn2")
	                .accept(MediaType.APPLICATION_JSON))
	                .andExpect(status().isOk())
	                .andExpect(jsonPath("$.title",is("Test2")))
	                .andReturn();
	    }


}
