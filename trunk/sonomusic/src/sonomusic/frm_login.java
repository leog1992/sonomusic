/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sonomusic;

import Clases.Cl_Usuario;
import Clases.Cl_Conectar;
import Clases.Cl_Varios;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.ImageIcon;
import nicon.notify.core.Notification;
import org.jvnet.substance.SubstanceLookAndFeel;

public class frm_login extends javax.swing.JFrame {

    Cl_Usuario usu = new Cl_Usuario();
    Cl_Conectar con = new Cl_Conectar();
    Cl_Varios ven = new Cl_Varios();
    ImageIcon icon_error;
    ImageIcon icon_ok;

    public frm_login() {
        setUndecorated(true);
        initComponents();
        con.conectar();
        this.setLocationRelativeTo(null);
        lbl_user.setVisible(false);
        lbl_pass.setVisible(false);
        String path_error = "/Recursos/error.png";
        URL url_error = this.getClass().getResource(path_error);
        icon_error = new ImageIcon(url_error);

        String path_ok = "/Recursos/ok.png";
        URL url_ok = this.getClass().getResource(path_ok);
        icon_ok = new ImageIcon(url_ok);

        //JFrame.setDefaultLookAndFeelDecorated(true);
        SubstanceLookAndFeel.setSkin("org.jvnet.substance.skin.OfficeSilver2007Skin");
        //SubstanceLookAndFeel.setCurrentTheme("org.jvnet.substance.theme.SubstanceRaspberryTheme");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        txt_usu = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btn_ent = new javax.swing.JButton();
        lbl_user = new javax.swing.JLabel();
        btn_can = new javax.swing.JButton();
        lbl_pass = new javax.swing.JLabel();
        txt_pass = new javax.swing.JPasswordField();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Login - SONO MUSIC IMPORT");
        setIconImage(Toolkit.getDefaultToolkit().getImage("logo.png"));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        txt_usu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_usuActionPerformed(evt);
            }
        });
        txt_usu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_usuKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_usuKeyTyped(evt);
            }
        });

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Usuario :");

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Contraseña: ");

        btn_ent.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/accept.png"))); // NOI18N
        btn_ent.setText("Aceptar");
        btn_ent.setEnabled(false);
        btn_ent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_entActionPerformed(evt);
            }
        });
        btn_ent.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btn_entKeyPressed(evt);
            }
        });

        lbl_user.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/ok.png"))); // NOI18N

        btn_can.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/cancel.png"))); // NOI18N
        btn_can.setText("Cancelar");
        btn_can.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_canActionPerformed(evt);
            }
        });

        lbl_pass.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/ok.png"))); // NOI18N

        txt_pass.setEditable(false);
        txt_pass.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_passKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_ent)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_can)
                .addGap(14, 14, 14))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_usu, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_pass, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_user, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbl_pass, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txt_usu, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lbl_user, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_pass, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lbl_pass, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_can, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_ent, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(248, 0, 0));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/logo_sonomusic.png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(248, 0, 0));

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Sistema de Punto de Venta");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_usuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_usuKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!txt_usu.getText().isEmpty()) {
                usu.setNick(txt_usu.getText());
                try {
                    Statement st = con.conexion();
                    String ver_usu = "select * from usuario where nick = '" + usu.getNick() + "' and estado = '1'";
                    ResultSet rs = con.consulta(st, ver_usu);
                    if (rs.next()) {
                        usu.setContra(rs.getString("contra"));
                        frm_menu.usu.setPer_anu_traslado(rs.getString("pe_tras"));
                        frm_menu.usu.setPer_compra_productos(rs.getString("pv_comp"));
                        frm_menu.usu.setPer_compra_servicios(rs.getString("pv_coms"));
                        frm_menu.usu.setPer_conf_documento(rs.getString("pm_docs"));
                        frm_menu.usu.setPer_cuentas(rs.getString("pm_cue"));
                        frm_menu.usu.setPer_eli_empresa(rs.getString("pe_emp"));
                        frm_menu.usu.setPer_eli_producto(rs.getString("pe_prod"));
                        frm_menu.usu.setPer_mod_almacen(rs.getString("pm_alm"));
                        frm_menu.usu.setPer_mod_producto(rs.getString("pm_prod"));
                        frm_menu.usu.setPer_reg_adelanto(rs.getString("pr_ade"));
                        frm_menu.usu.setPer_reg_almacen(rs.getString("pr_alm"));
                        frm_menu.usu.setPer_reg_oferta(rs.getString("pr_ofe"));
                        frm_menu.usu.setPer_reg_producto(rs.getString("pr_prod"));
                        frm_menu.usu.setPer_reg_traslado(rs.getString("pr_tras"));
                        frm_menu.usu.setPer_reg_venta(rs.getString("pr_ven"));
                        frm_menu.usu.setPer_usuario(rs.getString("pm_usu"));
                        frm_menu.usu.setPer_ver_caja(rs.getString("pv_caj"));
                        frm_menu.usu.setPer_ver_reportes(rs.getString("pv_rep"));
                        frm_menu.usu.setPer_ver_rrhh(rs.getString("pv_rh"));
                        txt_pass.setEditable(true);
                        txt_pass.requestFocus();
                        lbl_user.setIcon(icon_ok);
                        lbl_user.setVisible(true);

                    } else {
                        txt_usu.setText("");
                        txt_usu.requestFocus();
                        lbl_user.setVisible(true);
                        lbl_user.setIcon(icon_error);
                    }
                } catch (SQLException ex) {
                    System.out.print(ex);
                }
            }
            //lbl_estado.setVisible(false);
        }
    }//GEN-LAST:event_txt_usuKeyPressed

    private void txt_usuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_usuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_usuActionPerformed

    private void txt_usuKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_usuKeyTyped
        if (txt_usu.getText().length() == 8) {
            evt.consume();
        }
        ven.solo_numeros(evt);
    }//GEN-LAST:event_txt_usuKeyTyped

    private void btn_canActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_canActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btn_canActionPerformed

    private void btn_entKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btn_entKeyPressed
//        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
//            btn_ent.doClick();
//        }
    }//GEN-LAST:event_btn_entKeyPressed

    private void btn_entActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_entActionPerformed
        frm_menu menu = new frm_menu();
        menu.usu.setNick(usu.getNick());
        menu.lbl_user.setText(usu.getNick());
        Notification.show("Inicio de Session", "Bienvenido Sr(a): " + usu.obtener_nombre(usu.getNick()), Notification.CONFIRM_MESSAGE, Notification.NICON_LIGHT_THEME);    
        menu.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btn_entActionPerformed

    private void txt_passKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_passKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!txt_pass.getText().isEmpty()) {
                String pass = txt_pass.getText();
                if (pass.equals(usu.getContra())) {
                    btn_ent.setEnabled(true);
                    btn_ent.requestFocus();
                    lbl_pass.setIcon(icon_ok);
                    lbl_pass.setVisible(true);
                } else {
                    txt_pass.setText("");
                    txt_pass.requestFocus();
                    lbl_pass.setIcon(icon_error);
                    lbl_pass.setVisible(true);
                }
            }
        }
    }//GEN-LAST:event_txt_passKeyPressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(frm_login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(frm_login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(frm_login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(frm_login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frm_login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_can;
    private javax.swing.JButton btn_ent;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lbl_pass;
    private javax.swing.JLabel lbl_user;
    private javax.swing.JPasswordField txt_pass;
    private javax.swing.JTextField txt_usu;
    // End of variables declaration//GEN-END:variables
}
