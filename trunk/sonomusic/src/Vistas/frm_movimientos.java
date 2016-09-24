/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import Clases.Cl_Conectar;
import Clases.Cl_Movimiento;
import Clases.Cl_Varios;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import sonomusic.frm_menu;

/**
 *
 * @author pc
 */
public class frm_movimientos extends javax.swing.JInternalFrame {

    Cl_Conectar con = new Cl_Conectar();
    Cl_Varios ven = new Cl_Varios();
    Cl_Movimiento mov = new Cl_Movimiento();
    DefaultTableModel mostrar;
    double suma_ingc;
    double suma_salc;
    double suma_ingb;
    double suma_salb;
    public static String ventana = "movimientos";
    String fecha;

    /**
     * Creates new form frm_movimientos
     */
    public frm_movimientos() {
        initComponents();
        fecha = ven.getFechaActual();
        mostrar = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int fila, int columna) {
                return false;
            }
        };
        //tipo_mov (compra, venta, pago, cobro, pago_trabajador, adelanto_trabajador,
        // otras_salidas, otros_ingresos), moneda (soles, dolares, euros) , destino 
        // (efectivo_tienda, deposito_banco)
        mostrar.addColumn("Ori / Dest");
        mostrar.addColumn("Descripcion");
        mostrar.addColumn("Fecha");
        mostrar.addColumn("Ingreso");
        mostrar.addColumn("Salida");

        String query = "select * from movimiento where fec_mov = '" + fecha + "' and"
                + " idAlmacen = '" + frm_menu.alm.getId() + "' order by idMovimiento asc";
        ver_movimientos(query);
        t_movimientos.setModel(mostrar);
        sumar_ing_caja();
        sumar_sal_caja();
        Double totalc;
        totalc = suma_ingc - suma_salc;
        txt_tot.setText(ven.formato_numero(totalc));

        t_movimientos.getColumnModel().getColumn(0).setPreferredWidth(80);
        t_movimientos.getColumnModel().getColumn(1).setPreferredWidth(500);
        t_movimientos.getColumnModel().getColumn(2).setPreferredWidth(80);
        t_movimientos.getColumnModel().getColumn(3).setPreferredWidth(70);
        t_movimientos.getColumnModel().getColumn(4).setPreferredWidth(70);
        ven.centrar_celda(t_movimientos, 2);
        ven.derecha_celda(t_movimientos, 3);
        ven.derecha_celda(t_movimientos, 4);
        mostrar.fireTableDataChanged();
        t_movimientos.updateUI();

    }

    private void ver_movimientos(String query) {
        try {
            DefaultTableModel modelo = (DefaultTableModel) t_movimientos.getModel();
            while (modelo.getRowCount() > 0) {
                modelo.removeRow(0);
            }

            Statement st = con.conexion();
            ResultSet rs = con.consulta(st, query);

            //Creando las filas para el JTable
            while (rs.next()) {
                Object[] fila = new Object[5];
                if (rs.getString("destino").equals("C")) {
                    fila[0] = "CAJA";
                } else {
                    fila[0] = "BANCO";
                }
                fila[1] = rs.getObject("glosa");
                fila[2] = ven.fechaformateada(rs.getString("fec_mov"));
                fila[3] = rs.getObject("entrada");
                fila[4] = rs.getObject("salida");
                mostrar.addRow(fila);
            }
            con.cerrar(st);
            con.cerrar(rs);
        } catch (SQLException e) {
            System.out.print(e);
        }

    }

    private void sumar_ing_caja() {
        int tot_filas_caja = t_movimientos.getRowCount();
        suma_ingc = 0.00;
        suma_ingb = 0.00;
        if (tot_filas_caja > 0) {
            for (int x = 0; x < tot_filas_caja; x++) {
                if (t_movimientos.getValueAt(x, 0).equals("CAJA")) {
                    suma_ingc += Double.parseDouble(t_movimientos.getValueAt(x, 3).toString());
                }
            }
            txt_ing.setText(ven.formato_numero(suma_ingc));
        }
    }

    private void sumar_sal_caja() {
        int tot_filas_caja = t_movimientos.getRowCount();
        suma_salc = 0.00;
        if (tot_filas_caja > 0) {
            for (int x = 0; x < tot_filas_caja; x++) {
                if (t_movimientos.getValueAt(x, 0).equals("CAJA")) {
                    suma_salc += Double.parseDouble(t_movimientos.getValueAt(x, 4).toString());
                }
            }
        }
            txt_sal.setText(ven.formato_numero(suma_salc));
        }

        /**
         * This method is called from within the constructor to initialize the
         * form. WARNING: Do NOT modify this code. The content of this method is
         * always regenerated by the Form Editor.
         */
        @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        t_movimientos = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        rbt_ing = new javax.swing.JRadioButton();
        rbt_sal = new javax.swing.JRadioButton();
        jLabel3 = new javax.swing.JLabel();
        txt_monto = new javax.swing.JTextField();
        btn_reg = new javax.swing.JButton();
        btn_clo = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txt_mot = new javax.swing.JTextField();
        txt_ing = new javax.swing.JTextField();
        txt_sal = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txt_tot = new javax.swing.JTextField();
        txt_fecha = new javax.swing.JFormattedTextField();

        setBackground(new java.awt.Color(254, 254, 254));
        setClosable(true);
        setTitle("Movimientos");

        jScrollPane1.setBackground(new java.awt.Color(254, 254, 254));

        t_movimientos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"1", "LUIS OYANGUREN GIRON", "COMPRA DE FILTROS", "2014-11-09", "0.00", "135.00"},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Id.", "Usuario", "Descripcion", "Fecha", "Ingreso", "Salida"
            }
        ));
        jScrollPane1.setViewportView(t_movimientos);
        if (t_movimientos.getColumnModel().getColumnCount() > 0) {
            t_movimientos.getColumnModel().getColumn(0).setPreferredWidth(10);
            t_movimientos.getColumnModel().getColumn(1).setPreferredWidth(40);
            t_movimientos.getColumnModel().getColumn(2).setPreferredWidth(180);
            t_movimientos.getColumnModel().getColumn(3).setPreferredWidth(40);
            t_movimientos.getColumnModel().getColumn(4).setPreferredWidth(30);
            t_movimientos.getColumnModel().getColumn(5).setPreferredWidth(40);
        }

        jLabel2.setForeground(new java.awt.Color(212, 2, 2));
        jLabel2.setText("Seleccionar Operacion:");

        buttonGroup1.add(rbt_ing);
        rbt_ing.setText("Registrar Ingreso de Dinero");
        rbt_ing.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                rbt_ingKeyPressed(evt);
            }
        });

        buttonGroup1.add(rbt_sal);
        rbt_sal.setSelected(true);
        rbt_sal.setText("Registrar Salida de Dinero");
        rbt_sal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                rbt_salKeyPressed(evt);
            }
        });

        jLabel3.setForeground(new java.awt.Color(212, 2, 2));
        jLabel3.setText("Monto:");

        txt_monto.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_monto.setEnabled(false);
        txt_monto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_montoKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_montoKeyTyped(evt);
            }
        });

        btn_reg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/add.png"))); // NOI18N
        btn_reg.setText("Aceptar");
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

        btn_clo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/cancel.png"))); // NOI18N
        btn_clo.setText("Cerrar");
        btn_clo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cloActionPerformed(evt);
            }
        });

        jLabel4.setForeground(new java.awt.Color(212, 2, 2));
        jLabel4.setText("Fecha");

        jLabel5.setForeground(new java.awt.Color(212, 2, 2));
        jLabel5.setText("Total Otros Ingresos:");

        jLabel6.setForeground(new java.awt.Color(212, 2, 2));
        jLabel6.setText("Total Otros Egresos:");

        jLabel7.setText("Motivo:");

        txt_mot.setEnabled(false);
        txt_mot.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_motKeyPressed(evt);
            }
        });

        txt_ing.setEditable(false);
        txt_ing.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        txt_sal.setEditable(false);
        txt_sal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jLabel8.setForeground(new java.awt.Color(212, 2, 2));
        jLabel8.setText("Total Otros Gastos:");

        txt_tot.setEditable(false);
        txt_tot.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        try {
            txt_fecha.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txt_fecha.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_fecha.setEnabled(false);
        txt_fecha.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_fechaKeyPressed(evt);
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
                    .addComponent(btn_clo, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel5)
                        .addGap(17, 17, 17)
                        .addComponent(txt_ing, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(jLabel6)
                        .addGap(12, 12, 12)
                        .addComponent(txt_sal, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 84, Short.MAX_VALUE)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_tot, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(rbt_ing)
                                    .addComponent(rbt_sal))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel3))
                                .addGap(26, 26, 26))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_mot)
                                .addGap(31, 31, 31)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btn_reg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txt_monto)
                            .addComponent(txt_fecha, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_monto, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(rbt_ing, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rbt_sal, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_mot, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btn_reg, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_ing, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_sal, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_tot, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_clo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_cloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cloActionPerformed

        this.dispose();
    }//GEN-LAST:event_btn_cloActionPerformed

    private void txt_montoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_montoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String monto = txt_monto.getText();
            if (!monto.equals("")) {
                if (ven.esDecimal(monto)) {
                    double numero = Double.parseDouble(txt_monto.getText());
                    txt_monto.setText(ven.formato_numero(numero));
                    txt_fecha.setEnabled(true);
                    txt_fecha.setText(ven.fechaformateada(ven.getFechaActual()));
                    txt_fecha.requestFocus();
                } else {
                    JOptionPane.showMessageDialog(null, "ESTE NO ES UN NUMERO");
                    txt_monto.setText("");
                    txt_monto.requestFocus();
                }
            }
        }
    }//GEN-LAST:event_txt_montoKeyPressed

    private void btn_regKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btn_regKeyPressed
