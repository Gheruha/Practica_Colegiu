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

public class ContracteController implements Initializable {
    @FXML
    AnchorPane ContracteAnchor;
    @FXML
    ListView<Personal> listViewPersonal;
    @FXML
    ListView<Clienti> listViewClienti;
    @FXML
    Db db = new Db();

    public void goToAddContract() throws IOException, SQLException {
        AnchorPane view = FXMLLoader.load(getClass().getResource("addContract.fxml"));
        setNode(view);
    }

    private void setNode(Node node) throws SQLException {
        ContracteAnchor.getChildren().clear();
        ContracteAnchor.getChildren().add(node);
        AnchorPane.setTopAnchor(node, 0.0);
        AnchorPane.setRightAnchor(node, 0.0);
        AnchorPane.setBottomAnchor(node, 0.0);
        AnchorPane.setLeftAnchor(node, 0.0);

    }

    public void refresh() throws SQLException {
        System.out.println("Refreshing");
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
