package boundary;

import java.util.Date;

import javax.swing.JButton;

import javax.swing.JComboBox;

import javax.swing.JFrame;

import javax.swing.JLabel;

import javax.swing.JOptionPane;

import javax.swing.JTextField;

import control.Controller;

import entity.Audio;

import entity.ElementoMultimediale;

import entity.Video;

public class CaricaElementoPage extends JFrame {

    private Controller controller;

    private JTextField txtId;

    private JTextField txtTitolo;

    private JTextField txtDescrizione;

    private JTextField txtImmagineCopertina;

    private JTextField txtPeso;

    private JComboBox<String> cmbTipo;

    private JLabel lblCampo1;

    private JLabel lblCampo2;

    private JLabel lblCampo3;

    private JTextField txtCampo1;

    private JTextField txtCampo2;

    private JTextField txtCampo3;

    private JButton btnCarica;

    private JButton btnAnnulla;

    public CaricaElementoPage(Controller controller) {

        this.controller = controller;

        setTitle("Carica Elemento");

        setSize(470, 560);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setLocationRelativeTo(null);

        setLayout(null);

        JLabel lblId = new JLabel("ID elemento");

        lblId.setBounds(40, 30, 130, 25);

        add(lblId);

        txtId = new JTextField();

        txtId.setBounds(190, 30, 210, 25);

        add(txtId);

        JLabel lblTitolo = new JLabel("Titolo");

        lblTitolo.setBounds(40, 70, 130, 25);

        add(lblTitolo);

        txtTitolo = new JTextField();

        txtTitolo.setBounds(190, 70, 210, 25);

        add(txtTitolo);

        JLabel lblDescrizione = new JLabel("Descrizione");

        lblDescrizione.setBounds(40, 110, 130, 25);

        add(lblDescrizione);

        txtDescrizione = new JTextField();

        txtDescrizione.setBounds(190, 110, 210, 25);

        add(txtDescrizione);

        JLabel lblCopertina = new JLabel("Immagine copertina");

        lblCopertina.setBounds(40, 150, 140, 25);

        add(lblCopertina);

        txtImmagineCopertina = new JTextField();

        txtImmagineCopertina.setBounds(190, 150, 210, 25);

        add(txtImmagineCopertina);

        JLabel lblTipo = new JLabel("Tipo elemento");

        lblTipo.setBounds(40, 190, 130, 25);

        add(lblTipo);

        cmbTipo = new JComboBox<>();

        cmbTipo.addItem("Audio");

        cmbTipo.addItem("Video");

        cmbTipo.setBounds(190, 190, 210, 25);

        add(cmbTipo);

        lblCampo1 = new JLabel();

        lblCampo1.setBounds(40, 240, 130, 25);

        add(lblCampo1);

        txtCampo1 = new JTextField();

        txtCampo1.setBounds(190, 240, 210, 25);

        add(txtCampo1);

        lblCampo2 = new JLabel();

        lblCampo2.setBounds(40, 280, 130, 25);

        add(lblCampo2);

        txtCampo2 = new JTextField();

        txtCampo2.setBounds(190, 280, 210, 25);

        add(txtCampo2);

        lblCampo3 = new JLabel();

        lblCampo3.setBounds(40, 320, 130, 25);

        add(lblCampo3);

        txtCampo3 = new JTextField();

        txtCampo3.setBounds(190, 320, 210, 25);

        add(txtCampo3);

        JLabel lblPeso = new JLabel("Peso");

        lblPeso.setBounds(40, 370, 130, 25);

        add(lblPeso);

        txtPeso = new JTextField();

        txtPeso.setBounds(190, 370, 210, 25);

        add(txtPeso);

        btnCarica = new JButton("Carica");

        btnCarica.setBounds(90, 440, 120, 30);

        add(btnCarica);

        btnAnnulla = new JButton("Annulla");

        btnAnnulla.setBounds(250, 440, 120, 30);

        add(btnAnnulla);

        aggiornaCampiSpecifici();

        cmbTipo.addActionListener(e -> aggiornaCampiSpecifici());

        btnCarica.addActionListener(e -> caricaElemento());

        btnAnnulla.addActionListener(e -> {

            dispose();

            controller.mostraMenu();

        });

    }

