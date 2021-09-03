package unidevteam.classes;

import java.io.Serializable;
import java.sql.Date;
import unidevteam.enumerators.TipoEvento;
import unidevteam.enumerators.TipoVaccino;

public class EventoAvverso implements Serializable {
    // Serializable ID
    private static final long serialVersionUID = 1;

    private int idEvento;
    private TipoEvento tipoEvento;
    private TipoVaccino tipoVaccino;
    private int gradoSeverita;
    private Date dataSomministrazione;
    private Date dataAvventimento;
    private String note;

    public int getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(int idEvento) {
        this.idEvento = idEvento;
    }

    public TipoEvento getTipoEvento() {
        return tipoEvento;
    }

    public void setTipoEvento(TipoEvento tipoEvento) {
        this.tipoEvento = tipoEvento;
    }

    public TipoVaccino getTipoVaccino() {
        return tipoVaccino;
    }

    public void setTipoVaccino(TipoVaccino tipoVaccino) {
        this.tipoVaccino = tipoVaccino;
    }

    public int getGradoSeverita() {
        return gradoSeverita;
    }

    public void setGradoSeverita(int gradoSeverita) {
        this.gradoSeverita = gradoSeverita;
    }

    public Date getDataSomministrazione() {
        return dataSomministrazione;
    }

    public void setDataSomministrazione(Date dataSomministrazione) {
        this.dataSomministrazione = dataSomministrazione;
    }

    public Date getDataAvventimento() {
        return dataAvventimento;
    }

    public void setDataAvventimento(Date dataAvventimento) {
        this.dataAvventimento = dataAvventimento;
    }

    public String getNote(){
        return this.note;
    }

    public void setNote(String note){
        this.note = note;
    }

    public EventoAvverso() {}

    public EventoAvverso(int idEvento, TipoEvento tipoEvento, TipoVaccino tipoVaccino, int gradoSeverita, Date dataSomministrazione, Date dataAvventimento) {
        this.idEvento = idEvento;
        this.tipoEvento = tipoEvento;
        this.tipoVaccino = tipoVaccino;
        this.gradoSeverita = gradoSeverita;
        this.dataSomministrazione = dataSomministrazione;
        this.dataAvventimento = dataAvventimento;
    }

    @Override
    public String toString() {
        return "EventoAvverso [dataAvventimento=" + dataAvventimento + ", dataSomministrazione=" + dataSomministrazione
                + ", gradoSeverita=" + gradoSeverita + ", idEvento=" + idEvento + ", note=" + note + ", tipoEvento="
                + tipoEvento + ", tipoVaccino=" + tipoVaccino + "]";
    }
}
