package Clases;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
    Cl_Almacen alm = new Cl_Almacen();
    int pedidos_actual = 0;
    int pedidos_nuevo = 0;

    @Override
    public void run() {
        cargar_notificaciones();
    }

    private void cargar_notificaciones() {
        pedidos_actual = cantidad_guias();
        System.out.println("pedidos cantidad actual = " + pedidos_actual);
        cantidad_solicitudes();
        try {
            Timer timer;
            timer = new Timer(300000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    notificar();
                    //Notification.show("TRASLADO ENTRE ALMACENES", "TRASLADO ENTRANTE DE TIENDA: XXX, FECHA: 2016-10-01", Notification.WARNING_MESSAGE, Notification.NICON_LIGHT_THEME);
                    //Notification.show(Notification.FACEBOOK_ICON, "CARGANDO CONTROLADOR", "CADA 10 SEGUNDOS");
                }
            });
            timer.start();
            timer.setRepeats(true);
        } catch (Exception e) {
            System.out.println("Error grave " + e.getLocalizedMessage());
        }
    }

    public void notificar() {
        //cantidad_guias();
        // cantidad_solicitudes();
        notificar_pedido();
    }

    private int cantidad_guias() {
        int cantidad = 0;
        try {
            Statement st = con.conexion();
            String ver_ped = "select count(nick) as nro from traslado where almacen_destino = '" + frm_menu.alm.getId()+ "' and estado = '0'";
            System.out.println(ver_ped);
            ResultSet rs = con.consulta(st, ver_ped);
            if (rs.next()) {
                cantidad = rs.getInt("nro");
                frm_menu.lbl_traslados.setText("Traslados (" + cantidad + ")");
            } else {
                cantidad = 0;
                frm_menu.lbl_traslados.setText("Traslados");
            }
            con.cerrar(rs);
            con.cerrar(st);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return cantidad;
    }

    private void cantidad_solicitudes() {
        try {
            Statement st = con.conexion();
            String ver_ped = "select count(nick) as nro from solicitud_articulos where id_alm_des = '" + frm_menu.alm.getId() + "' and estado = '0'";
            System.out.println(ver_ped);
            ResultSet rs = con.consulta(st, ver_ped);
            if (rs.next()) {
                    int cantidad = rs.getInt("nro");
                    frm_menu.lbl_pedidos.setText("Pedidos (" + cantidad + ")");
                    System.out.println("Pedidos (" + cantidad + ")");
            }
            con.cerrar(rs);
            con.cerrar(st);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    private void notificar_pedido() {
        pedidos_nuevo = cantidad_guias();
        int diferencia = 0;
        if (pedidos_nuevo > pedidos_actual) {
            System.out.println("comparando cantidades");
            System.out.println("cantidad actual " + pedidos_actual);
            System.out.println("cantidad nueva consulta " +pedidos_nuevo);
            diferencia = pedidos_nuevo - pedidos_actual;

            try {
                Statement st = con.conexion();
                String ver_ped = "select origen, fecha from traslado where destino = '" + frm_menu.alm.getDireccion() + "' and estado = '0' order by fecha desc limit " + diferencia + "";
                ResultSet rs = con.consulta(st, ver_ped);
                while (rs.next()) {
                    String fecha = ven.fechaformateada(rs.getString("fecha"));
                    String tienda = alm.nom_alm(rs.getString("origen"));
                    Notification.show("TRASLADO ENTRE ALMACENES", "TRASLADO ENTRANTE DE TIENDA: " + tienda + ", FECHA: " + fecha, Notification.WARNING_MESSAGE, Notification.NICON_LIGHT_THEME);
                    
                }
                con.cerrar(rs);
                con.cerrar(st);
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }
        pedidos_actual = cantidad_guias();
    }
}
