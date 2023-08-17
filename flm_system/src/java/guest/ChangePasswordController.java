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

/**
 *
 * @author dell
 */
public class ChangePasswordController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String status = request.getParameter("status");

        if (status != null) {
            if (status.equals("change_success")) {
                request.setAttribute("status", "update_success");
            } else {
                request.setAttribute("status", "update_fail");
            }
        }
        request.getRequestDispatcher("guest/change-password.jsp").forward(request, response);
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
        UserDAO userDAO = new UserDAO();

        String oldPassword = request.getParameter("oldpassword");
        String newPassword = request.getParameter("newpassword");
        String user_id = request.getParameter("userid");
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            String pass = userDAO.getPassword(user.getUser_id());
            if (pass.equals(oldPassword)) {
                if (oldPassword.equals(newPassword)) {
                    response.getWriter().print("dup");
                } else {
                    boolean checkChange = userDAO.changePasswod(user.getUser_id()+"", newPassword);
                    if (checkChange) {
                        response.getWriter().print("success");
                    } else {
                        response.getWriter().print("fail");

                    }
                }
            } else {
                response.getWriter().print("fail-pass|"+pass+"|"+oldPassword+"|"+newPassword);

            }
        } else {
            response.sendRedirect("login");
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
