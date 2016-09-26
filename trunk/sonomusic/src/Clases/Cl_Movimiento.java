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
 * @author Dereck
 */
public class Cl_Movimiento {

    private int id;
    private String glosa;
    private String fec_mov;
    private double ingreso;
    private double egreso;

    Cl_Conectar con = new Cl_Conectar();
    Cl_Varios ven = new Cl_Varios();

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

    public double monto_sistema(String fecha, int almacen) {
        double monto = 0;
        try {
            Statement st = con.conexion();
            String ver_monto = "select sum(entrada) as ingresos, sum(salida) as egresos from movimiento where fec_mov = '" + fecha + "' "
                    + "and idalmacen = '" + almacen + "' and destino = 'C'";
            ResultSet rs = con.consulta(st, ver_monto);
            if (rs.next()) {
                monto = rs.getDouble("ingresos") - rs.getDouble("egresos");
            }
            con.cerrar(rs);
            con.cerrar(st);
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
        return monto;
    }

}
