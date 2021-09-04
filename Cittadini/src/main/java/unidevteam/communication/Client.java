package unidevteam.communication;

import java.rmi.RemoteException;
import java.rmi.registry.*;
import java.util.List;

import unidevteam.classes.CentroVaccinale;
import unidevteam.classes.Cittadino;
import unidevteam.classes.DatiExtraCentroVaccinale;
import unidevteam.enumerators.TipoEvento;
import unidevteam.enumerators.TipologiaCentroVaccinale;
import unidevteam.interfaces.CittadiniInterfaccia;
import unidevteam.classes.EventoAvverso;

public class Client {
    static final int port = 2099;
    private CittadiniInterfaccia stub;
    
    public Client() {
        try {
            Registry registry = LocateRegistry.getRegistry(port);
            stub = (CittadiniInterfaccia) registry.lookup("Server_cittadini");

            // if(stub == null) throw new Exception("Impossibile connettersi al server");
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
        }
    }

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

    public Cittadino autenticaUtente(String email, String plainPassword) {
        try {
            if(stub != null) {
                return stub.autenticaUtente(email, plainPassword);
            }
		} catch (RemoteException e) {}

        return null;
    }

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
