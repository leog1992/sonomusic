/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

import Clases.Cl_Almacen;
import Clases.Cl_Compra;
import Clases.Cl_Conectar;
import Clases.Cl_Moneda;
import Clases.Cl_Productos;
import Clases.Cl_Proveedor;
import Clases.Cl_Tipo_Documentos;
import Clases.Cl_Varios;
import Clases.render_productos;
import Vistas.frm_ver_ingresos;
import Vistas.frm_ver_productos;
import Vistas.frm_ver_proveedores;
import com.mxrck.autocompleter.AutoCompleterCallback;
import com.mxrck.autocompleter.TextAutoCompleter;
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import sonomusic.frm_menu;

/**
 *
 * @author luis-d
 */
public class frm_reg_ingreso extends javax.swing.JInternalFrame {

    Cl_Conectar con = new Cl_Conectar();
    Cl_Varios ven = new Cl_Varios();
    Cl_Proveedor pro = new Cl_Proveedor();
    Cl_Productos art = new Cl_Productos();
    Cl_Compra com = new Cl_Compra();
    Cl_Almacen alm = new Cl_Almacen();
    Cl_Tipo_Documentos tido = new Cl_Tipo_Documentos();
    Cl_Moneda mon = new Cl_Moneda();
    public static DefaultTableModel detalle;
    Integer i;
    double tc;

