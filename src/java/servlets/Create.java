/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import database.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import logger.LogNotebook;
import logger.Logger;

/**
 *
 * @author Windows
 */
@WebServlet(name = "dodaj", urlPatterns = {"/dodaj"})
public class Create extends HttpServlet {

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
        Integer pages = null;
        String name = request.getParameter("name");
        boolean numeration = false;



        try {
            pages = Integer.valueOf(request.getParameter("pages"));
        } catch (NumberFormatException e) {
        }
        try{
            numeration = Boolean.valueOf(request.getParameter("numeration"));
        }catch (NumberFormatException e) {
        }       

        Logger logger = new Logger();
        logger.setCookieVector(request.getCookies());
        
        
        try (PrintWriter out = response.getWriter()) {

            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head><meta><link rel='stylesheet' href='Style/css/components.css'><title>Lab2</title></head>");
            out.println("<link rel='stylesheet' href='Style/css/icons.css'>");
            out.println("<link rel='stylesheet' href='Style/css/responsee.css'>");
            out.println("<body><br />");
            out.println("<h1>Dodano</h1>");
            int id = new NotebookQuery().getLastId();
            Notebook autobus = new Notebook(id,name,pages ,numeration);
            new NotebookQuery().insert(autobus);
            Notebook nb = new NotebookQuery().getNotebook(id);

            String str = NotebookQuery.getHTMLList(Arrays.asList(nb),true);
            out.println(str);                    
                        
            logger.addCookie(new LogNotebook("Dodano",new Date(),nb));
            for (Cookie cookie : logger.getCookieVector()) {
                response.addCookie(cookie);
            }
            
            out.println("<a class=\'button rounded-full-btn reload-btn s-2 margin-bottom\' href=");
            out.println(request.getContextPath() + "");
            out.println("><i class='icon-sli-arrow-left'>Powrót</i></a>");

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
