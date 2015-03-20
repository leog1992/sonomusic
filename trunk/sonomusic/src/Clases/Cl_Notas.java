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
public class Cl_Notas {
    private int id;
    private String fec_reg;
    private String fec_eje;
    private String glosa;
    private String est = "0";

    public Cl_Notas(int id, String fec_reg, String fec_eje, String glosa) {
        this.id = id;
        this.fec_reg = fec_reg;
        this.fec_eje = fec_eje;
        this.glosa = glosa;
    }

    public Cl_Notas() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFec_reg() {
        return fec_reg;
    }

    public void setFec_reg(String fec_reg) {
        this.fec_reg = fec_reg;
    }

    public String getFec_eje() {
        return fec_eje;
    }

    public void setFec_eje(String fec_eje) {
        this.fec_eje = fec_eje;
    }

    public String getGlosa() {
        return glosa;
    }

    public void setGlosa(String glosa) {
        this.glosa = glosa.toUpperCase();
    }

    public String getEst() {
        return est;
    }

    public void setEst(String est) {
        this.est = est;
    }
    
    
}
