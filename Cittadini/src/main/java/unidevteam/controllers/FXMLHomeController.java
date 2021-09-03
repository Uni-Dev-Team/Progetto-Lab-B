package unidevteam.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
// import java.util.stream.Collectors;
import java.util.concurrent.ExecutionException;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import unidevteam.classes.CentroVaccinale;
import unidevteam.classes.Cittadino;
import unidevteam.communication.Client;
import unidevteam.enumerators.TipologiaCentroVaccinale;
import unidevteam.util.JsonReader;
import unidevteam.util.SceneManager;
import unidevteam.util.SessionHandler;

public class FXMLHomeController implements Initializable {

    JsonReader jsonReader;
    List<String> comuni;
    List<CentroVaccinale> centriVaccinali;
    List<CentroVaccinale> ricercaCentriVaccinali;

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

    @FXML
    void onClickRicercaPerNome(ActionEvent event) {
        String nomeCentro = ricercaNomeCentroTextField.getText();

        /*ricercaCentriVaccinali = centriVaccinali
            .stream()
            .filter(cv -> 
                cv.getNome().toLowerCase()
                .contains(nomeCentro.toLowerCase()
            ))
            .collect(Collectors.toList());
            
        risultatiRicercaListView.getItems().setAll(ricercaCentriVaccinali);*/

        Task<Void> getCentriVaccinaliTask = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                centriVaccinali = new Client().cercaCentroVaccinale(nomeCentro);
                return null;
            }
        };

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

    @FXML
    void onClickLogout(MouseEvent event) {
        SessionHandler.logout();
        new SceneManager().switchToNewScene(event, "accesso");
    }

    @FXML
    void onClickInserisciEvento(ActionEvent event) {
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
                        System.out.println(SessionHandler.getUtente().getIdVaccinazione());
                        System.out.println(centroVaccinale.getId());
                        return new Client().controlloVaccinatoInCentro(SessionHandler.getUtente().getIdVaccinazione(), centroVaccinale.getId());
                    }
                };

                checkPuoInserireEvento.setOnSucceeded(e -> {
                    try {
                        if(checkPuoInserireEvento.get()) {
                            FXMLAggiungiEventoController.setCentroVaccinale(risultatiRicercaListView.getSelectionModel().getSelectedItem());
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

    public void setComuniComboBox() {
        Task<Void> getComuniTask = new Task<Void>() {
            protected Void call() throws Exception {
                comuni = jsonReader.getAllComuni();
                return null;
            }
        };

        getComuniTask.setOnSucceeded(e -> {
            comuneComboBox.getItems().setAll(comuni);
            comuneComboBox.setDisable(false);
            ricercaPerComuneETipoButton.setDisable(false);
        });

        getComuniTask.setOnFailed(e -> {
            ricercaPerComuneETipoButton.setDisable(true);
        });

        new Thread(getComuniTask).start();
        ricercaPerComuneETipoButton.setDisable(true);
    }

    public void setListaCentriVaccinali() {
        Task<Void> getCentriVaccinaliTask = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                centriVaccinali = new Client().cercaCentroVaccinale("");
                return null;
            }
        };

        getCentriVaccinaliTask.setOnSucceeded(e -> {
            if(centriVaccinali != null) {
                if(ricercaCentriVaccinali == null) ricercaCentriVaccinali = new ArrayList<CentroVaccinale>();
                for(CentroVaccinale cv: centriVaccinali) ricercaCentriVaccinali.add(cv);
                risultatiRicercaListView.getItems().setAll(ricercaCentriVaccinali);
            }
        });

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
        }

        jsonReader = new JsonReader();
        setComuniComboBox();

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

                        /*Task<Void> getAdverseEventsData = new Task<Void>() {
                            @Override
                            protected Void call() throws Exception {
                                
                                return null;
                            }
                        };

                        getAdverseEventsData.setOnSucceeded(e -> {

                        });

                        getAdverseEventsData.setOnFailed(e -> {

                        });

                        new Thread(getAdverseEventsData).start();*/
                    }
                }
            }
        );

        setListaCentriVaccinali();
    }

}