/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import Clases.Cl_Conectar;
import Clases.Cl_Requerimiento;
import Clases.Cl_Varios;
import Forms.frm_reg_solicitud;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import sonomusic.frm_menu;

/**
 *
 * @author luis-d
 */
public class frm_ver_solicitudes extends javax.swing.JInternalFrame {

    Cl_Varios ven = new Cl_Varios();
    Cl_Conectar con = new Cl_Conectar();
    Cl_Requerimiento req = new Cl_Requerimiento();
    DefaultTableModel mostrar;
    int i;

    /**
     * Creates new form frm_ver_solicitudes
     */
    public frm_ver_solicitudes() {
        initComponents();
        String query = "select sol.fec_sol, date_add(sol.fec_sol, INTERVAL sol.plazo DAY) as fec_apro, sol.idsolicitud, "
                + "sol.estado, sol.id_alm_ori, sol.nick, emp.nom_per, sol.id_alm_des, sol.plazo, sol.fec_env from "
                + "solicitud_articulos as sol inner join usuario as usu on sol.nick=usu.nick inner join empleados as emp on "
                + "usu.dni=emp.dni where id_alm_ori = '" + frm_menu.alm.getId() + "' or id_alm_des = "
                + "'" + frm_menu.alm.getId() + "' order by fec_apro desc";
        ver_requerimientos(query);
    }

    private void ver_requerimientos(String query) {
        try {
            mostrar = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int fila, int columna) {
                    return false;
                }
            };
            Statement st = con.conexion();
            ResultSet rs = con.consulta(st, query);

            //Establecer como cabezeras el nombre de las colimnas
            mostrar.addColumn("Fec. Ing.");
            mostrar.addColumn("F. Env. Aprox.");
            mostrar.addColumn("Nro");
            mostrar.addColumn("Estado");
            mostrar.addColumn("Alm. Origen");
            mostrar.addColumn("Solicitante");
            mostrar.addColumn("Alm. Destino");
            mostrar.addColumn("Plazo dias");
            mostrar.addColumn("Fec. Env.");

