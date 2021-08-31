package unidevteam.communication;

import java.rmi.registry.*;

import unidevteam.interfaces.CittadiniInterfaccia;

public class Client {
    static final int port = 2099;
    private CittadiniInterfaccia stub;
    
    public Client() {
        try {
            Registry registry = LocateRegistry.getRegistry(port);
            stub = (CittadiniInterfaccia) registry.lookup("Server_cittadini");

            stub.cercaCentroVaccinale("Piermario");
            // if(stub == null) throw new Exception("Impossibile connettersi al server");
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
        }
    }
}
