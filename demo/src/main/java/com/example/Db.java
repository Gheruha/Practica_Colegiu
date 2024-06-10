package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Db {

    private Connection connection;
    private Logger logger = Logger.getLogger(this.getClass().getName());

    // Getting the connection with the Sqlite
    public void getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection("jdbc:sqlite:practica.db");
                logger.info("\n\nConnected to Database ✅");
            }
        } catch (SQLException e) {
            logger.info(e.toString());
        }
    }

    // Creating , deleting tables
    public void createTable() {
        String query = "drop table personal";

        try (PreparedStatement statement = connection.prepareStatement(query);) {
            statement.executeUpdate();
            logger.info("Table created ✅");
        } catch (SQLException e) {
            logger.info(e.toString());
        }
    }

    // Inserting Data in the existing tables
    public void insertData() {
        String query = "";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.executeUpdate();
            logger.info("The data is inserted ✅");
        } catch (SQLException e) {
            logger.info(e.toString());
        }
    }

    // Read data from filiale table and returning list <filiale>
    public List<Filiale> readDataFiliale() {
        getConnection();

        List<Filiale> filiale = new ArrayList<>();
        String query = "select * from filiale";
        try (PreparedStatement statement = connection.prepareStatement(query);
                ResultSet result = statement.executeQuery()) {

            while (result.next()) {
                int id = result.getInt("id_filiala");
                String nume = result.getString("nume");
                String adresa = result.getString("adresa");
                String telefon = result.getString("telefon");

                filiale.add(new Filiale(id, nume, telefon, adresa));
            }

        } catch (SQLException e) {
            logger.info(e.toString());
        }
        return filiale;
    }

    // Read data from personal table and returnig list <personal>
    public List<Personal> readDataPersonal() {
        getConnection();

        List<Personal> personal = new ArrayList<>();
        String query = "select * from personal1";
        try (PreparedStatement statement = connection.prepareStatement(query);
                ResultSet result = statement.executeQuery()) {

            while (result.next()) {
                int id = result.getInt("id");
                String nume = result.getString("nume");
                String prenume = result.getString("prenume");
                String idnp = result.getString("idnp");
                String oras = result.getString("oras");
                String telefon = result.getString("telefon");
                int id_filiala = result.getInt("id_filiala");

                personal.add(new Personal(id, id_filiala, nume, prenume, idnp, oras, telefon));
            }
        }

        catch (SQLException e) {
            logger.info(e.toString());
        }

        return personal;
    }
}
