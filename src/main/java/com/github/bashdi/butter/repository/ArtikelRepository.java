package com.github.bashdi.butter.repository;

import com.github.bashdi.butter.entities.Artikel;

import java.sql.SQLException;
import java.util.List;

public interface ArtikelRepository {

    public Artikel getArtikelById(int id) throws SQLException;

    public Artikel getArtikelByBezeichnung(String name) throws SQLException;

    public List<Artikel> getArtikelList() throws SQLException;

    public List<Artikel> getArtikelListByBezeichnung(String beschreibung) throws SQLException;

    public void saveArtikel(Artikel artikel) throws SQLException;

    public void deleteArtikelById(int id) throws SQLException;
}
