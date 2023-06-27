package com.dcm.books;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Book {
	private @Id @GeneratedValue long id;
	private String title;
	private String author;

	Book() {

	}

	Book(long id, String title, String author) {
		this.id = id;
		this.title = title;
		this.author = author;
	}

	public long getId() {
		return this.id;
	}

	public String getTitle() {
		return this.title;
	}

	public String getAuthor() {
		return this.author;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	@Override
	public boolean equals(Object o) {

		if (this == o)
			return true;
		if (!(o instanceof Book))
			return false;
		Book book = (Book) o;
		return Objects.equals(this.id, book.id) && Objects.equals(this.title, book.title)
				&& Objects.equals(this.author, book.author);
	}

//  hash
//  toString 
}