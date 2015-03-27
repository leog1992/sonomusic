/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import Clases.Cl_Cliente;
import Clases.Cl_Conectar;
import Clases.Cl_Varios;
import Forms.frm_reg_cliente;
import Forms.frm_reg_cotizacion;
import Forms.frm_reg_venta;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author pc
 */
public class frm_ver_cliente extends javax.swing.JInternalFrame {

    Cl_Varios ven = new Cl_Varios();
    Cl_Conectar con = new Cl_Conectar();
    Cl_Cliente cli = new Cl_Cliente();
    public static String ventana = "cliente";
    public static String win = "reg";
    Integer i;
    DefaultTableModel mostrar;

    /**
     * Creates new form frm_cliente
     */
    public frm_ver_cliente() {
        initComponents();
        
        String query = "select nro_doc, nom_per, tel_per,tel2_per, est_per from cliente order by nom_per asc";
        ver_cliente(query);
    }

    private void ver_cliente(String query) {
        try {
            mostrar = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int fila, int columna) {
                    return false;
                }
            };
            Statement st = con.conexion();
            ResultSet rs = con.consulta(st, query);

            //La cantidad de columnas que tiene la consulta
            //Establecer como cabezeras el nombre de las colimnas
            mostrar.addColumn("Nro Doc.");
            mostrar.addColumn("Nombre Completo");
            mostrar.addColumn("Telefono 1");
            mostrar.addColumn("Telefono 2");
            mostrar.addColumn("Estado");

            //Creando las filas para el JTable
            while (rs.next()) {
                Object[] fila = new Object[5];
                fila[0] = rs.getObject("nro_doc");
                fila[1] = rs.getObject("nom_per");
                fila[2] = rs.getObject("tel_per");
                fila[3] = rs.getObject("tel2_per");
                String estado = rs.getString("est_per");
                if (estado.equals("1")) {
                    fila[4] = "Activo";
                } else {
                    fila[4] = "-";
                }

                mostrar.addRow(fila);
            }
            con.cerrar(st);
            con.cerrar(rs);
            t_clientes.setModel(mostrar);
            t_clientes.getColumnModel().getColumn(0).setPreferredWidth(50);
            t_clientes.getColumnModel().getColumn(1).setPreferredWidth(280);
            t_clientes.getColumnModel().getColumn(2).setPreferredWidth(50);
            t_clientes.getColumnModel().getColumn(3).setPreferredWidth(50);
            t_clientes.getColumnModel().getColumn(4).setPreferredWidth(50);
            mostrar.fireTableDataChanged();
        } catch (SQLException e) {
            System.out.print(e);
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txt_bus = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        btn_mod = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        t_clientes = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        btn_eli = new javax.swing.JButton();
        btn_historial = new javax.swing.JButton();

        setClosable(true);
        setTitle("Ver Clientes");

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/find.png"))); // NOI18N
        jLabel1.setText("Buscar:");

        txt_bus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_busKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_busKeyTyped(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/application_add.png"))); // NOI18N
        jButton1.setText("Registrar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
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

        t_clientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"46993209", "LUIS ENRIQUE OYANGUREN GIRON", "947396729", "Normal"},
                {"10469932091", "LEOG SYSTEMS ", "947396729", "Normal"},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Nro. Doc", "Nombre o Razon Social", "Telefono", "Estado"
            }
        ));
        t_clientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                t_clientesMousePressed(evt);
            }
        });
        t_clientes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                t_clientesKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(t_clientes);
        if (t_clientes.getColumnModel().getColumnCount() > 0) {
            t_clientes.getColumnModel().getColumn(0).setPreferredWidth(50);
            t_clientes.getColumnModel().getColumn(1).setPreferredWidth(200);
            t_clientes.getColumnModel().getColumn(2).setPreferredWidth(50);
            t_clientes.getColumnModel().getColumn(3).setPreferredWidth(20);
        }

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/cancel.png"))); // NOI18N
        jButton3.setText("Cerrar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
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

        btn_historial.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/blog.png"))); // NOI18N
        btn_historial.setText("Ver Historial");
        btn_historial.setEnabled(false);
        btn_historial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_historialActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 747, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_bus, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_mod))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btn_eli)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_historial)
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
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_mod, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_eli, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_historial, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        frm_reg_cliente cliente = new Forms.frm_reg_cliente();
        ven.llamar_ventana(cliente);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void t_clientesMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_t_clientesMousePressed
        i = t_clientes.getSelectedRow();
        btn_mod.setEnabled(true);
        btn_eli.setEnabled(true);
        btn_historial.setEnabled(true);
        cli.setNro_doc(t_clientes.getValueAt(i, 0).toString());
