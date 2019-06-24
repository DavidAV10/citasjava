package model.dao;

import uml.Doctor;
import model.idao.DataAccessObject;
import conexion.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DoctorDAO implements DataAccessObject<Doctor> {

    @Override
    public void insertar(Doctor obj) {
        String sql = "CALL citas.sp_ins_citas_doctor(?, ?, ?, ?, ?, ?, ?, ?)";
        Conexion conn = new Conexion();

        try (PreparedStatement stmt = conn.getConnection().prepareStatement(sql)) {
            stmt.setInt(1, obj.getCedula());
            stmt.setString(2, obj.getNombres());
            stmt.setString(3, obj.getApellidos());
            stmt.setString(4, obj.getCorreo());
            stmt.setString(5, obj.getEspecialidad());
            stmt.setString(6, obj.getFechaNacimiento());
            stmt.setString(7, obj.getNombreUsuario());
            stmt.setString(8, obj.getClave());
            stmt.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(DoctorDAO.class.getName()).log(Level.SEVERE, null, e);
        }

        conn.desconectar();
    }

    @Override
    public void eliminar(Doctor obj) {
        String sql = "call citas.sp_del_citas_doctor(?)";
        Conexion conn = new Conexion();

        try (PreparedStatement stmt = conn.getConnection().prepareStatement(sql)) {
            stmt.setInt(1, obj.getCedula());
            stmt.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(DoctorDAO.class.getName()).log(Level.SEVERE, null, e);
        }

        conn.desconectar();
    }

    @Override
    public void modificar(Doctor obj) {
        String sql = "call citas.sp_upd_citas_doctor(?, ?, ?, ?, ?, ?, ?, ?)";
        Conexion conn = new Conexion();

        try (PreparedStatement stmt = conn.getConnection().prepareStatement(sql)) {
            stmt.setString(1, obj.getNombres());
            stmt.setString(2, obj.getApellidos());
            stmt.setString(3, obj.getCorreo());
            stmt.setString(4, obj.getEspecialidad());
            stmt.setString(5, obj.getFechaNacimiento());
            stmt.setString(6, obj.getNombreUsuario());
            stmt.setString(7, obj.getClave());
            stmt.setInt(8, obj.getCedula());
            stmt.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(DoctorDAO.class.getName()).log(Level.SEVERE, null, e);
        }

        conn.desconectar();
    }

    @Override
    public List<Doctor> getLista() {
        List<Doctor> listaFuncionarios = new LinkedList<>();
        String sql = "call citas.sp_sel_citas_doctor()";
        Conexion conn = new Conexion();

        try (Statement st = conn.getConnection().createStatement()) {

            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {

                Doctor doctor = new Doctor(rs.getInt("cedula"), rs.getString("nombres"),
                        rs.getString("apellidos"), rs.getString("correo"), rs.getString("especialidad"), rs.getString("fecha_nacimiento"),
                        rs.getString("nombre_usuario"), rs.getString("clave"));

                listaFuncionarios.add(doctor);
            }

        } catch (SQLException e) {
            Logger.getLogger(DoctorDAO.class.getName()).log(Level.SEVERE, null, e);
        }

        conn.desconectar();
        return listaFuncionarios;
    }

    @Override
    public List<Doctor> filtrar(String campo, String criterio) {
        List<Doctor> lista = new LinkedList<>();
        String sql = "call citas.sp_fil_citas_doctor(?, ?)";
        Conexion conn = new Conexion();

        try (PreparedStatement stmt = conn.getConnection().prepareStatement(sql)) { // Preparar consulta
            stmt.setString(1, campo);
            stmt.setString(2, criterio);

            ResultSet rs = stmt.executeQuery(); // Ejecutar consulta

            while (rs.next()) {

                Doctor doctor = new Doctor(rs.getInt("cedula"), rs.getString("nombres"),
                        rs.getString("apellidos"), rs.getString("correo"), rs.getString("especialidad"), rs.getString("fecha_nacimiento"),
                        rs.getString("nombre_usuario"), rs.getString("clave"));

                lista.add(doctor);
            }

        } catch (SQLException e) {
            Logger.getLogger(DoctorDAO.class.getName()).log(Level.SEVERE, null, e);
        }

        conn.desconectar();

        return lista;
    }

    public Doctor getDoctor(String nombreUsuario, String clave) {
        Doctor doctor = null;
        String sql = "call citas.sp_fildoc_citas_doctor(?, ?)";
        Conexion conn = new Conexion();

        try (PreparedStatement stmt = conn.getConnection().prepareStatement(sql)) { // Preparar consulta
            stmt.setString(1, nombreUsuario);
            stmt.setString(2, clave);

            ResultSet rs = stmt.executeQuery(); // Ejecutar consulta

            if (rs.next()) {

                doctor = new Doctor(rs.getInt("cedula"), rs.getString("nombres"), rs.getString("apellidos"),
                        null, null, null, rs.getString("nombre_usuario"), null);
            }

        } catch (SQLException e) {
            Logger.getLogger(DoctorDAO.class.getName()).log(Level.SEVERE, null, e);
        }

        conn.desconectar();
        return doctor;
    }

    @Override
    public int getNumeroRegistros() {
        String sql = "call sp_cou_citas_doctor()";
        int cantidadFilas = -1;
        Conexion conn = new Conexion();

        try (Statement st = conn.getConnection().createStatement()) {

            ResultSet rs = st.executeQuery(sql);
            rs.next();
            cantidadFilas = rs.getInt(1);

        } catch (SQLException e) {
            Logger.getLogger(DoctorDAO.class.getName()).log(Level.SEVERE, null, e);
        }

        conn.desconectar();
        return cantidadFilas;
    }
}
