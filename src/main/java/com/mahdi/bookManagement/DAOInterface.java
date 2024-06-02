package com.mahdi.bookManagement;

import java.util.List;

public interface DAOInterface
{
    void save(Book book);
    Book getById(int id);
    List<Book> getAll();
    void update(Book book);
    void delete(int id);
    void deleteAll();
}
