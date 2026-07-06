package entity;

import java.util.ArrayList;

public class Condivisa extends Playlist {

    private ArrayList<Utente> utenti;

    public Condivisa(String idPlaylist, String nomePlaylist) {
        super(idPlaylist, nomePlaylist);
        this.utenti = new ArrayList<>();
    }

    public ArrayList<Utente> getUtenti() {
        return utenti;
    }

    public void condividiPlaylist(Utente u) {
        utenti.add(u);
    }

    public void rimuoviUtente(Utente u) {
        utenti.remove(u);
    }

    @Override
    public String toString() {
        return "Playlist condivisa - " + super.toString() +
               ", Numero utenti condivisi: " + utenti.size();
    }
}