/**
 * Christian Loschiavo 739894 VA
 * Ivan Giubilei 739892 VA
 * Nicolò Rossi 742626 VA
 * Andrea Ferrario 740485 VA
 */

package cittadini.controllers;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import cittadini.classes.*;
import cittadini.communication.Client;
import cittadini.enumerators.TipoEvento;
import cittadini.util.*;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

/**
 * Classe per gestire elementi grafici
 * Permette di:
 * <ul>
 * <li>Inserire evento avverso</li>
 * <li>Tornare alla pagina precedente</li>
 * </ul>
 */
public class FXMLAggiungiEventoController implements Initializable {

    private static CentroVaccinale centroVaccinale;
    private static Image fotoProfilo;
    DatiExtraCentroVaccinale datiExtraCentroVaccinale;

    public static void setCentroVaccinale(CentroVaccinale c) { centroVaccinale = c; }
    public static void setFotoProfiloURI(Image i) { fotoProfilo = i; }

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
        int severita = (int)severitaEventoSlider.getValue();
        String noteOpzionali = noteOpzionaliTextArea.getText();

        Task<Void> addEventoAvversoTask = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                EventoAvverso eventoAvverso = new EventoAvverso();
                eventoAvverso.setTipoEvento(tipoEvento);
                eventoAvverso.setGradoSeverita(severita);
                eventoAvverso.setNote(noteOpzionali);
                new Client().inserisciEventoAvverso(eventoAvverso, SessionHandler.getUtente().getIdVaccinazione(), centroVaccinale.getId());
                return null;
            }
        };

        // aggiunta evento avverso succeeded
        addEventoAvversoTask.setOnSucceeded(e -> {
            Task<Void> getAdverseEventsData = new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    datiExtraCentroVaccinale = new Client().getDatiSuEventiAvversi(centroVaccinale.getId());
                    return null;
                }
            };
    
            getAdverseEventsData.setOnSucceeded(e1 -> {
                if(datiExtraCentroVaccinale != null) {
                    numeroEventiLabel.setText(Integer.toString(datiExtraCentroVaccinale.getNumeroEventiAvversi()));
                    severitaMediaLabel.setText(String.format("%.1f",datiExtraCentroVaccinale.getGradoSeveritaMedio()));
    
                    if(datiExtraCentroVaccinale.getEventoPiuFrequente() != null)
                        eventoPiuFrequenteLabel.setText(datiExtraCentroVaccinale.getEventoPiuFrequente().getValue());
                    else
                        eventoPiuFrequenteLabel.setText("-");
                }
            });
    
            getAdverseEventsData.setOnFailed(e1 -> {
                numeroEventiLabel.setText("-");
                severitaMediaLabel.setText("-");
                eventoPiuFrequenteLabel.setText("-");
            });
    
            new Thread(getAdverseEventsData).start();
            numeroEventiLabel.setText("-");
            severitaMediaLabel.setText("-");
            eventoPiuFrequenteLabel.setText("-");

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

        // aggiunta evento avverso failed
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

    /**
     * Permette di tornare alla scena precedente
     * @param event
     */
    @FXML
    public void onClickGoBack(MouseEvent event) {
        new SceneManager().switchToNewScene(event, "home");
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        resultMessage.setVisible(false);

        // immagine del centro vaccinale
        File file = new File(String.format("src/main/resources/%s.png", centroVaccinale.getTipologiaCentroVaccinale().getValue().toLowerCase()));
        Image image = new Image(file.toURI().toString());
        fotoCentroImageView.setImage(image);

        if(fotoProfilo != null)
            fotoUtenteImageView.setImage(fotoProfilo);

        // creazione stringa indirizzo
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

        // riempimento combobox
        tipoEventoComboBox.getItems().setAll(
            TipoEvento.MAL_DI_TESTA.getValue(),
            TipoEvento.FEBBRE.getValue(),
            TipoEvento.TACHICARDIA.getValue(),
            TipoEvento.DOLORI_MUSCOLARI_ARTICOLARI.getValue(),
            TipoEvento.CRISI_IPERTENSIVA.getValue(),
            TipoEvento.LINFOADENOPATIA.getValue()
        );
        tipoEventoComboBox.getSelectionModel().select(0);

        Task<Void> getAdverseEventsData = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                datiExtraCentroVaccinale = new Client().getDatiSuEventiAvversi(centroVaccinale.getId());
                return null;
            }
        };

        // ottenimento eventi avversi succeeded
        getAdverseEventsData.setOnSucceeded(e -> {
            if(datiExtraCentroVaccinale != null) {
                numeroEventiLabel.setText(Integer.toString(datiExtraCentroVaccinale.getNumeroEventiAvversi()));
                severitaMediaLabel.setText(String.format("%.1f",datiExtraCentroVaccinale.getGradoSeveritaMedio()));

                if(datiExtraCentroVaccinale.getEventoPiuFrequente() != null)
                    eventoPiuFrequenteLabel.setText(datiExtraCentroVaccinale.getEventoPiuFrequente().getValue());
                else
                    eventoPiuFrequenteLabel.setText("-");
            }
        });

        // ottenimento eventi avversi failed
        getAdverseEventsData.setOnFailed(e -> {
            numeroEventiLabel.setText("-");
            severitaMediaLabel.setText("-");
            eventoPiuFrequenteLabel.setText("-");
        });

        new Thread(getAdverseEventsData).start();
        numeroEventiLabel.setText("-");
        severitaMediaLabel.setText("-");
        eventoPiuFrequenteLabel.setText("-");
    }
}