package unidevteam;

import java.io.File;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import unidevteam.interfaces.CentroVaccinaleInterfaccia;

/**
 * Hello world!
 *
 */
public class App extends Application{
    public static void main( String[] args ) throws RemoteException, NotBoundException {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(new File("src/main/resources/scene.fxml").toURI().toURL());
        stage.setScene(new Scene(root));
        stage.setTitle("Server Dashboard");
        stage.show();
        root.requestFocus();

        Registry registro = LocateRegistry.getRegistry(1099);
        CentroVaccinaleInterfaccia server = (CentroVaccinaleInterfaccia) registro.lookup("server");
        server.getCentroVaccinaleById("");
    }
}
