/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

import Clases.Cl_Conectar;
import Clases.Cl_Empresa;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author luis-d
 */
public class frm_reg_empresa extends javax.swing.JInternalFrame {
Cl_Empresa emp = new Cl_Empresa();
Cl_Conectar con = new Cl_Conectar();
String accion;
    /**
     * Creates new form frm_reg_empresa
     */
    public frm_reg_empresa() {
        initComponents();
        
        cargar_empresa();
    }
    
    private void cargar_empresa(){
        try {
            Statement st = con.conexion();
            String query = "select * from empresa";
            ResultSet rs = con.consulta(st, query);
            if (rs.next()) {
                txt_ruc.setText(rs.getString("ruc"));
                txt_raz.setText(rs.getString("raz_soc"));
                txt_dir.setText(rs.getString("dir"));
                txt_raz.setEditable(true);
                txt_dir.setEditable(true);
                btn_reg.setEnabled(true);
                txt_ruc.requestFocus();
                accion = "modificar";
            } else {
                txt_ruc.requestFocus();
                accion = "registrar";
            }
        } catch (SQLException ex) {
            System.out.print(ex);
        }
    }
    
    private void llenar() {
        emp.setRaz_soc(txt_raz.getText());
        emp.setRuc(txt_ruc.getText());
        emp.setDir(txt_dir.getText());
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
        txt_ruc = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txt_raz = new javax.swing.JTextField();
        txt_dir = new javax.swing.JTextField();
        btn_reg = new javax.swing.JButton();

        setTitle("Datos de Empresa");

        jLabel1.setText("RUC:");

        txt_ruc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_rucKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_rucKeyTyped(evt);
            }
        });

        jLabel2.setText("Razon Social:");

        jLabel3.setText("Direccion:");

        txt_raz.setEditable(false);
        txt_raz.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_razKeyPressed(evt);
            }
        });

        txt_dir.setEditable(false);
        txt_dir.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_dirKeyPressed(evt);
            }
        });

        btn_reg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/accept.png"))); // NOI18N
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_dir)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_ruc, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_raz, javax.swing.GroupLayout.PREFERRED_SIZE, 371, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 103, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btn_reg)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_ruc, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_raz, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_dir, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_reg, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_regActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_regActionPerformed
        llenar();
        if (accion.equals("registrar")) {
            try {
                Statement st = con.conexion();
                String ins_emp = "insert into empresa Values ('"+emp.getRuc()+"', '"+emp.getRaz_soc()+"', '"+emp.getDir()+"')";
                con.actualiza(st, ins_emp);
                con.cerrar(st);
                JOptionPane.showMessageDialog(null, "Se ha registrado correctamente");
            } catch (Exception ex) {
                System.out.print(ex);
            }
        } else {
            try {
                Statement st = con.conexion();
                String act_emp = "update empresa set raz_soc = '"+emp.getRaz_soc()+"', dir = '"+emp.getDir()+"' where ruc = '"+emp.getRuc()+"'";
                con.actualiza(st, act_emp);
                con.cerrar(st);
                JOptionPane.showMessageDialog(null, "Se ha modificado correctamente");
            } catch (Exception ex) {
                System.out.print(ex);
            }
        }
        this.dispose();
    }//GEN-LAST:event_btn_regActionPerformed

    private void txt_rucKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_rucKeyPressed
        if (evt.getKeyCode()==KeyEvent.VK_ENTER) {
            if (txt_ruc.getText().length()==11) {
                txt_raz.setEditable(true);
                txt_raz.requestFocus();
            }
        }
    }//GEN-LAST:event_txt_rucKeyPressed

    private void txt_razKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_razKeyPressed
        if (evt.getKeyCode()==KeyEvent.VK_ENTER) {
            if (!txt_raz.getText().isEmpty()) {
                txt_dir.setEditable(true);
                txt_dir.requestFocus();
            }
        }
    }//GEN-LAST:event_txt_razKeyPressed

    private void txt_dirKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_dirKeyPressed
        if (evt.getKeyCode()==KeyEvent.VK_ENTER) {
            if (!txt_dir.getText().isEmpty()) {
                btn_reg.setEnabled(true);
                btn_reg.requestFocus();
            }
        }
    }//GEN-LAST:event_txt_dirKeyPressed

    private void txt_rucKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_rucKeyTyped
        if(txt_ruc.getText().length()==11) evt.consume();       
        char car = evt.getKeyChar();
        if((car<'0' || car>'9')) evt.consume();
    }//GEN-LAST:event_txt_rucKeyTyped

    private void btn_regKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btn_regKeyPressed
        if (evt.getKeyCode()==KeyEvent.VK_ENTER) {
            btn_reg.doClick();
        }
            
    }//GEN-LAST:event_btn_regKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_reg;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField txt_dir;
    private javax.swing.JTextField txt_raz;
    private javax.swing.JTextField txt_ruc;
    // End of variables declaration//GEN-END:variables
}
