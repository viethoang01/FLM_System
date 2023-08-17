/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Admin;

import data_access.SettingDAO;
import entity.Setting;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author nguye
 */
@WebServlet(name = "AddSettingController", urlPatterns = {"/addsetting"})
public class AddSettingController extends HttpServlet {

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
//        try ( PrintWriter out = response.getWriter()) {
//            /* TODO output your page here. You may use following sample code. */
//        }
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
//        processRequest(request, response);
        String settingId = request.getParameter("sid");
        SettingDAO dao = new SettingDAO();
        if (settingId != null) {
            Setting setting = dao.getSetting(settingId);
            List<Setting> allSetting = dao.getAllSetting();
            request.setAttribute("st", setting);
            request.setAttribute("ast", allSetting);
            request.getRequestDispatcher("admin/settingdetail.jsp").forward(request, response);
        } else {
//            try ( PrintWriter out = response.getWriter()) {
//                out.println("<h1>Servlet RoomcategoryController at</h1>");
//            }
            List<Setting> allSetting = dao.getAllSetting();
            request.setAttribute("ast", allSetting);
            request.getRequestDispatcher("./admin/addsetting.jsp").forward(request, response);
        }
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
//        processRequest(request, response)
        SettingDAO std = new SettingDAO();
        String dos = request.getParameter("do");
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String type = request.getParameter("type");
        String desc = request.getParameter("desc");
        String order = request.getParameter("order");
        String status = request.getParameter("status");
        if (dos.equals("update")) {
              std.UpdateSetting(id, name, type, desc, status, order);
                     response.sendRedirect("./setting");
        } 
        if(dos.equals("add")){
             std.insertSetting(name, type, desc, status, order);
                     response.sendRedirect("./setting");
        }
        else {
            try ( PrintWriter out = response.getWriter()) {
                out.println("<h1>Ðây không  là do post "+dos+"</h1>");
            }
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
