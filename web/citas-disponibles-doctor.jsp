<%@page import="uml.Cita"%>
<%@page import="controller.ControllerCita"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Citas Disponibles</title>
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
        <h2>Citas disponibles día <script>var fecha = new Date();
            document.write(fecha.getDay() + "/" + fecha.getMonth() + "/" + fecha.getFullYear());</script></h2>


        <%
            ControllerCita cc = new ControllerCita();
            List<Cita> lista = cc.filtrar("codigo_doctor", Integer.toString((int) session.getAttribute("cedula")));
        %>

        <table>
            <thead>
                <tr>
                    <th>Código Cita</th>
                    <th>Lugar</th>
                    <th>Hora</th>
                    <th>Tipo Cita</th>
                    <th>Consultorio</th>
                </tr>
            </thead>
            <tbody>
                <% for (Cita c : lista) {%>
                <tr>
                    <td><%= c.getCodigoCita()%></td>
                    <td><%= c.getLugar()%></td>
                    <td><%= c.getHora()%></td>
                    <td><%= c.getTipoCita()%></td>
                    <td><%= c.getConsultorio()%></td>
                </tr>
                <%}%>
            </tbody>
        </table>

        <%}%>
    </body>
</html>
