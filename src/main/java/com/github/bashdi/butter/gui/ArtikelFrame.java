package com.github.bashdi.butter.gui;

import com.github.bashdi.butter.database.AbstractDatabase;
import com.github.bashdi.butter.database.H2Database;
import com.github.bashdi.butter.entities.Artikel;
import com.github.bashdi.butter.repository.ArtikelRepository;
import com.github.bashdi.butter.repository.ArtikelRepositoryH2;
import com.github.bashdi.butter.services.ArtikelService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class ArtikelFrame extends JFrame {

    ArtikelService artikelService;

    Vector<Vector<String>> tableContentVector;
    JTable artikelTable;
    JScrollPane tableScrollPane;


    public ArtikelFrame() {
        setTitle("Artikelverwaltung");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        initArtikelService();

        createMenuBar();

        add(createArtikelsuche());



        pack();
        setVisible(true);
    }



    private void initArtikelService() {
        AbstractDatabase database = new H2Database("test");
        ArtikelRepository artikelRepository = new ArtikelRepositoryH2(database);
        artikelService = new ArtikelService(artikelRepository);
    }



    /**
     * Handelt MenuBar erstellung
     */
    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        //Export menu
        JMenu exportMenu = new JMenu("Export");
        menuBar.add(exportMenu);

        JMenuItem exportCsvMenuItem = new JMenuItem("Export as CSV");
        exportMenu.add(exportCsvMenuItem);

        JMenuItem exportJsonMenuItem = new JMenuItem("Export as JSON");
        exportMenu.add(exportJsonMenuItem);

    }


    /**
     * Handelt alle Komponenten für Artikelsuche
     */
    private JPanel createArtikelsuche() {
        JPanel artikelsuchePanel = new JPanel();
        artikelsuchePanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(2,2,2,2);
        gbc.fill = GridBagConstraints.BOTH;



        //Artikelsuchfeld-Label
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel artikelsucheLabel = new JLabel("Artikelsuche");
        artikelsuchePanel.add(artikelsucheLabel, gbc);



        //Artikelsuchfeld
        gbc.gridx = 0;
        gbc.gridy = 1;

        JTextField artikelsucheTextField = new JTextField(30);
        artikelsucheTextField.setToolTipText("Artikelnr/Artikelbezeichnung");
        artikelsuchePanel.add(artikelsucheTextField, gbc);



        //Alle Artikel holen Button
        gbc.gridx = 0;
        gbc.gridy = 2;

        JButton sucheArtikelButton = new JButton("Suche");
        artikelsuchePanel.add(sucheArtikelButton, gbc);

        sucheArtikelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String suchEingabe = artikelsucheTextField.getText();
                int suchArtikelId = 0;
                boolean isNumber = true;

                try {
                    suchArtikelId = Integer.parseInt(suchEingabe);
                } catch (NumberFormatException exception) {
                    isNumber = false;
                }

               List<Artikel> artikelList = new ArrayList<Artikel>();

                //Direkt nach Artnr suchen, wenn es eine ID sein könnte
               if (isNumber) {
                   try {
                       Artikel artikel = artikelService.getArtikelById(suchArtikelId);
                       if (artikel != null) artikelList.add(artikel);
                   } catch (SQLException ex) {
                       ex.printStackTrace();
                   }
               }

               //Artikel mit ähnlichen Bezeichnungen suchen
                try {
                    artikelList.addAll(artikelService.getArtikelListByBezeichnung(suchEingabe));
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

                tableContentVector.clear();
                addArtikelToTableContent(artikelList);
            }
        });



        //Alle Artikel holen Button
        gbc.gridx = 0;
        gbc.gridy = 3;

        JButton getAllArtikelButton = new JButton("Alle Artikel");
        artikelsuchePanel.add(getAllArtikelButton, gbc);

        getAllArtikelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    tableContentVector.clear();
                    addArtikelToTableContent(artikelService.getArtikelList());
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });



        //Artikeltabelle
        gbc.gridx = 0;
        gbc.gridy = 4;

        //Tabellenspaltennamen
        Vector<String> columnNamesVector = new Vector<>();
        columnNamesVector.add("Id");
        columnNamesVector.add("Bezeichnung");
        columnNamesVector.add("Preis");
        columnNamesVector.add("Bestand");
        columnNamesVector.add("Mindestbestand");
        columnNamesVector.add("Bestellbestand");

        //Tabelleninhalt
        tableContentVector = new Vector<>();
        Vector<String> testArtikelVector = new Vector<>();
        testArtikelVector.add("6666");
        testArtikelVector.add("Test");
        testArtikelVector.add("10000");
        testArtikelVector.add("123");
        testArtikelVector.add("100");
        testArtikelVector.add("130");
        tableContentVector.add(testArtikelVector);

        artikelTable = new JTable(tableContentVector, columnNamesVector);
        artikelTable.setPreferredScrollableViewportSize(new Dimension(800, 600));
        artikelTable.setFillsViewportHeight(true);
        artikelTable.getColumnModel().getColumn(0).setPreferredWidth(20);
        artikelTable.getColumnModel().getColumn(1).setPreferredWidth(300);
        tableScrollPane = new JScrollPane(artikelTable);
        tableScrollPane.setSize(1000, 300);

        artikelsuchePanel.add(tableScrollPane, gbc);





        return artikelsuchePanel;
    }



    /**
     * Fügt neue Artikel der Tabelle in der GUI hinzu
     * @param artikelList
     */
    public void addArtikelToTableContent(List<Artikel> artikelList) {

        for (Artikel artikel : artikelList) {
            Vector<String> newContent = new Vector<>();
            newContent.add(String.valueOf(artikel.getId()));
            newContent.add(artikel.getBezeichnung());
            newContent.add(String.valueOf(artikel.getPreis()));
            newContent.add(String.valueOf(artikel.getBestand()));
            newContent.add(String.valueOf(artikel.getMindestbestand()));
            newContent.add(String.valueOf(artikel.getBestellbestand()));
            tableContentVector.add(newContent);
        }
        artikelTable.repaint();
    }
}