package unidevteam.comunication;

import unidevteam.classes.*;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Date;
import java.util.List;

import unidevteam.comunication.interfaces.CentroVaccinaleInterfaccia;
import unidevteam.enumerators.TipoVaccino;
import unidevteam.util.DBManager;

public class Server extends UnicastRemoteObject implements CentroVaccinaleInterfaccia{

    public Server() throws RemoteException {
        super();
        Registry registry = LocateRegistry.createRegistry(2000);
        System.setProperty("java.security.policy","./policy/security.policy");
        registry.rebind("server", this);
        System.out.println("Server: RMI started and listening");
    }

    @Override
    public CentroVaccinale getCentroVaccinaleById(String id) throws RemoteException {
        System.out.println("GET CENTRO VACCINALE");
        if(!id.isBlank() && id != null) {
            try {
                CentroVaccinale centro = DBManager.getInstance().getCentroVaccinaleById(id);
                return centro;
            } catch(Exception e) {
                return null;
            }
        }

        return null;
    }

    @Override
    public boolean registraCentroVaccinale(CentroVaccinale centro) throws RemoteException {
        try {
            Boolean res = DBManager.getInstance().addCentroVaccinale(centro);
            return res;
        } catch(Exception e) {
            return false;
        }
    }

    @Override
    public boolean cancellaCentroVaccinale(CentroVaccinale centro) throws RemoteException {
        try {
            Boolean res = DBManager.getInstance().deleteCentroVaccinale(centro);
            return res;
        } catch(Exception e) {
            return false;
        }
    }

    @Override
    public boolean registraVaccinato(String nomeCentro, String nomeCittadino, String cognomeCittadino,
            String codiceFiscale, Date dataSomministrazione, TipoVaccino tipoVaccino, String idVaccinazione)
            throws RemoteException {
       
        // TODO: Da implementare in DBManager
        return false;
    }

    @Override
    public List<CentroVaccinale> ottieniCentriVaccinali() throws RemoteException {
        try {
            List<CentroVaccinale> centri = DBManager.getInstance().getAllCentriVaccinali();
            return centri;
        } catch(Exception e) {
            return null;
        }
    }

    @Override
    public String getValidId(String columnName, String tableName) throws RemoteException {
        // TODO Auto-generated method stub
        try {
            return DBManager.getInstance().getValidId(columnName, tableName);
        } catch (Exception e) {
            return null;
        }
    }
}