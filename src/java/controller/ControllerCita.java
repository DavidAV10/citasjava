package controller;

import java.util.List;
import uml.Cita;
import model.dao.CitaDAO;
import model.idao.DataAccessObject;

public class ControllerCita {
    
    public List<Cita> getLista() {
        DataAccessObject<Cita> dao = new CitaDAO();
        List<Cita> citas = dao.getLista();
        return citas;
    }
    
    public List<Cita> filtrar(String campo, String criterio) {
        DataAccessObject<Cita> dao = new CitaDAO();
        List<Cita> filtroCitas = dao.filtrar(campo, criterio);
        return filtroCitas;
    }
    
    public List<Cita> filtrarCitaPorCodigoUsuario(int criterio) {
        CitaDAO dao = new CitaDAO();
        List<Cita> filtroCitas = dao.filtrarCitaPorCodigoUsuario(criterio);
        return filtroCitas;
    }
}
