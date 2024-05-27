package Controllers;

import Models.Accounts;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Properties;
import java.util.Random;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Aplal
 */
public class VerifyOtpServlet extends HttpServlet {

    private static final String USERNAME = "bookhavenshop03@gmail.com";
    private static final String PASSWORD = "dryy mszj cwmr emok";
    private static final String SMTP_HOST = "smtp.gmail.com";
    private static final String SMTP_PORT = "587";
    private static final String DISPLAY_NAME = "Book Haven Shop";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet VerifyOtpServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet VerifyOtpServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Accounts account = (Accounts) request.getSession().getAttribute("account");
        if (account == null) {
            response.sendRedirect("signinView.jsp");
            return;
        }

        String email = account.getEmail();

        String otp = generateOtp();
        request.getSession().setAttribute("otp2", otp);

        if (sendOtpEmail(email, otp)) {
            response.sendRedirect("emalForm.jsp");
        } else {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to send OTP email.");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Accounts account = (Accounts) request.getSession().getAttribute("account");
        if (account == null) {
            response.sendRedirect("signinView.jsp");
            return;
        }

        String email = request.getParameter("email");
        String email2 = account.getEmail();
        String enteredOtp = request.getParameter("otp");
        String expectedOtp = (String) request.getSession().getAttribute("otp2");

        if (email.equals(email2)) {
            if (enteredOtp.equals(expectedOtp)) {
                String mess = "OTP is valid. User authenticated successfully.";
                request.setAttribute("mess", mess);
                request.getRequestDispatcher("home").forward(request, response);
            } else {
                String mess = "Invalid OTP. Authentication failed.";
                request.setAttribute("mess", mess);
                request.getRequestDispatcher("emalForm.jsp").forward(request, response);
            }
        } else {
            response.sendRedirect("Notfound.jsp");
        }
    }

    @Override
    public String getServletInfo() {
        return "VerifyOtpServlet handles OTP verification for user authentication.";
    }

    private String generateOtp() {
        Random random = new Random();
        StringBuilder otp = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            otp.append(random.nextInt(10));
        }
        return otp.toString();
    }

    private boolean sendOtpEmail(String toEmail, String otp) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", SMTP_HOST);
        props.put("mail.smtp.port", SMTP_PORT);

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                        return new javax.mail.PasswordAuthentication(USERNAME, PASSWORD);
                    }
                });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(USERNAME, DISPLAY_NAME));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject("Your OTP");
            message.setText("Your OTP is: " + otp);
            Transport.send(message);
            return true;
        } catch (MessagingException | java.io.UnsupportedEncodingException e) {
            e.printStackTrace();
            return false;
        }
    }
}
