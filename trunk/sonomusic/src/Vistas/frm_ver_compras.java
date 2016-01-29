package Vistas;

import Clases.Cl_Almacen;
import Clases.Cl_Compra;
import Clases.Cl_Conectar;
import Clases.Cl_Productos;
import Clases.Cl_Proveedor;
import Clases.Cl_Tipo_Documentos;
import Clases.Cl_Varios;
import Forms.frm_reg_compra;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author luis-d
 */
public class frm_ver_compras extends javax.swing.JInternalFrame {

    Cl_Conectar con = new Cl_Conectar();
    Cl_Varios ven = new Cl_Varios();
    Cl_Compra com = new Cl_Compra();
    Cl_Proveedor pro = new Cl_Proveedor();
    Cl_Tipo_Documentos tido = new Cl_Tipo_Documentos();
    Cl_Almacen alm = new Cl_Almacen();
    Cl_Productos mat = new Cl_Productos();
    DefaultTableModel mostrar;
    Integer i;

    /**
     * Creates new form frm_ver_compras
     */
    public frm_ver_compras() {
        initComponents();

        String query = "select c.idcompra, c.periodo, c.ruc_prov, pr.raz_soc_pro, c.fec_com, c.fec_pago, td.desc_tipd, c.serie, c.nro, m.nombre, m.simbolo, m.siglas, c.tc, c.base, c.empresa, "
                + "c.estado from compra as c inner join proveedor as pr on c.ruc_prov = pr.ruc_pro inner join tipo_doc as td on c.idtido = td.idtipo_doc inner join moneda as m on "
                + "c.idmon = m.idmoneda";
        ver_compras(query);
        //     t_compras.setDefaultRenderer(Object.class, new table_render());

    }

    private void ver_compras(String query) {
        try {
            mostrar = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int fila, int columna) {
                    return false;
                }
            };
            mostrar.addColumn("Id");
            mostrar.addColumn("Periodo");
            mostrar.addColumn("Fec. Com.");
            mostrar.addColumn("Fec. Pago");
            mostrar.addColumn("Tipo Doc.");
            mostrar.addColumn("Ser.");
            mostrar.addColumn("Nro.");
            mostrar.addColumn("RUC");
            mostrar.addColumn("Razon Social");
            mostrar.addColumn("Moneda");
            mostrar.addColumn("Base");
            mostrar.addColumn("Base M.N");
            mostrar.addColumn("IGV");
            mostrar.addColumn("Total");
            mostrar.addColumn("Estado");

