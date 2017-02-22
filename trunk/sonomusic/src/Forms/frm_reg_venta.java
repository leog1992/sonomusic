package Forms;

import Clases.Cl_Cliente;
import Clases.Cl_Conectar;
import Clases.Cl_Entidad;
import Clases.Cl_Hilo_Imprime;
import Clases.Cl_Moneda;
import Clases.Cl_Ofertas;
import Clases.Cl_Pedido;
import Clases.Cl_Productos;
import Clases.Cl_Tipo_Documentos;
import Clases.Cl_Tipo_Pago;
import Clases.Cl_Usuario;
import Clases.Cl_Varios;
import com.mxrck.autocompleter.AutoCompleterCallback;
import com.mxrck.autocompleter.TextAutoCompleter;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import nicon.notify.core.Notification;
import nicon.notify.gui.desktopNotify.DesktopNotify;
import org.json.simple.parser.ParseException;
import sonomusic.frm_menu;

/**
 *
 * @author pc
 */
public class frm_reg_venta extends javax.swing.JInternalFrame {

    Cl_Varios ven = new Cl_Varios();
    Cl_Conectar con = new Cl_Conectar();
    Cl_Ofertas ofe = new Cl_Ofertas();
    Cl_Usuario usu = new Cl_Usuario();
    Cl_Cliente cli = new Cl_Cliente();
    Cl_Pedido ped = new Cl_Pedido();
    Cl_Moneda mon = new Cl_Moneda();
    public static Cl_Productos pro = new Cl_Productos();
    Cl_Tipo_Pago tipa = new Cl_Tipo_Pago();
    Cl_Tipo_Documentos tido = new Cl_Tipo_Documentos();
    public static DefaultTableModel detalle;
    Integer i;
    int ida;
    double total_new;
    double deuda_actual;
    double vuelto;
    double tc;
    double monto_tarjeta;
    double comision_tarjeta;

