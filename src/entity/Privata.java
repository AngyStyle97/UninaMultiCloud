package entity;

public class Privata extends Playlist {

    public Privata(String idPlaylist, String nomePlaylist) {
        super(idPlaylist, nomePlaylist);
    }

    @Override
    public String toString() {
        return "Playlist privata - " + super.toString();
    }
}