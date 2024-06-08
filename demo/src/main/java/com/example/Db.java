package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

public class Db {

    private Connection connection;
    private Logger logger = Logger.getLogger(this.getClass().getName());

    public void getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection("jdbc:sqlite:practica.db");
                logger.info("Connected to Database");
            }
        } catch (SQLException e) {
            logger.info(e.toString());
        }
    }
}
