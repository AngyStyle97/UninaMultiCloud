package database;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection {

    public static Connection getConnection() {
        Properties props = new Properties();

        try {
            props.load(new FileInputStream("src/config.properties"));

            String url = props.getProperty("db.url");
            String user = props.getProperty("db.user");
            String password = props.getProperty("db.password");

            return DriverManager.getConnection(url, user, password);

        } catch (IOException | SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}