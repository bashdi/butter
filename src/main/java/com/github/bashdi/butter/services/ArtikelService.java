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

    public Artikel getArtikelById(int nr) throws SQLException {
        return repository.getArtikelById(nr);
    }

    public Artikel getArtikelByBezeichnung(String name) throws SQLException {
        return repository.getArtikelByBezeichnung(name);
    }

    public List<Artikel> getArtikelList() throws SQLException {
        return repository.getArtikelList();
    }

    public List<Artikel> getArtikelListByBezeichnung(String beschreibung) throws SQLException {
        return repository.getArtikelListByBezeichnung(beschreibung);
    }

    public void saveArtikel(Artikel artikel) throws SQLException {
        repository.saveArtikel(artikel);
    }

    public void deleteArtikelById(int id) throws SQLException {
        repository.deleteArtikelById(id);
    }


}
