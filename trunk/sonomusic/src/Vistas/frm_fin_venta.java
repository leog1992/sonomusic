/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import Clases.Cl_Albaran;
import Clases.Cl_Almacen;
import Clases.Cl_Cliente;
import Clases.Cl_Conectar;
import Clases.Cl_Hilo_Imprime;
import Clases.Cl_Pedido;
import Clases.Cl_Productos;
import Clases.Cl_Proveedor;
import Clases.Cl_Tipo_Documentos;
import Clases.Cl_Tipo_Pago;
import Clases.Cl_Usuario;
import Clases.Cl_Varios;
import Forms.frm_reg_nota;
import Forms.frm_reg_venta;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import javax.swing.JOptionPane;
import sonomusic.frm_menu;

/**
 *
 * @author pc
 */
public class frm_fin_venta extends javax.swing.JInternalFrame {

    Cl_Conectar con = new Cl_Conectar();
    Cl_Varios ven = new Cl_Varios();
    Cl_Cliente cli = new Cl_Cliente();
    public static Cl_Pedido ped = new Cl_Pedido();
    Cl_Tipo_Documentos tido = new Cl_Tipo_Documentos();
    Cl_Tipo_Pago tipa = new Cl_Tipo_Pago();
    Cl_Productos pro;
    Cl_Usuario usu = new Cl_Usuario();
    Cl_Proveedor prov = new Cl_Proveedor();
    String accion = "venta";
    DecimalFormatSymbols simbolo = new DecimalFormatSymbols();
    DecimalFormat formato = null;
    double comision;
    double m_efect;
    double m_tarj;
    double m_tarj_com;

    public frm_fin_venta() {
        initComponents();
        simbolo.setDecimalSeparator('.');
        formato = new DecimalFormat("####0.00", simbolo);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        lbl_tot = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btn_add_nota = new javax.swing.JButton();
        lbl_doc = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        txt_subt = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        chk_incluir = new javax.swing.JCheckBox();
        txt_efec = new javax.swing.JTextField();
        txt_tarj = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        cbx_plazo = new javax.swing.JComboBox();
        btn_cerrar = new javax.swing.JButton();
        btn_reg = new javax.swing.JButton();
        txt_com = new javax.swing.JTextField();
        btn_cont = new javax.swing.JRadioButton();
        btn_cred = new javax.swing.JRadioButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setClosable(true);
        setTitle("Finalizar Venta");
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameActivated(evt);
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameOpened(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(212, 2, 2));
        jLabel1.setText("Total");

        lbl_tot.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        lbl_tot.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_tot.setText("S/. 1,888.00");

        jLabel2.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(212, 2, 2));
        jLabel2.setText("Documento:");

