package sample;

import code.Einstellung;
import code.Manager;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class Einstellungen extends JFrame {
    // Anfang Attribute
    //JLabel
    private final JLabel erklaerung = new JLabel();
    private final JLabel kategorie1 = new JLabel();

    private final JLabel hinweisfensterBoxTooltipLabel = new JLabel("?");
    private final JLabel fehlerfensterBoxTooltipLabel = new JLabel("?");

    //JButton
    private final JButton speichern = new JButton();

    //Checkbox
    private final Checkbox hinweisfensterBox = new Checkbox("Hinweisfenster anzeigen");
    private final Checkbox fehlerfensterBox = new Checkbox("Fehlerfenster anzeigen");

    //Sonstige Klassen
    private final Manager manager = new Manager();
    private ArrayList<Einstellung> List_2;

    //Variablen
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
        int frameWidth =270;
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
        erklaerung.setBounds(25,15,250,35);
        erklaerung.setText("<html>Bewegen sie die Maus für mehr<br>Informationen über die Fragezeichen</html>");
        cp.add(erklaerung);

        kategorie1.setBounds(25, 45, 250, 35);
        kategorie1.setText("<html><u>Dialogfenster</u></html>");
        cp.add(kategorie1);


        // Buttons
        speichern.setBounds(28, 320, 200, 30);
        speichern.setText("Speichern & Verlassen");
        speichern.setMargin(new Insets(2, 2, 2, 2));
        speichern.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                speichern_ActionPerformed(evt);
            }
        });
        cp.add(speichern);

        //Hinweisfenster-Option
        hinweisfensterBox.setBounds(50,80,170,20);
        hinweisfensterBox.setState(getEinstellungWert(hinweisfensterBox.getLabel()));
        cp.add(hinweisfensterBox);

        hinweisfensterBoxTooltipLabel.setBounds(220, 80, 20, 20);
        hinweisfensterBoxTooltipLabel.setToolTipText("Einige essentielle Dialogfenster werden durch diese Einstellung möglicherweise nicht beeinflusst");
        //hinweisfensterBoxTooltipLabel.setBorder(new LineBorder(new Color(0,0,0),1));
        cp.add(hinweisfensterBoxTooltipLabel);

        //Fehlerfenster-Option
        fehlerfensterBox.setBounds(50,105,170,20);
        fehlerfensterBox.setState(getEinstellungWert(fehlerfensterBox.getLabel()));
        cp.add(fehlerfensterBox);

        fehlerfensterBoxTooltipLabel.setBounds(220, 105, 20, 20);
        fehlerfensterBoxTooltipLabel.setToolTipText("Einige essentielle Dialogfenster werden durch diese Einstellung möglicherweise nicht beeinflusst");
        cp.add(fehlerfensterBoxTooltipLabel);

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
        einstellungWert = hinweisfensterBox.getState();
        einstellungTyp = hinweisfensterBox.getLabel();
        manager.aendereEinstellung(einstellungTyp, einstellungWert);

        einstellungWert = fehlerfensterBox.getState();
        einstellungTyp = fehlerfensterBox.getLabel();
        manager.aendereEinstellung(einstellungTyp, einstellungWert);

        dispose();
    } // end of pruefenUndHinzufuegen_ActionPerformed

    // Ende Methoden

} // end of class ManuelleEingabe