package com.dcm.books;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}