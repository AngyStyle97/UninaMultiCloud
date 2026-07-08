package dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.PlaylistDAO;
import database.DatabaseConnection;
import entity.Condivisa;
import entity.Playlist;
import entity.Privata;
import entity.Pubblica;
import entity.Utente;

public class JDBCPlaylistDAO implements PlaylistDAO {

	@Override
	public boolean salvaPlaylist(Playlist p) {
		String query="INSERT INTO playlist(idPlaylist,nomePlaylist) VALUES(?,?)";
		try(Connection connection=DatabaseConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(query)) {
			
			statement.setString(1, p.getIdPlaylist());	     
			statement.setString(2, p.getNomePlaylist());
			     
			int righeInserite = statement.executeUpdate(); 
			return righeInserite > 0; 
		} catch (SQLException e) {
			e.printStackTrace(); 
		}
		return false;
	}

	@Override
	public Playlist cercaPerId(String idPlaylist) {
		String query="SELECT * FROM playlist WHERE idPlaylist=?";
		// CORRETTO: Sostituito il ';' dopo getConnection() con una virgola ',' per legare il PreparedStatement
		try(Connection connection=DatabaseConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(query)) {
				        
			statement.setString(1, idPlaylist); // Sistemato il parametro coerente con l'id
		                
			try (ResultSet rs = statement.executeQuery()) {
				if(rs.next()) {
					return creaPlaylistDaResultSet(rs); // Chiamata al metodo di supporto per restituire Playlist anziché ResultSet
				}
			}
		} catch(SQLException e) {  
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean aggiornaPlaylist(Playlist p) {
		String query="UPDATE SET idPlaylist=?,nomeplaylist=?";
		try (Connection connection = DatabaseConnection.getConnection();
				  PreparedStatement statement = connection.prepareStatement(query)) {
			
			       statement.setString(1, p.getIdPlaylist());
			       statement.setString(2, p.getNomePlaylist());
					int righeAggiornate = statement.executeUpdate();
					
		            return righeAggiornate > 0;
			  } catch (SQLException e) {
				  
				  e.printStackTrace();
				  
				  return false;
			  }
			}
		
		
		
	

	@Override
	public boolean eliminaPlaylist(String idPlaylist) {
		
		String query="DELETE FROM utente WHERE idPlaylist=?";
		try(Connection connection=DatabaseConnection.getConnection();
				  PreparedStatement statement = connection.prepareStatement(query)) {
					  statement.setString(1, idPlaylist);

			            int righeEliminate = statement.executeUpdate();

			            return righeEliminate > 0;

			        } catch (SQLException e) {

			            e.printStackTrace();

				  }
						
				
		return false;
	}

	@Override
	public ArrayList<Playlist> cercaPerUtente(String email) {
		String query="SELECT*  FROM playlist WHERE email=?";
		try(Connection connection=DatabaseConnection.getConnection();
				  PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setString(1, email); 
            
			try (ResultSet rs = statement.executeQuery()) {
				ArrayList<Playlist> lista = new ArrayList<>();
				while (rs.next()) {
				    Playlist p = creaPlaylistDaResultSet(rs);
				    lista.add(p);
				}
				return lista;
			}
			       
		}
		catch(SQLException e) {  
			e.printStackTrace();
		}
		
	
		return null;
	}

	@Override
	public ArrayList<Playlist> cercaPerTipo(String tipoPlaylist) {
		String query="SELECT*  FROM playlist WHERE email=?";
		try(Connection connection=DatabaseConnection.getConnection();
				  PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setString(1, tipoPlaylist); 
            
			try (ResultSet rs = statement.executeQuery()) {
				ArrayList<Playlist> lista = new ArrayList<>();
				while (rs.next()) {
				    Playlist p = creaPlaylistDaResultSet(rs);
				    lista.add(p);
				}
				return lista;
			}
			       
		}
		catch(SQLException e) {  
			e.printStackTrace();
		}
		
		
		return null;
	}
	
	private Playlist creaPlaylistDaResultSet(ResultSet rs) throws SQLException { //questa parte non mi convince non posso fare come utente perche playlist e definita come classe astratta
	    String id = rs.getString("idPlaylist");
	    String nome = rs.getString("nomePlaylist");
	    
	    String tipo = rs.getString("tipo"); 
	    
	    switch (tipo.toUpperCase()) {
	        case "PRIVATA":
	            return new Privata(id, nome);
	            
	        case "PUBBLICA":
	            return new Pubblica(id, nome);
	            
	        case "CONDIVISA":
	            return new Condivisa(id, nome);
	            
	        default:
	            throw new IllegalArgumentException("Tipo playlist non riconosciuto: " + tipo);
	    }
	}
    
}