package boundary;

import javax.swing.*;

import control.Controller;

import entity.Playlist;

public class VisualizzaPlaylistPage extends JFrame {

    private Controller controller;

    private Playlist playlist;

    private JButton btnVisualizzaElemento;

    private JButton btnRimuoviElemento;

    private JButton btnCondividiPlaylist;

    private JButton btnEliminaPlaylist;

    private JButton btnEsci;

    public VisualizzaPlaylistPage(Controller controller, Playlist playlist) {

        this.controller = controller;

        this.playlist = playlist;

        setTitle("Visualizza Playlist");

        setSize(300, 260);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setLocationRelativeTo(null);

        setLayout(null);

        btnVisualizzaElemento = new JButton("Visualizza Elemento");

        btnVisualizzaElemento.setBounds(50, 25, 180, 30);

        add(btnVisualizzaElemento);

        btnRimuoviElemento = new JButton("Rimuovi Elemento");

        btnRimuoviElemento.setBounds(50, 65, 180, 30);

        add(btnRimuoviElemento);

        btnCondividiPlaylist = new JButton("Condividi Playlist");

        btnCondividiPlaylist.setBounds(50, 105, 180, 30);

        add(btnCondividiPlaylist);

        btnEliminaPlaylist = new JButton("Elimina Playlist");

        btnEliminaPlaylist.setBounds(50, 145, 180, 30);

        add(btnEliminaPlaylist);

        btnEsci = new JButton("Esci");

        btnEsci.setBounds(150, 185, 80, 25);

        add(btnEsci);

        btnVisualizzaElemento.addActionListener(e ->

                controller.mostraVisualizzaElementoDaPlaylist(playlist.getIdPlaylist()));

        btnRimuoviElemento.addActionListener(e -> {

            String idElemento = JOptionPane.showInputDialog(this, "ID elemento da rimuovere:");

            if (idElemento != null && !idElemento.isEmpty()) {

                boolean ok = controller.rimuoviElementoDaPlaylist(idElemento, playlist.getIdPlaylist());

                if (ok) {

                    JOptionPane.showMessageDialog(this, "Elemento rimosso dalla playlist");

                } else {

                    JOptionPane.showMessageDialog(this, "Errore rimozione elemento");

                }

            }

        });

        btnCondividiPlaylist.addActionListener(e ->

                controller.mostraCondividiPlaylist(playlist));

        btnEliminaPlaylist.addActionListener(e -> {

            int scelta = JOptionPane.showConfirmDialog(

                    this,

                    "Sei sicuro?",

                    "Messaggio",

                    JOptionPane.YES_NO_OPTION

            );

            if (scelta == JOptionPane.YES_OPTION) {

                boolean ok = controller.eliminaPlaylist(playlist.getIdPlaylist());

                if (ok) {

                    JOptionPane.showMessageDialog(this, "Playlist eliminata");

                    dispose();

                } else {

                    JOptionPane.showMessageDialog(this, "Errore eliminazione playlist");

                }

            }

        });

        btnEsci.addActionListener(e -> dispose());

    }

}