/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import Clases.Cl_Albaran;
import Clases.Cl_Almacen;
import Clases.Cl_Conectar;
import Clases.Cl_Productos;
import Clases.Cl_Varios;
import Forms.frm_reg_traslado_almacen;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import sonomusic.frm_menu;
import static sonomusic.frm_menu.usu;

/**
 *
 * @author pc
 */
public class frm_ver_guias extends javax.swing.JInternalFrame {

    Cl_Varios ven = new Cl_Varios();
    Cl_Conectar con = new Cl_Conectar();
    Cl_Albaran alb = new Cl_Albaran();
    Cl_Almacen alm = new Cl_Almacen();
    int i;

    /**
     * Creates new form frm_ver_guias
     */
    public frm_ver_guias() {
        initComponents();
        System.out.println(frm_menu.alm.getDireccion());
        String query = "select motivo, fecha, idTraslado, origen, raz_soc_dest, destino, ser_doc, nro_doc, nick, "
                + "estado from traslado where origen = '" + frm_menu.alm.getDireccion() + "' or destino = "
                + "'" + frm_menu.alm.getDireccion() + "' order by fecha desc, idTraslado desc";
        ver_guia(query);
    }

    private void ver_guia(String query) {
        DefaultTableModel modelo = null;
        modelo = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int fila, int columna) {
                    return false;
                }
            };
        modelo.addColumn("Motivo");
        modelo.addColumn("Fecha");
        modelo.addColumn("Id");
        modelo.addColumn("Origen");
        modelo.addColumn("Raz. Social");
        modelo.addColumn("Dir. Destino");
        modelo.addColumn("Guia");
        modelo.addColumn("Usuario");
        modelo.addColumn("Estado");
        try {
            Statement st = con.conexion();
            ResultSet rs = con.consulta(st, query);
            while (rs.next()) {
                Object[] fila = new Object[9];
                fila[0] = rs.getString("motivo");
                fila[1] = ven.fechaformateada(rs.getString("fecha"));
                fila[2] = rs.getString("idTraslado");
                fila[3] = rs.getString("origen");
                fila[4] = rs.getString("raz_soc_dest");
                fila[5] = rs.getString("destino");
                if (rs.getInt("ser_doc") == 0 && rs.getInt("nro_doc") == 0) {
                    fila[6] = "------";
                } else {
                    fila[6] = rs.getString("ser_doc") + " - " + rs.getString("nro_doc");
                }
                fila[7] = rs.getString("nick");
                switch (rs.getString("estado")) {
                    case "0":
                        fila[8] = "PENDIENTE";
                        break;
                    case "1":
                        fila[8] = "APROBADO";
                        break;
                    case "2":
                        fila[8] = "ANULADO";
                        break;
                }

                modelo.addRow(fila);
            }
            t_guias.setModel(modelo);
            t_guias.getColumnModel().getColumn(0).setPreferredWidth(80);
            t_guias.getColumnModel().getColumn(1).setPreferredWidth(80);
            t_guias.getColumnModel().getColumn(2).setPreferredWidth(30);
            t_guias.getColumnModel().getColumn(3).setPreferredWidth(200);
            t_guias.getColumnModel().getColumn(4).setPreferredWidth(200);
            t_guias.getColumnModel().getColumn(5).setPreferredWidth(200);
            t_guias.getColumnModel().getColumn(6).setPreferredWidth(120);
            t_guias.getColumnModel().getColumn(7).setPreferredWidth(80);
            t_guias.getColumnModel().getColumn(8).setPreferredWidth(80);
            con.cerrar(rs);
            con.cerrar(st);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        t_guias = new javax.swing.JTable();
        btn_guia = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        btn_anu = new javax.swing.JButton();
        btn_envio = new javax.swing.JButton();

        setBackground(new java.awt.Color(254, 254, 254));
        setClosable(true);
        setTitle("Detalle de Guias");

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/magnifier.png"))); // NOI18N
        jLabel1.setText("Buscar");

        jScrollPane1.setBackground(new java.awt.Color(254, 254, 254));

        t_guias.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        t_guias.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        t_guias.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                t_guiasMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                t_guiasMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(t_guias);

        btn_guia.setText("Ver Guia");
        btn_guia.setEnabled(false);
        btn_guia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_guiaActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/cancel.png"))); // NOI18N
        jButton2.setText("Cerrar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/add.png"))); // NOI18N
        jButton3.setText("Registrar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        btn_anu.setText("Anular");
        btn_anu.setEnabled(false);
        btn_anu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_anuActionPerformed(evt);
            }
        });

        btn_envio.setText("Verificar Envio");
        btn_envio.setEnabled(false);
        btn_envio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_envioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1008, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_envio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btn_guia)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_anu)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_envio, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_guia, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_anu, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if (usu.getPer_reg_traslado().equals("1")) {
            frm_reg_traslado_almacen tras = new frm_reg_traslado_almacen();
            ven.llamar_ventana(tras);
            tras.accion = "traslado";
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(null, "Ud No tiene permisos");
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btn_envioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_envioActionPerformed
        // cargar datos en frm_reg_traslado
        frm_reg_traslado_almacen traslado = new frm_reg_traslado_almacen();
        alb.setId(Integer.parseInt(t_guias.getValueAt(i, 2).toString()));
        try {
            Statement st = con.conexion();
            String ver_guia = "select * from traslado where idTraslado = '" + alb.getId() + "'";
            ResultSet rs = con.consulta(st, ver_guia);
            if (rs.next()) {
                traslado.idtras = alb.getId();
                traslado.txt_fec.setText(ven.fechaformateada(rs.getString("fecha")));
                traslado.cbx_alm_or.setSelectedIndex(alm.id_alm_dir(rs.getString("origen")) - 1);
                traslado.cbx_alm_de.setSelectedIndex(alm.id_alm_dir(rs.getString("destino")) - 1);
                traslado.txt_ser.setText(rs.getString("ser_doc"));
                traslado.txt_num.setText(rs.getString("nro_doc"));
                traslado.txt_ruc_tra.setText(rs.getString("ruc_trans"));
                traslado.txt_raz_tra.setText(rs.getString("raz_trans"));
                traslado.txt_marca.setText(rs.getString("marca_veh"));
                traslado.txt_placa.setText(rs.getString("placa_veh"));
                traslado.txt_brev.setText(rs.getString("brevete"));
                traslado.txt_cons.setText(rs.getString("constancia"));
                traslado.txt_chofer.setText(rs.getString("chofer"));
                traslado.txt_ruc_alm.setText(rs.getString("ruc_dest"));
                traslado.txt_raz_alm.setText(rs.getString("raz_soc_dest"));
                llenar_tguias(alb.getId());
                traslado.accion = "compruebat";
                traslado.btn_reg.setEnabled(true);
            }
            con.cerrar(rs);
            con.cerrar(st);
            ven.llamar_ventana(traslado);
            this.dispose();
        } catch (Exception ex) {
            System.out.println(ex);

        }
    }//GEN-LAST:event_btn_envioActionPerformed

    private void t_guiasMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_t_guiasMousePressed
        i = t_guias.getSelectedRow();
        btn_guia.setEnabled(true);

        if (t_guias.getValueAt(i, 8).toString().equals("APROBADO")) {
            btn_anu.setEnabled(false);
        } else if (t_guias.getValueAt(i, 8).equals("ANULADO")) {
            btn_anu.setEnabled(false);
        } else {
            btn_anu.setEnabled(true);
            btn_envio.setEnabled(true);
        }
    }//GEN-LAST:event_t_guiasMousePressed

    private void btn_guiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_guiaActionPerformed
        alb.setId(Integer.parseInt(t_guias.getValueAt(i, 2).toString()));
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("idtraslado", alb.getId());
        ven.ver_reporte("rpt_ver_guia", parametros);
    }//GEN-LAST:event_btn_guiaActionPerformed

    private int ver_id_alm(String dir_alm) {
        int ida = 0;
        try {
            Statement st = con.conexion();
            String ver_alm = "select idAlmacen from almacen where dir_alm like '" + dir_alm + "%'";
            ResultSet rs = con.consulta(st, ver_alm);
            if (rs.next()) {
                ida = rs.getInt("idAlmacen");
            }
            con.cerrar(rs);
            con.cerrar(st);
        } catch (Exception e) {
            System.out.println(e);
        }
        return ida;
    }

    private String ruc_alm(String id_alm) {
        String ruc = null;
        try {
            Statement st = con.conexion();
            String ver_alm = "select ruc from almacen where idAlmacen = '" + id_alm + "'";
            ResultSet rs = con.consulta(st, ver_alm);
            if (rs.next()) {
                ruc = rs.getString("ruc");
            }
            con.cerrar(rs);
            con.cerrar(st);
        } catch (Exception e) {
            System.out.println(e);
        }
        return ruc;
    }

    private String raz_soc(String id_alm) {
        String raz = null;
        try {
            Statement st = con.conexion();
            String ver_alm = "select raz_soc from almacen where idAlmacen = '" + id_alm + "'";
            ResultSet rs = con.consulta(st, ver_alm);
            if (rs.next()) {
                raz = rs.getString("raz_soc");
            }
            con.cerrar(rs);
            con.cerrar(st);
        } catch (Exception e) {
            System.out.println(e);
        }
        return raz;
    }

    private void btn_anuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_anuActionPerformed
        if (usu.getPer_anu_traslado().equals("1")) {
            int confirmado = JOptionPane.showConfirmDialog(null, "¿Confirma eliminar el traslado?");
            if (JOptionPane.OK_OPTION == confirmado) {
                //anular guia si es pendiente
                alb.setId(Integer.parseInt(t_guias.getValueAt(i, 2).toString()));
                String est_guia = t_guias.getValueAt(i, 8).toString();

                if (est_guia.equals("PENDIENTE")) {

                    try {
                        Statement st = con.conexion();
                        String ver_det_env = "select fecha, ser_doc, nro_doc from traslado where idTraslado = '" + alb.getId() + "'";
                        ResultSet rs = con.consulta(st, ver_det_env);
                        if (rs.next()) {
                            alb.setFecha(rs.getString("fecha"));
                            alb.setSer(rs.getInt("ser_doc"));
                            alb.setNro(rs.getInt("nro_doc"));
                        }
                    } catch (Exception ex) {
                        System.out.println(ex);
                    }

                    Cl_Productos pro = new Cl_Productos();

                    String nom_alm_or = t_guias.getValueAt(i, 3).toString();
                    //ver id almacen origen:
                    int id_ao = ver_id_alm(nom_alm_or);
                    System.out.println("almacen origen " + id_ao + "\n");

                    String ruc = ruc_alm("" + id_ao);
                    String raz = raz_soc("" + id_ao);

                    //seleccionar detalle envio
                    try {
                        Statement st = con.conexion();
                        String ver_det_env = "select cant, idProductos from productos_traslado where idTraslado = '" + alb.getId() + "' ";
                        ResultSet rs = con.consulta(st, ver_det_env);
                        while (rs.next()) {
                            pro.setCan(rs.getDouble("cant"));
                            pro.setId_pro(rs.getInt("idProductos"));
                            // Seleccionar cantidad actual producto en almacen destino
                            try {
                                Statement st1 = con.conexion();
                                String ver_pro = "select cant from producto_almacen where idProductos = '" + pro.getId_pro() + "' and idAlmacen = '" + id_ao + "'";
                                ResultSet rs1 = con.consulta(st1, ver_pro);
                                if (rs1.next()) {
                                    pro.setCan_act_pro(rs1.getDouble("cant"));
                                }
                                con.cerrar(rs1);
                                con.cerrar(st1);
                            } catch (Exception ex) {
                                System.out.println(ex);
                            }
                            double can_new = pro.getCan_act_pro() + pro.getCan();

                            try {
                                Statement st1 = con.conexion();
                                String cam_can = "update producto_almacen set cant = '" + can_new + "' where idProductos = '" + pro.getId_pro() + "' and idAlmacen = '" + id_ao + "'";
                                con.actualiza(st1, cam_can);
                                con.cerrar(st1);
                            } catch (Exception e) {
                                System.out.println(e);
                            }

                            try {
                                Statement st1 = con.conexion();
                                String ins_kardex = "insert into kardex Values (null, '" + alb.getFecha() + "', '" + pro.getId_pro() + "', '" + pro.getCan() + "', '0.00' , '0.00', '0.00', "
                                        + "'" + alb.getSer() + "', '" + alb.getNro() + "', '4', '" + id_ao + "', '" + ruc + "', '" + raz + "', '5')";
                                con.actualiza(st1, ins_kardex);
                                con.cerrar(st1);
                            } catch (Exception ex) {
                                System.out.print(ex);
                            }
                        }
                        con.cerrar(rs);
                        con.cerrar(st);
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    // cambiar cantidades de almacen origen

//                try {
//                    Statement st = con.conexion();
//                    String del_det = "delete * from productos_traslado where idTraslado = '" + alb.getId() + "'";
//                    con.actualiza(st, del_det);
//                    con.cerrar(st);
//                } catch (Exception e) {
//                    System.out.println(e);
//                }
                    try {
                        Statement st = con.conexion();
                        String del_tra = "update traslado set estado = '2' where idTraslado = '" + alb.getId() + "'";
                        con.actualiza(st, del_tra);
                        con.cerrar(st);
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }
            }
            String query = "select motivo, fecha, idTraslado, origen, raz_soc_dest, destino, ser_doc, nro_doc, nick, "
                    + "estado from traslado where origen = '" + frm_menu.alm.getDireccion() + "' or destino = "
                    + "'" + frm_menu.alm.getDireccion() + "' order by fecha desc, idTraslado desc";
            ver_guia(query);
        } else {
            JOptionPane.showMessageDialog(null, "Ud No tiene permisos");
        }
    }//GEN-LAST:event_btn_anuActionPerformed

    private void t_guiasMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_t_guiasMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_t_guiasMouseReleased

    private void llenar_tguias(int idtra) {
        frm_reg_traslado_almacen traslado = null;
        traslado.detalle.addColumn("Cant. Rec.");
        try {
            Statement st = con.conexion();
            String ver_det = "select pt.idProductos, p.desc_pro, p.modelo, p.serie, p.marca, pt.cant, u.desc_und from productos_traslado as pt "
                    + "inner join productos as p on pt.idProductos = p.idProductos inner join und_medida as u on p.idUnd_medida=u.idUnd_medida"
                    + " where pt.idTraslado = '" + idtra + "'";
            ResultSet rs = con.consulta(st, ver_det);
            while (rs.next()) {
                Object[] fila = new Object[5];
                fila[0] = rs.getString("idProductos");
                fila[1] = rs.getString("p.desc_pro") + " " + rs.getString("p.modelo") + " " + rs.getString("p.serie");
                fila[2] = rs.getString("p.marca");
                fila[3] = rs.getDouble("cant");
                fila[4] = rs.getString("u.desc_und");
                traslado.detalle.addRow(fila);
            }
            traslado.t_detalle.setModel(traslado.detalle);
            traslado.t_detalle.getColumnModel().getColumn(0).setPreferredWidth(10);
            traslado.t_detalle.getColumnModel().getColumn(1).setPreferredWidth(350);
            traslado.t_detalle.getColumnModel().getColumn(2).setPreferredWidth(30);
            traslado.t_detalle.getColumnModel().getColumn(3).setPreferredWidth(30);
            traslado.t_detalle.getColumnModel().getColumn(4).setPreferredWidth(30);
            traslado.t_detalle.getColumnModel().getColumn(5).setPreferredWidth(30);
            con.cerrar(rs);
            con.cerrar(st);
        } catch (SQLException e) {
            System.out.println(e);
        }

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_anu;
    private javax.swing.JButton btn_envio;
    private javax.swing.JButton btn_guia;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTable t_guias;
    // End of variables declaration//GEN-END:variables
}
