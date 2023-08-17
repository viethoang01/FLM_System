/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Admin;

import data_access.PermissionDAO;
import data_access.PloDAO;
import data_access.SubjectDAO;
import entity.Plo;
import entity.PloSubject;
import entity.Subject;
import entity.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author SHD
 */
public class MappingSubject extends HttpServlet {

    
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
        response.setContentType("text/html;charset=UTF-8");
        User u = (User) request.getSession().getAttribute("user");
        if (u != null) {
            List<String> permissions = new PermissionDAO().getAllRolesOfUser(((User) request.getSession().getAttribute("user")).getUser_id());
            if (permissions.contains("System Admin") || permissions.contains("CRDD Staff") || permissions.contains("CRDD Head")) {
                request.setAttribute("isAllow", true);
            }
        }
        int id = Integer.parseInt(request.getParameter("id"));
        List<Plo> plos = new PloDAO().getPlosByCuriculumn(id);
        List<Subject> subjects = new SubjectDAO().getAllSubjectForMapping(id);
        List<PloSubject> ploSubjects = new SubjectDAO().getPloSubjectByCuriculumn(id);
        request.setAttribute("listPlo", plos);
        request.setAttribute("listSubject", subjects);
        request.setAttribute("listPloSubject", ploSubjects);
        request.setAttribute("curriculum_id", id);
        System.out.println(subjects.size());
        request.getRequestDispatcher("./system_admin/plo_subject.jsp").forward(request, response);
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
        int curId = Integer.parseInt(request.getParameter("curri_id"));
        List<String> parameterNamesList = Collections.list(request.getParameterNames());
        try {
            new SubjectDAO().deleteMapping(curId);
            for (String string : parameterNamesList) {
                if (string.contains("param")) {
                    String getPloSubject = string.split(" ")[1];
                    int subject_id = Integer.parseInt(getPloSubject.split("_")[0]);
                    int plo_id = Integer.parseInt(getPloSubject.split("_")[1]);
                    new SubjectDAO().addMapping(subject_id, plo_id,curId);
                }
            }
            response.getWriter().write("success");
        } catch (Exception e) {
            response.getWriter().write("failed");
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
