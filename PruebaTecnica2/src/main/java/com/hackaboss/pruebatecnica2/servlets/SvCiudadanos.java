/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.hackaboss.pruebatecnica2.servlets;

import com.hackaboss.pruebatecnica2.logica.Ciudadano;
import com.hackaboss.pruebatecnica2.logica.Controladora;
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
 * @author Alberto
 */
@WebServlet(name = "SvCiudadanos", urlPatterns = {"/SvCiudadanos"})
public class SvCiudadanos extends HttpServlet {
    
    Controladora controlLogica = new Controladora();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SvCiudadanos</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SvCiudadanos at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Se obtienen los equipos desde la BD
        List<Ciudadano> listCiudadanos = controlLogica.traerCiudadanos();
        
        // Establecer los resultados en la solicitud para que se muestren en el JSP
        request.setAttribute("ciudadanos", listCiudadanos);
        
        // Redirigir de vuelta al formulario
        request.getRequestDispatcher("index.jsp").forward(request, response);  
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Lógica para manejar el POST
        String nombre = request.getParameter("nombre_ciudadano");
        String email = request.getParameter("email_ciudadano");
        
        //Crear un nuevo objeto ciudadanos
        Ciudadano ciudadano = new Ciudadano();
        ciudadano.setNombre(nombre);
        ciudadano.setEmail(email);
        
        //Se persiste el equipo en la BD
        controlLogica.crearCiudadano(ciudadano);
        
        response.sendRedirect("index.jsp"); 
        

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
