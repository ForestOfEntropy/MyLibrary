package com.mahdi.bookManagement.repository;

import com.mahdi.bookManagement.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {
}
