package unidevteam.comunication;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.ExportException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Date;
import java.util.List;

import unidevteam.classes.CentroVaccinale;
import unidevteam.enumerators.TipoVaccino;
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
        } catch(ExportException e) {
            registry = LocateRegistry.getRegistry(port);
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

    @Override
    public String addVaccinato(String nomeCittadino, String cognomeCittadino,
            String codiceFiscale, Date dataSomministrazione, TipoVaccino typeVaccino, String idCentro) throws RemoteException {
        try {
            String token = DBManager.getInstance().getValidId("id", "vaccinati");
            return DBManager.getInstance().addVaccinato(token, nomeCittadino, cognomeCittadino, codiceFiscale, dataSomministrazione, typeVaccino, idCentro);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
	public List<CentroVaccinale> getAllCentriVaccinali() throws RemoteException {
        try {
			return DBManager.getInstance().getAllCentriVaccinali();
		} catch (Exception e) {
			return null;
		}
	}
    

    public void exit() throws RemoteException {
    try{
        registry.unbind("Server_centroVaccinale");
        UnicastRemoteObject.unexportObject(this, true);
        System.out.println("RMI Server: exiting.");
    } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
