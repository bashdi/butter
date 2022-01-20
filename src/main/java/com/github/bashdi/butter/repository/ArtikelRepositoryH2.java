package com.github.bashdi.butter.repository;

import com.github.bashdi.butter.database.AbstractDatabase;
import com.github.bashdi.butter.entities.Artikel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ArtikelRepositoryH2 implements ArtikelRepository{

    AbstractDatabase database;
    Connection connection;

    public ArtikelRepositoryH2(AbstractDatabase database) {
        this.database = database;
    }



    @Override
    public Artikel getArtikelByNr(int nr) throws SQLException {
        String sql = "select nr, name, beschreibung from TblArtikel where nr = ?";
        Connection connection = database.getConnection();

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, nr);

        ResultSet resultSet = statement.executeQuery();
        Artikel artikel = null;

        if (resultSet.first()) {
            artikel = new Artikel(resultSet.getInt("nr"),
                                  resultSet.getString("name"),
                                  resultSet.getString("beschreibung"));
        }

        return artikel;
    }



    @Override
    public List<Artikel> getAllArtikel() throws SQLException {
        String sql = "select nr, name, beschreibung from TblArtikel";
        Connection connection = database.getConnection();

        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();

        List<Artikel> artikelList = new ArrayList<>();

        if (!resultSet.first()) {
            return  artikelList;
        }

        do {
            Artikel artikel = new Artikel(resultSet.getInt("nr"),
                    resultSet.getString("name"),
                    resultSet.getString("beschreibung"));
            artikelList.add(artikel);
        } while (resultSet.next());

        return artikelList;
    }



    @Override
    public List<Artikel> getArtikelByBeschreibung(String beschreibung) throws SQLException {
        String sql = "select nr, name, beschreibung from TblArtikel " +
                     "where beschreibung like ?";
        Connection connection = database.getConnection();

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, beschreibung);
        ResultSet resultSet = statement.executeQuery();

        List<Artikel> artikelList = new ArrayList<>();

        if (!resultSet.first()) {
            return  artikelList;
        }

        do {
            Artikel artikel = new Artikel(resultSet.getInt("nr"),
                    resultSet.getString("name"),
                    resultSet.getString("beschreibung"));
            artikelList.add(artikel);
        } while (resultSet.next());

        return artikelList;
    }



    @Override
    public boolean saveArtikel(Artikel artikel) throws SQLException {
        String updateSql = "update TblArtikel set name = ?, beschreibung = ? " +
                            "where nr = ?";
        String insertSql = "INSERT INTO TBLARTIKEL(name, beschreibung) " +
                           "values(?, ?)";

        Connection connection = database.getConnection();


        PreparedStatement statement;
        if (artikel.getNr() == 0) {
            statement = connection.prepareStatement(insertSql);

            statement.setString(1, artikel.getName());
            statement.setString(2, artikel.getBeschreibung());
        } else {
            statement = connection.prepareStatement(updateSql);

            statement.setString(1, artikel.getName());
            statement.setString(2, artikel.getBeschreibung());
            statement.setInt(3, artikel.getNr());
        }
        return statement.execute();
    }
}
