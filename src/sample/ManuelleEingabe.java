package sample;

import code.FileOpener;
import code.Manager;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class ManuelleEingabe extends JFrame {
    // Anfang Attribute
    private final JLabel erklaerungsFeld = new JLabel();

    private final JTextField vornameFeld = new JTextField();
    private final JTextField nachnameFeld = new JTextField();
    private final JTextField kursstufeFeld = new JTextField();
    private final JTextField macAdresseFeld = new JTextField();
    private final JTextField weitereBemerkungenFeld = new JTextField();

    private final JButton pruefenUndHinzufuegen = new JButton();
    private final JButton importLogAnzeigen = new JButton();

    private final JLabel kursstufeLabel = new JLabel("Kursstufe");
    private final JLabel nachnameLabel = new JLabel("Nachname");
    private final JLabel vornameLabel = new JLabel("Vorname");
    private final JLabel macLabel = new JLabel("MAC-Adresse");
    private final JLabel grundLabel = new JLabel("Weitere Bemerkungen");

    private final JLabel rueckgabeFenster = new JLabel("");

    private String vorname;
    private String nachname;
    private String kursstufe;
    private String macAdresse;
    private String weitereBemerkung;

    private final Manager manager = new Manager();
    // Ende Attribute

    public ManuelleEingabe() {
        // Frame-Initialisierung
        super();

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); //look & feel von System wird hier gesetzt
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        int frameWidth = 530;
        int frameHeight = 450;
        setSize(frameWidth, frameHeight);
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (d.width - getSize().width) / 2;
        int y = (d.height - getSize().height) / 2;
        setLocation(x, y);
        setTitle("Manuelle Eingabe");
        setResizable(false);
        Container cp = getContentPane();
        cp.setLayout(null);


        // Anfang Komponenten
        erklaerungsFeld.setBounds(24, 24, 280, 35);
        erklaerungsFeld.setText("Hier können Sie Datensätze manuell hinzufügen");
        cp.add(erklaerungsFeld);

        vornameFeld.setBounds(24, 72, 190, 36);
        cp.add(vornameFeld);
        vorname = vornameFeld.getText();

        nachnameFeld.setBounds(24, 128, 190, 36);
        cp.add(nachnameFeld);
        nachname = nachnameFeld.getText();

        kursstufeFeld.setBounds(24, 184, 190, 36);
        cp.add(kursstufeFeld);
        kursstufe = kursstufeFeld.getText();;

        macAdresseFeld.setBounds(24, 240, 190, 36);
        cp.add(macAdresseFeld);
        macAdresse = macAdresseFeld.getText();

        weitereBemerkungenFeld.setBounds(24, 296, 190, 36);
        cp.add(weitereBemerkungenFeld);
        weitereBemerkung = weitereBemerkungenFeld.getText();

        rueckgabeFenster.setBounds(273, 72, 216, 260);
        cp.add(rueckgabeFenster);
        rueckgabeFenster.setOpaque(true);
        rueckgabeFenster.setBackground(Color.white);
        rueckgabeFenster.setBorder(new LineBorder(Color.DARK_GRAY,1));

        EmptyBorder eBorder = new EmptyBorder(2, 10, 2, 10); // oben, rechts, unten, links
        LineBorder lBorder = new LineBorder(new Color(100, 100, 100));
        rueckgabeFenster.setBorder(BorderFactory.createCompoundBorder(lBorder, eBorder));

        /* Buttons */
        pruefenUndHinzufuegen.setBounds(24, 352, 190, 33);
        pruefenUndHinzufuegen.setText("Prüfen und Hinzufügen");
        pruefenUndHinzufuegen.setMargin(new Insets(2, 2, 2, 2));
        pruefenUndHinzufuegen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                pruefenUndHinzufuegen_ActionPerformed(evt);
            }
        });
        cp.add(pruefenUndHinzufuegen);

        importLogAnzeigen.setBounds(272, 352, 216, 33);
        importLogAnzeigen.setText("Berichte anzeigen");
        importLogAnzeigen.setMargin(new Insets(2, 2, 2, 2));
        importLogAnzeigen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                importLogAnzeigen_ActionPerformed(evt);
            }
        });
        cp.add(importLogAnzeigen);

        /* Labels zu Eingabefenstern */
        kursstufeLabel.setBounds(26, 215, 190, 25);
        cp.add(kursstufeLabel);
        kursstufeLabel.setOpaque(true);

        nachnameLabel.setBounds(26, 159, 190, 25);
        cp.add(nachnameLabel);
        nachnameLabel.setOpaque(true);

        vornameLabel.setBounds(26, 103, 190, 25);
        cp.add(vornameLabel);
        vornameLabel.setOpaque(true);

        macLabel.setBounds(26, 271, 190, 25);
        cp.add(macLabel);
        macLabel.setOpaque(true);

        grundLabel.setBounds(26, 327, 190, 25);
        cp.add(grundLabel);
        grundLabel.setOpaque(true);
        // Ende Komponenten

        setVisible(true);
    } // end of public ManuelleEingabe

    // Anfang Methoden

    public static void main(String[] args) {
        new ManuelleEingabe();
    } // end of main

    public void pruefenUndHinzufuegen_ActionPerformed(ActionEvent evt) {
        vorname = vornameFeld.getText();
        nachname = nachnameFeld.getText();
        kursstufe = kursstufeFeld.getText();
        macAdresse = macAdresseFeld.getText();
        weitereBemerkung = weitereBemerkungenFeld.getText();
        rueckgabeFenster.setText(manager.ergaenze(kursstufe, nachname, vorname, macAdresse, weitereBemerkung));
    } // end of pruefenUndHinzufuegen_ActionPerformed

    private void importLogAnzeigen_ActionPerformed(ActionEvent evt) {
        FileOpener fileOpen = new FileOpener("./Fehlerlog.txt");
    }
    // Ende Methoden

} // end of class ManuelleEingabe
