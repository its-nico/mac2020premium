package sample;

import code.Manager;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class ManuelleEingabe extends JFrame {
    // Anfang Attribute
    private JLabel erklaerungsFeld = new JLabel();

    private JTextField vornameFeld = new JTextField();
    private JTextField nachnameFeld = new JTextField();
    private JTextField kursstufeFeld = new JTextField();
    private JTextField macAdresseFeld = new JTextField();
    private JTextField weitereBemerkungenFeld = new JTextField();

    private JButton pruefenUndHinzufuegen = new JButton();
    private JButton zurueck = new JButton();

    private String vorname;
    private String nachname;
    private String kursstufe;
    private String macAdresse;
    private String weitereBemerkung;

    private Manager manager = new Manager();
    // Ende Attribute

    public ManuelleEingabe() {
        // Frame-Initialisierung
        super();
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        int frameWidth = 342;
        int frameHeight = 450;
        setSize(frameWidth, frameHeight);
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (d.width - getSize().width) / 2;
        int y = (d.height - getSize().height) / 2;
        setLocation(x, y);
        setTitle("ManuelleEingabe");
        setResizable(false);
        Container cp = getContentPane();
        cp.setLayout(null);
        // Anfang Komponenten

        erklaerungsFeld.setBounds(24, 24, 280, 35);
        erklaerungsFeld.setText("Hier können Sie Datensätze manuell hinzufügen.");
        cp.add(erklaerungsFeld);


        vornameFeld.setBounds(24, 72, 190, 36);
        cp.add(vornameFeld);
        vorname = vornameFeld.getText();
        nachnameFeld.setBounds(24, 128, 190, 36);
        cp.add(nachnameFeld);
        nachname = nachnameFeld.getText();
        kursstufeFeld.setBounds(24, 184, 190, 36);
        cp.add(kursstufeFeld);
        kursstufe = kursstufeFeld.getText();
        macAdresseFeld.setBounds(24, 240, 190, 36);
        cp.add(macAdresseFeld);
        macAdresse = macAdresseFeld.getText();
        weitereBemerkungenFeld.setBounds(24, 296, 190, 36);
        cp.add(weitereBemerkungenFeld);
        weitereBemerkung = weitereBemerkungenFeld.getText();

        pruefenUndHinzufuegen.setBounds(24, 352, 235, 33);
        pruefenUndHinzufuegen.setText("Prüfen und Hinzufügen");
        pruefenUndHinzufuegen.setMargin(new Insets(2, 2, 2, 2));
        pruefenUndHinzufuegen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                pruefenUndHinzufuegen_ActionPerformed(evt);
            }
        });
        cp.add(pruefenUndHinzufuegen);
        zurueck.setBounds(273, 352, 35, 33);
        zurueck.setText("↺");
        zurueck.setMargin(new Insets(2, 2, 2, 2));
        zurueck.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                zurueck_ActionPerformed(evt);
            }
        });
        cp.add(zurueck);
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
        manager.ergaenze(kursstufe, nachname, vorname, macAdresse, weitereBemerkung);
    } // end of pruefenUndHinzufuegen_ActionPerformed

    public void zurueck_ActionPerformed(ActionEvent evt) {
        // TODO hier Quelltext einfügen

    } // end of zurueck_ActionPerformed

    // Ende Methoden

} // end of class ManuelleEingabe
