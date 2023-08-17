/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package system_admin;

import data_access.PermissionDAO;
import data_access.SettingDAO;
import entity.Permission;
import entity.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author SHD
 */
public class PermissionSetting extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
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
            out.println("<title>Servlet PermissionSetting</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PermissionSetting at " + request.getContextPath() + "</h1>");
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
        //Check session
        User u = (User) request.getSession().getAttribute("user");
        if (request.getSession().getAttribute("user") == null || !u.getRole().equalsIgnoreCase("System Admin")) {
            request.getRequestDispatcher("./guest/access_denied.jsp").forward(request, response);
        }
        
        //Get index page
        int index = 1;
        if (request.getParameter("index") != null) {
            index = Integer.parseInt(request.getParameter("index"));
        }
        
        //If filter is all role
        String filter = "";
        if (request.getParameter("filter") == null || request.getParameter("filter").equals("all")) {
            request.setAttribute("list", new PermissionDAO().getListPermissionByPagingAndFilter("",(index - 1) * 10));           
            request.setAttribute("total", new PermissionDAO().getAllPermissionByFilter("").size());
        }else{
            filter = request.getParameter("filter");
             request.setAttribute("list", new PermissionDAO().getListPermissionByPagingAndFilter(filter,(index - 1) * 10));           
            request.setAttribute("total", new PermissionDAO().getAllPermissionByFilter(filter).size());
        }
        
        request.setAttribute("filter", request.getParameter("filter"));
        request.setAttribute("index", index);
        request.setAttribute("roles", new SettingDAO().getAllRole());
        request.getRequestDispatcher("./system_admin/permission_setting.jsp").forward(request, response);
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
        int index = Integer.parseInt(request.getParameter("index"));
        String filter = "";
        List<Permission> list = new ArrayList<>();
        if (request.getParameter("filter") == null || request.getParameter("filter").equals("all")) {
            list = new PermissionDAO().getListPermissionByPagingAndFilter("",(index - 1) * 10);           
            
        }else{
            filter = request.getParameter("filter");
             list =  new PermissionDAO().getListPermissionByPagingAndFilter(filter,(index - 1) * 10);           
            
        }
        for (Permission permission : list) {
            boolean isAccessAll = Boolean.parseBoolean(request.getParameter(permission.getRole_id()+"_"+permission.getPage_id()+"_isAccessAll"));
            boolean isRead = Boolean.parseBoolean(request.getParameter(permission.getRole_id()+"_"+permission.getPage_id()+"_isCanRead"));
            boolean isAdd = Boolean.parseBoolean(request.getParameter(permission.getRole_id()+"_"+permission.getPage_id()+"_isCanAdd"));
            boolean isEdit = Boolean.parseBoolean(request.getParameter(permission.getRole_id()+"_"+permission.getPage_id()+"_isCanEdit"));
            boolean isDelete = Boolean.parseBoolean(request.getParameter(permission.getRole_id()+"_"+permission.getPage_id()+"_isCanDelete"));
            if(!(permission.getRole_id() == 5 && permission.getPage_id() == 14))
                new PermissionDAO().updatePermission(new Permission(permission.getRole_id(),permission.getPage_id(),isAccessAll,isRead,isAdd,isEdit,isDelete,"",""));
        }
        request.setAttribute("list", new PermissionDAO().getListPermissionByPagingAndFilter("",0));           
        request.setAttribute("total", new PermissionDAO().getAllPermissionByFilter("").size());
        request.setAttribute("index", 1);
        request.setAttribute("roles", new SettingDAO().getAllRole());
        request.getRequestDispatcher("./system_admin/permission_setting.jsp").forward(request, response);

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
