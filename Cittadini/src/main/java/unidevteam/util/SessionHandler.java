package unidevteam.util;

import unidevteam.classes.Cittadino;

public class SessionHandler {
    private static Cittadino utente = null;

    public static Cittadino getUtente() { return utente; }
    public static void setUtente(Cittadino c) { utente = c; }
}
