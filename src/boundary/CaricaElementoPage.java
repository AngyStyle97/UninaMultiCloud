package boundary;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
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
    private JPanel pannelloCaricamento;

    public CaricaElementoPage(Controller controller) {

        this.controller = controller;

        setTitle("Carica Elemento");
        setSize(650, 650);
        setMinimumSize(new Dimension(500, 550));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);
        setLayout(new GridBagLayout());

        pannelloCaricamento = new JPanel(new GridLayout(9, 2, 12, 12));

        JLabel lblId = new JLabel("ID elemento");
        txtId = new JTextField();

        JLabel lblTitolo = new JLabel("Titolo");
        txtTitolo = new JTextField();

        JLabel lblDescrizione = new JLabel("Descrizione");
        txtDescrizione = new JTextField();

        JLabel lblCopertina = new JLabel("Immagine copertina");
        txtImmagineCopertina = new JTextField();

        JLabel lblTipo = new JLabel("Tipo elemento");

        cmbTipo = new JComboBox<>();
        cmbTipo.addItem("Audio");
        cmbTipo.addItem("Video");

        lblCampo1 = new JLabel();
        txtCampo1 = new JTextField();
        lblCampo2 = new JLabel();
        txtCampo2 = new JTextField();
        lblCampo3 = new JLabel();
        txtCampo3 = new JTextField();

        JLabel lblPeso = new JLabel("Peso");
        txtPeso = new JTextField();

        btnCarica = new JButton("Carica");
        btnAnnulla = new JButton("Annulla");

        pannelloCaricamento.add(lblId);
        pannelloCaricamento.add(txtId);
        pannelloCaricamento.add(lblTitolo);
        pannelloCaricamento.add(txtTitolo);
        pannelloCaricamento.add(lblDescrizione);
        pannelloCaricamento.add(txtDescrizione);
        pannelloCaricamento.add(lblCopertina);
        pannelloCaricamento.add(txtImmagineCopertina);
        pannelloCaricamento.add(lblTipo);
        pannelloCaricamento.add(cmbTipo);
        pannelloCaricamento.add(lblCampo1);
        pannelloCaricamento.add(txtCampo1);
        pannelloCaricamento.add(lblCampo2);
        pannelloCaricamento.add(txtCampo2);
        pannelloCaricamento.add(lblCampo3);
        pannelloCaricamento.add(txtCampo3);
        pannelloCaricamento.add(lblPeso);
        pannelloCaricamento.add(txtPeso);

        JPanel pannelloPulsanti = new JPanel(new GridLayout(1, 2, 15, 0));

        pannelloPulsanti.add(btnCarica);
        pannelloPulsanti.add(btnAnnulla);

        JPanel pannelloCompleto = new JPanel(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;

        pannelloCompleto.add(pannelloCaricamento, gbc);

        gbc.gridy = 1;
        gbc.weighty = 0.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new java.awt.Insets(20, 0, 0, 0);

        pannelloCompleto.add(pannelloPulsanti, gbc);

        add(pannelloCompleto, new GridBagConstraints());

        addComponentListener(new ComponentAdapter() {

            @Override

            public void componentResized(ComponentEvent e) {

                aggiornaDimensioni(pannelloCompleto, pannelloPulsanti, lblId, lblTitolo, lblDescrizione, lblCopertina, lblTipo, lblPeso);
                }
        });

        aggiornaCampiSpecifici();
        cmbTipo.addActionListener(e -> aggiornaCampiSpecifici());
        btnCarica.addActionListener(e -> caricaElemento());
        
        btnAnnulla.addActionListener(e -> {
            dispose();
            controller.mostraMenu();
        });

        aggiornaDimensioni(pannelloCompleto, pannelloPulsanti, lblId, lblTitolo, lblDescrizione, lblCopertina, lblTipo, lblPeso);
    }

    private void aggiornaDimensioni(JPanel pannelloCompleto, JPanel pannelloPulsanti, JLabel lblId, JLabel lblTitolo,
                                    JLabel lblDescrizione, JLabel lblCopertina, JLabel lblTipo, JLabel lblPeso) {

        int larghezzaFinestra = getContentPane().getWidth();
        int altezzaFinestra = getContentPane().getHeight();
        int larghezzaPannello = (int) (larghezzaFinestra * 0.70);
        int altezzaPannello = (int) (altezzaFinestra * 0.78);

        larghezzaPannello = Math.max(400, Math.min(larghezzaPannello, 750));
        altezzaPannello = Math.max(430, Math.min(altezzaPannello, 650));

        pannelloCompleto.setPreferredSize(new Dimension(larghezzaPannello, altezzaPannello));

        int dimensioneFont = Math.min(larghezzaFinestra / 45, altezzaFinestra / 35);

        dimensioneFont = Math.max(13, Math.min(dimensioneFont, 21));

        Font font = new Font("Arial", Font.PLAIN, dimensioneFont);

        lblId.setFont(font);
        lblTitolo.setFont(font);
        lblDescrizione.setFont(font);
        lblCopertina.setFont(font);
        lblTipo.setFont(font);
        lblPeso.setFont(font);
        lblCampo1.setFont(font);
        lblCampo2.setFont(font);
        lblCampo3.setFont(font);
        
        txtId.setFont(font);
        txtTitolo.setFont(font);
        txtDescrizione.setFont(font);
        txtImmagineCopertina.setFont(font);
        txtPeso.setFont(font);
        txtCampo1.setFont(font);
        txtCampo2.setFont(font);
        txtCampo3.setFont(font);

        cmbTipo.setFont(font);

        btnCarica.setFont(font);
        btnAnnulla.setFont(font);

        pannelloCaricamento.revalidate();
        pannelloCaricamento.repaint();
        pannelloPulsanti.revalidate();
        pannelloPulsanti.repaint();
        pannelloCompleto.revalidate();
        pannelloCompleto.repaint();

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

                if (isrc.isEmpty()

                        || testoDurataAudio.isEmpty()) {

                    throw new IllegalArgumentException();

                }

                double durataAudio = Double.parseDouble(testoDurataAudio);

                if (durataAudio <= 0) {

                    throw new IllegalArgumentException();

                }

                elemento = new Audio(id, descrizione, new Date(), titolo, 0, copertina, isrc, durataAudio);

            } else {

                String risoluzione = txtCampo1.getText().trim();
                String formato = txtCampo2.getText().trim();
                String testoDurataVideo = txtCampo3.getText().trim();

                if (risoluzione.isEmpty()

                        || formato.isEmpty()

                        || testoDurataVideo.isEmpty()) {

                    throw new IllegalArgumentException();

                }

                double durataVideo = Double.parseDouble(testoDurataVideo);

                if (durataVideo <= 0) {

                    throw new IllegalArgumentException();

                }

                elemento = new Video(id, descrizione, new Date(), titolo, 0, copertina, risoluzione, formato, durataVideo);
            }

            boolean ok = controller.inserisciElemento(elemento, peso);

            if (ok) {

                JOptionPane.showMessageDialog(this, "Elemento caricato correttamente");

                dispose();
                controller.mostraMenu();

            } else {

                JOptionPane.showMessageDialog(this, "Impossibile caricare l'elemento", "Errore", JOptionPane.ERROR_MESSAGE);

            }

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(this, "Peso e durata devono essere numeri", "Errore", JOptionPane.ERROR_MESSAGE);

        } catch (IllegalArgumentException ex) {

            JOptionPane.showMessageDialog(this, "Controlla i dati inseriti: peso e durata devono essere maggiori di zero", "Errore",
            		JOptionPane.ERROR_MESSAGE);

        }

    }

}