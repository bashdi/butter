package com.github.bashdi.butter.gui;

import com.github.bashdi.butter.entities.Artikel;
import com.github.bashdi.butter.services.ArtikelService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class ModifyArtikelDialog extends JDialog {

    ArtikelService artikelService;
    Artikel artikel;
    JDialog thisDialog;

    public ModifyArtikelDialog(JFrame parentFrame, ArtikelService artikelService) {
        super(parentFrame, true);
        this.artikelService = artikelService;

        createDialog();
    }

    public ModifyArtikelDialog(JFrame parentFrame, ArtikelService artikelService, int id) {
        super(parentFrame, true);
        this.artikelService = artikelService;
        try {
            artikel = artikelService.getArtikelById(id);
            createDialog();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createDialog() {
        setTitle("Artikel neu/bearbeiten");

        thisDialog = this;

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10,10,10,10);

        //Id Label
        gbc.gridx = 0;
        gbc.gridy = 0;

        JLabel idLabel = new JLabel("Artikelnr");
        add(idLabel, gbc);

        //Id Textfeld
        gbc.gridx = 1;
        gbc.gridy = 0;

        JTextField idTextField = new JTextField();
        idTextField.setEnabled(false);
        add(idTextField, gbc);



        //Bezeichnung Label
        gbc.gridx = 0;
        gbc.gridy = 1;

        JLabel bezeichnungLabel = new JLabel("Bezeichnung");
        add(bezeichnungLabel, gbc);

        //Bezeichnung Textfeld
        gbc.gridx = 1;
        gbc.gridy = 1;

        JTextField bezeichnungTextField = new JTextField();
        add(bezeichnungTextField, gbc);



        //Preis Label
        gbc.gridx = 0;
        gbc.gridy = 2;

        JLabel preisLabel = new JLabel("Preis");
        add(preisLabel, gbc);

        //Preis Textfeld
        gbc.gridx = 1;
        gbc.gridy = 2;

        JTextField preisTextField = new JTextField();
        add(preisTextField, gbc);



        //Bestand Label
        gbc.gridx = 0;
        gbc.gridy = 3;

        JLabel bestandLabel = new JLabel("Bestand");
        add(bestandLabel, gbc);

        //Bestand Textfeld
        gbc.gridx = 1;
        gbc.gridy = 3;

        JTextField bestandTextField = new JTextField();
        add(bestandTextField, gbc);



        //Mindestbestand Label
        gbc.gridx = 0;
        gbc.gridy = 4;

        JLabel mindestbestandLabel = new JLabel("Mindestbestand");
        add(mindestbestandLabel, gbc);

        //Mindestbestand Textfeld
        gbc.gridx = 1;
        gbc.gridy = 4;

        JTextField mindestbestandTextField = new JTextField();
        add(mindestbestandTextField, gbc);



        //Bestellbestand Label
        gbc.gridx = 0;
        gbc.gridy = 5;

        JLabel bestellbestandLabel = new JLabel("Bestellbestand");
        add(bestellbestandLabel, gbc);

        //Bestellbestand Textfeld
        gbc.gridx = 1;
        gbc.gridy = 5;

        JTextField bestellbestandTextField = new JTextField();
        add(bestellbestandTextField, gbc);



        //Abbrechen-Button
        gbc.gridx = 0;
        gbc.gridy = 6;

        JButton cancelButton = new JButton("Abbrechen");
        add(cancelButton, gbc);

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                thisDialog.dispose();
            }
        });


        //Speichern-Button
        gbc.gridx = 1;
        gbc.gridy = 6;

        JButton saveButton = new JButton("Speichern");
        add(saveButton, gbc);

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (artikel != null) {
                        artikel.setBezeichnung(bezeichnungTextField.getText());
                    } else {
                        artikel = new Artikel(bezeichnungTextField.getText());
                    }

                    artikel.setPreis(Integer.parseInt(preisTextField.getText()));
                    artikel.setBestand(Integer.parseInt(bestandTextField.getText()));
                    artikel.setMindestbestand(Integer.parseInt(mindestbestandTextField.getText()));
                    artikel.setBestellbestand(Integer.parseInt(bestellbestandTextField.getText()));

                    artikelService.saveArtikel(artikel);
                    JOptionPane.showMessageDialog(thisDialog, "Gespeichert", "Gespeichert", JOptionPane.INFORMATION_MESSAGE, null);

                    artikel = artikelService.getArtikelByBezeichnung(artikel.getBezeichnung());
                    idTextField.setText(String.valueOf(artikel.getId()));

                } catch (NumberFormatException | SQLException ex) {
                    JOptionPane.showMessageDialog(thisDialog, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE, null);
                }

            }
        });


        //Textfelder füllen
        if (artikel != null) {
            idTextField.setText(String.valueOf(artikel.getId()));
            bezeichnungTextField.setText(artikel.getBezeichnung());
            preisTextField.setText(String.valueOf(artikel.getPreis()));
            bestandTextField.setText(String.valueOf(artikel.getBestand()));
            mindestbestandTextField.setText(String.valueOf(artikel.getMindestbestand()));
            bestellbestandTextField.setText(String.valueOf(artikel.getBestellbestand()));
        }



        pack();
        setResizable(false);
        setLocation(MouseInfo.getPointerInfo().getLocation());
        setVisible(true);
    }


}
