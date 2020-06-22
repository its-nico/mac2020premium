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
        String message = "Die Datensätze der folgenden Adressen wurden nicht übernommen," + "\n" + "da es sich bei diesen um IP-Adressen handelt:" + pMac;
        JOptionPane.showMessageDialog(null, message, "Fehler", JOptionPane.ERROR_MESSAGE);
    }

    public void falschesFormat(String pMac){
        Runnable runnable = (Runnable) Toolkit.getDefaultToolkit().getDesktopProperty("win.sound.exclamation");
        runnable.run();
        String message = "Die Datensätze der folgenden Adressen wutden nicht übernommen," + "\n" + "da sich diese nicht im für MAC-Adressen erforderlichen Format (xx:xx:xx:xx:xx:xx) befinden:" + pMac;
        JOptionPane.showMessageDialog(null, message, "Fehler", JOptionPane.ERROR_MESSAGE);
    }

    public void zwischenablage() {
        //Runnable runnable = (Runnable) Toolkit.getDefaultToolkit().getDesktopProperty("win.sound.exclamation");
        //runnable.run();
        String message = "Die MAC-Adressen aus dieser Datei wurden in ihre Zwischenablage kopiert";
        JOptionPane.showMessageDialog(null, message, "Kopie in Zwischenablage", JOptionPane.INFORMATION_MESSAGE);
    }
}
