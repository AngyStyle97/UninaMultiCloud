package boundary;

import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import control.Controller;
import entity.ElementoMultimediale;

public class RicercaElementoPage extends JFrame {

    private Controller controller;

    private JLabel lblTitolo;
    private JTextField txtTitolo;

    private JButton btnCerca;
    private JButton btnVisualizza;
    private JButton btnAnnulla;

    private DefaultListModel<ElementoMultimediale> modelloLista;
    private JList<ElementoMultimediale> listaRisultati;

    public RicercaElementoPage(Controller controller) {

        this.controller = controller;

        setTitle("Ricerca Elemento");
        setSize(450, 360);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        /*
         * Campo in cui l'utente inserisce il titolo
         * o una parte del titolo dell'elemento.
         */
        lblTitolo = new JLabel("Titolo elemento");
        lblTitolo.setBounds(35, 30, 120, 25);
        add(lblTitolo);

        txtTitolo = new JTextField();
        txtTitolo.setBounds(160, 30, 225, 25);
        add(txtTitolo);

        /*
         * Pulsante per avviare la ricerca.
         */
        btnCerca = new JButton("Cerca");
        btnCerca.setBounds(75, 75, 120, 30);
        add(btnCerca);

        /*
         * Pulsante per chiudere la schermata.
         */
        btnAnnulla = new JButton("Annulla");
        btnAnnulla.setBounds(235, 75, 120, 30);
        add(btnAnnulla);

        /*
         * Modello e lista che conterranno gli elementi
         * restituiti dalla ricerca.
         */
        modelloLista = new DefaultListModel<>();

        listaRisultati = new JList<>(modelloLista);

        JScrollPane scrollRisultati = new JScrollPane(listaRisultati);
        scrollRisultati.setBounds(35, 125, 350, 120);
        add(scrollRisultati);

        /*
         * Apre l'elemento selezionato.
         */
        btnVisualizza = new JButton("Visualizza");
        btnVisualizza.setBounds(145, 270, 140, 30);
        add(btnVisualizza);

        /*
         * Quando l'utente clicca Cerca, viene eseguito
         * il metodo privato cercaElementi().
         */
        btnCerca.addActionListener(e -> cercaElementi());

        /*
         * Quando l'utente clicca Visualizza, viene recuperato
         * l'elemento selezionato e passato al Controller.
         */
        btnVisualizza.addActionListener(e -> {

            ElementoMultimediale elementoSelezionato =
                    listaRisultati.getSelectedValue();

            if (elementoSelezionato == null) {

                JOptionPane.showMessageDialog(
                        this,
                        "Seleziona un elemento da visualizzare",
                        "Attenzione",
                        JOptionPane.WARNING_MESSAGE
                );

                return;
            }

            controller.mostraRiproduzioneElemento(elementoSelezionato);
        });

        btnAnnulla.addActionListener(e -> dispose());
    }

    /*
     * Recupera il titolo inserito e chiede al Controller
     * di cercare gli elementi nel database.
     */
    private void cercaElementi() {

        String titolo = txtTitolo.getText().trim();

        if (titolo.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Inserisci il titolo dell'elemento",
                    "Attenzione",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        ArrayList<ElementoMultimediale> risultati =
                controller.cercaElemento(titolo);

        mostraRisultati(risultati);
    }

    /*
     * Inserisce nella JList gli elementi trovati.
     */
    public void mostraRisultati(
            ArrayList<ElementoMultimediale> risultati) {

        modelloLista.clear();

        if (risultati == null || risultati.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Nessun elemento trovato",
                    "Ricerca elemento",
                    JOptionPane.INFORMATION_MESSAGE
            );

            return;
        }

        for (ElementoMultimediale elemento : risultati) {
            modelloLista.addElement(elemento);
        }
    }

    public void mostraMessaggio(String messaggio) {
        JOptionPane.showMessageDialog(this, messaggio);
    }
}