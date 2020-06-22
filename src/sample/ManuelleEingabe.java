package sample;

import code.Manager;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.*;

public class ManuelleEingabe extends JFrame {


    // Anfang Attribute
    private JLabel erklaerungsFeld = new JLabel();

    private JTextField vornameFeld = new JTextField("Vorname");
    private JTextField nachnameFeld = new JTextField("Nachname");
    private JTextField kursstufeFeld = new JTextField("Kursstufe");
    private JTextField macAdresseFeld = new JTextField("MAC-Adresse");
    private JTextField weitereBemerkungenFeld = new JTextField("Weitere Bemerkungen");

    private JButton pruefenUndHinzufuegen = new JButton();
    private JButton zurueck = new JButton();

    private JLabel rueckgabeFenster = new JLabel("");

    private String vorname = new String();
    private String nachname = new String();
    private String kursstufe = new String();
    private String macAdresse = new String();
    private String weitereBemerkung = new String();

    private Manager manager = new Manager();
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
        setTitle("ManuelleEingabe");
        setResizable(false);
        Container cp = getContentPane();
        cp.setLayout(null);

        setIconImage(new ImageIcon(getClass().getResource("88208755.png")).getImage());


        // Anfang Komponenten

        erklaerungsFeld.setBounds(24, 24, 280, 35);
        erklaerungsFeld.setText("Hier können Sie Datensätze manuell hinzufügen.");
        cp.add(erklaerungsFeld);


        vornameFeld.setBounds(24, 72, 190, 36);
        cp.add(vornameFeld);
        vornameFeld.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                vornameFeldMouseClicked(evt);
            }
        });
        vorname = vornameFeld.getText();

        nachnameFeld.setBounds(24, 128, 190, 36);
        cp.add(nachnameFeld);
        nachname = nachnameFeld.getText();
        nachnameFeld.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nachnameFeldMouseClicked(evt);
            }
        });

        kursstufeFeld.setBounds(24, 184, 190, 36);
        cp.add(kursstufeFeld);
        kursstufe = kursstufeFeld.getText();
        kursstufeFeld.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                kursstufeFeldMouseClicked(evt);
            }
        });

        macAdresseFeld.setBounds(24, 240, 190, 36);
        cp.add(macAdresseFeld);
        macAdresse = macAdresseFeld.getText();
        macAdresseFeld.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                macAdresseFeldMouseClicked(evt);
            }
        });

        weitereBemerkungenFeld.setBounds(24, 296, 190, 36);
        cp.add(weitereBemerkungenFeld);
        weitereBemerkung = weitereBemerkungenFeld.getText();
        weitereBemerkungenFeld.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                weitereBemerkungenFeldMouseClicked(evt);
            }
        });

        rueckgabeFenster.setBounds(273, 72, 216, 260);
        cp.add(rueckgabeFenster);
        rueckgabeFenster.setOpaque(true);
        rueckgabeFenster.setBackground(Color.white);
        rueckgabeFenster.setBorder(new LineBorder(Color.DARK_GRAY,1));

        EmptyBorder eBorder = new EmptyBorder(2, 10, 2, 10); // oben, rechts, unten, links
        LineBorder lBorder = new LineBorder(new Color(100, 100, 100));
        rueckgabeFenster.setBorder(BorderFactory.createCompoundBorder(lBorder, eBorder));

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
        rueckgabeFenster.setText(manager.ergaenze(kursstufe, nachname, vorname, macAdresse, weitereBemerkung));
      //  rueckgabeFenster.setVerticalAlignment(JLabel.TOP);
     //   manager.ergaenze(kursstufe, nachname, vorname, macAdresse, weitereBemerkung);
    } // end of pruefenUndHinzufuegen_ActionPerformed

    public void zurueck_ActionPerformed(ActionEvent evt) {
        // TODO hier Quelltext einfügen

    } // end of zurueck_ActionPerformed

    private void vornameFeldMouseClicked(java.awt.event.MouseEvent evt) {
        vornameFeld.setText("");
    }

    private void nachnameFeldMouseClicked(java.awt.event.MouseEvent evt) {
        nachnameFeld.setText("");
    }

    private void kursstufeFeldMouseClicked(java.awt.event.MouseEvent evt) {
        kursstufeFeld.setText("");
    }

    private void macAdresseFeldMouseClicked(java.awt.event.MouseEvent evt) {
        macAdresseFeld.setText("");
    }

    private void weitereBemerkungenFeldMouseClicked(java.awt.event.MouseEvent evt) {
        weitereBemerkungenFeld.setText("");
    }
    // Ende Methoden

} // end of class ManuelleEingabe
