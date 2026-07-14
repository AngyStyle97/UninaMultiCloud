package boundary;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.net.URL;
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
import java.awt.Color;

public class RicercaElementoPage extends JFrame {

    private Controller controller;
    private CardLayout cardLayout;
    private JPanel pannelloPrincipale;
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
    private JPanel pannelloRiproduzione;
    private JPanel pannelloRiproduzioneCompleto;
    private JPanel pannelloPulsantiRiproduzione;
    private JLabel lblInRiproduzione;
    private JLabel lblTitoloRiproduzione;
    private JButton btnAggiungiPlaylist;
    private JButton btnIndietro;
    private ElementoMultimediale elementoRiprodotto;

    public RicercaElementoPage(Controller controller) {

        this.controller = controller;

        URL icona = RicercaElementoPage.class.getResource("/images/UNINAFY.png");

        if (icona != null) {
            setIconImage(Toolkit.getDefaultToolkit().getImage(icona));
        }

        setTitle("Ricerca Elemento");
        setSize(600, 500);
        setMinimumSize(new Dimension(430, 380));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);

        cardLayout = new CardLayout();
        pannelloPrincipale = new PannelloSfondo("/images/sfondo.jpg");
        pannelloPrincipale.setLayout(cardLayout);

        creaPannelloRicerca();
        creaPannelloRiproduzione();

        pannelloPrincipale.add(pannelloRicerca, "RICERCA");

        pannelloPrincipale.add(pannelloRiproduzione, "RIPRODUZIONE");

        setContentPane(pannelloPrincipale);
        
        addComponentListener(new ComponentAdapter() {

            @Override
            public void componentResized(ComponentEvent e) {

                aggiornaDimensioni();
            }
        });

