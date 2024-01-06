package group06.entity.db;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

import java.sql.Connection;
import group06.utils.*;

// Không vi phạm SOLID

public class AIMSDB {

    private static Logger LOGGER = Utils.getLogger(Connection.class.getName());
    private static Connection connect;

    public static Connection getConnection() {
        if (connect != null)
            return connect;
        
        try {
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:src/main/assets/db/aims.db";
            connect = DriverManager.getConnection(url);
            LOGGER.info("Connected to the database successfully");
        } catch (ClassNotFoundException e) {
            LOGGER.severe("SQLite JDBC driver not found");
            e.printStackTrace();
        } catch (SQLException e) {
            LOGGER.severe("Error connecting to the database");
            e.printStackTrace();
        }

        return connect;
    }

    public static void main(String[] args) {
        Connection connection = AIMSDB.getConnection();
        System.out.println("Database connection: " + connection);
    }
}
