package unidevteam.classes;

import java.io.Serializable;

import unidevteam.enumerators.TipoEvento;

public class DatiExtraCentroVaccinale implements Serializable {
    // Serializable ID
    private static final long serialVersionUID = 1;

    private int numeroEventiAvversi;
    private double gradoSeveritaMedio;
    private TipoEvento eventoPiuFrequente;

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public int getNumeroEventiAvversi() {
        return numeroEventiAvversi;
    }
    
    public void setNumeroEventiAvversi(int numeroEventiAvversi) {
        this.numeroEventiAvversi = numeroEventiAvversi;
    }

    public double getGradoSeveritaMedio() {
        return gradoSeveritaMedio;
    }

    public void setGradoSeveritaMedio(double gradoSeveritaMedio) {
        this.gradoSeveritaMedio = gradoSeveritaMedio;
    }

    public TipoEvento getEventoPiuFrequente() {
        return eventoPiuFrequente;
    }

    public void setEventoPiuFrequente(TipoEvento eventoPiuFrequente) {
        this.eventoPiuFrequente = eventoPiuFrequente;
    }

    public DatiExtraCentroVaccinale(int numeroEventiAvversi, double gradoSeveritaMedio, TipoEvento eventoPiuFrequente) {
        this.numeroEventiAvversi = numeroEventiAvversi;
        this.gradoSeveritaMedio = gradoSeveritaMedio;
        this.eventoPiuFrequente = eventoPiuFrequente;
    }

    @Override
    public String toString() {
        return "DatiExtraCentroVaccinale [eventoPiuFrequente=" + eventoPiuFrequente + ", gradoSeveritaMedio="
                + gradoSeveritaMedio + ", numeroEventiAvversi=" + numeroEventiAvversi + "]";
    }
}
