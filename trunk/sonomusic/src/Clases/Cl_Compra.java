/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

/**
 *
 * @author luis-d
 */
public class Cl_Compra {
    private int id;
    private String glosa;
    private String fec_com;
    private String fec_pag;
    private int serie;
    private int nro;
    private double total;
    private String est = "1";

    public Cl_Compra() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFec_com() {
        return fec_com;
    }

    public void setFec_com(String fec_com) {
        this.fec_com = fec_com;
    }

    public String getFec_pag() {
        return fec_pag;
    }

    public void setFec_pag(String fec_pag) {
        this.fec_pag = fec_pag;
    }

    public int getSerie() {
        return serie;
    }

    public void setSerie(int serie) {
        this.serie = serie;
    }

    public int getNro() {
        return nro;
    }

    public void setNro(int nro) {
        this.nro = nro;
    }

    public String getEst() {
        return est;
    }

    public void setEst(String est) {
        this.est = est;
    }

    public String getGlosa() {
        return glosa;
    }

    public void setGlosa(String glosa) {
        this.glosa = glosa.toUpperCase();
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    
    
}
