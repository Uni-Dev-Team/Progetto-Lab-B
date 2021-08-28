package unidevteam.comunication;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import unidevteam.interfaces.CentroVaccinaleInterfaccia;

public class Client {
    static final int port = 10999;
    
    public Client() {
        try {
            Registry registry = LocateRegistry.getRegistry(1099);
            CentroVaccinaleInterfaccia stub = (CentroVaccinaleInterfaccia) registry.lookup("Server_centroVaccinale");
            
            System.out.println("Server response: " + stub.getValidId("id", "centrivaccinali"));
            System.out.println("Server response: " + stub.getCentroVaccinaleById("AzvJCIuEMRLCsK0o"));
            System.exit(1);
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
    
}
