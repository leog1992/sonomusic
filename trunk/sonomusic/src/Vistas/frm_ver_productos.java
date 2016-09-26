/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import Clases.Cl_Clasificacion;
import Clases.Cl_Conectar;
import Clases.Cl_Medida;
import Clases.Cl_Moneda;
import Clases.Cl_Productos;
import Clases.Cl_Varios;
import Clases.Clase_CellEditor;
import Clases.Clase_CellRender;
import Clases.ImagenURL;
import Clases.render_productos;
import Forms.frm_reg_ingreso;
import Forms.frm_reg_cotizacion;
import Forms.frm_reg_ofertas;
import Forms.frm_reg_productos;
import Forms.frm_rpt_fechas;
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import static sonomusic.frm_menu.usu;

/**
 *
 * @author pc
 */
public class frm_ver_productos extends javax.swing.JInternalFrame {

    Cl_Conectar con = new Cl_Conectar();
    Cl_Clasificacion cla = new Cl_Clasificacion();
    Cl_Medida med = new Cl_Medida();
    Cl_Productos pro = new Cl_Productos();
    Cl_Varios ven = new Cl_Varios();
    Cl_Moneda mon = new Cl_Moneda();
    public static String ventana = "productos";
    DecimalFormatSymbols simbolo = new DecimalFormatSymbols(Locale.US);
    DecimalFormat formato = new DecimalFormat("####0.00", simbolo);
    Integer i;
    public static DefaultTableModel mostrar;

    public frm_ver_productos() {
        initComponents();
        String query = "select p.idProductos, p.desc_pro, p.marca, p.modelo, p.serie, p.grado, p.precio_venta, "
                + "c.desc_clas, u.desc_und, p.cant_actual, p.cant_min, p.estado from productos as p inner join "
                + "und_medida as u on p.idUnd_medida = u.idUnd_medida inner join clasificacion as c on "
                + "p.id_clas = c.id_clas  order by p.desc_pro asc, p.modelo asc limit 0";
        ver_productos(query);
        t_productos.setDefaultRenderer(Object.class, new render_productos());

        String clas = "select * from clasificacion order by id_clas asc";
        ver_clasificacion(clas);
    }

    private void ver_clasificacion(String query) {
        try {
            Statement st = con.conexion();
            ResultSet rs = con.consulta(st, query);

            while (rs.next()) {
                String fila;
                fila = rs.getString("desc_clas");
                cbx_cla.addItem(fila);
            }
            con.cerrar(st);
            con.cerrar(rs);
        } catch (SQLException e) {
            System.out.print(e);
        }

    }

    private void ver_productos_marca(String query) {
        try {
            mostrar = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int fila, int columna) {
                    return false;
                }
            };
            Statement st = con.conexion();
            ResultSet rs = con.consulta(st, query);
            //Establecer como cabezeras el nombre de las colimnas
            mostrar.addColumn("Id");
            mostrar.addColumn("Descripcion");//descripcion modelo serie
            mostrar.addColumn("Marca");
            mostrar.addColumn("Precio");
            mostrar.addColumn("Clasificacion");
            mostrar.addColumn("Cant. Actual");
            mostrar.addColumn("Cant. minima");
            mostrar.addColumn("Und. Medida");
            mostrar.addColumn("Grado");
            mostrar.addColumn("Estado");

