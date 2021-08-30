package unidevteam.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.Date;

import unidevteam.classes.CentroVaccinale;
import unidevteam.enumerators.TipoVaccino;

public interface CentroVaccinaleInterfaccia extends Remote{
    String getValidId(String columnName, String tableName) throws RemoteException;
    CentroVaccinale getCentroVaccinaleById(String id) throws RemoteException;
    String registraCentroVaccinale(CentroVaccinale centro) throws RemoteException;
    String addVaccinato(String nomeCittadino, String cognomeCittadino, String codiceFiscale, Date dataSomministrazione, TipoVaccino typeVaccino, String idCentro) throws RemoteException;
}
