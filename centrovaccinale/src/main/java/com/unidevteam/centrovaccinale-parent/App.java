package com.unidevteam.centrovaccinale-parent;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        DBManager manager = new DBManager("tai.db.elephantsql.com", 
        "igabhvra", "igabhvra", 
        "pM8kEKh0soYm_ZRR_DcpIlelPAFb2UFv");
        try {
            manager.connect();
        } catch (SQLException exception) {
            System.err.println(exception.getMessage());
        }

        for (CentroVaccinale centroVaccinale : manager.getAllCentriVaccinali()) {
            System.out.println(centroVaccinale.toString());
        }

    }
}
