CREATE SCHEMA IF NOT EXISTS book_management;

USE book_management;

CREATE TABLE IF NOT EXISTS books (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255),
    chapters_read INT,
    chapters_total INT,
    finished BOOLEAN
);
