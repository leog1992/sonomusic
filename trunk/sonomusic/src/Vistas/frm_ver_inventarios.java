/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import Clases.Cl_Conectar;
import Clases.Cl_Varios;
import Forms.frm_reg_inventario;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Oyanguren
 */
public class frm_ver_inventarios extends javax.swing.JInternalFrame {

    Cl_Varios ven = new Cl_Varios();
    Cl_Conectar con = new Cl_Conectar();
    int fila_seleccionada;

    DefaultTableModel detalle;

    /**
     * Creates new form frm_ver_inventarios
     */
    public frm_ver_inventarios() {
        initComponents();

        ver_inventarios();
    }

    private void ver_inventarios() {
        detalle = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int fila, int columna) {
                return false;
            }
        };
        detalle.addColumn("Id");
        detalle.addColumn("Año");
        detalle.addColumn("Id");
        detalle.addColumn("Almacen");
        detalle.addColumn("Tipo");
        detalle.addColumn("Fecha Ejecutado");
        try {
            Statement st = con.conexion();
            String c_inventario = "select i.id, i.anio, i.tipo, i.fecha_ejecucion, i.almacen, a.nom_alm from inventario as i inner join almacen as a "
                    + "on i.almacen = a.idalmacen order by i.fecha_ejecucion desc, i.id desc, i.anio asc";
            ResultSet rs = con.consulta(st, c_inventario);
            while (rs.next()) {
                Object filas[] = new Object[6];
                filas[0] = rs.getString("id");
                filas[1] = rs.getString("anio");
                filas[2] = rs.getString("almacen");
                filas[3] = rs.getString("nom_alm");
                filas[4] = rs.getString("tipo");
                filas[5] = ven.fechaformateada(rs.getString("fecha_ejecucion"));
                detalle.addRow(filas);
            }
            con.cerrar(rs);
            con.cerrar(st);
        } catch (Exception e) {
        }
        t_inventario.setModel(detalle);
        t_inventario.getColumnModel().getColumn(0).setPreferredWidth(40);
        t_inventario.getColumnModel().getColumn(1).setPreferredWidth(40);
        t_inventario.getColumnModel().getColumn(2).setPreferredWidth(20);
        t_inventario.getColumnModel().getColumn(3).setPreferredWidth(70);
        t_inventario.getColumnModel().getColumn(4).setPreferredWidth(70);
        t_inventario.getColumnModel().getColumn(5).setPreferredWidth(70);
        ven.centrar_celda(t_inventario, 0);
        ven.centrar_celda(t_inventario, 1);
        ven.centrar_celda(t_inventario, 2);
        ven.centrar_celda(t_inventario, 3);
        ven.centrar_celda(t_inventario, 4);
        ven.centrar_celda(t_inventario, 5);
        detalle.fireTableDataChanged();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btn_cerrar = new javax.swing.JButton();
        btn_registrar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        t_inventario = new javax.swing.JTable();
        btn_reporte = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setTitle("Listar Inventarios");

        btn_cerrar.setText("Cerrar");
        btn_cerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cerrarActionPerformed(evt);
            }
        });

        btn_registrar.setText("Registrar");
        btn_registrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_registrarActionPerformed(evt);
            }
        });

        t_inventario.setModel(new javax.swing.table.DefaultTableModel(
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
        t_inventario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                t_inventarioMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(t_inventario);

        btn_reporte.setText("Ver Reporte");
        btn_reporte.setEnabled(false);
        btn_reporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_reporteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btn_reporte)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_registrar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_cerrar))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 885, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_cerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_registrar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_reporte, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 436, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_registrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_registrarActionPerformed
        frm_reg_inventario inv = new frm_reg_inventario();
        ven.llamar_ventana(inv);
        this.dispose();
    }//GEN-LAST:event_btn_registrarActionPerformed

    private void btn_cerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cerrarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btn_cerrarActionPerformed

    private void t_inventarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_t_inventarioMouseClicked
        if (evt.getClickCount() == 2) {
            btn_reporte.setEnabled(true);
            fila_seleccionada = t_inventario.getSelectedRow();
        }
    }//GEN-LAST:event_t_inventarioMouseClicked

    private void btn_reporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_reporteActionPerformed
        if (fila_seleccionada > -1) {
            String id, anio, almacen;
            id = t_inventario.getValueAt(fila_seleccionada, 0).toString();
            anio = t_inventario.getValueAt(fila_seleccionada, 1).toString();
            almacen = t_inventario.getValueAt(fila_seleccionada, 2).toString();
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            parametros.put("anio", anio);
            parametros.put("almacen", almacen);
            ven.ver_reporte("rpt_inventario", parametros);
            btn_reporte.setEnabled(false);
        }
    }//GEN-LAST:event_btn_reporteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_cerrar;
    private javax.swing.JButton btn_registrar;
    private javax.swing.JButton btn_reporte;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable t_inventario;
    // End of variables declaration//GEN-END:variables
}
