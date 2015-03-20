/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import Clases.Cl_Conectar;
import Clases.Cl_Movimiento;
import Clases.Cl_Varios;
import java.awt.Desktop;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
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
    Double suma_ing;
    Double suma_sal;
    public static String ventana = "movimientos";
    String fecha;
    public static DecimalFormat formato = new DecimalFormat("####0.00");

    /**
     * Creates new form frm_movimientos
     */
    public frm_movimientos() {
        initComponents();
        
        fecha = ven.getFechaActual();
        String query = "select * from movimiento where fec_mov = '" + fecha + "' and"
                + " idAlmacen = '" + frm_menu.alm.getId() + "' order by idMovimiento asc";
        ver_movimientos(query);
        sumar_ing_caja();
        sumar_sal_caja();
        Double total;
        total = suma_ing - suma_sal;
        txt_tot.setText(formato.format(total));
    }

    private void ver_movimientos(String query) {
        try {
            mostrar = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int fila, int columna) {
                    return false;
                }
            };
            Statement st = con.conexion();
            ResultSet rs = con.consulta(st, query);

            mostrar.addColumn("Ori / Dest");
            mostrar.addColumn("Descripcion");
            mostrar.addColumn("Fecha");
            mostrar.addColumn("Ingreso");
            mostrar.addColumn("Salida");

            //Creando las filas para el JTable
            while (rs.next()) {
                Object[] fila = new Object[5];
                if (rs.getString("destino").equals("C")) {
                    fila[0] = "CAJA";
                } else {
                    fila[0] = "BANCO";
                }
                fila[1] = rs.getObject("glosa");
                fila[2] = rs.getObject("fec_mov");
                fila[3] = rs.getObject("entrada");
                fila[4] = rs.getObject("salida");
                mostrar.addRow(fila);
            }
            con.cerrar(st);
            con.cerrar(rs);
            t_movimientos.setModel(mostrar);
            t_movimientos.getColumnModel().getColumn(0).setPreferredWidth(80);
            t_movimientos.getColumnModel().getColumn(1).setPreferredWidth(500);
            t_movimientos.getColumnModel().getColumn(2).setPreferredWidth(80);
            t_movimientos.getColumnModel().getColumn(3).setPreferredWidth(70);
            t_movimientos.getColumnModel().getColumn(4).setPreferredWidth(70);
            mostrar.fireTableDataChanged();
        } catch (SQLException e) {
            System.out.print(e);
        }

    }

    private void sumar_ing_caja() {
        int tot_filas_caja = t_movimientos.getRowCount();
        suma_ing = 0.00;
        for (int x = 0; x < tot_filas_caja; x++) {
            if (t_movimientos.getValueAt(x, 0).equals("CAJA")) {
                suma_ing += Double.parseDouble(t_movimientos.getValueAt(x, 3).toString());
            }
        }
        txt_ing.setText(formato.format(suma_ing));
    }

    private void sumar_sal_caja() {
        int tot_filas_caja = t_movimientos.getRowCount();
        suma_sal = 0.00;
        for (int x = 0; x < tot_filas_caja; x++) {
            if (t_movimientos.getValueAt(x, 0).equals("CAJA")) {
                suma_sal += Double.parseDouble(t_movimientos.getValueAt(x, 4).toString());
            }
        }
        txt_sal.setText(formato.format(suma_sal));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        t_movimientos = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txt_dni = new javax.swing.JTextField();
        txt_nom = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        rbt_ini = new javax.swing.JRadioButton();
        rbt_ing = new javax.swing.JRadioButton();
        rbt_sal = new javax.swing.JRadioButton();
        jLabel3 = new javax.swing.JLabel();
        txt_monto = new javax.swing.JTextField();
        btn_reg = new javax.swing.JButton();
        btn_clo = new javax.swing.JButton();
        btn_buse = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        btn_rep = new javax.swing.JButton();
        btn_rep_fec = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txt_mot = new javax.swing.JTextField();
        txt_ing = new javax.swing.JTextField();
        txt_sal = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txt_tot = new javax.swing.JTextField();
        txt_fecha = new javax.swing.JFormattedTextField();

        setTitle("Movimientos");

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

        jLabel1.setText("Seleccionar Empleado");

        txt_dni.setEditable(false);

        txt_nom.setEditable(false);

        jLabel2.setText("Seleccionar Operacion:");

        buttonGroup1.add(rbt_ini);
        rbt_ini.setText("Iniciar Caja Chica");
        rbt_ini.setEnabled(false);
        rbt_ini.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbt_iniActionPerformed(evt);
            }
        });

        buttonGroup1.add(rbt_ing);
        rbt_ing.setText("Registrar Ingreso de Dinero");
        rbt_ing.setEnabled(false);
        rbt_ing.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbt_ingActionPerformed(evt);
            }
        });

        buttonGroup1.add(rbt_sal);
        rbt_sal.setText("Registrar Salida de Dinero");
        rbt_sal.setEnabled(false);
        rbt_sal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbt_salActionPerformed(evt);
            }
        });

        jLabel3.setText("Monto:");

        txt_monto.setEditable(false);
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

        btn_buse.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/find.png"))); // NOI18N
        btn_buse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_buseActionPerformed(evt);
            }
        });

        jLabel4.setText("Fecha");

        btn_rep.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/clipboard_text.png"))); // NOI18N
        btn_rep.setText("Imprimir Reporte");
        btn_rep.setEnabled(false);
        btn_rep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_repActionPerformed(evt);
            }
        });

        btn_rep_fec.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/clipboard_text.png"))); // NOI18N
        btn_rep_fec.setText("Imprimir Por Fecha");
        btn_rep_fec.setEnabled(false);
        btn_rep_fec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_rep_fecActionPerformed(evt);
            }
        });

        jLabel5.setText("Total Ingresos CAJA");

        jLabel6.setText("Total Salidas CAJA");

        jLabel7.setText("Motivo:");

        txt_mot.setEditable(false);
        txt_mot.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_motKeyPressed(evt);
            }
        });

        txt_ing.setEditable(false);
        txt_ing.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        txt_sal.setEditable(false);
        txt_sal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jLabel8.setText("Total CAJA");

        txt_tot.setEditable(false);
        txt_tot.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        txt_fecha.setEditable(false);
        try {
            txt_fecha.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txt_fecha.setHorizontalAlignment(javax.swing.JTextField.CENTER);
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
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btn_rep)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_rep_fec)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_clo))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addComponent(txt_mot))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txt_dni, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_nom)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btn_buse))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(rbt_ing)
                                            .addComponent(rbt_ini))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 213, Short.MAX_VALUE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel4)
                                            .addComponent(jLabel3))
                                        .addGap(26, 26, 26)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txt_monto)
                                            .addComponent(txt_fecha, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(rbt_sal)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btn_reg))))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(txt_ing, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_sal, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_tot, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(btn_buse, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_nom, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_dni, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_monto, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rbt_ini, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rbt_ing, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbt_sal, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_reg, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_mot, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_clo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_rep, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_rep_fec, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rbt_ingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbt_ingActionPerformed
        txt_monto.setEditable(false);
        txt_mot.setText("");
        txt_mot.setEditable(true);
        txt_mot.requestFocus();

    }//GEN-LAST:event_rbt_ingActionPerformed

    private void rbt_iniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbt_iniActionPerformed
        txt_mot.setText("MONTO INICIAL DE CAJA CHICA");
        txt_mot.setEditable(false);
        txt_monto.setEditable(true);
        txt_monto.requestFocus();
    }//GEN-LAST:event_rbt_iniActionPerformed

    private void rbt_salActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbt_salActionPerformed
        txt_monto.setEditable(false);
        txt_mot.setText("");
        txt_mot.setEditable(true);
        txt_mot.requestFocus();
    }//GEN-LAST:event_rbt_salActionPerformed

    private void btn_cloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cloActionPerformed

        this.dispose();
    }//GEN-LAST:event_btn_cloActionPerformed

    private void btn_buseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_buseActionPerformed
        // if (frm_menu.usu.getPcaj_mov().equals("1")) {
        frm_ver_empleado empleado = new frm_ver_empleado();
        empleado.ventana = "movimiento";
        ven.llamar_ventana(empleado);
        //} else {
        //  JOptionPane.showMessageDialog(null, "Ud. No tiene permisos");
        //}
    }//GEN-LAST:event_btn_buseActionPerformed

    private void txt_montoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_montoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txt_fecha.setEditable(true);
            txt_fecha.setText(ven.fechaformateada(ven.getFechaActual()));
            txt_fecha.requestFocus();
        }
    }//GEN-LAST:event_txt_montoKeyPressed

    private void btn_regKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btn_regKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btn_reg.doClick();
        }
    }//GEN-LAST:event_btn_regKeyPressed

    private void txt_montoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_montoKeyTyped
        if (txt_monto.getText().length() == 8) {
            evt.consume();
        }
        char car = evt.getKeyChar();
        if ((car < '0' || car > '9') && car != '.') {
            evt.consume();
        }
    }//GEN-LAST:event_txt_montoKeyTyped

    private void llenar() {
        mov.setGlosa(txt_mot.getText() + " - " + txt_nom.getText());
        mov.setIngreso(Double.parseDouble(txt_monto.getText()));
        mov.setEgreso(Double.parseDouble(txt_monto.getText()));
        mov.setFec_mov(ven.fechabase(txt_fecha.getText()));
    }

    private void limpiar() {
        txt_dni.setText("");
        txt_nom.setText("");
        rbt_ini.setEnabled(false);
        rbt_ing.setEnabled(false);
        rbt_sal.setEnabled(false);
        txt_mot.setText("");
        txt_mot.setEditable(false);
        txt_monto.setEditable(false);
        txt_monto.setText("");
        txt_fecha.setText("");
        txt_fecha.setEditable(false);
        btn_reg.setEnabled(false);
        rbt_ini.setSelected(false);
        rbt_ing.setSelected(false);
        rbt_sal.setSelected(false);
        btn_buse.requestFocus();

    }
    private void btn_regActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_regActionPerformed
        llenar();

        if (rbt_ini.isSelected() || rbt_ing.isSelected()) {
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

        String query = "select * from movimiento where fec_mov = '" + fecha + "' and"
                + " idAlmacen = '" + frm_menu.alm.getId() + "' order by idMovimiento asc";
        ver_movimientos(query);
        sumar_ing_caja();
        sumar_sal_caja();
        Double total;
        total = suma_ing - suma_sal;
        txt_tot.setText(formato.format(total));

        limpiar();
    }//GEN-LAST:event_btn_regActionPerformed

    private void txt_motKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_motKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (txt_mot.getText().length() > 10) {
                txt_monto.setEditable(true);
                txt_monto.requestFocus();
            }
        }
    }//GEN-LAST:event_txt_motKeyPressed

    private void btn_repActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_repActionPerformed
