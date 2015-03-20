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
public class Cl_Proveedor {
    private String ruc;
    private String raz;
    private String dir;
    private String tel;
    private String est = "1";
    private String email;
    private String web;
    private String con;
    private String tel1;
    private String tel2;
    

    public Cl_Proveedor(String ruc, String raz, String dir, String tel, String email, String web, String con, String tel1, String tel2) {
        this.ruc = ruc;
        this.raz = raz;
        this.dir = dir;
        this.tel = tel;
        this.email = email;
        this.web = web;
        this.con = con;
        this.tel1 = tel1;
        this.tel2 = tel2;
    }

    public Cl_Proveedor() {
    }

    
    
    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getRaz() {
        return raz;
    }

    public void setRaz(String raz) {
        this.raz = raz.toUpperCase();
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir.toUpperCase();
    }

    public String getEst() {
        return est;
    }

    public void setEst(String est) {
        this.est = est;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email.toUpperCase();
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web.toUpperCase();
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getCon() {
        return con;
    }

    public void setCon(String con) {
        this.con = con.toUpperCase();
    }

    public String getTel1() {
        return tel1;
    }

    public void setTel1(String tel1) {
        this.tel1 = tel1;
    }

    public String getTel2() {
        return tel2;
    }

    public void setTel2(String tel2) {
        this.tel2 = tel2;
    }
    
    
    
}
