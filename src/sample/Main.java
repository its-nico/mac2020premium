package sample;

import code.FileOpener;
import code.Manager;
import code.OeffnenDialogClass;

import code.*;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.File;
import java.lang.*;

public class Main extends JFrame {
    // Anfang Attribute
    private JLabel ueberschrift = new JLabel();
    private JLabel beschreibung = new JLabel();
    private JLabel datenHinzufuegen = new JLabel();
    private JLabel datenAnsehen = new JLabel();

    private JButton manuelleEingabe = new JButton();
    private JButton schnellerImport = new JButton();
    private JButton datenLöschen = new JButton();
    private JButton exportMACAdressen = new JButton();
    private JButton importLogAnzeigen = new JButton();
    private JButton doppelteDatensaetzeLoeschen = new JButton();
    private JButton handbuch = new JButton();
    private JButton credits = new JButton();
    private JButton inDatenbankSpeichern = new JButton();
    private JButton datenbankImportieren = new JButton();
    private JButton leerenDatenbank = new JButton();

    private ManuelleEingabe manuellesEingabefenster;

    private Manager manager = new Manager();
    private OeffnenDialogClass oeffnenDialogClass = new OeffnenDialogClass();
    private Dialogfenster dialogfenster = new Dialogfenster();
    private File file1 = new File("./savedList.ser");

    private Closing closing;

    // Ende Attribute

    public Main() {
        // Frame-Initialisierung
        super();

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); //look & feel von System wird hier gesetzt
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        int frameWidth = 720;
        int frameHeight = 488;
        setSize(frameWidth, frameHeight);
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (d.width - getSize().width) / 2;
        int y = (d.height - getSize().height) / 2;
        setLocation(x, y);
        setTitle("MAC-Projekt");
        setResizable(false);
        Container cp = getContentPane();
        cp.setLayout(null);

        setIconImage(new ImageIcon(getClass().getResource("88208755.png")).getImage());

        // Anfang Komponenten

        ueberschrift.setBounds(16, 16, 390, 35);
        ueberschrift.setText("MAC-Projekt");
        ueberschrift.setFont(new Font("Arial", Font.PLAIN, 30));
        cp.add(ueberschrift);
        beschreibung.setBounds(16, 50, 450, 150);
        beschreibung.setText("<html>Dieses Programm dient zur Verwaltung und Korrektur von MAC-Adressen. Es ist auf die Verwaltung von MAC-Adressen an Schulen optimiert und ermöglicht Ihnen deshalb das Speichern ganzer Datensätze, die Informationen über den Namen der Schüler*innen, deren Kursstufe und MAC-Adresse enthalten. \n" +
                "Kernfunktionen sind die Korrektur von MAC-Adressen, sowie deren Export. Außerdem lässt sich die Korrektur der MAC-Adressen in einem stetig wachsenden Korrektur-Log verfolgen.\n" +
                "Das Speichern und Herunterladen der Datensätze auf, beziehungsweise von einer Datenbank, sind ebenfalls möglich.</html>");
        cp.add(beschreibung);

        datenHinzufuegen.setBounds(16, 216, 230, 28);
        datenHinzufuegen.setText("Lokale Datensätze");
        cp.add(datenHinzufuegen);

        datenAnsehen.setBounds(504, 216, 190, 28);
        datenAnsehen.setText("Datenbank Verwaltung");
        cp.add(datenAnsehen);

