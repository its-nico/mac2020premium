package code;

import com.mysql.cj.jdbc.exceptions.CommunicationsException;

import java.sql.*;
import java.util.ArrayList;

public class Datenbank {

    private String kursstufe;
    private String nachname;
    private String vorname;
    private String mac;
    private String grund;

    private final Manager manager = new Manager();
    private final Dialogfenster dialogfenster = new Dialogfenster();

    //Die Zeitzone UTC muss hier in der URL festgelegt werden, um einen Kommunikationsfehler zu vermeiden
    private final String url = "jdbc:mysql://ngr.bplaced.net:3306/ngr_macfilter?useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UCT";
    private final String username = "ngr";
    private final String password = "ngrSecret";

    public void datenbankImportieren() { /* Importiert alle Datensätze, die aktuell in der Datenbank vorhanden sind, in die ArrayList */
        dialogfenster.verbindungWirdAufgebaut();
        System.out.println("Connecting database...");
        Connection con = null; /* Verbindung zur Datenbank wird hergestellt */
        try {
            con = DriverManager.getConnection(url, username, password);
        } catch (CommunicationsException c) {
            dialogfenster.verbindungFehlgeschlagen();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println("Connected!");

        Statement stmt1 = null;
        try {
            stmt1 = con.createStatement();
            ResultSet rs1 = stmt1.executeQuery("SELECT * FROM table2");

            int anzahl = 0;
            while (rs1.next()) { /* Cursor der Datenbank wechselt mit jedem rs1.next()-Aufruf in die nächste Zeile */
                kursstufe = rs1.getString("Kursstufe");
                nachname = rs1.getString("Nachname");
                vorname = rs1.getString("Vorname");
                mac = rs1.getString("MAC");
                grund = rs1.getString("Grund");
                manager.ergaenze(kursstufe, nachname, vorname, mac, grund); /* Aus jeder Zeile wird ein Datensatz-Objekt erstellt und in die Liste eingefügt */
                anzahl++;
            }
            dialogfenster.DatenbankImportiert(anzahl);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void datenbankErgaenzen(ArrayList<Datensatz> liste) { /* Importiert die Datensätze, die aktuell in der Liste vorhanden sind, in eine Datenbank-Tabelle*/
        if (liste.isEmpty()) {
            dialogfenster.datenbankListeLeer();
        } else {
            dialogfenster.verbindungWirdAufgebaut();
            System.out.println("Connecting database...");
            Connection con = null; /* Verbindung zur Datenbank wird hergestellt */
            try {
                con = DriverManager.getConnection(url, username, password);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            System.out.println("Connected!");

            int len = liste.size();
            for (int i = 0; i < len; i++) {
                Datensatz aktDatensatz = liste.get(i);
                kursstufe = aktDatensatz.getKursstufe();
                nachname = aktDatensatz.getNachname();
                vorname = aktDatensatz.getVorname();
                mac = aktDatensatz.getMac();
                grund = aktDatensatz.getGrund();

                Statement stmt1 = null;
                try {
                    stmt1 = con.createStatement();
                    stmt1.executeUpdate("INSERT INTO Table2 (Kursstufe, Nachname, Vorname, MAC, Grund) VALUES ('" + kursstufe + "','" + nachname + "','" + vorname + "','" + mac + "','" + grund + "')");
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (NullPointerException e) {
                    e.printStackTrace();
                    System.out.println("NullPointerException");
                }
            }
            dialogfenster.DatenbankErgaenzt(len);
        }
    }

    public void datenbankLeeren() {
        dialogfenster.verbindungWirdAufgebaut();
        System.out.println("Connecting database...");
        Connection con = null; /* Verbindung zur Datenbank wird hergestellt */
        try {
            con = DriverManager.getConnection(url, username, password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println("Connected!");

        Statement stmt1 = null;
        try {
            stmt1 = con.createStatement();
            stmt1.executeUpdate("TRUNCATE Table2");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        dialogfenster.DatenbankGeleert();
    }
}