    private void aggiornaCampiSpecifici() {

        String tipo = (String) cmbTipo.getSelectedItem();

        txtCampo1.setText("");

        txtCampo2.setText("");

        txtCampo3.setText("");

        if ("Audio".equals(tipo)) {

            lblCampo1.setText("ISRC");

            lblCampo2.setText("Durata audio");

            lblCampo3.setVisible(false);

            txtCampo3.setVisible(false);

        } else {

            lblCampo1.setText("Risoluzione");

            lblCampo2.setText("Formato");

            lblCampo3.setText("Durata video");

            lblCampo3.setVisible(true);

            txtCampo3.setVisible(true);

        }

    }

    private void caricaElemento() {

        String id = txtId.getText().trim();

        String titolo = txtTitolo.getText().trim();

        String descrizione = txtDescrizione.getText().trim();

        String copertina = txtImmagineCopertina.getText().trim();

        String tipo = (String) cmbTipo.getSelectedItem();

        String testoPeso = txtPeso.getText().trim();

        if (id.isEmpty()

                || titolo.isEmpty()

                || descrizione.isEmpty()

                || copertina.isEmpty()

                || testoPeso.isEmpty()) {

            JOptionPane.showMessageDialog(

                    this,

                    "Compila tutti i campi obbligatori",

                    "Attenzione",

                    JOptionPane.WARNING_MESSAGE

            );

            return;

        }

        try {

            double peso = Double.parseDouble(testoPeso);

            if (peso <= 0) {

                throw new IllegalArgumentException();

            }

            ElementoMultimediale elemento;

            if ("Audio".equals(tipo)) {

                String isrc = txtCampo1.getText().trim();

                String testoDurataAudio = txtCampo2.getText().trim();

                if (isrc.isEmpty() || testoDurataAudio.isEmpty()) {

                    throw new IllegalArgumentException();

                }

                double durataAudio =

                        Double.parseDouble(testoDurataAudio);

                if (durataAudio <= 0) {

                    throw new IllegalArgumentException();

                }

                elemento = new Audio(

                        id,

                        descrizione,

                        new Date(),

                        titolo,

                        0,

                        copertina,

                        isrc,

                        durataAudio

                );

            } else {

                String risoluzione = txtCampo1.getText().trim();

                String formato = txtCampo2.getText().trim();

                String testoDurataVideo = txtCampo3.getText().trim();

                if (risoluzione.isEmpty()

                        || formato.isEmpty()

                        || testoDurataVideo.isEmpty()) {

                    throw new IllegalArgumentException();

                }

                double durataVideo =

                        Double.parseDouble(testoDurataVideo);

                if (durataVideo <= 0) {

                    throw new IllegalArgumentException();

                }

                elemento = new Video(

                        id,

                        descrizione,

                        new Date(),

                        titolo,

                        0,

                        copertina,

                        risoluzione,

                        formato,

                        durataVideo

                );

            }

            boolean ok =

                    controller.inserisciElemento(

                            elemento,

                            peso

                    );

            if (ok) {

                JOptionPane.showMessageDialog(

                        this,

                        "Elemento caricato correttamente"

                );

                dispose();

                controller.mostraMenu();

            } else {

                JOptionPane.showMessageDialog(

                        this,

                        "Impossibile caricare l'elemento",

                        "Errore",

                        JOptionPane.ERROR_MESSAGE

                );

            }

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(

                    this,

                    "Peso e durata devono essere numeri",

                    "Errore",

                    JOptionPane.ERROR_MESSAGE

            );

        } catch (IllegalArgumentException ex) {

            JOptionPane.showMessageDialog(

                    this,

                    "Controlla i dati inseriti: peso e durata devono essere maggiori di zero",

                    "Errore",

                    JOptionPane.ERROR_MESSAGE

            );

        }

    }

}