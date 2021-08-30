package unidevteam.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class SceneManager {
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchToNewScene(ActionEvent event, String fileName) {
        try {
            try{
                root = FXMLLoader.load(new File("Server/src/main/resources/"+ fileName + ".fxml").toURI().toURL());
            } catch (FileNotFoundException e) {
                root = FXMLLoader.load(new File("src/main/resources/"+ fileName + ".fxml").toURI().toURL());
            } finally {
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                if(fileName.equals("metrics")) {
                    stage.setOnCloseRequest(onCLickEvent -> {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Attenzione");
                        alert.setHeaderText("Attenzione");
                        alert.setContentText("Impossibile chiudere la pagina. \n Se vuoi terminare il programma termina il server e poi esci.");
                        alert.showAndWait();
                        onCLickEvent.consume();
                });
                } else {
                    stage.setOnCloseRequest(onCLickEvent -> {
                        stage.close();
                    });
                }
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
                root.requestFocus();
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}
