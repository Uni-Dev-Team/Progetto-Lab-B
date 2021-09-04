/**
 * Christian Loschiavo 739894 VA
 * Ivan Giubilei 739892 VA
 * Nicolò Rossi 742626 VA
 * Andrea Ferrario 740485 VA
 */

package server.util;

/**
 * Classe per generare valori
 */
public class Generator {
     /**
     * Genera stringa alfanumerica
     * 
     * @param int dimensione della stringa random
     * @return la stringa ottenuta
     */
    public static String getAlphaNumericString(int length) 
    { 
  
        // Sceglie un carattere casuale dalla stringa
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvxyz"; 
  
        // crea uno string buffer
        StringBuilder sb = new StringBuilder(length); 
  
        for (int i = 0; i < length; i++) { 
  
            // genera un numero casuale tra zero e la lunghezza della stringa usata per generare l'output
            int index = (int)(AlphaNumericString.length() * Math.random()); 
  
            // Aggiunge un carattere alla volta nella stringa di output
            sb.append(AlphaNumericString.charAt(index)); 
        } 
  
        return sb.toString(); 
    } 
}
