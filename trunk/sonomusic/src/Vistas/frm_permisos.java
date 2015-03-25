package Vistas;

import Clases.Cl_Conectar;
import Clases.Cl_Usuario;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import sonomusic.frm_menu;

/**
 *
 * @author pc
 */
public class frm_permisos extends javax.swing.JInternalFrame {

    Cl_Conectar con = new Cl_Conectar();
    Cl_Usuario usu = new Cl_Usuario();

    public frm_permisos() {
        initComponents();
        //cargar_permisos();

    }

    void cargar_permisos() {
        try {
            
            String dni=frm_ver_usuarios.mostrar.getValueAt(0, 0).toString();
            Statement st = con.conexion();
            String sql = "select per_panel, per_menu, per_repor, per_ver_venta, per_adelanto, per_coti_cliente,"
                    + " per_comp_servicio, per_conf_documentos, per_caja_chica, per_cierre_caja, per_notas,"
                    + " per_depositos from usuario where nick='" + dni + "' ";
            ResultSet rs = con.consulta(st, sql);
            if (rs.next()) {
                if (rs.getInt("per_panel") == 1) {
                    chk_panel.setSelected(true);
                } else {
                    chk_panel.setSelected(false);
                }
                //
                if (rs.getInt("per_menu") == 1) {
                    chk_menu.setSelected(true);
                } else {
                    chk_menu.setSelected(false);
                }
                //
                if (rs.getInt("per_repor") == 1) {
                    chk_reportes.setSelected(true);
                } else {
                    chk_reportes.setSelected(false);
                }
                //
                if (rs.getInt("per_ver_venta") == 1) {
                    chk_ventas.setSelected(true);
                } else {
                    chk_ventas.setSelected(false);
                }
                if (rs.getInt("per_adelanto") == 1) {
                    chk_adelantos.setSelected(true);
                } else {
                    chk_adelantos.setSelected(false);
                }
                //
                if (rs.getInt("per_coti_cliente") == 1) {
                    chk_cotizacion_cliente.setSelected(true);
                } else {
                    chk_cotizacion_cliente.setSelected(false);
                }
                //
                if (rs.getInt("per_comp_servicio") == 1) {
                    chk_compra_servicios.setSelected(true);
                } else {
                    chk_compra_servicios.setSelected(false);
                }
                //
                if (rs.getInt("per_conf_documentos") == 1) {
                    chk_documentos.setSelected(true);
                } else {
                    chk_documentos.setSelected(false);
                }
                //
                if (rs.getInt("per_caja_chica") == 1) {
                    chk_caja_chica.setSelected(true);
                } else {
                    chk_caja_chica.setSelected(false);
                }
                //
                if (rs.getInt("per_cierre_caja") == 1) {
                    chk_cierre_caja.setSelected(true);
                } else {
                    chk_cierre_caja.setSelected(false);
                }
                //
                if (rs.getInt("per_notas") == 1) {
                    chk_notas.setSelected(true);
                } else {
                    chk_notas.setSelected(false);
                }
                //
                if (rs.getInt("per_depositos") == 1) {
                    chk_depositos.setSelected(true);
                } else {
                    chk_depositos.setSelected(false);
                }
                //                                
            }
            con.cerrar(st);
            con.cerrar(rs);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getLocalizedMessage());
        }

    }

    void llenar() {
        if (chk_panel.isSelected()) {
            usu.setPer_panel("1");
        } else {
            usu.setPer_panel("0");
        }
        //
        if (chk_menu.isSelected()) {
            usu.setPer_menu("1");
        } else {
            usu.setPer_menu("0");
        }
        //
        if (chk_reportes.isSelected()) {
            usu.setPer_report("1");
        } else {
            usu.setPer_report("0");
        }
        //
        if (chk_ventas.isSelected()) {
            usu.setPer_venta("1");
        } else {
            usu.setPer_venta("0");
        }
        //
        if (chk_adelantos.isSelected()) {
            usu.setPer_adelanto("1");
        } else {
            usu.setPer_adelanto("0");
        }
        //
        if (chk_cotizacion_cliente.isSelected()) {
            usu.setPer_coti_cliente("1");
        } else {
            usu.setPer_coti_cliente("0");
        }
        //
        if (chk_compra_servicios.isSelected()) {
            usu.setPer_comp_servicio("1");
        } else {
            usu.setPer_comp_servicio("0");
        }
        //
        if (chk_documentos.isSelected()) {
            usu.setPer_conf_documentos("1");
        } else {
            usu.setPer_conf_documentos("0");
        }
        if (chk_caja_chica.isSelected()) {
            usu.setPer_caja_chica("1");
        } else {
            usu.setPer_caja_chica("0");
        }
        //
        if (chk_cierre_caja.isSelected()) {
            usu.setPer_cierre_caja("1");
        } else {
            usu.setPer_cierre_caja("0");
        }
        //
        if (chk_notas.isSelected()) {
            usu.setPer_notas("1");
        } else {
            usu.setPer_notas("0");
        }
        //
        if (chk_depositos.isSelected()) {
            usu.setPer_depositos("1");
        } else {
            usu.setPer_depositos("0");
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lbl_dni = new javax.swing.JLabel();
        lbl_nombre = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        chk_panel = new javax.swing.JCheckBox();
        chk_reportes = new javax.swing.JCheckBox();
        chk_ventas = new javax.swing.JCheckBox();
        chk_adelantos = new javax.swing.JCheckBox();
        chk_cotizacion_cliente = new javax.swing.JCheckBox();
        chk_compra_servicios = new javax.swing.JCheckBox();
        chk_documentos = new javax.swing.JCheckBox();
        chk_caja_chica = new javax.swing.JCheckBox();
        chk_cierre_caja = new javax.swing.JCheckBox();
        chk_notas = new javax.swing.JCheckBox();
        chk_depositos = new javax.swing.JCheckBox();
        chk_menu = new javax.swing.JCheckBox();
        btn_cerrar = new javax.swing.JButton();
        btn_guardar = new javax.swing.JButton();

        setTitle("Establecer Permisos");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/permisos2.png"))); // NOI18N

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Usuario", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        jLabel1.setText("DNI:");

        jLabel2.setText("Nombre:");

        lbl_dni.setText("jLabel3");

        lbl_nombre.setText("jLabel4");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbl_dni, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(lbl_nombre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(lbl_dni)
                    .addComponent(lbl_nombre))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Permisos", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        chk_panel.setText("ver Panel");
        chk_panel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chk_panelActionPerformed(evt);
            }
        });

        chk_reportes.setText("Ver Reportes");
        chk_reportes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chk_reportesActionPerformed(evt);
            }
        });

        chk_ventas.setText("Ver Ventas");
        chk_ventas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chk_ventasActionPerformed(evt);
            }
        });

        chk_adelantos.setText("ver Adelantos");
        chk_adelantos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chk_adelantosActionPerformed(evt);
            }
        });

        chk_cotizacion_cliente.setText("Ver Cotizacion Cliente");
        chk_cotizacion_cliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chk_cotizacion_clienteActionPerformed(evt);
            }
        });

        chk_compra_servicios.setText("Ver Compra Servicios");
        chk_compra_servicios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chk_compra_serviciosActionPerformed(evt);
            }
        });

        chk_documentos.setText("ver Configurar Documentos");
        chk_documentos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chk_documentosActionPerformed(evt);
            }
        });

        chk_caja_chica.setText("Ver Caja Chica");
        chk_caja_chica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chk_caja_chicaActionPerformed(evt);
            }
        });

        chk_cierre_caja.setText("Ver Cierre de Caja");
        chk_cierre_caja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chk_cierre_cajaActionPerformed(evt);
            }
        });

        chk_notas.setText("Ver Notas");
        chk_notas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chk_notasActionPerformed(evt);
            }
        });

        chk_depositos.setText("Ver Depositos");
        chk_depositos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chk_depositosActionPerformed(evt);
            }
        });

        chk_menu.setText("Ver Menu Aministrativo");
        chk_menu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chk_menuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chk_panel)
                    .addComponent(chk_reportes)
                    .addComponent(chk_ventas)
                    .addComponent(chk_adelantos)
                    .addComponent(chk_cotizacion_cliente)
                    .addComponent(chk_menu))
                .addGap(47, 47, 47)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chk_depositos)
                    .addComponent(chk_notas)
                    .addComponent(chk_cierre_caja)
                    .addComponent(chk_caja_chica)
                    .addComponent(chk_documentos)
                    .addComponent(chk_compra_servicios))
                .addContainerGap(161, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chk_compra_servicios)
                    .addComponent(chk_panel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chk_documentos)
                    .addComponent(chk_menu))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chk_reportes)
                    .addComponent(chk_caja_chica))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chk_ventas)
                    .addComponent(chk_cierre_caja))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chk_adelantos)
                    .addComponent(chk_notas))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chk_cotizacion_cliente)
                    .addComponent(chk_depositos))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btn_cerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/cancel.png"))); // NOI18N
        btn_cerrar.setText("Cancelar");
        btn_cerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cerrarActionPerformed(evt);
            }
        });

        btn_guardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/disk.png"))); // NOI18N
        btn_guardar.setText("Guardar");
        btn_guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_guardarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn_cerrar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_guardar)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_cerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_guardar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_cerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cerrarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btn_cerrarActionPerformed

    private void btn_guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_guardarActionPerformed
        llenar();
        try {
            Statement st = con.conexion();
            String sql = "update usuario set per_panel='" + usu.getPer_panel() + "',per_menu='" + usu.getPer_menu() + "',per_repor='" + usu.getPer_report() + "'"
                    + ",per_ver_venta='" + usu.getPer_venta() + "',per_adelanto='" + usu.getPer_adelanto() + "',per_coti_cliente='" + usu.getPer_coti_cliente() + "'"
                    + ",per_comp_servicio='" + usu.getPer_comp_servicio() + "',per_conf_documentos='" + usu.getPer_conf_documentos() + "',per_caja_chica='" + usu.getPer_caja_chica() + "'"
                    + ",per_cierre_caja='" + usu.getPer_cierre_caja() + "',per_notas='" + usu.getPer_notas() + "',per_depositos='" + usu.getPer_depositos() + "' where nick='" + lbl_dni.getText() + "' ";
            con.actualiza(st, sql);
            con.cerrar(st);
            JOptionPane.showMessageDialog(null, "Permisos Establecidos");
            this.dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getLocalizedMessage());
        }
    }//GEN-LAST:event_btn_guardarActionPerformed

    private void chk_menuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chk_menuActionPerformed

    }//GEN-LAST:event_chk_menuActionPerformed

    private void chk_panelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chk_panelActionPerformed

    }//GEN-LAST:event_chk_panelActionPerformed

    private void chk_reportesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chk_reportesActionPerformed

    }//GEN-LAST:event_chk_reportesActionPerformed

    private void chk_ventasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chk_ventasActionPerformed

    }//GEN-LAST:event_chk_ventasActionPerformed

    private void chk_adelantosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chk_adelantosActionPerformed

    }//GEN-LAST:event_chk_adelantosActionPerformed

    private void chk_cotizacion_clienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chk_cotizacion_clienteActionPerformed

    }//GEN-LAST:event_chk_cotizacion_clienteActionPerformed

    private void chk_compra_serviciosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chk_compra_serviciosActionPerformed

    }//GEN-LAST:event_chk_compra_serviciosActionPerformed

    private void chk_documentosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chk_documentosActionPerformed

    }//GEN-LAST:event_chk_documentosActionPerformed

    private void chk_caja_chicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chk_caja_chicaActionPerformed

    }//GEN-LAST:event_chk_caja_chicaActionPerformed

    private void chk_cierre_cajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chk_cierre_cajaActionPerformed

    }//GEN-LAST:event_chk_cierre_cajaActionPerformed

    private void chk_notasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chk_notasActionPerformed

    }//GEN-LAST:event_chk_notasActionPerformed

    private void chk_depositosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chk_depositosActionPerformed

    }//GEN-LAST:event_chk_depositosActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_cerrar;
    private javax.swing.JButton btn_guardar;
    public static javax.swing.JCheckBox chk_adelantos;
    public static javax.swing.JCheckBox chk_caja_chica;
    public static javax.swing.JCheckBox chk_cierre_caja;
    public static javax.swing.JCheckBox chk_compra_servicios;
    public static javax.swing.JCheckBox chk_cotizacion_cliente;
    public static javax.swing.JCheckBox chk_depositos;
    public static javax.swing.JCheckBox chk_documentos;
    public static javax.swing.JCheckBox chk_menu;
    public static javax.swing.JCheckBox chk_notas;
    public static javax.swing.JCheckBox chk_panel;
    public static javax.swing.JCheckBox chk_reportes;
    public static javax.swing.JCheckBox chk_ventas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    public static javax.swing.JLabel lbl_dni;
    public static javax.swing.JLabel lbl_nombre;
    // End of variables declaration//GEN-END:variables
}
