/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import Clases.Cl_Almacen;
import Clases.Cl_Compra;
import Clases.Cl_Conectar;
import Clases.Cl_Productos;
import Clases.Cl_Proveedor;
import Clases.Cl_Tipo_Documentos;
import Clases.Cl_Varios;
import Forms.frm_reg_compra_prod;
import Forms.frm_reg_pago_compra;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import sonomusic.frm_menu;

/**
 *
 * @author luis-d
 */
public class frm_ver_compras_prod extends javax.swing.JInternalFrame {

    Cl_Conectar con = new Cl_Conectar();
    Cl_Varios ven = new Cl_Varios();
    Cl_Compra com = new Cl_Compra();
    Cl_Proveedor pro = new Cl_Proveedor();
    Cl_Tipo_Documentos tido = new Cl_Tipo_Documentos();
    Cl_Almacen alm = new Cl_Almacen();
    Cl_Productos art = new Cl_Productos();
    DefaultTableModel mostrar;
    DecimalFormatSymbols simbolo = new DecimalFormatSymbols(Locale.US);
    DecimalFormat formato = new DecimalFormat("####0.00", simbolo);
    Integer i;
    String valor;

    /**
     * Creates new form frm_ver_compras
     */
    public frm_ver_compras_prod() {
        initComponents();

        String query = "select c.idCompra, c.fecha_doc, c.fecha_pago, c.total, t.desc_tipd, c.serie_doc, c.estado, c.nro_doc, c.ruc_pro, p.raz_soc_pro, a.nom_alm from compra as c "
                + "inner join tipo_doc as t on c.idtipo_doc=t.idtipo_doc inner join proveedor as p on c.ruc_pro=p.ruc_pro "
                + "inner join almacen as a on c.idAlmacen=a.idAlmacen where c.tipo_compra = 'P' order by c.fecha_doc desc, c.idCompra desc";
        ver_compras(query);

    }

    private void ver_compras(String query) {
        try {
            mostrar = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int fila, int columna) {
                    return false;
                }
            };
            mostrar.addColumn("Id");
            mostrar.addColumn("Fec. Compra");
            mostrar.addColumn("Fec. Pago");
            mostrar.addColumn("Tipo Doc.");
            mostrar.addColumn("Serie");
            mostrar.addColumn("Nro.");
            mostrar.addColumn("RUC");
            mostrar.addColumn("Razon Social");
            mostrar.addColumn("Total");
            mostrar.addColumn("Almacen");
            mostrar.addColumn("Estado");

