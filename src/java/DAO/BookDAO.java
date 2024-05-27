/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DAL.DBContext;
import Models.Books;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Aplal
 */
public class BookDAO extends DBContext {

    PreparedStatement stm;//thực hiên câu lệnh sql
    ResultSet rs;//lưu trữ dữ liệu lấy về từ câu ljeenh select

    public ArrayList<Books> getBooks() {
        ArrayList<Books> data = new ArrayList<Books>();
        try {
            String sql = "select * from Books";
            stm = connection.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                Books book = new Books(rs.getInt(1),
                        rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7),
                         rs.getString(8), rs.getString(9), rs.getDate(10), rs.getDate(11));
                data.add(book);
            }
        } catch (Exception e) {
            System.out.println("getBooks" + e.getMessage());
        }
        return data;
    }
    
    
    public static void main(String[] args) {
        BookDAO a = new BookDAO();
        System.out.println(a.getBooks());
    }
}
