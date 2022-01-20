package com.github.bashdi.butter.entities;

public class Artikel {

    int id;
    String bezeichnung;
    int preis;
    int bestand;
    int mindestbestand;
    int bestellbestand;

    public Artikel(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    public Artikel(String bezeichnung, int preis, int bestand, int mindestbestand, int bestellbestand) {
        this.bezeichnung = bezeichnung;
        this.preis = preis;
        this.bestand = bestand;
        this.mindestbestand = mindestbestand;
        this.bestellbestand = bestellbestand;
    }

    public Artikel(int id, String bezeichnung, int preis, int bestand, int mindestbestand, int bestellbestand) {
        this.id = id;
        this.bezeichnung = bezeichnung;
        this.preis = preis;
        this.bestand = bestand;
        this.mindestbestand = mindestbestand;
        this.bestellbestand = bestellbestand;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBezeichnung() {
        return bezeichnung;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    public int getPreis() {
        return preis;
    }

    public void setPreis(int preis) {
        this.preis = preis;
    }

    public int getBestand() {
        return bestand;
    }

    public void setBestand(int bestand) {
        this.bestand = bestand;
    }

    public int getMindestbestand() {
        return mindestbestand;
    }

    public void setMindestbestand(int mindestbestand) {
        this.mindestbestand = mindestbestand;
    }

    public int getBestellbestand() {
        return bestellbestand;
    }

    public void setBestellbestand(int bestellbestand) {
        this.bestellbestand = bestellbestand;
    }

    @Override
    public String toString() {
        return "Artikel{" +
                "id=" + id +
                ", bezeichnung='" + bezeichnung + '\'' +
                ", preis=" + preis +
                ", bestand=" + bestand +
                ", mindestbestand=" + mindestbestand +
                ", bestellbestand=" + bestellbestand +
                '}';
    }
}
