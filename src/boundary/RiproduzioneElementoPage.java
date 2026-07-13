package boundary;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import control.Controller;
import entity.ElementoMultimediale;
import java.awt.Toolkit;

public class RiproduzioneElementoPage extends JFrame {

    private Controller controller;
    private ElementoMultimediale elemento;

    private JLabel lblRiproduzione;
    private JTextField txtTitolo;

    private JButton btnAggiungiPlaylist;
    private JButton btnEsci;

    public RiproduzioneElementoPage(Controller controller,ElementoMultimediale elemento) {
    	setIconImage(Toolkit.getDefaultToolkit().getImage(RiproduzioneElementoPage.class.getResource("/images/UNINAFY.png")));

        this.controller = controller;
        this.elemento = elemento;

        setTitle("Riproduzione Elemento");
        setSize(380, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);

        lblRiproduzione = new JLabel("In riproduzione");
        lblRiproduzione.setBounds(40, 35, 120, 25);
        getContentPane().add(lblRiproduzione);

        txtTitolo = new JTextField(elemento.getTitolo());
        txtTitolo.setBounds(165, 35, 160, 25);
        txtTitolo.setEditable(false);
        getContentPane().add(txtTitolo);

        btnAggiungiPlaylist = new JButton("Aggiungi alla Playlist");
        btnAggiungiPlaylist.setBounds(40, 105, 190, 35);
        getContentPane().add(btnAggiungiPlaylist);

        btnEsci = new JButton("Esci");
        btnEsci.setBounds(250, 105, 80, 35);
        getContentPane().add(btnEsci);

        btnAggiungiPlaylist.addActionListener(e -> {

            String idPlaylist = JOptionPane.showInputDialog(this, "Inserisci l'ID della playlist:", "Aggiungi alla playlist",
                    JOptionPane.QUESTION_MESSAGE);

            if (idPlaylist == null) {
                return;
            }

            idPlaylist = idPlaylist.trim();

            if (idPlaylist.isEmpty()) {

                JOptionPane.showMessageDialog(this, "Inserisci un ID playlist valido", "Attenzione", JOptionPane.WARNING_MESSAGE);

                return;
            }

            boolean risultato = controller.aggiungiElementoAPlaylist(elemento.getIdElemento(), idPlaylist);

            if (risultato) {

                JOptionPane.showMessageDialog(this, "Elemento aggiunto alla playlist");

            } else {

                JOptionPane.showMessageDialog(this, "Impossibile aggiungere l'elemento alla playlist", "Errore", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnEsci.addActionListener(e -> dispose());
    }

    public void mostraMessaggio(String messaggio) {
        JOptionPane.showMessageDialog(this, messaggio);
    }
}