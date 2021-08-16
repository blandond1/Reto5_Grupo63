package model.dao;

//Estructura de datos
import java.util.ArrayList;

//Librerías para SQL y Base de Datos
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

//Clase para conexión
import util.JDBCUtilities;

//Encapsulamiento de los datos
import model.vo.Requerimiento1;

public class Requerimiento1Dao {

    public ArrayList<Requerimiento1> rankingProyectosComprasDescendente10() throws SQLException {

        ArrayList<Requerimiento1> respuesta = new ArrayList<Requerimiento1>();
        Connection conexion = JDBCUtilities.getConnection();

        try {

            String consulta = "SELECT c.ID_Proyecto, \n"
                    + "p.Clasificacion, \n"
                    + "SUM(c.Cantidad*m.Precio_Unidad) AS Gasto_Compras, \n"
                    + "p.Serial \n"
                    + "FROM Compra c \n"
                    + "JOIN MaterialConstruccion m \n"
                    + "ON c.ID_MaterialConstruccion = m.ID_MaterialConstruccion \n"
                    + "JOIN Proyecto p \n"
                    + "ON p.ID_Proyecto = c.ID_Proyecto \n"
                    + "GROUP BY c.ID_Proyecto \n"
                    + "ORDER BY Gasto_Compras \n"
                    + "LIMIT 5 ";

            PreparedStatement statement = conexion.prepareStatement(consulta);
            ResultSet resultSet = statement.executeQuery();

            //Recorrer los registros en los VO específicos
            while (resultSet.next()) {
                Requerimiento1 proyectoRankeadoCompras = new Requerimiento1();
                proyectoRankeadoCompras.setIdProyecto(resultSet.getInt("ID_Proyecto"));
                //Codigo
                proyectoRankeadoCompras.setClasificacion(resultSet.getString("Clasificacion"));
                proyectoRankeadoCompras.setGastoCompras(resultSet.getDouble("Gasto_Compras"));
                proyectoRankeadoCompras.setSerial(resultSet.getString("Serial"));

                //Se agrega cada registro como un objeto del ArrayList que contiene la consulta
                respuesta.add(proyectoRankeadoCompras);
            }

            resultSet.close();
            statement.close();

        } catch (SQLException e) {
            System.err.println("Error consultando ranking de los 10 proyectos con mayor gasto por compras: " + e);
        } finally {
            if (conexion != null) {
                conexion.close();
            }
        }

        //Retornar la colección de vo's
        return respuesta;

    }

}
