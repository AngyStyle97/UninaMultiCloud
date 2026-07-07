package dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import dao.UtenteDAO;
import database.DatabaseConnection;
import entity.Utente;

public class JDBCUtenteDAO implements UtenteDAO {
	
	
	@Override

    public Utente login(String email, String password) {

        String query = "SELECT * FROM utente WHERE email = ? AND password = ?";

        try (Connection connection = DatabaseConnection.getConnection();

             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, email);

            statement.setString(2, password);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {

                return creaUtenteDaResultSet(rs);

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return null;

    }

	@Override
	public boolean salvaUtente(Utente u) {
		
		String query = "INSERT INTO utente(email, password, nome, cognome, dataiscrizione, datanascita) VALUES(?, ?, ?, ?, ?, ?)";
		
		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(query)) {
			
		               statement.setString(1, u.getEmail());
		                statement.setString(2, u.getPassword());		                
		                 statement.setString(3, u.getNome());
		                  statement.setString(4, u.getCognome());
   		                   statement.setDate(5, new java.sql.Date(u.getDataIscrizione().getTime()));
		                    statement.setDate(6, new java.sql.Date(u.getDataNascita().getTime()));
		                    
		                 int righeInserite = statement.executeUpdate(); 
		                      return righeInserite > 0; 
		        } catch (SQLException e) {
		            e.printStackTrace(); 
		     return false;
		   }
	}

	@Override
	public Utente cercaEmail(String email) {
	    String query = "SELECT * FROM utente WHERE email = ?";
	    
	    try (Connection connection = DatabaseConnection.getConnection();
	              PreparedStatement statement = connection.prepareStatement(query)) {
	        
	                statement.setString(1,  email);
	                
	                ResultSet rs = statement.executeQuery();
	                
	                if(rs.next()) {
	                	return creaUtenteDaResultSet(rs);
	                }
	          }
	              catch(SQLException e) {  
	            	  e.printStackTrace();
	              }
	       return null;
	}
	            	  
	     
	@Override
	public boolean aggiornaUtente(Utente u) {
		
		String query = "UPDATE utente SET password = ?, nome = ?, cognome = ?, datanascita = ?  WHERE email = ?";
		
		try (Connection connection = DatabaseConnection.getConnection();
			  PreparedStatement statement = connection.prepareStatement(query)) {
				  
			statement.setString(1, u.getPassword());
			statement.setString(2, u.getNome());
			statement.setString(3, u.getCognome());
			statement.setDate(4,  new java.sql.Date(u.getDataNascita().getTime()));
			statement.setString(5, u.getEmail());
						
			int righeAggiornate = statement.executeUpdate();
			
            return righeAggiornate > 0;
	  } catch (SQLException e) {
		  
		  e.printStackTrace();
		  
		  return false;
	  }
	}

	@Override

    public boolean eliminaUtente(String email) {

        String query = "DELETE FROM utente WHERE email = ?";

        try (Connection connection = DatabaseConnection.getConnection();

             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, email);

            int righeEliminate = statement.executeUpdate();

            return righeEliminate > 0;

        } catch (SQLException e) {

            e.printStackTrace();

            return false;

        }

    }

    private Utente creaUtenteDaResultSet(ResultSet rs) throws SQLException {

        return new Utente(

                rs.getString("email"),
                rs.getString("nome"),
                rs.getString("cognome"),                
                rs.getString("password"),
                rs.getDate("dataiscrizione"),
                rs.getInt("numerocaricamenti"),
                rs.getDate("datanascita"));

    }

}