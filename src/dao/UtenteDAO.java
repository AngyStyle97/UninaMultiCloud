package dao;

import entity.Utente;

public interface UtenteDAO {
	
	Utente login(String email, String password);
	
	boolean salvaUtente(Utente u);
	
	Utente cercaEmail(String email);
	
	String aggiornaUtente(Utente u);
	
	boolean eliminaUtente(String email);
	
}
  
	
	
	


	 
	
