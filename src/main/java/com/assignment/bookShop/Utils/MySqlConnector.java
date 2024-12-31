package com.assignment.bookShop.Utils;

import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


@Component
public class MySqlConnector {

    private static final String DATABASE_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/bookshop";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "Eh102353";
    private static final String MAX_POOL = "250";


    private Connection connection;
    private Properties properties;


    private Properties getProperties() {
        if (properties == null) {
            properties = new Properties();
            properties.setProperty("user", DB_USERNAME);
            properties.setProperty("password", DB_PASSWORD);
            properties.setProperty("MaxPooledStatements", MAX_POOL);
        }
        return properties;
    }


    public Connection connect() {
        if (connection == null) {
            try {
                Class.forName(DATABASE_DRIVER);
                connection = DriverManager.getConnection(DB_URL, getProperties());
                System.out.println("Connected to the database successfully!");
            } catch (ClassNotFoundException | SQLException e) {
                System.err.println("Database connection error: " + e.getMessage());
            }
        }
        return connection;
    }


    public void disconnect() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
                System.out.println("Disconnected from the database.");
            } catch (SQLException e) {
                System.err.println("Error disconnecting: " + e.getMessage());
            }
        }
    }
}
