/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import Clases.Cl_Albaran;
import Clases.Cl_Almacen;
import Clases.Cl_Conectar;
import Clases.Cl_Empleado;
import Clases.Cl_Productos;
import Clases.Cl_Varios;
import Forms.frm_reg_traslado_almacen;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import sonomusic.frm_menu;
import static sonomusic.frm_menu.usu;

/**
 *
 * @author pc
 */
public class frm_ver_guias extends javax.swing.JInternalFrame {

    Cl_Varios ven = new Cl_Varios();
    Cl_Conectar con = new Cl_Conectar();
    Cl_Albaran alb = new Cl_Albaran();
    Cl_Almacen alm = new Cl_Almacen();
    Cl_Empleado emp = new Cl_Empleado();
    String valor = "";
    int i;

    /**
     * Creates new form frm_ver_guias
     */
    public frm_ver_guias() {
        initComponents();
        System.out.println(frm_menu.alm.getDireccion());
        String query = "select t.fecha, t.idTraslado, ao.nom_alm as origen, ad.raz_soc, ad.nom_alm as destino, td.desc_tipd as tipo_documento, t.ser_doc, t.nro_doc, em.nom_per, t.estado "
                + "from traslado as t "
                + "inner join almacen as ao on ao.idalmacen = t.almacen_origen "
                + "inner join almacen as ad on ad.idalmacen = t.almacen_destino "
                + "inner join tipo_doc as td on td.idtipo_doc = t.tipo_documento "
                + "inner join empleados as em on em.dni = t.nick "
                + "where t.estado = '0' and (t.almacen_origen = '" + frm_menu.alm.getId() + "' or t.almacen_destino = '" + frm_menu.alm.getId() + "') "
                + "order by t.fecha desc, t.idTraslado desc";
        String query = "select t.motivo, t.fecha, t.idTraslado, t.origen, t.raz_soc_dest, t.destino, td.desc_tipd as tipo_documento, t.ser_doc, t.nro_doc, t.nick, t.estado "
                + "from traslado as t "
                + "inner join tipo_doc as td on td.idtipo_doc = t.tipo_documento "
                + "inner join almacen as ao on ao.dir_alm = t.origen "
                + "where t.estado = '0' and (t.origen = '" + frm_menu.alm.getDireccion() + "' or t.destino = '" + frm_menu.alm.getDireccion() + "') order by t.fecha desc, t.idTraslado desc";
        ver_guia(query);
    }

