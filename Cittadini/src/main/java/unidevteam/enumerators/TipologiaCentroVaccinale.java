package unidevteam.enumerators;

import java.util.Arrays;
import java.util.Optional;

public enum TipologiaCentroVaccinale {
    OSPEDALIERO("Ospedaliero"),
    AZIENDALE("Aziendale"),
    HUB("HUB");
    
    private final String value;

    public String getValue() { return value; }

    TipologiaCentroVaccinale(String value) {
        this.value = value;
    }

    public static Optional<TipologiaCentroVaccinale> valueFromString(String value) {
        return Arrays.stream(values())
        .filter(q -> q.value == value)
        .findFirst();
    }

    public int getSize() {
        return values().length;
    }
}
