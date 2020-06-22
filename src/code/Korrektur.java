package code;
import javax.swing.*;
import java.io.*;
import java.util.regex.*;
import java.util.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Korrektur {

    public int zaehler = 1; //Zählervariable wird erstellt, damit die Logs später alle verschieden benannt werden können

    private String textVorIP; //Der ursprünglich zu überprüfende Text wird in der Variable gespeichert, damit später das Log damit erstellt werden kann
    private boolean bool;
    private String textVorO;
    private String textNachO; //Der korrigierte Text wird in der Variable gespeichert, damit später das Log damit erstellt werden kann
    private String textVorLeerzeichen;
    private String textNachLeerzeichen;
    private String textVorBindestriche;
    private String textNachBindestriche;

    /* Klasse führt automatisch alle Korrektur-Methoden auf einmal aus */
    public String autoKorrektur (String text) {
        if (istIP(text)) {
            System.err.println("Es handelt sich hierbei um eine IP-Adresse. Bitte geben Sie eine MAC-Adresse ein!");
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
        textVorO = text;
        text = text.replace("o", "0");
        text = text.replace("O", "0");
        textNachO = text;

        String exportfile = "./Fehlerlog.txt";
        FileWriter fw = null;
        try {
            fw = new FileWriter(exportfile, true); /* FileWriter wird erstellt */
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedWriter bw = new BufferedWriter(fw);

        try {
            if (!textVorO.equals(textNachO)){
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
        textVorLeerzeichen = text;
        text = text.replace(" ", "");
        textNachLeerzeichen = text;

        String exportfile = "./Fehlerlog.txt";
        FileWriter fw = null;
        try {
            fw = new FileWriter(exportfile, true); /* FileWriter wird erstellt */
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedWriter bw = new BufferedWriter(fw);

        try {
            if (!textVorLeerzeichen.equals(textNachLeerzeichen)) {
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
        textVorBindestriche = text;
        text = text.replace("-",":");
        textNachBindestriche = text;

        String exportfile = "./Fehlerlog.txt";
        FileWriter fw = null;
        try {
            fw = new FileWriter(exportfile, true); /* FileWriter wird erstellt */
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedWriter bw = new BufferedWriter(fw);

        try {
            if (!textVorBindestriche.equals(textNachBindestriche)) {
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
        textVorIP = text;
        bool = (text.contains(".") && !text.contains(":"));

        String exportfile = "./Fehlerlog.txt";
        FileWriter fw = null;
        try {
            fw = new FileWriter(exportfile, true); /* FileWriter wird erstellt */
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedWriter bw = new BufferedWriter(fw);

        try {
            if (bool == true){
                bw.write("  Es handelt sich um eine IP-Adresse, nicht um eine MAC-Adresse. Die Adresse wurde daher nicht übernommen." +"\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            bw.close(); /* Wenn der bw nicht geschlossen wird, bleibt die log-Datei leer */
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bool;
    }

    public boolean format(String text){
        String exportfile = "./Fehlerlog.txt";
        FileWriter fw = null;
        try {
            fw = new FileWriter(exportfile, true); /* FileWriter wird erstellt */
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedWriter bw = new BufferedWriter(fw);

        try {
            if (Pattern.matches("."+"."+":"+"."+"."+":"+"."+"."+":"+"."+"."+":"+"."+"."+":"+"."+".", text)){
            }
            else {
                bw.write("  Die Adresse befindet sich nicht im für MAC-Adressen erforderlichen Format (xx:xx:xx:xx:xx:xx). Sie konnte nicht übernommen werden.\n");
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

    public boolean formatOhneLog(String text){
        return (Pattern.matches("."+"."+":"+"."+"."+":"+"."+"."+":"+"."+"."+":"+"."+"."+":"+"."+".", text));
    }

    /* Überprüfung, ob die MAC-Adresse korrekt ist */
    public boolean istKorrekt (String text) {
        String exportfile = "./Fehlerlog.txt";
        FileWriter fw = null;
        try {
            fw = new FileWriter(exportfile, true); /* FileWriter wird erstellt */
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedWriter bw = new BufferedWriter(fw);

        try {
            if ((!text.contains("o") && !text.contains("O") && !text.contains("-") && !text.contains(" ") && !istIPOhneLog(text)) && formatOhneLog(text)){
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

    public void logErstellen(String pMac) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
       // SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String uhrzeit = sdf.format(new Date());
        SimpleDateFormat sdf2 = new SimpleDateFormat("dd.MM.yyyy");
        String tag = sdf2.format(new Date());

        String exportfile = "./Fehlerlog.txt";
        FileWriter fw = null;
        try {
            fw = new FileWriter(exportfile, true); /* FileWriter wird erstellt */
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedWriter bw = new BufferedWriter(fw);
        try {
            bw.write("\n- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -\n\n");
            bw.write("Log erstellt am " + tag + " um " + uhrzeit + ".\n\n");
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

        zaehler = zaehler + 1;
    }

    /* Überprüfung, ob es sich um eine IP-Adresse statt einer MAC-Adresse handelt, ohne dabei ins Log zu schreiben */
    public boolean istIPOhneLog(String text){
        textVorIP = text;
        bool = (text.contains(".") && !text.contains(":")); //Variable bool wird hier noch nicht richtig definiert
        return bool;
    }
}

