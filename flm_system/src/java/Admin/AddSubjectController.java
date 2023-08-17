/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Admin;

import data_access.SubjectDAO;
import entity.Subject;
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
@WebServlet(name = "AddSubjectController", urlPatterns = {"/AddSubject"})
public class AddSubjectController extends HttpServlet {

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
        SubjectDAO subjectDAO = new SubjectDAO();
String dup = "";
        boolean checkAdd = false;
        String type = request.getParameter("type");
        String curriculum = request.getParameter("curriculum");
        String curriculum_subject_id = request.getParameter("curriculum_subject_id");
        if (type.equals("m")) {
            // add nhieu subject
            List<Subject> importSubjects = (List<Subject>) request.getSession().getAttribute("listSubject");
            int maxIDSubject = subjectDAO.getMaxSub_ID();
            for (Subject importSubject : importSubjects) {
//response.getWriter().print(++maxIDSubject+" idst, crr: "+curriculum+",csi: "+curriculum_subject_id);
                if (subjectDAO.checkDup(importSubject)) {
                    checkAdd = subjectDAO.insertSubject(importSubject);
                    if (checkAdd) {
                        subjectDAO.addCurriculumSubject(curriculum, ++maxIDSubject + "");

                    }
                } else {
                    checkAdd = false;

                }
            }

        } else if (type.equals("1")) {
            //add 1 subject
            String code = request.getParameter("code");
            String name = request.getParameter("name");
            int maxIDSubject = subjectDAO.getMaxSub_ID();

            Subject subject = new Subject();
            try {
                subject.setCode(code);
                subject.setName(name);
               
                if (subjectDAO.checkDup(subject)) {
                    checkAdd = subjectDAO.insertSubject(subject);
                    if(checkAdd){
                        subjectDAO.addCurriculumSubject(Integer.parseInt(curriculum)+"", ++maxIDSubject+"");
                    }
                    dup = "false";
                } else {
                    checkAdd = false;
                    dup = "true";
                }
            } catch (Exception e) {
                response.getWriter().print(curriculum+" cur");
                checkAdd = false;
            }

        }
        response.sendRedirect("SubjectImport?status=" + checkAdd+"&dup="+dup);

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
        processRequest(request, response);
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
