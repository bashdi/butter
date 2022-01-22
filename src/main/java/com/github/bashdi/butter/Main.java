package com.github.bashdi.butter;

import com.github.bashdi.butter.database.AbstractDatabase;
import com.github.bashdi.butter.database.H2Database;
import com.github.bashdi.butter.gui.ArtikelFrame;
import com.github.weisj.darklaf.LafManager;
import com.github.weisj.darklaf.theme.DarculaTheme;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

    public static void main(String[] args) {

        AbstractDatabase database = null;
        try {
            database = new H2Database("artikelverwaltung");

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


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (database != null) {
                database.close();
            }
        }

        LafManager.install(new DarculaTheme());



        ArtikelFrame artikelFrame = new ArtikelFrame();
    }
}
