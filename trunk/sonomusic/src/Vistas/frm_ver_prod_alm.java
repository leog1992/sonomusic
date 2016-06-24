/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import Clases.Cl_Almacen;
import Clases.Cl_Conectar;
import Clases.Cl_Productos;
import Clases.Cl_Varios;
import Forms.frm_reg_solicitud;
import Forms.frm_reg_traslado_almacen;
import Forms.frm_reg_venta;
import Forms.frm_rpt_fechas;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author luis-d
 */
public class frm_ver_prod_alm extends javax.swing.JInternalFrame {

    Cl_Varios ven = new Cl_Varios();
    Cl_Conectar con = new Cl_Conectar();
    Cl_Productos pro = new Cl_Productos();
    Cl_Almacen alm = new Cl_Almacen();
    public static DefaultTableModel mostrar;
    public static String funcion = "producto_almacen";
    Integer i;

    /**
     * Creates new form frm_ver_mat_alm
     */
    public frm_ver_prod_alm() {
        initComponents();

        txt_bus.requestFocus();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        t_productos = new javax.swing.JTable();
        txt_bus = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        btn_cer = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txt_ida = new javax.swing.JTextField();
        txt_noma = new javax.swing.JTextField();
        btn_kar = new javax.swing.JButton();
        cbx_bus = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(254, 254, 254));
        setClosable(true);
        setResizable(true);
        setTitle("Ver Productos en Almacen");

        jScrollPane1.setBackground(new java.awt.Color(254, 254, 254));

        t_productos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6", "Title 7", "Title 8", "Title 9", "Title 10"
            }
        ));
        t_productos.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        t_productos.setRowHeight(20);
        t_productos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                t_productosMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                t_productosMousePressed(evt);
            }
        });
        t_productos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                t_productosKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(t_productos);

        txt_bus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_busKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_busKeyTyped(evt);
            }
        });

        jLabel1.setForeground(new java.awt.Color(212, 2, 2));
        jLabel1.setText("Buscar:");

        btn_cer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/cancel.png"))); // NOI18N
        btn_cer.setText("Cerrar");
        btn_cer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cerActionPerformed(evt);
            }
        });

        jLabel2.setForeground(new java.awt.Color(212, 2, 2));
        jLabel2.setText("Almacen");

        txt_ida.setEditable(false);
        txt_ida.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        txt_noma.setEditable(false);

        btn_kar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/clipboard_text.png"))); // NOI18N
        btn_kar.setText("Ver Kardex");
        btn_kar.setEnabled(false);
        btn_kar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_karActionPerformed(evt);
            }
        });

        cbx_bus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "TODOS", "DISPONIBLES", "POR TERMINAR ", "TERMINADO" }));
        cbx_bus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cbx_busKeyPressed(evt);
            }
        });

        jLabel3.setText("0");

        jButton1.setText("ver Historial");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel4.setText("encontrados");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1060, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_bus, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(53, 53, 53)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cbx_bus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_ida, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_noma, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btn_kar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_cer)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_bus, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_ida, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_noma, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbx_bus, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 349, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_cer, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_kar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void t_productosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_t_productosKeyPressed

    }//GEN-LAST:event_t_productosKeyPressed

    private void btn_cerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cerActionPerformed
        funcion = "material_almacen";
        this.dispose();
    }//GEN-LAST:event_btn_cerActionPerformed

    private void btn_karActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_karActionPerformed
        frm_rpt_fechas rpt = new frm_rpt_fechas();
        rpt.rpt = "kardex";
        rpt.pro.setId_pro(Integer.parseInt(t_productos.getValueAt(i, 0).toString()));
        rpt.alm.setId(Integer.parseInt(txt_ida.getText()));
        ven.llamar_ventana(rpt);
    }//GEN-LAST:event_btn_karActionPerformed

    private void t_productosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_t_productosMousePressed
