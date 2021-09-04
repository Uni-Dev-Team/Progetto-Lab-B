package unidevteam;

import java.io.File;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Hello world!
 *
 */
public class App extends Application
{
    public static void main( String[] args ){
        launch(args);   
    }

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(new File("src/main/resources/scene.fxml").toURI().toURL());
        stage.setScene(new Scene(root));
        stage.setTitle("Client: Cittadini");
        stage.setResizable(false);
        stage.show();
    }
}
