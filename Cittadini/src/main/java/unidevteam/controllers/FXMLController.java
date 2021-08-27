package unidevteam.controllers;

/*
Put header here


 */

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;


public class FXMLController implements Initializable {

    @FXML
    private Label lb_test;

    @FXML
    private Button btnClicked;

    @FXML
    void onClick(ActionEvent event) {
        if (lb_test.isVisible()) {
            lb_test.setVisible(false);
        } else {
            lb_test.setVisible(true);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       lb_test.setVisible(false);
    }
}