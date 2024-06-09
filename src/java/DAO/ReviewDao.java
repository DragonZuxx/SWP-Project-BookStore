/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import DAL.DBContext;
import Models.Reviews;

/**
 *
 * @author Aplal
 */
public class ReviewDao extends DBContext{
    PreparedStatement stm;
    ResultSet rs;

    // Thêm review theo tài khoản và sách
    public Reviews addReview(int userId, int bookId, String comment) {
        String sql = "INSERT INTO Reviews (UserID, BookID, Comment) VALUES(?, ?, ?)";
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, userId);
            stm.setInt(2, bookId);
            stm.setString(3, comment);
            stm.executeUpdate();
        } catch (Exception e) {
            System.out.println("addReview: " + e.getMessage());
        }
        return null;
    }

    // Lấy ra danh sách review của sách theo BookID
    public ArrayList<Reviews> getReviewByBookId(int id) {
        ArrayList<Reviews> reviews = new ArrayList<>();
        String sql = "SELECT * FROM Reviews WHERE BookID = ?";
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            rs = stm.executeQuery();
            while (rs.next()) {
                Reviews review = new Reviews();
                review.setUserID(rs.getInt("UserID"));
                review.setBookID(rs.getInt("BookID"));
                review.setComment(rs.getString("Comment"));
                review.setReviewDate(rs.getTimestamp("ReviewDate").toLocalDateTime());
                review.setCreatedAt(rs.getTimestamp("CreatedAt").toLocalDateTime());
                review.setUpdatedAt(rs.getTimestamp("UpdatedAt").toLocalDateTime());
                reviews.add(review);
            }
        } catch (Exception e) {
            System.out.println("getReviewByBookId: " + e.getMessage());
        }
        return reviews;
    }
    
    //Đếm số lượng review của sách theo BookID
    public int countReviewByBookId(int id) {
        String sql = "SELECT COUNT(*) FROM Reviews WHERE BookID = ?";
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            rs = stm.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("countReviewByBookId: " + e.getMessage());
        }
        return 0;
    }
    //Lấy review của sách theo tài khoản
    public Reviews getReviewByUserId(int id) {
        String sql = "SELECT * FROM Reviews WHERE UserID = ?";
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            rs = stm.executeQuery();
            while (rs.next()) {
                Reviews review = new Reviews();
                review.setUserID(rs.getInt("UserID"));
                review.setBookID(rs.getInt("BookID"));
                review.setComment(rs.getString("Comment"));
                review.setReviewDate(rs.getTimestamp("ReviewDate").toLocalDateTime());
                review.setCreatedAt(rs.getTimestamp("CreatedAt").toLocalDateTime());
                review.setUpdatedAt(rs.getTimestamp("UpdatedAt").toLocalDateTime());
                return review;
            }
        } catch (Exception e) {
            System.out.println("getReviewByUserId: " + e.getMessage());
        }
        return null;
    }
    
    //Test method
    public static void main(String[] args) {
        ReviewDao dao = new ReviewDao();
        System.out.println(dao.getReviewByBookId(1));
    }
}
