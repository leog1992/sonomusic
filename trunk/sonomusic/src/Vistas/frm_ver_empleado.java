package Vistas;

import Clases.*;
import Forms.frm_reg_empleado;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class frm_ver_empleado extends javax.swing.JInternalFrame {
    
    Cl_Varios ven = new Cl_Varios();
    Cl_Conectar con = new Cl_Conectar();
    Cl_Empleado emp = new Cl_Empleado();
    public static String ventana = "empleado";
    Integer i;
    String valor;
    DefaultTableModel mostrar;
    public static String fecha;
    DecimalFormatSymbols sim = new DecimalFormatSymbols(Locale.US);
    DecimalFormat formato = new DecimalFormat("####0.00", sim);
    
    public frm_ver_empleado() {
        initComponents();
        
        String query = "select e.dni, e.nom_per, e.tel_per, e.tel2_per, e.est_per, a.nom_alm, c.tipo_cargo from empleados as e "
                + "inner join almacen as a on e.idAlmacen=a.idAlmacen inner join cargo as c "
                + "on e.idCargo=c.idCargo where e.est_per = '1' order by a.nom_alm asc, dni asc";
        ver_empleado(query);
    }
    
    private void ver_empleado(String query) {
        try {
            mostrar = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int fila, int columna) {
                    return false;
                }
            };
            Statement st = con.conexion();
            ResultSet rs = con.consulta(st, query);
            mostrar.addColumn("DNI");
            mostrar.addColumn("Nombre Completo");
            mostrar.addColumn("Cargo");
            mostrar.addColumn("Telefono 1");
            mostrar.addColumn("Telefono 2");
            mostrar.addColumn("Almacen");
            mostrar.addColumn("Estado");

            //Creando las filas para el JTable
            while (rs.next()) {
                Object[] fila = new Object[7];
                fila[0] = rs.getObject("dni");
                fila[1] = rs.getObject("nom_per");
                fila[3] = rs.getObject("tel_per");
                fila[4] = rs.getObject("tel2_per");
                fila[5] = rs.getObject("nom_alm");
                fila[2] = rs.getObject("tipo_cargo");
                
                String estado = rs.getString("est_per");
                if (estado.equals("1")) {
                    fila[6] = "ACTIVO";
                } else {
                    fila[6] = "-";
                }
                mostrar.addRow(fila);
            }
            con.cerrar(st);
            con.cerrar(rs);
            t_empleado.setModel(mostrar);
            t_empleado.getColumnModel().getColumn(0).setPreferredWidth(80);
            t_empleado.getColumnModel().getColumn(1).setPreferredWidth(300);
            t_empleado.getColumnModel().getColumn(2).setPreferredWidth(190);
            t_empleado.getColumnModel().getColumn(3).setPreferredWidth(80);
            t_empleado.getColumnModel().getColumn(4).setPreferredWidth(80);
            t_empleado.getColumnModel().getColumn(5).setPreferredWidth(80);
            t_empleado.getColumnModel().getColumn(6).setPreferredWidth(80);
        } catch (SQLException e) {
            System.out.print(e);
        }
        
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txt_bus = new javax.swing.JTextField();
        btn_reg = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        t_empleado = new javax.swing.JTable();
        btn_clo = new javax.swing.JButton();
        btn_ret = new javax.swing.JButton();
        btn_mod = new javax.swing.JButton();
        btn_act = new javax.swing.JButton();
        cbx_tipo = new javax.swing.JComboBox();
        btn_adel = new javax.swing.JButton();
        cbx_est = new javax.swing.JComboBox();
        btn_rem = new javax.swing.JButton();

        setBackground(new java.awt.Color(254, 254, 254));
        setClosable(true);
        setTitle("Ver Empleado");

        jLabel1.setForeground(new java.awt.Color(212, 2, 2));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/find.png"))); // NOI18N
        jLabel1.setText("Buscar");

        txt_bus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_busKeyPressed(evt);
            }
        });

        btn_reg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/application_add.png"))); // NOI18N
        btn_reg.setText("Registrar");
        btn_reg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_regActionPerformed(evt);
            }
        });

        jScrollPane1.setBackground(new java.awt.Color(254, 254, 254));

        t_empleado.setModel(new javax.swing.table.DefaultTableModel(
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
        t_empleado.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        t_empleado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                t_empleadoMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                t_empleadoMousePressed(evt);
            }
        });
        t_empleado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                t_empleadoKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(t_empleado);
        if (t_empleado.getColumnModel().getColumnCount() > 0) {
            t_empleado.getColumnModel().getColumn(0).setPreferredWidth(30);
            t_empleado.getColumnModel().getColumn(1).setPreferredWidth(200);
            t_empleado.getColumnModel().getColumn(2).setPreferredWidth(190);
            t_empleado.getColumnModel().getColumn(3).setPreferredWidth(50);
            t_empleado.getColumnModel().getColumn(4).setPreferredWidth(30);
        }

        btn_clo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/cancel.png"))); // NOI18N
        btn_clo.setText("Cerrar");
        btn_clo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cloActionPerformed(evt);
            }
        });

        btn_ret.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/asterisk_orange.png"))); // NOI18N
        btn_ret.setText("Retirar");
        btn_ret.setEnabled(false);
        btn_ret.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_retActionPerformed(evt);
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

        btn_act.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/asterisk_orange.png"))); // NOI18N
        btn_act.setText("Re-Ingresar");
        btn_act.setEnabled(false);
        btn_act.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_actActionPerformed(evt);
            }
        });

        cbx_tipo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "DNI", "Nombre" }));
        cbx_tipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbx_tipoActionPerformed(evt);
            }
        });

        btn_adel.setText("Adelantos");
        btn_adel.setEnabled(false);
        btn_adel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_adelActionPerformed(evt);
            }
        });

        cbx_est.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "ACTIVO", "INACTIVO", "TODOS" }));
        cbx_est.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbx_estActionPerformed(evt);
            }
        });

        btn_rem.setText("Remuneracion");
        btn_rem.setEnabled(false);
        btn_rem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_remActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 952, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn_ret)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_act)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_adel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_rem)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_clo))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_bus, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbx_tipo, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cbx_est, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_reg)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_mod)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(btn_reg, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_mod, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_bus, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbx_tipo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbx_est, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_ret, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_clo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_act, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_adel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_rem, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_cloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cloActionPerformed
        this.dispose();
    }//GEN-LAST:event_btn_cloActionPerformed

    private void btn_regActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_regActionPerformed
