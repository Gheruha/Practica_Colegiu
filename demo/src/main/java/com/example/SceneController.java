package com.example;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class SceneController {

    @FXML
    BorderPane borderPane = new BorderPane();

    @FXML
    public void goToFiliale(ActionEvent event) throws IOException {
        AnchorPane view = FXMLLoader.load(getClass().getResource("filiale.fxml"));
        borderPane.setCenter(view);
    }

    @FXML
    public void goToPersonal(ActionEvent event) throws IOException {
        AnchorPane view = FXMLLoader.load(getClass().getResource("personal.fxml"));
        borderPane.setCenter(view);
    }

    @FXML
    public void goToClienti(ActionEvent event) throws IOException {
        AnchorPane view = FXMLLoader.load(getClass().getResource("clienti.fxml"));
        borderPane.setCenter(view);
    }

    @FXML
    public void goToInfo(ActionEvent event) throws IOException {
        AnchorPane view = FXMLLoader.load(getClass().getResource("info.fxml"));
        borderPane.setCenter(view);
    }

}