        btn_add_nota.setText("+Nota");
        btn_add_nota.setFocusable(false);
        btn_add_nota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_add_notaActionPerformed(evt);
            }
        });

        lbl_doc.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        lbl_doc.setText("Boleta / 0001 - 0000354");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        txt_subt.setEditable(false);
        txt_subt.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jLabel4.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(204, 0, 0));
        jLabel4.setText("Entregado:");

        jLabel7.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(204, 0, 0));
        jLabel7.setText("Tarjeta:");

        jLabel5.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(204, 0, 0));
        jLabel5.setText("Efectivo:");

        chk_incluir.setBackground(new java.awt.Color(255, 255, 255));
        chk_incluir.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        chk_incluir.setText("Incluir 5%");
        chk_incluir.setEnabled(false);
        chk_incluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chk_incluirActionPerformed(evt);
            }
        });

        txt_efec.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_efec.setEnabled(false);
        txt_efec.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_efecKeyPressed(evt);
            }
        });

        txt_tarj.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_tarj.setEnabled(false);
        txt_tarj.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_tarjKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel7)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_subt, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_tarj, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_efec))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(chk_incluir)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_efec, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_tarj, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chk_incluir))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_subt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel8.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(204, 0, 0));
        jLabel8.setText("Plazo (dias)");

        cbx_plazo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "a 15 dias", "a 30 dias", "a 45 dias" }));
        cbx_plazo.setEnabled(false);

        btn_cerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/cancel.png"))); // NOI18N
        btn_cerrar.setText("Cerrar");
        btn_cerrar.setFocusable(false);
        btn_cerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cerrarActionPerformed(evt);
            }
        });

        btn_reg.setText("Registrar");
        btn_reg.setEnabled(false);
        btn_reg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_regActionPerformed(evt);
            }
        });
        btn_reg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btn_regKeyPressed(evt);
            }
        });

        txt_com.setEditable(false);
        txt_com.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_com.setText("Comision 5%");
        txt_com.setFocusable(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbx_plazo, 0, 101, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txt_com, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btn_reg)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_cerrar)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbx_plazo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_com, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_reg, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_cerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        btn_cont.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(btn_cont);
        btn_cont.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        btn_cont.setForeground(new java.awt.Color(204, 0, 0));
        btn_cont.setText("Contado");
        btn_cont.setFocusable(false);
        btn_cont.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_contActionPerformed(evt);
            }
        });

        btn_cred.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(btn_cred);
        btn_cred.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        btn_cred.setForeground(new java.awt.Color(204, 0, 0));
        btn_cred.setText("Credito");
        btn_cred.setFocusable(false);
        btn_cred.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_credActionPerformed(evt);
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
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbl_doc)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_add_nota))
                    .addComponent(lbl_tot, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_cont))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btn_cred)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(13, 13, 13))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_add_nota, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_doc, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbl_tot, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_cred, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_cont, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_cerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cerrarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btn_cerrarActionPerformed

    private void llenar() {
        tido.setId(frm_reg_venta.cbx_tipd.getSelectedIndex() + 1);
        tido.setDesc(frm_reg_venta.cbx_tipd.getSelectedItem().toString());
        tido.setSerie(tido.ver_ser(tido.getId(), frm_menu.alm.getId()));
        tido.setNro(tido.ver_num(tido.getId(), frm_menu.alm.getId()));
    }


    private void btn_regActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_regActionPerformed
