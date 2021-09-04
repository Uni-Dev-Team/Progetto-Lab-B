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
import java.sql.Date;
import java.util.List;

import server.classes.CentroVaccinale;
import server.enumerators.TipoVaccino;
import server.interfaces.CentroVaccinaleInterfaccia;
import server.util.DBManager;

/**
 * Classe per gestire le funzionalità del server per quanto riguarda i centri vaccinali
 * Estende UnicastRemoteObject in quanto usiamo questa classe per creare il collegamento con l'RMI
 * Implementa l'interfaccia remota {@link server.interfaces.CentroVaccinaleInterfaccia CentroVaccinaleInterfaccia}
 */
public class ServerCentroVaccinale extends UnicastRemoteObject implements CentroVaccinaleInterfaccia {
    static final int port = 1099;
    Registry registry;

    /**
     * Creazione collegamento tramite RMI
     * @throws RemoteException
     */
    public ServerCentroVaccinale() throws RemoteException {
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

    // metodi definite nell'interfaccia:

    // ottiene id da DB
    @Override
    public String getValidId(String columnName, String tableName) throws RemoteException {
        try {
            return DBManager.getInstance().getValidId(columnName, tableName);
        } catch (Exception e) {
            return null;
        }
    }

    // ottiene centro vaccinale dando in input un id
    @Override
    public CentroVaccinale getCentroVaccinaleById(String id) throws RemoteException {
        try {
            return DBManager.getInstance().getCentroVaccinaleById(id);
        } catch (Exception e) {
            return null;
        }
    }

    // registra il centro vaccinale dato in input nel DB
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

    // aggiunge vaccinato al DB
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

    // ritorna lista di tutti i centri vaccinali
    @Override
	public List<CentroVaccinale> getAllCentriVaccinali() throws RemoteException {
        try {
			return DBManager.getInstance().getAllCentriVaccinali();
		} catch (Exception e) {
			return null;
		}
	}
    
    // kill server
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
