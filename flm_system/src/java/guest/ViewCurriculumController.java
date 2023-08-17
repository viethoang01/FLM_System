/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package guest;

import data_access.CurriculumDAO;
import data_access.PermissionDAO;
import entity.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author SHD
 */
public class ViewCurriculumController extends HttpServlet {

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
            out.println("<title>Servlet ViewCurriculumController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ViewCurriculumController at " + request.getContextPath() + "</h1>");
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
        User u = (User) request.getSession().getAttribute("user");
        if (u != null) {
            List<String> permissions = new PermissionDAO().getAllRolesOfUser(((User) request.getSession().getAttribute("user")).getUser_id());
            if (permissions.contains("System Admin") || permissions.contains("CRDD Staff") || permissions.contains("CRDD Head")) {
                request.setAttribute("isAllow", true);
            }
        }
        int index = 1;
        String filter = "";
        String txt = "";
        String sort = "";

        if (request.getParameter("sort") != null && !request.getParameter("sort").equals("")) {
            sort = request.getParameter("sort");
        }
        if (request.getParameter("index") != null && !request.getParameter("index").equals("")) {
            index = Integer.parseInt(request.getParameter("index"));
        }
        if (request.getParameter("filter") != null && request.getParameter("txt") != null) {
            filter = request.getParameter("filter").trim();
            txt = request.getParameter("txt").trim();
        }

        //Moi vao trang lan dau
        if (request.getParameter("filter") == null) {
            request.setAttribute("list", null);
        } //Khi bat dau search
        else {
            request.setAttribute("list", new CurriculumDAO().getAllCuriculum(sort, filter, txt, (index - 1) * 3));
        }

        request.setAttribute("totalItem", new CurriculumDAO().getTotalItem(filter, txt));
        request.setAttribute("filter", filter);
        request.setAttribute("txt", txt);
        request.setAttribute("index", index);
        request.setAttribute("sort", sort);
        request.getRequestDispatcher("./guest/curriculum.jsp").forward(request, response);
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
        processRequest(request, response);
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
