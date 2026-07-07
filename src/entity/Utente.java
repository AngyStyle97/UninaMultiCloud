package entity;

import java.util.Date;
import java.util.ArrayList;

public class Utente {

	private String email;
	private String nome;
	private String cognome;
	private String password;
	private Date dataIscrizione;
	private int numeroCaricamenti;
	private java.sql.Date dataNascita;
	private ArrayList<Playlist> playlist;
	
	public Utente(String email, String nome, String cognome, String password, Date dataIscrizione,
			int numeroCaricamenti, Date dataNascita) {
		
		this.email = email;
		this.nome = nome;
		this.cognome = cognome;
		this.password = password;
		this.dataIscrizione = dataIscrizione;
		this.dataNascita = (java.sql.Date) dataNascita;
		this.numeroCaricamenti = numeroCaricamenti;
		this.playlist = new ArrayList<>();
	}

	public String getEmail() {
		return email;
	}

	public String getNome() {
		return nome;
	}

	public String getCognome() {
		return cognome;
	}

	public String getPassword() {
		return password;
	}

	public Date getDataIscrizione() {
		return dataIscrizione;
	}

	public int getNumeroCaricamenti() {
		return numeroCaricamenti;
	}

	public java.sql.Date getDataNascita() {
		return dataNascita;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public void addPlaylist(Playlist p) {
		playlist.add(p);
	}
	
	public void remPlaylist(Playlist p) {
		playlist.remove(p);
	}
	
}
