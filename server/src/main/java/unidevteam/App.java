package unidevteam;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import unidevteam.comunication.Server;


public class App extends Application
{
    public static void main( String[] args ){
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = null;
        try {
            root = FXMLLoader.load(new File("Server/src/main/resources/login.fxml").toURI().toURL());
            stage.getIcons().add(new Image(new File("Server/src/main/resources/primula.png").toURI().toURL().toString()));
        } catch (FileNotFoundException e) {
            root = FXMLLoader.load(new File("src/main/resources/login.fxml").toURI().toURL());
            stage.getIcons().add(new Image(new File("src/main/resources/primula.png").toURI().toURL().toString()));
        } finally {
            stage.setScene(new Scene(root));
            stage.setTitle("Server Dashboard");
            stage.show();
            root.requestFocus();
        }
    }
}
