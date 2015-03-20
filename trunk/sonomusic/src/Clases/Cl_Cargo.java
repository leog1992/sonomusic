/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Dereck
 */
public class Cl_Cargo {
    Cl_Conectar con = new Cl_Conectar();
    private int id;
    private String descripcion;

    public Cl_Cargo(int id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

    public Cl_Cargo() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion.toUpperCase();
    }
    public int mi_cargo (String user) {
        int cargo = 0;
        try {
        Statement st = con.conexion();
        String mi_cargo = "select e.dni, e.nom_per, c.tipo_cargo, e.idCargo from empleados as e inner join cargo as c "
                + "on e.idCargo=c.idCargo where e.dni = '"+user+"'";
        ResultSet rs = con.consulta(st, mi_cargo);
        if (rs.next()) {
            cargo = rs.getInt("idCargo");
        }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return cargo;
    }
    
    
}
