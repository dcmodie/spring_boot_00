package com.dcm.books;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dcm.payroll.Employee;

@RestController
@RequestMapping("/books")
public class BookController {
	
	@Autowired BookService bookService;
	
	BookController(){
		
	}
	
	@GetMapping 
	public List<Book> getBooks() {
		return bookService.getAll();
		
	}
	
	@GetMapping ("/{id}")
	public Book getOne(@PathVariable Long id) throws BookNotFoundException{
		//bookService.getBook(id);
		return new Book(1,"a","b");
	}
	
	@PostMapping
	public @ResponseBody ResponseEntity<Book> add(@RequestBody Book newBook) {
		Book savedBook = bookService.addBook(newBook);
		ResponseEntity<Book> resEnt = new ResponseEntity<Book>(savedBook, HttpStatus.OK);
		return resEnt;	
	}
	
	@PutMapping ("/{id}")
	public @ResponseBody ResponseEntity <Book> update(@PathVariable Long id, @RequestBody Book newBook)throws BookNotFoundException{
		Book savedBook = bookService.updateBook(id, newBook);
		ResponseEntity<Book> resEnt = new ResponseEntity<Book>( savedBook, HttpStatus.OK);
		return resEnt;
	}
	
}

//reference code:
//
//
//@PutMapping("/tutorials/{id}")
//public ResponseEntity<Tutorial> updateTutorial(@PathVariable("id") long id, @RequestBody Tutorial tutorial) {
//  Optional<Tutorial> tutorialData = tutorialRepository.findById(id);
//
//  if (tutorialData.isPresent()) {
//    Tutorial _tutorial = tutorialData.get();
//    _tutorial.setTitle(tutorial.getTitle());
//    _tutorial.setDescription(tutorial.getDescription());
//    _tutorial.setPublished(tutorial.isPublished());
//    return new ResponseEntity<>(tutorialRepository.save(_tutorial), HttpStatus.OK);
//  } else {
//    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//  }
//}