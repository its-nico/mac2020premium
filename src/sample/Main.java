package sample;

import code.*;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.io.File;
import java.lang.*;
import java.sql.SQLException;

public class Main extends JFrame {
    // Anfang Attribute

    private final JLabel ueberschrift = new JLabel();
    private final JLabel beschreibung = new JLabel();
    private final JLabel datenHinzufuegen = new JLabel();
    private final JLabel datenAnsehen = new JLabel();

    private final JButton manuelleEingabe = new JButton();
    private final JButton schnellerImport = new JButton();
    private final JButton datenLoeschen = new JButton();
    private final JButton exportMACAdressen = new JButton();
    private final JButton importLogAnzeigen = new JButton();
    private final JButton doppelteDatensaetzeLoeschen = new JButton();
    private final JButton handbuch = new JButton();
    private final JButton credits = new JButton();
    private final JButton inDatenbankSpeichern = new JButton();
    private final JButton datenbankImportieren = new JButton();
    private final JButton leerenDatenbank = new JButton();

    private ManuelleEingabe manuellesEingabefenster;

    private final Manager manager = new Manager();
    private final OeffnenDialogClass oeffnenDialogClass = new OeffnenDialogClass();
    private final Dialogfenster dialogfenster = new Dialogfenster();
    private final Datenbank datenbank = new Datenbank();

    private final File file1 = new File("./savedList.ser");

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

        setIconImage(new ImageIcon(getClass().getResource("Logo2.png")).getImage());

        // Anfang Komponenten

        ueberschrift.setBounds(16, 16, 390, 35);
        ueberschrift.setText("MAC-Projekt");
        ueberschrift.setFont(new Font("Arial", Font.PLAIN, 30));
        cp.add(ueberschrift);

        beschreibung.setBounds(16, 50, 450, 150);
        beschreibung.setText("<html>Dieses Programm dient der Verwaltung und Korrektur von MAC-Adressen. Es ermöglicht das Speichern ganzer Datensätze, die Informationen über den Namen der Schüler*innen, deren Kursstufe und MAC-Adresse enthalten. <br>" +
                "Verknüpfungen mit einer Datenbank sind ebenfalls möglich.<br>" +
                "Das Programm ist für die Nutzung an Schulen optimiert.</html>");
        cp.add(beschreibung);

        datenHinzufuegen.setBounds(16, 216, 230, 28);
        datenHinzufuegen.setText("Lokale Datensätze");
        cp.add(datenHinzufuegen);

        datenAnsehen.setBounds(504, 216, 190, 28);
        datenAnsehen.setText("Datenbank-Verwaltung");
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
        manuelleEingabe.setBackground(new Color(255, 130, 0));
        manuelleEingabe.setBorder(new LineBorder(new Color(255,130,0),1));

