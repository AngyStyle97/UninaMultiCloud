package boundary;

import javax.swing.*;
import java.awt.*;
import control.Controller;

public class LoginPage extends JFrame {

    private Controller controller;
    private JTextField txtEmail;
    private JPasswordField txtPassword;

    public LoginPage(Controller controller) {
        this.controller = controller;

        setTitle("Login - UninaMultiCloud");
        setSize(350, 220);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setBounds(40, 35, 80, 25);
        add(lblEmail);

        txtEmail = new JTextField();
        txtEmail.setBounds(130, 35, 160, 25);
        add(txtEmail);

        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setBounds(40, 75, 80, 25);
        add(lblPassword);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(130, 75, 160, 25);
        add(txtPassword);

        JButton btnAccedi = new JButton("Accedi");
        btnAccedi.setBounds(60, 125, 100, 30);
        add(btnAccedi);

        JButton btnCancella = new JButton("Canceeella");
        btnCancella.setBounds(180, 125, 100, 30);
        add(btnCancella);

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
    }

    public void mostraErrore(String messaggio) {
        JOptionPane.showMessageDialog(this, messaggio, "Errore", JOptionPane.ERROR_MESSAGE);
    }

    public void pulisciCampi() {
        txtEmail.setText("");
        txtPassword.setText("");
    }
}