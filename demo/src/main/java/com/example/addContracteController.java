package com.example;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

public class addContracteController implements Initializable {
    @FXML
    AnchorPane ContracteAnchor;
    @FXML
    ListView<Personal> listViewPersonal;
    @FXML
    ListView<Clienti> listViewClienti2;
    @FXML
    Db db = new Db();

    public void refresh() throws SQLException {
        List<Personal> personal = db.readDataPersonal();
        List<Clienti> clienti = db.readDataClienti();
        listViewClienti2.getItems().clear();
        listViewPersonal.getItems().clear();
        listViewClienti2.getItems().addAll(clienti);
        listViewPersonal.getItems().addAll(personal);
    }

    public void addNewContract() {
        if (listViewClienti2.getSelectionModel().getSelectedItem() != null
                && listViewPersonal.getSelectionModel().getSelectedItem() != null) {

            String numeClient = listViewClienti2.getSelectionModel().getSelectedItem().getNume()
                    + " " + listViewClienti2.getSelectionModel().getSelectedItem().getPrenume();

            String numePersonal = listViewPersonal.getSelectionModel().getSelectedItem().getNume()
                    + " " + listViewPersonal.getSelectionModel().getSelectedItem().getPrenume();

            System.out.println(numeClient);
            System.out.println(numePersonal);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            refresh();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
