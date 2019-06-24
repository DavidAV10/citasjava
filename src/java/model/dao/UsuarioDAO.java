package model.dao;

import uml.Usuario;
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

public class UsuarioDAO implements DataAccessObject<Usuario> {

    @Override
    public void insertar(Usuario obj) {
        String sql = "CALL citas.sp_ins_citas_usuario(?, ?, ?, ?, ?, ?, ?)";
        Conexion conn = new Conexion();

        try (PreparedStatement stmt = conn.getConnection().prepareStatement(sql)) {
            stmt.setInt(1, obj.getCedula());
            stmt.setString(2, obj.getNombres());
            stmt.setString(3, obj.getApellidos());
            stmt.setString(4, obj.getCorreo());
            stmt.setString(5, obj.getFechaNacimiento());
            stmt.setString(6, obj.getNombreUsuario());
            stmt.setString(7, obj.getClave());
            stmt.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, e);
        }

        conn.desconectar();
    }

    @Override
    public void eliminar(Usuario obj) {
        String sql = "call citas.sp_del_citas_usuario(?)";
        Conexion conn = new Conexion();

        try (PreparedStatement stmt = conn.getConnection().prepareStatement(sql)) {
            stmt.setInt(1, obj.getCedula());
            stmt.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, e);
        }

        conn.desconectar();
    }

    @Override
    public void modificar(Usuario obj) {
        String sql = "call citas.sp_upd_citas_usuario(?, ?, ?, ?, ?, ?, ?)";
        Conexion conn = new Conexion();

        try (PreparedStatement stmt = conn.getConnection().prepareStatement(sql)) {
            stmt.setString(1, obj.getNombres());
            stmt.setString(2, obj.getApellidos());
            stmt.setString(3, obj.getCorreo());
            stmt.setString(4, obj.getFechaNacimiento());
            stmt.setString(5, obj.getNombreUsuario());
            stmt.setString(6, obj.getClave());
            stmt.setInt(7, obj.getCedula());
            stmt.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, e);
        }

        conn.desconectar();
    }

    @Override
    public List<Usuario> getLista() {
        List<Usuario> listaFuncionarios = new LinkedList<>();
        String sql = "call citas.sp_sel_citas_usuario()";
        Conexion conn = new Conexion();

        try (Statement st = conn.getConnection().createStatement()) {

            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {

                Usuario usuario = new Usuario(rs.getInt("cedula"), rs.getString("nombres"),
                        rs.getString("apellidos"), rs.getString("correo"), rs.getString("fecha_nacimiento"),
                        rs.getString("nombre_usuario"), rs.getString("clave"));

                listaFuncionarios.add(usuario);
            }

        } catch (SQLException e) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, e);
        }

        conn.desconectar();
        return listaFuncionarios;
    }

    @Override
    public List<Usuario> filtrar(String campo, String criterio) {
        List<Usuario> lista = new LinkedList<>();
        String sql = "call citas.sp_fil_citas_usuario(?, ?);";
        Conexion conn = new Conexion();

        try (PreparedStatement stmt = conn.getConnection().prepareStatement(sql)) { // Preparar consulta
            stmt.setString(1, campo);
            stmt.setString(2, criterio);

            ResultSet rs = stmt.executeQuery(); // Ejecutar consulta

            while (rs.next()) {

                Usuario usuario = new Usuario(rs.getInt("cedula"), rs.getString("nombres"),
                        rs.getString("apellidos"), rs.getString("correo"), rs.getString("fecha_nacimiento"),
                        rs.getString("nombre_usuario"), rs.getString("clave"));

                lista.add(usuario);
            }

        } catch (SQLException e) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, e);
        }

        conn.desconectar();
        return lista;
    }

    public Usuario getUsuario(String nombreUsuario, String clave) {
        Usuario usuario = null;
        String sql = "call citas.sp_filusu_citas_usuario(?, ?)";
        Conexion conn = new Conexion();

        try (PreparedStatement stmt = conn.getConnection().prepareStatement(sql)) { // Preparar consulta
            stmt.setString(1, nombreUsuario);
            stmt.setString(2, clave);

            ResultSet rs = stmt.executeQuery(); // Ejecutar consulta

            if (rs.next()) {

                usuario = new Usuario(rs.getInt("cedula"), rs.getString("nombres"), rs.getString("apellidos"),
                        null, null, rs.getString("nombre_usuario"), null);
            }

        } catch (SQLException e) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, e);
        }

        conn.desconectar();
        return usuario;
    }

    @Override
    public int getNumeroRegistros() {
        String sql = "call citas.sp_cou_citas_usuario()";
        int cantidadFilas = -1;
        Conexion conn = new Conexion();

        try (Statement st = conn.getConnection().createStatement()) {

            ResultSet rs = st.executeQuery(sql);
            rs.next();
            cantidadFilas = rs.getInt(1);

        } catch (SQLException e) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return cantidadFilas;
    }
}
