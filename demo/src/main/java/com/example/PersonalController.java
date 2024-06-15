package com.example;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class PersonalController implements Initializable {
    @FXML
    ListView<Personal> personalListView = new ListView<>();
    @FXML
    TextField nume, prenume, idnp, oras, telefon, idFiliala, numeTest;
    @FXML
    Db db = new Db();
    @FXML
    Stage newStage = new Stage();
    @FXML
    Button deletePersonalBtn;

    @FXML
    public void openAddPersonal(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("addPersonal.fxml"));
        Scene scene = new Scene(root, 600, 800, Color.DARKGREY);
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        newStage.setScene(scene);
        newStage.setTitle("Add Personal Form");
        newStage.setResizable(false);
        newStage.show();
    }

    @FXML
    public void enterPersonal(ActionEvent event) throws SQLException {
        boolean validation = true;
        if (nume.getText().trim().isEmpty() || prenume.getText().trim().isEmpty()
                || prenume.getText().trim().isEmpty() || idnp.getText().trim().isEmpty()
                || oras.getText().trim().isEmpty() || idFiliala.getText().trim().isEmpty()) {
            showAlert("Eroare Validare", "Te rog sa umpli toate campurile.");
        } else {
            String numeStr = nume.getText();
            String prenumeStr = prenume.getText();
            String idnpStr = idnp.getText();
            String orasStr = oras.getText();
            String telefonStr = telefon.getText();
            String filiala = idFiliala.getText();

            // Validation
            if (!idnpStr.matches("^\\d{13}$")) {
                validation = false;
                idnp.setStyle("-fx-border-color: #ef4444");
                showAlert("Eroare Validare", "Idnp-ul trebuie sa contina 13 cifre.");
            } else {
                idnp.setStyle("");
            }
            if (!telefonStr.matches("^([0-9]{3}+(-[0-9]+)+)$")) {
                validation = false;
                telefon.setStyle("-fx-border-color: #ef4444");
                showAlert("Eroare Validare", "Formatul telefonului trebuie sa fie: xxx-xx-xxxx.");
            } else {
                telefon.setStyle("");
            }
            if (!filiala.matches("^([0-9]+)$")) {
                validation = false;
                idFiliala.setStyle("-fx-border-color: #ef4444");
                showAlert("Eroare Validare", "ID-ul trebuie sa contina doar numere.");
            } else {

                idFiliala.setStyle("");
            }

            // Clear the text inputs
            nume.clear();
            prenume.clear();
            idnp.clear();
            oras.clear();
            telefon.clear();
            idFiliala.clear();

            // Inserting the data in the database & refreshing
            if (validation) {
                int idF = Integer.parseInt(filiala);
                db.insertPersonal(numeStr, prenumeStr, idnpStr, orasStr, telefonStr, idF);
            }
            refresh();
        }

    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText("");
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    public void deleteDataPersonal(ActionEvent event) throws SQLException {
        if (personalListView.getSelectionModel().getSelectedItem() != null) {
            db.deleteData(personalListView.getSelectionModel().getSelectedItem().getId());
            refresh();
        }
    }

    @FXML
    public void refresh() throws SQLException {
        List<Personal> personal = db.readDataPersonal();
        System.out.println("Refreshing ...");
        personalListView.getItems().clear();
        personalListView.getItems().addAll(personal);
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