//        if (ventana.equals("venta")) {
//            frm_reg_venta.txt_nrod.setText(t_clientes.getValueAt(i, 0).toString());
//            frm_reg_venta.txt_nomc.setText(t_clientes.getValueAt(i, 1).toString());
//            frm_reg_venta.txt_fec.requestFocus();
//            frm_reg_venta.btn_add_pro.setEnabled(true);
//            ventana = "cliente";
//            this.dispose();
//        }
//        
//        if (ventana.equals("cotizacion")) {
//            frm_reg_cotizacion.txt_doc.setText(t_clientes.getValueAt(i, 0).toString());
//            frm_reg_cotizacion.txt_nom.setText(t_clientes.getValueAt(i, 1).toString());
//            frm_reg_cotizacion.txt_tel.setText(t_clientes.getValueAt(i, 2).toString());
//            frm_reg_cotizacion.txt_fec.setText(ven.getFechaActual());
//            frm_reg_cotizacion.txt_fec.setEditable(true);
//            frm_reg_cotizacion.txt_fec.requestFocus();
//            frm_reg_cotizacion.btn_add.setEnabled(true);
//            ventana = "cliente";
//            this.dispose();
//        }
    }//GEN-LAST:event_t_clientesMousePressed

    private void txt_busKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_busKeyTyped

    }//GEN-LAST:event_txt_busKeyTyped

    private void txt_busKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_busKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String buscar = txt_bus.getText();
            String query = "select nro_doc, nom_per, tel_per,tel2_per, est_per from cliente where nom_per like '%" + buscar + "%' or nro_doc like '%" + buscar + "%'";
            ver_cliente(query);
        }
    }//GEN-LAST:event_txt_busKeyPressed

    private void btn_modActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_modActionPerformed
        frm_reg_cliente cliente = new frm_reg_cliente();
        try {
            cli.setNro_doc(t_clientes.getValueAt(i, 0).toString());
            Statement st = con.conexion();
            String ver_cli = "select * from cliente where nro_doc = '" + cli.getNro_doc() + "'";
            ResultSet rs = con.consulta(st, ver_cli);
            if (rs.next()) {
                cliente.cbx_cli.setSelectedItem(rs.getString("tipo_doc"));
                cliente.cbx_cli.setEnabled(false);
                cliente.txt_ndoc.setText(rs.getString("nro_doc"));
                cliente.txt_ndoc.setEditable(false);
                cliente.txt_nom.setText(rs.getString("nom_per"));
                cliente.txt_nom.setEditable(true);
                cliente.txt_nom.requestFocus();
                cliente.txt_dir.setText(rs.getString("dir_per"));
                cliente.txt_dir.setEditable(true);
                cliente.txt_tel.setText(rs.getString("tel_per"));
                cliente.txt_tel.setEditable(true);
                cliente.txt_tel1.setText(rs.getString("tel2_per"));
                cliente.txt_tel1.setEditable(true);
                cliente.btn_lim.setEnabled(false);
                cliente.btn_reg.setEnabled(true);
                cliente.win = "mod";
                ven.llamar_ventana(cliente);
                this.dispose();
            }
        } catch (SQLException ex) {
            System.out.print(ex);
        }
    }//GEN-LAST:event_btn_modActionPerformed

    private void btn_eliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_eliActionPerformed
        int confirmado = JOptionPane.showConfirmDialog(null, "¿Confirma eliminar el cliente?");

        if (JOptionPane.OK_OPTION == confirmado) {
            try {
                Statement st = con.conexion();
                String eli_cli = "delete from cliente where nro_doc = '" + cli.getNro_doc() + "'";
                con.actualiza(st, eli_cli);
                con.cerrar(st);
                String query = "select nro_doc, nom_per, tel_per, tel2_per, est_per from cliente order by nom_per asc";
                ver_cliente(query);
            } catch (Exception ex) {
                System.out.print(ex);
                JOptionPane.showMessageDialog(null, "Error al Eliminar!");
            }
        } else {
            System.out.print("no hago nada!");
        }
    }//GEN-LAST:event_btn_eliActionPerformed

    private void t_clientesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_t_clientesKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            if (ventana.equals("cotizacion")) {
                frm_reg_cotizacion coti = null;
                cli.setNro_doc(t_clientes.getValueAt(i, 0).toString());
                try {
                    Statement st = con.conexion();
                    String ver_pro = "select * from cliente where nro_doc = '" + cli.getNro_doc() + "'";
                    ResultSet rs = con.consulta(st, ver_pro);
                    if (rs.next()) {
                        coti.txt_doc.setText(cli.getNro_doc() + "");
                        coti.txt_nom.setText(rs.getString("nom_per"));
                        coti.txt_tel.setText(rs.getString("tel_per") + " - " + rs.getString("tel2_per"));
                        coti.btn_add.setEnabled(true);
                        coti.btn_add.requestFocus();
                        this.dispose();
                    }
                } catch (SQLException ex) {
                    System.out.print(ex);
                }
            }
            //clientes
            if (ventana.equals("reg_venta")) {
                frm_reg_venta venta = null;
                cli.setNro_doc(t_clientes.getValueAt(i, 0).toString());
                try {
                    Statement st = con.conexion();
                    String ver_pro = "select nro_doc, nom_per from cliente where nro_doc = '" + cli.getNro_doc() + "'";
                    ResultSet rs = con.consulta(st, ver_pro);
                    if (rs.next()) {
                        //venta.txt_nrod.setText(cli.getNro_doc() + "");
                        //venta.txt_nomc.setText(rs.getString("nom_per"));                        
                        frm_reg_venta.cbx_tipd.setEnabled(true);
                        frm_reg_venta.cbx_tipd.requestFocus();
                        this.dispose();
                    }
                } catch (SQLException ex) {
                    System.out.print(ex);
                }
            }
        }
    }//GEN-LAST:event_t_clientesKeyPressed

    private void btn_historialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_historialActionPerformed
        try {
            frm_ver_historial_cliente historial = new frm_ver_historial_cliente();
            cli.setNro_doc(t_clientes.getValueAt(i, 0).toString());
            historial.txt_doc.setText(cli.getNro_doc());
            historial.txt_nom.setText(t_clientes.getValueAt(i, 1).toString());
            DefaultTableModel modelo = new DefaultTableModel();
            modelo.addColumn("Fecha");
            modelo.addColumn("Tipo Doc.");
            modelo.addColumn("Serie - Nro");
            modelo.addColumn("Producto");
            modelo.addColumn("Marca");
            modelo.addColumn("Precio");
            modelo.addColumn("Cant.");
            modelo.addColumn("SubTotal");
            Statement st = con.conexion();
            String ver_historia = "select p.fec_ped, td.desc_tipd, p.serie_doc, p.nro_doc, dp.idProductos, pr.desc_pro, "
                    + "pr.modelo, pr.serie, pr.marca, dp.precio, dp.cantidad from pedido as p inner join detalle_pedido as "
                    + "dp on p.idPedido=dp.idPedido inner join tipo_doc as td on p.idtipo_doc=td.idtipo_doc inner join productos"
                    + " as pr on dp.idProductos = pr.idProductos where p.nro_doc = '"+cli.getNro_doc()+"' order by p.fec_ped desc, dp.idProductos asc";
            ResultSet rs = con.consulta(st, ver_historia);
            while (rs.next()) {
                Object[] fila = new Object[8];
                fila[0] = rs.getString("p.fec_ped");
                fila[1] = rs.getString("td.desc_tipd");
                fila[2] = rs.getString("p.serie_doc") + " - " + rs.getString("p.nro_doc");
                fila[3] = rs.getString("pr.desc_pro") + " - " + rs.getString("pr.modelo") + " - " + rs.getString("pr.serie");
                fila[4] = rs.getString("pr.marca");
                fila[5] = rs.getDouble("dp.precio");
                fila[6] = rs.getDouble("dp.cantidad");
                fila[7] = rs.getDouble("dp.cantidad") * rs.getDouble("dp.precio");
                modelo.addRow(fila);
            }
            con.cerrar(rs);
            con.cerrar(st);
            historial.t_historial.setModel(modelo);
            historial.t_historial.getColumnModel().getColumn(0).setPreferredWidth(80);
            historial.t_historial.getColumnModel().getColumn(1).setPreferredWidth(100);
            historial.t_historial.getColumnModel().getColumn(2).setPreferredWidth(90);
            historial.t_historial.getColumnModel().getColumn(3).setPreferredWidth(250);
            historial.t_historial.getColumnModel().getColumn(4).setPreferredWidth(80);
            historial.t_historial.getColumnModel().getColumn(5).setPreferredWidth(70);
            historial.t_historial.getColumnModel().getColumn(6).setPreferredWidth(60);
            historial.t_historial.getColumnModel().getColumn(7).setPreferredWidth(70);
            ven.centrar_celda(historial.t_historial, 0);
            ven.centrar_celda(historial.t_historial, 2);
            ven.centrar_celda(historial.t_historial, 4);
            ven.derecha_celda(historial.t_historial, 5);
            ven.derecha_celda(historial.t_historial, 6);
            ven.derecha_celda(historial.t_historial, 7);
            historial.t_historial.updateUI();
            ven.llamar_ventana(historial);
            this.dispose();
        } catch (SQLException ex) {
            System.out.println(ex);
            JOptionPane.showMessageDialog(null, ex.getLocalizedMessage());
        }
    }//GEN-LAST:event_btn_historialActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_eli;
    private javax.swing.JButton btn_historial;
    private javax.swing.JButton btn_mod;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable t_clientes;
    private javax.swing.JTextField txt_bus;
    // End of variables declaration//GEN-END:variables
}
