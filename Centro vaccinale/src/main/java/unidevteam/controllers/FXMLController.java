package unidevteam.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import unidevteam.util.SceneManager;

public class FXMLController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane centroVaccinaleButton;

    @FXML
    private AnchorPane vaccinatoButton;

    @FXML
    void onClickRegistraCentro(MouseEvent event) {
        new SceneManager().switchToNewScene(event, "registercentro");
    }

    @FXML
    void onClickRegistraVaccinato(MouseEvent event) {
        new SceneManager().switchToNewScene(event, "registervaccinato");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
}