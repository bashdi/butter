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

    public ArtikelRepositoryH2(AbstractDatabase database) {
        this.database = database;
    }



    @Override
    public Artikel getArtikelById(int id) throws SQLException {
        String sql = "select id, bezeichnung, preis, bestand, mindestbestand, bestellbestand from TblArtikel where id = ?";
        Connection connection = database.getConnection();

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);

        ResultSet resultSet = statement.executeQuery();
        Artikel artikel = null;

        if (resultSet.first()) {
            artikel = new Artikel(resultSet.getInt("id"),
                                  resultSet.getString("bezeichnung"),
                                  resultSet.getInt("preis"),
                                  resultSet.getInt("bestand"),
                                  resultSet.getInt("mindestbestand"),
                                  resultSet.getInt("bestellbestand")
                    );
        }

        return artikel;
    }

    @Override
    public Artikel getArtikelByBezeichnung(String bezeichnung) throws SQLException {
        String sql = "select id, bezeichnung, preis, bestand, mindestbestand, bestellbestand from TblArtikel where bezeichnung = ?";
        Connection connection = database.getConnection();

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, bezeichnung);

        ResultSet resultSet = statement.executeQuery();
        Artikel artikel = null;

        if (resultSet.first()) {
            artikel = new Artikel(resultSet.getInt("id"),
                    resultSet.getString("bezeichnung"),
                    resultSet.getInt("preis"),
                    resultSet.getInt("bestand"),
                    resultSet.getInt("mindestbestand"),
                    resultSet.getInt("bestellbestand")
            );
        }

        return artikel;
    }


    @Override
    public List<Artikel> getArtikelList() throws SQLException {
        String sql = "select id, bezeichnung, preis, bestand, mindestbestand, bestellbestand from TblArtikel";
        Connection connection = database.getConnection();

        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();

        List<Artikel> artikelList = new ArrayList<>();

        if (!resultSet.first()) {
            return  artikelList;
        }

        do {
            Artikel artikel = new Artikel(resultSet.getInt("id"),
                    resultSet.getString("bezeichnung"),
                    resultSet.getInt("preis"),
                    resultSet.getInt("bestand"),
                    resultSet.getInt("mindestbestand"),
                    resultSet.getInt("bestellbestand")
            );
            artikelList.add(artikel);
        } while (resultSet.next());

        return artikelList;
    }



    @Override
    public List<Artikel> getArtikelListByBezeichnung(String bezeichnung) throws SQLException {
        String sql = "select id, bezeichnung, preis, bestand, mindestbestand, bestellbestand from TblArtikel " +
                     "where lower(bezeichnung) like lower(?)";
        Connection connection = database.getConnection();

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, "%" + bezeichnung + "%");
        ResultSet resultSet = statement.executeQuery();

        List<Artikel> artikelList = new ArrayList<>();

        if (!resultSet.first()) {
            return  artikelList;
        }

        do {
            Artikel artikel = new Artikel(resultSet.getInt("id"),
                    resultSet.getString("bezeichnung"),
                    resultSet.getInt("preis"),
                    resultSet.getInt("bestand"),
                    resultSet.getInt("mindestbestand"),
                    resultSet.getInt("bestellbestand")
            );
            artikelList.add(artikel);
        } while (resultSet.next());

        return artikelList;
    }

    @Override
    public List<Artikel> getArtikelWithStockSmallerMinimumStock() throws SQLException {
        String sql = "select id, bezeichnung, preis, bestand, mindestbestand, bestellbestand from TblArtikel " +
                "where mindestbestand > bestand";
        Connection connection = database.getConnection();

        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();

        List<Artikel> artikelList = new ArrayList<>();

        if (!resultSet.first()) {
            return  artikelList;
        }

        do {
            Artikel artikel = new Artikel(resultSet.getInt("id"),
                    resultSet.getString("bezeichnung"),
                    resultSet.getInt("preis"),
                    resultSet.getInt("bestand"),
                    resultSet.getInt("mindestbestand"),
                    resultSet.getInt("bestellbestand")
            );
            artikelList.add(artikel);
        } while (resultSet.next());

        return artikelList;
    }


    @Override
    public void saveArtikel(Artikel artikel) throws SQLException {
        String updateSql = "update TblArtikel set bezeichnung = ?, preis = ?, bestand = ?, mindestbestand = ?, bestellbestand = ? " +
                            "where id = ?";
        String insertSql = "INSERT INTO TBLARTIKEL(bezeichnung, preis, bestand, mindestbestand, bestellbestand) " +
                           "values(?, ?, ?, ?, ?)";

        Connection connection = database.getConnection();


        PreparedStatement statement;
        if (artikel.getId() == 0) {
            statement = connection.prepareStatement(insertSql);

            statement.setString(1, artikel.getBezeichnung());
            statement.setInt(2, artikel.getPreis());
            statement.setInt(3, artikel.getBestand());
            statement.setInt(4, artikel.getMindestbestand());
            statement.setInt(5, artikel.getBestellbestand());
        } else {
            statement = connection.prepareStatement(updateSql);

            statement.setString(1, artikel.getBezeichnung());
            statement.setInt(2, artikel.getPreis());
            statement.setInt(3, artikel.getBestand());
            statement.setInt(4, artikel.getMindestbestand());
            statement.setInt(5, artikel.getBestellbestand());
            statement.setInt(6, artikel.getId());
        }
        statement.execute();
    }

    @Override
    public void deleteArtikelById(int id) throws SQLException {
        String sql = "delete from tblArtikel where id = ?";

        Connection connection = database.getConnection();

        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setInt(1, id);

        statement.execute();
    }
}
