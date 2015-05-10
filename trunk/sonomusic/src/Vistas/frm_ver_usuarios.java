/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import Clases.Cl_Conectar;
import Clases.Cl_Varios;
import Forms.frm_reg_cambio_contra;
import Forms.frm_reg_usuario;
import Forms.frm_rpt_fechas;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Dereck
 */
public class frm_ver_usuarios extends javax.swing.JInternalFrame {

    Cl_Conectar con = new Cl_Conectar();
    Cl_Varios ven = new Cl_Varios();
    public static DefaultTableModel mostrar;
    int i;
    public static String id;
    public static String rpt;

    /**
     * Creates new form frm_ver_usuarios
     */
    public frm_ver_usuarios() {
        initComponents();

        String query = "select e.dni, e.nom_per, c.tipo_cargo, u.estado from usuario as u inner join empleados"
                + " as e on u.dni = e.dni inner join cargo as c on e.idCargo = c.idCargo";
        cargar_usuarios(query);
    }

    private void cargar_usuarios(String query) {
        try {
            mostrar = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int fila, int columna) {
                    return false;
                }
            };
            Statement st = con.conexion();
            mostrar.addColumn("DNI");
            mostrar.addColumn("Apellidos y Nombres");
            mostrar.addColumn("Cargo");
            mostrar.addColumn("Estado");

