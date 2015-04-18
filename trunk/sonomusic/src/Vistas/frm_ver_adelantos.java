package Vistas;

import Clases.Cl_Conectar;
import Clases.Cl_Varios;
import Forms.frm_reg_adelanto;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class frm_ver_adelantos extends javax.swing.JInternalFrame {

    Cl_Varios ven = new Cl_Varios();
    Cl_Conectar con = new Cl_Conectar();
    DefaultTableModel modelo;
    String valor;
    int i;

    public frm_ver_adelantos() {
        initComponents();
        String sql = "select a.idadelanto, a.fecha, a.monto, a.dni as dni, e.nom_per as nombre, e.tel_per as tel "
                + "from adelanto as a inner join empleados as e on a.dni =e.dni order by a.idadelanto";
        ver_adelantos(sql);
    }

    public void ver_adelantos(String sql) {
        modelo = new DefaultTableModel() {

            @Override
            public boolean isCellEditable(int i, int i1) {
                return false;
            }
        };

        try {
            Statement st = con.conexion();

            modelo.addColumn("Id");
            modelo.addColumn("Fecha");
            modelo.addColumn("D.N.I");
            modelo.addColumn("Empleado");
            modelo.addColumn("Telefono");
            modelo.addColumn("Monto");

            ResultSet rs = con.consulta(st, sql);
            Object[] dato = new Object[6];
            while (rs.next()) {
                dato[0] = rs.getObject("idadelanto");
                dato[1] = ven.fechaformateada(rs.getString("fecha"));
                dato[2] = rs.getObject("dni");
                dato[3] = rs.getObject("nombre");
                dato[4] = rs.getObject("tel");
                dato[5] = rs.getObject("monto");
                modelo.addRow(dato);
            }
            con.cerrar(st);
            con.cerrar(rs);
            t_adelantos.setModel(modelo);
            t_adelantos.getColumnModel().getColumn(0).setPreferredWidth(20);
            t_adelantos.getColumnModel().getColumn(1).setPreferredWidth(50);
            t_adelantos.getColumnModel().getColumn(2).setPreferredWidth(50);
            t_adelantos.getColumnModel().getColumn(3).setPreferredWidth(180);
            t_adelantos.getColumnModel().getColumn(4).setPreferredWidth(60);
            t_adelantos.getColumnModel().getColumn(5).setPreferredWidth(60);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getLocalizedMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        txt_bus = new javax.swing.JTextField();
        cbx_bus = new javax.swing.JComboBox();
        btn_reg = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        t_adelantos = new javax.swing.JTable();
        btn_eliminar = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setBackground(new java.awt.Color(254, 254, 254));
        setClosable(true);
        setTitle("Adelantos");

        jLabel2.setForeground(new java.awt.Color(212, 2, 2));
        jLabel2.setText("Buscar:");

        txt_bus.setEditable(false);
        txt_bus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_busKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_busKeyTyped(evt);
            }
        });

        cbx_bus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Fecha", "Empleado" }));
        cbx_bus.setSelectedIndex(-1);
        cbx_bus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbx_busActionPerformed(evt);
            }
        });

        btn_reg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/add.png"))); // NOI18N
        btn_reg.setText("Registrar");
        btn_reg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_regActionPerformed(evt);
            }
        });

        jScrollPane1.setBackground(new java.awt.Color(254, 254, 254));

        t_adelantos.setModel(new javax.swing.table.DefaultTableModel(
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
        t_adelantos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                t_adelantosMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(t_adelantos);

        btn_eliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/cross.png"))); // NOI18N
        btn_eliminar.setText("Eliminar");
        btn_eliminar.setEnabled(false);
        btn_eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_eliminarActionPerformed(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/salir.png"))); // NOI18N
        jButton1.setText("Salir");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 649, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(txt_bus, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cbx_bus, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btn_eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_reg, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_bus, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbx_bus, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_reg, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void t_adelantosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_t_adelantosMousePressed
        i = t_adelantos.getSelectedRow();
        btn_eliminar.setEnabled(true);
    }//GEN-LAST:event_t_adelantosMousePressed

    private void cbx_busActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbx_busActionPerformed
        txt_bus.setText("");
        txt_bus.setEditable(true);
        txt_bus.requestFocus();
    }//GEN-LAST:event_cbx_busActionPerformed

    private void txt_busKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_busKeyTyped

    }//GEN-LAST:event_txt_busKeyTyped

    private void txt_busKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_busKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String bus = txt_bus.getText();
            if (cbx_bus.getSelectedIndex() == 0) {

                String sql = "select a.idadelanto, a.fecha, a.monto, a.dni as dni, e.nom_per as nombre, e.tel_per as tel "
                        + "from adelanto as a inner join empleados as e on a.dni =e.dni where a.fecha like '%" + bus + "%' order by a.fecha";
                ver_adelantos(sql);
            } else {
                String sql = "select a.idadelanto, a.fecha, a.monto, a.dni as dni, e.nom_per as nombre, e.tel_per as tel "
                        + "from adelanto as a inner join empleados as e on a.dni =e.dni where a.dni like '%" + bus + "%' order by a.dni";
                ver_adelantos(sql);
            }
        }
    }//GEN-LAST:event_txt_busKeyPressed

    private void btn_eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_eliminarActionPerformed
        String id = t_adelantos.getValueAt(i, 0).toString();
        try {
            Statement st = con.conexion();
            String sql = "delete from adelanto where idadelanto='" + id + "'";
            con.actualiza(st, sql);
            con.cerrar(st);
            String query = "select a.idadelanto, a.fecha, a.monto, a.dni as dni, e.nom_per as nombre, e.tel_per as tel "
                    + "from adelanto as a inner join empleados as e on a.dni =e.dni order by a.idadelanto";
            ver_adelantos(query);
            btn_eliminar.setEnabled(false);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: "+e.getLocalizedMessage());
        }
    }//GEN-LAST:event_btn_eliminarActionPerformed

    private void btn_regActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_regActionPerformed
        frm_reg_adelanto ad = new frm_reg_adelanto();
        ven.llamar_ventana(ad);
        this.dispose();
    }//GEN-LAST:event_btn_regActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_eliminar;
    private javax.swing.JButton btn_reg;
    private javax.swing.JComboBox cbx_bus;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable t_adelantos;
    private javax.swing.JTextField txt_bus;
    // End of variables declaration//GEN-END:variables
}
