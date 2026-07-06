package entity;

import java.util.Date;

public class Audio extends ElementoMultimediale {

    private String isrc;
    private double durataAudio;

    public Audio(String idElemento, String descrizione, Date dataCreazione,
                 String titolo, int numeroVisualizzazioni,
                 String immagineCopertina, String isrc, double durataAudio) {

        super(idElemento, descrizione, dataCreazione, titolo,
              numeroVisualizzazioni, immagineCopertina);

        this.isrc = isrc;
        this.durataAudio = durataAudio;
    }

    public String getIsrc() {
        return isrc;
    }

    public double getDurataAudio() {
        return durataAudio;
    }

    @Override
    public String toString() {
        return super.toString() +
               ", ISRC: " + isrc +
               ", Durata audio: " + durataAudio;
    }
}