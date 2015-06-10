package Vistas;

import Clases.Cl_Albaran;
import Clases.Cl_Almacen;
import Clases.Cl_Cliente;
import Clases.Cl_Conectar;
import Clases.Cl_Movimiento;
import Clases.Cl_Pedido;
import Clases.Cl_Productos;
import Clases.Cl_Tipo_Documentos;
import Clases.Cl_Tipo_Pago;
import Clases.Cl_Varios;
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
    Cl_Tipo_Documentos Tido = new Cl_Tipo_Documentos();
    Cl_Almacen alm = new Cl_Almacen();
    Cl_Albaran alb = new Cl_Albaran();
    Cl_Productos pro = new Cl_Productos();
    Cl_Movimiento mov = new Cl_Movimiento();
    Cl_Cliente cli = new Cl_Cliente();
    public static String id;

    DefaultTableModel mostrar;
    DecimalFormatSymbols simbolo = new DecimalFormatSymbols(Locale.US);
    DecimalFormat formato = new DecimalFormat("####.00", simbolo);
    Integer i;
    String fecha_hoy;

    /**
     * Creates new form frm_ver_cobranzas
     */
    public frm_ver_venta() {
        initComponents();
        try {
            fecha_hoy = ven.getFechaActual();
            String ver_ped = "select p.idPedido, p.fec_ped , p.fec_pago,p.idAlmacen,t.desc,p.idTipo_pago,p.total,p.est_ped,td.idtipo_doc,td.desc_tipd , "
                    + "p.serie_doc, p.nro_doc, u.nick, a.nom_alm , t.desc , p.est_ped,c.nom_per,p.cli_doc from pedido as p inner join tipo_pago as t "
                    + "on p.idTipo_pago=t.idTipo_pago inner join tipo_doc as td on p.idtipo_doc=td.idtipo_doc inner join usuario as u "
                    + "on p.nick = u.nick inner join  almacen as a on p.idAlmacen=a.idAlmacen inner join cliente as c on p.cli_doc=c.nro_doc  "
                    + "where p.idAlmacen='" + sonomusic.frm_menu.alm.getId() + "' and p.fec_ped='" + fecha_hoy + "' order by p.idPedido asc";
            ver_pedidos(ver_ped);
        } catch (Exception e) {
            System.out.println("error " + e);
        }
        cbx_estado.setSelectedIndex(0);
        txt_bus.requestFocus();
    }
    //

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
            mostrar.addColumn("Tipo Pago");
            mostrar.addColumn("Vendedor");
            mostrar.addColumn("Almacen");
            mostrar.addColumn("Estado");
            double sum = 0.0;
            //Creando las filas para el JTable
            while (rs.next()) {
                Object[] fila = new Object[11];
                fila[0] = rs.getObject("idPedido");
                String tido = rs.getString("idtipo_doc");
                String dtido = rs.getString("desc_tipd");
                String serie = rs.getString("serie_doc");
                String nro = rs.getString("nro_doc");

                fila[1] = ven.fechaformateada(rs.getString("fec_ped"));
                fila[2] = ven.fechaformateada(rs.getString("fec_pago"));
                if (tido.equals("1") & serie.equals("000") & nro.equals("0000000")) {
                    fila[3] = "VENTA SIN DOCUMENTO";
                } else {
                    fila[3] = dtido + " / " + serie + " - " + nro;
                }
                fila[4] = rs.getObject("cli_doc");
                fila[5] = rs.getString("nom_per");
                fila[6] = formato.format(rs.getDouble("total"));
                sum += (rs.getDouble("total"));
                fila[7] = rs.getString("desc");
                fila[8] = rs.getString("nick");
                fila[9] = rs.getString("nom_alm");

                if (rs.getString("est_ped").equals("1")) {
                    fila[10] = "PAGADO";
                }
                if (rs.getString("est_ped").equals("2")) {
                    fila[10] = "SEPARADO";
                }
                if (rs.getString("est_ped").equals("3")) {
                    fila[10] = "ANULADO";
                }

                mostrar.addRow(fila);
            }
            txt_tot.setText(formato.format(sum));
            con.cerrar(st);
            con.cerrar(rs);
            t_facturas.setModel(mostrar);
            t_facturas.getColumnModel().getColumn(0).setPreferredWidth(30);
            t_facturas.getColumnModel().getColumn(1).setPreferredWidth(80);
            t_facturas.getColumnModel().getColumn(2).setPreferredWidth(80);
            t_facturas.getColumnModel().getColumn(3).setPreferredWidth(170);
            t_facturas.getColumnModel().getColumn(4).setPreferredWidth(80);
            t_facturas.getColumnModel().getColumn(5).setPreferredWidth(170);
            t_facturas.getColumnModel().getColumn(7).setPreferredWidth(80);
            t_facturas.getColumnModel().getColumn(8).setPreferredWidth(80);
            t_facturas.getColumnModel().getColumn(9).setPreferredWidth(80);
            t_facturas.getColumnModel().getColumn(10).setPreferredWidth(80);
            ven.derecha_celda(t_facturas, 0);
            ven.centrar_celda(t_facturas, 1);
            ven.centrar_celda(t_facturas, 2);
            ven.centrar_celda(t_facturas, 4);
            ven.derecha_celda(t_facturas, 6);
            ven.centrar_celda(t_facturas, 7);
            ven.centrar_celda(t_facturas, 8);
            ven.centrar_celda(t_facturas, 9);
            ven.centrar_celda(t_facturas, 10);
            mostrar.fireTableDataChanged();
        } catch (SQLException e) {
            System.out.println("GRAVE ERROR: " + e + " EN " + e.getLocalizedMessage());
        }

    }

    void calcula_total() {
        double total = 0.0;
        for (int j = 0; j < t_facturas.getRowCount(); j++) {
            double subtot = (double) t_facturas.getValueAt(j, 5);
            total += subtot;
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
                {"2", "B/001-00025", "C0U944", "LUIS ENRIQUE OYANGUREN GIRON", "S/. 59.00", "Pendiente de Pago", null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Id Ped.", "Glosa", "Nro Placa.", "Cliente", "Monto", "Estado", "Title 7", "Title 8", "Title 9", "Title 10"
            }
        ));
        t_facturas.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        t_facturas.addMouseListener(new java.awt.event.MouseAdapter() {
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 530, Short.MAX_VALUE)
                        .addComponent(btn_anu)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_pagar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_det))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_tot, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                        .addComponent(btn_det, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 374, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_tot, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                String ver_ped = "select idTipo_pago, descuento, fec_ped, idAlmacen, total, cli_doc, serie_doc, nro_doc, idtipo_doc, albaran from pedido where idPedido = '" + ped.getId_ped() + "'";
                ResultSet rs = con.consulta(st, ver_ped);
                if (rs.next()) {
                    tipa.setId(rs.getInt("idTipo_pago"));
                    ped.setDes_ped(rs.getDouble("descuento"));
                    ped.setFec_ped(rs.getString("fec_ped"));
                    alm.setId(rs.getInt("idAlmacen"));
                    alb.setId(rs.getInt("albaran"));
                    Tido.setSerie(rs.getInt("serie_doc"));
                    Tido.setNro(rs.getInt("nro_doc"));
                    Tido.setId(rs.getInt("idtipo_doc"));
                    cli.setNro_doc(rs.getString("cli_doc"));
                    ped.setTotal(rs.getDouble("total"));
                }
                con.cerrar(rs);
                con.cerrar(st);
            } catch (SQLException ex) {
                System.out.print(ex);
            }

            try {
                Statement st = con.conexion();
                String act_ped = "update pedido set est_ped = '3', descuento = '0.00', idTipo_pago='1' where idPedido = '" + ped.getId_ped() + "'";
                con.actualiza(st, act_ped);
                con.cerrar(st);
            } catch (Exception ex) {
                System.out.print(ex);
            }

            //seleccionar detalle de pedido, cantidad de productos;
            try {
                Statement st = con.conexion();
                String ver_ped = "select idProductos, precio, cantidad, sum(precio * cantidad) as suma_pro from detalle_pedido where idPedido = '" + ped.getId_ped() + "'";
                ResultSet rs = con.consulta(st, ver_ped);
                while (rs.next()) {
                    pro.setId_pro(rs.getInt("idProductos"));
                    pro.setCan(rs.getDouble("cantidad"));
                    pro.setPre_pro(rs.getDouble("precio"));
                    suma_pro = rs.getDouble("suma_pro");
                    Double pro_can = 0.00;
                    Double new_can = 0.00;
                    Double pro_can_alm = 0.00;
                    Double new_can_alm = 0.00;
                    try {
                        Statement st1 = con.conexion();
                        String ver_pro = "select cant_actual from productos where idProductos = '" + pro.getId_pro() + "'";
                        ResultSet rs1 = con.consulta(st1, ver_pro);
                        if (rs1.next()) {
                            pro_can = rs1.getDouble("cant_actual");
                        }
                        con.cerrar(rs1);
                        con.cerrar(st1);
                        new_can = pro.getCan() + pro_can;
                    } catch (SQLException ex1) {
                        System.out.print(ex1);
                    }

                    try {
                        Statement st1 = con.conexion();
                        String ver_pro = "select cant from producto_almacen where idProductos = '" + pro.getId_pro() + "' and idAlmacen = '" + alm.getId() + "'";
                        ResultSet rs1 = con.consulta(st1, ver_pro);
                        if (rs1.next()) {
                            pro_can_alm = rs1.getDouble("cant");
                        }
                        con.cerrar(rs1);
                        con.cerrar(st1);
                        new_can_alm = pro.getCan() + pro_can_alm;
                    } catch (SQLException ex1) {
                        System.out.print(ex1);
                    }

                    try {
                        Statement st1 = con.conexion();
                        String upt_pro_alm = "update producto_almacen set cant = '" + new_can_alm + "' where idProductos = '" + pro.getId_pro() + "' and idAlmacen = '" + alm.getId() + "'";
                        con.actualiza(st1, upt_pro_alm);
                        con.cerrar(st1);
                    } catch (Exception ex1) {
                        System.out.print(ex1);
                    }

                    try {
                        Statement st1 = con.conexion();
                        String upt_pro = "update productos set cant_actual = '" + new_can + "' where idProductos = '" + pro.getId_pro() + "'";
                        con.actualiza(st1, upt_pro);
                        con.cerrar(st1);
                    } catch (Exception ex1) {
                        System.out.print(ex1);
                    }

                    try {
                        Statement st1 = con.conexion();
                        String ins_kardex = "insert into kardex Values (null, '" + ped.getFec_ped() + "', '" + pro.getId_pro() + "', '" + pro.getCan() + "', '" + pro.getPre_pro() + "', '0.00', '0.00',"
                                + "'" + Tido.getSerie() + "', '" + Tido.getNro() + "', '" + Tido.getId() + "', '" + alm.getId() + "', '" + cli.getNro_doc() + "', '" + cli.getNom_cli() + "', '5')";
                        con.actualiza(st1, ins_kardex);
                        con.cerrar(st1);
                    } catch (Exception ex) {
                        System.out.print(ex);
                    }

                }

            } catch (SQLException ex) {
                System.out.print(ex);
            }

            //calcular monto 
            System.out.print(tipa.getId());
            if (tipa.getId() == 1) {
                try {
                    mov.setGlosa("VENTA ANULADA - " + Tido.getSerie() + " - " + Tido.getNro() + " / " + cli.getNro_doc());
                    mov.setFec_mov(ped.getFec_ped());
                    mov.setEgreso(ped.getTotal());
                    Statement st = con.conexion();
                    String ins_mov = "insert into movimiento Values (null, '" + mov.getGlosa() + "', '" + mov.getFec_mov() + "' , "
                            + "'0.00', '" + mov.getEgreso() + "' , '" + sonomusic.frm_menu.lbl_user.getText() + "', "
                            + "'" + alm.getId() + "', 'C', '" + frm_menu.caja.getId() + "')";
                    con.actualiza(st, ins_mov);
                    con.cerrar(st);
                } catch (Exception ex) {
                    System.out.print(ex);
                }
            } else {
                try {
                    mov.setGlosa("VENTA ANULADA - " + Tido.getSerie() + " - " + Tido.getNro() + " / " + cli.getNro_doc());
                    mov.setFec_mov(ped.getFec_ped());
                    mov.setEgreso(ped.getTotal());
                    Statement st = con.conexion();
                    String ins_mov = "insert into movimiento Values (null, '" + mov.getGlosa() + "', '" + mov.getFec_mov() + "' , "
                            + "'0.00', '" + mov.getEgreso() + "' , '" + sonomusic.frm_menu.lbl_user.getText() + "', "
                            + "'" + alm.getId() + "', 'B', '1')";
                    con.actualiza(st, ins_mov);
                    con.cerrar(st);
                } catch (Exception ex) {
                    System.out.print(ex);
                }
            }

            try {
                Statement st1 = con.conexion();
                String del_ped = "delete from detalle_pedido where idPedido = '" + ped.getId_ped() + "'";
                con.actualiza(st1, del_ped);
                con.cerrar(st1);
            } catch (Exception ex1) {
                System.out.print(ex1);
            }

            String ver_ped = "select p.idPedido, p.fec_ped , p.fec_pago,p.idAlmacen,t.desc,p.idTipo_pago,p.total,p.est_ped,td.idtipo_doc,td.desc_tipd , "
                    + "p.serie_doc, p.nro_doc, u.nick, a.nom_alm , t.desc , p.est_ped,c.nom_per,p.cli_doc from pedido as p inner join tipo_pago as t "
                    + "on p.idTipo_pago=t.idTipo_pago inner join tipo_doc as td on p.idtipo_doc=td.idtipo_doc inner join usuario as u "
                    + "on p.nick = u.nick inner join  almacen as a on p.idAlmacen=a.idAlmacen inner join cliente as c on p.cli_doc=c.nro_doc  where p.idAlmacen='" + sonomusic.frm_menu.alm.getId() + "' and p.fec_ped='" + fecha_hoy + "'";
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
        String est = t_facturas.getValueAt(i, 10).toString();
        if (est.equals("ANULADO")) {
            btn_anu.setEnabled(false);
        } else {
            btn_anu.setEnabled(true);
            btn_det.setEnabled(true);
        }
        if (est.equals("SEPARADO")) {
            btn_pagar.setEnabled(true);
        } else {
            btn_pagar.setEnabled(false);
        }
    }//GEN-LAST:event_t_facturasMousePressed

    private void btn_pagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pagarActionPerformed
        frm_ver_letras_pedido letras = new frm_ver_letras_pedido();
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
            letras.modelo.addColumn("Monto");
            Statement st = con.conexion();
            String sql = "select idLetras_Pedido, monto, fecha, idPedido from  letras_pedido where idPedido='" + id + "' ";
            ResultSet rs = con.consulta(st, sql);
            Object dato[] = new Object[2];
            while (rs.next()) {
                dato[0] = rs.getObject("fecha");
                dato[1] = rs.getObject("monto");
                letras.modelo.addRow(dato);
            }
            letras.t_letras.setModel(modelo);
            letras.t_letras.getColumnModel().getColumn(0).setPreferredWidth(100);
            letras.t_letras.getColumnModel().getColumn(1).setPreferredWidth(120);
            ven.centrar_celda(letras.t_letras, 0);
            ven.derecha_celda(letras.t_letras, 1);

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
        detalle.txt_fec.setText(ven.fechaformateada(t_facturas.getValueAt(i, 1).toString()));
        detalle.lbl_id.setText(t_facturas.getValueAt(i, 0).toString());
        detalle.txt_pago.setText(t_facturas.getValueAt(i, 7).toString());
        detalle.txt_tipv.setText(t_facturas.getValueAt(i, 10).toString());
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
                fila[3] = formato.format(rs.getDouble("cantidad"));
                fila[4] = rs.getString("desc_und");
                fila[5] = formato.format(rs.getDouble("precio"));
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
            detalle.txt_sub.setText(formato.format(sub));
            detalle.txt_igv.setText(formato.format(igv));
            detalle.txt_tot.setText(formato.format(total));
        } catch (Exception e) {
            System.out.println(e);
        }
        ven.llamar_ventana(detalle);
    }//GEN-LAST:event_btn_detActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_anu;
    private javax.swing.JButton btn_det;
    private javax.swing.JButton btn_pagar;
    private javax.swing.JComboBox cbx_estado;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable t_facturas;
    private javax.swing.JTextField txt_bus;
    private javax.swing.JTextField txt_tot;
    // End of variables declaration//GEN-END:variables
}
