/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author luis-d
 */
public class Cl_Hilo_Imprime extends Thread {

    Cl_Varios ven = new Cl_Varios();
    private String tipv;
    private String idped;
    private String tipo_venta;

    @Override
    public void run() {
        impresion(get_tipv(), get_idped(), getTipo_venta());
        System.out.println("Pdf generado");
    }

    public void set_tipv(String tipv) {
        this.tipv = tipv;
    }

    public void set_idped(String idped) {
        this.idped = idped;
    }

    public String get_tipv() {
        return tipv;
    }

    public String get_idped() {
        return idped;
    }

    public String getTipo_venta() {
        return tipo_venta;
    }

    public void setTipo_venta(String tipo_venta) {
        this.tipo_venta = tipo_venta;
    }

    private void impresion(String tipv, String idped, String tventa) {
        if (tventa.equals("SEPARACION")) {
            if (tipv.equals("NOTA DE VENTA")) {
                Map<String, Object> parametros = new HashMap<>();
                parametros.put("idped", idped);
                String filename = "rpt_ticket_separacion";
                ven.imp_reporte(filename, parametros);
            }
        }

        if (tventa.equals("SEPARACION")) {
            if (tipv.equals("NOTA DE VENTA")) {
                Map<String, Object> parametros = new HashMap<>();
                parametros.put("idped", idped);
                String filename = "rpt_ticket_notaventa";
                //ven.imp_reporte(filename, parametros);
            }

            if (tipv.equals("BOLETA")) {
                Map<String, Object> parametros = new HashMap<>();
                parametros.put("idped", idped);
                String filename = "rpt_ticket_boleta";
                ven.imp_reporte(filename, parametros);
            }

            if (tipv.equals("FACTURA")) {
                Map<String, Object> parametros = new HashMap<>();
                parametros.put("idped", idped);
                String filename = "rpt_ticket_factura";
                ven.imp_reporte(filename, parametros);
            }
        }
    }
}