//        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
//            btn_reg.doClick();
//        }
    }//GEN-LAST:event_btn_regKeyPressed

    private void txt_montoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_montoKeyTyped
        ven.solo_precio(evt);
        ven.limitar_caracteres(evt, txt_monto, 10);
    }//GEN-LAST:event_txt_montoKeyTyped

    private void llenar() {
        mov.setGlosa(txt_mot.getText());
        mov.setIngreso(Double.parseDouble(txt_monto.getText()));
        mov.setEgreso(Double.parseDouble(txt_monto.getText()));
        mov.setFec_mov(ven.fechabase(txt_fecha.getText()));
    }

    private void limpiar() {
        rbt_ing.setEnabled(true);
        rbt_sal.setEnabled(true);
        txt_mot.setText("");
        txt_mot.setEditable(false);
        txt_monto.setEditable(false);
        txt_monto.setText("");
        txt_fecha.setText("");
        txt_fecha.setEditable(false);
        btn_reg.setEnabled(false);
        rbt_ing.setSelected(false);
        rbt_sal.setSelected(true);
        rbt_sal.requestFocus();

    }
    private void btn_regActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_regActionPerformed
        llenar();

        if (rbt_ing.isSelected()) {
            Statement st = con.conexion();
            String ins_ini = "insert into movimiento values (null, '" + mov.getGlosa() + "', '" + mov.getFec_mov() + "', "
                    + "'" + mov.getIngreso() + "', '0.00', '" + frm_menu.lbl_user.getText() + "', '" + frm_menu.alm.getId() + "', "
                    + "'C', '" + frm_menu.caja.getId() + "')";
            con.actualiza(st, ins_ini);
            con.cerrar(st);
        }

        if (rbt_sal.isSelected()) {
            Statement st = con.conexion();
            String ins_sal = "insert into movimiento values (null, '" + mov.getGlosa() + "', '" + mov.getFec_mov() + "', "
                    + "'0.00','" + mov.getEgreso() + "', '" + frm_menu.lbl_user.getText() + "', '" + frm_menu.alm.getId() + "', "
                    + "'C', '" + frm_menu.caja.getId() + "')";
            con.actualiza(st, ins_sal);
            con.cerrar(st);
        }
        t_movimientos.removeAll();
        String query = "select * from movimiento where fec_mov = '" + fecha + "' and"
                + " idAlmacen = '" + frm_menu.alm.getId() + "' order by idMovimiento asc";
        ver_movimientos(query);
        sumar_ing_caja();
        sumar_sal_caja();
        Double totalc;
        totalc = suma_ingc - suma_salc;
        txt_tot.setText(ven.formato_numero(totalc));

        limpiar();
    }//GEN-LAST:event_btn_regActionPerformed

    private void txt_motKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_motKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (txt_mot.getText().length() > 3) {
                txt_monto.setEnabled(true);
                txt_monto.requestFocus();
            }
        }
    }//GEN-LAST:event_txt_motKeyPressed

    private void txt_fechaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_fechaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (txt_fecha.getText().length() == 10) {
                btn_reg.setEnabled(true);
                btn_reg.requestFocus();
            }
        }
    }//GEN-LAST:event_txt_fechaKeyPressed

    private void rbt_ingKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_rbt_ingKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            rbt_sal.setSelected(true);
            rbt_sal.requestFocus();
        }
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txt_mot.setEnabled(true);
            txt_mot.requestFocus();
        }
    }//GEN-LAST:event_rbt_ingKeyPressed

    private void rbt_salKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_rbt_salKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_UP) {
            rbt_ing.setSelected(true);
            rbt_ing.requestFocus();
        }
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txt_mot.setEnabled(true);
            txt_mot.requestFocus();
        }
    }//GEN-LAST:event_rbt_salKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_clo;
    private javax.swing.JButton btn_reg;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JRadioButton rbt_ing;
    public static javax.swing.JRadioButton rbt_sal;
    private javax.swing.JTable t_movimientos;
    private javax.swing.JFormattedTextField txt_fecha;
    private javax.swing.JTextField txt_ing;
    private javax.swing.JTextField txt_monto;
    public static javax.swing.JTextField txt_mot;
    private javax.swing.JTextField txt_sal;
    private javax.swing.JTextField txt_tot;
    // End of variables declaration//GEN-END:variables
}
