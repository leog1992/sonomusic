/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import Clases.Cl_Almacen;
import Clases.Cl_Conectar;
import Clases.Cl_Productos;
import Clases.render_productos;
import Clases.Cl_Varios;
import Forms.frm_reg_productos;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import static sonomusic.frm_menu.usu;

/**
 *
 * @author luis-d
 */
public class frm_ver_prod_alm_det extends javax.swing.JInternalFrame {

    Cl_Varios ven = new Cl_Varios();
    Cl_Conectar con = new Cl_Conectar();
    Cl_Productos pro = new Cl_Productos();
    Cl_Almacen alm = new Cl_Almacen();
    public static DefaultTableModel mostrar;
    public static String funcion = "prod_alm_det";
    Integer i;

    /**
     * Creates new form frm_ver_mat_alm
     */
    public frm_ver_prod_alm_det() {
        initComponents();
        txt_bus.requestFocus();
        mostrar = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int fila, int columna) {
                return false;
            }
        };
        mostrar.addColumn("Id");
        mostrar.addColumn("Descripcion");
        mostrar.addColumn("Marca");
        mostrar.addColumn("Cant. Act.");
        mostrar.addColumn("Und. Med.");
        mostrar.addColumn("Precio");
        mostrar.addColumn("Clasificacion");
        mostrar.addColumn("Grado");
        mostrar.addColumn("Almacen");
        mostrar.addColumn("Estado");
        t_productos.setModel(mostrar);
        t_productos.getColumnModel().getColumn(0).setPreferredWidth(30);
        t_productos.getColumnModel().getColumn(1).setPreferredWidth(350);
        t_productos.getColumnModel().getColumn(2).setPreferredWidth(80);
        t_productos.getColumnModel().getColumn(3).setPreferredWidth(60);
        t_productos.getColumnModel().getColumn(4).setPreferredWidth(80);
        t_productos.getColumnModel().getColumn(5).setPreferredWidth(60);
        t_productos.getColumnModel().getColumn(6).setPreferredWidth(50);
        t_productos.getColumnModel().getColumn(7).setPreferredWidth(100);
        t_productos.getColumnModel().getColumn(8).setPreferredWidth(80);
        t_productos.getColumnModel().getColumn(9).setPreferredWidth(90);
        mostrar.fireTableDataChanged();
