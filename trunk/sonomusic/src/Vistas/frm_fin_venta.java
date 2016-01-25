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
    Cl_Productos pro = new Cl_Productos();
    Cl_Usuario usu = new Cl_Usuario();
    Cl_Proveedor prov = new Cl_Proveedor();
    public static String accion = "venta";
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
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_efecKeyTyped(evt);
            }
        });

        txt_tarj.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_tarj.setEnabled(false);
        txt_tarj.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_tarjKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_tarjKeyTyped(evt);
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
        cbx_plazo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cbx_plazoKeyPressed(evt);
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
                        .addComponent(cbx_plazo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txt_com, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btn_reg)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
        ped.setFec_ped(ven.fechabase(frm_reg_venta.txt_fec.getText()));
        ped.setFec_pag_ped(ped.getFec_ped());
        if (btn_cont.isSelected()) {
            tipa.setId(1);
        } else {
            tipa.setId(2);
        }
        ped.setDes_ped(0);
        if (frm_reg_venta.cbx_tip_venta.getSelectedItem().equals("VENTA") && btn_cont.isSelected()) {
            ped.setEst_ped("1");
        }
        if (frm_reg_venta.cbx_tip_venta.getSelectedItem().equals("SEPARACION")) {
            ped.setEst_ped("2");
        }
        if (frm_reg_venta.cbx_tip_venta.getSelectedItem().equals("VENTA") && btn_cred.isSelected()) {
            ped.setEst_ped("6");
        }
        usu.setNick(frm_reg_venta.cbx_vendedor.getSelectedItem().toString());
        cli.setNro_doc(frm_reg_venta.txt_nro_doc.getText());
        cli.setNom_cli(frm_reg_venta.txt_nom.getText());
        ped.setTotal(ped.getTotal() + comision);
    }


    private void btn_regActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_regActionPerformed
        llenar();
        Cl_Hilo_Imprime imprime = new Cl_Hilo_Imprime();
        if (accion.equals("venta")) {
            //crear pedido
            try {
                Statement st = con.conexion();
                String ins_ven = "insert into pedido Values (null, '" + ped.getFec_ped() + "', '" + ped.getFec_pag_ped() + "', "
                        + "'" + tipa.getId() + "', '" + ped.getDes_ped() + "', '" + ped.getEst_ped() + "', '" + tido.getId() + "', "
                        + "'" + tido.getSerie() + "', '" + tido.getNro() + "', '" + usu.getNick() + "', "
                        + "'" + frm_menu.alm.getId() + "', null, '" + cli.getNro_doc() + "', '" + cli.getNom_cli() + "','" + ped.getTotal() + "')";
                System.out.println(ins_ven);
                con.actualiza(st, ins_ven);
                con.cerrar(st);
            } catch (Exception ex) {
                System.out.println(ex.getLocalizedMessage());
            }
            tido.act_doc(tido.getSerie(), tido.getNro() + 1, frm_menu.alm.getId(), tido.getId());

//        //buscar ultimo pedido
            try {
                Statement st = con.conexion();
                String buscar_pedido = "select idPedido from pedido where nro_doc = '" + tido.getNro() + "' "
                        + "and fec_ped = '" + ped.getFec_ped() + "' and idAlmacen = "
                        + "'" + frm_menu.alm.getId() + "' order by idPedido desc limit 1";
                System.out.println(buscar_pedido + "\n");
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
            System.out.println(filas + "\n");
            for (int j = 0; j < filas; j++) {
                pro.setId_pro(Integer.parseInt(frm_reg_venta.t_detalle.getValueAt(j, 0).toString()));
                pro.setCan(Double.parseDouble(frm_reg_venta.t_detalle.getValueAt(j, 3).toString()));
                pro.setPre_pro(Double.parseDouble(frm_reg_venta.t_detalle.getValueAt(j, 5).toString()));
                try {
                    Statement st = con.conexion();
                    String ins_det_ped = "insert into detalle_pedido Values ('" + pro.getId_pro() + "', '" + ped.getId_ped() + "', "
                            + "'" + pro.getPre_pro() + "', '" + pro.getCan() + "')";
                    System.out.println(ins_det_ped + "\n");
                    con.actualiza(st, ins_det_ped);
                    con.cerrar(st);
                } catch (Exception ex) {
                    System.err.print(ex.getLocalizedMessage());
                }
                //insertar datos en kardex
                try {
                    Statement st = con.conexion();
                    String ins_kardex = "insert into kardex Values (null, '" + ped.getFec_ped() + "', '" + pro.getId_pro() + "', '0.00', '0.00', '"
                            + pro.getCan() + "', '" + pro.getPre_pro() + "','" + tido.getSerie() + "', '" + tido.getNro() + "', '" + tido.getId() + "',"
                            + " '" + frm_menu.alm.getId() + "','" + cli.getNro_doc() + "', '" + cli.getNom_cli() + "','1')";
                    System.out.println(ins_kardex);
                    con.actualiza(st, ins_kardex);
                    con.cerrar(st);
                } catch (Exception ex) {
                    System.err.print("Error en: " + ex.getLocalizedMessage());
                }

                //seleccionar cantidad de producto y restar
                try {
                    Statement st = con.conexion();
                    String bus_pro = "select cant_actual from productos where idProductos = '" + pro.getId_pro() + "'";
                    System.out.println(bus_pro + "\n");
                    ResultSet rs = con.consulta(st, bus_pro);
                    if (rs.next()) {
                        pro.setCan_act_pro(rs.getDouble("cant_actual"));
                    }
                    con.cerrar(rs);
                    con.cerrar(st);
                    pro.setCan_act_pro(pro.getCan_act_pro() - pro.getCan());
                } catch (SQLException ex) {
                    System.err.print("Error en: " + ex.getLocalizedMessage());
                }
                //actualizar cantidad de productos en general
                try {
                    Statement st = con.conexion();
                    String act_pro = "update productos set cant_actual = '" + pro.getCan_act_pro() + "' where idProductos = '" + pro.getId_pro() + "' ";
                    System.out.println(act_pro);
                    con.actualiza(st, act_pro);
                    con.cerrar(st);
                } catch (Exception ex) {
                    System.err.print(ex.getLocalizedMessage());
                }

                //consultar cantidad de producto en tienda
                try {
                    Statement st = con.conexion();
                    String ver_prod_alm = "select idProductos, cant from producto_almacen where idAlmacen = '" + frm_menu.alm.getId() + "' "
                            + "and idProductos = '" + pro.getId_pro() + "'";
                    System.out.println(ver_prod_alm + "\n");
                    ResultSet rs = con.consulta(st, ver_prod_alm);
                    if (rs.next()) {
                        pro.setCan_act_pro(rs.getDouble("cant"));
                    }
                    pro.setCan_act_pro(pro.getCan_act_pro() - pro.getCan());
                    con.cerrar(rs);
                    con.cerrar(st);
                } catch (SQLException ex) {
                    System.err.print(ex.getLocalizedMessage());
                }

                //actualizar cantidad de productos en tienda
                try {
                    Statement st = con.conexion();
                    String act_pro = "update producto_almacen set cant = '" + pro.getCan_act_pro() + "' where "
                            + "idProductos = '" + pro.getId_pro() + "' and idAlmacen = '" + frm_menu.alm.getId() + "'";
                    System.out.println(act_pro + "\n");
                    con.actualiza(st, act_pro);
                    con.cerrar(st);
                } catch (Exception ex) {
                    System.err.print(ex.getLocalizedMessage());
                }
            }
            //registrar movimiento
            switch (ped.getEst_ped()) {
                case "1":
                    System.out.println("Venta con Pago al Contado \n");
                    //registro de pago en efectivo
                    if (Double.parseDouble(txt_efec.getText()) > 0) {
                        String glosa = "VENTA EN EFECTIVO / " + tido.getDesc() + " / " + tido.getSerie() + " - " + tido.getNro() + " / " + cli.getNom_cli();
                        try {
                            Statement st = con.conexion();
                            String add_mov_caja = "insert into movimiento Values (null, '" + glosa + "', '" + ped.getFec_ped() + "' , '" + txt_efec.getText() + "' "
                                    + ", '0.00', '" + usu.getNick() + "','" + frm_menu.alm.getId() + "', 'C', '" + frm_menu.caja.getId() + "')";
                            System.out.println(add_mov_caja + "\n");
                            con.actualiza(st, add_mov_caja);
                            con.cerrar(st);
                        } catch (Exception ex) {
                            System.err.print("Error en:" + ex.getLocalizedMessage());

                        }
                        //registrar pago en detalle_pago
                        try {
                            Statement st = con.conexion();
                            String add_mov_caja = "insert into letras_pedido Values (null, '" + txt_efec.getText() + "', '" + ped.getFec_ped() + "', '" + ped.getId_ped() + "', 'EFECTIVO')";
                            System.out.println(add_mov_caja + "\n");
                            con.actualiza(st, add_mov_caja);
                            con.cerrar(st);
                        } catch (Exception ex) {
                            System.err.print("Error en:" + ex.getLocalizedMessage());

                        }

                    }
                    //registro de pago con tarjeta
                    if (Double.parseDouble(txt_tarj.getText()) > 0) {
                        String glosa = "VENTA CON TARJETA / " + tido.getDesc() + " / " + tido.getSerie() + " - " + tido.getNro() + " / " + cli.getNom_cli();
                        try {
                            Statement st = con.conexion();
                            String add_mov_tarj = "insert into movimiento Values (null, '" + glosa + "', '" + ped.getFec_ped() + "' , '" + txt_tarj.getText() + "' "
                                    + ", '0.00', '" + usu.getNick() + "','" + frm_menu.alm.getId() + "',  'B', '" + frm_menu.cue.getId_cuen() + "')";
                            System.out.println(add_mov_tarj + "\n");
                            con.actualiza(st, add_mov_tarj);
                            con.cerrar(st);
                        } catch (Exception ex) {
                            System.err.print("Error en:" + ex.getLocalizedMessage());

                        }
                        //registrar pago en detalle_pago
                        try {
                            Double tarjeta = Double.parseDouble(txt_tarj.getText());
                            Statement st = con.conexion();
                            String add_mov_caja = "insert into letras_pedido Values (null, '" + tarjeta + "', '" + ped.getFec_ped() + "', '" + ped.getId_ped() + "', 'TARJETA')";
                            System.out.println(add_mov_caja + "\n");
                            con.actualiza(st, add_mov_caja);
                            con.cerrar(st);
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, ex);
                            System.err.print("Error en:" + ex.getLocalizedMessage());

                        }
                    }

                    //imprimir ticket de venta
                    imprime.set_tipv(tido.getDesc());
                    imprime.set_idped(ped.getId_ped());
                    System.out.println(imprime.get_idped() + " - " + imprime.get_tipv());
                    imprime.start();
                    break;

                case "6":
                    // cambiar fecha de pago 
                    int dias = 0;
                    if (cbx_plazo.getSelectedIndex() == 0) {
                        dias = 15;
                    } else if (cbx_plazo.getSelectedIndex() == 1) {
                        dias = 30;
                    } else {
                        dias = 45;
                    }
                    ped.setFec_pag_ped(ven.suma_dia(ped.getFec_ped(), dias).toString());
                    try {
                        Statement st = con.conexion();
                        String upd_ped = "update pedido set fec_pag = '" + ped.getFec_pag_ped() + "' and est_ped = '" + ped.getEst_ped() + "' "
                                + "where idPedido = '" + ped.getId_ped() + "'";
                        System.out.println(upd_ped + "\n");
                        con.actualiza(st, upd_ped);
                        con.cerrar(st);
                    } catch (Exception ex) {
                        System.err.print("Error en:" + ex.getLocalizedMessage());

                    }
                    //imprimir ticket de venta
                    imprime.set_tipv(tido.getDesc());
                    imprime.set_idped(ped.getId_ped());
                    System.out.println(imprime.get_idped() + " - " + imprime.get_tipv());
                    imprime.start();
                    break;
            }

        } else {
            //registrar separacion
            try {
                Statement st = con.conexion();
                String ins_ven = "insert into pedido Values (null, '" + ped.getFec_ped() + "', '" + ped.getFec_pag_ped() + "', "
                        + "'" + tipa.getId() + "', '" + ped.getDes_ped() + "', '" + ped.getEst_ped() + "', '" + tido.getId() + "', "
                        + "'" + tido.getSerie() + "', '" + tido.getNro() + "', '" + usu.getNick() + "', "
                        + "'" + frm_menu.alm.getId() + "', null, '" + cli.getNro_doc() + "', '" + cli.getNom_cli() + "', '" + ped.getTotal() + "')";
                System.out.println(ins_ven + "\n");
                con.actualiza(st, ins_ven);
                con.cerrar(st);
            } catch (Exception ex) {
                System.out.println("Error en pedido: " + ex);
            }

            //buscar ultimo pedido
            try {
                Statement st = con.conexion();
                String buscar_pedido = "select idPedido from pedido where nro_doc = '" + tido.getNro() + "' "
                        + "and fec_ped = '" + ped.getFec_ped() + "' and idAlmacen = "
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

            //insertar detalle pedido en separacion
            int filas = frm_reg_venta.t_detalle.getRowCount();
            for (int j = 0; j < filas; j++) {
                pro.setId_pro(Integer.parseInt(frm_reg_venta.t_detalle.getValueAt(j, 0).toString()));
                pro.setCan(Double.parseDouble(frm_reg_venta.t_detalle.getValueAt(j, 3).toString()));
                pro.setPre_pro(Double.parseDouble(frm_reg_venta.t_detalle.getValueAt(j, 5).toString()));
                try {
                    Statement st = con.conexion();
                    String ins_det_ped = "insert into detalle_pedido Values ('" + pro.getId_pro() + "', '" + ped.getId_ped() + "', "
                            + "'" + pro.getPre_pro() + "', '" + pro.getCan() + "')";
                    System.out.println(ins_det_ped + "\n");
                    con.actualiza(st, ins_det_ped);
                    con.cerrar(st);
                } catch (Exception ex) {
                    System.err.print("Error detalle pedido: " + ex);
                }
            }
            Double ent_efec;
            Double ent_tarj;
            ent_efec = Double.parseDouble(txt_efec.getText());
            ent_tarj = Double.parseDouble(txt_tarj.getText());

            //pago en efectivo
            if (Double.parseDouble(txt_efec.getText()) > 0) {
                String glosa = "ADELANTO DE SEPARACION EN EFECTIVO/ " + tido.getDesc() + " / " + tido.getSerie() + " - " + tido.getNro() + " / " + cli.getNro_doc();
                try {
                    Statement st = con.conexion();
                    String add_mov = "insert into movimiento Values (null, '" + glosa + "', '" + ped.getFec_ped() + "' , '" + ent_efec + "' "
                            + ", '0.00', '" + usu.getNick() + "','" + frm_menu.alm.getId() + "', 'C', '" + frm_menu.caja.getId() + "')";
                    System.out.println(add_mov + "\n");
                    con.actualiza(st, add_mov);
                    con.cerrar(st);
                } catch (Exception ex) {
                    System.err.print("Error en:" + ex.getLocalizedMessage());
                }

                try {
                    Statement st = con.conexion();
                    String ins_let_efec = "insert into letras_pedido values(null, '" + ent_efec + "', '" + ped.getFec_ped() + "', '" + ped.getId_ped() + "', 'EFECTIVO')";
                    System.out.println(ins_let_efec + "\n");
                    con.actualiza(st, ins_let_efec);
                    con.cerrar(st);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Error en letras_pedido: " + e);
                }
            }
            //pago con tarjeta
            if (Double.parseDouble(txt_tarj.getText()) > 0) {
                String glosa = "ADELANTO DE SEPARACION CON TARJETA/ " + tido.getDesc() + " / " + tido.getSerie() + " - " + tido.getNro() + " / " + cli.getNro_doc();
                try {
                    Statement st = con.conexion();
                    String add_mov = "insert into movimiento Values (null, '" + glosa + "', '" + ped.getFec_ped() + "' , '" + ent_efec + "' "
                            + ", '0.00', '" + usu.getNick() + "','" + frm_menu.alm.getId() + "', 'B', '" + frm_menu.cue.getId_cuen() + "')";
                    System.out.println(add_mov + "\n");
                    con.actualiza(st, add_mov);
                    con.cerrar(st);
                } catch (Exception ex) {
                    System.err.print("Error en:" + ex.getLocalizedMessage());
                }

                try {
                    Statement st = con.conexion();
                    String ins_let_efec = "insert into letras_pedido values(null, '" + ent_efec + "', '" + ped.getFec_ped() + "', '" + ped.getId_ped() + "', 'TARJETA')";
                    System.out.println(ins_let_efec + "\n");
                    con.actualiza(st, ins_let_efec);
                    con.cerrar(st);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Error en letras_pedido: " + e);
                }
            }

            //actualizar documentos
            try {
                tido.act_doc(tido.getSerie(), tido.getNro() + 1, frm_menu.alm.getId(), tido.getId());
            } catch (Exception e) {
                System.out.println(e);
            }
            Double total = ent_efec + ent_tarj;

            // TICKET DE SEPARACION
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("idped", ped.getId_ped());
            parametros.put("Adelanto", total);
            parametros.put("Acumulado", total);
            String filename = "rpt_ticket_separacion";
            ven.imp_reporte(filename, parametros);

        }

        //cerrar y volver abrir
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
        comision = 0;
        if (chk_incluir.isSelected()) {
            comision = m_tarj * 0.05;
            txt_com.setText(formato.format(comision));
            lbl_tot.setText("S/. " + formato.format(ped.getTotal() + comision));
        } else {
            comision = 0;
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
            if (accion.equals("venta")) {
                if (ped.getTotal() - suma_tot <= 0.0005) {
                    btn_reg.setEnabled(true);
                    btn_reg.requestFocus();
                }
            } else {
                btn_reg.setEnabled(true);
                btn_reg.requestFocus();
            }
            txt_subt.setText(formato.format(suma_tot));
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

    private void txt_efecKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_efecKeyTyped
        char car = evt.getKeyChar();
        if ((car < '0' || car > '9') && car != '.') {
            evt.consume();
        }
    }//GEN-LAST:event_txt_efecKeyTyped

    private void txt_tarjKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_tarjKeyTyped
        char car = evt.getKeyChar();
        if ((car < '0' || car > '9') && car != '.') {
            evt.consume();
        }
    }//GEN-LAST:event_txt_tarjKeyTyped

    private void cbx_plazoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbx_plazoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btn_reg.setEnabled(true);
            btn_reg.requestFocus();
        }
    }//GEN-LAST:event_cbx_plazoKeyPressed


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
