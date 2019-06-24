package model.dao;

import uml.Cita;
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

public class CitaDAO implements DataAccessObject<Cita> {

    @Override
    public void insertar(Cita obj) {
        String sql = "call citas.sp_ins_citas_cita(?, ?, ?, ?, ?, ?, ?, ?)";
        Conexion conn = new Conexion();

        try (PreparedStatement stmt = conn.getConnection().prepareStatement(sql)) {
            stmt.setInt(1, obj.getCodigoCita());
            stmt.setString(2, obj.getLugar());
            stmt.setString(3, obj.getHora());
            stmt.setString(4, obj.getTipoCita());
            stmt.setInt(5, obj.getConsultorio());
            stmt.setBoolean(6, obj.isCitaPedida());

            if (obj.getCodigoUsuario() != 0) {
                stmt.setInt(7, obj.getCodigoUsuario());
            } else {
                stmt.setNull(7, java.sql.Types.INTEGER);
            }

            stmt.setInt(8, obj.getCodigoDoctor());
            stmt.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(CitaDAO.class.getName()).log(Level.SEVERE, null, e);
        }

        conn.desconectar();
    }

    @Override
    public void eliminar(Cita obj) {
        String sql = "call citas.sp_del_citas_cita(?)";
        Conexion conn = new Conexion();

        try (PreparedStatement stmt = conn.getConnection().prepareStatement(sql)) {
            stmt.setInt(1, obj.getCodigoCita());
            stmt.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(CitaDAO.class.getName()).log(Level.SEVERE, null, e);
        }

        conn.desconectar();
    }

    @Override
    public void modificar(Cita obj) {
        String sql = "call citas.sp_upd_citas_cita(?, ?, ?, ?, ?, ?, ?, ?)";
        Conexion conn = new Conexion();

        try (PreparedStatement stmt = conn.getConnection().prepareStatement(sql)) {
            stmt.setString(1, obj.getLugar());
            stmt.setString(2, obj.getHora());
            stmt.setString(3, obj.getTipoCita());
            stmt.setInt(4, obj.getConsultorio());
            stmt.setBoolean(5, obj.isCitaPedida());
            stmt.setInt(6, obj.getCodigoUsuario());
            stmt.setInt(7, obj.getCodigoDoctor());
            stmt.setInt(8, obj.getCodigoCita());
            stmt.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(CitaDAO.class.getName()).log(Level.SEVERE, null, e);
        }

        conn.desconectar();
    }

    @Override
    public List<Cita> getLista() {
        List<Cita> listaFuncionarios = new LinkedList<>();
        String sql = "call sp_sel_citas_cita()";
        Conexion conn = new Conexion();

        try (Statement st = conn.getConnection().createStatement()) {

            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {

                Cita cita = new Cita(rs.getInt("codigo_cita"), rs.getString("lugar"),
                        rs.getString("hora"), rs.getString("tipo_cita"), rs.getInt("consultorio"),
                        rs.getBoolean("cita_pedida"), rs.getInt("codigo_usuario"), rs.getInt("codigo_doctor"));

                listaFuncionarios.add(cita);
            }

        } catch (SQLException e) {
            Logger.getLogger(CitaDAO.class.getName()).log(Level.SEVERE, null, e);
        }

        conn.desconectar();
        return listaFuncionarios;
    }

    @Override
    public List<Cita> filtrar(String campo, String criterio) {
        List<Cita> lista = new LinkedList<>();
        String sql = "call citas.sp_fil_citas_cita(?, ?)";
        Conexion conn = new Conexion();

        try (PreparedStatement stmt = conn.getConnection().prepareStatement(sql)) { // Preparar consulta
            stmt.setString(1, campo);
            stmt.setString(2, criterio);

            ResultSet rs = stmt.executeQuery(); // Ejecutar consulta

            while (rs.next()) {

                Cita cita = new Cita(rs.getInt("codigo_cita"), rs.getString("lugar"),
                        rs.getString("hora"), rs.getString("tipo_cita"), rs.getInt("consultorio"),
                        rs.getBoolean("cita_pedida"), rs.getInt("codigo_usuario"), rs.getInt("codigo_doctor"));

                lista.add(cita);
            }

        } catch (SQLException e) {
            Logger.getLogger(CitaDAO.class.getName()).log(Level.SEVERE, null, e);
        }

        conn.desconectar();
        return lista;
    }

    public List<Cita> filtrarCitaPorCodigoUsuario(int criterio) {
        List<Cita> lista = new LinkedList<>();
        String sql = "call citas.sp_filcu_citas_cita(?)";
        Conexion conn = new Conexion();

        try (PreparedStatement stmt = conn.getConnection().prepareStatement(sql)) { // Preparar consulta
            stmt.setInt(1, criterio);

            ResultSet rs = stmt.executeQuery(); // Ejecutar consulta

            while (rs.next()) {

                Cita cita = new Cita(rs.getInt("codigo_cita"), rs.getString("lugar"),
                        rs.getString("hora"), rs.getString("tipo_cita"), rs.getInt("consultorio"),
                        rs.getBoolean("cita_pedida"), rs.getInt("codigo_usuario"), rs.getInt("codigo_doctor"));

                lista.add(cita);
            }

        } catch (SQLException e) {
            Logger.getLogger(CitaDAO.class.getName()).log(Level.SEVERE, null, e);
        }

        conn.desconectar();
        return lista;
    }

    public Cita filtrarPorCodigoCita(int criterio) {
        Cita cita = null;
        String sql = "call citas.sp_fil_citas_cita('codigo_cita', ?)";
        Conexion conn = new Conexion();

        try (PreparedStatement stmt = conn.getConnection().prepareStatement(sql)) { // Preparar consulta
            stmt.setInt(1, criterio);

            ResultSet rs = stmt.executeQuery(); // Ejecutar consulta

            if (rs.next()) {

                cita = new Cita(rs.getInt("codigo_cita"), rs.getString("lugar"),
                        rs.getString("hora"), rs.getString("tipo_cita"), rs.getInt("consultorio"),
                        rs.getBoolean("cita_pedida"), rs.getInt("codigo_usuario"), rs.getInt("codigo_doctor"));
            }

        } catch (SQLException e) {
            Logger.getLogger(CitaDAO.class.getName()).log(Level.SEVERE, null, e);
        }

        conn.desconectar();
        return cita;
    }

    @Override
    public int getNumeroRegistros() {
        String sql = "call citas.sp_cou_citas_cita()";
        int cantidadFilas = -1;
        Conexion conn = new Conexion();

        try (Statement st = conn.getConnection().createStatement()) {

            ResultSet rs = st.executeQuery(sql);
            rs.next();
            cantidadFilas = rs.getInt(1);

        } catch (SQLException e) {
            Logger.getLogger(CitaDAO.class.getName()).log(Level.SEVERE, null, e);
        }

        conn.desconectar();
        return cantidadFilas;
    }
}
