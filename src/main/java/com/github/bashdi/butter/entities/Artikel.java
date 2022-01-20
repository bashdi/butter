package com.github.bashdi.butter.entities;

public class Artikel {

    int nr;
    String name;
    String beschreibung;

    public Artikel(String name, String beschreibung) {
        this.name = name;
        this.beschreibung = beschreibung;
    }

    public Artikel(int nr, String name, String beschreibung) {
        this.nr = nr;
        this.name = name;
        this.beschreibung = beschreibung;
    }

    public int getNr() {
        return nr;
    }

    public void setNr(int nr) {
        this.nr = nr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    @Override
    public String toString() {
        return "Nr: " + nr + " Name: " + name + " Beschreibung: " + beschreibung;
    }
}
