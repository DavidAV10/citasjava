package controller;

import java.util.List;
import uml.Doctor;
import model.dao.DoctorDAO;
import model.idao.DataAccessObject;

public class ControllerDoctor {
    
    public List<Doctor> getLista() {
        DataAccessObject<Doctor> dao = new DoctorDAO();
        List<Doctor> doctores = dao.getLista();
        return doctores;
    }
    
    public List<Doctor> filtrar(String campo, String criterio) {
        DataAccessObject<Doctor> dao = new DoctorDAO();
        List<Doctor> filtroDoctores = dao.filtrar(campo, criterio);
        return filtroDoctores;
    }
}
