/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

/**
 *
 * @author Pitufina
 */
public class Cl_cuenta {
    private int id_cuen;
    private String tip_cuen;
    private String nro_cuen;
    private double mon_cuen;

    public int getId_cuen() {
        return id_cuen;
    }

    public void setId_cuen(int id_cuen) {
        this.id_cuen = id_cuen;
    }

    public String getTip_cuen() {
        return tip_cuen;
    }

    public void setTip_cuen(String tip_cuen) {
        this.tip_cuen = tip_cuen.toUpperCase();
    }

    public String getNro_cuen() {
        return nro_cuen;
    }

    public void setNro_cuen(String nro_cuen) {
        this.nro_cuen = nro_cuen.toUpperCase();
    }

    public double getMon_cuen() {
        return mon_cuen;
    }

    public void setMon_cuen(double mon_cuen) {
        this.mon_cuen = mon_cuen;
    }
    
    
    
}
