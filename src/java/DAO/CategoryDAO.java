/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DAL.DBContext;
import Models.Categories;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Aplal
 */
public class CategoryDAO extends DBContext{
    PreparedStatement stm;//thực hiên câu lệnh sql
    ResultSet rs;//lưu trữ dữ liệu lấy về từ câu ljeenh select
    
    public ArrayList<Categories> getCategories() {
        ArrayList<Categories> data = new ArrayList<Categories>();
        try {
            String sql = "select * from Categories";
            stm = connection.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                Categories c = new Categories(rs.getInt(1), rs.getString(2), rs.getDate(3), rs.getDate(4));
                data.add(c);
            }
        } catch (Exception e) {
            System.out.println("checkAccount" + e.getMessage());
        }
        return data;
    }
    
    public static void main(String[] args) {
        CategoryDAO a = new CategoryDAO();
        System.out.println(a.getCategories());
    }
}
