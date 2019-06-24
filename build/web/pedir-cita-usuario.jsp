<%@page import="uml.Doctor"%>
<%@page import="controller.ControllerDoctor"%>
<%@page import="uml.Cita"%>
<%@page import="controller.ControllerCita"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pedir Citas</title>
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

        <form action="ServlCita" method="post">
            <label for="cita_disponible">Seleccione una cita</label>
            <%
                ControllerCita cc = new ControllerCita();
                ControllerDoctor cdoctor = new ControllerDoctor();
                List<Cita> lista = cc.filtrar("cita_pedida", "0");
            %>
            <select id="cita_disponible" name="cita_disponible">
                <% for (Cita c : lista) {
                        Doctor temp = cdoctor.filtrar("cedula", Integer.toString(c.getCodigoDoctor())).get(0);
                %>
                <option value="<%= c.getCodigoCita()%>"><%= c.getHora() + " " + c.getLugar() + " " + temp.getNombreUsuario()%></option>
                <%}%>
            </select><br>

            <input type="submit" id="ped_cita" name="ped_cita" value="Pedir cita">
        </form>

        <button><a href="citas-pedidas-usuario.jsp">Ver citas pedidas</a></button><br>
        <button><a href="citas-disponibles-usuario.jsp">Ver citas disponibles</a></button>

        <%}%>
    </body>
</html>
