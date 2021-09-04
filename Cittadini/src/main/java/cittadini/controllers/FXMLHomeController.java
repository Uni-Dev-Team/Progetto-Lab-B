/**
 * Christian Loschiavo 739894 VA
 * Ivan Giubilei 739892 VA
 * Nicolò Rossi 742626 VA
 * Andrea Ferrario 740485 VA
 */

package cittadini.controllers;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;

import cittadini.classes.*;
import cittadini.communication.Client;
import cittadini.enumerators.TipologiaCentroVaccinale;
import cittadini.util.*;
import javafx.beans.value.*;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.input.MouseEvent;

/**
 * Classe per gestire elementi grafici
 * Permette:
 * <ul>
 * <li>Ricerca centro vaccinale per nome</li>
 * <li>Ricerca centro vaccinale per comune e tipo centro</li>
 * <li>Effettuare il logout</li>
 * <li>Aggiungere evento avverso</li>
 * <li>Ottenere comuni</li>
 * </ul>
 */
public class FXMLHomeController implements Initializable {

    JSONReader jsonReader;
    List<String> comuni;
    List<CentroVaccinale> centriVaccinali;
    List<CentroVaccinale> ricercaCentriVaccinali;
    DatiExtraCentroVaccinale datiExtraCentroVaccinale;

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
    private ListView<CentroVaccinale> risultatiRicercaListView;

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
    private ImageView logoutButton;