        schnellerImport.setBounds(16, 328, 155, 41);
        schnellerImport.setText("Datensätze importieren");
        schnellerImport.setMargin(new Insets(2, 2, 2, 2));
        schnellerImport.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                schnellerImport_ActionPerformed(evt);
            }
        });
        cp.add(schnellerImport);
        schnellerImport.setBackground(new Color(255, 130, 0));
        schnellerImport.setBorder(new LineBorder(new Color(255,130,0),1));

        datenLoeschen.setBounds(16, 392, 155, 41);
        datenLoeschen.setText("Datensätze löschen");
        datenLoeschen.setMargin(new Insets(2, 2, 2, 2));
        datenLoeschen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                datenLoeschen_ActionPerformed(evt);
            }
        });
        cp.add(datenLoeschen);

        datenLoeschen.setBackground(new Color(255, 130, 0));
        datenLoeschen.setBorder(new LineBorder(new Color(255,130,0),1));

        exportMACAdressen.setBounds(272, 264, 155, 41);
        exportMACAdressen.setText("Export MAC-Adressen");
        exportMACAdressen.setMargin(new Insets(2, 2, 2, 2));
        exportMACAdressen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                exportMACAdressen_ActionPerformed(evt);
            }
        });
        cp.add(exportMACAdressen);
        exportMACAdressen.setBackground(new Color(255, 130, 0));
        exportMACAdressen.setBorder(new LineBorder(new Color(255,130,0),1));

        importLogAnzeigen.setBounds(272, 328, 155, 41);
        importLogAnzeigen.setText("Berichte anzeigen");
        importLogAnzeigen.setMargin(new Insets(2, 2, 2, 2));
        importLogAnzeigen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                importLogAnzeigen_ActionPerformed(evt);
            }
        });
        cp.add(importLogAnzeigen);
        importLogAnzeigen.setBackground(new Color(255, 130, 0));
        importLogAnzeigen.setBorder(new LineBorder(new Color(255,130,0),1));

        doppelteDatensaetzeLoeschen.setBounds(272, 392, 155, 41);
        doppelteDatensaetzeLoeschen.setText("Dopplungen löschen");
        doppelteDatensaetzeLoeschen.setMargin(new Insets(2, 2, 2, 2));
        doppelteDatensaetzeLoeschen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                doppelteDatensaetzeLoeschen_ActionPerformed(evt);
            }
        });
        cp.add(doppelteDatensaetzeLoeschen);
        doppelteDatensaetzeLoeschen.setBackground(new Color(255, 130, 0));
        doppelteDatensaetzeLoeschen.setBorder(new LineBorder(new Color(255,130,0),1));

        handbuch.setBounds(528, 16, 155, 49);
        handbuch.setText("Handbuch");
        handbuch.setMargin(new Insets(2, 2, 2, 2));
        handbuch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                handbuch_ActionPerformed(evt);
            }
        });
        cp.add(handbuch);
        handbuch.setBackground(new Color(0, 255, 255));
        handbuch.setBorder(new LineBorder(new Color(0,255,255),1));

        credits.setBounds(528, 80, 155, 49);
        credits.setText("Credits");
        credits.setMargin(new Insets(2, 2, 2, 2));
        credits.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                credits_ActionPerformed(evt);
            }
        });
        cp.add(credits);
        credits.setBackground(new Color(0, 255, 255));
        credits.setBorder(new LineBorder(new Color(0,255,255),1));

        inDatenbankSpeichern.setBounds(504, 264, 155, 41);
        inDatenbankSpeichern.setText("Datenbank ergänzen");
        inDatenbankSpeichern.setMargin(new Insets(2, 2, 2, 2));
        inDatenbankSpeichern.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                inDatenbankSpeichern_ActionPerformed(evt);
            }
        });
        cp.add(inDatenbankSpeichern);
        inDatenbankSpeichern.setBackground(new Color(255, 130, 0));
        inDatenbankSpeichern.setBorder(new LineBorder(new Color(255,130,0),1));

        datenbankImportieren.setBounds(504, 328, 155, 41);
        datenbankImportieren.setText("Datenbank importieren");
        datenbankImportieren.setMargin(new Insets(2, 2, 2, 2));
        datenbankImportieren.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                datenbankImportieren_ActionPerformed(evt);
            }
        });
        cp.add(datenbankImportieren);
        datenbankImportieren.setBackground(new Color(255, 130, 0));
        datenbankImportieren.setBorder(new LineBorder(new Color(255,130,0),1));

        leerenDatenbank.setBounds(504, 392, 155, 41);
        leerenDatenbank.setText("Datenbank leeren");
        leerenDatenbank.setMargin(new Insets(2, 2, 2, 2));
        leerenDatenbank.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                leerenDatenbank_ActionPerformed(evt);
            }
        });
        cp.add(leerenDatenbank);
        leerenDatenbank.setBackground(new Color(255, 130, 0));
        leerenDatenbank.setBorder(new LineBorder(new Color(255,130,0),1));

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

    public void datenLoeschen_ActionPerformed(ActionEvent evt) {

        // TODO hier Quelltext einfügen
        manager.listeLoeschen();

    }

    public void exportMACAdressen_ActionPerformed(ActionEvent evt) {
        manager.exportiereMac();
        dialogfenster.zwischenablage();
        FileOpener fileOpen = new FileOpener("./export.txt");
    }

    public void doppelteDatensaetzeLoeschen_ActionPerformed(ActionEvent evt) {
        manager.dopplungenLoeschen();
    }

    public void handbuch_ActionPerformed(ActionEvent evt) {
        // TODO hier Quelltext einfügen
    }

    public void credits_ActionPerformed(ActionEvent evt) {
        try {
            manager.datenbankErgaenzen();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQLException occured");
        }
    }

    public void inDatenbankSpeichern_ActionPerformed(ActionEvent evt) {
        try {
            manager.datenbankErgaenzen();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void datenbankImportieren_ActionPerformed(ActionEvent evt) {
        datenbank.datenbankImportieren();
    }

    public void leerenDatenbank_ActionPerformed(ActionEvent evt) {
        datenbank.datenbankLeeren();
    }

    public void importLogAnzeigen_ActionPerformed(ActionEvent evt) {
        FileOpener fileOpen = new FileOpener("./Fehlerlog.txt");
    }
}