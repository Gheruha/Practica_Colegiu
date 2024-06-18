package com.example;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class FilialeController implements Initializable {

    @FXML
    Db db = new Db();
    @FXML
    VBox vbox;

    private AnchorPane createPane(String title, String content, Filiale filiala) {
        AnchorPane pane = new AnchorPane();
        pane.getStyleClass().add("rounded-pane");

        Label titleLabel = new Label(title);
        titleLabel.getStyleClass().add("labelContracteTitle");
        AnchorPane.setTopAnchor(titleLabel, 10.0);
        AnchorPane.setLeftAnchor(titleLabel, 10.0);

        Label contentLabel = new Label(content);
        contentLabel.getStyleClass().add("labelContracte");
        AnchorPane.setTopAnchor(contentLabel, 80.0);
        AnchorPane.setLeftAnchor(contentLabel, 10.0);

        Button deleteButton = new Button("Sterge Contract");
        deleteButton.setId("deletePersonalBtn");
        AnchorPane.setLeftAnchor(deleteButton, 580.0);
        AnchorPane.setTopAnchor(deleteButton, 55.0);
        deleteButton.setUserData(filiala.getId());
        deleteButton.setOnAction(event -> {
            String query = "delete from filiale where id_filiala = ?";

            int deleteId = (int) deleteButton.getUserData();
            try {
                db.deleteData(query, deleteId);
                refresh();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        pane.getChildren().addAll(titleLabel, contentLabel, deleteButton);

        return pane;
    }

    public void refresh() throws SQLException {
        vbox.setSpacing(80);
        vbox.getChildren().clear();
        List<Filiale> filiale = db.readDataFiliale();
        for (Filiale filiala : filiale) {
            AnchorPane pane = createPane(filiala.getNume(), filiala.toString(), filiala);
            vbox.getChildren().add(pane);
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
