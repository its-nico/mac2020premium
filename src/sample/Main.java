package sample;

import code.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.io.File;
import java.lang.*;
import java.sql.SQLException;
import java.util.ArrayList;

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
    private final JButton datensaetzeZeigen = new JButton();
    private final JButton handbuch = new JButton();
    private final JButton credits = new JButton();
    private final JButton einstellungn = new JButton();
    private final JButton inDatenbankSpeichern = new JButton();
    private final JButton datenbankImportieren = new JButton();
    private final JButton leerenDatenbank = new JButton();
    private final JButton support = new JButton();

    private ManuelleEingabe manuellesEingabefenster;
    private Einstellungen einstellungenFenster;

    private final Manager manager = new Manager();
    private final OeffnenDialogClass oeffnenDialogClass = new OeffnenDialogClass();
    private final Dialogfenster dialogfenster = new Dialogfenster();
    private final Datenbank datenbank = new Datenbank();

    private final File file1 = new File("./savedList.ser");
    private final File file2 = new File("./settings.ser");

    private ArrayList<Einstellung> LIST_2 = new ArrayList<>();

    private Closing closing;

    // Ende Attribute

    public Main() {
        // Frame-Initialisierung
        super();

        /*try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()){
                if ("Nimbus".equals(info.getName())){
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }*/

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
        setTitle("MAC-Manager");
        setResizable(false);
        Container cp = getContentPane();
        cp.setLayout(null);

        setIconImage(new ImageIcon(getClass().getResource("Logo2.png")).getImage());

        // Anfang Komponenten

        ueberschrift.setBounds(16, 16, 390, 50);
        ueberschrift.setText("<html> MAC-Manager<u><html>");
        ueberschrift.setFont(new Font("Roboto", Font.BOLD, 42));
        cp.add(ueberschrift);

        beschreibung.setBounds(16, 75, 400, 120);
        beschreibung.setText("<html>Dieses Programm dient der Verwaltung und Korrektur von MAC-Adressen. Es ermöglicht das Speichern ganzer Datensätze, die Informationen über den Namen der Schüler*innen, deren Kursstufe und MAC-Adresse enthalten. <br>" +
                "Verknüpfungen mit einer Datenbank sind ebenfalls möglich.<br>" +
                "Das Programm ist für die Nutzung an Schulen optimiert.</html>");
        cp.add(beschreibung);

        datenHinzufuegen.setBounds(16, 216, 230, 28);
        datenHinzufuegen.setText("Lokale Datensätze");
        cp.add(datenHinzufuegen);

        datenAnsehen.setBounds(528, 216, 190, 28);
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
        manuelleEingabe.setBackground(new Color(107, 149, 230));
        manuelleEingabe.setBorder(new LineBorder(new Color(107, 149, 230),1));
        manuelleEingabe.setToolTipText("Datensätze manuell über Eingabefelder in das Programm einfügen");

        schnellerImport.setBounds(16, 328, 155, 41);
        schnellerImport.setText("Datensätze importieren");
        schnellerImport.setMargin(new Insets(2, 2, 2, 2));
        schnellerImport.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                schnellerImport_ActionPerformed(evt);
            }
        });
        cp.add(schnellerImport);
        schnellerImport.setBackground(new Color(107, 149, 230));
        schnellerImport.setBorder(new LineBorder(new Color(107, 149, 230),1));
        schnellerImport.setToolTipText("Datensätze aus einer txt- oder CSV-Datei in das Programm importieren");

        datenLoeschen.setBounds(16, 392, 155, 41);
        datenLoeschen.setText("Datensätze löschen");
        datenLoeschen.setMargin(new Insets(2, 2, 2, 2));
        datenLoeschen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                datenLoeschen_ActionPerformed(evt);
            }
        });
        cp.add(datenLoeschen);
        datenLoeschen.setBackground(new Color(107, 149, 230));
        datenLoeschen.setBorder(new LineBorder(new Color(107, 149, 230),1));
        datenLoeschen.setToolTipText("Im Programm gespeicherte Datensätze löschen");

        exportMACAdressen.setBounds(272, 264, 155, 41);
        exportMACAdressen.setText("Export MAC-Adressen");
        exportMACAdressen.setMargin(new Insets(2, 2, 2, 2));
        exportMACAdressen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                exportMACAdressen_ActionPerformed(evt);
            }
        });
        cp.add(exportMACAdressen);
        exportMACAdressen.setBackground(new Color(107, 149, 230));
        exportMACAdressen.setBorder(new LineBorder(new Color(107, 149, 230),1));
        exportMACAdressen.setToolTipText("Alle vorhandenen und korrigierten MAC-Adressen in einer txt-Datei anzeigen lassen");

        importLogAnzeigen.setBounds(272, 328, 155, 41);
        importLogAnzeigen.setText("Berichte anzeigen");
        importLogAnzeigen.setMargin(new Insets(2, 2, 2, 2));
        importLogAnzeigen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                importLogAnzeigen_ActionPerformed(evt);
            }
        });
        cp.add(importLogAnzeigen);
        importLogAnzeigen.setBackground(new Color(107, 149, 230));
        importLogAnzeigen.setBorder(new LineBorder(new Color(107, 149, 230),1));
        importLogAnzeigen.setToolTipText("Details über die Änderungen der Autokorrektur an den MAC-Adressen einsehen");

        datensaetzeZeigen.setBounds(272, 392, 155, 41);
        datensaetzeZeigen.setText("Datensätze anzeigen");
        datensaetzeZeigen.setMargin(new Insets(2, 2, 2, 2));
        datensaetzeZeigen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                datensaetzeZeigen_ActionPerformed(evt);
            }
        });
        cp.add(datensaetzeZeigen);
        datensaetzeZeigen.setBackground(new Color(107, 149, 230));
        datensaetzeZeigen.setBorder(new LineBorder(new Color(107, 149, 230),1));
        datensaetzeZeigen.setToolTipText("Alle gespeicherten Datensätze vollständig anzeigen");

        handbuch.setBounds(528, 16, 155, 49);
        handbuch.setText("Handbuch");
        handbuch.setMargin(new Insets(2, 2, 2, 2));
        handbuch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                handbuch_ActionPerformed(evt);
            }
        });
        cp.add(handbuch);
        handbuch.setBackground(new Color(0, 204, 255));
        handbuch.setBorder(new LineBorder(new Color(0,204,255),1));
        handbuch.setToolTipText("Das Handbuch zu diesem Programm einsehen, dass die verschiedenen Funktionen detailliert erklärt");

        credits.setBounds(528, 80, 70, 49);
        credits.setText("Credits");
        credits.setMargin(new Insets(2, 2, 2, 2));
        credits.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                credits_ActionPerformed(evt);
            }
        });
        cp.add(credits);
        credits.setBackground(new Color(0, 204, 255));
        credits.setBorder(new LineBorder(new Color(0,204,255),1));
        credits.setToolTipText("Die Namen der allerbesten Entwickler von diesem Programm ansehen");

        support.setBounds(613, 80, 70, 49);
        support.setText("Support");
        support.setMargin(new Insets(2, 2, 2, 2));
        support.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                support_ActionPerformed(evt);
            }
        });
        cp.add(support);
        support.setBackground(new Color(0, 204, 255));
        support.setBorder(new LineBorder(new Color(0,204,255),1));
        support.setToolTipText("itslearning-Benutzernamen der Entwickler einsehen");

        einstellungn.setBounds(528, 144, 155, 49);
        einstellungn.setText("Einstellungen");
        einstellungn.setMargin(new Insets(2, 2, 2, 2));
        einstellungn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                einstellungn_ActionPerformed(evt);
            }
        });
        cp.add(einstellungn);
        einstellungn.setBackground(new Color(0, 204, 255));
        einstellungn.setBorder(new LineBorder(new Color(0,204,255),1));
        einstellungn.setToolTipText("Das Programm anpassen");

        inDatenbankSpeichern.setBounds(528, 264, 155, 41);
        inDatenbankSpeichern.setText("Datenbank ergänzen");
        inDatenbankSpeichern.setMargin(new Insets(2, 2, 2, 2));
        inDatenbankSpeichern.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                inDatenbankSpeichern_ActionPerformed(evt);
            }
        });
        cp.add(inDatenbankSpeichern);
        inDatenbankSpeichern.setBackground(new Color(179, 102, 255));
        inDatenbankSpeichern.setBorder(new LineBorder(new Color(179, 102, 255),1));
        inDatenbankSpeichern.setToolTipText("Die im Programm vorhandenen Datensätze in eine Datenbank hochladen");

        datenbankImportieren.setBounds(528, 328, 155, 41);
        datenbankImportieren.setText("Datenbank importieren");
        datenbankImportieren.setMargin(new Insets(2, 2, 2, 2));
        datenbankImportieren.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                datenbankImportieren_ActionPerformed(evt);
            }
        });
        cp.add(datenbankImportieren);
        datenbankImportieren.setBackground(new Color(179, 102, 255));
        datenbankImportieren.setBorder(new LineBorder(new Color(179, 102, 255),1));
        datenbankImportieren.setToolTipText("Die auf der Datenbank vorhandenen Datensätze in das Programm importieren");

        leerenDatenbank.setBounds(528, 392, 155, 41);
        leerenDatenbank.setText("Datenbank leeren");
        leerenDatenbank.setMargin(new Insets(2, 2, 2, 2));
        leerenDatenbank.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                leerenDatenbank_ActionPerformed(evt);
            }
        });
        cp.add(leerenDatenbank);
        leerenDatenbank.setBackground(new Color(179, 102, 255));
        leerenDatenbank.setBorder(new LineBorder(new Color(179, 102, 255),1));
        leerenDatenbank.setToolTipText("Alle auf der Datenbank gespeicherten Datensätze löschen");

        // Ende Komponenten

        if (!file1.exists()){
            manager.speichern(); //hier wird überprüft, ob die serialisierte Listen-Datei schon vorhanden ist. Wenn nicht, wird eine erstellt, da sonst eine NullPointerException auftritt
        }
        if (!file2.exists()){
            manager.speichernEinstellungen(); //hier wird überprüft, ob die serialisierte Listen-Datei schon vorhanden ist. Wenn nicht, wird eine erstellt, da sonst eine NullPointerException auftritt
        }

        manager.laden();
        manager.ladenEinstellungen();

        setVisible(true);

        addWindowListener(new WindowAdapter()
        {
            @Override
           public void windowClosing(WindowEvent e) {
                manager.speichernEinstellungen();
                if (manager.getEinstellungWertZuEinstellungTyp("Speichern-Abfrage")) {
                    closing = new Closing();
                    closing.close(manager);
                } else {
                    manager.speichern();
                }

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
        manager.listeLoeschen();

    }

    public void exportMACAdressen_ActionPerformed(ActionEvent evt) {
        manager.exportiereMac();
        LIST_2 = manager.getList2();
        if (manager.getEinstellungWertZuEinstellungTyp("Bei Export kopieren")) {
            if (!manager.getList1().isEmpty()) {
                dialogfenster.zwischenablage(); //Wird nur geöffnet, wenn Inhalt von txt-Datei tatsächlich kopiert wird
            }
        }
        FileOpener fileOpen = new FileOpener("./export.txt");
    }

    public void handbuch_ActionPerformed(ActionEvent evt) {
        FileOpener fileOpen = new FileOpener("./Handbuch.pdf");
    }

    public void credits_ActionPerformed(ActionEvent evt) {
        FileOpener fileOpen = new FileOpener("./Credits.txt");
    }

    public void support_ActionPerformed(ActionEvent evt) {
        FileOpener fileOpen = new FileOpener("./Support.txt");
    }

    public void einstellungn_ActionPerformed(ActionEvent evt) {
        einstellungenFenster = new Einstellungen();
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
        File file = new File("./Fehlerlog.txt");
        if (file.exists()) {
            FileOpener fileOpen = new FileOpener("./Fehlerlog.txt");
        } else {
            dialogfenster.logExistiertNicht();
        }
    }

    public void datensaetzeZeigen_ActionPerformed(ActionEvent evt) {
        manager.exportiereDatensaetze();
        FileOpener fileOpen = new FileOpener("./datensaetze.txt");
    }
}