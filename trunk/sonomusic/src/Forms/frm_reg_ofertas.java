/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

import Clases.Cl_Almacen;
import Clases.Cl_Conectar;
import Clases.Cl_Ofertas;
import Clases.Cl_Varios;
import Vistas.frm_ver_ofertas;
import Vistas.frm_ver_productos;
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import sonomusic.frm_menu;

/**
 *
 * @author pc
 */
public class frm_reg_ofertas extends javax.swing.JInternalFrame {

    Cl_Conectar con = new Cl_Conectar();
    Cl_Varios ven = new Cl_Varios();
    Cl_Ofertas ofe = new Cl_Ofertas();
    Cl_Almacen alm = new Cl_Almacen();
    public static String ventana = "oferta";
    public static String win = "reg";
    public static DefaultTableModel modelo;
    int i;

    public frm_reg_ofertas() {
        initComponents();
        ver_detalle_oferta();
        ver_almacen();
    }

    public void ver_detalle_oferta() {
        modelo = new DefaultTableModel() {

            @Override
            public boolean isCellEditable(int row, int column) {
                return false; //To change body of generated methods, choose Tools | Templates.
            }
        };

        modelo.addColumn("Id");
        modelo.addColumn("Descripcion");
        modelo.addColumn("Marca");
        modelo.addColumn("Cantidad");
        modelo.addColumn("Und. Medida");
        modelo.addColumn("Precio");
        t_oferta.setModel(modelo);
        t_oferta.getColumnModel().getColumn(0).setPreferredWidth(30);
        t_oferta.getColumnModel().getColumn(1).setPreferredWidth(300);
        t_oferta.getColumnModel().getColumn(2).setPreferredWidth(80);
        t_oferta.getColumnModel().getColumn(3).setPreferredWidth(50);
        t_oferta.getColumnModel().getColumn(4).setPreferredWidth(90);
        t_oferta.getColumnModel().getColumn(5).setPreferredWidth(90);
        //alineado de columnas
        ven.centrar_celda(t_oferta, 1);
        ven.centrar_celda(t_oferta, 2);
        ven.centrar_celda(t_oferta, 4);
        ven.centrar_celda(t_oferta, 3);
        ven.derecha_celda(t_oferta, 5);
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
                    cbx_alm.addItem(almacen.get(j));
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

    public void llenar() {

        ofe.setNom_ofe(txt_des_ofer.getText());
        ofe.setFec_ini(ven.fechabase(txt_fec_ini.getText()));
        ofe.setFec_fin(ven.fechabase(txt_fec_fin.getText()));
        alm.setId(cbx_alm.getSelectedIndex() + 1);

    }

    void limpiar() {
        txt_des_ofer.setText("");
        txt_des_ofer.requestFocus();
        txt_fec_ini.setText("");
        txt_fec_ini.setEditable(false);
        txt_fec_ini.setText(ven.getFechaActual());
        txt_fec_fin.setText("");
        txt_fec_fin.setEditable(false);
        txt_id_pro.setText("");
        txt_des_pro.setText("");
    }

    void limpiar_tabla() {
        for (int j = 0; j < modelo.getRowCount(); j++) {
            modelo.removeRow(j);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        btn_bus_pro = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        t_oferta = new javax.swing.JTable();
        txt_id_pro = new javax.swing.JTextField();
        txt_des_pro = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txt_des_ofer = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        btn_reg = new javax.swing.JButton();
        btn_cc = new javax.swing.JButton();
        btn_cp = new javax.swing.JButton();
        btn_el = new javax.swing.JButton();
        txt_fec_fin = new javax.swing.JFormattedTextField();
        txt_fec_ini = new javax.swing.JFormattedTextField();
        jLabel5 = new javax.swing.JLabel();
        cbx_alm = new javax.swing.JComboBox();

        setTitle("Registrar Oferta");
        setToolTipText("");
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
            }
        });

        jLabel2.setText("Descripción de Oferta:");

        jLabel1.setText("Fec. Ini.");

