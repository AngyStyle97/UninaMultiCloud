package entity;

public class Privata extends Playlist {

    public Privata(String idPlaylist, String nomePlaylist, Utente utente) {
        super(idPlaylist, nomePlaylist, utente);
    }

    @Override
    public String getTipoPlaylist() {
    	return "privata";
     }
    
    @Override
    public String toString() {
        return "Playlist privata - " + super.toString();
    }
}