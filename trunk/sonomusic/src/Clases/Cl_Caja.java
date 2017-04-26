/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Usuario
 */
public class Cl_Caja {

    Cl_Conectar con = new Cl_Conectar();
    private int id;
    private String nom;
    private Double monto;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public int id_caja(int idalm) {
        int id_caja = 0;
        try {
            Statement st = con.conexion();
            String ver_id = "select idcaja from caja where idAlmacen = '" + idalm + "'";
            ResultSet rs = con.consulta(st, ver_id);
            if (rs.next()) {
                id_caja = rs.getInt("idcaja");
            }
            con.cerrar(rs);
            con.cerrar(st);
        } catch (Exception e) {
            System.out.println(e);
        }
        return id_caja;
    }

    public double apertura(int almacen, String fecha) {
        double apertura = 0;
        try {
            Statement st = con.conexion();
            String ver_monto = "select apertura from caja where idalmacen = '" + almacen + "' and fecha = '" + fecha + "'";
            ResultSet rs = con.consulta(st, ver_monto);
            if (rs.next()) {
                apertura = rs.getDouble("apertura");
            }
            con.cerrar(rs);
            con.cerrar(st);
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
        return apertura;
    }

}
