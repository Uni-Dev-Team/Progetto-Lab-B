package unidevteam.interfaces;

import java.rmi.*;
import java.util.List;

import unidevteam.classes.*;
import unidevteam.enumerators.TipologiaCentroVaccinale;

public interface CittadiniInterfaccia extends Remote {
    
    public List<CentroVaccinale> cercaCentroVaccinale(String nomeCentro) throws RemoteException;
    public List<CentroVaccinale> cercaCentroVaccinale(String comune, TipologiaCentroVaccinale tipologiaCentro) throws RemoteException;
    // public ? visualizzaInfoCentroVaccinale(String idCentro) throws RemoteException;
    public Cittadino autenticaUtente(String email, String plainPassword) throws RemoteException;
    public boolean registraCittadino(Cittadino cittadino) throws RemoteException;
    public boolean inserisciEventoAvverso(EventoAvverso eventoAvverso) throws RemoteException;
}
