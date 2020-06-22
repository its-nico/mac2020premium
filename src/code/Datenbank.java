package code;

import javax.xml.transform.Result;
import java.io.IOException;
import java.sql.*;

public class Datenbank {

    String kursstufe;
    String nachname;
    String vorname;
    String mac;
    String grund;

    Manager manager = new Manager();

    public static void main(String[] args) {
        Datenbank datenbank = new Datenbank();
        try {
            datenbank.datenbankAusgabe("ngr","ngrSecret");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void datenbankAusgabe(String username, String password) throws SQLException {
        String url = "jdbc:mysql://bplaced:3306/ngr_macfilter";

        Connection con = DriverManager.getConnection(url, username, password);

        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT a, b, c FROM Table1");

        while (rs.next()) {
            int a = rs.getInt("a");
            int b = rs.getInt("b");
            int c = rs.getInt("c");
            System.out.print(a);
            System.out.print(b);
            System.out.print(c);
        }
    }

    public void datenbankImportieren() throws SQLException {
        String url = "jdbc:mysql://bplaced:3306/ngr_macfilter";
        String username = "ngr";
        String password = "ngrSecret";

        Connection con = DriverManager.getConnection(url, username, password);

        Statement stmt1 = con.createStatement();
        ResultSet rs1 = stmt1.executeQuery("SELECT * FROM table2");

        while (rs1.next()) { /* Cursor der Datenbank wechselt mit jedem rs1.next()-Aufruf in die nächste Zeile */
            kursstufe = rs1.getString("Kursstufe");
            nachname = rs1.getString("Nachname");
            vorname = rs1.getString("Vorname");
            mac = rs1.getString("MAC");
            grund = rs1.getString("Grund");
            manager.ergaenze(kursstufe, nachname, vorname, mac, grund); /* Aus jeder Zeile wird ein Datensatz-Objekt erstellt und in die Liste eingefügt */
        }

    }

    public void datenbankErgaenzen(List1<Datensatz> liste) throws SQLException {
        String url = "jdbc:mysql://bplaced:3306/ngr_macfilter";
        String username = "ngr";
        String password = "ngrSecret";

        //Connection con = DriverManager.getConnection(url, username, password);

        liste.toFirst();
        while (liste.hasAccess()) {
            kursstufe = (liste.getContent()).getKursstufe();
            nachname = (liste.getContent()).getNachname();
            vorname = (liste.getContent()).getVorname();
            mac = (liste.getContent()).getMac();
            grund = (liste.getContent()).getGrund();

            //Statement stmt1 = con.createStatement();
            //stmt1.executeQuery("INSERT INTO Table2 (Kursstufe, Nachname, Vorname, MAC, Grund) VALUES ('" + kursstufe + "','" + nachname + "','"+ vorname + "','" + mac + "','" + grund + "')");
            String stmt1 = "INSERT INTO Table2 (Kursstufe, Nachname, Vorname, MAC, Grund) VALUES ('" + kursstufe + "','" + nachname + "','"+ vorname + "','" + mac + "','" + grund + "')";
            System.out.println(stmt1);

            liste.next();
        }
    }
}
