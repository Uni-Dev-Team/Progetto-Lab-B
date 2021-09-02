package unidevteam.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class FXMLHomeController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField ricercaNomeCentroTextField;

    @FXML
    private Button ricercaPerNomeButton;

    @FXML
    private ComboBox<String> comuneComboBox;

    @FXML
    private ComboBox<String> tipoCentroComboBox;

    @FXML
    private Button ricercaPerComuneETipoButton;

    @FXML
    private ListView<String> risultatiRicercaListView;

    @FXML
    private ImageView fotoUtenteImageView;

    @FXML
    private Label nomeUtenteLabel;

    @FXML
    private ImageView fotoCentroImageView;

    @FXML
    private Label nomeCentroLabel;

    @FXML
    private Label indirizzoCentroLabel;

    @FXML
    private Label tipoCentroLabel;

    @FXML
    private Label numeroEventiLabel;

    @FXML
    private Label severitaMediaLabel;

    @FXML
    private Label eventoPiuFrequenteLabel;

    @FXML
    private Button inserisciEventoButton;

    @FXML
    void onClickInserisciEvento(ActionEvent event) {

    }

    @FXML
    void onClickRicercaPerComuneETipoCentro(ActionEvent event) {

    }

    @FXML
    void onClickRicercaPerNome(ActionEvent event) {

    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        
    }

}