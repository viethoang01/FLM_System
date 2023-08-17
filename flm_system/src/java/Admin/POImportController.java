/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Admin;

import data_access.ReadExcel;
import data_access.SubjectDAO;
import entity.Po;
import entity.Subject;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author dell
 */
public class POImportController extends HttpServlet {
     @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
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
        Part file_subject = request.getPart("file");
        String curriculum_id = request.getParameter("curriculum_id");
        int int_curriculum_id = 0;
        try {
            int_curriculum_id = Integer.parseInt(curriculum_id);
        } catch (NumberFormatException e) {
            int_curriculum_id = 1;
        }
//        response.getWriter().print("subjects_file.getName(): " + file_subject.getName());
        InputStream inputStream = file_subject.getInputStream();
        String mess = "";
        
        List<Po> listPo = new ArrayList<>();
        String err = ReadExcel.readExcelwithPO(inputStream, "xlsx",listPo,int_curriculum_id);
        if(err != null){
            response.getWriter().print(err);
        }else{
        request.getSession().setAttribute("listPo", listPo);
        response.getWriter().print("<table style=\"width: 60%;\" class=\"table table-hover\">\n"
                + "                    <thead>\n"
                + "                        <tr>\n"
                + "                            <th>Name</th>\n"
                + "                            <th>Description</th>\n"
                + "                            \n"
                + "\n"
                + "\n"
                + "                        </tr>\n"
                + "                    </thead>\n"
                + "                    <tbody>\n");
        int count = 1;
        for (Po po : listPo) {
            response.getWriter().print(
                    "                            <tr>\n"
                    + "                                <td>" + (count++) + "</td>\n"
                    + "                                <td>" + po.getName()+ "</td>\n"
                    + "                                <td>" + po.getDescription()+ "</td>\n"
                    + "                                \n"
                    + "\n"
                    + "                            </tr>\n");
        }
        response.getWriter().print(
                "\n"
                + "                    </tbody>\n"
                + "                </table>");
        response.getWriter().println("<a href=\"AddPo?type=m\"  style=\"text-decoration: none;color: #00a8ff\">Add to list</a>");
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
