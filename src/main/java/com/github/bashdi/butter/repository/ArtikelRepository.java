package com.github.bashdi.butter.repository;

import com.github.bashdi.butter.entities.Artikel;

import java.sql.SQLException;
import java.util.List;

public interface ArtikelRepository {

    public Artikel getArtikelByNr(int nr) throws SQLException;

    public Artikel getArtikelByName(String name) throws SQLException;

    public List<Artikel> getArtikelList() throws SQLException;

    public List<Artikel> getArtikelListByBeschreibung(String beschreibung) throws SQLException;

    public boolean saveArtikel(Artikel artikel) throws SQLException;
}
