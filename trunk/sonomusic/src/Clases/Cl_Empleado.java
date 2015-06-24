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
public class Cl_Empleado {
    
    Cl_Conectar con = new Cl_Conectar();
    private int dni;
    private String nom;
    private String dir;
    private String fec_nac;
    private String img;
    private String tel;
    private String tel2;
    private double sueldo;
    private String est = "1";

    public Cl_Empleado(int dni, String nom, String dir, String fec_nac, String img, String tel, String tel2, double sueldo) {
        this.dni = dni;
        this.nom = nom;
        this.dir = dir;
        this.fec_nac = fec_nac;
        this.img = img;
        this.tel = tel;
        this.tel2 = tel2;
        this.sueldo = sueldo;
    }

    public Cl_Empleado() {
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom.toUpperCase();
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir.toUpperCase();
    }

    public String getFec_nac() {
        return fec_nac;
    }

    public void setFec_nac(String fec_nac) {
        this.fec_nac = fec_nac;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getTel2() {
        return tel2;
    }

    public void setTel2(String tel2) {
        this.tel2 = tel2;
    }

    public double getSueldo() {
        return sueldo;
    }

    public void setSueldo(double sueldo) {
        this.sueldo = sueldo;
    }

    public String getEst() {
        return est;
    }

    public void setEst(String est) {
        this.est = est;
    }
    
    public String nom_emp (String dni) {
        String nom = null;
        try {
            Statement st = con.conexion();
            String ver_id = "select nom_per from empleados where dni = '"+dni+"'";
            ResultSet rs = con.consulta(st, ver_id);
            if (rs.next()) {
                nom = rs.getString("nom_per");
            }
            con.cerrar(rs);
            con.cerrar(st);
        } catch (SQLException e) {
            System.out.println(e);
        }
        return nom;
    }
    
}
