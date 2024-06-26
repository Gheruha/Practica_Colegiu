package com.example;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;

public class MainController {

    @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;

    // Swith to the app page
    public void switchToApp(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("app.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.setFill(null); // Set the scene to be transparent
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

}