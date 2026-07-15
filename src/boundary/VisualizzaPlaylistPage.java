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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import control.Controller;
import entity.Playlist;
import java.awt.Toolkit;
import java.awt.Color;

public class VisualizzaPlaylistPage extends JFrame {

    private Controller controller;
    private Playlist playlist;
    private int numeroElementi;
    private JLabel lblTitoloPlaylist;
    private JLabel lblNumeroElementi;
    private JButton btnVisualizzaElemento;
    private JButton btnRimuoviElemento;
    private JButton btnCondividiPlaylist;
    private JButton btnEliminaPlaylist;
    private JButton btnIndietro;
    private JPanel pannelloInformazioni;
    private JPanel pannelloPulsanti;
    private JPanel pannelloCompleto;

    public VisualizzaPlaylistPage(Controller controller, Playlist playlist,int numeroElementi) {
    	setIconImage(Toolkit.getDefaultToolkit().getImage(VisualizzaPlaylistPage.class.getResource("/images/UNINAFY.png")));

        this.controller = controller;
        this.playlist = playlist;
        this.numeroElementi = numeroElementi;

        setTitle("Visualizza Playlist");
        setSize(520, 500);
        setMinimumSize(new Dimension(400, 420));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);
        
        PannelloSfondo pannelloSfondo = new PannelloSfondo("/images/sfondo.jpg");
        pannelloSfondo.setLayout(new GridBagLayout());
        setContentPane(pannelloSfondo);

        pannelloInformazioni = new JPanel(new GridLayout(2, 1, 0, 10));
        pannelloInformazioni.setOpaque(false);

        lblTitoloPlaylist = new JLabel("Playlist: " + playlist.getNomePlaylist(), JLabel.CENTER);
        lblTitoloPlaylist.setForeground(Color.WHITE);
        
        lblNumeroElementi = new JLabel("Numero elementi: " + numeroElementi, JLabel.CENTER);
        lblNumeroElementi.setForeground(Color.WHITE);

        pannelloInformazioni.add(lblTitoloPlaylist);
        pannelloInformazioni.add(lblNumeroElementi);

        pannelloPulsanti = new JPanel(new GridLayout(5, 1, 0, 12));
        pannelloPulsanti.setOpaque(false);

        btnVisualizzaElemento = new JButton("Visualizza Elemento");
        btnRimuoviElemento = new JButton("Rimuovi Elemento");
        btnCondividiPlaylist = new JButton("Condividi Playlist");
        btnEliminaPlaylist = new JButton("Elimina Playlist");
        btnIndietro = new JButton("Indietro");

        pannelloPulsanti.add(btnVisualizzaElemento);
        pannelloPulsanti.add(btnRimuoviElemento);
        pannelloPulsanti.add(btnCondividiPlaylist);
        pannelloPulsanti.add(btnEliminaPlaylist);
        pannelloPulsanti.add(btnIndietro);

        pannelloCompleto = new JPanel(new GridBagLayout());
        pannelloCompleto.setOpaque(false);

        GridBagConstraints gbcInformazioni = new GridBagConstraints();

        gbcInformazioni.gridx = 0;
        gbcInformazioni.gridy = 0;
        gbcInformazioni.weightx = 1.0;
        gbcInformazioni.weighty = 0.0;
        gbcInformazioni.fill = GridBagConstraints.HORIZONTAL;

        pannelloCompleto.add(pannelloInformazioni, gbcInformazioni);

        GridBagConstraints gbcPulsanti = new GridBagConstraints();

        gbcPulsanti.gridx = 0;
        gbcPulsanti.gridy = 1;
        gbcPulsanti.weightx = 1.0;
        gbcPulsanti.weighty = 1.0;
        gbcPulsanti.fill = GridBagConstraints.BOTH;

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