    /**
     * Ricerca per nome di un centro vaccinale
     * @param event
     */
    @FXML
    void onClickRicercaPerNome(ActionEvent event) {
        String nomeCentro = ricercaNomeCentroTextField.getText();

        Task<Void> getCentriVaccinaliTask = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                centriVaccinali = new Client().cercaCentroVaccinale(nomeCentro);
                return null;
            }
        };
        // ricerca succeeded
        getCentriVaccinaliTask.setOnSucceeded(e -> {
            if(centriVaccinali != null) {
                if(ricercaCentriVaccinali == null) ricercaCentriVaccinali = new ArrayList<CentroVaccinale>();
                ricercaCentriVaccinali.clear();
                for(CentroVaccinale cv: centriVaccinali) ricercaCentriVaccinali.add(cv);
                risultatiRicercaListView.getItems().setAll(ricercaCentriVaccinali);
            }
            ricercaPerNomeButton.setText("Cerca");
            ricercaPerNomeButton.setDisable(false);
            ricercaNomeCentroTextField.setDisable(false);
        });

        // ricerca failed
        getCentriVaccinaliTask.setOnFailed(e -> {
            System.err.println("Errore durante il retrieve dei centri vaccinali");
            ricercaPerNomeButton.setText("Cerca");
            ricercaPerNomeButton.setDisable(false);
            ricercaNomeCentroTextField.setDisable(false);
        });

        new Thread(getCentriVaccinaliTask).start();
        ricercaPerNomeButton.setText("...");
        ricercaPerNomeButton.setDisable(true);
        ricercaNomeCentroTextField.setDisable(true);
    }

    /**
     * Ricerca per comune e tipo di un centro vaccinale
     * @param event
     */
    @FXML
    void onClickRicercaPerComuneETipoCentro(ActionEvent event) {
        String comune = comuneComboBox.getSelectionModel().getSelectedItem();
        String tipologiaCentro = tipoCentroComboBox.getSelectionModel().getSelectedItem();

        if(comune != null && tipologiaCentro != null && !comune.isBlank() && !tipologiaCentro.isBlank()) {
            Task<Void> getCentriVaccinaliTask = new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    centriVaccinali = new Client().cercaCentroVaccinale(comune, TipologiaCentroVaccinale.valueFromString(tipologiaCentro).get());
                    return null;
                }
            };
    
            // get centri vaccinali succeeded
            getCentriVaccinaliTask.setOnSucceeded(e -> {
                if(centriVaccinali != null) {
                    if(ricercaCentriVaccinali == null) ricercaCentriVaccinali = new ArrayList<CentroVaccinale>();
                    ricercaCentriVaccinali.clear();
                    for(CentroVaccinale cv: centriVaccinali) ricercaCentriVaccinali.add(cv);
                    risultatiRicercaListView.getItems().setAll(ricercaCentriVaccinali);
                }

                ricercaPerComuneETipoButton.setText("Cerca");
                ricercaPerComuneETipoButton.setDisable(false);
                comuneComboBox.setDisable(false);
                tipoCentroComboBox.setDisable(false);
            });

            // get centri vaccinali failed
            getCentriVaccinaliTask.setOnFailed(e -> {
                System.err.println("Errore durante il retrieve dei centri vaccinali");
                ricercaPerComuneETipoButton.setText("Cerca");
                ricercaPerComuneETipoButton.setDisable(false);
                comuneComboBox.setDisable(false);
                tipoCentroComboBox.setDisable(false);
            });
    
            new Thread(getCentriVaccinaliTask).start();
            ricercaPerComuneETipoButton.setText("...");
            ricercaPerComuneETipoButton.setDisable(true);
            comuneComboBox.setDisable(true);
            tipoCentroComboBox.setDisable(true);
        } else {
            System.err.println("Errore ricerca");
        }
    }

    /**
     * Permette il logout
     * @param event
     */
    @FXML
    void onClickLogout(MouseEvent event) {
        SessionHandler.logout();
        new SceneManager().switchToNewScene(event, "accesso");
    }

    /**
     * Permette di inserire un evento avverso
     * @param event
     */
    @FXML
    void onClickInserisciEvento(ActionEvent event) {
        // controllo se guest
        if(SessionHandler.getUtente() == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Attenzione");
            alert.setHeaderText("Attenzione");
            alert.setContentText("Per inserire un evento avverso devi aver prima effettuato l'accesso.");
            alert.showAndWait();
        } else {
            if(risultatiRicercaListView.getSelectionModel().getSelectedItem() != null) {
                // Controllo se il cittadino si e' effettivamente vaccinato in quel centro vaccinale
                CentroVaccinale centroVaccinale = risultatiRicercaListView.getSelectionModel().getSelectedItem();
                Task<Boolean> checkPuoInserireEvento = new Task<Boolean>() {
                    @Override
                    protected Boolean call() throws Exception {
                        return new Client().controlloVaccinatoInCentro(SessionHandler.getUtente().getIdVaccinazione(), centroVaccinale.getId());
                    }
                };

                // puoi inserire evento succeeded
                checkPuoInserireEvento.setOnSucceeded(e -> {
                    try {
                        if(checkPuoInserireEvento.get()) {
                            FXMLAggiungiEventoController.setCentroVaccinale(risultatiRicercaListView.getSelectionModel().getSelectedItem());
                            FXMLAggiungiEventoController.setFotoProfiloURI(fotoUtenteImageView.getImage());
                            new SceneManager().switchToNewScene(event, "aggiungievento");
                        } else {
                            // Errore: Non ti sei vaccinato in questo centro
                            Alert alert = new Alert(Alert.AlertType.WARNING);
                            alert.setTitle("Attenzione");
                            alert.setHeaderText("Attenzione");
                            alert.setContentText("Puoi inserire eventi avversi solo nei centri vaccinali in cui ti sei effettivamente vaccinato.");
                            alert.showAndWait();
                        }
                    } catch (InterruptedException | ExecutionException e1) {
                        e1.printStackTrace();
                    }

                    inserisciEventoButton.setText("Inserisci evento avverso");
                    inserisciEventoButton.setDisable(false);
                });

                // puoi inserire evento failed
                checkPuoInserireEvento.setOnFailed(e -> {
                    inserisciEventoButton.setText("Inserisci evento avverso");
                    inserisciEventoButton.setDisable(false);
                });

                new Thread(checkPuoInserireEvento).start();
                inserisciEventoButton.setText("Caricamento...");
                inserisciEventoButton.setDisable(true);
            }
        }
    }

    /**
     * Imposta la combobox dei comuni usando il {@link cittadini.util.JSONReader#JSONReader() JSONReader}
     */
    public void setComuniComboBox() {
        Task<Void> getComuniTask = new Task<Void>() {
            protected Void call() throws Exception {
                comuni = jsonReader.getAllComuni();
                return null;
            }
        };

        // get comuni succeeded 
        getComuniTask.setOnSucceeded(e -> {
            comuneComboBox.getItems().setAll(comuni);
            comuneComboBox.setDisable(false);
            ricercaPerComuneETipoButton.setDisable(false);
        });

        // get comuni failed
        getComuniTask.setOnFailed(e -> {
            ricercaPerComuneETipoButton.setDisable(true);
        });

        new Thread(getComuniTask).start();
        ricercaPerComuneETipoButton.setDisable(true);
    }

    /**
     * Riempie la lista dei centri vaccinali
     */
    public void setListaCentriVaccinali() {
        Task<Void> getCentriVaccinaliTask = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                centriVaccinali = new Client().cercaCentroVaccinale("");
                return null;
            }
        };

        // ottenimento centri vaccinali succeeded
        getCentriVaccinaliTask.setOnSucceeded(e -> {
            if(centriVaccinali != null) {
                if(ricercaCentriVaccinali == null) ricercaCentriVaccinali = new ArrayList<CentroVaccinale>();
                for(CentroVaccinale cv: centriVaccinali) ricercaCentriVaccinali.add(cv);
                risultatiRicercaListView.getItems().setAll(ricercaCentriVaccinali);
            }
        });

        // ottenimento centri vaccinali failed
        getCentriVaccinaliTask.setOnFailed(e -> {
            System.err.println("Errore durante il retrieve dei centri vaccinali");
        });

        new Thread(getCentriVaccinaliTask).start();
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        if(SessionHandler.getUtente() != null) {
            Cittadino utente = SessionHandler.getUtente();
            nomeUtenteLabel.setText(String.format("%s %s", utente.getNome(), utente.getCognome()));

            // se il giorno di nascita sul codice fiscale è maggiore di 40 allora l'utente è donna
            boolean isMale = Integer.parseInt(utente.getCodiceFiscale().subSequence(9, 11).toString()) < 41;

            // impostazione dell'immagine corretta in base al sesso dell'utente
            File file = new File(isMale ? "src/main/resources/male.png" : "src/main/resources/female.png");
            Image image = new Image(file.toURI().toString());
            fotoUtenteImageView.setImage(image);
        }

        jsonReader = new JSONReader();
        setComuniComboBox();

        // settaggio della combobox usando
        tipoCentroComboBox.getItems().setAll(
            TipologiaCentroVaccinale.OSPEDALIERO.getValue(),
            TipologiaCentroVaccinale.AZIENDALE.getValue(),
            TipologiaCentroVaccinale.HUB.getValue()
        );

        risultatiRicercaListView.getSelectionModel().selectedItemProperty().addListener(
            new ChangeListener<CentroVaccinale>() {
                @Override
                public void changed(ObservableValue<? extends CentroVaccinale> observableValue, CentroVaccinale oldValue, CentroVaccinale newValue) {
                    if(newValue != null) {
                        File file = new File(String.format("src/main/resources/%s.png", newValue.getTipologiaCentroVaccinale().getValue().toLowerCase()));
                        Image image = new Image(file.toURI().toString());
                        fotoCentroImageView.setImage(image);

                        // costruzione dell'indirizzo centro da printare
                        nomeCentroLabel.setText(newValue.getNome());
                        String indirizzoCentro = String.format(
                            "%s, %s %s %s - %s (%s)", 
                            newValue.getComune(), 
                            newValue.getQualificatoreIndirizzo().getValue(),
                            newValue.getNomeIndirizzo(),
                            newValue.getNumeroCivico(),
                            newValue.getCAP(),
                            newValue.getProvincia()
                        );
                        indirizzoCentroLabel.setText(indirizzoCentro);
                        tipoCentroLabel.setText(newValue.getTipologiaCentroVaccinale().getValue());

                        Task<Void> getAdverseEventsData = new Task<Void>() {
                            @Override
                            protected Void call() throws Exception {
                                datiExtraCentroVaccinale = new Client().getDatiSuEventiAvversi(newValue.getId());
                                return null;
                            }
                        };

                        // ottenimento eventi avversi succeeded
                        getAdverseEventsData.setOnSucceeded(e -> {
                            if(datiExtraCentroVaccinale != null) {
                                numeroEventiLabel.setText(Integer.toString(datiExtraCentroVaccinale.getNumeroEventiAvversi()));
                                severitaMediaLabel.setText(String.format("%.1f", datiExtraCentroVaccinale.getGradoSeveritaMedio()));

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
            }
        );

        setListaCentriVaccinali();
    }

}