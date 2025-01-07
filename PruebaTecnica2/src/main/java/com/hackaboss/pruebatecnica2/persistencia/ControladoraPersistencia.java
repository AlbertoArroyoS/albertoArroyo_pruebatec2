/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hackaboss.pruebatecnica2.persistencia;

import com.hackaboss.pruebatecnica2.logica.Ciudadano;
import com.hackaboss.pruebatecnica2.logica.Turno;
import com.hackaboss.pruebatecnica2.persistencia.exceptions.NonexistentEntityException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author Alberto
 */
public class ControladoraPersistencia {
    
    CiudadanoJpaController ciudadanoJPA = new CiudadanoJpaController();
    TurnoJpaController turnoJPA = new TurnoJpaController();
    
    /*PARA CIUDADANO*/
    public void crearCiudadano(Ciudadano ciudadano) {
        ciudadanoJPA.create(ciudadano);
    }
    
    public void eliminarCiudadano(Long id) {
        try {
            ciudadanoJPA.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<Ciudadano> traerCiudadanos() {
        return ciudadanoJPA.findCiudadanoEntities();
    }
    
    public void editarCiudadano(Ciudadano ciudadano) {
        try {
            ciudadanoJPA.edit(ciudadano);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    
    public Ciudadano ObtenerCiudadano(Long id) {
        return ciudadanoJPA.findCiudadano(id);
    }    
    
    
    
    /*PARA Turno*/
    public void crearTurno(Turno turno) {
        turnoJPA.create(turno);
    }
    
    public void eliminarTurno(Long id) {
        try {
            turnoJPA.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<Turno> traerTurnos() {
        return turnoJPA.findTurnoEntities();
    }
    
    public void editarTurno(Turno turno) {
        try {
            turnoJPA.edit(turno);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Turno obtenerTurno(Long id) {
        return turnoJPA.findTurno(id);
    } 
}

