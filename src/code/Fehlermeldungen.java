package code;

import javax.swing.*;
import java.awt.*;

public class Fehlermeldungen {
    public static void main(String[] args) {
        Fehlermeldungen fehlermeldungen = new Fehlermeldungen();
        fehlermeldungen.keineMacAdresse("1");
    }

    public void keineMacAdresse(String pMac) {
        Runnable runnable = (Runnable) Toolkit.getDefaultToolkit().getDesktopProperty("win.sound.exclamation");
        runnable.run();
        String message = "Der Datensatz zur Adresse " + pMac + " wurde nicht übernommen, da es sich um eine IP-Adresse handelt.";
        JOptionPane.showMessageDialog(null, message, "Fehler", JOptionPane.ERROR_MESSAGE);
    }
}
