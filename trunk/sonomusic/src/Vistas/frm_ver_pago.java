/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import Clases.Cl_Conectar;
import Clases.Cl_Varios;
import Forms.frm_reg_pago;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author pc
 */
public class frm_ver_pago extends javax.swing.JInternalFrame {

    Cl_Conectar con = new Cl_Conectar();
    Cl_Varios ven = new Cl_Varios();
    DefaultTableModel modelo;
    int i;

    public frm_ver_pago() {
        initComponents();
        String sql = "select p.idpago, p.fecha , p.comisiones, p.otrosingresos, p.adelantos, p.otrosdescuentos"
                + ", p.dni, e.nom_per, e.tel_per, e.tel2_per, c.tipo_cargo, p.salario from pago as p inner join empleados as e "
                + "on p.dni=e.dni inner join cargo as c on e.idCargo=c.idCargo order by p.idpago";
        ver_pago(sql);
    }
    
    void ver_pago(String sql) {
        modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int i, int i1) {
                return false; 
            }
        };
        try {
            Statement st = con.conexion();
            modelo.addColumn("Id");
            modelo.addColumn("Fecha");
            modelo.addColumn("Dni");
            modelo.addColumn("Nombre");
            modelo.addColumn("Cargo");
            modelo.addColumn("Comision");
            modelo.addColumn("Otros Ingresos");
            modelo.addColumn("Adelantos");
            modelo.addColumn("Otros Descuentos");
            modelo.addColumn("Salario");            
           
            ResultSet rs = con.consulta(st, sql);
            Object[] dato = new Object[10];
            while (rs.next()) {
                dato[0] = rs.getObject("idpago");
                dato[1] = rs.getObject("fecha");
                dato[2] = rs.getObject("dni");
                dato[3] = rs.getObject("nom_per");
                dato[4] = rs.getObject("tipo_cargo");
                dato[5] = rs.getObject("comisiones");
                dato[6] = rs.getObject("otrosingresos");
                dato[7] = rs.getObject("adelantos");
                dato[8] = rs.getObject("otrosdescuentos");
                dato[9] = rs.getObject("salario");                             
                modelo.addRow(dato);
            }
            con.cerrar(st);
            con.cerrar(rs);
            t_pago.setModel(modelo);
            t_pago.getColumnModel().getColumn(0).setPreferredWidth(20);
            t_pago.getColumnModel().getColumn(1).setPreferredWidth(20);
            t_pago.getColumnModel().getColumn(2).setPreferredWidth(20);
            t_pago.getColumnModel().getColumn(3).setPreferredWidth(180);
            t_pago.getColumnModel().getColumn(4).setPreferredWidth(100);
            t_pago.getColumnModel().getColumn(5).setPreferredWidth(20);
            t_pago.getColumnModel().getColumn(6).setPreferredWidth(50);
            t_pago.getColumnModel().getColumn(7).setPreferredWidth(20);
            t_pago.getColumnModel().getColumn(8).setPreferredWidth(50);
            t_pago.getColumnModel().getColumn(9).setPreferredWidth(80);            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e);
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txt_bus = new javax.swing.JTextField();
        cbx_bus = new javax.swing.JComboBox();
        btn_reg = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        t_pago = new javax.swing.JTable();
        btn_eliminar = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setClosable(true);

        jLabel1.setText("Buscar:");

        txt_bus.setEditable(false);
        txt_bus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_busKeyPressed(evt);
            }
        });

        cbx_bus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Fecha", "Dni", "Nombre", "Cargo" }));
        cbx_bus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbx_busActionPerformed(evt);
            }
        });

        btn_reg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/add.png"))); // NOI18N
        btn_reg.setText("Registrar");
        btn_reg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_regActionPerformed(evt);
            }
        });

        t_pago.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6", "Title 7", "Title 8", "Title 9", "Title 10", "Title 11"
            }
        ));
        t_pago.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        t_pago.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                t_pagoMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(t_pago);

        btn_eliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/cross.png"))); // NOI18N
        btn_eliminar.setText("Eliminar");
        btn_eliminar.setEnabled(false);
        btn_eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_eliminarActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/cancel.png"))); // NOI18N
        jButton2.setText("Cancelar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1001, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_bus, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbx_bus, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_reg, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn_eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_bus, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbx_bus, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_reg, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void txt_busKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_busKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String bus = txt_bus.getText();
            if (!bus.isEmpty()) {
                if (cbx_bus.getSelectedIndex() == 0) {
                    String sql = "select p.idpago, p.fecha , p.comisiones, p.otrosingresos, p.adelantos, p.otrosdescuentos"
                            + ", p.dni, e.nom_per, e.tel_per, e.tel2_per, c.tipo_cargo, p.salario from pago as p inner join empleados as e "
                            + "on p.dni=e.dni inner join cargo as c on e.idCargo=c.idCargo where p.fecha like '%" + bus + "%' order by p.fecha";
                    ver_pago(sql);
                }
                if (cbx_bus.getSelectedIndex() == 1) {
                    String sql = "select p.idpago, p.fecha , p.comisiones, p.otrosingresos, p.adelantos, p.otrosdescuentos"
                            + ", p.dni, e.nom_per, e.tel_per, e.tel2_per, c.tipo_cargo, p.salario from pago as p inner join empleados as e "
                            + "on p.dni=e.dni inner join cargo as c on e.idCargo=c.idCargo where p.dni like '%" + bus + "%' order by p.idpago";
                    ver_pago(sql);
                }
                if (cbx_bus.getSelectedIndex() == 2) {
                    String sql = "select p.idpago, p.fecha , p.comisiones, p.otrosingresos, p.adelantos, p.otrosdescuentos"
                            + ", p.dni, e.nom_per, e.tel_per, e.tel2_per, c.tipo_cargo, p.salario from pago as p inner join empleados as e "
                            + "on p.dni=e.dni inner join cargo as c on e.idCargo=c.idCargo where e.nom_per like '%" + bus + "%' order by p.idpago";
                    ver_pago(sql);
                }
                if (cbx_bus.getSelectedIndex() == 3) {
                    String sql = "select p.idpago, p.fecha , p.comisiones, p.otrosingresos, p.adelantos, p.otrosdescuentos"
                            + ", p.dni, e.nom_per, e.tel_per, e.tel2_per, c.tipo_cargo, p.salario from pago as p inner join empleados as e "
                            + "on p.dni=e.dni inner join cargo as c on e.idCargo=c.idCargo where c.tipo_cargo like '%" + bus + "%' order by c.tipo_cargo";
                    ver_pago(sql);
                }
            }
        }
    }//GEN-LAST:event_txt_busKeyPressed

    private void cbx_busActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbx_busActionPerformed
        txt_bus.setEditable(true);
        txt_bus.requestFocus();
    }//GEN-LAST:event_cbx_busActionPerformed

    private void btn_eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_eliminarActionPerformed
        try {
            Statement st = con.conexion();
            String id = t_pago.getValueAt(i, 0).toString();
            String sql = "delete from pago where idpago= '" + id + "'";
            con.actualiza(st, sql);
            con.cerrar(st);
            String query = "select p.idpago, p.fecha , p.comisiones, p.otrosingresos, p.adelantos, p.otrosdescuentos"
                    + ", p.dni, e.nom_per, e.tel_per, e.tel2_per, c.tipo_cargo, p.salario from pago as p inner join empleados as e "
                    + "on p.dni=e.dni inner join cargo as c on e.idCargo=c.idCargo order by p.idpago";
            ver_pago(query);
            btn_eliminar.setEnabled(false);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getLocalizedMessage());
        }
    }//GEN-LAST:event_btn_eliminarActionPerformed

    private void t_pagoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_t_pagoMousePressed
        i = t_pago.getSelectedRow();
        btn_eliminar.setEnabled(true);
    }//GEN-LAST:event_t_pagoMousePressed

    private void btn_regActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_regActionPerformed
        frm_reg_pago pago = new frm_reg_pago();
        ven.llamar_ventana(pago);
        this.dispose();
    }//GEN-LAST:event_btn_regActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_eliminar;
    private javax.swing.JButton btn_reg;
    private javax.swing.JComboBox cbx_bus;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable t_pago;
    private javax.swing.JTextField txt_bus;
    // End of variables declaration//GEN-END:variables
}
