package com.example;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class SceneController implements Initializable {
    @FXML
    Stage stage = new Stage();
    @FXML
    BorderPane borderPane = new BorderPane();
    @FXML
    ListView<Filiale> listView = new ListView<>();
    @FXML
    ListView<Personal> personalListView = new ListView<>();
    @FXML
    Db db = new Db();
    @FXML
    Button filialeBtn, personalBtn, clientiBtn, addPersonalBtn, enterPersonalBtn, contracteBtn;
    @FXML
    private List<Button> buttons = new ArrayList<>();
    @FXML
    TextField nume;
    @FXML
    Pane Menu;

    // Navigate between scenes
    @FXML
    public void goToFiliale(ActionEvent event) throws IOException {
        setButtonsColor(filialeBtn);
        AnchorPane view = FXMLLoader.load(getClass().getResource("filiale.fxml"));
        borderPane.setCenter(view);
    }

    @FXML
    public void goToPersonal(ActionEvent event) throws IOException {
        setButtonsColor(personalBtn);
        AnchorPane view = FXMLLoader.load(getClass().getResource("personal.fxml"));
        borderPane.setCenter(view);
    }

    @FXML
    public void goToClienti(ActionEvent event) throws IOException {
        setButtonsColor(clientiBtn);
        AnchorPane view = FXMLLoader.load(getClass().getResource("clienti.fxml"));
        borderPane.setCenter(view);
    }

    @FXML
    public void goToContracte(ActionEvent event) throws IOException {
        setButtonsColor(contracteBtn);
        AnchorPane view = FXMLLoader.load(getClass().getResource("contracte.fxml"));
        borderPane.setCenter(view);
    }

    // Change the color of the button you clicked
    @FXML
    public void setButtonsColor(Button buttonID) {
        for (Button button : buttons) {
            if (button == buttonID) {
                button.setStyle("-fx-background-color: #3b82f6");
            } else {
                button.setStyle("");
            }
        }
    }

    // Refreshing , for changes on the UI
    @FXML
    public void refresh() {
        List<Filiale> filiale = db.readDataFiliale();

        listView.getItems().clear();
        listView.getItems().addAll(filiale);
    }

    // Initialize the necesarry stuff
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        buttons.add(filialeBtn);
        buttons.add(personalBtn);
        buttons.add(clientiBtn);
        buttons.add(contracteBtn);

        refresh();
    }
}
