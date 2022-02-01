package com.github.bashdi.butter.exporter;

import com.github.bashdi.butter.entities.Artikel;

import java.io.File;
import java.util.List;

public class ArtikelExporterJson implements ArtikelExporter{
    @Override
    public String getBezeichnung() {
        return "json";
    }

    @Override
    public String[] getDateiendungen() {
        return new String[]{"json"};
    }

    @Override
    public void export(File file, List<Artikel> artikelList) {
        //Implementierung fehlt
    }
}