//        Connection st = con.conx();
//        String fecha = ven.getFechaActual();
//        Map<String, Object> parametros = new HashMap<>();
//        parametros.put("fecha", fecha);
//
//        try {
//            JasperReport jasperReport;
//            JasperPrint jasperPrint;
//            jasperReport = JasperCompileManager.compileReport("Reports//rpt_ver_movimiento_hoy.jrxml");
//            jasperPrint = JasperFillManager.fillReport(
//                    jasperReport, parametros, st);
//            JasperExportManager.exportReportToPdfFile(
//                    jasperPrint, "Reports/rpt_ver_movimiento_" + fecha + ".pdf");
//
//            try {
//                File file = new File("Reports/rpt_ver_movimiento_" + fecha + ".pdf");
//                Desktop.getDesktop().open(file);
//            } catch (IOException e) {
//                System.out.print(e);
//                JOptionPane.showMessageDialog(null, e);
//            }
//
//        } catch (JRException ex) {
//            System.out.print(ex);
//            JOptionPane.showMessageDialog(null, ex);
//
//        }
    }//GEN-LAST:event_btn_repActionPerformed

    private void btn_rep_fecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_rep_fecActionPerformed
        // frm_rpt_fec rpt_fec = new frm_rpt_fec();
        // rpt_fec.rpt = "mov_det";
        // ven.llamar_ventana(rpt_fec);
        this.dispose();
    }//GEN-LAST:event_btn_rep_fecActionPerformed

    private void txt_fechaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_fechaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btn_reg.setEnabled(true);
            btn_reg.requestFocus();
        }
    }//GEN-LAST:event_txt_fechaKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JButton btn_buse;
    private javax.swing.JButton btn_clo;
    private javax.swing.JButton btn_reg;
    private javax.swing.JButton btn_rep;
    private javax.swing.JButton btn_rep_fec;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JRadioButton rbt_ing;
    public static javax.swing.JRadioButton rbt_ini;
    public static javax.swing.JRadioButton rbt_sal;
    private javax.swing.JTable t_movimientos;
    public static javax.swing.JTextField txt_dni;
    private javax.swing.JFormattedTextField txt_fecha;
    private javax.swing.JTextField txt_ing;
    private javax.swing.JTextField txt_monto;
    public static javax.swing.JTextField txt_mot;
    public static javax.swing.JTextField txt_nom;
    private javax.swing.JTextField txt_sal;
    private javax.swing.JTextField txt_tot;
    // End of variables declaration//GEN-END:variables
}
