package Vistas;

import Clases.Cl_Albaran;
import Clases.Cl_Almacen;
import Clases.Cl_Cliente;
import Clases.Cl_Conectar;
import Clases.Cl_Hilo_Imprime;
import Clases.Cl_Movimiento;
import Clases.Cl_Pedido;
import Clases.Cl_Productos;
import Clases.Cl_Tipo_Documentos;
import Clases.Cl_Tipo_Pago;
import Clases.Cl_Usuario;
import Clases.Cl_Varios;
import Clases.Print_Venta_Ticket;
import static Vistas.frm_ver_letras_pedido.modelo;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import sonomusic.frm_menu;

/**
 *
 * @author CONTABILIDAD 02
 */
public class frm_ver_venta extends javax.swing.JInternalFrame {

    Cl_Conectar con = new Cl_Conectar();
    Cl_Varios ven = new Cl_Varios();
    Cl_Pedido ped = new Cl_Pedido();
    Cl_Tipo_Pago tipa = new Cl_Tipo_Pago();
    Cl_Tipo_Documentos tido = new Cl_Tipo_Documentos();
    Cl_Almacen alm = new Cl_Almacen();
    Cl_Albaran alb = new Cl_Albaran();
    Cl_Productos pro = new Cl_Productos();
    Cl_Movimiento mov = new Cl_Movimiento();
    Cl_Cliente cli = new Cl_Cliente();
    Cl_Usuario usu = new Cl_Usuario();
    public static String id;

    DefaultTableModel mostrar;
    Integer i;
    String fecha_hoy;

    /**
     * Creates new form frm_ver_cobranzas
     */
    public frm_ver_venta() {
        initComponents();
        try {
            fecha_hoy = ven.getFechaActual();
            String ver_ped = "select p.idPedido, p.fec_ped , p.fec_pago, p.idAlmacen, t.desc, p.idTipo_pago, p.total, p.est_ped, td.idtipo_doc, td.desc_tipd , "
                    + "p.serie_doc, p.nro_doc, u.nick, a.nom_alm , t.desc , p.est_ped, p.cli_nom, c.nom_per, p.cli_doc "
                    + "from pedido as p "
                    + "inner join tipo_pago as t on p.idTipo_pago=t.idTipo_pago "
                    + "inner join tipo_doc as td on p.idtipo_doc=td.idtipo_doc "
                    + "inner join usuario as u on p.nick = u.nick "
                    + "inner join  almacen as a on p.idAlmacen=a.idAlmacen "
                    + "inner join cliente as c on p.cli_doc=c.nro_doc  "
                    + "where p.idAlmacen='" + sonomusic.frm_menu.alm.getId() + "' and p.fec_ped='" + fecha_hoy + "' "
                    + "order by p.idPedido asc";
            ver_pedidos(ver_ped);
        } catch (Exception e) {
            System.out.println("error " + e);
        }
        cbx_estado.setSelectedIndex(0);
        txt_bus.requestFocus();
    }
    //

    void cargar_permiso() {
        if (frm_menu.usu.getPer_ver_caja().equals("1")) {
            txt_bus.setEnabled(false);

        }
    }

