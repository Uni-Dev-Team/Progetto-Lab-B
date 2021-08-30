package unidevteam.comunication;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import unidevteam.classes.CentroVaccinale;
import unidevteam.interfaces.CentroVaccinaleInterfaccia;
import unidevteam.util.DBManager;

public class Server extends UnicastRemoteObject implements CentroVaccinaleInterfaccia{
    static final int port = 1099;
    Registry registry;

    public Server() throws RemoteException {
        super();
        System.out.println("RMI Server Starting");
        try {
            registry = LocateRegistry.createRegistry(port);
            registry.rebind("Server_centroVaccinale", this);
            System.out.println("RMI Server ready and listening on port: " + port);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getValidId(String columnName, String tableName) throws RemoteException {
        try {
            return DBManager.getInstance().getValidId(columnName, tableName);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public CentroVaccinale getCentroVaccinaleById(String id) throws RemoteException {
        try {
            return DBManager.getInstance().getCentroVaccinaleById(id);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
	public String registraCentroVaccinale(CentroVaccinale centro) throws RemoteException {
        try {
            String token = DBManager.getInstance().getValidId("id", "centrivaccinali");
            centro.setId(token);
            return DBManager.getInstance().addCentroVaccinale(centro);
        } catch (Exception e) {
            return null;
        }
	}

    public void exit() throws RemoteException {
    try{
        // Unregister ourself
        registry.unbind("Server_centroVaccinale");

        // Unexport; this will also remove us from the RMI runtime
        UnicastRemoteObject.unexportObject(this, true);

        System.out.println("RMI Server: exiting.");
    } catch(Exception e) {
        e.printStackTrace();
    }
}
    
}
