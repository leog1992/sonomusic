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
public class Cl_Cliente {
    private String nro_doc;
    private String tipo_doc;
    private String nom_cli;
    private String dir_cli;
    private String tel;
    private String tel2;
    private String est;

    public Cl_Cliente(String nro_doc, String tipo_doc, String nom_cli, String dir_cli, String tel, String tel2, String est) {
        this.nro_doc = nro_doc;
        this.tipo_doc = tipo_doc;
        this.nom_cli = nom_cli;
        this.dir_cli = dir_cli;
        this.tel = tel;
        this.tel2 = tel2;
        this.est = est;
    }

    public Cl_Cliente() {
    }

    public String getNro_doc() {
        return nro_doc;
    }

    public void setNro_doc(String nro_doc) {
        this.nro_doc = nro_doc;
    }

    public String getTipo_doc() {
        return tipo_doc;
    }

    public void setTipo_doc(String tipo_doc) {
        this.tipo_doc = tipo_doc.toUpperCase();
    }

    public String getNom_cli() {
        return nom_cli;
    }

    public void setNom_cli(String nom_cli) {
        this.nom_cli = nom_cli.toUpperCase();
    }

    public String getDir_cli() {
        return dir_cli;
    }

    public void setDir_cli(String dir_cli) {
        this.dir_cli = dir_cli.toUpperCase();
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

    public String getEst() {
        return est;
    }

    public void setEst(String est) {
        this.est = est;
    }
    
    
}
