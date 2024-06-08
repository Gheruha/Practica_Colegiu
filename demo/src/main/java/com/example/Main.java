package com.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    private Db databaseObj = new Db();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
        Scene scene = new Scene(root, 1500, 900, Color.DARKGREY);
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

        // Stage costumization
        stage.setTitle("Asig Management App");

        // Get the connection with the database
        databaseObj.getConnection();

        stage.setScene(scene);
        stage.show();
    }

}