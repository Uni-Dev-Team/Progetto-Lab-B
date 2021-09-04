/**
 * Christian Loschiavo 739894 VA
 * Ivan Giubilei 739892 VA
 * Nicolò Rossi 742626 VA
 * Andrea Ferrario 740485 VA
 */

package server.interfaces;

import java.rmi.*;
import java.util.List;

import server.classes.*;
import server.enumerators.TipologiaCentroVaccinale;

public interface CittadiniInterfaccia extends Remote {
    
    /**
     * @param nomeCentro nome del centro vaccinale
     * @return Centro vaccinale trovato
     * @throws RemoteException
     */
    public List<CentroVaccinale> cercaCentroVaccinale(String nomeCentro) throws RemoteException;
    
    /**
     * @param comune
     * @param tipologiaCentro tipologia di centro vaccinale
     * @return lista di centri vaccinali matchati
     * @throws RemoteException
     */
    public List<CentroVaccinale> cercaCentroVaccinale(String comune, TipologiaCentroVaccinale tipologiaCentro) throws RemoteException;
    
    /**
     * @param email
     * @param plainPassword password non hashata
     * @return Cittadino 
     * @throws RemoteException
     */
    public Cittadino autenticaUtente(String email, String plainPassword) throws RemoteException;
    
    /**
     * @param cittadino utente
     * @return risultato dell'operazione
     * @throws RemoteException
     */
    public boolean registraCittadino(Cittadino cittadino) throws RemoteException;

    /**
     * @param eventoAvverso tipologia di evento avverso
     * @param idVaccinazione id della vaccinazione 
     * @param idCentro id del centro vaccinale
     * @return il risultato dell'operazione
     * @throws RemoteException
     */
    public boolean inserisciEventoAvverso(EventoAvverso eventoAvverso, String idVaccinazione, String idCentro) throws RemoteException;

    /**
     * @param idVaccinazione id della vaccinazione
     * @param idCentro id del centro vaccinale
     * @return il risultato dell'operazione
     * @throws RemoteException
     */
    public boolean controlloVaccinatoInCentro(String idVaccinazione, String idCentro) throws RemoteException;

    /**
     * @param idCentro id del centro vaccinazione
     * @return dati statistici sul centro vaccinale
     * @throws RemoteException
     */
    public DatiExtraCentroVaccinale getDatiSuEventiAvversi(String idCentro) throws RemoteException;
}