    /**
     * Creates new form frm_reg_venta
     */
    public frm_reg_venta() {
        initComponents();

        //cargar monedas        
        String ver_mon = "select nombre from moneda order by idmoneda asc";
        ver_moneda(ver_mon);

        //establecer fecha actual
        txt_fec.setText(ven.fechaformateada(ven.getFechaActual()));

        //mostrar nombres de columnas
        detalle = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int fila, int columna) {
                return columna == 3 || columna == 5;
            }
        };
        detalle.addColumn("Id");
        detalle.addColumn("Descripcion");
        detalle.addColumn("C. Act.");
        detalle.addColumn("Cant.");
        detalle.addColumn("Und. Med");
        detalle.addColumn("Precio");
        detalle.addColumn("Parcial");
        t_detalle.setModel(detalle);
        t_detalle.getColumnModel().getColumn(0).setPreferredWidth(40);
        t_detalle.getColumnModel().getColumn(1).setPreferredWidth(380);
        t_detalle.getColumnModel().getColumn(2).setPreferredWidth(30);
        t_detalle.getColumnModel().getColumn(3).setPreferredWidth(30);
        t_detalle.getColumnModel().getColumn(4).setPreferredWidth(70);
        t_detalle.getColumnModel().getColumn(5).setPreferredWidth(50);
        t_detalle.getColumnModel().getColumn(6).setPreferredWidth(80);
        ven.derecha_celda(t_detalle, 0);
        ven.derecha_celda(t_detalle, 2);
        ven.derecha_celda(t_detalle, 3);
        ven.centrar_celda(t_detalle, 4);
        ven.derecha_celda(t_detalle, 5);
        ven.derecha_celda(t_detalle, 6);
        detalle.fireTableDataChanged();

        //cargar tipo documento
        String query = "select * from tipo_doc";
        ver_tipodoc(query);
        //mostrar vendedores activos
        ver_vendedores("" + frm_menu.alm.getId());
        int contar_cbx = cbx_vendedor.getItemCount();
        int repetido = 0;
        for (int j = 0; j < contar_cbx; j++) {
            if (cbx_vendedor.getItemAt(j).toString().equals(frm_menu.usu.getNick())) {
                repetido++;
            }
        }
        if (repetido == 0) {
            cbx_vendedor.addItem(frm_menu.usu.getNick());
        }
        cbx_vendedor.setSelectedItem(frm_menu.usu.getNick());
        cbx_vendedor.requestFocus();
    }
    //fin del constructor

    private void ver_tipodoc(String query) {
        try {
            Statement st = con.conexion();
            ResultSet rs = con.consulta(st, query);

            while (rs.next()) {
                String fila;
                fila = rs.getString("desc_tipd");
                cbx_documento_venta.addItem(fila);
            }
            con.cerrar(st);
            con.cerrar(rs);
        } catch (SQLException e) {
            System.out.print(e);
        }
    }

    private void ver_vendedores(String id_alm) {
        try {
            //mostrar vendedores segun cargo x prioridad
            String query = "select dni from empleados where idAlmacen = '" + id_alm + "' "
                    + "and est_per = '1' order by idCargo asc ";
            Statement st = con.conexion();
            ResultSet rs = con.consulta(st, query);

            while (rs.next()) {
                String fila;
                fila = rs.getString("dni");
                cbx_vendedor.addItem(fila);
            }
            con.cerrar(st);
            con.cerrar(rs);
            cbx_vendedor.setSelectedItem(frm_menu.usu.getNick());
        } catch (SQLException e) {
            System.out.print(e);
        }
    }

    private void ver_moneda(String query) {
        try {
            Statement st = con.conexion();
            ResultSet rs = con.consulta(st, query);

            while (rs.next()) {
                String fila;
                fila = rs.getString("nombre");
                cbx_jd_moneda.addItem(fila);
            }
            con.cerrar(st);
            con.cerrar(rs);
        } catch (SQLException e) {
            System.out.print(e);
        }
    }

    public static double subtotal() {
        double sub_total;
        int totalRow = t_detalle.getRowCount();
        double suma_parcial = 0.00;
        for (int i = 0; i < totalRow; i++) {
            double precio = Double.parseDouble(String.valueOf(t_detalle.getValueAt(i, 5)));
            double cantidad = Double.parseDouble(String.valueOf(t_detalle.getValueAt(i, 3)));
            suma_parcial += precio * cantidad;
        }
        sub_total = suma_parcial / 1.18;
        return sub_total;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        j_productos = new javax.swing.JDialog();
        jLabel6 = new javax.swing.JLabel();
        txt_busqueda = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        t_productos = new javax.swing.JTable();
        btn_cer = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        j_fin_venta = new javax.swing.JDialog();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        txt_jd_documento = new javax.swing.JTextField();
        txt_jd_serie = new javax.swing.JTextField();
        txt_jd_numero = new javax.swing.JTextField();
        lbl_total = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        cbx_jd_moneda = new javax.swing.JComboBox();
        jLabel25 = new javax.swing.JLabel();
        txt_com = new javax.swing.JTextField();
        chk_incluir = new javax.swing.JCheckBox();
        txt_jd_efectivo = new javax.swing.JTextField();
        txt_jd_tarjeta = new javax.swing.JTextField();
        txt_jd_vale = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        txt_jd_codigo_vale = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        txt_jd_tipo_venta = new javax.swing.JTextField();
        txt_jd_deuda_actual = new javax.swing.JTextField();
        txt_jd_vuelto = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        txt_jd_suma_pago = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        btn_jd_cerrar = new javax.swing.JButton();
        btn_jd_registrar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        t_detalle = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        txt_subt = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txt_igv = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lbl_tot = new javax.swing.JLabel();
        btn_reg = new javax.swing.JButton();
        btn_clo = new javax.swing.JButton();
        btn_cam_can = new javax.swing.JButton();
        btn_eli = new javax.swing.JButton();
        btn_add_pro = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        cbx_documento_venta = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblcod_alm1 = new javax.swing.JLabel();
        cbx_alm = new javax.swing.JLabel();
        txt_fec = new javax.swing.JFormattedTextField();
        jLabel1 = new javax.swing.JLabel();
        cbx_tipo_venta = new javax.swing.JComboBox();
        txt_nro_doc = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txt_nom = new javax.swing.JTextField();
        btn_cpre = new javax.swing.JButton();
        txt_buscar_producto = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        cbx_vendedor = new javax.swing.JComboBox();
        txt_vend = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();

        jLabel6.setForeground(new java.awt.Color(212, 2, 2));
        jLabel6.setText("Buscar:");

        txt_busqueda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_busquedaKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_busquedaKeyTyped(evt);
            }
        });

        jLabel13.setText("encontrados");

        jScrollPane2.setBackground(new java.awt.Color(254, 254, 254));

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
        t_productos.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        t_productos.setRowHeight(20);
        t_productos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                t_productosMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                t_productosMousePressed(evt);
            }
        });
        t_productos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                t_productosKeyPressed(evt);
            }
        });
        jScrollPane2.setViewportView(t_productos);

        btn_cer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/cancel.png"))); // NOI18N
        btn_cer.setText("Cerrar");
        btn_cer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cerActionPerformed(evt);
            }
        });

        jLabel14.setText("0");

        jLabel28.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        jLabel28.setText("Esc - Limpiar Busqueda");

        jLabel29.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        jLabel29.setText("Enter - Busca / Seleccionar Producto");

        jLabel30.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        jLabel30.setText("Flecha Arriba y Abajo - Subir y Bajar en Cuadro");

        javax.swing.GroupLayout j_productosLayout = new javax.swing.GroupLayout(j_productos.getContentPane());
        j_productos.getContentPane().setLayout(j_productosLayout);
        j_productosLayout.setHorizontalGroup(
            j_productosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(j_productosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(j_productosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(j_productosLayout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_busqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(62, 62, 62)
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 433, Short.MAX_VALUE)
                        .addComponent(btn_cer))
                    .addGroup(j_productosLayout.createSequentialGroup()
                        .addComponent(jLabel28)
                        .addGap(87, 87, 87)
                        .addComponent(jLabel30)
                        .addGap(70, 70, 70)
                        .addComponent(jLabel29)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        j_productosLayout.setVerticalGroup(
            j_productosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(j_productosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(j_productosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_busqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(btn_cer, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 383, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(j_productosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(jLabel30)
                    .addComponent(jLabel29))
                .addContainerGap())
        );

        j_fin_venta.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        j_fin_venta.setTitle("Pagar Venta");

        jLabel23.setForeground(new java.awt.Color(255, 0, 51));
        jLabel23.setText("Total");

        jLabel24.setForeground(new java.awt.Color(255, 0, 51));
        jLabel24.setText("Documento:");

        txt_jd_documento.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_jd_documento.setFocusable(false);

        txt_jd_serie.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_jd_serie.setFocusable(false);

        txt_jd_numero.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_jd_numero.setFocusable(false);

        lbl_total.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        lbl_total.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_total.setText("S/. 1,888.00");

        jLabel15.setForeground(new java.awt.Color(255, 0, 51));
        jLabel15.setText("Moneda:");

        cbx_jd_moneda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cbx_jd_monedaKeyPressed(evt);
            }
        });

        jLabel25.setForeground(new java.awt.Color(255, 0, 51));
        jLabel25.setText("Comision 5%");

        txt_com.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_com.setText("0.00");
        txt_com.setFocusable(false);

        chk_incluir.setBackground(new java.awt.Color(255, 255, 255));
        chk_incluir.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        chk_incluir.setText("Incluir 5%");
        chk_incluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chk_incluirActionPerformed(evt);
            }
        });

        txt_jd_efectivo.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_jd_efectivo.setEnabled(false);
        txt_jd_efectivo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_jd_efectivoKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_jd_efectivoKeyTyped(evt);
            }
        });

        txt_jd_tarjeta.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_jd_tarjeta.setEnabled(false);
        txt_jd_tarjeta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_jd_tarjetaKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_jd_tarjetaKeyTyped(evt);
            }
        });

        txt_jd_vale.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_jd_vale.setText("0.00");
        txt_jd_vale.setFocusable(false);

        jLabel18.setForeground(new java.awt.Color(255, 0, 51));
        jLabel18.setText("Tarjeta:");

        jLabel17.setForeground(new java.awt.Color(255, 0, 51));
        jLabel17.setText("Efectivo:");

        jLabel19.setForeground(new java.awt.Color(255, 0, 51));
        jLabel19.setText("Vale:");

        txt_jd_codigo_vale.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_jd_codigo_vale.setEnabled(false);
        txt_jd_codigo_vale.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_jd_codigo_valeKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_jd_codigo_valeKeyTyped(evt);
            }
        });

        jLabel26.setForeground(new java.awt.Color(255, 0, 51));
        jLabel26.setText("Tipo de Venta:");

        txt_jd_tipo_venta.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_jd_tipo_venta.setFocusable(false);
        txt_jd_tipo_venta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_jd_tipo_ventaKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_jd_tipo_ventaKeyTyped(evt);
            }
        });

        txt_jd_deuda_actual.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_jd_deuda_actual.setText("0.00");
        txt_jd_deuda_actual.setFocusable(false);

        txt_jd_vuelto.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_jd_vuelto.setText("0.00");
        txt_jd_vuelto.setFocusable(false);

        jLabel22.setForeground(new java.awt.Color(255, 0, 51));
        jLabel22.setText("Deuda Actual:");

        txt_jd_suma_pago.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_jd_suma_pago.setText("0.00");
        txt_jd_suma_pago.setFocusable(false);

        jLabel21.setForeground(new java.awt.Color(255, 0, 51));
        jLabel21.setText("Vuelto:");

        jLabel20.setForeground(new java.awt.Color(255, 0, 51));
        jLabel20.setText("Suma Pago:");

        btn_jd_cerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/cancel.png"))); // NOI18N
        btn_jd_cerrar.setText("Cerrar");
        btn_jd_cerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_jd_cerrarActionPerformed(evt);
            }
        });

        btn_jd_registrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/accept.png"))); // NOI18N
        btn_jd_registrar.setText("Registrar");
        btn_jd_registrar.setEnabled(false);
        btn_jd_registrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_jd_registrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btn_jd_registrar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_jd_cerrar))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel25, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbx_jd_moneda, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_jd_efectivo, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txt_jd_tarjeta, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(chk_incluir))
                            .addComponent(txt_com, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txt_jd_vale, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_jd_codigo_vale, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel22, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel26, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(23, 23, 23)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_jd_tipo_venta, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_jd_suma_pago, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_jd_vuelto, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_jd_deuda_actual, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbx_jd_moneda, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_jd_tipo_venta, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_jd_efectivo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_jd_tarjeta, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(chk_incluir))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_com, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_jd_vale, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_jd_codigo_vale, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_jd_registrar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_jd_cerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_jd_suma_pago, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_jd_vuelto, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel22)
                            .addComponent(txt_jd_deuda_actual, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        javax.swing.GroupLayout j_fin_ventaLayout = new javax.swing.GroupLayout(j_fin_venta.getContentPane());
        j_fin_venta.getContentPane().setLayout(j_fin_ventaLayout);
        j_fin_ventaLayout.setHorizontalGroup(
            j_fin_ventaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(j_fin_ventaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(j_fin_ventaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(j_fin_ventaLayout.createSequentialGroup()
                        .addComponent(jLabel23)
                        .addGap(141, 141, 141)
                        .addComponent(jLabel24)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_jd_documento, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_jd_serie, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_jd_numero, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(j_fin_ventaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(lbl_total, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        j_fin_ventaLayout.setVerticalGroup(
            j_fin_ventaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(j_fin_ventaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(j_fin_ventaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_jd_documento, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_jd_serie, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_jd_numero, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_total, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        setBackground(new java.awt.Color(254, 254, 254));
        setClosable(true);
        setTitle("Registro de Venta");
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameActivated(evt);
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
            }
        });
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        jScrollPane1.setBackground(new java.awt.Color(254, 254, 254));
        jScrollPane1.setFocusable(false);

        t_detalle.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5"
            }
        ));
        t_detalle.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        t_detalle.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                t_detalleMouseClicked(evt);
            }
        });
        t_detalle.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                t_detalleFocusLost(evt);
            }
        });
        t_detalle.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                t_detalleKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(t_detalle);
        if (t_detalle.getColumnModel().getColumnCount() > 0) {
            t_detalle.getColumnModel().getColumn(0).setPreferredWidth(10);
            t_detalle.getColumnModel().getColumn(1).setPreferredWidth(350);
            t_detalle.getColumnModel().getColumn(2).setPreferredWidth(30);
            t_detalle.getColumnModel().getColumn(3).setPreferredWidth(20);
            t_detalle.getColumnModel().getColumn(4).setPreferredWidth(50);
        }

        jLabel2.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel2.setForeground(java.awt.Color.red);
        jLabel2.setText("Sub Total:");
        jLabel2.setFocusable(false);

        txt_subt.setEditable(false);
        txt_subt.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_subt.setFocusable(false);
        txt_subt.setPreferredSize(new java.awt.Dimension(50, 20));

        jLabel7.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel7.setForeground(java.awt.Color.red);
        jLabel7.setText("IGV");
        jLabel7.setFocusable(false);

        txt_igv.setEditable(false);
        txt_igv.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_igv.setFocusable(false);
        txt_igv.setPreferredSize(new java.awt.Dimension(50, 20));

        jLabel8.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel8.setForeground(java.awt.Color.red);
        jLabel8.setText("Total:");
        jLabel8.setFocusable(false);

        jLabel9.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel9.setForeground(java.awt.Color.red);
        jLabel9.setText("Detalle de Venta:");
        jLabel9.setFocusable(false);

        lbl_tot.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lbl_tot.setForeground(java.awt.Color.red);
        lbl_tot.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_tot.setText("S/. 0.00");
        lbl_tot.setFocusable(false);
        lbl_tot.setPreferredSize(new java.awt.Dimension(120, 52));

        btn_reg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/add.png"))); // NOI18N
        btn_reg.setText("Registrar");
        btn_reg.setEnabled(false);
        btn_reg.setFocusable(false);
        btn_reg.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_reg.setMargin(new java.awt.Insets(2, 2, 2, 2));
        btn_reg.setPreferredSize(new java.awt.Dimension(85, 25));
        btn_reg.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        btn_reg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_regActionPerformed(evt);
            }
        });

        btn_clo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/cancel.png"))); // NOI18N
        btn_clo.setText("Cancelar Venta");
        btn_clo.setFocusable(false);
        btn_clo.setMargin(new java.awt.Insets(2, 2, 2, 2));
        btn_clo.setPreferredSize(new java.awt.Dimension(85, 25));
        btn_clo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cloActionPerformed(evt);
            }
        });

        btn_cam_can.setText("Cambiar Cantidad");
        btn_cam_can.setEnabled(false);
        btn_cam_can.setFocusable(false);
        btn_cam_can.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cam_canActionPerformed(evt);
            }
        });

        btn_eli.setText("Eliminar Producto");
        btn_eli.setEnabled(false);
        btn_eli.setFocusable(false);
        btn_eli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_eliActionPerformed(evt);
            }
        });

        btn_add_pro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/find.png"))); // NOI18N
        btn_add_pro.setText("Agregar Producto (F2)");
        btn_add_pro.setEnabled(false);
        btn_add_pro.setFocusable(false);
        btn_add_pro.setMargin(new java.awt.Insets(2, 2, 2, 2));
        btn_add_pro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_add_proActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(254, 254, 254));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setFocusable(false);

        cbx_documento_venta.setEnabled(false);
        cbx_documento_venta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cbx_documento_ventaKeyPressed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel5.setForeground(java.awt.Color.red);
        jLabel5.setText("Tipo de Doc:");
        jLabel5.setFocusable(false);

        jLabel4.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel4.setForeground(java.awt.Color.red);
        jLabel4.setText("Fecha de Venta:");
        jLabel4.setFocusable(false);

        lblcod_alm1.setText("000");

        cbx_alm.setText("-");

        try {
            txt_fec.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txt_fec.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_fec.setEnabled(false);
        txt_fec.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_fecKeyPressed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel1.setForeground(java.awt.Color.red);
        jLabel1.setText("Tipo Venta:");

        cbx_tipo_venta.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "VENTA", "SEPARACION" }));
        cbx_tipo_venta.setEnabled(false);
        cbx_tipo_venta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cbx_tipo_ventaKeyPressed(evt);
            }
        });

        txt_nro_doc.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_nro_doc.setEnabled(false);
        txt_nro_doc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_nro_docKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_nro_docKeyTyped(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel10.setForeground(java.awt.Color.red);
        jLabel10.setText("D.N.I / R.U.C");

        jLabel12.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel12.setForeground(java.awt.Color.red);
        jLabel12.setText("Nombre:");

        txt_nom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_nomActionPerformed(evt);
            }
        });
        txt_nom.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_nomKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cbx_documento_venta, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_nro_doc))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(cbx_tipo_venta, 0, 283, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_fec, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txt_nom))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbx_documento_venta, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_fec, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbx_tipo_venta, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_nro_doc, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_nom, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btn_cpre.setText("Cambiar Precio");
        btn_cpre.setEnabled(false);
        btn_cpre.setFocusable(false);
        btn_cpre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cpreActionPerformed(evt);
            }
        });

        txt_buscar_producto.setEnabled(false);
        txt_buscar_producto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_buscar_productoKeyPressed(evt);
            }
        });

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel3.setForeground(java.awt.Color.red);
        jLabel3.setText("Vendedor:");

        cbx_vendedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cbx_vendedorKeyPressed(evt);
            }
        });

        txt_vend.setEditable(false);
        txt_vend.setFocusable(false);

        jLabel11.setFont(new java.awt.Font("DialogInput", 0, 11)); // NOI18N
        jLabel11.setText("F3 - Realizar Venta");

        jLabel16.setFont(new java.awt.Font("DialogInput", 0, 11)); // NOI18N
        jLabel16.setText("F1 - Buscar Productos");

        jLabel27.setFont(new java.awt.Font("DialogInput", 0, 11)); // NOI18N
        jLabel27.setText("F2 - Buscar Producto en otra ventana");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btn_cam_can)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_cpre)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btn_eli))
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_buscar_producto)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_add_pro, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btn_reg, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_clo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel2))
                                .addGap(26, 26, 26)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txt_subt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txt_igv, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(lbl_tot, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbx_vendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_vend))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addGap(81, 81, 81)
                        .addComponent(jLabel27)
                        .addGap(95, 95, 95)
                        .addComponent(jLabel11)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel16)
                    .addComponent(jLabel27))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbx_vendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_vend, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(btn_add_pro, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_buscar_producto, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_cam_can, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_cpre, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_eli, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_subt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_igv, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbl_tot, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_reg, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btn_clo, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_cloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cloActionPerformed
        this.dispose();
    }//GEN-LAST:event_btn_cloActionPerformed

    private void btn_add_proActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_add_proActionPerformed
        ida = frm_menu.alm.getId();
        j_productos.setModal(true);
        j_productos.setSize(1000, 450);
        j_productos.setLocationRelativeTo(null);
        String query = "select p.idProductos, p.desc_pro, p.modelo, p.serie, p.marca, pa.cant, p.cant_min, pa.precio, p.estado, c.desc_clas, "
                + "u.desc_und, p.grado from producto_almacen as pa inner join productos as p on pa.idProductos=p.idProductos "
                + "inner join clasificacion as c on p.id_clas=c.id_clas inner join und_medida as u on "
                + "p.idUnd_Medida=u.idUnd_Medida where pa.idAlmacen = '" + frm_menu.alm.getId() + "' order by p.desc_pro asc limit 0";
        pro.mostrar_productos(query, t_productos);
        j_productos.setVisible(true);
    }//GEN-LAST:event_btn_add_proActionPerformed


    private void btn_regActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_regActionPerformed
