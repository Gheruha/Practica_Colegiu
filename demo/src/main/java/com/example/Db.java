package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

public class Db {

    private Connection connection;
    private Logger logger = Logger.getLogger(this.getClass().getName());

    public void getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection("jdbc:sqlite:practica.db");
                logger.info("\n\nConnected to Database ✅");
                createTable();
                readDataFiliale();
            }
        } catch (SQLException e) {
            logger.info(e.toString());
        }
    }

    private void createTable() {
        String query = "CREATE TABLE IF NOT EXISTS filiale ( id_filiala INTEGER PRIMARY KEY AUTOINCREMENT, nume TEXT DEFAULT NULL, adresa TEXT DEFAULT NULL,  telefon TEXT DEFAULT NULL );";

        try (PreparedStatement statement = connection.prepareStatement(query);) {
            statement.executeUpdate();
            logger.info("Table created ✅");
            // insertFiliale();
        } catch (SQLException e) {
            logger.info(e.toString());
        }
    }

    private void insertFiliale() {
        String query = "insert into filiale(nume , adresa , telefon) values('MoldaSig' , 'Soseaua pandurilor' , '022-99-23-43');";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.executeUpdate();
            logger.info("Inserted data in Filiale ✅");
        } catch (SQLException e) {
            logger.info(e.toString());
        }
    }

    private void readDataFiliale() {
        String query = "select * from filiale";
        try (PreparedStatement statement = connection.prepareStatement(query);
                ResultSet result = statement.executeQuery()) {

            while (result.next()) {
                int idFiliala = result.getInt("id_filiala");
                String nume = result.getString("nume");
                String adresa = result.getString("adresa");
                String telefon = result.getString("telefon");

                System.out.println("Filiala Details:");
                System.out.println("ID: " + idFiliala);
                System.out.println("Nume: " + nume);
                System.out.println("Adresa: " + adresa);
                System.out.println("Telefon: " + telefon);
                System.out.println("----------");
            }

        } catch (SQLException e) {
            logger.info(e.toString());
        }
    }
}
