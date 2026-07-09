package boundary;

import javax.swing.*;

import control.Controller;

public class RicercaPlaylistPage extends JFrame {

    private Controller controller;

    private JLabel lblNome;

    private JLabel lblTipo;

    private JTextField txtNomePlaylist;

    private JComboBox<String> cmbTipoPlaylist;

    private JButton btnCerca;

    private JButton btnAnnulla;

    public RicercaPlaylistPage(Controller controller) {

        this.controller = controller;

        setTitle("Ricerca Playlist");

        setSize(360,230);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setLayout(null);

        lblNome = new JLabel("Nome Playlist");

        lblNome.setBounds(30,30,110,25);

        add(lblNome);

        txtNomePlaylist = new JTextField();

        txtNomePlaylist.setBounds(150,30,150,25);

        add(txtNomePlaylist);

        lblTipo = new JLabel("Tipo Playlist");

        lblTipo.setBounds(30,80,110,25);

        add(lblTipo);

        cmbTipoPlaylist = new JComboBox<>();

        cmbTipoPlaylist.addItem("Privata");

        cmbTipoPlaylist.addItem("Pubblica");

        cmbTipoPlaylist.addItem("Condivisa");

        cmbTipoPlaylist.setBounds(150,80,150,25);

        add(cmbTipoPlaylist);

        btnCerca = new JButton("Cerca");

        btnCerca.setBounds(60,150,100,30);

        add(btnCerca);

        btnAnnulla = new JButton("Annulla");

        btnAnnulla.setBounds(190,150,100,30);

        add(btnAnnulla);

        btnCerca.addActionListener(e ->

            controller.cercaPlaylist(

                    txtNomePlaylist.getText(),

                    (String)cmbTipoPlaylist.getSelectedItem())

        );

        btnAnnulla.addActionListener(e -> dispose());

    }

    public JTextField getTxtNomePlaylist() {

        return txtNomePlaylist;

    }

    public JComboBox<String> getCmbTipoPlaylist() {

        return cmbTipoPlaylist;

    }

}