        btn_bus_pro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/find.png"))); // NOI18N
        btn_bus_pro.setEnabled(false);
        btn_bus_pro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_bus_proActionPerformed(evt);
            }
        });
        btn_bus_pro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btn_bus_proKeyPressed(evt);
            }
        });

        jLabel3.setText("Producto:");

        t_oferta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6"
            }
        ));
        t_oferta.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        t_oferta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                t_ofertaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(t_oferta);

        txt_id_pro.setEditable(false);
        txt_id_pro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_id_proActionPerformed(evt);
            }
        });

        txt_des_pro.setEditable(false);
        txt_des_pro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_des_proActionPerformed(evt);
            }
        });

        jLabel4.setText("Fec. Fin");

        txt_des_ofer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_des_oferActionPerformed(evt);
            }
        });
        txt_des_ofer.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_des_oferKeyPressed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/cancel.png"))); // NOI18N
        jButton2.setText("Cerrar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        btn_reg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/add.png"))); // NOI18N
        btn_reg.setText("Registrar");
        btn_reg.setEnabled(false);
        btn_reg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_regActionPerformed(evt);
            }
        });

        btn_cc.setText("+/-");
        btn_cc.setEnabled(false);
        btn_cc.setMargin(new java.awt.Insets(2, 2, 2, 2));
        btn_cc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ccActionPerformed(evt);
            }
        });

        btn_cp.setText("S/.");
        btn_cp.setEnabled(false);
        btn_cp.setMargin(new java.awt.Insets(2, 2, 2, 2));
        btn_cp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cpActionPerformed(evt);
            }
        });

        btn_el.setText("X");
        btn_el.setEnabled(false);
        btn_el.setMargin(new java.awt.Insets(2, 2, 2, 2));
        btn_el.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_elActionPerformed(evt);
            }
        });

        txt_fec_fin.setEditable(false);
        try {
            txt_fec_fin.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txt_fec_fin.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_fec_fin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_fec_finActionPerformed(evt);
            }
        });
        txt_fec_fin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_fec_finKeyPressed(evt);
            }
        });

        txt_fec_ini.setEditable(false);
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

        jLabel5.setText("Tienda:");

        cbx_alm.setEnabled(false);
        cbx_alm.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cbx_almKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(txt_id_pro, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_des_pro)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_bus_pro))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(txt_des_ofer, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn_cc, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(61, 61, 61)
                        .addComponent(btn_cp, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_el, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_reg)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbx_alm, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_fec_ini, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(txt_fec_fin, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_des_ofer, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_fec_fin, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_fec_ini, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbx_alm, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_id_pro, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_des_pro, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btn_bus_pro, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(btn_cc, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_cp, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_el, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_reg, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_id_proActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_id_proActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_id_proActionPerformed

    private void txt_des_proActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_des_proActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_des_proActionPerformed

    private void txt_des_oferActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_des_oferActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_des_oferActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        frm_ver_ofertas ofe = new frm_ver_ofertas();
        ven.llamar_ventana(ofe);
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void formInternalFrameActivated(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameActivated
        txt_des_ofer.setEditable(true);
        txt_des_ofer.requestFocus();
    }//GEN-LAST:event_formInternalFrameActivated

    private void txt_des_oferKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_des_oferKeyPressed
        if (!txt_des_ofer.getText().isEmpty()) {
            if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                cbx_alm.setEnabled(true);
//                String fechita = txt_des_ofer.getText();
//                System.out.println(ven.fechabase(fechita));
                cbx_alm.requestFocus();
            }
        }
    }//GEN-LAST:event_txt_des_oferKeyPressed

    private void btn_bus_proActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_bus_proActionPerformed
        frm_ver_productos prod = new frm_ver_productos();
        prod.ventana = "oferta";
        ven.llamar_ventana(prod);
    }//GEN-LAST:event_btn_bus_proActionPerformed

    private void btn_regActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_regActionPerformed
        llenar();

        try {
            Statement st = con.conexion();
            String insert_ofe = "insert oferta Values (null, '" + alm.getId() + "','" + ofe.getNom_ofe() + "', '" + ofe.getFec_ini() + "', "
                    + "'" + ofe.getFec_fin() + "', '" + ofe.getEst_ofe() + "')";
            con.actualiza(st, insert_ofe);
            con.cerrar(st);
        } catch (Exception e) {
            System.out.println("Error " + e + " en: " + e.getLocalizedMessage());
            JOptionPane.showMessageDialog(null, "Error " + e.getLocalizedMessage());
        }

        try {
            Statement st = con.conexion();
            String ultimo_id = "select idOferta from oferta where fecha_ofer ='" + ofe.getFec_ini() + "' and "
                    + "fecha_venc ='" + ofe.getFec_fin()+ "' and idAlmacen = '" + frm_menu.alm.getId() + "' order by idOferta "
                    + "desc LIMIT 1";
            ResultSet rs = con.consulta(st, ultimo_id);
            if (rs.next()) {
                ofe.setId_ofe(rs.getInt("idOferta"));
            }
            con.cerrar(rs);
            con.cerrar(st);
        } catch (Exception e) {
            System.out.println("Error " + e.getLocalizedMessage());
            JOptionPane.showMessageDialog(null, "Error " + e.getLocalizedMessage());
        }
        System.out.println(ofe.getId_ofe());
        //insertar productos en detalle_oferta
        try {
            int filas = t_oferta.getRowCount();
            for (int j = 0; j <= (filas - 1); j++) {
                String idPro = t_oferta.getValueAt(j, 0).toString();
                Double precio = Double.parseDouble(t_oferta.getValueAt(j, 5).toString());
                Double cantidad = Double.parseDouble(t_oferta.getValueAt(j, 3).toString());

                //Statement st = con.conexion();
                Statement st = con.conexion();
                String ins_det_cot = "insert into detalle_oferta Values ('" + idPro + "', '" + ofe.getId_ofe() + "', '" + precio + "', '" + cantidad + "')";
                con.actualiza(st, ins_det_cot);
                con.cerrar(st);
                System.out.print("insertando producto: id: " + idPro + " Cantidad: " + cantidad + " Precio: " + precio + "\n");
            }
            JOptionPane.showMessageDialog(null, "Datos grabados satisfactoriamente");
            limpiar_tabla();
            limpiar();
            btn_reg.setEnabled(false);
        } catch (NumberFormatException | HeadlessException e) {
            System.out.println("Error " + e.getLocalizedMessage());
            JOptionPane.showMessageDialog(null, "Error " + e.getLocalizedMessage());
        }
    }//GEN-LAST:event_btn_regActionPerformed

    private void btn_bus_proKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btn_bus_proKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btn_bus_pro.doClick();
        }
    }//GEN-LAST:event_btn_bus_proKeyPressed

    private void t_ofertaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_t_ofertaMouseClicked
        i = t_oferta.getSelectedRow();
        btn_el.setEnabled(true);
        btn_cc.setEnabled(true);
        btn_cp.setEnabled(true);
    }//GEN-LAST:event_t_ofertaMouseClicked

    private void btn_ccActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ccActionPerformed
        Double cantidad = Double.parseDouble(JOptionPane.showInputDialog("Ingrese Cantidad"));
        t_oferta.setValueAt(cantidad, i, 3);
    }//GEN-LAST:event_btn_ccActionPerformed

    private void btn_cpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cpActionPerformed
        Double precio = Double.parseDouble(JOptionPane.showInputDialog("Ingrese Precio"));
        t_oferta.setValueAt(precio, i, 5);

    }//GEN-LAST:event_btn_cpActionPerformed

    private void btn_elActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_elActionPerformed
        modelo.removeRow(i);
        btn_el.setEnabled(false);
        btn_cc.setEnabled(false);
        btn_cp.setEnabled(false);
    }//GEN-LAST:event_btn_elActionPerformed

    private void txt_fec_finActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_fec_finActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_fec_finActionPerformed

    private void txt_fec_finKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_fec_finKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (txt_fec_fin.getText().trim().length() == 10) {
                btn_bus_pro.setEnabled(true);
                btn_bus_pro.requestFocus();
            }
        }
    }//GEN-LAST:event_txt_fec_finKeyPressed

    private void txt_fec_iniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_fec_iniActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_fec_iniActionPerformed

    private void txt_fec_iniKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_fec_iniKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txt_fec_fin.setText(txt_fec_ini.getText());
            txt_fec_fin.setEditable(true);
            txt_fec_fin.requestFocus();
        }
    }//GEN-LAST:event_txt_fec_iniKeyPressed

    private void cbx_almKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbx_almKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            // MOSTRAR FECHA ACTUAL FORMATEADA
            txt_fec_ini.setText(ven.fechaformateada(ven.getFechaActual()));
            txt_fec_ini.setEditable(true);
            txt_fec_ini.requestFocus();
        }
    }//GEN-LAST:event_cbx_almKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_bus_pro;
    private javax.swing.JButton btn_cc;
    private javax.swing.JButton btn_cp;
    private javax.swing.JButton btn_el;
    public static javax.swing.JButton btn_reg;
    private javax.swing.JComboBox cbx_alm;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTable t_oferta;
    private javax.swing.JTextField txt_des_ofer;
    public static javax.swing.JTextField txt_des_pro;
    private javax.swing.JFormattedTextField txt_fec_fin;
    private javax.swing.JFormattedTextField txt_fec_ini;
    public static javax.swing.JTextField txt_id_pro;
    // End of variables declaration//GEN-END:variables
}
