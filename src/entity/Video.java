package entity;

import java.util.Date;

public class Video extends ElementoMultimediale {

    private String risoluzione;
    private String formato;
    private double durataVideo;

    public Video(String idElemento, String descrizione, Date dataCreazione,
                 String titolo, int numeroVisualizzazioni,
                 String immagineCopertina, String risoluzione,
                 String formato, double durataVideo) {

        super(idElemento, descrizione, dataCreazione, titolo,
              numeroVisualizzazioni, immagineCopertina);

        this.risoluzione = risoluzione;
        this.formato = formato;
        this.durataVideo = durataVideo;
    }

    public String getRisoluzione() {
        return risoluzione;
    }

    public String getFormato() {
        return formato;
    }

    public double getDurataVideo() {
        return durataVideo;
    }

    @Override
    public String toString() {
        return super.toString() +
               ", Risoluzione: " + risoluzione +
               ", Formato: " + formato +
               ", Durata video: " + durataVideo;
    }
}