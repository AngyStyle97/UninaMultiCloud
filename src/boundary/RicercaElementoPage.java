package boundary;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import control.Controller;
import entity.ElementoMultimediale;

public class RicercaElementoPage extends JFrame {

    private Controller controller;
    private JLabel lblTitolo;
    private JTextField txtTitolo;
    private JButton btnCerca;
    private JButton btnVisualizza;
    private JButton btnAnnulla;
    private DefaultListModel<ElementoMultimediale> modelloLista;
    private JList<ElementoMultimediale> listaRisultati;
    private JScrollPane scrollRisultati;
    private JPanel pannelloRicerca;
    private JPanel pannelloTitolo;
    private JPanel pannelloPulsantiRicerca;
    private JPanel pannelloPulsantiFinali;

    public RicercaElementoPage(Controller controller) {

        this.controller = controller;
        setTitle("Ricerca Elemento");
        setSize(600, 500);
        setMinimumSize(new Dimension(430, 380));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);
        setLayout(new GridBagLayout());

        pannelloTitolo = new JPanel(new GridLayout(1, 2, 12, 0));

        lblTitolo = new JLabel("Titolo elemento");
        txtTitolo = new JTextField();
        pannelloTitolo.add(lblTitolo);
        pannelloTitolo.add(txtTitolo);
        
        pannelloPulsantiRicerca = new JPanel(new GridLayout(1, 2, 15, 0));

        btnCerca = new JButton("Cerca");
        btnAnnulla = new JButton("Annulla");

        pannelloPulsantiRicerca.add(btnCerca);
        pannelloPulsantiRicerca.add(btnAnnulla);

        modelloLista = new DefaultListModel<>();
        listaRisultati = new JList<>(modelloLista);
        scrollRisultati = new JScrollPane(listaRisultati);

        pannelloPulsantiFinali = new JPanel(new GridLayout(1, 1));

        btnVisualizza = new JButton("Visualizza");
        pannelloPulsantiFinali.add(btnVisualizza);

 

        pannelloRicerca = new JPanel(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new java.awt.Insets(0, 0, 15, 0);

        pannelloRicerca.add(pannelloTitolo, gbc);

        gbc.gridy = 1;
        gbc.insets = new java.awt.Insets(0, 0, 15, 0);

        pannelloRicerca.add(pannelloPulsantiRicerca, gbc);

        gbc.gridy = 2;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new java.awt.Insets(0, 0, 15, 0);

        pannelloRicerca.add(scrollRisultati, gbc);

        gbc.gridy = 3;
        gbc.weighty = 0.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new java.awt.Insets(0, 0, 0, 0);

        pannelloRicerca.add(pannelloPulsantiFinali, gbc);

        add(pannelloRicerca, new GridBagConstraints());

        addComponentListener(new ComponentAdapter() {

            @Override

            public void componentResized(ComponentEvent e) {

                aggiornaDimensioni();

            }

        });

        btnCerca.addActionListener(e -> cercaElementi());

        btnVisualizza.addActionListener(e -> {

            ElementoMultimediale elementoSelezionato = listaRisultati.getSelectedValue();

            if (elementoSelezionato == null) {

                JOptionPane.showMessageDialog(this, "Seleziona un elemento da visualizzare", "Attenzione", JOptionPane.WARNING_MESSAGE);

                return;

            }

            controller.mostraRiproduzioneElemento(elementoSelezionato);
            });

        btnAnnulla.addActionListener(e -> {
        	
         dispose();
         controller.mostraProfilo();
        });

        aggiornaDimensioni();

    }

    private void aggiornaDimensioni() {

        int larghezzaFinestra = getContentPane().getWidth();
        int altezzaFinestra = getContentPane().getHeight();
        int larghezzaPannello = (int) (larghezzaFinestra * 0.72);
        int altezzaPannello = (int) (altezzaFinestra * 0.75);
        
        larghezzaPannello = Math.max(340,Math.min(larghezzaPannello, 760));
        altezzaPannello = Math.max(280, Math.min(altezzaPannello, 620));

        pannelloRicerca.setPreferredSize(new Dimension(larghezzaPannello, altezzaPannello));

        int dimensioneFont = Math.min(larghezzaFinestra / 42, altezzaFinestra / 28);
        dimensioneFont = Math.max(13, Math.min(dimensioneFont, 22));
        Font font = new Font("Arial", Font.PLAIN, dimensioneFont);

        lblTitolo.setFont(font);
        txtTitolo.setFont(font);
        btnCerca.setFont(font);
        btnVisualizza.setFont(font);
        btnAnnulla.setFont(font);
        listaRisultati.setFont(font);
        pannelloTitolo.revalidate();
        pannelloTitolo.repaint();
        pannelloPulsantiRicerca.revalidate();
        pannelloPulsantiRicerca.repaint();
        pannelloPulsantiFinali.revalidate();
        pannelloPulsantiFinali.repaint();
        pannelloRicerca.revalidate();
        pannelloRicerca.repaint();

    }

    private void cercaElementi() {

        String titolo = txtTitolo.getText().trim();

        if (titolo.isEmpty()) {

            JOptionPane.showMessageDialog(this, "Inserisci il titolo dell'elemento", "Attenzione", JOptionPane.WARNING_MESSAGE);

            return;

        }

        ArrayList<ElementoMultimediale> risultati = controller.cercaElemento(titolo);
        mostraRisultati(risultati);

    }

    public void mostraRisultati(

            ArrayList<ElementoMultimediale> risultati) {modelloLista.clear();

        if (risultati == null || risultati.isEmpty()) {

            JOptionPane.showMessageDialog(this, "Nessun elemento trovato", "Ricerca elemento", JOptionPane.INFORMATION_MESSAGE);

            return;

        }

        for (ElementoMultimediale elemento : risultati) {

            modelloLista.addElement(elemento);

        }

    }

    public void mostraMessaggio(String messaggio) {

        JOptionPane.showMessageDialog(this, messaggio);

    }
}