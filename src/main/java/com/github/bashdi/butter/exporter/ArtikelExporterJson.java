package com.github.bashdi.butter.exporter;

import com.github.bashdi.butter.entities.Artikel;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
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
        Gson gson = new Gson();
        String jsonString = gson.toJson(artikelList);

        try {
            PrintWriter writer = new PrintWriter(file);
            writer.write(jsonString);
            writer.flush();
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
