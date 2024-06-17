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
    @FXML
    TextField tipContract, filialaContract, dataBegin, dataEnd, plataContract;

    public void addNewContract() {
        String query = "insert into contracte(tip_contract , client , personal , filiala , data_begin , data_end , plata , id_personal , id_client) values (? , ? , ? , ? , ? , ? , ? , ? , ?)";
        boolean validation = true;
        if (listViewClienti2.getSelectionModel().getSelectedItem() == null
                || listViewPersonal.getSelectionModel().getSelectedItem() == null
                || tipContract.getText().trim().isEmpty() ||
                filialaContract.getText().trim().isEmpty() || plataContract.getText().trim().isEmpty()
                || dataBegin.getText().trim().isEmpty() || dataEnd.getText().trim().isEmpty()
                || plataContract.getText().trim().isEmpty()) {
            validation = false;
            showAlert("Eroare Validare", "Te rog sa completezi toate campurile.");
        } else {

            String numeClient = listViewClienti2.getSelectionModel().getSelectedItem().getNume()
                    + " " + listViewClienti2.getSelectionModel().getSelectedItem().getPrenume();

            String numePersonal = listViewPersonal.getSelectionModel().getSelectedItem().getNume()
                    + " " + listViewPersonal.getSelectionModel().getSelectedItem().getPrenume();

            int idPersonal = listViewPersonal.getSelectionModel().getSelectedItem().getId();

            int idClient = listViewClienti2.getSelectionModel().getSelectedItem().getId();

            String tipContractStr = tipContract.getText();
            String filialaContractStr = filialaContract.getText();
            String dataBeginStr = dataBegin.getText();
            String dataEndStr = dataEnd.getText();
            String plataContractStr = plataContract.getText();

            if (!filialaContractStr.matches("^\\d+")) {
                validation = false;
                filialaContract.setStyle("-fx-border-color: #ef4444");
                showAlert("Eroare Validare", "ID-ul trebuie sa contina doar numere.");
                filialaContract.clear();
            } else {
                filialaContract.setStyle("");
            }

            if (!dataBeginStr.matches("^\\d{4}-\\d{2}-\\d{2}$")) {
                validation = false;
                dataBegin.setStyle("-fx-border-color: #ef4444");
                showAlert("Eroare Validare", "Data necesita formatul yyyy-mm-dd");
                dataBegin.clear();
            } else {
                dataBegin.setStyle("");
            }

            if (!dataEndStr.matches("^\\d{4}-\\d{2}-\\d{2}$")) {
                validation = false;
                dataEnd.setStyle("-fx-border-color: #ef4444");
                showAlert("Eroare Validare", "Data necesita formatul yyyy-mm-dd");
                dataEnd.clear();
            } else {
                dataEnd.setStyle("");
            }
            if (!plataContractStr.matches("^\\d+")) {
                validation = false;
                plataContract.setStyle("-fx-border-color: #ef4444");
                showAlert("Eroare Validare", "Plata trebuie sa contina doar numere.");
                plataContract.clear();
            } else {
                plataContract.setStyle("");
            }

            if (validation) {
                double plata = Double.valueOf(plataContractStr);
                db.insertData(query, tipContractStr, numeClient, numePersonal, filialaContractStr, dataBeginStr,
                        dataEndStr, plata, idPersonal, idClient);

                tipContract.clear();
                filialaContract.clear();
                dataBegin.clear();
                dataEnd.clear();
                plataContract.clear();
            }
        }
    }

    public void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText("");
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void refresh() throws SQLException {
        List<Personal> personal = db.readDataPersonal();
        List<Clienti> clienti = db.readDataClienti();
        listViewClienti2.getItems().clear();
        listViewPersonal.getItems().clear();
        listViewClienti2.getItems().addAll(clienti);
        listViewPersonal.getItems().addAll(personal);
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
