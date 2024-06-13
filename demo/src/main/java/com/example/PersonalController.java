package com.example;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
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
    TextField nume, prenume, idnp, oras, telefon, idFiliala;
    @FXML
    Db db = new Db();
    @FXML
    Stage newStage = new Stage();

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
    public void enterPersonal(ActionEvent event) {
        String numeStr = nume.getText();
        String prenumeStr = prenume.getText();
        String idnpStr = idnp.getText();
        String orasStr = oras.getText();
        String telefonStr = telefon.getText();
        int filiala = Integer.parseInt(idFiliala.getText());

        db.insertPersonal(numeStr, prenumeStr, idnpStr, orasStr, telefonStr, filiala);
        refresh();
    }

    @FXML
    public void deleteDataPersonal(ActionEvent event) {
        if (personalListView.getSelectionModel().getSelectedItem() != null) {
            db.deleteData(personalListView.getSelectionModel().getSelectedItem().getId());
            refresh();
        }
    }

    @FXML
    public void refresh() {
        List<Personal> personal = db.readDataPersonal();

        personalListView.getItems().clear();
        personalListView.getItems().addAll(personal);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        refresh();
    }
}
