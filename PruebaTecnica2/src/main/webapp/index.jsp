<%-- 
    Document   : index
    Created on : 2 ene 2025, 13:10:07
    Author     : Alberto
--%>

<%@page import="com.hackaboss.pruebatecnica2.logica.Ciudadano"%>
<%@page import="com.hackaboss.pruebatecnica2.logica.Turno"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Gestión de Turnos</title>
        <!-- Agregar estilos de Bootstrap -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    </head>
    <body>
        <div class="container mt-4">
            <!-- Formulario para agregar Ciudadano -->
            <h2>Formulario Ciudadano</h2>
            <form action="SvCiudadanos" method="POST">
                <div class="form-group">
                    <label for="nombre">Nombre:</label>
                    <input type="text" class="form-control" id="nombre_ciudadano" name="nombre_ciudadano">
                </div>
                <div class="form-group">
                    <label for="email">Email:</label>
                    <input type="email" class="form-control" id="email_ciudadano" name="email_ciudadano">
                </div>
                <button type="submit" class="btn btn-primary">Guardar Ciudadano</button>
            </form>

            <hr>
            <form action="SvCiudadanos" method="GET">
                <button type="submit" class="btn btn-primary">Ver ciudadanos</button>
            </form>

            <br>
            <!-- Resultados en tabla CIUDADANOS -->
            <div class="results-table">
                <% if (request.getAttribute("ciudadanos") != null) { %>
                    <h3>Ciudadanos registrados:</h3>
                    <table class="table">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Nombre</th>
                                <th>Email</th>
                            </tr>
                        </thead>
                        <tbody>
                            <% for (Ciudadano ciudadano : (List<Ciudadano>) request.getAttribute("ciudadanos")) { %>
                                <tr>
                                    <td><%= ciudadano.getId() %></td>
                                    <td><%= ciudadano.getNombre() %></td>
                                    <td><%= ciudadano.getEmail() %></td>
                                </tr>
                            <% } %>
                        </tbody>
                    </table>
                <% } %>
            </div>

            <hr>
            <!-- Formulario para agregar Turno -->
            <h2>Formulario Turno</h2>
            <form action="SvTurnos" method="POST">
                <div class="form-group">
                    <label for="numero">Número de Turno:</label>
                    <input type="number" class="form-control" id="numero_turno" name="numero_turno">
                </div>
                <div class="form-group">
                    <label for="fecha">Fecha:</label>
                    <input type="date" class="form-control" id="fecha_turno" name="fecha_turno">
                </div>
                <div class="form-group">
                    <label for="descripcion">Descripción:</label>
                    <input type="text" class="form-control" id="descripcion_turno" name="descripcion_turno">
                </div>
                <div class="form-group">
                    <label for="estado">Estado:</label>
                    <select class="form-control" id="estado_turno" name="estado_turno">
                        <option value="EN_ESPERA">En espera</option>
                        <option value="YA_ATENDIDO">Ya atendido</option>
                    </select>
                </div>
                <div class="form-group">
                    <label for="ciudadanoId">ID del Ciudadano:</label>
                    <input type="number" class="form-control" id="ciudadano_id" name="ciudadano_id">
                </div>
                <button type="submit" class="btn btn-primary">Guardar Turno</button>
            </form>

            <hr>
            <form action="SvTurnos" method="GET">
                <button type="submit" class="btn btn-primary">Ver turnos</button>
            </form>

            <br>
            <!-- Resultados en tabla TURNOS -->
            <div class="results-table">
                <% if (request.getAttribute("turnos") != null) { %>
                    <h3>Turnos registrados:</h3>
                    <table class="table">
                        <thead>
                            <tr>
                                <th>Número</th>
                                <th>Fecha</th>
                                <th>Descripción</th>
                                <th>Estado</th>
                                <th>Ciudadano</th>
                            </tr>
                        </thead>
                        <tbody>
                            <% for (Turno turno : (List<Turno>) request.getAttribute("turnos")) { %>
                                <tr>
                                    <td><%= turno.getNumero() %></td>
                                    <td><%= turno.getFecha() %></td>
                                    <td><%= turno.getDescripcion() %></td>
                                    <td><%= turno.getEstado() %></td>
                                    <td><%= turno.getCiudadano().getNombre() %></td>
                                </tr>
                            <% } %>
                        </tbody>
                    </table>
                <% } %>
            </div>
        </div>
    </body>
</html>
