package boundary;

import javax.swing.*; 
import java.util.ArrayList;
import entity.ElementoMultimediale;
import entity.Playlist;
import control.Controller;

public class CaricaElementoPage extends JFrame {

    private Controller controller;
    private JLabel lblElemento;
    private JLabel lblPlaylist;
    private JComboBox<String> cmbElemento;
    private JComboBox<String> cmbPlaylist;
    private JButton btnCarica;
    private JButton btnAnnulla;

    public CaricaElementoPage(Controller controller) {

        this.controller = controller;

        setTitle("Carica Elemento");

        setSize(360,220);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setLayout(null);

        lblElemento = new JLabel("Inserisci Elemento");

        lblElemento.setBounds(20,30,130,25);

        add(lblElemento);

        cmbElemento = new JComboBox<>();

        cmbElemento.setBounds(160,30,150,25);

        add(cmbElemento);

        lblPlaylist = new JLabel("Inserisci Playlist");

        lblPlaylist.setBounds(20,80,130,25);

        add(lblPlaylist);

        cmbPlaylist = new JComboBox<>();

        cmbPlaylist.setBounds(160,80,150,25);

        add(cmbPlaylist);

        btnCarica = new JButton("Carica");

        btnCarica.setBounds(60,140,100,30);

        add(btnCarica);

        btnAnnulla = new JButton("Annulla");

        btnAnnulla.setBounds(190,140,100,30);

        add(btnAnnulla);

        btnCarica.addActionListener(e -> {

            String idElemento =

                    (String) cmbElemento.getSelectedItem();

            String idPlaylist =

                    (String) cmbPlaylist.getSelectedItem();

            boolean ok = controller.caricaElemento(

                    idElemento,

                    idPlaylist

            );

            if (ok) {

                JOptionPane.showMessageDialog(

                        this,

                        "Elemento caricato nella playlist"

                );

            } else {

                JOptionPane.showMessageDialog(

                        this,

                        "Errore durante il caricamento"

                );

            }

        });
    }

    public JComboBox<String> getCmbElemento() {

        return cmbElemento;

    }

    public JComboBox<String> getCmbPlaylist() {

        return cmbPlaylist;

    }
    
    public void mostraElementi(

            ArrayList<ElementoMultimediale> elementi) {

        cmbElemento.removeAllItems();

        if (elementi == null) {

            return;

        }

        for (ElementoMultimediale elemento : elementi) {

            cmbElemento.addItem(

                    elemento.getIdElemento()

            );

        }

    }

    public void mostraPlaylist(

            ArrayList<Playlist> playlist) {

        cmbPlaylist.removeAllItems();

        if (playlist == null) {

            return;

        }

        for (Playlist p : playlist) {

            cmbPlaylist.addItem(

                    p.getIdPlaylist()

            );

        }

    }

}