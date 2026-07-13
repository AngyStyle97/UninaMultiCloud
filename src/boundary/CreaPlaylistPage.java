package boundary;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import control.Controller;

public class CreaPlaylistPage extends JFrame {

    private Controller controller;
    private JTextField txtIdPlaylist;
    private JTextField txtNomePlaylist;
    private JComboBox<String> cmbTipoPlaylist;
    private JButton btnCrea;
    private JButton btnAnnulla;
    private JLabel lblIdPlaylist;
    private JLabel lblNomePlaylist;
    private JLabel lblTipoPlaylist;
    private JPanel pannelloCampi;
    private JPanel pannelloPulsanti;
    private JPanel pannelloCompleto;

    public CreaPlaylistPage(Controller controller) {

        this.controller = controller;
        setTitle("Crea Playlist");
        setSize(520, 400);
        setMinimumSize(new Dimension(420, 330));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);
        setLayout(new GridBagLayout());

        pannelloCampi = new JPanel(new GridLayout(3, 2, 12, 18));

        lblIdPlaylist = new JLabel("ID playlist");
        txtIdPlaylist = new JTextField();
        lblNomePlaylist = new JLabel("Nome playlist");
        txtNomePlaylist = new JTextField();
        lblTipoPlaylist = new JLabel("Tipo playlist");
        cmbTipoPlaylist = new JComboBox<>();
        cmbTipoPlaylist.addItem("Privata");
        cmbTipoPlaylist.addItem("Pubblica");
        cmbTipoPlaylist.addItem("Condivisa");

        pannelloCampi.add(lblIdPlaylist);
        pannelloCampi.add(txtIdPlaylist);
        pannelloCampi.add(lblNomePlaylist);
        pannelloCampi.add(txtNomePlaylist);
        pannelloCampi.add(lblTipoPlaylist);
        pannelloCampi.add(cmbTipoPlaylist);

        btnCrea = new JButton("Crea");
        btnAnnulla = new JButton("Annulla");

        pannelloPulsanti = new JPanel(new GridLayout(1, 2, 15, 0));
        pannelloPulsanti.add(btnCrea);
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

        addComponentListener(new ComponentAdapter() {

            @Override

            public void componentResized(ComponentEvent e) {

                aggiornaDimensioni();

            }
        });

        btnCrea.addActionListener(e -> creaPlaylist());

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
        int altezzaPannello = (int) (altezzaFinestra * 0.55);

        larghezzaPannello = Math.max(330, Math.min(larghezzaPannello, 650));

        altezzaPannello = Math.max(220, Math.min(altezzaPannello, 380));

        pannelloCompleto.setPreferredSize(new Dimension(larghezzaPannello, altezzaPannello));

        int dimensioneFont = Math.min(larghezzaFinestra / 40, altezzaFinestra / 22);

        dimensioneFont = Math.max(13, Math.min(dimensioneFont, 22));

        Font font = new Font("Arial", Font.PLAIN, dimensioneFont);

        lblIdPlaylist.setFont(font);
        lblNomePlaylist.setFont(font);
        lblTipoPlaylist.setFont(font);
        txtIdPlaylist.setFont(font);
        txtNomePlaylist.setFont(font);
        cmbTipoPlaylist.setFont(font);
        
        btnCrea.setFont(font);
        btnAnnulla.setFont(font);

        pannelloCampi.revalidate();
        pannelloCampi.repaint();
        pannelloPulsanti.revalidate();
        pannelloPulsanti.repaint();
        pannelloCompleto.revalidate();
        pannelloCompleto.repaint();

    }

    private void creaPlaylist() {

        String idPlaylist = txtIdPlaylist.getText().trim();
        String nomePlaylist = txtNomePlaylist.getText().trim();
        String tipoPlaylist = (String) cmbTipoPlaylist.getSelectedItem();

        if (idPlaylist.isEmpty()

                || nomePlaylist.isEmpty()

                || tipoPlaylist == null) {

            JOptionPane.showMessageDialog(

                    this, "Compila tutti i campi", "Attenzione", JOptionPane.WARNING_MESSAGE);

            return;

        }

        boolean ok = controller.creaPlaylist(idPlaylist, nomePlaylist, tipoPlaylist);

        if (ok) {

            JOptionPane.showMessageDialog(this, "Playlist creata correttamente");

            dispose();
            controller.mostraMenu();

        } else {

            JOptionPane.showMessageDialog(this, "Impossibile creare la playlist", "Errore", JOptionPane.ERROR_MESSAGE);

        }
    }
}