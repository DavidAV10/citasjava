<%@page import="uml.Cita"%>
<%@page import="controller.ControllerCita"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Citas Pedidas</title>
    </head>
    <body>
        <%
            // Comprobar que la página haya pasado por un formulario de login
            if (session.getAttribute("sesion_iniciada") == null) {
        %>
        <h1>Por favor, inicie sesión para poder modificar la base de datos</h1>
        <%} else {
            ControllerCita cc = new ControllerCita();
            List<Cita> lista = cc.filtrarCitaPorCodigoUsuario((int) session.getAttribute("cedula"));
        %>
        <h1>Bienvenido <%= session.getAttribute("nombre_completo")%></h1>
        <a href="index.jsp?cerrar=true">Cerrar Sesión</a>

        <table>
            <thead>
                <tr>
                    <th>Código Cita</th>
                    <th>Lugar</th>
                    <th>Hora</th>
                    <th>Tipo Cita</th>
                    <th>Consultorio</th>
                    <th>Código Usuario</th>
                    <th>Código Doctor</th>
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
                    <td><%= c.getCodigoUsuario()%></td>
                    <td><%= c.getCodigoDoctor()%></td>
                </tr>
                <%}%>
            </tbody>
        </table>
        <%}%>
    </body>
</html>
