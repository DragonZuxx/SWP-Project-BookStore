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
 * @author LENOVO
 */
public class BookDao extends DBContext {

    PreparedStatement stm;
    ResultSet rs;
    
    //Lây tất cả Books
    public ArrayList<Books> getAllBooks() {
        ArrayList<Books> books = new ArrayList<Books>();
        String sql = "SELECT * FROM Books";
        try {  
            stm = connection.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                Books book = new Books();
                book.setBookID(rs.getInt("BookID"));
                book.setTitle(rs.getString("Title"));
                book.setPublisher(rs.getString("Publisher"));
                book.setPublicationDate(rs.getString("PublicationDate"));
                book.setISBN(rs.getString("ISBN"));
                book.setPrice(rs.getString("Price"));
                book.setStock(rs.getInt("Stock"));
                book.setSoldQuantity(rs.getInt("SoldQuantity"));
                book.setDescription(rs.getString("Description"));
                book.setCoverImage(rs.getString("CoverImage"));
                book.setIsAvailable(rs.getBoolean("IsAvailable"));
                book.setIsBanned(rs.getBoolean("IsBanned"));
                book.setCreatedAt(rs.getTimestamp("CreatedAt").toLocalDateTime());
                book.setUpdatedAt(rs.getTimestamp("UpdatedAt").toLocalDateTime());
                books.add(book);
            }
        } catch (Exception e) {
            System.out.println("getAllBooks" + e.getMessage());
        }
        return books;
    }

    //Lấy sách theo ID
    public Books getBookByID(int id) {
        String sql = "SELECT * FROM Books WHERE BookID = ?";
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            rs = stm.executeQuery();
            while (rs.next()) {
                Books book = new Books();
                book.setBookID(rs.getInt("BookID"));
                book.setTitle(rs.getString("Title"));
                book.setPublisher(rs.getString("Publisher"));
                book.setPublicationDate(rs.getString("PublicationDate"));
                book.setISBN(rs.getString("ISBN"));
                book.setPrice(rs.getString("Price"));
                book.setStock(rs.getInt("Stock"));
                book.setSoldQuantity(rs.getInt("SoldQuantity"));
                book.setDescription(rs.getString("Description"));
                book.setCoverImage(rs.getString("CoverImage"));
                book.setIsAvailable(rs.getBoolean("IsAvailable"));
                book.setIsBanned(rs.getBoolean("IsBanned"));
                book.setCreatedAt(rs.getTimestamp("CreatedAt").toLocalDateTime());
                book.setUpdatedAt(rs.getTimestamp("UpdatedAt").toLocalDateTime());
                return book;
            }
        } catch (Exception e) {
            System.out.println("getBookByID" + e.getMessage());
        }
        return null;
    }


    //Tìm kiếm sách theo tên
    public ArrayList<Books> searchBookByName(String name) {
        ArrayList<Books> books = new ArrayList<Books>();
        String sql = "SELECT * FROM Books WHERE Title LIKE ?";
        try {
            stm = connection.prepareStatement(sql);
            stm.setString(1, "%" + name + "%");
            rs = stm.executeQuery();
            while (rs.next()) {
                Books book = new Books();
                book.setBookID(rs.getInt("BookID"));
                book.setTitle(rs.getString("Title"));
                book.setPublisher(rs.getString("Publisher"));
                book.setPublicationDate(rs.getString("PublicationDate"));
                book.setISBN(rs.getString("ISBN"));
                book.setPrice(rs.getString("Price"));
                book.setStock(rs.getInt("Stock"));
                book.setSoldQuantity(rs.getInt("SoldQuantity"));
                book.setDescription(rs.getString("Description"));
                book.setCoverImage(rs.getString("CoverImage"));
                book.setIsAvailable(rs.getBoolean("IsAvailable"));
                book.setIsBanned(rs.getBoolean("IsBanned"));
                book.setCreatedAt(rs.getTimestamp("CreatedAt").toLocalDateTime());
                book.setUpdatedAt(rs.getTimestamp("UpdatedAt").toLocalDateTime());
                books.add(book);
            }
        } catch (Exception e) {
            System.out.println("searchBookByName" + e.getMessage());
        }
        return books;
    }

     // Create (Add new Book)
    public void addBook(Books book) {
        String sql = "INSERT INTO Books(Title, Publisher, PublicationDate, ISBN, Price, Stock, SoldQuantity, Description, CoverImage, IsAvailable, IsBanned, CreatedAt, UpdatedAt) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            stm = connection.prepareStatement(sql);
            stm.setString(1, book.getTitle());
            stm.setString(2, book.getPublisher());
            stm.setString(3, book.getPublicationDate());
            stm.setString(4, book.getISBN());
            stm.setString(5, book.getPrice());
            stm.setInt(6, book.getStock());
            stm.setInt(7, book.getSoldQuantity());
            stm.setString(8, book.getDescription());
            stm.setString(9, book.getCoverImage());
            stm.setBoolean(10, book.getIsAvailable());
            stm.setBoolean(11, book.getIsBanned());
            stm.setTimestamp(12, java.sql.Timestamp.valueOf(book.getCreatedAt()));
            stm.setTimestamp(13, java.sql.Timestamp.valueOf(book.getUpdatedAt()));
            stm.executeUpdate();
        } catch (Exception e) {
            System.out.println("addBook" + e.getMessage());
        }
    }
    // Update (Update Book)
    public void updateBook(Books book) {
        String sql = "UPDATE Books SET Title = ?, Publisher = ?, PublicationDate = ?, ISBN = ?, Price = ?, Stock = ?, SoldQuantity = ?, Description = ?, CoverImage = ?, IsAvailable = ?, IsBanned = ?, UpdatedAt = ? WHERE BookID = ?";
        try {
            stm = connection.prepareStatement(sql);
            stm.setString(1, book.getTitle());
            stm.setString(2, book.getPublisher());
            stm.setString(3, book.getPublicationDate());
            stm.setString(4, book.getISBN());
            stm.setString(5, book.getPrice());
            stm.setInt(6, book.getStock());
            stm.setInt(7, book.getSoldQuantity());
            stm.setString(8, book.getDescription());
            stm.setString(9, book.getCoverImage());
            stm.setBoolean(10, book.getIsAvailable());
            stm.setBoolean(11, book.getIsBanned());
            stm.setTimestamp(12, java.sql.Timestamp.valueOf(book.getUpdatedAt()));
            stm.setInt(13, book.getBookID());
            stm.executeUpdate();
        } catch (Exception e) {
            System.out.println("updateBook" + e.getMessage());
        }
    }
    // Delete (Delete Book)
    public void deleteBook(int id) {
        String sql = "DELETE FROM Books WHERE BookID = ?";
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            stm.executeUpdate();
        } catch (Exception e) {
            System.out.println("deleteBook" + e.getMessage());
        }
    }
    // Read (Get Book by ISBN)
    public Books getBookByISBN(String isbn) {
        String sql = "SELECT * FROM Books WHERE ISBN = ?";
        try {
            stm = connection.prepareStatement(sql);
            stm.setString(1, isbn);
            rs = stm.executeQuery();
            while (rs.next()) {
                Books book = new Books();
                book.setBookID(rs.getInt("BookID"));
                book.setTitle(rs.getString("Title"));
                book.setPublisher(rs.getString("Publisher"));
                book.setPublicationDate(rs.getString("PublicationDate"));
                book.setISBN(rs.getString("ISBN"));
                book.setPrice(rs.getString("Price"));
                book.setStock(rs.getInt("Stock"));
                book.setSoldQuantity(rs.getInt("SoldQuantity"));
                book.setDescription(rs.getString("Description"));
                book.setCoverImage(rs.getString("CoverImage"));
                book.setIsAvailable(rs.getBoolean("IsAvailable"));
                book.setIsBanned(rs.getBoolean("IsBanned"));
                book.setCreatedAt(rs.getTimestamp("CreatedAt").toLocalDateTime());
                book.setUpdatedAt(rs.getTimestamp("UpdatedAt").toLocalDateTime());
                return book;
            }
        } catch (Exception e) {
            System.out.println("getBookByISBN" + e.getMessage());
        }
        return null;
    }


    //Lấy sách theo Books và check xem sách đó có bị banned hay không
    public Books getBookByIDAndIsBanned(int id) {
        String sql = "SELECT * FROM Books WHERE BookID = ? AND IsBanned = 0";
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            rs = stm.executeQuery();
            while (rs.next()) {
                Books book = new Books();
                book.setBookID(rs.getInt("BookID"));
                book.setTitle(rs.getString("Title"));
                book.setPublisher(rs.getString("Publisher"));
                book.setPublicationDate(rs.getString("PublicationDate"));
                book.setISBN(rs.getString("ISBN"));
                book.setPrice(rs.getString("Price"));
                book.setStock(rs.getInt("Stock"));
                book.setSoldQuantity(rs.getInt("SoldQuantity"));
                book.setDescription(rs.getString("Description"));
                book.setCoverImage(rs.getString("CoverImage"));
                book.setIsAvailable(rs.getBoolean("IsAvailable"));
                book.setIsBanned(rs.getBoolean("IsBanned"));
                book.setCreatedAt(rs.getTimestamp("CreatedAt").toLocalDateTime());
                book.setUpdatedAt(rs.getTimestamp("UpdatedAt").toLocalDateTime());
                return book;
            }
        } catch (Exception e) {
            System.out.println("getBookByIDAndIsBanned" + e.getMessage());
        }
        return null;
    }

    //Lấy danh sách của Books và check xem nó có bị banned hay không và có còn hàng không theo BookID
    public Books getBookByIDAndIsBannedAndIsAvailable(int id) {
        String sql = "SELECT * FROM Books WHERE BookID = ? AND IsBanned = 0 AND IsAvailable = 1";
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            rs = stm.executeQuery();
            while (rs.next()) {
                Books book = new Books();
                book.setBookID(rs.getInt("BookID"));
                book.setTitle(rs.getString("Title"));
                book.setPublisher(rs.getString("Publisher"));
                book.setPublicationDate(rs.getString("PublicationDate"));
                book.setISBN(rs.getString("ISBN"));
                book.setPrice(rs.getString("Price"));
                book.setStock(rs.getInt("Stock"));
                book.setSoldQuantity(rs.getInt("SoldQuantity"));
                book.setDescription(rs.getString("Description"));
                book.setCoverImage(rs.getString("CoverImage"));
                book.setIsAvailable(rs.getBoolean("IsAvailable"));
                book.setIsBanned(rs.getBoolean("IsBanned"));
                book.setCreatedAt(rs.getTimestamp("CreatedAt").toLocalDateTime());
                book.setUpdatedAt(rs.getTimestamp("UpdatedAt").toLocalDateTime());
                return book;
            }
        } catch (Exception e) {
            System.out.println("getBookByIDAndIsBannedAndIsAvailable" + e.getMessage());
        }
        return null;
    }

    //Chỉ lấy ra Publisher của Books và không trùng nhau (Distinct)
    public ArrayList<String> getDistinctPublisher() {
        ArrayList<String> publishers = new ArrayList<String>();
        String sql = "SELECT DISTINCT Publisher FROM Books";
        try {
            stm = connection.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                publishers.add(rs.getString("Publisher"));
            }
        } catch (Exception e) {
            System.out.println("getDistinctPublisher" + e.getMessage());
        }
        return publishers;
    }

    //getBookCategoriesByPublisher và check xem sách đó có bị banned hay không và có còn hàng không theo Publisher
    public ArrayList<Books> getBookByPublisher(String publisher) {
        ArrayList<Books> books = new ArrayList<Books>();
        String sql = "SELECT * FROM Books WHERE Publisher = ? AND IsBanned = 0 AND IsAvailable = 1";
        try {
            stm = connection.prepareStatement(sql);
            stm.setString(1, publisher);
            rs = stm.executeQuery();
            while (rs.next()) {
                Books book = new Books();
                book.setBookID(rs.getInt("BookID"));
                book.setTitle(rs.getString("Title"));
                book.setPublisher(rs.getString("Publisher"));
                book.setPublicationDate(rs.getString("PublicationDate"));
                book.setISBN(rs.getString("ISBN"));
                book.setPrice(rs.getString("Price"));
                book.setStock(rs.getInt("Stock"));
                book.setSoldQuantity(rs.getInt("SoldQuantity"));
                book.setDescription(rs.getString("Description"));
                book.setCoverImage(rs.getString("CoverImage"));
                book.setIsAvailable(rs.getBoolean("IsAvailable"));
                book.setIsBanned(rs.getBoolean("IsBanned"));
                book.setCreatedAt(rs.getTimestamp("CreatedAt").toLocalDateTime());
                book.setUpdatedAt(rs.getTimestamp("UpdatedAt").toLocalDateTime());
                books.add(book);
            }
        } catch (Exception e) {
            System.out.println("getBookByPublisher" + e.getMessage());
        }
        return books;
    }

    //Viết hàm Dao để làm totalPage
    public int getTotalPage(int pageSize) {
        String sql = "SELECT COUNT(*) FROM Books";
        try {
            stm = connection.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                int totalRecord = rs.getInt(1);
                int totalPage = (totalRecord % pageSize == 0) ? totalRecord / pageSize : totalRecord / pageSize + 1;
                return totalPage;
            }
        } catch (Exception e) {
            System.out.println("getTotalPage" + e.getMessage());
        }
        return 0;
    }
        
    public static void main(String[] args) {
        BookDao b = new BookDao();
        System.out.println(b.getBookByPublisher("Văn học").size());
    }

    
}
