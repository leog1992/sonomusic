/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.awt.HeadlessException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author Pitufina
 */
public class Cl_Moneda {

    Cl_Conectar con = new Cl_Conectar();
    Cl_Varios ven = new Cl_Varios();

    private int id;
    private String nombre;
    private String siglas;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre.toUpperCase();
    }

    public String getSiglas() {
        return siglas;
    }

    public void setSiglas(String siglas) {
        this.siglas = siglas.toUpperCase();
    }

    public Double tc_venta(String fecha, int moneda) {
        //para cambiar de dolares a soles
        Double precio = 0.0;
        try {
            Statement st = con.conexion();
            String ver_tc = "select venta from tipo_cambio where fecha = '" + fecha + "' and idmon = '" + moneda + "'";
            ResultSet rs = con.consulta(st, ver_tc);
            if (rs.next()) {
                precio = rs.getDouble("venta");
            } else {
                precio = 0.0;
                JOptionPane.showMessageDialog(null, "EL TIPO DE CAMBIO DEL DIA " + ven.fechaformateada(fecha) + " NO EXISTE");
            }
            con.cerrar(rs);
            con.cerrar(st);
        } catch (SQLException | HeadlessException e) {
            System.out.println(e.getLocalizedMessage());
        }
        return precio;
    }

    public Double tc_compra(String fecha, int moneda) {
        //para cambiar de dolares a soles
        Double precio = 0.0;
        try {
            Statement st = con.conexion();
            String ver_tc = "select compra from tipo_cambio where fecha = '" + fecha + "' and idmon = '" + moneda + "'";
            ResultSet rs = con.consulta(st, ver_tc);
            if (rs.next()) {
                precio = rs.getDouble("compra");
            } else {
                precio = 0.0;
                JOptionPane.showMessageDialog(null, "EL TIPO DE CAMBIO DEL DIA " + ven.fechaformateada(fecha) + " NO EXISTE");
            }
            con.cerrar(rs);
            con.cerrar(st);
        } catch (SQLException | HeadlessException e) {
            System.out.println(e.getLocalizedMessage());
        }
        return precio;
    }

    public Double cambio_venta_dolar(String fecha, int moneda, Double valor) {
        //para cambiar de soles a dolares
        Double precio = 0.0;
        Double monto = 0.0;
        try {
            Statement st = con.conexion();
            String ver_tc = "select venta from tipo_cambio where fecha = '" + fecha + "' and idmon = '" + moneda + "'";
            ResultSet rs = con.consulta(st, ver_tc);
            if (rs.next()) {
                precio = rs.getDouble("venta");
            } else {
                precio = 0.0;
                JOptionPane.showMessageDialog(null, "EL TIPO DE CAMBIO DEL DIA " + ven.fechaformateada(fecha) + " NO EXISTE");
            }
            monto = valor / precio;
            con.cerrar(rs);
            con.cerrar(st);
        } catch (SQLException | HeadlessException e) {
            System.out.println(e.getLocalizedMessage());
        }
        return monto;
    }

    public Double cambio_compra_dolar(String fecha, int moneda, Double valor) {
        //para cambiar de soles a dolares
        Double precio = 0.0;
        Double monto = 0.0;
        try {
            Statement st = con.conexion();
            String ver_tc = "select compra from tipo_cambio where fecha = '" + fecha + "' and idmon = '" + moneda + "'";
            ResultSet rs = con.consulta(st, ver_tc);
            if (rs.next()) {
                precio = rs.getDouble("compra");
            } else {
                precio = 0.0;
                JOptionPane.showMessageDialog(null, "EL TIPO DE CAMBIO DEL DIA " + ven.fechaformateada(fecha) + " NO EXISTE");
            }
            monto = valor / precio;
            con.cerrar(rs);
            con.cerrar(st);
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
        return monto;
    }

    public Double cambio_venta_sol(String fecha, int moneda, Double valor) {
        //para cambiar de dolares a soles
        Double precio = 0.0;
        Double monto = 0.0;
        try {
            Statement st = con.conexion();
            String ver_tc = "select venta from tipo_cambio where fecha = '" + fecha + "' and idmon = '" + moneda + "'";
            ResultSet rs = con.consulta(st, ver_tc);
            if (rs.next()) {
                precio = rs.getDouble("venta");
            } else {
                precio = 0.0;
                JOptionPane.showMessageDialog(null, "EL TIPO DE CAMBIO DEL DIA " + ven.fechaformateada(fecha) + " NO EXISTE");
            }
            monto = valor * precio;
            con.cerrar(rs);
            con.cerrar(st);
        } catch (SQLException | HeadlessException e) {
            System.out.println(e.getLocalizedMessage());
        }
        return monto;
    }

    public Double cambio_compra_sol(String fecha, int moneda, Double valor) {
        //para cambiar de dolares a soles
        Double precio = 0.0;
        Double monto = 0.0;
        try {
            Statement st = con.conexion();
            String ver_tc = "select compra from tipo_cambio where fecha = '" + fecha + "' and idmon = '" + moneda + "'";
            ResultSet rs = con.consulta(st, ver_tc);
            if (rs.next()) {
                precio = rs.getDouble("compra");
            } else {
                precio = 0.0;
                JOptionPane.showMessageDialog(null, "EL TIPO DE CAMBIO DEL DIA " + ven.fechaformateada(fecha) + " NO EXISTE");
            }
            monto = valor * precio;
            con.cerrar(rs);
            con.cerrar(st);
        } catch (SQLException | HeadlessException e) {
            System.out.println(e.getLocalizedMessage());
        }
        return monto;
    }

}
