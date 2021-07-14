# Funzionalità progetto

## Enumeratori generali
```java
class Enumeratori {
    public static enum QualificatoreIndirizzo { Via, Viale, Piazza };
    public static enum TipologiaCentro { Ospedaliero, Aziendale, Hub };
    public static enum TipoVaccino { Pfizer, AstraZeneca, Moderna, J&J };
    public static enum TipoEvento { mal di testa, febbre, dolori_muscolari_e_articolari, linfoadenopatia, tachicardia, crisi_ipertensiva };
}
```

## Classi generali
```java
class Indirizzo {
    private Enumeratori.QualificatoreIndirizzo qualificatore;
    private String nomeIndirizzo;
    private int numeroCivico;
    private String comune;
    private String sigliaProvincia;
    private int CAP;

    // Getters e setters

    // Costruttore

    // Override di toString
}

class CentroVaccinale {
    private String nome;
    private Indirizzo indirizzo;
    private Enumeratori.TipologiaCentro tipologia;

    // Getters e setters

    // Costruttori

    // Override di toString
}

class EventoAvverso {
    private Enumeratori.TipoEvento tipoEvento;
    private int gradoSeverita;
    private String noteOpzionali;

    // Getters e setters

    // Costruttori

    // Override di toString
}
```

## 1. App Centri Vaccinali

```java
// Dichiarazione metodo da inserire nellinterfaccia RMI per la 
// registrazione di un nuovo centro vaccinale
// il ritorno del metodo è un bool e indica lesito delloperazione:
//
// true -> Registrazione andata a buon fine
// false -> Errore di qualsiasi tipo
bool registraCentroVaccinale(CentroVaccinale centroVaccinale);

// Dichiarazione metodo da inserire nellinterfaccia RMI per la 
// registrazione di un nuovo centro vaccinale
// il ritorno del metodo è un bool e indica lesito delloperazione:
//
// true -> Registrazione andata a buon fine
// false -> Errore di qualsiasi tipo
bool registraVaccinato(String nomeCentro, String nomeCittadino, String cognomeCittadino, String codiceFiscale, Date dataSomministrazione, Enumeratori.TipoVaccino tipoVaccino, String idVaccinazione);
```

## 2. App Cittadini

```java
class Cittadino {
    private String nome;
    private String cognome;
    private String codiceFiscale;
    private String email;
    private String idVaccinazione;

    // Getters e setters

    // Costruttori

    // Override di toString
}

// Dichiarazione metodo da inserire nellinterfaccia RMI per
// lottenimento dei nomi dei centri vaccinali che matchano
// il nome passato come parametro.
// Il ritorno è una lista di stringhe contenente tutti i nomi dei centri vaccinali
List<String> cercaCentroVaccinale(String nomeCentro);

// Dichiarazione metodo da inserire nellinterfaccia RMI per
// lottenimento dei nomi dei centri vaccinali situati nel comune e della
// stessa tipologia dei dati passati come parametro.
// Il ritorno è una lista di stringhe contenente tutti i nomi dei centri vaccinali
List<String> cercaCentroVaccinale(String comune, Enumeratori.TipologiaCentro tipologia);

// Dichiarazione metodo da inserire nellinterfaccia RMI per
// lottenimento del centro vaccinale che possiede il nome passato come parametro.
// Il ritorno è una lista di oggetti CentroVaccinale
List<CentroVaccinale> ottieniInfoCentroVaccinale(String nomeCentro);

// Dichiarazione metodo client-side per visualizzare la pagina
// di info complete relativa al centro vaccinale avente il nome
// passato per parametro.
// Questo metodo utilizza il metodo "ottieniInfoCentroVaccinale()"
// per ottenere tutti gli altri dati necessari da mostrare nella pagina
void visualizzaInfoCentroVaccinale(String nomeCentro);

// Dichiarazione metodo da inserire nellinterfaccia RMI per
// la registrazione del cittadino passato come parametro.
// il ritorno del metodo è un bool e indica l'esito dell'operazione:
//
// true -> Registrazione andata a buon fine
// false -> Errore di qualsiasi tipo
bool registraCittadino(Cittadino cittadino);

// Dichiarazione metodo da inserire nellinterfaccia RMI per
// la registrazione di un evento avverso passato come parametro.
// il ritorno del metodo è un bool e indica l'esito dell'operazione:
//
// true -> Registrazione andata a buon fine
// false -> Errore di qualsiasi tipo
bool inserisciEventiAvversi(EventoAvverso evento);
```