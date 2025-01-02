/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.hackaboss.pruebatecnica2.servlets;

import com.hackaboss.pruebatecnica2.logica.Ciudadano;
import com.hackaboss.pruebatecnica2.logica.Controladora;
import com.hackaboss.pruebatecnica2.logica.Estado;
import com.hackaboss.pruebatecnica2.logica.Turno;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Alberto
 */
@WebServlet(name = "SvTurnos", urlPatterns = {"/SvTurnos"})
public class SvTurnos extends HttpServlet {
    
    Controladora controlLogica = new Controladora();


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SvTurnos</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SvTurnos at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        /*
        // Se obtienen los turnos desde la BD
        List<Turno> listaTurnos = controlLogica.traerTurnos();

        // Establecer los resultados en la solicitud para que se muestren en el JSP
        request.setAttribute("turnos", listaTurnos);
  
        // Redirigir de vuelta al formulario
        request.getRequestDispatcher("index.jsp").forward(request, response);  */
        // Obtener el parámetro de fecha para la búsqueda
        
        //Obtener el parametro fecha
        // Obtener los parámetros de fecha y estado para la búsqueda
        String buscarFechaStr = request.getParameter("buscar_fecha");
        String estadoStr = request.getParameter("estado_turno");
        List<Turno> listaTurnos = new ArrayList<>();

        try {
            // Si se ha proporcionado una fecha, buscar turnos por fecha
            if (buscarFechaStr != null && !buscarFechaStr.isEmpty()) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // Formato de la fecha
                Date buscarFecha = sdf.parse(buscarFechaStr);

                // Si también se ha proporcionado un estado, buscar turnos por fecha y estado
                if (estadoStr != null && !estadoStr.isEmpty()) {
                    listaTurnos = controlLogica.obtenerTurnosPorFechaYEstado(buscarFecha, estadoStr);
                } else {
                    // Si no se ha proporcionado un estado, buscar solo por fecha
                    listaTurnos = controlLogica.obtenerTurnosPorFecha(buscarFecha);
                }
            } else {
                // Si no se ha proporcionado una fecha, buscar todos los turnos
                listaTurnos = controlLogica.traerTurnos();
            }

            // Establecer los resultados en la solicitud para que se muestren en el JSP
            request.setAttribute("turnos", listaTurnos);
            request.getRequestDispatcher("index.jsp").forward(request, response);

        } catch (ParseException e) {
            throw new ServletException("Error al parsear la fecha: " + buscarFechaStr, e);
        }
        
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        
        //Obtener datos desde el JSP

        //String numeroStr = request.getParameter("numero_turno");
        String fechaStr = request.getParameter("fecha_turno");
        String descripcion = request.getParameter("descripcion_turno");
        String estadoStr = request.getParameter("estado_turno");
        String ciudadanoIdStr = request.getParameter("ciudadano_id");
        
        try {
            //Convertir la fecha
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyy-mm-dd");
            Date fecha = sdf.parse(fechaStr);
            //Crear nuevo turno
            Turno turno = new Turno();
            //turno.setNumero(Integer.parseInt(numeroStr));
            Estado estado = Estado.valueOf(estadoStr);  // Convertir el String a Enum
            turno.setFecha(fecha);
            turno.setDescripcion(descripcion);
            turno.setEstado(estado);
            
            //Mandar a persistir el partido, enviado los IDs 
            controlLogica.crearTurno(turno, Long.parseLong(ciudadanoIdStr));

            // Redirigir de vuelta al formulario
            request.getRequestDispatcher("index.jsp").forward(request, response);               
            
        } catch (ParseException ex) {
            Logger.getLogger(SvTurnos.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

   
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
