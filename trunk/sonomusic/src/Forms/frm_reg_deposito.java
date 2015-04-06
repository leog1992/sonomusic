/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

import Clases.Cl_Conectar;
import Clases.Cl_Movimiento;
import Clases.Cl_Varios;
import Vistas.frm_movimientos;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import sonomusic.frm_menu;

/**
 *
 * @author Usuario
 */
public class frm_reg_deposito extends javax.swing.JInternalFrame {

    Cl_Conectar con = new Cl_Conectar();
    Cl_Varios ven = new Cl_Varios();
    Cl_Movimiento mov = new Cl_Movimiento();
    int idcuenta;

    /**
     * Creates new form frm_reg_deposito
     */
    public frm_reg_deposito() {
        initComponents();
        ver_bancos();
        ver_moneda();
    }

    private void ver_bancos() {
        try {
            ArrayList banco = new ArrayList();
            Statement st = con.conexion();
            String query = "select nombre from banco order by idbanco asc";
            ResultSet rs = con.consulta(st, query);
            while (rs.next()) {
                banco.add(rs.getString("nombre"));
            }
            if (banco.size() > 0) {
                for (int j = 0; j < banco.size(); j++) {
                    cbx_banc.addItem(banco.get(j));
                }
            } else {
                JOptionPane.showMessageDialog(null, "Lista de Bancos no disponible");
            }
            con.cerrar(st);
            con.cerrar(rs);
        } catch (SQLException ex) {
            System.out.print(ex);
            JOptionPane.showMessageDialog(null, "Lista no disponible");
        }
    }

    private void ver_moneda() {
        try {
            ArrayList moneda = new ArrayList();
            Statement st = con.conexion();
            String query = "select nombre from moneda order by idmoneda asc";
            ResultSet rs = con.consulta(st, query);
            while (rs.next()) {
                moneda.add(rs.getString("nombre"));
            }
            if (moneda.size() > 0) {
                for (int j = 0; j < moneda.size(); j++) {
                    cbx_mon.addItem(moneda.get(j));
                }
            } else {
                JOptionPane.showMessageDialog(null, "Lista de Bancos no disponible");
            }
            con.cerrar(st);
            con.cerrar(rs);
        } catch (SQLException ex) {
            System.out.print(ex);
            JOptionPane.showMessageDialog(null, "Lista no disponible");
        }
    }

    private void ver_tipo_cuenta(int idban, int idmon) {

        try {
            ArrayList tipoc = new ArrayList();
            Statement st = con.conexion();
            String query = "select tipo_cuenta from cuenta where idbanco = '" + idban + "' and idmoneda = '" + idmon + "' "
                    + "order by idcuenta asc";
            ResultSet rs = con.consulta(st, query);
            while (rs.next()) {
                tipoc.add(rs.getString("tipo_cuenta"));
            }
            if (tipoc.size() > 0) {
                for (int j = 0; j < tipoc.size(); j++) {
                    cbx_tipc.addItem(tipoc.get(j));
                }
                cbx_tipc.setEnabled(true);
                cbx_tipc.requestFocus();
            } else {
                cbx_tipc.setEnabled(false);
                cbx_mon.requestFocus();
                JOptionPane.showMessageDialog(null, "No existe tipo de cuenta con las condiciones indicadas");

            }
            con.cerrar(st);
            con.cerrar(rs);
        } catch (SQLException ex) {
            System.out.print(ex);
            JOptionPane.showMessageDialog(null, ex.getLocalizedMessage());
        }
    }

    private void ver_nro_cuenta(int idban, int idmon, String tipc) {

        try {
            Statement st = con.conexion();
            String query = "select idcuenta, nro_cuenta from cuenta where idbanco = '" + idban + "' and idmoneda = '" + idmon + "' "
                    + "and tipo_cuenta = '" + tipc + "' order by idcuenta asc";
            ResultSet rs = con.consulta(st, query);
            if (rs.next()) {
                idcuenta = rs.getInt("idcuenta");
                txt_cuenta.setText(rs.getString("nro_cuenta"));
                txt_fec.setEditable(true);
                txt_fec.requestFocus();
            }
            con.cerrar(st);
            con.cerrar(rs);
        } catch (SQLException ex) {
            System.out.print(ex);
            JOptionPane.showMessageDialog(null, ex.getLocalizedMessage());
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
        cbx_banc = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        cbx_mon = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        txt_cuenta = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        cbx_tipc = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        txt_fec = new javax.swing.JFormattedTextField();
        jLabel6 = new javax.swing.JLabel();
        txt_dep = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txt_mon = new javax.swing.JTextField();
        btn_cer = new javax.swing.JButton();
        btn_reg = new javax.swing.JButton();

        setBackground(new java.awt.Color(254, 254, 254));
        setClosable(true);
        setTitle("Deposito a Cuenta ");

        jLabel1.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(212, 2, 2));
        jLabel1.setText("Banco:");

        cbx_banc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cbx_bancKeyPressed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(212, 2, 2));
        jLabel2.setText("Moneda:");

