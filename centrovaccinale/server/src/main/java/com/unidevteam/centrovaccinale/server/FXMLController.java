package com.unidevteam.centrovaccinale.server;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class FXMLController implements Initializable {
    
    @FXML
    private Button btConnect;

    @FXML
    private TextField tf_username;

    @FXML
    private PasswordField pf_password;

    @FXML
    private Label ib_info;

    @FXML
    void connectToServer(ActionEvent event) {
        
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO:
    }    
}
