package entity;

import java.util.Date;

public class Carica {

	private Date dataCaricamento;
	private double peso;
	private ElementoMultimediale elemento;
	private Utente utente;
	
	public Carica(Date dataCaricamento, double peso, ElementoMultimediale elemento, Utente utente) {
		
		this.dataCaricamento = dataCaricamento;
		this.peso = peso;
		this.elemento = elemento;
		this.utente = utente;
	}

	public Date getDataCaricamento() {
		return dataCaricamento;
	}

	public double getPeso() {
		return peso;
	}

	public ElementoMultimediale getElemento() {
		return elemento;
	}

	public Utente getUtente() {
		return utente;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}
	
	@Override
	public String toString() {
		return "Data caricamento: " + dataCaricamento + " Peso: " + peso +
				" Elemento: " + elemento.getTitolo() + " Utente: " + utente.getEmail();
	}
}
