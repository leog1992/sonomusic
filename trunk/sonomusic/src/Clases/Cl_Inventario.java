/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;

/**
 *
 * @author luis-d
 */
public class Cl_Inventario {

    Cl_Conectar con = new Cl_Conectar();
    Cl_Varios ven = new Cl_Varios();

    private int id;
    private int anio;
    private int almacen;
    private String fecha_ejecucion;
    private String responsable;
    private String usuario;
    private String tipo;

    public Cl_Inventario() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public int getAlmacen() {
        return almacen;
    }

    public void setAlmacen(int almacen) {
        this.almacen = almacen;
    }

    public String getFecha_ejecucion() {
        return fecha_ejecucion;
    }

    public void setFecha_ejecucion(String fecha_ejecucion) {
        this.fecha_ejecucion = fecha_ejecucion;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int ultimo_ingreso() {
        int codigo = 1;
        try {
            Statement st = con.conexion();
            String c_inventario = "select id from inventario where almacen = '" + almacen + "' and anio = '" + anio + "' order by id desc limit 1";
            ResultSet rs = con.consulta(st, c_inventario);
            if (rs.next()) {
                codigo = rs.getInt("id") + 1;
            }
            con.cerrar(rs);
            con.cerrar(st);
        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
        }
        return codigo;
    }

    public boolean crear() {
        boolean registro = false;
        int resultado;
        Statement st = con.conexion();
        String i_inventario = "insert into inventario values ('" + id + "', '" + anio + "', '" + almacen + "', '" + fecha_ejecucion + "', '" + responsable + "', "
                + "'" + tipo + "', '" + usuario + "', CURRENT_TIMESTAMP())";
        resultado = con.actualiza(st, i_inventario);
        if (resultado > -1) {
            registro = true;
        }
        con.cerrar(st);
        return registro;
    }
}