//        i = t_productos.getSelectedRow();
//        btn_kar.setEnabled(true);
    }//GEN-LAST:event_t_productosMousePressed

    private void cbx_busKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbx_busKeyPressed
        if (cbx_bus.getSelectedIndex() == 0) {
            String query = "select p.idProductos, p.desc_pro, p.modelo, p.serie, p.marca, pa.cant, p.cant_min, pa.precio, p.estado, c.desc_clas, "
                    + "u.desc_und, p.grado from producto_almacen as pa inner join productos as p on pa.idProductos=p.idProductos "
                    + "inner join clasificacion as c on p.id_clas=c.id_clas inner join und_medida as u on "
                    + "p.idUnd_Medida=u.idUnd_Medida where pa.idAlmacen = '" + txt_ida.getText() + "' order by p.desc_pro asc";
            pro.mostrar_productos(query);
        } else if (cbx_bus.getSelectedIndex() == 1) {
            String query = "select p.idProductos, p.desc_pro, p.modelo, p.serie, p.marca, pa.cant, p.cant_min, pa.precio, p.estado, c.desc_clas, "
                    + "u.desc_und, p.grado from producto_almacen as pa inner join productos as p on pa.idProductos=p.idProductos "
                    + "inner join clasificacion as c on p.id_clas=c.id_clas inner join und_medida as u on "
                    + "p.idUnd_Medida=u.idUnd_Medida where pa.idAlmacen = '" + txt_ida.getText() + "' and pa.cant > p.cant_min order by p.desc_pro asc";
            pro.mostrar_productos(query);
        } else if (cbx_bus.getSelectedIndex() == 2) {
            String query = "select p.idProductos, p.desc_pro, p.modelo, p.serie, p.marca, pa.cant, p.cant_min, pa.precio, p.estado, c.desc_clas, "
                    + "u.desc_und, p.grado from producto_almacen as pa inner join productos as p on pa.idProductos=p.idProductos "
                    + "inner join clasificacion as c on p.id_clas=c.id_clas inner join und_medida as u on "
                    + "p.idUnd_Medida=u.idUnd_Medida where pa.idAlmacen = '" + txt_ida.getText() + "' and pa.cant < p.cant_min and pa.cant > 0 order by p.desc_pro asc";
            pro.mostrar_productos(query);
        } else {
            String query = "select p.idProductos, p.desc_pro, p.modelo, p.serie, p.marca, pa.cant, p.cant_min, pa.precio, p.estado, c.desc_clas, "
                    + "u.desc_und, p.grado from producto_almacen as pa inner join productos as p on pa.idProductos=p.idProductos "
                    + "inner join clasificacion as c on p.id_clas=c.id_clas inner join und_medida as u on "
                    + "p.idUnd_Medida=u.idUnd_Medida where pa.idAlmacen = '" + txt_ida.getText() + "' and pa.cant = 0 order by p.desc_pro asc";
            pro.mostrar_productos(query);
        }
        //falta demas oopciones del combo
    }//GEN-LAST:event_cbx_busKeyPressed

    private void txt_busKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_busKeyTyped

    }//GEN-LAST:event_txt_busKeyTyped

    private int tot_reg() {
        int tot = 0;
        for (int j = 0; j < t_productos.getRowCount(); j++) {
            tot++;
        }
        return tot;
    }

    private void txt_busKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_busKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String texto = txt_bus.getText();
            String query = "select p.idProductos, p.desc_pro, p.modelo, p.serie, p.marca, pa.cant, p.cant_min, pa.precio, p.estado, c.desc_clas, "
                    + "u.desc_und, p.grado from producto_almacen as pa inner join productos as p on pa.idProductos=p.idProductos "
                    + "inner join clasificacion as c on p.id_clas=c.id_clas inner join und_medida as u on "
                    + "p.idUnd_Medida=u.idUnd_Medida where pa.idAlmacen = '" + txt_ida.getText() + "' and (p.desc_pro like '%" + texto + "%' or p.modelo "
                    + "like '%" + texto + "%' or p.serie like '%" + texto + "%' or p.marca like '%" + texto + "%')  order by p.desc_pro asc, p.modelo asc";
            pro.mostrar_productos(query);
            jLabel3.setText("" + tot_reg());
        }
    }//GEN-LAST:event_txt_busKeyPressed

    private void t_productosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_t_productosMouseClicked
        if (evt.getClickCount() == 2) {
            txt_bus.setText("");
            txt_bus.requestFocus();
            i = t_productos.getSelectedRow();
            btn_kar.setEnabled(true);
            if (funcion.equals("solicitar")) {
                Object fila[] = new Object[5];
                fila[0] = t_productos.getValueAt(i, 0);                                         //COD PRO
                fila[1] = t_productos.getValueAt(i, 1);                                         // DESCRIPCION
                fila[2] = t_productos.getValueAt(i, 2);                                         // MARCA
                fila[3] = t_productos.getValueAt(i, 6);                                         // CANTIDAD
                fila[4] = t_productos.getValueAt(i, 4);                                         // UND MED

                frm_reg_solicitud pedido = null;
                pro.setId_pro((int) t_productos.getValueAt(i, 0));
                Integer filas_tabla = pedido.t_solicitud.getRowCount();
                Integer copiado = 0;
                if (filas_tabla > 0) {
                    for (int x = 0; x < filas_tabla; x++) {
                        Integer id_pro_tabla;
                        id_pro_tabla = Integer.parseInt(pedido.t_solicitud.getValueAt(x, 0).toString());
                        if (id_pro_tabla == pro.getId_pro()) {
                            copiado++;
                        }
                    }
                    if (copiado == 0) {
                        pedido.detalle.addRow(fila);
                        pedido.txt_id_pro.requestFocus();
                        pedido.btn_reg.setEnabled(true);
                        funcion = "material_almacen";
                        this.dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Se esta escogiendo un producto ya existente");
                    }
                } else {
                    pedido.detalle.addRow(fila);
                    pedido.txt_id_pro.requestFocus();
                    pedido.btn_reg.setEnabled(true);
                    funcion = "material_almacen";
                    this.dispose();
                }
            }
            //venta
            if (funcion.equals("venta")) {
                Object fila[] = new Object[6];
                fila[0] = t_productos.getValueAt(i, 0);                                         //COD PRO
                fila[1] = t_productos.getValueAt(i, 1);                                         // DESCRIPCION
                fila[2] = t_productos.getValueAt(i, 2);                                         // MARCA
                fila[3] = "1.00";                                                               // CANTIDAD
                fila[4] = t_productos.getValueAt(i, 4);                                         // UND MED
                fila[5] = t_productos.getValueAt(i, 5);                                         // precio
                frm_reg_venta venta = null;
                int prod = (int) t_productos.getValueAt(i, 0);
                Integer filas_tabla = frm_reg_venta.t_detalle.getRowCount();
                Integer copiado = 0;
                if (filas_tabla > 0) {
                    for (int x = 0; x < filas_tabla; x++) {
                        Integer id_pro_tabla;
                        id_pro_tabla = Integer.parseInt(venta.t_detalle.getValueAt(x, 0).toString());
                        if (id_pro_tabla == prod) {
                            copiado++;
                        }
                    }
                    if (copiado == 0) {
                        frm_reg_venta.detalle.addRow(fila);
                        frm_reg_venta.t_detalle.setModel(frm_reg_venta.detalle);
                        frm_reg_venta.txt_id.requestFocus();
                        frm_reg_venta.subtotal();
                        frm_reg_venta.total();
                        funcion = "material_almacen";
                        this.dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Se esta escogiendo un producto ya existente");
                    }
                } else {
                    frm_reg_venta.detalle.addRow(fila);
                    frm_reg_venta.t_detalle.setModel(frm_reg_venta.detalle);
                    frm_reg_venta.txt_id.requestFocus();
                    frm_reg_venta.subtotal();
                    frm_reg_venta.total();
                    funcion = "material_almacen";
                    this.dispose();
                }
            }

            //traslado
            if (funcion.equals("traslado")) {
                Object fila[] = new Object[5];
                fila[0] = t_productos.getValueAt(i, 0);                                         //COD PRO
                fila[1] = t_productos.getValueAt(i, 1);                                         // DESCRIPCION
                fila[2] = t_productos.getValueAt(i, 2);                                         // MARCA
                fila[3] = "1.00";                                                               // CANTIDAD
                fila[4] = t_productos.getValueAt(i, 4);                                         // UND MED

                frm_reg_traslado_almacen traslado = null;
                int prod = (int) t_productos.getValueAt(i, 0);
                Integer filas_tabla = frm_reg_traslado_almacen.t_detalle.getRowCount();
                Integer copiado = 0;
                if (filas_tabla > 0) {
                    for (int x = 0; x < filas_tabla; x++) {
                        Integer id_pro_tabla;
                        id_pro_tabla = Integer.parseInt(traslado.t_detalle.getValueAt(x, 0).toString());
                        if (id_pro_tabla == prod) {
                            copiado++;
                        }
                    }
                    if (copiado == 0) {
                        frm_reg_traslado_almacen.detalle.addRow(fila);
                        frm_reg_traslado_almacen.t_detalle.setModel(frm_reg_traslado_almacen.detalle);
                        frm_reg_traslado_almacen.btn_reg.setEnabled(true);
                        funcion = "material_almacen";
                        this.dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Se esta escogiendo un producto ya existente");
                    }
                } else {
                    frm_reg_traslado_almacen.detalle.addRow(fila);
                    frm_reg_traslado_almacen.t_detalle.setModel(frm_reg_traslado_almacen.detalle);
                    frm_reg_traslado_almacen.btn_reg.setEnabled(true);
                    funcion = "material_almacen";
                    this.dispose();
                }
            }
        }
    }//GEN-LAST:event_t_productosMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        frm_historial_producto historia = new frm_historial_producto();
        String idpro = t_productos.getValueAt(i, 0).toString();
        String idalm = txt_ida.getText();
        
        historia.txt_idpo.setText(t_productos.getValueAt(i, 0).toString());
        historia.txt_desc.setText(t_productos.getValueAt(i, 1).toString());
        historia.txt_marca.setText(t_productos.getValueAt(i, 2).toString());
        historia.txt_cant.setText(ven.formato_numero(Double.parseDouble(t_productos.getValueAt(i, 3).toString())));
        historia.lbl_und.setText(t_productos.getValueAt(i, 4).toString());
        historia.txt_precio.setText(ven.formato_numero(Double.parseDouble(t_productos.getValueAt(i, 5).toString())));
        
        //cargar ingresos
        DefaultTableModel model_ingreso;
        model_ingreso = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int fila, int columna) {
                return false;
            }
        };
        model_ingreso.addColumn("COD");
        model_ingreso.addColumn("FECHA");
        model_ingreso.addColumn("MOTIVO");
        model_ingreso.addColumn("RUC");
        model_ingreso.addColumn("RAZON SOCIAL - NOMBRE");
        model_ingreso.addColumn("TIPO DOC.");
        model_ingreso.addColumn("SERIE - NRO");
        model_ingreso.addColumn("CANT");
        model_ingreso.addColumn("PRE. UNI.");
        model_ingreso.addColumn("SUB. TOTAL.");
        Object[] fila_ing = new Object[10];
        try {
            Statement st = con.conexion();
            String ver_ing = "select k.idKardex, k.fecha, tm.nombre as tipomov, k.doc_nro, k.nombre as raz_soc, td.desc_tipd, k.serie, k.numero, k.cant_ing, k.pre_uni_ing from "
                    + "kardex as k inner join tipo_movimiento as tm on k.idtipo_movimiento = tm.idtipo_movimiento inner join tipo_doc as td on k.idtipo_doc = td.idtipo_doc"
                    + " where k.idProductos = '" + idpro + "' and k.idAlmacen = '" + idalm + "' and k.cant_sal = '0' and k.pre_uni_sal = '0' order by idKardex desc";
            ResultSet rs = con.consulta(st, ver_ing);
            while (rs.next()) {
                fila_ing[0] = rs.getString("idKardex");
                fila_ing[1] = ven.fechaformateada(rs.getString("fecha"));
                fila_ing[2] = rs.getString("tipomov");
                fila_ing[3] = rs.getString("doc_nro");
                fila_ing[4] = rs.getString("raz_soc");
                fila_ing[5] = rs.getString("desc_tipd");
                fila_ing[6] = rs.getString("serie") + " - " + rs.getString("numero");
                fila_ing[7] = ven.formato_numero(rs.getDouble("cant_ing"));
                fila_ing[8] = ven.formato_numero(rs.getDouble("pre_uni_ing"));
                fila_ing[9] = ven.formato_numero(rs.getDouble("cant_ing") * rs.getDouble("pre_uni_ing"));
                model_ingreso.addRow(fila_ing);
            }
            con.cerrar(rs);
            con.cerrar(st);
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
        historia.t_ingresos.setModel(model_ingreso);
        historia.t_ingresos.getColumnModel().getColumn(0).setPreferredWidth(45);
        historia.t_ingresos.getColumnModel().getColumn(1).setPreferredWidth(70);
        historia.t_ingresos.getColumnModel().getColumn(2).setPreferredWidth(150);
        historia.t_ingresos.getColumnModel().getColumn(3).setPreferredWidth(80);
        historia.t_ingresos.getColumnModel().getColumn(4).setPreferredWidth(300);
        historia.t_ingresos.getColumnModel().getColumn(5).setPreferredWidth(100);
        historia.t_ingresos.getColumnModel().getColumn(6).setPreferredWidth(90);
        historia.t_ingresos.getColumnModel().getColumn(7).setPreferredWidth(60);
        historia.t_ingresos.getColumnModel().getColumn(8).setPreferredWidth(60);
        historia.t_ingresos.getColumnModel().getColumn(9).setPreferredWidth(60);
        ven.derecha_celda(historia.t_ingresos, 0);
        ven.centrar_celda(historia.t_ingresos, 1);
        ven.centrar_celda(historia.t_ingresos, 2);
        ven.centrar_celda(historia.t_ingresos, 3);
        ven.centrar_celda(historia.t_ingresos, 5);
        ven.centrar_celda(historia.t_ingresos, 6);
        ven.derecha_celda(historia.t_ingresos, 7);
        ven.derecha_celda(historia.t_ingresos, 8);
        ven.derecha_celda(historia.t_ingresos, 9);
        
        //cargar salidas
        DefaultTableModel model_salida;
        model_salida = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int fila, int columna) {
                return false;
            }
        };
        model_salida.addColumn("COD");
        model_salida.addColumn("FECHA");
        model_salida.addColumn("MOTIVO");
        model_salida.addColumn("RUC");
        model_salida.addColumn("RAZON SOCIAL - NOMBRE");
        model_salida.addColumn("TIPO DOC.");
        model_salida.addColumn("SERIE - NRO");
        model_salida.addColumn("CANT");
        model_salida.addColumn("PRE. UNI.");
        model_salida.addColumn("SUB. TOTAL.");
        Object[] fila_sal = new Object[10];
        try {
            Statement st = con.conexion();
            String ver_ing = "select k.idKardex, k.fecha, tm.nombre as tipomov, k.doc_nro, k.nombre as raz_soc, td.desc_tipd, k.serie, k.numero, k.cant_sal, k.pre_uni_sal from "
                    + "kardex as k inner join tipo_movimiento as tm on k.idtipo_movimiento = tm.idtipo_movimiento inner join tipo_doc as td on k.idtipo_doc = td.idtipo_doc "
                    + "where k.idProductos = '" + idpro + "' and k.idAlmacen = '" + idalm + "' and k.cant_ing = '0' and k.pre_uni_ing = '0' order by idKardex desc";
            ResultSet rs = con.consulta(st, ver_ing);
            while (rs.next()) {
                fila_sal[0] = rs.getString("idKardex");
                fila_sal[1] = ven.fechaformateada(rs.getString("fecha"));
                fila_sal[2] = rs.getString("tipomov");
                fila_sal[3] = rs.getString("doc_nro");
                fila_sal[4] = rs.getString("raz_soc");
                fila_sal[5] = rs.getString("desc_tipd");
                fila_sal[6] = rs.getString("serie") + " - " + rs.getString("numero");
                fila_sal[7] = ven.formato_numero(rs.getDouble("cant_sal"));
                fila_sal[8] = ven.formato_numero(rs.getDouble("pre_uni_sal"));
                fila_sal[9] = ven.formato_numero(rs.getDouble("cant_sal") * rs.getDouble("pre_uni_sal"));
                model_salida.addRow(fila_sal);
            }
            con.cerrar(rs);
            con.cerrar(st);
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
        historia.t_salidas.setModel(model_salida);
        historia.t_salidas.getColumnModel().getColumn(0).setPreferredWidth(45);
        historia.t_salidas.getColumnModel().getColumn(1).setPreferredWidth(70);
        historia.t_salidas.getColumnModel().getColumn(2).setPreferredWidth(150);
        historia.t_salidas.getColumnModel().getColumn(3).setPreferredWidth(80);
        historia.t_salidas.getColumnModel().getColumn(4).setPreferredWidth(300);
        historia.t_salidas.getColumnModel().getColumn(5).setPreferredWidth(100);
        historia.t_salidas.getColumnModel().getColumn(6).setPreferredWidth(90);
        historia.t_salidas.getColumnModel().getColumn(7).setPreferredWidth(60);
        historia.t_salidas.getColumnModel().getColumn(8).setPreferredWidth(60);
        historia.t_salidas.getColumnModel().getColumn(9).setPreferredWidth(60);
        ven.derecha_celda(historia.t_salidas, 0);
        ven.centrar_celda(historia.t_salidas, 1);
        ven.centrar_celda(historia.t_salidas, 2);
        ven.centrar_celda(historia.t_salidas, 3);
        ven.centrar_celda(historia.t_salidas, 5);
        ven.centrar_celda(historia.t_salidas, 6);
        ven.derecha_celda(historia.t_salidas, 7);
        ven.derecha_celda(historia.t_salidas, 8);
        ven.derecha_celda(historia.t_salidas, 9);
        ven.llamar_ventana(historia);
        this.dispose();

    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_cer;
    private javax.swing.JButton btn_kar;
    private javax.swing.JComboBox cbx_bus;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTable t_productos;
    private javax.swing.JTextField txt_bus;
    public static javax.swing.JTextField txt_ida;
    public static javax.swing.JTextField txt_noma;
    // End of variables declaration//GEN-END:variables
}