//if (frm_menu.usu.getPem_emp().equals("1")) {
        frm_reg_empleado empleado = new frm_reg_empleado();
        ven.llamar_ventana(empleado);
        this.dispose();
//} else {
//            JOptionPane.showMessageDialog(null, "Ud. No tiene permisos");
//}
    }//GEN-LAST:event_btn_regActionPerformed

    private void t_empleadoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_t_empleadoMousePressed
        i = t_empleado.getSelectedRow();
        btn_mod.setEnabled(true);
        
        String estado = t_empleado.getValueAt(i, 6).toString();
        if (estado.equals("-")) {
            btn_act.setEnabled(true);
            btn_ret.setEnabled(false);
            btn_adel.setEnabled(false);
            btn_rem.setEnabled(false);
        } else {
            btn_act.setEnabled(false);
            btn_ret.setEnabled(true);
            btn_adel.setEnabled(true);
            btn_rem.setEnabled(true);
        }
    }//GEN-LAST:event_t_empleadoMousePressed

    private void btn_modActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_modActionPerformed
        //if (frm_menu.usu.getPem_emp().equals("1")) {
        frm_reg_empleado empleado = new frm_reg_empleado();
        
        empleado.win = "mod";
        emp.setDni((int) t_empleado.getValueAt(i, 0));
        try {
            Statement st = con.conexion();
            String query = "select * from empleados where dni = '" + emp.getDni() + "' ";
            ResultSet rs = con.consulta(st, query);
            if (rs.next()) {
                empleado.txt_ndoc.setText(rs.getString("dni"));
                empleado.txt_nom.setText(rs.getString("nom_per"));
                empleado.txt_dir.setText(rs.getString("dir_per"));
                empleado.txt_tel1.setText(rs.getString("tel_per"));
                empleado.txt_tel2.setText(rs.getString("tel2_per"));
                empleado.txt_sue.setText(rs.getString("sueldo"));
                try {
                    DateFormat dt = new SimpleDateFormat("dd/MM/yyyy");
                    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                    Date fec = df.parse(rs.getString("fecha"));
                    empleado.txt_fec.setText(dt.format(fec));
                } catch (Exception ex) {
                    System.out.println(ex);
                }
                empleado.txt_fec.setText(rs.getString("fecha"));
                empleado.cbx_almacen.setSelectedIndex(rs.getInt("idAlmacen") - 1);
                empleado.cbx_cargo.setSelectedIndex(rs.getInt("idCargo") - 1);
                empleado.txt_ndoc.setEnabled(false);
                empleado.txt_nom.setEditable(true);
                empleado.txt_dir.setEditable(true);
                empleado.txt_tel1.setEditable(true);
                empleado.txt_tel2.setEditable(true);
                empleado.txt_sue.setEditable(true);
                empleado.txt_fec.setEditable(true);
                empleado.cbx_almacen.setEnabled(true);
                empleado.cbx_cargo.setEnabled(true);
                empleado.btn_reg.setEnabled(true);
                empleado.btn_lim.setEnabled(false);
                empleado.txt_nom.requestFocus();
                ven.llamar_ventana(empleado);
                this.dispose();
            }
        } catch (SQLException ex) {
            System.out.print(ex);
        }
        //} else {
        //  JOptionPane.showMessageDialog(null, "Ud. No tiene permisos");
        //}
    }//GEN-LAST:event_btn_modActionPerformed

    private void btn_retActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_retActionPerformed
        //if (frm_menu.usu.getPem_emp().equals("1")) {
