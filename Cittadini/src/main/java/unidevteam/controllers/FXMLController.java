package unidevteam.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import unidevteam.communication.Client;
import unidevteam.util.SceneManager;

public class FXMLController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane accediButton;

    @FXML
    private AnchorPane registratiButton;

    @FXML
    private Label ospiteButton;

    @FXML
    void onClickAccessoCittadino(MouseEvent event) {
        new SceneManager().switchToNewScene(event, "accesso");
    }

    @FXML
    void onClickAccessoOspite(MouseEvent event) {
        new SceneManager().switchToNewScene(event, "home");
    }

    @FXML
    void onClickRegistraCittadino(MouseEvent event) {
        new SceneManager().switchToNewScene(event, "registrazione1");
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        new Client();
    }
}