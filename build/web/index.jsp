<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true"%>

<%

    if (request.getParameter("cerrar") != null) {
        session.invalidate();
    } else {
        Boolean comprobar = (Boolean) session.getAttribute("sesion_iniciada");

        // Esta condición se cumple en caso que el usuario inicie sesión
        if (comprobar != null) {
            boolean esDoctor = (boolean) session.getAttribute("es_doctor");
            if (esDoctor) {
                response.sendRedirect("crear-cita-doctor.jsp");
            } else {
                response.sendRedirect("pedir-cita-usuario.jsp");
            }
        }
    }

%>

<!DOCTYPE html>
<html>
    <head>
        <title>Aplicación de Citas Médicas</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <form action="ServlSesion" method="post">
            <label for="nom_usu">Nombre de usuario:</label>
            <input type="text" id="nom_usu" name="nom_usu"><br>
            <label for="clave">Contraseña:</label>
            <input type="password" id="clave" name="clave"><br>
            <input type="submit" id="btn_iniciar" name="btn_iniciar" value="Iniciar Sesión">
        </form>

        <br>
        <br>

        <button><a href="registrar-usuario.jsp">Registrar usuario</a></button>
        <button><a href="registrar-doctor.jsp">Registrar doctor</a></button>
    </body>
</html>