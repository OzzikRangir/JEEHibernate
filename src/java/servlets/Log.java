/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import database.Notebook;
import database.NotebookQuery;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logger.LogNotebook;
import logger.Logger;

/**
 *
 * @author Windows
 */
@WebServlet(name = "historia", urlPatterns = {"/historia"})
public class Log extends HttpServlet {

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

                    Logger logger = new Logger();
            logger.setCookieVector(request.getCookies());
            Vector tmp = logger.getOperations();
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<head><meta><link rel='stylesheet' href='Style/css/components.css'>");
            out.println("<link rel='stylesheet' href='Style/css/icons.css'>");
            out.println("<link rel='stylesheet' href='Style/css/responsee.css'>");
            out.println("<title>Historia operacji</title>");
            out.println("</head>");
            out.println("<body>");
            if (request.getCookies() != null) {
                out.println("<h1> Lista ostatnich "+ tmp.size()+" operacji w ciągu 24 godzin</h1>");

                out.println(logger.getHTMLOperations((Vector<LogNotebook>)tmp));
            } else {
                out.println("<h2>Brak ostatnich operacji</h2>");
            }
            out.println("</body>");
            out.println("</html>");
            out.println("<a class=\'button rounded-full-btn reload-btn s-2 margin-bottom\' href=");
            out.println(request.getHeader("referer"));
            out.println("><i class='icon-sli-arrow-left'>Powrót</i></a>");
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
