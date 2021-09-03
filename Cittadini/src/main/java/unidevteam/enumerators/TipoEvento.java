package unidevteam.enumerators;

public enum TipoEvento {
    MAL_DI_TESTA("Mal di testa"),
    FEBBRE("Febbre"),
    DOLORI_MUSCOLARI_ARTICOLARI("Dolori muscolari articolari"),
    LINFOADENOPATIA("Linfoadenopatia"),
    TACHICARDIA("Tachicardia"),
    CRISI_IPERTENSIVA("Crisi ipertensiva");

    private final String value;

    public String getValue() { return value; }

    TipoEvento(String value) {
        this.value = value;
    }

    public static TipoEvento valueFromString(String value) {
        switch(value) {
            case "Mal di testa":
                return TipoEvento.MAL_DI_TESTA;

            case "Febbre":
                return TipoEvento.FEBBRE;
            
            case "Dolori muscolari articolari":
                return TipoEvento.DOLORI_MUSCOLARI_ARTICOLARI;

            case "Linfoadenopatia":
                return TipoEvento.LINFOADENOPATIA;

            case "Tachicardia":
                return TipoEvento.TACHICARDIA;

            case "Crisi ipertensiva":
                return TipoEvento.CRISI_IPERTENSIVA;
        }

        return null;
    }

    public int getSize() {
        return values().length;
    }
}