            //Creando las filas para el JTable
            while (rs.next()) {
                Object[] fila = new Object[11];
                fila[0] = rs.getObject("idProductos");
                fila[1] = rs.getObject("desc_pro") + " - " + rs.getObject("modelo") + " - " + rs.getObject("serie");
                fila[2] = rs.getObject("marca");
                fila[3] = rs.getObject("precio_venta");
                fila[4] = rs.getObject("desc_clas");
                fila[5] = rs.getObject("cant_actual");
                fila[6] = rs.getObject("cant_min");
                fila[7] = rs.getObject("desc_und");
                fila[8] = rs.getObject("grado");
                if (rs.getString("estado").equals("1")) {
                    if (rs.getDouble("cant_actual") > rs.getDouble("cant_min")) {
                        fila[9] = "NORMAL";
                    }
                    if (rs.getDouble("cant_actual") <= rs.getDouble("cant_min")) {
                        fila[9] = "POR TERMINAR";
                    }
                    if (rs.getDouble("cant_actual") <= 0) {
                        fila[9] = "NO DISPONIBLE";
                    }
                } else {
                    fila[9] = "-";
                }

                mostrar.addRow(fila);
            }
            con.cerrar(st);
            con.cerrar(rs);
            t_productos.setModel(mostrar);
            t_productos.getColumnModel().getColumn(0).setPreferredWidth(10);
            t_productos.getColumnModel().getColumn(1).setPreferredWidth(390);
            t_productos.getColumnModel().getColumn(2).setPreferredWidth(50);
            t_productos.getColumnModel().getColumn(3).setPreferredWidth(20);
            t_productos.getColumnModel().getColumn(4).setPreferredWidth(30);
            t_productos.getColumnModel().getColumn(5).setPreferredWidth(30);
            t_productos.getColumnModel().getColumn(6).setPreferredWidth(40);
            t_productos.getColumnModel().getColumn(7).setPreferredWidth(40);
            t_productos.getColumnModel().getColumn(8).setPreferredWidth(40);
            t_productos.getColumnModel().getColumn(9).setPreferredWidth(40);
            t_productos.setDefaultRenderer(Object.class, new render_productos());
        } catch (SQLException e) {
            System.out.print(e);
        }
    }

    private void ver_productos(String query) {
        try {
            mostrar = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int fila, int columna) {
                    return false;
                }
            };
            Statement st = con.conexion();
            ResultSet rs = con.consulta(st, query);

            //Establecer como cabezeras el nombre de las colimnas
            mostrar.addColumn("Id");
            mostrar.addColumn("Descripcion");//descripcion modelo serie
            mostrar.addColumn("Marca");
            mostrar.addColumn("Precio");
            mostrar.addColumn("Clasificacion");
            mostrar.addColumn("Cant. Actual");
            mostrar.addColumn("Cant. minima");
            mostrar.addColumn("Und. Medida");
            mostrar.addColumn("Grado");
            mostrar.addColumn("Estado");

            //Creando las filas para el JTable
            while (rs.next()) {
                Object[] fila = new Object[10];
                fila[0] = rs.getObject("idProductos");
                fila[1] = rs.getObject("desc_pro") + " - " + rs.getObject("modelo") + " - " + rs.getObject("serie");
                fila[2] = rs.getObject("marca");
                fila[3] = rs.getObject("precio_venta");
                fila[4] = rs.getObject("desc_clas");
                fila[5] = rs.getObject("cant_actual");
                fila[6] = rs.getObject("cant_min");
                fila[7] = rs.getObject("desc_und");
                fila[8] = rs.getObject("grado");
                if (rs.getString("estado").equals("1")) {
                    if (rs.getDouble("cant_actual") > rs.getDouble("cant_min")) {
                        fila[9] = "NORMAL";
                    }
                    if (rs.getDouble("cant_actual") <= rs.getDouble("cant_min")) {
                        fila[9] = "POR TERMINAR";
                    }
                    if (rs.getDouble("cant_actual") <= 0) {
                        fila[9] = "NO DISPONIBLE";
                    }
                } else {
                    fila[9] = "-";
                }

                mostrar.addRow(fila);
            }
            con.cerrar(st);
            con.cerrar(rs);
            this.t_productos.setModel(mostrar);
            t_productos.getColumnModel().getColumn(0).setPreferredWidth(10);
            t_productos.getColumnModel().getColumn(1).setPreferredWidth(390);
            t_productos.getColumnModel().getColumn(2).setPreferredWidth(50);
            t_productos.getColumnModel().getColumn(3).setPreferredWidth(20);
            t_productos.getColumnModel().getColumn(4).setPreferredWidth(30);
            t_productos.getColumnModel().getColumn(5).setPreferredWidth(30);
            t_productos.getColumnModel().getColumn(6).setPreferredWidth(40);
            t_productos.getColumnModel().getColumn(7).setPreferredWidth(40);
            t_productos.getColumnModel().getColumn(8).setPreferredWidth(40);
            t_productos.getColumnModel().getColumn(9).setPreferredWidth(40);
            t_productos.setDefaultRenderer(Object.class, new render_productos());
            mostrar.fireTableDataChanged();

        } catch (SQLException e) {
            System.out.print(e);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton3 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txt_bus = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        t_productos = new javax.swing.JTable();
        btn_reg = new javax.swing.JButton();
        btn_mod = new javax.swing.JButton();
        btn_eli = new javax.swing.JButton();
        cbx_cla = new javax.swing.JComboBox();

        setBackground(new java.awt.Color(254, 254, 254));
        setClosable(true);
        setTitle("Ver Productos");

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/cancel.png"))); // NOI18N
        jButton3.setText("Cerrar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel1.setForeground(new java.awt.Color(212, 2, 2));
        jLabel1.setText("Buscar:");

        txt_bus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_busKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_busKeyTyped(evt);
            }
        });

        jScrollPane1.setBackground(new java.awt.Color(254, 254, 254));
        jScrollPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jScrollPane1MouseClicked(evt);
            }
        });

        t_productos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null}
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
            public void keyReleased(java.awt.event.KeyEvent evt) {
                t_productosKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(t_productos);
        if (t_productos.getColumnModel().getColumnCount() > 0) {
            t_productos.getColumnModel().getColumn(0).setPreferredWidth(20);
            t_productos.getColumnModel().getColumn(1).setPreferredWidth(150);
            t_productos.getColumnModel().getColumn(2).setPreferredWidth(50);
            t_productos.getColumnModel().getColumn(3).setPreferredWidth(20);
            t_productos.getColumnModel().getColumn(4).setPreferredWidth(30);
            t_productos.getColumnModel().getColumn(5).setPreferredWidth(40);
            t_productos.getColumnModel().getColumn(6).setPreferredWidth(40);
        }

        btn_reg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/application_add.png"))); // NOI18N
        btn_reg.setText("Registrar");
        btn_reg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_regActionPerformed(evt);
            }
        });

        btn_mod.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/application_edit.png"))); // NOI18N
        btn_mod.setText("Modifcar");
        btn_mod.setEnabled(false);
        btn_mod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_modActionPerformed(evt);
            }
        });

        btn_eli.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/cross.png"))); // NOI18N
        btn_eli.setText("Eliminar");
        btn_eli.setEnabled(false);
        btn_eli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_eliActionPerformed(evt);
            }
        });

        cbx_cla.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "TODOS" }));
        cbx_cla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbx_claActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 974, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_bus, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cbx_cla, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_reg)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_mod))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(btn_eli)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton3)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_bus, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_reg, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_mod, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbx_cla, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 402, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_eli, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_regActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_regActionPerformed
        if (usu.getPer_reg_producto().equals("1")) {
            frm_reg_productos productos = new frm_reg_productos();
            productos.win = "reg";
            productos.subventana = "prod_compra";

            ven.llamar_ventana(productos);
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(null, "Ud No tiene permisos");
        }
    }//GEN-LAST:event_btn_regActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        ventana = "productos";
        this.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void txt_busKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_busKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String bus = txt_bus.getText();
            String query = "select p.idProductos , p.desc_pro, p.marca, p.modelo, p.serie, p.grado, p.costo_compra, p.precio_venta, c.desc_clas, u.desc_und, p.cant_actual,p.cant_min, p.estado "
                    + "from productos as p inner join und_medida as u on p.idUnd_medida=u.idUnd_medida inner join clasificacion as c on p.id_clas=c.id_clas "
                    //+ "WHERE MATCH (p.desc_pro, p.modelo, p.serie, p.marca) AGAINST ('%" + bus + "%') "
                    + "where p.desc_pro like '%" + bus + "%' "
                    + "or p.modelo like '%" + bus + "%' or p.serie like '%" + bus + "%' or p.marca like '%" + bus + "%'"
                    + " order by p.desc_pro asc, p.modelo asc";
            ver_productos(query);
        }
    }//GEN-LAST:event_txt_busKeyPressed

    private void jScrollPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane1MouseClicked

    }//GEN-LAST:event_jScrollPane1MouseClicked

    private void btn_modActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_modActionPerformed
        if (usu.getPer_mod_producto().equals("1")) {
            frm_reg_productos prod = new frm_reg_productos();
            try {
                int id = Integer.parseInt(t_productos.getValueAt(i, 0).toString());
                String query = "select * from productos where idProductos = '" + id + "'";
                Statement st = con.conexion();
                ResultSet rs = con.consulta(st, query);
                if (rs.next()) {
                    prod.txt_cod.setText(rs.getString("idProductos"));
                    prod.txt_cod.setEditable(false);
                    prod.txt_des.setText(rs.getString("desc_pro"));
                    prod.txt_des.setEditable(true);
                    prod.txt_des.requestFocus();
                    prod.txt_mar.setText(rs.getString("marca"));
                    prod.txt_mar.setEditable(true);
                    prod.txt_mod.setText(rs.getString("modelo"));
                    prod.txt_mod.setEditable(true);
                    prod.txt_ser.setText(rs.getString("serie"));
                    prod.txt_ser.setEditable(true);
                    prod.cbo_gra.setSelectedItem(rs.getString("grado"));
                    prod.cbo_gra.setEnabled(true);
                    prod.cbx_und.setSelectedIndex(rs.getInt("idUnd_medida") - 1);
                    prod.cbx_und.setEnabled(true);
                    prod.txt_pcom.setText(rs.getString("costo_compra"));
                    prod.txt_pcom.setEditable(true);
                    prod.txt_pven.setText(rs.getString("precio_venta"));
                    prod.txt_pven.setEditable(true);
                    prod.cbx_cla.setSelectedIndex(rs.getInt("id_clas") - 1);
                    prod.cbx_cla.setEnabled(true);
                    prod.txt_cantm.setText(rs.getString("cant_min"));
                    prod.txt_cantm.setEditable(true);
                    prod.txt_com.setText(rs.getString("comision"));
                    prod.txt_com.setEditable(true);
                    prod.btn_reg.setEnabled(true);

                    //cargar imagen 
                    ImagenURL Imagen = new ImagenURL();
                    String imgname = rs.getString("nom_img");
                    prod.txt_img.setText(imgname);
                    prod.pro.setImg(imgname);
                    String url;
                    url = "http://conmetal.pe/images/productos/" + imgname;
                    Imagen.setUrl(url);
                    prod.jp_img.add(Imagen);
                    prod.jp_img.repaint();
                    //fin de carga
                }

                prod.win = "mod";
                prod.id = id;
                ven.llamar_ventana(prod);
                this.dispose();

            } catch (SQLException ex) {
                System.out.print(ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Ud No tiene permisos");
        }
    }//GEN-LAST:event_btn_modActionPerformed

    private void t_productosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_t_productosMousePressed
        i = t_productos.getSelectedRow();
        btn_eli.setEnabled(true);
        btn_mod.setEnabled(true);
    }//GEN-LAST:event_t_productosMousePressed

    private void btn_eliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_eliActionPerformed
        if (usu.getPer_eli_producto().equals("1")) {
            //eliminar
            int confirmado = JOptionPane.showConfirmDialog(null, "¿Confirma eliminar el producto?", "Seguridad", JOptionPane.WARNING_MESSAGE);

            if (JOptionPane.OK_OPTION == confirmado) {
                pro.setId_pro((int) t_productos.getValueAt(i, 0));
                try {
                    Statement st = con.conexion();
                    String query = "delete from productos where idProductos ='" + pro.getId_pro() + "'";
                    con.actualiza(st, query);
                    con.cerrar(st);
                    String query1 = "select p.idProductos, p.desc_pro, p.marca, p.modelo, p.serie, p.grado, p.precio_venta, c.desc_clas, u.desc_und, p.cant_actual, p.cant_min, p.estado"
                            + " from productos as p inner join und_medida as u on p.idUnd_medida = u.idUnd_medida inner join clasificacion as c on p.id_clas = c.id_clas  order by p.desc_pro asc";
                    ver_productos(query1);
                    btn_eli.setEnabled(false);
                    btn_mod.setEnabled(false);
                } catch (Exception ex) {
                    System.out.print(ex + " " + ex.getLocalizedMessage() + " " + ex.getCause());
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Ud No tiene permisos");
        }
    }//GEN-LAST:event_btn_eliActionPerformed

    private void cbx_claActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbx_claActionPerformed
        if (cbx_cla.getSelectedIndex() == 0) {
            String query = "select p.idProductos , p.desc_pro, p.marca, p.modelo, p.serie, p.grado, p.costo_compra, p.precio_venta, c.desc_clas, u.desc_und, p.cant_actual,p.cant_min, p.estado "
                    + "from productos as p inner join und_medida as u on p.idUnd_medida=u.idUnd_medida inner join clasificacion as c on p.id_clas=c.id_clas  order by p.desc_pro asc";
            ver_productos(query);
        } else {
            Integer idcla = cbx_cla.getSelectedIndex();
            String query = "select p.idProductos , p.desc_pro, p.marca, p.modelo, p.serie, p.grado, p.costo_compra, p.precio_venta, c.desc_clas, u.desc_und, p.cant_actual,p.cant_min, p.estado "
                    + "from productos as p inner join und_medida as u on p.idUnd_medida=u.idUnd_medida inner join clasificacion as c on p.id_clas=c.id_clas  where p.id_clas= '" + idcla + "' order by p.desc_pro asc";
            ver_productos(query);

        }
    }//GEN-LAST:event_cbx_claActionPerformed

    private void t_productosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_t_productosKeyPressed

        int a = t_productos.getSelectedRow();
        Object[] dato = new Object[6];
        dato[0] = t_productos.getValueAt(a, 0);         //idproducto
        dato[1] = t_productos.getValueAt(a, 1);         //descripcion
        dato[2] = t_productos.getValueAt(a, 2);         //marca
        dato[3] = "1";                                  //cantidad
        dato[4] = t_productos.getValueAt(a, 7);         //und. med
        dato[5] = t_productos.getValueAt(a, 3);         //precio

        if (evt.getKeyCode() == KeyEvent.VK_C) {
            pro.setId_pro(Integer.parseInt(t_productos.getValueAt(i, 0).toString()));
            try {
                Statement st = con.conexion();
                String ver_cp = "select costo_compra, precio_venta from productos where idproductos = '"+pro.getId_pro()+"'";
                ResultSet rs = con.consulta(st, ver_cp);
                if (rs.next()) {
                    double costo, precio, utilidad;
                    costo = rs.getDouble("costo_compra");
                    precio = rs.getDouble("precio_venta");
                    utilidad = precio - costo;
                    JOptionPane.showMessageDialog(null, "PRODUCTO SELECCIONADO: " + t_productos.getValueAt(a, 1) + t_productos.getValueAt(a, 2) + "\nCOSTO DEL "
                            + "PRODUCTO: " + ven.formato_numero(costo) + "\nPRECIO DE VENTA: " + ven.formato_numero(precio) + "\nUTILIDAD: " + ven.formato_numero(utilidad));
                }
                con.cerrar(rs);
                con.cerrar(st);
            } catch (SQLException | HeadlessException e) {
                System.out.println(e.getLocalizedMessage());
            }
        }
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            int i = t_productos.getSelectedRow();
            System.out.println(ventana + "\n");
            System.out.println(t_productos.getValueAt(i, 0).toString());
            pro.setId_pro(Integer.parseInt(t_productos.getValueAt(i, 0).toString()));
