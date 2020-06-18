import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class ManuelleEingabe extends JFrame {
    // Anfang Attribute
    private JTextArea logFeld = new JTextArea("");
    private JScrollPane jTextArea1ScrollPane = new JScrollPane(logFeld);
    private JLabel erklaerungsFeld = new JLabel();
    private JTextField vornameFeld = new JTextField("Vorname");
    private JTextField nachnameFeld = new JTextField("Nachname");
    private JTextField kursstufeFeld = new JTextField("Kursstufe");
    private JTextField macAdresseFeld = new JTextField("MAC-Adresse");
    private JTextField weitereBemerkungenFeld = new JTextField("Weitere Bemerkungen");
    private JButton pruefenUndHinzufuegen = new JButton();
    private JButton zurueck = new JButton();
    // Ende Attribute

    public ManuelleEingabe() {
        // Frame-Initialisierung
        super();
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        int frameWidth = 580;
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

        jTextArea1ScrollPane.setBounds(320, 32, 200, 300);
        cp.add(jTextArea1ScrollPane);

        erklaerungsFeld.setBounds(24, 24, 240, 35);
        erklaerungsFeld.setText("Hier könnte Ihre Erklärung stehen!");
        cp.add(erklaerungsFeld);


        vornameFeld.setBounds(24, 72, 190, 36);
        cp.add(vornameFeld);
        nachnameFeld.setBounds(24, 128, 190, 36);
        cp.add(nachnameFeld);
        kursstufeFeld.setBounds(24, 184, 190, 36);
        cp.add(kursstufeFeld);
        macAdresseFeld.setBounds(24, 240, 190, 36);
        cp.add(macAdresseFeld);
        weitereBemerkungenFeld.setBounds(24, 296, 190, 36);
        cp.add(weitereBemerkungenFeld);

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
        // TODO hier Quelltext einfügen

    } // end of pruefenUndHinzufuegen_ActionPerformed

    public void zurueck_ActionPerformed(ActionEvent evt) {
        // TODO hier Quelltext einfügen

    } // end of zurueck_ActionPerformed

    // Ende Methoden

} // end of class ManuelleEingabe
