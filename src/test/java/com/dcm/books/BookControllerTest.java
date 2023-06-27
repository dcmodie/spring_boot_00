package com.dcm.books;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import org.springframework.http.MediaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = BookController.class)
class BookControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	BookRepository bookRepo;

	@MockBean
	BookService bookService;

	@Test
	void whenValidInput_thenReturns201() throws Exception {
		Book book = new Book(1, "Spring Boot @WebMvcTest", "John");

		mockMvc.perform(
				post("/books").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(book)))
				.andExpect(status().isCreated()).andDo(print());
	}

}