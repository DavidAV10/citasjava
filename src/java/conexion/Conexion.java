package conexion;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexion {

    /* private static String usuario = "Rotzeh";
    private static String clave = "J0rg%N0M0l3st#";
    private static String servidor = "localhost";
    private static String bbdd = "citas";
    private static String url = "jdbc:mysql://localhost:3311/" + bbdd; */
    private static Connection conexion = null;

    public Conexion() {
        if (conexion == null) {
            try {
                Properties propiedadesBBDD = new Properties();
                try {
                    propiedadesBBDD.load(getClass().getResourceAsStream("Configuration.properties"));
                } catch (IOException ex) {
                    Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
                }

                String usuario = propiedadesBBDD.getProperty("USERNAME");
                String clave = propiedadesBBDD.getProperty("PASSWORD");
                String host = propiedadesBBDD.getProperty("HOST");
                String bbdd = propiedadesBBDD.getProperty("DATABASE");

                String url = "jdbc:mysql://" + host + "/" + bbdd;

                Class.forName("com.mysql.jdbc.Driver");
                conexion = DriverManager.getConnection(url, usuario, clave);
            } catch (ClassNotFoundException ec) {
                System.err.println("classnotfound");
                Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ec);
            } catch (SQLException es) {
                System.err.println("Error de enlace canal");
                Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, es);
            }
        }
    }

    public Connection getConnection() {
        return conexion;
    }

    public void desconectar() {
        if (conexion != null) {
            try {
                conexion.close();
            } catch (SQLException e) {
                Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, e);
            }
            conexion = null;
        }
    }
}
