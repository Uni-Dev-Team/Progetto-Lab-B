package unidevteam.util;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import unidevteam.classes.*;
import unidevteam.enumerators.*;

//  TODO: Controllare i return dei boolean non sembrano corretti

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

    private static DBManager instance = null;

    public static DBManager getInstance() throws Exception {
        if(instance != null) return instance;
        throw new Exception("Database manager is not initialized.");
    }

    public static DBManager getInstance(String hostname, String dbName, String user, String password) {
        if(instance == null) instance = new DBManager(hostname, dbName, user, password);

        return instance;
    }

    private DBManager(String hostname, String dbName, String user, String password) {
        this.url = "jdbc:postgresql://"+ hostname +"/" + dbName;
        this.user = user;
        this.password = password;
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
     * It gets a valid id
     * 
     * @return String
     * @category INSERT
     * @author AndrewF17
     */
    public String getValidId(String columnName, String tableName) {
       String sql = "SELECT COUNT("+ columnName +") FROM "+ tableName + " WHERE " + columnName +"=?";
       String resl;
       int count = 0;
       ResultSet sqlResultSet;
       do {
        resl = Generator.getAlphaNumericString(16);
        try (
            Connection connection = connect();
            PreparedStatement statement = connection.prepareStatement(sql);) {
                statement.setString(1, resl);
                sqlResultSet = statement.executeQuery();
                while (sqlResultSet.next()) {
                    count = sqlResultSet.getInt(1);
                }
            }
        catch (SQLException e) {
            e.printStackTrace();
        }
       } while(count > 0);

       System.out.println(resl);

       return resl;
    }
    /**
     * Insert a new Centro vaccinale
     * 
     * @return a String
     * @category INSERT
     * @throws java.sql.SQLException
     * @author AndrewF17
     */
    public String addCentroVaccinale(CentroVaccinale object) {
        String sql = "INSERT INTO CentriVaccinali(id, nome, qualificatoreIndirizzo, nomeIndirizzo, numeroCivico, comune, provincia, CAP, tipologia) "
                + "VALUES(?,?,?::qualificatoreindirizzo,?,?,?,?,?,?::tipologiacentrovaccinale);";
        try (
            Connection connection = connect();
            PreparedStatement statement = connection.prepareStatement(sql);
            ) {
                statement.setString(1, object.getId());
                statement.setString(2, object.getNome());
                statement.setString(3, object.getQualificatoreIndirizzo().name());
                statement.setString(4, object.getNomeIndirizzo());
                statement.setString(5, object.getNumeroCivico());
                statement.setString(6, object.getComune());
                statement.setString(7, object.getProvincia());
                statement.setString(8, object.getCAP());
                statement.setString(9, object.getTipologiaCentroVaccinale().name());

                System.out.println(statement.toString());

                statement.executeUpdate();
                return object.getId();
            } catch (SQLException exception) {
                System.err.println(exception.getMessage());
                return null;
            }
        
    }


    /**
     * Insert a new Cittadino
     * 
     * @return a Boolean
     * @category INSERT
     * @throws java.sql.SQLException
     * @author AndrewF17
     */
    public Boolean addCittadino(Cittadino object) {
        String sql = "INSERT INTO Cittadini_Registrati(codiceFiscale, nome, cognome, email, idVaccinazione, passwd) VALUES (?,?,?,?,?,?)";

        System.out.println("Password hashata: " + object.getHashedPassword());

        try (
            Connection connection = connect();
            PreparedStatement statement = connection.prepareStatement(sql);) {
                statement.setString(1, object.getCodiceFiscale());
                statement.setString(2, object.getNome());
                statement.setString(3, object.getCognome());
                statement.setString(4, object.getEmail());
                statement.setString(5, object.getIdVaccinazione());
                statement.setString(6, object.getHashedPassword());

                statement.executeUpdate();
                return true;
        } catch (SQLException exception) {
            System.err.println(exception.getMessage());
            return false;
        }   
    }

    public String addVaccinato(String idVaccinazione, String nomeCittadino, String cognomeCittadino, 
                                String codiceFiscale, Date dataSomministrazione, TipoVaccino typeVaccino, String idCentro) {
        String sql = "INSERT INTO Vaccinati(id, nomeCittadino, cognomeCittadino, codiceFiscale, dataSomministrazione, tipoVaccino, idCentro) VALUES (?,?,?,?,?,?::TipoVaccino,?)";
        try (
            Connection connection = connect();
            PreparedStatement statement = connection.prepareStatement(sql);) {
                statement.setString(1, idVaccinazione);
                statement.setString(2, nomeCittadino);
                statement.setString(3, cognomeCittadino);
                statement.setString(4, codiceFiscale);
                statement.setDate(5, dataSomministrazione);
                statement.setString(6, typeVaccino.name().toUpperCase());
                statement.setString(7, idCentro);
                statement.executeUpdate();
                return idVaccinazione;
        } catch (SQLException exception) {
            System.err.println(exception.getMessage());
            return null;
        }
        
        
    }

    /**
     * Delete Centro vaccinale
     * 
     * @return a Boolean
     * @category DELETE
     * @throws java.sql.SQLException
     * @author AndrewF17
     */
    public Boolean deleteCentroVaccinale(CentroVaccinale object) {
        String sql = "DELETE FROM CentriVaccinali WHERE nome = ?";
        try (
            Connection connection = connect();
            PreparedStatement statement = connection.prepareStatement(sql);) {
                statement.setString(1, object.getNome());
                statement.executeUpdate();
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
                        CentroVaccinale centro = new CentroVaccinale(   rs.getString("id"),
                                                                        rs.getString("nome"), 
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
                        CentroVaccinale centro = new CentroVaccinale(   rs.getString("id"),
                                                                        rs.getString("nome"), 
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
        String sql = "SELECT * FROM CentriVaccinali WHERE id = ?";
        CentroVaccinale resl = null;
        try (
            Connection connection = connect();
            PreparedStatement statement = connection.prepareStatement(sql);) {
                statement.setString(1, id);
                ResultSet rs = statement.executeQuery();
                if (!rs.wasNull()) {
                    while (rs.next()) {
                        resl = new CentroVaccinale( rs.getString("id"),
                                                    rs.getString("nome"), 
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
                        CentroVaccinale centro = new CentroVaccinale(   rs.getString("id"),
                                                                        rs.getString("nome"), 
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
                        CentroVaccinale centro =new CentroVaccinale(    rs.getString("id"),
                                                                        rs.getString("nome"), 
                                                                        QualificatoreIndirizzo.valueOf(rs.getString("qualificatoreIndirizzo")),
                                                                        rs.getString("nomeIndirizzo"),
                                                                        rs.getString("numeroCivico"),
                                                                        rs.getString("comune"),
                                                                        rs.getString("provincia"),
                                                                        rs.getString("cap"),
                                                                        TipologiaCentroVaccinale.valueOf(rs.getString("tipologia")));
                                                                        resl.add(centro);
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
                        CentroVaccinale centro = new CentroVaccinale(   rs.getString("id"),
                                                                        rs.getString("nome"), 
                                                                        QualificatoreIndirizzo.valueOf(rs.getString("qualificatoreIndirizzo")),
                                                                        rs.getString("nomeIndirizzo"),
                                                                        rs.getString("numeroCivico"),
                                                                        rs.getString("comune"),
                                                                        rs.getString("provincia"),
                                                                        rs.getString("cap"),
                                                                        TipologiaCentroVaccinale.valueOf(rs.getString("tipologia")));
                                                                        resl.add(centro);
                                                                        resl.add(centro);
                    }
                }
                
            } catch (SQLException exception) {
                System.err.println(exception.getMessage());
            }
        return resl;
    }

    /**
     * get Numbers of CentriVaccinali
     * 
     * @return long
     * @category COUNT
     * @throws java.sql.SQLException
     * @author AndrewF17
     */
    public long getCountCentriVaccinali() {
        String sql = "SELECT COUNT(nome) FROM CentriVaccinali";
         try (
            Connection connection = connect();
            PreparedStatement statement = connection.prepareStatement(sql);) {
                ResultSet rs = statement.executeQuery();
                while(rs.next()){
                        return rs.getLong(1);
                }
            } catch (SQLException exception) {
                System.err.println(exception.getMessage());
            }
        return 0;
    }

    /**
     * get Numbers of Cittadini
     * 
     * @return long
     * @category COUNT
     * @throws java.sql.SQLException
     * @author AndrewF17
     */
    public long getCountCittadini() {
        String sql = "SELECT COUNT(codiceFiscale) FROM Cittadini_Registrati";
         try (
            Connection connection = connect();
            PreparedStatement statement = connection.prepareStatement(sql);) {
                ResultSet rs = statement.executeQuery();
                while(rs.next()){
                        return rs.getLong(1);
                }
            } catch (SQLException exception) {
                System.err.println(exception.getMessage());
            }
        return 0;
    }

    public long getCountEventiAvversi() {
        String sql = "SELECT COUNT(*) FROM Eventi_Avversi";
        try (
            Connection connection = connect();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                return rs.getLong(1);
            }
        } catch (SQLException exception) {
            System.err.println(exception.getMessage());
        }
        return 0;
    }

    public Cittadino autenticaUtente(String email, String plainPassword) {
        String sql = "SELECT * FROM Cittadini_Registrati WHERE email = ?";
        Cittadino res = null;
        try (
            Connection connection = connect();
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setString(1, email);
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                String hashedPsw = rs.getString(6);
                if(BCrypt.checkpw(plainPassword, hashedPsw)) {
                    String _idVaccinazione = rs.getString(1);
                    String _nome = rs.getString(2);
                    String _cognome = rs.getString(3);
                    String _email = rs.getString(4);
                    String _codiceFiscale = rs.getString(5);

                    res = new Cittadino(_nome, _cognome, _codiceFiscale, _email, _idVaccinazione);
                }
            }
        } catch (SQLException exception) {
            System.err.println(exception.getMessage());
        }

        return res;
    }
}