            ResultSet rs = con.consulta(st, query);
            while (rs.next()) {
                Object[] fila = new Object[4];
                fila[0] = rs.getString("dni");
                fila[1] = rs.getString("nom_per");
                fila[2] = rs.getString("tipo_cargo");
                if (rs.getString("estado").equals("0")) {
                    fila[3] = "-";
                } else {
                    fila[3] = "ACITVO";
                }
                mostrar.addRow(fila);
            }
            con.cerrar(rs);
            con.cerrar(st);
            t_usuarios.setModel(mostrar);
            t_usuarios.getColumnModel().getColumn(0).setPreferredWidth(75);
            t_usuarios.getColumnModel().getColumn(1).setPreferredWidth(200);
            t_usuarios.getColumnModel().getColumn(2).setPreferredWidth(100);
            t_usuarios.getColumnModel().getColumn(3).setPreferredWidth(80);
        } catch (SQLException ex) {
            System.out.print(ex);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        t_usuarios = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        btn_reg = new javax.swing.JButton();
        btn_inhabilitar = new javax.swing.JButton();
        btn_habilitar = new javax.swing.JButton();
        btn_permisos = new javax.swing.JButton();
        btn_cambiar_pass = new javax.swing.JButton();

        setBackground(new java.awt.Color(254, 254, 254));
        setClosable(true);
        setResizable(true);
        setTitle("Ver Usuarios");

        jScrollPane1.setBackground(new java.awt.Color(254, 254, 254));
        jScrollPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jScrollPane1MousePressed(evt);
            }
        });
        jScrollPane1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jScrollPane1KeyPressed(evt);
            }
        });

        t_usuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "DNI", "Nombres", "Title 3", "Title 4"
            }
        ));
        t_usuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                t_usuariosMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                t_usuariosMousePressed(evt);
            }
        });
        t_usuarios.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                t_usuariosKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(t_usuarios);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/cancel.png"))); // NOI18N
        jButton1.setText("Cerrar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btn_reg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/accept.png"))); // NOI18N
        btn_reg.setText("Registrar");
        btn_reg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_regActionPerformed(evt);
            }
        });

        btn_inhabilitar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/delete.png"))); // NOI18N
        btn_inhabilitar.setText("Inhabilitar");
        btn_inhabilitar.setEnabled(false);
        btn_inhabilitar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_inhabilitarActionPerformed(evt);
            }
        });

        btn_habilitar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/asterisk_orange.png"))); // NOI18N
        btn_habilitar.setText("Habilitar");
        btn_habilitar.setEnabled(false);
        btn_habilitar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_habilitarActionPerformed(evt);
            }
        });

        btn_permisos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/connect.png"))); // NOI18N
        btn_permisos.setText("Permisos");
        btn_permisos.setEnabled(false);
        btn_permisos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_permisosActionPerformed(evt);
            }
        });

        btn_cambiar_pass.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/hammer_screwdriver.png"))); // NOI18N
        btn_cambiar_pass.setText("Cambiar Contraseña");
        btn_cambiar_pass.setEnabled(false);
        btn_cambiar_pass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cambiar_passActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 540, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_reg, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_cambiar_pass, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_habilitar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_inhabilitar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_permisos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn_reg, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_cambiar_pass, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_habilitar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_inhabilitar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_permisos, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btn_regActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_regActionPerformed
        frm_reg_usuario user = new frm_reg_usuario();
        ven.llamar_ventana(user);
        this.dispose();
    }//GEN-LAST:event_btn_regActionPerformed

    private void jScrollPane1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jScrollPane1KeyPressed

    }//GEN-LAST:event_jScrollPane1KeyPressed

    private void t_usuariosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_t_usuariosKeyPressed

    }//GEN-LAST:event_t_usuariosKeyPressed

    private void t_usuariosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_t_usuariosMousePressed
        i = t_usuarios.getSelectedRow();
        btn_permisos.setEnabled(true);
        btn_cambiar_pass.setEnabled(true);
        String estado = t_usuarios.getValueAt(i, 3).toString();
        if (estado.equals("-")) {
            btn_habilitar.setEnabled(true);
            btn_inhabilitar.setEnabled(false);
        } else {
            btn_inhabilitar.setEnabled(true);
            btn_habilitar.setEnabled(false);
        }

    }//GEN-LAST:event_t_usuariosMousePressed

    private void btn_permisosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_permisosActionPerformed
        frm_permisos permi = new frm_permisos();
        String dni = t_usuarios.getValueAt(i, 0).toString();
        String nombre = t_usuarios.getValueAt(i, 1).toString();
        permi.lbl_dni.setForeground(Color.RED);
        permi.lbl_nombre.setForeground(Color.RED);
        permi.lbl_dni.setText(dni);
        permi.lbl_nombre.setText(nombre);
        try {
            Statement st = con.conexion();
            String sql = "select * from usuario where nick = '" + dni + "' ";
            ResultSet rs = con.consulta(st, sql);
            if (rs.next()) {
                permi.pv_rh.setSelected(rs.getBoolean("pv_rh"));
                permi.pr_ade.setSelected(rs.getBoolean("pr_ade"));
                permi.pv_rep.setSelected(rs.getBoolean("pv_rep"));
                permi.pr_ofe.setSelected(rs.getBoolean("pr_ofe"));
                permi.pr_ven.setSelected(rs.getBoolean("pr_ven"));
                permi.pr_comp.setSelected(rs.getBoolean("pv_comp"));
                permi.pr_coms.setSelected(rs.getBoolean("pv_coms"));
                permi.pr_alm.setSelected(rs.getBoolean("pr_alm"));
                permi.pm_alm.setSelected(rs.getBoolean("pm_alm"));
                permi.pr_tras.setSelected(rs.getBoolean("pr_tras"));
                permi.pe_tras.setSelected(rs.getBoolean("pe_tras"));
                permi.pr_prod.setSelected(rs.getBoolean("pr_prod"));
                permi.pm_prod.setSelected(rs.getBoolean("pm_prod"));
                permi.pe_prod.setSelected(rs.getBoolean("pe_prod"));
                permi.pm_docs.setSelected(rs.getBoolean("pm_docs"));
                permi.pm_emp.setSelected(rs.getBoolean("pm_emp"));
                permi.pe_emp.setSelected(rs.getBoolean("pe_emp"));
                permi.pv_caja.setSelected(rs.getBoolean("pv_caj"));
                permi.pm_usu.setSelected(rs.getBoolean("pm_usu"));
                permi.pm_cue.setSelected(rs.getBoolean("pm_cue"));
            }
            con.cerrar(st);
            con.cerrar(rs);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getLocalizedMessage());
        }

        ven.llamar_ventana(permi);
    }//GEN-LAST:event_btn_permisosActionPerformed

    private void btn_habilitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_habilitarActionPerformed
        try {
            String dni = mostrar.getValueAt(i, 0).toString();
            Statement st = con.conexion();
            String sql = "update usuario set estado='1' where dni='" + dni + "' ";
            con.actualiza(st, sql);
            con.cerrar(st);
            String query = "select e.dni, e.nom_per, c.tipo_cargo, u.estado from usuario as u inner join empleados"
                    + " as e on u.dni = e.dni inner join cargo as c on e.idCargo = c.idCargo";
            cargar_usuarios(query);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getLocalizedMessage());
        }
    }//GEN-LAST:event_btn_habilitarActionPerformed

    private void btn_inhabilitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_inhabilitarActionPerformed
        try {
            String dni = mostrar.getValueAt(i, 0).toString();
            Statement st = con.conexion();
            String sql = "update usuario set estado='0' where nick='" + dni + "' ";
            con.actualiza(st, sql);
            con.cerrar(st);
            String query = "select e.dni, e.nom_per, c.tipo_cargo, u.estado from usuario as u inner join empleados"
                    + " as e on u.dni = e.dni inner join cargo as c on e.idCargo = c.idCargo";
            cargar_usuarios(query);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getLocalizedMessage());
        }
    }//GEN-LAST:event_btn_inhabilitarActionPerformed

    private void jScrollPane1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane1MousePressed

    }//GEN-LAST:event_jScrollPane1MousePressed

    private void t_usuariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_t_usuariosMouseClicked
        if (evt.getClickCount() == 2) {
            if (rpt.equals("venta_vendedor")) {
                id = t_usuarios.getValueAt(i, 0).toString();
                frm_rpt_fechas fec = new frm_rpt_fechas();
                fec.usu.setNick(id);
                fec.rpt = "venta_vendedor";
                ven.llamar_ventana(fec);
                this.dispose();
            }

            if (rpt.equals("ganancia_vendedor")) {
                id = t_usuarios.getValueAt(i, 0).toString();
                frm_rpt_fechas fec = new frm_rpt_fechas();
                fec.usu.setNick(id);
                fec.rpt = "ganancia_vendedor";
                ven.llamar_ventana(fec);
                this.dispose();
            }
        }
    }//GEN-LAST:event_t_usuariosMouseClicked

    private void btn_cambiar_passActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cambiar_passActionPerformed
        frm_reg_cambio_contra cambio = new frm_reg_cambio_contra();
        cambio.txt_nick.setText(t_usuarios.getValueAt(i, 0).toString());
        cambio.txt_nom.setText(t_usuarios.getValueAt(i, 1).toString());
        cambio.txt_con_ant.setEditable(true);
        cambio.txt_con_ant.requestFocus();
        ven.llamar_ventana(cambio);
    }//GEN-LAST:event_btn_cambiar_passActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_cambiar_pass;
    private javax.swing.JButton btn_habilitar;
    private javax.swing.JButton btn_inhabilitar;
    private javax.swing.JButton btn_permisos;
    private javax.swing.JButton btn_reg;
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTable t_usuarios;
    // End of variables declaration//GEN-END:variables
}
