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
 * Clase que actúa como intermediaria entre la capa de presentación y la capa de persistencia.
 * Gestiona las operaciones CRUD (Crear, Leer, Actualizar, Eliminar) para las entidades `Ciudadano` y `Turno`.
 * 
 * @author Alberto
 */
public class Controladora {

    ControladoraPersistencia controlPersis = new ControladoraPersistencia();

    /* CIUDADANOS */
    
    /**
     * Crea un nuevo ciudadano si no existe uno con el mismo nombre o email.
     * 
     * @param ciudadano El ciudadano a crear.
     */
    public void crearCiudadano(Ciudadano ciudadano) {
        List<Ciudadano> listaAux = controlPersis.traerCiudadanos();
        
        // Verificar si el nombre del ciudadano ya existe usando Stream, quitando los espacios del principio y del final
        /*
        boolean existe = listaAux.stream()
                                 .anyMatch(c -> c.getNombre().equalsIgnoreCase(ciudadano.getNombre().trim()));*/
        
        //Verificar que si el nombre o el email existen no se pueda crear un nuevo usuario
        boolean existe = listaAux.stream()
                                 .anyMatch(c -> c.getNombre().equalsIgnoreCase(ciudadano.getNombre()) ||
                                                c.getEmail().equalsIgnoreCase(ciudadano.getEmail()));

        if (!existe) {
            // Si no existe, crear el ciudadano
            controlPersis.crearCiudadano(ciudadano);
        }else{
            System.out.println("El ciudadano a crear ya existe");
        }

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
    
    /**
     * Crea un nuevo turno para un ciudadano específico.
     * 
     * @param turno El turno a crear.
     * @param ciudadanoId El ID del ciudadano asociado al turno.
     */
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
    
    /**
     * Obtiene una lista de turnos para una fecha específica.
     * 
     * @param fecha La fecha para la cual se desean obtener los turnos.
     * @return Una lista de turnos para la fecha dada.
     */
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

    
    /**
     * Obtiene el último turno registrado para una fecha específica.
     * 
     * @param fecha La fecha para la cual se desea obtener el último turno.
     * @return El último turno registrado para la fecha dada, o null si no hay turnos.
     */
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
    
    /**
     * Obtiene una lista de turnos para una fecha y estado específicos.
     * 
     * @param fecha La fecha para la cual se desean obtener los turnos.
     * @param estado El estado de los turnos a filtrar.
     * @return Una lista de turnos para la fecha y estado dados.
     */
    public List<Turno> obtenerTurnosPorFechaYEstado(Date fecha, String estado) {
        List<Turno> turnos = obtenerTurnosPorFecha(fecha); // Filtrar primero por fecha
        return turnos.stream()
                     .filter(turno -> turno.getEstado().toString().equals(estado)) // Filtrar por estado
                     .collect(Collectors.toList());
    }
    
    /**
     * Actualiza el estado de un turno específico.
     * 
     * @param id El ID del turno a actualizar.
     * @param nuevoEstado El nuevo estado del turno.
     */
    public void actualizarEstadoTurno(long id, Estado nuevoEstado) {
        Turno turno = controlPersis.obtenerTurno(id);
        if (turno != null) {
            turno.setEstado(nuevoEstado);
            editarTurno(turno); 
        }
    }
    
    
    /**
     * Obtiene un turno por su ID.
     * 
     * @param id El ID del turno a obtener.
     * @return El turno correspondiente al ID dado.
     */
    public Turno obtenerTurnoPorId(long id){
        
        return controlPersis.obtenerTurno(id);
        
    }
    
}
