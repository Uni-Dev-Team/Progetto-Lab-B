package cittadini.communication;

import java.rmi.RemoteException;
import java.rmi.registry.*;
import java.util.List;

import cittadini.classes.CentroVaccinale;
import cittadini.classes.Cittadino;
import cittadini.classes.DatiExtraCentroVaccinale;
import cittadini.classes.EventoAvverso;
import cittadini.enumerators.TipologiaCentroVaccinale;
import cittadini.interfaces.CittadiniInterfaccia;

/**
 * Classe utilizzata per la connessione tramite RMI e per la gestione dei dati dei cittadini.
 * @see cittadini.classes.Cittadino#Cittadino()
 */
public class Client {
    static final int port = 2099;
    private CittadiniInterfaccia stub;

    public Client() {
        try {
            Registry registry = LocateRegistry.getRegistry(port);
            stub = (CittadiniInterfaccia) registry.lookup("Server_cittadini");
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
        }
    }

    /**
     * @param cittadino
     * @return boolean
     * @throws Exception
     */
    public boolean registraCittadino(Cittadino cittadino) throws Exception {
        try {
            if(stub != null) {
                return stub.registraCittadino(cittadino);
            }    
            throw new Exception("Impossibile connettersi al server");
		} catch (RemoteException e) {
			return false;
		}
    }

    /**
     * Metodo per autenticare l'utente
     * @param email
     * @param plainPassword
     * @return Cittadino
     */
    public Cittadino autenticaUtente(String email, String plainPassword) {
        try {
            if(stub != null) {
                return stub.autenticaUtente(email, plainPassword);
            }
		} catch (RemoteException e) {}

        return null;
    }

    /**
     * Metodo per cercare il centro vaccinale
     * @param nomeCentro
     * @return List<CentroVaccinale>
     */
    public List<CentroVaccinale> cercaCentroVaccinale(String nomeCentro) {
        try {
            if(stub != null) {
                List<CentroVaccinale> res = stub.cercaCentroVaccinale(nomeCentro);
                return res;
            }
		} catch (RemoteException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Metodo per cercare il centro vaccinale
     * @param comune
     * @param tipologiaCentroVaccinale
     * @return List<CentroVaccinale>
     */
    public List<CentroVaccinale> cercaCentroVaccinale(String comune, TipologiaCentroVaccinale tipologiaCentroVaccinale) {
        try {
            if(stub != null) {
                List<CentroVaccinale> res = stub.cercaCentroVaccinale(comune, tipologiaCentroVaccinale);
                return res;
            }
		} catch (RemoteException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Metodo per inserire evento avverso
     * @param eventoAvverso
     * @param idVaccinazione
     * @param idCentro
     * @return boolean
     */
    public boolean inserisciEventoAvverso(EventoAvverso eventoAvverso, String idVaccinazione, String idCentro) {
        try {
            if(stub != null) {
                return stub.inserisciEventoAvverso(eventoAvverso, idVaccinazione, idCentro);
            }
		} catch (RemoteException e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Metodo per vedere se un vaccinato ha inserito gli eventi avversi nel centro giusto
     * @param idVaccinazione
     * @param idCentro
     * @return boolean
     */
    public boolean controlloVaccinatoInCentro(String idVaccinazione, String idCentro) {
        try {
            if(stub != null) {
                return stub.controlloVaccinatoInCentro(idVaccinazione, idCentro);
            }
		} catch (RemoteException e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Metodo che ritorna dati su eventi avversi
     * @param idCentro
     * @return DatiExtraCentroVaccinale
     */
    public DatiExtraCentroVaccinale getDatiSuEventiAvversi(String idCentro) {
        try {
            if(stub != null) {
                return stub.getDatiSuEventiAvversi(idCentro);
            }
		} catch (RemoteException e) {
            e.printStackTrace();
        }

        return null;
    }
}
