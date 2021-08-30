package unidevteam.comunication;

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
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
    
    public String addCentroVaccinale(CentroVaccinale centro) {
        try {
			return stub.registraCentroVaccinale(centro);
		} catch (RemoteException e) {
            e.printStackTrace();
			return null;
		}
    }
}
