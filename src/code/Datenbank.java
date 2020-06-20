package code;

import java.sql.*;

public class Datenbank {

    public static void main(String[] args) {
        Datenbank datenbank = new Datenbank();
        try {
            datenbank.datenbankAusgabe("noahdyj","bp#NoJu09");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void datenbankAusgabe(String username, String password) throws SQLException {
        String url = "jdbc:mysql://bplaced:3306/noahdyj_MAC-Adressen-Projekt";

        Connection con = DriverManager.getConnection(url, username, password);


        /*Connection con = DriverManager.getConnection(
                "jdbc:mysql://bplaced:3306/noahdyj_MAC-Adressen-Projekt",
                username,
                password); */

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
}
