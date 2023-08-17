/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package guest;

import data_access.CurriculumDAO;
import entity.Curiculum;
import entity.User;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author SHD
 */
public class AddCurriculum extends HttpServlet {

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
            out.println("<title>Servlet AddCurriculum</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddCurriculum at " + request.getContextPath() + "</h1>");
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
        if (u == null) {
            request.getRequestDispatcher("./guest/access_denied.jsp").forward(request, response);
        }
        request.setAttribute("list", new CurriculumDAO().getDecisions());
        request.getRequestDispatcher("./guest/add_curriculum.jsp").forward(request, response);
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
        User u = (User) request.getSession().getAttribute("user");

        //Validate exist code
        boolean validate = true;
        if (new CurriculumDAO().getAllCode().contains(request.getParameter("code"))) {
            request.setAttribute("msgCode", "This code already exist");
            validate = false;
        }
        if (request.getParameter("code").length() > 255) {
            request.setAttribute("msgCode", "This code must not over 255");
            validate = false;
        }
        if (request.getParameter("name").length() > 255) {
            request.setAttribute("msgName", "This name must not over 255");
            validate = false;
        }
        if (validate == false) {
            request.setAttribute("code", request.getParameter("code"));
            request.setAttribute("list", new CurriculumDAO().getDecisions());
            request.setAttribute("name", request.getParameter("name"));
            request.setAttribute("description", request.getParameter("description"));
            request.setAttribute("total", request.getParameter("total"));
            request.setAttribute("decision", request.getParameter("decision"));
            request.setAttribute("status", request.getParameter("status"));
            request.getRequestDispatcher("./guest/add_curriculum.jsp").forward(request, response);
        }
        else{
            String code = request.getParameter("code");
            int decision_id = Integer.parseInt(request.getParameter("decision"));
            String name = request.getParameter("name");
            String total = request.getParameter("total");
            boolean status = Boolean.parseBoolean(request.getParameter("status"));
            String description = request.getParameter("description");
            new CurriculumDAO().add(new Curiculum(-1, code, name, description, total, decision_id, status), u.getUser_id());
            response.sendRedirect("curriculum?filter=code&txt=" + code);
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
