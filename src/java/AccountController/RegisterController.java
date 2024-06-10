/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package AccountController;

import DAO.AccountDao;
import Models.Accounts;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author LENOVO
 */
@WebServlet(name="RegisterController", urlPatterns={"/register"})
public class RegisterController extends HttpServlet {
   
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
            out.println("<title>Servlet RegisterController</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RegisterController at " + request.getContextPath () + "</h1>");
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
       response.sendRedirect("signupView.jsp");
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
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String fullName = request.getParameter("fullName");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");

        // Validate email format
        if (!isValidEmail(email)) {
            request.setAttribute("errorMessage", "Invalid email format. Please enter a valid email address.");
            request.getRequestDispatcher("signupView.jsp").forward(request, response);
            return;
        }
        
        // Validate password
        if (!isValidPassword(password)) {
            request.setAttribute("errorMessage", "Password must be at least 8 characters long and contain at least one uppercase letter.");
            request.getRequestDispatcher("signupView.jsp").forward(request, response);
            return;
        }

        Accounts account= new Accounts();
        account.setUsername(username);
        account.setPassword(password);
        account.setEmail(email);
        account.setFullName(fullName);
        account.setAddress(address);
        account.setPhone(phone);
        account.setRoleID(3);

        AccountDao accountdao= new AccountDao();
        if (accountdao.createUser(account)) {
            request.setAttribute("mess", "Register Successfull!");
            request.getRequestDispatcher("signinView.jsp").forward(request, response);
        } else {
            request.setAttribute("errorMessage", "Failed to register user. Please try again.");
            request.getRequestDispatcher("signupView.jsp").forward(request, response);
        }
    }

    private boolean isValidPassword(String password) {
        // Validate password: at least 8 characters with at least one uppercase letter
        return password.length() >= 8 && password.matches(".*[A-Z].*");
    }
    
    private boolean isValidEmail(String email) {
        // Regular expression for email validation
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email.matches(emailRegex);
    }
    
}
