package entity;

public class Pubblica extends Playlist {

    public Pubblica(String idPlaylist, String nomePlaylist, Utente utente) {
        super(idPlaylist, nomePlaylist, utente);
    }
    
    @Override
    public String getTipoPlaylist() {
    	return "pubblica";
     }
    
    @Override
    public String toString() {
        return "Playlist pubblica - " + super.toString();
    }
}