package Clases;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import javax.swing.Timer;
import nicon.notify.core.Notification;
import sonomusic.frm_menu;

/**
 *
 * @author luis-d
 */
public class Cl_Hilo_Notificacion extends Thread {

    Cl_Conectar con = new Cl_Conectar();
    Cl_Varios ven = new Cl_Varios();
    int contar;

    @Override
    public void run() {
        contar = 0;
        notificar();
        cargar_notificaciones();
        frm_menu.lbl_nro.setText("" + contar);
    }

    private void cargar_notificaciones() {
        try {
            Timer timer = new Timer(500000, new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    notificar();
                }
            });
            timer.start();
            timer.setRepeats(true);
        } catch (Exception e) {
            System.out.println("Error grave " + e.getLocalizedMessage());
        }
    }

    public void notificar() {
        notificar_prod_fin();
        notificar_pedido();
        notificar_guia();
        notificar_oferta();
        notificar_meta();
        notificar_pago_compra();
        camb_est_com();
    }

    private void notificar_prod_fin() {
        try {
            Statement st = con.conexion();
            String ver_pro_fin = "select count(idProductos) as nro from producto_almacen where cant = '0' and idAlmacen = '" + frm_menu.alm.getId() + "'";
            ResultSet rs = con.consulta(st, ver_pro_fin);
            if (rs.next()) {
                if (rs.getInt("nro") != 0) {
                    Notification.show("Productos Terminados", "Existen " + rs.getInt("nro") + " producto que se han agotado en almacen");
                    contar = contar + 1;
                    System.out.println(contar);
                }
            }
            con.cerrar(rs);
            con.cerrar(st);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    private void notificar_pedido() {
        try {
            Statement st = con.conexion();
            String ver_ped = "select count(nick) as nro from solicitud_articulos where id_alm_des = '" + frm_menu.alm.getId() + "' and estado = '0'";
            ResultSet rs = con.consulta(st, ver_ped);
            if (rs.next()) {
                if (rs.getInt("nro") != 0) {
                    Notification.show("Nueva Solicitud de Productos", "Acaba de recibir " + rs.getInt("nro") + " nueva(s) solicitud(es) para enviar productos");
                    contar = contar + 1;
                    System.out.println(contar);
                }
            }
            con.cerrar(rs);
            con.cerrar(st);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    private void notificar_guia() {
        try {
            Statement st = con.conexion();
            String ver_ped = "select count(nick) as nro from traslado where destino = '" + frm_menu.alm.getDireccion() + "' and estado = '0'";
            ResultSet rs = con.consulta(st, ver_ped);
            if (rs.next()) {
                if (rs.getInt("nro") != 0) {
                    Notification.show("Respuesta a solicitud de productos", "Existen " + rs.getInt("nro") + " respuesta(s) a sus solicitudes");
                    contar = contar + 1;
                    System.out.println(contar);
                }
            }
            con.cerrar(rs);
            con.cerrar(st);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    private void notificar_oferta() {
        try {
            Statement st = con.conexion();
            String ver_ped = "select nom_ofer from oferta where idAlmacen = '" + frm_menu.alm.getId() + "'  and (curdate() between fecha_ofer and fecha_venc) and estado = '1'";
            ResultSet rs = con.consulta(st, ver_ped);
            while (rs.next()) {
                Notification.show("Nueva Oferta", "Se ha encontrado nueva oferta: \n " + rs.getString("nom_ofer"));
                contar = contar + 1;
                System.out.println(contar);
            }
            con.cerrar(rs);
            con.cerrar(st);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    private void notificar_meta() {
        DecimalFormatSymbols simbolo = new DecimalFormatSymbols();
        simbolo.setDecimalSeparator('.');
        DecimalFormat formato = new DecimalFormat("####0.00", simbolo);
        Cl_Cargo car = new Cl_Cargo();
        car.setId(car.mi_cargo(frm_menu.usu.getNick()));
        Cl_Meta meta = new Cl_Meta();
        Double mi_meta = meta.mis_metas(frm_menu.usu.getNick());
        Double monto = meta.monto(car.getId());
        if (mi_meta < monto & monto > 0.0) {
            Notification.show("Meta Encontrada", "Ya has alcanzado " + formato.format(mi_meta) + " de " + formato.format(monto) + " ---- FELICITACIONES!!");
            contar = contar + 1;
        }
    }
    
    private void notificar_pago_compra() {
        try {
            Statement st = con.conexion();
            String ver_pagos = "select count(*) as contar from pago_compras where estado ='0' and fec_venc >= '"+ven.getFechaActual()+"'";
            ResultSet rs = con.consulta(st, ver_pagos);
            if (rs.next()) { 
                Notification.show("Pagos Pendiente", "Existe " + rs.getString("contar") + " pagos pendientes por realizar hasta la fecha.");
                contar = contar + 1;
            }
            con.cerrar(rs);
            con.cerrar(st);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    private void camb_est_com () {
        //sumar monto de cuotas
        try {
            Statement st = con.conexion();
            String sum = "select sum(monto) as suma, idCompra from pago_compras where estado = '1' group by idCompra";
            ResultSet rs = con.consulta(st, sum);
            while (rs.next()) {
                //seleccionar monto de compra
                double monto = 0;
                double monto_cuota = 0;
                try {
                    Statement st1 = con.conexion();
                    String idCom = "select total from compra where idCompra = '"+rs.getString("idCompra")+"'";        
                    ResultSet rs1 = con.consulta(st1, idCom);
                    if (rs1.next()) {
                        monto = rs1.getDouble("total");
                    }
                    con.cerrar(rs1);
                    con.cerrar(st1);
                } catch (Exception e) {
                    System.out.println(e);
                }
                monto_cuota = rs.getDouble("suma");
                if (monto_cuota >= monto) {
                    System.out.println("Compra pagada, modificar estado y fecha" +rs.getString("idCompra") + "\n");
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
