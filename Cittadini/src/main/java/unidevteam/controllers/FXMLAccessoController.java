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

public class FXMLAccessoController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView goBackButton;

    @FXML
    private TextField emailTextField;

    @FXML
    private Button accediButton;

    @FXML
    private Label errorMessage;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    void onClickAccedi(ActionEvent event) {
        // Prelievo valori campi

        // Validazione campi

        // Task richiesta autenticazione server

        // Handle del risultato
    }

    @FXML
    void onClickGoBack(MouseEvent event) {
        new SceneManager().switchToNewScene(event, "scene");
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        
    }
}

