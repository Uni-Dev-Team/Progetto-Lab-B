package unidevteam.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import unidevteam.classes.CentroVaccinale;
import unidevteam.communication.Client;
import unidevteam.enumerators.TipoEvento;
import unidevteam.classes.EventoAvverso;
import unidevteam.util.SceneManager;
import unidevteam.util.SessionHandler;

public class FXMLAggiungiEventoController implements Initializable {

    private static CentroVaccinale centroVaccinale;

    public static void setCentroVaccinale(CentroVaccinale c) { centroVaccinale = c; }

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

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
    private ComboBox<String> tipoEventoComboBox;

    @FXML
    private Slider severitaEventoSlider;

    @FXML
    private TextArea noteOpzionaliTextArea;

    @FXML
    private Button inserisciEventoButton;

    @FXML
    private ImageView goBackButton;

    @FXML
    private Label resultMessage;

    @FXML
    void onClickInserisciEvento(ActionEvent event) {
        TipoEvento tipoEvento = TipoEvento.valueFromString(tipoEventoComboBox.getSelectionModel().getSelectedItem());
        System.out.println(tipoEvento.getValue());
        int severita = (int)severitaEventoSlider.getValue();
        String noteOpzionali = noteOpzionaliTextArea.getText();

        Task<Void> addEventoAvversoTask = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                EventoAvverso eventoAvverso = new EventoAvverso();
                eventoAvverso.setTipoEvento(tipoEvento);
                eventoAvverso.setGradoSeverita(severita);
                eventoAvverso.setNote(noteOpzionali);
                new Client().inserisciEventoAvverso(eventoAvverso, SessionHandler.getUtente().getIdVaccinazione());
                return null;
            }
        };

        addEventoAvversoTask.setOnSucceeded(e -> {
            inserisciEventoButton.setText("Inserisci evento avverso");
            inserisciEventoButton.setDisable(false);
    
            tipoEventoComboBox.setDisable(false);
            severitaEventoSlider.setDisable(false);
            noteOpzionaliTextArea.setDisable(false);

            tipoEventoComboBox.getSelectionModel().select(0);
            severitaEventoSlider.setValue(1);
            noteOpzionaliTextArea.setText(null);

            resultMessage.setText("Evento avverso inserito con successo!");
            resultMessage.setTextFill(Color.GREEN);
            resultMessage.setVisible(true);
        });

        addEventoAvversoTask.setOnFailed(e -> {
            inserisciEventoButton.setText("Inserisci evento avverso");
            inserisciEventoButton.setDisable(false);
    
            tipoEventoComboBox.setDisable(false);
            severitaEventoSlider.setDisable(false);
            noteOpzionaliTextArea.setDisable(false);

            tipoEventoComboBox.getSelectionModel().select(0);
            severitaEventoSlider.setValue(1);
            noteOpzionaliTextArea.setText(null);

            resultMessage.setText("Errore nell'inserimento, ritenta.");
            resultMessage.setTextFill(Color.RED);
            resultMessage.setVisible(true);
        });

        new Thread(addEventoAvversoTask).start();
        inserisciEventoButton.setText("Caricamento...");
        inserisciEventoButton.setDisable(true);

        tipoEventoComboBox.setDisable(true);
        severitaEventoSlider.setDisable(true);
        noteOpzionaliTextArea.setDisable(true);
    }

    @FXML
    public void onClickGoBack(MouseEvent event) {
        new SceneManager().switchToNewScene(event, "home");
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        resultMessage.setVisible(false);
        nomeCentroLabel.setText(centroVaccinale.getNome());
        String indirizzoCentro = String.format(
                            "%s, %s %s %s - %s (%s)", 
                            centroVaccinale.getComune(), 
                            centroVaccinale.getQualificatoreIndirizzo().getValue(),
                            centroVaccinale.getNomeIndirizzo(),
                            centroVaccinale.getNumeroCivico(),
                            centroVaccinale.getCAP(),
                            centroVaccinale.getProvincia()
                        );
        indirizzoCentroLabel.setText(indirizzoCentro);
        tipoCentroLabel.setText(centroVaccinale.getTipologiaCentroVaccinale().getValue());

        nomeUtenteLabel.setText(String.format("%s %s", SessionHandler.getUtente().getNome(), SessionHandler.getUtente().getCognome()));

        tipoEventoComboBox.getItems().setAll(
            TipoEvento.MAL_DI_TESTA.getValue(),
            TipoEvento.FEBBRE.getValue(),
            TipoEvento.TACHICARDIA.getValue(),
            TipoEvento.DOLORI_MUSCOLARI_ARTICOLARI.getValue(),
            TipoEvento.CRISI_IPERTENSIVA.getValue(),
            TipoEvento.LINFOADENOPATIA.getValue()
        );
        tipoEventoComboBox.getSelectionModel().select(0);
    }
}