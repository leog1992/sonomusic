package Vistas;

import Clases.Cl_Conectar;
import Clases.Cl_Ofertas;
import Clases.Cl_Varios;
import Forms.frm_reg_ofertas;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class frm_ver_ofertas extends javax.swing.JInternalFrame {

    Cl_Conectar con = new Cl_Conectar();
    Cl_Varios ven = new Cl_Varios();
    Cl_Ofertas ofe = new Cl_Ofertas();
    public static String ventana = "oferta";
    public static String win = "reg";
    public String bus;
    DefaultTableModel modelo;
    int i;

    public frm_ver_ofertas() {
        initComponents();
        String query = "select o.idOferta, a.nom_alm, o.nom_ofer, o.fecha_ofer, o.fecha_venc, o.estado from oferta as o "
                + "inner join almacen as a on o.idAlmacen = a.idAlmacen order by o.idOferta";
        ver_ofertas(query);

    }

    public void ver_ofertas(String sql) {
        try {
            modelo = new DefaultTableModel() {

                @Override
                public boolean isCellEditable(int row, int column) {
                    return false; //To change body of generated methods, choose Tools | Templates.
                }
            };
            Statement st = con.conexion();
            ResultSet rs = con.consulta(st, sql);
            modelo.addColumn("Id");
            modelo.addColumn("Tienda");
            modelo.addColumn("Descripcion");//descripcion modelo serie
            modelo.addColumn("Fec. Ini.");
            modelo.addColumn("Fec. Fin");
            modelo.addColumn("Estado");

            //Creando las filas para el JTable
            while (rs.next()) {
                Object[] fila = new Object[6];
                fila[0] = rs.getObject("idOferta");
                fila[1] = rs.getObject("nom_alm");
                fila[2] = rs.getObject("nom_ofer");
                fila[3] = rs.getObject("fecha_ofer");
                fila[4] = rs.getObject("fecha_venc");

                if (rs.getString("estado").equals("1")) {
                    fila[5] = "ACTIVO";
                } else {
                    fila[5] = "INHABILITADO";
                }

                modelo.addRow(fila);
            }
            con.cerrar(st);
            con.cerrar(rs);
            this.t_oferta.setModel(modelo);
            t_oferta.getColumnModel().getColumn(0).setPreferredWidth(10);
            t_oferta.getColumnModel().getColumn(1).setPreferredWidth(40);
            t_oferta.getColumnModel().getColumn(2).setPreferredWidth(300);
            t_oferta.getColumnModel().getColumn(3).setPreferredWidth(50);
            t_oferta.getColumnModel().getColumn(4).setPreferredWidth(50);
            t_oferta.getColumnModel().getColumn(5).setPreferredWidth(30);

        } catch (SQLException e) {
            System.out.print(e);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        t_oferta = new javax.swing.JTable();
        txt_bus = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        btn_reg = new javax.swing.JButton();
        btn_eliminar = new javax.swing.JButton();
        cbx_busca = new javax.swing.JComboBox();

        setClosable(true);
        setTitle("Oferta");
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

        jLabel2.setText("Buscar");

        t_oferta.setModel(new javax.swing.table.DefaultTableModel(
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
        t_oferta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                t_ofertaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(t_oferta);

        txt_bus.setEditable(false);
        txt_bus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_busActionPerformed(evt);
            }
        });
        txt_bus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_busKeyPressed(evt);
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
        btn_reg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_regActionPerformed(evt);
            }
        });

        btn_eliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/cross.png"))); // NOI18N
        btn_eliminar.setText("Eliminar");
        btn_eliminar.setEnabled(false);
        btn_eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_eliminarActionPerformed(evt);
            }
        });

        cbx_busca.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Fecha", "Nombre" }));
        cbx_busca.setSelectedIndex(1);
        cbx_busca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbx_buscaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 679, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_bus, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbx_busca, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_reg))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn_eliminar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_bus, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_reg, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbx_busca, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_busActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_busActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_busActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void formInternalFrameActivated(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameActivated
    }//GEN-LAST:event_formInternalFrameActivated

    private void txt_busKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_busKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String val = txt_bus.getText();
            String query = "select * from oferta where " + bus + " like '%" + val + "%' order by fecha_ofer asc";
            ver_ofertas(query);
        }
    }//GEN-LAST:event_txt_busKeyPressed

    private void btn_regActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_regActionPerformed
        frm_reg_ofertas ofe = new frm_reg_ofertas();
        ven.llamar_ventana(ofe);
        this.dispose();
    }//GEN-LAST:event_btn_regActionPerformed

    private void btn_eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_eliminarActionPerformed
        //eliminar detalle_oferta
        int confirmado = JOptionPane.showConfirmDialog(null, "¿Confirma eliminar la oferta?", "Seguridad", JOptionPane.WARNING_MESSAGE);
        ofe.setId_ofe((int) t_oferta.getValueAt(i, 0));
        if (JOptionPane.OK_OPTION == confirmado) {
            try {
                Statement st = con.conexion();
                String sql = "delete from detalle_oferta where idOferta ='" + ofe.getId_ofe() + "'";
                con.actualiza(st, sql);
                con.cerrar(st);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Imposible eliminar: " + e + " en: " + e.getLocalizedMessage());
                System.out.println("no se elimino el registro: " + ofe.getId_ofe());
            }

        //eliminar oferta
            try {
                Statement st = con.conexion();
                String query = "delete from oferta where idOferta ='" + ofe.getId_ofe() + "'";
                con.actualiza(st, query);
                con.cerrar(st);
                String query1 = "select o.idOferta, a.nom_alm, o.nom_ofer, o.fecha_ofer, o.fecha_venc, o.estado from oferta as o "
                + "inner join almacen as a on o.idAlmacen = a.idAlmacen order by o.idOferta";
                ver_ofertas(query1);
                btn_eliminar.setEnabled(false);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getLocalizedMessage());
                System.out.print(ex + " " + ex.getLocalizedMessage() + " " + ex.getCause());
            }
        }
    }//GEN-LAST:event_btn_eliminarActionPerformed

    private void t_ofertaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_t_ofertaMouseClicked
        i = t_oferta.getSelectedRow();
        btn_eliminar.setEnabled(true);
        System.out.println("selecciono la fila :" + i);
    }//GEN-LAST:event_t_ofertaMouseClicked

    private void cbx_buscaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbx_buscaActionPerformed
        if (cbx_busca.getSelectedIndex() == 0) {
            bus = "fecha_ofer";
            txt_bus.setEditable(true);
            txt_bus.requestFocus();
        } else {
            bus = "nom_ofer";
            txt_bus.setEditable(true);
            txt_bus.requestFocus();
        }
    }//GEN-LAST:event_cbx_buscaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_eliminar;
    private javax.swing.JButton btn_reg;
    private javax.swing.JComboBox cbx_busca;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTable t_oferta;
    private javax.swing.JTextField txt_bus;
    // End of variables declaration//GEN-END:variables
}