        manuelleEingabe.setBounds(16, 264, 155, 41);
        manuelleEingabe.setText("Datensätze ergänzen");
        manuelleEingabe.setMargin(new Insets(2, 2, 2, 2));
        manuelleEingabe.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                manuelleEingabe_ActionPerformed(evt);
            }
        });
        cp.add(manuelleEingabe);

        schnellerImport.setBounds(16, 328, 155, 41);
        schnellerImport.setText("Datensätze importieren");
        schnellerImport.setMargin(new Insets(2, 2, 2, 2));
        schnellerImport.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                schnellerImport_ActionPerformed(evt);
            }
        });
        cp.add(schnellerImport);

        datenLöschen.setBounds(16, 392, 155, 41);
        datenLöschen.setText("Datensätze löschen");
        datenLöschen.setMargin(new Insets(2, 2, 2, 2));
        datenLöschen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                datenLöschen_ActionPerformed(evt);
            }
        });
        cp.add(datenLöschen);

        exportMACAdressen.setBounds(272, 264, 155, 41);
        exportMACAdressen.setText("Export MAC-Adressen");
        exportMACAdressen.setMargin(new Insets(2, 2, 2, 2));
        exportMACAdressen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                exportMACAdressen_ActionPerformed(evt);
            }
        });
        cp.add(exportMACAdressen);

        importLogAnzeigen.setBounds(272, 328, 155, 41);
        importLogAnzeigen.setText("Log Anzeigen");
        importLogAnzeigen.setMargin(new Insets(2, 2, 2, 2));
        importLogAnzeigen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                importLogAnzeigen_ActionPerformed(evt);
            }
        });
        cp.add(importLogAnzeigen);

        doppelteDatensaetzeLoeschen.setBounds(272, 392, 155, 41);
        doppelteDatensaetzeLoeschen.setText("Doppelungen löschen");
        doppelteDatensaetzeLoeschen.setMargin(new Insets(2, 2, 2, 2));
        doppelteDatensaetzeLoeschen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                doppelteDatensaetzeLoeschen_ActionPerformed(evt);
            }
        });
        cp.add(doppelteDatensaetzeLoeschen);

        handbuch.setBounds(528, 16, 155, 49);
        handbuch.setText("Handbuch");
        handbuch.setMargin(new Insets(2, 2, 2, 2));
        handbuch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                handbuch_ActionPerformed(evt);
            }
        });
        cp.add(handbuch);
        credits.setBounds(528, 80, 155, 49);
        credits.setText("Credits");
        credits.setMargin(new Insets(2, 2, 2, 2));
        credits.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                credits_ActionPerformed(evt);
            }
        });
        cp.add(credits);

        inDatenbankSpeichern.setBounds(504, 264, 155, 41);
        inDatenbankSpeichern.setText("Datenbank Runterladen");
        inDatenbankSpeichern.setMargin(new Insets(2, 2, 2, 2));
        inDatenbankSpeichern.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                inDatenbankSpeichern_ActionPerformed(evt);
            }
        });
        cp.add(inDatenbankSpeichern);

        datenbankImportieren.setBounds(504, 328, 155, 41);
        datenbankImportieren.setText("Datenbank Importieren");
        datenbankImportieren.setMargin(new Insets(2, 2, 2, 2));
        datenbankImportieren.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                datenbankImportieren_ActionPerformed(evt);
            }
        });
        cp.add(datenbankImportieren);

        leerenDatenbank.setBounds(504, 392, 155, 41);
        leerenDatenbank.setText("Import-Log Anzeigen");
        leerenDatenbank.setMargin(new Insets(2, 2, 2, 2));
        leerenDatenbank.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                leerenDatenbank_ActionPerformed(evt);
            }
        });
        cp.add(leerenDatenbank);
        // Ende Komponenten

        if (!file1.exists()){
            manager.speichern(); //hier wird überprüft, ob die serialisierte Listen-Datei schon vorhanden ist. Wenn nicht, wird eine erstellt, da sonst eine NullPointerException auftritt
        }

        manager.laden();

        setVisible(true);

        addWindowListener(new WindowAdapter()
        {
            @Override
           public void windowClosing(WindowEvent e) {
                closing = new Closing();
                closing.close(manager);
            }
        });
    } // end of public Main

    // Anfang Methoden
    public static void main(String[] args) {
        new Main();
    }

    public void manuelleEingabe_ActionPerformed(ActionEvent evt) {
        manuellesEingabefenster = new ManuelleEingabe();
    }

    public void schnellerImport_ActionPerformed(ActionEvent evt) {
        manager.schnellerImport(oeffnenDialogClass.oeffnen());
    }

    public void datenLöschen_ActionPerformed(ActionEvent evt) {
        // TODO hier Quelltext einfügen
    }

    public void exportMACAdressen_ActionPerformed(ActionEvent evt) {
        manager.exportiereMac();
        FileOpener fileOpen = new FileOpener("./export.txt");

        dialogfenster.zwischenablage();
    }

    public void leerenDatenbank_ActionPerformed(ActionEvent evt) {
        // TODO hier Quelltext einfügen
    }

    public void doppelteDatensaetzeLoeschen_ActionPerformed(ActionEvent evt) {
        // TODO hier Quelltext einfügen
    }

    public void handbuch_ActionPerformed(ActionEvent evt) {
        // TODO hier Quelltext einfügen
    }

    public void credits_ActionPerformed(ActionEvent evt) {
        // TODO hier Quelltext einfügen
    }

    public void inDatenbankSpeichern_ActionPerformed(ActionEvent evt) {
        // TODO hier Quelltext einfügen
    }

    public void datenbankImportieren_ActionPerformed(ActionEvent evt) {
        // TODO hier Quelltext einfügen
    }

    public void importLogAnzeigen_ActionPerformed(ActionEvent evt) {
        FileOpener fileOpen = new FileOpener("./Fehlerlog.txt");
    }
}