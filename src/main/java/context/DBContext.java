/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package context;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 *
 * @author PC-31
 */
public class DBContext {
    private static final String URL 
            = "jdbc:sqlserver://PC-29\\SQLEXPRESS01:1433;"
            + "databaseName=QuanLyCongViec;"
            + "encrypt=true;"
            + "trustServerCertificate=true;";

    private static final String USER = "sa";
    private static final String PASSWORD = "1234";

    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = (Connection) DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Kết nối SQL Server thành công.");
        } catch (SQLException e) {
            System.out.println("Không thể kết nối SQL Server!");
            System.out.println(e.getMessage());
        }
        return conn;
    } public static void main(String[] args) {
        getConnection();
    }

}
