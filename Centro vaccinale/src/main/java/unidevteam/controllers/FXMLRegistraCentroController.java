package unidevteam.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.Action;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import unidevteam.enumerators.*;
import unidevteam.util.JsonReader;
import unidevteam.util.SceneManager;

public class FXMLRegistraCentroController implements Initializable {

    JsonReader jsonReader;
    List<String> province;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField nomeCentroTextField;

    @FXML
    private Button registerButton;

    @FXML
    private TextField nomeStradaTextField;

    @FXML
    private ComboBox<String> qualificatoreIndirizzoComboBox;

    @FXML
    private TextField numeroAbitazioneTextField;

    @FXML
    private ComboBox<String> provinciaComboBox;

    @FXML
    private ComboBox<String> comuneComboBox;

    @FXML
    private ComboBox<String> tipologiaCentroComboBox;

    @FXML
    private ImageView goBackButton;

    @FXML
    void onClickRegistraCentro(ActionEvent event) {

    }

    @FXML
    void onClickGoBack(MouseEvent event) {
        new SceneManager().switchToNewScene(event, "scene");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        jsonReader = new JsonReader();

        provinciaComboBox.setDisable(true);
        comuneComboBox.setDisable(true);
        Task<Void> getProvinceTask = new Task<Void>() {
            protected Void call() throws Exception {
                province = jsonReader.getProvince();
                return null;
            }
        };

        getProvinceTask.setOnSucceeded(e -> {
            provinciaComboBox.getItems().addAll(province);
            provinciaComboBox.setDisable(false);
        });

        getProvinceTask.setOnFailed(e -> {
            // Errore
        });

        new Thread(getProvinceTask).start();

        qualificatoreIndirizzoComboBox.getItems().addAll(
            QualificatoreIndirizzo.VIA.toString().toLowerCase(),
            QualificatoreIndirizzo.VIALE.toString().toLowerCase(),
            QualificatoreIndirizzo.PIAZZA.toString().toLowerCase()
        );

        tipologiaCentroComboBox.getItems().addAll(
            TipologiaCentroVaccinale.OSPEDALIERO.toString().toLowerCase(),
            TipologiaCentroVaccinale.AZIENDALE.toString().toLowerCase(),
            TipologiaCentroVaccinale.HUB.toString().toLowerCase()
        );
    }
}
