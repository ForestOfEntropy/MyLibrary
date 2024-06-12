package com.mahdi.bookManagement.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Books")
public class Book
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String title;
    @Column
    private String author;
    @Column
    private int chaptersRead;
    @Column
    private int chaptersTotal;
    @Column
    private boolean finished = false;

    public Book()
    {

    }
//  Constructor to create a book with all attributes specified.
    public Book( String title, String author, int chaptersRead, int chaptersTotal, boolean finished)
    {
        this.title = title;
        this.author = author;
        this.chaptersRead = chaptersRead;
        this.chaptersTotal = chaptersTotal;
        this.finished = finished;
    }

//  Creates a book with known title, chaptersRead, chaptersTotal and status
    public Book(String title, int chaptersRead, int chaptersTotal, boolean finished)
    {
        setTitle(title);
        setAuthor("Unknown");
        setChaptersRead(chaptersRead);
        setChaptersTotal(chaptersTotal);
        setFinished(finished);
    }

//  Creates a book with known title, chaptersRead and the status
    public Book(String title, int chaptersRead, boolean finished)
    {
        setTitle(title);
        setAuthor("Unknown");
        setChaptersRead(chaptersRead);
        setChaptersTotal(99999);
        setFinished(finished);
    }

//  Creates a book with known title and chaptersRead
    public Book(String title, int chaptersRead)
    {
        setTitle(title);
        setAuthor("Unknown");
        setChaptersRead(chaptersRead);
        setChaptersTotal(99999);
        setFinished(false);
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        if (title == null|| title.trim().isEmpty())
        {
            throw new IllegalArgumentException("Title must contain content");
        }
        this.title = title;
    }

    public String getAuthor()
    {
        return author;
    }

    public void setAuthor(String author)
    {
        if (author == null|| author.trim().isEmpty())
        {
            throw new IllegalArgumentException("Author must contain content");
        }
        this.author = author;
    }

    public int getChaptersRead()
    {
        return chaptersRead;
    }

    public void setChaptersRead(int chaptersRead)
    {
        if (chaptersRead < 0)
        {
            throw new IllegalArgumentException("Chapters can't be negative");
        }
        if (chaptersRead > chaptersTotal)
        {
            throw new IllegalArgumentException("Exceeds the total chapters published");
        }
        this.chaptersRead = chaptersRead;
    }

    public int getChaptersTotal()
    {
        return chaptersTotal;
    }

    public void setChaptersTotal(int chaptersTotal)
    {
        if (chaptersTotal < 0)
        {
            throw new IllegalArgumentException("Chapters can't be negative");
        }
        if (chaptersRead > chaptersTotal)
        {
            throw new IllegalArgumentException("Total chapters cannot be less than chapters read");
        }
        this.chaptersTotal = chaptersTotal;
    }

    public boolean isFinished()
    {
        return finished;
    }

    public void setFinished(boolean finished)
    {
        this.finished = finished;
    }
}
