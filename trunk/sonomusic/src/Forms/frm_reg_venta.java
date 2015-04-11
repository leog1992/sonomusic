package Forms;

import Clases.Cl_Almacen;
import Vistas.frm_fin_venta;
import Clases.Cl_Cliente;
import Clases.Cl_Conectar;
import Clases.Cl_Ofertas;
import Clases.Cl_Pedido;
import Clases.Cl_Productos;
import Clases.Cl_Tipo_Documentos;
import Clases.Cl_Tipo_Pago;
import Clases.Cl_Usuario;
import Clases.Cl_Varios;
import Clases.table_render;
import Vistas.frm_ver_prod_alm;
import com.mxrck.autocompleter.TextAutoCompleter;
import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
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
 * @author pc
 */
public class frm_reg_venta extends javax.swing.JInternalFrame {

    public static Cl_Varios ven = new Cl_Varios();
    Cl_Conectar con = new Cl_Conectar();
    Cl_Ofertas ofe = new Cl_Ofertas();
    public static Cl_Usuario usu = new Cl_Usuario();
    public static Cl_Almacen alm = new Cl_Almacen();
    public static Cl_Cliente cli = new Cl_Cliente();
    public static Cl_Pedido ped = new Cl_Pedido();
    public static Cl_Productos pro = new Cl_Productos();
    public static Cl_Tipo_Pago tipa = new Cl_Tipo_Pago();
    public static Cl_Tipo_Documentos tido = new Cl_Tipo_Documentos();
    public static DefaultTableModel detalle;
    public static double sub = 0.00;
    public static double descuento = 0.00;
    public static double igv = 0.00;
    public static double tot = 0.00;
    public static DecimalFormatSymbols simbolo = new DecimalFormatSymbols(Locale.US);
    public static DecimalFormat formato = new DecimalFormat("####0.00", simbolo);
    public static String tipo_venta = "";
    Integer i;

