/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

import Clases.Cl_Almacen;
import Clases.Cl_Cliente;
import Clases.Cl_Conectar;
import Clases.Cl_Productos;
import Clases.Cl_Usuario;
import Clases.Cl_Varios;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;
import sonomusic.frm_menu;

/**
 *
 * @author pc
 */
public class frm_rpt_fechas extends javax.swing.JInternalFrame {

    Cl_Conectar con = new Cl_Conectar();
    Cl_Varios ven = new Cl_Varios();
    public Cl_Productos pro = new Cl_Productos();
    public Cl_Almacen alm = new Cl_Almacen();
    public Cl_Usuario usu = new Cl_Usuario();
    public Cl_Cliente cli = new Cl_Cliente();
    public static String rpt = "";

    /**
     * Creates new form frm_rpt_fechas
     */
    public frm_rpt_fechas() {
        initComponents();
        txt_fec_ini.setText(ven.fechaformateada(ven.getFechaActual()));
        txt_fec_fin.setText(ven.fechaformateada(ven.getFechaActual()));
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
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        txt_fec_ini = new javax.swing.JFormattedTextField();
        txt_fec_fin = new javax.swing.JFormattedTextField();

        setClosable(true);
        setTitle("Imprimir Reportes");

        jLabel1.setText("Ingrese Fechas:");
        jLabel1.setFocusable(false);

        jLabel2.setText("Fecha Inicio:");
        jLabel2.setFocusable(false);

        jLabel3.setText("Fecha Fin:");
        jLabel3.setFocusable(false);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/cancel.png"))); // NOI18N
        jButton1.setText("Cerrar");
        jButton1.setFocusable(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        try {
            txt_fec_ini.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txt_fec_ini.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_fec_ini.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_fec_iniActionPerformed(evt);
            }
        });
        txt_fec_ini.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_fec_iniKeyPressed(evt);
            }
        });

        txt_fec_fin.setEditable(false);
        try {
            txt_fec_fin.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txt_fec_fin.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_fec_fin.setFocusable(false);
        txt_fec_fin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_fec_finKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txt_fec_ini, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_fec_fin, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_fec_ini, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_fec_fin, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txt_fec_iniKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_fec_iniKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (txt_fec_ini.getText().trim().length() == 10) {
                txt_fec_fin.setFocusable(true);
                txt_fec_fin.setEditable(true);
                txt_fec_fin.requestFocus();
            }
        }
    }//GEN-LAST:event_txt_fec_iniKeyPressed

    private void txt_fec_finKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_fec_finKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String fec_ini = ven.fechabase(txt_fec_ini.getText());
            String fec_fin = ven.fechabase(txt_fec_fin.getText());

            // CARGAR REPORTE KARDEX X ALMACEN
            if (rpt.equals("kardex")) {
                Map<String, Object> parametros = new HashMap<>();
                parametros.put("fec_fin", fec_fin);
                parametros.put("fec_ini", fec_ini);
                parametros.put("idalm", alm.getId());
                parametros.put("idmat", pro.getId_pro());
                ven.ver_reporte("rpt_kardex", parametros);
            }
            // REPORTE PRODUCTOS X ALMACEN
            if (rpt.equals("rpt_prod_alm")) {
                Map<String, Object> parametros = new HashMap<>();
                parametros.put("idalm", alm.getId());
                ven.ver_reporte("rpt_prod_alm", parametros);
            }
            // REPORTE DE COMPRAS EN GENERAL
            if (rpt.equals("compras")) {
                Map<String, Object> parametros = new HashMap<>();
                parametros.put("fecha_pago", fec_fin);
                parametros.put("fecha_doc", fec_ini);
                ven.ver_reporte("rpt_compras", parametros);
            }
            // REPORTE DE COMPRAS POR PRODUCTO
            if (rpt.equals("compra_producto")) {
                Map<String, Object> parametros = new HashMap<>();
                parametros.put("idproducto", pro.getId_pro());
                parametros.put("fecha_pago", fec_fin);
                parametros.put("fecha_doc", fec_ini);
                ven.ver_reporte("rpt_compras_producto", parametros);
            }
            // REPORTE DE VENTAS EN GENERAL
            if (rpt.equals("venta_total")) {
                Map<String, Object> parametros = new HashMap<>();
                parametros.put("fecha_inicio", fec_ini);
                parametros.put("fecha_fin", fec_fin);
                ven.ver_reporte("rpt_ventas", parametros);
            }
            // REPORTE DE VENTAS X ALMACEN
            if (rpt.equals("venta_almacen")) {
                Map<String, Object> parametros = new HashMap<>();
                parametros.put("fecha_inicio", fec_ini);
                parametros.put("fecha_fin", fec_fin);
                parametros.put("almacen",alm.getId() );
                ven.ver_reporte("rpt_ventas_almacen", parametros);
            }
            // REPORTE E VENTAS X VENDEDOR
            if (rpt.equals("venta_vendedor")) {
                Map<String, Object> parametros = new HashMap<>();
                parametros.put("fecha_inicio", fec_ini);
                parametros.put("fecha_fin", fec_fin);
                parametros.put("usuario",usu.getNick());
                ven.ver_reporte("rpt_ventas_vendedor", parametros);
            }
            // REPORTE DE GANANCIAS POR VENDEDOR
            if (rpt.equals("ganancia_vendedor")) {
                Map<String, Object> parametros = new HashMap<>();
                parametros.put("fec_ini", fec_ini);
                parametros.put("fec_fin", fec_fin);
                parametros.put("nick",usu.getNick());
                ven.ver_reporte("rpt_ventas_productos_vendedor", parametros);
            }
            // REPORTE DE VENTAS POR CLIENTE
            if (rpt.equals("rpt_cliente")) {
                Map<String, Object> parametros = new HashMap<>();
                parametros.put("cliente",cli.getNro_doc());
                ven.ver_reporte("rpt_ventas_cliente", parametros);
            }
            if (rpt.equals("cajas_tienda")) {
                Map<String, Object> parametros = new HashMap<>();
                parametros.put("fec_mov", fec_ini);
                parametros.put("idalm", frm_menu.alm.getId());
                parametros.put("fec_fin", fec_fin);
                ven.ver_reporte("rpt_caja_movimiento_cajas", parametros);
            }
            this.dispose();
        }
    }//GEN-LAST:event_txt_fec_finKeyPressed

    private void txt_fec_iniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_fec_iniActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_fec_iniActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JFormattedTextField txt_fec_fin;
    private javax.swing.JFormattedTextField txt_fec_ini;
    // End of variables declaration//GEN-END:variables
}
