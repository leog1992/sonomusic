/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

import Clases.Cl_Almacen;
import Clases.Cl_Conectar;
import Clases.Cl_Productos;
import Clases.Cl_Requerimiento;
import Clases.Cl_Varios;
import Vistas.frm_ver_prod_alm;
import Vistas.frm_ver_solicitudes;
import com.mxrck.autocompleter.AutoCompleterCallback;
import com.mxrck.autocompleter.TextAutoCompleter;
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import sonomusic.frm_menu;

/**
 *
 * @author luis-d
 */
public class frm_reg_solicitud extends javax.swing.JInternalFrame {

    Cl_Varios ven = new Cl_Varios();
    Cl_Conectar con = new Cl_Conectar();
    Cl_Almacen alm = new Cl_Almacen();
    Cl_Productos pro = new Cl_Productos();
    Cl_Requerimiento req = new Cl_Requerimiento();
    public static DefaultTableModel detalle;
    public static int idsol = 0;

    /**
     * Creates new form frm_reg_solicitud
     */
    public frm_reg_solicitud() {
        initComponents();
        cbx_destino.requestFocus();
        detalle = new DefaultTableModel();
// {@Override
//     public boolean isCellEditable (int fila, int columna) {
//         return false;
//     }
// };
        detalle.addColumn("Id");
        detalle.addColumn("Descripcion");
        detalle.addColumn("Marca");
        detalle.addColumn("Cantidad");
        detalle.addColumn("Und. Med.");
        t_solicitud.setModel(detalle);
        t_solicitud.getColumnModel().getColumn(0).setPreferredWidth(30);
        t_solicitud.getColumnModel().getColumn(1).setPreferredWidth(300);
        t_solicitud.getColumnModel().getColumn(2).setPreferredWidth(90);
        t_solicitud.getColumnModel().getColumn(3).setPreferredWidth(60);
        t_solicitud.getColumnModel().getColumn(4).setPreferredWidth(80);
        btn_env.setVisible(false);

        cargar_almacen();
    }

    private void cargar_almacen() {
        try {
            Statement st = con.conexion();
            String query = "select * from almacen";
            ResultSet rs = con.consulta(st, query);

            while (rs.next()) {
                String cap;
                cap = rs.getString("nom_alm");
                cbx_destino.addItem(cap);
            }

            con.cerrar(st);
            con.cerrar(rs);
        } catch (Exception e) {
            System.out.println("Ocurrio un error " + e.getMessage() + " en :" + e.getLocalizedMessage());
        }
    }

