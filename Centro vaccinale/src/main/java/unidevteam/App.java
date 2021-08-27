package unidevteam;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javafx.application.Application;
import javafx.stage.Stage;
import unidevteam.interfaces.CentroVaccinaleInterfaccia;

/**
 * Hello world!
 *
 */
public class App extends Application{
    public static void main( String[] args ) throws RemoteException, NotBoundException{
        Registry registro = LocateRegistry.getRegistry(1099);
        CentroVaccinaleInterfaccia server = (CentroVaccinaleInterfaccia) registro.lookup("server");
        server.getCentroVaccinaleById("");
        launch(args);
    }

    @Override
    public void start(Stage arg0) throws Exception {
        
        
    }
}
