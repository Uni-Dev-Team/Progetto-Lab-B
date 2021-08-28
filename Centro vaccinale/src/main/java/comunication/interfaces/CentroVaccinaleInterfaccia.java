package comunication.interfaces;
import java.rmi.*;
import java.sql.Date;
import java.util.List;

import unidevteam.classes.CentroVaccinale;
import unidevteam.enumerators.TipoVaccino;

public interface CentroVaccinaleInterfaccia extends Remote {
    public boolean registraCentroVaccinale(CentroVaccinale centro) throws RemoteException;
    public boolean cancellaCentroVaccinale(CentroVaccinale centro) throws RemoteException;
    public boolean registraVaccinato(String nomeCentro, String nomeCittadino, String cognomeCittadino, String codiceFiscale, Date dataSomministrazione, TipoVaccino tipoVaccino, String idVaccinazione) throws RemoteException;
    public List<CentroVaccinale> ottieniCentriVaccinali() throws RemoteException;
    public CentroVaccinale getCentroVaccinaleById(String id) throws RemoteException;
    public String getValidId(String columnName, String tableName) throws RemoteException;
}