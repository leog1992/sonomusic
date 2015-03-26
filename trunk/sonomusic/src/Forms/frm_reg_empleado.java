package Forms;

import Clases.*;
import Vistas.*;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author pc
 */
public class frm_reg_empleado extends javax.swing.JInternalFrame {

    Cl_Conectar con = new Cl_Conectar();
    Cl_Varios ven = new Cl_Varios();
    Cl_Empleado emp = new Cl_Empleado();
    Cl_Almacen alm = new Cl_Almacen();
    Cl_Meta met = new Cl_Meta();
    Cl_Cargo car = new Cl_Cargo();
    public static String win = "reg";

    public frm_reg_empleado() {
        initComponents();

        String k = "select * from metas";
        cargarmetas(k);
        String a = "select * from almacen";
        cargaralmacen(a);
        String b = "select * from cargo";
        cargarcargo(b);
    }

    public void cargarmetas(String query) {
        try {
            Statement st = con.conexion();
            ResultSet rs = con.consulta(st, query);

            while (rs.next()) {
                String cap;
                cap = rs.getString("monto") + " - " + rs.getString("fec_inicio") + " - " + rs.getString("fec_fin");
                cbx_mon.addItem(cap);
            }
            con.cerrar(st);
            con.cerrar(rs);

        } catch (Exception e) {
            System.out.println("Ocurrio un error " + e.getMessage() + " en :" + e.getLocalizedMessage());
        }

    }

    public void cargaralmacen(String query) {
        try {
            Statement st = con.conexion();
            ResultSet rs = con.consulta(st, query);

            while (rs.next()) {
                String cap;
                cap = rs.getString("nom_alm");
                cbx_almacen.addItem(cap);
            }

            con.cerrar(st);
            con.cerrar(rs);
        } catch (Exception e) {
            System.out.println("Ocurrio un error " + e.getMessage() + " en :" + e.getLocalizedMessage());
        }
    }

