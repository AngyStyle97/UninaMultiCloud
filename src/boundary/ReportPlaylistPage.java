package boundary;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import control.Controller;

public class ReportPlaylistPage extends JFrame {

    private Controller controller;
    private int numeroPlaylist;
    private int numeroCaricamenti;
    private JLabel lblNumeroPlaylist;
    private JLabel lblNumeroCaricamenti;
    private JButton btnIndietro;
    private DefaultCategoryDataset dataset;

    public ReportPlaylistPage(Controller controller, int numeroPlaylist, int numeroCaricamenti) {

        this.controller = controller;
        this.numeroPlaylist = numeroPlaylist;
        this.numeroCaricamenti = numeroCaricamenti;

        setTitle("Report Playlist");
        setSize(650, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel pannelloDati = new JPanel(new GridLayout(1, 2, 20, 0));
        lblNumeroPlaylist = new JLabel("Numero playlist: " + numeroPlaylist, JLabel.CENTER);
        lblNumeroPlaylist.setFont(new Font("Arial", Font.BOLD, 16));
        lblNumeroCaricamenti = new JLabel("Numero caricamenti: " + numeroCaricamenti, JLabel.CENTER);
        lblNumeroCaricamenti.setFont(new Font("Arial", Font.BOLD, 16));

        pannelloDati.add(lblNumeroPlaylist);
        pannelloDati.add(lblNumeroCaricamenti);

        add(pannelloDati, BorderLayout.NORTH);

        dataset = new DefaultCategoryDataset();     
        dataset.addValue(numeroPlaylist,"Quantità", "Playlist");
        dataset.addValue( numeroCaricamenti, "Quantità", "Caricamenti");

        JFreeChart grafico = ChartFactory.createBarChart("Report attività utente", "Categoria", "Numero",
                             dataset, PlotOrientation.VERTICAL, false, true, false);

        ChartPanel pannelloGrafico = new ChartPanel(grafico);
        add(pannelloGrafico, BorderLayout.CENTER);

        JPanel pannelloPulsanti = new JPanel();

        btnIndietro = new JButton("Indietro");
        pannelloPulsanti.add(btnIndietro);

        add(pannelloPulsanti, BorderLayout.SOUTH);

        btnIndietro.addActionListener(e ->
                controller.tornaAlProfiloDalReport()
        );
    }

    public void aggiornaReport(int numeroPlaylist, int numeroCaricamenti) {

        this.numeroPlaylist = numeroPlaylist;
        this.numeroCaricamenti = numeroCaricamenti;

        lblNumeroPlaylist.setText("Numero playlist: " + numeroPlaylist);
        lblNumeroCaricamenti.setText("Numero caricamenti: " + numeroCaricamenti);

        dataset.clear();
        dataset.addValue(numeroPlaylist,"Quantità", "Playlist");
        dataset.addValue(numeroCaricamenti, "Quantità", "Caricamenti");

        revalidate();
        repaint();
    }
}