package unidevteam.util;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import unidevteam.classes.CentroVaccinale;
import unidevteam.enumerators.*;



/**
     * Manager for the Database
     *
     * @version 1.0
     * @category Database Comunicaion
     * @author AndrewF17
     */
public class DBManager {
    private String url;
    private String user;
    private String password;

    public DBManager(String hostname, String dbName, String user, String password) {
        this.url = "jdbc:postgresql://"+ hostname +"/" + dbName;
        this.user = user;
        this.password = password;
    }

    public DBManager() {
        this.url = "jdbc:postgresql://tai.db.elephantsql.com/igabhvra";
        this.user = "igabhvra";
        this.password = "pM8kEKh0soYm_ZRR_DcpIlelPAFb2UFv";
    }

   /**
     * Connect to the PostgreSQL database
     *
     * @return a Connection object
     * @category connection
     * @throws java.sql.SQLException
     * @author AndrewF17
     */
    public Connection connect() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return DriverManager.getConnection(url, user, password);
    }

    /**
     * Insert a new Centro vaccinale
     * 
     * @return a Boolean
     * @category INSERT
     * @throws java.sql.SQLException
     * @author AndrewF17
     */
    public Boolean insertCentroVaccinale(CentroVaccinale object) {
        String sql = "INSERT INTO CentriVaccinali(nome, qualificatoreIndirizzo, nomeIndirizzo, numeroCivico, comune, provincia, CAP, tipologia) "
                + "VALUES(?,?::qualificatoreindirizzo,?,?,?,?,?,?::tipologiacentrovaccinale)";
        try (
            Connection connection = connect();
            PreparedStatement statement = connection.prepareStatement(sql);) {
                statement.setString(1, object.getNome());
                statement.setString(2, object.getQualificatoreIndirizzo().name());
                statement.setString(3, object.getNomeIndirizzo());
                statement.setString(4, object.getNumeroCivico());
                statement.setString(5, object.getComune());
                statement.setString(6, object.getProvincia());
                statement.setString(7, object.getCAP());
                statement.setString(8, object.getTipologiaCentroVaccinale().name());

                statement.executeQuery();
            } catch (SQLException exception) {
                System.err.println(exception.getMessage());
                return false;
            }
        return true;
    }

    /**
     * Delete Centro vaccinale
     * 
     * @return a Boolean
     * @category DELETE
     * @throws java.sql.SQLException
     * @author AndrewF17
     */
    public Boolean deletetCentroVaccinale(CentroVaccinale object) {
        String sql = "DELETE FROM CentriVaccinali WHERE nome = ?";
        try (
            Connection connection = connect();
            PreparedStatement statement = connection.prepareStatement(sql);) {
                statement.setString(1, object.getNome());
                statement.executeQuery();
            } catch (SQLException exception) {
                System.err.println(exception.getMessage());
                return false;
            }
        return true;
    }

    /**
     * Get all Centri vaccinali
     * 
     * @return List<CentroVaccinale>
     * @category SELECT
     * @throws java.sql.SQLException
     * @author AndrewF17
     */
    public List<CentroVaccinale> getAllCentriVaccinali() {
        String sql = "SELECT * FROM CentriVaccinali";
        List<CentroVaccinale> resl = new ArrayList<CentroVaccinale>();
        try (
            Connection connection = connect();
            PreparedStatement statement = connection.prepareStatement(sql);) {
                ResultSet rs = statement.executeQuery();
                if (!rs.wasNull()) {
                    while (rs.next()) {
                        CentroVaccinale centro = new CentroVaccinale(rs.getString("nome"), 
                                                                    QualificatoreIndirizzo.valueOf(rs.getString("qualificatoreIndirizzo")),
                                                                    rs.getString("nomeIndirizzo"),
                                                                    rs.getString("numeroCivico"),
                                                                    rs.getString("comune"),
                                                                    rs.getString("provincia"),
                                                                    rs.getString("cap"),
                                                                    TipologiaCentroVaccinale.valueOf(rs.getString("tipologia")));
                        resl.add(centro);
                    }
                }
                
            } catch (SQLException exception) {
                System.err.println(exception.getMessage());

            }
        return resl;
    }
    
    /**
     * Select Centro vaccinale by Comune
     * 
     * @return List<CentroVaccinale>
     * @category SELECT
     * @throws java.sql.SQLException
     * @author AndrewF17
     */
    public List<CentroVaccinale> getCentriVaccinaliByComune(String comune) {
        String sql = "SELECT * FROM CentriVaccinali WHERE comune = ?";
        List<CentroVaccinale> resl = new ArrayList<CentroVaccinale>();
        try (
            Connection connection = connect();
            PreparedStatement statement = connection.prepareStatement(sql);) {
                statement.setString(1, comune);
                ResultSet rs = statement.executeQuery();
                if (!rs.wasNull()) {
                    while (rs.next()) {
                        CentroVaccinale centro = new CentroVaccinale(rs.getString("nome"), 
                                                                    QualificatoreIndirizzo.valueOf(rs.getString("qualificatoreIndirizzo")),
                                                                    rs.getString("nomeIndirizzo"),
                                                                    rs.getString("numeroCivico"),
                                                                    rs.getString("comune"),
                                                                    rs.getString("provincia"),
                                                                    rs.getString("cap"),
                                                                    TipologiaCentroVaccinale.valueOf(rs.getString("tipologia")));
                        resl.add(centro);
                    }
                }
                
            } catch (SQLException exception) {
                System.err.println(exception.getMessage());

            }
        return resl;
    }

    /**
     * Select Centro vaccinale by id (nome)
     * 
     * @return CentroVaccinale
     * @category SELECT
     * @throws java.sql.SQLException
     * @author AndrewF17
     */
    public CentroVaccinale getCentroVaccinaleById(String id) {
        String sql = "SELECT * FROM CentriVaccinali WHERE nome = ?";
        CentroVaccinale resl = null;
        try (
            Connection connection = connect();
            PreparedStatement statement = connection.prepareStatement(sql);) {
                statement.setString(1, id);
                ResultSet rs = statement.executeQuery();
                if (!rs.wasNull()) {
                    while (rs.next()) {
                        resl = new CentroVaccinale(rs.getString("nome"), 
                                                                    QualificatoreIndirizzo.valueOf(rs.getString("qualificatoreIndirizzo")),
                                                                    rs.getString("nomeIndirizzo"),
                                                                    rs.getString("numeroCivico"),
                                                                    rs.getString("comune"),
                                                                    rs.getString("provincia"),
                                                                    rs.getString("cap"),
                                                                    TipologiaCentroVaccinale.valueOf(rs.getString("tipologia")));
                    }
                }
                
            } catch (SQLException exception) {
                System.err.println(exception.getMessage());

            }
        return resl;
    }

    /**
     * Select Centro vaccinale by Provincia
     * 
     * @return List<CentroVaccinale>
     * @category SELECT
     * @throws java.sql.SQLException
     * @author AndrewF17
     */
    public List<CentroVaccinale> getCentriVaccinaliByProvincia(String provincia) {
        String sql = "SELECT * FROM CentriVaccinali WHERE provincia = ?";
        List<CentroVaccinale> resl = new ArrayList<CentroVaccinale>();
        try (
            Connection connection = connect();
            PreparedStatement statement = connection.prepareStatement(sql);) {
                statement.setString(1, provincia);
                ResultSet rs = statement.executeQuery();
                if (!rs.wasNull()) {
                    while (rs.next()) {
                        CentroVaccinale centro = new CentroVaccinale(rs.getString("nome"), 
                                                                    QualificatoreIndirizzo.valueOf(rs.getString("qualificatoreIndirizzo")),
                                                                    rs.getString("nomeIndirizzo"),
                                                                    rs.getString("numeroCivico"),
                                                                    rs.getString("comune"),
                                                                    rs.getString("provincia"),
                                                                    rs.getString("cap"),
                                                                    TipologiaCentroVaccinale.valueOf(rs.getString("tipologia")));
                        resl.add(centro);
                    }
                }
                
            } catch (SQLException exception) {
                System.err.println(exception.getMessage());

            }
        return resl;
    }

    /**
     * Select Centro vaccinale by CAP
     * 
     * @return List<CentroVaccinale>
     * @category SELECT
     * @throws java.sql.SQLException
     * @author AndrewF17
     */
    public List<CentroVaccinale> getCentriVaccinaliByCAP(String cap) {
        String sql = "SELECT * FROM CentriVaccinali WHERE cap = ?";
        List<CentroVaccinale> resl = new ArrayList<CentroVaccinale>();
        try (
            Connection connection = connect();
            PreparedStatement statement = connection.prepareStatement(sql);) {
                statement.setString(1, cap);
                ResultSet rs = statement.executeQuery();
                if (!rs.wasNull()) {
                    while (rs.next()) {
                        CentroVaccinale centro = new CentroVaccinale(rs.getString("nome"), 
                                                                    QualificatoreIndirizzo.valueOf(rs.getString("qualificatoreIndirizzo")),
                                                                    rs.getString("nomeIndirizzo"),
                                                                    rs.getString("numeroCivico"),
                                                                    rs.getString("comune"),
                                                                    rs.getString("provincia"),
                                                                    rs.getString("cap"),
                                                                    TipologiaCentroVaccinale.valueOf(rs.getString("tipologia")));
                        resl.add(centro);
                    }
                }
                
            } catch (SQLException exception) {
                System.err.println(exception.getMessage());

            }
        return resl;
    }

    /**
     * Select Centro vaccinale by Tipologia
     * 
     * @return List<CentroVaccinale>
     * @category SELECT
     * @throws java.sql.SQLException
     * @author AndrewF17
     */
    public List<CentroVaccinale> getCentriVaccinaliByComune(TipologiaCentroVaccinale type) {
        String sql = "SELECT * FROM CentriVaccinali WHERE tipologia = ?::tipologiacentrovaccinale";
        List<CentroVaccinale> resl = new ArrayList<CentroVaccinale>();
        try (
            Connection connection = connect();
            PreparedStatement statement = connection.prepareStatement(sql);) {
                statement.setString(1, type.name());
                ResultSet rs = statement.executeQuery();
                if (!rs.wasNull()) {
                    while (rs.next()) {
                        CentroVaccinale centro = new CentroVaccinale(rs.getString("nome"), 
                                                                    QualificatoreIndirizzo.valueOf(rs.getString("qualificatoreIndirizzo")),
                                                                    rs.getString("nomeIndirizzo"),
                                                                    rs.getString("numeroCivico"),
                                                                    rs.getString("comune"),
                                                                    rs.getString("provincia"),
                                                                    rs.getString("cap"),
                                                                    TipologiaCentroVaccinale.valueOf(rs.getString("tipologia")));
                        resl.add(centro);
                    }
                }
                
            } catch (SQLException exception) {
                System.err.println(exception.getMessage());

            }
        return resl;
    }
}
