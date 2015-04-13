package Forms;

import Clases.Cl_Clasificacion;
import Clases.Cl_Cliente;
import Clases.Cl_Conectar;
import Clases.Cl_Medida;
import Clases.Cl_Productos;
import Clases.Cl_Varios;
import Clases.Clase_CellEditor;
import Clases.Clase_CellRender;
import Vistas.frm_ver_prod_alm_det;
import Vistas.frm_ver_productos;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class frm_reg_productos extends javax.swing.JInternalFrame {

    Cl_Cliente cli = new Cl_Cliente();
    Cl_Varios ven = new Cl_Varios();
    Cl_Conectar con = new Cl_Conectar();
    Cl_Productos pro = new Cl_Productos();
    Cl_Clasificacion cla = new Cl_Clasificacion();
    Cl_Medida med = new Cl_Medida();
    public static String win = "reg";
    public static String ventana = "producto";
    public static String subventana = "producto";
    public static int ida;
    public static int id;

    String filename;
    String name;
    public static DecimalFormat formato = new DecimalFormat("####.00");

    public frm_reg_productos() {
        initComponents();

        String query = "select desc_clas from clasificacion";
        ver_clasificacion(query);
        String und = "select desc_und from und_medida";
        ver_unidad(und);

    }

    private void ver_clasificacion(String query) {
        try {
            Statement st = con.conexion();
            ResultSet rs = con.consulta(st, query);

            while (rs.next()) {
                String fila;
                fila = rs.getString("desc_clas");
                cbx_cla.addItem(fila);
            }
            con.cerrar(st);
            con.cerrar(rs);
        } catch (SQLException e) {
            System.out.print(e);
        }

    }

    private void ver_unidad(String query) {
        try {
            Statement st = con.conexion();
            ResultSet rs = con.consulta(st, query);

            while (rs.next()) {
                String fila;
                fila = rs.getString("desc_und");
                cbx_und.addItem(fila);
            }
            con.cerrar(st);
            con.cerrar(rs);
        } catch (SQLException e) {
            System.out.print(e);
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txt_des = new javax.swing.JTextField();
        txt_mar = new javax.swing.JTextField();
        txt_mod = new javax.swing.JTextField();
        txt_cantm = new javax.swing.JTextField();
        cbx_und = new javax.swing.JComboBox();
        txt_pcom = new javax.swing.JTextField();
        txt_pven = new javax.swing.JTextField();
        cbx_cla = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();
        btn_reg = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        txt_ser = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txt_gan = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txt_cod = new javax.swing.JTextField();
        cbo_gra = new javax.swing.JComboBox();
        txt_com = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(254, 254, 254));
        setClosable(true);
        setTitle("Registrar Productos");
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

        jLabel2.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(212, 2, 2));
        jLabel2.setText("Descripcion");

        jLabel3.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(212, 2, 2));
        jLabel3.setText("Marca:");

        jLabel4.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(212, 2, 2));
        jLabel4.setText("Modelo:");

        jLabel5.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(212, 2, 2));
        jLabel5.setText("Grado:");

        jLabel6.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(212, 2, 2));
        jLabel6.setText("Cantidad Minima:");

        jLabel7.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(212, 2, 2));
        jLabel7.setText("Costo de Compra:");

        jLabel8.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(212, 2, 2));
        jLabel8.setText("Precio de Venta:");

        jLabel1.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(212, 2, 2));
        jLabel1.setText("Clasificacion:");

        jLabel9.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(212, 2, 2));
        jLabel9.setText("Unidad de Medida:");

        txt_des.setForeground(new java.awt.Color(0, 0, 153));
        txt_des.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_desKeyPressed(evt);
            }
        });

        txt_mar.setEditable(false);
        txt_mar.setForeground(new java.awt.Color(0, 0, 153));
        txt_mar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_marKeyPressed(evt);
            }
        });

        txt_mod.setEditable(false);
        txt_mod.setForeground(new java.awt.Color(0, 0, 153));
        txt_mod.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_modKeyPressed(evt);
            }
        });

        txt_cantm.setEditable(false);
        txt_cantm.setForeground(new java.awt.Color(0, 0, 153));
        txt_cantm.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_cantmKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_cantmKeyTyped(evt);
            }
        });

        cbx_und.setForeground(new java.awt.Color(0, 0, 153));
        cbx_und.setEnabled(false);
        cbx_und.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbx_undActionPerformed(evt);
            }
        });
        cbx_und.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cbx_undKeyPressed(evt);
            }
        });

        txt_pcom.setEditable(false);
        txt_pcom.setForeground(new java.awt.Color(0, 0, 153));
        txt_pcom.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_pcomKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_pcomKeyTyped(evt);
            }
        });

        txt_pven.setEditable(false);
        txt_pven.setForeground(new java.awt.Color(0, 0, 153));
        txt_pven.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_pvenActionPerformed(evt);
            }
        });
        txt_pven.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_pvenKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_pvenKeyTyped(evt);
            }
        });

        cbx_cla.setForeground(new java.awt.Color(0, 0, 153));
        cbx_cla.setEnabled(false);
        cbx_cla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbx_claActionPerformed(evt);
            }
        });
        cbx_cla.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cbx_claKeyPressed(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/cancel.png"))); // NOI18N
        jButton1.setText("Cerrar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btn_reg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/add.png"))); // NOI18N
        btn_reg.setText("Registrar");
        btn_reg.setEnabled(false);
        btn_reg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_regActionPerformed(evt);
            }
        });
        btn_reg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btn_regKeyPressed(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/asterisk_orange.png"))); // NOI18N
        jButton3.setText("Limpiar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(212, 2, 2));
        jLabel10.setText("Serie:");

        txt_ser.setEditable(false);
        txt_ser.setForeground(new java.awt.Color(0, 0, 153));
        txt_ser.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_serKeyPressed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(212, 2, 2));
        jLabel12.setText("Ganancia:");

        txt_gan.setEditable(false);
        txt_gan.setForeground(new java.awt.Color(0, 0, 204));
        txt_gan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_ganKeyPressed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(212, 2, 2));
        jLabel13.setText("Codigo:");

        txt_cod.setEditable(false);
        txt_cod.setForeground(new java.awt.Color(0, 0, 153));
        txt_cod.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_codKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_codKeyTyped(evt);
            }
        });

        cbo_gra.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "NUEVO", "SEGUNDA", "REPARADO", " " }));
        cbo_gra.setEnabled(false);
        cbo_gra.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cbo_graKeyPressed(evt);
            }
        });

        txt_com.setEditable(false);
        txt_com.setForeground(new java.awt.Color(0, 0, 204));
        txt_com.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_comKeyPressed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(212, 2, 2));
        jLabel14.setText("Comision:");

        jLabel11.setText("%");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txt_mar, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_mod, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txt_ser, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cbo_gra, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 96, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txt_des)
                        .addContainerGap())))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_reg)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(52, 52, 52)
                                .addComponent(jLabel13)
                                .addGap(18, 18, 18)
                                .addComponent(txt_cod, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel7)
                                        .addComponent(jLabel6))
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txt_cantm, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txt_pcom, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(99, 99, 99)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(cbx_und, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txt_pven, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txt_gan, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(144, 144, 144)
                                        .addComponent(jLabel14)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txt_com, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel11))
                                    .addComponent(cbx_cla, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jButton3)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_cod, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_des, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_mar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_mod, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_ser, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbo_gra, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_cantm, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbx_und, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_pcom, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_pven, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel14)
                        .addComponent(txt_com, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel12)
                        .addComponent(txt_gan, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbx_cla, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_reg, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
        win = "reg";
        if (ventana.equals("producto")) {
            frm_ver_productos productos = new frm_ver_productos();
            ven.llamar_ventana(productos);
        } else {
            frm_ver_prod_alm_det pro_det = new frm_ver_prod_alm_det();
            ven.llamar_ventana(pro_det);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void ver_productos(String query) {
        try {
            frm_ver_productos prod = null;
            prod.mostrar = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int fila, int columna) {
                    if (columna == 1) {
                        return true;
                    } else {
                        return false;
                    }

                }
            };
            Statement st = con.conexion();
            ResultSet rs = con.consulta(st, query);
            //Establecer como cabezeras el nombre de las colimnas
            prod.mostrar.addColumn("Id");
            prod.mostrar.addColumn("Sel.");
            prod.mostrar.addColumn("Descripcion");//descripcion modelo serie
            prod.mostrar.addColumn("Marca");
            prod.mostrar.addColumn("Precio");
            prod.mostrar.addColumn("Clasificacion");
            prod.mostrar.addColumn("Cant. Actual");
            prod.mostrar.addColumn("Cant. minima");
            prod.mostrar.addColumn("Und. Medida");
            prod.mostrar.addColumn("Grado");
            prod.mostrar.addColumn("Estado");

            //Creando las filas para el JTable
            while (rs.next()) {
                Object[] fila = new Object[11];
                fila[0] = rs.getObject("idProductos");
                fila[1] = Boolean.FALSE;
                fila[2] = rs.getObject("desc_pro") + " - " + rs.getObject("modelo") + " - " + rs.getObject("serie");
                fila[3] = rs.getObject("marca");
                fila[4] = rs.getObject("precio_venta");
                fila[5] = rs.getObject("desc_clas");
                fila[6] = rs.getObject("cant_actual");
                fila[7] = rs.getObject("cant_min");
                fila[8] = rs.getObject("desc_und");
                fila[9] = rs.getObject("grado");
                if (rs.getString("estado").equals("1")) {
                    if (rs.getDouble("cant_actual") > rs.getDouble("cant_min")) {
                        fila[10] = "NORMAL";
                    }
                    if (rs.getDouble("cant_actual") <= rs.getDouble("cant_min")) {
                        fila[10] = "POR TERMINAR";
                    }
                    if (rs.getDouble("cant_actual") <= 0) {
                        fila[10] = "NO DISPONIBLE";
                    }
                } else {
                    fila[10] = "-";
                }

                prod.mostrar.addRow(fila);
            }
            con.cerrar(st);
            con.cerrar(rs);
            prod.t_productos.setModel(prod.mostrar);
            prod.t_productos.getColumnModel().getColumn(0).setPreferredWidth(10);
            prod.t_productos.getColumnModel().getColumn(1).setPreferredWidth(10);
            prod.t_productos.getColumnModel().getColumn(2).setPreferredWidth(390);
            prod.t_productos.getColumnModel().getColumn(3).setPreferredWidth(50);
            prod.t_productos.getColumnModel().getColumn(4).setPreferredWidth(20);
            prod.t_productos.getColumnModel().getColumn(5).setPreferredWidth(30);
            prod.t_productos.getColumnModel().getColumn(6).setPreferredWidth(30);
            prod.t_productos.getColumnModel().getColumn(7).setPreferredWidth(40);
            prod.t_productos.getColumnModel().getColumn(8).setPreferredWidth(40);
            prod.t_productos.getColumnModel().getColumn(9).setPreferredWidth(40);
            prod.t_productos.getColumnModel().getColumn(10).setPreferredWidth(40);
            prod.t_productos.getColumnModel().getColumn(1).setCellEditor(new Clase_CellEditor());
            prod.t_productos.getColumnModel().getColumn(1).setCellRenderer(new Clase_CellRender());
            prod.mostrar.fireTableDataChanged();
            prod.t_productos.updateUI();

        } catch (SQLException e) {
            System.out.print(e);
        }
    }

    private void llenar() {
        pro.setId_pro(id);
        pro.setDes_pro(txt_des.getText());
        pro.setMar_pro(txt_mar.getText());
        pro.setMod_pro(txt_mod.getText());
        pro.setSer_pro(txt_ser.getText());
        pro.setGra_pro(cbo_gra.getSelectedItem().toString());
        pro.setCos_pro(Double.parseDouble(txt_pcom.getText()));
        pro.setPre_pro(Double.parseDouble(txt_pven.getText()));
        cla.setId(cbx_cla.getSelectedIndex() + 1);
        med.setId_med(cbx_und.getSelectedIndex() + 1);
        pro.setCan_min_pro(Double.parseDouble(txt_cantm.getText()));
        pro.setCom_pro(Double.parseDouble(txt_com.getText()));

    }
    private void btn_regActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_regActionPerformed
        llenar();
        if (ventana.equals("producto")) {
            try {
                if (win.equals("reg")) {
                    Statement st = con.conexion();
                    String query = "insert into productos Values (null, '" + pro.getDes_pro() + "', "
                            + "'" + pro.getMar_pro() + "', '" + pro.getMod_pro() + "', '" + pro.getSer_pro() + "', "
                            + "'" + pro.getGra_pro() + "', '" + pro.getCos_pro() + "', '" + pro.getPre_pro() + "',"
                            + "'" + cla.getId() + "', '" + med.getId_med() + "', '" + "0" + "', "
                            + "'" + pro.getCan_min_pro() + "', '" + pro.getEst() + "','" + pro.getCom_pro() + "')";
                    con.actualiza(st, query);
                    con.cerrar(st);
                    JOptionPane.showMessageDialog(null, "Se ingreso los datos correctamente");
                    this.dispose();
                } else {

                    Statement st = con.conexion();
                    String update = "update productos set desc_pro='" + pro.getDes_pro() + "', marca='" + pro.getMar_pro()
                            + "', modelo='" + pro.getMod_pro() + "', serie='" + pro.getSer_pro() + "', "
                            + "grado='" + pro.getGra_pro() + "', costo_compra='" + pro.getCos_pro() + "', precio_venta='" + pro.getPre_pro() + "', "
                            + " id_clas='" + cla.getId() + "', idUnd_medida='" + med.getId_med() + "', "
                            + "cant_min = '" + pro.getCan_min_pro() + "', comision = '" + pro.getCom_pro() + "' where idProductos = '" + pro.getId_pro() + "'";
                    con.actualiza(st, update);
                    con.cerrar(st);
                    win = "reg";
                    JOptionPane.showMessageDialog(null, "Se modifico los datos correctamente");
                    this.dispose();
                }

                frm_ver_productos productos = new frm_ver_productos();
                if (subventana.equals("prod_compra")) {
                    productos.ventana = "compra_prod";
                    String query = "select p.idProductos, p.desc_pro, p.marca, p.modelo, p.serie, p.grado, p.precio_venta, c.desc_clas, u.desc_und, p.cant_actual, p.cant_min, p.estado"
                            + " from productos as p inner join und_medida as u on p.idUnd_medida = u.idUnd_medida inner join clasificacion as c on p.id_clas = c.id_clas  order by p.desc_pro asc";
                    ver_productos(query);
                    productos.btn_enviar.setEnabled(true);
                } else {
                    productos.ventana = "productitos";
                }
                ven.llamar_ventana(productos);

            } catch (Exception e) {
                System.out.println(e);
            }

        } else {
            Statement st = con.conexion();
            String update = "update producto_almacen set precio = '" + pro.getPre_pro() + "' where idProductos = '" + pro.getId_pro() + "' and idAlmacen = '" + ida + "'";
            con.actualiza(st, update);
            con.cerrar(st);
            win = "reg";
            JOptionPane.showMessageDialog(null, "Se modifico los datos correctamente");
            this.dispose();
            frm_ver_prod_alm_det pro_det = new frm_ver_prod_alm_det();
            ven.llamar_ventana(pro_det);
        }
        subventana = "";
        System.out.println(pro.getPre_pro());

    }//GEN-LAST:event_btn_regActionPerformed

    private void txt_desKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_desKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!txt_des.getText().isEmpty()) {
                txt_mar.setEditable(true);
                txt_mar.requestFocus();
            }
        }
    }//GEN-LAST:event_txt_desKeyPressed

    private void txt_marKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_marKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!txt_mar.getText().isEmpty()) {
                txt_mod.setEditable(true);
                txt_mod.requestFocus();
            }
        }
    }//GEN-LAST:event_txt_marKeyPressed

    private void txt_modKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_modKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!txt_mod.getText().isEmpty()) {
                txt_ser.setEditable(true);
                txt_ser.requestFocus();
            }
        }
    }//GEN-LAST:event_txt_modKeyPressed

    private void txt_serKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_serKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!txt_ser.getText().isEmpty()) {
                cbo_gra.setEnabled(true);
                cbo_gra.requestFocus();
            }
        }
    }//GEN-LAST:event_txt_serKeyPressed

    private void txt_cantmKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_cantmKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!txt_cantm.getText().isEmpty()) {
                cbx_und.setEnabled(true);
                cbx_und.requestFocus();
            }
        }
    }//GEN-LAST:event_txt_cantmKeyPressed

    private void cbx_undActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbx_undActionPerformed

    }//GEN-LAST:event_cbx_undActionPerformed

    private void txt_pcomKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_pcomKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!txt_pcom.getText().isEmpty()) {
                txt_pven.setEditable(true);
                txt_pven.requestFocus();
            }
        }
    }//GEN-LAST:event_txt_pcomKeyPressed

    private void txt_pvenKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_pvenKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!txt_pven.getText().isEmpty()) {
                Double pven = Double.parseDouble(txt_pven.getText());
                Double pcom = Double.parseDouble(txt_pcom.getText());
                Double gan = pven - pcom;
                txt_gan.setText(formato.format(gan));
                txt_com.setEditable(true);
                txt_com.requestFocus();

            }
        }
    }//GEN-LAST:event_txt_pvenKeyPressed

    private void cbx_claActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbx_claActionPerformed

    }//GEN-LAST:event_cbx_claActionPerformed

    private void btn_regKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btn_regKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btn_reg.doClick();
        }
    }//GEN-LAST:event_btn_regKeyPressed

    private void txt_codKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_codKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            try {
                pro.setId_pro(Integer.parseInt(txt_cod.getText()));
                Statement st = con.conexion();
                String buscar = "select * from productos where idProductos = '" + pro.getId_pro() + "'";
                ResultSet rs = con.consulta(st, buscar);
                if (rs.next()) {
                    JOptionPane.showMessageDialog(null, "El Producto ya existe, ingrese otro codigo");
                    txt_cod.setText("");
                    txt_cod.requestFocus();
                } else {
                    txt_des.setEditable(true);
                    txt_des.requestFocus();
                }
            } catch (SQLException ex) {
                System.out.print(ex);

            }

        }
    }//GEN-LAST:event_txt_codKeyPressed

    private void txt_codKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_codKeyTyped
        if (txt_cod.getText().length() == 5) {
            evt.consume();
        }
    }//GEN-LAST:event_txt_codKeyTyped

    private void cbo_graKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbo_graKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txt_cantm.setEditable(true);
            txt_cantm.requestFocus();
        }
    }//GEN-LAST:event_cbo_graKeyPressed

    private void txt_ganKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_ganKeyPressed

    }//GEN-LAST:event_txt_ganKeyPressed

    private void txt_pvenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_pvenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_pvenActionPerformed

    private void txt_comKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_comKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cbx_cla.setEnabled(true);
            cbx_cla.requestFocus();
        }
    }//GEN-LAST:event_txt_comKeyPressed

    private void cbx_claKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbx_claKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btn_reg.setEnabled(true);
            btn_reg.requestFocus();
        }
    }//GEN-LAST:event_cbx_claKeyPressed

    private void txt_cantmKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_cantmKeyTyped
        if (Character.isLetter(evt.getKeyChar())) {
            evt.consume();
        }
    }//GEN-LAST:event_txt_cantmKeyTyped

    private void txt_pcomKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_pcomKeyTyped
        if (Character.isLetter(evt.getKeyChar())) {
            evt.consume();
        }
    }//GEN-LAST:event_txt_pcomKeyTyped

    private void txt_pvenKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_pvenKeyTyped
        if (Character.isLetter(evt.getKeyChar())) {
            evt.consume();
        }
    }//GEN-LAST:event_txt_pvenKeyTyped

    private void cbx_undKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbx_undKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txt_pcom.setEditable(true);
            txt_pcom.requestFocus();
        }
    }//GEN-LAST:event_cbx_undKeyPressed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        txt_des.setText("");
        txt_mar.setText("");
        txt_mar.setEditable(false);
        txt_mod.setText("");
        txt_mod.setEditable(false);
        txt_ser.setText("");
        txt_ser.setEditable(false);
        cbo_gra.setSelectedIndex(0);
        cbo_gra.setEnabled(false);
        txt_cantm.setText("");
        txt_cantm.setEditable(false);
        cbx_und.setSelectedIndex(0);
        cbx_und.setEnabled(false);
        txt_pcom.setText("");
        txt_pcom.setEditable(false);
        txt_pven.setText("");
        txt_pven.setEditable(false);
        txt_com.setText("");
        txt_com.setEditable(false);
        cbx_cla.setSelectedIndex(0);
        cbx_cla.setEnabled(false);
        txt_gan.setText("");
        btn_reg.setEnabled(false);
        txt_des.requestFocus();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void formInternalFrameActivated(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameActivated
        txt_des.requestFocus();
    }//GEN-LAST:event_formInternalFrameActivated

