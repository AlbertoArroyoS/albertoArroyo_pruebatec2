/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hackaboss.pruebatecnica2.logica;

import com.hackaboss.pruebatecnica2.persistencia.ControladoraPersistencia;
import java.util.Date;
import java.util.List;

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

    /* TURNOS */
    public void crearTurno(Turno turno, Long ciudadanoId) {
        // Obtener el Ciudadano a partir del ID
        Ciudadano ciudadano = controlPersis.ObtenerCiudadano(ciudadanoId);
        
        // Setear el Ciudadano en el Turno antes de persistir
        turno.setCiudadano(ciudadano);
        
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

}