//            try {
            if (ventana.equals("cotizacion")) {

                frm_reg_cotizacion coti = null;
                int tabla = coti.t_productos.getRowCount();                   //obtener la cantidad de filas de la tabla cotizacion
                String id = t_productos.getValueAt(a, 0).toString();         //id del formulario ver_productos(formulario actual)
                String id_prod = "";
                int contar_repetidos = 0;
                if (tabla > 0) {            //verifica si existen registros
                    for (int j = 0; j < tabla; j++) {           //recorremos la tabla reg_cotizaciones
                        id_prod = coti.t_productos.getValueAt(j, 0).toString();// captura el id reg_cotizaciones

                        if (id_prod.equals(id)) {
                            contar_repetidos++;
                        }
                    }
                    if (contar_repetidos == 0) {
                        coti.detalle.addRow(dato);
                        coti.t_productos.setModel(frm_reg_cotizacion.detalle);
                        coti.subtotal();
                        coti.total();
                        coti.btn_reg.setEnabled(true);
                        coti.btn_reg.requestFocus();
                        this.dispose();
                    }

                } else {
                    coti.detalle.addRow(dato);
                    coti.t_productos.setModel(frm_reg_cotizacion.detalle);
                    coti.btn_reg.setEnabled(true);
                    coti.btn_reg.requestFocus();
                    this.dispose();
                }
            }

            if (ventana.equals("compra_prod")) {
                frm_reg_ingreso prod = null;
                int tabla = prod.t_detalle.getRowCount();                   //obtener la cantidad de filas
                String id = t_productos.getValueAt(a, 0).toString();         //id del formulario ver_productos
                String id_prod = "";
                int contar_repetidos = 0;
                Object[] fila_compra = new Object[6];
                fila_compra[0] = t_productos.getValueAt(a, 0);         //idproducto
                fila_compra[1] = t_productos.getValueAt(a, 2);         //descripcion
                fila_compra[2] = t_productos.getValueAt(a, 3);         //marca
                fila_compra[3] = "1";                                  //cantidad
                fila_compra[4] = t_productos.getValueAt(a, 8);         //und. med

                try {
                    Statement st1 = con.conexion();
                    String ver_cos = "select costo_compra from productos where idProductos = '" + id + "'";
                    ResultSet rs1 = con.consulta(st1, ver_cos);
                    if (rs1.next()) {
                        fila_compra[5] = rs1.getObject("costo_compra"); // Costo Compra
                    }
                    con.cerrar(rs1);
                    con.cerrar(st1);
                } catch (SQLException ex) {
                    System.out.print(ex);
                }

                if (tabla > 0) {            //verifica si existen registros
                    for (int j = 0; j < tabla; j++) {           //recorremos la tabla reg_compra
                        id_prod = prod.t_detalle.getValueAt(j, 0).toString();// captura el id reg_compra

                        if (id_prod.equals(id)) {
                            contar_repetidos++;
                        }
                    }

                    if (contar_repetidos == 0) {
                        prod.detalle.addRow(fila_compra);
                        prod.txt_sub.setText(formato.format(prod.subtotal()));
                        prod.txt_igv.setText(formato.format(prod.igv()));
                        prod.txt_tot.setText(formato.format(prod.total()));
                        prod.btn_reg.setEnabled(true);
                        frm_reg_ingreso.txt_idp.requestFocus();
                        this.dispose();
                    }

                } else {
                    prod.detalle.addRow(fila_compra);
                    prod.t_detalle.setModel(prod.detalle);
                    prod.txt_sub.setText(formato.format(prod.subtotal()));
                    prod.txt_igv.setText(formato.format(prod.igv()));
                    prod.txt_tot.setText(formato.format(prod.total()));
                    prod.btn_reg.setEnabled(true);
                    frm_reg_ingreso.txt_idp.setText(t_productos.getValueAt(a, 0).toString());
                    frm_reg_ingreso.txt_desp.setText(t_productos.getValueAt(a, 1).toString());
                    frm_reg_ingreso.txt_idp.requestFocus();
                    this.dispose();
                }
            }

            //ventana ver_ofertas
            if (ventana.equals("oferta")) {
                frm_reg_ofertas ofer = null;
                int tabla = frm_reg_ofertas.t_oferta.getRowCount();                   //obtener la cantidad de filas de oferta                                                                                                                                                   
                String id = t_productos.getValueAt(a, 0).toString();         //id del formulario ver_productos
                String id_prod = "";
                int contar_repetidos = 0;
                if (tabla > 0) {            //verifica si existen registros
                    for (int j = 0; j < tabla; j++) {           //recorremos la tabla reg_cotizaciones
                        id_prod = frm_reg_ofertas.t_oferta.getValueAt(j, 0).toString();// captura el id reg_oferta

                        if (id_prod.equals(id)) {
                            contar_repetidos++;
                        }
                    }

                    if (contar_repetidos == 0) {
                        ofer.modelo.addRow(dato);
                        ofer.t_oferta.setModel(frm_reg_ofertas.modelo);
                        ofer.btn_reg.setEnabled(true);
                        this.dispose();
                    }

                } else {
                    ofer.modelo.addRow(dato);
                    ofer.t_oferta.setModel(frm_reg_ofertas.modelo);
                    frm_reg_ofertas.txt_id_pro.setText(t_productos.getValueAt(a, 0).toString());
                    frm_reg_ofertas.txt_des_pro.setText(t_productos.getValueAt(a, 1).toString());
                    ofer.btn_reg.setEnabled(true);
                    this.dispose();
                }
            }

            if (ventana.equals("compra_productos")) {
                System.out.println(t_productos.getValueAt(a, 0).toString());
                frm_rpt_fechas fec_rpt;
                fec_rpt = new frm_rpt_fechas();
                fec_rpt.pro.setId_pro(pro.getId_pro());
                fec_rpt.rpt = "compra_producto";
                ven.llamar_ventana(fec_rpt);
                this.dispose();
            }
            ventana = "productos";

