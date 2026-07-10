package boundary;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import control.Controller;
import entity.Playlist;

public class RicercaPlaylistPage extends JFrame {

    private Controller controller;

    private JLabel lblNome;
    private JLabel lblTipo;

    private JTextField txtNomePlaylist;
    private JComboBox<String> cmbTipoPlaylist;

    private JButton btnCerca;
    private JButton btnAnnulla;

    public RicercaPlaylistPage(Controller controller) {

        this.controller = controller;

        setTitle("Ricerca Playlist");
        setSize(360, 230);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        lblNome = new JLabel("Nome Playlist");
        lblNome.setBounds(30, 30, 110, 25);
        add(lblNome);

        txtNomePlaylist = new JTextField();
        txtNomePlaylist.setBounds(150, 30, 150, 25);
        add(txtNomePlaylist);

        lblTipo = new JLabel("Tipo Playlist");
        lblTipo.setBounds(30, 80, 110, 25);
        add(lblTipo);

        cmbTipoPlaylist = new JComboBox<>();

        cmbTipoPlaylist.addItem("Privata");
        cmbTipoPlaylist.addItem("Pubblica");
        cmbTipoPlaylist.addItem("Condivisa");

        cmbTipoPlaylist.setBounds(150, 80, 150, 25);
        add(cmbTipoPlaylist);

        btnCerca = new JButton("Cerca");
        btnCerca.setBounds(60, 150, 100, 30);
        add(btnCerca);

        btnAnnulla = new JButton("Annulla");
        btnAnnulla.setBounds(190, 150, 100, 30);
        add(btnAnnulla);

        btnCerca.addActionListener(e -> cercaPlaylist());

        btnAnnulla.addActionListener(e -> dispose());
    }

    private void cercaPlaylist() {

        String nome =
                txtNomePlaylist.getText().trim();

        String tipo =
                (String) cmbTipoPlaylist.getSelectedItem();

        ArrayList<Playlist> risultati =
                controller.cercaPlaylist(nome, tipo);

        if (risultati == null || risultati.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Nessuna playlist trovata",
                    "Ricerca Playlist",
                    JOptionPane.INFORMATION_MESSAGE
            );

            return;
        }

        Playlist playlistSelezionata =
                (Playlist) JOptionPane.showInputDialog(
                        this,
                        "Seleziona la playlist:",
                        "Risultati ricerca",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        risultati.toArray(),
                        risultati.get(0)
                );

        if (playlistSelezionata != null) {

            controller.mostraVisualizzaPlaylist(
                    playlistSelezionata
            );
        }
    }

    public JTextField getTxtNomePlaylist() {
        return txtNomePlaylist;
    }

    public JComboBox<String> getCmbTipoPlaylist() {
        return cmbTipoPlaylist;
    }
}