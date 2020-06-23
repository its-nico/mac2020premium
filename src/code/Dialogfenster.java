package code;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Dialogfenster {

    private schnellerImport schnellerImport = new schnellerImport();

    public void keineMacAdresse(String pMac) {
        Runnable runnable = (Runnable) Toolkit.getDefaultToolkit().getDesktopProperty("win.sound.exclamation");
        runnable.run();
        String message = "<html>" + "Die Datensätze der folgenden Adressen wurden nicht übernommen," + "\n" + "da es sich bei diesen um IP-Adressen handelt:" + "<br>" + pMac + "<br>" + "</html>";
        JLabel label = new JLabel(message);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        JOptionPane.showMessageDialog(null, label, "Keine MAC-Adresse", JOptionPane.ERROR_MESSAGE);
    }

    public void falschesFormat(String pMac) {
        Runnable runnable = (Runnable) Toolkit.getDefaultToolkit().getDesktopProperty("win.sound.exclamation");
        runnable.run();
        String message = "<html>" + "Die Datensätze der folgenden Adressen wurden nicht übernommen, da sich diese nicht" + "<br>" + "im für MAC-Adressen erforderlichen Format (xx:xx:xx:xx:xx:xx) befinden:" + "<br>" + pMac + "<br>" + "</html>";
        JLabel label = new JLabel(message);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        JOptionPane.showMessageDialog(null, label, "Falsches Format", JOptionPane.ERROR_MESSAGE);
    }

    public void zwischenablage() {
        String message = "Die MAC-Adressen aus dieser Datei wurden in ihre Zwischenablage kopiert";
        JOptionPane.showMessageDialog(null, message, "Kopie in Zwischenablage", JOptionPane.INFORMATION_MESSAGE);
    }

    public boolean bereitsImportiertDialog(String pPfad, ArrayList LIST_1) {
        String message = "Die Datei unter diesem Dateipfad wurde von ihnen bereits als Import-Datei verwendet. \nMöchten sie die Datei trotzdem importieren?";
        boolean answer = false;
        int result = JOptionPane.showConfirmDialog(null, message, "Dateiauswahl bestätigen", JOptionPane.YES_NO_OPTION);
        switch (result) {
            case JOptionPane.YES_OPTION:
                schnellerImport.schnellerImport(pPfad, LIST_1); //Aktion(en) bei Klicken auf den "Ja-Button"
            case JOptionPane.NO_OPTION:
        }
        return answer;
    }

    public void DatenbankImportiert (int anzahl) {
        String anzahlString = Integer.toString(anzahl);
        String message = anzahlString + " Datensätze wurden wurden aus der Datenbank heruntergeladen \nund in das Programm importiert";
        JOptionPane.showMessageDialog(null, message, "Import erfolgreich", JOptionPane.INFORMATION_MESSAGE);
    }

    public void DatenbankErgaenzt(int anzahl) {
        String anzahlString = Integer.toString(anzahl);
        String message = anzahlString + " Datensätze wurden erfolgreich in die Datenbank hochgeladen";
        JOptionPane.showMessageDialog(null, message, "Hochladen erfolgreich", JOptionPane.INFORMATION_MESSAGE);
    }

    public void DatenbankGeleert () {
        String message = "Alle Datensätze wurden aus der Datenbank gelöscht";
        JOptionPane.showMessageDialog(null, message, "Datenbank geleert", JOptionPane.INFORMATION_MESSAGE);
    }
}