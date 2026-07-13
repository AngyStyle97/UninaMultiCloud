package boundary;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
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
    private JPanel pannelloCampi;
    private JPanel pannelloPulsanti;
    private JPanel pannelloCompleto;

    public RicercaPlaylistPage(Controller controller) {

        this.controller = controller;

        setTitle("Ricerca Playlist");
        setSize(520, 400);
        setMinimumSize(new Dimension(420, 330));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(true);
        setLayout(new GridBagLayout());

        pannelloCampi = new JPanel(new GridLayout(2, 2, 12, 18));

        lblNome = new JLabel("Nome Playlist");
        txtNomePlaylist = new JTextField();

        lblTipo = new JLabel("Tipo Playlist");

        cmbTipoPlaylist = new JComboBox<>();
        cmbTipoPlaylist.addItem("Privata");
        cmbTipoPlaylist.addItem("Pubblica");
        cmbTipoPlaylist.addItem("Condivisa");

        pannelloCampi.add(lblNome);
        pannelloCampi.add(txtNomePlaylist);
        pannelloCampi.add(lblTipo);
        pannelloCampi.add(cmbTipoPlaylist);

        btnCerca = new JButton("Cerca");
        btnAnnulla = new JButton("Annulla");

        pannelloPulsanti = new JPanel(new GridLayout(1, 2, 15, 0));

        pannelloPulsanti.add(btnCerca);
        pannelloPulsanti.add(btnAnnulla);

        pannelloCompleto = new JPanel(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;

        pannelloCompleto.add(pannelloCampi, gbc);

        gbc.gridy = 1;
        gbc.weighty = 0.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new java.awt.Insets(25, 0, 0, 0);

        pannelloCompleto.add(pannelloPulsanti, gbc);

        add(pannelloCompleto, new GridBagConstraints());

        addComponentListener(
                new ComponentAdapter() {

                    @Override
                    public void componentResized(
                            ComponentEvent e) {

                        aggiornaDimensioni();
                    }
                }
        );

        btnCerca.addActionListener(
                e -> cercaPlaylist()
        );

        btnAnnulla.addActionListener(e -> {

            dispose();
            controller.mostraMenu();
        });

        aggiornaDimensioni();
    }

    private void aggiornaDimensioni() {

        int larghezzaFinestra = getContentPane().getWidth();
        int altezzaFinestra = getContentPane().getHeight();

        int larghezzaPannello = (int) (larghezzaFinestra * 0.65);
        int altezzaPannello = (int) (altezzaFinestra * 0.48);

        larghezzaPannello = Math.max(330, Math.min(larghezzaPannello, 650));
        altezzaPannello = Math.max(190, Math.min(altezzaPannello, 330));

        pannelloCompleto.setPreferredSize(new Dimension(larghezzaPannello, altezzaPannello));
        int dimensioneFont = Math.min(larghezzaFinestra / 40, altezzaFinestra / 22);
        dimensioneFont = Math.max(13, Math.min(dimensioneFont, 22));

        Font font = new Font("Arial", Font.PLAIN, dimensioneFont);

        lblNome.setFont(font);
        lblTipo.setFont(font);

        txtNomePlaylist.setFont(font);
        cmbTipoPlaylist.setFont(font);

        btnCerca.setFont(font);
        btnAnnulla.setFont(font);

        pannelloCampi.revalidate();
        pannelloCampi.repaint();
        pannelloPulsanti.revalidate();
        pannelloPulsanti.repaint();
        pannelloCompleto.revalidate();
        pannelloCompleto.repaint();
    }

    private void cercaPlaylist() {

        String nome = txtNomePlaylist.getText().trim();
        String tipo = (String) cmbTipoPlaylist.getSelectedItem();

        ArrayList<Playlist> risultati = controller.cercaPlaylist(nome, tipo);

        if (risultati == null || risultati.isEmpty()) {

            JOptionPane.showMessageDialog(this, "Nessuna playlist trovata", "Ricerca Playlist", JOptionPane.INFORMATION_MESSAGE);

            return;
        }

        String[] opzioni = new String[risultati.size()];

        for (int i = 0; i < risultati.size(); i++) {

            Playlist playlist = risultati.get(i);

            int numeroElementi = controller.contaElementiPlaylist(playlist.getIdPlaylist());

            opzioni[i] = "Playlist " + playlist.getTipoPlaylist() + " - ID: " + playlist.getIdPlaylist() + " - Nome: "
                            + playlist.getNomePlaylist() + " - Numero elementi: " + numeroElementi;}

        String opzioneSelezionata = (String) JOptionPane.showInputDialog(this, "Seleziona la playlist:",
                                "Risultati ricerca", JOptionPane.QUESTION_MESSAGE, null, opzioni, opzioni[0]);

        if (opzioneSelezionata == null) {
            return;
        }

        int indiceSelezionato = -1;

        for (int i = 0; i < opzioni.length; i++) {

            if (opzioni[i].equals(opzioneSelezionata)) {

                indiceSelezionato = i;
                break;
            }
        }

        if (indiceSelezionato == -1) {
            return;
        }

        Playlist playlistSelezionata = risultati.get(indiceSelezionato);

        controller.mostraVisualizzaPlaylist(playlistSelezionata);

        dispose();
    }

    public JTextField getTxtNomePlaylist() {

        return txtNomePlaylist;
    }

    public JComboBox<String> getCmbTipoPlaylist() {

        return cmbTipoPlaylist;
    }
}