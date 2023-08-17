/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Admin;

import data_access.PermissionDAO;
import data_access.PoDAO;
import data_access.ReadExcel;
import data_access.SubjectDAO;
import entity.Po;
import entity.Subject;
import entity.SubjectGroup;
import entity.User;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.Path;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.apache.tomcat.util.http.fileupload.impl.FileUploadIOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.annotation.MultipartConfig;

/**
 *
 * @author dell
 */
@MultipartConfig(maxFileSize = 16177215)// upload file's size up to 16MB
@WebServlet(name = "SubjectImportController", urlPatterns = {"/SubjectImport"})
public class SubjectImportController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String status = request.getParameter("status");
        if(status!=null){
            request.setAttribute("status",status);
        }
        String dup = request.getParameter("dup");
        if(status!=null){
            request.setAttribute("dup",dup);
        }
        
        

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
        int curriculum_id = 1;
        try {
            curriculum_id = Integer.parseInt(request.getParameter("curriculum_id"));
        } catch (NumberFormatException e) {
        }

        List<SubjectGroup> subjectGroups = new SubjectDAO().getListSubjectGroupByCurri_ID(curriculum_id);
        request.setAttribute("subjectGroups", subjectGroups);
        
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
            request.setAttribute("list", new SubjectDAO().getAllSubject(sort, txt, (index - 1) * 3, curriculum_id));
        }
        request.setAttribute("totalItem", new SubjectDAO().getTotalItem(txt, curriculum_id));
        request.setAttribute("txt", txt);
        request.setAttribute("curriculum_id", curriculum_id);
        request.setAttribute("index", index);
        request.setAttribute("sort", sort);
        request.getRequestDispatcher("./system_admin/subject-list.jsp").forward(request, response);
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
//        try {
        Part file_subject = request.getPart("file");
        int curriculum_id = Integer.parseInt(request.getParameter("curriculum_id"));
        InputStream inputStream = file_subject.getInputStream();
        String mess = "";

        List<Subject> listSubject = new ArrayList<>();
        String err = ReadExcel.readExcelwithSubject(inputStream, "xlsx", listSubject, curriculum_id);
        if (listSubject.isEmpty()) {
            if (!err.equals("")) {
                response.getWriter().println("<div>" + err + "</div>");
            }
        } else {
            request.getSession().setAttribute("listSubject", listSubject);
            response.getWriter().println("<table class=\"table table-hover\">");
            response.getWriter().println("<thead>\n"
                    + "    <tr>\n"
                    + "      <th scope=\"col\" class=\"text-center\">ID</th>\n"
                    + "      <th scope=\"col\" class=\"text-center\">Code</th>\n"
                    + "      <th scope=\"col\" class=\"text-center\">Name</th>\n"
                    + "    </tr>\n"
                    + "  </thead>");
            int count = 1;
            response.getWriter().println("<tbody>");
            for (Subject subject : listSubject) {
                response.getWriter().println("  <tr>\n"
                        + "      <td>" + (count++) + "</td>\n"
                        + "      <td>" + subject.getCode() + "</td>\n"
                        + "      <td>" + subject.getName() + "</td>\n"
                        + "    </tr>\n"
                        + "  ");
            }
            response.getWriter().println("</tbody>");
            response.getWriter().write("</table>");
            if (!listSubject.isEmpty()) {
                List<SubjectGroup> subjectGroups = new SubjectDAO().getListSubjectGroupByCurri_ID(curriculum_id);
                response.getWriter().println("<div style=\"text-align: left;padding-bottom: 30px;\"><p >Choice Curriculum Group:</p> <select id=\"view_subject_id\" name=\"curriculum_subject_id\" style=\"cursor: pointer\">\n");
                for (SubjectGroup subjectGroup : subjectGroups) {
                    response.getWriter().print("<option value=\"" + subjectGroup.getSubject_group_id() + "\" selected=\"true\" \">" + subjectGroup.getName() + "</option>\n");
                }

                response.getWriter().println("   </select></div> ");
                response.getWriter().println("<button class=\"btn\" type=\"submit\"  style=\"text-decoration: none;color: #00a8ff;margin-b: 30px\">Add to list</button><br>");
            }
            if (!err.equals("")) {
                response.getWriter().println("<div>" + err + "</div>");
            }
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
