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
import java.awt.Toolkit;

public class HomePage extends JFrame {

    private Controller controller;
    private JPanel pannelloMenu;
    private JButton btnProfilo;
    private JButton btnCaricaElemento;
    private JButton btnCreaPlaylist;
    private JButton btnRicercaElemento;
    private JButton btnRicercaPlaylist;
    private JButton btnEsci;

    public HomePage(Controller controller) {
    	setIconImage(Toolkit.getDefaultToolkit().getImage(HomePage.class.getResource("/images/UNINAFY.png")));

        this.controller = controller;
        setTitle("Menu - UninaMultiCloud");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(700, 600);

        setMinimumSize(new Dimension(360, 450));
        setLocationRelativeTo(null);
        setResizable(true);

        PannelloSfondo pannelloSfondo = new PannelloSfondo("/images/sfondo.jpg");
        
        pannelloSfondo.setLayout(new GridBagLayout());       
        setContentPane(pannelloSfondo);       
        pannelloMenu = new JPanel(new GridLayout(6, 1, 0, 12));
        pannelloMenu.setOpaque(false);
        
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

        getContentPane().add(pannelloMenu);

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

        btnCreaPlaylist.addActionListener(e -> {

            controller.mostraCreaPlaylist();

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

        int larghezzaPannello = (int) (larghezzaFinestra * 0.35);

        int altezzaPannello = (int) (altezzaFinestra * 0.65);

        larghezzaPannello = Math.max(240, Math.min(larghezzaPannello, 500));
        altezzaPannello = Math.max(330, Math.min(altezzaPannello, 540));

        pannelloMenu.setPreferredSize(new Dimension(larghezzaPannello, altezzaPannello));

        int dimensioneFont = Math.min(larghezzaFinestra / 45, altezzaFinestra / 34);

        dimensioneFont = Math.max(13, Math.min(dimensioneFont, 22));

        Font fontPulsanti = new Font("Arial", Font.PLAIN, dimensioneFont);

        btnProfilo.setFont(fontPulsanti);
        btnCaricaElemento.setFont(fontPulsanti);
        btnCreaPlaylist.setFont(fontPulsanti);
        btnRicercaElemento.setFont(fontPulsanti);
        btnRicercaPlaylist.setFont(fontPulsanti);
        btnEsci.setFont(fontPulsanti);

        pannelloMenu.revalidate();
        pannelloMenu.repaint();

    }

}