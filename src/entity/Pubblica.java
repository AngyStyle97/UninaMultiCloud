package entity;

public class Pubblica extends Playlist {

    public Pubblica(String idPlaylist, String nomePlaylist) {
        super(idPlaylist, nomePlaylist);
    }

    @Override
    public String toString() {
        return "Playlist pubblica - " + super.toString();
    }
}