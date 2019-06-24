<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Proceso exitoso</title>
    </head>
    <body>
        <h1>¡Proceso Exitoso!</h1>
        <% if (session.getAttribute("sesion_iniciada") != null) {%>
        <h1>Bienvenido <%= session.getAttribute("nombre_completo")%></h1>
        <a href="index.jsp?cerrar=true">Cerrar Sesión</a>
        <%}%>
    </body>
</html>
