/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import Clases.Cl_Conectar;
import Clases.Cl_Cotizacion;
import Clases.Cl_Varios;
import Forms.frm_reg_cotizacion;
import java.awt.Desktop;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

/**
 *
 * @author pc
 */
public class frm_ver_cotizacion extends javax.swing.JInternalFrame {

    Cl_Conectar con = new Cl_Conectar();
    Cl_Varios ven = new Cl_Varios();
    Cl_Cotizacion cot = new Cl_Cotizacion();
    DefaultTableModel mostrar;
    DecimalFormat formato = new DecimalFormat("####.00");
    String valor;
    int i;

    /**
     * Creates new form frm_ver_cotizacion
     */
    public frm_ver_cotizacion() {
        initComponents();

        String query = "select c.idCotizacion, c.nro_doc, cl.nom_per, c.fec_cot, sum(d.cant * d.precio_cot) "
                + "as suma from cotizacion as c inner join detalle_cotizacion as d on c.idCotizacion=d.idCotizacion"
                + " inner join cliente as cl on c.nro_doc=cl.nro_doc group by c.idCotizacion";
        ver_cotizacion(query);
    }

    private void ver_cotizacion(String query) {
        try {
            mostrar = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int fila, int columna) {
                    return false;
                }
            };
            Statement st = con.conexion();
            ResultSet rs = con.consulta(st, query);
            ResultSetMetaData rsMd = rs.getMetaData();
            //La cantidad de columnas que tiene la consulta
            int cantidadColumnas = rsMd.getColumnCount();
            //Establecer como cabezeras el nombre de las colimnas
            mostrar.addColumn("Nro. Cot.");
            mostrar.addColumn("Nro Doc.");
            mostrar.addColumn("Cliente");
            mostrar.addColumn("Fecha Cot.");
            mostrar.addColumn("Monto");

