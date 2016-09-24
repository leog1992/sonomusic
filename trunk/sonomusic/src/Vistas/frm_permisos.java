package Vistas;

import Clases.Cl_Conectar;
import Clases.Cl_Usuario;
import Clases.Cl_Varios;
import java.sql.Statement;

/**
 *
 * @author pc
 */
public class frm_permisos extends javax.swing.JInternalFrame {

    Cl_Conectar con = new Cl_Conectar();
    Cl_Usuario usu = new Cl_Usuario();
    Cl_Varios ven = new Cl_Varios();

    public frm_permisos() {
        initComponents();
        //cargar_permisos();

    }

    private void llenar() {
        usu.setNick(lbl_dni.getText());
        
        if (pe_tras.isSelected()) {
            usu.setPer_anu_traslado("1");
        } else {
            usu.setPer_anu_traslado("0");
        }

        if (pr_ven.isSelected()) {
            usu.setPer_reg_venta("1");
        } else {
            usu.setPer_reg_venta("0");
        }

        if (pv_caja.isSelected()) {
            usu.setPer_ver_caja("1");
        } else {
            usu.setPer_ver_caja("0");
        }

        if (pv_rh.isSelected()) {
            usu.setPer_ver_rrhh("1");
        } else {
            usu.setPer_ver_rrhh("0");
        }

        if (pr_ade.isSelected()) {
            usu.setPer_reg_adelanto("1");
        } else {
            usu.setPer_reg_adelanto("0");
        }

        if (pv_rep.isSelected()) {
            usu.setPer_ver_reportes("1");
        } else {
            usu.setPer_ver_reportes("0");
        }

        if (pr_ofe.isSelected()) {
            usu.setPer_reg_oferta("1");
        } else {
            usu.setPer_reg_oferta("0");
        }

        if (pr_comp.isSelected()) {
            usu.setPer_compra_productos("1");
        } else {
            usu.setPer_compra_productos("0");
        }

        if (pr_coms.isSelected()) {
            usu.setPer_compra_servicios("1");
        } else {
            usu.setPer_compra_servicios("0");
        }

        if (pr_alm.isSelected()) {
            usu.setPer_reg_almacen("1");
        } else {
            usu.setPer_reg_almacen("0");
        }

        if (pm_alm.isSelected()) {
            usu.setPer_mod_almacen("1");
        } else {
            usu.setPer_mod_almacen("0");
        }

        if (pr_tras.isSelected()) {
            usu.setPer_reg_traslado("1");
        } else {
            usu.setPer_reg_traslado("0");
        }
        
        if (pe_tras.isSelected()) {
            usu.setPer_anu_traslado("1");
        } else {
            usu.setPer_anu_traslado("0");
        }

        if (pr_prod.isSelected()) {
            usu.setPer_reg_producto("1");
        } else {
            usu.setPer_reg_producto("0");
        }

        if (pm_prod.isSelected()) {
            usu.setPer_mod_producto("1");
        } else {
            usu.setPer_mod_producto("0");
        }

        if (pe_prod.isSelected()) {
            usu.setPer_eli_producto("1");
        } else {
            usu.setPer_eli_producto("0");
        }

        if (pm_docs.isSelected()) {
            usu.setPer_conf_documento("1");
        } else {
            usu.setPer_conf_documento("0");
        }

        if (pm_emp.isSelected()) {
            usu.setPer_mod_empresa("1");
        } else {
            usu.setPer_mod_empresa("0");
        }

        if (pe_emp.isSelected()) {
            usu.setPer_eli_empresa("1");
        } else {
            usu.setPer_eli_empresa("0");
        }

        if (pm_usu.isSelected()) {
            usu.setPer_usuario("1");
        } else {
            usu.setPer_usuario("0");
        }

        if (pm_cue.isSelected()) {
            usu.setPer_cuentas("1");
        } else {
            usu.setPer_cuentas("0");
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
        jLabel3 = new javax.swing.JLabel();
        pv_rh = new javax.swing.JCheckBox();
        pr_ade = new javax.swing.JCheckBox();
        jLabel4 = new javax.swing.JLabel();
        pv_rep = new javax.swing.JCheckBox();
        jLabel5 = new javax.swing.JLabel();
        pr_ofe = new javax.swing.JCheckBox();
        pr_ven = new javax.swing.JCheckBox();
        jLabel6 = new javax.swing.JLabel();
        pr_comp = new javax.swing.JCheckBox();
        pr_coms = new javax.swing.JCheckBox();
        jLabel7 = new javax.swing.JLabel();
        pr_alm = new javax.swing.JCheckBox();
        pm_alm = new javax.swing.JCheckBox();
        pr_tras = new javax.swing.JCheckBox();
        pe_tras = new javax.swing.JCheckBox();
        pr_prod = new javax.swing.JCheckBox();
        pm_prod = new javax.swing.JCheckBox();
        pe_prod = new javax.swing.JCheckBox();
        jLabel8 = new javax.swing.JLabel();
        pm_docs = new javax.swing.JCheckBox();
        jLabel9 = new javax.swing.JLabel();
        pm_emp = new javax.swing.JCheckBox();
        pe_emp = new javax.swing.JCheckBox();
        pm_usu = new javax.swing.JCheckBox();
        pm_cue = new javax.swing.JCheckBox();
        pv_caja = new javax.swing.JCheckBox();
        btn_cerrar = new javax.swing.JButton();
        btn_guardar = new javax.swing.JButton();

        setBackground(new java.awt.Color(254, 254, 254));
        setClosable(true);
        setTitle("Establecer Permisos");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/permisos2.png"))); // NOI18N

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Usuario", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12), new java.awt.Color(255, 0, 0))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(212, 2, 2));
        jLabel1.setText("DNI:");

        jLabel2.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(212, 2, 2));
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
                .addComponent(lbl_nombre, javax.swing.GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)
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

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Permisos", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12), new java.awt.Color(255, 0, 0))); // NOI18N

        jLabel3.setForeground(java.awt.Color.red);
        jLabel3.setText("Administracion:");

        pv_rh.setForeground(new java.awt.Color(255, 0, 0));
        pv_rh.setText("Recursos Humanos");

        pr_ade.setForeground(new java.awt.Color(255, 0, 0));
        pr_ade.setText("Reg. Adelantos");

        jLabel4.setForeground(java.awt.Color.red);
        jLabel4.setText("Reportes");

        pv_rep.setForeground(new java.awt.Color(255, 0, 0));
        pv_rep.setText("Ver Reportes");

        jLabel5.setForeground(java.awt.Color.red);
        jLabel5.setText("Ventas");

        pr_ofe.setForeground(new java.awt.Color(255, 0, 0));
        pr_ofe.setText("Reg. Ofertas");

        pr_ven.setForeground(new java.awt.Color(255, 0, 0));
        pr_ven.setText("Reg. Ventas");

        jLabel6.setForeground(java.awt.Color.red);
        jLabel6.setText("Compras");

        pr_comp.setForeground(new java.awt.Color(255, 0, 0));
        pr_comp.setText("Reg. Compra Productos");

        pr_coms.setForeground(new java.awt.Color(255, 0, 0));
        pr_coms.setText("Reg. Compra Servicios");

        jLabel7.setForeground(java.awt.Color.red);
        jLabel7.setText("Almacen");

        pr_alm.setForeground(new java.awt.Color(255, 0, 0));
        pr_alm.setText("Reg. Almacen");

        pm_alm.setForeground(new java.awt.Color(255, 0, 0));
        pm_alm.setText("Mod. Almacen");
        pm_alm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pm_almActionPerformed(evt);
            }
        });

        pr_tras.setForeground(new java.awt.Color(255, 0, 0));
        pr_tras.setText("Reg. Traslado");

        pe_tras.setForeground(new java.awt.Color(255, 0, 0));
        pe_tras.setText("Anu. Traslado");

        pr_prod.setForeground(new java.awt.Color(255, 0, 0));
        pr_prod.setText("Reg. Producto");

        pm_prod.setForeground(new java.awt.Color(255, 0, 0));
        pm_prod.setText("Mod. Producto");

        pe_prod.setForeground(new java.awt.Color(255, 0, 0));
        pe_prod.setText("Eli. Producto");

        jLabel8.setForeground(java.awt.Color.red);
        jLabel8.setText("Contabilidad");

        pm_docs.setForeground(new java.awt.Color(255, 0, 0));
        pm_docs.setText("Conf. Documentos");

        jLabel9.setForeground(java.awt.Color.red);
        jLabel9.setText("Datos Generales");

        pm_emp.setForeground(new java.awt.Color(255, 0, 0));
        pm_emp.setText("Mod. Empresa");

        pe_emp.setForeground(new java.awt.Color(255, 0, 0));
        pe_emp.setText("Eli. Empresa");

        pm_usu.setForeground(new java.awt.Color(255, 0, 0));
        pm_usu.setText("Usuario");

        pm_cue.setForeground(new java.awt.Color(255, 0, 0));
        pm_cue.setText("Cuentas Bancarias");

        pv_caja.setForeground(new java.awt.Color(255, 0, 0));
        pv_caja.setText("Caja Chica");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(pr_comp)
                                    .addComponent(pr_ofe)
                                    .addComponent(pv_rep)
                                    .addComponent(pr_ade)
                                    .addComponent(pv_rh)
                                    .addComponent(pr_ven)
                                    .addComponent(pr_coms)))
                            .addComponent(jLabel6))
                        .addGap(40, 40, 40)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(pm_docs)
                                            .addComponent(pm_alm)
                                            .addComponent(pr_alm)
                                            .addComponent(pr_tras)
                                            .addComponent(pe_tras)
                                            .addComponent(pr_prod)
                                            .addComponent(pm_prod)
                                            .addComponent(pe_prod)
                                            .addComponent(pv_caja))))
                                .addGap(44, 44, 44)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(pe_emp)
                                            .addComponent(pm_emp)
                                            .addComponent(pm_usu)
                                            .addComponent(pm_cue))))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel7)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pv_rh)
                    .addComponent(pr_alm)
                    .addComponent(pm_emp))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pr_ade)
                    .addComponent(pm_alm)
                    .addComponent(pe_emp))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(pr_tras)
                    .addComponent(pm_usu))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pv_rep)
                    .addComponent(pe_tras)
                    .addComponent(pm_cue))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(pr_prod))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pr_ofe)
                    .addComponent(pm_prod))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pr_ven)
                    .addComponent(pe_prod))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pr_comp)
                    .addComponent(pm_docs))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pr_coms)
                    .addComponent(pv_caja))
                .addContainerGap())
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
        Statement st = con.conexion();
        String ins_per = "update usuario set pr_ven = '"+usu.getPer_reg_venta()+"', pv_caj = '"+usu.getPer_ver_caja()+"', pv_rh = '"+usu.getPer_ver_rrhh()+"', pr_ade = '"+usu.getPer_reg_adelanto()+"',"
                + "pv_rep = '"+usu.getPer_ver_reportes()+"', pr_ofe = '"+usu.getPer_reg_oferta()+"', pv_comp = '"+usu.getPer_compra_productos()+"', pv_coms = '"+usu.getPer_compra_servicios()+"', "
                + "pr_alm = '"+usu.getPer_reg_almacen()+"', pm_alm = '"+usu.getPer_mod_almacen()+"', pr_tras = '"+usu.getPer_reg_traslado()+"', pe_tras = '"+usu.getPer_anu_traslado()+"', pr_prod = '"+usu.getPer_reg_producto()+"', "
                + "pm_prod = '"+usu.getPer_mod_producto()+"', pe_prod = '"+usu.getPer_eli_producto()+"', pm_docs = '"+usu.getPer_conf_documento()+"', pm_emp = '"+usu.getPer_mod_empresa()+"', "
                + "pe_emp = '"+usu.getPer_eli_empresa()+"', pm_usu = '"+usu.getPer_usuario()+"', pm_cue = '"+usu.getPer_cuentas()+"' where nick = '"+usu.getNick()+"'";
        con.actualiza(st, ins_per);
        con.cerrar(st);
        frm_ver_usuarios usuarios = new frm_ver_usuarios();
        ven.llamar_ventana(usuarios);
        this.dispose();
    }//GEN-LAST:event_btn_guardarActionPerformed

    private void pm_almActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pm_almActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pm_almActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_cerrar;
    private javax.swing.JButton btn_guardar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    public static javax.swing.JLabel lbl_dni;
    public static javax.swing.JLabel lbl_nombre;
    public static javax.swing.JCheckBox pe_emp;
    public static javax.swing.JCheckBox pe_prod;
    public static javax.swing.JCheckBox pe_tras;
    public static javax.swing.JCheckBox pm_alm;
    public static javax.swing.JCheckBox pm_cue;
    public static javax.swing.JCheckBox pm_docs;
    public static javax.swing.JCheckBox pm_emp;
    public static javax.swing.JCheckBox pm_prod;
    public static javax.swing.JCheckBox pm_usu;
    public static javax.swing.JCheckBox pr_ade;
    public static javax.swing.JCheckBox pr_alm;
    public static javax.swing.JCheckBox pr_comp;
    public static javax.swing.JCheckBox pr_coms;
    public static javax.swing.JCheckBox pr_ofe;
    public static javax.swing.JCheckBox pr_prod;
    public static javax.swing.JCheckBox pr_tras;
    public static javax.swing.JCheckBox pr_ven;
    public static javax.swing.JCheckBox pv_caja;
    public static javax.swing.JCheckBox pv_rep;
    public static javax.swing.JCheckBox pv_rh;
    // End of variables declaration//GEN-END:variables
}
