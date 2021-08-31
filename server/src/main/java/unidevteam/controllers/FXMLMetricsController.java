package unidevteam.controllers;

import unidevteam.communication.*;
import unidevteam.util.DBManager;
import unidevteam.util.SceneManager;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class FXMLMetricsController {

    ServerCentroVaccinale rmiServerCentroVaccinale;
    ServerCittadino rmiServerCittadini;

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
        try {
            rmiServerCentroVaccinale.exit();
            rmiServerCittadini.exit();
            new SceneManager().switchToNewScene(event, "login");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void initialize() {
        DBManager dbManager;
        try {
            dbManager = DBManager.getInstance();
            numOfVaccineCentersLabel.setText(Long.toString(dbManager.getCountCentriVaccinali()));
            numOfCitizensLabel.setText(Long.toString(dbManager.getCountCittadini()));

            rmiServerCentroVaccinale = new ServerCentroVaccinale();
            rmiServerCittadini = new ServerCittadino();
        
        } catch(Exception e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }
}
