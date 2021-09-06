/**
 * Christian Loschiavo 739894 VA
 * Ivan Giubilei 739892 VA
 * Nicolò Rossi 742626 VA
 * Andrea Ferrario 740485 VA
 */


package unidevteam;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Cittadini extends Application
{
    public static void main( String[] args ){
        launch(args);   
    }

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("scene.fxml"));
        stage.setScene(new Scene(root));
        stage.setTitle("Client: Cittadini");
        stage.setResizable(false);
        stage.show();
    }
}
