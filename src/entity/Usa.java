package entity;

import java.util.Date;

public class Usa {
	
	private Date dataUtilizzo;
    private double tempoUtilizzo;
    private Utente utente;
    private ElementoMultimediale elemento;
    
    public Usa(Date dataUtilizzo, double tempoUtilizzo, Utente utente, ElementoMultimediale elemento) {
    	
    	this.dataUtilizzo = dataUtilizzo;
    	this.tempoUtilizzo = tempoUtilizzo;
    	this.utente = utente;
    	this.elemento = elemento;
    }

	public Date getDataUtilizzo() {
		return dataUtilizzo;
	}

	public double getTempoUtilizzo() {
		return tempoUtilizzo;
	}

	public Utente getUtente() {
		return utente;
	}

	public ElementoMultimediale getElemento() {
		return elemento;
	}

	@Override
	public String toString() {
		
	   	return "Data utilizzo: " + dataUtilizzo + " Tempo utilizzo: " + tempoUtilizzo + 
	   			" Utente: " + utente.getEmail() + " Elemento: " + elemento.getTitolo();
	}
}