//        long time_start, time_end;
//        time_start = System.currentTimeMillis();
//        frm_reg_venta.llenar();//llenar de la clase reg_venta
//        llenar();
//        if (accion.equals("venta")) {
//            int tpventa = (frm_reg_venta.cbx_tip_venta.getSelectedIndex() + 1);
//            //crear pedido
//            try {
//                Statement st = con.conexion();
//                String ins_ven = "insert into pedido Values (null, '" + frm_reg_venta.ped.getFec_ped() + "', '" + ped.getFec_pag_ped() + "', "
//                        + "'" + tipa.getId() + "', '" + ped.getDes_ped() + "', '" + ped.getEst_ped() + "', '" + tido.getId() + "', "
//                        + "'" + tido.getSerie() + "', '" + tido.getNro() + "', '" + usu.getNick() + "', "
//                        + "'" + frm_menu.alm.getId() + "', null, '" + cli.getNro_doc() + "', '"+cli.getNom_cli()+"','" + total + "')";
//                con.actualiza(st, ins_ven);
//                con.cerrar(st);
//            } catch (Exception ex) {
//                System.out.println(ex.getLocalizedMessage());
//            }
//            mostrarinsert();
//            tido.act_doc(tido.getSerie(), tido.getNro() + 1, frm_menu.alm.getId(), tido.getId());
//
////        //buscar ultimo pedido
//            try {
//                Statement st = con.conexion();
//                String buscar_pedido = "select idPedido from pedido where nro_doc = '" + tido.getNro() + "' "
//                        + "and fec_ped = '" + frm_reg_venta.ped.getFec_ped() + "' and idAlmacen = "
//                        + "'" + frm_menu.alm.getId() + "' order by idPedido desc limit 1";
//                ResultSet rs = con.consulta(st, buscar_pedido);
//                if (rs.next()) {
//                    ped.setId_ped(rs.getString("idPedido"));
//                }
//                con.cerrar(rs);
//                con.cerrar(st);
//            } catch (SQLException ex) {
//                System.out.print(ex.getLocalizedMessage());
//            }
//
//            //registrar detalle de venta
//            int filas = frm_reg_venta.t_detalle.getRowCount();
//            for (int j = 0; j <= (filas - 1); j++) {
//                String idPro = frm_reg_venta.t_detalle.getValueAt(j, 0).toString();
//                Double cantidad = Double.parseDouble(frm_reg_venta.t_detalle.getValueAt(j, 3).toString());
//                Double precio = Double.parseDouble(frm_reg_venta.t_detalle.getValueAt(j, 5).toString());
//                try {
//                    Statement st = con.conexion();
//                    String ins_det_ped = "insert into detalle_pedido Values ('" + idPro + "', '" + ped.getId_ped() + "', '" + precio + "', '" + cantidad + "')";
//                    con.actualiza(st, ins_det_ped);
//                    con.cerrar(st);
//                } catch (Exception ex) {
//                    System.err.print(ex.getLocalizedMessage());
//                }
////                //insertar datos en kardex
//                try {
//                    Statement st = con.conexion();
//                    String ins_kardex = "insert into kardex Values (null, '" + frm_reg_venta.ped.getFec_ped() + "', '" + idPro + "', '0.00', '0.00', '"
//                            + cantidad + "', '" + precio + "','" + tido.getSerie() + "', '" + tido.getNro() + "', '" + tido.getId() + "',"
//                            + " '" + frm_menu.alm.getId() + "','" + cli.getNro_doc() + "', '" + cli.getNom_cli() + "','" + tpventa + "')";
//                    con.actualiza(st, ins_kardex);
//                    con.cerrar(st);
//                } catch (Exception ex) {
//                    System.err.print("Error en: " + ex.getLocalizedMessage());
//                }
//            }
////
//            //seleccionar cantidad de producto y restar
//            for (int j = 0; j <= (filas - 1); j++) {
//                String idPro = frm_reg_venta.t_detalle.getValueAt(j, 0).toString();
//                Double cantidad = Double.parseDouble(frm_reg_venta.t_detalle.getValueAt(j, 3).toString());
//                Double precio = Double.parseDouble(frm_reg_venta.t_detalle.getValueAt(j, 5).toString());
//                Double cant_actual = 0.00;
//                Double cant_nueva = 0.00;
//
//                try {
//                    Statement st = con.conexion();
//                    String bus_pro = "select cant_actual from productos where idProductos = '" + idPro + "'";
//                    ResultSet rs = con.consulta(st, bus_pro);
//                    if (rs.next()) {
//                        cant_actual = rs.getDouble("cant_actual");
//                    }
//                    System.out.print("Seleccionando cantidad actual del producto: " + idPro + " cantidad: " + cant_actual + "\n");
//                    con.cerrar(rs);
//                    con.cerrar(st);
//                    cant_nueva = cant_actual - cantidad;
//                } catch (SQLException ex) {
//                    System.err.print("Error en: " + ex.getLocalizedMessage());
//                }
////
//                try {
//                    Statement st = con.conexion();
//                    String act_pro = "update productos set cant_actual = '" + cant_nueva + "' where idProductos = '" + idPro + "' ";
//                    con.actualiza(st, act_pro);
//                    con.cerrar(st);
//                    System.out.print("actualizando cantidad actual Prod:" + idPro + " cantidad: " + cant_nueva + "\n");
//                } catch (Exception ex) {
//                    System.err.print(ex.getLocalizedMessage());
//                }
//            }
////
//            //verificar si producto existe en almacen
//            try {
//                for (int j = 0; j <= (filas - 1); j++) {
//                    String idPro = frm_reg_venta.t_detalle.getValueAt(j, 0).toString();
//                    Double cantidad = Double.parseDouble(frm_reg_venta.t_detalle.getValueAt(j, 3).toString());
//                    Statement st = con.conexion();
//                    String ver_prod_alm = "select idProductos, cant from producto_almacen where idAlmacen = '" + frm_menu.alm.getId() + "' and idProductos = '" + idPro + "'";
//                    ResultSet rs = con.consulta(st, ver_prod_alm);
//                    if (rs.next()) {
//                        //si producto existe actualizar cantidad
//                        Double cant = rs.getDouble("cant");
//                        Double cant_act = cant - cantidad;
//                        Statement st1 = con.conexion();
//                        String act_pro_alm = "update producto_almacen set cant = '" + cant_act + "' where idProductos = '" + idPro + "' and idAlmacen = '" + frm_menu.alm.getId() + "'";
//                        con.actualiza(st1, act_pro_alm);
//                        con.cerrar(st1);
//                    } else {
//                        //si producto no existe agregar
//                        double prec = Double.parseDouble(frm_reg_venta.t_detalle.getValueAt(j, 5).toString());
//                        Statement st1 = con.conexion();
//                        String add_pro_alm = "insert into producto_almacen Values ('" + idPro + "', '" + frm_menu.alm.getId() + "', '" + cantidad + "','" + prec + "')";
//                        con.actualiza(st1, add_pro_alm);
//                        con.cerrar(st1);
//                    }
//                }
//            } catch (SQLException ex) {
//                System.err.print(ex.getLocalizedMessage());
//            }
////
//            //registrar movimiento 
//            if (cbx_tipopago.getSelectedIndex() == 0) {
//                String glosa = "VENTA / " + tido.getDesc() + " / " + tido.getSerie() + " - " + tido.getNro() + " / " + cli.getNro_doc();
//                try {
//                    Statement st = con.conexion();
//                    String add_mov = "insert into movimiento Values (null, '" + glosa + "', '" + frm_reg_venta.ped.getFec_ped() + "' , '" + total + "' "
//                            + ", '0.00', '" + usu.getNick() + "','" + frm_menu.alm.getId() + "', 'C', '" + frm_menu.caja.getId() + "')";
//                    con.actualiza(st, add_mov);
//                    con.cerrar(st);
//                } catch (Exception ex) {
//                    System.err.print("Error en:" + ex.getLocalizedMessage());
//
//                }
//            } else {
//                String glosa = "VENTA / " + tido.getDesc() + " / " + tido.getSerie() + " - " + tido.getNro() + " / " + cli.getNro_doc();
//                try {
//                    Statement st = con.conexion();
//                    String add_mov = "insert into movimiento Values (null, '" + glosa + "', '" + frm_reg_venta.ped.getFec_ped() + "' , '" + total + "' "
//                            + ", '0.00', '" + usu.getNick() + "','" + frm_menu.alm.getId() + "',  'B', '" + frm_menu.cue.getId_cuen() + "')";
//                    con.actualiza(st, add_mov);
//                    con.cerrar(st);
//                } catch (Exception ex) {
//                    System.err.print("Error en:" + ex.getLocalizedMessage());
//
//                }
//            }
//            //enviar por theard
//
//            if (modo.equals("VENTA")) {
//                Cl_Hilo_Imprime imprime = new Cl_Hilo_Imprime();
//                imprime.set_tipv(txt_doc.getText());
//                imprime.set_idped(ped.getId_ped());
//                System.out.println(imprime.get_idped() + " - " + imprime.get_tipv());
//                imprime.start();
//            }
//
//            //SEPARACION
//            //insertar separacion
//        } else {
//            ped.setEst_ped("2");
//
//            try {
//                Statement st = con.conexion();
//                String ins_ven = "insert into pedido Values (null, '" + frm_reg_venta.ped.getFec_ped() + "', '" + ped.getFec_pag_ped() + "', "
//                        + "'" + tipa.getId() + "', '" + ped.getDes_ped() + "', '" + ped.getEst_ped() + "', '" + tido.getId() + "', "
//                        + "'" + tido.getSerie() + "', '" + tido.getNro() + "', '" + usu.getNick() + "', "
//                        + "'" + frm_menu.alm.getId() + "', null, '" + cli.getNro_doc() + "', '"+cli.getNom_cli()+"', '" + total + "')";
//                con.actualiza(st, ins_ven);
//                con.cerrar(st);
//            } catch (Exception ex) {
//                System.out.println("Error en pedido: " + ex);
//            }
//
//            //buscar ultimo pedido
//            try {
//                Statement st = con.conexion();
//                String buscar_pedido = "select idPedido from pedido where nro_doc = '" + tido.getNro() + "' "
//                        + "and fec_ped = '" + frm_reg_venta.ped.getFec_ped() + "' and idAlmacen = "
//                        + "'" + frm_menu.alm.getId() + "' order by idPedido desc limit 1";
//                ResultSet rs = con.consulta(st, buscar_pedido);
//                if (rs.next()) {
//                    ped.setId_ped(rs.getString("idPedido"));
//                }
//                con.cerrar(rs);
//                con.cerrar(st);
//            } catch (SQLException ex) {
//                System.out.print("Error lectura de pedido: " + ex);
//            }
//
//            //insertar en detalle pedido
//            int filas = frm_reg_venta.t_detalle.getRowCount();
//            for (int j = 0; j <= (filas - 1); j++) {
//                String idPro = frm_reg_venta.t_detalle.getValueAt(j, 0).toString();
//                Double cantidad = Double.parseDouble(frm_reg_venta.t_detalle.getValueAt(j, 3).toString());
//                Double precio = Double.parseDouble(frm_reg_venta.t_detalle.getValueAt(j, 5).toString());
//                try {
//                    Statement st = con.conexion();
//                    String ins_det_ped = "insert into detalle_pedido Values ('" + idPro + "', '" + ped.getId_ped() + "', '" + precio + "', '" + cantidad + "')";
//                    con.actualiza(st, ins_det_ped);
//                    con.cerrar(st);
//                } catch (Exception ex) {
//                    System.err.print("Error detalle pedido: " + ex);
//                }
//            }
//
//            //insertar datos en letras_pedido
//            try {
//                Statement st = con.conexion();
//                String sql = "insert into letras_pedido values(null, '" + Double.parseDouble(txt_entrega.getText()) + "', '" + frm_reg_venta.ped.getFec_ped() + "', '" + ped.getId_ped() + "' )";
//                con.actualiza(st, sql);
//                con.cerrar(st);
//            } catch (Exception e) {
//                JOptionPane.showMessageDialog(null, "Error en letras_pedido: " + e);
//            }
//
//            //imprimir resultados ingresados
//            mostrarinsert();
//
//            //insertar datos en movimiento
//            try {
//                tido.act_doc(tido.getSerie(), tido.getNro() + 1, frm_menu.alm.getId(), tido.getId());
//            } catch (Exception e) {
//                System.out.println(e);
//            }
//            total = entrega;
//            String glosa = "SEPARACION / " + tido.getDesc() + " / " + tido.getSerie() + " - " + tido.getNro() + " / " + cli.getNro_doc();
//            try {
//                Statement st = con.conexion();
//                String add_mov = "insert into movimiento Values (null, '" + glosa + "', '" + frm_reg_venta.ped.getFec_ped() + "' , '" + total + "' "
//                        + ", '0.00', '" + usu.getNick() + "','" + frm_menu.alm.getId() + "', 'C', '" + frm_menu.caja.getId() + "')";
//                con.actualiza(st, add_mov);
//                con.cerrar(st);
//            } catch (Exception ex) {
//                System.err.print("Error en:" + ex.getLocalizedMessage());
//
//            }
//
//            // TICKET DE SEPARACION
//            Map<String, Object> parametros = new HashMap<>();
//            parametros.put("idped", ped.getId_ped());
//            parametros.put("Adelanto", total);
//            parametros.put("Acumulado", total);
//            String filename = "rpt_ticket_separacion";
//            ven.imp_reporte(filename, parametros);
//        }
//
//        //cerrr y volver abrir
//        this.dispose();
//        frm_reg_venta venta = null;
//        venta.btn_clo.doClick();
//        venta = new frm_reg_venta();
//        ven.llamar_ventana(venta);
    }//GEN-LAST:event_btn_regActionPerformed

    private void btn_add_notaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_add_notaActionPerformed
