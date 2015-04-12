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
    public static Cl_Varios ven = new Cl_Varios();
    Cl_Almacen alm = new Cl_Almacen();
    Cl_Albaran alb = new Cl_Albaran();
    Cl_Cliente cli = new Cl_Cliente();
    Cl_Pedido ped = new Cl_Pedido();
    Cl_Tipo_Documentos tido = new Cl_Tipo_Documentos();
    Cl_Tipo_Pago tipa = new Cl_Tipo_Pago();
    Cl_Productos pro;
    Cl_Usuario usu = new Cl_Usuario();
    Cl_Proveedor prov = new Cl_Proveedor();
    public static Double total;
    public static String modo;
    public static String doc;
    String accion = "venta";
    DecimalFormatSymbols simbolo = new DecimalFormatSymbols();
    DecimalFormat formato = null;
    Double entrega;
    double comision;

    public frm_fin_venta() {
        initComponents();
        simbolo.setDecimalSeparator('.');
        formato = new DecimalFormat("####0.00", simbolo);
        String query = "select * from tipo_pago";
        ver_tipo(query);
        txt_fec_pago.setText(ven.fechaformateada(ven.getFechaActual()));

    }

    private void ver_tipo(String query) {
        try {
            Statement st = con.conexion();
            ResultSet rs = con.consulta(st, query);

            while (rs.next()) {
                String fila;
                fila = rs.getString("desc");
                cbx_tipopago.addItem(fila);
            }
            con.cerrar(st);
            con.cerrar(rs);
        } catch (SQLException e) {
            System.err.print(e);
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        lbl_tot = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cbx_tipopago = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        txt_entrega = new javax.swing.JTextField();
        jlabelvuelto = new javax.swing.JLabel();
        txt_vuelto = new javax.swing.JTextField();
        btn_reg = new javax.swing.JButton();
        btn_imp_guiar = new javax.swing.JButton();
        btn_cerrar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txt_doc = new javax.swing.JTextField();
        btn_add_nota = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        txt_fec_pago = new javax.swing.JFormattedTextField();
        chk_incluir = new javax.swing.JCheckBox();

        setBackground(new java.awt.Color(254, 254, 254));
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

        jLabel1.setForeground(new java.awt.Color(212, 2, 2));
        jLabel1.setText("Total");

        lbl_tot.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        lbl_tot.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_tot.setText("S/. 1,888.00");

        jLabel3.setForeground(new java.awt.Color(212, 2, 2));
        jLabel3.setText("Pago:");

        cbx_tipopago.setEnabled(false);
        cbx_tipopago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbx_tipopagoActionPerformed(evt);
            }
        });
        cbx_tipopago.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cbx_tipopagoKeyPressed(evt);
            }
        });

        jLabel4.setForeground(new java.awt.Color(212, 2, 2));
        jLabel4.setText("Entrega:");

        txt_entrega.setEditable(false);
        txt_entrega.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_entrega.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_entregaKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_entregaKeyTyped(evt);
            }
        });

        jlabelvuelto.setForeground(new java.awt.Color(212, 2, 2));
        jlabelvuelto.setText("Vuelto:");

        txt_vuelto.setEditable(false);
        txt_vuelto.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_vuelto.setFocusable(false);

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

        btn_imp_guiar.setText("Generar Guia de Remision");
        btn_imp_guiar.setEnabled(false);
        btn_imp_guiar.setFocusable(false);
        btn_imp_guiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_imp_guiarActionPerformed(evt);
            }
        });

        btn_cerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/cancel.png"))); // NOI18N
        btn_cerrar.setText("Cerrar");
        btn_cerrar.setFocusable(false);
        btn_cerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cerrarActionPerformed(evt);
            }
        });

        jLabel2.setForeground(new java.awt.Color(212, 2, 2));
        jLabel2.setText("Documento:");

        txt_doc.setEditable(false);
        txt_doc.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_doc.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txt_doc.setFocusable(false);

        btn_add_nota.setText("+Nota");
        btn_add_nota.setFocusable(false);
        btn_add_nota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_add_notaActionPerformed(evt);
            }
        });

        jLabel6.setForeground(new java.awt.Color(212, 2, 2));
        jLabel6.setText("Fecha Pago");

        txt_fec_pago.setEditable(false);
        try {
            txt_fec_pago.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txt_fec_pago.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_fec_pago.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N
        txt_fec_pago.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_fec_pagoKeyPressed(evt);
            }
        });

        chk_incluir.setText("Incluir 5%");
        chk_incluir.setEnabled(false);
        chk_incluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chk_incluirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbl_tot, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chk_incluir))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btn_add_nota))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(cbx_tipopago, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txt_entrega, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(12, 12, 12)
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txt_doc)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel6)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txt_fec_pago, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jlabelvuelto)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txt_vuelto, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(btn_imp_guiar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 195, Short.MAX_VALUE)
                        .addComponent(btn_reg)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_cerrar)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_add_nota, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_tot, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chk_incluir))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbx_tipopago, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(txt_doc, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(txt_fec_pago, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_entrega, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlabelvuelto, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_vuelto, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_reg, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_imp_guiar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_cerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_cerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cerrarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btn_cerrarActionPerformed

    private void cbx_tipopagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbx_tipopagoActionPerformed

    }//GEN-LAST:event_cbx_tipopagoActionPerformed

    private void txt_entregaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_entregaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            entrega = Double.parseDouble(txt_entrega.getText());
            Double vuelto = entrega - total;
            System.out.println(vuelto);
            //accion = tipo de venta
            if (frm_reg_venta.cbx_tip_venta.getSelectedItem().equals("SEPARACION")) {
                accion = "separacion";
                vuelto = total - entrega;
                jlabelvuelto.setText("Restante:");
                txt_vuelto.setText(formato.format(vuelto));
                btn_reg.setEnabled(true);
                btn_reg.requestFocus();
            } else {
                accion = "venta";
                if (vuelto >= -0.5) {
                    txt_vuelto.setText(formato.format(vuelto));
                    btn_reg.setEnabled(true);
                    btn_reg.requestFocus();
                    if (txt_doc.getText().equals("FACTURA")) {
                        btn_imp_guiar.setEnabled(true);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "La cantidad ingresada es menor al valor de la venta\n"
                            + "Ingrese de nuevo por favor.");
                    txt_entrega.setText("");
                    txt_entrega.requestFocus();
                    
                }
            }
            txt_entrega.setBackground(Color.white);
            txt_entrega.setForeground(Color.black);
            btn_reg.requestFocus();
        }

        if (evt.getKeyCode() == KeyEvent.VK_N) {
            btn_add_nota.doClick();
        }

        if (evt.getKeyCode() == KeyEvent.VK_R) {
            btn_reg.doClick();
        }

        if (evt.getKeyCode() == KeyEvent.VK_G) {
            btn_imp_guiar.doClick();
        }

        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            txt_entrega.setText("");
            txt_entrega.requestFocus();
        }
    }//GEN-LAST:event_txt_entregaKeyPressed

    private void llenar() {
        ped.setFec_pag_ped(ven.fechabase(txt_fec_pago.getText()));
        tipa.setId(cbx_tipopago.getSelectedIndex() + 1);
        tipa.setDesc(cbx_tipopago.getSelectedItem().toString());
        tido.setId(frm_reg_venta.cbx_tipd.getSelectedIndex() + 1);
        tido.setDesc(frm_reg_venta.cbx_tipd.getSelectedItem().toString());
        cli.setNro_doc(frm_reg_venta.txt_nro_doc.getText());
        tido.setSerie(tido.ver_ser(tido.getId(), frm_menu.alm.getId()));
        tido.setNro(tido.ver_num(tido.getId(), frm_menu.alm.getId()));
        ped.setTotal(total);
        //kardex
        //tipo venta

    }

    void mostrarinsert() {
        System.out.println("================DATOS INSERTADOS EN LA TABLA PEDIDO(VENTA)================");
        System.out.println("Fecha Venta: " + frm_reg_venta.ped.getFec_ped() + "\nFecha Pago: " + ped.getFec_pag_ped() + "\nTipo Pago: " + tipa.getId()
                + "\nDescuento: " + ped.getDes_ped() + "\nEstado: " + ped.getEst_ped() + "\nTipo Documento: " + tido.getId() + "\nSerie: " + tido.getSerie()
                + "\nNro.Documento: " + tido.getNro() + "\nNick: " + frm_menu.usu.getNick() + "\nAlmacen: " + frm_menu.alm.getId() + "\nAlbaran: " + "null"
                + "\nCliente: " + cli.getNro_doc() + "\nTotal: " + ped.getTotal());
        System.out.println("================FIN DE INSERTADOS EN LA TABLA PEDIDO(VENTA)================");
    }


    private void btn_regActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_regActionPerformed
        long time_start, time_end;
        time_start = System.currentTimeMillis();
        frm_reg_venta.llenar();//llenar de la clase reg_venta
        llenar();
        if (accion.equals("venta")) {
            int tpventa = (frm_reg_venta.cbx_tip_venta.getSelectedIndex() + 1);
            //crear pedido
            try {
                Statement st = con.conexion();
                String ins_ven = "insert into pedido Values (null, '" + frm_reg_venta.ped.getFec_ped() + "', '" + ped.getFec_pag_ped() + "', "
                        + "'" + tipa.getId() + "', '" + ped.getDes_ped() + "', '" + ped.getEst_ped() + "', '" + tido.getId() + "', "
                        + "'" + tido.getSerie() + "', '" + tido.getNro() + "', '" + frm_menu.lbl_user.getText() + "', "
                        + "'" + frm_menu.alm.getId() + "', null, '" + cli.getNro_doc() + "','" + total + "')";
                con.actualiza(st, ins_ven);
                con.cerrar(st);
            } catch (Exception ex) {
                System.out.println(ex.getLocalizedMessage());
            }
            mostrarinsert();
            tido.act_doc(tido.getSerie(), tido.getNro() + 1, frm_menu.alm.getId(), tido.getId());

//        //buscar ultimo pedido
            try {
                Statement st = con.conexion();
                String buscar_pedido = "select idPedido from pedido where nro_doc = '" + tido.getNro() + "' "
                        + "and fec_ped = '" + frm_reg_venta.ped.getFec_ped() + "' and idAlmacen = "
                        + "'" + frm_menu.alm.getId() + "' order by idPedido desc limit 1";
                ResultSet rs = con.consulta(st, buscar_pedido);
                if (rs.next()) {
                    ped.setId_ped(rs.getString("idPedido"));
                }
                con.cerrar(rs);
                con.cerrar(st);
            } catch (SQLException ex) {
                System.out.print(ex.getLocalizedMessage());
            }

            //registrar detalle de venta
            int filas = frm_reg_venta.t_detalle.getRowCount();
            for (int j = 0; j <= (filas - 1); j++) {
                String idPro = frm_reg_venta.t_detalle.getValueAt(j, 0).toString();
                Double cantidad = Double.parseDouble(frm_reg_venta.t_detalle.getValueAt(j, 3).toString());
                Double precio = Double.parseDouble(frm_reg_venta.t_detalle.getValueAt(j, 5).toString());
                try {
                    Statement st = con.conexion();
                    String ins_det_ped = "insert into detalle_pedido Values ('" + idPro + "', '" + ped.getId_ped() + "', '" + precio + "', '" + cantidad + "')";
                    con.actualiza(st, ins_det_ped);
                    con.cerrar(st);
                } catch (Exception ex) {
                    System.err.print(ex.getLocalizedMessage());
                }
//                //insertar datos en kardex
                try {
                    Statement st = con.conexion();
                    String ins_kardex = "insert into kardex Values (null, '" + frm_reg_venta.ped.getFec_ped() + "', '" + idPro + "', '0.00', '0.00', '"
                            + cantidad + "', '" + precio + "','" + tido.getSerie() + "', '" + tido.getNro() + "', '" + tido.getId() + "',"
                            + " '" + frm_menu.alm.getId() + "','" + cli.getNro_doc() + "', '" + frm_reg_venta.txt_nom.getText() + "','" + tpventa + "')";
                    con.actualiza(st, ins_kardex);
                    con.cerrar(st);
                } catch (Exception ex) {
                    System.err.print("Error en: " + ex.getLocalizedMessage());
                }
            }
//
            //seleccionar cantidad de producto y restar
            for (int j = 0; j <= (filas - 1); j++) {
                String idPro = frm_reg_venta.t_detalle.getValueAt(j, 0).toString();
                Double cantidad = Double.parseDouble(frm_reg_venta.t_detalle.getValueAt(j, 3).toString());
                Double precio = Double.parseDouble(frm_reg_venta.t_detalle.getValueAt(j, 5).toString());
                Double cant_actual = 0.00;
                Double cant_nueva = 0.00;

                try {
                    Statement st = con.conexion();
                    String bus_pro = "select cant_actual from productos where idProductos = '" + idPro + "'";
                    ResultSet rs = con.consulta(st, bus_pro);
                    if (rs.next()) {
                        cant_actual = rs.getDouble("cant_actual");
                    }
                    System.out.print("Seleccionando cantidad actual del producto: " + idPro + " cantidad: " + cant_actual + "\n");
                    con.cerrar(rs);
                    con.cerrar(st);
                    cant_nueva = cant_actual - cantidad;
                } catch (SQLException ex) {
                    System.err.print("Error en: " + ex.getLocalizedMessage());
                }
//
                try {
                    Statement st = con.conexion();
                    String act_pro = "update productos set cant_actual = '" + cant_nueva + "' where idProductos = '" + idPro + "' ";
                    con.actualiza(st, act_pro);
                    con.cerrar(st);
                    System.out.print("actualizando cantidad actual Prod:" + idPro + " cantidad: " + cant_nueva + "\n");
                } catch (Exception ex) {
                    System.err.print(ex.getLocalizedMessage());
                }
            }
//
            //verificar si producto existe en almacen
            try {
                for (int j = 0; j <= (filas - 1); j++) {
                    String idPro = frm_reg_venta.t_detalle.getValueAt(j, 0).toString();
                    Double cantidad = Double.parseDouble(frm_reg_venta.t_detalle.getValueAt(j, 3).toString());
                    Statement st = con.conexion();
                    String ver_prod_alm = "select idProductos, cant from producto_almacen where idAlmacen = '" + frm_menu.alm.getId() + "' and idProductos = '" + idPro + "'";
                    ResultSet rs = con.consulta(st, ver_prod_alm);
                    if (rs.next()) {
                        //si producto existe actualizar cantidad
                        Double cant = rs.getDouble("cant");
                        Double cant_act = cant - cantidad;
                        Statement st1 = con.conexion();
                        String act_pro_alm = "update producto_almacen set cant = '" + cant_act + "' where idProductos = '" + idPro + "' and idAlmacen = '" + frm_menu.alm.getId() + "'";
                        con.actualiza(st1, act_pro_alm);
                        con.cerrar(st1);
                    } else {
                        //si producto no existe agregar
                        double prec = Double.parseDouble(frm_reg_venta.t_detalle.getValueAt(j, 5).toString());
                        Statement st1 = con.conexion();
                        String add_pro_alm = "insert into producto_almacen Values ('" + idPro + "', '" + frm_menu.alm.getId() + "', '" + cantidad + "','" + prec + "')";
                        con.actualiza(st1, add_pro_alm);
                        con.cerrar(st1);
                    }
                }
            } catch (SQLException ex) {
                System.err.print(ex.getLocalizedMessage());
            }
//
            //registrar movimiento 
            if (cbx_tipopago.getSelectedIndex() == 0) {
                String glosa = "VENTA / " + tido.getDesc() + " / " + tido.getSerie() + " - " + tido.getNro() + " / " + cli.getNro_doc();
                try {
                    Statement st = con.conexion();
                    String add_mov = "insert into movimiento Values (null, '" + glosa + "', '" + frm_reg_venta.ped.getFec_ped() + "' , '" + total + "' "
                            + ", '0.00', '" + frm_menu.lbl_user.getText() + "','" + frm_menu.alm.getId() + "', 'C', '" + frm_menu.caja.getId() + "')";
                    con.actualiza(st, add_mov);
                    con.cerrar(st);
                } catch (Exception ex) {
                    System.err.print("Error en:" + ex.getLocalizedMessage());

                }
            } else {
                String glosa = "VENTA / " + tido.getDesc() + " / " + tido.getSerie() + " - " + tido.getNro() + " / " + cli.getNro_doc();
                try {
                    Statement st = con.conexion();
                    String add_mov = "insert into movimiento Values (null, '" + glosa + "', '" + frm_reg_venta.ped.getFec_ped() + "' , '" + total + "' "
                            + ", '0.00', '" + frm_menu.lbl_user.getText() + "','" + frm_menu.alm.getId() + "',  'B', '" + frm_menu.cue.getId_cuen() + "')";
                    con.actualiza(st, add_mov);
                    con.cerrar(st);
                } catch (Exception ex) {
                    System.err.print("Error en:" + ex.getLocalizedMessage());

                }
            }
            //enviar por theard

            if (modo.equals("VENTA")) {
                Cl_Hilo_Imprime imprime = new Cl_Hilo_Imprime();
                imprime.set_tipv(txt_doc.getText());
                imprime.set_idped(ped.getId_ped());
                System.out.println(imprime.get_idped() + " - " + imprime.get_tipv());
                imprime.start();
            }

            //SEPARACION
            //insertar separacion
        } else {
            ped.setEst_ped("2");

            try {
                Statement st = con.conexion();
                String ins_ven = "insert into pedido Values (null, '" + frm_reg_venta.ped.getFec_ped() + "', '" + ped.getFec_pag_ped() + "', "
                        + "'" + tipa.getId() + "', '" + ped.getDes_ped() + "', '" + ped.getEst_ped() + "', '" + tido.getId() + "', "
                        + "'" + tido.getSerie() + "', '" + tido.getNro() + "', '" + frm_menu.lbl_user.getText() + "', "
                        + "'" + frm_menu.alm.getId() + "', null, '" + cli.getNro_doc() + "','" + total + "')";
                con.actualiza(st, ins_ven);
                con.cerrar(st);
            } catch (Exception ex) {
                System.out.println("Error en pedido: " + ex);
            }

            //buscar ultimo pedido
            try {
                Statement st = con.conexion();
                String buscar_pedido = "select idPedido from pedido where nro_doc = '" + tido.getNro() + "' "
                        + "and fec_ped = '" + frm_reg_venta.ped.getFec_ped() + "' and idAlmacen = "
                        + "'" + frm_menu.alm.getId() + "' order by idPedido desc limit 1";
                ResultSet rs = con.consulta(st, buscar_pedido);
                if (rs.next()) {
                    ped.setId_ped(rs.getString("idPedido"));
                }
                con.cerrar(rs);
                con.cerrar(st);
            } catch (SQLException ex) {
                System.out.print("Error lectura de pedido: " + ex);
            }

            //insertar en detalle pedido
            int filas = frm_reg_venta.t_detalle.getRowCount();
            for (int j = 0; j <= (filas - 1); j++) {
                String idPro = frm_reg_venta.t_detalle.getValueAt(j, 0).toString();
                Double cantidad = Double.parseDouble(frm_reg_venta.t_detalle.getValueAt(j, 3).toString());
                Double precio = Double.parseDouble(frm_reg_venta.t_detalle.getValueAt(j, 5).toString());
                try {
                    Statement st = con.conexion();
                    String ins_det_ped = "insert into detalle_pedido Values ('" + idPro + "', '" + ped.getId_ped() + "', '" + precio + "', '" + cantidad + "')";
                    con.actualiza(st, ins_det_ped);
                    con.cerrar(st);
                } catch (Exception ex) {
                    System.err.print("Error detalle pedido: " + ex);
                }
            }

            //insertar datos en letras_pedido
            try {
                Statement st = con.conexion();
                String sql = "insert into letras_pedido values(null, '" + Double.parseDouble(txt_entrega.getText()) + "', '" + frm_reg_venta.ped.getFec_ped() + "', '" + ped.getId_ped() + "' )";
                con.actualiza(st, sql);
                con.cerrar(st);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error en letras_pedido: " + e);
            }

            //imprimir resultados ingresados
            mostrarinsert();

            //insertar datos en movimiento
            try {
                tido.act_doc(tido.getSerie(), tido.getNro() + 1, frm_menu.alm.getId(), tido.getId());
            } catch (Exception e) {
                System.out.println(e);
            }
            total = entrega;
            String glosa = "SEPARACION / " + tido.getDesc() + " / " + tido.getSerie() + " - " + tido.getNro() + " / " + cli.getNro_doc();
            try {
                Statement st = con.conexion();
                String add_mov = "insert into movimiento Values (null, '" + glosa + "', '" + frm_reg_venta.ped.getFec_ped() + "' , '" + total + "' "
                        + ", '0.00', '" + frm_menu.lbl_user.getText() + "','" + frm_menu.alm.getId() + "', 'C', '" + frm_menu.caja.getId() + "')";
                con.actualiza(st, add_mov);
                con.cerrar(st);
            } catch (Exception ex) {
                System.err.print("Error en:" + ex.getLocalizedMessage());

            }

            // TICKET DE SEPARACION
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("idped", ped.getId_ped());
            parametros.put("Adelanto", total);
            String filename = "rpt_ticket_separacion";
            ven.imp_reporte(filename, parametros);
        }

        //cerrr y volver abrir
        this.dispose();
        frm_reg_venta venta = null;
        venta.btn_clo.doClick();
        venta = new frm_reg_venta();
        ven.llamar_ventana(venta);
    }//GEN-LAST:event_btn_regActionPerformed

    private void btn_add_notaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_add_notaActionPerformed
