package Vistas;

import Clases.Cl_Banco;
import Clases.Cl_Conectar;
import Clases.Cl_Moneda;
import Clases.Cl_Varios;
import Clases.Cl_cuenta;
import Forms.frm_reg_almacen;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author programmer
 */
public class frm_cuentas extends javax.swing.JInternalFrame {

    Cl_Banco ban = new Cl_Banco();
    Cl_Moneda mon = new Cl_Moneda();
    Cl_cuenta cue = new Cl_cuenta();
    Cl_Varios ven = new Cl_Varios();
    Cl_Conectar con = new Cl_Conectar();
    DefaultTableModel mostrar;
    int i;
    public static String ventana="grabar";
    public static String funcion = "cuenta";

    public frm_cuentas() {
        initComponents();
        mostrar_banco();
        mostrar_moneda();
        String sql = "select c.idcuenta,c.tipo_cuenta,c.nro_cuenta,c.monto,b.nombre,m.nombre as moneda,m.siglas"
                + " from cuenta as c inner join banco as b on c.idbanco=b.idbanco inner join moneda as m "
                + "on c.idmoneda=m.idmoneda order by idcuenta asc";
        mostrar_cuenta(sql);
        cbx_banco.setSelectedIndex(-1);
        cbx_moneda.setSelectedIndex(-1);
    }

  public  void mostrar_cuenta(String sql) {
        mostrar = new DefaultTableModel() {

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        mostrar.addColumn("Id");
        mostrar.addColumn("Tipo Cuenta");
        mostrar.addColumn("Numero de cuenta");
        mostrar.addColumn("Nombre de Banco");
        mostrar.addColumn("Moneda");
        mostrar.addColumn("Siglas");
        mostrar.addColumn("Monto");

        try {
            Statement st = con.conexion();
            ResultSet rs = con.consulta(st, sql);
            Object[] dato = new Object[7];
            while (rs.next()) {
                dato[0] = rs.getObject("idcuenta");
                dato[1] = rs.getObject("tipo_cuenta");
                dato[2] = rs.getObject("nro_cuenta");
                dato[3] = rs.getObject("b.nombre");
                dato[4] = rs.getObject("moneda");
                dato[5] = rs.getObject("siglas");
                dato[6] = rs.getObject("monto");
                mostrar.addRow(dato);
            }
            con.cerrar(st);
            con.cerrar(rs);
            t_cuentas.setModel(mostrar);
            t_cuentas.getColumnModel().getColumn(0).setPreferredWidth(30);
            t_cuentas.getColumnModel().getColumn(1).setPreferredWidth(150);
            t_cuentas.getColumnModel().getColumn(2).setPreferredWidth(150);
            t_cuentas.getColumnModel().getColumn(3).setPreferredWidth(180);
            t_cuentas.getColumnModel().getColumn(4).setPreferredWidth(90);
            t_cuentas.getColumnModel().getColumn(5).setPreferredWidth(50);
            t_cuentas.getColumnModel().getColumn(6).setPreferredWidth(90);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getLocalizedMessage());
        }

    }