            //Creando las filas para el JTable
            while (rs.next()) {
                Object[] fila = new Object[9];
                fila[0] = rs.getObject("fec_sol");
                fila[1] = rs.getObject("fec_apro");
                fila[2] = rs.getObject("idsolicitud");
                if (rs.getString("estado").equals("0")) {
                    fila[3] = "PENDIENTE";
                } else {
                    fila[3] = "ENVIADO";
                }
                fila[4] = ver_nom(rs.getString("id_alm_ori"));
                fila[5] = rs.getObject("nom_per");
                fila[6] = ver_nom(rs.getString("id_alm_des"));
                fila[7] = rs.getObject("plazo");
                if (rs.getString("fec_env").equals("7000-01-01")) {
                    fila[8] = "-";
                } else {
                    fila[8] = rs.getObject("fec_env");
                }
                mostrar.addRow(fila);

            }
            con.cerrar(st);
            con.cerrar(rs);
            t_requerimiento.setModel(mostrar);
            t_requerimiento.getColumnModel().getColumn(0).setPreferredWidth(80);
            t_requerimiento.getColumnModel().getColumn(1).setPreferredWidth(80);
            t_requerimiento.getColumnModel().getColumn(2).setPreferredWidth(40);
            t_requerimiento.getColumnModel().getColumn(3).setPreferredWidth(80);
            t_requerimiento.getColumnModel().getColumn(4).setPreferredWidth(80);
            t_requerimiento.getColumnModel().getColumn(5).setPreferredWidth(180);
            t_requerimiento.getColumnModel().getColumn(6).setPreferredWidth(80);
            t_requerimiento.getColumnModel().getColumn(7).setPreferredWidth(50);
            t_requerimiento.getColumnModel().getColumn(8).setPreferredWidth(80);
            //ven.centrar_celda(t_requerimiento, 0);
            //ven.centrar_celda(t_requerimiento, 1);
            //ven.centrar_celda(t_requerimiento, 2);
            //ven.centrar_celda(t_requerimiento, 8);
            mostrar.fireTableDataChanged();
        } catch (SQLException e) {
            System.out.print(e);
        }
    }

    private String ver_nom(String idalm) {
        String nomalm = null;
        try {
            Statement st = con.conexion();
            String ver_alm = "select nom_alm from almacen where idAlmacen='" + idalm + "'";
            ResultSet rs = con.consulta(st, ver_alm);
            if (rs.next()) {
                nomalm = rs.getString("nom_alm");
            }
        } catch (SQLException ex) {
            System.out.print(ex);
            JOptionPane.showMessageDialog(null, ex.getLocalizedMessage());
        }
        return nomalm;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btn_cer = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        t_requerimiento = new javax.swing.JTable();
        btn_reg = new javax.swing.JButton();
        btn_ver = new javax.swing.JButton();
        btn_imp = new javax.swing.JButton();
        btn_eli = new javax.swing.JButton();

        setBackground(new java.awt.Color(254, 254, 254));
        setClosable(true);
        setTitle("Listar Pedidos");

        btn_cer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/cancel.png"))); // NOI18N
        btn_cer.setText("Cerrar");
        btn_cer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cerActionPerformed(evt);
            }
        });

        jScrollPane1.setBackground(new java.awt.Color(254, 254, 254));

        t_requerimiento.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Fec. Ing.", "Nro.", "Estado", "Alm. Origen.", "Solicitante", "Alm. Destino", "Plazo Dias", "Fec. Envio"
            }
        ));
        t_requerimiento.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        t_requerimiento.setAutoscrolls(false);
        t_requerimiento.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                t_requerimientoMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(t_requerimiento);

        btn_reg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/add.png"))); // NOI18N
        btn_reg.setText("Crear Pedido");
        btn_reg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_regActionPerformed(evt);
            }
        });

        btn_ver.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/clipboard_text.png"))); // NOI18N
        btn_ver.setText("Ver Ped.");
        btn_ver.setEnabled(false);
        btn_ver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_verActionPerformed(evt);
            }
        });

        btn_imp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/clipboard_text.png"))); // NOI18N
        btn_imp.setText("Imp. Ped.");
        btn_imp.setEnabled(false);

        btn_eli.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/cross.png"))); // NOI18N
        btn_eli.setText("Eliminar");
        btn_eli.setEnabled(false);
        btn_eli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_eliActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 691, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn_eli)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_imp)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_ver)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_reg)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_cer)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_cer, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_reg, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_ver, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_imp, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_eli, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 401, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_regActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_regActionPerformed
        frm_reg_solicitud solicitud = new frm_reg_solicitud();
        frm_menu menu = null;
        solicitud.txt_id_ori.setText("" + menu.alm.getId());
        solicitud.txt_nom_ori.setText(menu.alm.getNom());
        solicitud.txt_id_des.requestFocus();
        ven.llamar_ventana(solicitud);
        this.dispose();
    }//GEN-LAST:event_btn_regActionPerformed

    private void btn_verActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_verActionPerformed
        frm_reg_solicitud soli = new frm_reg_solicitud();

        int idsol = Integer.parseInt(t_requerimiento.getValueAt(i, 2).toString());
        try {
            Statement st = con.conexion();
            String ver_sol = "select * from solicitud_articulos where idsolicitud = '" + idsol + "'";
            ResultSet rs = con.consulta(st, ver_sol);
            if (rs.next()) {
                soli.txt_id_ori.setText(rs.getString("id_alm_ori"));
                soli.txt_id_des.setText(rs.getString("id_alm_des"));
                soli.txt_fec.setText(ven.fechaformateada(rs.getString("fec_sol")));
                soli.spn_dias.setValue(rs.getInt("plazo"));
                soli.btn_env.setEnabled(true);
                soli.btn_reg.setEnabled(false);
                soli.btn_reg.setVisible(false);
            }
            con.cerrar(rs);
            con.cerrar(st);
        } catch (SQLException ex) {
            System.out.println(ex);
            JOptionPane.showMessageDialog(null, ex.getLocalizedMessage());
        }

        try {
            soli.detalle.addColumn("Cant. Env.");
            Statement st = con.conexion();
            String ver_det_ped = "select ds.idProductos, p.desc_pro, p.modelo, p.serie, p.marca, ds.cant_sol, u.desc_und from detalle_solicitud "
                    + "as ds inner join productos as p on ds.idProductos = p.idProductos inner join und_medida as u on p.idUnd_medida=u.idUnd_medida"
                    + " where ds.idsolicitud = '" + idsol + "'";
            ResultSet rs = con.consulta(st, ver_det_ped);
            while (rs.next()) {
                Object[] fila = new Object[6];
                fila[0] = rs.getString("idProductos");
                fila[1] = rs.getString("p.desc_pro") + " " + rs.getString("p.modelo") + " " + rs.getString("p.serie");
                fila[2] = rs.getString("p.marca");
                fila[3] = rs.getString("cant_sol");
                fila[4] = rs.getString("u.desc_und");
                fila[5] = '0';
                soli.detalle.addRow(fila);
            }

            soli.t_solicitud.setModel(soli.detalle);
            soli.t_solicitud.getColumnModel().getColumn(0).setPreferredWidth(30);
            soli.t_solicitud.getColumnModel().getColumn(1).setPreferredWidth(300);
            soli.t_solicitud.getColumnModel().getColumn(2).setPreferredWidth(90);
            soli.t_solicitud.getColumnModel().getColumn(3).setPreferredWidth(60);
            soli.t_solicitud.getColumnModel().getColumn(4).setPreferredWidth(80);
            soli.t_solicitud.getColumnModel().getColumn(5).setPreferredWidth(60);
            ven.derecha_celda(soli.t_solicitud, 3);

            con.cerrar(rs);
            con.cerrar(st);
        } catch (SQLException ex) {
            System.out.println(ex);
            JOptionPane.showMessageDialog(null, ex.getLocalizedMessage());
        }
        soli.btn_env.setVisible(true);
        soli.t_solicitud.requestFocus();
        soli.idsol = idsol;
        ven.llamar_ventana(soli);
        soli.t_solicitud.requestFocus();
        this.dispose();
    }//GEN-LAST:event_btn_verActionPerformed

    private void btn_cerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cerActionPerformed
        this.dispose();
    }//GEN-LAST:event_btn_cerActionPerformed

    private void t_requerimientoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_t_requerimientoMousePressed
        i = t_requerimiento.getSelectedRow();
        if (!t_requerimiento.getValueAt(i, 4).toString().equals(frm_menu.alm.getNom()) & 
                !t_requerimiento.getValueAt(i, 3).toString().equals("ENVIADO")) {
            btn_ver.setEnabled(true);
        } else {
            btn_ver.setEnabled(false);
        }

    }//GEN-LAST:event_t_requerimientoMousePressed

    private void btn_eliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_eliActionPerformed
        int idsol = Integer.parseInt(t_requerimiento.getValueAt(i, 0).toString());
        // Eliminando detalle de solicitud.
        try {
            Statement st = con.conexion();
            String eli_det = "delete * from detalle_solicitud where idsolicitud = '"+idsol+"'";
            con.actualiza(st, eli_det);
            con.cerrar(st);
        } catch (Exception ex) {
            System.out.println(ex);
        }
        
        try {
            Statement st = con.conexion();
            String eli_sol = "delete * from solicitud_articulos where idsolicitud = '"+idsol+"'";
            con.actualiza(st, eli_sol);
            con.cerrar(st);
        } catch (Exception e) {
            System.out.println(e);
        }
        
        String query = "select sol.fec_sol, date_add(sol.fec_sol, INTERVAL sol.plazo DAY) as fec_apro, sol.idsolicitud, "
                + "sol.estado, sol.id_alm_ori, sol.nick, emp.nom_per, sol.id_alm_des, sol.plazo, sol.fec_env from "
                + "solicitud_articulos as sol inner join usuario as usu on sol.nick=usu.nick inner join empleados as emp on "
                + "usu.dni=emp.dni where id_alm_ori = '" + frm_menu.alm.getId() + "' or id_alm_des = "
                + "'" + frm_menu.alm.getId() + "' order by fec_apro desc";
        ver_requerimientos(query);
        
        
    }//GEN-LAST:event_btn_eliActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_cer;
    private javax.swing.JButton btn_eli;
    private javax.swing.JButton btn_imp;
    private javax.swing.JButton btn_reg;
    private javax.swing.JButton btn_ver;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable t_requerimiento;
    // End of variables declaration//GEN-END:variables
}
