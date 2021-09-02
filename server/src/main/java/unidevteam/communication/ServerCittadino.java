package unidevteam.communication;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.ExportException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import unidevteam.classes.CentroVaccinale;
import unidevteam.classes.Cittadino;
import unidevteam.classes.EventoAvverso;
import unidevteam.enumerators.TipologiaCentroVaccinale;
import unidevteam.interfaces.CittadiniInterfaccia;
import unidevteam.util.DBManager;

public class ServerCittadino extends UnicastRemoteObject implements CittadiniInterfaccia {
    static final int port = 2099;
    Registry registry;

    public ServerCittadino() throws RemoteException {
        super();
        System.out.println("RMI Server Starting");
        try {
            registry = LocateRegistry.createRegistry(port);
            registry.rebind("Server_cittadini", this);
            System.out.println("RMI Server ready and listening on port: " + port);
        } catch(ExportException e) {
            registry = LocateRegistry.getRegistry(port);
            registry.rebind("Server_cittadini", this);
            System.out.println("RMI Server ready and listening on port: " + port);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<CentroVaccinale> cercaCentroVaccinale(String nomeCentro) throws RemoteException {
        System.out.println("Cerca centro vaccinale chiamato");
        return null;
    }

    @Override
    public List<CentroVaccinale> cercaCentroVaccinale(String comune, TipologiaCentroVaccinale tipologiaCentro)
            throws RemoteException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean registraCittadino(Cittadino cittadino) throws RemoteException {
        System.out.println("CHIAMATO METODO REGISTRA CITTADINO SERVER");

        try {
            DBManager.getInstance().addCittadino(cittadino);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean inserisciEventoAvverso(EventoAvverso eventoAvverso) throws RemoteException {
        // TODO Auto-generated method stub
        return false;
    }
    
    public void exit() throws RemoteException {
        try{
            registry.unbind("Server_cittadini");
            UnicastRemoteObject.unexportObject(this, true);
            System.out.println("RMI Server: exiting.");
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Cittadino autenticaUtente(String email, String plainPassword) {
        try {
            return DBManager.getInstance().autenticaUtente(email, plainPassword);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
