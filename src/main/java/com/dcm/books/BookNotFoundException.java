package com.dcm.books;



public class BookNotFoundException extends Exception { 
    private static final long serialVersionUID = 1L;//??

	public BookNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}