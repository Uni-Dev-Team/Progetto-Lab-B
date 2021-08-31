package unidevteam.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class SceneManager {
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchToNewScene(MouseEvent event, String fileName) {
        try {
            try{
                root = FXMLLoader.load(new File("Centro vaccinale/src/main/resources/"+ fileName + ".fxml").toURI().toURL());
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
