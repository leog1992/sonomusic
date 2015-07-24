package Forms;

import Clases.Cl_Conectar;
import Clases.Cl_Pedido;
import Clases.Cl_Varios;
import Vistas.frm_ver_compras_prod;
import Vistas.frm_ver_letras_pedido;
import java.awt.event.KeyEvent;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.HashMap;
import java.util.Map;
import sonomusic.frm_menu;

public class frm_reg_pago_venta extends javax.swing.JInternalFrame {

    Cl_Conectar con = new Cl_Conectar();
    Cl_Varios ven = new Cl_Varios();
    public static Cl_Pedido ped = new Cl_Pedido();
    public static Double deuda = 0.00;
    public static Double pagado = 0.00;
    public static Double restante = 0.00;
    public static String funcion = "pagar";
    public static String glosa = "";
    Double queda = 0.00;
    Double real = 0.00;
    DecimalFormatSymbols simbolo = new DecimalFormatSymbols();
    DecimalFormat formato = null;

    public frm_reg_pago_venta() {
        initComponents();
        simbolo.setDecimalSeparator('.');
        formato = new DecimalFormat("####0.00", simbolo);
        txt_fec.setText(ven.fechaformateada(ven.getFechaActual()));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txt_dni = new javax.swing.JTextField();
        txt_raz = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txt_deu = new javax.swing.JTextField();
        txt_pag = new javax.swing.JTextField();
        txt_sal = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txt_real = new javax.swing.JTextField();
        txt_rest = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txt_tido = new javax.swing.JTextField();
        btn_add = new javax.swing.JButton();
        btn_clo = new javax.swing.JButton();
        txt_fec = new javax.swing.JFormattedTextField();
        cbx_tipa = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(254, 254, 254));
        setClosable(true);
        setTitle("Pago de Venta");

