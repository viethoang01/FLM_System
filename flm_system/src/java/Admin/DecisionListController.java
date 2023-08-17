/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Admin;

import data_access.DecisionDAO;
import data_access.PermissionDAO;
import data_access.PloDAO;
import entity.User;
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
 * @author dell
 */
@WebServlet(name = "DecisionListController", urlPatterns = {"/decision-list"})
public class DecisionListController extends HttpServlet {

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
            out.println("<title>Servlet DecisionListController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DecisionListController at " + request.getContextPath() + "</h1>");
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
        response.setContentType("text/html;charset=UTF-8");
         User u = (User) request.getSession().getAttribute("user");
        if (u != null) {
            List<String> permissions = new PermissionDAO().getAllRolesOfUser(((User) request.getSession().getAttribute("user")).getUser_id());
            if (permissions.contains("System Admin") || permissions.contains("CRDD Staff") || permissions.contains("CRDD Head")) {
                request.setAttribute("isAllow", true);
            }
        }
        
        int index = 1;
        String txt = "";
        String sort = "";
        

        if (request.getParameter("sort") != null && !request.getParameter("sort").equals("")) {
            sort = request.getParameter("sort");
        }
        if (request.getParameter("index") != null && !request.getParameter("index").equals("")) {
            index = Integer.parseInt(request.getParameter("index"));
        }
        if (request.getParameter("txt") != null) {
            txt = request.getParameter("txt").trim();
        }
        
        
        //Moi vao trang lan dau
        if (request.getParameter("txt") == null && request.getParameter("index") == null) {
            request.setAttribute("list", null);
        } //Khi bat dau search
        else {
            request.setAttribute("list", new DecisionDAO().getAllDecision(sort, txt, (index - 1) * 3));
            response.getWriter().print(new DecisionDAO().getAllDecision(sort, txt, (index - 1) * 3));
        }
        request.setAttribute("totalItem", new DecisionDAO().getTotalItem(txt));
                    response.getWriter().print(new DecisionDAO().getTotalItem(txt));

        request.setAttribute("txt", txt);
        request.setAttribute("index", index);
        request.setAttribute("sort", sort);
        request.getRequestDispatcher("./system_admin/decision-list.jsp").forward(request, response);
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
