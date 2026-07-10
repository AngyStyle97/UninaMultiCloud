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
//aggiunto cose
    private Controller controller;

    private JPanel pannelloMenu;

    private JButton btnProfilo;

    private JButton btnCaricaElemento;

    private JButton btnCreaPlaylist;

    private JButton btnRicercaElemento;

    private JButton btnRicercaPlaylist;

    private JButton btnEsci;

    public HomePage(Controller controller) {

        this.controller = controller;

        setTitle("Menu - UninaMultiCloud");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /*

         * Dimensione iniziale della finestra.

         */

        setSize(700, 600);

        /*

         * Impedisce di rimpicciolire troppo la finestra,

         * evitando che i pulsanti spariscano.

         */

        setMinimumSize(new Dimension(360, 450));

        setLocationRelativeTo(null);

        setResizable(true);

        /*

         * GridBagLayout mantiene il pannello centrale

         * indipendentemente dalla dimensione della finestra.

         */

        setLayout(new GridBagLayout());

        /*

         * Sei righe, una per ogni pulsante.

         * Il valore 12 rappresenta lo spazio verticale

         * tra un pulsante e l'altro.

         */

        pannelloMenu = new JPanel(

                new GridLayout(6, 1, 0, 12)

        );

        btnProfilo = new JButton("Profilo");

        btnCaricaElemento = new JButton("Carica Elemento");

        btnCreaPlaylist = new JButton("Crea Playlist");

        btnRicercaElemento = new JButton("Ricerca Elemento");

        btnRicercaPlaylist = new JButton("Ricerca Playlist");

        btnEsci = new JButton("Esci");

        pannelloMenu.add(btnProfilo);

        pannelloMenu.add(btnCaricaElemento);

        pannelloMenu.add(btnCreaPlaylist);

        pannelloMenu.add(btnRicercaElemento);

        pannelloMenu.add(btnRicercaPlaylist);

        pannelloMenu.add(btnEsci);

        /*

         * Il pannello viene aggiunto al centro della finestra.

         */

        add(pannelloMenu);

        /*

         * Ogni volta che la finestra viene ingrandita

         * o rimpicciolita, aggiorna le dimensioni del pannello

         * e il font dei pulsanti.

         */

        addComponentListener(new ComponentAdapter() {

            @Override

            public void componentResized(ComponentEvent e) {

                aggiornaDimensioni();

            }

        });

        /*

         * Apertura della schermata Profilo.

         */

        btnProfilo.addActionListener(e -> {

            controller.mostraProfilo();

            dispose();

        });

        /*

         * Apertura della schermata per caricare

         * un nuovo elemento multimediale.

         */

        btnCaricaElemento.addActionListener(e -> {

            controller.mostraCaricaElemento();

            dispose();

        });

        /*

         * Apertura della schermata per creare

         * una nuova playlist.

         */

        btnCreaPlaylist.addActionListener(e -> {

            controller.mostraCreaPlaylist();

            dispose();

        });

        /*

         * Apertura della ricerca degli elementi.

         */

        btnRicercaElemento.addActionListener(e -> {

            controller.mostraRicercaElemento();

            dispose();

        });

        /*

         * Apertura della ricerca delle playlist.

         */

        btnRicercaPlaylist.addActionListener(e -> {

            controller.mostraRicercaPlaylist();

            dispose();

        });

        /*

         * Logout e ritorno alla schermata di login.

         */

        btnEsci.addActionListener(e -> {

            controller.logout();

            dispose();

            controller.mostraLogin();

        });

        aggiornaDimensioni();

    }

    private void aggiornaDimensioni() {

        int larghezzaFinestra =

                getContentPane().getWidth();

        int altezzaFinestra =

                getContentPane().getHeight();

        /*

         * Il pannello occupa circa il 35% della larghezza

         * della finestra.

         */

        int larghezzaPannello =

                (int) (larghezzaFinestra * 0.35);

        /*

         * Il pannello occupa circa il 65% dell'altezza,

         * perché ora contiene sei pulsanti.

         */

        int altezzaPannello =

                (int) (altezzaFinestra * 0.65);

        /*

         * Impedisce al pannello di diventare

         * troppo piccolo o troppo grande.

         */

        larghezzaPannello =

                Math.max(

                        240,

                        Math.min(larghezzaPannello, 500)

                );

        altezzaPannello =

                Math.max(

                        330,

                        Math.min(altezzaPannello, 540)

                );

        pannelloMenu.setPreferredSize(

                new Dimension(

                        larghezzaPannello,

                        altezzaPannello

                )

        );

        /*

         * Il font cresce e diminuisce insieme alla finestra,

         * ma resta compreso tra 13 e 22.

         */

        int dimensioneFont =

                Math.min(

                        larghezzaFinestra / 45,

                        altezzaFinestra / 34

                );

        dimensioneFont =

                Math.max(

                        13,

                        Math.min(dimensioneFont, 22)

                );

        Font fontPulsanti =

                new Font(

                        "Arial",

                        Font.PLAIN,

                        dimensioneFont

                );

        btnProfilo.setFont(fontPulsanti);

        btnCaricaElemento.setFont(fontPulsanti);

        btnCreaPlaylist.setFont(fontPulsanti);

        btnRicercaElemento.setFont(fontPulsanti);

        btnRicercaPlaylist.setFont(fontPulsanti);

        btnEsci.setFont(fontPulsanti);

        /*

         * Aggiorna graficamente il pannello.

         */

        pannelloMenu.revalidate();

        pannelloMenu.repaint();

    }

}