//        frm_reg_nota nota = new frm_reg_nota();
//        nota.txt_nota.setText(frm_reg_venta.txt_pla.getText() + " - ");
//        nota.txt_nota.requestFocus();
//        ven.llamar_ventana(nota);

    }//GEN-LAST:event_btn_add_notaActionPerformed

    private void txt_entregaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_entregaKeyTyped
        char car = evt.getKeyChar();
        if ((car < '0' || car > '9') && car != '.') {
            evt.consume();
        }
    }//GEN-LAST:event_txt_entregaKeyTyped

    private void btn_imp_guiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_imp_guiarActionPerformed

    }//GEN-LAST:event_btn_imp_guiarActionPerformed

    private void cbx_tipopagoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbx_tipopagoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (cbx_tipopago.getSelectedItem().equals("TARJETA")) {
                chk_incluir.setEnabled(true);
                total = total;
                comision = total * 0.05;
                btn_reg.setEnabled(true);
                btn_reg.requestFocus();
                txt_fec_pago.setText(ven.fechaformateada(ven.getFechaActual()));
            } else {
                txt_fec_pago.setEditable(true);
                txt_fec_pago.setText(ven.fechaformateada(ven.getFechaActual()));
                txt_fec_pago.requestFocus();
                txt_fec_pago.setBackground(Color.red);
                txt_fec_pago.setForeground(Color.white);
            }
        }
    }//GEN-LAST:event_cbx_tipopagoKeyPressed

    private void formInternalFrameActivated(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameActivated
        if (frm_reg_venta.tipo_venta.equals("VENTA")) {
            cbx_tipopago.setEnabled(true);
            txt_fec_pago.setText(ven.fechaformateada(ven.getFechaActual()));
            cbx_tipopago.requestFocus();
        } else if (frm_reg_venta.tipo_venta.equals("SEPARACION")) {
            cbx_tipopago.setEnabled(false);
            txt_fec_pago.setText(ven.fechaformateada(ven.getFechaActual()));
            txt_fec_pago.setForeground(Color.RED);
            txt_entrega.setEditable(true);
            txt_entrega.requestFocus();
        }
    }//GEN-LAST:event_formInternalFrameActivated

    private void txt_fec_pagoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_fec_pagoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (txt_fec_pago.getText().length() == 10) {
                txt_entrega.setEditable(true);
                txt_entrega.requestFocus();
                txt_fec_pago.setBackground(Color.white);
                txt_fec_pago.setForeground(Color.black);
                txt_entrega.setBackground(Color.red);
                txt_entrega.setForeground(Color.white);
            }
        }
    }//GEN-LAST:event_txt_fec_pagoKeyPressed

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        if (frm_reg_venta.tipo_venta.equals("VENTA")) {
            cbx_tipopago.setEnabled(true);
            cbx_tipopago.requestFocus();
        } else if (frm_reg_venta.tipo_venta.equals("SEPARACION")) {
            cbx_tipopago.setEnabled(false);
            txt_fec_pago.setForeground(Color.RED);
            txt_entrega.setEditable(true);
            txt_entrega.requestFocus();
        }
    }//GEN-LAST:event_formInternalFrameOpened

    private void btn_regKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btn_regKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btn_reg.doClick();
        }
    }//GEN-LAST:event_btn_regKeyPressed

    private void chk_incluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chk_incluirActionPerformed
        if (chk_incluir.isSelected()) {
            total += comision;
            lbl_tot.setText("S/. " + formato.format(total));
            System.out.println("el total " + total);
            System.out.println("La comision " + comision);
        } else {
            total -= comision;
            lbl_tot.setText("S/. " + formato.format(total));
            System.out.println("El total " + total);
            System.out.println("La comision " + comision);
        }
    }//GEN-LAST:event_chk_incluirActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_add_nota;
    private javax.swing.JButton btn_cerrar;
    private javax.swing.JButton btn_imp_guiar;
    private javax.swing.JButton btn_reg;
    public static javax.swing.JComboBox cbx_tipopago;
    private javax.swing.JCheckBox chk_incluir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jlabelvuelto;
    public static javax.swing.JLabel lbl_tot;
    public static javax.swing.JTextField txt_doc;
    private javax.swing.JTextField txt_entrega;
    public static javax.swing.JFormattedTextField txt_fec_pago;
    private javax.swing.JTextField txt_vuelto;
    // End of variables declaration//GEN-END:variables
}