    private void cargar_productos_txt() {
        try {
            // autocompletar = new TextAutoCompleter(txt_buscar_producto);
            TextAutoCompleter autocompletar = new TextAutoCompleter(txt_buscar_producto, new AutoCompleterCallback() {
                @Override
                public void callback(Object selectedItem) {
                    txt_buscar_producto.setText("");
                    txt_buscar_producto.requestFocus();
                    String cadena = selectedItem.toString();
                    JOptionPane.showMessageDialog(null, "El usuario seleccionó: " + cadena);
                    if (cadena.length() > 0) {
                        boolean contiene_guion = false;
                        for (int j = 0; j < cadena.length(); j++) {
                            if (cadena.charAt(j) == '-') {
                                contiene_guion = true;
                            }
                        }
                        if (contiene_guion == true) {
                            contiene_guion = false;
                            String[] array_producto = cadena.split("-");
                            String id_producto = array_producto[0].trim();
                            //JOptionPane.showMessageDialog(null, "El id del Producto es : " + id_producto);

                            try {
                                Statement st = con.conexion();
                                String ver_producto = "select p.idProductos, p.desc_pro, p.modelo, p.serie, p.marca, pa.cant, p.cant_min, pa.precio, p.estado, c.desc_clas, "
                                        + "u.desc_und, p.grado from producto_almacen as pa inner join productos as p on pa.idProductos=p.idProductos "
                                        + "inner join clasificacion as c on p.id_clas=c.id_clas inner join und_medida as u on "
                                        + "p.idUnd_Medida=u.idUnd_Medida where pa.idAlmacen = '" + frm_menu.alm.getId() + "' and p.idProductos = '" + id_producto + "'";
                                System.out.println(ver_producto);
                                ResultSet rs = con.consulta(st, ver_producto);
                                if (rs.next()) {
                                    Object fila[] = new Object[6];
                                    fila[0] = id_producto;
                                    fila[1] = rs.getString("desc_pro") + " " + rs.getString("marca") + " " + rs.getString("modelo") + " " + rs.getString("serie");
                                    fila[2] = rs.getString("marca");
                                    double cantidad = rs.getDouble("cant");
                                    fila[3] = ven.formato_numero(cantidad);
                                    String texto = JOptionPane.showInputDialog("Ingrese Cantidad");
                                    double cantidad_nueva = 1;
                                    if (texto != null) {
                                        if (ven.esDecimal(texto)) {
                                            cantidad_nueva = Double.parseDouble(texto);
                                            // if (cantidad >= cantidad_nueva) {
                                            fila[4] = ven.formato_numero(cantidad_nueva);
                                            /* } else {
                                             double exceso = cantidad_nueva - cantidad;
                                             cantidad_nueva = cantidad;
                                             fila[4] = ven.formato_numero(cantidad_nueva);
                                             JOptionPane.showMessageDialog(null, "NO HAY DEMASIADOS PRODUCTOS \n EXCESO DE " + exceso + " UNIDADES");
                                             }*/
                                        } else {
                                            fila[4] = ven.formato_numero(cantidad_nueva);
                                        }
                                    } else {
                                        fila[4] = ven.formato_numero(cantidad_nueva);
                                    }
                                    fila[5] = rs.getString("desc_und");

                                    //if (cantidad > 0.0) {
                                    valida_tabla(Integer.parseInt(id_producto), fila);
                                    btn_enviar.setEnabled(true);
                                    //} else {
                                    //    JOptionPane.showMessageDialog(null, "No existe suficiente cantidad para agregar el producto.");
                                    //}
                                }

                                con.cerrar(rs);
                                con.cerrar(st);

                            } catch (SQLException | HeadlessException e) {
                                System.out.println(e);
                                JOptionPane.showMessageDialog(null, e.getLocalizedMessage());
                            }

                            txt_buscar_producto.setText("");
                            txt_buscar_producto.requestFocus();

                        }
                        if (contiene_guion == false) {
                            txt_buscar_producto.setText("");
                            txt_buscar_producto.requestFocus();
                        }

                    }
                }
            });

            autocompletar.setMode(0);
            autocompletar.setCaseSensitive(false);
            Statement st = con.conexion();
            String sql = "select pa.idProductos,p.desc_pro, p.marca, p.modelo, p.serie, pa.cant, pa.precio"
                    + " from producto_almacen as pa inner join productos as p"
                    + " on pa.idProductos=p.idProductos where pa.idAlmacen ='" + frm_menu.alm.getId() + "' "
                    + "order by p.desc_pro asc, p.modelo asc";
            ResultSet rs = con.consulta(st, sql);
            while (rs.next()) {
                autocompletar.addItem(rs.getString("pa.idProductos") + " - " + rs.getString("p.desc_pro") + " " + rs.getString("p.marca") + " " + rs.getString("p.modelo")
                        + " -- Cant.: " + rs.getString("pa.cant"));
            }
            con.cerrar(rs);
            con.cerrar(st);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error " + e.getLocalizedMessage());
            System.out.println(e.getLocalizedMessage());
        }
    }

    private void valida_tabla(int producto, Object[] objeto) {
        //estado de ingreso
        boolean ingresar = false;
        int cuenta_iguales = 0;

        //verificar fila no se repite
        int contar_filas = t_solicitud.getRowCount();
        if (contar_filas == 0) {
            ingresar = true;
        }

        if (contar_filas > 0) {
            for (int j = 0; j < contar_filas; j++) {
                int id_producto_fila = Integer.parseInt(t_solicitud.getValueAt(j, 0).toString());
                if (producto == id_producto_fila) {
                    ingresar = false;
                    cuenta_iguales++;
                    JOptionPane.showMessageDialog(null, "El Producto a Ingresar ya existe en la lista");
                } else {
                    ingresar = true;
                }
            }
        }

        if (ingresar == true && cuenta_iguales == 0) {
            detalle.addRow(objeto);
            t_solicitud.setModel(detalle);
            // contar_filas = t_detalle.getRowCount();
            contar_filas();
//            String texto = JOptionPane.showInputDialog("Ingrese Cantidad");
//            if (texto != null) {
//                if (ven.esDecimal(texto)) {
//                    double cantidad = Double.parseDouble(texto);
//                    Double cantidad_actual = Double.parseDouble(t_detalle.getValueAt(contar_filas - 1, 2).toString());
//                    if (cantidad_actual >= cantidad) {
//                        t_detalle.setValueAt(ven.formato_numero(cantidad), contar_filas - 1, 3);
//                    } else {
//                        JOptionPane.showMessageDialog(null, "NO PUEDE SOBREPASAR CANTIDAD ACTUAL");
//                    }
//                    calcular_total();
//                }
//            }
        }
    }

