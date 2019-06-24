<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Formulario de Registro de Doctores</title>
    </head>
    <body>
        <form action="ServlDoctor" method="post">
            <label for="cod_doctor">Cédula:</label>
            <input type="text" id="cod_doctor" name="cod_doctor"><br>
            <label for="nom_doctor">Nombres:</label>
            <input type="text" id="nom_doctor" name="nom_doctor"><br>
            <label for="ape_doctor">Apellidos:</label>
            <input type="text" id="ape_doctor" name="ape_doctor"><br>
            <label for="cor_doctor">Correo:</label>
            <input type="email" id="cor_doctor" name="cor_doctor"><br>
            <label for="especialidad">Especialidad:</label>
            <select id="especialidad" name="especialidad">
                <option value="Médico General">Médico General</option>
                <option value="Otorrinonaringolo">Otorrinonaringolo</option>
                <option value="Pediatra">Pediatra</option>
                <option value="Oftalmólogo">Oftalmólogo</option>
            </select><br>
            <label for="fech_nac_doctor">Fecha de Nacimiento:</label>
            <input type="date" id="fech_nac_doctor" name="fech_nac_doctor"><br>
            
            <label for="nom_usuario">Nombre de Usuario:</label>
            <input type="text" id="nom_usuario" name="nom_usuario"><br>
            <label for="clave_doctor">Clave:</label>
            <input type="password" id="clave_doctor" name="clave_doctor"><br>
            
            <input type="submit" value="Registrar Doctor" id="reg_doctor" name="reg_doctor">
        </form>

    </body>
</html>
