package unidevteam.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import unidevteam.util.SceneManager;

public class FXMLRegistrazioneController2 implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView goBackButton;

    @FXML
    private Button registraCittadinoButton;

    @FXML
    private Label errorMessage;

    @FXML
    private TextField idVaccinazioneTextField;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private PasswordField confermaPasswordTextField;

    @FXML
    private Label errorMessage1;

    @FXML
    void onClickGoBack(MouseEvent event) {
        new SceneManager().switchToNewScene(event, "scene");
    }

    @FXML
    void onClickRegistraCittadino(ActionEvent event) {

    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        
    }
}