        cbx_mon.setEnabled(false);
        cbx_mon.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cbx_monKeyPressed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(212, 2, 2));
        jLabel3.setText("Cuenta:");

        txt_cuenta.setEditable(false);

        jLabel4.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(212, 2, 2));
        jLabel4.setText("Tipo Cuenta:");

        cbx_tipc.setEnabled(false);
        cbx_tipc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cbx_tipcKeyPressed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(212, 2, 2));
        jLabel5.setText("Fecha:");

        txt_fec.setEditable(false);
        try {
            txt_fec.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txt_fec.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_fec.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_fecKeyPressed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(212, 2, 2));
        jLabel6.setText("Cod. Deposito:");

        txt_dep.setEditable(false);
        txt_dep.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_depKeyPressed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(212, 2, 2));
        jLabel7.setText("Monto:");

        txt_mon.setEditable(false);
        txt_mon.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_monKeyPressed(evt);
            }
        });

        btn_cer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/cancel.png"))); // NOI18N
        btn_cer.setText("Cerrar");
        btn_cer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cerActionPerformed(evt);
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cbx_tipc, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbx_banc, 0, 150, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_fec, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)
                        .addComponent(jLabel6)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(txt_dep, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_mon, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn_reg)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_cer))
                    .addComponent(txt_cuenta, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbx_mon, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbx_mon, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbx_banc, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbx_tipc, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_cuenta, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_fec, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_dep, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_mon, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_cer, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_reg, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_cerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cerActionPerformed
        this.dispose();
    }//GEN-LAST:event_btn_cerActionPerformed

    private void cbx_bancKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbx_bancKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cbx_mon.setEnabled(true);
            cbx_mon.requestFocus();
        }
    }//GEN-LAST:event_cbx_bancKeyPressed

    private void cbx_monKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbx_monKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            int idban = cbx_banc.getSelectedIndex() + 1;
            int idmon = cbx_mon.getSelectedIndex() + 1;
            ver_tipo_cuenta(idban, idmon);
        }
    }//GEN-LAST:event_cbx_monKeyPressed

    private void cbx_tipcKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbx_tipcKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            int idban = cbx_banc.getSelectedIndex() + 1;
            int idmon = cbx_mon.getSelectedIndex() + 1;
            String tipo = cbx_tipc.getSelectedItem().toString();
            ver_nro_cuenta(idban, idmon, tipo);
        }
    }//GEN-LAST:event_cbx_tipcKeyPressed

    private void txt_fecKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_fecKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (txt_fec.getText().trim().length() == 10) {
                txt_dep.setEditable(true);
                txt_dep.requestFocus();
            }
        }
    }//GEN-LAST:event_txt_fecKeyPressed

    private void txt_depKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_depKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!txt_dep.getText().isEmpty()) {
                txt_mon.setEditable(true);
                txt_mon.requestFocus();
            }
        }
    }//GEN-LAST:event_txt_depKeyPressed

    private void txt_monKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_monKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!txt_mon.getText().isEmpty()) {
                btn_reg.setEnabled(true);
                btn_reg.requestFocus();
            }
        }
    }//GEN-LAST:event_txt_monKeyPressed

    private void llenar() {
        mov.setIngreso(Double.parseDouble(txt_mon.getText()));
        mov.setGlosa("DEPOSITO EN EFECTIVO EN CUENTA Nro. " + txt_cuenta.getText());
        mov.setFec_mov(ven.fechabase(txt_fec.getText()));
    }
    
    private void btn_regActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_regActionPerformed
        llenar();
        try {
            Statement st = con.conexion();
            String ins_ini = "insert into movimiento values (null, '" + mov.getGlosa() + "', '" + mov.getFec_mov() + "', "
                    + "'" + mov.getIngreso() + "', '0.00', '" + frm_menu.lbl_user.getText() + "', '" + frm_menu.alm.getId() + "', "
                    + "'B', '" + idcuenta + "')";
            con.actualiza(st, ins_ini);
            con.cerrar(st);
        } catch (Exception ex) {
            System.out.println(ex);
            JOptionPane.showMessageDialog(null, ex.getLocalizedMessage());
        }
        
        frm_movimientos movi = new frm_movimientos();
        ven.llamar_ventana(movi);
        this.dispose();
    }//GEN-LAST:event_btn_regActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_cer;
    private javax.swing.JButton btn_reg;
    private javax.swing.JComboBox cbx_banc;
    private javax.swing.JComboBox cbx_mon;
    private javax.swing.JComboBox cbx_tipc;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JTextField txt_cuenta;
    private javax.swing.JTextField txt_dep;
    private javax.swing.JFormattedTextField txt_fec;
    private javax.swing.JTextField txt_mon;
    // End of variables declaration//GEN-END:variables
}
