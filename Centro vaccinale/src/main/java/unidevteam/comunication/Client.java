package unidevteam.comunication;

import java.net.ConnectException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import unidevteam.classes.CentroVaccinale;
import unidevteam.interfaces.CentroVaccinaleInterfaccia;

public class Client {
    static final int port = 10999;
    private CentroVaccinaleInterfaccia stub;
    
    public Client() {
        try {
            Registry registry = LocateRegistry.getRegistry(1099);
            stub = (CentroVaccinaleInterfaccia) registry.lookup("Server_centroVaccinale");
            // if(stub == null) throw new Exception("Impossibile connettersi al server");
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
        }
    }
    
    public String addCentroVaccinale(CentroVaccinale centro) throws Exception {
        try {
            if(stub != null) {
                return stub.registraCentroVaccinale(centro);
            }
            throw new Exception("Impossibile connettersi al server");
		} catch (RemoteException e) {
			return null;
		}
    }
}
