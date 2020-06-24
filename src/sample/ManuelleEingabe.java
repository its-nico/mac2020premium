package sample;

import code.Manager;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class ManuelleEingabe extends JFrame {
    // Anfang Attribute
    private final JLabel erklaerungsFeld = new JLabel();

    private final JTextField vornameFeld = new JTextField("vorname");
    private final JTextField nachnameFeld = new JTextField("nachname");
    private final JTextField kursstufeFeld = new JTextField("kursstufe");
    private final JTextField macAdresseFeld = new JTextField("mac");
    private final JTextField weitereBemerkungenFeld = new JTextField("Bemerkung");

    private final JButton pruefenUndHinzufuegen = new JButton();
    private final JButton zurueck = new JButton();

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

        setIconImage(new ImageIcon(getClass().getResource("Logo2.png")).getImage());

        // Anfang Komponenten
        erklaerungsFeld.setBounds(24, 24, 280, 35);
        erklaerungsFeld.setText("Hier können Sie Datensätze manuell hinzufügen");
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

        pruefenUndHinzufuegen.setBounds(24, 352, 190, 33);
        pruefenUndHinzufuegen.setText("Prüfen und Hinzufügen");
        pruefenUndHinzufuegen.setMargin(new Insets(2, 2, 2, 2));
        pruefenUndHinzufuegen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                pruefenUndHinzufuegen_ActionPerformed(evt);
            }
        });
        cp.add(pruefenUndHinzufuegen);

        /* Labels zu Eingabefenstern */
        kursstufeLabel.setBounds(25, 215, 190, 25);
        cp.add(kursstufeLabel);
        kursstufeLabel.setOpaque(true);

        nachnameLabel.setBounds(25, 159, 190, 25);
        cp.add(nachnameLabel);
        nachnameLabel.setOpaque(true);

        vornameLabel.setBounds(25, 103, 190, 25);
        cp.add(vornameLabel);
        vornameLabel.setOpaque(true);

        macLabel.setBounds(25, 271, 190, 25);
        cp.add(macLabel);
        macLabel.setOpaque(true);

        grundLabel.setBounds(25, 272, 190, 25);
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