//    public class ImagenLOCAL extends javax.swing.JPanel {
//
//        public ImagenLOCAL() {
//            this.setSize(200, 200); //se selecciona el tamaño del panel
//        }
//
//    //Se crea un método cuyo parámetro debe ser un objeto Graphics
//        public void paint(Graphics grafico) {
//            Dimension height = getSize();
//            //Se selecciona la imagen que tenemos en el paquete de la //ruta del programa
//            ImageIcon Img = new ImageIcon(filename);
//            //se dibuja la imagen que tenemos en el paquete Images //dentro de un panel
//            grafico.drawImage(Img.getImage(), 0, 0, height.width, height.height, null);
//            setOpaque(false);
//            super.paintComponent(grafico);
//        }
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JButton btn_reg;
    public static javax.swing.JComboBox cbo_gra;
    public static javax.swing.JComboBox cbx_cla;
    public static javax.swing.JComboBox cbx_und;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    public static javax.swing.JTextField txt_cantm;
    public static javax.swing.JTextField txt_cod;
    public static javax.swing.JTextField txt_com;
    public static javax.swing.JTextField txt_des;
    private javax.swing.JTextField txt_gan;
    public static javax.swing.JTextField txt_mar;
    public static javax.swing.JTextField txt_mod;
    public static javax.swing.JTextField txt_pcom;
    public static javax.swing.JTextField txt_pven;
    public static javax.swing.JTextField txt_ser;
    // End of variables declaration//GEN-END:variables
}
