package unidevteam.controllers;

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

    }

    @FXML
    void initialize() {
        
    }
}
