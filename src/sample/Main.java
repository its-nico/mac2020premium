package sample;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

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
    private ManuelleEingabe manuellesEingabefenster;
    private JButton importLogAnzeigen = new JButton();

    // Ende Attribute

    public Main() {
        // Frame-Initialisierung
        super();
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


        // Anfang Komponenten

        ueberschrift.setBounds(16, 16, 390, 10);
        ueberschrift.setText("MAC-Projekt");
        cp.add(ueberschrift);
        beschreibung.setBounds(16, 104, 390, 60);
        beschreibung.setText("Hier könnte Ihre Beschreibung stehen!");
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

        setVisible(true);
    } // end of public loool

    // Anfang Methoden

    public static void main(String[] args) {
        new Main();
    } // end of main

    public void manuelleEingabe_ActionPerformed(ActionEvent evt) {
        manuellesEingabefenster = new ManuelleEingabe();
    } // end of manuelleEingabe_ActionPerformed

    public void schnellerImport_ActionPerformed(ActionEvent evt) {
        // TODO hier Quelltext einfügen

    } // end of schnellerImport_ActionPerformed

    public void langsamerImport_ActionPerformed(ActionEvent evt) {
        // TODO hier Quelltext einfügen

    } // end of langsamerImport_ActionPerformed

    public void zurueckButton1_ActionPerformed(ActionEvent evt) {
        // TODO hier Quelltext einfügen

    } // end of zurueckButton1_ActionPerformed

    public void zurueckButton2_ActionPerformed(ActionEvent evt) {
        // TODO hier Quelltext einfügen

    } // end of zurueckButton2_ActionPerformed

    public void exportMACAdressen_ActionPerformed(ActionEvent evt) {
        // TODO hier Quelltext einfügen

    } // end of exportMACAdressen_ActionPerformed

    public void datenbankErgaenzen_ActionPerformed(ActionEvent evt) {
        // TODO hier Quelltext einfügen

    } // end of datenbankErgaenzen_ActionPerformed

    public void datenbankBearbeiten_ActionPerformed(ActionEvent evt) {
        // TODO hier Quelltext einfügen

    } // end of datenbankBearbeiten_ActionPerformed

    public void handbuch_ActionPerformed(ActionEvent evt) {
        // TODO hier Quelltext einfügen

    } // end of handbuch_ActionPerformed

    public void credits_ActionPerformed(ActionEvent evt) {
        // TODO hier Quelltext einfügen

    } // end of credits_ActionPerformed

    public void datenbankRunterladen_ActionPerformed(ActionEvent evt) {
        // TODO hier Quelltext einfügen

    } // end of datenbankRunterladen_ActionPerformed

    public void aktuelleTextdateiRunterladen_ActionPerformed(ActionEvent evt) {
        // TODO hier Quelltext einfügen

    } // end of aktuelleTextdateiRunterladen_ActionPerformed

    public void importLogAnzeigen_ActionPerformed(ActionEvent evt) {
        // TODO hier Quelltext einfügen

    } // end of importLogAnzeigen_ActionPerformed

    // Ende Methoden
} // end of class loool