    void contar_filas() {
        int contar_filas = t_solicitud.getRowCount();
        if (contar_filas == 29) {
            txt_buscar_producto.setEnabled(false);
            JOptionPane.showMessageDialog(null, "SE HA LLEGADO AL LIMITE DE 30 PRODUCTOS");
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
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txt_id_ori = new javax.swing.JTextField();
        txt_nom_ori = new javax.swing.JTextField();
        txt_fec = new javax.swing.JFormattedTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txt_buscar_producto = new javax.swing.JTextField();
        btn_bus = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        t_solicitud = new javax.swing.JTable();
        btn_cer = new javax.swing.JButton();
        btn_enviar = new javax.swing.JButton();
        btn_env = new javax.swing.JButton();
        cbx_destino = new javax.swing.JComboBox();
        cbx_busqueda = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(254, 254, 254));
        setClosable(true);
        setTitle("Crear Pedido");

        jLabel1.setForeground(new java.awt.Color(255, 0, 0));
        jLabel1.setText("DATOS DEL PEDIDO");
        jLabel1.setToolTipText("");
        jLabel1.setFocusable(false);

        jLabel2.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel2.setForeground(java.awt.Color.red);
        jLabel2.setText("Tienda Origen:");
        jLabel2.setFocusable(false);

        jLabel3.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel3.setForeground(java.awt.Color.red);
        jLabel3.setText("Tienda Destino:");
        jLabel3.setFocusable(false);

        jLabel4.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel4.setForeground(java.awt.Color.red);
        jLabel4.setText("Fecha Solicitud:");
        jLabel4.setFocusable(false);

        txt_id_ori.setEditable(false);
        txt_id_ori.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_id_ori.setFocusable(false);

        txt_nom_ori.setEditable(false);
        txt_nom_ori.setFocusable(false);

        txt_fec.setEditable(false);
        try {
            txt_fec.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txt_fec.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_fec.setFocusable(false);
        txt_fec.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_fecKeyPressed(evt);
            }
        });

        jLabel7.setForeground(new java.awt.Color(255, 0, 0));
        jLabel7.setText("Detalle de Articulos:");
        jLabel7.setFocusable(false);

        jLabel8.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel8.setForeground(java.awt.Color.red);
        jLabel8.setText("Buscar Productos:");
        jLabel8.setFocusable(false);

        txt_buscar_producto.setEditable(false);
        txt_buscar_producto.setFocusable(false);

        btn_bus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/find.png"))); // NOI18N
        btn_bus.setEnabled(false);
        btn_bus.setFocusable(false);
        btn_bus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_busActionPerformed(evt);
            }
        });

        jScrollPane1.setFocusable(false);

        t_solicitud.setModel(new javax.swing.table.DefaultTableModel(
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
        t_solicitud.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                t_solicitudKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                t_solicitudKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(t_solicitud);

        btn_cer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/cancel.png"))); // NOI18N
        btn_cer.setText("Cerrar");
        btn_cer.setFocusable(false);
        btn_cer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cerActionPerformed(evt);
            }
        });

        btn_enviar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/accept.png"))); // NOI18N
        btn_enviar.setText("Solicitar");
        btn_enviar.setFocusable(false);
        btn_enviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_enviarActionPerformed(evt);
            }
        });

        btn_env.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/add.png"))); // NOI18N
        btn_env.setText("Enviar Ped.");
        btn_env.setEnabled(false);
        btn_env.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_envActionPerformed(evt);
            }
        });
        btn_env.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btn_envKeyPressed(evt);
            }
        });

        cbx_destino.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cbx_destinoKeyPressed(evt);
            }
        });

        cbx_busqueda.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "CARGAR TODOS LOS PRODUCTOS", "SOLO PRODUCTOS FALTANTES" }));
        cbx_busqueda.setEnabled(false);
        cbx_busqueda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cbx_busquedaKeyPressed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel5.setForeground(java.awt.Color.red);
        jLabel5.setText("Busqueda:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel7))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txt_id_ori, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txt_nom_ori, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cbx_destino, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGap(41, 41, 41)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txt_fec, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(cbx_busqueda, 0, 356, Short.MAX_VALUE)
                                        .addGap(48, 48, 48))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_buscar_producto)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addComponent(btn_bus))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btn_env)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_enviar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_cer)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_id_ori, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_nom_ori, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbx_destino, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_fec, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbx_busqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_bus, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_buscar_producto, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 322, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_cer, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_enviar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_env))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_cerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cerActionPerformed
        this.dispose();

    }//GEN-LAST:event_btn_cerActionPerformed

    private void txt_fecKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_fecKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (txt_fec.getText().trim().length() == 10) {
                cbx_busqueda.setEnabled(true);
                cbx_busqueda.requestFocus();
            } else {
                txt_fec.requestFocus();
            }
        }
    }//GEN-LAST:event_txt_fecKeyPressed

    private void btn_busActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_busActionPerformed
        // Buscar Productos que estan por terminar
        frm_ver_prod_alm mat = new frm_ver_prod_alm();
        frm_menu menu = null;
        String query = "select p.idProductos, p.desc_pro, p.modelo, p.serie, p.marca, pa.cant, p.cant_min, pa.precio, p.costo_compra, p.estado, c.desc_clas, "
                + "u.desc_und, p.grado from producto_almacen as pa inner join productos as p on pa.idProductos=p.idProductos inner join clasificacion as "
                + "c on p.id_clas=c.id_clas inner join und_medida as u on p.idUnd_Medida=u.idUnd_Medida where pa.idAlmacen = '" + menu.alm.getId() + "' "
                + "and pa.cant <= p.cant_min and pa.cant = '0' order by p.desc_pro asc";
        pro.mostrar_productos(query, mat.t_productos);
        mat.funcion = "solicitar";
        mat.txt_ida.setText("" + menu.alm.getId());
        mat.txt_noma.setText(menu.alm.getNom());
        ven.llamar_ventana(mat);
    }//GEN-LAST:event_btn_busActionPerformed

    private void t_solicitudKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_t_solicitudKeyReleased
    }//GEN-LAST:event_t_solicitudKeyReleased

    private void llenar() {
        frm_menu menu = null;
        req.setAlm_ori(menu.alm.getId());
        req.setAlm_des(cbx_destino.getSelectedIndex() + 1);
        req.setFec_rea("7000-01-01");
        req.setFec_sol(ven.fechabase(txt_fec.getText()));
    }

    private void btn_enviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_enviarActionPerformed
        llenar();
