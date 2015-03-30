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
        
    @Override
    public void run() {
        impresion(get_tipv(), get_idped());
        System.out.println("Pdf generado");
    }

    public void set_tipv (String tipv) {
        this.tipv = tipv;
    }
    
    public void set_idped (String idped) {
        this.idped = idped;
    }
    
    public String get_tipv () {
        return tipv;
    }
    
    public String get_idped () {
        return idped;
    }
    
    
    
    private void impresion (String tipv, String idped) {
        if (tipv.equals("NOTA DE VENTA")) {
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("idped", idped);
            String filename = "rpt_ticket_notaventa";
            ven.imp_reporte(filename, parametros);
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
