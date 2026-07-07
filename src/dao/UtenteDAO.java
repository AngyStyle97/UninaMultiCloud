package dao;

import entity.Utente;

public interface UtenteDAO {
	
	
	boolean SalvaUtente(Utente u);
	
	Utente CercaEmail(String email);
	
	String AggiornaUtente(Utente u);
	
	boolean EliminaUtente(String email);
	
	
	
	
	

}
  
	
	
	


	 
	
