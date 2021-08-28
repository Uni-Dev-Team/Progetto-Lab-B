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

    public Server() throws RemoteException {
        super();
        System.out.println("Service Starting");
        try {
            Registry registry = LocateRegistry.createRegistry(port);
            registry.rebind("Server_centroVaccinale", this);
            System.out.println("Server ready! On port: " + port);
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

    
}
