package unidevteam;

import java.io.File;
import java.rmi.ConnectException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import comunication.interfaces.CentroVaccinaleInterfaccia;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class App extends Application{
    public static void main( String[] args ) throws RemoteException, NotBoundException {
        try {
            Registry registry = LocateRegistry.getRegistry(2000);
            CentroVaccinaleInterfaccia server = (CentroVaccinaleInterfaccia) registry.lookup("server");
            System.out.println(server.getCentroVaccinaleById("AzvJCIuEMRLCsK0o"));
        } catch (ConnectException e) {
            System.err.println("Server not opened or no connection");
        }
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = null;
        try {
            root = FXMLLoader.load(new File("Centro vaccinale/src/main/resources/scene.fxml").toURI().toURL());
        } catch (Exception e) {
            root = FXMLLoader.load(new File("src/main/resources/scene.fxml").toURI().toURL());
        } finally {
            
            stage.setScene(new Scene(root));
            stage.setTitle("Client: Centro vaccinale");
            stage.show();
            root.requestFocus();
        }
        

        
        
    }
}
