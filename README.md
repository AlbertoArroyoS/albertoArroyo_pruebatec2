# Alberto Arroyo Santofimia

# Gestión de Turnos - Prueba Técnica 2 (HACK A BOSS)

## Wiki de la aplicación

https://github.com/AlbertoArroyoS/albertoArroyo_pruebatec2/wiki

## Descripción

Esta aplicación permite la administración de turnos asignados a ciudadanos para diversos trámites. Los turnos tienen:
- Un número generado automáticamente en función de los turnos del día elegido.
- Una fecha y una descripción del trámite.
- Un estado que puede ser **"En espera"** o **"Ya atendido"**.

Los usuarios pueden realizar operaciones CRUD (Crear, Leer, Actualizar, Borrar) tanto sobre turnos como ciudadanos.

## Funcionalidades

### Ciudadanos
- **Crear ciudadano**: Añadir nombre y email a la base de datos. No se permite duplicidad de datos, ni nombre ni email.
- **Listar ciudadanos**: Mostrar ciudadanos registrados en la base de datos.
- **Borrar ciudadanos**: Al borrar el ciudadano se borraran tambien todos los turnos que tenga asignados.

### Turnos
- **Agregar nuevo turno**: Ingresar fecha, descripción, estado e ID de ciudadano. El turno se genera automáticamente como el primer número disponible del día.
- **Listar turnos**: Ver todos los turnos con detalles y opciones para cambiar el estado o eliminarlos.
- **Filtrar turnos**: Buscar turnos por fecha o estado (En espera / Ya atendido).
- **Borrar turnos**: Borrar turno al pulsar el botón de la lista.
- **Modificar estado del turno**: Cambiar el estado de los turnos en espera y ya atendidos en la lista de turnos.

## Tecnologías utilizadas
- **Java 17**: Backend y lógica de negocio.
- **JSP (JavaServer Pages)**: Frontend.
- **JPA (Java Persistence API)**: Interacción con base de datos.
- **Servlets**: Manejo de solicitudes web.
- **Bootstrap**: Diseño y estructura de la interfaz.
- **Funciones Lambda, Optionals y Streams**: Optimización y modernización del código.

## Cómo ejecutar la aplicación

1. **Clonar el repositorio**:
   ```bash
   git clone https://github.com/AlbertoArroyoS/albertoArroyo_pruebatec2.git


1. **Instalar dependencias** : Configura las dependencias necesarias en pom.xml para habilitar JPA y otras funcionalidades.
1. **Configurar base de datos**: La aplicación está preconfigurada para usar una base de datos MySQL llamada turnos.
   Edita la configuración en el archivo:
   
   Other Sources/src/main/resources/META-INF/persistence.xml
   ```bash
   <property name="javax.persistence.jdbc.url" value="jdbc:mysql://localhost:3306/turnos?serverTimezone=UTC"/>

1. **Ejecutar la aplicación**: Usa Tomcat 9.0.98 para desplegar la aplicación. Asegúrate de que el servidor esté ejecutándose.
1. **Acceder a la aplicación**: Una vez que el servidor esté en funcionamiento, abre tu navegador web y dirígete a
   ```bash
   http://localhost:8080/PruebaTecnica2/SvCiudadanos?

**Estructura del proyecto**
   ```
   /src
  ├── /main
       ├── /java
           ├── /com
               ├── /hackaboss
                   ├── /pruebatecnica2
                       ├── /logica
                           ├── Ciudadano.java
                           ├── Controladora.java
                           ├── Estado.java
                           ├── Turno.java
                       ├── /persistencia
                           ├── CiudadanoJpaController.java
                           ├── ControladoraPersistencia.java
                           ├── TurnoJpaController.java
                           ├── /exceptions
                       ├── /servlets
                           ├── SvCiudadanos.java
                           ├── SvExtraCiudadanos.java
                           ├── SvTurnos.java
                           ├── SvExtraTurnos.java
       ├── /resources
           ├── /META-INF
               ├── persistence.xml

   ```

**Archivos importantes**

1. **Turno.java**: Entidad que representa el turno, con atributos como número, fecha, descripción y estado.
1. **Ciudadano.java**: Entidad que representa al ciudadano asociado a un turno.
1. **Estado.java**: Enum que define los posibles estados de un turno ("EN\_ESPERA", "YA\_ATENDIDO").
1. **ControladoraPersistencia.java**: Clase que maneja las operaciones CRUD para las entidades.
1. **Controladora**.java: Actúa como intermediaria entre la capa de presentación y la capa de persistencia.
1. **Paquete servlets**: Para maneja las solicitudes HTTP para agregar, listar, eliminar y cambiar el estado de los turnos/ciudadanos.
1. **index.jsp**: Página principal donde los usuarios pueden crear ciudadanos y turnos


