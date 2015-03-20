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
public class Cl_Requerimiento {
    private int id;
    private String fec_sol;
    private String fec_rea;
    private int dias;
    private int alm_ori;
    private int alm_des;
    private String estado = "1";

    public Cl_Requerimiento() {
    }

    public Cl_Requerimiento(int id, String fec_sol, String fec_rea, int dias, int alm_ori, int alm_des) {
        this.id = id;
        this.fec_sol = fec_sol;
        this.fec_rea = fec_rea;
        this.dias = dias;
        this.alm_ori = alm_ori;
        this.alm_des = alm_des;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFec_sol() {
        return fec_sol;
    }

    public void setFec_sol(String fec_sol) {
        this.fec_sol = fec_sol;
    }

    public String getFec_rea() {
        return fec_rea;
    }

    public void setFec_rea(String fec_rea) {
        this.fec_rea = fec_rea;
    }

    public int getDias() {
        return dias;
    }

    public void setDias(int dias) {
        this.dias = dias;
    }

    public int getAlm_ori() {
        return alm_ori;
    }

    public void setAlm_ori(int alm_ori) {
        this.alm_ori = alm_ori;
    }

    public int getAlm_des() {
        return alm_des;
    }

    public void setAlm_des(int alm_des) {
        this.alm_des = alm_des;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
}
