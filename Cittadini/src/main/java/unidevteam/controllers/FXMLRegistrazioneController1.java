package unidevteam.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import unidevteam.util.SceneManager;

public class FXMLRegistrazioneController1 implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView goBackButton;

    @FXML
    private TextField emailTextField;

    @FXML
    private Button avantiButton;

    @FXML
    private Label errorMessage;

    @FXML
    private TextField codiceFiscaleTextField;

    @FXML
    private TextField nomeTextField;

    @FXML
    private TextField cognomeTextField;

    @FXML
    void onClickAvanti(ActionEvent event) {

    }

    @FXML
    void onClickGoBack(MouseEvent event) {
        new SceneManager().switchToNewScene(event, "scene");
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        
    }
}