    /**
     * Creates new form frm_reg_compra
     */
    public frm_reg_ingreso() {
        initComponents();
        ver_almacen();
        txt_fec_com.setText(ven.fechaformateada(ven.getFechaActual()));
        ver_tido();
        ver_monedas();
        //dar formato a tabla
        detalle = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int fila, int columna) {
                return columna == 2 || columna == 4 || columna == 6;
            }
        };
        detalle.addColumn("Id");
        detalle.addColumn("Descripcion");
        detalle.addColumn("Cant.");
        detalle.addColumn("Und. Med");
        detalle.addColumn("P. Costo");
        detalle.addColumn("Parcial");
        detalle.addColumn("P. Venta");
        t_detalle.setModel(detalle);
        t_detalle.getColumnModel().getColumn(0).setPreferredWidth(80);
        t_detalle.getColumnModel().getColumn(1).setPreferredWidth(450);
        t_detalle.getColumnModel().getColumn(2).setPreferredWidth(80);
        t_detalle.getColumnModel().getColumn(3).setPreferredWidth(80);
        t_detalle.getColumnModel().getColumn(4).setPreferredWidth(80);
        t_detalle.getColumnModel().getColumn(5).setPreferredWidth(80);
        t_detalle.getColumnModel().getColumn(6).setPreferredWidth(80);
        ven.centrar_celda(t_detalle, 0);
        ven.derecha_celda(t_detalle, 2);
        ven.centrar_celda(t_detalle, 3);
        ven.derecha_celda(t_detalle, 4);
        ven.derecha_celda(t_detalle, 5);
        ven.derecha_celda(t_detalle, 6);
        detalle.fireTableDataChanged();
        cargar_productos_txt();
        jtb_compra.setSelectedIndex(0);
        cbx_tido.requestFocus();
    }

    private void ver_almacen() {
        try {
            ArrayList almacen = new ArrayList();
            Statement st = con.conexion();
            String query = "select nom_alm from almacen order by idAlmacen asc";
            ResultSet rs = con.consulta(st, query);
            while (rs.next()) {
                almacen.add(rs.getString("nom_alm"));
            }
            if (almacen.size() > 0) {
                for (Object almacen1 : almacen) {
                    cbx_alm.addItem(almacen1);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Lista de Almacen no disponible");
            }
            con.cerrar(st);
            con.cerrar(rs);
        } catch (SQLException ex) {
            System.out.print(ex);
            JOptionPane.showMessageDialog(null, "Lista no disponible");
        }
    }

    private void ver_tido() {
        try {
            ArrayList tido = new ArrayList();
            Statement st = con.conexion();
            String query = "select desc_tipd from tipo_doc order by idtipo_doc asc";
            ResultSet rs = con.consulta(st, query);
            while (rs.next()) {
                tido.add(rs.getString("desc_tipd"));
            }
            if (tido.size() > 0) {
                for (Object tido1 : tido) {
                    cbx_tido.addItem(tido1);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Lista no disponible");
            }
            con.cerrar(st);
            con.cerrar(rs);
        } catch (SQLException ex) {
            System.out.print(ex);
            JOptionPane.showMessageDialog(null, ex.getLocalizedMessage());
        }
    }

    private void ver_monedas() {
        try {
            ArrayList array_mon = new ArrayList();
            Statement st = con.conexion();
            String query = "select nombre from moneda order by idmoneda asc";
            ResultSet rs = con.consulta(st, query);
            while (rs.next()) {
                array_mon.add(rs.getString("nombre"));
            }
            if (array_mon.size() > 0) {
                for (Object object_mon : array_mon) {
                    cbx_mon.addItem(object_mon);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Lista no disponible");
            }
            con.cerrar(st);
            con.cerrar(rs);
        } catch (SQLException ex) {
            System.out.print(ex);
            JOptionPane.showMessageDialog(null, ex.getLocalizedMessage());
        }
    }

    private void cargar_productos_txt() {
        try {
            // autocompletar = new TextAutoCompleter(txt_buscar_producto);
            TextAutoCompleter autocompletar = new TextAutoCompleter(txt_busqueda, new AutoCompleterCallback() {
                @Override
                public void callback(Object selectedItem) {
                    txt_busqueda.setText("");
                    txt_busqueda.requestFocus();
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
                            boolean incluir_producto = valida_tabla(Integer.parseInt(id_producto));

                            if (incluir_producto == true) {
                                try {
                                    Statement st = con.conexion();
                                    String ver_producto = "select p.idProductos, p.desc_pro, p.modelo, p.serie, p.marca, p.costo_compra, p.precio_venta, u.desc_und, p.grado from productos as p "
                                            + "inner join und_medida as u on p.idUnd_Medida=u.idUnd_Medida "
                                            + "where p.idProductos = '" + id_producto + "'";
                                    System.out.println(ver_producto);
                                    ResultSet rs = con.consulta(st, ver_producto);
                                    if (rs.next()) {
                                        lbl_codigo.setText(id_producto);
                                        txt_busqueda.setText(rs.getString("desc_pro") + " " + rs.getString("marca") + " " + rs.getString("modelo") + " " + rs.getString("serie"));
                                        txt_precio.setText(ven.formato_numero(rs.getDouble("precio_venta")));
                                        txt_unidad_medida.setText(rs.getString("desc_und"));
                                        txt_costo.setEnabled(true);
                                        txt_costo.requestFocus();
                                    }
                                    con.cerrar(rs);
                                    con.cerrar(st);
                                } catch (SQLException | HeadlessException e) {
                                    System.out.println(e);
                                    JOptionPane.showMessageDialog(null, e.getLocalizedMessage());
                                }
                            }

                            txt_busqueda.setText("");
                            txt_busqueda.requestFocus();

                        }
                        if (contiene_guion == false) {
                            txt_busqueda.setText("");
                            txt_busqueda.requestFocus();
                        }

                    }
                }
            });

            autocompletar.setMode(0);
            autocompletar.setCaseSensitive(false);
            Statement st = con.conexion();
            String sql = "select p.idProductos,p.desc_pro, p.marca, p.modelo, p.serie, p.precio_venta"
                    + " from productos as p order by p.desc_pro asc, p.modelo asc";
            ResultSet rs = con.consulta(st, sql);
            while (rs.next()) {
                autocompletar.addItem(rs.getString("p.idProductos") + " - " + rs.getString("p.desc_pro") + " " + rs.getString("p.marca") + " " + rs.getString("p.modelo"));
            }
            con.cerrar(rs);
            con.cerrar(st);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error " + e.getLocalizedMessage());
            System.out.println(e.getLocalizedMessage());
        }
    }

    private boolean valida_tabla(int producto) {
        boolean incluir = false;
        //estado de ingreso
        boolean ingresar = false;
        int cuenta_iguales = 0;

        //verificar fila no se repite
        int contar_filas = t_detalle.getRowCount();
        if (contar_filas == 0) {
            ingresar = true;
        }

        if (contar_filas > 0) {
            for (int j = 0; j < contar_filas; j++) {
                int id_producto_fila = Integer.parseInt(t_detalle.getValueAt(j, 0).toString());
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
            incluir = true;
        }
        return incluir;
    }

    void contar_filas() {
        int contar_filas = t_detalle.getRowCount();
        if (contar_filas == 29) {
            txt_busqueda.setEnabled(false);
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

        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        t_detalle = new javax.swing.JTable();
        jLabel13 = new javax.swing.JLabel();
        txt_tot = new javax.swing.JTextField();
        btn_ca = new javax.swing.JButton();
        btn_va = new javax.swing.JButton();
        btn_el = new javax.swing.JButton();
        btn_registrar = new javax.swing.JButton();
        btn_cer = new javax.swing.JButton();
        jtb_compra = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        txt_tc = new javax.swing.JTextField();
        cbx_mon = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        txt_fec_com = new javax.swing.JFormattedTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txt_nro = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txt_ser = new javax.swing.JTextField();
        btn_busp = new javax.swing.JButton();
        txt_raz = new javax.swing.JTextField();
        txt_ruc = new javax.swing.JTextField();
        txt_dir = new javax.swing.JTextField();
        cbx_alm = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        cbx_tido = new javax.swing.JComboBox();
        jPanel2 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        txt_busqueda = new javax.swing.JTextField();
        btn_buscar_productos = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txt_costo = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txt_precio = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        txt_cantidad = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        txt_unidad_medida = new javax.swing.JTextField();
        btn_add = new javax.swing.JButton();
        lbl_moneda = new javax.swing.JLabel();
        lbl_codigo = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txt_utilidad = new javax.swing.JTextField();

        setBackground(new java.awt.Color(254, 254, 254));
        setClosable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Registrar Compra");

        jLabel8.setForeground(new java.awt.Color(255, 0, 0));
        jLabel8.setText("DETALLE DE COMPRA");
        jLabel8.setFocusable(false);

        jScrollPane1.setBackground(new java.awt.Color(254, 254, 254));
        jScrollPane1.setFocusable(false);

        t_detalle.setModel(new javax.swing.table.DefaultTableModel(
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
        t_detalle.setFocusable(false);
        t_detalle.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                t_detalleMouseClicked(evt);
            }
        });
        t_detalle.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                t_detalleKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(t_detalle);

        jLabel13.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(212, 2, 2));
        jLabel13.setText("Total:");
        jLabel13.setFocusable(false);

        txt_tot.setEditable(false);
        txt_tot.setFocusable(false);

        btn_ca.setText("+/-");
        btn_ca.setEnabled(false);
        btn_ca.setFocusable(false);
        btn_ca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_caActionPerformed(evt);
            }
        });

        btn_va.setText("S/.");
        btn_va.setEnabled(false);
        btn_va.setFocusable(false);
        btn_va.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_vaActionPerformed(evt);
            }
        });

        btn_el.setText("X");
        btn_el.setEnabled(false);
        btn_el.setFocusable(false);
        btn_el.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_elActionPerformed(evt);
            }
        });

        btn_registrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/accept.png"))); // NOI18N
        btn_registrar.setText("Aceptar");
        btn_registrar.setEnabled(false);
        btn_registrar.setFocusable(false);
        btn_registrar.setMargin(new java.awt.Insets(2, 8, 2, 8));
        btn_registrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_registrarActionPerformed(evt);
            }
        });

        btn_cer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/cancel.png"))); // NOI18N
        btn_cer.setText("Cerrar");
        btn_cer.setFocusable(false);
        btn_cer.setMargin(new java.awt.Insets(2, 8, 2, 8));
        btn_cer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cerActionPerformed(evt);
            }
        });

        jtb_compra.setForeground(java.awt.Color.red);

        txt_tc.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_tc.setEnabled(false);
        txt_tc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_tcKeyPressed(evt);
            }
        });

        cbx_mon.setEnabled(false);
        cbx_mon.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cbx_monKeyPressed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(212, 2, 2));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("Tc.");
        jLabel6.setFocusable(false);

        try {
            txt_fec_com.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txt_fec_com.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_fec_com.setEnabled(false);
        txt_fec_com.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_fec_comKeyPressed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(212, 2, 2));
        jLabel1.setText("RUC:");
        jLabel1.setFocusable(false);

        jLabel5.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(212, 2, 2));
        jLabel5.setText("Moneda");
        jLabel5.setFocusable(false);

        jLabel16.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(212, 2, 2));
        jLabel16.setText("Nro. Doc:");
        jLabel16.setFocusable(false);

        txt_nro.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_nro.setEnabled(false);
        txt_nro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_nroKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_nroKeyTyped(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(212, 2, 2));
        jLabel15.setText("Serie:");
        jLabel15.setFocusable(false);

        jLabel3.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(212, 2, 2));
        jLabel3.setText("Alm. Dest.");
        jLabel3.setFocusable(false);

        txt_ser.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_ser.setEnabled(false);
        txt_ser.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_serKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_serKeyTyped(evt);
            }
        });

        btn_busp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/find.png"))); // NOI18N
        btn_busp.setEnabled(false);
        btn_busp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_buspActionPerformed(evt);
            }
        });

        txt_raz.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txt_raz.setEnabled(false);
        txt_raz.setFocusable(false);

        txt_ruc.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_ruc.setEnabled(false);
        txt_ruc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_rucKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_rucKeyTyped(evt);
            }
        });

        txt_dir.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        txt_dir.setEnabled(false);
        txt_dir.setFocusable(false);

        cbx_alm.setEnabled(false);
        cbx_alm.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cbx_almKeyPressed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(212, 2, 2));
        jLabel4.setText("Fecha de Compra:");
        jLabel4.setFocusable(false);

        jLabel14.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(212, 2, 2));
        jLabel14.setText("Tipo de Documento:");
        jLabel14.setFocusable(false);

        jLabel9.setForeground(new java.awt.Color(255, 0, 0));
        jLabel9.setText("DATOS GENERALES");
        jLabel9.setFocusable(false);

        cbx_tido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cbx_tidoKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cbx_tido, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txt_ser)
                            .addComponent(txt_nro)
                            .addComponent(txt_fec_com, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(txt_ruc, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txt_raz))
                                    .addComponent(txt_dir)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(cbx_mon, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cbx_alm, 0, 184, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_tc, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_busp)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_busp, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbx_tido, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                        .addComponent(txt_raz, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_ruc, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_ser, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_dir, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbx_alm, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_nro, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbx_mon, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_tc, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_fec_com, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jtb_compra.addTab("Datos Generales", jPanel1);

        jLabel10.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(212, 2, 2));
        jLabel10.setText("Producto:");
        jLabel10.setFocusable(false);

        txt_busqueda.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txt_busqueda.setEnabled(false);
        txt_busqueda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_busquedaKeyPressed(evt);
            }
        });

        btn_buscar_productos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/find.png"))); // NOI18N
        btn_buscar_productos.setEnabled(false);
        btn_buscar_productos.setFocusable(false);
        btn_buscar_productos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_buscar_productosActionPerformed(evt);
            }
        });

        jLabel2.setForeground(java.awt.Color.red);
        jLabel2.setText("Costo:");

        txt_costo.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_costo.setEnabled(false);
        txt_costo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_costoKeyPressed(evt);
            }
        });

        jLabel7.setForeground(java.awt.Color.red);
        jLabel7.setText("P. Venta:");

        txt_precio.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_precio.setEnabled(false);
        txt_precio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_precioKeyPressed(evt);
            }
        });

        jLabel17.setForeground(java.awt.Color.red);
        jLabel17.setText("Cantidad:");

        txt_cantidad.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_cantidad.setText("1.00");
        txt_cantidad.setEnabled(false);
        txt_cantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_cantidadKeyPressed(evt);
            }
        });

        jLabel18.setForeground(java.awt.Color.red);
        jLabel18.setText("Und. Medida:");

        txt_unidad_medida.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_unidad_medida.setEnabled(false);

        btn_add.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/add.png"))); // NOI18N
        btn_add.setText("Agregar");
        btn_add.setEnabled(false);
        btn_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_addActionPerformed(evt);
            }
        });

        lbl_moneda.setForeground(java.awt.Color.red);
        lbl_moneda.setText("Moneda");

        lbl_codigo.setText("Codigo");

        jLabel11.setForeground(java.awt.Color.red);
        jLabel11.setText("Utilidad:");

        txt_utilidad.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_utilidad.setEnabled(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel11)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel10)
                        .addComponent(jLabel2)
                        .addComponent(jLabel7)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txt_busqueda)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_buscar_productos))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txt_utilidad, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_costo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
                            .addComponent(txt_precio, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(182, 182, 182)
                                .addComponent(jLabel18))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbl_moneda, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel17)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txt_cantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lbl_codigo))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txt_unidad_medida, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                                .addComponent(btn_add)))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_buscar_productos, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_busqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_costo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_cantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_moneda)
                    .addComponent(lbl_codigo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_precio, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_unidad_medida, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_add, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txt_utilidad, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(72, Short.MAX_VALUE))
        );

        jtb_compra.addTab("Seleccion de Productos", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jScrollPane1))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jtb_compra))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_tot, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(131, 131, 131)
                                .addComponent(btn_ca)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_va)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_el)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btn_registrar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_cer, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jtb_compra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btn_ca, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_va, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_el, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txt_tot)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btn_cer, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_registrar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_rucKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_rucKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_F2) {
            btn_busp.doClick();
        }

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (txt_ruc.getText().length() == 11) {
                pro.setRuc(txt_ruc.getText());
                if (tido.validar_RUC(pro.getRuc()) == true) {
                    try {
                        Statement st = con.conexion();
                        String ver_pro = "select * from proveedor where ruc_pro = '" + pro.getRuc() + "'";
                        ResultSet rs = con.consulta(st, ver_pro);
                        if (rs.next()) {
                            pro.setRaz(rs.getString("raz_soc_pro"));
                            txt_raz.setText(rs.getString("raz_soc_pro"));
                            txt_dir.setText(rs.getString("dir_pro"));
                            cbx_alm.setEnabled(true);
                            cbx_alm.requestFocus();
                        } else {
                            txt_ruc.setText("");
                            txt_ruc.requestFocus();
                            JOptionPane.showMessageDialog(null, "El Proveedor no existe \nPor favor ingrese otro nro de RUC");
                        }
                    } catch (SQLException ex) {
                        System.out.print(ex);
                    }
                } else {
                    txt_ruc.setText("");
                    txt_ruc.requestFocus();
                    JOptionPane.showMessageDialog(null, "EL NRO DE RUC INGRESADO NO ES CORRECTO");
                }
            }
        }
    }//GEN-LAST:event_txt_rucKeyPressed

    private void btn_cerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cerActionPerformed
        frm_ver_ingresos compra = new frm_ver_ingresos();
        ven.llamar_ventana(compra);
        this.dispose();
    }//GEN-LAST:event_btn_cerActionPerformed

    private void cbx_almKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbx_almKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cbx_mon.setEnabled(true);
            cbx_mon.setSelectedIndex(2);
            cbx_mon.requestFocus();
        }
    }//GEN-LAST:event_cbx_almKeyPressed

    private void cbx_tidoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbx_tidoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txt_ser.setEnabled(true);
            txt_ser.requestFocus();
        }
    }//GEN-LAST:event_cbx_tidoKeyPressed

    private void txt_serKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_serKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!txt_ser.getText().isEmpty()) {
                txt_nro.setEnabled(true);
                txt_nro.requestFocus();
            }
        }
    }//GEN-LAST:event_txt_serKeyPressed

    private void txt_nroKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_nroKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!txt_nro.getText().isEmpty()) {
                txt_fec_com.setEnabled(true);
                txt_fec_com.requestFocus();
            }
        }
    }//GEN-LAST:event_txt_nroKeyPressed

    private void btn_buspActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_buspActionPerformed
        frm_ver_proveedores proveedor = new frm_ver_proveedores();
        proveedor.funcion = "ingreso_almacen";
        ven.llamar_ventana(proveedor);
    }//GEN-LAST:event_btn_buspActionPerformed

    private void btn_buscar_productosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_buscar_productosActionPerformed
        frm_ver_productos prod = new frm_ver_productos();
        frm_ver_productos.ventana = "ingreso_productos";
        frm_ver_productos.btn_mod.setEnabled(false);
        frm_ver_productos.btn_eli.setEnabled(false);
        ven.llamar_ventana(prod);
    }//GEN-LAST:event_btn_buscar_productosActionPerformed

    private void btn_caActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_caActionPerformed
        Double cantidad = Double.parseDouble(JOptionPane.showInputDialog("Ingrese Cantidad"));
        t_detalle.setValueAt(cantidad, i, 3);
        txt_tot.setText(ven.formato_numero(total()));

    }//GEN-LAST:event_btn_caActionPerformed

    private void btn_vaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_vaActionPerformed
        Double precio = Double.parseDouble(JOptionPane.showInputDialog("Ingrese Precio"));
        t_detalle.setValueAt(precio, i, 5);
        txt_tot.setText(ven.formato_numero(total()));
    }//GEN-LAST:event_btn_vaActionPerformed

    private void btn_elActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_elActionPerformed
        detalle.removeRow(i);
        txt_tot.setText(ven.formato_numero(total()));
    }//GEN-LAST:event_btn_elActionPerformed

    private void t_detalleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_t_detalleMouseClicked
        i = t_detalle.getSelectedRow();
        btn_ca.setEnabled(true);
        btn_va.setEnabled(true);
        btn_el.setEnabled(true);
        t_detalle.setFocusable(true);
    }//GEN-LAST:event_t_detalleMouseClicked

    private void t_detalleKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_t_detalleKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_PLUS) {
            btn_ca.doClick();
        }

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txt_tot.setText(ven.formato_numero(total()));
        }

        if (evt.getKeyCode() == KeyEvent.VK_DELETE) {
            i = t_detalle.getSelectedRow();
            detalle.removeRow(i);
            txt_tot.setText(ven.formato_numero(total()));
        }
    }//GEN-LAST:event_t_detalleKeyPressed

    private void llenar() {
        pro.setRuc(txt_ruc.getText());
        tido.setId(cbx_tido.getSelectedIndex() + 1);
        com.setSerie(Integer.parseInt(txt_ser.getText()));
        com.setNro(Integer.parseInt(txt_nro.getText()));
        com.setFec_com(ven.fechabase(txt_fec_com.getText()));
        alm.setId(cbx_alm.getSelectedIndex() + 1);
        mon.setId(cbx_mon.getSelectedIndex() + 1);
        tc = Double.parseDouble(txt_tc.getText());
        com.setTotal(total());
    }

    private void btn_registrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_registrarActionPerformed
        llenar();

        //Registrar Compra
        try {
            Statement st = con.conexion();
            String ins_com = "insert into ingreso Values (null, '" + pro.getRuc() + "', '" + tido.getId() + "', '" + com.getSerie() + "', '" + com.getNro() + "', "
                    + "'" + com.getFec_com() + "', '" + frm_menu.lbl_user.getText() + "','" + alm.getId() + "', '" + mon.getId() + "','" + tc + "', '" + com.getTotal() + "')";
            System.out.println(ins_com);
            con.actualiza(st, ins_com);
            con.cerrar(st);
        } catch (Exception ex) {
            System.out.print(ex);
        }

        //seleccionar ultima compra
        try {
            Statement st = con.conexion();
            String ver_id = "select idingreso from ingreso where ruc_pro = '" + pro.getRuc() + "' and idtipo_doc = '" + tido.getId() + "' and serie_doc = '" + com.getSerie() + "' "
                    + " and nro_doc = '" + com.getNro() + "' and fecha_doc = '" + com.getFec_com() + "' order by idingreso desc limit 1";
            System.out.println(ver_id);
            ResultSet rs = con.consulta(st, ver_id);
            if (rs.next()) {
                com.setId(rs.getInt("idingreso"));
            }
            con.cerrar(rs);
            con.cerrar(st);
        } catch (SQLException ex) {
            System.out.print(ex);
        }

        //Contar filas de tabla
        int filas = t_detalle.getRowCount();
        //Recorrer filas de tabla
        for (int j = 0; j < filas; j++) {
            art.setId_pro(Integer.parseInt(t_detalle.getValueAt(j, 0).toString()));
            art.setCan(Double.parseDouble(t_detalle.getValueAt(j, 2).toString()));
            Double precio_compra = Double.parseDouble(t_detalle.getValueAt(j, 4).toString());
            if (mon.getId() != 2) {
                art.setCos_pro(precio_compra * tc);
            } else {
                art.setCos_pro(precio_compra);
            }
            // buscar precio de prodcuto
            try {
                Statement st = con.conexion();
                String ver_pre_ven = "select precio_venta from productos where idProductos = '" + art.getId_pro() + "'";
                System.out.println(ver_pre_ven);
                ResultSet rs = con.consulta(st, ver_pre_ven);
                if (rs.next()) {
                    art.setPre_pro(rs.getDouble("precio_venta"));
                }
                con.cerrar(rs);
                con.cerrar(st);
            } catch (Exception ex) {
                System.out.print(ex);
            }
            //Registrando detalle de compra
            try {
                Statement st = con.conexion();
                String ins_det_com = "insert into detalle_ingreso Values ('" + com.getId() + "', "
                        + "'" + art.getId_pro() + "', '" + art.getCan() + "', '" + art.getCos_pro() + "')";
                System.out.println(ins_det_com);
                con.actualiza(st, ins_det_com);
                con.cerrar(st);
            } catch (Exception ex) {
                System.out.print(ex);
            }

            //Registrar Movimiento en kardex
            try {
                Statement st = con.conexion();
                String ins_kar = "insert into kardex Values (null, '" + com.getFec_com() + "', '" + art.getId_pro() + "', '" + art.getCan() + "', '" + art.getCos_pro() + "', "
                        + "'0.00', '0.00', '" + com.getSerie() + "', '" + com.getNro() + "', '" + tido.getId() + "', '" + alm.getId() + "', '" + pro.getRuc() + "',"
                        + "'" + pro.getRaz() + "', '2')";
                System.out.println(ins_kar);
                con.actualiza(st, ins_kar);
                con.cerrar(st);
            } catch (Exception ex) {
                System.out.print(ex);
            }

            //Ver Cantidad actual de Material
            try {
                Statement st = con.conexion();
                String bus_pro = "select cant_actual from productos where idProductos = '" + art.getId_pro() + "'";
                System.out.println(bus_pro);
                ResultSet rs = con.consulta(st, bus_pro);
                if (rs.next()) {
                    art.setCan_act_pro(rs.getDouble("cant_actual"));
                }
                con.cerrar(rs);
                con.cerrar(st);
            } catch (SQLException ex) {
                System.out.print(ex);
            }

            //Actualizando cantidad actual de material
            try {
                art.setCan_act_pro(art.getCan_act_pro() + art.getCan());
                Statement st = con.conexion();
                String act_pro = "update productos set cant_actual = '" + art.getCan_act_pro() + "', costo_compra = "
                        + "'" + art.getCos_pro() + "' where idProductos = '" + art.getId_pro() + "' ";
                System.out.println(act_pro);
                con.actualiza(st, act_pro);
                con.cerrar(st);
            } catch (Exception ex) {
                System.out.print(ex);
            }

            //Verificar producto en almacen
            try {
                Statement st = con.conexion();
                String ver_mat_alm = "select idProductos, cant from producto_almacen "
                        + "where idAlmacen = '" + alm.getId() + "' and idProductos = '" + art.getId_pro() + "'";
                System.out.println(ver_mat_alm);
                ResultSet rs = con.consulta(st, ver_mat_alm);
                if (rs.next()) {
                    //seleccionando cantidad
                    art.setCan_act_pro(rs.getDouble("cant"));
                    art.setCan_act_pro(art.getCan_act_pro() + art.getCan());
                    //actualizar cantidad
                    Statement st1 = con.conexion();
                    String act_mat_alm = "update producto_almacen set cant= '" + art.getCan_act_pro() + "', precio = '" + art.getPre_pro() + "' "
                            + "where idProductos = '" + art.getId_pro() + "' and idAlmacen = '" + alm.getId() + "'";
                    con.actualiza(st1, act_mat_alm);
                    con.cerrar(st1);
                } else {
                    //si producto no existe agregar
                    Statement st1 = con.conexion();
                    String add_pro_alm = "insert into producto_almacen Values ('" + art.getId_pro() + "', '" + alm.getId() + "', "
                            + "'" + art.getCan() + "', '" + art.getPre_pro() + "')";
                    con.actualiza(st1, add_pro_alm);
                    con.cerrar(st1);
                }
                con.cerrar(rs);
                con.cerrar(st);
            } catch (Exception ex) {
                System.out.print(ex);
            }

        }

        frm_ver_ingresos compra = new frm_ver_ingresos();
        ven.llamar_ventana(compra);
        this.dispose();
    }//GEN-LAST:event_btn_registrarActionPerformed

    private void txt_rucKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_rucKeyTyped
        if (txt_ruc.getText().length() == 11) {
            evt.consume();
        }
        char car = evt.getKeyChar();
        if ((car < '0' || car > '9')) {
            evt.consume();
        }
    }//GEN-LAST:event_txt_rucKeyTyped

    private void txt_serKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_serKeyTyped
        char car = evt.getKeyChar();
        if ((car < '0' || car > '9')) {
            evt.consume();
        }
    }//GEN-LAST:event_txt_serKeyTyped

    private void txt_nroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_nroKeyTyped
        char car = evt.getKeyChar();
        if ((car < '0' || car > '9')) {
            evt.consume();
        }
    }//GEN-LAST:event_txt_nroKeyTyped

    private void txt_fec_comKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_fec_comKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (txt_fec_com.getText().length() == 10) {
                txt_ruc.setEnabled(true);
                txt_ruc.requestFocus();
                btn_busp.setEnabled(true);
            }
        }
    }//GEN-LAST:event_txt_fec_comKeyPressed

    private void cbx_monKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbx_monKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            com.setFec_com(txt_fec_com.getText());
            int moneda = cbx_mon.getSelectedIndex() + 1;
            if (moneda != 2) {
                txt_tc.setText(ven.formato_tc(mon.tc_compra(ven.fechabase(com.getFec_com()), moneda)));
            } else {
                txt_tc.setText("1.000");
            }
            lbl_moneda.setText(cbx_mon.getSelectedItem().toString());
            txt_tc.setEnabled(true);
            txt_tc.requestFocus();
        }
    }//GEN-LAST:event_cbx_monKeyPressed

    private void txt_tcKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_tcKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Double tc = Double.parseDouble(txt_tc.getText());
            if (tc > 0.0) {
                jtb_compra.setSelectedIndex(1);
                txt_busqueda.setEnabled(true);
                btn_buscar_productos.setEnabled(true);
                txt_busqueda.requestFocus();
            } else {
                JOptionPane.showMessageDialog(null, "EL TIPO DE CAMBIO NO ES CORRECTO");
            }
        }
    }//GEN-LAST:event_txt_tcKeyPressed

    private void txt_busquedaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_busquedaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String texto = txt_busqueda.getText();
            if (texto.length() > 15) {
                txt_costo.setEnabled(true);
                txt_costo.requestFocus();
            }
        }
        if (evt.getKeyCode() == KeyEvent.VK_F2) {
            btn_buscar_productos.doClick();
        }
    }//GEN-LAST:event_txt_busquedaKeyPressed

    private void txt_costoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_costoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String texto = txt_costo.getText();
            if (ven.esDecimal(texto)) {
                txt_costo.setText(ven.formato_numero(Double.parseDouble(texto)));
                txt_precio.setEnabled(true);
                txt_precio.selectAll();
                txt_precio.requestFocus();
            }
        }
    }//GEN-LAST:event_txt_costoKeyPressed

    private void txt_precioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_precioKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String texto = txt_precio.getText();
            if (ven.esDecimal(texto)) {
                txt_precio.setText(ven.formato_numero(Double.parseDouble(texto)));
                double costo, tc, precio, utilidad;
                costo = Double.parseDouble(txt_costo.getText());
                tc = Double.parseDouble(txt_tc.getText());
                precio = Double.parseDouble(txt_precio.getText());
                utilidad = precio - (costo * tc);
                if (utilidad < 0) {
                    JOptionPane.showMessageDialog(null, "ERROR UTILIDAD ES NEGATIVO");
                }
                txt_utilidad.setText(ven.formato_numero(utilidad));
                txt_cantidad.setEnabled(true);
                txt_cantidad.selectAll();
                txt_cantidad.requestFocus();
            }
        }
    }//GEN-LAST:event_txt_precioKeyPressed

    private void txt_cantidadKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_cantidadKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String texto = txt_cantidad.getText();
            if (ven.esDecimal(texto)) {
                txt_cantidad.setText(ven.formato_numero(Double.parseDouble(texto)));
                btn_add.setEnabled(true);
                btn_add.requestFocus();
            }
        }
    }//GEN-LAST:event_txt_cantidadKeyPressed

    private void btn_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_addActionPerformed
        double cantidad = Double.parseDouble(txt_cantidad.getText());
        double costo = Double.parseDouble(txt_costo.getText());
        double parcial = cantidad * costo;
        Object filas[] = new Object[7];
        filas[0] = lbl_codigo.getText();
        filas[1] = txt_busqueda.getText();
        filas[2] = txt_cantidad.getText();
        filas[3] = txt_unidad_medida.getText();
        filas[4] = txt_costo.getText();
        filas[5] = ven.formato_numero(parcial);
        filas[6] = txt_precio.getText();
        detalle.addRow(filas);
        t_detalle.setModel(detalle);

        //limpiar
        txt_busqueda.setText("");
        txt_cantidad.setText("1.00");
        txt_costo.setText("");
        txt_precio.setText("");
        txt_unidad_medida.setText("");
        txt_utilidad.setText("");
        txt_cantidad.setEnabled(false);
        txt_costo.setEnabled(false);
        txt_precio.setEnabled(false);
        btn_add.setEnabled(false);

        btn_registrar.setEnabled(true);

    }//GEN-LAST:event_btn_addActionPerformed

    public static Double subtotal() {
        int totalRow = t_detalle.getRowCount();
        double suma_sub = 0.00;
        for (int i = 0; i < totalRow; i++) {
            suma_sub += Double.parseDouble(String.valueOf(t_detalle.getValueAt(i, 4))) * Double.parseDouble(String.valueOf(t_detalle.getValueAt(i, 2))) / 1.18;

        }
        return suma_sub;
    }

    public static Double igv() {
        Double igv = subtotal() * 0.18;
        return igv;
    }

    public static Double total() {
        double total = subtotal() + igv();
        return total;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_add;
    private javax.swing.JButton btn_buscar_productos;
    private javax.swing.JButton btn_busp;
    private javax.swing.JButton btn_ca;
    private javax.swing.JButton btn_cer;
    private javax.swing.JButton btn_el;
    public static javax.swing.JButton btn_registrar;
    private javax.swing.JButton btn_va;
    public static javax.swing.JComboBox cbx_alm;
    public static javax.swing.JComboBox cbx_mon;
    private javax.swing.JComboBox cbx_tido;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jtb_compra;
    public static javax.swing.JLabel lbl_codigo;
    private javax.swing.JLabel lbl_moneda;
    public static javax.swing.JTable t_detalle;
    public static javax.swing.JTextField txt_busqueda;
    public static javax.swing.JTextField txt_cantidad;
    public static javax.swing.JTextField txt_costo;
    public static javax.swing.JTextField txt_dir;
    public static javax.swing.JFormattedTextField txt_fec_com;
    private javax.swing.JTextField txt_nro;
    public static javax.swing.JTextField txt_precio;
    public static javax.swing.JTextField txt_raz;
    public static javax.swing.JTextField txt_ruc;
    private javax.swing.JTextField txt_ser;
    private javax.swing.JTextField txt_tc;
    public static javax.swing.JTextField txt_tot;
    public static javax.swing.JTextField txt_unidad_medida;
    public static javax.swing.JTextField txt_utilidad;
    // End of variables declaration//GEN-END:variables
}
