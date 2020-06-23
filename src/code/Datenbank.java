package code;

import java.sql.*;
import java.util.ArrayList;

public class Datenbank {

    String kursstufe;
    String nachname;
    String vorname;
    String mac;
    String grund;

    Manager manager = new Manager();
    Dialogfenster dialogfenster = new Dialogfenster();

    String url = "jdbc:mysql://ngr.bplaced.net:3306/ngr_macfilter?useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UCT"; //Die Zeitzone UTC muss hier festgelegt werden, um einen Kommunikationsfehler zu vermeiden
    String username = "ngr";
    String password = "ngrSecret";

    public void datenbankImportieren() throws SQLException { /* Importiert alle Datensätze, die aktuell in der Datenbank vorhanden sind, in die ArrayList */
        System.out.println("Connecting database...");
        Connection con = DriverManager.getConnection(url, username, password); /* Verbindung zur Datenbank wird hergestellt */
        System.out.println("Connected!");

        Statement stmt1 = con.createStatement();
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
    }

    public void datenbankErgaenzen(ArrayList<Datensatz> liste) throws SQLException { /* Importiert die Datensätze, die aktuell in der Liste vorhanden sind, in eine Datenbank-Tabelle*/
        System.out.println("Connecting database...");
        Connection con = DriverManager.getConnection(url, username, password); /* Verbindung zur Datenbank wird hergestellt */
        System.out.println("Connected!");

        int len = liste.size();
        for (int i = 0; i < len; i++) {
            Datensatz aktDatensatz = liste.get(i);
            kursstufe = aktDatensatz.getKursstufe();
            nachname = aktDatensatz.getNachname();
            vorname = aktDatensatz.getVorname();
            mac = aktDatensatz.getMac();
            grund = aktDatensatz.getGrund();

            Statement stmt1 = con.createStatement();
            stmt1.executeUpdate("INSERT INTO Table2 (Kursstufe, Nachname, Vorname, MAC, Grund) VALUES ('" + kursstufe + "','" + nachname + "','"+ vorname + "','" + mac + "','" + grund + "')");
        }
        dialogfenster.DatenbankErgaenzt(len);
    }

    public void datenbankLeeren() throws SQLException {
        System.out.println("Connecting database...");
        Connection con = DriverManager.getConnection(url, username, password); /* Verbindung zur Datenbank wird hergestellt */
        System.out.println("Connected!");

        Statement stmt1 = con.createStatement();
        stmt1.executeUpdate("TRUNCATE Table2");

        dialogfenster.DatenbankGeleert();
    }
}
