# Book Management System

This project is a simple book management system implemented in Java. It provides functionalities to manage books such as adding, updating, deleting, and retrieving books from a database.

## Features

- Add a new book with title, author, number of chapters read, total chapters, and finished status.
- Update existing book information.
- Delete a book from the system.
- Retrieve a list of all books in the system.
- Connects to an H2 database for data storage.

1 Ensure you have Java and Maven installed on your system.

2. Set up the database connection properties in the `db.properties` file located in the `src/main/resources` directory. Modify the following properties as per your database setup:
jdbc.url=jdbc:h2:~/bookManagement;AUTO_SERVER=TRUE;DB_CLOSE_DELAY=-1
jdbc.username=your-username
jdbc.password=your-password
jdbc.driverClassName=org.h2.Driver

3. Build the project using Maven.
4. Run the application.

## Usage

- Upon running the application, you can use it to perform various book management operations such as adding, updating, deleting, and retrieving books.

## Contributing

Contributions are welcome! If you find any bugs or have suggestions for improvements, feel free to open an issue or create a pull request.