        aggiornaDimensioni();
        mostraSchermataRicerca();
    }

    private void creaPannelloRicerca() {

        pannelloRicerca = new JPanel(new GridBagLayout());
        pannelloTitolo = new JPanel(new GridLayout(1, 2, 12, 0));
        pannelloRicerca.setOpaque(false);
        pannelloTitolo.setOpaque(false);
        
        lblTitolo = new JLabel("Titolo elemento");
        lblTitolo.setForeground(Color.WHITE);
        txtTitolo = new JTextField();

        pannelloTitolo.add(lblTitolo);
        pannelloTitolo.add(txtTitolo);

        pannelloPulsantiRicerca = new JPanel(new GridLayout(1, 2, 15, 0));
        pannelloPulsantiRicerca.setOpaque(false);

        btnCerca = new JButton("Cerca");

        btnAnnulla = new JButton("Annulla");

        pannelloPulsantiRicerca.add(btnCerca);
        pannelloPulsantiRicerca.add(btnAnnulla);

        modelloLista = new DefaultListModel<>();
        listaRisultati = new JList<>(modelloLista);
        scrollRisultati = new JScrollPane(listaRisultati);

        pannelloPulsantiFinali = new JPanel(new GridLayout(1, 1));
        pannelloPulsantiFinali.setOpaque(false);

        btnVisualizza = new JButton("Visualizza");
        pannelloPulsantiFinali.add(btnVisualizza);

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

        pannelloRicerca.add(pannelloPulsantiRicerca, gbcPulsantiRicerca);

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

        pannelloRicerca.add(pannelloPulsantiFinali, gbcPulsantiFinali);

        btnCerca.addActionListener(
                e -> cercaElementi()
        );

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
            controller.mostraMenu();
        });
    }

    private void creaPannelloRiproduzione() {

        pannelloRiproduzione = new JPanel(new GridBagLayout());
        pannelloRiproduzioneCompleto = new JPanel(new GridBagLayout());
        pannelloRiproduzione.setOpaque(false);
        pannelloRiproduzioneCompleto.setOpaque(false);

        lblInRiproduzione = new JLabel("In riproduzione", JLabel.CENTER);
        lblInRiproduzione.setForeground(Color.WHITE);
        
        lblTitoloRiproduzione = new JLabel("", JLabel.CENTER);
        lblTitoloRiproduzione.setForeground(Color.WHITE);
        
        btnAggiungiPlaylist = new JButton("Aggiungi alla Playlist");
        btnIndietro = new JButton("Indietro");

        pannelloPulsantiRiproduzione = new JPanel(new GridLayout(1, 2, 15, 0));
        pannelloPulsantiRiproduzione.setOpaque(false);
        pannelloPulsantiRiproduzione.add(btnAggiungiPlaylist);
        pannelloPulsantiRiproduzione.add(btnIndietro);

        GridBagConstraints gbcInRiproduzione = new GridBagConstraints();

        gbcInRiproduzione.gridx = 0;
        gbcInRiproduzione.gridy = 0;
        gbcInRiproduzione.weightx = 1.0;
        gbcInRiproduzione.fill = GridBagConstraints.HORIZONTAL;

        gbcInRiproduzione.insets = new Insets(12, 10, 12, 10);

        pannelloRiproduzioneCompleto.add(lblInRiproduzione, gbcInRiproduzione);

        GridBagConstraints gbcTitoloRiproduzione = new GridBagConstraints();

        gbcTitoloRiproduzione.gridx = 0;
        gbcTitoloRiproduzione.gridy = 1;
        gbcTitoloRiproduzione.weightx = 1.0;
        gbcTitoloRiproduzione.fill = GridBagConstraints.HORIZONTAL;

        gbcTitoloRiproduzione.insets = new Insets(12, 10, 12, 10);

        pannelloRiproduzioneCompleto.add(lblTitoloRiproduzione, gbcTitoloRiproduzione);

        GridBagConstraints gbcPulsantiRiproduzione = new GridBagConstraints();

        gbcPulsantiRiproduzione.gridx = 0;
        gbcPulsantiRiproduzione.gridy = 2;
        gbcPulsantiRiproduzione.weightx = 1.0;
        gbcPulsantiRiproduzione.fill = GridBagConstraints.HORIZONTAL;

        gbcPulsantiRiproduzione.insets = new Insets(12, 10, 12, 10);

        pannelloRiproduzioneCompleto.add(pannelloPulsantiRiproduzione, gbcPulsantiRiproduzione);
        pannelloRiproduzione.add(pannelloRiproduzioneCompleto);

        btnAggiungiPlaylist.addActionListener(e -> {

            if (elementoRiprodotto == null) {
                return;
            }

            String idPlaylist = JOptionPane.showInputDialog(this, "Inserisci ID della playlist:", "Aggiungi alla playlist",
            		JOptionPane.QUESTION_MESSAGE);

            if (idPlaylist == null) {
                return;
            }

            idPlaylist = idPlaylist.trim();

            if (idPlaylist.isEmpty()) {

                JOptionPane.showMessageDialog(this, "Inserisci un ID playlist valido", "Attenzione", JOptionPane.WARNING_MESSAGE);

                return;
            }

            boolean ok = controller.aggiungiElementoAPlaylist(elementoRiprodotto.getIdElemento(), idPlaylist);

            if (ok) {

                JOptionPane.showMessageDialog(this, "Elemento aggiunto alla playlist");

            } else {

                JOptionPane.showMessageDialog(this, "Impossibile aggiungere l'elemento", "Errore", JOptionPane.ERROR_MESSAGE);
            }
        });
    
        btnIndietro.addActionListener(e ->

                mostraSchermataRicerca()
        );
    }

    public void mostraSchermataRicerca() {

        setTitle("Ricerca Elemento");

        cardLayout.show(pannelloPrincipale, "RICERCA");

        pannelloPrincipale.revalidate();
        pannelloPrincipale.repaint();
    }

    public void mostraSchermataRiproduzione(ElementoMultimediale elemento) {

        if (elemento == null) {
            return;
        }

        elementoRiprodotto = elemento;
        setTitle("Riproduzione Elemento");
        lblTitoloRiproduzione.setText(elemento.getTitolo());

        cardLayout.show(pannelloPrincipale, "RIPRODUZIONE");

        pannelloPrincipale.revalidate();
        pannelloPrincipale.repaint();
    }

    private void aggiornaDimensioni() {

        int larghezzaFinestra = getContentPane().getWidth();
        int altezzaFinestra = getContentPane().getHeight();
        int larghezzaContenuto = (int) (larghezzaFinestra * 0.48);
        int altezzaContenuto = (int) (altezzaFinestra * 0.48);

        larghezzaContenuto = Math.max(360, Math.min(larghezzaContenuto, 600));
        altezzaContenuto = Math.max(290, Math.min(altezzaContenuto, 440));
        int margineOrizzontale = Math.max(20, (larghezzaFinestra - larghezzaContenuto) / 2);
        int margineVerticale = Math.max(20, (altezzaFinestra - altezzaContenuto) / 2);

        pannelloRicerca.setBorder(javax.swing.BorderFactory.createEmptyBorder(margineVerticale, margineOrizzontale,
                               margineVerticale, margineOrizzontale));

        int larghezzaRiproduzione = (int) (larghezzaFinestra * 0.42);
        int altezzaRiproduzione = (int) (altezzaFinestra * 0.32);

        larghezzaRiproduzione = Math.max(340, Math.min(larghezzaRiproduzione, 560));

        altezzaRiproduzione = Math.max(190, Math.min(altezzaRiproduzione, 300));

        pannelloRiproduzioneCompleto.setPreferredSize(new Dimension(larghezzaRiproduzione, altezzaRiproduzione));

        int dimensioneFont = Math.min(larghezzaFinestra / 65, altezzaFinestra / 42);
        dimensioneFont = Math.max(13, Math.min(dimensioneFont, 18));
        Font fontNormale = new Font("Arial", Font.PLAIN, dimensioneFont);
        Font fontTitolo = new Font("Arial", Font.BOLD, Math.min(dimensioneFont + 2, 20));

        lblTitolo.setFont(fontNormale);
        txtTitolo.setFont(fontNormale);
        btnCerca.setFont(fontNormale);
        btnVisualizza.setFont(fontNormale);
        btnAnnulla.setFont(fontNormale);
        listaRisultati.setFont(fontNormale);
        lblInRiproduzione.setFont(fontTitolo);
        lblTitoloRiproduzione.setFont(fontNormale);
        btnAggiungiPlaylist.setFont(fontNormale);
        btnIndietro.setFont(fontNormale);

        pannelloTitolo.revalidate();
        pannelloTitolo.repaint();
        pannelloPulsantiRicerca.revalidate();
        pannelloPulsantiRicerca.repaint();
        pannelloPulsantiFinali.revalidate();
        pannelloPulsantiFinali.repaint();
        pannelloPulsantiRiproduzione.revalidate();
        pannelloPulsantiRiproduzione.repaint();
        pannelloRiproduzioneCompleto.revalidate();
        pannelloRiproduzioneCompleto.repaint();
        pannelloRicerca.revalidate();
        pannelloRicerca.repaint();
        pannelloRiproduzione.revalidate();
        pannelloRiproduzione.repaint();
        pannelloPrincipale.revalidate();
        pannelloPrincipale.repaint();

    }
    private void cercaElementi() {

        String titolo = txtTitolo.getText().trim();

        if (titolo.isEmpty()) {
              JOptionPane.showMessageDialog(this, "inserisci il titolo dell'elemento", "Attenzione", JOptionPane.WARNING_MESSAGE);

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