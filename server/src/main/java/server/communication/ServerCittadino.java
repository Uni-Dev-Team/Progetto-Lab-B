/**
 * Christian Loschiavo 739894 VA
 * Ivan Giubilei 739892 VA
 * Nicolò Rossi 742626 VA
 * Andrea Ferrario 740485 VA
 */

package server.communication;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.ExportException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import server.classes.CentroVaccinale;
import server.classes.Cittadino;
import server.classes.DatiExtraCentroVaccinale;
import server.classes.EventoAvverso;
import server.enumerators.TipologiaCentroVaccinale;
import server.interfaces.CittadiniInterfaccia;
import server.util.DBManager;

/**
 * Classe per gestire le funzionalità del server per quanto riguarda i cittadini
 * Estende UnicastRemoteObject in quanto usiamo questa classe per creare il collegamento con l'RMI.
 * 
 * Implementa l'interfaccia remota {@link server.interfaces.CittadiniInterfaccia CittadiniInterfaccia}
 */
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
        try {
            if(!nomeCentro.equals(""))
                return DBManager.getInstance().getCentriVaccinaliByNome(nomeCentro);
            else
                return DBManager.getInstance().getAllCentriVaccinali();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<CentroVaccinale> cercaCentroVaccinale(String comune, TipologiaCentroVaccinale tipologiaCentro)
            throws RemoteException {
        try {
            if(!comune.equals("") && !tipologiaCentro.name().toUpperCase().equals(""))
                return DBManager.getInstance().getCentriVaccinaliByComuneETipologiaCentro(comune, tipologiaCentro);
            else
                return DBManager.getInstance().getAllCentriVaccinali();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean registraCittadino(Cittadino cittadino) throws RemoteException {
        try {
            DBManager.getInstance().addCittadino(cittadino);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean inserisciEventoAvverso(EventoAvverso eventoAvverso, String idVaccinazione, String idCentro) throws RemoteException {
        try {
            return DBManager.getInstance().inserisciEventoAvverso(eventoAvverso, idVaccinazione, idCentro);
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    @Override
    public boolean controlloVaccinatoInCentro(String idVaccinazione, String idCentro) throws RemoteException {
        try {
            return DBManager.getInstance().controlloVaccinatoInCentro(idVaccinazione, idCentro);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public DatiExtraCentroVaccinale getDatiSuEventiAvversi(String idCentro) throws RemoteException {
        try {
            return DBManager.getInstance().getDatiSuEventiAvversi(idCentro);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
