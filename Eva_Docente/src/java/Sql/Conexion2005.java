/**
 *
 * @author mac2
 */
package Sql;

import java.sql.*;


public class Conexion2005 {
    //creamos un constructor vacio
    public Conexion2005() {
    }
    //se crea un metodo para crear un objeto de tipo Connection para conectarnos a la base de datos
    public Connection conexion_SCE2005(){
        String connectionUrl = "jdbc:sqlserver://172.16.1.1;databaseName=SCE2005;user=EvaDocT;password=evaluaD17;";      
    // Declaramos los sioguientes objetos
    Connection con = null;
         try {
        //Establecemos la conexión
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        con = DriverManager.getConnection(connectionUrl);
        //se establece el error esperado
        }
    catch (ClassNotFoundException | SQLException e)
    {
        e.printStackTrace();
    }
         //retornamos la conexion
        return con;
 }   
    //se crea un metodo para cerrar cualquier conexion
    public void cerrar (Connection conexion){
        try {
            conexion.close();
        } catch (SQLException ex) {
            //Logger.getLogger(conexion_sql.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
}    