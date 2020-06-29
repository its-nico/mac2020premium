package code;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Dialogfenster { //In dieser Klasse werden die Dialogfenster zu verschiedenen Warnungen und Fehlern erstellt

    private final schnellerImport schnellerImport = new schnellerImport();
    private final Manager manager = new Manager();
    private ArrayList<Einstellung> LIST_2;

    //Dialogfenster wird bei Import und manueller Eingabe genutzt
    public void keineMacAdresse(String pMac) { //Kategorie: Fehlermeldung (Autokorrektur)
        Runnable runnable = (Runnable) Toolkit.getDefaultToolkit().getDesktopProperty("win.sound.exclamation");
        runnable.run();
        //Dieser Text wird im Fenster angezeigt
        String message = "<html>" + "Die Datensätze der folgenden Adressen wurden nicht übernommen," + "\n" + "da es sich bei diesen um IP-Adressen handelt:" + "<br>" + pMac + "<br>" + "</html>";
        JLabel label = new JLabel(message);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        JOptionPane.showMessageDialog(null, label, "Keine MAC-Adresse", JOptionPane.ERROR_MESSAGE); //Es handelt sich um eine Fehlermeldung
    }

    //Dialogfenster wird bei Import und manueller Eingabe genutzt
    public void falschesFormat(String pMac) { //Kategorie: Fehlermeldung (Autokorrektur)
        Runnable runnable = (Runnable) Toolkit.getDefaultToolkit().getDesktopProperty("win.sound.exclamation");
        runnable.run();
        String message = "<html>" + "Die Datensätze der folgenden Adressen wurden nicht übernommen, da sich diese nicht" + "<br>" + "im für MAC-Adressen erforderlichen Format (xx:xx:xx:xx:xx:xx) befinden:" + "<br>" + pMac + "<br>" + "</html>";
        JLabel label = new JLabel(message);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        JOptionPane.showMessageDialog(null, label, "Falsches Format", JOptionPane.ERROR_MESSAGE); //Es handelt sich um eine Fehlermeldung
    }

    public void zwischenablage() { //Kategorie: Hinweis (Export)
        String message = "Die MAC-Adressen aus dieser Datei wurden (wenn vorhanden) in ihre Zwischenablage kopiert";
        JOptionPane.showMessageDialog(null, message, "Kopie in Zwischenablage", JOptionPane.INFORMATION_MESSAGE); //Es handelt sich um ein Informationsfenster
    }

    public void bereitsImportiertDialog(String pPfad, ArrayList LIST_1) { //Kategorie: essentiell (nicht durch Einstellungen deaktivierbar)
        LIST_2 = manager.getList2();
        if (manager.getEinstellungWertZuEinstellungTyp("Hinweisfenster anzeigen")) {
            String message = "Die Datei unter diesem Dateipfad wurde von ihnen bereits als Import-Datei verwendet\nMöchten sie die Datei trotzdem importieren?";
            int result = JOptionPane.showConfirmDialog(null, message, "Dateiauswahl bestätigen", JOptionPane.YES_NO_OPTION); //Nutzer*innen müssen sich zwischen JA/Nein entscheiden
            switch (result) {
                case JOptionPane.YES_OPTION:
                    schnellerImport.schnellerImport(pPfad, LIST_1); //Aktion(en) bei Klicken auf den "Ja-Button"
                case JOptionPane.NO_OPTION:
            }
        }
    }

    public void DatenbankImportiert (int anzahl) { //Kategorie: Hinweis (Datenbank)
        String anzahlString = Integer.toString(anzahl);
        String message = anzahlString + " Datensätze wurden wurden aus der Datenbank heruntergeladen\nund in das Programm importiert";
        JOptionPane.showMessageDialog(null, message, "Import erfolgreich", JOptionPane.INFORMATION_MESSAGE); //Es handelt sich um ein Informationsfenster
    }

    public void DatenbankErgaenzt(int anzahl) { //Kategorie: Hinweis (Datenbank)
        String anzahlString = Integer.toString(anzahl);
        String message = anzahlString + " Datensätze wurden erfolgreich in die Datenbank hochgeladen";
        JOptionPane.showMessageDialog(null, message, "Hochladen erfolgreich", JOptionPane.INFORMATION_MESSAGE); //Es handelt sich um ein Informationsfenster
    }

    public void DatenbankGeleert () { //Kategorie: Hinweis (Datenbank)
        String message = "Alle Datensätze wurden aus der Datenbank gelöscht";
        JOptionPane.showMessageDialog(null, message, "Datenbank geleert", JOptionPane.INFORMATION_MESSAGE); //Es handelt sich um ein Informationsfenster
    }

    public void ObjektLaden (){ //Kategorie: Fehlermeldung (serialisierte ArrayList)
        String message = "Das Objekt konnte nicht geladen werden";
        JOptionPane.showMessageDialog(null, message, "Lade-Fehler", JOptionPane.ERROR_MESSAGE); //Es handelt sich um eine Fehlermeldung
    }

    public void ObjektKlasse (){ //Kategorie: Fehlermeldung (serialisierte ArrayList)
        String message = "Die Klasse des geladenen Objekts konnte nicht gefunden werden";
        JOptionPane.showMessageDialog(null, message, "Lade-Fehler", JOptionPane.ERROR_MESSAGE); //Es handelt sich um eine Fehlermeldung
    }

    public boolean verbindungWirdAufgebaut() { //Kategorie: essentiell (nicht durch Einstellungen deaktivierbar)
        boolean antwort = false;
        String message = "Die Datenbank-Verbindung wird hergestellt\nDieser Vorgang kann mehrere Sekunden dauern\nund das Programm reagiert während dieser Zeit nicht auf Eingaben\n\nMöchten sie die Datenbank-Verbindung herstellen?";
        int result = JOptionPane.showConfirmDialog(null, message, "Verbindungsaufbau", JOptionPane.YES_NO_OPTION);
        switch (result) {
            case JOptionPane.YES_OPTION:
                antwort = true; //Aktion(en) bei Klicken auf den "Ja-Button"
            case JOptionPane.NO_OPTION:
                antwort = false;
        }
        return antwort;
    }

    public void datenbankListeLeer() { //Kategorie: Fehlermeldung (Datenbank)
        Runnable runnable = (Runnable) Toolkit.getDefaultToolkit().getDesktopProperty("win.sound.exclamation");
        runnable.run();
        String message = "Es gibt keine Datensätze, die in die Datenbank hochgeladen werden können";
        JOptionPane.showMessageDialog(null, message, "Upload verhindert", JOptionPane.ERROR_MESSAGE); //Es handelt sich um eine Fehlermeldung
    }

    public void verbindungFehlgeschlagen() { //Kategorie: Fehlermeldung (Datenbank)
        Runnable runnable = (Runnable) Toolkit.getDefaultToolkit().getDesktopProperty("win.sound.exclamation");
        runnable.run();
        String message = "Die Verbindung zur Datenbank konnte nicht hergestellt werden";
        JOptionPane.showMessageDialog(null, message, "Verbindung fehleschlagen", JOptionPane.ERROR_MESSAGE); //Es handelt sich um eine Fehlermeldung
    }
}