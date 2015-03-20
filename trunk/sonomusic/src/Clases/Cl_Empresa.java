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
public class Cl_Empresa {
    private String ruc;
    private String raz_soc;
    private String dir;

    public Cl_Empresa(String ruc, String raz_soc, String dir) {
        this.ruc = ruc;
        this.raz_soc = raz_soc;
        this.dir = dir;
    }

    public Cl_Empresa() {
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
        this.raz_soc = raz_soc.toUpperCase();
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }
    
    
}
