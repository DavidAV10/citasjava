package uml;

public class Cita {
    
    private int codigoCita;
    private String lugar;
    private String hora;
    private String tipoCita;
    private int consultorio;
    private boolean citaPedida;
    private int codigoUsuario;
    private int codigoDoctor;

    public Cita(int codigoCita, String lugar, String hora, String tipoCita, int consultorio, boolean citaPedida, int codigoUsuario, int codigoDoctor) {
        this.codigoCita = codigoCita;
        this.lugar = lugar;
        this.hora = hora;
        this.tipoCita = tipoCita;
        this.consultorio = consultorio;
        this.citaPedida = citaPedida;
        this.codigoUsuario = codigoUsuario;
        this.codigoDoctor = codigoDoctor;
    }

    public int getCodigoCita() {
        return codigoCita;
    }

    public void setCodigoCita(int codigoCita) {
        this.codigoCita = codigoCita;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getTipoCita() {
        return tipoCita;
    }

    public void setTipoCita(String tipoCita) {
        this.tipoCita = tipoCita;
    }

    public int getConsultorio() {
        return consultorio;
    }

    public void setConsultorio(int consultorio) {
        this.consultorio = consultorio;
    }

    public boolean isCitaPedida() {
        return citaPedida;
    }

    public void setCitaPedida(boolean citaPedida) {
        this.citaPedida = citaPedida;
    }

    public int getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(int codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    public int getCodigoDoctor() {
        return codigoDoctor;
    }

    public void setCodigoDoctor(int codigoDoctor) {
        this.codigoDoctor = codigoDoctor;
    }
}
