package unidevteam.comunication;

import java.net.ConnectException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.Date;
import java.util.List;

import unidevteam.classes.CentroVaccinale;
import unidevteam.enumerators.TipoVaccino;
import unidevteam.interfaces.CentroVaccinaleInterfaccia;

public class Client {
    static final int port = 1099;
    private CentroVaccinaleInterfaccia stub;
    
    public Client() {
        try {
            Registry registry = LocateRegistry.getRegistry(port);
            stub = (CentroVaccinaleInterfaccia) registry.lookup("Server_centroVaccinale");
            // if(stub == null) throw new Exception("Impossibile connettersi al server");
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
        }
    }
    
    public String addCentroVaccinale(CentroVaccinale centro) throws Exception {
        try {
            if(stub != null) {
                return stub.registraCentroVaccinale(centro);
            }
            throw new Exception("Impossibile connettersi al server");
		} catch (RemoteException e) {
			return null;
		}
    }

    public List<CentroVaccinale> getAllCentriVaccinali() throws Exception {
        try {
            if(stub != null) {
                return stub.getAllCentriVaccinali();
            }    
            throw new Exception("Impossibile connettersi al server");
		} catch (RemoteException e) {
			return null;
		}
    }
    public String addVaccinato(String nomeCittadino, String cognomeCittadino, String codiceFiscale, Date dataSomministrazione, TipoVaccino typeVaccino, String idCentro) throws Exception {
        try{
            if(stub != null) {
                return stub.addVaccinato(nomeCittadino, cognomeCittadino, codiceFiscale, dataSomministrazione, typeVaccino, idCentro);
            }
            throw new Exception("Impossibile connettersi al server");
        } catch (RemoteException e) {
            return null;
        }
        
    }
}
