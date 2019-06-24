<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Formulario de Registro de Usuarios Comúnes</title>
    </head>
    <body>
        <form action="ServlUsuario" method="post">
            <label for="cod_usuario">Cédula:</label>
            <input type="number" id="cod_usuario" name="cod_usuario"><br>
            <label for="nombres">Nombres:</label>
            <input type="text" id="nombres" name="nombres"><br>
            <label for="ape_usuario">Apellidos:</label>
            <input type="text" id="ape_usuario" name="ape_usuario"><br>
            <label for="cor_usuario">Correo:</label>
            <input type="email" id="cor_usuario" name="cor_usuario"><br>
            <label for="fech_nac_usuario">Fecha de Nacimiento:</label>
            <input type="date" id="fech_nac_usuario" name="fech_nac_usuario"><br>

            <label for="nom_usuario">Nombre de Usuario:</label>
            <input type="text" id="nom_usuario" name="nom_usuario"><br>
            <label for="clave_usuario">Clave:</label>
            <input type="password" id="clave_usuario" name="clave_usuario"><br>

            <input type="submit" id="reg_usuario" name="reg_usuario" value="Registrar Usuario">
        </form>

    </body>
</html>
