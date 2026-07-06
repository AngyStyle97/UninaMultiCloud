package entity;

import java.util.ArrayList;
import java.util.Date;

public abstract class ElementoMultimediale {

	private String idElemento;
	private String descrizione;
    private Date dataCreazione;
    private String titolo;
    private int numeroVisualizzazioni;
    private String immagineCopertina;
    private ArrayList<Playlist> playlist;
    
    public ElementoMultimediale(String idElemento, String descrizione, Date dataCreazione, String titolo, int numeroVisualizzazioni,
    		String immagineCopertina) {
    	
    	this.idElemento = idElemento;
    	this.descrizione = descrizione;
    	this.dataCreazione = dataCreazione;
    	this.titolo = titolo;
    	this.numeroVisualizzazioni = numeroVisualizzazioni;
    	this.immagineCopertina = immagineCopertina;
    	this.playlist = new ArrayList<>();
    }

	public String getIdElemento() {
		return idElemento;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public Date getDataCreazione() {
		return dataCreazione;
	}

	public String getTitolo() {
		return titolo;
	}

	public int getNumeroVisualizzazioni() {
		return numeroVisualizzazioni;
	}
	
	public ArrayList<Playlist> getPlaylist() {
	    return playlist;
	}


	public String getImmagineCopertina() {
		return immagineCopertina;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public void setImmagineCopertina(String immagineCopertina) {
		this.immagineCopertina = immagineCopertina;
	}
	
	public void addPlaylist(Playlist p) {
	    playlist.add(p);
	}

	public void remPlaylist(Playlist p) {
	    playlist.remove(p);
	}
    
	@Override
	public String toString() {
	    return "ID elemento: " + idElemento +
	           ", Titolo: " + titolo +
	           ", Descrizione: " + descrizione +
	           ", Data creazione: " + dataCreazione +
	           ", Numero visualizzazioni: " + numeroVisualizzazioni +
	           ", Immagine copertina: " + immagineCopertina;
	}
    
}
