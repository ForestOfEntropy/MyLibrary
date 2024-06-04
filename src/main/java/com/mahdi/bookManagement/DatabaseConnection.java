package com.mahdi.bookManagement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

// This class manages the database connection and initializes it using properties defined in db.properties.
public class DatabaseConnection
{
//  for logging errors and other messages related to database connection.
    private static final Logger logger = Logger.getLogger(DatabaseConnection.class.getName());
//  Name of the properties file containing database connection details.
    private static final String propertiesSource = "db.properties";
//  Properties object to hold the database connection properties.
    private static final Properties properties = new Properties();

    static
    {
//      Try to load the database connection properties from db.properties and also loads the JDBC Driver
        try
        {
            properties.load(DatabaseConnection.class.getClassLoader().getResourceAsStream(propertiesSource));
            Class.forName(properties.getProperty("jdbc.driverClassName"));

//          Try to establish a connection and execute any initialization queries.
            try (Connection conn = getConnection(); Statement stmt = conn.createStatement())
            {
//              Reads SQL script for creating tables from the sql file and executes it.
                String createTableSQL = new String(Objects.requireNonNull(DatabaseConnection.class.getClassLoader()
                        .getResourceAsStream("createTables.sql")).readAllBytes());
                stmt.execute(createTableSQL);
            }
        } catch (Exception e)
        {
            logger.log(Level.SEVERE, "Failed to load database properties or to initialize the database", e);
        }
    }

//  Returns a connection to the database.
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                properties.getProperty("jdbc.url"),
                properties.getProperty("jdbc.username"),
                properties.getProperty("jdbc.password")
        );
    }
}
