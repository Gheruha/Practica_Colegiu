package com.example;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
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
    @FXML
    VBox vbox;
    @FXML
    Contracte contracte;

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

    private AnchorPane createPane(String title, String content, Contracte contract) {
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
        AnchorPane.setTopAnchor(deleteButton, 105.0);
        deleteButton.setUserData(contract.getId());
        deleteButton.setOnAction(event -> {
            String query = "delete from contracte where id = ?";

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
        List<Contracte> contracte = db.readDataContracte();
        vbox.setSpacing(80);
        vbox.getChildren().clear();
        for (Contracte contract : contracte) {
            AnchorPane pane = createPane(contract.getTipContract(),
                    contract.toString(), contract);

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
