package dao.jdbc;

import java.sql.Connection; //CONNESSIONE AL DB G.B

import java.sql.PreparedStatement; //PREPARAZIONE DELLA QUERY G.B

import java.sql.ResultSet; //CONTIENE I RISULTATI DELLA QUERY G.B

import java.sql.SQLException //ERRORE G.B
;
import dao.UtenteDAO;

import entity.Utente;

public class JDBCUtenteDAO implements UtenteDAO {
	//RIFERIMENTO ALLA LIBRERIA CONNECTION
	Connection connection;
	
	
	public JDBCUtenteDAO(String connection) {
		
		
	}

	@Override
	public boolean SalvaUtente(Utente u) {
		
		String query="INSERT INTO utente(email,password,nome,cognome,dataiscrizione,datanascita)VALUES(gennarobitondo@hotmail.it,456,Gennaro,Bitondo,2026-06-11,2005-05-02,)";
		try (PreparedStatement statement = connection.prepareStatement(query)) {  // aggiunge nuovi elementi G.B
		               statement.setString(1, u.getEmail()); //ANGELO NON CAPISCO COME PRENDERMI IL TIPO DATE
		               
		                statement.setString(2, u.getPassword());
		                
		                 statement.setString(3, u.getNome());
		                 
		                  statement.setString(4, u.getCognome());
		                  
		                  
		                  
		                
		            
		                 int righeInserite = statement.executeUpdate(); //CI ACCERTIAMO CHE CI SIANO STATE RIGHE INSERITE G.B
		                      return righeInserite > 0; 
		        } catch (SQLException e) {
		            e.printStackTrace(); //STAMPA L'ERRORE
		return false;
		        }
	}

	@Override
	public Utente CercaEmail(String email) {
	    String query = "SELECT * FROM utente WHERE email = ?";
	    
	    try (PreparedStatement statement = connection.prepareStatement(query)) {  //prende l'email G.B
	        statement.setString(1, email);
	        
	        try (ResultSet resultSet = statement.executeQuery()) {
	            if (resultSet.next()) {         //i risultato viene salvato in delle variabili G.B
	                
	                String emailDb = resultSet.getString("email");
	                
	                String passwordDb = resultSet.getString("password");
	                
	                String nomeDb = resultSet.getString("nome");
	                
	                String cognomeDb = resultSet.getString("cognome");
	                
	                java.sql.Date dataIscrizioneDb = resultSet.getDate("dataiscrizione");
	                
	                java.sql.Date dataNascitaDb = resultSet.getDate("datanascita");

	                
	                Utente u = new Utente(emailDb, passwordDb, nomeDb, cognomeDb, dataIscrizioneDb, 0, dataNascitaDb); //CREA UNA NUOVA ISTANZA DI UTENTE CON I NUOVI ELEMENTI TROVATI
	                
	                return u;
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return null; // Ritorna null se l'utente non viene trovato o in caso di errore
	}
	@Override
	public String AggiornaUtente(Utente u) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean EliminaUtente(String email) {
		// TODO Auto-generated method stub
		return false;
	}
	

}
