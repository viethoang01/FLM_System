/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Admin;

import data_access.PoDAO;
import entity.Po;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author dell
 */
@WebServlet(name = "DetailsPoController", urlPatterns = {"/DetailsPo"})
public class DetailsPoController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String po_id = request.getParameter("po_id");
        PoDAO poDAO = new PoDAO();
        try {
            Po po = poDAO.getPo(Integer.parseInt(po_id));
            response.getWriter().print("<div class=\"mb-3 mt-3\">\n"
                    + "                                    <label for=\"uname\" class=\"form-label\">Po ID:</label>\n"
                    + "                                    <input type=\"text\" class=\"form-control\" id=\"cuid\" placeholder=\"Enter id\" name=\"cuid\" value=\"" + po.getCurriculum_id() + "\" hidden required>\n"
                    + "                                    <input type=\"text\" class=\"form-control\" id=\"id\" placeholder=\"Enter id\" name=\"id\" value=\"" + po.getPo_id() + "\" readonly required>\n"
                    + "                                </div>\n"
                    + "                                <div class=\"mb-3 mt-3\">\n"
                    + "                                    <input type=\"text\" class=\"form-control\" onchange=\"openSubmit()\" id=\"nameold\" placeholder=\"Enter username\" name=\"nameold\" value=\"" + po.getName() + "\" hidden required>\n"
                    + "                                    <label for=\"uname\" class=\"form-label\">Name:</label>\n"
                    + "                                    <input type=\"text\" class=\"form-control\" onchange=\"openSubmit()\" id=\"name\" placeholder=\"Enter username\" name=\"name\" value=\"" + po.getName() + "\"  required>\n"
                    + "                                    <div class=\"valid-feedback\">Valid.</div>\n"
                    + "                                    <div class=\"invalid-feedback\">Please fill out this field.</div>\n"
                    + "                                </div>\n"
                    + "                                <div class=\"mb-3\">\n"
                    + "                                    <label for=\"pwd\" class=\"form-label\">Description:</label>\n"
                    + "                                    <input type=\"text\" class=\"form-control\" onchange=\"openSubmit()\" id=\"des\" placeholder=\"Enter des\" name=\"des\"  value=\"" + po.getDescription() + "\"  required>\n"
                    + "                                    <div class=\"valid-feedback\">Valid.</div>\n"
                    + "                                    <div class=\"invalid-feedback\">Please fill out this field.</div>\n"
                    + "                                </div>\n"
                    + "                                \n"
                    + "                                <button type=\"submit\" id=\"btnupdate\" class=\"btn btn-primary disabled\">Submit</button>");
        } catch (NumberFormatException e) {
            response.getWriter().print("<div class=\"mb-3 mt-3\">\n"
                    + "                                    <label for=\"uname\" class=\"form-label\">Po ID:</label>\n"
                    + "                                    <input type=\"text\" class=\"form-control\" id=\"id\" placeholder=\"Enter id\" name=\"id\" value=\"\" required>\n"
                    + "                                    <div class=\"valid-feedback\">Valid.</div>\n"
                    + "                                    <div class=\"invalid-feedback\">Please fill out this field.</div>\n"
                    + "                                </div>\n"
                    + "                                <div class=\"mb-3 mt-3\">\n"
                    + "                                    <label for=\"uname\" class=\"form-label\">Name:</label>\n"
                    + "                                    <input type=\"text\" class=\"form-control\" id=\"name\" placeholder=\"Enter username\" name=\"name\" value=\"\"  required>\n"
                    + "                                    <div class=\"valid-feedback\">Valid.</div>\n"
                    + "                                    <div class=\"invalid-feedback\">Please fill out this field.</div>\n"
                    + "                                </div>\n"
                    + "                                <div class=\"mb-3\">\n"
                    + "                                    <label for=\"pwd\" class=\"form-label\">Description:</label>\n"
                    + "                                    <input type=\"text\" class=\"form-control\" id=\"des\" placeholder=\"Enter des\" name=\"des\"  value=\"\"  required>\n"
                    + "                                    <div class=\"valid-feedback\">Valid.</div>\n"
                    + "                                    <div class=\"invalid-feedback\">Please fill out this field.</div>\n"
                    + "                                </div>\n"
                    + "                                \n"
                    + "                                <button type=\"submit\" id=\"btnupdate\" class=\"btn btn-primary \">Submit</button>");
        }
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
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String des = request.getParameter("des");
        String cuid = request.getParameter("cuid");
        String nameold = request.getParameter("nameold");

        Po po = new Po();
        po.setPo_id(Integer.parseInt(id));
        po.setDescription(des);
        po.setName(name);
        PoDAO poDAO = new PoDAO();

        if (nameold.equals(name)) {

            boolean status = false;
            status = poDAO.updatePo(po);
            if (status) {
                response.getWriter().print("success");
            } else {
                response.getWriter().print("fail| " + id + "|" + name + "|" + des + "|");

            }
        } else {
            if (!poDAO.checkDup(name)) {
                boolean status = false;
                status = poDAO.updatePo(po);
                if (status) {
                    response.getWriter().print("success");
                } else {
                    response.getWriter().print("fail| " + id + "|" + name + "|" + des + "|");

                }
            } else {
                response.getWriter().print("dup");

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
