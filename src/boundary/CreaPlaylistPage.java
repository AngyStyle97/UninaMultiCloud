package boundary;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
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
import java.awt.Toolkit;
import java.awt.Color;

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
    	setIconImage(Toolkit.getDefaultToolkit().getImage(CreaPlaylistPage.class.getResource("/images/UNINAFY.png")));

        this.controller = controller;

        setTitle("Crea Playlist");
        setSize(520, 400);
        setMinimumSize(new Dimension(420, 330));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);
        
        PannelloSfondo pannelloSfondo = new PannelloSfondo("/images/sfondo.jpg");
        pannelloSfondo.setLayout(new GridBagLayout());
        setContentPane(pannelloSfondo);

        pannelloCampi = new JPanel(new GridLayout(3, 2, 12, 18));
        pannelloCampi.setOpaque(false);

        lblIdPlaylist = new JLabel("ID playlist");
        lblIdPlaylist.setForeground(Color.WHITE);
        txtIdPlaylist = new JTextField();
        
        lblNomePlaylist = new JLabel("Nome playlist");
        lblNomePlaylist.setForeground(Color.WHITE);
        txtNomePlaylist = new JTextField();
        
        lblTipoPlaylist = new JLabel("Tipo playlist");
        lblTipoPlaylist.setForeground(Color.WHITE);
        
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
        pannelloPulsanti.setOpaque(false);
        
        pannelloPulsanti.add(btnCrea);
        pannelloPulsanti.add(btnAnnulla);
        
        pannelloCompleto = new JPanel(new GridBagLayout());
        pannelloCompleto.setOpaque(false);

        GridBagConstraints gbcCampi = new GridBagConstraints();

        gbcCampi.gridx = 0;
        gbcCampi.gridy = 0;
        gbcCampi.weightx = 1.0;
        gbcCampi.weighty = 1.0;
        gbcCampi.fill = GridBagConstraints.BOTH;

        pannelloCompleto.add(pannelloCampi, gbcCampi);

        GridBagConstraints gbcPulsanti = new GridBagConstraints();

        gbcPulsanti.gridx = 0;
        gbcPulsanti.gridy = 1;
        gbcPulsanti.weightx = 1.0;
        gbcPulsanti.weighty = 0.0;
        gbcPulsanti.fill = GridBagConstraints.HORIZONTAL;
        gbcPulsanti.insets = new Insets(25, 0, 0, 0);

        pannelloCompleto.add(pannelloPulsanti, gbcPulsanti);

        GridBagConstraints gbcCompleto = new GridBagConstraints();
        gbcCompleto.gridx = 0;
        gbcCompleto.gridy = 0;

        getContentPane().add(pannelloCompleto, gbcCompleto);

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

        if (idPlaylist.isEmpty() || nomePlaylist.isEmpty()|| tipoPlaylist == null) {

            JOptionPane.showMessageDialog(this, "Compila tutti i campi", "Attenzione", JOptionPane.WARNING_MESSAGE);

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