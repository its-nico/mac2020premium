package code;

import javax.swing.*;
import java.awt.*;

public class Dialogfenster {
    public static void main(String[] args) {
        Dialogfenster fehlermeldungen = new Dialogfenster();
        fehlermeldungen.keineMacAdresse("1");
    }

    public void keineMacAdresse(String pMac) {
        Runnable runnable = (Runnable) Toolkit.getDefaultToolkit().getDesktopProperty("win.sound.exclamation");
        runnable.run();
        String message = "Der Datensatz zur Adresse " + pMac + " wurde nicht übernommen, da es sich um eine IP-Adresse handelt";
        JOptionPane.showMessageDialog(null, message, "Fehler", JOptionPane.ERROR_MESSAGE);
    }

    public void zwischenablage() {
        //Runnable runnable = (Runnable) Toolkit.getDefaultToolkit().getDesktopProperty("win.sound.exclamation");
        //runnable.run();
        String message = "Die MAC-Adressen aus dieser Datei wurden in ihre Zwischenablage kopiert";
        JOptionPane.showMessageDialog(null, message, "Kopie in Zwischenablage", JOptionPane.INFORMATION_MESSAGE);
    }
}
