package boundary;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
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
    private JButton btnEsci;
    private JPanel pannelloProfilo;

    public ProfiloPage(Controller controller) {

        this.controller = controller;
        
        setTitle("Profilo");
        setSize(500, 420);
        setMinimumSize(new Dimension(400, 340));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);
        setLayout(new GridBagLayout());

        pannelloProfilo = new JPanel(new GridLayout(5, 2, 12, 15));

        lblNome = new JLabel("Nome");
        txtNome = new JTextField();
        txtNome.setEditable(false);
        lblCognome = new JLabel("Cognome");
        txtCognome = new JTextField();
        txtCognome.setEditable(false);
        lblEmail = new JLabel("Email");
        txtEmail = new JTextField();
        txtEmail.setEditable(false);

        btnReportPlaylist = new JButton("Report Playlist");
        btnEsci = new JButton("Esci");

        pannelloProfilo.add(lblNome);
        pannelloProfilo.add(txtNome);
        pannelloProfilo.add(lblCognome);
        pannelloProfilo.add(txtCognome);
        pannelloProfilo.add(lblEmail);
        pannelloProfilo.add(txtEmail);
        pannelloProfilo.add(new JLabel(""));
        pannelloProfilo.add(btnReportPlaylist);
        pannelloProfilo.add(new JLabel(""));
        pannelloProfilo.add(btnEsci);
        
        add(pannelloProfilo, new GridBagConstraints());

        addComponentListener(new ComponentAdapter() {

            @Override

            public void componentResized(ComponentEvent e) {

                aggiornaDimensioni();

            }

        });

        btnReportPlaylist.addActionListener(e ->

                controller.mostraReportPlaylist());

        btnEsci.addActionListener(e -> dispose());

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
        btnEsci.setFont(font);

        pannelloProfilo.revalidate();
        pannelloProfilo.repaint();

    }

    public void mostraProfilo(String nome, String cognome, String email) {

        txtNome.setText(nome);
        txtCognome.setText(cognome);
        txtEmail.setText(email);

    }

}