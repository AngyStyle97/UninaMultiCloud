package boundary;

import javax.swing.*;

import control.Controller;

public class ProfiloPage extends JFrame {

    private Controller controller;

    private JLabel lblNome;

    private JLabel lblCognome;

    private JLabel lblEmail;

    private JTextField txtNome;

    private JTextField txtCognome;

    private JTextField txtEmail;

    private JButton btnReportPlaylist;

    private JButton btnEsci;

    public ProfiloPage(Controller controller) {

        this.controller = controller;

        setTitle("Profilo");

        setSize(350,300);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setLocationRelativeTo(null);

        setLayout(null);

        lblNome = new JLabel("Nome");

        lblNome.setBounds(40,30,80,25);

        add(lblNome);

        txtNome = new JTextField();

        txtNome.setBounds(130,30,150,25);

        txtNome.setEditable(false);

        add(txtNome);

        lblCognome = new JLabel("Cognnome");

        lblCognome.setBounds(40,70,80,25);

        add(lblCognome);

        txtCognome = new JTextField();

        txtCognome.setBounds(130,70,150,25);

        txtCognome.setEditable(false);

        add(txtCognome);

        lblEmail = new JLabel("Email");

        lblEmail.setBounds(40,110,80,25);

        add(lblEmail);

        txtEmail = new JTextField();

        txtEmail.setBounds(130,110,150,25);

        txtEmail.setEditable(false);

        add(txtEmail);

        btnReportPlaylist = new JButton("Report Playlist");

        btnReportPlaylist.setBounds(80,160,170,30);

        add(btnReportPlaylist);

        btnEsci = new JButton("Esci");

        btnEsci.setBounds(110,210,110,30);

        add(btnEsci);

        btnReportPlaylist.addActionListener(e ->

                controller.mostraReportPlaylist());

        btnEsci.addActionListener(e -> dispose());

    }

    public void mostraProfilo(String nome, String cognome, String email) {

        txtNome.setText(nome);

        txtCognome.setText(cognome);

        txtEmail.setText(email);

    }

}
