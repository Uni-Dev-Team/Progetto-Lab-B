/**
 * Christian Loschiavo 739894 VA
 * Ivan Giubilei 739892 VA
 * Nicolò Rossi 742626 VA
 * Andrea Ferrario 740485 VA
 */

package centrivaccinali.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.Date;
import java.util.List;

import centrivaccinali.classes.CentroVaccinale;
import centrivaccinali.enumerators.TipoVaccino;

/**
 * Interfaccia remota per RMI
 * Gestisce quali metodi possono essere lanciati remotamente dal client
 */
public interface CentroVaccinaleInterfaccia extends Remote{
    String getValidId(String columnName, String tableName) throws RemoteException;
    CentroVaccinale getCentroVaccinaleById(String id) throws RemoteException;
    String registraCentroVaccinale(CentroVaccinale centro) throws RemoteException;
    String addVaccinato(String nomeCittadino, String cognomeCittadino, String codiceFiscale, Date dataSomministrazione, TipoVaccino typeVaccino, String idCentro) throws RemoteException;
    List<CentroVaccinale> getAllCentriVaccinali() throws RemoteException;
}
