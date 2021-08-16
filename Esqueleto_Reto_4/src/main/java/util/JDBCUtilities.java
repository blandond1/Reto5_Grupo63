package util;

import java.io.File;//Para verificación de longitud de base de datos


import java.sql.*;

public class JDBCUtilities {

    //Atributos de clase para gestión de conexión con la base de datos    
    private static final String UBICACION_BD = "ProyectosConstruccion.db";

    String url = "jdbc:sqlite:" + UBICACION_BD;
    
    Connection conn = null;
      
    public static Connection getConnection() throws SQLException {
        String url = "jdbc:sqlite:" + UBICACION_BD;        
        return DriverManager.getConnection(url);
    }
    public JDBCUtilities(){
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(url);
            System.out.println("Conexi9on Exitosa");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public ResultSet consultarRegistros(String url){
        try {
            PreparedStatement pstm = conn.prepareStatement(url);
            ResultSet respuesta = pstm.executeQuery();
            return respuesta;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
    
}