    void mostrar_banco() {
        try {
            Statement st = con.conexion();
            String sql = "select nombre from banco";
            ResultSet rs = con.consulta(st, sql);

            while (rs.next()) {
                cbx_banco.addItem(rs.getObject("nombre"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getLocalizedMessage());
        }
    }

    void mostrar_moneda() {
        try {
            Statement st = con.conexion();
            String sql = "select * from moneda";
            ResultSet rs = con.consulta(st, sql);
            while (rs.next()) {
                cbx_moneda.addItem(rs.getObject("nombre"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getLocalizedMessage());
        }
    }

    public void llenar() {
        ban.setId(cbx_banco.getSelectedIndex() + 1);
        mon.setId(cbx_moneda.getSelectedIndex() + 1);
        cue.setTip_cuen(cbx_tipo_cuenta.getSelectedItem().toString());
        cue.setNro_cuen(txt_nro_cuenta.getText());
        cue.setMon_cuen(Double.parseDouble(txt_monto.getText()));
    }

    public void limpiar() {
        cbx_banco.setSelectedIndex(-1);
        cbx_moneda.setSelectedIndex(-1);
        cbx_tipo_cuenta.setSelectedIndex(-1);
        txt_nro_cuenta.setText("");
        txt_monto.setText("");
        cbx_moneda.setEnabled(false);
        cbx_tipo_cuenta.setEnabled(false);
        txt_nro_cuenta.setEditable(false);
        txt_monto.setEditable(false);
        btn_reg.setEnabled(false);
        cbx_banco.requestFocus();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cbx_banco = new javax.swing.JComboBox();
        cbx_tipo_cuenta = new javax.swing.JComboBox();
        cbx_moneda = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        txt_nro_cuenta = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txt_monto = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        t_cuentas = new javax.swing.JTable();
        btn_eli = new javax.swing.JButton();
        btn_mod = new javax.swing.JButton();
        btn_reg = new javax.swing.JButton();
        btn_cancelar = new javax.swing.JButton();

        setTitle("Cuentas");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/vcard.png"))); // NOI18N

        jLabel1.setText("Banco:");

        jLabel2.setText("Tipo Cuenta:");

        jLabel3.setText("Moneda:");

        cbx_banco.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cbx_bancoKeyPressed(evt);
            }
        });

        cbx_tipo_cuenta.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "CTA. CORRIENTE", "CTA. AHORROS" }));
        cbx_tipo_cuenta.setSelectedIndex(-1);
        cbx_tipo_cuenta.setEnabled(false);
        cbx_tipo_cuenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbx_tipo_cuentaActionPerformed(evt);
            }
        });
        cbx_tipo_cuenta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cbx_tipo_cuentaKeyPressed(evt);
            }
        });

        cbx_moneda.setEnabled(false);
        cbx_moneda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cbx_monedaKeyPressed(evt);
            }
        });

        jLabel4.setText("Nro. Cuenta:");

        txt_nro_cuenta.setEditable(false);
        txt_nro_cuenta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_nro_cuentaKeyPressed(evt);
            }
        });

        jLabel5.setText("Monto:");

        txt_monto.setEditable(false);
        txt_monto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_montoActionPerformed(evt);
            }
        });
        txt_monto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_montoKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_montoKeyTyped(evt);
            }
        });

        t_cuentas.setModel(new javax.swing.table.DefaultTableModel(
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
        t_cuentas.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        t_cuentas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                t_cuentasMousePressed(evt);
            }
        });
        t_cuentas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                t_cuentasKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(t_cuentas);

        btn_eli.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/cross.png"))); // NOI18N
        btn_eli.setText("Eliminar");
        btn_eli.setEnabled(false);
        btn_eli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_eliActionPerformed(evt);
            }
        });

        btn_mod.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/application_edit.png"))); // NOI18N
        btn_mod.setText("Modificar");
        btn_mod.setEnabled(false);
        btn_mod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_modActionPerformed(evt);
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
        btn_reg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btn_regKeyPressed(evt);
            }
        });

        btn_cancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/cancel.png"))); // NOI18N
        btn_cancelar.setText("Cancelar");
        btn_cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelarActionPerformed(evt);
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
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txt_nro_cuenta)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txt_monto, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cbx_banco, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cbx_moneda, 0, 131, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbx_tipo_cuenta, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 12, Short.MAX_VALUE)))
                        .addGap(0, 249, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn_eli, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_mod, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_reg, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbx_banco, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbx_tipo_cuenta, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbx_moneda, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_nro_cuenta, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_monto, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_eli, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_reg, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_mod, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_montoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_montoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_montoActionPerformed

    private void btn_eliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_eliActionPerformed

        try {
            String id = t_cuentas.getValueAt(i, 0).toString();
            Statement st = con.conexion();
            String sql = "delete from cuenta where idcuenta='" + id + "'";
            con.actualiza(st, sql);
            con.cerrar(st);
            String query = "select c.idcuenta,c.tipo_cuenta,c.nro_cuenta,c.monto,b.nombre,m.nombre as moneda,m.siglas"
                    + " from cuenta as c inner join banco as b on c.idbanco=b.idbanco inner join moneda as m "
                    + "on c.idmoneda=m.idmoneda order by idcuenta asc";
            mostrar_cuenta(query);
            btn_eli.setEnabled(false);
            btn_mod.setEnabled(false);
            cbx_banco.requestFocus();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getLocalizedMessage());
        }

    }//GEN-LAST:event_btn_eliActionPerformed

    private void btn_regActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_regActionPerformed
        llenar();
        if(ventana.equals("grabar")){
        try {
            Statement st = con.conexion();
            String sql = "insert into cuenta values(null,'" + ban.getId() + "','" + mon.getId() + "','" + cue.getTip_cuen() + "','" + cue.getNro_cuen() + "','" + cue.getMon_cuen() + "')";
            con.actualiza(st, sql);
            String query = "select c.idcuenta,c.tipo_cuenta,c.nro_cuenta,c.monto,b.nombre,m.nombre as moneda,m.siglas"
                    + " from cuenta as c inner join banco as b on c.idbanco=b.idbanco inner join moneda as m "
                    + "on c.idmoneda=m.idmoneda order by idcuenta asc";
            mostrar_cuenta(query);
            con.cerrar(st);
            limpiar();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getLocalizedMessage());
        }
        }else if(ventana.equals("modificar")){
            try {
                llenar();
                int id=(int) t_cuentas.getValueAt(i, 0);
                Statement st =con.conexion();
                String sql="update cuenta set idbanco='"+ban.getId()+"',idmoneda='"+mon.getId()+"', tipo_cuenta='"+cue.getTip_cuen()+"',"
                         + " nro_cuenta='"+cue.getNro_cuen()+"', monto='"+cue.getMon_cuen()+"' where idcuenta='"+id+"'";
                con.actualiza(st, sql);
                con.cerrar(st);
                            String query = "select c.idcuenta,c.tipo_cuenta,c.nro_cuenta,c.monto,b.nombre,m.nombre as moneda,m.siglas"
                    + " from cuenta as c inner join banco as b on c.idbanco=b.idbanco inner join moneda as m "
                    + "on c.idmoneda=m.idmoneda order by idcuenta asc";
            mostrar_cuenta(query);
            con.cerrar(st);
            limpiar();
            btn_mod.setEnabled(false);
            btn_eli.setEnabled(false);
            btn_reg.setEnabled(false);
            
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error: "+e.getLocalizedMessage());
            }
        }
        ventana="grabar";
    }//GEN-LAST:event_btn_regActionPerformed

    private void btn_modActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_modActionPerformed
        String banco,moneda,tipo_cuenta,nro_cuenta,monto;
        banco=t_cuentas.getValueAt(i, 3).toString();
        moneda=t_cuentas.getValueAt(i, 4).toString();
        tipo_cuenta=t_cuentas.getValueAt(i, 1).toString();        
        nro_cuenta=t_cuentas.getValueAt(i, 2).toString();
        monto=t_cuentas.getValueAt(i, 6).toString();
        cbx_banco.setEnabled(true);
        cbx_moneda.setEnabled(true);
        cbx_tipo_cuenta.setEnabled(true);
        txt_nro_cuenta.setEnabled(true);
        txt_monto.setEnabled(true);
        cbx_banco.setSelectedItem(banco);
        cbx_moneda.setSelectedItem(moneda);
        cbx_tipo_cuenta.setSelectedItem(tipo_cuenta);
        txt_nro_cuenta.setText(nro_cuenta);
        txt_monto.setText(monto);
        //System.out.println(banco+"\n"+moneda+"\n"+tipo_cuenta+"\n"+nro_cuenta+"\n"+monto);
        btn_mod.setEnabled(false);
        btn_reg.setEnabled(true);
        ventana="modificar";
        cbx_banco.requestFocus();
    }//GEN-LAST:event_btn_modActionPerformed

    private void btn_cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btn_cancelarActionPerformed

    private void cbx_bancoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbx_bancoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cbx_moneda.setEnabled(true);
            cbx_moneda.requestFocus();
        }
    }//GEN-LAST:event_cbx_bancoKeyPressed

    private void cbx_monedaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbx_monedaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cbx_tipo_cuenta.setEnabled(true);
            cbx_tipo_cuenta.requestFocus();
        }
    }//GEN-LAST:event_cbx_monedaKeyPressed

    private void cbx_tipo_cuentaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbx_tipo_cuentaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txt_nro_cuenta.setEditable(true);
            txt_nro_cuenta.requestFocus();
        }
    }//GEN-LAST:event_cbx_tipo_cuentaKeyPressed

    private void txt_nro_cuentaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_nro_cuentaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!txt_nro_cuenta.getText().isEmpty()) {
                txt_monto.setEditable(true);
                txt_monto.requestFocus();
            }
        }
    }//GEN-LAST:event_txt_nro_cuentaKeyPressed

    private void txt_montoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_montoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!txt_monto.getText().isEmpty()) {
                btn_reg.setEnabled(true);
                btn_reg.requestFocus();
            }
        }
    }//GEN-LAST:event_txt_montoKeyPressed

    private void btn_regKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btn_regKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btn_reg.doClick();
        }
    }//GEN-LAST:event_btn_regKeyPressed

    private void t_cuentasMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_t_cuentasMousePressed
        i = t_cuentas.getSelectedRow();
        btn_mod.setEnabled(true);
        btn_eli.setEnabled(true);
    }//GEN-LAST:event_t_cuentasMousePressed

    private void cbx_tipo_cuentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbx_tipo_cuentaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbx_tipo_cuentaActionPerformed

    private void txt_montoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_montoKeyTyped
        if (Character.isLetter(evt.getKeyChar())) {
            evt.consume();
        }
    }//GEN-LAST:event_txt_montoKeyTyped

    private void t_cuentasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_t_cuentasKeyPressed
        if (evt.getKeyCode()==KeyEvent.VK_SPACE) {
            if (funcion.equals("almacen")){
                String banco = t_cuentas.getValueAt(i, 3).toString();
                String cuenta = t_cuentas.getValueAt(i, 2).toString();
                frm_reg_almacen almacen = null;
                almacen.txt_banco.setText(banco);
                almacen.txt_cuenta.setText(cuenta);
                almacen.lbl_idc.setText(t_cuentas.getValueAt(i, 0).toString());
                this.dispose();
            }
        }
    }//GEN-LAST:event_t_cuentasKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_cancelar;
    private javax.swing.JButton btn_eli;
    private javax.swing.JButton btn_mod;
    private javax.swing.JButton btn_reg;
    private javax.swing.JComboBox cbx_banco;
    private javax.swing.JComboBox cbx_moneda;
    private javax.swing.JComboBox cbx_tipo_cuenta;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable t_cuentas;
    private javax.swing.JTextField txt_monto;
    private javax.swing.JTextField txt_nro_cuenta;
    // End of variables declaration//GEN-END:variables
}
