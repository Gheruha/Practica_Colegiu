package com.example;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class ClientiController implements Initializable {
    @FXML
    ListView<Clienti> listViewClienti;
    @FXML
    TextField numeClient, prenumeClient, idnpClient, orasClient, telefonClient;
    @FXML
    Db db = new Db();

    @FXML
    public void enterClienti(ActionEvent event) throws SQLException {
        String query = "insert into clienti(nume , prenume , idnp , oras , telefon) values(? , ? , ? , ? , ?)";
        boolean validation = true;
        if (numeClient.getText().trim().isEmpty() || prenumeClient.getText().trim().isEmpty()
                || idnpClient.getText().trim().isEmpty()
                || orasClient.getText().trim().isEmpty() || telefonClient.getText().trim().isEmpty()) {
            showAlert("Eroare Validare", "Te rog sa completezi toate campurile.");
            validation = false;
        } else {
            String numeStr = numeClient.getText();
            String prenumeStr = prenumeClient.getText();
            String idnpStr = idnpClient.getText();
            String orasStr = orasClient.getText();
            String telefonStr = telefonClient.getText();

            // Validation
            if (!idnpStr.matches("^\\d{13}$")) {
                validation = false;
                idnpClient.setStyle("-fx-border-color: #ef4444");
                showAlert("Eroare Validare", "Idnp-ul trebuie sa contina 13 cifre.");
                idnpClient.clear();
            } else {
                idnpClient.setStyle("");
            }
            if (!telefonStr.matches("^([0-9]{3}+(-[0-9]+)+)$")) {
                validation = false;
                telefonClient.setStyle("-fx-border-color: #ef4444");
                showAlert("Eroare Validare", "Formatul telefonului trebuie sa fie: xxx-xx-xxxx.");
                telefonClient.clear();
            } else {
                telefonClient.setStyle("");
            }

            // Inserting the data in the database & refreshing
            if (validation) {
                db.insertData(query, numeStr, prenumeStr, idnpStr, orasStr, telefonStr);
                numeClient.clear();
                prenumeClient.clear();
                idnpClient.clear();
                orasClient.clear();
                telefonClient.clear();
            }
            refresh();
        }

    }

    public void deleteData() throws SQLException {
        String query = "delete from clienti where id = ?";
        if (listViewClienti.getSelectionModel().getSelectedItem() != null) {
            db.deleteData(query, listViewClienti.getSelectionModel().getSelectedItem().getId());
            refresh();
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
        List<Clienti> clienti = db.readDataClienti();
        listViewClienti.getItems().clear();
        listViewClienti.getItems().addAll(clienti);
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
