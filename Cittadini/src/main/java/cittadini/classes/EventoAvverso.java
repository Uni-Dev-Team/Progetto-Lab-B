/**
 * Christian Loschiavo 739894 VA
 * Ivan Giubilei 739892 VA
 * Nicolò Rossi 742626 VA
 * Andrea Ferrario 740485 VA
 */

package cittadini.classes;

import java.io.Serializable;
import java.sql.Date;

import cittadini.enumerators.TipoEvento;
import cittadini.enumerators.TipoVaccino;
/**
 * Struttura dati per la gestione degli eventi avversi
 * Possiede getter e setter per impostare i valori chiave della classe
 * Serializzabile in quanto utilizzata assieme all'RMI
 */
public class EventoAvverso implements Serializable {
    // Serializable ID
    private static final long serialVersionUID = 1;

    private int idEvento;
    private TipoEvento tipoEvento;
    private TipoVaccino tipoVaccino;
    private int gradoSeverita;
    private Date dataSomministrazione;
    private Date dataAvvenimento;
    private String note;

    /**
     * 
     * @return id evento
     */
    public int getIdEvento() {
        return idEvento;
    }

    /**
     * 
     * @param idEvento
     */
    public void setIdEvento(int idEvento) {
        this.idEvento = idEvento;
    }

    public TipoEvento getTipoEvento() {
        return tipoEvento;
    }

    /**
     * 
     * @param tipoEvento
     */
    public void setTipoEvento(TipoEvento tipoEvento) {
        this.tipoEvento = tipoEvento;
    }

    /**
     * 
     * @return tipo vaccino {@link cittadini.enumerators.TipoEvento#TipoEvento() TipoVaccino}
     */
    public TipoVaccino getTipoVaccino() {
        return tipoVaccino;
    }

    /**
     * 
     * @param tipoVaccino
     */
    public void setTipoVaccino(TipoVaccino tipoVaccino) {
        this.tipoVaccino = tipoVaccino;
    }

    /**
     * 
     * @return grado di severità
     */
    public int getGradoSeverita() {
        return gradoSeverita;
    }

    /**
     * 
     * @param gradoSeverita
     */
    public void setGradoSeverita(int gradoSeverita) {
        this.gradoSeverita = gradoSeverita;
    }

    /**
     * 
     * @return data di somministrazione
     */
    public Date getDataSomministrazione() {
        return dataSomministrazione;
    }

    /**
     * 
     * @param dataSomministrazione
     */
    public void setDataSomministrazione(Date dataSomministrazione) {
        this.dataSomministrazione = dataSomministrazione;
    }

    /**
     * 
     * @return data dell'avvenimento
     */
    public Date getDataAvvenimento() {
        return dataAvvenimento;
    }

    /**
     * 
     * @param dataAvvenimento
     */
    public void setDataAvventimento(Date dataAvvenimento) {
        this.dataAvvenimento = dataAvvenimento;
    }

    /**
     * 
     * @return note aggiuntive
     */
    public String getNote(){
        return this.note;
    }

    /**
     * 
     * @param note
     */
    public void setNote(String note){
        this.note = note;
    }

    public EventoAvverso() {}

    /**
     * @param idEvento id dell'evento
     * @param tipoEvento tipo di evento {@link cittadini.enumerators.TipoEvento#TipoEvento() TipoEvento} 
     * @param tipoVaccino tipo di vaccino {@link cittadini.enumerators.TipoEvento#TipoEvento() TipoVaccino}
     * @param gradoSeverita grado di severità
     * @param dataSomministrazione data di somministrazione
     * @param dataAvvenimento data di avvenimento
     */
    public EventoAvverso(int idEvento, TipoEvento tipoEvento, TipoVaccino tipoVaccino, int gradoSeverita, Date dataSomministrazione, Date dataAvventimento) {
        this.idEvento = idEvento;
        this.tipoEvento = tipoEvento;
        this.tipoVaccino = tipoVaccino;
        this.gradoSeverita = gradoSeverita;
        this.dataSomministrazione = dataSomministrazione;
        this.dataAvvenimento = dataAvventimento;
    }

    @Override
    public String toString() {
        return "EventoAvverso [dataAvventimento=" + dataAvvenimento + ", dataSomministrazione=" + dataSomministrazione
                + ", gradoSeverita=" + gradoSeverita + ", idEvento=" + idEvento + ", note=" + note + ", tipoEvento="
                + tipoEvento + ", tipoVaccino=" + tipoVaccino + "]";
    }
}
