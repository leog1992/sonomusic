/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Ricardo
 */
public class Cl_Ofertas {
    Cl_Conectar con = new Cl_Conectar();
    private int id_ofe;
    private String nom_ofe;
    private String fec_ini;
    private String fec_fin;
    private String est_ofe = "1";
        public Cl_Ofertas() {
    }

    public Cl_Ofertas(int id_ofe, String nom_ofe, String fec_ini, String fec_fin) {
        this.id_ofe = id_ofe;
        this.nom_ofe = nom_ofe;
        this.fec_ini = fec_ini;
        this.fec_fin = fec_fin;
    }

    public int getId_ofe() {
        return id_ofe;
    }

    public void setId_ofe(int id_ofe) {
        this.id_ofe = id_ofe;
    }

    public String getNom_ofe() {
        return nom_ofe;
    }

    public void setNom_ofe(String nom_ofe) {
        this.nom_ofe = nom_ofe.toUpperCase();
    }

    public String getFec_ini() {
        return fec_ini;
    }

    public void setFec_ini(String fec_ini) {
        this.fec_ini = fec_ini;
    }

    public String getFec_fin() {
        return fec_fin;
    }

    public void setFec_fin(String fec_fin) {
        this.fec_fin = fec_fin;
    }

    public String getEst_ofe() {
        return est_ofe;
    }

    public void setEst_ofe(String est_ofe) {
        this.est_ofe = est_ofe;
    }

    public Double precio_oferta (Integer idalm, Integer idpro) {
        Double precio = 0.0;
        try {
            Statement st = con.conexion();
            String ver_pre = "select do.precio from detalle_oferta as do inner join oferta as o on do.idOferta = o.idOferta "
                    + "where do.idProductos = '"+idpro+"' and o.idAlmacen = '"+idalm+"' and (curdate() between o.fecha_ofer and o.fecha_venc)";
            ResultSet rs = con.consulta(st, ver_pre);
            if (rs.next()) {
                precio = rs.getDouble("precio");
            } else {
                precio = 0.00;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return precio;
    }
}
