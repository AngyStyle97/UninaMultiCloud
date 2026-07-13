package dao;

import entity.Playlist;
import java.util.ArrayList;

public interface PlaylistDAO {
	
	boolean salvaPlaylist(Playlist p);
	Playlist cercaPerId(String idPlaylist);
	boolean aggiornaPlaylist(Playlist p);	
	boolean  eliminaPlaylist(String idPlaylist);
	ArrayList<Playlist> cercaPerUtente(String email);
	ArrayList<Playlist> cercaPerTipo (String tipoPlaylist); 
	boolean aggiungiElemento(String idPlaylist, String idElemento);
	boolean rimuoviElemento(String idPlaylist, String idElemento);
	int contaElementi(String idPlaylist);
}
