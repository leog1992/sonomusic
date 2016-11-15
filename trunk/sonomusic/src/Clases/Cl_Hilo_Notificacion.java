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

    @Override
    public void run() {
        notificar();
        cargar_notificaciones();
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
        notificar_pedido();
        cantidad_guias();
    }

    private void notificar_pedido() {
        try {
            Statement st = con.conexion();
            String ver_ped = "select count(nick) as nro from solicitud_articulos where id_alm_des = '" + frm_menu.alm.getId() + "' and estado = '0'";
            ResultSet rs = con.consulta(st, ver_ped);
            if (rs.next()) {
                if (rs.getInt("nro") != 0) {
                    Notification.show("Nueva Solicitud de Productos", "Acaba de recibir " + rs.getInt("nro") + " nueva(s) solicitud(es) para enviar productos");
                }
            }
            con.cerrar(rs);
            con.cerrar(st);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    private void cantidad_guias() {
        try {
            Statement st = con.conexion();
            String ver_ped = "select count(nick) as nro from traslado where destino = '" + frm_menu.alm.getDireccion() + "' and estado = '0'";
            System.out.println(ver_ped);
            ResultSet rs = con.consulta(st, ver_ped);
            if (rs.next()) {
                if (rs.getInt("nro") != 0) {
                    int cantidad = rs.getInt("nro");
                    frm_menu.lbl_traslados.setText("Traslados (" + cantidad + ")");
                    
                    System.out.println("Traslados (" + cantidad + ")");
                }
            }
            con.cerrar(rs);
            con.cerrar(st);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
}
