/* CentroVaccini v1.0 */
/* -------------------------- */
/* Written by: Andrea Ferrario, Loschiavo Christian, Ivan Giubilei, Nicolo' Rossi */
/* -------------------------- */
/* DATA DEFINITION */

/* Enum Definition */
CREATE TYPE TipoEvento AS ENUM ('mal di testa', 'febbre', 'dolori muscolari e articolari', 'linfoadenopatia', 'tachicardia', 'crisi ipertensiva');
CREATE TYPE TipoVaccino AS ENUM ('Pfizer', 'AstraZeneca', 'Moderna', 'J&J');

/* Table Definition */
CREATE TABLE Eventi_Avversi (
    dataAvvenimento date CHECK (dataAvvenimento > dataSomministrazione) NOT NULL,
    tipoVaccino TipoVaccino NOT NULL,
    dataSomministrazione date CHECK (dataAvvenimento < dataSomministrazione),
    gradoSeverita int CHECK (gradoSeverita < 5 AND gradoSeverita > 1),
    tipoEvento TipoEvento NOT NULL,
    note text,
);




