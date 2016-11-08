/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

import Clases.Cl_Almacen;
import Clases.Cl_Conectar;
import Clases.Cl_Inventario;
import Clases.Cl_Varios;
import Vistas.frm_ver_empleado;
import Vistas.frm_ver_inventarios;
import com.mxrck.autocompleter.AutoCompleterCallback;
import com.mxrck.autocompleter.TextAutoCompleter;
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import sonomusic.frm_menu;

/**
 *
 * @author luis-d
 */
public class frm_reg_inventario extends javax.swing.JInternalFrame {

    Cl_Conectar con = new Cl_Conectar();
    Cl_Varios ven = new Cl_Varios();
    Cl_Almacen alm = new Cl_Almacen();
    Cl_Inventario inv = new Cl_Inventario();
    DefaultTableModel detalle;

    public static String responsable;

    /**
     * Creates new form frm_reg_inventario
     */
    public frm_reg_inventario() {
        initComponents();
        alm.ver_tiendas(cbx_tienda);

        //mostrar nombres de columnas
        detalle = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int fila, int columna) {
                return columna == 4;
            }
        };
        detalle.addColumn("Id");
        detalle.addColumn("Descripcion");
        detalle.addColumn("Costo S/");
        detalle.addColumn("C. Act.");
        detalle.addColumn("C. Fisco");
        detalle.addColumn("Diferencia");
        t_productos.setModel(detalle);
        t_productos.getColumnModel().getColumn(0).setPreferredWidth(40);
        t_productos.getColumnModel().getColumn(1).setPreferredWidth(380);
        t_productos.getColumnModel().getColumn(2).setPreferredWidth(70);
        t_productos.getColumnModel().getColumn(3).setPreferredWidth(70);
        t_productos.getColumnModel().getColumn(4).setPreferredWidth(70);
        t_productos.getColumnModel().getColumn(5).setPreferredWidth(70);
        ven.centrar_celda(t_productos, 0);
        ven.derecha_celda(t_productos, 2);
        ven.derecha_celda(t_productos, 3);
        ven.derecha_celda(t_productos, 4);
        ven.derecha_celda(t_productos, 5);
        detalle.fireTableDataChanged();
    }

    public void cargar_productos_txt(int almacen) {
        final int id_almacen = almacen;
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
                            boolean norepite = verifica_repetido(Integer.parseInt(id_producto));
                            if (norepite == true) {
                                try {
                                    Statement st = con.conexion();
                                    String ver_producto = "select p.idProductos, p.desc_pro, p.modelo, p.serie, p.marca, pa.cant, p.cant_min, pa.precio, p.estado, c.desc_clas, "
                                            + "u.desc_und, p.grado, p.costo_compra from producto_almacen as pa inner join productos as p on pa.idProductos=p.idProductos "
                                            + "inner join clasificacion as c on p.id_clas=c.id_clas inner join und_medida as u on "
                                            + "p.idUnd_Medida=u.idUnd_Medida where pa.idAlmacen = '" + id_almacen + "' and p.idProductos = '" + id_producto + "'";
                                    System.out.println(ver_producto);
                                    ResultSet rs = con.consulta(st, ver_producto);
                                    if (rs.next()) {
                                        txt_busqueda.setText("");
                                        txt_codigo.setText(rs.getString("idProductos"));
                                        txt_descripcion.setText(rs.getString("desc_pro") + " " + rs.getString("marca") + " " + rs.getString("modelo") + " " + rs.getString("serie"));
                                        double cantidad = rs.getDouble("cant");
                                        txt_cant_actual.setText(ven.formato_numero(cantidad));
                                        txt_pventa.setText(ven.formato_numero(rs.getDouble("precio")));
                                        txt_pcompra.setText(ven.formato_numero(rs.getDouble("costo_compra")));
                                        txt_und_med.setText(rs.getString("desc_und"));
                                        txt_cant_fisico.setEnabled(true);
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
            String sql = "select pa.idProductos,p.desc_pro, p.marca, p.modelo, p.serie, pa.cant, pa.precio"
                    + " from producto_almacen as pa inner join productos as p"
                    + " on pa.idProductos=p.idProductos where pa.idAlmacen ='" + almacen + "' ";
            //+ "order by p.desc_pro asc, p.modelo asc";
            ResultSet rs = con.consulta(st, sql);
            while (rs.next()) {
                autocompletar.addItem(rs.getString("pa.idProductos") + " - " + rs.getString("p.desc_pro") + " " + rs.getString("p.marca") + " " + rs.getString("p.modelo")
                        + " -- S/ " + rs.getString("pa.precio") + " -- Cant.: " + rs.getString("pa.cant"));
            }
            con.cerrar(rs);
            con.cerrar(st);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error " + e.getLocalizedMessage());
            System.out.println(e.getLocalizedMessage());
        }
    }

    private boolean verifica_repetido(int producto) {
        boolean verificado = false;
        //estado de ingreso
        boolean ingresar = false;
        int cuenta_iguales = 0;

        //verificar fila no se repite
        int contar_filas = t_productos.getRowCount();
        if (contar_filas == 0) {
            ingresar = true;
        }

        if (contar_filas > 0) {
            for (int j = 0; j < contar_filas; j++) {
                int id_producto_fila = Integer.parseInt(t_productos.getValueAt(j, 0).toString());
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
            verificado = true;
        }
        return verificado;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cbx_tienda = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        txt_fecha = new javax.swing.JFormattedTextField();
        jLabel3 = new javax.swing.JLabel();
        btn_bus_responsable = new javax.swing.JButton();
        txt_responsable = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        cbx_tipo = new javax.swing.JComboBox();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        t_productos = new javax.swing.JTable();
        btn_cerrar = new javax.swing.JButton();
        btn_registrar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txt_busqueda = new javax.swing.JTextField();
        btn_bus_producto = new javax.swing.JButton();
        txt_cant_actual = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txt_und_med = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txt_cant_fisico = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txt_diferencia = new javax.swing.JTextField();
        btn_add_producto = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        txt_codigo = new javax.swing.JTextField();
        txt_descripcion = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txt_pventa = new javax.swing.JTextField();
        txt_pcompra = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setTitle("Inventario / Cuadre de Mercaderia");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText("Tienda:");

        cbx_tienda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cbx_tiendaKeyPressed(evt);
            }
        });

        jLabel2.setText("Fecha:");

        try {
            txt_fecha.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txt_fecha.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_fecha.setEnabled(false);
        txt_fecha.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_fechaKeyPressed(evt);
            }
        });

        jLabel3.setText("Responsable:");

        btn_bus_responsable.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/find.png"))); // NOI18N
        btn_bus_responsable.setEnabled(false);
        btn_bus_responsable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_bus_responsableActionPerformed(evt);
            }
        });

        txt_responsable.setForeground(new java.awt.Color(0, 0, 0));
        txt_responsable.setEnabled(false);

        jLabel9.setText("Tipo Inventario:");

        cbx_tipo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "INICIAL", "PERIODICO", "MUESTREO", "CORRECIONAL", "CIERRE" }));
        cbx_tipo.setEnabled(false);
        cbx_tipo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cbx_tipoKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbx_tienda, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txt_responsable)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_bus_responsable))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(cbx_tipo, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 242, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_bus_responsable, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbx_tienda, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_responsable, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbx_tipo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        t_productos.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(t_productos);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 368, Short.MAX_VALUE)
                .addContainerGap())
        );

        btn_cerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/cancel.png"))); // NOI18N
        btn_cerrar.setText("Cerrar");
        btn_cerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cerrarActionPerformed(evt);
            }
        });

        btn_registrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/accept.png"))); // NOI18N
        btn_registrar.setText("Registrar");
        btn_registrar.setEnabled(false);
        btn_registrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_registrarActionPerformed(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel4.setText("Buscar Productos:");

        txt_busqueda.setEnabled(false);
        txt_busqueda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_busquedaKeyPressed(evt);
            }
        });

        btn_bus_producto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/find.png"))); // NOI18N
        btn_bus_producto.setEnabled(false);

        txt_cant_actual.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_cant_actual.setEnabled(false);

        jLabel5.setText("Cantidad Actual:");

        txt_und_med.setText("Und. Med.");

        jLabel7.setText("Cantidad Fisico:");

        txt_cant_fisico.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_cant_fisico.setEnabled(false);
        txt_cant_fisico.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_cant_fisicoKeyPressed(evt);
            }
        });

        jLabel8.setText("Diferencia:");

        txt_diferencia.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_diferencia.setEnabled(false);
        txt_diferencia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_diferenciaKeyPressed(evt);
            }
        });

        btn_add_producto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/add.png"))); // NOI18N
        btn_add_producto.setText("Agregar");
        btn_add_producto.setEnabled(false);
        btn_add_producto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_add_productoActionPerformed(evt);
            }
        });
        btn_add_producto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btn_add_productoKeyPressed(evt);
            }
        });

        jLabel6.setText("Codigo Producto:");

        txt_codigo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_codigo.setEnabled(false);

        txt_descripcion.setEnabled(false);

        jLabel10.setText("Precio Vta/Cpa");

        txt_pventa.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_pventa.setEnabled(false);

        txt_pcompra.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_pcompra.setEnabled(false);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel8)
                    .addComponent(jLabel4)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(txt_busqueda)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_bus_producto))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txt_codigo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_diferencia, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_pventa, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_descripcion)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(jLabel7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txt_cant_fisico, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(txt_pcompra, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txt_cant_actual, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(109, 109, 109)
                                        .addComponent(btn_add_producto))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txt_und_med)))))))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_busqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_bus_producto, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_codigo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_descripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_cant_actual, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_und_med, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(txt_pventa, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_pcompra, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_diferencia, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_add_producto, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_cant_fisico, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/bin_closed.png"))); // NOI18N
        jButton6.setText("Eliminar");
        jButton6.setEnabled(false);

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/asterisk_orange.png"))); // NOI18N
        jButton7.setText("Modificar");
        jButton7.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_registrar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_cerrar))
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_cerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_registrar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbx_tiendaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbx_tiendaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cbx_tienda.setEnabled(false);
            txt_fecha.setEnabled(true);
            alm.setId(cbx_tienda.getSelectedIndex() + 1);
            cargar_productos_txt(alm.getId());
            txt_fecha.setText(ven.fechaformateada(ven.getFechaActual()));
            txt_fecha.requestFocus();

        }
    }//GEN-LAST:event_cbx_tiendaKeyPressed

    private void txt_fechaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_fechaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (txt_fecha.getText().length() == 10) {
                btn_bus_responsable.setEnabled(true);
                btn_bus_responsable.requestFocus();
            }
        }
    }//GEN-LAST:event_txt_fechaKeyPressed

    private void btn_bus_responsableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_bus_responsableActionPerformed
        frm_ver_empleado empleado = new frm_ver_empleado();
        frm_ver_empleado.ventana = "inventario";
        ven.llamar_ventana(empleado);
    }//GEN-LAST:event_btn_bus_responsableActionPerformed

    private void txt_busquedaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_busquedaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (txt_busqueda.getText().length() > 20) {
                txt_busqueda.setText("");
                txt_cant_fisico.requestFocus();
            }
        }
    }//GEN-LAST:event_txt_busquedaKeyPressed

    private void txt_cant_fisicoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_cant_fisicoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String texto = txt_cant_fisico.getText();
            double fisico = 1;
            if (texto != null) {
                if (ven.esDecimal(texto)) {
                    fisico = Double.parseDouble(texto);
                    txt_cant_fisico.setText(ven.formato_numero(fisico));
                } else {
                    txt_cant_fisico.setText(ven.formato_numero(fisico));
                }
                double actual = Double.parseDouble(txt_cant_actual.getText());
                double diferencia = fisico - actual;
                txt_diferencia.setText(ven.formato_numero(diferencia));
                btn_add_producto.setEnabled(true);
                btn_add_producto.requestFocus();
            } else {
                txt_cant_fisico.setText(ven.formato_numero(fisico));
            }
        }
    }//GEN-LAST:event_txt_cant_fisicoKeyPressed

    private void txt_diferenciaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_diferenciaKeyPressed
    }//GEN-LAST:event_txt_diferenciaKeyPressed

    private void btn_add_productoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btn_add_productoKeyPressed

    }//GEN-LAST:event_btn_add_productoKeyPressed

    private void cbx_tipoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbx_tipoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txt_busqueda.setEnabled(true);
            btn_bus_producto.setEnabled(true);
            txt_busqueda.requestFocus();
        }
    }//GEN-LAST:event_cbx_tipoKeyPressed

    private void btn_registrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_registrarActionPerformed
        btn_registrar.setEnabled(false);
        Calendar calendario = Calendar.getInstance();
        inv.setAnio(calendario.get(Calendar.YEAR));
        inv.setAlmacen(cbx_tienda.getSelectedIndex() + 1);
        inv.setId(inv.ultimo_ingreso());
        inv.setFecha_ejecucion(ven.fechabase(txt_fecha.getText()));
        inv.setResponsable(responsable);
        inv.setTipo(cbx_tipo.getSelectedItem().toString());
        inv.setUsuario(frm_menu.usu.getNick());

        boolean registrar = inv.crear();

        if (registrar == true) {
            //detalle de inventario
            int nro_filas = t_productos.getRowCount();

            if (nro_filas > 0) {
                for (int i = 0; i < nro_filas; i++) {
                    int producto = Integer.parseInt(t_productos.getValueAt(i, 0).toString());
                    double precio = Double.parseDouble(t_productos.getValueAt(i, 2).toString());
                    double cant_actual = Double.parseDouble(t_productos.getValueAt(i, 3).toString());
                    double cant_fisico = Double.parseDouble(t_productos.getValueAt(i, 4).toString());

                    //registrar detalle
                    try {
                        Statement st = con.conexion();
                        String i_detalle = "insert into detalle_inventario values ('" + inv.getId() + "', '" + inv.getAnio() + "', '" + inv.getAlmacen() + "', "
                                + "'" + producto + "', '" + cant_actual + "', '" + cant_fisico + "')";
                        System.out.println(i_detalle);
                        con.actualiza(st, i_detalle);
                        con.cerrar(st);
                    } catch (Exception e) {
                        System.out.println(e.getLocalizedMessage());
                    }

                    //cuadra productos en almacen
                    try {
                        Statement st = con.conexion();
                        String a_producto = "update producto_almacen set cant = '" + cant_fisico + "' where idproductos = '" + producto + "' and idalmacen = '" + inv.getAlmacen() + "'";
                        System.out.println(a_producto);
                        con.actualiza(st, a_producto);
                        con.cerrar(st);
                    } catch (Exception e) {
                        System.out.println(e.getLocalizedMessage());
                    }

                    //ingresar al kardex (productos en el aire)
                    //3 - 5
                    double diferencia = cant_fisico - cant_actual;
                    double cant_salida = 0;
                    double cant_ingreso = 0;
                    int tipo_documento = 5;
                    if (diferencia > 0) {
                        cant_salida = 0;
                        cant_ingreso = diferencia;
                    }
                    if (diferencia < 0) {
                        diferencia = cant_actual - cant_fisico;
                        cant_salida = diferencia;
                        cant_ingreso = 0;
                    }

                    

                    try {
                        Statement st = con.conexion();
                        String ins_kardex = "insert into kardex Values (null, '" + inv.getFecha_ejecucion() + "', '" + producto + "', '" + cant_ingreso + "', '" + precio + "', '"
                                + cant_salida + "', '" + precio + "','0', '0', '" + tipo_documento + "',"
                                + " '" + inv.getAlmacen() + "','00000000000', 'CUADRE INVENTARIO','13')";
                        System.out.println(ins_kardex);
                        con.actualiza(st, ins_kardex);
                        con.cerrar(st);
                    } catch (Exception ex) {
                        System.err.print("Error en: " + ex.getLocalizedMessage());
                    }
                    
                    btn_cerrar.doClick();
                }
            }
        }
    }//GEN-LAST:event_btn_registrarActionPerformed

    private void btn_add_productoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_add_productoActionPerformed
        //agregar fila
        Object fila[] = new Object[6];
        fila[0] = txt_codigo.getText();
        fila[1] = txt_descripcion.getText();
        fila[2] = txt_pcompra.getText();
        fila[3] = txt_cant_actual.getText();
        fila[4] = txt_cant_fisico.getText();
        fila[5] = txt_diferencia.getText();
        detalle.addRow(fila);
        t_productos.setModel(detalle);

        //limpiar campos
        txt_codigo.setText("");
        txt_descripcion.setText("");
        txt_pventa.setText("");
        txt_pcompra.setText("");
        txt_cant_actual.setText("");
        txt_cant_fisico.setText("");
        txt_diferencia.setText("");
        txt_und_med.setText("Und. Med.");
        txt_cant_fisico.setEnabled(false);
        btn_add_producto.setEnabled(false);
        txt_busqueda.setText("");
        txt_busqueda.requestFocus();

        btn_registrar.setEnabled(true);
    }//GEN-LAST:event_btn_add_productoActionPerformed

    private void btn_cerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cerrarActionPerformed
        frm_ver_inventarios inventario = new frm_ver_inventarios();
        ven.llamar_ventana(inventario);
        this.dispose();
    }//GEN-LAST:event_btn_cerrarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_add_producto;
    public static javax.swing.JButton btn_bus_producto;
    private javax.swing.JButton btn_bus_responsable;
    private javax.swing.JButton btn_cerrar;
    private javax.swing.JButton btn_registrar;
    private javax.swing.JComboBox cbx_tienda;
    public static javax.swing.JComboBox cbx_tipo;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
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
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable t_productos;
    public static javax.swing.JTextField txt_busqueda;
    private javax.swing.JTextField txt_cant_actual;
    private javax.swing.JTextField txt_cant_fisico;
    private javax.swing.JTextField txt_codigo;
    private javax.swing.JTextField txt_descripcion;
    private javax.swing.JTextField txt_diferencia;
    private javax.swing.JFormattedTextField txt_fecha;
    private javax.swing.JTextField txt_pcompra;
    private javax.swing.JTextField txt_pventa;
    public static javax.swing.JTextField txt_responsable;
    private javax.swing.JLabel txt_und_med;
    // End of variables declaration//GEN-END:variables
}
