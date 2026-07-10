package boundary;

import javax.swing.JButton;

import javax.swing.JComboBox;

import javax.swing.JFrame;

import javax.swing.JLabel;

import javax.swing.JOptionPane;

import javax.swing.JTextField;

import control.Controller;

public class CreaPlaylistPage extends JFrame {

    private Controller controller;

    private JTextField txtIdPlaylist;

    private JTextField txtNomePlaylist;

    private JComboBox<String> cmbTipoPlaylist;

    private JButton btnCrea;

    private JButton btnAnnulla;

    public CreaPlaylistPage(Controller controller) {

        this.controller = controller;

        setTitle("Crea Playlist");

        setSize(420, 300);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setLocationRelativeTo(null);

        setLayout(null);

        JLabel lblIdPlaylist = new JLabel("ID playlist");

        lblIdPlaylist.setBounds(40, 40, 120, 25);

        add(lblIdPlaylist);

        txtIdPlaylist = new JTextField();

        txtIdPlaylist.setBounds(170, 40, 180, 25);

        add(txtIdPlaylist);

        JLabel lblNomePlaylist = new JLabel("Nome playlist");

        lblNomePlaylist.setBounds(40, 85, 120, 25);

        add(lblNomePlaylist);

        txtNomePlaylist = new JTextField();

        txtNomePlaylist.setBounds(170, 85, 180, 25);

        add(txtNomePlaylist);

        JLabel lblTipoPlaylist = new JLabel("Tipo playlist");

        lblTipoPlaylist.setBounds(40, 130, 120, 25);

        add(lblTipoPlaylist);

        cmbTipoPlaylist = new JComboBox<>();

        cmbTipoPlaylist.addItem("Privata");

        cmbTipoPlaylist.addItem("Pubblica");

        cmbTipoPlaylist.addItem("Condivisa");

        cmbTipoPlaylist.setBounds(170, 130, 180, 25);

        add(cmbTipoPlaylist);

        btnCrea = new JButton("Crea");

        btnCrea.setBounds(75, 200, 110, 30);

        add(btnCrea);

        btnAnnulla = new JButton("Annulla");

        btnAnnulla.setBounds(220, 200, 110, 30);

        add(btnAnnulla);

        btnCrea.addActionListener(e -> creaPlaylist());

        btnAnnulla.addActionListener(e -> {

            dispose();

            controller.mostraMenu();

        });

    }

    private void creaPlaylist() {

        String idPlaylist =

                txtIdPlaylist.getText().trim();

        String nomePlaylist =

                txtNomePlaylist.getText().trim();

        String tipoPlaylist =

                (String) cmbTipoPlaylist.getSelectedItem();

        if (idPlaylist.isEmpty()

                || nomePlaylist.isEmpty()

                || tipoPlaylist == null) {

            JOptionPane.showMessageDialog(

                    this,

                    "Compila tutti i campi",

                    "Attenzione",

                    JOptionPane.WARNING_MESSAGE

            );

            return;

        }

        boolean ok = controller.creaPlaylist(

                idPlaylist,

                nomePlaylist,

                tipoPlaylist

        );

        if (ok) {

            JOptionPane.showMessageDialog(

                    this,

                    "Playlist creata correttamente"

            );

            dispose();

            controller.mostraMenu();

        } else {

            JOptionPane.showMessageDialog(

                    this,

                    "Impossibile creare la playlist",

                    "Errore",

                    JOptionPane.ERROR_MESSAGE

            );

        }

    }

}