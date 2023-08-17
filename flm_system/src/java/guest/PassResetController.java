/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package guest;

import data_access.UserDAO;
import entity.User;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author baova
 */
public class PassResetController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet PassResetController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PassResetController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("guest/passResetByE2.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String pass = request.getParameter("password");
        String re_pass = request.getParameter("repassword");
        String otp = request.getParameter("otp");

        UserDAO loginDAO = new UserDAO();
        User user = loginDAO.getUser(email);
        HttpSession session = request.getSession();

        if (email.isEmpty()) {
            request.setAttribute("error", "Please enter your email!");
            request.getRequestDispatcher("guest/passResetByE2.jsp").forward(request, response);
        }
        if (user.getUser_name() == null) {
            request.setAttribute("error", "This email is not registered for an account!");
            request.getRequestDispatcher("guest/passResetByE2.jsp").forward(request, response);
        }
        if (otp.isEmpty()) {
            request.setAttribute("error", "Please enter OTP code!");
            request.setAttribute("e", email);
            request.getRequestDispatcher("guest/passResetByE2.jsp").forward(request, response);
        }

        if (!otp.matches("\\d+")) {
            request.setAttribute("error", "OTP code consists of 6 integers!");
            request.setAttribute("e", email);
            request.getRequestDispatcher("guest/passResetByE2.jsp").forward(request, response);
        }

        int otp_code = Integer.parseInt(otp);
        int otp_cf = (Integer) session.getAttribute("otp_code");

        if (otp_cf != otp_code) {
            request.setAttribute("error", "OTP code is not correct!");
            request.setAttribute("e", email);
            request.getRequestDispatcher("guest/passResetByE2.jsp").forward(request, response);
        }

        if (pass.isEmpty()) {
            request.setAttribute("error", "Please enter password!");
            request.setAttribute("e", email);
            request.setAttribute("otp", otp);
            request.getRequestDispatcher("guest/passResetByE2.jsp").forward(request, response);
        }
        if (re_pass.isEmpty()) {
            request.setAttribute("error", "Please enter Conform password!");
            request.setAttribute("e", email);
            request.setAttribute("otp", otp);
            request.getRequestDispatcher("guest/passResetByE2.jsp").forward(request, response);
        }

        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,25}$";

        if (!pass.matches(regex)) {
            request.setAttribute("error", "The password is not in the correct format, the password needs to have "
                    + "\nat least 1 uppercase letter, 1 lowercase letter and 1 number");
            request.setAttribute("e", email);
            request.setAttribute("otp", otp);
            request.getRequestDispatcher("guest/passResetByE2.jsp").forward(request, response);
        }
        if (!pass.equals(re_pass)) {
            request.setAttribute("error", "Confirmation password needs to match the password!");
            request.setAttribute("e", email);
            request.setAttribute("otp", otp);
            request.getRequestDispatcher("guest/passResetByE2.jsp").forward(request, response);
        }

        if (otp_code == otp_cf && pass.equals(re_pass)) {
            loginDAO.resetPass(pass, email);
            session.removeAttribute("code_otp");
            request.getRequestDispatcher("guest/login.jsp").forward(request, response);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
