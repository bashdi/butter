package com.github.bashdi.butter;

import com.github.bashdi.butter.database.AbstractDatabase;
import com.github.bashdi.butter.database.H2Database;
import com.github.bashdi.butter.entities.Artikel;
import com.github.bashdi.butter.repository.ArtikelRepository;
import com.github.bashdi.butter.repository.ArtikelRepositoryH2;
import com.github.bashdi.butter.services.ArtikelService;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        AbstractDatabase database = null;
        try {
            database = new H2Database("test");
            ArtikelRepository artikelRepository = new ArtikelRepositoryH2(database);
            ArtikelService artikelService = new ArtikelService(artikelRepository);

            //Tabelle erstellen
            Connection connection = database.getConnection();
            String crateTableSql = "create table if not exists tblArtikel(\n" +
                    "created timestamp default current_timestamp,\n" +
                    "modified timestamp default current_timestamp on update current_timestamp,\n" +
                    "id int auto_increment,\n" +
                    "bezeichnung varchar(200) unique,\n" +
                    "preis int DEFAULT 0,\n" +
                    "bestand int DEFAULT 0,\n" +
                    "mindestbestand int DEFAULT 0,\n" +
                    "bestellbestand int DEFAULT 0,\n" +
                    "primary key (id)\n" +
                    ")";
            Statement statement = connection.createStatement();

            statement.execute(crateTableSql);

            try {
                artikelService.saveArtikel(new Artikel("Orange"));
                artikelService.saveArtikel(new Artikel("Apfel"));
                artikelService.saveArtikel(new Artikel("Rotes T-Shirt xxl"));
                artikelService.saveArtikel(new Artikel("Rotes T-Shirt xl"));
                artikelService.saveArtikel(new Artikel("Rotes T-Shirt m"));
                artikelService.saveArtikel(new Artikel("Blaues T-Shirt xxl"));
                artikelService.saveArtikel(new Artikel("Blaues T-Shirt xl"));
                artikelService.saveArtikel(new Artikel("Blaues T-Shirt m"));
                artikelService.saveArtikel(new Artikel("Rockstar T-Shirt xxl"));
                artikelService.saveArtikel(new Artikel("Rockstar T-Shirt xl"));
                artikelService.saveArtikel(new Artikel("Rockstar T-Shirt m"));
                artikelService.saveArtikel(new Artikel("Screw Schraubenzieher"));
                artikelService.saveArtikel(new Artikel("GutGünstig Schraubenzieher"));
                artikelService.saveArtikel(new Artikel("BeatIt Hammer"));
                artikelService.saveArtikel(new Artikel("GutGünstig Hammer"));
            } catch (SQLException e) {
                System.err.println(e);
            }

            Artikel art1 = artikelService.getArtikelById(1);
            art1.setBestand(100);
            art1.setPreis(100);

            artikelService.saveArtikel(art1);


            List<Artikel> artikelList = artikelService.getArtikelListByBezeichnung("T-Shirt");

            artikelList.forEach( a -> {
                System.out.println(a.toString());
            });

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (database != null) {
                database.close();
            }
        }
    }
}
