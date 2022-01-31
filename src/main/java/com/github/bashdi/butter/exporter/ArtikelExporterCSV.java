package com.github.bashdi.butter.exporter;

import com.github.bashdi.butter.entities.Artikel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

public class ArtikelExporterCSV implements ArtikelExporter{

    @Override
    public String getBezeichnung() {
        return "csv";
    }

    @Override
    public void export(File file, List<Artikel> artikelList) {
        StringBuilder csvString = new StringBuilder();

        //Überschriften
        csvString.append("\"id\";");
        csvString.append("\"bezeichnung\";");
        csvString.append("\"preis\";");
        csvString.append("\"bestand\";");
        csvString.append("\"mindestbestand\";");
        csvString.append("\"bestellbestand\";");
        csvString.append(System.lineSeparator());

        for (Artikel artikel : artikelList) {
            //id
            csvString.append("\"");
            csvString.append(artikel.getId());
            csvString.append("\";");
            //bezeichnung
            csvString.append("\"");
            csvString.append(artikel.getBezeichnung());
            csvString.append("\";");
            //preis
            csvString.append("\"");
            csvString.append(artikel.getPreis());
            csvString.append("\";");
            //bestand
            csvString.append("\"");
            csvString.append(artikel.getBestand());
            csvString.append("\";");
            //mindestbestand
            csvString.append("\"");
            csvString.append(artikel.getMindestbestand());
            csvString.append("\";");
            //bestellbestand
            csvString.append("\"");
            csvString.append(artikel.getBestellbestand());
            csvString.append("\";");
            //NächsteZeile
            csvString.append(System.lineSeparator());
        }

        try {
            PrintWriter writer = new PrintWriter(file);
            writer.write(csvString.toString());
            writer.flush();
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
