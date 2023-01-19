package org.example.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {

    public static Connection getConnection() {
        try {


            return DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/java8",
                    "postgres",
                    "postgres"
            );
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }
}