            Statement st = con.conexion();
            ResultSet rs = con.consulta(st, query);
            while (rs.next()) {
                Object fila[] = new Object[11];
                fila[0] = rs.getString("idCompra");
                fila[1] = rs.getString("fecha_doc");
                fila[2] = rs.getString("fecha_pago");
                fila[3] = rs.getString("desc_tipd");
                fila[4] = rs.getString("serie_doc");
                fila[5] = rs.getString("nro_doc");
                fila[6] = rs.getString("ruc_pro");
                fila[7] = rs.getString("raz_soc_pro");
                fila[8] = formato.format(rs.getDouble("total"));
                fila[9] = rs.getString("nom_alm");
                if (rs.getString("estado").equals("1")) {
                    fila[10] = "PAGADO";
                } else {
                    fila[10] = "PENDIENTE";
                }
                mostrar.addRow(fila);
            }
            con.cerrar(st);
            con.cerrar(rs);
            t_compras.setModel(mostrar);
            t_compras.getColumnModel().getColumn(0).setPreferredWidth(30);
            t_compras.getColumnModel().getColumn(1).setPreferredWidth(75);
            t_compras.getColumnModel().getColumn(2).setPreferredWidth(75);
            t_compras.getColumnModel().getColumn(3).setPreferredWidth(80);
            t_compras.getColumnModel().getColumn(4).setPreferredWidth(35);
            t_compras.getColumnModel().getColumn(4).setPreferredWidth(35);
            t_compras.getColumnModel().getColumn(5).setPreferredWidth(70);
            t_compras.getColumnModel().getColumn(6).setPreferredWidth(90);
            t_compras.getColumnModel().getColumn(7).setPreferredWidth(250);
            t_compras.getColumnModel().getColumn(8).setPreferredWidth(60);
            t_compras.getColumnModel().getColumn(9).setPreferredWidth(80);
            t_compras.getColumnModel().getColumn(10).setPreferredWidth(80);
            ven.centrar_celda(t_compras, 1);
            ven.centrar_celda(t_compras, 2);
            ven.centrar_celda(t_compras, 4);
            ven.centrar_celda(t_compras, 5);
            ven.derecha_celda(t_compras, 8);

        } catch (SQLException ex) {
            System.out.print(ex);
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
        txt_bus = new javax.swing.JTextField();
        btn_reg = new javax.swing.JButton();
        btn_anu = new javax.swing.JButton();
        btn_det = new javax.swing.JButton();
        btn_cer = new javax.swing.JButton();
        cbx_bus = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        t_compras = new javax.swing.JTable();
        btn_pagar = new javax.swing.JButton();

        setBackground(new java.awt.Color(254, 254, 254));
        setClosable(true);
        setTitle("Ver Compras de Productos");

        jLabel1.setForeground(new java.awt.Color(212, 2, 2));
        jLabel1.setText("Buscar por:");

        txt_bus.setEditable(false);
        txt_bus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_busKeyPressed(evt);
            }
        });

        btn_reg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/add.png"))); // NOI18N
        btn_reg.setText("Registrar");
        btn_reg.setMargin(new java.awt.Insets(2, 8, 2, 8));
        btn_reg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_regActionPerformed(evt);
            }
        });

        btn_anu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/bin_closed.png"))); // NOI18N
        btn_anu.setText("Anular");
        btn_anu.setEnabled(false);
        btn_anu.setMargin(new java.awt.Insets(2, 8, 2, 8));
        btn_anu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_anuActionPerformed(evt);
            }
        });

        btn_det.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/clipboard_text.png"))); // NOI18N
        btn_det.setText("Ver Detalle");
        btn_det.setEnabled(false);
        btn_det.setMargin(new java.awt.Insets(2, 8, 2, 8));
        btn_det.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_detActionPerformed(evt);
            }
        });

        btn_cer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/cancel.png"))); // NOI18N
        btn_cer.setText("Cerrar");
        btn_cer.setMargin(new java.awt.Insets(2, 8, 2, 8));
        btn_cer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cerActionPerformed(evt);
            }
        });

        cbx_bus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "RUC", "RAZON SOCIAL", "NRO. DOC." }));
        cbx_bus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbx_busActionPerformed(evt);
            }
        });

        jScrollPane1.setBackground(new java.awt.Color(254, 254, 254));

        t_compras.setModel(new javax.swing.table.DefaultTableModel(
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
        t_compras.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        t_compras.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                t_comprasMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(t_compras);

        btn_pagar.setText("Pagar");
        btn_pagar.setEnabled(false);
        btn_pagar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pagarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn_reg)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_anu)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_det)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_pagar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbx_bus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_bus, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_cer)
                        .addGap(9, 9, 9))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(txt_bus, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_reg, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_det, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_anu, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_cer, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbx_bus, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_pagar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 475, Short.MAX_VALUE)
                .addContainerGap())
        );

        getAccessibleContext().setAccessibleName("Ver Compras");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_cerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cerActionPerformed
        this.dispose();
    }//GEN-LAST:event_btn_cerActionPerformed

    private void btn_regActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_regActionPerformed
        frm_reg_compra_prod compra = new frm_reg_compra_prod();
        ven.llamar_ventana(compra);
        this.dispose();
    }//GEN-LAST:event_btn_regActionPerformed

    private void t_comprasMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_t_comprasMousePressed
        i = t_compras.getSelectedRow();
        com.setEst(t_compras.getValueAt(i, 10).toString());
        btn_anu.setEnabled(true);
        btn_det.setEnabled(true);
        if (!com.getEst().equals("PAGADO")) {
            btn_pagar.setEnabled(true);
        } else {
            btn_pagar.setEnabled(false);
        }
    }//GEN-LAST:event_t_comprasMousePressed

    private void btn_anuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_anuActionPerformed
        int confirmado = JOptionPane.showConfirmDialog(null, "¿Confirma eliminar la compra?");
        if (JOptionPane.OK_OPTION == confirmado) {
            com.setId(Integer.parseInt(t_compras.getValueAt(i, 0).toString()));
            Double suma_pro = 0.00;
            //seleccionar almacen
            try {
                Statement st = con.conexion();
                String ver_alm = "select c.idtipo_doc, c.serie_doc, c.ruc_pro, p.raz_soc_pro, c.nro_doc, c.fecha_doc, c.total, "
                        + "c.idAlmacen from compra as c inner join proveedor as p on c.ruc_pro=p.ruc_pro where c.idCompra = '" + com.getId() + "'";
                ResultSet rs = con.consulta(st, ver_alm);
                if (rs.next()) {
                    com.setFec_com(rs.getString("fecha_doc"));
                    pro.setRuc(rs.getString("ruc_pro"));
                    pro.setRaz(rs.getString("raz_soc_pro"));
                    alm.setId(rs.getInt("idAlmacen"));
                    tido.setId(rs.getInt("idtipo_doc"));
                    tido.setSerie(rs.getInt("serie_doc"));
                    tido.setNro(rs.getInt("nro_doc"));
                    com.setTotal(rs.getDouble("total"));
                }
                con.cerrar(rs);
                con.cerrar(st);
            } catch (SQLException ex) {
                System.out.print(ex);
            }

            //seleccionar detalle de compra, cantidad de productos;
            try {
                Statement st = con.conexion();
                String ver_ped = "select idProductos, precio_compra, cant_compra from detalle_compra where idCompra = '" + com.getId() + "'";
                ResultSet rs = con.consulta(st, ver_ped);
                int nro = 0;
                while (rs.next()) {
                    nro++;
                    System.out.print(nro + "\n");
                    art.setId_pro(rs.getInt("idProductos"));
                    art.setCan(rs.getDouble("cant_compra"));
                    art.setCos_pro(rs.getDouble("precio_compra"));
                    suma_pro = suma_pro + (art.getCan() * art.getCos_pro());
                    System.out.print("Suma Actual: " + suma_pro + "\n");
                    Double pro_can = 0.00;
                    Double new_can = 0.00;
                    Double pro_can_alm = 0.00;
                    Double new_can_alm = 0.00;
                    try {
                        Statement st1 = con.conexion();
                        String ver_pro = "select cant_actual from productos where idProductos = '" + art.getId_pro() + "'";
                        ResultSet rs1 = con.consulta(st1, ver_pro);
                        if (rs1.next()) {
                            pro_can = rs1.getDouble("cant_actual");
                        }
                        con.cerrar(rs1);
                        con.cerrar(st1);
                        new_can = pro_can - art.getCan();
                    } catch (SQLException ex1) {
                        System.out.print(ex1);
                    }
                    System.out.print("Cantidad actual: " + pro_can + " Producto: " + art.getId_pro() + "\n");

                    try {
                        Statement st1 = con.conexion();
                        String ver_pro = "select cant from producto_almacen where idProductos = '" + art.getId_pro() + "' and idAlmacen = '" + alm.getId() + "'";
                        ResultSet rs1 = con.consulta(st1, ver_pro);
                        if (rs1.next()) {
                            pro_can_alm = rs1.getDouble("cant");
                        }
                        con.cerrar(rs1);
                        con.cerrar(st1);
                        new_can_alm = pro_can_alm - art.getCan();
                    } catch (SQLException ex1) {
                        System.out.print(ex1);
                    }
                    System.out.print("Cantidad actual: " + pro_can_alm + " Producto: " + art.getId_pro() + " en Almacen: " + alm.getId() + "\n");

                    try {
                        Statement st1 = con.conexion();
                        String upt_pro_alm = "update producto_almacen set cant = '" + new_can_alm + "' where idProductos = '" + art.getId_pro() + "' and idAlmacen = '" + alm.getId() + "'";
                        con.actualiza(st1, upt_pro_alm);
                        con.cerrar(st1);
                    } catch (Exception ex1) {
                        System.out.print(ex1);
                    }
                    System.out.print("Cantidad nueva: " + new_can_alm + " Producto: " + art.getId_pro() + " en Almacen: " + alm.getId() + "\n");

                    try {
                        Statement st1 = con.conexion();
                        String upt_pro = "update productos set cant_actual = '" + new_can + "' where idProductos = '" + art.getId_pro() + "'";
                        con.actualiza(st1, upt_pro);
                        con.cerrar(st1);
                    } catch (Exception ex1) {
                        System.out.print(ex1);
                    }
                    System.out.print("Cantidad nueva: " + new_can + " Producto: " + art.getId_pro() + "\n");

                    try {
                        Statement st1 = con.conexion();
                        String ins_kar = "insert into kardex Values (null, '" + com.getFec_com() + "', '" + art.getId_pro() + "', '0.00', '0.00', '" + art.getCan() + "', '" + art.getPre_pro() + "', "
                                + "'" + com.getSerie() + "', '" + com.getNro() + "', '" + tido.getId() + "', '" + alm.getId() + "', '" + pro.getRuc() + "', '" + pro.getRaz() + "', '2')";
                        con.actualiza(st1, ins_kar);
                        con.cerrar(st1);
                    } catch (Exception ex) {
                        System.out.print(ex);
                    }
                    System.out.print("Agregando al Kardex: Producto: " + art.getId_pro() + " - " + com.getFec_com() + " - " + art.getCan() + "\n");

                }

            } catch (SQLException ex) {
                System.out.print(ex);
            }

            //calcular monto 
            Double monto = 0.00;
            monto = suma_pro * 1.18;
            //eliminar
            try {
                Statement st1 = con.conexion();
                String del_ped = "delete from detalle_compra where idCompra = '" + com.getId() + "'";
                con.actualiza(st1, del_ped);
                con.cerrar(st1);
            } catch (Exception ex1) {
                System.out.print(ex1);
            }
            System.out.print("Eliminando compra" + "\n");

            try {
                Statement st1 = con.conexion();
                String del_ped = "delete from compra where idCompra = '" + com.getId() + "'";
                con.actualiza(st1, del_ped);
                con.cerrar(st1);
            } catch (Exception ex1) {
                System.out.print(ex1);
            }
            System.out.print("Eliminando detalle de compra \n");

          //  registrar en movimientos. 
            if (t_compras.getValueAt(i, 10).equals("PAGADO")) {
                String glosa = "ANULACION DE COMPRA - " + t_compras.getValueAt(i, 3) + " / " + t_compras.getValueAt(i, 4) + " - " + t_compras.getValueAt(i, 5) + " - " + pro.getRuc();
                try {
                    Statement st = con.conexion();
                    String add_mov = "insert into movimiento Values (null, '" + glosa + "', '" + com.getFec_com() + "', '" + monto + "', '0.00' , '" + frm_menu.lbl_user.getText() + "', "
                            + " 'C', '1')";
                    con.actualiza(st, add_mov);
                    con.cerrar(st);
                } catch (Exception ex) {
                    System.out.print(ex);
                }
            }

            String query = "select c.idCompra, c.fecha_doc, c.fecha_pago, c.total, t.desc_tipd, c.serie_doc, c.estado, c.nro_doc, c.ruc_pro, p.raz_soc_pro, a.nom_alm from compra as c "
                    + "inner join tipo_doc as t on c.idtipo_doc=t.idtipo_doc inner join proveedor as p on c.ruc_pro=p.ruc_pro "
                    + "inner join almacen as a on c.idAlmacen=a.idAlmacen where c.tipo_compra = 'P' order by c.fecha_doc desc, c.idCompra desc";
            ver_compras(query);

            System.out.print("Mostrando lista \n");
            txt_bus.requestFocus();

        }
    }//GEN-LAST:event_btn_anuActionPerformed

    private void btn_detActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_detActionPerformed
        try {
            String id=t_compras.getValueAt(i, 0).toString();
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("compra", id);
            ven.ver_reporte("rpt_ver_det_compra", parametros);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: "+e.getLocalizedMessage());
        }
    }//GEN-LAST:event_btn_detActionPerformed

    private void btn_pagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pagarActionPerformed
        frm_reg_pago_compra pagar = new frm_reg_pago_compra();
        pagar.txt_ruc.setText(t_compras.getValueAt(i, 6).toString());
        pagar.txt_raz.setText(t_compras.getValueAt(i, 7).toString());
        pagar.txt_tido.setText(t_compras.getValueAt(i, 3).toString());
        pagar.txt_ser.setText(t_compras.getValueAt(i, 4).toString());
        pagar.txt_nro.setText(t_compras.getValueAt(i, 5).toString());
        pagar.txt_deu.setText(t_compras.getValueAt(i, 8).toString());

        Double actual = 0.0;
        Double pagado = 0.0;
        actual = Double.parseDouble(t_compras.getValueAt(i, 8).toString());
        try {
            Statement st = con.conexion();
            String ver_pagos = "select sum(monto) as pagos from pago_compras where idCompra = '" + t_compras.getValueAt(i, 0).toString() + "'";
            ResultSet rs = con.consulta(st, ver_pagos);
            if (rs.next()) {
                pagado = rs.getDouble("pagos");
                pagar.pagado = rs.getDouble("pagos");
                pagar.txt_pag.setText(formato.format(rs.getDouble("pagos")));
            } else {
                pagar.txt_pag.setText("0.00");
            }
        } catch (SQLException es) {
            System.out.print(es);
        }
        Double restante = 0.0;
        restante = actual - pagado;
        pagar.txt_fec.setText(ven.fechaformateada(ven.getFechaActual()));
        pagar.txt_sal.setText(formato.format(restante));
        pagar.restante = restante;
        pagar.com.setId(Integer.parseInt(t_compras.getValueAt(i, 0).toString()));
        pagar.funcion = "productos";
        pagar.glosa = "PAGO DE COMPRA - " + t_compras.getValueAt(i, 3).toString() + " / " + t_compras.getValueAt(i, 4).toString() + 
                " - " + t_compras.getValueAt(i, 5).toString() + " - " + t_compras.getValueAt(i, 6).toString();
        ven.llamar_ventana(pagar);
        this.dispose();
    }//GEN-LAST:event_btn_pagarActionPerformed

    private void txt_busKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_busKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String lee = txt_bus.getText();
            if (cbx_bus.getSelectedIndex() == 0) {
                String query = "select c.idCompra, c.fecha_doc, c.fecha_pago, c.total, t.desc_tipd, c.serie_doc, c.estado, c.nro_doc, c.ruc_pro, p.raz_soc_pro, a.nom_alm from compra as c "
                        + "inner join tipo_doc as t on c.idtipo_doc=t.idtipo_doc inner join proveedor as p on c.ruc_pro=p.ruc_pro "
                        + "inner join almacen as a on c.idAlmacen=a.idAlmacen where c.tipo_compra = 'P' and  c.ruc_pro like '%" + lee + "%' order by c.fecha_doc desc, c.idCompra desc ";
                ver_compras(query);
            } else if (cbx_bus.getSelectedIndex() == 1) {
                String query = "select c.idCompra, c.fecha_doc, c.fecha_pago, c.total, t.desc_tipd, c.serie_doc, c.estado, c.nro_doc, c.ruc_pro, p.raz_soc_pro, a.nom_alm from compra as c "
                        + "inner join tipo_doc as t on c.idtipo_doc=t.idtipo_doc inner join proveedor as p on c.ruc_pro=p.ruc_pro "
                        + "inner join almacen as a on c.idAlmacen=a.idAlmacen where c.tipo_compra = 'P' and p.raz_soc_pro like '%" + lee + "%' order by c.fecha_doc desc, c.idCompra desc ";
                ver_compras(query);
            } else {
                String query = "select c.idCompra, c.fecha_doc, c.fecha_pago, c.total, t.desc_tipd, c.serie_doc, c.estado, c.nro_doc, c.ruc_pro, p.raz_soc_pro, a.nom_alm from compra as c "
                        + "inner join tipo_doc as t on c.idtipo_doc=t.idtipo_doc inner join proveedor as p on c.ruc_pro=p.ruc_pro "
                        + "inner join almacen as a on c.idAlmacen=a.idAlmacen where c.tipo_compra = 'P' and c.nro_doc like '%" + lee + "%' order by c.fecha_doc desc, c.idCompra desc ";
                ver_compras(query);
            }
        }
    }//GEN-LAST:event_txt_busKeyPressed

    private void cbx_busActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbx_busActionPerformed
        txt_bus.setEditable(true);
        txt_bus.setText("");
        txt_bus.requestFocus();
    }//GEN-LAST:event_cbx_busActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_anu;
    private javax.swing.JButton btn_cer;
    private javax.swing.JButton btn_det;
    private javax.swing.JButton btn_pagar;
    private javax.swing.JButton btn_reg;
    private javax.swing.JComboBox cbx_bus;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable t_compras;
    private javax.swing.JTextField txt_bus;
    // End of variables declaration//GEN-END:variables
}
