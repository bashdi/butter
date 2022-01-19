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

public class Main {

    public static void main(String[] args) {
        try {
            AbstractDatabase database = new H2Database("test");
            ArtikelRepository artikelRepository = new ArtikelRepositoryH2(database);
            ArtikelService artikelService = new ArtikelService(artikelRepository);

            //Tabelle erstellen
            Connection connection = database.getConnection();
            String crateTableSql = "create table TblArtikel(nr int auto_increment, " +
                                    "name varchar(300), beschreibung varchar(500))";
            Statement statement = connection.createStatement();

            if (!statement.execute(crateTableSql)) {
                return;
            }

            artikelService.saveArtikel(new Artikel("Orange", "Obst"));
            artikelService.saveArtikel(new Artikel("Apfel", "Obst"));

            Artikel art1 = artikelService.getArtikelByNr(1);
            art1.setBeschreibung("Frucht aus Florida");

            artikelService.saveArtikel(art1);


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
