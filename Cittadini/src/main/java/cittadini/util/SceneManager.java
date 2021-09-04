package cittadini.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * Gestione scene
 */
public class SceneManager {
    private Stage stage;
    private Scene scene;
    private Parent root;

    /**
     * Metodo per cambiare scene
     * @param event
     * @param fileName nome della scena a cui si vuole arrivare
     */
    public void switchToNewScene(ActionEvent event, String fileName) {
        try {
            try{
                root = FXMLLoader.load(new File("Cittadini/src/main/resources/"+ fileName + ".fxml").toURI().toURL());
            } catch (FileNotFoundException e) {
                root = FXMLLoader.load(new File("src/main/resources/"+ fileName + ".fxml").toURI().toURL());
            } finally {
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
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

    /**
     * Metodo per cambiare scene
     * @param event
     * @param fileName nome della scena a cui si vuole arrivare
     */
    public void switchToNewScene(MouseEvent event, String fileName) {
        try {
            try{
                root = FXMLLoader.load(new File("Cittadini/src/main/resources/"+ fileName + ".fxml").toURI().toURL());
            } catch (FileNotFoundException e) {
                root = FXMLLoader.load(new File("src/main/resources/"+ fileName + ".fxml").toURI().toURL());
            } finally {
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
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
