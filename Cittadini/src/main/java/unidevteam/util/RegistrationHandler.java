package unidevteam.util;

import unidevteam.classes.*;

public class RegistrationHandler {
    private static Cittadino cittadino;

    public static Cittadino getCittadino() { return cittadino; }

    public static void setData1(String nome, String cognome, String codiceFiscale, String email) {
        cittadino = new Cittadino();

        cittadino.setNome(nome);
        cittadino.setCognome(cognome);
        cittadino.setCodiceFiscale(codiceFiscale);
        cittadino.setEmail(email);
    }

    public static void setData2(String idVaccinazione, String password) {
        cittadino.setIdVaccinazione(idVaccinazione);

        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        System.out.println(hashedPassword);
        cittadino.setHashedPassword(hashedPassword);
    }
}
