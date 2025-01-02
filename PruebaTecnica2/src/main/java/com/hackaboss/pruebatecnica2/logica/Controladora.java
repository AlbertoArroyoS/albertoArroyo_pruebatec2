/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hackaboss.pruebatecnica2.logica;

import com.hackaboss.pruebatecnica2.persistencia.ControladoraPersistencia;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Alberto
 */
public class Controladora {

    ControladoraPersistencia controlPersis = new ControladoraPersistencia();

    /* CIUDADANOS */
    public void crearCiudadano(Ciudadano ciudadano) {
        controlPersis.crearCiudadano(ciudadano);
    }

    public void eliminarCiudadano(Long id) {
        controlPersis.eliminarCiudadano(id);
    }

    public List<Ciudadano> traerCiudadanos() {
        return controlPersis.traerCiudadanos();
    }

    public void editarCiudadano(Ciudadano ciudadano) {
        controlPersis.editarCiudadano(ciudadano);
    }

    /*** TURNOS ***/
    
    public void crearTurno(Turno turno, Long ciudadanoId) {
        
        // Obtener el Ciudadano a partir del ID
        Ciudadano ciudadano = controlPersis.ObtenerCiudadano(ciudadanoId);

        // Validar que el ciudadano exista
        if (ciudadano == null) {
            throw new IllegalArgumentException("No se encontró un ciudadano con el ID proporcionado.");
        }

        // Setear el Ciudadano en el Turno antes de persistir
        turno.setCiudadano(ciudadano);

        // Obtener la fecha del turno a crear
        Date fechaTurno = turno.getFecha();

        // Buscar el último turno registrado para la misma fecha
        Turno ultimoTurno = obtenerUltimoTurnoPorFecha(fechaTurno);

        if (ultimoTurno != null) {
            // Incrementar el número de turno
            turno.setNumero(ultimoTurno.getNumero() + 1);
        } else {
            // Si no hay turnos para la fecha, comenzar desde 1
            turno.setNumero(1);
        }

        // Persistir el turno
        controlPersis.crearTurno(turno);
    }


    public void eliminarTurno(Long id) {
        controlPersis.eliminarTurno(id);
    }

    public List<Turno> traerTurnos() {
        return controlPersis.traerTurnos();
    }

    public void editarTurno(Turno turno) {
        controlPersis.editarTurno(turno);
    }
    
    // METODOS PROPIOS
    
    //Metodo para obtener toda la lista por fecha
    public List<Turno> obtenerTurnosPorFecha(Date fecha) {
        // Crear un SimpleDateFormat para formatear las fechas
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

        // Formatear la fecha de comparación
        String fechaStr = sdf.format(fecha);

        // Obtener todos los turnos desde la capa de persistencia
        List<Turno> listaTurnos = controlPersis.traerTurnos();

        // Filtrar los turnos cuya fecha coincida con la fecha dada
        return listaTurnos.stream()
                .filter(turno -> {
                    // Formatear la fecha del turno
                    String turnoFechaStr = sdf.format(turno.getFecha());
                    return turnoFechaStr.equals(fechaStr);
                })
                .collect(Collectors.toList());
    }

    
    //Metodo para obtener el ultimo turno en una fecha para que cuando alguien pida se autoincremente
    public Turno obtenerUltimoTurnoPorFecha(Date fecha) {
        
        // Obtener todos los turnos desde la capa de persistencia
        List<Turno> listaTurnos = controlPersis.traerTurnos();
        
        // Crear un SimpleDateFormat para formatear las fechas
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

        // Formatear la fecha de comparación
        String fechaStr = sdf.format(fecha);

        // Filtrar y encontrar el último turno para la misma fecha
        return listaTurnos.stream()
                .filter(turno -> {
                    // Formatear la fecha del turno
                    String turnoFechaStr = sdf.format(turno.getFecha());
                    return turnoFechaStr.equals(fechaStr);
                })
                .max(Comparator.comparingInt(Turno::getNumero))
                .orElse(null); // Devuelve null si no hay turnos para la fecha
    }

}
