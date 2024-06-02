package com.mahdi.bookManagement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseConnection
{

    private static final Logger logger = Logger.getLogger(DatabaseConnection.class.getName());
    private static final String propertiesSource = "db.properties";
    private static final Properties properties = new Properties();

    static
    {
        try
        {
            properties.load(DatabaseConnection.class.getClassLoader().getResourceAsStream(propertiesSource));
            Class.forName(properties.getProperty("jdbc.driverClassName"));

            try (Connection conn = getConnection(); Statement stmt = conn.createStatement())
            {
                String createTableSQL = new String(Objects.requireNonNull(DatabaseConnection.class.getClassLoader()
                        .getResourceAsStream("createTables.sql")).readAllBytes());
                stmt.execute(createTableSQL);
            }
        } catch (Exception e)
        {
            logger.log(Level.SEVERE, "Failed to load database properties or to initialize the database", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                properties.getProperty("jdbc.url"),
                properties.getProperty("jdbc.username"),
                properties.getProperty("jdbc.password")
        );
    }
}