//        String query = "select p.idProductos, p.desc_pro, p.modelo, p.serie, p.marca, pa.cant,"
//                + " p.cant_min, pa.precio, p.estado, c.desc_clas, u.desc_und, "
//                + "p.grado,a.nom_alm from producto_almacen as pa inner join productos as p "
//                + "on pa.idProductos=p.idProductos inner join clasificacion as c "
//                + "on p.id_clas=c.id_clas inner join und_medida as u "
//                + "on p.idUnd_Medida=u.idUnd_Medida  inner join almacen as a"
//                + " on pa.idAlmacen=a.idAlmacen order by p.desc_pro asc, p.modelo asc";
//        ver_productos(query);
        t_productos.setDefaultRenderer(Object.class, new render_productos());
        ver_almacen();
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
            mostrar.addColumn("Descripcion");
            mostrar.addColumn("Marca");
            mostrar.addColumn("Cant. Act.");
            mostrar.addColumn("Und. Med.");
            mostrar.addColumn("Precio");
            mostrar.addColumn("Clasificacion");
            mostrar.addColumn("Grado");
            mostrar.addColumn("Almacen");
            mostrar.addColumn("Estado");
            t_productos.setModel(mostrar);
            t_productos.getColumnModel().getColumn(0).setPreferredWidth(30);
            t_productos.getColumnModel().getColumn(1).setPreferredWidth(350);
            t_productos.getColumnModel().getColumn(2).setPreferredWidth(80);
            t_productos.getColumnModel().getColumn(3).setPreferredWidth(60);
            t_productos.getColumnModel().getColumn(4).setPreferredWidth(80);
            t_productos.getColumnModel().getColumn(5).setPreferredWidth(60);
            t_productos.getColumnModel().getColumn(6).setPreferredWidth(50);
            t_productos.getColumnModel().getColumn(7).setPreferredWidth(100);
            t_productos.getColumnModel().getColumn(8).setPreferredWidth(80);
            t_productos.getColumnModel().getColumn(9).setPreferredWidth(90);
            mostrar.fireTableDataChanged();

            //Creando las filas para el JTable
            while (rs.next()) {
                Object[] fila = new Object[10];
                fila[0] = rs.getObject("idProductos");
                fila[1] = rs.getObject("desc_pro") + " - " + rs.getObject("modelo") + " - " + rs.getObject("serie");
                fila[2] = rs.getObject("marca");
                fila[3] = rs.getObject("cant");
                fila[4] = rs.getObject("desc_und");
                fila[5] = rs.getObject("precio");
                fila[6] = rs.getObject("desc_clas");
                fila[7] = rs.getObject("grado");
                fila[8] = rs.getObject("nom_alm");
                if (rs.getString("estado").equals("1")) {
                    if (rs.getDouble("cant") > rs.getDouble("cant_min")) {
                        fila[9] = "NORMAL";
                    }
                    if (rs.getDouble("cant") <= rs.getDouble("cant_min")) {
                        fila[9] = "POR TERMINAR";
                    }
                    if (rs.getDouble("cant") <= 0) {
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
            t_productos.getColumnModel().getColumn(0).setPreferredWidth(30);
            t_productos.getColumnModel().getColumn(1).setPreferredWidth(350);
            t_productos.getColumnModel().getColumn(2).setPreferredWidth(80);
            t_productos.getColumnModel().getColumn(3).setPreferredWidth(60);
            t_productos.getColumnModel().getColumn(4).setPreferredWidth(80);
            t_productos.getColumnModel().getColumn(5).setPreferredWidth(60);
            t_productos.getColumnModel().getColumn(6).setPreferredWidth(50);
            t_productos.getColumnModel().getColumn(7).setPreferredWidth(100);
            t_productos.getColumnModel().getColumn(8).setPreferredWidth(80);
            t_productos.getColumnModel().getColumn(9).setPreferredWidth(90);
            //    TableRowSorter sorter = new TableRowSorter(mostrar);
            //  t_productos.setRowSorter(sorter);
            mostrar.fireTableDataChanged();

        } catch (SQLException e) {
            System.out.print(e);
        }

    }

    private void ver_almacen() {
        try {
            ArrayList almacen = new ArrayList();
            Statement st = con.conexion();
            String query = "select nom_alm from almacen order by idAlmacen asc";
            ResultSet rs = con.consulta(st, query);
            while (rs.next()) {
                almacen.add(rs.getString("nom_alm"));
            }
            if (almacen.size() > 0) {
                for (int j = 0; j < almacen.size(); j++) {
                    cbx_clas.addItem(almacen.get(j));
                }
            } else {
                JOptionPane.showMessageDialog(null, "Lista de Almacen no disponible");
            }
            con.cerrar(st);
            con.cerrar(rs);
        } catch (SQLException ex) {
            System.out.print(ex);
            JOptionPane.showMessageDialog(null, "Lista no disponible");
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

        jScrollPane1 = new javax.swing.JScrollPane();
        t_productos = new javax.swing.JTable();
        txt_bus = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        btn_cer = new javax.swing.JButton();
        cbx_clas = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        btn_mod = new javax.swing.JButton();

        setBackground(new java.awt.Color(254, 254, 254));
        setClosable(true);
        setTitle("Ver Productos en los Almacen");

        jScrollPane1.setBackground(new java.awt.Color(254, 254, 254));

        t_productos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6", "Title 7", "Title 8", "Title 9", "Title 10"
            }
        ));
        t_productos.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        t_productos.setRowHeight(20);
        t_productos.addMouseListener(new java.awt.event.MouseAdapter() {
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
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_busKeyReleased(evt);
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

        cbx_clas.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "TODOS" }));
        cbx_clas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbx_clasActionPerformed(evt);
            }
        });
        cbx_clas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cbx_clasKeyPressed(evt);
            }
        });

        jLabel2.setForeground(new java.awt.Color(212, 2, 2));
        jLabel2.setText("Ver por Tienda");

        btn_mod.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/application_edit.png"))); // NOI18N
        btn_mod.setText("Modificar");
        btn_mod.setEnabled(false);
        btn_mod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_modActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 978, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_bus, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbx_clas, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btn_mod)
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
                    .addComponent(cbx_clas, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 351, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_cer, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_mod, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void t_productosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_t_productosKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            txt_bus.setText("");
            txt_bus.requestFocus();
        }
    }//GEN-LAST:event_t_productosKeyPressed

    private void btn_cerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cerActionPerformed
        funcion = "material_almacen";
        this.dispose();
    }//GEN-LAST:event_btn_cerActionPerformed

    private void t_productosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_t_productosMousePressed
        i = t_productos.getSelectedRow();
        btn_mod.setEnabled(true);
    }//GEN-LAST:event_t_productosMousePressed

    private void cbx_clasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbx_clasKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbx_clasKeyPressed

    private void cbx_clasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbx_clasActionPerformed
