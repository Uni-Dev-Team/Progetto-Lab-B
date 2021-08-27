package unidevteam.controllers;

import unidevteam.util.DBManager;
import unidevteam.util.SceneManager;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class FXMLMetricsController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label serverStatusLabel;

    @FXML
    private Label numOfVaccineCentersLabel;

    @FXML
    private Label numOfCitizensLabel;

    @FXML
    private Label numOfAdverseEventsLabel;

    @FXML
    private Button killServerButton;

    @FXML
    void onClickKillServer(ActionEvent event) {
        // TODO: Chiudere sessione server per ascolto ed esecuzione di metodi
        new SceneManager().switchToNewScene(event, "login");
    }

    @FXML
    void initialize() {
        DBManager dbManager;
        try {
            dbManager = DBManager.getInstance();

            numOfVaccineCentersLabel.setText(Long.toString(dbManager.getCountCentriVaccinali()));
            numOfCitizensLabel.setText(Long.toString(dbManager.getCountCittadini()));
        } catch(Exception e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }
}
