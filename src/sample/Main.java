package sample;
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
    private JLabel datenVerarbeiten = new JLabel();
    private JLabel datenbankVerwaltung = new JLabel();
    private JLabel datenAnsehen = new JLabel();

    private JButton manuelleEingabe = new JButton();
    private JButton schnellerImport = new JButton();
    private JButton langsamerImport = new JButton();
    private JButton zurueckButton1 = new JButton();
    private JButton zurueckButton2 = new JButton();
    private JButton exportMACAdressen = new JButton();
    private JButton datenbankErgaenzen = new JButton();
    private JButton datenbankBearbeiten = new JButton();
    private JButton handbuch = new JButton();
    private JButton credits = new JButton();
    private JButton datenbankRunterladen = new JButton();
    private JButton aktuelleTextdateiRunterladen = new JButton();
    private JButton importLogAnzeigen = new JButton();

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
        int frameHeight = 520;
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
        datenHinzufuegen.setText("Daten hinzufügen");
        cp.add(datenHinzufuegen);
        datenVerarbeiten.setBounds(272, 216, 190, 28);
        datenVerarbeiten.setText("Daten verarbeiten");
        cp.add(datenVerarbeiten);
        datenbankVerwaltung.setBounds(272, 320, 190, 28);
        datenbankVerwaltung.setText("Datenbank Verwaltung");
        cp.add(datenbankVerwaltung);
        datenAnsehen.setBounds(504, 216, 190, 28);
        datenAnsehen.setText("Daten Ansehen");
        cp.add(datenAnsehen);

        manuelleEingabe.setBounds(16, 264, 155, 41);
        manuelleEingabe.setText("Manuelle Eingabe");
        manuelleEingabe.setMargin(new Insets(2, 2, 2, 2));
        manuelleEingabe.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                manuelleEingabe_ActionPerformed(evt);
            }
        });
        cp.add(manuelleEingabe);
        schnellerImport.setBounds(16, 328, 155, 41);
        schnellerImport.setText("Schneller Import");
        schnellerImport.setMargin(new Insets(2, 2, 2, 2));
        schnellerImport.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                schnellerImport_ActionPerformed(evt);
            }
        });
        cp.add(schnellerImport);
        langsamerImport.setBounds(16, 392, 155, 41);
        langsamerImport.setText("Import");
        langsamerImport.setMargin(new Insets(2, 2, 2, 2));
        langsamerImport.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                langsamerImport_ActionPerformed(evt);
            }
        });
        cp.add(langsamerImport);
        zurueckButton1.setBounds(200, 328, 43, 41);
        zurueckButton1.setText("↺");
        zurueckButton1.setMargin(new Insets(2, 2, 2, 2));
        zurueckButton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                zurueckButton1_ActionPerformed(evt);
            }
        });
        cp.add(zurueckButton1);
        zurueckButton2.setBounds(200, 392, 43, 41);
        zurueckButton2.setText("↺");
        zurueckButton2.setMargin(new Insets(2, 2, 2, 2));
        zurueckButton2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                zurueckButton2_ActionPerformed(evt);
            }
        });
        cp.add(zurueckButton2);
        exportMACAdressen.setBounds(272, 264, 155, 41);
        exportMACAdressen.setText("Export MAC-Adressen");
        exportMACAdressen.setMargin(new Insets(2, 2, 2, 2));
        exportMACAdressen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                exportMACAdressen_ActionPerformed(evt);
            }
        });
        cp.add(exportMACAdressen);
        datenbankErgaenzen.setBounds(272, 360, 155, 41);
        datenbankErgaenzen.setText("Datenbank ergänzen");
        datenbankErgaenzen.setMargin(new Insets(2, 2, 2, 2));
        datenbankErgaenzen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                datenbankErgaenzen_ActionPerformed(evt);
            }
        });
        cp.add(datenbankErgaenzen);
        datenbankBearbeiten.setBounds(272, 416, 155, 41);
        datenbankBearbeiten.setText("Datenbank Bearbeiten");
        datenbankBearbeiten.setMargin(new Insets(2, 2, 2, 2));
        datenbankBearbeiten.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                datenbankBearbeiten_ActionPerformed(evt);
            }
        });
        cp.add(datenbankBearbeiten);
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

        datenbankRunterladen.setBounds(504, 264, 155, 41);
        datenbankRunterladen.setText("Datenbank Runterladen");
        datenbankRunterladen.setMargin(new Insets(2, 2, 2, 2));
        datenbankRunterladen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                datenbankRunterladen_ActionPerformed(evt);
            }
        });
        cp.add(datenbankRunterladen);

        aktuelleTextdateiRunterladen.setBounds(504, 328, 155, 41);
        aktuelleTextdateiRunterladen.setText("Aktuelle Textdatei runterladen");
        aktuelleTextdateiRunterladen.setMargin(new Insets(2, 2, 2, 2));
        aktuelleTextdateiRunterladen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                aktuelleTextdateiRunterladen_ActionPerformed(evt);
            }
        });
        cp.add(aktuelleTextdateiRunterladen);

        importLogAnzeigen.setBounds(504, 392, 155, 41);
        importLogAnzeigen.setText("Import-Log Anzeigen");
        importLogAnzeigen.setMargin(new Insets(2, 2, 2, 2));
        importLogAnzeigen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                importLogAnzeigen_ActionPerformed(evt);
            }
        });
        cp.add(importLogAnzeigen);
        // Ende Komponenten

        if (!file1.exists()){
            manager.speichern();
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

    public void langsamerImport_ActionPerformed(ActionEvent evt) {
        // TODO hier Quelltext einfügen
    }

    public void zurueckButton1_ActionPerformed(ActionEvent evt) {
        // TODO hier Quelltext einfügen
    }

    public void zurueckButton2_ActionPerformed(ActionEvent evt) {
        // TODO hier Quelltext einfügen
    }

    public void exportMACAdressen_ActionPerformed(ActionEvent evt) {
        manager.exportiereMac();
        FileOpener fileOpen = new FileOpener("./export.txt");
        dialogfenster.zwischenablage();
    }

    public void datenbankErgaenzen_ActionPerformed(ActionEvent evt) {
        // TODO hier Quelltext einfügen
    }

    public void datenbankBearbeiten_ActionPerformed(ActionEvent evt) {
        // TODO hier Quelltext einfügen
    }

    public void handbuch_ActionPerformed(ActionEvent evt) {
        // TODO hier Quelltext einfügen
    }

    public void credits_ActionPerformed(ActionEvent evt) {
        // TODO hier Quelltext einfügen
    }

    public void datenbankRunterladen_ActionPerformed(ActionEvent evt) {
        // TODO hier Quelltext einfügen
    }

    public void aktuelleTextdateiRunterladen_ActionPerformed(ActionEvent evt) {
        // TODO hier Quelltext einfügen
    }

    public void importLogAnzeigen_ActionPerformed(ActionEvent evt) {
        FileOpener fileOpen = new FileOpener("./Fehlerlog.txt");
    }
}