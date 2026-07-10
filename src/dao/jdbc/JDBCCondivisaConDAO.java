package dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import dao.CondivisaConDAO;
import database.DatabaseConnection;

public class JDBCCondivisaConDAO
        implements CondivisaConDAO {

    @Override
    public boolean salvaCondivisione(
            String email,
            String idPlaylist) {

        String query =
                "INSERT INTO condivisa_con(email, id_playlist) "
              + "VALUES (?, ?)";

        try (Connection connection =
                     DatabaseConnection.getConnection();

             PreparedStatement statement =
                     connection.prepareStatement(query)) {

            statement.setString(1, email);
            statement.setString(2, idPlaylist);

            int righeInserite =
                    statement.executeUpdate();

            return righeInserite > 0;

        } catch (SQLException e) {

            e.printStackTrace();
            return false;
        }
    }
}