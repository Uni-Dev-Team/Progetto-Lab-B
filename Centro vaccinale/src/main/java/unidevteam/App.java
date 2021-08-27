package unidevteam;

import java.io.File;
import java.rmi.ConnectException;
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


public class App extends Application{
    public static void main( String[] args ) throws RemoteException, NotBoundException {
        try {
            Registry registro = LocateRegistry.getRegistry(1099);
            CentroVaccinaleInterfaccia server = (CentroVaccinaleInterfaccia) registro.lookup("server");
            server.getCentroVaccinaleById("");
        } catch (ConnectException e) {
            System.err.println("Server not opened or no connection");
        }
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(new File("Centro vaccinale/src/main/resources/scene.fxml").toURI().toURL());
        stage.setScene(new Scene(root));
        stage.setTitle("Client: Centro vaccinale");
        stage.show();
        root.requestFocus();

        
        
    }
}
