package unidevteam.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

import unidevteam.classes.CentroVaccinale;

public interface CentroVaccinaleInterfaccia extends Remote{
    String getValidId(String columnName, String tableName) throws RemoteException;
    CentroVaccinale getCentroVaccinaleById(String id) throws RemoteException;
}
