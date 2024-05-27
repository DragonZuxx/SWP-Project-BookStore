/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

/**
 *
 * @author Aplal
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBContext {

    protected Connection connection;

    public DBContext() {
        try {
// Edit URL , username, password to authenticate with your MS SQL Server
            String url = "jdbc:sqlserver://localhost:1433;databaseName=BookStoreDB_Test";
            String username = "sa";
            String password = "123";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex);
        }
    }

    

        public static void main(String[] args) {
            DBContext dbContext = new DBContext();

            try {
                if (dbContext.connection != null && !dbContext.connection.isClosed()) {
                    System.out.println("Connected to the database.");
                } else {
                    System.out.println("Failed to connect to the database.");
                }
            } catch (SQLException ex) {
                System.out.println("Exception while checking connection: " + ex.getMessage());
            } finally {
                try {
                    if (dbContext.connection != null) {
                        dbContext.connection.close();
                    }
                } catch (SQLException ex) {
                    System.out.println("Exception while closing connection: " + ex.getMessage());
                }
            }
        }
    

}
