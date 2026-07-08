package dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import dao.ElementoMultimedialeDAO;
import database.DatabaseConnection;
import entity.Audio;
import entity.ElementoMultimediale;
import entity.Video;

public class JDBCElementoDAO implements ElementoMultimedialeDAO {

    @Override

    public boolean salvaElemento(ElementoMultimediale e) {

        String query = "INSERT INTO elemento_multimediale "

                + "(id_elemento, descrizione, datacreazione, immaginecopertina, titolo, numerovisualizzazioni, "

                + "isrc, durataaudio, risoluzione, formato, duratavideo, tipoelemento) "

                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();

             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, e.getIdElemento());

            statement.setString(2, e.getDescrizione());

            statement.setDate(3, new java.sql.Date(e.getDataCreazione().getTime()));

            statement.setString(4, e.getImmagineCopertina());

            statement.setString(5, e.getTitolo());

            statement.setInt(6, e.getNumeroVisualizzazioni());

            if (e instanceof Audio) {

                Audio a = (Audio) e;

                statement.setString(7, a.getIsrc());

                statement.setDouble(8, a.getDurataAudio());

                statement.setString(9, null);

                statement.setString(10, null);

                statement.setNull(11, java.sql.Types.DOUBLE);

                statement.setString(12, "Audio");

            } else if (e instanceof Video) {

                Video v = (Video) e;

                statement.setString(7, null);

                statement.setNull(8, java.sql.Types.DOUBLE);

                statement.setString(9, v.getRisoluzione());

                statement.setString(10, v.getFormato());

                statement.setDouble(11, v.getDurataVideo());

                statement.setString(12, "Video");

            }

            int righeInserite = statement.executeUpdate();

            return righeInserite > 0;

        } catch (SQLException ex) {

            ex.printStackTrace();

            return false;

        }

    }

    @Override

    public boolean eliminaElemento(String idElemento) {

        String query = "DELETE FROM elemento_multimediale WHERE id_elemento = ?";

        try (Connection connection = DatabaseConnection.getConnection();

             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, idElemento);

            int righeEliminate = statement.executeUpdate();

            return righeEliminate > 0;

        } catch (SQLException ex) {

            ex.printStackTrace();

            return false;

        }

    }

    @Override

    public ElementoMultimediale cercaPerId(String idElemento) {

        String query = "SELECT * FROM elemento_multimediale WHERE id_elemento = ?";

        try (Connection connection = DatabaseConnection.getConnection();

             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, idElemento);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {

                return creaElementoDaResultSet(rs);

            }

        } catch (SQLException ex) {

            ex.printStackTrace();

        }

        return null;

    }

    @Override

    public boolean aggiornaElemento(ElementoMultimediale e) {

        String query = "UPDATE elemento_multimediale SET descrizione = ?, immaginecopertina = ?, titolo = ? "

                + "WHERE id_elemento = ?";

        try (Connection connection = DatabaseConnection.getConnection();

             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, e.getDescrizione());

            statement.setString(2, e.getImmagineCopertina());

            statement.setString(3, e.getTitolo());

            statement.setString(4, e.getIdElemento());

            int righeAggiornate = statement.executeUpdate();

            return righeAggiornate > 0;

        } catch (SQLException ex) {

            ex.printStackTrace();

            return false;

        }

    }

    @Override

    public ArrayList<ElementoMultimediale> cercaElementi(String titolo) {

        String query = "SELECT * FROM elemento_multimediale WHERE titolo ILIKE ?";

        ArrayList<ElementoMultimediale> lista = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();

             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, "%" + titolo + "%");

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {

                lista.add(creaElementoDaResultSet(rs));

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return lista;

    }

    private ElementoMultimediale creaElementoDaResultSet(ResultSet rs) throws SQLException {

        String tipo = rs.getString("tipoelemento");

        if (tipo.equalsIgnoreCase("Audio")) {

            return new Audio(

                    rs.getString("id_elemento"),

                    rs.getString("descrizione"),

                    rs.getDate("datacreazione"),

                    rs.getString("titolo"),
                    
                    rs.getInt("numerovisualizzazioni"),
                    
                    rs.getString("immaginecopertina"),

                    rs.getString("isrc"),

                    rs.getDouble("durataaudio")

            );

        }

        if (tipo.equalsIgnoreCase("Video")) {

            return new Video(

                    rs.getString("id_elemento"),

                    rs.getString("descrizione"),

                    rs.getDate("datacreazione"),

                    rs.getString("titolo"),
                    
                    rs.getInt("numerovisualizzazioni"),
                    
                    rs.getString("immaginecopertina"),

                    rs.getString("risoluzione"),

                    rs.getString("formato"),

                    rs.getDouble("duratavideo")

            );

        }

        return null;

    }

}