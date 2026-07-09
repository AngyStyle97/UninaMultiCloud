package boundary;

import javax.swing.*;

import control.Controller;

import entity.Playlist;

public class CondividiPlaylistPage extends JFrame {

    private Controller controller;

    private Playlist playlist;

    private JLabel lblEmailUtente;

    private JTextField txtEmailUtente;

    private JButton btnConferma;

    private JButton btnAnnulla;

    public CondividiPlaylistPage(Controller controller, Playlist playlist) {

        this.controller = controller;

        this.playlist = playlist;

        setTitle("Condividi Playlist");

        setSize(330, 220);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setLocationRelativeTo(null);

        setLayout(null);

        lblEmailUtente = new JLabel("Email Utente");

        lblEmailUtente.setBounds(35, 45, 100, 25);

        add(lblEmailUtente);

        txtEmailUtente = new JTextField();

        txtEmailUtente.setBounds(150, 45, 130, 25);

        add(txtEmailUtente);

        btnConferma = new JButton("Conferma");

        btnConferma.setBounds(45, 125, 110, 30);

        add(btnConferma);

        btnAnnulla = new JButton("Annulla");

        btnAnnulla.setBounds(180, 125, 110, 30);

        add(btnAnnulla);

        btnConferma.addActionListener(e -> {

            String email = txtEmailUtente.getText();

            boolean ok = controller.condividiPlaylist(

                    playlist.getIdPlaylist(),

                    email

            );

            if (ok) {

                JOptionPane.showMessageDialog(this, "Playlist condivisa");

                dispose();

            } else {

                JOptionPane.showMessageDialog(this, "Errore condivisione playlist");

            }

        });

        btnAnnulla.addActionListener(e -> dispose());

    }

}