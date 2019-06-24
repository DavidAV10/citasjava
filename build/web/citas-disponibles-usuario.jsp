<%@page import="uml.Cita"%>
<%@page import="uml.Doctor"%>
<%@page import="controller.ControllerDoctor"%>
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
            ControllerDoctor cd = new ControllerDoctor();
            List<Cita> lista = cc.filtrar("cita_pedida", "0");
        %>

        <table>
            <thead>
                <tr>
                    <th>Lugar</th>
                    <th>Hora</th>
                    <th>Tipo Cita</th>
                    <th>Consultorio</th>
                    <th>Nombre Doctor</th>
                </tr>
            </thead>
            <tbody>
                <% for (Cita c : lista) {%>
                <tr>
                    <td><%= c.getLugar()%></td>
                    <td><%= c.getHora()%></td>
                    <td><%= c.getTipoCita()%></td>
                    <td><%= c.getConsultorio()%></td>
                    <% Doctor temp = cd.filtrar("cedula", Integer.toString(c.getCodigoDoctor())).get(0);%>
                    <td><%= temp.getNombres() + " " + temp.getApellidos()%></td>
                </tr>
                <%}%>
            </tbody>
        </table>

        <%}%>
    </body>
</html>