            Statement st = con.conexion();
            ResultSet rs = con.consulta(st, query);
            while (rs.next()) {
                Object fila[] = new Object[15];
                String periodo = rs.getString("periodo");
                int idcompra = rs.getInt("idcompra");
                fila[0] = idcompra;
                fila[1] = periodo;
                fila[2] = ven.fechaformateada(rs.getString("fec_com"));
                String fechadepago = fecha_pago(periodo, idcompra);
                if (fechadepago.equals("7000-01-01")) {
                    fila[3] = "-";
                } else {
                    fila[3] = ven.fechaformateada(fechadepago);
                }
                fila[4] = rs.getString("desc_tipd");
                fila[5] = rs.getString("serie");
                fila[6] = rs.getString("nro");
                fila[7] = rs.getString("ruc_prov");
                fila[8] = rs.getString("raz_soc_pro");
                fila[9] = rs.getString("simbolo");
                Double base;
                if (!rs.getString("siglas").equals("PEN")) {
                    base = rs.getDouble("base") * rs.getDouble("tc");
                } else {
                    base = rs.getDouble("base");
                }
                fila[10] = ven.formato_numero(rs.getDouble("base"));
                fila[11] = ven.formato_numero(base);
                fila[12] = ven.formato_numero(base * 0.18);
                fila[13] = ven.formato_numero(base * 1.18);
                if (rs.getString("estado").equals("0")) {
                    fila[14] = "PENDIENTE";
                } else {
                    fila[14] = "PAGADO";
                }

                mostrar.addRow(fila);
            }
            con.cerrar(st);
            con.cerrar(rs);
            t_compras.setModel(mostrar);
            t_compras.getColumnModel().getColumn(0).setPreferredWidth(30);
            t_compras.getColumnModel().getColumn(1).setPreferredWidth(60);
            t_compras.getColumnModel().getColumn(2).setPreferredWidth(75);
            t_compras.getColumnModel().getColumn(3).setPreferredWidth(75);
            t_compras.getColumnModel().getColumn(4).setPreferredWidth(80);
            t_compras.getColumnModel().getColumn(5).setPreferredWidth(35);
            t_compras.getColumnModel().getColumn(6).setPreferredWidth(70);
            t_compras.getColumnModel().getColumn(7).setPreferredWidth(90);
            t_compras.getColumnModel().getColumn(8).setPreferredWidth(250);
            t_compras.getColumnModel().getColumn(9).setPreferredWidth(40);
            t_compras.getColumnModel().getColumn(10).setPreferredWidth(70);
            t_compras.getColumnModel().getColumn(11).setPreferredWidth(70);
            t_compras.getColumnModel().getColumn(12).setPreferredWidth(70);
            t_compras.getColumnModel().getColumn(13).setPreferredWidth(70);
            t_compras.getColumnModel().getColumn(14).setPreferredWidth(70);
            ven.derecha_celda(t_compras, 0);
            ven.centrar_celda(t_compras, 1);
            ven.centrar_celda(t_compras, 2);
            ven.centrar_celda(t_compras, 3);
            ven.centrar_celda(t_compras, 4);
            ven.centrar_celda(t_compras, 5);
            ven.centrar_celda(t_compras, 6);
            ven.centrar_celda(t_compras, 7);
            ven.derecha_celda(t_compras, 9);
            ven.derecha_celda(t_compras, 10);
            ven.derecha_celda(t_compras, 11);
            ven.derecha_celda(t_compras, 12);
            ven.derecha_celda(t_compras, 13);
            ven.centrar_celda(t_compras, 14);
            txt_sub.setText(ven.formato_totales(suma_subtotal()));
            txt_igv.setText(ven.formato_totales(suma_subtotal() * 0.18));
            txt_total.setText(ven.formato_totales(suma_subtotal() * 1.18));
        } catch (SQLException ex) {
            System.out.print(ex);
        }
    }

    private String fecha_pago(String periodo, int idcompra) {
        String fecha = null;
        try {
            Statement st = con.conexion();
            String ver_fec = "select fec_venc from pago_compras where idcompra = '" + idcompra + "' and periodo = '" + periodo + "' and estado = '0' order by fec_venc asc limit 1";
            System.out.println(ver_fec);
            ResultSet rs = con.consulta(st, ver_fec);
            if (rs.next()) {
                fecha = rs.getString("fec_venc");
            } else {
                fecha = "7000-01-01";
            }
            con.cerrar(rs);
            con.cerrar(st);
        } catch (Exception e) {
            System.out.println(e);
        }
        return fecha;
    }

    private Double suma_subtotal() {
        Double suma = 0.0;
        int nro_filas = t_compras.getRowCount();
        for (int j = 0; j < nro_filas; j++) {
            suma += Double.parseDouble(t_compras.getValueAt(j, 11).toString());
        }
        return suma;
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
        btn_reg = new javax.swing.JButton();
        btn_anu = new javax.swing.JButton();
        btn_cer = new javax.swing.JButton();
        cbx_bus = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        t_compras = new javax.swing.JTable();
        btn_pagar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txt_total = new javax.swing.JTextField();
        txt_igv = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txt_sub = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setClosable(true);
        setResizable(true);
        setTitle("Ver Compras");

        jLabel1.setForeground(new java.awt.Color(212, 2, 2));
        jLabel1.setText("Buscar por:");

        btn_reg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/add.png"))); // NOI18N
        btn_reg.setText("Registrar");
        btn_reg.setMargin(new java.awt.Insets(2, 8, 2, 8));
        btn_reg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_regActionPerformed(evt);
            }
        });

        btn_anu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/bin_closed.png"))); // NOI18N
        btn_anu.setText("Anular");
        btn_anu.setEnabled(false);
        btn_anu.setMargin(new java.awt.Insets(2, 8, 2, 8));
        btn_anu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_anuActionPerformed(evt);
            }
        });

        btn_cer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/cancel.png"))); // NOI18N
        btn_cer.setText("Cerrar");
        btn_cer.setMargin(new java.awt.Insets(2, 8, 2, 8));
        btn_cer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cerActionPerformed(evt);
            }
        });

        cbx_bus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "RUC", "Razon Social", "Nro Doc." }));

        jScrollPane1.setBackground(new java.awt.Color(254, 254, 254));

        t_compras.setModel(new javax.swing.table.DefaultTableModel(
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
        t_compras.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        t_compras.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                t_comprasMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(t_compras);

        btn_pagar.setText("Pagar");
        btn_pagar.setEnabled(false);
        btn_pagar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pagarActionPerformed(evt);
            }
        });

        jLabel2.setText("Total");

        txt_total.setEditable(false);
        txt_total.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        txt_igv.setEditable(false);
        txt_igv.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jLabel3.setText("IGV");

        txt_sub.setEditable(false);
        txt_sub.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jLabel4.setText("SubTotal");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn_reg)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_anu)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_pagar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 342, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbx_bus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_bus, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_cer)
                        .addGap(9, 9, 9))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_sub, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_igv, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_total, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(txt_bus, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_reg, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_anu, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_cer, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbx_bus, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_pagar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 448, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_total, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_igv, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_sub, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_cerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cerActionPerformed
        this.dispose();
    }//GEN-LAST:event_btn_cerActionPerformed

    private void btn_regActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_regActionPerformed
        frm_reg_compra compra = new frm_reg_compra();
        ven.llamar_ventana(compra);
        this.dispose();
    }//GEN-LAST:event_btn_regActionPerformed

    private void t_comprasMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_t_comprasMousePressed
        i = t_compras.getSelectedRow();
        btn_anu.setEnabled(true);
        btn_pagar.setEnabled(true);
    }//GEN-LAST:event_t_comprasMousePressed

    private void btn_anuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_anuActionPerformed