    double pagados() {
        double pag = 0;
        try {
            Statement st = con.conexion();
            String sql = "select sum(monto) as calculo from letras_pedido where idPedido='" + id + "'";
            ResultSet rs = con.consulta(st, sql);
            if (rs.next()) {
                pag = rs.getDouble("calculo");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error " + e);
        }
        return pag;
    }

    private void ver_pedidos(String query) {
        try {
            mostrar = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int fila, int columna) {
                    return false;
                }
            };
            Statement st = con.conexion();
            ResultSet rs = con.consulta(st, query);
            mostrar.addColumn("Id");
            mostrar.addColumn("Fecha Venta");
            mostrar.addColumn("Fecha Pago");
            mostrar.addColumn("Tipo Doc.");
            mostrar.addColumn("D.N.I / R.U.C");
            mostrar.addColumn("Cliente");
            mostrar.addColumn("Total");
            mostrar.addColumn("Vendedor");
            mostrar.addColumn("Almacen");
            mostrar.addColumn("Estado");
            double sum = 0.0;
            //Creando las filas para el JTable
            while (rs.next()) {
                Object[] fila = new Object[10];
                fila[0] = rs.getObject("idPedido");
                String tipodoc = rs.getString("idtipo_doc");
                String dtido = rs.getString("desc_tipd");
                String serie = rs.getString("serie_doc");
                String nro = rs.getString("nro_doc");

                fila[1] = ven.fechaformateada(rs.getString("fec_ped"));
                fila[2] = ven.fechaformateada(rs.getString("fec_pago"));
                if (tipodoc.equals("1") & serie.equals("000") & nro.equals("0000000")) {
                    fila[3] = "VENTA SIN DOCUMENTO";
                } else {
                    if (tipodoc.equals("6")) {
                        if (rs.getString("cli_doc").length() == 8) {
                            dtido = "TICKET BOLETA";
                        }
                        if (rs.getString("cli_doc").length() == 11) {
                            dtido = "TICKET FACTURA";
                        }
                    }
                    fila[3] = dtido + " / " + serie + " - " + nro;
                }
                fila[4] = rs.getObject("cli_doc");
                fila[5] = rs.getString("cli_nom");
                if (frm_menu.usu.getPer_ver_caja().equals("0") && rs.getString("est_ped").equals("1")) {
                    fila[6] = "0.00";
                } else {
                    fila[6] = ven.formato_numero(rs.getDouble("total"));
                }

                fila[7] = rs.getString("nick");
                fila[8] = rs.getString("nom_alm");

                if (rs.getString("est_ped").equals("1")) {
                    fila[9] = "PAGADO";
                }
                if (rs.getString("est_ped").equals("2")) {
                    fila[9] = "SEPARADO";
                }
                if (rs.getString("est_ped").equals("3")) {
                    fila[9] = "ANULADO";
                }
                if (rs.getString("est_ped").equals("4")) {
                    fila[9] = "POR RECOGER";
                }
                if (rs.getString("est_ped").equals("5")) {
                    fila[9] = "ENTREGADO";
                }
                if (rs.getString("est_ped").equals("6")) {
                    fila[9] = "POR COBRAR";
                }

                if (frm_menu.usu.getPer_ver_caja().equals("1")) {
                    sum += (rs.getDouble("total"));
                }

                mostrar.addRow(fila);
            }
            txt_tot.setText(ven.formato_totales(sum));
            con.cerrar(st);
            con.cerrar(rs);
            t_facturas.setModel(mostrar);
            t_facturas.getColumnModel().getColumn(0).setPreferredWidth(30);
            t_facturas.getColumnModel().getColumn(1).setPreferredWidth(80);
            t_facturas.getColumnModel().getColumn(2).setPreferredWidth(80);
            t_facturas.getColumnModel().getColumn(3).setPreferredWidth(170);
            t_facturas.getColumnModel().getColumn(4).setPreferredWidth(80);
            t_facturas.getColumnModel().getColumn(5).setPreferredWidth(170);
            t_facturas.getColumnModel().getColumn(6).setPreferredWidth(80);
            t_facturas.getColumnModel().getColumn(7).setPreferredWidth(80);
            t_facturas.getColumnModel().getColumn(8).setPreferredWidth(80);
            t_facturas.getColumnModel().getColumn(9).setPreferredWidth(80);
            t_facturas.setDefaultRenderer(Object.class, new Clases.render_ventas());
        } catch (SQLException e) {
            System.out.println("GRAVE ERROR: " + e + " EN " + e.getLocalizedMessage());
        }

    }

    void calcula_total() {
        double total = 0.0;
        for (int j = 0; j < t_facturas.getRowCount(); j++) {
            if (t_facturas.getValueAt(j, 9).toString().equals("PAGADO")) {
                double subtot = (double) t_facturas.getValueAt(j, 5);
                total += subtot;
            }
        }
        txt_tot.setText(total + "");
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
        txt_bus = new javax.swing.JTextField();
        cbx_estado = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        t_facturas = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txt_tot = new javax.swing.JTextField();
        btn_anu = new javax.swing.JButton();
        btn_pagar = new javax.swing.JButton();
        btn_det = new javax.swing.JButton();
        btn_ent = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox();

        setBackground(new java.awt.Color(254, 254, 254));
        setClosable(true);
        setTitle("Registro de Ventas");

        jLabel1.setForeground(new java.awt.Color(255, 0, 0));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/find.png"))); // NOI18N
        jLabel1.setText("Buscar");

        txt_bus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_busKeyPressed(evt);
            }
        });

        cbx_estado.setForeground(new java.awt.Color(255, 0, 0));
        cbx_estado.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Fecha", "Cliente" }));
        cbx_estado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbx_estadoActionPerformed(evt);
            }
        });

        jScrollPane1.setBackground(new java.awt.Color(254, 254, 254));

        t_facturas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6", "Title 7", "Title 8", "Title 9", "Title 10"
            }
        ));
        t_facturas.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        t_facturas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                t_facturasMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                t_facturasMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(t_facturas);
        if (t_facturas.getColumnModel().getColumnCount() > 0) {
            t_facturas.getColumnModel().getColumn(0).setPreferredWidth(10);
            t_facturas.getColumnModel().getColumn(1).setPreferredWidth(50);
            t_facturas.getColumnModel().getColumn(2).setPreferredWidth(30);
            t_facturas.getColumnModel().getColumn(3).setPreferredWidth(250);
            t_facturas.getColumnModel().getColumn(4).setPreferredWidth(30);
            t_facturas.getColumnModel().getColumn(5).setPreferredWidth(60);
        }

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/cancel.png"))); // NOI18N
        jButton2.setText("Cerrar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel2.setForeground(new java.awt.Color(255, 0, 0));
        jLabel2.setText("Total:");

        txt_tot.setEditable(false);
        txt_tot.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        btn_anu.setForeground(new java.awt.Color(255, 0, 0));
        btn_anu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/delete.png"))); // NOI18N
        btn_anu.setText("Anular");
        btn_anu.setEnabled(false);
        btn_anu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_anuActionPerformed(evt);
            }
        });

        btn_pagar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/pagar.png"))); // NOI18N
        btn_pagar.setText("Pagar");
        btn_pagar.setEnabled(false);
        btn_pagar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pagarActionPerformed(evt);
            }
        });

        btn_det.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/blog.png"))); // NOI18N
        btn_det.setText("Ver Detalle");
        btn_det.setEnabled(false);
        btn_det.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_detActionPerformed(evt);
            }
        });

        btn_ent.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/deposito.png"))); // NOI18N
        btn_ent.setText("Entregar Producto");
        btn_ent.setEnabled(false);
        btn_ent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_entActionPerformed(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Separaciones", "Creditos", "Por Entregar", "Entregados", "Anulados" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
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
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_bus, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbx_estado, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 154, Short.MAX_VALUE)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_anu)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_pagar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_det))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_tot, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(91, 91, 91)
                        .addComponent(btn_ent)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_bus, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cbx_estado, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                        .addComponent(btn_pagar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_anu, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_det, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 382, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_tot, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_ent, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void cbx_estadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbx_estadoActionPerformed
        if (cbx_estado.isDisplayable()) {
            txt_bus.requestFocus();
        }
    }//GEN-LAST:event_cbx_estadoActionPerformed

    private void txt_busKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_busKeyPressed
        String buscar = txt_bus.getText();
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (txt_bus.getText().length() > 1) {
                if (cbx_estado.getSelectedIndex() == 0) {
                    if (txt_bus.getText().length() == 10) {
                        buscar = ven.fechabase(buscar);
                        System.out.println(buscar);
                        String ver_ped = "select p.idPedido, p.fec_ped , p.fec_pago,p.idAlmacen,t.desc,p.idTipo_pago,p.total,p.est_ped,td.idtipo_doc,td.desc_tipd , "
                                + "p.serie_doc, p.nro_doc, u.nick, a.nom_alm , t.desc , p.est_ped,c.nom_per,p.cli_doc from pedido as p inner join tipo_pago as t "
                                + "on p.idTipo_pago=t.idTipo_pago inner join tipo_doc as td on p.idtipo_doc=td.idtipo_doc inner join usuario as u "
                                + "on p.nick = u.nick inner join  almacen as a on p.idAlmacen=a.idAlmacen inner join cliente as c on p.cli_doc=c.nro_doc  where p.idAlmacen='" + sonomusic.frm_menu.alm.getId() + "' and p.fec_ped = '" + buscar + "'";
                        ver_pedidos(ver_ped);
                    }
                } else {
                    System.out.println("almacen: " + sonomusic.frm_menu.alm.getId());
                    String ver_ped = "select p.idPedido, p.fec_ped , p.fec_pago,p.idAlmacen,t.desc,p.idTipo_pago,p.total,p.est_ped,td.idtipo_doc,td.desc_tipd , "
                            + "p.serie_doc, p.nro_doc, u.nick, a.nom_alm , t.desc , p.est_ped,c.nom_per,p.cli_doc from pedido as p inner join tipo_pago as t "
                            + "on p.idTipo_pago=t.idTipo_pago inner join tipo_doc as td on p.idtipo_doc=td.idtipo_doc inner join usuario as u "
                            + "on p.nick = u.nick inner join  almacen as a on p.idAlmacen=a.idAlmacen inner join cliente as c on p.cli_doc=c.nro_doc  where p.idAlmacen='" + sonomusic.frm_menu.alm.getId() + "' "
                            + "and (p.cli_doc like '%" + buscar + "%' or c.nom_per like '%" + buscar + "%') order by p.fec_ped desc, p.idPedido desc";
                    ver_pedidos(ver_ped);
                }
            }
        }
    }//GEN-LAST:event_txt_busKeyPressed

    private void btn_anuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_anuActionPerformed
        //if (frm_menu.usu.getPve_eli().equals("1")) {
        int confirmado = JOptionPane.showConfirmDialog(null, "¿Confirma anular la venta?");

        if (JOptionPane.OK_OPTION == confirmado) {
            ped.setId_ped(t_facturas.getValueAt(i, 0).toString());
            cli.setNom_cli(t_facturas.getValueAt(i, 5).toString());
            Double suma_pro = 0.00;
            //ver datos de pedido 
            try {
                Statement st = con.conexion();
                String ver_ped = "select idTipo_pago, descuento, fec_ped, idAlmacen, total, cli_doc, serie_doc, nro_doc, idtipo_doc, est_ped, albaran from pedido where idPedido = '" + ped.getId_ped() + "'";
                ResultSet rs = con.consulta(st, ver_ped);
                if (rs.next()) {
                    tipa.setId(rs.getInt("idTipo_pago"));
                    ped.setDes_ped(rs.getDouble("descuento"));
                    ped.setFec_ped(rs.getString("fec_ped"));
                    alm.setId(rs.getInt("idAlmacen"));
                    alb.setId(rs.getInt("albaran"));
                    tido.setSerie(rs.getInt("serie_doc"));
                    tido.setNro(rs.getInt("nro_doc"));
                    tido.setId(rs.getInt("idtipo_doc"));
                    cli.setNro_doc(rs.getString("cli_doc"));
                    ped.setTotal(rs.getDouble("total"));
                    ped.setEst_ped(rs.getString("est_ped"));
                }
                con.cerrar(rs);
                con.cerrar(st);
            } catch (SQLException ex) {
                System.out.print(ex);
            }

            //actualizar pedido a anulado
            try {
                Statement st = con.conexion();
                String act_ped = "update pedido set est_ped = '3', total = '0.0', descuento = '0.00', idTipo_pago='1' where idPedido = '" + ped.getId_ped() + "'";
                con.actualiza(st, act_ped);
                con.cerrar(st);
            } catch (Exception ex) {
                System.out.print(ex);
            }
            if (!ped.getEst_ped().equals("2")) {
                //seleccionar detalle de pedido, cantidad de productos;
                try {
                    Statement st = con.conexion();
                    String ver_ped = "select idProductos, precio, cantidad from detalle_pedido where idPedido = '" + ped.getId_ped() + "'";
                    System.out.println(ver_ped);
                    ResultSet rs = con.consulta(st, ver_ped);
                    while (rs.next()) {
                        pro.setId_pro(rs.getInt("idProductos"));
                        pro.setCan(rs.getDouble("cantidad"));
                        pro.setPre_pro(rs.getDouble("precio"));
                        suma_pro = pro.getCan() * pro.getPre_pro();
                        Double pro_can = 0.00;
                        Double new_can = 0.00;
                        Double pro_can_alm = 0.00;
                        Double new_can_alm = 0.00;

                        try {
                            Statement st1 = con.conexion();
                            String upt_pro_alm = "update producto_almacen set cant = cant + '" + new_can_alm + "' where idProductos = '" + pro.getId_pro() + "' and idAlmacen = '" + alm.getId() + "'";
                            System.out.println(upt_pro_alm);
                            con.actualiza(st1, upt_pro_alm);
                            con.cerrar(st1);
                            System.out.println("actualizando cantidad del producto " + pro.getId_pro() + " en el almacen " + alm.getId() + "\n");
                        } catch (Exception ex1) {
                            System.out.print(ex1);
                        }

                        try {
                            Statement st1 = con.conexion();
                            String upt_pro = "update productos set cant_actual = cant_actual + '" + new_can + "' where idProductos = '" + pro.getId_pro() + "'";
                            System.out.println(upt_pro);
                            con.actualiza(st1, upt_pro);
                            con.cerrar(st1);
                            System.out.println("atualizando cantidad total del producto " + pro.getId_pro() + "\n");
                        } catch (Exception ex1) {
                            System.out.print(ex1);
                        }

                        try {
                            Statement st1 = con.conexion();
                            String ins_kardex = "insert into kardex Values (null, current_date(), '" + pro.getId_pro() + "', '" + pro.getCan() + "', '" + pro.getPre_pro() + "', '0.00', '0.00',"
                                    + "'" + tido.getSerie() + "', '" + tido.getNro() + "', '" + tido.getId() + "', '" + alm.getId() + "', '" + cli.getNro_doc() + "', '" + cli.getNom_cli() + "', '5')";
                            System.out.println(ins_kardex);
                            con.actualiza(st1, ins_kardex);
                            con.cerrar(st1);
                            System.out.println("insertando producto en el kardex \n");
                        } catch (Exception ex) {
                            System.out.print(ex);
                        }

                    }
                    con.cerrar(rs);
                    con.cerrar(st);

                } catch (SQLException ex) {
                    System.out.print(ex);
                }
            }

            //calcular monto 
            System.out.print(tipa.getId());
            //devolver dinero pagado 
            try {
                Statement st = con.conexion();
                String ver_let_ped = "select monto, tipo_pago, fecha from letras_pedido where idPedido = '" + ped.getId_ped() + "'";
                System.out.println(ver_let_ped);
                ResultSet rs = con.consulta(st, ver_let_ped);
                while (rs.next()) {
                    if (rs.getString("tipo_pago").equals("EFECTIVO")) {
                        try {
                            mov.setGlosa("DEVOLUCION POR VENTA ANULADA - " + tido.getSerie() + " - " + tido.getNro() + " / " + cli.getNro_doc());
                            mov.setFec_mov(rs.getString("fecha"));
                            mov.setEgreso(rs.getDouble("monto"));
                            Statement st1 = con.conexion();
                            String ins_mov = "insert into movimiento Values (null, '" + mov.getGlosa() + "', current_date() , "
                                    + "'0.00', '" + mov.getEgreso() + "' , '" + sonomusic.frm_menu.lbl_user.getText() + "', "
                                    + "'" + alm.getId() + "', 'C', '" + frm_menu.caja.getId() + "')";
                            System.out.println(ins_mov);
                            con.actualiza(st1, ins_mov);
                            con.cerrar(st1);
                        } catch (Exception ex) {
                            System.out.print(ex);
                        }
                    } else {
                        try {
                            mov.setGlosa("DEVOLUCION POR VENTA ANULADA - " + tido.getSerie() + " - " + tido.getNro() + " / " + cli.getNro_doc());
                            mov.setFec_mov(rs.getString("fecha"));
                            mov.setEgreso(rs.getDouble("monto"));
                            Statement st1 = con.conexion();
                            String ins_mov = "insert into movimiento Values (null, '" + mov.getGlosa() + "', current_date() , "
                                    + "'0.00', '" + mov.getEgreso() + "' , '" + sonomusic.frm_menu.lbl_user.getText() + "', "
                                    + "'" + alm.getId() + "', 'B', '" + frm_menu.caja.getId() + "')";
                            System.out.println(ins_mov);
                            con.actualiza(st1, ins_mov);
                            con.cerrar(st1);
                        } catch (Exception ex) {
                            System.out.print(ex);
                        }
                    }
                }
                con.cerrar(rs);
                con.cerrar(st);
            } catch (SQLException e) {
                System.out.println(e.getLocalizedMessage());
            }

            //eliminar pago en detalle_pago
            try {
                Statement st = con.conexion();
                String del_mov_caja = "delete from letras_pedido where idPedido = '" + ped.getId_ped() + "'";
                System.out.println(del_mov_caja);
                con.actualiza(st, del_mov_caja);
                con.cerrar(st);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex);
                System.err.print("Error en:" + ex.getLocalizedMessage());

            }

            String ver_ped = "select p.idPedido, p.fec_ped , p.fec_pago,p.idAlmacen,t.desc,p.idTipo_pago,p.total,p.est_ped,td.idtipo_doc,td.desc_tipd , "
                    + "p.serie_doc, p.nro_doc, u.nick, a.nom_alm , t.desc , p.est_ped,c.nom_per,p.cli_doc from pedido as p inner join tipo_pago as t "
                    + "on p.idTipo_pago=t.idTipo_pago inner join tipo_doc as td on p.idtipo_doc=td.idtipo_doc inner join usuario as u "
                    + "on p.nick = u.nick inner join  almacen as a on p.idAlmacen=a.idAlmacen inner join cliente as c on p.cli_doc=c.nro_doc  "
                    + "where p.idAlmacen='" + sonomusic.frm_menu.alm.getId() + "' and p.fec_ped='" + fecha_hoy + "'";
            ver_pedidos(ver_ped);
            cbx_estado.setSelectedIndex(0);
            txt_bus.requestFocus();

        }
