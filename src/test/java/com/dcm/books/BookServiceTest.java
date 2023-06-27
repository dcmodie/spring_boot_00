package com.dcm.books;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName; 
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {
	@Mock
	BookRepository bookRepo;

	private BookService bookService;
	private Book testBook = new Book( 1,"Football Book","John Smith");
	
    @BeforeAll
    static void initAll() {
    }

    @BeforeEach
    void init() {
    	//populate mock repo w one book
		bookService = new BookService(bookRepo);
		Optional<Book> myBook = Optional.of(testBook);
		when(bookRepo.findById(1L)).thenReturn(myBook);
	
    }

	
	@Test
	@DisplayName("Get book by id")
	public void getBookById () throws Exception {
		Book foundBook = bookService.getBook(1);		
		assertEquals(foundBook, testBook);	
	}
	
	@Test
	@DisplayName("Throws exception for not found")
	public void getBookByIdNotPresent () throws Exception {
		Optional<Book> noBook = Optional.empty();
		bookService = new BookService(bookRepo);
		when(bookRepo.findById(1L)).thenReturn(noBook);
		assertThrows(BookNotFoundException.class, ()-> bookService.getBook(1));
		//verify(catRepo).findById(1L);


	}
	
	@Test
	public void updateBook () throws Exception {
		Book newBook = new Book(testBook.getId(), testBook.getTitle(), testBook.getAuthor());
		newBook.setTitle("new  Title");
		Book updatedBook =	bookService.updateBook (newBook.getId(), newBook);
		assertEquals(updatedBook, newBook);
	}
	
	
}

//@before
//create test suite
//before each
