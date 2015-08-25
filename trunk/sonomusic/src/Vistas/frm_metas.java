package Vistas;

import Clases.Cl_Cargo;
import Clases.Cl_Conectar;
import Clases.Cl_Meta;
import Clases.Cl_Varios;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class frm_metas extends javax.swing.JInternalFrame {

    Cl_Conectar con = new Cl_Conectar();
    Cl_Varios ven = new Cl_Varios();
    Cl_Meta met = new Cl_Meta();
    Cl_Cargo car = new Cl_Cargo();
    public static DefaultTableModel modelo;
    int i;

    public frm_metas() {
        initComponents();
        txt_fec_ini.setText(ven.fechaformateada(ven.getFechaActual()));
        txt_fec_fin.setText(ven.fechaformateada(ven.getFechaActual()));
        String sql = "select nom_alm from almacen order by idAlmacen asc";
        cargar_tiendas(sql);
        String sql2 = "select m.idMetas, m.monto, m.estado, m.fec_inicio, m.fec_fin, "
                + "a.nom_alm from metas as m inner join almacen as a on m.idalmacen = a.idAlmacen order by idMetas asc;";
        ver_metas(sql2);
    }

    void cargar_tiendas(String sql) {
        try {
            Statement st = con.conexion();
            ResultSet rs = con.consulta(st, sql);
            while (rs.next()) {
                cbx_tienda.addItem(rs.getString("nom_alm"));
            }
            con.cerrar(rs);
            con.cerrar(st);
        } catch (Exception e) {
            System.out.println("error " + e + "en " + e.getLocalizedMessage());
        }
    }

    private void ver_metas(String sql) {
        modelo = new DefaultTableModel() {

            @Override
            public boolean isCellEditable(int i, int i1) {
                return false; //To change body of generated methods, choose Tools | Templates.
            }
        };
        try {
            Statement st = con.conexion();
            ResultSet rs = con.consulta(st, sql);
            modelo.addColumn("Id");
            modelo.addColumn("Monto");
            modelo.addColumn("Fecha Inicio");
            modelo.addColumn("Fecha Fin");
            modelo.addColumn("Tienda");
            modelo.addColumn("Estado");

            Object dato[] = new Object[6];
            while (rs.next()) {
                dato[0] = rs.getObject("idMetas");
                dato[1] = rs.getObject("monto");
                dato[2] = ven.fechaformateada(rs.getString("fec_inicio"));
                dato[3] = ven.fechaformateada(rs.getString("fec_fin"));
                dato[4] = rs.getObject("nom_alm");
                String est = rs.getString("estado");
                if (est.equals("1")) {
                    dato[5] = "Activo";
                } else {
                    dato[5] = " - ";
                }
                modelo.addRow(dato);
            }
            con.cerrar(rs);
            con.cerrar(st);
            t_metas.setModel(modelo);
            t_metas.getColumnModel().getColumn(0).setPreferredWidth(20);
            t_metas.getColumnModel().getColumn(1).setPreferredWidth(80);
            t_metas.getColumnModel().getColumn(2).setPreferredWidth(80);
            t_metas.getColumnModel().getColumn(3).setPreferredWidth(80);
            t_metas.getColumnModel().getColumn(4).setPreferredWidth(120);
            t_metas.getColumnModel().getColumn(5).setPreferredWidth(80);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en: " + e.getLocalizedMessage());
        }

    }

    void limpiar() {
        txt_monto.setText("");
        txt_fec_ini.setText("");
        txt_fec_ini.setEditable(false);
        txt_fec_ini.setText(ven.fechaformateada(ven.getFechaActual()));
        txt_fec_fin.setText("");
        txt_fec_fin.setEditable(false);
        cbx_tienda.setSelectedIndex(-1);
        cbx_tienda.setEnabled(false);
        txt_monto.requestFocus();
    }

    void llenar() {
        met.setMonto(Double.parseDouble(txt_monto.getText()));
        met.setFec_ini(ven.fechabase(txt_fec_ini.getText()));
        met.setFec_fin(ven.fechabase(txt_fec_fin.getText()));
        car.setId(cbx_tienda.getSelectedIndex() + 1);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txt_monto = new javax.swing.JTextField();
        txt_fec_ini = new javax.swing.JFormattedTextField();
        txt_fec_fin = new javax.swing.JFormattedTextField();
        cbx_tienda = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        t_metas = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        btn_reg = new javax.swing.JButton();

        setBackground(new java.awt.Color(254, 254, 254));
        setClosable(true);
        setTitle("Programacion de Metas");
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

        jLabel2.setForeground(new java.awt.Color(212, 2, 2));
        jLabel2.setText("Monto:");

        jLabel3.setForeground(new java.awt.Color(212, 2, 2));
        jLabel3.setText("Fecha de Inicio:");

        jLabel4.setForeground(new java.awt.Color(212, 2, 2));
        jLabel4.setText("Fecha de Fin:");

        jLabel5.setForeground(new java.awt.Color(212, 2, 2));
        jLabel5.setText("Tienda:");

        txt_monto.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
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

        cbx_tienda.setEnabled(false);
        cbx_tienda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbx_tiendaActionPerformed(evt);
            }
        });

        jScrollPane1.setBackground(new java.awt.Color(254, 254, 254));

        t_metas.setModel(new javax.swing.table.DefaultTableModel(
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
        t_metas.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        t_metas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                t_metasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(t_metas);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/cross.png"))); // NOI18N
        jButton1.setText("Eliminar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbx_tienda, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_monto, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(36, 36, 36)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_fec_fin, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_fec_ini, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 255, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_reg)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton2))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_fec_ini, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_fec_fin, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_monto, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbx_tienda, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_reg, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_montoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_montoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_montoActionPerformed

    private void txt_fec_iniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_fec_iniActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_fec_iniActionPerformed

    private void txt_fec_finActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_fec_finActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_fec_finActionPerformed

    private void cbx_tiendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbx_tiendaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbx_tiendaActionPerformed

    private void formInternalFrameActivated(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameActivated
        txt_monto.requestFocus();
    }//GEN-LAST:event_formInternalFrameActivated

    private void btn_regActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_regActionPerformed

        llenar();
        try {
            Statement st = con.conexion();
            String query = "insert into metas values(null,'" + met.getMonto() + "','" + met.getEst() + "', '" + met.getFec_ini() + "', "
                    + "'" + met.getFec_fin() + "','" + car.getId() + "')";
            con.actualiza(st, query);
            con.cerrar(st);
            //JOptionPane.showMessageDialog(null, "Operacion realizada con exito");
            //mostrar
            String sql2 = "select m.idMetas, m.monto, m.estado, m.fec_inicio, m.fec_fin, "
                    + "a.nom_alm from metas as m inner join almacen as a on m.idalmacen = a.idAlmacen order by idMetas asc;";
            ver_metas(sql2);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error " + e + "en: " + e.getLocalizedMessage());
        }
        limpiar();

    }//GEN-LAST:event_btn_regActionPerformed

    private void txt_montoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_montoKeyPressed
        if (!txt_monto.getText().isEmpty()) {
            if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                txt_fec_ini.setEditable(true);
                txt_fec_ini.requestFocus();
            }
        }
    }//GEN-LAST:event_txt_montoKeyPressed

    private void txt_fec_iniKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_fec_iniKeyPressed
        if (txt_fec_ini.getText().trim().length() == 10) {
            if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd");
                String strFecha = txt_fec_ini.getText();
                Date fecha = null;
                try {
                    fecha = formatoDelTexto.parse(ven.fechabase(strFecha));
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(fecha);
                calendar.add(Calendar.DAY_OF_YEAR, 30);
                SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd");
                txt_fec_fin.setText(ven.fechaformateada(formateador.format(calendar.getTime())));
                txt_fec_fin.setEditable(true);
                txt_fec_fin.requestFocus();
            }
        }
    }//GEN-LAST:event_txt_fec_iniKeyPressed

    private void txt_fec_finKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_fec_finKeyPressed
        if (txt_fec_fin.getText().trim().length() == 10) {
            if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                cbx_tienda.setEnabled(true);
                cbx_tienda.requestFocus();
            }
        }
    }//GEN-LAST:event_txt_fec_finKeyPressed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void t_metasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_t_metasMouseClicked
        i = t_metas.getSelectedRow();
        System.out.println(i);
    }//GEN-LAST:event_t_metasMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int confirmar = JOptionPane.showConfirmDialog(null, "Seguro que desea eliminar el Codigo " + t_metas.getValueAt(i, 0));
        if (JOptionPane.OK_OPTION == confirmar) {
            try {
                Statement st = con.conexion();
                String sql = "delete from metas where idMetas ='" + met.getId() + "'";
                con.consulta(st, sql);
                con.cerrar(st);
                String sql2 = "select m.idMetas, m.monto, m.estado, m.fec_inicio, m.fec_fin, "
                        + "a.nom_alm from metas as m inner join almacen as a on m.idalmacen = a.idAlmacen order by idMetas asc;";
                ver_metas(sql2);
            } catch (Exception e) {
                System.out.println("Error en " + e.getLocalizedMessage());
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txt_montoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_montoKeyTyped
        if (Character.isLetter(evt.getKeyChar())) {
            evt.consume();
        }
    }//GEN-LAST:event_txt_montoKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_reg;
    private javax.swing.JComboBox cbx_tienda;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable t_metas;
    private javax.swing.JFormattedTextField txt_fec_fin;
    private javax.swing.JFormattedTextField txt_fec_ini;
    private javax.swing.JTextField txt_monto;
    // End of variables declaration//GEN-END:variables
}
