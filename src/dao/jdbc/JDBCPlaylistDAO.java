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
		String query="INSERT INTO playlist(id_playlist, nomeplaylist, email, tipoplaylist) VALUES(?, ?, ?, ?)";
		
		try(Connection connection = DatabaseConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(query)) {
			
			statement.setString(1, p.getIdPlaylist());	     
			statement.setString(2, p.getNomePlaylist());
			statement.setString(3, p.getUtente().getEmail());
			statement.setString(4, p.getTipoPlaylist());
			     
			int righeInserite = statement.executeUpdate(); 
			return righeInserite > 0; 
			
		} catch (SQLException e) {
			e.printStackTrace(); 
			return false;
		}
	}

	@Override
	public Playlist cercaPerId(String idPlaylist) {
		String query="SELECT * FROM playlist WHERE id_playlist = ?";
		
		try(Connection connection = DatabaseConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(query)) {
				        
			statement.setString(1, idPlaylist);
		                
			ResultSet rs = statement.executeQuery();
			
				if(rs.next()) {
					return creaPlaylistDaResultSet(rs);
				}
			
		} catch(SQLException e) {  
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean aggiornaPlaylist(Playlist p) {
		String query="UPDATE playlist SET nomeplaylist = ?, tipoplaylist = ? WHERE id_playlist = ?";
		
		try (Connection connection = DatabaseConnection.getConnection();
				  PreparedStatement statement = connection.prepareStatement(query)) {
			
			       statement.setString(1, p.getNomePlaylist());
			       statement.setString(2, p.getTipoPlaylist());
			       statement.setString(3, p.getIdPlaylist());
			       
					int righeAggiornate = statement.executeUpdate();
		            return righeAggiornate > 0;
		            
			  } catch (SQLException e) {
				  e.printStackTrace();				  
				  return false;
			  }
			}		
	
	@Override
	public boolean eliminaPlaylist(String idPlaylist) {
		
		String query="DELETE FROM playlist WHERE id_playlist = ?";
		try(Connection connection = DatabaseConnection.getConnection();
				  PreparedStatement statement = connection.prepareStatement(query)) {
			
					  statement.setString(1, idPlaylist);
					  
			            int righeEliminate = statement.executeUpdate();
			            return righeEliminate > 0;

			        } catch (SQLException e) {
			            e.printStackTrace();
			        	return false;
				  }
	}

	@Override
	public ArrayList<Playlist> cercaPerUtente(String email) {
		String query="SELECT * FROM playlist WHERE email = ?";
		ArrayList<Playlist> lista = new ArrayList<>();
		
		try(Connection connection = DatabaseConnection.getConnection();
				  PreparedStatement statement = connection.prepareStatement(query)) {
			
			statement.setString(1, email); 
            ResultSet rs = statement.executeQuery();
            
				while (rs.next()) {
				  lista.add(creaPlaylistDaResultSet(rs));
				}		       
		}
		catch(SQLException e) {  
			e.printStackTrace();
		}	
		return lista;
	}

	@Override
	public ArrayList<Playlist> cercaPerTipo(String tipoPlaylist) {
		String query="SELECT * FROM playlist WHERE tipoplaylist = ?";
		ArrayList<Playlist> lista = new ArrayList<>();
		
		try(Connection connection = DatabaseConnection.getConnection();
				  PreparedStatement statement = connection.prepareStatement(query)) {
			
			statement.setString(1, tipoPlaylist); 
            
			ResultSet rs = statement.executeQuery();
			
				while (rs.next()) {
				   lista.add(creaPlaylistDaResultSet(rs));
				}			       
		}
		catch(SQLException e) {  
			e.printStackTrace();
		}
		
		
		return lista;
	}
	
	 @Override
	    public boolean aggiungiElemento(String idPlaylist, String idElemento) {
	        String query = "INSERT INTO ha(id_playlist, id_elemento) VALUES (?, ?)";

	        try (Connection connection = DatabaseConnection.getConnection();
	             PreparedStatement statement = connection.prepareStatement(query)) {

	            statement.setString(1, idPlaylist);
	            statement.setString(2, idElemento);

	            int righeInserite = statement.executeUpdate();
	            return righeInserite > 0;

	        } catch (SQLException e) {
	            e.printStackTrace();
	            return false;
	        }
	    }

	    @Override
	    public boolean rimuoviElemento(String idPlaylist, String idElemento) {
	        String query = "DELETE FROM ha WHERE id_playlist = ? AND id_elemento = ?";

	        try (Connection connection = DatabaseConnection.getConnection();
	             PreparedStatement statement = connection.prepareStatement(query)) {

	            statement.setString(1, idPlaylist);
	            statement.setString(2, idElemento);

	            int righeEliminate = statement.executeUpdate();
	            return righeEliminate > 0;

	        } catch (SQLException e) {
	            e.printStackTrace();
	            return false;
	        }
	    }
	
	private Playlist creaPlaylistDaResultSet(ResultSet rs) throws SQLException {
	    String id = rs.getString("id_playlist");
	    String nome = rs.getString("nomeplaylist");
	    String tipo = rs.getString("tipoplaylist");
	    String email = rs.getString("email");
	    
	    JDBCUtenteDAO utenteDAO = new JDBCUtenteDAO();
	    Utente u = new Utente(email, null, null, null, null, 0, null);
	    
	    switch (tipo.toUpperCase()) {
	        case "PRIVATA":
	            return new Privata(id, nome, u);
	            
	        case "PUBBLICA":
	            return new Pubblica(id, nome, u);
	            
	        case "CONDIVISA":
	            return new Condivisa(id, nome, u);
	            
	        default:
	            throw new IllegalArgumentException("Tipo playlist non riconosciuto: " + tipo);
	    }
	}
    
}