/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DAL.DBContext;
import Models.Accounts;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author LENOVO
 */
public class AccountDao extends DBContext {

    PreparedStatement stm;
    ResultSet rs;
    
    
   //Lấy thông tin người dùng bằng Email, Password
    public Accounts getLogin(String email, String pass) {
        String sql = "SELECT * FROM Users WHERE Email = ? AND Password = ?";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setString(1, email);
            stm.setString(2, pass);
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    Accounts account = new Accounts();
                    account.setUserID(rs.getInt("UserID"));
                    account.setUsername(rs.getString("Username"));
                    account.setPassword(rs.getString("Password"));
                    account.setEmail(rs.getString("Email"));
                    account.setFullName(rs.getString("FullName"));
                    account.setAddress(rs.getString("Address"));
                    account.setPhone(rs.getString("Phone"));
                    account.setRoleID(rs.getInt("RoleID"));
                    account.setCreatedAt(rs.getTimestamp("CreatedAt").toLocalDateTime());
                    account.setUpdatedAt(rs.getTimestamp("UpdatedAt").toLocalDateTime());
                    return account;
                }
            }
        } catch (SQLException e) {
            System.out.println("getLogin: " + e.getMessage());
        }
        return null;
    }


    //Tạo tài khoản mới
    public boolean createUser(Accounts account) {
        String query = "INSERT INTO Users (Username, Password, Email, FullName, Address, Phone, RoleID) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement str = connection.prepareStatement(query);
            str.setString(1, account.getUsername());
            str.setString(2, account.getPassword());
            str.setString(3, account.getEmail());
            str.setString(4, account.getFullName());
            str.setString(5, account.getAddress());
            str.setString(6, account.getPhone());
            str.setInt(7, account.getRoleID());
            int result = str.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            System.out.println("createUser" + e.getMessage());
        }
        return false;
    }

    public static void main(String[] args) {
       AccountDao a = new  AccountDao();
        System.out.println(a.getLogin("longvu13062003@gmail.com", "Zux1306@"));
    }
}
