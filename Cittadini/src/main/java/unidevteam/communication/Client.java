package unidevteam.communication;

import java.rmi.RemoteException;
import java.rmi.registry.*;

import unidevteam.classes.Cittadino;
import unidevteam.interfaces.CittadiniInterfaccia;

public class Client {
    static final int port = 2099;
    private CittadiniInterfaccia stub;
    
    public Client() {
        try {
            Registry registry = LocateRegistry.getRegistry(port);
            stub = (CittadiniInterfaccia) registry.lookup("Server_cittadini");

            // if(stub == null) throw new Exception("Impossibile connettersi al server");
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
        }
    }

    public boolean registraCittadino(Cittadino cittadino) throws Exception {
        try {
            if(stub != null) {
                return stub.registraCittadino(cittadino);
            }    
            throw new Exception("Impossibile connettersi al server");
		} catch (RemoteException e) {
			return false;
		}
    }

    public Cittadino autenticaUtente(String email, String plainPassword) {
        try {
            if(stub != null) {
                return stub.autenticaUtente(email, plainPassword);
            }
		} catch (RemoteException e) {}

        return null;
    }
}
