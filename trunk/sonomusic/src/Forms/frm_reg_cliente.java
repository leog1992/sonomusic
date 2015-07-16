package Forms;

import Clases.Cl_Cliente;
import Clases.Cl_Conectar;
import Clases.Cl_Varios;
import Vistas.frm_ver_cliente;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import sonomusic.frm_menu;

/**
 *
 * @author pc
 */
public class frm_reg_cliente extends javax.swing.JInternalFrame {

    Cl_Varios ven = new Cl_Varios();
    Cl_Conectar con = new Cl_Conectar();
    Cl_Cliente cli = new Cl_Cliente();
    public static String win = "reg";
    public static String ventana = "cliente";

    public frm_reg_cliente() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        cbx_cli = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        txt_ndoc = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txt_nom = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txt_dir = new javax.swing.JTextField();
        txt_tel = new javax.swing.JTextField();
        btn_cer = new javax.swing.JButton();
        btn_reg = new javax.swing.JButton();
        btn_lim = new javax.swing.JButton();
        txt_tel1 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(254, 254, 254));
        setClosable(true);
        setTitle("Registrar Cliente");

        jLabel1.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(229, 2, 2));
        jLabel1.setText("Tipo de Documento");

        cbx_cli.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "DNI", "RUC" }));
        cbx_cli.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cbx_cliKeyPressed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(229, 2, 2));
        jLabel2.setText("Nro de Doc:");

        txt_ndoc.setEditable(false);
        txt_ndoc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_ndocKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_ndocKeyTyped(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(229, 2, 2));
        jLabel3.setText("Nombre Completo:");

        txt_nom.setEditable(false);
        txt_nom.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_nomKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_nomKeyTyped(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(229, 2, 2));
        jLabel4.setText("Direccion:");

        jLabel5.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(229, 2, 2));
        jLabel5.setText("Telefono:");

        txt_dir.setEditable(false);
        txt_dir.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_dirKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_dirKeyTyped(evt);
            }
        });

        txt_tel.setEditable(false);
        txt_tel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_telKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_telKeyTyped(evt);
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
        btn_reg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btn_regKeyPressed(evt);
            }
        });

        btn_lim.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/asterisk_orange.png"))); // NOI18N
        btn_lim.setText("Limpiar");
        btn_lim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_limActionPerformed(evt);
            }
        });

        txt_tel1.setEditable(false);
        txt_tel1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_tel1KeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_tel1KeyTyped(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(229, 2, 2));
        jLabel7.setText("Telefono 2 :");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btn_lim)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3)
                            .addComponent(jLabel7))
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(btn_reg)
                                        .addGap(26, 26, 26)
                                        .addComponent(btn_cer))
                                    .addComponent(txt_dir, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 342, Short.MAX_VALUE)
                                    .addComponent(txt_nom, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txt_tel, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txt_tel1, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txt_ndoc, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addGap(25, 25, 25))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cbx_cli, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbx_cli, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_ndoc, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_nom, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_dir, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_tel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_tel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_lim, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_reg, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_cer, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_cerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cerActionPerformed
        if (ventana.equals("cliente")) {
            frm_ver_cliente cliente = new frm_ver_cliente();
            ven.llamar_ventana(cliente);
            win = "reg";
        }
        this.dispose();

    }//GEN-LAST:event_btn_cerActionPerformed

    private void llenar() {
        if (cbx_cli.getSelectedIndex() == 0) {
            cli.setTipo_doc("DNI");
        }
        if (cbx_cli.getSelectedIndex() == 1) {
            cli.setTipo_doc("RUC");
        }
        cli.setNom_cli(txt_nom.getText());
        cli.setNro_doc(txt_ndoc.getText());
        cli.setDir_cli(txt_dir.getText());
        cli.setTel(txt_tel.getText());
        cli.setTel2(txt_tel1.getText());
    }
    private void btn_regActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_regActionPerformed
        llenar();

        if (win.equals("reg")) {
            Statement st = con.conexion();
            String inser_cli = "insert into cliente Values ('" + cli.getNro_doc() + "', '" + cli.getTipo_doc() + "', "
                    + "'" + cli.getNom_cli() + "', '" + cli.getDir_cli() + "', '" + cli.getTel() + "', "
                    + "'" + cli.getTel2() + "', '" + 1 + "')";
            con.actualiza(st, inser_cli);
            con.cerrar(st);
            JOptionPane.showMessageDialog(null, "Se ha ingresado los datos correctamente");
            this.dispose();
        }

        if (win.equals("mod")) {
            Statement st = con.conexion();
            String act_cli = "update cliente set nom_per = '" + cli.getNom_cli() + "', dir_per = '" + cli.getDir_cli() + "', "
                    + "tel_per = '" + cli.getTel() + "', tel2_per = '" + cli.getTel2() + "' where nro_doc='" + cli.getNro_doc() + "'";
            con.actualiza(st, act_cli);
            con.cerrar(st);
            win = "reg";
            JOptionPane.showMessageDialog(null, "Se ha modificado los datos correctamente");
            btn_cer.doClick();
        }
        frm_ver_cliente cliente = new frm_ver_cliente();
        ven.llamar_ventana(cliente);
        win = "reg";

        if (ventana.equals("reg_venta")) {
            this.dispose();
            cliente.dispose();
            frm_reg_venta.txt_nro_doc.requestFocus();
            frm_reg_venta.txt_nom.setText(txt_nom.getText());
        }
        ventana = "cliente";
    }//GEN-LAST:event_btn_regActionPerformed

    private void btn_limActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_limActionPerformed
        cbx_cli.setSelectedIndex(0);
        txt_ndoc.setText("");
        txt_nom.setText("");
        txt_dir.setText("");
        txt_tel.setText("");
        txt_tel1.setText("");
        txt_ndoc.setEditable(false);
        txt_nom.setEditable(false);
        txt_dir.setEditable(false);
        txt_tel.setEditable(false);
        txt_tel1.setEditable(false);
        btn_reg.setEnabled(false);
        cbx_cli.requestFocus();
    }//GEN-LAST:event_btn_limActionPerformed

    private void txt_ndocKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_ndocKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (cbx_cli.getSelectedItem().equals("DNI") && txt_ndoc.getText().length() == 8) {
                try {
                    cli.setNro_doc(txt_ndoc.getText());
                    Statement st = con.conexion();
                    String buscar = "select * from cliente where nro_doc = '" + cli.getNro_doc() + "'";
                    ResultSet rs = con.consulta(st, buscar);
                    if (rs.next()) {
                        JOptionPane.showMessageDialog(null, "El cliente ya existe, ingrese otro numero");
                        txt_ndoc.setText("");
                        txt_ndoc.requestFocus();
                    } else {
                        txt_nom.setEditable(true);
                        txt_nom.requestFocus();
                    }
                } catch (SQLException ex) {
                    System.out.print(ex);
                }
            } else if (cbx_cli.getSelectedItem().equals("RUC") && txt_ndoc.getText().length() == 11) {
                try {
                    cli.setNro_doc(txt_ndoc.getText());
                    Statement st = con.conexion();
                    String buscar = "select * from cliente where nro_doc = '" + cli.getNro_doc() + "'";
                    ResultSet rs = con.consulta(st, buscar);
                    if (rs.next()) {
                        JOptionPane.showMessageDialog(null, "El cliente ya existe, ingrese otro numero");
                        txt_ndoc.setText("");
                        txt_ndoc.requestFocus();
                    } else {
                        txt_nom.setEditable(true);
                        txt_nom.requestFocus();
                    }
                } catch (SQLException ex) {
                    System.out.print(ex);
                }
            }
        }
    }//GEN-LAST:event_txt_ndocKeyPressed

    private void txt_nomKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_nomKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (txt_nom.getText().length() > 0) {
                txt_dir.setEditable(true);
                txt_dir.requestFocus();
            }
        }
    }//GEN-LAST:event_txt_nomKeyPressed

    private void txt_dirKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_dirKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (txt_dir.getText().length() > 0) {
                txt_tel.setEditable(true);
                txt_tel.requestFocus();
            }
        }
    }//GEN-LAST:event_txt_dirKeyPressed

    private void txt_telKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_telKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (txt_tel.getText().length() > 0) {
                txt_tel1.setEditable(true);
                txt_tel1.requestFocus();
            }
        }
    }//GEN-LAST:event_txt_telKeyPressed

    private void txt_tel1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_tel1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (txt_tel1.getText().length() > 0) {
                btn_reg.setEnabled(true);
                btn_reg.requestFocus();
            }
        }
    }//GEN-LAST:event_txt_tel1KeyPressed

    private void btn_regKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btn_regKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btn_reg.doClick();
        }
    }//GEN-LAST:event_btn_regKeyPressed

    private void txt_ndocKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_ndocKeyTyped
        char car = evt.getKeyChar();
        if ((car < '0' || car > '9')) {
            evt.consume();
        }

        if (cbx_cli.getSelectedItem().equals("DNI")) {
            if (txt_ndoc.getText().length() == 8) {
                evt.consume();
            }
        }

        if (cbx_cli.getSelectedItem().equals("RUC")) {
            if (txt_ndoc.getText().length() == 11) {
                evt.consume();
            }
        }
    }//GEN-LAST:event_txt_ndocKeyTyped

    private void txt_nomKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_nomKeyTyped
        if (txt_nom.getText().length() == 245) {
            evt.consume();
        }
    }//GEN-LAST:event_txt_nomKeyTyped

    private void txt_dirKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_dirKeyTyped
        if (txt_dir.getText().length() == 245) {
            evt.consume();
        }
    }//GEN-LAST:event_txt_dirKeyTyped

    private void txt_telKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_telKeyTyped
        if (txt_tel.getText().length() == 9) {
            evt.consume();
        }

    }//GEN-LAST:event_txt_telKeyTyped

    private void txt_tel1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_tel1KeyTyped
        if (txt_tel1.getText().length() == 9) {
            evt.consume();
        }

    }//GEN-LAST:event_txt_tel1KeyTyped

    private void cbx_cliKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbx_cliKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txt_ndoc.setEditable(true);
            txt_ndoc.requestFocus();
        }
    }//GEN-LAST:event_cbx_cliKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_cer;
    public static javax.swing.JButton btn_lim;
    public static javax.swing.JButton btn_reg;
    public static javax.swing.JComboBox cbx_cli;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    public static javax.swing.JTextField txt_dir;
    public static javax.swing.JTextField txt_ndoc;
    public static javax.swing.JTextField txt_nom;
    public static javax.swing.JTextField txt_tel;
    public static javax.swing.JTextField txt_tel1;
    // End of variables declaration//GEN-END:variables
}
