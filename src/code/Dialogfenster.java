package code;

import javax.swing.*;
import java.awt.*;

public class Dialogfenster {

    private schnellerImport schnellerImport = new schnellerImport();

    public void keineMacAdresse(String pMac) {
        Runnable runnable = (Runnable) Toolkit.getDefaultToolkit().getDesktopProperty("win.sound.exclamation");
        runnable.run();
        String message = "Die Datensätze der folgenden Adressen wurden nicht übernommen," + "\n" + "da es sich bei diesen um IP-Adressen handelt:" + "<br>" + pMac;
        JLabel label = new JLabel(message);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        JOptionPane.showMessageDialog(null, label, "Fehler", JOptionPane.ERROR_MESSAGE);
    }

    public void falschesFormat(String pMac){
        Runnable runnable = (Runnable) Toolkit.getDefaultToolkit().getDesktopProperty("win.sound.exclamation");
        runnable.run();
        String message = "<html><p align=\"center\">" + "Die Datensätze der folgenden Adressen wurden nicht übernommen, da sich diese nicht" + "<br>" + "im für MAC-Adressen erforderlichen Format (xx:xx:xx:xx:xx:xx) befinden:" + pMac + "<br>" + "</p></html>\"";
        JLabel label = new JLabel(message);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        JOptionPane.showMessageDialog(null, label, "Fehler", JOptionPane.ERROR_MESSAGE);
    }

    public void zwischenablage() {
        //Runnable runnable = (Runnable) Toolkit.getDefaultToolkit().getDesktopProperty("win.sound.exclamation");
        //runnable.run();
        String message = "Die MAC-Adressen aus dieser Datei wurden in ihre Zwischenablage kopiert";
        JOptionPane.showMessageDialog(null, message, "Kopie in Zwischenablage", JOptionPane.INFORMATION_MESSAGE);
    }

    public boolean bereitsImportiertDialog(String pPfad, List1 LIST_1) {
        String message = "Die Datei unter diesem Dateipfad wurde von ihnen bereits als Import-Datei verwendet. \nMöchten sie die Datei trotzdem importieren?";
        boolean answer = false;
        int result = JOptionPane.showConfirmDialog(null, message, "Dateiauswahl bestätigen", JOptionPane.YES_NO_OPTION);
        switch (result) {
            case JOptionPane.YES_OPTION:
                schnellerImport.schnellerImport(pPfad, LIST_1); //Aktion(en) bei Klicken auf den "Ja-Button"
            case JOptionPane.NO_OPTION:
                System.out.println("Die Datei wurde nicht importiert, da der Benutzer diese Option gewählt hat");
        }
        return answer;
    }
}