//retirar empleado
        emp.setDni((int) t_empleado.getValueAt(i, 0));
        Statement st = con.conexion();
        String retira = "update empleados set est_per = '0' where dni = '" + emp.getDni() + "'";
        con.actualiza(st, retira);
        con.cerrar(st);
        String query = "select e.dni,e.nom_per,e.tel_per,e.tel2_per,e.est_per,a.nom_alm,m.monto,c.tipo_cargo from empleados as e"
                + "inner join almacen as a on e.idAlmacen=a.idAlmacen inner join metas as m on e.idMetas=m.idMetas inner join cargo as c"
                + "on e.idCargo=c.idCargo order by dni asc";
        ver_empleado(query);
        //} else {
        //  JOptionPane.showMessageDialog(null, "Ud. No tiene permisos");
        //}
    }//GEN-LAST:event_btn_retActionPerformed

    private void txt_busKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_busKeyPressed
        try {
            if (cbx_tipo.getSelectedIndex() == 0) {
                valor = "dni";
            } else {
                valor = "nom_per";
            }
            if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                
                String buscar = txt_bus.getText();
                String query = "select e.dni,e.nom_per,e.tel_per,e.tel2_per,e.est_per,a.nom_alm,m.monto,c.tipo_cargo from empleados as e\n"
                        + "inner join almacen as a on e.idAlmacen=a.idAlmacen inner join metas as m on e.idMetas=m.idMetas inner join cargo as c \n"
                        + "on e.idCargo=c.idCargo where " + valor + " like '%" + buscar + "%' order by nom_per asc";
                ver_empleado(query);
                
            }
        } catch (Exception e) {
            System.out.println("error " + e.getMessage() + " en " + e.getLocalizedMessage());
        }
    }//GEN-LAST:event_txt_busKeyPressed

    private void btn_actActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_actActionPerformed
        //if (frm_menu.usu.getPem_emp().equals("1")) {
        //reingresa al trabajador
        emp.setDni((int) t_empleado.getValueAt(i, 0));
        Statement st = con.conexion();
        String ingresa = "update empleados set est_per = '1' where dni = '" + emp.getDni() + "'";
        con.actualiza(st, ingresa);
        con.cerrar(st);
        String query = "select e.dni,e.nom_per,e.tel_per,e.tel2_per,e.est_per,a.nom_alm,m.monto,c.tipo_cargo from empleados as e\n"
                + "inner join almacen as a on e.idAlmacen=a.idAlmacen inner join metas as m on e.idMetas=m.idMetas inner join cargo as c \n"
                + "on e.idCargo=c.idCargo order by dni asc";
        ver_empleado(query);
        //} else {
        // JOptionPane.showMessageDialog(null, "Ud. No tiene permisos");
        //}
    }//GEN-LAST:event_btn_actActionPerformed

    private void cbx_tipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbx_tipoActionPerformed
        txt_bus.setText("");
        txt_bus.requestFocus();
    }//GEN-LAST:event_cbx_tipoActionPerformed

    private void t_empleadoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_t_empleadoKeyPressed

    }//GEN-LAST:event_t_empleadoKeyPressed

    private void t_empleadoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_t_empleadoMouseClicked
        if (evt.getClickCount() == 2) {
            if (ventana.equals("movimiento")) {
                frm_movimientos movi = null;
                emp.setDni((int) t_empleado.getValueAt(i, 0));
                try {
                    Statement st = con.conexion();
                    String ver_pro = "select * from empleados where dni = '" + emp.getDni() + "'";
                    ResultSet rs = con.consulta(st, ver_pro);
                    if (rs.next()) {
                        movi.txt_dni.setText(emp.getDni() + "");
                        movi.txt_nom.setText(rs.getString("nom_per"));
                        movi.rbt_ini.setEnabled(true);
                        movi.rbt_ing.setEnabled(true);
                        movi.rbt_ing.setSelected(true);
                        movi.rbt_sal.setEnabled(true);
                        movi.txt_mot.setEditable(true);
                        movi.txt_mot.requestFocus();
                        this.dispose();
                    }
                } catch (SQLException ex) {
                    System.out.print(ex);
                }
            }
        }
    }//GEN-LAST:event_t_empleadoMouseClicked

    private void btn_adelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_adelActionPerformed
        frm_ver_adelantos adelanto = new frm_ver_adelantos();
        adelanto.txt_dni.setText(t_empleado.getValueAt(i, 0).toString());
        String dni = t_empleado.getValueAt(i, 0).toString();
        adelanto.txt_nom.setText(t_empleado.getValueAt(i, 1).toString());
        cargar_adelanto(dni);
        ven.llamar_ventana(adelanto);
        this.dispose();

    }//GEN-LAST:event_btn_adelActionPerformed

    private void cbx_estActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbx_estActionPerformed
        if (cbx_est.getSelectedIndex() == 0) {
            String query = "select e.dni, e.nom_per, e.tel_per, e.tel2_per, e.est_per, a.nom_alm, c.tipo_cargo from empleados as e "
                    + "inner join almacen as a on e.idAlmacen=a.idAlmacen inner join cargo as c "
                    + "on e.idCargo=c.idCargo where e.est_per = '1' order by a.nom_alm asc, dni asc";
            ver_empleado(query);
        }
        if (cbx_est.getSelectedIndex() == 1) {
            String query = "select e.dni, e.nom_per, e.tel_per, e.tel2_per, e.est_per, a.nom_alm, c.tipo_cargo from empleados as e "
                    + "inner join almacen as a on e.idAlmacen=a.idAlmacen inner join cargo as c "
                    + "on e.idCargo=c.idCargo where e.est_per = '0' order by a.nom_alm asc, dni asc";
            ver_empleado(query);
        }
        if (cbx_est.getSelectedIndex() == 2) {
            String query = "select e.dni, e.nom_per, e.tel_per, e.tel2_per, e.est_per, a.nom_alm, c.tipo_cargo from empleados as e "
                    + "inner join almacen as a on e.idAlmacen=a.idAlmacen inner join cargo as c "
                    + "on e.idCargo=c.idCargo order by a.nom_alm asc, dni asc";
            ver_empleado(query);
        }
    }//GEN-LAST:event_cbx_estActionPerformed

    private void btn_remActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_remActionPerformed
        frm_ver_pago pago = new frm_ver_pago();
        String dni = t_empleado.getValueAt(i, 0).toString();
        String sql = "select p.idpago, p.fecha , p.comisiones, p.otrosingresos, p.adelantos, p.otrosdescuentos, "
                + "p.salario from pago as p where p.dni = '" + dni + "' order by p.idpago";
        cargar_remuneracion(sql);
        pago.txt_dni.setText(dni);
        pago.txt_col.setText(t_empleado.getValueAt(i, 1).toString());
        ven.llamar_ventana(pago);
        this.dispose();
    }//GEN-LAST:event_btn_remActionPerformed
    
    void cargar_remuneracion(String sql) {
        DefaultTableModel modelo;
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
            modelo.addColumn("Comision");
            modelo.addColumn("Otros Ingresos");
            modelo.addColumn("Adelantos");
            modelo.addColumn("Otros Descuentos");
            modelo.addColumn("Salario");
            
            ResultSet rs = con.consulta(st, sql);
            Object[] dato = new Object[7];
            while (rs.next()) {
                dato[0] = rs.getObject("idpago");
                dato[1] = ven.fechaformateada(rs.getString("fecha"));
                dato[2] = rs.getObject("comisiones");
                dato[3] = rs.getObject("otrosingresos");
                dato[4] = rs.getObject("adelantos");
                dato[5] = rs.getObject("otrosdescuentos");
                dato[6] = rs.getObject("salario");
                modelo.addRow(dato);
            }
            con.cerrar(st);
            con.cerrar(rs);
            frm_ver_pago.t_pago.setModel(modelo);
            frm_ver_pago.t_pago.getColumnModel().getColumn(0).setPreferredWidth(30);
            frm_ver_pago.t_pago.getColumnModel().getColumn(1).setPreferredWidth(80);
            frm_ver_pago.t_pago.getColumnModel().getColumn(2).setPreferredWidth(60);
            frm_ver_pago.t_pago.getColumnModel().getColumn(3).setPreferredWidth(60);
            frm_ver_pago.t_pago.getColumnModel().getColumn(4).setPreferredWidth(60);
            frm_ver_pago.t_pago.getColumnModel().getColumn(5).setPreferredWidth(60);
            frm_ver_pago.t_pago.getColumnModel().getColumn(6).setPreferredWidth(60);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e);
        }
    }
    
    private void cargar_adelanto(String dni) {
        DefaultTableModel modelo;
        modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int i, int i1) {
                return false;
            }
        };
        Statement st = con.conexion();
        modelo.addColumn("Id");
        modelo.addColumn("Fecha");
        modelo.addColumn("Monto");
        modelo.addColumn("Estado");
        try {
            String ver_ade = "select * from adelanto where dni = '" + dni + "'";
            ResultSet rs = con.consulta(st, ver_ade);
            Object fila[] = new Object[4];
            while (rs.next()) {
                fila[0] = rs.getString("idadelanto");
                fila[1] = ven.fechaformateada(rs.getString("fecha"));
                fila[2] = rs.getDouble("monto");
                fila[3] = "--";
                modelo.addRow(fila);
            }
            con.cerrar(rs);
            con.cerrar(st);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        frm_ver_adelantos.t_adelantos.setModel(modelo);
        frm_ver_adelantos.t_adelantos.getColumnModel().getColumn(0).setPreferredWidth(20);
        frm_ver_adelantos.t_adelantos.getColumnModel().getColumn(1).setPreferredWidth(80);
        frm_ver_adelantos.t_adelantos.getColumnModel().getColumn(2).setPreferredWidth(80);
        frm_ver_adelantos.t_adelantos.getColumnModel().getColumn(3).setPreferredWidth(80);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_act;
    private javax.swing.JButton btn_adel;
    private javax.swing.JButton btn_clo;
    private javax.swing.JButton btn_mod;
    private javax.swing.JButton btn_reg;
    private javax.swing.JButton btn_rem;
    private javax.swing.JButton btn_ret;
    private javax.swing.JComboBox cbx_est;
    private javax.swing.JComboBox cbx_tipo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable t_empleado;
    private javax.swing.JTextField txt_bus;
    // End of variables declaration//GEN-END:variables
}
