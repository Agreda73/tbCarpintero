/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.UUID;
import java.sql.Statement;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.UUID;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import vista.AgregarCarpintero;



/**
 *
 * @author stanl
 */
public class AgrgarCarpintero {
    private String mUUID;

    public String getUUID() {
        return mUUID;
    }

    public void setUUID(String UUID) {
        this.mUUID = UUID;
    }
    private String nombre;
    private int peso;
    private int edad;
    private String correo;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
        public void Guardar() {
        //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conecxion = Conexion.getConexion();
        try {
            //Creamos el PreparedStatement que ejecutará la Query
            PreparedStatement addProducto = conecxion.prepareStatement("insert into tbCarpintero (UUID_Carpintero,Nombre_Carpintero,Edad_Carpintero,Peso_Carpintero,Correo_Carpintero)\n" +
            "values (?,?,?,?,?)");
            //Establecer valores de la consulta SQL
            addProducto.setString(1, UUID.randomUUID().toString());
            addProducto.setString(2, getNombre());
            addProducto.setInt(3, getEdad());
            addProducto.setInt(4, getPeso());
            addProducto.setString(5, getCorreo());

            //Ejecutar la consulta
            addProducto.executeUpdate();
 
        } catch (SQLException ex) {
            System.out.println("este es el error en el modelo:metodo guardar " + ex);
        }
    }
        public void Eliminar(JTable tabla) {
        //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conecxion = Conexion.getConexion();

        //obtenemos que fila seleccionó el usuario
        int filaSeleccionada = tabla.getSelectedRow();
        //Obtenemos el id de la fila seleccionada
        String miId = tabla.getValueAt(filaSeleccionada, 0).toString();
        
        //borramos 
        try {
            PreparedStatement deleteCarpintero = conecxion.prepareStatement("delete from tbCarpintero where UUID_Carpintero = ?");
            deleteCarpintero.setString(1, miId);
            deleteCarpintero.executeUpdate();
        } catch (Exception e) {
            System.out.println("este es el error metodo de eliminar" + e);
        }
    }
        public void cargarDatosTabla(AgregarCarpintero vista) {
        // Obtén la fila seleccionada 
        int filaSeleccionada = vista.jtCarpinteros.getSelectedRow();

        // Debemos asegurarnos que haya una fila seleccionada antes de acceder a sus valores
        if (filaSeleccionada != -1) {
            String UUIDDeTb = vista.jtCarpinteros.getValueAt(filaSeleccionada, 0).toString();
            String NombreDeCarp = vista.jtCarpinteros.getValueAt(filaSeleccionada, 1).toString();
            String EdadCarp = vista.jtCarpinteros.getValueAt(filaSeleccionada, 2).toString();
            String PesoCarp = vista.jtCarpinteros.getValueAt(filaSeleccionada, 3).toString();
            String CorreoCarp = vista.jtCarpinteros.getValueAt(filaSeleccionada, 4).toString();

            // Establece los valores en los campos de texto
            vista.txtNombre.setText(NombreDeCarp);
            vista.txtEdad.setText(EdadCarp);
            vista.txtPeso.setText(PesoCarp);
            vista.txtCorreo.setText(CorreoCarp);

        }
    }
             public void Mostrar(JTable tabla) {
        //Creamos una variable de la clase de conexion
        Connection conexion = Conexion.getConexion();
        //Definimos el modelo de la tabla
        DefaultTableModel modeloDeDatos = new DefaultTableModel();

        modeloDeDatos.setColumnIdentifiers(new Object[]{"UUID_Carpintero", "Nombre_Carpintero", "Edad_Carpintero", "Peso_Carpintero","Correo_Carpintero"});
        try {
            
            //Creamos un Statement
            Statement statement = conexion.createStatement();
            //Ejecutamos el Statement con la consulta y lo asignamos a una variable de tipo ResultSet
            ResultSet rs = statement.executeQuery("SELECT * FROM tbCarpintero");
            //Recorremos el ResultSet
            while (rs.next()) {
                //Llenamos el modelo por cada vez que recorremos el resultSet
                modeloDeDatos.addRow(new Object[]{rs.getString("UUID_Carpintero"), 
                    rs.getString("Nombre_Carpintero"), 
                    rs.getInt("Edad_Carpintero"), 
                    rs.getInt("Peso_Carpintero"),
                    rs.getString("Correo_Carpintero")
                       
                });
            }
            //Asignamos el nuevo modelo lleno a la tabla
            tabla.setModel(modeloDeDatos);
        } catch (Exception e) {
            System.out.println("Este es el error en el modelo, metodo mostrar " + e);
        }
    }    
              public void Actualizar(JTable tabla) {
        //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = Conexion.getConexion();
        //obtenemos que fila seleccionó el usuario
        int filaSeleccionada = tabla.getSelectedRow();
        if (filaSeleccionada != -1) {
            //Obtenemos el id de la fila seleccionada
            String mUUId = tabla.getValueAt(filaSeleccionada, 0).toString();
            try { 
                //Ejecutamos la Query
                PreparedStatement updateUser = conexion.prepareStatement("update tbCarpintero set Nombre_Carpintero=?,Edad_Carpintero=?,Peso_Carpintero = ?,Correo_Carpintero=? where UUID_Carpintero = ?");
                updateUser.setString(1, getNombre());
                updateUser.setInt(2, getEdad());
                updateUser.setInt(3, getPeso());
                updateUser.setString(4, getCorreo());
                updateUser.setString(5, mUUid());

                updateUser.executeUpdate();

            } catch (Exception e) {
                System.out.println("este es el error en el metodo de actualizar" + e);
            }
        } else {
            System.out.println("no");
        }
    }

}