//        } else {
//            JOptionPane.showMessageDialog(null, "Ud. No tiene permisos");
//        }
    }//GEN-LAST:event_btn_anuActionPerformed

    private void t_facturasMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_t_facturasMousePressed
        i = t_facturas.getSelectedRow();
        String est = t_facturas.getValueAt(i, 9).toString();

        switch (est) {
            case "PAGADO":
                btn_anu.setEnabled(true);
                btn_ent.setEnabled(false);
                if (frm_menu.usu.getPer_ver_caja().equals("1")) {
                    btn_det.setEnabled(true);
                    btn_pagar.setEnabled(true);
                } else {
                    btn_det.setEnabled(false);
                    btn_pagar.setEnabled(false);
                }
                break;
            case "ANULADO":
                btn_anu.setEnabled(false);
                btn_det.setEnabled(true);
                btn_ent.setEnabled(false);
                btn_pagar.setEnabled(false);
                break;
            case "SEPARADO":
                btn_anu.setEnabled(true);
                btn_det.setEnabled(true);
                btn_ent.setEnabled(false);
                btn_pagar.setEnabled(true);
                break;
            case "POR RECOGER":
                btn_anu.setEnabled(false);
                btn_det.setEnabled(true);
                btn_ent.setEnabled(true);
                btn_pagar.setEnabled(true);
                break;
            case "ENTREGADO":
                btn_anu.setEnabled(false);
                btn_det.setEnabled(true);
                btn_ent.setEnabled(false);
                btn_pagar.setEnabled(true);
                break;
            case "POR COBRAR":
                btn_anu.setEnabled(true);
                btn_det.setEnabled(true);
                btn_ent.setEnabled(false);
                btn_pagar.setEnabled(true);
                break;
        }
    }//GEN-LAST:event_t_facturasMousePressed

    private void btn_pagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pagarActionPerformed
        frm_ver_letras_pedido letras = new frm_ver_letras_pedido();
        String est = t_facturas.getValueAt(i, 9).toString();
        if (est.equals("PAGADO")) {
            letras.btn_registrar.setEnabled(false);
        }
        letras.lbl_dni.setText(t_facturas.getValueAt(i, 4).toString());
        letras.lbl_nombre.setText(t_facturas.getValueAt(i, 5).toString());
        letras.lbl_tipo_doc.setText(t_facturas.getValueAt(i, 3).toString());
        letras.lbl_fecha.setText(t_facturas.getValueAt(i, 1).toString());
        letras.lbl_total.setText(t_facturas.getValueAt(i, 6).toString());
        id = t_facturas.getValueAt(i, 0).toString();

        //ver_letras_pedido         
        try {
            letras.modelo = new DefaultTableModel() {

                @Override
                public boolean isCellEditable(int i, int i1) {
                    return false;
                }
            };
            letras.modelo.addColumn("Fecha");
            letras.modelo.addColumn("Tipo Pago");
            letras.modelo.addColumn("Monto");
            Statement st = con.conexion();
            String sql = "select idLetras_Pedido, tipo_pago, monto, fecha, idPedido from  letras_pedido where idPedido='" + id + "' ";
            ResultSet rs = con.consulta(st, sql);
            Object dato[] = new Object[3];
            while (rs.next()) {
                dato[0] = rs.getObject("fecha");
                dato[1] = rs.getObject("tipo_pago");
                dato[2] = rs.getObject("monto");
                letras.modelo.addRow(dato);
            }
            letras.t_letras.setModel(modelo);
            letras.t_letras.getColumnModel().getColumn(0).setPreferredWidth(100);
            letras.t_letras.getColumnModel().getColumn(1).setPreferredWidth(100);
            letras.t_letras.getColumnModel().getColumn(2).setPreferredWidth(80);
            ven.centrar_celda(letras.t_letras, 0);
            ven.centrar_celda(letras.t_letras, 1);
            ven.derecha_celda(letras.t_letras, 2);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e);
        }
        //cargar pagado
        letras.lbl_pagado.setText(pagados() + "");
        letras.resta();
        letras.id = this.id;
        ven.llamar_ventana(letras);
    }//GEN-LAST:event_btn_pagarActionPerformed

    private void btn_detActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_detActionPerformed
        frm_ver_det_venta detalle = new frm_ver_det_venta();
        detalle.txt_nro.setText(t_facturas.getValueAt(i, 4).toString());
        detalle.txt_nom.setText(t_facturas.getValueAt(i, 5).toString());
        detalle.txt_tipd.setText(t_facturas.getValueAt(i, 3).toString());
        detalle.txt_fec.setText(t_facturas.getValueAt(i, 1).toString());
        detalle.lbl_id.setText(t_facturas.getValueAt(i, 0).toString());
        detalle.txt_pago.setText(t_facturas.getValueAt(i, 9).toString());
        detalle.txt_tipv.setText(t_facturas.getValueAt(i, 9).toString());
        ped.setId_ped(t_facturas.getValueAt(i, 0).toString());
        try {
            DefaultTableModel modelo = new DefaultTableModel();
            modelo.addColumn("Id");
            modelo.addColumn("Descripcion");
            modelo.addColumn("Marca");
            modelo.addColumn("Cant.");
            modelo.addColumn("Und. Med.");
            modelo.addColumn("Precio");
            Statement st = con.conexion();
            String query = "select p.idProductos, p.desc_pro, p.marca, p.modelo, dp.precio, sum(dp.precio*dp.cantidad) as sump, u.desc_und, dp.cantidad from detalle_pedido as dp inner join productos as p on "
                    + "p.idProductos=dp.idProductos inner join und_medida as u on p.idUnd_medida=u.idUnd_medida where dp.idPedido = '" + ped.getId_ped() + "' group by p.idProductos";
            ResultSet rs = con.consulta(st, query);
            Object fila[] = new Object[6];
            double total = 0;
            while (rs.next()) {
                fila[0] = rs.getString("idProductos");
                fila[1] = rs.getString("desc_pro") + " - " + rs.getString("modelo");
                fila[2] = rs.getString("marca");
                fila[3] = ven.formato_numero(rs.getDouble("cantidad"));
                fila[4] = rs.getString("desc_und");
                fila[5] = ven.formato_numero(rs.getDouble("precio"));
                modelo.addRow(fila);
                total += rs.getDouble("sump");
            }
            detalle.t_detalle.setModel(modelo);
            con.cerrar(rs);
            con.cerrar(st);
            detalle.t_detalle.getColumnModel().getColumn(0).setPreferredWidth(30);
            detalle.t_detalle.getColumnModel().getColumn(1).setPreferredWidth(350);
            detalle.t_detalle.getColumnModel().getColumn(2).setPreferredWidth(60);
            detalle.t_detalle.getColumnModel().getColumn(3).setPreferredWidth(80);
            detalle.t_detalle.getColumnModel().getColumn(4).setPreferredWidth(60);
            ven.derecha_celda(detalle.t_detalle, 0);
            ven.derecha_celda(detalle.t_detalle, 3);
            ven.derecha_celda(detalle.t_detalle, 5);
            double sub = total / 1.18;
            double igv = sub * 0.18;
            detalle.txt_sub.setText(ven.formato_numero(sub));
            detalle.txt_igv.setText(ven.formato_numero(igv));
            detalle.txt_tot.setText(ven.formato_numero(total));
        } catch (Exception e) {
            System.out.println(e);
        }
        ven.llamar_ventana(detalle);
    }//GEN-LAST:event_btn_detActionPerformed

    private void btn_entActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_entActionPerformed
        //seleccionar datos de la separacion
        String separacion = t_facturas.getValueAt(i, 0).toString();
        try {
            Statement st = con.conexion();
            String ver_ped = "select cli_doc, cli_nom, nick, total from pedido where idPedido = '" + separacion + "'";
            ResultSet rs = con.consulta(st, ver_ped);
            if (rs.next()) {
                cli.setNro_doc(rs.getString("cli_doc"));
                cli.setNom_cli(rs.getString("cli_nom"));
                usu.setNick(rs.getString("nick"));
                ped.setTotal(rs.getDouble("total"));
            }
            con.cerrar(rs);
            con.cerrar(st);
        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
        }

        //Variables para registrar
        ped.setFec_pag_ped(ven.getFechaActual());
        ped.setFec_ped(ven.getFechaActual());
        tipa.setId(1);
        tipa.setDesc("EFECTIVO");
        tido.setId(2);
        tido.setDesc("BOLETA");
        tido.setSerie(tido.ver_ser(2, frm_menu.alm.getId()));
        tido.setNro(tido.ver_num(2, frm_menu.alm.getId()));
        ped.setDes_ped(0);
        ped.setEst_ped("1");

        try {
            Statement st = con.conexion();
            String upd_com = "update pedido set est_ped = '5' where idPedido = '" + separacion + "'";
            con.actualiza(st, upd_com);
            con.cerrar(st);
        } catch (Exception ex) {
            System.out.print(ex);
        }

        //registrar venta en tabla pedido
        try {
            Statement st = con.conexion();
            String ins_ven = "insert into pedido Values (null, '" + ped.getFec_ped() + "', '" + ped.getFec_pag_ped() + "', "
                    + "'" + tipa.getId() + "', '" + ped.getDes_ped() + "', '" + ped.getEst_ped() + "', '" + tido.getId() + "', "
                    + "'" + tido.getSerie() + "', '" + tido.getNro() + "', '" + usu.getNick() + "', "
                    + "'" + frm_menu.alm.getId() + "', null, '" + cli.getNro_doc() + "', '" + cli.getNom_cli() + "','" + ped.getTotal() + "')";
            con.actualiza(st, ins_ven);
            con.cerrar(st);
        } catch (Exception ex) {
            System.out.println(ex.getLocalizedMessage());
        }
        tido.act_doc(tido.getSerie(), tido.getNro() + 1, frm_menu.alm.getId(), tido.getId());

        //buscar ultimo pedido
        try {
            Statement st = con.conexion();
            String buscar_pedido = "select idPedido from pedido where nro_doc = '" + tido.getNro() + "' "
                    + "and fec_ped = '" + ped.getFec_ped() + "' and idAlmacen = "
                    + "'" + frm_menu.alm.getId() + "' order by idPedido desc limit 1";
            ResultSet rs = con.consulta(st, buscar_pedido);
            if (rs.next()) {
                ped.setId_ped(rs.getString("idPedido"));
            }
            con.cerrar(rs);
            con.cerrar(st);
        } catch (SQLException ex) {
            System.out.print(ex.getLocalizedMessage());
        }

        //registrar detalle de venta
        try {
            Statement st = con.conexion();
            String det_ped = "select idProductos, cantidad, precio from detalle_pedido where idPedido = '" + separacion + "'";
            ResultSet rs = con.consulta(st, det_ped);
            while (rs.next()) {
                //copiar datos de separacion a pedido entregado
                pro.setCan(rs.getDouble("cantidad"));
                pro.setId_pro(rs.getInt("idProductos"));
                pro.setPre_pro(rs.getDouble("precio"));
                try {
                    Statement st1 = con.conexion();
                    String ins_det_ped = "insert into detalle_pedido Values ('" + pro.getId_pro() + "', '" + ped.getId_ped() + "', '" + pro.getPre_pro() + "', '" + pro.getCan() + "')";
                    con.actualiza(st1, ins_det_ped);
                    con.cerrar(st1);
                } catch (Exception ex) {
                    System.err.print(ex.getLocalizedMessage());
                }
                //insertar datos en kardex
                try {
                    Statement st1 = con.conexion();
                    String ins_kardex = "insert into kardex Values (null, '" + ped.getFec_ped() + "', '" + pro.getId_pro() + "', '0.00', '0.00', '"
                            + pro.getCan() + "', '" + pro.getPre_pro() + "','" + tido.getSerie() + "', '" + tido.getNro() + "', '" + tido.getId() + "',"
                            + " '" + frm_menu.alm.getId() + "','" + cli.getNro_doc() + "', '" + cli.getNom_cli() + "','1')";
                    con.actualiza(st1, ins_kardex);
                    con.cerrar(st1);
                } catch (Exception ex) {
                    System.err.print("Error en: " + ex.getLocalizedMessage());
                }
                Double cant_actual = 0.00;
                Double cant_nueva = 0.00;
                try {
                    Statement st1 = con.conexion();
                    String bus_pro = "select cant_actual from productos where idProductos = '" + pro.getId_pro() + "'";
                    ResultSet rs1 = con.consulta(st1, bus_pro);
                    if (rs1.next()) {
                        cant_actual = rs.getDouble("cant_actual");
                    }
                    System.out.print("Seleccionando cantidad actual del producto: " + pro.getId_pro() + " cantidad: " + cant_actual + "\n");
                    con.cerrar(rs1);
                    con.cerrar(st1);
                    cant_nueva = cant_actual - pro.getCan();
                } catch (SQLException ex) {
                    System.err.print("Error en: " + ex.getLocalizedMessage());
                }
//
                try {
                    Statement st1 = con.conexion();
                    String act_pro = "update productos set cant_actual = '" + cant_nueva + "' where idProductos = '" + pro.getId_pro() + "' ";
                    con.actualiza(st1, act_pro);
                    con.cerrar(st1);
                    System.out.print("actualizando cantidad actual Prod:" + pro.getId_pro() + " cantidad: " + cant_nueva + "\n");
                } catch (Exception ex) {
                    System.err.print(ex.getLocalizedMessage());
                }

                try {
                    Statement st1 = con.conexion();
                    String ver_prod_alm = "select idProductos, cant from producto_almacen where idAlmacen = '" + frm_menu.alm.getId() + "' and idProductos = '" + pro.getId_pro() + "'";
                    ResultSet rs1 = con.consulta(st1, ver_prod_alm);
                    if (rs1.next()) {
                        //si producto existe actualizar cantidad
                        Double cant = rs1.getDouble("cant");
                        Double cant_act = cant - pro.getCan();
                        Statement st2 = con.conexion();
                        String act_pro_alm = "update producto_almacen set cant = '" + cant_act + "' where idProductos = '" + pro.getId_pro() + "' and idAlmacen = '" + frm_menu.alm.getId() + "'";
                        con.actualiza(st2, act_pro_alm);
                        con.cerrar(st2);
                    } else {
                        //si producto no existe agregar
                        Statement st2 = con.conexion();
                        String add_pro_alm = "insert into producto_almacen Values ('" + pro.getId_pro() + "', '" + frm_menu.alm.getId() + "', '" + pro.getCan() + "','" + pro.getPre_pro() + "')";
                        con.actualiza(st2, add_pro_alm);
                        con.cerrar(st2);
                    }
                } catch (SQLException e) {
                    System.out.println(e.getLocalizedMessage());
                }
            }
            con.cerrar(rs);
            con.cerrar(st);
        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
        }

        //cambiar pagos de separacion a boleta entregada
