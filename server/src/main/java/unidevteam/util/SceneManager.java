package unidevteam.util;

import java.io.File;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneManager {
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchToNewScene(ActionEvent event, String fileName) {
        try {
            root = FXMLLoader.load(new File("Server/src/main/resources/"+ fileName + ".fxml").toURI().toURL());
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}
