package code;

import javax.swing.*;

public class Fehlermeldungen {

    public void keineMacAdresse(String pMac) {
        String message = "Der Datensatz zur Adresse " + pMac + " wurde nicht übernommen, da es sich um eine IP-Adresse handelt.";
        JOptionPane.showMessageDialog(null, message, "Fehler", JOptionPane.ERROR_MESSAGE);
    }
}
