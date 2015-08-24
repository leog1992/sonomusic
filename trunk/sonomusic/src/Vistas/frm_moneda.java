package Vistas;

import Clases.Cl_Conectar;
import Clases.Cl_Moneda;
import Clases.Cl_Varios;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class frm_moneda extends javax.swing.JInternalFrame {

    Cl_Conectar con = new Cl_Conectar();
    Cl_Varios ven = new Cl_Varios();
    Cl_Moneda mon = new Cl_Moneda();
    DefaultTableModel mostrar;
    int i;

    public frm_moneda() {
        initComponents();
        String sql = "select * from moneda";
        mostrar_moneda(sql);
    }

    void mostrar_moneda(String sql) {
        mostrar = new DefaultTableModel() {

            @Override
            public boolean isCellEditable(int i, int i1) {
                return false;
            }
        };
        try {
            Statement st = con.conexion();
            mostrar.addColumn("Id");
            mostrar.addColumn("Moneda");
            mostrar.addColumn("Siglas");

            ResultSet rs = con.consulta(st, sql);
            Object[] dato = new Object[3];
            while (rs.next()) {
                dato[0] = rs.getObject("idmoneda");
                dato[1] = rs.getObject("nombre");
                dato[2] = rs.getObject("siglas");
                mostrar.addRow(dato);
            }
            con.cerrar(st);
            con.cerrar(rs);
            t_moneda.setModel(mostrar);
            t_moneda.getColumnModel().getColumn(0).setPreferredWidth(20);
            t_moneda.getColumnModel().getColumn(1).setPreferredWidth(200);
            t_moneda.getColumnModel().getColumn(2).setPreferredWidth(80);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getLocalizedMessage());
        }
    }
    
    void llenar(){
        mon.setNombre(txt_nombre.getText().trim());
        mon.setSiglas(txt_siglas.getText().trim());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txt_nombre = new javax.swing.JTextField();
        txt_siglas = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        t_moneda = new javax.swing.JTable();
        btn_reg = new javax.swing.JButton();
        btn_mod = new javax.swing.JButton();
        btn_grabar = new javax.swing.JButton();
        btn_salir = new javax.swing.JButton();

        setBackground(new java.awt.Color(254, 254, 254));
        setClosable(true);
        setTitle("Registrar Moneda");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/coins.png"))); // NOI18N

        jLabel1.setForeground(new java.awt.Color(212, 2, 2));
        jLabel1.setText("Nombre:");

        jLabel2.setForeground(new java.awt.Color(212, 2, 2));
        jLabel2.setText("Siglas:");

        txt_nombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_nombreKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_nombreKeyTyped(evt);
            }
        });

        txt_siglas.setEditable(false);
        txt_siglas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_siglasKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_siglasKeyTyped(evt);
            }
        });

        t_moneda.setBackground(new java.awt.Color(254, 254, 254));
        t_moneda.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3"
            }
        ));
        t_moneda.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                t_monedaMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(t_moneda);

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

        btn_grabar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/disk.png"))); // NOI18N
        btn_grabar.setText("Grabar");
        btn_grabar.setEnabled(false);
        btn_grabar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_grabarActionPerformed(evt);
            }
        });

        btn_salir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/cancel.png"))); // NOI18N
        btn_salir.setText("Salir");
        btn_salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_salirActionPerformed(evt);
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
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_reg, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
                            .addComponent(btn_mod, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_grabar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_salir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_siglas, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_siglas, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn_reg, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_mod, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_grabar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_salir, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_salirActionPerformed
        this.dispose();
    }//GEN-LAST:event_btn_salirActionPerformed

    private void txt_nombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_nombreKeyTyped
        if (Character.isDigit(evt.getKeyChar())) {
            evt.consume();
        }
    }//GEN-LAST:event_txt_nombreKeyTyped

    private void txt_nombreKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_nombreKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!txt_nombre.getText().isEmpty()) {
                txt_siglas.setEditable(true);
                txt_siglas.requestFocus();
            }
        }
    }//GEN-LAST:event_txt_nombreKeyPressed

    private void txt_siglasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_siglasKeyPressed
        if (evt.getKeyCode()==KeyEvent.VK_ENTER) {
            if (!txt_siglas.getText().isEmpty()) {
                btn_reg.setEnabled(true);
                btn_reg.requestFocus();
            }
        }
    }//GEN-LAST:event_txt_siglasKeyPressed

    private void btn_regKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btn_regKeyPressed
//        if (evt.getKeyCode()==KeyEvent.VK_ENTER) {
//            btn_reg.doClick();
//        }
    }//GEN-LAST:event_btn_regKeyPressed

    private void btn_regActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_regActionPerformed
        llenar();
        try {
            Statement st = con.conexion();
            String sql="insert into moneda values(null,'"+mon.getNombre()+"','"+mon.getSiglas()+"')";
            con.actualiza(st, sql);
            txt_nombre.setText("");
            txt_siglas.setText("");
            txt_nombre.requestFocus();
            btn_reg.setEnabled(false);
            mostrar_moneda("select * from moneda");
            con.cerrar(st);            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: "+e.getLocalizedMessage());
        }
    }//GEN-LAST:event_btn_regActionPerformed

    private void txt_siglasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_siglasKeyTyped
        if (txt_siglas.getText().length()==4) {
            evt.consume();
        }
    }//GEN-LAST:event_txt_siglasKeyTyped

    private void t_monedaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_t_monedaMousePressed
        i=t_moneda.getSelectedRow();
        btn_mod.setEnabled(true);
        
    }//GEN-LAST:event_t_monedaMousePressed

    private void btn_modActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_modActionPerformed
        btn_reg.setEnabled(false);
        mon.setId(Integer.parseInt(t_moneda.getValueAt(i, 0).toString()));
        mon.setNombre(t_moneda.getValueAt(i, 1).toString());
        mon.setSiglas(t_moneda.getValueAt(i, 2).toString());
        txt_nombre.setText(""+mon.getNombre());      
        txt_siglas.setText(""+mon.getSiglas());
        txt_nombre.requestFocus();
        btn_mod.setEnabled(false);
        btn_grabar.setEnabled(true);
        
    }//GEN-LAST:event_btn_modActionPerformed

    private void btn_grabarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_grabarActionPerformed
        llenar();
        try {
            Statement st =con.conexion();
            String sql="update moneda set nombre='"+mon.getNombre()+"' , siglas='"+mon.getSiglas()+"' where idmoneda='"+mon.getId()+"'";
            con.actualiza(st, sql);
            mostrar_moneda("select * from moneda");
            btn_mod.setEnabled(false);
            btn_grabar.setEnabled(false);
            txt_nombre.setText("");
            txt_siglas.setText("");
            txt_siglas.setEditable(false);
            txt_nombre.requestFocus();
            con.cerrar(st);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: "+e.getLocalizedMessage());
        }
    }//GEN-LAST:event_btn_grabarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_grabar;
    private javax.swing.JButton btn_mod;
    private javax.swing.JButton btn_reg;
    private javax.swing.JButton btn_salir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable t_moneda;
    private javax.swing.JTextField txt_nombre;
    private javax.swing.JTextField txt_siglas;
    // End of variables declaration//GEN-END:variables
}