//grabar pedido
        try {
            Statement st = con.conexion();
            String ins_ped = "insert into solicitud_articulos Values (null, '" + req.getFec_sol() + "', '" + req.getFec_rea() + "','" + req.getDias() + "',"
                    + "'" + req.getAlm_ori() + "','" + req.getAlm_des() + "', '0', '" + frm_menu.usu.getNick() + "')";
            con.actualiza(st, ins_ped);
            con.cerrar(st);
            System.out.println("insertado con exito \n");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getLocalizedMessage());
            System.out.print(ex);
        }

        // consultar id pedido;
        try {
            Statement st = con.conexion();
            String ver_ped = "select idsolicitud from solicitud_articulos where fec_sol = '" + req.getFec_sol() + "' "
                    + "and nick = '" + frm_menu.usu.getNick() + "' order by idsolicitud desc limit 1";
            ResultSet rs = con.consulta(st, ver_ped);
            if (rs.next()) {
                req.setId(rs.getInt("idsolicitud"));
            }
            con.cerrar(rs);
            con.cerrar(st);
            System.out.println("el id es " + req.getId() + " \n");
        } catch (Exception e) {
            System.out.println(e);
        }

        try {
            int nro_filas = t_solicitud.getRowCount();
            for (int j = 0; j < nro_filas; j++) {
                pro.setId_pro(Integer.parseInt(t_solicitud.getValueAt(j, 0).toString()));
                pro.setCan(Double.parseDouble(t_solicitud.getValueAt(j, 3).toString()));
                Statement st = con.conexion();
                String det_ped = "insert into detalle_solicitud Values ('" + req.getId() + "', '" + pro.getId_pro() + "', '" + pro.getCan() + "', '0')";
                con.actualiza(st, det_ped);
                con.cerrar(st);
                System.out.println("insertado linea " + j + " con exito \n");
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        //grabar detalle pedido
        frm_ver_solicitudes soli = new frm_ver_solicitudes();
        ven.llamar_ventana(soli);
        this.dispose();
    }//GEN-LAST:event_btn_enviarActionPerformed

    private void t_solicitudKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_t_solicitudKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            int i = t_solicitud.getSelectedRow();
            int idp = Integer.parseInt(t_solicitud.getValueAt(i, 0).toString());
            try {
                Statement st = con.conexion();
                String ver_pro = "select idProductos from producto_almacen where"
                        + " idProductos = '" + idp + "' and idAlmacen = '" + frm_menu.alm.getId() + "'";
                ResultSet rs = con.consulta(st, ver_pro);
                if (!rs.next()) {
                    JOptionPane.showMessageDialog(null, "El Producto no existe en su almacen");
                    t_solicitud.setValueAt("0", i, 5);
                    btn_env.setEnabled(false);
                } else {
                    btn_env.setEnabled(true);
                    btn_env.requestFocus();
                }
                con.cerrar(rs);
                con.cerrar(st);
            } catch (SQLException | HeadlessException e) {
                System.out.println(e);
            }

        }

        //RECORRER TABLA Y DETECTA PRODUCTOS
        int nro_filas = t_solicitud.getRowCount();
        for (int j = 0; j < nro_filas; j++) {
            int idp = Integer.parseInt(t_solicitud.getValueAt(j, 0).toString());
            try {
                Statement st = con.conexion();
                String ver_pro = "select idProductos from producto_almacen where"
                        + " idProductos = '" + idp + "' and idAlmacen = '" + frm_menu.alm.getId() + "'";
                ResultSet rs = con.consulta(st, ver_pro);
                if (!rs.next()) {
                    JOptionPane.showMessageDialog(null, "El Producto no existe en su almacen");
                    t_solicitud.setValueAt("0", j, 5);
                    btn_env.setEnabled(false);
                } else {
                    btn_env.setEnabled(true);
                }
                con.cerrar(rs);
                con.cerrar(st);
            } catch (SQLException | HeadlessException e) {
                System.out.println(e);
            }
        }
    }//GEN-LAST:event_t_solicitudKeyPressed

    private void btn_envKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btn_envKeyPressed

    }//GEN-LAST:event_btn_envKeyPressed

    private void btn_envActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_envActionPerformed
        frm_reg_traslado_almacen traslado = new frm_reg_traslado_almacen();
        int idalm = Integer.parseInt(txt_id_ori.getText());
        traslado.cbx_alm_de.setEnabled(false);
        traslado.cbx_alm_de.setSelectedIndex(Integer.parseInt(txt_id_ori.getText()) - 1);
        System.out.println(idalm);
        try {
            Statement st = con.conexion();
            String ver_alm = "select ruc, raz_soc from Almacen where idAlmacen = '" + idalm + "'";
            ResultSet rs = con.consulta(st, ver_alm);
            if (rs.next()) {
                traslado.txt_ruc_alm.setText(rs.getString("ruc"));
                traslado.txt_raz_alm.setText(rs.getString("raz_soc"));
            }
            con.cerrar(rs);
            con.cerrar(st);
        } catch (Exception e) {
            System.out.println(e);
        }
        traslado.idsol = idsol;

        traslado.txt_fec.setEditable(true);
        traslado.txt_fec.requestFocus();

        int filas_tabla = t_solicitud.getRowCount();
        for (int j = 0; j < filas_tabla; j++) {
            if (Integer.parseInt(t_solicitud.getValueAt(j, 5).toString()) != 0) {
                Object[] fila = new Object[5];
                fila[0] = t_solicitud.getValueAt(j, 0);
                fila[1] = t_solicitud.getValueAt(j, 1);
                fila[2] = t_solicitud.getValueAt(j, 2);
                fila[3] = t_solicitud.getValueAt(j, 5);
                fila[4] = t_solicitud.getValueAt(j, 4);
                traslado.detalle.addRow(fila);
            }
        }
        traslado.t_detalle.setModel(traslado.detalle);
        traslado.btn_enviar.setEnabled(true);
        traslado.accion = "traslado";
        ven.llamar_ventana(traslado);
        this.dispose();
    }//GEN-LAST:event_btn_envActionPerformed

    private void cbx_destinoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbx_destinoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txt_fec.setText(ven.fechaformateada(ven.getFechaActual()));
            txt_fec.setEditable(true);
            txt_fec.setFocusable(true);
            txt_fec.requestFocus();
        }
    }//GEN-LAST:event_cbx_destinoKeyPressed

    private void cbx_busquedaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbx_busquedaKeyPressed
        
    }//GEN-LAST:event_cbx_busquedaKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_bus;
    private javax.swing.JButton btn_cer;
    public static javax.swing.JButton btn_env;
    public static javax.swing.JButton btn_enviar;
    private javax.swing.JComboBox cbx_busqueda;
    public static javax.swing.JComboBox cbx_destino;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTable t_solicitud;
    private javax.swing.JTextField txt_buscar_producto;
    public static javax.swing.JFormattedTextField txt_fec;
    public static javax.swing.JTextField txt_id_ori;
    public static javax.swing.JTextField txt_nom_ori;
    // End of variables declaration//GEN-END:variables
}
