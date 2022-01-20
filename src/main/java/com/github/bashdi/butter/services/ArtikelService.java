package com.github.bashdi.butter.services;

import com.github.bashdi.butter.entities.Artikel;
import com.github.bashdi.butter.repository.ArtikelRepository;

import java.sql.SQLException;
import java.util.List;

public class ArtikelService {

    ArtikelRepository repository;

    public ArtikelService(ArtikelRepository repository) {
        this.repository = repository;
    }

    public Artikel getArtikelByNr(int nr) throws SQLException {
        return repository.getArtikelByNr(nr);
    }

    public Artikel getArtikelByName(String name) throws SQLException {
        return repository.getArtikelByName(name);
    }

    public List<Artikel> getAllArtikel() throws SQLException {
        return repository.getArtikelList();
    }

    public List<Artikel> getArtikelByBeschreibung(String beschreibung) throws SQLException {
        return repository.getArtikelListByBeschreibung(beschreibung);
    }

    public boolean saveArtikel(Artikel artikel) throws SQLException {
        return repository.saveArtikel(artikel);
    }


}