//        try {
//            Statement st = con.conexion();
//            String upd_letra_pedido = "update letras_pedido set idPedido = '" + ped.getId_ped() + "' where idPedido = '" + separacion + "'";
//            System.out.println(upd_letra_pedido);
//            con.actualiza(st, upd_letra_pedido);
//            con.cerrar(st);
//        } catch (Exception e) {
//            System.out.println(e.getLocalizedMessage());
//        }
//        Cl_Hilo_Imprime imprime = new Cl_Hilo_Imprime();
//        imprime.set_tipv("BOLETA");
//        imprime.set_idped(ped.getId_ped());
//        System.out.println(imprime.get_idped() + " - " + imprime.get_tipv());
//        imprime.start();
        int venta = Integer.parseInt(ped.getId_ped());
        Print_Venta_Ticket ticket = new Print_Venta_Ticket();
        ticket.generar(venta);

        fecha_hoy = ven.getFechaActual();
        String ver_ped = "select p.idPedido, p.fec_ped , p.fec_pago,p.idAlmacen,t.desc,p.idTipo_pago,p.total,p.est_ped,td.idtipo_doc,td.desc_tipd , "
                + "p.serie_doc, p.nro_doc, u.nick, a.nom_alm , t.desc , p.est_ped,c.nom_per,p.cli_doc from pedido as p inner join tipo_pago as t "
                + "on p.idTipo_pago=t.idTipo_pago inner join tipo_doc as td on p.idtipo_doc=td.idtipo_doc inner join usuario as u "
                + "on p.nick = u.nick inner join  almacen as a on p.idAlmacen=a.idAlmacen inner join cliente as c on p.cli_doc=c.nro_doc  "
                + "where p.idAlmacen='" + sonomusic.frm_menu.alm.getId() + "' and p.fec_ped='" + fecha_hoy + "' order by p.idPedido asc";
        ver_pedidos(ver_ped);

    }//GEN-LAST:event_btn_entActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        //seleccionar separaciones
        if (jComboBox1.getSelectedIndex() == 0) {
            String ver_ped = "select p.idPedido, p.fec_ped , p.fec_pago,p.idAlmacen,t.desc,p.idTipo_pago,p.total,p.est_ped,td.idtipo_doc,td.desc_tipd , "
                    + "p.serie_doc, p.nro_doc, u.nick, a.nom_alm , t.desc , p.est_ped,c.nom_per,p.cli_doc from pedido as p inner join tipo_pago as t "
                    + "on p.idTipo_pago=t.idTipo_pago inner join tipo_doc as td on p.idtipo_doc=td.idtipo_doc inner join usuario as u "
                    + "on p.nick = u.nick inner join  almacen as a on p.idAlmacen=a.idAlmacen inner join cliente as c on p.cli_doc=c.nro_doc  "
                    + "where p.idAlmacen='" + sonomusic.frm_menu.alm.getId() + "' and p.est_ped = '2' order by p.idPedido asc";
            ver_pedidos(ver_ped);
        }
        //seleccionar creditos
        if (jComboBox1.getSelectedIndex() == 1) {
            String ver_ped = "select p.idPedido, p.fec_ped , p.fec_pago,p.idAlmacen,t.desc,p.idTipo_pago,p.total,p.est_ped,td.idtipo_doc,td.desc_tipd , "
                    + "p.serie_doc, p.nro_doc, u.nick, a.nom_alm , t.desc , p.est_ped,c.nom_per,p.cli_doc from pedido as p inner join tipo_pago as t "
                    + "on p.idTipo_pago=t.idTipo_pago inner join tipo_doc as td on p.idtipo_doc=td.idtipo_doc inner join usuario as u "
                    + "on p.nick = u.nick inner join  almacen as a on p.idAlmacen=a.idAlmacen inner join cliente as c on p.cli_doc=c.nro_doc  "
                    + "where p.idAlmacen='" + sonomusic.frm_menu.alm.getId() + "' and p.est_ped = '6' order by p.idPedido asc";
            ver_pedidos(ver_ped);
        }
        //seleccionar por entregar
        if (jComboBox1.getSelectedIndex() == 2) {
            String ver_ped = "select p.idPedido, p.fec_ped , p.fec_pago,p.idAlmacen,t.desc,p.idTipo_pago,p.total,p.est_ped,td.idtipo_doc,td.desc_tipd , "
                    + "p.serie_doc, p.nro_doc, u.nick, a.nom_alm , t.desc , p.est_ped,c.nom_per,p.cli_doc from pedido as p inner join tipo_pago as t "
                    + "on p.idTipo_pago=t.idTipo_pago inner join tipo_doc as td on p.idtipo_doc=td.idtipo_doc inner join usuario as u "
                    + "on p.nick = u.nick inner join  almacen as a on p.idAlmacen=a.idAlmacen inner join cliente as c on p.cli_doc=c.nro_doc  "
                    + "where p.idAlmacen='" + sonomusic.frm_menu.alm.getId() + "' and p.est_ped = '4' order by p.idPedido asc";
            ver_pedidos(ver_ped);
        }
        //seleccionar entregados
        if (jComboBox1.getSelectedIndex() == 3) {
            String ver_ped = "select p.idPedido, p.fec_ped , p.fec_pago,p.idAlmacen,t.desc,p.idTipo_pago,p.total,p.est_ped,td.idtipo_doc,td.desc_tipd , "
                    + "p.serie_doc, p.nro_doc, u.nick, a.nom_alm , t.desc , p.est_ped,c.nom_per,p.cli_doc from pedido as p inner join tipo_pago as t "
                    + "on p.idTipo_pago=t.idTipo_pago inner join tipo_doc as td on p.idtipo_doc=td.idtipo_doc inner join usuario as u "
                    + "on p.nick = u.nick inner join  almacen as a on p.idAlmacen=a.idAlmacen inner join cliente as c on p.cli_doc=c.nro_doc  "
                    + "where p.idAlmacen='" + sonomusic.frm_menu.alm.getId() + "' and p.est_ped = '5' order by p.idPedido asc";
            ver_pedidos(ver_ped);
        }
        //seleccionar anulados
        if (jComboBox1.getSelectedIndex() == 4) {
            String ver_ped = "select p.idPedido, p.fec_ped , p.fec_pago,p.idAlmacen,t.desc,p.idTipo_pago,p.total,p.est_ped,td.idtipo_doc,td.desc_tipd , "
                    + "p.serie_doc, p.nro_doc, u.nick, a.nom_alm , t.desc , p.est_ped,c.nom_per,p.cli_doc from pedido as p inner join tipo_pago as t "
                    + "on p.idTipo_pago=t.idTipo_pago inner join tipo_doc as td on p.idtipo_doc=td.idtipo_doc inner join usuario as u "
                    + "on p.nick = u.nick inner join  almacen as a on p.idAlmacen=a.idAlmacen inner join cliente as c on p.cli_doc=c.nro_doc  "
                    + "where p.idAlmacen='" + sonomusic.frm_menu.alm.getId() + "' and p.est_ped = '3' order by p.idPedido asc";
            ver_pedidos(ver_ped);
        }
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void t_facturasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_t_facturasMouseClicked
//        if (evt.getClickCount() == 2) {
//            i = t_facturas.getSelectedRow();
//            int venta  = Integer.parseInt(t_facturas.getValueAt(i, 0).toString());
//            Print_Venta_Ticket ticket = new Print_Venta_Ticket();
//            ticket.generar(venta);
//        }
    }//GEN-LAST:event_t_facturasMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_anu;
    private javax.swing.JButton btn_det;
    private javax.swing.JButton btn_ent;
    private javax.swing.JButton btn_pagar;
    private javax.swing.JComboBox cbx_estado;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable t_facturas;
    private javax.swing.JTextField txt_bus;
    private javax.swing.JTextField txt_tot;
    // End of variables declaration//GEN-END:variables
}
