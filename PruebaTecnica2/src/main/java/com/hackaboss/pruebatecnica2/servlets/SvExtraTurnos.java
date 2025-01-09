/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.hackaboss.pruebatecnica2.servlets;

import com.hackaboss.pruebatecnica2.logica.Controladora;
import com.hackaboss.pruebatecnica2.logica.Estado;
import com.hackaboss.pruebatecnica2.logica.Turno;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * La clase `SvExtraTurnos` es un servlet que maneja las solicitudes HTTP relacionadas con operaciones adicionales sobre la entidad `Turno`.
 * Proporciona métodos para procesar solicitudes GET y POST.
 * Permite eliminar un turno por su ID y cambiar el estado de un turno.
 * 
 * @author Alberto
 */
@WebServlet(name = "SvExtraTurnos", urlPatterns = {"/SvExtraTurnos"})
public class SvExtraTurnos extends HttpServlet {
    
    Controladora controlLogica = new Controladora();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SvExtra</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SvExtra at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    //Eliminar el turno por id y modificar el estado del turno
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String metodoExtra = request.getParameter("metodo_extra");
        Long idTurno = Long.parseLong(request.getParameter("id"));
        
        if ("DELETE".equalsIgnoreCase(metodoExtra)) {
            
            try {

                controlLogica.eliminarTurno(idTurno);
                response.sendRedirect("index.jsp"); // Redirige después de eliminar
            } catch (Exception e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Error al eliminar el turno.");
            }
        } else if ("PUT".equals(metodoExtra)) {
            // Obtener el turno de la base de datos
            Turno turno = controlLogica.obtenerTurnoPorId(idTurno);
            if (turno != null) {
                // Cambiar el estado del turno
                if (turno.getEstado() == Estado.EN_ESPERA) {
                    turno.setEstado(Estado.YA_ATENDIDO); // Cambiar a YA_ATENDIDO
                } else if (turno.getEstado() == Estado.YA_ATENDIDO) {
                    turno.setEstado(Estado.EN_ESPERA); // Cambiar a EN_ESPERA
                }
                // Guardar el turno actualizado
                controlLogica.editarTurno(turno);
                response.sendRedirect("index.jsp"); // Redirige después de editar
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