//            } catch (Exception ex) {
//                JOptionPane.showMessageDialog(null, ex + " en: " + ex.getLocalizedMessage());
//                System.out.println(ex);
//                System.out.print(ex);
//            }
        }

    }//GEN-LAST:event_t_productosKeyPressed

    private void t_productosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_t_productosMouseClicked

        int a = t_productos.getSelectedRow();
        Object[] dato = new Object[6];
        dato[0] = t_productos.getValueAt(a, 0);         //idproducto
        dato[1] = t_productos.getValueAt(a, 1);         //descripcion
        dato[2] = t_productos.getValueAt(a, 2);         //marca
        dato[3] = "1";                                  //cantidad
        dato[4] = t_productos.getValueAt(a, 7);         //und. med
        dato[5] = t_productos.getValueAt(a, 3);         //precio

        if (evt.getClickCount() == 2) {
            int i = t_productos.getSelectedRow();
            System.out.println(ventana + "\n");
            System.out.println(t_productos.getValueAt(i, 0).toString());
            pro.setId_pro(Integer.parseInt(t_productos.getValueAt(i, 0).toString()));
//            try {
            if (ventana.equals("cotizacion")) {

                frm_reg_cotizacion coti = null;
                int tabla = coti.t_productos.getRowCount();                   //obtener la cantidad de filas de la tabla cotizacion
                String id = t_productos.getValueAt(a, 0).toString();         //id del formulario ver_productos(formulario actual)
                String id_prod = "";
                int contar_repetidos = 0;
                if (tabla > 0) {            //verifica si existen registros
                    for (int j = 0; j < tabla; j++) {           //recorremos la tabla reg_cotizaciones
                        id_prod = coti.t_productos.getValueAt(j, 0).toString();// captura el id reg_cotizaciones

                        if (id_prod.equals(id)) {
                            contar_repetidos++;
                        }
                    }
                    if (contar_repetidos == 0) {
                        coti.detalle.addRow(dato);
                        coti.t_productos.setModel(frm_reg_cotizacion.detalle);
                        coti.subtotal();
                        coti.total();
                        coti.btn_reg.setEnabled(true);
                        coti.btn_reg.requestFocus();
                        this.dispose();
                    }

                } else {
                    coti.detalle.addRow(dato);
                    coti.t_productos.setModel(frm_reg_cotizacion.detalle);
                    coti.btn_reg.setEnabled(true);
                    coti.btn_reg.requestFocus();
                    this.dispose();
                }
            }

            if (ventana.equals("compra_prod")) {
                frm_reg_ingreso prod = null;
                int tabla = prod.t_detalle.getRowCount();                   //obtener la cantidad de filas
                String id = t_productos.getValueAt(a, 0).toString();         //id del formulario ver_productos
                String id_prod = "";
                int contar_repetidos = 0;
                Object[] fila_compra = new Object[6];
                fila_compra[0] = t_productos.getValueAt(a, 0);         //idproducto
                fila_compra[1] = t_productos.getValueAt(a, 1);         //descripcion
                fila_compra[2] = t_productos.getValueAt(a, 2);         //marca
                fila_compra[3] = "1";                                  //cantidad
                fila_compra[4] = t_productos.getValueAt(a, 7);         //und. med

                int id_moneda = prod.cbx_mon.getSelectedIndex();
                double costo_compra = 0;
                String fecha_compra = ven.fechabase(prod.txt_fec_com.getText());
                try {
                    Statement st1 = con.conexion();
                    String ver_cos = "select costo_compra from productos where idProductos = '" + id + "'";
                    ResultSet rs1 = con.consulta(st1, ver_cos);
                    if (rs1.next()) {
                        costo_compra = rs1.getDouble("costo_compra"); // Costo Compra
                    }
                    con.cerrar(rs1);
                    con.cerrar(st1);
                } catch (SQLException ex) {
                    System.out.print(ex);
                }
                if (id_moneda == 1) {
                    fila_compra[5] = ven.formato_numero(costo_compra);
                } else {
                    fila_compra[5] = ven.formato_numero(mon.cambio_compra_dolar(fecha_compra, id_moneda + 1, costo_compra));
                }

                if (tabla > 0) {            //verifica si existen registros
                    for (int j = 0; j < tabla; j++) {           //recorremos la tabla reg_compra
                        id_prod = prod.t_detalle.getValueAt(j, 0).toString();// captura el id reg_compra

                        if (id_prod.equals(id)) {
                            contar_repetidos++;
                        }
                    }

                    if (contar_repetidos == 0) {
                        prod.detalle.addRow(fila_compra);
                        prod.txt_sub.setText(formato.format(prod.subtotal()));
                        prod.txt_igv.setText(formato.format(prod.igv()));
                        prod.txt_tot.setText(formato.format(prod.total()));
                        prod.btn_reg.setEnabled(true);
                        frm_reg_ingreso.txt_idp.requestFocus();
                        this.dispose();
                    }

                } else {
                    prod.detalle.addRow(fila_compra);
                    prod.t_detalle.setModel(prod.detalle);
                    prod.txt_sub.setText(formato.format(prod.subtotal()));
                    prod.txt_igv.setText(formato.format(prod.igv()));
                    prod.txt_tot.setText(formato.format(prod.total()));
                    prod.btn_reg.setEnabled(true);
                    frm_reg_ingreso.txt_idp.requestFocus();
                    this.dispose();
                }
            }

            //ventana ver_ofertas
            if (ventana.equals("oferta")) {
                frm_reg_ofertas ofer = null;
                int tabla = frm_reg_ofertas.t_oferta.getRowCount();                   //obtener la cantidad de filas de oferta                                                                                                                                                   
                String id = t_productos.getValueAt(a, 0).toString();         //id del formulario ver_productos
                String id_prod = "";
                int contar_repetidos = 0;
                if (tabla > 0) {            //verifica si existen registros
                    for (int j = 0; j < tabla; j++) {           //recorremos la tabla reg_cotizaciones
                        id_prod = frm_reg_ofertas.t_oferta.getValueAt(j, 0).toString();// captura el id reg_oferta

                        if (id_prod.equals(id)) {
                            contar_repetidos++;
                        }
                    }

                    if (contar_repetidos == 0) {
                        ofer.modelo.addRow(dato);
                        ofer.t_oferta.setModel(frm_reg_ofertas.modelo);
                        ofer.btn_reg.setEnabled(true);
                        this.dispose();
                    }

                } else {
                    ofer.modelo.addRow(dato);
                    ofer.t_oferta.setModel(frm_reg_ofertas.modelo);
                    frm_reg_ofertas.txt_id_pro.setText(t_productos.getValueAt(a, 0).toString());
                    frm_reg_ofertas.txt_des_pro.setText(t_productos.getValueAt(a, 1).toString());
                    ofer.btn_reg.setEnabled(true);
                    this.dispose();
                }
            }

            if (ventana.equals("compra_productos")) {
                System.out.println(t_productos.getValueAt(a, 0).toString());
                frm_rpt_fechas fec_rpt;
                fec_rpt = new frm_rpt_fechas();
                fec_rpt.pro.setId_pro(pro.getId_pro());
                fec_rpt.rpt = "compra_producto";
                ven.llamar_ventana(fec_rpt);
                this.dispose();
            }
            ventana = "productos";

//            } catch (Exception ex) {
//                JOptionPane.showMessageDialog(null, ex + " en: " + ex.getLocalizedMessage());
//                System.out.println(ex);
//                System.out.print(ex);
//            }
        }


    }//GEN-LAST:event_t_productosMouseClicked

    private void t_productosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_t_productosKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_t_productosKeyReleased

    private void txt_busKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_busKeyTyped

    }//GEN-LAST:event_txt_busKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JButton btn_eli;
    public static javax.swing.JButton btn_mod;
    public static javax.swing.JButton btn_reg;
    private javax.swing.JComboBox cbx_cla;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTable t_productos;
    private javax.swing.JTextField txt_bus;
    // End of variables declaration//GEN-END:variables
}
