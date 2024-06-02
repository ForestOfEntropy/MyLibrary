package com.mahdi.bookManagement;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        BookRepository bookRepository = new BookRepository();

        // Add a book
        Book newBook = new Book("Tree of Aeons", "Spaizzzer", 15, 294, false);
        bookRepository.save(newBook);
        System.out.println("Book added with ID: " + newBook.getId());

        // Get a book by ID
        Book book = bookRepository.getById(newBook.getId());
        System.out.println("Retrieved Book: " + book.getTitle());

        // Get all books
        List<Book> books = bookRepository.getAll();
        System.out.println("All Books:");
        for (Book b : books) {
            System.out.println(b.getTitle());
        }

        // Update a book
        book.setChaptersRead(291);
        bookRepository.update(book);
        System.out.println("Updated Book: " + book.getTitle() + " with chapters read: " + book.getChaptersRead());

        // Delete a book
        bookRepository.delete(book.getId());
        System.out.println("Book deleted with ID: " + book.getId());
        bookRepository.deleteAll();
        System.out.println("All books deleted");
    }
}