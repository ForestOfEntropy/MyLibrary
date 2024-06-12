package com.mahdi.bookManagement;

import com.mahdi.bookManagement.model.Book;
import com.mahdi.bookManagement.repository.BookRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;

@Configuration
@Import(com.mahdi.bookManagement.config.ConfigJPA.class)
@ComponentScan(basePackages = "com.mahdi.bookManagement")
@EnableJpaRepositories(basePackages = "com.mahdi.bookManagement.repository")
public class Main {
    public static void main(String[] args) {
        // Load the Spring context
        ApplicationContext context = new AnnotationConfigApplicationContext(Main.class);

        // Get the BookRepository bean from the Spring context
        BookRepository bookRepository = context.getBean(BookRepository.class);

        // Adds a new book
        Book newBook = new Book("Tree of Aeons", "Spaizzzer", 15, 294, false);
        bookRepository.save(newBook);
        System.out.println("Book added with ID: " + newBook.getId());

        // Search a book by ID
        Book book = bookRepository.findById(newBook.getId()).orElse(null);
        if (book != null) {
            System.out.println("Retrieved Book: " + book.getTitle());
        } else {
            System.out.println("Book not found");
        }

        // Gets all books
        List<Book> books = bookRepository.findAll();
        System.out.println("All Books:");
        for (Book b : books) {
            System.out.println(b.getTitle());
        }

        // Updates a book
        if (book != null) {
            book.setChaptersRead(291);
            bookRepository.save(book);
            System.out.println("Updated Book: " + book.getTitle() + " with chapters read: " + book.getChaptersRead());
        }

        // Deletes a book
        if (book != null) {
            bookRepository.delete(book);
            System.out.println("Book deleted with ID: " + book.getId());
        }

        // Deletes all books
        bookRepository.deleteAll();
        System.out.println("All books deleted");
    }
}
