package uml;

public class Doctor extends Usuario {

    private String especialidad;

    public Doctor(int cedula, String nombres, String apellidos, String correo, String especialidad, String fechaNacimiento, String nombreUsuario, String clave) {
        super(cedula, nombres, apellidos, correo, fechaNacimiento, nombreUsuario, clave);
        this.especialidad = especialidad;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }
}
