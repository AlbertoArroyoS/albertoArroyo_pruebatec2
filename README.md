# Alberto Arroyo Santofimia

HACK A BOSS

Prueba Técnica 2 - Desarrollo de una aplicación de gestión de turnos


**Descripción**

Esta es una aplicación de gestión de turnos, cuyo objetivo es permitir la administración de turnos asignados a ciudadanos para diversos trámites. Los turnos tienen un número, una fecha y una descripción del trámite, y un estado que puede ser "En espera" o "Ya atendido". El número se genera automáticamente dependiendo de los turnos que haya el día elegido. Los usuarios pueden realizar operaciones CRUD (Crear, Leer, Actualizar, Borrar) sobre los turnos.

**Funcionalidades**

- **Crear ciudadano**: Permite al usuario ingresar el nombre y el email del ciudadano. No se permite añadir ciudadanos si ya existe el nombre o correo electrónico en la base de datos.
- **Listar ciudadanos**: Muestra los ciudadanos dados de alta, permite borrar los ciudadanos y las citas que tiene asignadas el ciudadano.
- **Agregar un nuevo turno**: Permite al usuario ingresar información sobre un nuevo turno, como la fecha, la descripción del trámite, estado y el id del ciudadano asignado. El turno es auto incremental, se asignará el primer numero disponible del día elegido.
- **Listar turnos**: Muestra todos los turnos en la base de datos, con detalles del ciudadano asignado con opciones de cambiar el estado del turno y eliminarlo.
- **Filtrar turnos**: Permite al usuario filtrar turnos por fecha y estado (En espera / Ya atendido).

**Tecnologías utilizadas**

- **Java 17**: Para la lógica de la aplicación y el backend.
- **JSP (JavaServer Pages)**: Para la interfaz de usuario (Frontend).
- **JPA (Java Persistence API)**: Para interactuar con la base de datos.
- **Servlets**: Para el manejo de la lógica de la aplicación web.
- **Bootstrap**: Para el diseño del frontend y la estructura de la interfaz.
- **Funciones Lambda, Optionals, Streams**: Para implementar funcionalidades avanzadas y optimización del código.

**Cómo ejecutar la aplicación**

1. **Clonar el repositorio**:

   git clone https://github.com/AlbertoArroyoS/albertoArroyo\_pruebatec2.git

1. **Instalar dependencias** (si las hubiera): La aplicación utiliza JPA, así que asegúrate de tener configurada la base de datos y las dependencias necesarias en tu proyecto en ProjectFiles/pom.xml
1. **Configurar base de datos**: La aplicación debe tener configurada una fuente de datos que apunte a tu base de datos. Esta preconfigurada para una base de datos en MySQL con el nombre turnos. Puede cambiar la configuración en 

   Other Sources/src/main/resurces/META-INF/persistence.xml

   <property name="javax.persistence.jdbc.url" value="jdbc:mysql://localhost:3306/turnos?serverTimezone=UTC"/>

1. **Compilar y ejecutar la aplicación**: La herramienta usada para el servidor web ha sido Tomcat 9.0.98, debes desplegar la aplicación en el servidor. Asegúrate de que el servidor esté ejecutándose antes de intentar acceder a la aplicación.
1. **Acceder a la aplicación**: Una vez que el servidor esté en funcionamiento, abre tu navegador web y dirígete a

   http://localhost:8080/PruebaTecnica2/SvCiudadanos?

   ` `Allí podrás ver la interfaz de usuario para agregar, listar y cambiar el estado de los turnos.

**Estructura del proyecto**

   ![image](https://github.com/user-attachments/assets/7aed7688-f484-4564-9bf4-a83882126290)


**Archivos importantes**

1. **Turno.java**: Entidad que representa el turno, con atributos como número, fecha, descripción y estado.
1. **Ciudadano.java**: Entidad que representa al ciudadano asociado a un turno.
1. **Estado.java**: Enum que define los posibles estados de un turno ("EN\_ESPERA", "YA\_ATENDIDO").
1. **ControladoraPersistencia.java**: Clase que maneja las operaciones CRUD para las entidades.
1. **Controladora**.java: Actúa como intermediaria entre la capa de presentación y la capa de persistencia.
1. **Paquete servlets**: Para maneja las solicitudes HTTP para agregar, listar, eliminar y cambiar el estado de los turnos/ciudadanos.
1. **index.jsp**: Página principal donde los usuarios pueden crear ciudadanos y turnos


