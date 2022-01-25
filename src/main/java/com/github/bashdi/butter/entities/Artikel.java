package com.github.bashdi.butter.entities;

public record Artikel (int id, String bezeichnung, int preis, int bestand, int mindestbestand, int bestellbestand) {



    public Artikel(String bezeichnung) {
        this(0, bezeichnung, 0,0,0,0);
    }

    public Artikel(String bezeichnung, int preis, int bestand, int mindestbestand, int bestellbestand) {
        this(0, bezeichnung, preis,bestand,mindestbestand,bestellbestand);
    }

}
