package boundary;

import javax.swing.*;
import control.Controller;

public class HomePage extends JFrame {

    private Controller controller;

    public HomePage(Controller controller) {
        this.controller = controller;

        setTitle("Menu - UninaMultiCloud");
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JButton btnProfilo = new JButton("Profilo");
        btnProfilo.setBounds(100, 30, 180, 30);
        add(btnProfilo);

        JButton btnCaricaElemento = new JButton("Carica Elemento");
        btnCaricaElemento.setBounds(100, 70, 180, 30);
        add(btnCaricaElemento);

        JButton btnVisualizzaElemento = new JButton("Visualizza Elemento");
        btnVisualizzaElemento.setBounds(100, 110, 180, 30);
        add(btnVisualizzaElemento);

        JButton btnRicercaPlaylist = new JButton("Ricerca Playlist");
        btnRicercaPlaylist.setBounds(100, 150, 180, 30);
        add(btnRicercaPlaylist);

        JButton btnEsci = new JButton("Esci");
        btnEsci.setBounds(100, 240, 180, 30);
        add(btnEsci);

        btnProfilo.addActionListener(e -> controller.mostraProfilo());

        btnCaricaElemento.addActionListener(e -> controller.mostraCaricaElemento());

        btnVisualizzaElemento.addActionListener(e -> controller.mostraVisualizzaElemento());

        btnRicercaPlaylist.addActionListener(e -> controller.mostraRicercaPlaylist());
        

        btnEsci.addActionListener(e -> {
            controller.logout();
            dispose();
            controller.mostraLogin();
        });
    }
}