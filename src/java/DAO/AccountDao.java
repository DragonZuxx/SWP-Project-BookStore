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
 * @author Aplal
 */
public class AccountDao extends DBContext{

    public Accounts getLogin(String email, String pass) {
        String sql = "select * from Users where Email= ? and Password =?";
        try {
            PreparedStatement str = connection.prepareStatement(sql);
            str.setString(1, email);
            str.setString(2, pass);
            ResultSet rs = str.executeQuery();
            if (rs.next()) {
                Accounts a = new Accounts(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getDate(9),
                        rs.getDate(10));
                return a;
            }
        } catch (SQLException e) {
            System.out.println("getLogin" + e.getMessage());
        }
        return null;
    }
}