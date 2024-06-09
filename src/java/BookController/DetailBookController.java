/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package BookController;

import DAO.AuthorDao;
import DAO.BookAuthorDao;
import DAO.BookCategorieDao;
import DAO.BookDao;
import DAO.CategoryDao;
import DAO.PromotionDao;
import DAO.ReviewDao;
import Models.Authors;
import Models.BookAuthors;
import Models.BookCategories;
import Models.Books;
import Models.Categories;
import Models.Promotions;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Aplal
 */
@WebServlet(name="DetailBookController", urlPatterns={"/detailbook"})
public class DetailBookController extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet DetailBookController</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DetailBookController at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String bookIDStr = request.getParameter("id");
        int bookID = Integer.parseInt(bookIDStr);

        // Khởi tạo các DAO
        BookDao bd = new BookDao();
        AuthorDao ad = new AuthorDao();
        CategoryDao cd = new CategoryDao();
        BookAuthorDao bad = new BookAuthorDao();
        BookCategorieDao bcd = new BookCategorieDao();
        ReviewDao rd = new ReviewDao();
        PromotionDao pd = new PromotionDao();

        // Truy xuất chi tiết sách
        Books product = bd.getBookByID(bookID);
        
        // Truy xuất tác giả 
        BookAuthors ba = bad.getBookAuthorById(bookID);
        // Truy xuất danh mục
        BookCategories bc = bcd.getBookCategoriesByBookID(bookID);
        // Truy xuất tác giả và danh mục
        int auid = ba != null ? ba.getAuthorID() : -1;
        int cateID = bc != null ? bc.getCategoryID() : -1;
        //Lấy ra list tác giả
        ArrayList<Authors> author = ad.getAuthorsByBookId(auid);
        //Lấy ra category
        Categories category = cd.getCategoryByID(cateID);
        // Truy xuất khuyến mãi
        Promotions promotion = pd.getPromotionByCode(bookID);
        // Truy xuất đánh giá
        //ArrayList<Reviews> review = rd.getReviewByBookId(bookID);
        int countReview = rd.countReviewByBookId(bookID);

        // Truy xuất các danh mục liên quan và sách
        ArrayList<BookCategories> bookCategories = bcd.getBookCategoriesByCategoryID(cateID);
        List<Books> bookgetbyid = new ArrayList<>();
        for (int i = 0; i < bookCategories.size(); i++) {
            BookCategories bcate = bookCategories.get(i);
            Books bookbyid = bd.getBookByID(bcate.getBookID());
            bookgetbyid.add(bookbyid);
        }

        // Đặt các thuộc tính và chuyển tiếp tới view
        request.setAttribute("bookgetbyid", bookgetbyid);
        request.setAttribute("product", product);
        request.setAttribute("author", author);
        request.setAttribute("promotion", promotion);
        //request.setAttribute("review", review);
        request.setAttribute("category", category);
        request.setAttribute("countReview", countReview);
        request.getRequestDispatcher("productView.jsp").forward(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
