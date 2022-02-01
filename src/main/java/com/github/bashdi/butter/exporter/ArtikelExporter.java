package com.github.bashdi.butter.exporter;

import com.github.bashdi.butter.entities.Artikel;

import java.io.File;
import java.util.List;

public interface ArtikelExporter {

    public String getBezeichnung();

    public String[] getDateiendungen();

    public void export(File file, List<Artikel> artikelList);
}