        jLabel1.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(212, 2, 2));
        jLabel1.setText("Cliente:");
        jLabel1.setFocusable(false);

        jLabel3.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(212, 2, 2));
        jLabel3.setText("Fecha:");
        jLabel3.setFocusable(false);

        txt_dni.setEditable(false);
        txt_dni.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_dni.setFocusable(false);

        txt_raz.setEditable(false);
        txt_raz.setFocusable(false);

        jLabel2.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(212, 2, 2));
        jLabel2.setText("Deuda Total:");
        jLabel2.setFocusable(false);

        jLabel4.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(212, 2, 2));
        jLabel4.setText("Pagos Realizados:");
        jLabel4.setFocusable(false);

        txt_deu.setEditable(false);
        txt_deu.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_deu.setFocusable(false);

        txt_pag.setEditable(false);
        txt_pag.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_pag.setFocusable(false);

        txt_sal.setEditable(false);
        txt_sal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_sal.setFocusable(false);

        jLabel5.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(212, 2, 2));
        jLabel5.setText("Saldo Actual:");
        jLabel5.setFocusable(false);

        jLabel6.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(212, 2, 2));
        jLabel6.setText("Pago a Realizar:");
        jLabel6.setFocusable(false);

        jLabel7.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(212, 2, 2));
        jLabel7.setText("Restante");
        jLabel7.setFocusable(false);

        txt_real.setEditable(false);
        txt_real.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_real.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_realKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_realKeyTyped(evt);
            }
        });

        txt_rest.setEditable(false);
        txt_rest.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_rest.setFocusable(false);

        jLabel8.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(212, 2, 2));
        jLabel8.setText("Tipo Documento:");
        jLabel8.setFocusable(false);

        txt_tido.setEditable(false);
        txt_tido.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_tido.setFocusable(false);

        btn_add.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/accept.png"))); // NOI18N
        btn_add.setText("Realizar");
        btn_add.setEnabled(false);
        btn_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_addActionPerformed(evt);
            }
        });
        btn_add.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btn_addKeyPressed(evt);
            }
        });

        btn_clo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/cancel.png"))); // NOI18N
        btn_clo.setText("Salir");
        btn_clo.setFocusable(false);
        btn_clo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cloActionPerformed(evt);
            }
        });

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

        cbx_tipa.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "EFECTIVO", "TARJETA" }));
        cbx_tipa.setEnabled(false);
        cbx_tipa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cbx_tipaKeyPressed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(212, 2, 2));
        jLabel9.setText("Tipo de Pago");
        jLabel9.setFocusable(false);

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
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txt_dni, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_raz))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txt_fec, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_tido))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_deu, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_pag, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_sal, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 68, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txt_rest, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                            .addComponent(txt_real, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                            .addComponent(cbx_tipa, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btn_add, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_clo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_dni, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_raz, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_tido, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_fec, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbx_tipa, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txt_real, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txt_rest, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2)
                                    .addComponent(txt_deu, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(txt_pag, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(btn_add, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_sal, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(btn_clo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_cloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cloActionPerformed
        if (funcion.equals("productos")) {
            frm_ver_compras_prod compras = new frm_ver_compras_prod();
            ven.llamar_ventana(compras);
        }
        funcion = "pagar";
        this.dispose();
    }//GEN-LAST:event_btn_cloActionPerformed

    private void btn_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_addActionPerformed
        String fecha = ven.fechabase(txt_fec.getText());
        String tipa = cbx_tipa.getSelectedItem().toString();
        ped.setId_ped(frm_ver_letras_pedido.id);
        Double monto;
        if (queda <= 0.00) {
            try {
                Statement st = con.conexion();
                String ins_pago = "insert into letras_pedido Values (null, '" + restante + "', '" + fecha + "', '" + ped.getId_ped() + "', '" + tipa + "')";
                con.actualiza(st, ins_pago);
                con.cerrar(st);
            } catch (Exception ex) {
                System.out.print(ex);
            }
            monto = restante;
            try {
                Statement st = con.conexion();
                String upd_com = "update pedido set est_ped = '4' where idPedido = '" + ped.getId_ped() + "'";
                con.actualiza(st, upd_com);
                con.cerrar(st);
            } catch (Exception ex) {
                System.out.print(ex);
            }

        } else {
            monto = real;
            pagado = Double.parseDouble(txt_pag.getText());
            try {
                Statement st = con.conexion();
                String ins_pago = "insert into letras_pedido Values (null, '" + real + "', '" + fecha + "', '" + ped.getId_ped() + "', '" + tipa + "')";
                con.actualiza(st, ins_pago);
                con.cerrar(st);
            } catch (Exception ex) {
                System.out.print(ex);
            }
            pagado = pagado + monto;
            System.out.println(pagado);

            // TICKET DE SEPARACION
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("idped", ped.getId_ped());
            parametros.put("Adelanto", monto);
            parametros.put("Acumulado", pagado);
            String filename = "rpt_ticket_separacion";
            ven.imp_reporte(filename, parametros);
        }
        //REGISTRO DE MOVIMIENTO EN CAJA
        if (tipa.equals("EFECTIVO")) {
            String glosa = "CUOTA POR SEPARACION / " + txt_tido.getText() + " / " + txt_dni.getText() + " - " + txt_raz.getText();
            try {
                Statement st = con.conexion();
                String add_mov = "insert into movimiento Values (null, '" + glosa + "', '" + fecha + "' , '" + monto + "' "
                        + ", '0.00', '" + frm_menu.usu.getNick() + "','" + frm_menu.alm.getId() + "', 'C', '" + frm_menu.caja.getId() + "')";
                con.actualiza(st, add_mov);
                con.cerrar(st);
            } catch (Exception ex) {
                System.err.print("Error en:" + ex.getLocalizedMessage());
            }
        } else {
            String glosa = "CUOTA POR SEPARACION / " + txt_tido.getText() + " / " + txt_dni.getText() + " - " + txt_raz.getText();
            try {
                Statement st = con.conexion();
                String add_mov = "insert into movimiento Values (null, '" + glosa + "', '" + fecha + "' , '" + monto + "' "
                        + ", '0.00', '" + frm_menu.usu.getNick() + "','" + frm_menu.alm.getId() + "', 'B', '" + frm_menu.caja.getId() + "')";
                con.actualiza(st, add_mov);
                con.cerrar(st);
            } catch (Exception ex) {
                System.err.print("Error en:" + ex.getLocalizedMessage());
            }
        }
        this.dispose();

    }//GEN-LAST:event_btn_addActionPerformed

    private void btn_addKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btn_addKeyPressed
//        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
//            btn_add.doClick();
//        }
    }//GEN-LAST:event_btn_addKeyPressed

    private void txt_fecKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_fecKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (txt_fec.getText().trim().length() == 10) {
                cbx_tipa.setEnabled(true);
                cbx_tipa.requestFocus();
            }
        }
    }//GEN-LAST:event_txt_fecKeyPressed

    private void txt_realKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_realKeyTyped
        if (txt_real.getText().length() == 8) {
            evt.consume();
        }
        char car = evt.getKeyChar();
        if ((car < '0' || car > '9') && car != '.') {
            evt.consume();
        }
    }//GEN-LAST:event_txt_realKeyTyped

    private void txt_realKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_realKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (txt_real.getText().length() > 1) {
                real = Double.parseDouble(txt_real.getText());
                queda = restante - real;
                txt_rest.setText(formato.format(queda));
                btn_add.setEnabled(true);
                btn_add.requestFocus();
            }
        }
    }//GEN-LAST:event_txt_realKeyPressed

    private void cbx_tipaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbx_tipaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (txt_fec.getText().trim().length() == 10) {
                txt_real.setEditable(true);
                txt_real.requestFocus();
            }
        }
    }//GEN-LAST:event_cbx_tipaKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_add;
    private javax.swing.JButton btn_clo;
    private javax.swing.JComboBox cbx_tipa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    public static javax.swing.JTextField txt_deu;
    public static javax.swing.JTextField txt_dni;
    public static javax.swing.JFormattedTextField txt_fec;
    public static javax.swing.JTextField txt_pag;
    public static javax.swing.JTextField txt_raz;
    public static javax.swing.JTextField txt_real;
    private javax.swing.JTextField txt_rest;
    public static javax.swing.JTextField txt_sal;
    public static javax.swing.JTextField txt_tido;
    // End of variables declaration//GEN-END:variables
}
