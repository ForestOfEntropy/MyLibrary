package com.mahdi.bookManagement;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class BookRepository implements DAOInterface
{
    private static final Logger logger = Logger.getLogger(BookRepository.class.getName());
    @Override
    public void save(Book book)
    {
    String sql = "INSERT INTO books (title, author, chapters_read, chapters_total, finished) VALUES (?, ?, ?, ?, ?)";
         try (Connection conn = DatabaseConnection.getConnection();
              PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS))
         {
            stmt.setString(1, book.getTitle());
            stmt.setString(2, book.getAuthor());
            stmt.setInt(3, book.getChaptersRead());
            stmt.setInt(4, book.getChaptersTotal());
            stmt.setBoolean(5, book.isFinished());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next())
            {
                book.setId(rs.getInt(1));
            }
        } catch (SQLException e)
         {
        logger.log(Level.SEVERE, "Failed to save book", e);
        }
    }
        @Override
    public Book getById(int id)
        {
    String sql = "SELECT * FROM books WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql))
        {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next())
            {
                return new Book(
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getInt("chapters_read"),
                        rs.getInt("chapters_total"),
                        rs.getBoolean("finished")
                );
            }
        }
        catch (SQLException e)
        {
            logger.log(Level.SEVERE, "Failed to find book", e);
        }
        return null;
    }

    @Override
    public List<Book> getAll()
    {
        List<Book> books = new ArrayList<>();
        String sql = "Select * FROM books";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql))
        {
            while (rs.next())
            {
                books.add(new Book(
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getInt("chapters_read"),
                        rs.getInt("chapters_total"),
                        rs.getBoolean("finished")
                ));
            }
        }
        catch (SQLException e)
        {
            logger.log(Level.SEVERE, "Failed to load books", e);
        }
        return books;
    }

    @Override
    public void update(Book book)
    {
        String sql = "UPDATE books SET title = ?, author = ?, chapters_read = ?, chapters_total = ?, finished = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql))
        {
            stmt.setString(1, book.getTitle());
            stmt.setString(2, book.getAuthor());
            stmt.setInt(3, book.getChaptersRead());
            stmt.setInt(4, book.getChaptersTotal());
            stmt.setBoolean(5, book.isFinished());
            stmt.setInt(6, book.getId());
            stmt.executeUpdate();
        } catch (SQLException e)
        {
            logger.log(Level.SEVERE, "Failed to update book", e);
        }
    }

    @Override
    public void delete(int id)
    {
        String sql = "DELETE FROM books WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql))
        {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e)
        {
            logger.log(Level.SEVERE, "Deletion failed", e);
        }
    }
    @Override
    public void deleteAll()
    {
        String sql = "DELETE FROM books";
        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql))
        {
            stmt.executeUpdate();
        }
            catch (SQLException e)
            {
                logger.log(Level.SEVERE, "Deletion failed", e);
            }
    }
}



