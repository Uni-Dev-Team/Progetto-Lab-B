package unidevteam.classes;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Date;
import java.util.List;

import unidevteam.enumerators.TipoVaccino;
import unidevteam.interfaces.CentroVaccinaleInterfaccia;
import unidevteam.util.DBManager;

public class Server extends UnicastRemoteObject implements CentroVaccinaleInterfaccia{

    public Server() throws RemoteException {
        super();

        System.out.println("Registering ...");
        Registry registry = LocateRegistry.createRegistry(1099);
        registry.rebind("server", this);
        System.out.println("Registered");
    }

    @Override
    public CentroVaccinale getCentroVaccinaleById(String id) throws RemoteException {
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
}