package hospitalmanagementsystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String URL =
            "jdbc:mysql://localhost:3307/hospital_db";

    private static final String USER = "root";

    // Enter your actual MySQL password here.
    // Keep empty only if root has no password.
    private static final String PASSWORD = "";

    public static Connection getConnection() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(
                    URL,
                    USER,
                    PASSWORD
            );

            System.out.println("DATABASE CONNECTED SUCCESSFULLY!");

            return con;

        } catch (ClassNotFoundException e) {

            System.out.println("JDBC DRIVER NOT FOUND!");
            e.printStackTrace();

        } catch (SQLException e) {

            System.out.println("DATABASE CONNECTION FAILED!");
            System.out.println("ERROR: " + e.getMessage());
        }

        return null;
    }

    public static void main(String[] args) {

        try (Connection con = getConnection()) {

            if (con != null) {
                System.out.println("JDBC CONNECTION TEST SUCCESSFUL!");
                System.out.println("DATABASE CONNECTION CLOSED!");
            }

        } catch (SQLException e) {
            System.out.println("ERROR CLOSING CONNECTION: " + e.getMessage());
        }
    }
}