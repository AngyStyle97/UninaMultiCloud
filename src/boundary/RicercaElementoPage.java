package boundary;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
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
import java.awt.Toolkit;

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
    	setIconImage(Toolkit.getDefaultToolkit().getImage(RicercaElementoPage.class.getResource("/images/UNINAFY.png")));

        this.controller = controller;

        setTitle("Ricerca Elemento");
        setSize(600, 500);
        setMinimumSize(new Dimension(430, 380));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);
        getContentPane().setLayout(new GridBagLayout());

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

        GridBagConstraints gbcTitolo = new GridBagConstraints();

        gbcTitolo.gridx = 0;
        gbcTitolo.gridy = 0;
        gbcTitolo.weightx = 1.0;
        gbcTitolo.fill = GridBagConstraints.HORIZONTAL;
        gbcTitolo.insets = new Insets(0, 0, 15, 0);

        pannelloRicerca.add(pannelloTitolo, gbcTitolo);

        GridBagConstraints gbcPulsantiRicerca = new GridBagConstraints();

        gbcPulsantiRicerca.gridx = 0;
        gbcPulsantiRicerca.gridy = 1;
        gbcPulsantiRicerca.weightx = 1.0;
        gbcPulsantiRicerca.fill = GridBagConstraints.HORIZONTAL;
        gbcPulsantiRicerca.insets = new Insets(0, 0, 15, 0);

        pannelloRicerca.add(
                pannelloPulsantiRicerca,
                gbcPulsantiRicerca
        );

        GridBagConstraints gbcRisultati = new GridBagConstraints();

        gbcRisultati.gridx = 0;
        gbcRisultati.gridy = 2;
        gbcRisultati.weightx = 1.0;
        gbcRisultati.weighty = 1.0;
        gbcRisultati.fill = GridBagConstraints.BOTH;
        gbcRisultati.insets = new Insets(0, 0, 15, 0);

        pannelloRicerca.add(scrollRisultati, gbcRisultati);

        GridBagConstraints gbcPulsantiFinali = new GridBagConstraints();

        gbcPulsantiFinali.gridx = 0;
        gbcPulsantiFinali.gridy = 3;
        gbcPulsantiFinali.weightx = 1.0;
        gbcPulsantiFinali.weighty = 0.0;
        gbcPulsantiFinali.fill = GridBagConstraints.HORIZONTAL;
        gbcPulsantiFinali.insets = new Insets(0, 0, 0, 0);

        pannelloRicerca.add(pannelloPulsantiFinali, gbcPulsantiFinali);

        GridBagConstraints gbcRicerca = new GridBagConstraints();

        gbcRicerca.gridx = 0;
        gbcRicerca.gridy = 0;

        getContentPane().add(pannelloRicerca, gbcRicerca);

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

        larghezzaPannello = Math.max(340, Math.min(larghezzaPannello, 760));
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

    public void mostraRisultati(ArrayList<ElementoMultimediale> risultati) {

        modelloLista.clear();

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