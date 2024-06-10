package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import Models.Promotions;
import DAL.DBContext;

public  class PromotionDao extends DBContext{
    PreparedStatement stm;
    ResultSet rs;
    

    // Thêm mã giảm giá
    public Promotions addPromotion(Promotions promotion) {
        try {
            String sql = "INSERT INTO Promotions (Title, Description, StartDate, EndDate, DiscountPercentage) VALUES(?, ?, ?, ?, ?)";
            stm = connection.prepareStatement(sql);
            stm.setString(1, promotion.getTitle());
            stm.setString(2, promotion.getDescription());
            stm.setTimestamp(3, java.sql.Timestamp.valueOf(promotion.getStartDate()));
            stm.setTimestamp(4, java.sql.Timestamp.valueOf(promotion.getEndDate()));
            stm.setString(5, promotion.getDiscountPercentage());
            stm.executeUpdate();
        } catch (Exception e) {
            System.out.println("addPromotion: " + e.getMessage());
        }
        return null;
    }
    
    
    //Sửa mã giảm giá
    public Promotions updatePromotion(Promotions promotion) {
        try {
            String sql = "UPDATE Promotions SET Title = ?, Description = ?, StartDate = ?, EndDate = ?, DiscountPercentage = ?, UpdatedAt = ? WHERE PromotionID = ?";
            stm = connection.prepareStatement(sql);
            stm.setString(1, promotion.getTitle());
            stm.setString(2, promotion.getDescription());
            stm.setTimestamp(3, java.sql.Timestamp.valueOf(promotion.getStartDate()));
            stm.setTimestamp(4, java.sql.Timestamp.valueOf(promotion.getEndDate()));
            stm.setString(5, promotion.getDiscountPercentage());
            stm.setTimestamp(6, java.sql.Timestamp.valueOf(promotion.getUpdatedAt()));
            stm.setInt(7, promotion.getPromotionID());
            stm.executeUpdate();
        } catch (Exception e) {
            System.out.println("updatePromotion: " + e.getMessage());
        }
        return null;
    }

    //Xóa mã giảm giá theo id
    public Promotions deletePromotionById(int id) {
        try {
            String sql = "DELETE FROM Promotions WHERE PromotionID = ?";
            stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            stm.executeUpdate();
        } catch (Exception e) {
            System.out.println("deletePromotion: " + e.getMessage());
        }
        return null;
    }
    
    
    // Lấy mã giảm giá theo id
    public Promotions getPromotionByCode(int id) {
        try {
            String sql = "SELECT * FROM Promotions WHERE PromotionID = ?";
            stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            rs = stm.executeQuery();
            while (rs.next()) {
                Promotions promotion = new Promotions();
                promotion.setPromotionID(rs.getInt("PromotionID"));
                promotion.setTitle(rs.getString("Title"));
                promotion.setDescription(rs.getString("Description"));
                promotion.setStartDate(rs.getTimestamp("StartDate").toLocalDateTime());
                promotion.setEndDate(rs.getTimestamp("EndDate").toLocalDateTime());
                promotion.setDiscountPercentage(rs.getString("DiscountPercentage"));
                promotion.setCreatedAt(rs.getTimestamp("CreatedAt").toLocalDateTime());
                promotion.setUpdatedAt(rs.getTimestamp("UpdatedAt").toLocalDateTime());
                return promotion;
            }
        } catch (Exception e) {
            System.out.println("getPromotionByCode: " + e.getMessage());
        }
        return null;
    }
    //Lấy mã giảm giá theo ngày bắt đầu và kết thúc 
    public Promotions getPromotionByDate(java.time.LocalDateTime startDate, java.time.LocalDateTime endDate) {
        try {
            String sql = "SELECT * FROM Promotions WHERE StartDate = ? AND EndDate = ?";
            stm = connection.prepareStatement(sql);
            stm.setTimestamp(1, java.sql.Timestamp.valueOf(startDate));
            stm.setTimestamp(2, java.sql.Timestamp.valueOf(endDate));
            rs = stm.executeQuery();
            while (rs.next()) {
                Promotions promotion = new Promotions();
                promotion.setPromotionID(rs.getInt("PromotionID"));
                promotion.setTitle(rs.getString("Title"));
                promotion.setDescription(rs.getString("Description"));
                promotion.setStartDate(rs.getTimestamp("StartDate").toLocalDateTime());
                promotion.setEndDate(rs.getTimestamp("EndDate").toLocalDateTime());
                promotion.setDiscountPercentage(rs.getString("DiscountPercentage"));
                promotion.setCreatedAt(rs.getTimestamp("CreatedAt").toLocalDateTime());
                promotion.setUpdatedAt(rs.getTimestamp("UpdatedAt").toLocalDateTime());
                return promotion;
            }
        } catch (Exception e) {
            System.out.println("getPromotionByDate: " + e.getMessage());
        }
        return null;
    }

    //Lấy mã giảm giá vẫn còn hiệu lực

    public Promotions getPromotionValid() {
        try {
            String sql = "SELECT * FROM Promotions WHERE EndDate > ?";
            stm = connection.prepareStatement(sql);
            stm.setTimestamp(1, java.sql.Timestamp.valueOf(java.time.LocalDateTime.now()));
            rs = stm.executeQuery();
            while (rs.next()) {
                Promotions promotion = new Promotions();
                promotion.setPromotionID(rs.getInt("PromotionID"));
                promotion.setTitle(rs.getString("Title"));
                promotion.setDescription(rs.getString("Description"));
                promotion.setStartDate(rs.getTimestamp("StartDate").toLocalDateTime());
                promotion.setEndDate(rs.getTimestamp("EndDate").toLocalDateTime());
                promotion.setDiscountPercentage(rs.getString("DiscountPercentage"));
                promotion.setCreatedAt(rs.getTimestamp("CreatedAt").toLocalDateTime());
                promotion.setUpdatedAt(rs.getTimestamp("UpdatedAt").toLocalDateTime());
                return promotion;
            }
        } catch (Exception e) {
            System.out.println("getPromotionValid: " + e.getMessage());
        }
        return null;
    }

    //Test method  
    public static void main(String[] args) {
        PromotionDao dao = new PromotionDao();
        Promotions promotion = new Promotions();
        promotion.setTitle("Sale 50%");
        promotion.setDescription("Sale 50% for all books");
        promotion.setStartDate(java.time.LocalDateTime.now());
        promotion.setEndDate(java.time.LocalDateTime.now());
        promotion.setDiscountPercentage("50%");
        dao.addPromotion(promotion);
    }

}
