/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

/**
 *
 * @author Dereck
 */
public class Cl_Movimiento {
    private int id;
    private String glosa;
    private String fec_mov;
    private double ingreso;
    private double egreso;

    public Cl_Movimiento(int id, String glosa, String fec_mov, double ingreso, double egreso) {
        this.id = id;
        this.glosa = glosa;
        this.fec_mov = fec_mov;
        this.ingreso = ingreso;
        this.egreso = egreso;
    }

    public Cl_Movimiento() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGlosa() {
        return glosa;
    }

    public void setGlosa(String glosa) {
        this.glosa = glosa.toUpperCase();
    }

    public String getFec_mov() {
        return fec_mov;
    }

    public void setFec_mov(String fec_mov) {
        this.fec_mov = fec_mov;
    }

    public double getIngreso() {
        return ingreso;
    }

    public void setIngreso(double ingreso) {
        this.ingreso = ingreso;
    }

    public double getEgreso() {
        return egreso;
    }

    public void setEgreso(double egreso) {
        this.egreso = egreso;
    }
    
    
}
