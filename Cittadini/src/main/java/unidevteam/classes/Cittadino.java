package unidevteam.classes;

public class Cittadino {
    private String nome;
    private String cognome;
    private String codiceFiscale;
    private String email;
    private String idVaccinazione;
    // Password? Hash password)

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
    public String getCodiceFiscale() {
        return codiceFiscale;
    }
    public void setCodiceFiscale(String codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
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

    public Cittadino(String nome, String cognome, String codiceFiscale, String email, String idVaccinazione) {
        this.nome = nome;
        this.cognome = cognome;
        this.codiceFiscale = codiceFiscale;
        this.email = email;
        this.idVaccinazione = idVaccinazione;
    }

    @Override
    public String toString() {
        return "Cittadino [codiceFiscale=" + codiceFiscale + ", cognome=" + cognome + ", email=" + email
                + ", idVaccinazione=" + idVaccinazione + ", nome=" + nome + "]";
    }
}
