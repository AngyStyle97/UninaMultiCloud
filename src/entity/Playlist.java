package entity;

import java.util.ArrayList;

public abstract class Playlist {
	
	private String idPlaylist;
	private String nomePlaylist;
	private ArrayList<ElementoMultimediale> elementi;
	
	public Playlist(String idPlaylist, String nomePlaylist) {
		
		this.idPlaylist = idPlaylist;
		this.nomePlaylist = nomePlaylist;
		this.elementi = new ArrayList<>();
	}

	public String getIdPlaylist() {
		return idPlaylist;
	}

	public String getNomePlaylist() {
		return nomePlaylist;
	}
	
	public ArrayList<ElementoMultimediale> getElementi() {
	    return elementi;
	}

	public void setNomePlaylist(String nomePlaylist) {
		this.nomePlaylist = nomePlaylist;
	}
	
	public void addElemento(ElementoMultimediale e) {
		elementi.add(e);
	}
	
	public void remElemento(ElementoMultimediale e) {
		elementi.remove(e);
	}
	
	public int getNumeroElementi() {
		return elementi.size();
	}
	
	@Override
	public String toString() {
		return "ID playlist: " + idPlaylist + " Nome Playlist: " + nomePlaylist + " Numero di elementi: " + elementi.size();
	}
}
