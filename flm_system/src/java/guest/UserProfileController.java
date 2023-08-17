/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package guest;

import data_access.UserDAO;
import entity.User;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.servlet.http.Part;

/**
 *
 * @author dell
 */
@MultipartConfig(maxFileSize = 16177215)// upload file's size up to 16MB
public class UserProfileController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        UserDAO userDAO = new UserDAO();

        User userCurrent = (User) session.getAttribute("user");
        if (userCurrent == null) {
            String user_id = request.getParameter("user_id");
            if (user_id != null) {
                User user = userDAO.getUser(Integer.parseInt(user_id));
                session.setAttribute("user", user);
            }
        }

        String status = request.getParameter("status");
        if (status != null) {
            request.setAttribute("status", status);
        }
        request.getRequestDispatcher("guest/user-profile.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        UserDAO userDAO = new UserDAO();
        String fullname = request.getParameter("fullname");
        String email = request.getParameter("email");
        String mobile = request.getParameter("mobile");
        String title = request.getParameter("title");
        String company = request.getParameter("company");
        String username = request.getParameter("username");
        String job = request.getParameter("job");
        String user_id = request.getParameter("user_id");

        User user = new User(0, fullname, username, email, mobile, "", "", job, title, company, "");

        String checkchangeavatar = request.getParameter("checkchangeavatar");
        // obtains the upload file part in this multipart request
        Part filePart = request.getPart("avatar");
        boolean checkUpdateAvatar = false;
        String fileName = filePart.getSubmittedFileName();
        String relativePath = "images/" + fileName;
        //update avatar
        if (checkchangeavatar != null) {
            InputStream inputStream = null;

            // obtains input stream of the upload file
            inputStream = filePart.getInputStream();

            if (user_id != null) {

                if (filePart.getSize() > 0) {
                    user.setAvatar(relativePath);
                } else {
                    user.setAvatar(null);
                }
            } else {
//                response.getWriter().print("user not exit");

            }
        }
//        response.getWriter().print(user.getAvatar() == null);

//        //end update avatar
        user.setFull_name(fullname);
        user.setUser_id(Integer.parseInt(user_id));
        user.setEmail(email);
        user.setCompany(company);
        user.setJob(job);
        user.setMobile(mobile);
        user.setTitle(title);
        user.setUser_name(username);
        User user2 = (User) request.getSession().getAttribute("user");
        if (user2.getUser_name().equals(username) && user2.getMobile().equals(mobile)) {
            boolean checkUpdate = false;
            if (filePart.getSize() > 0) {
                checkUpdate = userDAO.updateUser(user);
            } else {
                User user1 = (User) request.getSession().getAttribute("user");
                user.setAvatar(user1.getAvatar());
                checkUpdate = userDAO.updateUserNoAvatar(user);

            }

            if (checkUpdate) {

                request.getSession().setAttribute("user", user);
                response.getWriter().print("success");
            } else {
                response.getWriter().print("fail");
            }
        } else {

            if (userDAO.checkDupUsername(username)) {
                response.getWriter().print("dup_username");
            } else {
                if (userDAO.checkDupMobile(mobile)) {
                    response.getWriter().print("dup_phone");

                } else {
                    boolean checkUpdate = false;
                    if (filePart.getSize() > 0) {
                        checkUpdate = userDAO.updateUser(user);
                    } else {
                        User user1 = (User) request.getSession().getAttribute("user");
                        user.setAvatar(user1.getAvatar());
                        checkUpdate = userDAO.updateUserNoAvatar(user);

                    }

                    if (checkUpdate) {

                        request.getSession().setAttribute("user", user);
                        response.getWriter().print("success");
                    } else {
                        response.getWriter().print("fail");
                    }
                }
            }
        }
//        user.setStatus(1);
//        response.getWriter().print(user);
//        response.sendRedirect("userprofile?status=" + status + "&user_id=" + user_id);
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
