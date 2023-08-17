/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package guest;

import data_access.UserDAO;
//import entity.SendMail;
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
        String email = request.getParameter("email");
        String full_name = request.getParameter("fullname");
        request.setAttribute("e", email);
        request.setAttribute("fullname", full_name);

        if (email != null) {
            int code = (int) Math.floor(((Math.random() * 899999) + 100000));
            String subject = "Reset code";
            String message = "<!DOCTYPE html>\n"
                    + "<html lang=\"en\">\n"
                    + "\n"
                    + "<head>\n"
                    + "</head>\n"
                    + "\n"
                    + "<body>\n"
                    + "    <h3 style=\"color: blue;\">Do not share this code with anyone.</h3>\n"
                    + "    <div>Your OTP code <strong>" + code + "</strong></div>\n"
                    + "    <h3 style=\"color: blue;\">Thank you!</h3>\n"
                    + "\n"
                    + "</body>\n"
                    + "\n"
                    + "</html>";
//            SendMail.send(email, subject, message, "manhphhe150568@fpt.edu.vn", "16Phm.@&");
            request.setAttribute("error", "Please check your email!");
        }
        request.getRequestDispatcher("guest/registerByEmail.jsp").forward(request, response);
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
        String full_name = request.getParameter("fullname");
        String email = request.getParameter("email");
        
        if(full_name.isEmpty()){
            request.setAttribute("error", "Please enter full name!");
            request.getRequestDispatcher("guest/registerByEmail.jsp").forward(request, response);
        }
        if(email.isEmpty()){
            request.setAttribute("error", "Please enter email!");
            request.getRequestDispatcher("guest/registerByEmail.jsp").forward(request, response);
        }
        
        UserDAO loginDAO = new UserDAO();
        User user = loginDAO.getUser(email);
        if(user.getUser_name() != null){
            request.setAttribute("error", "This email is registered!");
            request.getRequestDispatcher("guest/registerByEmail.jsp").forward(request, response);
        }

        int code = (int) Math.floor(((Math.random() * 899999) + 100000));
        String subject = "Reset code";
        String message = "<!DOCTYPE html>\n"
                + "<html lang=\"en\">\n"
                + "\n"
                + "<head>\n"
                + "</head>\n"
                + "\n"
                + "<body>\n"
                + "    <h3 style=\"color: blue;\">Do not share this code with anyone.</h3>\n"
                + "    <div>Your OTP code <strong>" + code + "</strong></div>\n"
                + "    <h3 style=\"color: blue;\">Thank you!</h3>\n"
                + "\n"
                + "</body>\n"
                + "\n"
                + "</html>";
//        SendMail.send(email, subject, message, "manhphhe150568@fpt.edu.vn", "16Phm.@&");
        
        HttpSession session = request.getSession();
        session.setAttribute("otp_code", code);
        request.setAttribute("fullname", full_name);
        request.setAttribute("email", email);
        request.setAttribute("error", "Please check your email!");
        request.getRequestDispatcher("guest/registerByE2.jsp").forward(request, response);
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
