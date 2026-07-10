package boundary;

import java.awt.BorderLayout; 
import java.awt.Font;

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
    private JButton btnEsci;

    public ReportPlaylistPage(
            Controller controller,
            int numeroPlaylist,
            int numeroCaricamenti) {

        this.controller = controller;
        this.numeroPlaylist = numeroPlaylist;
        this.numeroCaricamenti = numeroCaricamenti;

        setTitle("Report Playlist");
        setSize(650, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        /*
         * Pannello superiore contenente i dati numerici.
         */
        JPanel pannelloDati = new JPanel();
        pannelloDati.setLayout(null);
        pannelloDati.setPreferredSize(new java.awt.Dimension(650, 100));

        lblNumeroPlaylist = new JLabel(
                "Numero playlist: " + numeroPlaylist
        );
        lblNumeroPlaylist.setBounds(80, 20, 220, 30);
        lblNumeroPlaylist.setFont(new Font("Arial", Font.BOLD, 16));
        pannelloDati.add(lblNumeroPlaylist);

        lblNumeroCaricamenti = new JLabel(
                "Numero caricamenti: " + numeroCaricamenti
        );
        lblNumeroCaricamenti.setBounds(330, 20, 250, 30);
        lblNumeroCaricamenti.setFont(
                new Font("Arial", Font.BOLD, 16)
        );
        pannelloDati.add(lblNumeroCaricamenti);

        add(pannelloDati, BorderLayout.NORTH);

        /*
         * Creazione del dataset usato da JFreeChart.
         */
        DefaultCategoryDataset dataset =
                new DefaultCategoryDataset();

        dataset.addValue(
                numeroPlaylist,
                "Quantità",
                "Playlist"
        );

        dataset.addValue(
                numeroCaricamenti,
                "Quantità",
                "Caricamenti"
        );

        /*
         * Creazione del grafico a barre.
         */
        JFreeChart grafico = ChartFactory.createBarChart(
                "Report attività utente",
                "Categoria",
                "Numero",
                dataset,
                PlotOrientation.VERTICAL,
                false,
                true,
                false
        );

        /*
         * ChartPanel consente di inserire il grafico
         * all'interno della finestra Swing.
         */
        ChartPanel pannelloGrafico = new ChartPanel(grafico);
        add(pannelloGrafico, BorderLayout.CENTER);

        /*
         * Pannello inferiore con il pulsante Esci.
         */
        JPanel pannelloPulsanti = new JPanel();

        btnEsci = new JButton("Esci");
        pannelloPulsanti.add(btnEsci);

        add(pannelloPulsanti, BorderLayout.SOUTH);

        btnEsci.addActionListener(e -> dispose());
    }
}