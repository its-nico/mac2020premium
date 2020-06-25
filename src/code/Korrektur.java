package code;
import java.io.*;
import java.util.regex.*;
import java.util.*;
import java.text.SimpleDateFormat;

public class Korrektur {

    private boolean bool;

    // Klasse führt automatisch alle Korrektur-Methoden auf einmal aus. Dabei wrid die korrigierte Adresse zurückgegeben
    public String autoKorrektur (String text) {
        if (istIP(text)) {
            return null;
        }

        if (istKorrekt(text)){
            return text;
        } else {
            return (o(bindestriche(leerzeichen(text))));
        }
    }

    /* Alle Os (groß- und kleingeschrieben) werden mit Nullen ersetzt */
    public String o(String text) {
        String textVorO = text; //Text vor der möglichen Veränderungen wird gespeichert, sodass später ein Vegleich möglich ist
        text = text.replace("o", "0"); //replace() ersetzt Textbausteine durch beliebige Zeichenfolgen (hier kleiner Buchstabe o durch Zahl 0)
        text = text.replace("O", "0"); //replace() ersetzt Textbausteine durch beliebige Zeichenfolgen (hier großer Buchstabe O durch Zahl 0)
        String textNachO = text; //Text nach eventueller Veränderung durch replace() wird gespiechert

        String exportfile = "./Fehlerlog.txt"; //es wird in die Fehlerlog-Datei geschrieben
        FileWriter fw = null;
        try {
            fw = new FileWriter(exportfile, true); /* FileWriter wird erstellt */
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedWriter bw = new BufferedWriter(fw);

        try {
            if (!textVorO.equals(textNachO)){ //wurde der Text verändert, wird dies im Log vermerkt
                bw.write("  Die Buchstaben 'O' wurden durch Nullen ersetzt: " + textNachO + "\n" );
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        try {
            bw.close(); /* Wenn der bw nicht geschlossen wird, bleibt die log-Datei leer */
        } catch (IOException e) {
            e.printStackTrace();
        }

        return text;
    }

    /* Alle Leerzeichen werden gelöscht */
    public String leerzeichen (String text) {
        String textVorLeerzeichen = text; //Text vor der möglichen Veränderungen wird gespeichert, sodass später ein Vegleich möglich ist
        text = text.replace(" ", ""); //replace() ersetzt Textbausteine durch beliebige Zeichenfolgen (hier Leerzeichen gelöscht)
        String textNachLeerzeichen = text; //Text nach eventueller Veränderung durch replace() wird gespiechert

        String exportfile = "./Fehlerlog.txt"; //es wird in die Fehlerlog-Datei geschrieben
        FileWriter fw = null;
        try {
            fw = new FileWriter(exportfile, true); /* FileWriter wird erstellt */
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedWriter bw = new BufferedWriter(fw);

        try {
            if (!textVorLeerzeichen.equals(textNachLeerzeichen)) { //wurde der Text verändert, wird dies im Log vermerkt
                bw.write("  Die Leerzeichen wurden gelöscht: " + textNachLeerzeichen +"\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            bw.close(); /* Wenn der bw nicht geschlossen wird, bleibt die log-Datei leer */
        } catch (IOException e) {
            e.printStackTrace();
        }

        return text;
    }

    /* Alle Bindestriche werden mit Doppelpunkten ersetzt */
    public String bindestriche (String text) {
        String textVorBindestriche = text; //Text vor der möglichen Veränderungen wird gespeichert, sodass später ein Vegleich möglich ist
        text = text.replace("-",":"); //replace() ersetzt Textbausteine durch beliebige Zeichenfolgen (hier Bindestriche durch Doppelpunkte)
        String textNachBindestriche = text; //Text nach eventueller Veränderung durch replace() wird gespiechert

        String exportfile = "./Fehlerlog.txt"; //es wird in die Fehlerlog-Datei geschrieben
        FileWriter fw = null;
        try {
            fw = new FileWriter(exportfile, true); /* FileWriter wird erstellt */
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedWriter bw = new BufferedWriter(fw);

        try {
            if (!textVorBindestriche.equals(textNachBindestriche)) { //wurde der Text verändert, wird dies im Log vermerkt
                bw.write("  Die Bindestriche wurden durch Doppelpunkte ersetzt: " + textNachBindestriche + "\n" );
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            bw.close(); /* Wenn der bw nicht geschlossen wird, bleibt die log-Datei leer */
        } catch (IOException e) {
            e.printStackTrace();
        }

        return text;
    }

    /* Überprüfung, ob es sich um eine IP-Adresse statt einer MAC-Adresse handelt */
    public boolean istIP (String text) {
        bool = (text.contains(".") && !text.contains(":")); //Enthält der Text Punkte, aber keine Doppelpunkte, handelt es sich vermutlich um eine IP

        String exportfile = "./Fehlerlog.txt"; //es wird in die Fehlerlog-Datei geschrieben
        FileWriter fw = null;
        try {
            fw = new FileWriter(exportfile, true); /* FileWriter wird erstellt */
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedWriter bw = new BufferedWriter(fw);

        try {
            if (bool){ //handelt es sich um eine IP (bool == true), darf der Datensatz nicht hinzugefügt werden. Dies muss im Log vermerkt werden.
                bw.write("  Es handelt sich um eine IP-Adresse, nicht um eine MAC-Adresse. Die Adresse wurde daher nicht übernommen." +"\n");
                bw.write("\n");
                bw.write("Status: Hinzufügen fehlgeschlagen\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            bw.close(); /* Wenn der bw nicht geschlossen wird, bleibt die log-Datei leer */
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bool; //Rückgabe, ob es sich um IP handelt (true) oder nicht (false)
    }

    public boolean format(String text){ //Ist die MAC im erforderlichen Format (xx:xx:xx:xx:xx:xx)?
        String exportfile = "./Fehlerlog.txt"; //es wird in die Fehlerlog-Datei geschrieben
        FileWriter fw = null;
        try {
            fw = new FileWriter(exportfile, true); /* FileWriter wird erstellt */
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedWriter bw = new BufferedWriter(fw);

        try {
            if (Pattern.matches("."+"."+":"+"."+"."+":"+"."+"."+":"+"."+"."+":"+"."+"."+":"+"."+".", text)){ //mit pattern.matches wird der text mit einer Vorlage verglichen. "." steht dabei für einen beliebigen Buchstaben
                bw.write("\n");
                bw.write("Status: Erfolgreich hinzugefügt\n");
            }
            else {
                bw.write("  Die Adresse befindet sich nicht im für MAC-Adressen erforderlichen Format (xx:xx:xx:xx:xx:xx). Sie konnte nicht übernommen werden.\n");
                bw.write("\n");
                bw.write("Status: Hinzufügen fehlgeschlagen\n");
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        try {
            bw.close(); /* Wenn der bw nicht geschlossen wird, bleibt die log-Datei leer */
        } catch (IOException e) {
            e.printStackTrace();
        }

    return (Pattern.matches("."+"."+":"+"."+"."+":"+"."+"."+":"+"."+"."+":"+"."+"."+":"+"."+".", text));
    }

    public boolean formatOhneLog(String text){ //Klasse überprüft ebenfalls Format, allerdings wird hierbei nicht in das Log geschrieben
        return (Pattern.matches("."+"."+":"+"."+"."+":"+"."+"."+":"+"."+"."+":"+"."+"."+":"+"."+".", text));
    }

    /* Überprüfung, ob die MAC-Adresse korrekt ist */
    public boolean istKorrekt (String text) {
        String exportfile = "./Fehlerlog.txt"; //es wird in die Fehlerlog-Datei geschrieben
        FileWriter fw = null;
        try {
            fw = new FileWriter(exportfile, true); /* FileWriter wird erstellt */
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedWriter bw = new BufferedWriter(fw);

        try {
            if ((!text.contains("o") && !text.contains("O") && !text.contains("-") && !text.contains(" ") && !istIPOhneLog(text)) && formatOhneLog(text)){ //sind die Abfragen true, ist die MAC korrekt (Methoden, die ins Log schreiben würden, werden hier nicht ausgeführt)
                bw.write("  Die MAC-Adresse ist bereits korrekt und wurde daher nicht verändert.\n");
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        try {
            bw.close(); /* Wenn der bw nicht geschlossen wird, bleibt die log-Datei leer */
        } catch (IOException e) {
            e.printStackTrace();
        }

        return !text.contains("o") && !text.contains("O") && !text.contains("-") && !text.contains(" ") && !istIP(text) &&  formatOhneLog(text);
    }

    //Der "Kopf" des Logs wird erstellt
    public void logErstellen(String pMac) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss"); // Das Obejkt sdf speichert die aktuelle Systemzeit, diese wird später ins Log gesschrieben
        String uhrzeit = sdf.format(new Date()); //sdf wird in String umgewandelt
        SimpleDateFormat sdf2 = new SimpleDateFormat("dd.MM.yyyy"); //Aktuelles Datum wird gespeichert (sdf2)
        String tag = sdf2.format(new Date()); //Datum wird in String umgewandelt, um später dem Log hinzugefügt werden zu können

        String exportfile = "./Fehlerlog.txt"; //es wird in die Fehlerlog-Datei geschrieben
        FileWriter fw = null;
        try {
            fw = new FileWriter(exportfile, true); /* FileWriter wird erstellt */
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedWriter bw = new BufferedWriter(fw);
        try {
            bw.write("\n- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -\n\n");
            bw.write("Eintrag erstellt am " + tag + " um " + uhrzeit + ".\n\n");
            bw.write("Folgende Änderungen wurden durch die Korrektur an der möglicherweise fehlerhaften MAC-Adresse (" + pMac + ") vorgenommen:\n");
            bw.write("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            bw.close(); /* Wenn der bw nicht geschlossen wird, bleibt die log-Datei leer */
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* Überprüfung, ob es sich um eine IP-Adresse statt einer MAC-Adresse handelt, ohne dabei ins Log zu schreiben */
    public boolean istIPOhneLog(String text){
        bool = (text.contains(".") && !text.contains(":")); //Enthält der Text Punkte, aber keine Doppelpunkte, handelt es sich vermutlich um eine IP
        return bool;
    }
}