            //Creando las filas para el JTable
            while (rs.next()) {
                Object[] fila = new Object[cantidadColumnas];
                fila[0] = rs.getObject("idCotizacion");
                fila[1] = rs.getObject("nro_doc");
                fila[2] = rs.getObject("nom_per");
                fila[3] = ven.fechaformateada(rs.getString("fec_cot"));
                String nro_doc = rs.getString("nro_doc");
                Double suma = 0.00;
                if (nro_doc.length() > 8) {
                    suma = rs.getDouble("suma") * 1.18;
                } else {
                    suma = rs.getDouble("suma");
                }
                fila[4] = formato.format(suma);
                mostrar.addRow(fila);
            }
            con.cerrar(st);
            con.cerrar(rs);
            t_cotizacion.setModel(mostrar);
            t_cotizacion.getColumnModel().getColumn(0).setPreferredWidth(50);
            t_cotizacion.getColumnModel().getColumn(1).setPreferredWidth(50);
            t_cotizacion.getColumnModel().getColumn(2).setPreferredWidth(200);
            t_cotizacion.getColumnModel().getColumn(3).setPreferredWidth(50);
            t_cotizacion.getColumnModel().getColumn(4).setPreferredWidth(20);
            mostrar.fireTableDataChanged();
        } catch (SQLException e) {
            System.out.print(e);
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
        txt_buscar = new javax.swing.JTextField();
        cbx_tipo = new javax.swing.JComboBox();
        btn_reg = new javax.swing.JButton();
        btn_eli = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        t_cotizacion = new javax.swing.JTable();
        btn_cerrar = new javax.swing.JButton();
        btn_detalle = new javax.swing.JButton();

        setBackground(new java.awt.Color(254, 254, 254));
        setClosable(true);
        setTitle("Cotizaciones");

        jLabel1.setForeground(new java.awt.Color(212, 1, 1));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/find.png"))); // NOI18N
        jLabel1.setText("Buscar:");

        txt_buscar.setEditable(false);
        txt_buscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_buscarKeyPressed(evt);
            }
        });

        cbx_tipo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Nro Documento", "Nombre" }));
        cbx_tipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbx_tipoActionPerformed(evt);
            }
        });

        btn_reg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/application_add.png"))); // NOI18N
        btn_reg.setText("Registrar");
        btn_reg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_regActionPerformed(evt);
            }
        });

        btn_eli.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/application_edit.png"))); // NOI18N
        btn_eli.setText("Eliminar");
        btn_eli.setEnabled(false);
        btn_eli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_eliActionPerformed(evt);
            }
        });

        jScrollPane1.setBackground(new java.awt.Color(254, 254, 254));

        t_cotizacion.setModel(new javax.swing.table.DefaultTableModel(
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
        t_cotizacion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                t_cotizacionMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(t_cotizacion);

        btn_cerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/cancel.png"))); // NOI18N
        btn_cerrar.setText("Cerrar");
        btn_cerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cerrarActionPerformed(evt);
            }
        });

        btn_detalle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/clipboard_text.png"))); // NOI18N
        btn_detalle.setText("Ver Detalle");
        btn_detalle.setEnabled(false);
        btn_detalle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_detalleActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 652, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbx_tipo, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_reg))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btn_detalle)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_eli)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_cerrar)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbx_tipo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_reg, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 371, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_cerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_detalle, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_eli, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jLabel1.getAccessibleContext().setAccessibleName("");
        txt_buscar.getAccessibleContext().setAccessibleName("");
        cbx_tipo.getAccessibleContext().setAccessibleName("");
        btn_reg.getAccessibleContext().setAccessibleName("");
        btn_eli.getAccessibleContext().setAccessibleName("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_cerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cerrarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btn_cerrarActionPerformed

    private void t_cotizacionMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_t_cotizacionMousePressed
        i = t_cotizacion.getSelectedRow();
        cot.setId(Integer.parseInt(t_cotizacion.getValueAt(i, 0).toString()));
        btn_eli.setEnabled(true);
        btn_detalle.setEnabled(true);
    }//GEN-LAST:event_t_cotizacionMousePressed

    private void btn_regActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_regActionPerformed
        frm_reg_cotizacion cotizacion = new frm_reg_cotizacion();
        ven.llamar_ventana(cotizacion);
        this.dispose();
    }//GEN-LAST:event_btn_regActionPerformed

    private void btn_detalleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_detalleActionPerformed
        Connection st = con.conx();
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("cot", cot.getId());

        try {
            JasperReport jasperReport;
            JasperPrint jasperPrint;
            jasperReport = JasperCompileManager.compileReport("Reports//rpt_cotizacion.jrxml");
            jasperPrint = JasperFillManager.fillReport(
                    jasperReport, parametros, st);
            JasperExportManager.exportReportToPdfFile(
                    jasperPrint, "Reports/rpt_cotizacion_" + cot.getId() + ".pdf");

            try {
                File file = new File("Reports/rpt_cotizacion_" + cot.getId() + ".pdf");
                Desktop.getDesktop().open(file);
            } catch (IOException e) {
                System.out.print(e);
                JOptionPane.showMessageDialog(null, e);
            }

        } catch (JRException ex) {
            System.out.print(ex);
            JOptionPane.showMessageDialog(null, ex);

        }

    }//GEN-LAST:event_btn_detalleActionPerformed

    private void txt_buscarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_buscarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String buscar = txt_buscar.getText();
            String query = "select c.idCotizacion, c.nro_doc, cl.nom_per, c.fec_cot, sum(d.cant * d.precio_cot) "
                    + "as suma from cotizacion as c inner join Detalle_Cotizacion as d on c.idCotizacion=d.idCotizacion"
                    + " inner join cliente as cl on c.nro_doc=cl.nro_doc where " + valor + "  like '%" + buscar + "%' group by c.idCotizacion";
            ver_cotizacion(query);
        }
    }//GEN-LAST:event_txt_buscarKeyPressed

    private void cbx_tipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbx_tipoActionPerformed
        if (cbx_tipo.isDisplayable()) {
            if (cbx_tipo.getSelectedIndex() == 0) {
                valor = "c.nro_doc";
                txt_buscar.setEditable(true);
                txt_buscar.requestFocus();
            } else {
                valor = "cl.nom_per";
                txt_buscar.setEditable(true);
                txt_buscar.requestFocus();
            }
        }
    }//GEN-LAST:event_cbx_tipoActionPerformed

    private void btn_eliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_eliActionPerformed
        int confirmado = JOptionPane.showConfirmDialog(null, "¿Confirma eliminar la cotizacion?", "Seguridad", JOptionPane.WARNING_MESSAGE);

        if (JOptionPane.OK_OPTION == confirmado) {
            cot.setId((int) t_cotizacion.getValueAt(i, 0));
            try {
                Statement st = con.conexion();
                String query = "delete  from detalle_cotizacion where idCotizacion ='" + cot.getId() + "'";
                con.actualiza(st, query);
                con.cerrar(st);
                String query1 = "select c.idCotizacion, c.nro_doc, cl.nom_per, c.fec_cot, sum(d.cant * d.precio_cot) "
                        + "as suma from cotizacion as c inner join detalle_cotizacion as d on c.idCotizacion=d.idCotizacion"
                        + " inner join cliente as cl on c.nro_doc=cl.nro_doc group by c.idCotizacion";
                ver_cotizacion(query1);
                btn_eli.setEnabled(false);
            } catch (Exception ex) {
                System.out.print(ex + " " + ex.getLocalizedMessage() + " " + ex.getCause());
            }
        }
    }//GEN-LAST:event_btn_eliActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_cerrar;
    private javax.swing.JButton btn_detalle;
    private javax.swing.JButton btn_eli;
    private javax.swing.JButton btn_reg;
    private javax.swing.JComboBox cbx_tipo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable t_cotizacion;
    private javax.swing.JTextField txt_buscar;
    // End of variables declaration//GEN-END:variables
}
