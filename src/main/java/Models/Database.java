package Models;

import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;

public class Database {
    public static String url = "jdbc:mysql://localhost:3306/mydb";
    public static String username = "root";
    public static String password = "root";
    public static Connection dbConnection;
    private static Database myDBConnection;
    private static boolean connected;
    private Statement statement;
    private ResultSet resultSet;

    public Database() {

    }

    public static Database getInstance() {
        if (myDBConnection == null) {
            myDBConnection = new Database();
            try {
                dbConnection = DriverManager.getConnection(url, username, password);
                System.out.println("Connection to DB established");
                connected = true;
            } catch (SQLException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Failed to connect to DB");
                alert.showAndWait();
                e.getSuppressed();
                connected = false;
            }
        }

        return myDBConnection;
    }

    public static Connection getConnection() {

        return dbConnection;
    }

    public boolean isConnected() {

        return connected;
    }

    public void disconnect() {

        try {
            System.out.println("Disconnected from DB");
            if (dbConnection != null)
                dbConnection.close();
            connected = false;
            if (statement != null)
                statement.close();
            if (resultSet != null)
                resultSet.close();
        } catch (SQLException ex) {
            System.out.println("Failed to disconnect from DB!");
        }
    }
}


