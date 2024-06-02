package com.mahdi.bookManagement;

import java.util.List;

public interface DAOInterface
//Defines the interactions with a DAO for managing books.
{
    void save(Book book);
    Book getById(int id);
    List<Book> getAll();
    void update(Book book);
    void delete(int id);
    void deleteAll();
}