    private void ver_guia(String query) {
        DefaultTableModel modelo;
        modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int fila, int columna) {
                return false;
            }
        };
        modelo.addColumn("Id");
        modelo.addColumn("Fecha");
        modelo.addColumn("Origen");
        modelo.addColumn("Raz. Social Destino");
        modelo.addColumn("Destino");
        modelo.addColumn("Documento");
        modelo.addColumn("Usuario");
        modelo.addColumn("Estado");
        try {
            Statement st = con.conexion();
            ResultSet rs = con.consulta(st, query);
            while (rs.next()) {
                Object[] fila = new Object[8];
                fila[0] = rs.getString("idtraslado");
                fila[1] = ven.fechaformateada(rs.getString("fecha"));
                fila[2] = rs.getString("origen");
                fila[3] = rs.getString("raz_soc");
                fila[4] = rs.getString("destino");
                if (rs.getInt("ser_doc") == 0 && rs.getInt("nro_doc") == 0) {
                    fila[5] = "------";
                } else {
                    fila[5] = rs.getString("tipo_documento") + " / " + rs.getString("ser_doc") + " - " + rs.getString("nro_doc");
                }
                String nick = rs.getString("nom_per");
                fila[6] = nick;
                switch (rs.getString("estado")) {
                    case "0":
                        fila[7] = "PENDIENTE";
                        break;
                    case "1":
                        fila[7] = "APROBADO";
                        break;
                    case "2":
                        fila[7] = "ANULADO";
                        break;
                }

                modelo.addRow(fila);
            }
            t_guias.setModel(modelo);
            t_guias.getColumnModel().getColumn(0).setPreferredWidth(60);
            t_guias.getColumnModel().getColumn(1).setPreferredWidth(90);
            t_guias.getColumnModel().getColumn(2).setPreferredWidth(100);
            t_guias.getColumnModel().getColumn(3).setPreferredWidth(250);
            t_guias.getColumnModel().getColumn(4).setPreferredWidth(100);
            t_guias.getColumnModel().getColumn(5).setPreferredWidth(250);
            t_guias.getColumnModel().getColumn(6).setPreferredWidth(200);
            t_guias.getColumnModel().getColumn(7).setPreferredWidth(80);
            ven.centrar_celda(t_guias, 0);
            ven.centrar_celda(t_guias, 1);
            ven.centrar_celda(t_guias, 2);
            ven.centrar_celda(t_guias, 4);
            ven.centrar_celda(t_guias, 5);
            ven.centrar_celda(t_guias, 7);
            con.cerrar(rs);
            con.cerrar(st);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
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
        txt_busqueda = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        t_guias = new javax.swing.JTable();
        btn_guia = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        btn_anu = new javax.swing.JButton();
        btn_envio = new javax.swing.JButton();
        cbx_bus = new javax.swing.JComboBox();
        cbx_estado = new javax.swing.JComboBox();

        setBackground(new java.awt.Color(254, 254, 254));
        setClosable(true);
        setResizable(true);
        setTitle("Listado de Traslados --  Enviados / Recibidos");

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/magnifier.png"))); // NOI18N
        jLabel1.setText("Buscar");

        txt_busqueda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_busquedaKeyPressed(evt);
            }
        });

        jScrollPane1.setBackground(new java.awt.Color(254, 254, 254));

        t_guias.setModel(new javax.swing.table.DefaultTableModel(
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
        t_guias.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        t_guias.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                t_guiasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(t_guias);

        btn_guia.setText("Ver Guia");
        btn_guia.setEnabled(false);
        btn_guia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_guiaActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/cancel.png"))); // NOI18N
        jButton2.setText("Cerrar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/add.png"))); // NOI18N
        jButton3.setText("Registrar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        btn_anu.setText("Anular");
        btn_anu.setEnabled(false);
        btn_anu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_anuActionPerformed(evt);
            }
        });

        btn_envio.setText("Verificar Envio");
        btn_envio.setEnabled(false);
        btn_envio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_envioActionPerformed(evt);
            }
        });

        cbx_bus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Fecha" }));
        cbx_bus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbx_busActionPerformed(evt);
            }
        });

        cbx_estado.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "PENDIENTE", "ANULADOS", "RECIBIDOS", "TODOS" }));
        cbx_estado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbx_estadoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 974, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_busqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbx_bus, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cbx_estado, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_envio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btn_guia)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_anu)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_busqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_envio, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbx_bus, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbx_estado, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_guia, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_anu, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if (usu.getPer_reg_traslado().equals("1")) {
            frm_reg_traslado_almacen tras = new frm_reg_traslado_almacen();
            frm_reg_traslado_almacen.accion = "registrar";
            frm_reg_traslado_almacen.btn_enviar.setVisible(true);
            frm_reg_traslado_almacen.btn_recibir.setVisible(false);
            ven.llamar_ventana(tras);
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(null, "Ud No tiene permisos");
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    public void datos_tienda(int id_almacen) {
        try {
            Statement st = con.conexion();
            String ver_dir = "select * from almacen where idAlmacen='" + id_almacen + "'";
            ResultSet rs = con.consulta(st, ver_dir);
            if (rs.next()) {
                frm_reg_traslado_almacen.txt_ruc_alm.setText(rs.getString("ruc"));
                frm_reg_traslado_almacen.txt_raz_alm.setText(rs.getString("raz_soc"));
                frm_reg_traslado_almacen.txt_partida.setText(rs.getString("dir_alm"));
            }
            con.cerrar(rs);
            con.cerrar(st);
        } catch (SQLException ex) {
            System.out.print(ex);
        }
    }
    private void btn_envioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_envioActionPerformed
        // cargar datos en frm_reg_traslado
        frm_reg_traslado_almacen traslado = new frm_reg_traslado_almacen();
        frm_reg_traslado_almacen.accion = "verificar";
        alb.setId(Integer.parseInt(t_guias.getValueAt(i, 0).toString()));
        try {
            Statement st = con.conexion();
            String ver_guia = "select * from traslado where idTraslado = '" + alb.getId() + "'";
            ResultSet rs = con.consulta(st, ver_guia);
            if (rs.next()) {
                frm_reg_traslado_almacen.cbx_documento.setEnabled(false);
                frm_reg_traslado_almacen.idtras = alb.getId();
                frm_reg_traslado_almacen.txt_fec.setText(ven.fechaformateada(rs.getString("fecha")));
                frm_reg_traslado_almacen.lbl_tienda.setText("Tienda Origen:");
                frm_reg_traslado_almacen.cbx_alm_de.setSelectedIndex(rs.getInt("almacen_origen") - 1);
                datos_tienda(rs.getInt("almacen_origen"));
                frm_reg_traslado_almacen.cbx_documento.setSelectedIndex(rs.getInt("tipo_documento") - 1);
                frm_reg_traslado_almacen.txt_ser.setText(rs.getString("ser_doc"));
                frm_reg_traslado_almacen.txt_num.setText(rs.getString("nro_doc"));
                frm_reg_traslado_almacen.txt_llegada.setText(frm_menu.alm.getDireccion());
                llenar_tguias(alb.getId());
                frm_reg_traslado_almacen.btn_verificar.setEnabled(true);
                frm_reg_traslado_almacen.btn_enviar.setVisible(false);
                frm_reg_traslado_almacen.btn_recibir.setVisible(true);
                frm_reg_traslado_almacen.t_detalle.requestFocus();
            }
            con.cerrar(rs);
            con.cerrar(st);
            ven.llamar_ventana(traslado);
            this.dispose();
        } catch (Exception ex) {
            System.out.println(ex);

        }
    }//GEN-LAST:event_btn_envioActionPerformed

    private void btn_guiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_guiaActionPerformed
        alb.setId(Integer.parseInt(t_guias.getValueAt(i, 0).toString()));
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("idtraslado", alb.getId());
        ven.ver_reporte("rpt_ver_guia", parametros);
    }//GEN-LAST:event_btn_guiaActionPerformed

    private int ver_id_alm(String dir_alm) {
        int ida = 0;
        try {
            Statement st = con.conexion();
            String ver_alm = "select idAlmacen from almacen where nom_alm like '" + dir_alm + "%'";
            ResultSet rs = con.consulta(st, ver_alm);
            if (rs.next()) {
                ida = rs.getInt("idAlmacen");
            }
            con.cerrar(rs);
            con.cerrar(st);
        } catch (Exception e) {
            System.out.println(e);
        }
        return ida;
    }

    private String ruc_alm(String id_alm) {
        String ruc = null;
        try {
            Statement st = con.conexion();
            String ver_alm = "select ruc from almacen where idAlmacen = '" + id_alm + "'";
            ResultSet rs = con.consulta(st, ver_alm);
            if (rs.next()) {
                ruc = rs.getString("ruc");
            }
            con.cerrar(rs);
            con.cerrar(st);
        } catch (Exception e) {
            System.out.println(e);
        }
        return ruc;
    }

    private String raz_soc(String id_alm) {
        String raz = null;
        try {
            Statement st = con.conexion();
            String ver_alm = "select raz_soc from almacen where idAlmacen = '" + id_alm + "'";
            ResultSet rs = con.consulta(st, ver_alm);
            if (rs.next()) {
                raz = rs.getString("raz_soc");
            }
            con.cerrar(rs);
            con.cerrar(st);
        } catch (Exception e) {
            System.out.println(e);
        }
        return raz;
    }

    private String direccion_almacen(String ruc) {
        String direccion = null;
        try {
            Statement st = con.conexion();
            String ver_alm = "select dir from empresa where ruc = '" + ruc + "'";
            ResultSet rs = con.consulta(st, ver_alm);
            if (rs.next()) {
                direccion = rs.getString("dir");
            }
            con.cerrar(rs);
            con.cerrar(st);
        } catch (Exception e) {
            System.out.println(e);
        }
        return direccion;
    }


    private void btn_anuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_anuActionPerformed
        if (usu.getPer_anu_traslado().equals("1")) {
            int confirmado = JOptionPane.showConfirmDialog(null, "¿Confirma eliminar el traslado?");
            if (JOptionPane.OK_OPTION == confirmado) {
                //anular guia si es pendiente
                alb.setId(Integer.parseInt(t_guias.getValueAt(i, 0).toString()));
                String est_guia = t_guias.getValueAt(i, 8).toString();

                if (est_guia.equals("PENDIENTE")) {

                    try {
                        Statement st = con.conexion();
                        String ver_det_env = "select fecha, ser_doc, nro_doc from traslado where idTraslado = '" + alb.getId() + "'";
                        ResultSet rs = con.consulta(st, ver_det_env);
                        if (rs.next()) {
                            alb.setFecha(rs.getString("fecha"));
                            alb.setSerie(rs.getInt("ser_doc"));
                            alb.setNumero(rs.getInt("nro_doc"));
                        }
                    } catch (Exception ex) {
                        System.out.println(ex);
                    }

                    Cl_Productos pro = new Cl_Productos();

                    String nom_alm_or = t_guias.getValueAt(i, 2).toString();
                    //ver id almacen origen:
                    int id_ao = ver_id_alm(nom_alm_or);
                    System.out.println("almacen origen " + id_ao + "\n");

                    String ruc = ruc_alm("" + id_ao);
                    String raz = raz_soc("" + id_ao);

                    //seleccionar detalle envio
                    try {
                        Statement st = con.conexion();
                        String ver_det_env = "select cant, idProductos from productos_traslado where idTraslado = '" + alb.getId() + "' ";
                        ResultSet rs = con.consulta(st, ver_det_env);
                        while (rs.next()) {
                            pro.setCan(rs.getDouble("cant"));
                            pro.setId_pro(rs.getInt("idProductos"));
                            // Seleccionar cantidad actual producto en almacen destino
                            try {
                                Statement st1 = con.conexion();
                                String ver_pro = "select cant from producto_almacen where idProductos = '" + pro.getId_pro() + "' and idAlmacen = '" + id_ao + "'";
                                ResultSet rs1 = con.consulta(st1, ver_pro);
                                if (rs1.next()) {
                                    pro.setCan_act_pro(rs1.getDouble("cant"));
                                }
                                con.cerrar(rs1);
                                con.cerrar(st1);
                            } catch (Exception ex) {
                                System.out.println(ex);
                            }
                            double can_new = pro.getCan_act_pro() + pro.getCan();

                            try {
                                Statement st1 = con.conexion();
                                String cam_can = "update producto_almacen set cant = '" + can_new + "' where idProductos = '" + pro.getId_pro() + "' and idAlmacen = '" + id_ao + "'";
                                con.actualiza(st1, cam_can);
                                con.cerrar(st1);
                            } catch (Exception e) {
                                System.out.println(e);
                            }

                            try {
                                Statement st1 = con.conexion();
                                String ins_kardex = "insert into kardex Values (null, '" + alb.getFecha() + "', '" + pro.getId_pro() + "', '" + pro.getCan() + "', '0.00' , '0.00', '0.00', "
                                        + "'" + alb.getSerie() + "', '" + alb.getNumero() + "', '4', '" + id_ao + "', '" + ruc + "', '" + raz + "', '5')";
                                con.actualiza(st1, ins_kardex);
                                con.cerrar(st1);
                            } catch (Exception ex) {
                                System.out.print(ex);
                            }
                        }
                        con.cerrar(rs);
                        con.cerrar(st);
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    // cambiar cantidades de almacen origen

//                try {
//                    Statement st = con.conexion();
//                    String del_det = "delete * from productos_traslado where idTraslado = '" + alb.getId() + "'";
//                    con.actualiza(st, del_det);
//                    con.cerrar(st);
//                } catch (Exception e) {
//                    System.out.println(e);
//                }
                    try {
                        Statement st = con.conexion();
                        String del_tra = "update traslado set estado = '2' where idTraslado = '" + alb.getId() + "'";
                        con.actualiza(st, del_tra);
                        con.cerrar(st);
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }
            }
            String query = "select motivo, fecha, idTraslado, origen, raz_soc_dest, destino, ser_doc, nro_doc, nick, "
                    + "estado from traslado where origen = '" + frm_menu.alm.getDireccion() + "' or destino = "
                    + "'" + frm_menu.alm.getDireccion() + "' order by fecha desc, idTraslado desc";
            ver_guia(query);
        } else {
            JOptionPane.showMessageDialog(null, "Ud No tiene permisos");
        }
    }//GEN-LAST:event_btn_anuActionPerformed

    private void txt_busquedaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_busquedaKeyPressed
        String texto = txt_busqueda.getText();
        if (cbx_bus.getSelectedIndex() == 0) {
            texto = ven.fechabase(texto);
            String query = "select t.motivo, t.fecha, t.idTraslado, t.origen, t.raz_soc_dest, t.destino, td.desc_tipd as tipo_documento, t.ser_doc, t.nro_doc, t.nick, "
                    + "t.estado from traslado as t inner join tipo_doc as td on td.idtipo_doc = t.tipo_documento where t.fecha = '" + texto + "' and (t.origen = '" + frm_menu.alm.getDireccion() + "' or t.destino = "
                    + "'" + frm_menu.alm.getDireccion() + "') order by t.fecha desc, t.idTraslado desc";
            ver_guia(query);
        }
    }//GEN-LAST:event_txt_busquedaKeyPressed

    private void cbx_estadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbx_estadoActionPerformed
        if (cbx_estado.getSelectedIndex() == 0) {
            String query = "select t.motivo, t.fecha, t.idTraslado, t.origen, t.raz_soc_dest, t.destino, td.desc_tipd as tipo_documento, t.ser_doc, t.nro_doc, t.nick, "
                    + "t.estado from traslado as t inner join tipo_doc as td on td.idtipo_doc = t.tipo_documento where t.estado = '0' and (t.origen = '" + frm_menu.alm.getDireccion() + "' or t.destino = "
                    + "'" + frm_menu.alm.getDireccion() + "') order by t.fecha desc, t.idTraslado desc";
            ver_guia(query);
        }

      /*  if (cbx_estado.getSelectedIndex() == 1) {
            String query = "select t.motivo, t.fecha, t.idTraslado, t.origen, t.raz_soc_dest, t.destino, td.desc_tipd as tipo_documento, t.ser_doc, t.nro_doc, t.nick, t.estado "
                    + "from traslado as t "
                    + "inner join tipo_doc as td on td.idtipo_doc = t.tipo_documento "
                    + "where t.estado = '2' and (t.origen = '" + frm_menu.alm.getDireccion() + "' or t.destino = '" + frm_menu.alm.getDireccion() + "') order by t.fecha desc, t.idTraslado desc";
            System.out.println(query);
            ver_guia(query);
        }*/

        if (cbx_estado.getSelectedIndex() == 2) {
            String query = "select t.motivo, t.fecha, t.idTraslado, t.origen, t.raz_soc_dest, t.destino, td.desc_tipd as tipo_documento, t.ser_doc, t.nro_doc, t.nick, t.estado "
                    + "from traslado as t "
                    + "inner join tipo_doc as td on td.idtipo_doc = t.tipo_documento "
                    + "where t.estado = '1' and (t.origen = '" + frm_menu.alm.getDireccion() + "' or t.destino = '" + frm_menu.alm.getDireccion() + "') order by t.fecha desc, t.idTraslado desc limit 50";
            ver_guia(query);
        }
    }//GEN-LAST:event_cbx_estadoActionPerformed

    private void cbx_busActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbx_busActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbx_busActionPerformed

    private void t_guiasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_t_guiasMouseClicked
        i = t_guias.getSelectedRow();
        btn_guia.setEnabled(true);

        switch (t_guias.getValueAt(i, 7).toString()) {
            case "APROBADO":
                btn_anu.setEnabled(false);
                btn_envio.setEnabled(false);
                break;
            case "ANULADO":
                btn_anu.setEnabled(false);
                btn_envio.setEnabled(false);
                break;
            default:
                btn_anu.setEnabled(true);
                break;
        }

        if (frm_menu.alm.getNom().equals(t_guias.getValueAt(i, 4).toString())) {
            btn_envio.setEnabled(true);
        } else {
            btn_envio.setEnabled(false);
        }
        if (frm_menu.alm.getNom().equals(t_guias.getValueAt(i, 2).toString())) {
            btn_anu.setEnabled(true);
        } else {
            btn_anu.setEnabled(false);
        }
    }//GEN-LAST:event_t_guiasMouseClicked

    private void llenar_tguias(int idtra) {
        frm_reg_traslado_almacen.detalle.addColumn("Cant. Rec.");
        try {
            Statement st = con.conexion();
            String ver_det = "select pt.idProductos, p.desc_pro, p.modelo, p.serie, p.marca, pt.cant, u.desc_und from productos_traslado as pt "
                    + "inner join productos as p on pt.idProductos = p.idProductos inner join und_medida as u on p.idUnd_medida=u.idUnd_medida"
                    + " where pt.idTraslado = '" + idtra + "'";
            ResultSet rs = con.consulta(st, ver_det);
            while (rs.next()) {
                Object[] fila = new Object[7];
                fila[0] = rs.getString("idProductos");
                fila[1] = rs.getString("p.desc_pro") + " " + rs.getString("p.modelo") + " " + rs.getString("p.serie");
                fila[2] = rs.getString("p.marca");
                fila[3] = "--";
                fila[4] = ven.formato_numero(rs.getDouble("cant"));
                fila[5] = rs.getString("u.desc_und");
                fila[6] = "0.00";
                frm_reg_traslado_almacen.detalle.addRow(fila);
            }
            frm_reg_traslado_almacen.t_detalle.setModel(frm_reg_traslado_almacen.detalle);
            frm_reg_traslado_almacen.t_detalle.getColumnModel().getColumn(0).setPreferredWidth(10);
            frm_reg_traslado_almacen.t_detalle.getColumnModel().getColumn(1).setPreferredWidth(350);
            frm_reg_traslado_almacen.t_detalle.getColumnModel().getColumn(2).setPreferredWidth(30);
            frm_reg_traslado_almacen.t_detalle.getColumnModel().getColumn(3).setPreferredWidth(30);
            frm_reg_traslado_almacen.t_detalle.getColumnModel().getColumn(4).setPreferredWidth(30);
            frm_reg_traslado_almacen.t_detalle.getColumnModel().getColumn(5).setPreferredWidth(30);
            frm_reg_traslado_almacen.t_detalle.getColumnModel().getColumn(6).setPreferredWidth(30);
            ven.centrar_celda(frm_reg_traslado_almacen.t_detalle, 0);
            ven.centrar_celda(frm_reg_traslado_almacen.t_detalle, 2);
            ven.derecha_celda(frm_reg_traslado_almacen.t_detalle, 3);
            ven.derecha_celda(frm_reg_traslado_almacen.t_detalle, 4);
            ven.centrar_celda(frm_reg_traslado_almacen.t_detalle, 5);
            ven.derecha_celda(frm_reg_traslado_almacen.t_detalle, 6);
            con.cerrar(rs);
            con.cerrar(st);
        } catch (SQLException e) {
            System.out.println(e);
        }

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_anu;
    private javax.swing.JButton btn_envio;
    private javax.swing.JButton btn_guia;
    private javax.swing.JComboBox cbx_bus;
    private javax.swing.JComboBox cbx_estado;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable t_guias;
    private javax.swing.JTextField txt_busqueda;
    // End of variables declaration//GEN-END:variables
}
