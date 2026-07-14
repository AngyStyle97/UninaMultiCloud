package boundary;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.net.URL;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
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
    private JButton btnIndietro;
    private JPanel pannelloProfilo;

    public ProfiloPage(Controller controller) {

        this.controller = controller;

        URL urlIcona = ProfiloPage.class.getResource("/images/UNINAFY.png");

        if (urlIcona != null) {

            setIconImage(Toolkit.getDefaultToolkit().getImage(urlIcona));

        }

        setTitle("Profilo");
        setSize(500, 420);
        setMinimumSize(new Dimension(400, 340));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);

        PannelloSfondo pannelloSfondo = new PannelloSfondo("/images/sfondo.jpg");

        pannelloSfondo.setLayout(new GridBagLayout());
        setContentPane(pannelloSfondo);

        pannelloProfilo = new JPanel(new GridLayout(5, 2, 12, 15));

        pannelloProfilo.setOpaque(false);
        lblNome = new JLabel("Nome");
        lblNome.setForeground(Color.WHITE);
        txtNome = new JTextField();
        txtNome.setEditable(false);
        lblCognome = new JLabel("Cognome");
        lblCognome.setForeground(Color.WHITE);
        txtCognome = new JTextField();
        txtCognome.setEditable(false);
        lblEmail = new JLabel("Email");
        lblEmail.setForeground(Color.WHITE);
        txtEmail = new JTextField();
        txtEmail.setEditable(false);

        btnReportPlaylist = new JButton("Report Playlist");
        btnIndietro = new JButton("Indietro");

        pannelloProfilo.add(lblNome);
        pannelloProfilo.add(txtNome);
        pannelloProfilo.add(lblCognome);
        pannelloProfilo.add(txtCognome);
        pannelloProfilo.add(lblEmail);
        pannelloProfilo.add(txtEmail);

        JLabel spazioReport = new JLabel("");
        spazioReport.setOpaque(false);
        JLabel spazioIndietro = new JLabel("");
        spazioIndietro.setOpaque(false);

        pannelloProfilo.add(spazioReport);
        pannelloProfilo.add(btnReportPlaylist);
        pannelloProfilo.add(spazioIndietro);
        pannelloProfilo.add(btnIndietro);

        GridBagConstraints gbcProfilo = new GridBagConstraints();

        gbcProfilo.gridx = 0;
        gbcProfilo.gridy = 0;
        gbcProfilo.weightx = 0.0;
        gbcProfilo.weighty = 0.0;
        gbcProfilo.fill = GridBagConstraints.NONE;

        gbcProfilo.anchor = GridBagConstraints.CENTER;

        pannelloSfondo.add(pannelloProfilo, gbcProfilo);

        addComponentListener(new ComponentAdapter() {

                    @Override

                    public void componentResized(ComponentEvent e) {

                        aggiornaDimensioni();

                    }
                }

        );

        btnReportPlaylist.addActionListener(e -> controller.mostraReportPlaylist());

        btnIndietro.addActionListener(e -> controller.tornaAlMenuDalProfilo());

        aggiornaDimensioni();

    }

    private void aggiornaDimensioni() {

        int larghezzaFinestra = getContentPane().getWidth();
        int altezzaFinestra = getContentPane().getHeight();
        int larghezzaPannello = (int) (larghezzaFinestra * 0.60);
        int altezzaPannello = (int) (altezzaFinestra * 0.65);
        larghezzaPannello = Math.max(320, Math.min(larghezzaPannello, 650));

        altezzaPannello = Math.max(240, Math.min(altezzaPannello, 420));

        pannelloProfilo.setPreferredSize(new Dimension(larghezzaPannello, altezzaPannello));
        int dimensioneFont = Math.min(larghezzaFinestra / 40, altezzaFinestra / 24);
        dimensioneFont = Math.max(13, Math.min(dimensioneFont, 22));

        Font font = new Font("Arial", Font.PLAIN, dimensioneFont);

        lblNome.setFont(font);
        lblCognome.setFont(font);
        lblEmail.setFont(font);
        txtNome.setFont(font);
        txtCognome.setFont(font);
        txtEmail.setFont(font);
        btnReportPlaylist.setFont(font);
        btnIndietro.setFont(font);
        pannelloProfilo.revalidate();
        pannelloProfilo.repaint();
        getContentPane().revalidate();
        getContentPane().repaint();

    }

    public void mostraProfilo(String nome, String cognome, String email) {

        txtNome.setText(nome);
        txtCognome.setText(cognome);
        txtEmail.setText(email);
    }
}