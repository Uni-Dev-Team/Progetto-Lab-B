package unidevteam.classes;

import unidevteam.util.BCrypt;
import unidevteam.util.DBManager;

public class Cittadino {
    private String codiceFiscale;
    private String nome;
    private String cognome;
    private String email;
    private String idVaccinazione;
    private String password;

    // Debugging porpuces
    public Cittadino() {
        this.codiceFiscale = "FSSDSA80A01L682N";
        this.nome = "DSA";
        this.cognome = "FASS";
        this.email = "a@a.a";
        try {
            this.idVaccinazione = DBManager.getInstance().getValidId("idVaccinazione", "cittadini_registrati");
        } catch (Exception e) {
            this.idVaccinazione = null;
        }
        this.password = BCrypt.gensalt(60);
    }

    public Cittadino(String codiceFiscale, String nome, String cognome, String email, String idVaccinazione, String password){
        this.codiceFiscale = codiceFiscale;
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.idVaccinazione = idVaccinazione;
        this.password = password;
    }



    public String getCodiceFiscale() {
        return codiceFiscale;
    }

    public void setCodiceFiscale(String codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdVaccinazione() {
        return idVaccinazione;
    }

    public void setIdVaccinazione(String idVaccinazione) {
        this.idVaccinazione = idVaccinazione;
    }

    public String getPassword() {
        return password;
    }

    // TODO: incriptazione della password
    public void setPassword(String password) {
        this.password = password;
    }
    
}