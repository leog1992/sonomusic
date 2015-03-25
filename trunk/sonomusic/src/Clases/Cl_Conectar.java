/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author Administrador
 */
public class Cl_Conectar {

    private static Connection conexion = null;
    //conmetal_sm
    //sonomusic
    private static String bd = "sonomusic"; // Nombre de BD.
    //conmetal_root
    //lspserver
    private static String user = "lspserver"; // Usuario de BD.
    //root/*123
    private static String password = "root/*123"; // Password de BD.
    // Driver para MySQL en este caso.
    private static String driver = "com.mysql.jdbc.Driver";
    // Ruta del servidor.
    //208.43.223.210
    //lunasystemsperu.com
    String server = "jdbc:mysql://192.168.1.39:3306/" + bd;//10.42.0.1

    // String server = "jdbc:mysql://"+leer(
    private String leer() {
        String server = null;
        try {
            File Ffichero = new File("server.txt");

            /*Si existe el fichero*/
            if (Ffichero.exists()) {
                /*Abre un flujo de lectura a el fichero*/
                BufferedReader Flee = new BufferedReader(new FileReader(Ffichero));
                String Slinea;
                //System.out.println("**********Leyendo Fichero***********");  
                /*Lee el fichero linea a linea hasta llegar a la ultima*/
                while ((Slinea = Flee.readLine()) != null) {
                    /*Imprime la linea leida*/
                    server = Slinea;
                    // System.out.println(Slinea);                
                }
                //System.out.println("*********Fin Leer Fichero**********");  
                /*Cierra el flujo*/
                Flee.close();
                //return txt_alm;
            } else {
                System.out.println("Fichero No Existe");
            }
        } catch (IOException ex) {
            /*Captura un posible error y le imprime en pantalla*/
            System.out.println(ex.getMessage());
        }
        return server;
    }

    /**
     * Método neecesario para conectarse al Driver y poder usar MySQL.
     */
    public void conectar() {
        try {

            Class.forName(driver);
            conexion = DriverManager.getConnection(server, user, password);

        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: Imposible realizar la conexion a BD." + server + "," + user + "," + password);
            JOptionPane.showMessageDialog(null, e.getLocalizedMessage());
            System.out.print(e);
            e.printStackTrace();
            System.exit(0);

        }
    }

    /**
     * Método para establecer la conexión con la base de datos.
     *
     * @return
     */
    public Connection conx() {
        return conexion;
    }

    public Statement conexion() {
        Statement st = null;
        try {
            st = conexion.createStatement();
        } catch (SQLException e) {
            System.out.println("Error: Conexión incorrecta.");
            e.printStackTrace();
        }
        return st;
    }

    /**
     * Método para realizar consultas del tipo: SELECT * FROM tabla WHERE..."
     *
     * @param st
     * @param cadena La consulta en concreto
     * @return
     */
    public ResultSet consulta(Statement st, String cadena) {
        ResultSet rs = null;
        try {
            rs = st.executeQuery(cadena);
        } catch (SQLException e) {
            System.out.println("Error con: " + cadena);
            System.out.println("SQLException: " + e.getMessage());
            e.printStackTrace();
        }
        return rs;
    }

    /**
     * Método para realizar consultas de actualización, creación o eliminación.
     *
     * @param st
     * @param cadena La consulta en concreto
     * @return
     */
    public int actualiza(Statement st, String cadena) {
        int rs = -1;
        try {
            rs = st.executeUpdate(cadena);
        } catch (SQLException e) {
            System.out.println("Error con: " + cadena);
            System.out.println("SQLException: " + e.getMessage());
            e.printStackTrace();
        }
        return rs;
    }

    /**
     * Método para cerrar la consula
     *
     * @param rs
     */
    public void cerrar(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (Exception e) {
                System.out.print("Error: No es posible cerrar la consulta.");
            }
        }
    }

    /**
     * Método para cerrar la conexión.
     *
     * @param st
     */
    public void cerrar(java.sql.Statement st) {
        if (st != null) {
            try {
                st.close();
            } catch (Exception e) {
                System.out.print("Error: No es posible cerrar la conexión.");
            }
        }
    }
}
