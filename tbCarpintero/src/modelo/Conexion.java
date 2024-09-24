/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author stanl
 */
public class Conexion {
         private static final String URL = "jdbc:oracle:10.10.2.63:@localhost:1521:xe";
    private static final String USUARIO = "system";
    private static final String CONTRASENA = "fakedrips";
    public static Connection getConexion() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection conexion = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
            return conexion;
        } catch (SQLException e) {
            System.out.println("Hubo un error" + e);
              return null;
        } catch (ClassNotFoundException ex) {
            System.out.println("La clase presentó un error" + ex);
              return null;
        }
    }
}