//        if (cbx_clas.getSelectedItem().equals("TODOS")) {
//            String query = "select p.idProductos, p.desc_pro, p.modelo, p.serie, p.marca, pa.cant,"
//                    + " p.cant_min, pa.precio, p.estado, c.desc_clas, u.desc_und, "
//                    + "p.grado,a.nom_alm from producto_almacen as pa inner join productos as p "
//                    + "on pa.idProductos=p.idProductos inner join clasificacion as c "
//                    + "on p.id_clas=c.id_clas inner join und_medida as u "
//                    + "on p.idUnd_Medida=u.idUnd_Medida  inner join almacen as a"
//                    + " on pa.idAlmacen=a.idAlmacen order by p.desc_pro asc;";
//            ver_productos(query);
//            t_productos.setDefaultRenderer(Object.class, new render_productos());
//        } else {
//            String query = "select p.idProductos, p.desc_pro, p.modelo, p.serie, p.marca, pa.cant,"
//                    + " p.cant_min, pa.precio, p.estado, c.desc_clas, u.desc_und, "
//                    + "p.grado,a.nom_alm from producto_almacen as pa inner join productos as p "
//                    + "on pa.idProductos=p.idProductos inner join clasificacion as c "
//                    + "on p.id_clas=c.id_clas inner join und_medida as u "
//                    + "on p.idUnd_Medida=u.idUnd_Medida  inner join almacen as a"
//                    + " on pa.idAlmacen=a.idAlmacen where pa.idAlmacen = '" + cbx_clas.getSelectedIndex() + "' order by p.desc_pro asc;";
//            ver_productos(query);
//            t_productos.setDefaultRenderer(Object.class, new render_productos());
//        }
        txt_bus.setText("");
        txt_bus.requestFocus();
    }//GEN-LAST:event_cbx_clasActionPerformed

    private void btn_modActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_modActionPerformed
        if (usu.getPer_mod_producto().equals("1")) {
            frm_reg_productos prod = new frm_reg_productos();
            int ida = 0;
            String nom_alm = t_productos.getValueAt(i, 8).toString();

            try {
                Statement st = con.conexion();
                String query = "select idAlmacen from almacen where nom_alm = '" + nom_alm + "' ";
                ResultSet rs = con.consulta(st, query);
                if (rs.next()) {
                    ida = rs.getInt("idAlmacen");
                }
                con.cerrar(rs);
                con.cerrar(st);
            } catch (Exception e) {
                System.out.println(e);
            }

            try {
                int id = Integer.parseInt(t_productos.getValueAt(i, 0).toString());
                String query = "select p.idProductos, p.desc_pro, p.marca, p.modelo, p.serie, p.grado, p.idUnd_medida, p.costo_compra, pa.precio, p.id_clas, "
                        + "p.cant_min, p.comision from producto_almacen as pa inner join productos as p on pa.idProductos=p.idProductos where pa.idProductos = '" + id + "'"
                        + " and pa.idAlmacen =  '" + ida + "'";
                Statement st = con.conexion();
                ResultSet rs = con.consulta(st, query);
                if (rs.next()) {
                    prod.txt_cod.setText(rs.getString("idProductos"));
                    prod.txt_cod.setEditable(false);
                    prod.txt_des.setText(rs.getString("desc_pro"));
                    prod.txt_des.setEditable(false);
                    prod.txt_mar.setText(rs.getString("marca"));
                    prod.txt_mar.setEditable(false);
                    prod.txt_mod.setText(rs.getString("modelo"));
                    prod.txt_mod.setEditable(false);
                    prod.cbx_und.setSelectedIndex(rs.getInt("idUnd_medida") - 1);
                    prod.cbx_und.setEnabled(false);
                    prod.txt_pcom.setText(rs.getString("costo_compra"));
                    prod.txt_pcom.setEditable(false);
                    prod.txt_pven.requestFocus();
                    prod.txt_pven.setText(rs.getString("precio"));
                    prod.txt_pven.setEditable(true);
                    prod.cbx_cla.setSelectedIndex(rs.getInt("id_clas") - 1);
                    prod.cbx_cla.setEnabled(false);
                    prod.txt_cantm.setText(rs.getString("cant_min"));
                    prod.txt_cantm.setEditable(false);
                    prod.txt_com.setText(rs.getString("comision"));
                    prod.txt_com.setEditable(false);
                    prod.btn_reg.setEnabled(false);
                }

                prod.win = "mod";
                prod.ventana = "producto_almacen";
                prod.id = id;
                prod.ida = ida;
                ven.llamar_ventana(prod);
                this.dispose();

            } catch (SQLException ex) {
                System.out.print(ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Ud No tiene permisos");
        }
    }//GEN-LAST:event_btn_modActionPerformed

    private void txt_busKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_busKeyReleased
        if (txt_bus.getText().length() > 2) {
            if (cbx_clas.getSelectedIndex() == 0) {
                String texto = txt_bus.getText();
                String query = "select p.idProductos, p.desc_pro, p.modelo, p.serie, p.marca, pa.cant, p.cant_min, pa.precio, p.estado, c.desc_clas, u.desc_und, p.grado,a.nom_alm "
                        + "from producto_almacen as pa "
                        + "inner join productos as p on pa.idProductos=p.idProductos "
                        + "inner join clasificacion as c on p.id_clas=c.id_clas "
                        + "inner join und_medida as u on p.idUnd_Medida=u.idUnd_Medida "
                        + "inner join almacen as a on pa.idAlmacen=a.idAlmacen "
                        + "where concat(p.desc_pro, ' ' , p.marca, ' ' , p.modelo) like '%" + texto + "%' or pa.precio like '%" + texto + "%' "
                        + "order by p.desc_pro asc, p.modelo asc";
                ver_productos(query);
                t_productos.setDefaultRenderer(Object.class, new render_productos());
            }

            if (cbx_clas.getSelectedIndex() > 0) {
                int almacen = cbx_clas.getSelectedIndex();
                String texto = txt_bus.getText();
                String query = "select p.idProductos, p.desc_pro, p.modelo, p.serie, p.marca, pa.cant, p.cant_min, pa.precio, p.estado, c.desc_clas, u.desc_und, p.grado,a.nom_alm "
                        + "from producto_almacen as pa "
                        + "inner join productos as p on pa.idProductos=p.idProductos "
                        + "inner join clasificacion as c on p.id_clas=c.id_clas "
                        + "inner join und_medida as u on p.idUnd_Medida=u.idUnd_Medida "
                        + "inner join almacen as a on pa.idAlmacen=a.idAlmacen "
                        + "where pa.idalmacen = '" + almacen + "' and (concat(p.desc_pro, ' ' , p.marca, ' ' , p.modelo) like '%" + texto + "%' or pa.precio like '%" + texto + "%') "
                        + "order by p.desc_pro asc, p.modelo asc";
                ver_productos(query);
                t_productos.setDefaultRenderer(Object.class, new render_productos());
            }
        }
    }//GEN-LAST:event_txt_busKeyReleased

    private void txt_busKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_busKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            txt_bus.setText("");
            txt_bus.requestFocus();
        }
    }//GEN-LAST:event_txt_busKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_cer;
    private javax.swing.JButton btn_mod;
    private javax.swing.JComboBox cbx_clas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTable t_productos;
    private javax.swing.JTextField txt_bus;
    // End of variables declaration//GEN-END:variables
}
