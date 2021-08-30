package unidevteam.enumerators;

import java.util.Arrays;
import java.util.Optional;

public enum TipoVaccino {
    PFIZER("Pfizer"),
    ASTRAZENECA("Astrazeneca"),
    MODERNA("Moderna"),
    J_AND__J("Johnson&Johnson");

    private final String value;

    public String getValue() { return value; }

    TipoVaccino(String value) {
        this.value = value;
    }

    public static Optional<TipoVaccino> valueFromString(String value) {
        return Arrays.stream(values())
        .filter(q -> q.value == value)
        .findFirst();
    }

    public int getSize() {
        return values().length;
    }
}