    /**
     * Creates new form frm_reg_venta
     */
    public frm_reg_venta() {
        initComponents();
        txt_fec.setForeground(Color.blue);
        txt_fec.setText(ven.fechaformateada(ven.getFechaActual()));

        detalle = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int fila, int columna) {
                if (columna == 3 || columna == 5) {
                    return true;
                } else {
                    return false;
                }
            }
        };
        detalle.addColumn("Id");
        detalle.addColumn("Descripcion");
        detalle.addColumn("Marca");
        detalle.addColumn("Cant.");
        detalle.addColumn("Und. Med");
        detalle.addColumn("Precio");
        t_detalle.setModel(detalle);
        t_detalle.getColumnModel().getColumn(0).setPreferredWidth(10);
        t_detalle.getColumnModel().getColumn(1).setPreferredWidth(300);
        t_detalle.getColumnModel().getColumn(2).setPreferredWidth(80);
        t_detalle.getColumnModel().getColumn(3).setPreferredWidth(30);
        t_detalle.getColumnModel().getColumn(4).setPreferredWidth(70);
        t_detalle.getColumnModel().getColumn(5).setPreferredWidth(50);
        detalle.fireTableDataChanged();
        String query = "select * from tipo_doc";
        ver_tipodoc(query);

        try {
            File Ffichero = new File("almacen.txt");
            String txt_alm = null;
            /*Si existe el fichero*/
            if (Ffichero.exists()) {
                /*Abre un flujo de lectura a el fichero*/
                BufferedReader Flee = new BufferedReader(new FileReader(Ffichero));
                String Slinea;
                System.out.println("**********Leyendo Fichero***********");
                /*Lee el fichero linea a linea hasta llegar a la ultima*/
                while ((Slinea = Flee.readLine()) != null) {
                    /*Imprime la linea leida*/
                    txt_alm = Slinea;
                    System.out.println(Slinea);
                }

                System.out.println("*********Fin Leer Fichero**********");
                /*Cierra el flujo*/
                Flee.close();
            } else {
                System.out.println("Fichero No Existe");
            }
        } catch (IOException ex) {
            /*Captura un posible error y le imprime en pantalla*/

            System.out.println(ex.getMessage());
        }
    }
    //fin del constructor

    private void ver_tipodoc(String query) {
        try {
            Statement st = con.conexion();
            ResultSet rs = con.consulta(st, query);

            while (rs.next()) {
                String fila;
                fila = rs.getString("desc_tipd");
                cbx_tipd.addItem(fila);
            }
            con.cerrar(st);
            con.cerrar(rs);
        } catch (SQLException e) {
            System.out.print(e);
        }
    }

    public static void llenar() {
        //idpedido
        ped.setFec_ped(ven.fechabase(txt_fec.getText()));
        //fecha pago
        //tipo pago
        ped.setDes_ped(descuento);
        //estado pedido 1 == venta || 0 == separacion
        //tido.setId(cbx_tipd.getSelectedIndex()+1);        
        //nro documento
        usu.setNick(frm_menu.usu.getNick());
        alm.setId(frm_menu.alm.getId());
        //albaran
        //cli.setNro_doc(txt_nro_doc.getText());
        //total

    }

    public static void subtotal() {
        int totalRow = t_detalle.getRowCount();
        double suma_sub = 0.00;
        for (int i = 0; i < totalRow; i++) {
            suma_sub += Double.parseDouble(String.valueOf(t_detalle.getValueAt(i, 5))) * Double.parseDouble(String.valueOf(t_detalle.getValueAt(i, 3)));

        }
        if (cbx_tipd.getSelectedItem().equals("FACTURA")) {
            sub = suma_sub;
        } else {
            sub = suma_sub / 1.18;
        }
        txt_subt.setText(formato.format(sub));
    }

    public static void total() {
        if (cbx_tipd.getSelectedItem().equals("FACTURA")) {
            igv = (sub - descuento) * 0.18;
            tot = sub - descuento + igv;
            txt_des.setText(formato.format(descuento));
            txt_igv.setText(formato.format(igv));
            lbl_tot.setText("S/. " + formato.format(tot));
        } else {
            igv = sub * 0.18;
            tot = (sub + igv) - descuento;
            txt_des.setText(formato.format(descuento));
            txt_igv.setText(formato.format(igv));
            lbl_tot.setText("S/. " + formato.format(tot));
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        t_detalle = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        txt_subt = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txt_des = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txt_igv = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lbl_tot = new javax.swing.JLabel();
        btn_reg = new javax.swing.JButton();
        btn_clo = new javax.swing.JButton();
        btn_cam_can = new javax.swing.JButton();
        btn_desc = new javax.swing.JButton();
        btn_eli = new javax.swing.JButton();
        btn_add_pro = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        cbx_tipd = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblcod_alm1 = new javax.swing.JLabel();
        cbx_alm = new javax.swing.JLabel();
        txt_fec = new javax.swing.JFormattedTextField();
        jLabel1 = new javax.swing.JLabel();
        cbx_tip_venta = new javax.swing.JComboBox();
        txt_nro_doc = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txt_nom = new javax.swing.JTextField();
        btn_cpre = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        txt_id = new javax.swing.JTextField();
        txt_desc = new javax.swing.JTextField();

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
            public void mousePressed(java.awt.event.MouseEvent evt) {
                t_detalleMousePressed(evt);
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
        jLabel2.setForeground(new java.awt.Color(212, 2, 2));
        jLabel2.setText("Sub Total:");
        jLabel2.setFocusable(false);

        txt_subt.setEditable(false);
        txt_subt.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_subt.setFocusable(false);
        txt_subt.setPreferredSize(new java.awt.Dimension(50, 20));

        jLabel6.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(212, 2, 2));
        jLabel6.setText("Descuento:");
        jLabel6.setFocusable(false);

        txt_des.setEditable(false);
        txt_des.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_des.setFocusable(false);
        txt_des.setPreferredSize(new java.awt.Dimension(50, 20));
        txt_des.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_desActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(212, 2, 2));
        jLabel7.setText("IGV");
        jLabel7.setFocusable(false);

        txt_igv.setEditable(false);
        txt_igv.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_igv.setFocusable(false);
        txt_igv.setPreferredSize(new java.awt.Dimension(50, 20));

        jLabel8.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(212, 2, 2));
        jLabel8.setText("Total:");
        jLabel8.setFocusable(false);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 0, 0));
        jLabel9.setText("Detalle de Venta:");
        jLabel9.setFocusable(false);

        lbl_tot.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
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

        btn_desc.setText("Descuento");
        btn_desc.setEnabled(false);
        btn_desc.setFocusable(false);
        btn_desc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_descActionPerformed(evt);
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

        cbx_tipd.setEnabled(false);
        cbx_tipd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cbx_tipdKeyPressed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(212, 2, 2));
        jLabel5.setText("Tipo de Doc:");
        jLabel5.setFocusable(false);

        jLabel4.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(212, 2, 2));
        jLabel4.setText("Fecha de Venta:");
        jLabel4.setFocusable(false);

        lblcod_alm1.setText("000");

        cbx_alm.setText("-");

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
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_fecKeyTyped(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(212, 2, 2));
        jLabel1.setText("Tipo Venta:");

        cbx_tip_venta.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "VENTA", "SEPARACION" }));
        cbx_tip_venta.setEnabled(false);
        cbx_tip_venta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cbx_tip_ventaKeyPressed(evt);
            }
        });

        txt_nro_doc.setEditable(false);
        txt_nro_doc.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_nro_doc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_nro_docKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_nro_docKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_nro_docKeyTyped(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(212, 2, 2));
        jLabel10.setText("D.N.I / R.U.C");

        jLabel12.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(212, 2, 2));
        jLabel12.setText("Nombre:");

        txt_nom.setEditable(false);
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
                    .addComponent(cbx_tipd, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_nro_doc))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(cbx_tip_venta, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbx_tipd, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_fec, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbx_tip_venta, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txt_nro_doc, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_nom, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        btn_cpre.setText("Cambiar Precio");
        btn_cpre.setEnabled(false);
        btn_cpre.setFocusable(false);
        btn_cpre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cpreActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(212, 2, 2));
        jLabel11.setText("iD:");
        jLabel11.setFocusable(false);

        txt_id.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_idKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_idKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_idKeyTyped(evt);
            }
        });

        txt_desc.setEditable(false);
        txt_desc.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_descFocusGained(evt);
            }
        });
        txt_desc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_descKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_descKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_cam_can)
                            .addComponent(jLabel9))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_desc))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_id)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btn_cpre)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btn_eli)
                                .addGap(0, 298, Short.MAX_VALUE))
                            .addComponent(txt_desc))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_add_pro, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel2)
                                        .addComponent(jLabel6)
                                        .addComponent(jLabel7)
                                        .addComponent(jLabel8))
                                    .addGap(18, 18, 18)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txt_subt, javax.swing.GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE)
                                        .addComponent(txt_des, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txt_igv, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addComponent(lbl_tot, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btn_reg, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btn_clo, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(1, 1, 1)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(btn_add_pro, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel11)
                    .addComponent(txt_id, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_desc, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_cam_can, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_desc, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_cpre, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_eli, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_subt, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_des, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_igv, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbl_tot, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
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
        //frm_ver_vehiculos.ventana = "vehiculos";
        //frm_ver_productos.ventana = "productos";
        sub = 0.00;
        descuento = 0.00;
        igv = 0.00;
        tot = 0.00;
        this.dispose();

    }//GEN-LAST:event_btn_cloActionPerformed

    private void ver_productos(String query) {
        Cl_Productos pro = new Cl_Productos();
        try {
            frm_ver_prod_alm.mostrar = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int fila, int columna) {
                    return false;
                }
            };
            Statement st = con.conexion();
            ResultSet rs = con.consulta(st, query);
            //Establecer como cabezeras el nombre de las colimnas
            frm_ver_prod_alm.mostrar.addColumn("Id");
            frm_ver_prod_alm.mostrar.addColumn("Descripcion");
            frm_ver_prod_alm.mostrar.addColumn("Marca");
            frm_ver_prod_alm.mostrar.addColumn("Cant.");
            frm_ver_prod_alm.mostrar.addColumn("Und. Med.");
            frm_ver_prod_alm.mostrar.addColumn("Precio");
            frm_ver_prod_alm.mostrar.addColumn("Cant. Minima");
            frm_ver_prod_alm.mostrar.addColumn("Grado");
            frm_ver_prod_alm.mostrar.addColumn("Estado");

            //Creando las filas para el JTable
            while (rs.next()) {
                Object[] fila = new Object[9];
                fila[0] = rs.getObject("idProductos");
                fila[1] = rs.getObject("desc_pro");
                fila[2] = rs.getObject("marca");
                fila[3] = rs.getObject("cant");
                //pro.setCan(rs.getDouble("cant"));
                //pro.setCan_min_pro(rs.getDouble("cant_min"));
                fila[4] = rs.getObject("desc_und");
                if (ofe.precio_oferta(frm_menu.alm.getId(), rs.getInt("idProductos")) == 0.00) {
                    fila[5] = rs.getObject("precio");
                } else {
                    fila[5] = ofe.precio_oferta(frm_menu.alm.getId(), rs.getInt("idProductos"));
                }
//                fila[5] = rs.getObject("precio_venta");
                fila[6] = rs.getObject("cant_min");
                fila[7] = rs.getObject("grado");
                pro.setEst(rs.getString("estado"));
                if (pro.getEst().equals("1")) {
                    if (pro.getCan() > pro.getCan_min_pro()) {
                        fila[8] = "Normal";
                    } else if (pro.getCan() <= pro.getCan_min_pro()) {
                        fila[8] = "Por Terminar";
                    } else if (pro.getCan() == 0.00) {
                        fila[8] = "No Disponible";
                    }
                } else {
                    fila[7] = "No activo";
                }

                frm_ver_prod_alm.mostrar.addRow(fila);
            }
            con.cerrar(st);
            con.cerrar(rs);
            frm_ver_prod_alm.t_productos.setModel(frm_ver_prod_alm.mostrar);
            frm_ver_prod_alm.t_productos.getColumnModel().getColumn(0).setPreferredWidth(10);
            frm_ver_prod_alm.t_productos.getColumnModel().getColumn(1).setPreferredWidth(390);
            frm_ver_prod_alm.t_productos.getColumnModel().getColumn(2).setPreferredWidth(50);
            frm_ver_prod_alm.t_productos.getColumnModel().getColumn(3).setPreferredWidth(20);
            frm_ver_prod_alm.t_productos.getColumnModel().getColumn(4).setPreferredWidth(30);
            frm_ver_prod_alm.t_productos.getColumnModel().getColumn(5).setPreferredWidth(40);
            frm_ver_prod_alm.t_productos.getColumnModel().getColumn(6).setPreferredWidth(40);
            frm_ver_prod_alm.t_productos.getColumnModel().getColumn(7).setPreferredWidth(40);
            frm_ver_prod_alm.t_productos.getColumnModel().getColumn(8).setPreferredWidth(40);
            frm_ver_prod_alm.mostrar.fireTableDataChanged();

        } catch (SQLException e) {
            System.out.print(e + " en: " + e.getLocalizedMessage());
        }
    }

    private void btn_add_proActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_add_proActionPerformed
        frm_ver_prod_alm prod_alm = new frm_ver_prod_alm();
        Integer ida = frm_menu.alm.getId();
        String alma = frm_menu.alm.getNom();
        String prod = "select p.idProductos, p.desc_pro, p.modelo, p.serie, p.marca, pa.cant, p.cant_min, pa.precio, p.estado, c.desc_clas, "
                + "u.desc_und, p.grado from producto_almacen as pa inner join productos as p on pa.idProductos=p.idProductos inner join clasificacion as "
                + "c on p.id_clas=c.id_clas inner join und_medida as u on p.idUnd_Medida=u.idUnd_Medida where pa.idAlmacen = '" + frm_menu.alm.getId() + "'";
        pro.mostrar_productos(prod);
        prod_alm.txt_ida.setText(ida.toString());
        prod_alm.txt_noma.setText(alma);
        frm_ver_prod_alm.t_productos.setDefaultRenderer(Object.class, new table_render());
        prod_alm.funcion = "venta";
        ven.llamar_ventana(prod_alm);

    }//GEN-LAST:event_btn_add_proActionPerformed

    private void btn_regActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_regActionPerformed
        frm_fin_venta venta = new frm_fin_venta();
        frm_fin_venta.lbl_tot.setText("S/. " + formato.format(tot));
        frm_fin_venta.total = tot;
        System.out.println(tot);
        frm_fin_venta.txt_doc.setText(cbx_tipd.getSelectedItem().toString());
        tipo_venta = cbx_tip_venta.getSelectedItem().toString();
        frm_fin_venta.modo = cbx_tip_venta.getSelectedItem().toString();
        ven.llamar_ventana(venta);

    }//GEN-LAST:event_btn_regActionPerformed

    private void btn_cam_canActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cam_canActionPerformed
        Double cantidad = Double.parseDouble(JOptionPane.showInputDialog("Ingrese Cantidad"));
        t_detalle.setValueAt(cantidad, i, 3);
        subtotal();
        total();
        txt_id.requestFocus();
    }//GEN-LAST:event_btn_cam_canActionPerformed

    private void t_detalleMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_t_detalleMousePressed
        i = t_detalle.getSelectedRow();
        btn_cam_can.setEnabled(true);
        btn_eli.setEnabled(true);
        btn_cpre.setEnabled(true);
    }//GEN-LAST:event_t_detalleMousePressed

    private void btn_descActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_descActionPerformed
        descuento = Double.parseDouble(JOptionPane.showInputDialog("Ingrese Monto a Descontar"));
        txt_des.setText(formato.format(descuento));
        subtotal();
        total();
        txt_id.requestFocus();
    }//GEN-LAST:event_btn_descActionPerformed

    private void btn_cpreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cpreActionPerformed
        Double precio = Double.parseDouble(JOptionPane.showInputDialog("Ingrese Precio"));
        try {
            Statement st = con.conexion();
            String pre_com = "select costo_compra from productos where idProductos='" + t_detalle.getValueAt(i, 0) + "'";
            ResultSet rs = con.consulta(st, pre_com);
            if (rs.next()) {
                if (precio > rs.getDouble("costo_compra")) {
                    t_detalle.setValueAt(precio, i, 5);
                    subtotal();
                    total();
                    txt_id.requestFocus();
                } else {
                    JOptionPane.showMessageDialog(null, "No se puede establecer ese precio");
                    txt_id.requestFocus();
                }
            }
            con.cerrar(st);
            con.cerrar(rs);
        } catch (SQLException | HeadlessException e) {
            JOptionPane.showMessageDialog(null, "Error " + e.getLocalizedMessage());
        }


    }//GEN-LAST:event_btn_cpreActionPerformed

    private void btn_eliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_eliActionPerformed
        detalle.removeRow(i);
        subtotal();
        total();
        txt_id.requestFocus();
    }//GEN-LAST:event_btn_eliActionPerformed

    private void txt_idKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_idKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_F2) {
            btn_add_pro.doClick();
        }
        
        txt_desc.setText(txt_id.getText()); 
        if (Character.isLetter(evt.getKeyChar())) {
            txt_desc.setEditable(true);
            
            txt_id.setText("");
            txt_desc.requestFocus();
        }
        if (evt.getKeyCode() == KeyEvent.VK_F1) {
            String idpro = txt_id.getText();
            Integer idalm = frm_menu.alm.getId();
            try {
                Statement st1 = con.conexion();
                String query = "select p.desc_pro, p.grado, p.marca, p.modelo, p.serie from productos as p inner join producto_almacen"
                        + " as a on a.idProductos=p.idProductos where a.idProductos = '" + idpro + "' and idAlmacen = '" + idalm + "'";
                ResultSet rs1 = con.consulta(st1, query);
                if (rs1.next()) {
                    txt_desc.setText(rs1.getString("desc_pro") + " - " + rs1.getString("modelo") + " - " + rs1.getString("serie") + " - " + rs1.getString("marca"));
                    txt_id.requestFocus();
                } else {
                    txt_desc.setText("EL PRODUCTO NO EXISTE");
                    txt_id.setText("");
                    txt_id.requestFocus();
                }
                con.cerrar(rs1);
                con.cerrar(st1);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.getLocalizedMessage());
                System.err.println("Error");
            }
        }
        ///agregado de  productos
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (txt_id.getText().length() != 0) {
                Integer idpro = Integer.parseInt(txt_id.getText());
                Integer idalm = frm_menu.alm.getId();

                try {

                    Statement st1 = con.conexion();
                    String query = "select a.idProductos, p.desc_pro, p.grado, p.marca, p.modelo, p.serie, p.cant_actual, p.cant_min, a.cant, a.precio, p.estado, u.desc_und "
                            + "from producto_almacen as a inner join productos as p on a.idProductos=p.idProductos "
                            + "inner join und_medida as u on p.idUnd_medida=u.idUnd_medida where p.idProductos = '" + idpro + "' and a.idAlmacen = '" + idalm + "' order by p.desc_pro asc";
                    ResultSet rs1 = con.consulta(st1, query);
                    if (rs1.next()) {
                        Object fila[] = new Object[6];
                        fila[0] = rs1.getInt("idProductos");  //COD PRO
                        fila[1] = rs1.getString("desc_pro") + " - " + rs1.getString("modelo") + " - " + rs1.getString("serie"); // DESCRIPCION
                        fila[2] = rs1.getString("marca");           //MARCA
                        fila[3] = "1.00";                        // CANTIDAD
                        fila[4] = rs1.getString("u.desc_und"); // UND MED
                        Double cant_dis = rs1.getDouble("cant");
                        Integer filas_tabla;

                        //ENVIAR DATOS A FORMULARIO VENTA
                        filas_tabla = t_detalle.getRowCount();
                        if (cant_dis > 0) {
                            if (frm_reg_venta.cbx_tipd.getSelectedItem().equals("BOLETA") || frm_reg_venta.cbx_tipd.getSelectedItem().equals("NOTA DE VENTA") && filas_tabla <= 5) {

                                Integer copiado = 0;

                                if (filas_tabla > 0) {
                                    for (int x = 0; x < filas_tabla; x++) {
                                        Integer id_pro_tabla;
                                        id_pro_tabla = (Integer) t_detalle.getValueAt(x, 0);

                                        if (id_pro_tabla == idpro) {
                                            copiado++;
                                        }
                                    }

                                    if (copiado == 0) {
                                        if (ofe.precio_oferta(frm_menu.alm.getId(), rs1.getInt("idProductos")) == 0.00) {
                                            fila[5] = rs1.getDouble("precio");
                                        } else {
                                            fila[5] = ofe.precio_oferta(frm_menu.alm.getId(), rs1.getInt("idProductos"));
                                        }
                                        detalle.addRow(fila);
                                        txt_id.setText("");
                                        txt_id.requestFocus();
                                        btn_desc.setEnabled(true);
                                        subtotal();
                                        total();
                                    } else {
                                        JOptionPane.showMessageDialog(null, "Se esta escogiendo un producto ya existente");
                                        txt_id.setText("");
                                        txt_id.requestFocus();
                                    }

                                } else {
                                    if (ofe.precio_oferta(frm_menu.alm.getId(), rs1.getInt("idProductos")) == 0.00) {
                                        fila[5] = rs1.getDouble("precio");
                                    } else {
                                        fila[5] = ofe.precio_oferta(frm_menu.alm.getId(), rs1.getInt("idProductos"));
                                    }
                                    detalle.addRow(fila);
                                    txt_id.setText("");
                                    txt_id.requestFocus();
                                    btn_desc.setEnabled(true);
                                    subtotal();
                                    total();
                                }
                            } else if (frm_reg_venta.cbx_tipd.getSelectedItem().equals("FACTURA") && filas_tabla <= 10) {

                                Integer copiado = 0;
                                if (filas_tabla > 0) {
                                    for (int x = 0; x < filas_tabla; x++) {
                                        Integer id_pro_tabla;
                                        id_pro_tabla = Integer.parseInt(frm_reg_venta.t_detalle.getValueAt(x, 0).toString());

                                        if (id_pro_tabla == idpro) {
                                            copiado++;
                                        }
                                    }
                                    if (copiado == 0) {
                                        fila[5] = rs1.getDouble("precio"); // PRECIO VENTA
                                        detalle.addRow(fila);
                                        txt_id.setText("");
                                        txt_id.requestFocus();
                                        btn_desc.setEnabled(true);
                                        subtotal();
                                        total();

                                    } else {
                                        JOptionPane.showMessageDialog(null, "Se esta escogiendo un producto ya existente");
                                        txt_id.setText("");
                                        txt_id.requestFocus();
                                    }

                                } else {
                                    fila[5] = rs1.getDouble("precio"); // PRECIO VENTA
                                    detalle.addRow(fila);
                                    txt_id.setText("");
                                    txt_id.requestFocus();
                                    btn_desc.setEnabled(true);
                                    subtotal();
                                    total();

                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "No hay espacio para continuar agregando productos");
                                txt_id.setText("");
                                txt_id.requestFocus();
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "No existe suficiente producto para la operacion");
                            txt_id.setText("");
                            txt_id.requestFocus();
                        }

                    } else {
                        txt_desc.setText("EL PRODUCTO NO EXISTE");
                        txt_id.setText("");
                        txt_id.requestFocus();
                    }

                } catch (SQLException ex) {
                    System.out.println(ex);
                }
            }
            txt_desc.setText("");
        }
        if (evt.getKeyCode() == KeyEvent.VK_F3) {
            cbx_tip_venta.setEnabled(true);
            cbx_tip_venta.requestFocus();
        }

    }//GEN-LAST:event_txt_idKeyPressed

    private void txt_idKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_idKeyTyped
        /*        char car = evt.getKeyChar();
         if ((car < '0' || car > '9')) {
         evt.consume();
         }*/
    }//GEN-LAST:event_txt_idKeyTyped

    private void t_detalleFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_t_detalleFocusLost
        btn_cam_can.setEnabled(false);
        btn_cpre.setEnabled(false);
        btn_desc.setEnabled(false);
        btn_eli.setEnabled(false);
    }//GEN-LAST:event_t_detalleFocusLost

    private void btn_loadKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btn_loadKeyPressed

    }//GEN-LAST:event_btn_loadKeyPressed

    private void btn_loadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_loadActionPerformed

    }//GEN-LAST:event_btn_loadActionPerformed

    private void cbx_tipdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbx_tipdKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (cbx_tipd.getSelectedItem().equals("NOTA DE VENTA")) {
                if (cbx_tip_venta.getSelectedItem().equals("VENTA")) {
                    txt_nro_doc.setText("00000000");
                    txt_fec.setEditable(true);
                    txt_fec.requestFocus();
                    txt_fec.setBackground(Color.red);
                    txt_fec.setForeground(Color.white);
                } else {
                    txt_nro_doc.setEditable(true);
                    txt_nro_doc.requestFocus();
                    txt_nro_doc.setBackground(Color.red);
                    txt_nro_doc.setForeground(Color.white);
                }
            } else if (cbx_tipd.getSelectedItem().equals("GUIA DE REMISION")) {
                JOptionPane.showMessageDialog(null, "Error");
            } else {
                txt_nro_doc.setEditable(true);
                txt_nro_doc.requestFocus();
                txt_nro_doc.setBackground(Color.red);
                txt_nro_doc.setForeground(Color.white);
            }
        }

    }//GEN-LAST:event_cbx_tipdKeyPressed

    private void formInternalFrameActivated(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameActivated
        txt_id.setEditable(true);
        btn_add_pro.setEnabled(true);
        txt_id.requestFocus();
    }//GEN-LAST:event_formInternalFrameActivated


    private void txt_nro_docKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_nro_docKeyPressed
        if (txt_nro_doc.getText().length() == 8 || txt_nro_doc.getText().length() == 11) {
            if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

                if (cbx_tipd.getSelectedItem().equals("BOLETA") && tot > 750) {
                    if (txt_nro_doc.getText().equals("00000000")) {
                        txt_nro_doc.setText("");
                        txt_nro_doc.requestFocus();
                    }
                }

                if (!txt_nro_doc.getText().isEmpty()) {
                    cli.setNro_doc(txt_nro_doc.getText());
                    try {
                        Statement st = con.conexion();
                        String ver_pro = "select * from cliente where nro_doc = '" + cli.getNro_doc() + "'";
                        ResultSet rs = con.consulta(st, ver_pro);
                        if (rs.next()) {
                            txt_nro_doc.setText(rs.getString("nro_doc"));
                            txt_nom.setText(rs.getString("nom_per"));
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
                            ven.llamar_ventana(cliente);
                            frm_reg_cliente.ventana = "reg_venta";
                            if (txt_nro_doc.getText().length() == 8) {
                                frm_reg_cliente.cbx_cli.setSelectedItem("DNI");
                                frm_reg_cliente.txt_ndoc.setText(cli.getNro_doc());
                                frm_reg_cliente.txt_ndoc.setEditable(false);
                                frm_reg_cliente.txt_nom.setEditable(true);
                                frm_reg_cliente.txt_nom.requestFocus();
                            } else if (txt_nro_doc.getText().length() == 11) {
                                frm_reg_cliente.cbx_cli.setSelectedItem("RUC");
                                frm_reg_cliente.txt_ndoc.setText(cli.getNro_doc());
                                frm_reg_cliente.txt_ndoc.setEditable(false);
                                frm_reg_cliente.txt_nom.setEditable(true);
                                frm_reg_cliente.txt_nom.requestFocus();
                            }
                        }
                    } catch (SQLException ex) {
                        System.out.print(ex);
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Se cargaran numeros");
                    txt_nro_doc.setText("00000000");
                    txt_nro_doc.requestFocus();
                }

            }
        }
    }//GEN-LAST:event_txt_nro_docKeyPressed

    private void txt_nomKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_nomKeyPressed

    }//GEN-LAST:event_txt_nomKeyPressed

    private void cbx_tip_ventaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbx_tip_ventaKeyPressed

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cbx_tipd.setEnabled(true);
            cbx_tipd.requestFocus();
        }

    }//GEN-LAST:event_cbx_tip_ventaKeyPressed

    private void txt_fecKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_fecKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (txt_fec.getText().trim().length() == 10) {
                btn_reg.setEnabled(true);
                btn_reg.requestFocus();
                txt_fec.setBackground(Color.white);
                txt_fec.setForeground(Color.black);
                btn_reg.doClick();
            } else {
                txt_fec.requestFocus();
            }
        }
    }//GEN-LAST:event_txt_fecKeyPressed

    private void txt_nro_docKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_nro_docKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_nro_docKeyReleased

    private void txt_desActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_desActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_desActionPerformed

    private void txt_nro_docKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_nro_docKeyTyped
        if (cbx_tipd.getSelectedItem().equals("BOLETA")) {
            if (txt_nro_doc.getText().length() == 8) {
                evt.consume();
            }
        } else if (cbx_tipd.getSelectedItem().equals("FACTURA")) {
            if (txt_nro_doc.getText().length() == 11) {
                evt.consume();
            }
        }
    }//GEN-LAST:event_txt_nro_docKeyTyped

    private void t_detalleKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_t_detalleKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Double precio = Double.parseDouble(t_detalle.getValueAt(i, 5).toString());
            try {
                Statement st = con.conexion();
                String pre_com = "select costo_compra from productos where idProductos='" + t_detalle.getValueAt(i, 0) + "'";
                ResultSet rs = con.consulta(st, pre_com);
                if (rs.next()) {
                    Double precio_b = rs.getDouble("costo_compra");
                    if (precio > precio_b) {
                        t_detalle.setValueAt(precio, i, 5);
                        subtotal();
                        total();
                        txt_id.requestFocus();
                    } else {
                        JOptionPane.showMessageDialog(null, "No se puede establecer ese precio");
                        txt_id.requestFocus();
                        t_detalle.setValueAt(precio_b + 2, i, 5);
                        subtotal();
                        total();
                    }
                }
                con.cerrar(st);
                con.cerrar(rs);
            } catch (SQLException | HeadlessException e) {
                JOptionPane.showMessageDialog(null, "Error " + e.getLocalizedMessage());
            }
            //txt_id.requestFocus();
            cbx_tip_venta.setEnabled(true);
            cbx_tip_venta.requestFocus();
        }

        if (evt.getKeyCode() == KeyEvent.VK_PLUS) {
            btn_cam_can.doClick();
        }

        if (evt.getKeyCode() == KeyEvent.VK_DELETE) {
            detalle.removeRow(i);
            subtotal();
            total();
            txt_id.requestFocus();
        }
    }//GEN-LAST:event_t_detalleKeyPressed


    private void txt_fecKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_fecKeyTyped

    }//GEN-LAST:event_txt_fecKeyTyped

    private void t_detalleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_t_detalleMouseClicked

        if (t_detalle.getSelectedColumn() == 3) {
            t_detalle.setValueAt("", i, 3);
        } else if (t_detalle.getSelectedColumn() == 5) {
            t_detalle.setValueAt("", i, 5);
        }

    }//GEN-LAST:event_t_detalleMouseClicked

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed

    }//GEN-LAST:event_formKeyPressed

    private void txt_idKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_idKeyReleased
    
    }//GEN-LAST:event_txt_idKeyReleased

    private void txt_descKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_descKeyReleased
        try {
            TextAutoCompleter autocopletar = new TextAutoCompleter(txt_desc);
            autocopletar.setMode(0);            
            String busca = txt_desc.getText();
            Statement st = con.conexion();
            String sql = "select pa.idProductos,p.desc_pro, p.marca, p.modelo, p.serie, pa.cant, pa.precio"
                    + " from producto_almacen as pa inner join productos as p"
                    + " on pa.idProductos=p.idProductos where pa.idAlmacen ='" + frm_menu.alm.getId() + "'"
                    + " and (p.desc_pro like '%" + busca + "%' or p.marca like '%" + busca + "%' or p.modelo like '%" + busca + "%')";
            ResultSet rs = con.consulta(st, sql);
            while (rs.next()) {

                autocopletar.addItem(rs.getString("pa.idProductos") + " - " + rs.getString("p.desc_pro") + " - " + rs.getString("p.marca") + " - " + rs.getString("p.modelo"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error");
        }
    }//GEN-LAST:event_txt_descKeyReleased

    private void txt_descKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_descKeyPressed
        String cap = txt_desc.getText();
        boolean estado = false;
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            for (int j = 0; j < cap.length(); j++) {
                if (cap.charAt(j) == '-') {
                    estado = true;
                }
            }
            if (estado) {
                String[] cod = cap.split("-");
                String temp = cod[0];
                txt_id.setText("");
                txt_id.setText(temp.trim());
                txt_desc.setText("");
                txt_id.requestFocus();
            }
        }
    }//GEN-LAST:event_txt_descKeyPressed

    private void txt_descFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_descFocusGained
        txt_id.setText("");
    }//GEN-LAST:event_txt_descFocusGained


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JButton btn_add_pro;
    private javax.swing.JButton btn_cam_can;
    public static javax.swing.JButton btn_clo;
    private javax.swing.JButton btn_cpre;
    public static javax.swing.JButton btn_desc;
    private javax.swing.JButton btn_eli;
    public static javax.swing.JButton btn_reg;
    private javax.swing.JLabel cbx_alm;
    public static javax.swing.JComboBox cbx_tip_venta;
    public static javax.swing.JComboBox cbx_tipd;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    public static javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JLabel lbl_tot;
    private javax.swing.JLabel lblcod_alm1;
    public static javax.swing.JTable t_detalle;
    public static javax.swing.JTextField txt_des;
    private javax.swing.JTextField txt_desc;
    public static javax.swing.JFormattedTextField txt_fec;
    public static javax.swing.JTextField txt_id;
    public static javax.swing.JTextField txt_igv;
    public static javax.swing.JTextField txt_nom;
    public static javax.swing.JTextField txt_nro_doc;
    public static javax.swing.JTextField txt_subt;
    // End of variables declaration//GEN-END:variables
}
