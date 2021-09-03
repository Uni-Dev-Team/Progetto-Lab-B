package unidevteam.classes;

import java.io.Serializable;

import unidevteam.enumerators.QualificatoreIndirizzo;
import unidevteam.enumerators.TipologiaCentroVaccinale;

public class CentroVaccinale implements Serializable {
    // Serializable ID
    private static final long serialVersionUID = 1;

    private String id;
    private String nome;
    private QualificatoreIndirizzo qualificatoreIndirizzo;
    private String nomeIndirizzo;
    private String numeroCivico;
    private String comune;
    private String provincia;
    private String CAP;
    private TipologiaCentroVaccinale tipologiaCentroVaccinale;

    public CentroVaccinale(String id, String nome, QualificatoreIndirizzo qualificatoreIndirizzo, String nomeIndirizzo,
            String numeroCivico, String comune, String provincia, String cap, TipologiaCentroVaccinale tipologiaCentroVaccinale) {
        this.id = id;
        this.nome = nome;
        this.qualificatoreIndirizzo = qualificatoreIndirizzo;
        this.nomeIndirizzo = nomeIndirizzo;
        this.numeroCivico = numeroCivico;
        this.comune = comune;
        this.provincia = provincia;
        this.CAP = cap;
        this.tipologiaCentroVaccinale = tipologiaCentroVaccinale;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public QualificatoreIndirizzo getQualificatoreIndirizzo() {
        return qualificatoreIndirizzo;
    }

    public void setQualificatoreIndirizzo(QualificatoreIndirizzo qualificatoreIndirizzo) {
        this.qualificatoreIndirizzo = qualificatoreIndirizzo;
    }

    public String getNomeIndirizzo() {
        return nomeIndirizzo;
    }

    public void setNomeIndirizzo(String nomeIndirizzo) {
        this.nomeIndirizzo = nomeIndirizzo;
    }

    public String getNumeroCivico() {
        return numeroCivico;
    }

    public void setNumeroCivico(String numeroCivico) {
        this.numeroCivico = numeroCivico;
    }

    public String getComune() {
        return comune;
    }

    public void setComune(String comune) {
        this.comune = comune;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getCAP() {
        return CAP;
    }

    public void setCAP(String cAP) {
        CAP = cAP;
    }

    public TipologiaCentroVaccinale getTipologiaCentroVaccinale() {
        return tipologiaCentroVaccinale;
    }

    public void setTipologiaCentroVaccinale(TipologiaCentroVaccinale tipologiaCentroVaccinale) {
        this.tipologiaCentroVaccinale = tipologiaCentroVaccinale;
    }

    @Override
    public String toString() {
        return nome;
    }
}
