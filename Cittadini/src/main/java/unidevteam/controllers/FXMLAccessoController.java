package unidevteam.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import unidevteam.classes.Cittadino;
import unidevteam.communication.Client;
import unidevteam.util.SceneManager;
import unidevteam.util.SessionHandler;

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
        String email = emailTextField.getText();
        String plainPassword = passwordTextField.getText();

        // Validazione campi
        if(!email.isBlank() && !plainPassword.isBlank()) {

            // Task richiesta autenticazione server
            Task<Cittadino> authTask = new Task<Cittadino>(){
                @Override
                protected Cittadino call() throws Exception {
                    return new Client().autenticaUtente(email, plainPassword);
                }
            };

            authTask.setOnSucceeded(e -> {
                try {
                    Cittadino res = authTask.get();
                    if(res != null) { 
                        SessionHandler.setUtente(res);
                        // Cambio pagina in homepage
                        new SceneManager().switchToNewScene(event, "home");
                    } else {
                        errorMessage.setVisible(true);
                        errorMessage.setText("Accesso fallito, riprova.");
                    }
                    
                } catch (InterruptedException | ExecutionException e1) {
                    errorMessage.setVisible(true);
                    errorMessage.setText("Accesso fallito, riprova.");
                    e1.printStackTrace();
                }

                emailTextField.setDisable(false);
                passwordTextField.setDisable(false);
    
                accediButton.setText("Accedi");
                accediButton.setDisable(false);
            });

            authTask.setOnFailed(e -> {
                errorMessage.setVisible(true);
                errorMessage.setText("Accesso fallito, riprova.");

                emailTextField.setDisable(false);
                passwordTextField.setDisable(false);
    
                accediButton.setText("Accedi");
                accediButton.setDisable(false);
            });

            new Thread(authTask).start();

            emailTextField.setDisable(true);
            passwordTextField.setDisable(true);

            accediButton.setText("Caricamento...");
            accediButton.setDisable(true);

        } else {
            errorMessage.setVisible(true);
            errorMessage.setText("Compila tutti i campi.");
        }
    }

    @FXML
    void onClickGoBack(MouseEvent event) {
        new SceneManager().switchToNewScene(event, "scene");
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        
    }
}