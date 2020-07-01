package code;

import com.mysql.cj.jdbc.exceptions.CommunicationsException;

import java.sql.*;
import java.util.ArrayList;

public class Datenbank {

    //Datensatz-Attribute
    private String kursstufe;
    private String nachname;
    private String vorname;
    private String mac;
    private String grund;

    private boolean verbunden = true;

    private final Manager manager = new Manager();
    private final Dialogfenster dialogfenster = new Dialogfenster();

    //getConnection-Attribute
    //Die Zeitzone UTC muss hier in der URL festgelegt werden, um einen Kommunikationsfehler zu vermeiden
    private final String url = "jdbc:mysql://ngr.bplaced.net:3306/ngr_macfilter?useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UCT";
    private final String username = "ngr";
    private final String password = "ngrSecret";

    public void datenbankImportieren() { //Importiert alle Datensätze, die aktuell in der Datenbank vorhanden sind, in die ArrayList
        if (dialogfenster.verbindungWirdAufgebaut()) {//Falls Benutzer Verbindung herstellen will
            Connection con = null;
            try {
                con = DriverManager.getConnection(url, username, password); //Verbindung zur Datenbank wird hergestellt
            } catch (CommunicationsException c) {
                dialogfenster.verbindungFehlgeschlagen();
                verbunden = false;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            if (verbunden) {
                Statement stmt1 = null;
                try {
                    stmt1 = con.createStatement();
                    ResultSet rs1 = stmt1.executeQuery("SELECT * FROM Table2"); //SQL-Anweisung wird auf Datenbank ausgführt und Ausabe wird in rs1-Variable gespeichert

                    int anzahl = 0;
                    while (rs1.next()) { //Cursor der Datenbank wechselt mit jedem rs1.next()-Aufruf in die nächste Zeile
                        kursstufe = rs1.getString("Kursstufe");
                        nachname = rs1.getString("Nachname");
                        vorname = rs1.getString("Vorname");
                        mac = rs1.getString("MAC");
                        grund = rs1.getString("Grund");
                        manager.ergaenze(kursstufe, nachname, vorname, mac, grund); //Aus jeder Zeile wird ein Datensatz-Objekt erstellt und in die Liste eingefügt
                        anzahl++;
                    }
                    dialogfenster.DatenbankImportiert(anzahl); //Dialogfenster zeigt, wie viele Datensätze erfolgreich in das Programm importiert wurden
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }

    public void datenbankErgaenzen(ArrayList<Datensatz> liste) { //Importiert die Datensätze, die aktuell in der Liste vorhanden sind, in eine Datenbank-Tabelle
        if (liste.isEmpty()) {
            dialogfenster.datenbankListeLeer(); //Falls Liste leer ist, wird Benutzer informiert
        } else {
            if (dialogfenster.verbindungWirdAufgebaut()) { //Falls Benutzer Verbindung herstellen will
                Connection con = null; //Verbindung zur Datenbank wird hergestellt
                try {
                    con = DriverManager.getConnection(url, username, password); //Verbindung zur Datenbank wird hergestellt
                } catch (CommunicationsException c) {
                    dialogfenster.verbindungFehlgeschlagen();
                    verbunden = false;
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

                int len = liste.size();
                boolean hochgeladen = false;
                for (int i = 0; i < len; i++) { //Geht alle Listen-Elemente durch und lädt sie in die Datenbank, falls Verbindung existiert
                    Datensatz aktDatensatz = liste.get(i);
                    kursstufe = aktDatensatz.getKursstufe();
                    nachname = aktDatensatz.getNachname();
                    vorname = aktDatensatz.getVorname();
                    mac = aktDatensatz.getMac();
                    grund = aktDatensatz.getGrund();

                    if (verbunden) { //Falls Verbindung zur Datenbank existiert
                        Statement stmt1 = null;
                        try {
                            stmt1 = con.createStatement();
                            stmt1.executeUpdate("INSERT INTO Table2 (Kursstufe, Nachname, Vorname, MAC, Grund) VALUES ('" + kursstufe + "','" + nachname + "','" + vorname + "','" + mac + "','" + grund + "')");
                            hochgeladen = true; //Wenn mind. 1 Element aus Liste hochgeladen wird, wird Variable auf true gesetzt
                        } catch (NullPointerException e) {
                            e.printStackTrace();
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                    }
                }
                if (hochgeladen) {
                    dialogfenster.DatenbankErgaenzt(len); //Falls (mind. 1) Datensatz hochgeladen wurde, wird Dialogfenster geöffnet, das über erfolgreiches Hochladen von Datensätzen informiert
                }
            }
        }
    }

    public void datenbankLeeren() {
        if (dialogfenster.verbindungWirdAufgebaut()) { //Fall Benutzer Verbindung herstellen will
            Connection con = null; //Verbindung zur Datenbank wird hergestellt
            try {
                con = DriverManager.getConnection(url, username, password); //Verbindung zur Datenbank wird hergestellt
            } catch (CommunicationsException c) {
                dialogfenster.verbindungFehlgeschlagen();
                verbunden = false;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            if (verbunden) { //Falls Verbindung zur Datenbank existiert
                Statement stmt1 = null;
                try {
                    stmt1 = con.createStatement();
                    stmt1.executeUpdate("TRUNCATE Table2"); //Löscht den kompletten Inhalt aus Datenbank
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                dialogfenster.DatenbankGeleert(); //Informiert über erfolgreich geleerte Datenbank
            }
        }
    }
}