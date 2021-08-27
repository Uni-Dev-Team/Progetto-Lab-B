package unidevteam.classes;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Date;
import java.util.List;

import unidevteam.enumerators.TipoVaccino;
import unidevteam.interfaces.CentroVaccinaleInterfaccia;

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
        System.out.println("hi");
        return null;
    }

    @Override
    public boolean registraCentroVaccinale(CentroVaccinale centro) throws RemoteException {
        
        return false;
    }

    @Override
    public boolean cancellaCentroVaccinale(CentroVaccinale centro) throws RemoteException {
        
        return false;
    }

    @Override
    public boolean registraVaccinato(String nomeCentro, String nomeCittadino, String cognomeCittadino,
            String codiceFiscale, Date dataSomministrazione, TipoVaccino tipoVaccino, String idVaccinazione)
            throws RemoteException {
       
        return false;
    }

    @Override
    public List<CentroVaccinale> ottieniCentriVaccinali() throws RemoteException {
        
        return null;
    }
}