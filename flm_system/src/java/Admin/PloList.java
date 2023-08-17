/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Admin;

import data_access.PermissionDAO;
import data_access.PloDAO;
import data_access.ReadExcel;
import entity.Plo;
import entity.User;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author SHD
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 10, // 10 MB
        maxRequestSize = 1024 * 1024 * 100 // 100 MB
)
public class PloList extends HttpServlet {

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
            out.println("<title>Servlet PloList</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PloList at " + request.getContextPath() + "</h1>");
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
            request.setAttribute("list", new PloDAO().getAllPlo(sort, txt, (index - 1) * 3, curriculum_id));
        }
        request.setAttribute("totalItem", new PloDAO().getTotalItem(txt, curriculum_id));
        request.setAttribute("txt", txt);
        request.setAttribute("curriculum_id", curriculum_id);
        request.setAttribute("index", index);
        request.setAttribute("sort", sort);
        request.getRequestDispatcher("./system_admin/plo_list.jsp").forward(request, response);
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

        List<Plo> listPlo = new ArrayList<>();
        String err = ReadExcel.readExcelwithPLO(inputStream, "xlsx", listPlo, curriculum_id);
        if (listPlo.size() == 0) {
            if (!err.equals("")) {
                response.getWriter().println("<div>" + err + "</div>");
            }
        } else {
            request.getSession().setAttribute("listPlo", listPlo);
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
            for (Plo plo : listPlo) {
                response.getWriter().println("  <tr>\n"
                        + "      <td>" + (count++) + "</td>\n"
                        + "      <td>" + plo.getName() + "</td>\n"
                        + "      <td>" + plo.getDescription() + "</td>\n"
                        + "    </tr>\n"
                        + "  ");
            }
            response.getWriter().println("</tbody>");
            response.getWriter().write("</table>");
            if (listPlo.size() > 0) {
                response.getWriter().println("<a href=\"plo-excel?id=" + curriculum_id + "&type=m\"  style=\"text-decoration: none;color: #00a8ff\">Add to list</a><br>");
            }
            if (!err.equals("")) {
                response.getWriter().println("<div>" + err + "</div>");
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
