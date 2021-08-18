/* CentroVaccini v1.0 */
/* -------------------------- */
/* Written by: Andrea Ferrario, Loschiavo Christian, Ivan Giubilei, Nicolo' Rossi */
/* -------------------------- */
/* DATA DEFINITION */

/* Enum Definition */
CREATE TYPE QualificatoreIndirizzo AS ENUM ('Via', 'Viale', 'Piazza');
CREATE TYPE TipologiaCentroVaccinale AS ENUM ('Ospedaliero', 'Aziendale', 'Hub');
CREATE TYPE TipoEvento AS ENUM ('mal di testa', 'febbre', 'dolori muscolari e articolari', 'linfoadenopatia', 'tachicardia', 'crisi ipertensiva');
CREATE TYPE TipoVaccino AS ENUM ('Pfizer', 'AstraZeneca', 'Moderna', 'J&J');

/* Table Definition */
CREATE TABLE CentriVaccinali (
    nome VARCHAR(40) NOT NULL PRIMARY KEY,
    qualificatoreIndirizzo QualificatoreIndirizzo NOT NULL,
    nomeIndirizzo VARCHAR(50) NOT NULL,
    numeroCivico VARCHAR(5) NOT NULL,
    comune VARCHAR(50) NOT NULL,
    provincia CHAR(2) NOT NULL,
    CAP CHAR(5) NOT NULL,
    tipologia TipologiaCentroVaccinale NOT NULL
);

/* Questa definizione sarà soltanto un modello da utilizzare per la creazione dinamica
   della tabella vaccinati relativa ad un centro vaccinale particolare */
CREATE TABLE Vaccinati_NomeCentro (
    nomeCentro VARCHAR(40) NOT NULL PRIMARY KEY,
    nomeCittadino VARCHAR(40) NOT NULL,
    cognomeCittadino VARCHAR(40) NOT NULL,
    codiceFiscale CHAR(16) NOT NULL UNIQUE,
    dataSomministrazione DATE NOT NULL,
    tipoVaccino TipoVaccino NOT NULL,
    idVaccinazione CHAR(8) NOT NULL UNIQUE,

    FOREIGN KEY (nomeCentro) REFERENCES CentriVaccinali(nome)
    ON UPDATE CASCADE
    ON DELETE CASCADE
);

CREATE TABLE Cittadini_Registrati(
    codiceFiscale CHAR(16) NOT NULL PRIMARY KEY,
    nome VARCHAR(40) NOT NULL,
    cognome VARCHAR(40) NOT NULL,
    email VARCHAR(40) NOT NULL UNIQUE,
    idVaccinazione CHAR(8) NOT NULL UNIQUE,
    passwd CHAR(60) NOT NULL,

    /* Vaccinati_NomeCentro da sostituire con il nome del centro in cui si è vaccinato */
    FOREIGN KEY (idVaccinazione) REFERENCES Vaccinati_NomeCentro(idVaccinazione)
    ON UPDATE CASCADE
    ON DELETE CASCADE
);

CREATE TABLE Eventi_Avversi (
    idEvento SERIAL NOT NULL PRIMARY KEY,
    tipoEvento TipoEvento NOT NULL,
    tipoVaccino TipoVaccino NOT NULL,
    gradoSeverita SMALLINT CHECK (gradoSeverita < 5 AND gradoSeverita > 1) NOT NULL,
    dataSomministrazione DATE CHECK (dataAvvenimento > dataSomministrazione) NOT NULL,
    dataAvvenimento DATE CHECK (dataAvvenimento > dataSomministrazione) NOT NULL,
    note VARCHAR(256)
);




