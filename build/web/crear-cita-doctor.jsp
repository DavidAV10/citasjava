<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Crear cita doctor</title>
    </head>
    <body>
        <%
            // Comprobar que la página haya pasado por un formulario de login
            if (session.getAttribute("sesion_iniciada") == null) {
        %>
        <h1>Por favor, inicie sesión para poder modificar la base de datos</h1>
        <%} else {%>
        <h1>Bienvenido <%= session.getAttribute("nombre_completo")%></h1>
        <a href="index.jsp?cerrar=true">Cerrar Sesión</a>
        <h1>Agendar Citas</h1>

        <form action="ServlCita" method="post">
            <label for="lugar_cita">Lugar:</label>
            <input type="text" id="lugar_cita" name="lugar_cita"><br>
            <label for="hora_cita">Hora:</label>
            <input type="text" id="hora_cita" name="hora_cita"><br>
            <label for="tipo_cita">Tipo Cita:</label>
            <input type="text" id="tipo_cita" name="tipo_cita"><br>
            <label for="consultorio">Consultorio</label>
            <select id="consultorio" name="consultorio">
                <option value="303">303</option>
                <option value="205">205</option>
                <option value="501">501</option>
                <option value="202">202</option>
                <option value="404">404</option>
            </select><br>

            <input type="submit" id="crear_cita" name="crear_cita" value="Agendar cita">
        </form>
        
        <button><a href="citas-disponibles-doctor.jsp">Ver citas agendadas</a></button>
        <%}%>
    </body>
</html>