//        frm_reg_nota nota = new frm_reg_nota();
//        nota.txt_nota.setText(frm_reg_venta.txt_pla.getText() + " - ");
//        nota.txt_nota.requestFocus();
//        ven.llamar_ventana(nota);

    }//GEN-LAST:event_btn_add_notaActionPerformed

    private void formInternalFrameActivated(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameActivated
//        if (frm_reg_venta.tipo_venta.equals("VENTA")) {
//            cbx_tipopago.setEnabled(true);
//            txt_fec_pago.setText(ven.fechaformateada(ven.getFechaActual()));
//            cbx_tipopago.requestFocus();
//        } else if (frm_reg_venta.tipo_venta.equals("SEPARACION")) {
//            cbx_tipopago.setEnabled(false);
//            txt_fec_pago.setText(ven.fechaformateada(ven.getFechaActual()));
//            txt_fec_pago.setForeground(Color.RED);
//            txt_entrega.setEditable(true);
//            txt_entrega.requestFocus();
//        }
    }//GEN-LAST:event_formInternalFrameActivated

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened

    }//GEN-LAST:event_formInternalFrameOpened

    private void btn_regKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btn_regKeyPressed
//        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
//            btn_reg.doClick();
//        }
    }//GEN-LAST:event_btn_regKeyPressed

    private void chk_incluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chk_incluirActionPerformed
        m_tarj = Double.parseDouble(txt_tarj.getText());
        comision = m_tarj * 0.05;
        if (chk_incluir.isSelected()) {
            txt_com.setText(formato.format(comision));
            lbl_tot.setText("S/. " + formato.format(ped.getTotal() + comision));
        } else {
            txt_com.setText("0.00");
            lbl_tot.setText("S/. " + formato.format(ped.getTotal()));
        }
        txt_tarj.requestFocus();
    }//GEN-LAST:event_chk_incluirActionPerformed

    private void txt_efecKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_efecKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txt_tarj.setEnabled(true);
            txt_tarj.requestFocus();
            chk_incluir.setEnabled(true);
            m_tarj_com = 0;
        }
    }//GEN-LAST:event_txt_efecKeyPressed

    private void txt_tarjKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_tarjKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            //verificar suma y activar aceptar
            
            m_efect = Double.parseDouble(txt_efec.getText());
            m_tarj = Double.parseDouble(txt_tarj.getText());
            double suma_tot = m_efect + m_tarj;
            if (ped.getTotal() - suma_tot <= 0) {
                btn_reg.setEnabled(true);
                btn_reg.requestFocus();
            }
        }
    }//GEN-LAST:event_txt_tarjKeyPressed

    private void btn_contActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_contActionPerformed
        cbx_plazo.setEnabled(false);
        txt_efec.setEnabled(true);
        txt_efec.setText("0");
        txt_efec.requestFocus();
        txt_tarj.setEnabled(true);
        txt_tarj.setText("0");
        txt_subt.setText("0");
    }//GEN-LAST:event_btn_contActionPerformed

    private void btn_credActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_credActionPerformed
        cbx_plazo.setEnabled(true);
        txt_efec.setEnabled(false);
        txt_efec.setText("0");
        txt_efec.requestFocus();
        txt_tarj.setEnabled(false);
        txt_tarj.setText("0");
        txt_subt.setText("0");
    }//GEN-LAST:event_btn_credActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_add_nota;
    private javax.swing.JButton btn_cerrar;
    public static javax.swing.JRadioButton btn_cont;
    public static javax.swing.JRadioButton btn_cred;
    private javax.swing.JButton btn_reg;
    private javax.swing.ButtonGroup buttonGroup1;
    public static javax.swing.JComboBox cbx_plazo;
    public static javax.swing.JCheckBox chk_incluir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    public static javax.swing.JLabel lbl_doc;
    public static javax.swing.JLabel lbl_tot;
    private javax.swing.JTextField txt_com;
    public static javax.swing.JTextField txt_efec;
    public static javax.swing.JTextField txt_subt;
    public static javax.swing.JTextField txt_tarj;
    // End of variables declaration//GEN-END:variables
}