        btnVisualizzaElemento.addActionListener(e -> {

            boolean aperto = controller.mostraVisualizzaElementoDaPlaylist(playlist.getIdPlaylist());

            if (aperto) {

                dispose();

            }

        });
        btnRimuoviElemento.addActionListener(e -> {

            String idElemento = JOptionPane.showInputDialog(this, "Inserisci ID elemento da rimuovere:");

            if (idElemento == null) {

                return;
            }

            idElemento = idElemento.trim();

            if (idElemento.isEmpty()) {

                JOptionPane.showMessageDialog(this, "Inserisci un ID elemento valido", "Attenzione", JOptionPane.WARNING_MESSAGE);

                return;
            }

            boolean ok = controller.rimuoviElementoDaPlaylist(idElemento, playlist.getIdPlaylist());

            if (ok) {

                this.numeroElementi = controller.contaElementiPlaylist(playlist.getIdPlaylist());

                lblNumeroElementi.setText("Numero elementi: " + this.numeroElementi);

                JOptionPane.showMessageDialog(this, "Elemento rimosso dalla playlist");

            } else {

                JOptionPane.showMessageDialog(this, "Errore nella rimozione dell'elemento", "Errore", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnCondividiPlaylist.addActionListener(e -> {

            String email = JOptionPane.showInputDialog(this, "Condividi Playlist", JOptionPane.QUESTION_MESSAGE);

            if (email == null) {

                return;
            }

            email = email.trim();

            if (email.isEmpty()) {

                JOptionPane.showMessageDialog(this, "Inserisci una email valida", "Attenzione", JOptionPane.WARNING_MESSAGE);

                return;
            }

            boolean ok = controller.condividiPlaylist(playlist.getIdPlaylist(),email);

            if (ok) {

                JOptionPane.showMessageDialog(this, "Playlist condivisa correttamente");

            } else {

                JOptionPane.showMessageDialog(this, "Errore nella condivisione", "Errore", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnEliminaPlaylist.addActionListener(e -> {

            int scelta = JOptionPane.showConfirmDialog(this, "Sei sicuro di voler eliminare la playlist?",
                    "Conferma eliminazione", JOptionPane.YES_NO_OPTION);

            if (scelta != JOptionPane.YES_OPTION) {

                return;
            }

            boolean ok = controller.eliminaPlaylist(playlist.getIdPlaylist());

            if (ok) {

                JOptionPane.showMessageDialog(this, "Playlist eliminata correttamente");

                dispose();
                controller.mostraMenu();

            } else {

                JOptionPane.showMessageDialog(this, "Errore nell'eliminazione della playlist",
                        "Errore", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnIndietro.addActionListener(e -> {

            dispose();
            controller.mostraRicercaPlaylist();
        });

        aggiornaDimensioni();
    }

    private void aggiornaDimensioni() {

        int larghezzaFinestra = getContentPane().getWidth();
        int altezzaFinestra = getContentPane().getHeight();
        int larghezzaPannello = (int) (larghezzaFinestra * 0.60);
        int altezzaPannello = (int) (altezzaFinestra * 0.72);

        larghezzaPannello = Math.max(300, Math.min(larghezzaPannello, 620));

        altezzaPannello = Math.max(330,Math.min(altezzaPannello, 560));

        pannelloCompleto.setPreferredSize(new Dimension(larghezzaPannello, altezzaPannello));

        int dimensioneFont = Math.min(larghezzaFinestra / 40,altezzaFinestra / 28);
        dimensioneFont = Math.max(13, Math.min(dimensioneFont, 22));

        Font fontNormale = new Font("Arial", Font.PLAIN, dimensioneFont);

        Font fontTitolo = new Font("Arial", Font.BOLD, dimensioneFont + 1);

        lblTitoloPlaylist.setFont(fontTitolo);
        lblNumeroElementi.setFont(fontNormale);

        btnVisualizzaElemento.setFont(fontNormale);
        btnRimuoviElemento.setFont(fontNormale);
        btnCondividiPlaylist.setFont(fontNormale);
        btnEliminaPlaylist.setFont(fontNormale);
        btnIndietro.setFont(fontNormale);

        pannelloInformazioni.revalidate();
        pannelloInformazioni.repaint();

        pannelloPulsanti.revalidate();
        pannelloPulsanti.repaint();

        pannelloCompleto.revalidate();
        pannelloCompleto.repaint();
    }
}