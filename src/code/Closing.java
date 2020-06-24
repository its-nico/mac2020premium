package code;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;



public class Closing extends JFrame{

    public Closing(){
        addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent ef) {
                int result2 = JOptionPane.showConfirmDialog(null,
                        "Möchten Sie vor dem Beenden speichern?",
                        "Beenden bestätigen",
                        JOptionPane.YES_NO_OPTION);

                switch (result2) {
                    case JOptionPane.YES_OPTION:
                        ef.getWindow().dispose();
                    case JOptionPane.NO_OPTION:


                }


            }
        });
    }

    public static void close(Manager manager){
        int result = JOptionPane.showConfirmDialog(null,
                "Möchten Sie vor dem Beenden speichern?",
                "Beenden bestätigen",
                JOptionPane.YES_NO_OPTION);

        switch (result) {
            case JOptionPane.YES_OPTION:
                manager.speichern();
                System.exit(0); //Aktion(en) bei Klicken auf den "Ja-Button"
            case JOptionPane.NO_OPTION:
                System.exit(0);
                // e.getWindow().dispose();

        }

    }
}
