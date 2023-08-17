/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Admin;

import data_access.PermissionDAO;
import data_access.PloDAO;
import data_access.PoDAO;
import data_access.ReadExcel;
import entity.Plo;
import entity.Po;
import entity.User;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author dell
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 10, // 10 MB
        maxRequestSize = 1024 * 1024 * 100 // 100 MB
)
@WebServlet(name = "PoListController", urlPatterns = {"/po-list"})
public class PoListController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String status = request.getParameter("status");
        if(status != null){
            request.setAttribute("status",status);
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
        
        int curriculum_id = Integer.parseInt(request.getParameter("curriculum_id"));

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
            request.setAttribute("list", new PoDAO().getAllPo(sort, txt, (index - 1) * 3, curriculum_id));
        }
        request.setAttribute("totalItem", new PoDAO().getTotalItem(txt, curriculum_id));
        request.setAttribute("txt", txt);
        request.setAttribute("curriculum_id", curriculum_id);
        request.setAttribute("index", index);
        request.setAttribute("sort", sort);
        request.getRequestDispatcher("./system_admin/po-list.jsp").forward(request, response);
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

        List<Po> listPo = new ArrayList<>();
        String err = ReadExcel.readExcelwithPO(inputStream, "xlsx", listPo, curriculum_id);
        if (listPo.size() == 0) {
            if (!err.equals("")) {
                response.getWriter().println("<div>" + err + "</div>");
            }
        } else {
            request.getSession().setAttribute("listPo", listPo);
            response.getWriter().println("<table class=\"table table-hover\">");
            response.getWriter().println("<thead>\n"
                    + "    <tr>\n"
                    + "      <th scope=\"col\" class=\"text-center\">ID</th>\n"
                    + "      <th scope=\"col\" class=\"text-center\">Name</th>\n"
                    + "      <th scope=\"col\" class=\"text-center\">Description</th>\n"
                    + "    </tr>\n"
                    + "  </thead>");
            int count = 1;
            response.getWriter().println("<tbody>");
            for (Po po : listPo) {
                response.getWriter().println("  <tr>\n"
                        + "      <td>" + (count++) + "</td>\n"
                        + "      <td>" + po.getName() + "</td>\n"
                        + "      <td>" + po.getDescription() + "</td>\n"
                        + "    </tr>\n"
                        + "  ");
            }
            response.getWriter().println("</tbody>");
            response.getWriter().write("</table>");
            if (listPo.size() > 0) {
                response.getWriter().println("<a href=\"po-excel?id=" + curriculum_id + "&type=m&txt=\"  style=\"text-decoration: none;color: #00a8ff\">Add to list</a><br>");
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
