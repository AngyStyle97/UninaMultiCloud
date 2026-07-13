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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import control.Controller;

public class LoginPage extends JFrame {

    private Controller controller;
    private JTextField txtEmail;
    private JPasswordField txtPassword;
    private JLabel lblEmail;
    private JLabel lblPassword;
    private JButton btnAccedi;
    private JButton btnCancella;
    private JPanel pannelloLogin;

    public LoginPage(Controller controller) {

        this.controller = controller;

        setTitle("Login - UninaMultiCloud");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 320);
        setMinimumSize(new Dimension(420, 280));
        setLocationRelativeTo(null);
        setResizable(true);
        setLayout(new GridBagLayout());

        pannelloLogin = new JPanel(new GridLayout(4, 2, 10, 15));

        lblEmail = new JLabel("Email:");
        txtEmail = new JTextField();

        lblPassword = new JLabel("Password:");
        txtPassword = new JPasswordField();

        btnAccedi = new JButton("Accedi");
        btnCancella = new JButton("Cancella");

        pannelloLogin.add(lblEmail);
        pannelloLogin.add(txtEmail);

        pannelloLogin.add(lblPassword);
        pannelloLogin.add(txtPassword);

        pannelloLogin.add(btnAccedi);
        pannelloLogin.add(btnCancella);

        add(pannelloLogin, new GridBagConstraints());

        addComponentListener(new ComponentAdapter() {

            @Override
            public void componentResized(ComponentEvent e) {

                aggiornaDimensioni();

            }

        });

        btnAccedi.addActionListener(e -> {

            String email = txtEmail.getText();
            String password = new String(txtPassword.getPassword());

            boolean ok = controller.login(email, password);

            if (ok) {

                dispose();
                controller.mostraMenu();

            } else {

                mostraErrore("Credenziali errate");

            }

        });

        btnCancella.addActionListener(e -> pulisciCampi());

        aggiornaDimensioni();
    }

    private void aggiornaDimensioni() {

        int larghezza = getContentPane().getWidth();
        int altezza = getContentPane().getHeight();

        int larghezzaPannello = (int)(larghezza * 0.55);
        int altezzaPannello = (int)(altezza * 0.50);

        larghezzaPannello = Math.max(300, Math.min(larghezzaPannello, 600));
        altezzaPannello = Math.max(180, Math.min(altezzaPannello, 260));

        pannelloLogin.setPreferredSize(new Dimension(larghezzaPannello, altezzaPannello));

        int dimensioneFont = Math.min(larghezza / 40, altezza / 22);
        dimensioneFont = Math.max(13, Math.min(dimensioneFont, 22));

        Font font = new Font("Arial", Font.PLAIN, dimensioneFont);

        lblEmail.setFont(font);
        lblPassword.setFont(font);

        txtEmail.setFont(font);
        txtPassword.setFont(font);

        btnAccedi.setFont(font);
        btnCancella.setFont(font);

        pannelloLogin.revalidate();
        pannelloLogin.repaint();
    }

    public void mostraErrore(String messaggio) {

        JOptionPane.showMessageDialog(
                this,
                messaggio,
                "Errore",
                JOptionPane.ERROR_MESSAGE);

    }

    public void pulisciCampi() {

        txtEmail.setText("");
        txtPassword.setText("");

    }
}