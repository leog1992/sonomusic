package Vistas;

import Clases.Cl_Banco;
import Clases.Cl_Conectar;
import Clases.Cl_Varios;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class frm_banco extends javax.swing.JInternalFrame {

    Cl_Conectar con = new Cl_Conectar();
    Cl_Varios ven = new Cl_Varios();
    Cl_Banco ban = new Cl_Banco();
    DefaultTableModel t_banco;
    Integer i;

    public frm_banco() {
        initComponents();
        String sql = "select * from banco";
        mostrar_banco(sql);
    }

    void mostrar_banco(String sql) {
        t_banco = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int i, int i1) {
                return false;
            }
        };

        t_banco.addColumn("Id");
        t_banco.addColumn("Nombre del Banco");
        try {
            Statement st = con.conexion();
            ResultSet rs = con.consulta(st, sql);
            Object[] dato = new Object[2];
            while (rs.next()) {
                dato[0] = rs.getObject("idbanco");
                dato[1] = rs.getObject("nombre");
                t_banco.addRow(dato);
            }
            con.cerrar(st);
            con.cerrar(rs);
            t_ban.setModel(t_banco);
            t_ban.getColumnModel().getColumn(0).setPreferredWidth(25);
            t_ban.getColumnModel().getColumn(1).setPreferredWidth(350);
            ven.centrar_celda(t_ban, 1);
            ven.centrar_celda(t_ban, 0);
            t_banco.fireTableDataChanged();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getLocalizedMessage());
        }

    }

    void llenar() {
        ban.setNom_ban(txt_nom_banco.getText());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txt_nom_banco = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        t_ban = new javax.swing.JTable();
        btn_reg = new javax.swing.JButton();
        btn_mod = new javax.swing.JButton();
        btn_salir = new javax.swing.JButton();
        btn_grabar = new javax.swing.JButton();

        setTitle("Registro de Bancos");

        jLabel1.setText("Nombre de Banco:");

        txt_nom_banco.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_nom_bancoKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_nom_bancoKeyTyped(evt);
            }
        });

        t_ban.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Title 1", "Title 2"
            }
        ));
        t_ban.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        t_ban.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                t_banMousePressed(evt);
            }
        });
        t_ban.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                t_banKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(t_ban);

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

        btn_mod.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/application_edit.png"))); // NOI18N
        btn_mod.setText("Modificar");
        btn_mod.setEnabled(false);
        btn_mod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_modActionPerformed(evt);
            }
        });

        btn_salir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/cancel.png"))); // NOI18N
        btn_salir.setText("Cancelar");
        btn_salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_salirActionPerformed(evt);
            }
        });

        btn_grabar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/disk.png"))); // NOI18N
        btn_grabar.setText("Grabar");
        btn_grabar.setEnabled(false);
        btn_grabar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_grabarActionPerformed(evt);
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
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_reg, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_mod, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_salir, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_grabar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txt_nom_banco, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_nom_banco, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn_reg, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_mod, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(btn_grabar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                        .addComponent(btn_salir, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_nom_bancoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_nom_bancoKeyTyped
        if (Character.isDigit(evt.getKeyChar())) {
            evt.consume();
        }
    }//GEN-LAST:event_txt_nom_bancoKeyTyped

    private void btn_salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_salirActionPerformed
        this.dispose();
    }//GEN-LAST:event_btn_salirActionPerformed

    private void btn_regActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_regActionPerformed
        llenar();
        try {
            Statement st = con.conexion();
            String sql = "insert into banco values(null,'" + ban.getNom_ban() + "')";
            con.actualiza(st, sql);
            String mostrar = "select * from banco order by idbanco asc";
            mostrar_banco(mostrar);
            txt_nom_banco.setText("");
            txt_nom_banco.requestFocus();
            btn_reg.setEnabled(false);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getLocalizedMessage());
        }
    }//GEN-LAST:event_btn_regActionPerformed

    private void txt_nom_bancoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_nom_bancoKeyPressed
        if (!txt_nom_banco.getText().isEmpty()) {
            if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                btn_reg.setEnabled(true);
                btn_reg.requestFocus();
            }
        }
    }//GEN-LAST:event_txt_nom_bancoKeyPressed

    private void t_banKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_t_banKeyPressed
        
    }//GEN-LAST:event_t_banKeyPressed

    private void btn_modActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_modActionPerformed
        btn_reg.setEnabled(false);
        ban.setId(Integer.parseInt(t_banco.getValueAt(i, 0).toString()));
        ban.setNom_ban(t_banco.getValueAt(i, 1).toString());
        txt_nom_banco.setText(""+ban.getNom_ban());      
        btn_mod.setEnabled(false);
        btn_grabar.setEnabled(true);
    }//GEN-LAST:event_btn_modActionPerformed

    private void btn_grabarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_grabarActionPerformed
        llenar ();
        Statement st = con.conexion();
        String upd_car = "update banco set nombre = '"+ban.getNom_ban()+"' where idbanco = '"+ban.getId()+"'";
        con.actualiza(st, upd_car);
        con.cerrar(st);
        txt_nom_banco.setText("");
        txt_nom_banco.requestFocus();       
        String query = "select * from banco order by idbanco asc";
        mostrar_banco(query);
        btn_reg.setEnabled(true);
        btn_mod.setEnabled(false);
        btn_grabar.setEnabled(false);
    }//GEN-LAST:event_btn_grabarActionPerformed

    private void t_banMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_t_banMousePressed
         i = t_ban.getSelectedRow();
         btn_mod.setEnabled(true);
    }//GEN-LAST:event_t_banMousePressed

    private void btn_regKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btn_regKeyPressed
        if (evt.getKeyCode()==KeyEvent.VK_ENTER) {
            btn_reg.doClick();
        }
    }//GEN-LAST:event_btn_regKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_grabar;
    private javax.swing.JButton btn_mod;
    private javax.swing.JButton btn_reg;
    private javax.swing.JButton btn_salir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable t_ban;
    private javax.swing.JTextField txt_nom_banco;
    // End of variables declaration//GEN-END:variables
}