//    int confirmado = JOptionPane.showConfirmDialog(null, "¿Confirma eliminar la compra?");
//    if (JOptionPane.OK_OPTION == confirmado) {
//        String lote = "-";
//        com.setId(Integer.parseInt(t_compras.getValueAt(i, 0).toString()));
//        Double suma_pro = 0.00;
//        //seleccionar almacen
//        try {
//            Statement st = con.conexion();
//            String ver_alm = "select c.idTipo_Documento, c.serie_doc, c.ruc_pro, p.raz_soc_pro, c.nro_doc, c.fec_com, "
//                    + "c.idAlmacen from Compra as c inner join proveedor as p on c.ruc_pro=p.ruc_pro where c.idCompra = '"+com.getId()+"'";
//            ResultSet rs = con.consulta(st, ver_alm);
//            if (rs.next()) {
//                com.setFec_com(rs.getString("fec_com"));
//                pro.setRuc(rs.getString("ruc_pro"));
//                pro.setRaz_soc(rs.getString("raz_soc_pro"));
//                alm.setId(rs.getInt("idAlmacen"));
//                tido.setId(rs.getInt("idTipo_Documento"));
//                tido.setSerie(rs.getInt("serie_doc"));
//                tido.setNro(rs.getInt("nro_doc"));
//            }
//            con.cerrar(rs);
//            con.cerrar(st);
//        } catch (SQLException ex) {
//            System.out.print(ex);
//        }
//        
//        //seleccionar detalle de compra, cantidad de productos;
//        try {
//            Statement st = con.conexion();
//            String ver_ped = "select idMaterial, precio_com, cant_mat, lote from Detalle_Compra where idCompra = '"+com.getId()+"'";
//            ResultSet rs = con.consulta(st, ver_ped);
//            int nro = 0;
//            while (rs.next()) {
//                nro++;
//                System.out.print(nro + "\n");
//                mat.setId(rs.getInt("idMaterial"));
//                mat.setCant(rs.getDouble("cant_mat"));
//                mat.setPre(rs.getDouble("precio_com"));
//                lote = rs.getString("lote");
//                suma_pro = suma_pro + (mat.getCant() * mat.getPre());
//                Double pro_can = 0.00;
//                Double new_can = 0.00;
//                Double pro_can_alm = 0.00;
//                Double new_can_alm = 0.00;
//                try {
//                    Statement st1 = con.conexion();
//                    String ver_pro = "select cant_actual from Material where idMaterial = '"+mat.getId()+"'";
//                    ResultSet rs1 = con.consulta(st1, ver_pro);
//                    if (rs1.next()) {
//                        pro_can = rs1.getDouble("cant_actual");
//                    }
//                    con.cerrar(rs1);
//                    con.cerrar(st1);
//                    new_can = pro_can - mat.getCant();
//                 } catch (SQLException ex1) {
//                     System.out.print(ex1);
//                 }
//                System.out.print("Cantidad actual: " + pro_can + " Producto: " + mat.getId() + "\n");
//                
//                try {
//                    Statement st1 = con.conexion();
//                    String ver_pro = "select cant_mat from Material_Almacen where idMaterial = '"+mat.getId()+"' and idAlmacen = '"+alm.getId()+"'";
//                    ResultSet rs1 = con.consulta(st1, ver_pro);
//                    if (rs1.next()) {
//                        pro_can_alm = rs1.getDouble("cant_mat");
//                    }
//                    con.cerrar(rs1);
//                    con.cerrar(st1);
//                    new_can_alm = pro_can_alm - mat.getCant();
//                 } catch (SQLException ex1) {
//                     System.out.print(ex1);
//                 }
//                System.out.print("Cantidad actual: " + pro_can_alm + " Producto: " + mat.getId() + " en Almacen: " + alm.getId() + "\n");
//                
//                try {
//                    Statement st1 = con.conexion();
//                    String upt_pro_alm = "update Material_Almacen set cant_mat = '"+new_can_alm+"' where idMaterial = '"+mat.getId()+"' and idAlmacen = '"+alm.getId()+"'";
//                    con.actualiza(st1, upt_pro_alm);
//                    con.cerrar(st1);
//                 } catch (Exception ex1) {
//                     System.out.print(ex1);
//                 }
//                System.out.print("Cantidad nueva: " + new_can_alm  + " Producto: " + mat.getId() + " en Almacen: " + alm.getId() + "\n");
//                
//                try {
//                    Statement st1 = con.conexion();
//                    String upt_pro = "update Material set cant_actual = '"+new_can+"' where idMaterial = '"+mat.getId()+"'";
//                    con.actualiza(st1, upt_pro);
//                    con.cerrar(st1);
//                 } catch (Exception ex1) {
//                     System.out.print(ex1);
//                 }
//                System.out.print("Cantidad nueva: " + new_can  + " Producto: " + mat.getId() + "\n");
//
//                try {
//                    Statement st1 = con.conexion();
//                    String ins_kardex = "insert into Kardex Values (null, '"+mat.getId()+"', '"+alm.getId()+"', '"+pro.getRuc()+"', '"+pro.getRaz_soc()+"', '"+com.getFec_com()+"', "
//                            + "'"+tido.getId()+"', '"+tido.getSerie()+"', '"+tido.getNro()+"', '6',  '0.00', '0.00', '"+mat.getCant()+"', '"+mat.getPre()+"', '-', '"+lote+"')";
//                    con.actualiza(st1, ins_kardex);
//                    con.cerrar(st1);          
//                } catch (Exception ex) {
//                    System.out.print(ex);
//                }
//                System.out.print("Agregando al Kardex: Producto: " + mat.getId() + " - " + com.getFec_com()+ " - " + mat.getCant()+ "\n");
//                
//            }
//            
//        } catch (SQLException ex) {
//            System.out.print(ex);
//        }
//        
//        //calcular monto 
//        Double monto = 0.00;
//            monto = suma_pro; 
//
//        
//        try {
//            Statement st1 = con.conexion();
//            String del_ped = "delete from Detalle_Compra where idCompra = '"+com.getId()+"'";
//            con.actualiza(st1, del_ped);
//            con.cerrar(st1);
//         } catch (Exception ex1) {
//             System.out.print(ex1);
//         }
//        System.out.print("Eliminando compra" + "\n");
//        
//        try {
//            Statement st1 = con.conexion();
//            String del_ped = "delete from Compra where idCompra = '"+com.getId()+"'";
//            con.actualiza(st1, del_ped);
//            con.cerrar(st1);
//         } catch (Exception ex1) {
//             System.out.print(ex1);
//         }
//        System.out.print("Eliminando detalle de compra \n");
//        
//        String query = "select c.idCompra, c.fec_com, c.fec_pago, t.nom_doc, c.serie_doc, c.nro_doc, c.ruc_pro, p.raz_soc_pro, a.nom_alm from Compra as c "
//                + "inner join Tipo_Documento as t on c.idTipo_Documento=t.idTipo_Documento inner join Proveedor as p on c.ruc_pro=p.ruc_pro "
//                + "inner join Almacen as a on c.idAlmacen=a.idAlmacen order by c.fec_com desc, c.idCompra desc";
//        ver_compras(query);
//        
//        System.out.print("Mostrando lista \n");
//        txt_bus.requestFocus();
//        
//    }
    }//GEN-LAST:event_btn_anuActionPerformed

    private void btn_pagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pagarActionPerformed
        //  VER CUOTAS
        frm_ver_cuota_compra cuota = new frm_ver_cuota_compra();
        pro.setRuc(t_compras.getValueAt(i, 7).toString());
        // CARGAR DATOS DE LA FACTURA
        cuota.txt_ruc.setText(t_compras.getValueAt(i, 7).toString());
        cuota.txt_raz.setText(t_compras.getValueAt(i, 8).toString());
        cuota.txt_tipd.setText(t_compras.getValueAt(i, 4).toString());
        cuota.txt_sndoc.setText(t_compras.getValueAt(i, 5).toString() + " - " + t_compras.getValueAt(i, 6).toString());
        cuota.txt_fec.setText(t_compras.getValueAt(i, 2).toString());
        com.setTotal(Double.parseDouble(t_compras.getValueAt(i, 10).toString()) * 1.18);
        cuota.com.setTotal(com.getTotal());
        com.setId(Integer.parseInt(t_compras.getValueAt(i, 0).toString()));
        String periodo = t_compras.getValueAt(i, 1).toString();
        cuota.periodo = periodo;
        cuota.com.setId(Integer.parseInt(t_compras.getValueAt(i, 0).toString()));

        try {
            Statement st = con.conexion();
            String ver_moneda = "select m.nombre, c.idmon, c.tc from compra as c inner join moneda as m on c.idmon = m.idmoneda where c.idcompra = '" + com.getId() + "' and c.periodo = '" + periodo + "' ";
            ResultSet rs = con.consulta(st, ver_moneda);
            if (rs.next()) {
                cuota.txt_mon.setText(rs.getString("nombre"));
                cuota.moneda = rs.getInt("idmon");
                cuota.txt_tc.setText(ven.formato_tc(rs.getDouble("tc")));
            }
            con.cerrar(rs);
            con.cerrar(st);
        } catch (Exception e) {
        }

        Double tot_cuotas = 0.0;
        Double tot_monto = 0.0;

        //  CARGAR CUOTAS GENERADAS EN COMPRA
        try {
            cuota.mostrar = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int fila, int columna) {
                    return false;
                }
            };
            cuota.mostrar.addColumn("Nro Cuota");
            cuota.mostrar.addColumn("Fec. Venc.");
            cuota.mostrar.addColumn("Cuota");
            cuota.mostrar.addColumn("Fecha Pago");
            cuota.mostrar.addColumn("Moneda");
            cuota.mostrar.addColumn("Tipo Cambio");
            cuota.mostrar.addColumn("Monto");
            cuota.mostrar.addColumn("Estado");

            Statement st = con.conexion();
            String ver_cuotas = "select pc.idpago, pc.fec_venc, pc.fec_pago, pc.monto_cuota, m.simbolo, pc.tc, pc.monto, pc.estado from pago_compras as pc inner join moneda as m "
                    + "on pc.idmon = m.idmoneda where pc.idcompra = '" + com.getId() + "' and pc.periodo = '" + periodo + "'";
            ResultSet rs = con.consulta(st, ver_cuotas);

            while (rs.next()) {
                Object fila[] = new Object[8];
                fila[0] = rs.getString("idpago");
                fila[1] = ven.fechaformateada(rs.getString("fec_venc"));
                fila[2] = rs.getDouble("monto_cuota");
                tot_cuotas += rs.getDouble("monto_cuota");
                if (rs.getString("fec_pago").equals("7000-01-01")) {
                    fila[3] = "-";
                } else {
                    fila[3] = ven.fechaformateada(rs.getString("fec_pago"));
                }
                fila[4] = rs.getString("simbolo");
                fila[5] = ven.formato_tc(rs.getDouble("tc"));
                fila[6] = ven.formato_numero(rs.getDouble("monto"));
                tot_monto += rs.getDouble("monto");
                if (rs.getString("estado").equals("0")) {
                    fila[7] = "Pendiente";
                } else {
                    fila[7] = "Pagado";
                }
                cuota.mostrar.addRow(fila);
            }
            cuota.t_cuotas.setModel(cuota.mostrar);
            ven.derecha_celda(cuota.t_cuotas, 0);
            ven.centrar_celda(cuota.t_cuotas, 1);
            ven.derecha_celda(cuota.t_cuotas, 2);
            ven.centrar_celda(cuota.t_cuotas, 3);
            ven.derecha_celda(cuota.t_cuotas, 4);
            ven.derecha_celda(cuota.t_cuotas, 5);
            ven.derecha_celda(cuota.t_cuotas, 6);
            ven.centrar_celda(cuota.t_cuotas, 7);
            con.cerrar(rs);
            con.cerrar(st);
        } catch (Exception e) {
            System.out.println(e);
        }
        cuota.txt_dtot.setText(ven.formato_numero(com.getTotal()));
        cuota.txt_tot.setText(ven.formato_numero(tot_cuotas));
        cuota.txt_pen.setText(ven.formato_numero(com.getTotal() - tot_monto));
        cuota.txt_pag.setText(ven.formato_numero(tot_monto));
        if (tot_cuotas == com.getTotal()) {
            cuota.btn_addc.setEnabled(false);
        }
        cuota.origen = "paga_servicio";
        ven.llamar_ventana(cuota);
        this.dispose();
    }//GEN-LAST:event_btn_pagarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_anu;
    private javax.swing.JButton btn_cer;
    private javax.swing.JButton btn_pagar;
    private javax.swing.JButton btn_reg;
    private javax.swing.JComboBox cbx_bus;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable t_compras;
    private javax.swing.JTextField txt_bus;
    private javax.swing.JTextField txt_igv;
    private javax.swing.JTextField txt_sub;
    private javax.swing.JTextField txt_total;
    // End of variables declaration//GEN-END:variables
}
