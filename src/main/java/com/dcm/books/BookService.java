package com.dcm.books;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;


@Service
public class BookService {
	private BookRepository bookRepo;
	BookService(BookRepository bookRepo){
		this.bookRepo =  bookRepo;
	}
	
	public Book getBook(long id) throws BookNotFoundException{
		Optional<Book> book = bookRepo.findById(id);
		if (book.isEmpty()) {
			throw new BookNotFoundException("Book with id: " + id + " not found.");
		}
		return book.get();
		
	}
	
	public Book addBook (Book book) {
		Book savedBook = this.bookRepo.save(book);
		return savedBook;
	}
	
//	public Book updateBook(Long id, Book book)  throws BookNotFoundException{
//		Optional<Book> bookFromDb = bookRepo.findById(id);
//		if (bookFromDb.isEmpty()) {
//			throw new BookNotFoundException("Book with id: " + id + " not found.");
//		}
//		
//		Book savedBook = this.bookRepo.save(book);
//		return savedBook;
//	}
	
	
	  public Book updateBook(Long id, Book bookUpdate)  throws BookNotFoundException {
	    Optional<Book> bookDB = bookRepo.findById(id);

	    if (bookDB.isPresent()) {
	      Book bookObj = bookDB.get();
	      bookObj.setTitle(bookUpdate.getTitle());
	      bookObj.setAuthor(bookUpdate.getAuthor());
	      bookRepo.save(bookObj);
	      return bookObj;
	    } else {
	      throw new BookNotFoundException("Book with id: " + id + " not found.");
	    }
	  }
	
	public List<Book> getAll() {
		List<Book> books = new ArrayList<Book>();
		books = bookRepo.findAll();
		return books;		
	}
}