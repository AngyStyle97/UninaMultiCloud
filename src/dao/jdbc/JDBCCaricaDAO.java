package dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.CaricaDAO;
import database.DatabaseConnection;
import entity.Carica;
import entity.ElementoMultimediale;
import entity.Utente;

public class JDBCCaricaDAO implements CaricaDAO {

    @Override
    public boolean salvaCaricamento(Carica c) {
        String query = "INSERT INTO carica(email, id_elemento, datacaricamento, peso) VALUES (?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, c.getUtente().getEmail());
            statement.setString(2, c.getElemento().getIdElemento());
            statement.setDate(3, new java.sql.Date(c.getDataCaricamento().getTime()));
            statement.setDouble(4, c.getPeso());

            int righeInserite = statement.executeUpdate();
            return righeInserite > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public ArrayList<Carica> cercaCaricamentiUtente(String email) {
        String query = "SELECT * FROM carica WHERE email = ?";
        ArrayList<Carica> lista = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, email);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                lista.add(creaCaricaDaResultSet(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    @Override
    public ArrayList<Carica> cercaCaricamentiElemento(String idElemento) {
        String query = "SELECT * FROM carica WHERE id_elemento = ?";
        ArrayList<Carica> lista = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, idElemento);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                lista.add(creaCaricaDaResultSet(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    @Override
    public boolean eliminaCaricamento(String email, String idElemento) {
        String query = "DELETE FROM carica WHERE email = ? AND id_elemento = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, email);
            statement.setString(2, idElemento);

            int righeEliminate = statement.executeUpdate();
            return righeEliminate > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private Carica creaCaricaDaResultSet(ResultSet rs) throws SQLException {
        String email = rs.getString("email");
        String idElemento = rs.getString("id_elemento");

        JDBCUtenteDAO utenteDAO = new JDBCUtenteDAO();
        JDBCElementoDAO elementoDAO = new JDBCElementoDAO();

        Utente u = utenteDAO.cercaEmail(email);
        ElementoMultimediale e = elementoDAO.cercaPerId(idElemento);

        return new Carica(
                rs.getDate("datacaricamento"),
                rs.getDouble("peso"),
                e,
                u
        );
    }
}