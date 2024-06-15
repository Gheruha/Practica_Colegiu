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
    List<Personal> personal = new ArrayList<>();
    List<Clienti> clienti = new ArrayList<>();

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

    // Closing the connection
    public void closeConnection() throws SQLException {
        if (connection != null || !connection.isClosed()) {
            connection.close();
        }
    }

    // Insert data in the personal table
    public void insertData(String query, Object... parameters) {
        getConnection();

        try (PreparedStatement statement = connection.prepareStatement(query);) {
            for (int i = 0; i < parameters.length; i++) {
                statement.setObject(i + 1, parameters[i]);
            }
            statement.executeUpdate();
            logger.info("Data inserted in personal table ✅");

        } catch (SQLException e) {
            logger.info(e.toString());
        }
    }

    // Inserting Data in the existing tables
    public void deleteData(String query, int id) {

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
            logger.info("The data is Deleted ✅");
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
    public List<Personal> readDataPersonal() throws SQLException {
        getConnection();
        String query = "select * from personal1 order by nume , prenume";
        try (PreparedStatement statement = connection.prepareStatement(query);
                ResultSet result = statement.executeQuery()) {
            personal.clear();
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
        } catch (SQLException e) {
            logger.info(e.toString());
        }

        return personal;
    }

    // Read data from clienti table and returnig list <clienti>
    public List<Clienti> readDataClienti() throws SQLException {
        getConnection();
        String query = "select * from clienti order by nume , prenume";
        try (PreparedStatement statement = connection.prepareStatement(query);
                ResultSet result = statement.executeQuery()) {
            clienti.clear();
            while (result.next()) {
                int id = result.getInt("id");
                String nume = result.getString("nume");
                String prenume = result.getString("prenume");
                String idnp = result.getString("idnp");
                String oras = result.getString("oras");
                String telefon = result.getString("telefon");

                clienti.add(new Clienti(id, nume, prenume, idnp, oras, telefon));
            }
        } catch (SQLException e) {
            logger.info(e.toString());
        }

        return clienti;
    }

}
