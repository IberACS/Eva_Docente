/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sql_E;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author mac3
 */
public class Conexion {
        public Conexion() {
    }

//    se crea un metodo para crear un objeto de tipo Connection para conectarnos a la base de datos

    public Connection conexion_SCE2005() {
        String connectionUrl = "jdbc:sqlserver://172.16.1.1:1433;databaseName=SCE2005;user=EvaDocT; password=evaluaD17";
        // Declaramos los sioguientes objetos
        Connection con = null;
        try {
            //Establecemos la conexión
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl);
            //se establece el error esperado
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        //retornamos la conexion
        return con;
    }
      public static void main(String[] args) {
        Conexion c= new Conexion();
        Connection con;
        con=c.conexion_SCE2005();
        System.out.println("conexion : "+ c );
                
    }
    
}



