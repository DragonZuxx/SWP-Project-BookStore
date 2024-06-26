/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import Models.Wishlist;
import DAL.DBContext;

/**
 *
 * @author Aplal
 */
public class WishlistDao extends DBContext {
    PreparedStatement stm;
    ResultSet rs;


    // Thêm sản phẩm vào danh sách yêu thích
    public Wishlist addWishlist(int userId, int bookId) {
        String sql = "INSERT INTO Wishlist VALUES(?, ?)";
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, userId);
            stm.setInt(2, bookId);
            stm.executeUpdate();
        } catch (Exception e) {
            System.out.println("addWishlist: " + e.getMessage());
        }
        return null;
    }
    // Lấy danh sách sản phẩm yêu thích của người dùng theo id
    public Wishlist getWishlistByUserId(int id) {
        String sql = "SELECT * FROM Wishlist WHERE UserID = ?";
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            rs = stm.executeQuery();
            while (rs.next()) {
                Wishlist wishlist = new Wishlist();
                wishlist.setUserID(rs.getInt("UserID"));
                wishlist.setBookID(rs.getInt("BookID"));
                wishlist.setCreatedAt(rs.getTimestamp("CreatedAt").toLocalDateTime());
                wishlist.setUpdatedAt(rs.getTimestamp("UpdatedAt").toLocalDateTime());
                return wishlist;
            }
        } catch (Exception e) {
            System.out.println("getWishlistByUserId: " + e.getMessage());
        }
        return null;
    }
    
    //Lấy ra danh sanh sách yêu thích của người dùng theo UserID
    public Wishlist getWishlistByUserIdAndBookId(int userId, int bookId) {
        String sql = "SELECT * FROM Wishlist WHERE UserID = ? AND BookID = ?";
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, userId);
            stm.setInt(2, bookId);
            rs = stm.executeQuery();
            while (rs.next()) {
                Wishlist wishlist = new Wishlist();
                wishlist.setUserID(rs.getInt("UserID"));
                wishlist.setBookID(rs.getInt("BookID"));
                wishlist.setCreatedAt(rs.getTimestamp("CreatedAt").toLocalDateTime());
                wishlist.setUpdatedAt(rs.getTimestamp("UpdatedAt").toLocalDateTime());
                return wishlist;
            }
        } catch (Exception e) {
            System.out.println("getWishlistByUserIdAndBookId: " + e.getMessage());
        }
        return null;
    }

    //Test method
    public static void main(String[] args) {
        WishlistDao dao = new WishlistDao();
        dao.addWishlist(1, 1);
    }

}
