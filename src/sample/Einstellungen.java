package sample;

import code.Einstellung;
import code.Manager;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class Einstellungen extends JFrame {
    // Anfang Attribute
    private final JLabel erklaerungsFeld = new JLabel();

    private final JTextField vornameFeld = new JTextField();

    private final JButton speichern = new JButton();

    private final Checkbox dialogfensterBox = new Checkbox("Dialogfenster anzeigen");
    private final Manager manager = new Manager();
    private ArrayList<Einstellung> List_2;

    private String einstellungTyp;
    private boolean einstellungWert;
    // Ende Attribute

    public Einstellungen() {
        // Frame-Initialisierung
        super();

        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()){
                if ("Nimbus".equals(info.getName())){
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        int frameWidth =250;
        int frameHeight = 400;
        setSize(frameWidth, frameHeight);
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (d.width - getSize().width) / 2;
        int y = (d.height - getSize().height) / 2;
        setLocation(x, y);
        setTitle("Einstellungen");
        setResizable(false);
        Container cp = getContentPane();
        cp.setLayout(null);
        setIconImage(new ImageIcon(getClass().getResource("Logo2.png")).getImage());

        List_2 = manager.getList2();

        // Anfang Komponenten
        erklaerungsFeld.setBounds(25, 15, 250, 35);
        erklaerungsFeld.setText("Erklärung");
        cp.add(erklaerungsFeld);


        /* Buttons */
        speichern.setBounds(18, 320, 200, 30);
        speichern.setText("Speichern & Verlassen");
        speichern.setMargin(new Insets(2, 2, 2, 2));
        speichern.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                speichern_ActionPerformed(evt);
            }
        });
        cp.add(speichern);

        dialogfensterBox.setBounds(50,50,200,30);
        dialogfensterBox.setState(getEinstellungWert(dialogfensterBox.getLabel()));
        cp.add(dialogfensterBox);

        setVisible(true);
    } // end of public ManuelleEingabe

    // Anfang Methoden

    public static void main(String[] args) {
        new ManuelleEingabe();
    } // end of main

    public boolean getEinstellungWert(String pEinstellungTyp) {
        boolean einstellungWert = true;
        int len = List_2.size();
        for (int i = 0; i < len; i++) {
            Einstellung einstellung = List_2.get(i);
            if (einstellung.getEinstellungTyp().equals(pEinstellungTyp)) { //Wenn Listen-Element gefunden wird, dass den Einstellungswert für den übergebenen Einstellungstyp speichert, wird der Einstellungstyp auf den entsprechenden Wert gesetzt
                einstellungWert = einstellung.getEinstellungWert();
            }
        }
        return einstellungWert;
    }

    public void speichern_ActionPerformed(ActionEvent evt) {
        einstellungWert = dialogfensterBox.getState();
        einstellungTyp = dialogfensterBox.getLabel();
        manager.aendereEinstellung(einstellungTyp, einstellungWert);
        dispose();
    } // end of pruefenUndHinzufuegen_ActionPerformed

    // Ende Methoden

} // end of class ManuelleEingabe