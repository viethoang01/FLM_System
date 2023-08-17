/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Admin;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import data_access.SettingDAO;
import entity.Setting;
import java.util.List;

/**
 *
 * @author nguye
 */
@WebServlet(name = "SettingController", urlPatterns = {"/setting"})
public class SettingController extends HttpServlet {

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
            SettingDAO dao = new SettingDAO();
            String dos = request.getParameter("do");
            String indexPage = request.getParameter("index");
            int a;
            if (indexPage == null) {
                indexPage = "1";
            }
            int index = Integer.parseInt(indexPage);
            if (dos == null) {
                a = 1;
                //phan trang

                int count = dao.getTotal();
                int endPage = count / 3;
                if (count % 3 != 0) {
                    endPage++;
                }
                request.setAttribute("a", a);
                request.setAttribute("tag", index);
                request.setAttribute("endP", endPage);
                //
                List<Setting> list = dao.pagingSetting(index);
                request.setAttribute("listP", list);

                request.getRequestDispatcher("admin/setting.jsp").forward(request, response);
            }
            if (dos.equals("sortsetting")) {
                a = 2;
                List<Setting> list = dao.sortSetting(index);
                int count = dao.getTotal();
                int endPage = count / 3;
                if (count % 3 != 0) {
                    endPage++;
                }
                request.setAttribute("tag", index);
                request.setAttribute("a", a);
                request.setAttribute("endP", endPage);
                //
                request.setAttribute("listP", list);
                request.getRequestDispatcher("admin/setting.jsp").forward(request, response);
            }
            if (dos.equals("active")) {
                String page = request.getParameter("index");
                int n = Integer.parseInt(page);
                String id = request.getParameter("id");
                dao.editActive(id);
                response.sendRedirect("setting?index=" + n + "");
            }
            if (dos.equals("disactive")) {
                String page = request.getParameter("index");
                int n = Integer.parseInt(page);
                String id = request.getParameter("id");
                dao.editDisActive(id);
                response.sendRedirect("setting?index=" + n + "");
            }
            if (dos.equals("search")) {
                String author = request.getParameter("search");
                int count = dao.count(author);
                a = 3;
                int endPage = count / 3;
                if (count % 3 != 0) {
                    endPage++;
                }
                request.setAttribute("author", author);
                request.setAttribute("a", a);
                request.setAttribute("tag", index);
                request.setAttribute("endP", endPage);
                request.setAttribute("count", count);
                List<Setting> b = null;
                b = dao.searchSetting(index, author);

                request.setAttribute("listP", b);

                request.getRequestDispatcher("admin/setting.jsp").forward(request, response);
            }
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