//        btn_reg.setEnabled(false);
        j_fin_venta.setModal(true);
        j_fin_venta.setTitle("PAGO DE VENTA");
        j_fin_venta.setSize(590, 380);
        j_fin_venta.setLocationRelativeTo(null);
        txt_jd_documento.setText(cbx_documento_venta.getSelectedItem().toString());
        int id_cbx_documentos = cbx_documento_venta.getSelectedIndex() + 1;
        tido.setSerie(tido.ver_ser(id_cbx_documentos, frm_menu.alm.getId()));
        tido.setNro(tido.ver_num(id_cbx_documentos, frm_menu.alm.getId()));
        txt_jd_serie.setText(tido.getSerie() + "");
        txt_jd_numero.setText(tido.getNro() + "");
        lbl_total.setText("S/. " + ven.formato_totales(subtotal() * 1.18));
        txt_jd_tipo_venta.setText(cbx_tipo_venta.getSelectedItem().toString());
        cbx_jd_moneda.setSelectedIndex(1);
        cbx_jd_moneda.requestFocus();
        j_fin_venta.setVisible(true);
    }//GEN-LAST:event_btn_regActionPerformed

    void calcular_total() {
        txt_subt.setText(ven.formato_numero(subtotal()));
        txt_igv.setText(ven.formato_numero(subtotal() * 0.18));
        lbl_tot.setText("S/. " + ven.formato_numero(subtotal() * 1.18));
        txt_buscar_producto.setText("");
        txt_buscar_producto.requestFocus();
    }
    private void btn_cam_canActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cam_canActionPerformed
        String texto = JOptionPane.showInputDialog("Ingrese Cantidad");
        if (texto != null) {
            if (ven.esDecimal(texto)) {
                double cantidad = Double.parseDouble(texto);
                t_detalle.setValueAt(ven.formato_numero(cantidad), i, 3);
                calcular_total();
            }
        }

    }//GEN-LAST:event_btn_cam_canActionPerformed

    private void btn_cpreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cpreActionPerformed
        String texto = JOptionPane.showInputDialog("Ingrese Precio");
        if (texto != null) {
            if (ven.esDecimal(texto)) {
                double precio = Double.parseDouble(texto);

                try {
                    Statement st = con.conexion();
                    String pre_com = "select costo_compra from productos where idProductos='" + t_detalle.getValueAt(i, 0) + "'";
                    ResultSet rs = con.consulta(st, pre_com);
                    if (rs.next()) {
                        if (precio > rs.getDouble("costo_compra")) {
                            t_detalle.setValueAt(ven.formato_numero(precio), i, 5);
                            calcular_total();
                        } else {
                            JOptionPane.showMessageDialog(null, "No se puede establecer ese precio");
                            txt_buscar_producto.requestFocus();
                        }
                    }
                    con.cerrar(st);
                    con.cerrar(rs);
                } catch (SQLException | HeadlessException e) {
                    JOptionPane.showMessageDialog(null, "Error " + e.getLocalizedMessage());
                }
            }
        }
    }//GEN-LAST:event_btn_cpreActionPerformed

    private void btn_eliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_eliActionPerformed
        detalle.removeRow(i);
        calcular_total();
    }//GEN-LAST:event_btn_eliActionPerformed

    private void t_detalleFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_t_detalleFocusLost
        btn_cam_can.setEnabled(false);
        btn_cpre.setEnabled(false);
        btn_eli.setEnabled(false);
    }//GEN-LAST:event_t_detalleFocusLost

    private void btn_loadKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btn_loadKeyPressed

    }//GEN-LAST:event_btn_loadKeyPressed

    private void btn_loadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_loadActionPerformed

    }//GEN-LAST:event_btn_loadActionPerformed

    private void cbx_documento_ventaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbx_documento_ventaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            switch (cbx_documento_venta.getSelectedItem().toString()) {
                case "NOTA DE VENTA":
                    if (cbx_tipo_venta.getSelectedItem().equals("VENTA")) {
                        txt_nro_doc.setText("00000000");
                        txt_nom.setText("CLIENTES GENERALES");
                        txt_nro_doc.setEnabled(false);
                        txt_fec.setEnabled(true);
                        txt_fec.requestFocus();
                        txt_fec.setBackground(Color.red);
                        txt_fec.setForeground(Color.white);
                    } else {
                        txt_nro_doc.setText("");
                        txt_nom.setText("");
                        txt_nro_doc.setEnabled(true);
                        txt_nro_doc.requestFocus();
                        txt_nro_doc.setBackground(Color.red);
                        txt_nro_doc.setForeground(Color.white);
                    }
                    break;
                case "BOLETA":
                    if (cbx_tipo_venta.getSelectedItem().equals("VENTA")) {
                        if (subtotal() * 1.18 < 750) {
                            txt_nro_doc.setText("00000000");
                            txt_nom.setText("");
                            txt_nom.setEditable(true);
                            txt_nom.requestFocus();
                        } else {
                            txt_nro_doc.setText("");
                            txt_nro_doc.setEnabled(true);
                            txt_nro_doc.requestFocus();
                            txt_nro_doc.setBackground(Color.red);
                            txt_nro_doc.setForeground(Color.white);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "NO SE PUEDE SELECCIONAR ESTE DOCUMENTO");
                        cbx_documento_venta.setSelectedIndex(0);
                    }
                    break;
                case "FACTURA":
                    if (cbx_tipo_venta.getSelectedItem().equals("VENTA")) {
                        txt_nro_doc.setText("");
                        txt_nro_doc.setEnabled(true);
                        txt_nro_doc.requestFocus();
                        txt_nro_doc.setBackground(Color.red);
                        txt_nro_doc.setForeground(Color.white);
                    } else {
                        JOptionPane.showMessageDialog(null, "NO SE PUEDE SELECCIONAR ESTE DOCUMENTO");
                        cbx_documento_venta.setSelectedIndex(0);
                    }
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Error");
                    cbx_documento_venta.setSelectedIndex(0);
                    break;
            }
        }

        if (evt.getKeyCode() == KeyEvent.VK_F1) {
            txt_buscar_producto.setText("");
            txt_buscar_producto.requestFocus();
        }
    }//GEN-LAST:event_cbx_documento_ventaKeyPressed

    private void formInternalFrameActivated(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameActivated
    }//GEN-LAST:event_formInternalFrameActivated

    void cargar_datos_cliente() {
        cli.setNro_doc(txt_nro_doc.getText());
        try {
            Statement st = con.conexion();
            String ver_pro = "select * from cliente where nro_doc = '" + cli.getNro_doc() + "'";
            ResultSet rs = con.consulta(st, ver_pro);
            if (rs.next()) {
                txt_nro_doc.setText(rs.getString("nro_doc"));
                txt_nom.setText(rs.getString("nom_per"));
                txt_fec.setEnabled(true);
                txt_fec.setEditable(true);
                txt_fec.requestFocus();
                txt_nro_doc.setBackground(Color.white);
                txt_nro_doc.setForeground(Color.black);
                txt_fec.setBackground(Color.red);
                txt_fec.setForeground(Color.white);
            } else {
                txt_nro_doc.requestFocus();
                JOptionPane.showMessageDialog(null, "Cliente no registrado");
                frm_reg_cliente cliente = new frm_reg_cliente();
                cli.setNro_doc(txt_nro_doc.getText());

                if (txt_nro_doc.getText().length() == 8) {
                    ven.llamar_ventana(cliente);
                    frm_reg_cliente.ventana = "reg_venta";
                    frm_reg_cliente.cbx_cli.setSelectedItem("DNI");
                    frm_reg_cliente.txt_ndoc.setText(cli.getNro_doc());
                    frm_reg_cliente.txt_ndoc.setEditable(false);
                    frm_reg_cliente.txt_nom.setEditable(true);
                    frm_reg_cliente.txt_nom.requestFocus();

                } else if (txt_nro_doc.getText().length() == 11) {
                    JOptionPane.showMessageDialog(null, "Cargando Datos de la SUNAT");
                    String json = Cl_Entidad.getJSON(cli.getNro_doc());
                    //Lo mostramos
                    String[] datos = Cl_Entidad.showJSON(json);
                    String nombre = datos[0].trim();
                    String direccion = datos[1].trim();
                    JOptionPane.showMessageDialog(null, "DATOS DEL CLIENTE:\nRUC: " + cli.getNro_doc() + "\nRAZON SOCIAL: " + nombre + "\nDIRECCION: " + direccion);
                    txt_nom.setText(nombre);
                    Statement st1 = con.conexion();
                    String inser_cli = "insert into cliente Values ('" + cli.getNro_doc() + "', 'RUC', "
                            + "'" + nombre + "', '" + direccion + "', '0', '0', '" + 1 + "')";
                    con.actualiza(st1, inser_cli);
                    con.cerrar(st1);
                    JOptionPane.showMessageDialog(null, "Se ha ingresado los datos correctamente");
                    /* obtener_datos(cli.getNro_doc());
                     frm_reg_cliente.cbx_cli.setSelectedItem("RUC");
                     frm_reg_cliente.txt_ndoc.setText(cli.getNro_doc());
                     frm_reg_cliente.txt_ndoc.setEditable(false);
                     frm_reg_cliente.txt_nom.setEditable(true);
                     frm_reg_cliente.txt_nom.requestFocus();*/
                }
            }
        } catch (SQLException ex) {
            System.out.print(ex);
        } catch (ParseException ex) {
            Logger.getLogger(frm_reg_venta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void txt_nro_docKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_nro_docKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!txt_nro_doc.getText().isEmpty()) {
                if (txt_nro_doc.getText().length() == 8) {
                    if (cbx_documento_venta.getSelectedItem().equals("NOTA DE VENTA") || cbx_documento_venta.getSelectedItem().equals("BOLETA")) {
                        cargar_datos_cliente();
                    }
                }
                if (txt_nro_doc.getText().length() == 11) {
                    if (cbx_documento_venta.getSelectedItem().equals("FACTURA")) {
                        cargar_datos_cliente();
                        //obtener_datos(cli.getNro_doc());
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "INGRESE DATOS POR FAVOR");
                txt_nro_doc.requestFocus();
            }
        }
    }//GEN-LAST:event_txt_nro_docKeyPressed

    private void txt_nomKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_nomKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txt_fec.setBackground(Color.red);
            txt_fec.setForeground(Color.white);
            txt_nro_doc.setBackground(Color.white);
            txt_nro_doc.setForeground(Color.black);
            String nombre = txt_nom.getText().toUpperCase();
            txt_nom.setText(nombre);
            txt_fec.setEnabled(true);
            txt_fec.setEditable(true);
            txt_fec.setText(ven.fechaformateada(ven.getFechaActual()));
            txt_fec.requestFocus();
        }
    }//GEN-LAST:event_txt_nomKeyPressed

    private void cbx_tipo_ventaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbx_tipo_ventaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (cbx_tipo_venta.getSelectedIndex() == 0) { // venta
                cbx_documento_venta.setEnabled(true);
                cbx_documento_venta.requestFocus();
                txt_nro_doc.setText("");
                txt_nom.setText("");
            }
            if (cbx_tipo_venta.getSelectedIndex() == 1) { // separacion
                cbx_documento_venta.setSelectedIndex(0);
                cbx_documento_venta.setEnabled(false);
                txt_nro_doc.setEnabled(true);
                txt_nro_doc.setText("");
                txt_nom.setText("");
                txt_nro_doc.requestFocus();
            }
        }
        if (evt.getKeyCode() == KeyEvent.VK_F1) {
            txt_buscar_producto.setText("");
            txt_buscar_producto.requestFocus();
        }

    }//GEN-LAST:event_cbx_tipo_ventaKeyPressed

    private void txt_fecKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_fecKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (txt_fec.getText().trim().length() == 10) {
                if (txt_nro_doc.getText().trim().length() >= 8) {
                    btn_reg.setEnabled(true);
                    btn_reg.requestFocus();
                    btn_reg.doClick();
                    txt_fec.setBackground(Color.white);
                    txt_fec.setForeground(Color.black);
                }
            } else {
                txt_fec.requestFocus();
            }
        }
    }//GEN-LAST:event_txt_fecKeyPressed

    private void txt_nro_docKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_nro_docKeyTyped
        if (cbx_documento_venta.getSelectedItem().equals("NOTA DE VENTA")) {
            if (txt_nro_doc.getText().length() == 8) {
                evt.consume();
            }
        }

        if (cbx_documento_venta.getSelectedItem().equals("BOLETA")) {
            if (txt_nro_doc.getText().length() == 8) {
                evt.consume();
            }
        }

        if (cbx_documento_venta.getSelectedItem().equals("FACTURA")) {
            if (txt_nro_doc.getText().length() == 11) {
                evt.consume();
            }
        }
    }//GEN-LAST:event_txt_nro_docKeyTyped

    private void t_detalleKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_t_detalleKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Double precio = Double.parseDouble(t_detalle.getValueAt(i, 5).toString());
            Double cantidad = Double.parseDouble(t_detalle.getValueAt(i, 3).toString());
            double precio_nuevo = 0.0;
            try {
                Statement st = con.conexion();
                String pre_com = "select costo_compra from productos where idProductos='" + t_detalle.getValueAt(i, 0) + "'";
                ResultSet rs = con.consulta(st, pre_com);
                double precio_b = 0;
                if (rs.next()) {
                    precio_b = rs.getDouble("costo_compra") * 1.2;
                }
                con.cerrar(st);
                con.cerrar(rs);

                if (precio >= precio_b) {
                    precio_nuevo = precio;
                    t_detalle.setValueAt(ven.formato_numero(precio_nuevo), i, 5);
                } else {
                    precio_nuevo = precio_b;
                    JOptionPane.showMessageDialog(null, "No se puede establecer ese precio");
                    t_detalle.setValueAt(ven.formato_numero(precio_nuevo), i, 5);
                }
                double parcial = cantidad * precio_nuevo;
                t_detalle.setValueAt(ven.formato_numero(parcial), i, 6);
                calcular_total();

            } catch (SQLException | HeadlessException e) {
                JOptionPane.showMessageDialog(null, "Error " + e.getLocalizedMessage());
            }
        }

        if (evt.getKeyCode() == KeyEvent.VK_DELETE) {
            detalle.removeRow(i);
            calcular_total();
        }

        if (evt.getKeyCode() == KeyEvent.VK_F3) {
            cbx_tipo_venta.setEnabled(true);
            cbx_tipo_venta.requestFocus();
        }
        if (evt.getKeyCode() == KeyEvent.VK_F1) {
            txt_buscar_producto.setText("");
            txt_buscar_producto.requestFocus();
        }
    }//GEN-LAST:event_t_detalleKeyPressed


    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed

    }//GEN-LAST:event_formKeyPressed

    private void txt_buscar_productoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_buscar_productoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_F2) {
            btn_add_pro.doClick();
            txt_buscar_producto.setText("");
        }

        if (evt.getKeyCode() == KeyEvent.VK_F1) {
            txt_buscar_producto.setText("");
        }

        if (evt.getKeyCode() == KeyEvent.VK_F3) {
            txt_buscar_producto.setText("");
            cbx_tipo_venta.setEnabled(true);
            cbx_tipo_venta.requestFocus();
        }
    }//GEN-LAST:event_txt_buscar_productoKeyPressed

    private void cbx_vendedorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbx_vendedorKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            try {
                String user = cbx_vendedor.getSelectedItem().toString();
                String query = "select nom_per from empleados where dni = '" + user + "'";
                Statement st = con.conexion();
                ResultSet rs = con.consulta(st, query);

                if (rs.next()) {
                    txt_vend.setText(rs.getString("nom_per"));
                }
                con.cerrar(st);
                con.cerrar(rs);

                cargar_productos_txt();
                txt_buscar_producto.setEnabled(true);
                btn_add_pro.setEnabled(true);
                txt_buscar_producto.requestFocus();

            } catch (SQLException e) {
                System.out.print(e);
            }
        }
    }//GEN-LAST:event_cbx_vendedorKeyPressed

    void cargar_productos_txt() {
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
                                    Object fila[] = new Object[7];
                                    fila[0] = id_producto;
                                    fila[1] = rs.getString("desc_pro") + " " + rs.getString("marca") + " " + rs.getString("modelo") + " " + rs.getString("serie");
                                    double cantidad = rs.getDouble("cant");
                                    fila[2] = ven.formato_numero(cantidad);
                                    String texto = JOptionPane.showInputDialog("Ingrese Cantidad");
                                    double cantidad_nueva = 1;
                                    if (texto != null) {
                                        if (ven.esDecimal(texto)) {
                                            cantidad_nueva = Double.parseDouble(texto);
                                            //   if (cantidad >= cantidad_nueva) {
                                            fila[3] = ven.formato_numero(cantidad_nueva);
                                            /*    } else {
                                             double exceso = cantidad_nueva - cantidad;
                                             cantidad_nueva = cantidad;
                                             fila[3] = ven.formato_numero(cantidad_nueva);
                                             JOptionPane.showMessageDialog(null, "NO HAY DEMASIADOS PRODUCTOS \n EXCESO DE " + exceso + " UNIDADES");
                                             }*/

                                        } else {
                                            fila[3] = ven.formato_numero(cantidad_nueva);
                                        }
                                    } else {
                                        fila[3] = ven.formato_numero(cantidad_nueva);
                                    }
                                    fila[4] = rs.getString("desc_und");
                                    fila[5] = ven.formato_numero(rs.getDouble("precio"));
                                    fila[6] = ven.formato_numero(rs.getDouble("precio") * cantidad_nueva);

                                    // if (cantidad > 0.0) {
                                    valida_tabla(Integer.parseInt(id_producto), fila);
                                    calcular_total();
                                    // } else {
                                    //     JOptionPane.showMessageDialog(null, "No existe suficiente cantidad para agregar el producto.");
                                    // }
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

                        calcular_total();
                    }
                }
            });

            autocompletar.setMode(0);
            autocompletar.setCaseSensitive(false);
            Statement st = con.conexion();
            String sql = "select pa.idProductos,p.desc_pro, p.marca, p.modelo, p.serie, pa.cant, pa.precio"
                    + " from producto_almacen as pa inner join productos as p"
                    + " on p.idProductos = pa.idProductos where pa.idAlmacen ='" + frm_menu.alm.getId() + "' ";
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

    private void valida_tabla(int producto, Object[] objeto) {
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
            detalle.addRow(objeto);
            t_detalle.setModel(detalle);
//            contar_filas = t_detalle.getRowCount();
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

    private void txt_busquedaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_busquedaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String texto = txt_busqueda.getText();
            String query = "select p.idProductos, p.desc_pro, p.modelo, p.serie, p.marca, pa.cant, p.cant_min, pa.precio, p.estado, c.desc_clas, "
                    + "u.desc_und, p.grado from producto_almacen as pa inner join productos as p on pa.idProductos=p.idProductos "
                    + "inner join clasificacion as c on p.id_clas=c.id_clas inner join und_medida as u on "
                    + "p.idUnd_Medida=u.idUnd_Medida where pa.idAlmacen = '" + frm_menu.alm.getId() + "' and (p.desc_pro like '%" + texto + "%' or p.modelo "
                    + "like '%" + texto + "%' or p.serie like '%" + texto + "%' or p.marca like '%" + texto + "%')  order by p.desc_pro asc, p.modelo asc";
            pro.mostrar_productos(query, t_productos);
            jLabel3.setText("" + tot_reg());
            t_productos.requestFocus();
        }

        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            j_productos.dispose();
            txt_buscar_producto.requestFocus();
        }

        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            j_productos.dispose();
            txt_buscar_producto.requestFocus();
        }
    }//GEN-LAST:event_txt_busquedaKeyPressed

    private int tot_reg() {
        int tot = 0;
        for (int j = 0; j < t_productos.getRowCount(); j++) {
            tot++;
        }
        return tot;
    }

    private void txt_busquedaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_busquedaKeyTyped

    }//GEN-LAST:event_txt_busquedaKeyTyped

    private void t_productosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_t_productosMouseClicked
        if (evt.getClickCount() == 2) {
        }
    }//GEN-LAST:event_t_productosMouseClicked

    private void t_productosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_t_productosMousePressed
        //        i = t_productos.getSelectedRow();
        //        btn_kar.setEnabled(true);
    }//GEN-LAST:event_t_productosMousePressed

    private void t_productosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_t_productosKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            int nro_fila = t_productos.getSelectedRow();
            String id_producto = t_productos.getValueAt(nro_fila, 0).toString();
            String descripcion = t_productos.getValueAt(nro_fila, 1).toString();
            String marca = t_productos.getValueAt(nro_fila, 2).toString();
            String und_med = t_productos.getValueAt(nro_fila, 4).toString();
            double cantidad = Double.parseDouble(t_productos.getValueAt(nro_fila, 3).toString());
            double precio = Double.parseDouble(t_productos.getValueAt(nro_fila, 5).toString());

            Object fila[] = new Object[7];
            fila[0] = id_producto;
            fila[1] = descripcion + " " + marca;
            fila[2] = ven.formato_numero(cantidad);
            String texto = JOptionPane.showInputDialog("Ingrese Cantidad");
            double cantidad_nueva = 1;
            if (texto != null) {
                if (ven.esDecimal(texto)) {
                    cantidad_nueva = Double.parseDouble(texto);
                    //       if (cantidad >= cantidad_nueva) {
                    fila[3] = ven.formato_numero(cantidad_nueva);
                    /*       } else {
                     double exceso = cantidad_nueva - cantidad;
                     cantidad_nueva = cantidad;
                     fila[3] = ven.formato_numero(cantidad_nueva);
                     JOptionPane.showMessageDialog(null, "NO HAY DEMASIADOS PRODUCTOS \n EXCESO DE " + exceso + " UNIDADES");
                     }
                     */
                }
            }
            fila[4] = und_med;
            fila[5] = ven.formato_numero(precio);
            fila[6] = ven.formato_numero(precio * cantidad_nueva);

            if (cantidad > 0.0) {
                valida_tabla(Integer.parseInt(id_producto), fila);
                calcular_total();
                txt_busqueda.setText("");
            } else {
                JOptionPane.showMessageDialog(null, "No existe suficiente cantidad para agregar el producto.");
            }
        }

        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            txt_busqueda.setText("");
            txt_busqueda.requestFocus();
        }
    }//GEN-LAST:event_t_productosKeyPressed

    private void btn_cerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cerActionPerformed
        j_productos.dispose();
    }//GEN-LAST:event_btn_cerActionPerformed

    private void t_detalleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_t_detalleMouseClicked
        i = t_detalle.getSelectedRow();
        btn_cam_can.setEnabled(true);
        btn_eli.setEnabled(true);
        btn_cpre.setEnabled(true);
    }//GEN-LAST:event_t_detalleMouseClicked

    private void cbx_jd_monedaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbx_jd_monedaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (cbx_jd_moneda.getSelectedIndex() == 1) {
                lbl_total.setText("S/. " + ven.formato_totales(subtotal() * 1.18));
                total_new = Double.parseDouble(ven.formato_numero(subtotal() * 1.18));
            } else {
                Double convertido = mon.cambio_venta_dolar(ven.fechabase(txt_fec.getText()), cbx_jd_moneda.getSelectedIndex() + 1, subtotal() * 1.18);
                lbl_total.setText("US$/€ " + ven.formato_totales(convertido));
                total_new = Double.parseDouble(ven.formato_numero(convertido));
            }
            tc = tc_venta(ven.fechabase(txt_fec.getText()));
            txt_jd_efectivo.setEnabled(true);
            txt_jd_efectivo.requestFocus();
        }
    }//GEN-LAST:event_cbx_jd_monedaKeyPressed

    private Double tc_venta(String fecha) {
        double compra = 0.0;
        try {
            Statement st = con.conexion();
            String ver_tc = "select venta from tipo_cambio where fecha = '" + fecha + "'";
            ResultSet rs = con.consulta(st, ver_tc);
            if (rs.next()) {
                compra = rs.getDouble("venta");
            } else {
                compra = 0.0;
            }
            con.cerrar(rs);
            con.cerrar(st);
        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
        }
        return compra;
    }

    private void btn_jd_cerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_jd_cerrarActionPerformed
        j_fin_venta.dispose();
    }//GEN-LAST:event_btn_jd_cerrarActionPerformed

    private void btn_jd_registrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_jd_registrarActionPerformed
        tido.setId(frm_reg_venta.cbx_documento_venta.getSelectedIndex() + 1);
        tido.setDesc(frm_reg_venta.cbx_documento_venta.getSelectedItem().toString());
        tido.setSerie(tido.ver_ser(tido.getId(), frm_menu.alm.getId()));
        tido.setNro(tido.ver_num(tido.getId(), frm_menu.alm.getId()));
        ped.setFec_ped(ven.fechabase(frm_reg_venta.txt_fec.getText()));
        ped.setFec_pag_ped(ped.getFec_ped());
        ped.setTotal(total_new);
        ped.setDes_ped(0);

        usu.setNick(frm_reg_venta.cbx_vendedor.getSelectedItem().toString());
        cli.setNro_doc(frm_reg_venta.txt_nro_doc.getText());
        cli.setNom_cli(frm_reg_venta.txt_nom.getText().toUpperCase());
        ped.setTotal(ped.getTotal() + comision_tarjeta);

        Cl_Hilo_Imprime imprime = new Cl_Hilo_Imprime();
        btn_jd_registrar.setEnabled(false);
        btn_jd_cerrar.setEnabled(false);
        btn_reg.setEnabled(false);
        btn_clo.setEnabled(false);
        String fecha = ven.fechabase(txt_fec.getText());
        tido.setId(cbx_documento_venta.getSelectedIndex() + 1);
        tido.setSerie(Integer.parseInt(txt_jd_serie.getText()));
        tido.setNro(Integer.parseInt(txt_jd_numero.getText()));
        String fecha_pago;
        String estado;
        String tipo_venta = cbx_tipo_venta.getSelectedItem().toString();
        if (tipo_venta.equals("SEPARACION")) {
            ped.setEst_ped("2");
            tipa.setId(2);
        }
        if (tipo_venta.equals("VENTA")) {
            if (deuda_actual > 0) {
                ped.setFec_pag_ped("7000-01-01");
                ped.setEst_ped("6");
                tipa.setId(2);
            } else {
                ped.setFec_pag_ped(fecha);
                ped.setEst_ped("1");
                tipa.setId(1);
            }
        }

        int id_venta = 0;
        int id_moneda = cbx_jd_moneda.getSelectedIndex() + 1;
        String nro_documento = txt_nro_doc.getText();
        String nom_cliente = txt_nom.getText().toUpperCase();
        String anio_periodo = fecha.charAt(0) + "" + fecha.charAt(1) + fecha.charAt(2) + fecha.charAt(3);
        String mes_periodo = fecha.charAt(5) + "" + fecha.charAt(6);
        String periodo = mes_periodo + "-" + anio_periodo;

        //registrar venta
        int registro = -1;
        try {
            Statement st = con.conexion();
            String ins_ven = "insert into pedido Values (null, '" + ped.getFec_ped() + "', '" + ped.getFec_pag_ped() + "', "
                    + "'" + tipa.getId() + "', '" + ped.getDes_ped() + "', '" + ped.getEst_ped() + "', '" + tido.getId() + "', "
                    + "'" + tido.getSerie() + "', '" + tido.getNro() + "', '" + usu.getNick() + "', "
                    + "'" + frm_menu.alm.getId() + "', current_time(), '" + cli.getNro_doc() + "', '" + cli.getNom_cli() + "','" + ped.getTotal() + "')";
            System.out.println(ins_ven);
            registro = con.actualiza(st, ins_ven);
            con.cerrar(st);
        } catch (Exception ex) {
            System.out.println(ex.getLocalizedMessage());
        }

        if (registro > -1) {

            //buscar ultimo pedido
            try {
                Statement st = con.conexion();
                String buscar_pedido = "select idPedido from pedido where nro_doc = '" + tido.getNro() + "' "
                        + "and fec_ped = '" + ped.getFec_ped() + "' and idAlmacen = "
                        + "'" + frm_menu.alm.getId() + "' order by idPedido desc limit 1";
                System.out.println(buscar_pedido + "\n");
                ResultSet rs = con.consulta(st, buscar_pedido);
                if (rs.next()) {
                    ped.setId_ped(rs.getString("idPedido"));
                }
                con.cerrar(rs);
                con.cerrar(st);
            } catch (SQLException ex) {
                System.out.print(ex.getLocalizedMessage());
            }

            int nro_filas_detalle = t_detalle.getRowCount();
            if (nro_filas_detalle > 0) {
                for (int j = 0; j < nro_filas_detalle; j++) {
                    String tipo_prod = t_detalle.getValueAt(j, 1).toString();
                    pro.setId_pro(Integer.parseInt(frm_reg_venta.t_detalle.getValueAt(j, 0).toString()));
                    pro.setCan(Double.parseDouble(frm_reg_venta.t_detalle.getValueAt(j, 3).toString()));
                    pro.setPre_pro(Double.parseDouble(frm_reg_venta.t_detalle.getValueAt(j, 5).toString()));

                    //registrar detalle de venta
                    try {
                        Statement st = con.conexion();
                        String ins_det_ped = "insert into detalle_pedido Values ('" + pro.getId_pro() + "', '" + ped.getId_ped() + "', "
                                + "'" + pro.getPre_pro() + "', '" + pro.getCan() + "')";
                        System.out.println(ins_det_ped + "\n");
                        con.actualiza(st, ins_det_ped);
                        con.cerrar(st);
                    } catch (Exception ex) {
                        System.err.print(ex.getLocalizedMessage());
                    }
                    if (tipo_venta.equals("VENTA")) {
                        //insertar datos en kardex
                        try {
                            Statement st = con.conexion();
                            String ins_kardex = "insert into kardex Values (null, '" + ped.getFec_ped() + "', '" + pro.getId_pro() + "', '0.00', '0.00', '"
                                    + pro.getCan() + "', '" + pro.getPre_pro() + "','" + tido.getSerie() + "', '" + tido.getNro() + "', '" + tido.getId() + "',"
                                    + " '" + frm_menu.alm.getId() + "','" + cli.getNro_doc() + "', '" + cli.getNom_cli() + "','1')";
                            System.out.println(ins_kardex);
                            con.actualiza(st, ins_kardex);
                            con.cerrar(st);
                        } catch (Exception ex) {
                            System.err.print("Error en: " + ex.getLocalizedMessage());
                        }

                        //seleccionar cantidad de producto y restar
                        try {
                            Statement st = con.conexion();
                            String bus_pro = "select cant_actual from productos where idProductos = '" + pro.getId_pro() + "'";
                            System.out.println(bus_pro + "\n");
                            ResultSet rs = con.consulta(st, bus_pro);
                            if (rs.next()) {
                                pro.setCan_act_pro(rs.getDouble("cant_actual"));
                            }
                            con.cerrar(rs);
                            con.cerrar(st);
                            pro.setCan_act_pro(pro.getCan_act_pro() - pro.getCan());
                        } catch (SQLException ex) {
                            System.err.print("Error en: " + ex.getLocalizedMessage());
                        }
                        //actualizar cantidad de productos en general
                        try {
                            Statement st = con.conexion();
                            String act_pro = "update productos set cant_actual = '" + pro.getCan_act_pro() + "' where idProductos = '" + pro.getId_pro() + "' ";
                            System.out.println(act_pro);
                            con.actualiza(st, act_pro);
                            con.cerrar(st);
                        } catch (Exception ex) {
                            System.err.print(ex.getLocalizedMessage());
                        }

                        //consultar cantidad de producto en tienda
                        try {
                            Statement st = con.conexion();
                            String ver_prod_alm = "select idProductos, cant from producto_almacen where idAlmacen = '" + frm_menu.alm.getId() + "' "
                                    + "and idProductos = '" + pro.getId_pro() + "'";
                            System.out.println(ver_prod_alm + "\n");
                            ResultSet rs = con.consulta(st, ver_prod_alm);
                            if (rs.next()) {
                                pro.setCan_act_pro(rs.getDouble("cant"));
                            }
                            pro.setCan_act_pro(pro.getCan_act_pro() - pro.getCan());
                            con.cerrar(rs);
                            con.cerrar(st);
                        } catch (SQLException ex) {
                            System.err.print(ex.getLocalizedMessage());
                        }

                        //actualizar cantidad de productos en tienda
                        try {
                            Statement st = con.conexion();
                            String act_pro = "update producto_almacen set cant = '" + pro.getCan_act_pro() + "' where "
                                    + "idProductos = '" + pro.getId_pro() + "' and idAlmacen = '" + frm_menu.alm.getId() + "'";
                            System.out.println(act_pro + "\n");
                            con.actualiza(st, act_pro);
                            con.cerrar(st);
                        } catch (Exception ex) {
                            System.err.print(ex.getLocalizedMessage());
                        }
                    }
                }

                //guardar pago de la venta
                double efectivo = Double.parseDouble(txt_jd_efectivo.getText());
                double tarjeta = Double.parseDouble(txt_jd_tarjeta.getText());
                double comision5 = Double.parseDouble(txt_com.getText());
                double vale = Double.parseDouble(txt_jd_vale.getText());
                double suma_pagos = efectivo + tarjeta + vale;
                double nuevo_efectivo;
                if (suma_pagos > total_new) {
                    double exceso = suma_pagos - total_new;
                    nuevo_efectivo = efectivo - exceso;
                } else {
                    nuevo_efectivo = efectivo;
                }

                tido.setDesc(cbx_documento_venta.getSelectedItem().toString());

                int id_pago = 0;
                if (nuevo_efectivo > 0) {
                    //registrar pago en detalle_pago
                    try {
                        Statement st = con.conexion();
                        String add_mov_caja = "insert into letras_pedido Values (null, '" + nuevo_efectivo + "', '" + ped.getFec_ped() + "', '" + ped.getId_ped() + "', 'EFECTIVO')";
                        System.out.println(add_mov_caja + "\n");
                        con.actualiza(st, add_mov_caja);
                        con.cerrar(st);
                    } catch (Exception ex) {
                        System.err.print("Error en:" + ex.getLocalizedMessage());

                    }

                    //REGISTRAR MOVIMIENTO EN CAJA CHICA
                    String glosa = "VENTA EN EFECTIVO / " + tido.getDesc() + " / " + tido.getSerie() + " - " + tido.getNro() + " / " + cli.getNom_cli();
                    try {
                        Statement st = con.conexion();
                        String add_mov_caja = "insert into movimiento Values (null, '" + glosa + "', '" + ped.getFec_ped() + "' , '" + nuevo_efectivo + "' "
                                + ", '0.00', '" + usu.getNick() + "','" + frm_menu.alm.getId() + "', 'C', '" + frm_menu.caja.getId() + "')";
                        System.out.println(add_mov_caja + "\n");
                        con.actualiza(st, add_mov_caja);
                        con.cerrar(st);
                    } catch (Exception ex) {
                        System.err.print("Error en:" + ex.getLocalizedMessage());

                    }
                }

                if (tarjeta > 0) {
                    //registrar pago en detalle_pago
                    try {
                        Statement st = con.conexion();
                        String add_mov_caja = "insert into letras_pedido Values (null, '" + tarjeta + "', '" + ped.getFec_ped() + "', '" + ped.getId_ped() + "', 'TARJETA')";
                        System.out.println(add_mov_caja + "\n");
                        con.actualiza(st, add_mov_caja);
                        con.cerrar(st);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex);
                        System.err.print("Error en:" + ex.getLocalizedMessage());

                    }

                    //REGISTRAR MOVIMIENTO EN CAJA CHICA
                    String glosa = "VENTA CON TARJETA / " + tido.getDesc() + " / " + tido.getSerie() + " - " + tido.getNro() + " / " + cli.getNom_cli();
                    try {
                        Statement st = con.conexion();
                        String add_mov_tarj = "insert into movimiento Values (null, '" + glosa + "', '" + ped.getFec_ped() + "' , '" + tarjeta + "' "
                                + ", '0.00', '" + usu.getNick() + "','" + frm_menu.alm.getId() + "',  'B', '" + frm_menu.cue.getId_cuen() + "')";
                        System.out.println(add_mov_tarj + "\n");
                        con.actualiza(st, add_mov_tarj);
                        con.cerrar(st);
                    } catch (Exception ex) {
                        System.err.print("Error en:" + ex.getLocalizedMessage());

                    }
                }

                if (vale > 0) {
                    try {
                        Statement st = con.conexion();
                        String add_mov_caja = "insert into letras_pedido Values (null, '" + vale + "', '" + ped.getFec_ped() + "', '" + ped.getId_ped() + "', 'VALE')";
                        System.out.println(add_mov_caja + "\n");
                        con.actualiza(st, add_mov_caja);
                        con.cerrar(st);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex);
                        System.err.print("Error en:" + ex.getLocalizedMessage());

                    }
                }
                //actualizar documento de la tienda
                tido.act_doc(tido.getSerie(), tido.getNro() + 1, frm_menu.alm.getId(), tido.getId());

                //imprimir ticket
                imprime.setTipo_venta(tipo_venta);
                imprime.set_tipv(tido.getDesc());
                imprime.set_idped(ped.getId_ped());
                System.out.println(imprime.get_idped() + " - " + imprime.get_tipv());
                //imprime.start();

                j_fin_venta.dispose();
                this.dispose();
                frm_reg_venta venta = new frm_reg_venta();
                ven.llamar_ventana(venta);
            }
        } else {
            Notification.show("Error", "Error al ingresar venta.!!", Notification.DISK_ICON, Notification.NICON_DARK_THEME);
        }

    }//GEN-LAST:event_btn_jd_registrarActionPerformed

    private void txt_jd_efectivoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_jd_efectivoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            double efectivo = 0;
            if (txt_jd_efectivo.getText().length() > 0) {
                efectivo = Double.parseDouble(txt_jd_efectivo.getText());
            }
            txt_jd_efectivo.setText(ven.formato_numero(efectivo));
            double cupon = Double.parseDouble(txt_jd_vale.getText());
            double tarjeta = 0.0;
            double suma_pago = tarjeta + cupon + efectivo;
            vuelto = suma_pago - total_new;
            deuda_actual = total_new - suma_pago;

            txt_jd_suma_pago.setText(ven.formato_totales(suma_pago));

            if (vuelto > 0) {
                txt_jd_vuelto.setText(ven.formato_totales(vuelto));
            } else {
                txt_jd_vuelto.setText("0.00");
            }

            if (deuda_actual > 0) {
                txt_jd_deuda_actual.setText(ven.formato_totales(deuda_actual));
            } else {
                txt_jd_deuda_actual.setText("0.00");
            }

            txt_jd_tarjeta.setEnabled(true);
            txt_jd_tarjeta.requestFocus();
        }
    }//GEN-LAST:event_txt_jd_efectivoKeyPressed

    private void txt_jd_efectivoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_jd_efectivoKeyTyped
        ven.solo_precio(evt);
    }//GEN-LAST:event_txt_jd_efectivoKeyTyped

    private void txt_jd_tarjetaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_jd_tarjetaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            double tarjeta = 0;
            if (txt_jd_tarjeta.getText().length() > 0) {
                tarjeta = Double.parseDouble(txt_jd_tarjeta.getText());
            }
            txt_jd_tarjeta.setText(ven.formato_numero(tarjeta));

            //sumar digitos
            double efectivo = Double.parseDouble(txt_jd_efectivo.getText());
            double cupon = Double.parseDouble(txt_jd_vale.getText());

            double suma_pago = tarjeta + cupon + efectivo;
            vuelto = suma_pago - total_new;
            deuda_actual = total_new - suma_pago;

            txt_jd_suma_pago.setText(ven.formato_totales(suma_pago));

            if (vuelto > 0) {
                txt_jd_vuelto.setText(ven.formato_totales(vuelto));
            } else {
                txt_jd_vuelto.setText("0.00");
            }

            if (deuda_actual > 0) {
                txt_jd_deuda_actual.setText(ven.formato_totales(deuda_actual));
            } else {
                txt_jd_deuda_actual.setText("0.00");
            }

            if (suma_pago > 0.0) {
                btn_jd_registrar.setEnabled(true);
                btn_jd_registrar.requestFocus();
            }
        }
    }//GEN-LAST:event_txt_jd_tarjetaKeyPressed

    private void txt_jd_tarjetaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_jd_tarjetaKeyTyped
        ven.solo_precio(evt);
    }//GEN-LAST:event_txt_jd_tarjetaKeyTyped

    private void chk_incluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chk_incluirActionPerformed
        monto_tarjeta = Double.parseDouble(txt_jd_tarjeta.getText());
        if (ven.esDecimal(txt_jd_tarjeta.getText())) {
            comision_tarjeta = 0;
            if (chk_incluir.isSelected()) {
                comision_tarjeta = monto_tarjeta * 0.05;
                txt_com.setText(ven.formato_numero(comision_tarjeta));
                lbl_total.setText("S/. " + ven.formato_totales(total_new + comision_tarjeta));
            } else {
                comision_tarjeta = 0;
                txt_com.setText("0.00");
                lbl_total.setText("S/. " + ven.formato_totales(total_new));
            }
            txt_jd_tarjeta.requestFocus();
        }
    }//GEN-LAST:event_chk_incluirActionPerformed

    private void txt_jd_codigo_valeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_jd_codigo_valeKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_jd_codigo_valeKeyPressed

    private void txt_jd_codigo_valeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_jd_codigo_valeKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_jd_codigo_valeKeyTyped

    private void txt_jd_tipo_ventaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_jd_tipo_ventaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_jd_tipo_ventaKeyPressed

    private void txt_jd_tipo_ventaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_jd_tipo_ventaKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_jd_tipo_ventaKeyTyped

    private void txt_nomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_nomActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_nomActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JButton btn_add_pro;
    private javax.swing.JButton btn_cam_can;
    private javax.swing.JButton btn_cer;
    public static javax.swing.JButton btn_clo;
    private javax.swing.JButton btn_cpre;
    private javax.swing.JButton btn_eli;
    private javax.swing.JButton btn_jd_cerrar;
    private javax.swing.JButton btn_jd_registrar;
    public static javax.swing.JButton btn_reg;
    private javax.swing.JLabel cbx_alm;
    public static javax.swing.JComboBox cbx_documento_venta;
    private javax.swing.JComboBox cbx_jd_moneda;
    public static javax.swing.JComboBox cbx_tipo_venta;
    public static javax.swing.JComboBox cbx_vendedor;
    public static javax.swing.JCheckBox chk_incluir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    public static javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JDialog j_fin_venta;
    private javax.swing.JDialog j_productos;
    public static javax.swing.JLabel lbl_tot;
    public static javax.swing.JLabel lbl_total;
    private javax.swing.JLabel lblcod_alm1;
    public static javax.swing.JTable t_detalle;
    public static javax.swing.JTable t_productos;
    private javax.swing.JTextField txt_buscar_producto;
    private javax.swing.JTextField txt_busqueda;
    private javax.swing.JTextField txt_com;
    public static javax.swing.JFormattedTextField txt_fec;
    public static javax.swing.JTextField txt_igv;
    private javax.swing.JTextField txt_jd_codigo_vale;
    private javax.swing.JTextField txt_jd_deuda_actual;
    private javax.swing.JTextField txt_jd_documento;
    private javax.swing.JTextField txt_jd_efectivo;
    private javax.swing.JTextField txt_jd_numero;
    private javax.swing.JTextField txt_jd_serie;
    private javax.swing.JTextField txt_jd_suma_pago;
    private javax.swing.JTextField txt_jd_tarjeta;
    private javax.swing.JTextField txt_jd_tipo_venta;
    private javax.swing.JTextField txt_jd_vale;
    private javax.swing.JTextField txt_jd_vuelto;
    public static javax.swing.JTextField txt_nom;
    public static javax.swing.JTextField txt_nro_doc;
    public static javax.swing.JTextField txt_subt;
    private javax.swing.JTextField txt_vend;
    // End of variables declaration//GEN-END:variables
}
