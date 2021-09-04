/**
 * Christian Loschiavo 739894 VA
 * Ivan Giubilei 739892 VA
 * Nicolò Rossi 742626 VA
 * Andrea Ferrario 740485 VA
 */

package server.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.Date;
import java.util.List;

import server.classes.CentroVaccinale;
import server.enumerators.TipoVaccino;

public interface CentroVaccinaleInterfaccia extends Remote{
    /**
     * @param columnName nome colonna db
     * @param tableName nome tabella db
     * @return ID
     * @throws RemoteException
     */
    String getValidId(String columnName, String tableName) throws RemoteException;

    /**
     * @param id id del centro vaccinale
     * @return centro vaccinale con id matchato
     * @throws RemoteException
     */
    CentroVaccinale getCentroVaccinaleById(String id) throws RemoteException;

    /**
     * @param centro centro vaccinale
     * @return ID centro vaccinale registrato
     * @throws RemoteException
     */
    String registraCentroVaccinale(CentroVaccinale centro) throws RemoteException;

    /**
     * @param nomeCittadino nome del cittadino
     * @param cognomeCittadino cognome del cittadino
     * @param codiceFiscale codice fiscale del cittadino
     * @param dataSomministrazione data di somministrazione del vaccino
     * @param typeVaccino tipo di vaccino
     * @param idCentro id del centro vaccinale
     * @return id vaccinato aggiunto
     * @throws RemoteException
     */
    String addVaccinato(String nomeCittadino, String cognomeCittadino, String codiceFiscale, Date dataSomministrazione, TipoVaccino typeVaccino, String idCentro) throws RemoteException;
    
    /**
     * @return tutti i centri vaccinali
     * @throws RemoteException
     */
    List<CentroVaccinale> getAllCentriVaccinali() throws RemoteException;
}
