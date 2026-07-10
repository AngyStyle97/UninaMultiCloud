package boundary;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import control.Controller;

public class HomePage extends JFrame {

    private Controller controller;

    private JPanel pannelloMenu;

    private JButton btnProfilo;
    private JButton btnCaricaElemento;
    private JButton btnRicercaElemento;
    private JButton btnRicercaPlaylist;
    private JButton btnEsci;
    private JButton btnCreaPlaylist;
    
    public HomePage(Controller controller) {

        this.controller = controller;

        setTitle("Menu - UninaMultiCloud");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(700, 550);

        /*
         * Impedisce di rendere la finestra troppo piccola.
         */
        setMinimumSize(new Dimension(360, 400));

        setLocationRelativeTo(null);
        setResizable(true);

        /*
         * GridBagLayout centra automaticamente
         * il pannello contenente i pulsanti.
         */
        setLayout(new GridBagLayout());

        /*
         * I pulsanti sono disposti in verticale.
         */
        pannelloMenu = new JPanel(
                new GridLayout(5, 1, 0, 12)
        );

        btnProfilo = new JButton("Profilo");
        btnCaricaElemento = new JButton("Carica Elemento");
        btnRicercaElemento = new JButton("Ricerca Elemento");
        btnRicercaPlaylist = new JButton("Ricerca Playlist");
        btnEsci = new JButton("Esci");

        pannelloMenu.add(btnProfilo);
        pannelloMenu.add(btnCaricaElemento);
        pannelloMenu.add(btnRicercaElemento);
        pannelloMenu.add(btnRicercaPlaylist);
        pannelloMenu.add(btnEsci);

        add(pannelloMenu);

        /*
         * Ogni volta che la finestra cambia dimensione,
         * vengono aggiornate le dimensioni del pannello,
         * dei pulsanti e del font.
         */
        addComponentListener(new ComponentAdapter() {

            @Override
            public void componentResized(ComponentEvent e) {
                aggiornaDimensioni();
            }
        });

        btnProfilo.addActionListener(e -> {
            controller.mostraProfilo();
            dispose();
        });

        btnCaricaElemento.addActionListener(e -> {
            controller.mostraCaricaElemento();
            dispose();
        });

        btnRicercaElemento.addActionListener(e -> {
            controller.mostraRicercaElemento();
            dispose();
        });

        btnRicercaPlaylist.addActionListener(e -> {
            controller.mostraRicercaPlaylist();
            dispose();
        });

        btnEsci.addActionListener(e -> {
            controller.logout();
            dispose();
            controller.mostraLogin();
        });

        aggiornaDimensioni();
    }

    private void aggiornaDimensioni() {

        int larghezzaFinestra = getContentPane().getWidth();
        int altezzaFinestra = getContentPane().getHeight();

        /*
         * Larghezza pari circa al 35% della finestra.
         */
        int larghezzaPannello =
                (int) (larghezzaFinestra * 0.35);

        /*
         * Altezza pari circa al 55% della finestra.
         */
        int altezzaPannello =
                (int) (altezzaFinestra * 0.55);

        /*
         * Limiti minimi e massimi.
         */
        larghezzaPannello =
                Math.max(240, Math.min(larghezzaPannello, 500));

        altezzaPannello =
                Math.max(280, Math.min(altezzaPannello, 480));

        pannelloMenu.setPreferredSize(
                new Dimension(
                        larghezzaPannello,
                        altezzaPannello
                )
        );

        /*
         * Il font cresce con la finestra,
         * ma non diventa né troppo piccolo né enorme.
         */
        int dimensioneFont =
                Math.min(
                        larghezzaFinestra / 45,
                        altezzaFinestra / 32
                );

        dimensioneFont =
                Math.max(13, Math.min(dimensioneFont, 22));

        Font fontPulsanti =
                new Font(
                        "Arial",
                        Font.PLAIN,
                        dimensioneFont
                );

        btnProfilo.setFont(fontPulsanti);
        btnCaricaElemento.setFont(fontPulsanti);
        btnRicercaElemento.setFont(fontPulsanti);
        btnRicercaPlaylist.setFont(fontPulsanti);
        btnEsci.setFont(fontPulsanti);

        /*
         * Ricalcola e ridisegna la GUI.
         */
        pannelloMenu.revalidate();
        pannelloMenu.repaint();
    }
}