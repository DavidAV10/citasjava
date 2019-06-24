package model.dao;

public class Acceso {

    public Object validarCuenta(String usuario, String clave) {  
        DoctorDAO ddao = new DoctorDAO();
        Object persona = ddao.getDoctor(usuario, clave);

        if (persona == null) {
            UsuarioDAO udao = new UsuarioDAO();
            persona = udao.getUsuario(usuario, clave);
        }
        return persona;
    }
}
