/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.hackaboss.pruebatecnica2.servlets;

import com.hackaboss.pruebatecnica2.logica.Controladora;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Alberto
 */
@WebServlet(name = "SvExtraCiudadanos", urlPatterns = {"/SvExtraCiudadanos"})
public class SvExtraCiudadanos extends HttpServlet {
    
    Controladora controlLogica = new Controladora();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SvExtraCiudadanos</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SvExtraCiudadanos at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String metodoExtra = request.getParameter("metodo_extra");
        Long idCiudadano = Long.parseLong(request.getParameter("id_ciudadano"));
        
        if ("DELETE_CIUDADANO".equalsIgnoreCase(metodoExtra)) {
            
            try {
                controlLogica.eliminarCiudadano(idCiudadano);
                response.sendRedirect("index.jsp"); // Redirige después de eliminar
            } catch (Exception e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Error al eliminar el ciudadano.");
            }
        }
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
