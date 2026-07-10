package main;

import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import control.Controller;
import database.DatabaseConnection;

public class Main {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            /*
             * Prova ad aprire una connessione al database.
             */
            try (Connection connection =
                    DatabaseConnection.getConnection()) {

                if (connection == null) {

                    JOptionPane.showMessageDialog(
                            null,
                            "Connessione al database non riuscita.",
                            "Errore database",
                            JOptionPane.ERROR_MESSAGE
                    );

                    return;
                }

                System.out.println(
                        "Connessione al database riuscita."
                );

                /*
                 * Se la connessione funziona,
                 * avvia l'applicazione mostrando il login.
                 */
                Controller controller = new Controller();
                controller.avviaApplicazione();

            } catch (SQLException e) {

                e.printStackTrace();

                JOptionPane.showMessageDialog(
                        null,
                        "Errore durante la connessione al database:\n"
                                + e.getMessage(),
                        "Errore database",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });
    }
}