    public void cargarcargo(String query) {
        try {
            Statement st = con.conexion();
            ResultSet rs = con.consulta(st, query);
            while (rs.next()) {
                String busca;
                busca = rs.getString("tipo_cargo");
                cbx_cargo.addItem(busca);
            }
            con.cerrar(st);
            con.cerrar(rs);
        } catch (Exception e) {
            System.out.println("Ocurrio un error " + e.getMessage() + " en :" + e.getLocalizedMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel7 = new javax.swing.JLabel();
        txt_tel2 = new javax.swing.JTextField();
        btn_lim = new javax.swing.JButton();
        btn_reg = new javax.swing.JButton();
        btn_cer = new javax.swing.JButton();
        txt_tel1 = new javax.swing.JTextField();
        txt_dir = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txt_ndoc = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txt_nom = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txt_sue = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        cbx_almacen = new javax.swing.JComboBox();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        cbx_cargo = new javax.swing.JComboBox();
        cbx_mon = new javax.swing.JComboBox();
        txt_fec = new javax.swing.JFormattedTextField();
        jPanel1 = new javax.swing.JPanel();

        setTitle("Registrar Empleado");

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("Telefono 2 :");

        txt_tel2.setEditable(false);
        txt_tel2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_tel2ActionPerformed(evt);
            }
        });
        txt_tel2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_tel2KeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_tel2KeyTyped(evt);
            }
        });

        btn_lim.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/cross.png"))); // NOI18N
        btn_lim.setText("Limpiar");
        btn_lim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_limActionPerformed(evt);
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

        btn_cer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/cancel.png"))); // NOI18N
        btn_cer.setText("Cerrar");
        btn_cer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cerActionPerformed(evt);
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

        txt_dir.setEditable(false);
        txt_dir.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_dirKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_dirKeyTyped(evt);
            }
        });

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Telefono 1:");

        txt_ndoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_ndocActionPerformed(evt);
            }
        });
        txt_ndoc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_ndocKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_ndocKeyTyped(evt);
            }
        });

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Nombres y Apellidos");

        txt_nom.setEditable(false);
        txt_nom.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_nomKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_nomKeyTyped(evt);
            }
        });

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Direccion:");

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("DNI.");

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("Sueldo:");

        txt_sue.setEditable(false);
        txt_sue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_sueActionPerformed(evt);
            }
        });
        txt_sue.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_sueKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_sueKeyTyped(evt);
            }
        });

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("Fecha Nacimiento:");

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("Almacen:");

        cbx_almacen.setEnabled(false);
        cbx_almacen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbx_almacenActionPerformed(evt);
            }
        });
        cbx_almacen.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cbx_almacenKeyPressed(evt);
            }
        });

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText("Meta:");

        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel11.setText("Cargo:");

        cbx_cargo.setEnabled(false);
        cbx_cargo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbx_cargoActionPerformed(evt);
            }
        });
        cbx_cargo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cbx_cargoKeyPressed(evt);
            }
        });

        cbx_mon.setEnabled(false);
        cbx_mon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbx_monActionPerformed(evt);
            }
        });
        cbx_mon.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cbx_monKeyPressed(evt);
            }
        });

        txt_fec.setEditable(false);
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

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 150, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 200, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_nom, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbx_mon, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_ndoc, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txt_fec, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                                        .addComponent(txt_tel2, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                                        .addComponent(cbx_almacen, 0, 100, Short.MAX_VALUE)
                                        .addComponent(txt_tel1, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                                        .addComponent(cbx_cargo, 0, 100, Short.MAX_VALUE)
                                        .addComponent(txt_sue))
                                    .addGap(100, 100, 100)
                                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(txt_dir, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(btn_reg)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btn_cer)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(btn_lim)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txt_ndoc, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_nom, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_dir, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txt_tel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_tel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_sue, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_fec, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbx_almacen, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbx_cargo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(cbx_mon, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_lim, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_reg, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_cer, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_limActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_limActionPerformed
        txt_ndoc.setText("");
        txt_nom.setText("");
        txt_dir.setText("");
        txt_dir.setEditable(false);
        txt_tel1.setText("");
        txt_tel1.setEditable(false);
        txt_tel2.setText("");
        txt_tel2.setEditable(false);
        txt_sue.setText("");
        txt_sue.setEditable(false);
        txt_fec.setText("");
        txt_fec.setEditable(false);
        cbx_almacen.setSelectedIndex(0);
        cbx_almacen.setEnabled(false);
        cbx_mon.setSelectedIndex(0);
        cbx_mon.setEnabled(false);
        cbx_cargo.setSelectedIndex(0);
        cbx_cargo.setEnabled(false);
        txt_ndoc.requestFocus();

    }//GEN-LAST:event_btn_limActionPerformed

    private void llenar() {
        emp.setDni(Integer.parseInt(txt_ndoc.getText()));
        emp.setNom(txt_nom.getText());
        emp.setDir(txt_dir.getText());
        emp.setTel(txt_tel1.getText());
        emp.setTel2(txt_tel2.getText());
        emp.setImg("noimage.jpg");
        emp.setSueldo(Double.parseDouble(txt_sue.getText()));
        emp.setFec_nac(ven.fechabase(txt_fec.getText()));
        alm.setId(cbx_almacen.getSelectedIndex() + 1);
        met.setId(cbx_mon.getSelectedIndex() + 1);
        car.setId(cbx_cargo.getSelectedIndex() + 1);

    }

    private void btn_regActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_regActionPerformed
        llenar();
        try {
            if (win.equals("reg")) {

                Statement st = con.conexion();
                String insert_per = "insert into empleados Values ('" + emp.getDni() + "', '" + emp.getNom() + "', "
                        + "'" + emp.getDir() + "', '" + emp.getTel() + "', '" + emp.getTel2() + "', "
                        + "'" + emp.getImg() + "', '" + emp.getEst() + "', '" + emp.getSueldo() + "', "
                        + "'" + emp.getFec_nac() + "', '" + alm.getId() + "', '" + met.getId() + "', '" + car.getId() + "')";
                con.actualiza(st, insert_per);
                con.cerrar(st);
                JOptionPane.showMessageDialog(null, "Se ha ingresado los datos corectamente");
                btn_lim.doClick();
                this.dispose();
                frm_ver_empleado empleado = new frm_ver_empleado();
                ven.llamar_ventana(empleado);
            }
            if (win.equals("mod")) {
                Statement st = con.conexion();
                String insert_per = "update empleados set nom_per='" + emp.getNom() + "', dir_per='" + emp.getDir() + "', "
                        + "tel_per='" + emp.getTel() + "', tel2_per='" + emp.getTel2() + "', img_per='" + emp.getImg() + "', "
                        + "sueldo='" + emp.getSueldo() + "', fec_nac='" + emp.getFec_nac() + "', idAlmacen='" + alm.getId() + "', "
                        + "idMetas='" + met.getId() + "', idCargo='" + car.getId() + "' where dni='" + emp.getDni() + "'";
                con.actualiza(st, insert_per);
                con.cerrar(st);
                JOptionPane.showMessageDialog(null, "Se ha modificado los datos");
                win = "reg";
                this.dispose();
                frm_ver_empleado empleado = new frm_ver_empleado();
                ven.llamar_ventana(empleado);

            }
        } catch (Exception e) {
            System.out.println("Ocurrio un error " + e.getMessage() + " en :" + e.getLocalizedMessage());
            JOptionPane.showMessageDialog(null, "Error " + e.getLocalizedMessage());
        }
    }//GEN-LAST:event_btn_regActionPerformed

    private void btn_cerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cerActionPerformed
        frm_ver_empleado empleado = new frm_ver_empleado();
        ven.llamar_ventana(empleado);
        this.dispose();
    }//GEN-LAST:event_btn_cerActionPerformed

    private void txt_ndocKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_ndocKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            try {
                emp.setDni(Integer.parseInt(txt_ndoc.getText()));
                Statement st = con.conexion();
                String buscar = "select * from empleados where dni = '" + emp.getDni() + "'";
                ResultSet rs = con.consulta(st, buscar);
                if (rs.next()) {
                    JOptionPane.showMessageDialog(null, "El dni del  empleado ya existe, ingrese otro dni");
                    txt_ndoc.setText("");
                    txt_ndoc.requestFocus();
                } else {
                    txt_nom.setEditable(true);
                    txt_nom.requestFocus();
                }
            } catch (SQLException ex) {
                System.out.print(ex);
                JOptionPane.showMessageDialog(null, "Error " + ex.getLocalizedMessage());
            }
        }
    }//GEN-LAST:event_txt_ndocKeyPressed

    private void txt_nomKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_nomKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!txt_nom.getText().isEmpty()) {
                txt_dir.setEditable(true);
            }
            txt_dir.requestFocus();
        }
    }//GEN-LAST:event_txt_nomKeyPressed

    private void txt_dirKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_dirKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!txt_dir.getText().isEmpty()) {
                txt_tel1.setEditable(true);
            }
            txt_tel1.requestFocus();
        }
    }//GEN-LAST:event_txt_dirKeyPressed

    private void txt_tel1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_tel1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!txt_tel1.getText().isEmpty()) {
                txt_tel2.setEditable(true);
            }
            txt_tel2.requestFocus();
        }
    }//GEN-LAST:event_txt_tel1KeyPressed

    private void txt_tel2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_tel2KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!txt_tel2.getText().isEmpty()) {
                txt_sue.setEditable(true);
            }
            txt_sue.requestFocus();
        }
    }//GEN-LAST:event_txt_tel2KeyPressed

    private void txt_ndocKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_ndocKeyTyped
        if (txt_ndoc.getText().length() == 8) {
            evt.consume();
        }
        char car = evt.getKeyChar();
        if ((car < '0' || car > '9')) {
            evt.consume();
        }
    }//GEN-LAST:event_txt_ndocKeyTyped

    private void txt_nomKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_nomKeyTyped
        if (txt_nom.getText().length() == 245 || Character.isDigit(evt.getKeyChar())) {
            evt.consume();
        }
    }//GEN-LAST:event_txt_nomKeyTyped

    private void txt_dirKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_dirKeyTyped
        if (txt_dir.getText().length() == 245) {
            evt.consume();
        }
    }//GEN-LAST:event_txt_dirKeyTyped

    private void txt_tel1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_tel1KeyTyped
        if (txt_tel1.getText().length() == 9) {
            evt.consume();
        }
    }//GEN-LAST:event_txt_tel1KeyTyped

    private void txt_tel2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_tel2KeyTyped
        if (txt_tel2.getText().length() == 9) {
            evt.consume();
        }
        char car = evt.getKeyChar();
        if ((car < '0' || car > '9')) {
            evt.consume();
        }
    }//GEN-LAST:event_txt_tel2KeyTyped

    private void txt_sueKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_sueKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!txt_sue.getText().isEmpty()) {
                txt_fec.setEditable(true);
            }
            txt_fec.requestFocus();
        }
    }//GEN-LAST:event_txt_sueKeyPressed

    private void txt_sueKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_sueKeyTyped
        if (Character.isLetter(evt.getKeyChar())) {
            evt.consume();
        }
    }//GEN-LAST:event_txt_sueKeyTyped

    private void cbx_almacenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbx_almacenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbx_almacenActionPerformed

    private void txt_tel2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_tel2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_tel2ActionPerformed

    private void cbx_almacenKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbx_almacenKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (cbx_almacen.getSelectedIndex() != -1) {
                cbx_mon.setEnabled(true);
            }
            cbx_mon.requestFocus();
        }
    }//GEN-LAST:event_cbx_almacenKeyPressed

    private void txt_sueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_sueActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_sueActionPerformed

    private void cbx_cargoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbx_cargoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (cbx_cargo.getSelectedIndex() != -1) {
                btn_reg.setEnabled(true);
            }
            btn_reg.requestFocus();
        }
    }//GEN-LAST:event_cbx_cargoKeyPressed

    private void btn_regKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btn_regKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btn_reg.doClick();
        }
    }//GEN-LAST:event_btn_regKeyPressed

    private void txt_ndocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_ndocActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_ndocActionPerformed

    private void cbx_monActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbx_monActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbx_monActionPerformed

    private void cbx_monKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbx_monKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cbx_cargo.setEnabled(true);
            cbx_cargo.requestFocus();
        }
    }//GEN-LAST:event_cbx_monKeyPressed

    private void cbx_cargoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbx_cargoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbx_cargoActionPerformed

    private void txt_fecKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_fecKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (txt_fec.getText().trim().length() == 10) {
                cbx_almacen.setEnabled(true);
                cbx_almacen.requestFocus();
            }
        }
    }//GEN-LAST:event_txt_fecKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_cer;
    private javax.swing.JButton btn_lim;
    private javax.swing.JButton btn_reg;
    public static javax.swing.JComboBox cbx_almacen;
    public static javax.swing.JComboBox cbx_cargo;
    public static javax.swing.JComboBox cbx_mon;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    public static javax.swing.JTextField txt_dir;
    public static javax.swing.JFormattedTextField txt_fec;
    public static javax.swing.JTextField txt_ndoc;
    public static javax.swing.JTextField txt_nom;
    public static javax.swing.JTextField txt_sue;
    public static javax.swing.JTextField txt_tel1;
    public static javax.swing.JTextField txt_tel2;
    // End of variables declaration//GEN-END:variables
}
