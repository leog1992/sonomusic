/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;
    
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

/**
 *
 * @author Dereck
 */
public class Cl_Meta {
    Cl_Conectar con = new Cl_Conectar();
    private int id;
    private double monto;
    private String est = "1";
    private String fec_ini;
    private String fec_fin;

    public Cl_Meta(int id, double monto, String fec_ini, String fec_fin) {
        this.id = id;
        this.monto = monto;
        this.fec_ini = fec_ini;
        this.fec_fin = fec_fin;
    }

    public Cl_Meta() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String getEst() {
        return est;
    }

    public void setEst(String est) {
        this.est = est;
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
    
    public Double mis_metas (String user) {
        java.util.Date fecha = new Date();
        int mes = fecha.getMonth();
        System.out.println(mes + " MES");
        Double metas = 0.00;
        try {
            Statement st = con.conexion();
            String mis_metas = "select sum(dp.precio* dp.cantidad) as sum_valor from detalle_pedido as dp inner join pedido as p "
                    + "on dp.idPedido=p.idPedido where dp.precio > 50 and p.est_ped = '1' and p.nick = '"+user+"' and month(p.fec_ped) = '"+mes+1+"'";
            ResultSet rs = con.consulta(st, mis_metas);
            if (rs.next()) {
                metas = rs.getDouble("sum_valor");
            } else {
                metas = 0.00;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return metas;
    }
    
    public Double monto (int idcar) {
        Double monto = 0.00;
        try {
            Statement st = con.conexion();
            String metas = "select monto from metas where curdate() between fec_inicio and fec_fin order by idMetas desc limit 1";
            ResultSet rs = con.consulta(st, metas);
            if (rs.next()) {
                monto = rs.getDouble("monto");
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return monto;
    }
}
