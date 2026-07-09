package dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.UsaDAO;
import database.DatabaseConnection;
import entity.ElementoMultimediale;
import entity.Usa;
import entity.Utente;

public class JDBCUsaDAO implements UsaDAO {

    @Override
    public boolean salvaUtilizzo(Usa u) {
        String query = "INSERT INTO usa(email, id_elemento, datautilizzo, tempoutilizzo) VALUES (?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, u.getUtente().getEmail());
            statement.setString(2, u.getElemento().getIdElemento());
            statement.setDate(3, new java.sql.Date(u.getDataUtilizzo().getTime()));
            statement.setDouble(4, u.getTempoUtilizzo());

            int righeInserite = statement.executeUpdate();
            return righeInserite > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public ArrayList<Usa> cercaUtilizziUtente(String email) {
        String query = "SELECT * FROM usa WHERE email = ?";
        ArrayList<Usa> lista = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, email);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                lista.add(creaUsaDaResultSet(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    @Override
    public ArrayList<Usa> cercaUtilizziElemento(String idElemento) {
        String query = "SELECT * FROM usa WHERE id_elemento = ?";
        ArrayList<Usa> lista = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, idElemento);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                lista.add(creaUsaDaResultSet(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    private Usa creaUsaDaResultSet(ResultSet rs) throws SQLException {
        String email = rs.getString("email");
        String idElemento = rs.getString("id_elemento");

        JDBCUtenteDAO utenteDAO = new JDBCUtenteDAO();
        JDBCElementoDAO elementoDAO = new JDBCElementoDAO();

        Utente u = utenteDAO.cercaEmail(email);
        ElementoMultimediale e = elementoDAO.cercaPerId(idElemento);

        return new Usa(
                rs.getDate("datautilizzo"),
                rs.getDouble("tempoutilizzo"),
                u,
                e
        );
    }
}
