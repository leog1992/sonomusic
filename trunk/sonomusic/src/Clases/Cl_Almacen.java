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
public class Cl_Almacen {
    Cl_Conectar con = new Cl_Conectar();
    private int id;
    private String nom;
    private String direccion;
    private String ruc;
    private String raz_soc;
    private String ciudad;
    private String est = "1";

    public Cl_Almacen(int id, String nom, String direccion, String ciudad) {
        this.id = id;
        this.nom = nom;
        this.direccion = direccion;
        this.ciudad = ciudad;
    }

    public Cl_Almacen() {
    }

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
        this.nom = nom.toUpperCase();
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion.toUpperCase();
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad.toUpperCase();
    }

    public String getEst() {
        return est;
    }

    public void setEst(String est) {
        this.est = est;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getRaz_soc() {
        return raz_soc;
    }

    public void setRaz_soc(String raz_soc) {
        this.raz_soc = raz_soc;
    }
    
    public int id_alm_dir (String dir) {
        int idalm = 0;
        try {
            Statement st = con.conexion();
            String ver_id = "select idAlmacen from almacen where dir_alm = '"+dir+"'";
            ResultSet rs = con.consulta(st, ver_id);
            if (rs.next()) {
                idalm = rs.getInt("idAlmacen");
            }
            con.cerrar(rs);
            con.cerrar(st);
        } catch (SQLException e) {
            System.out.println(e);
        }
        return idalm;
    }
    
    public String nom_alm (String dir) {
        String nom = null;
        try {
            Statement st = con.conexion();
            String ver_id = "select nom_alm from almacen where dir_alm = '"+dir+"'";
            ResultSet rs = con.consulta(st, ver_id);
            if (rs.next()) {
                nom = rs.getString("nom_alm");
            }
            con.cerrar(rs);
            con.cerrar(st);
        } catch (SQLException e) {
            System.out.println(e);
        }
        return nom;
    }

    public void setRuc(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
