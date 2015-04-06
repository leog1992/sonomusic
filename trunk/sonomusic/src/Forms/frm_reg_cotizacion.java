
package Forms;

import Clases.Cl_Conectar;
import Clases.Cl_Cotizacion;
import Clases.Cl_Cliente;
import Clases.Cl_Usuario;
import Clases.Cl_Varios;
import Vistas.frm_ver_cliente;
import Vistas.frm_ver_cotizacion;
import Vistas.frm_ver_productos;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import sonomusic.frm_menu;

/**
 *
 * @author pc
 */
public class frm_reg_cotizacion extends javax.swing.JInternalFrame {
Cl_Conectar con = new Cl_Conectar();
Cl_Varios ven = new Cl_Varios();
Cl_Cotizacion cot = new Cl_Cotizacion();
Cl_Cliente per = new Cl_Cliente();
Cl_Usuario usu = new Cl_Usuario();
public static DefaultTableModel detalle;
public static double sub = 0.00;
public static double igv = 0.00;
public static double tot = 0.00;
public static DecimalFormat formato = new DecimalFormat("####.00");
Integer i;
    /**
     * Creates new form frm_reg_cotizacion
     */
    public frm_reg_cotizacion() {
        initComponents();
        
        txt_fec.setText(ven.fechaformateada(ven.getFechaActual()));
        detalle = new DefaultTableModel()
        
 {@Override
     public boolean isCellEditable (int fila, int columna) {
         return false;
     }
 };
        detalle.addColumn("Id");
        detalle.addColumn("Producto");
        detalle.addColumn("Marca");
        detalle.addColumn("Precio");
        detalle.addColumn("Cantidad");
        t_productos.setModel(detalle);
        t_productos.getColumnModel().getColumn(0).setPreferredWidth(10);
        t_productos.getColumnModel().getColumn(1).setPreferredWidth(250);
        t_productos.getColumnModel().getColumn(2).setPreferredWidth(30);
        t_productos.getColumnModel().getColumn(3).setPreferredWidth(30);        
        t_productos.getColumnModel().getColumn(4).setPreferredWidth(50);        
        detalle.fireTableDataChanged();
        txt_user.setText(frm_menu.lbl_user.getText());
    }

    public static void subtotal () {
        int totalRow= t_productos.getRowCount(); 
        double suma_sub = 0.00;
        for(int i=0;i<totalRow;i++)
        {
             suma_sub+= Double.parseDouble(String.valueOf(t_productos.getValueAt(i,3))) * Double.parseDouble(String.valueOf(t_productos.getValueAt(i,4)));
             
        }
        if (txt_doc.getText().length()==8) {
            sub = suma_sub/1.18;
        } else {
            sub = suma_sub * 1.18;
        }
        txt_sub.setText(formato.format(sub));
    }
    
    public static void total () {
        igv =  sub  * 0.18;
        tot = sub  + igv;
        txt_igv.setText(formato.format(igv));
        txt_tot.setText(formato.format(tot));
    }
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txt_doc = new javax.swing.JTextField();
        txt_nom = new javax.swing.JTextField();
        btn_bus = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txt_tel = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        t_productos = new javax.swing.JTable();
        txt_tot = new javax.swing.JTextField();
        btn_cc = new javax.swing.JButton();
        txt_sub = new javax.swing.JTextField();
        txt_igv = new javax.swing.JTextField();
        btn_cp = new javax.swing.JButton();
        btn_el = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        btn_cer = new javax.swing.JButton();
        btn_reg = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        btn_add = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txt_user = new javax.swing.JTextField();
        txt_fec = new javax.swing.JFormattedTextField();

        setBackground(new java.awt.Color(254, 254, 254));
        setClosable(true);
        setTitle("Registrar Cotizacion");

        jLabel1.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(212, 2, 2));
        jLabel1.setText("Cliente:");

        txt_doc.setEditable(false);
        txt_doc.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        txt_nom.setEditable(false);

        btn_bus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/find.png"))); // NOI18N
        btn_bus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_busActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(212, 2, 2));
        jLabel2.setText("Fecha:");

        jLabel3.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(212, 2, 2));
        jLabel3.setText("Telefono:");

        txt_tel.setEditable(false);
        txt_tel.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jScrollPane1.setBackground(new java.awt.Color(254, 254, 254));

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
        t_productos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                t_productosMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(t_productos);

        txt_tot.setEditable(false);

        btn_cc.setText("+/-");
        btn_cc.setEnabled(false);
        btn_cc.setMargin(new java.awt.Insets(2, 2, 2, 2));
        btn_cc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ccActionPerformed(evt);
            }
        });

        txt_sub.setEditable(false);

        txt_igv.setEditable(false);

        btn_cp.setText("S/.");
        btn_cp.setEnabled(false);
        btn_cp.setMargin(new java.awt.Insets(2, 2, 2, 2));
        btn_cp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cpActionPerformed(evt);
            }
        });

        btn_el.setText("X");
        btn_el.setEnabled(false);
        btn_el.setMargin(new java.awt.Insets(2, 2, 2, 2));
        btn_el.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_elActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(212, 2, 2));
        jLabel10.setText("Total");

        jLabel9.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(212, 2, 2));
        jLabel9.setText("IGV");

        btn_cer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/cancel.png"))); // NOI18N
        btn_cer.setText("Cerrar");
        btn_cer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cerActionPerformed(evt);
            }
        });

        btn_reg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/cart.png"))); // NOI18N
        btn_reg.setText("Registrar");
        btn_reg.setEnabled(false);
        btn_reg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_regActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(212, 2, 2));
        jLabel8.setText("Sub Total");

        btn_add.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/add.png"))); // NOI18N
        btn_add.setText("Agregar Producto");
        btn_add.setEnabled(false);
        btn_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_addActionPerformed(evt);
            }
        });
        btn_add.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btn_addKeyPressed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(212, 2, 2));
        jLabel4.setText("Usuario");

        txt_user.setEditable(false);

        txt_fec.setEditable(false);
        try {
            txt_fec.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txt_fec.setHorizontalAlignment(javax.swing.JTextField.CENTER);

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
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_doc, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txt_fec, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(50, 50, 50)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txt_tel, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(79, 79, 79)
                                .addComponent(btn_add, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_nom)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_bus))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10)
                            .addComponent(jLabel8))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_tot, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_igv, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_sub, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_cc, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(61, 61, 61)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btn_cp, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btn_el, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txt_user, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btn_cer, javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)
                            .addComponent(btn_reg, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addGap(6, 6, 6)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_bus, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_doc, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_nom, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_tel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_add, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_fec, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(btn_cc, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_cp, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_el, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(txt_igv, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btn_reg, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_sub, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_tot, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btn_cer)
                        .addComponent(jLabel4)
                        .addComponent(txt_user, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_ccActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ccActionPerformed
    Double cantidad = Double.parseDouble(JOptionPane.showInputDialog("Ingrese Cantidad"));
    t_productos.setValueAt(cantidad, i, 4);
    subtotal();
    total();
    }//GEN-LAST:event_btn_ccActionPerformed

    private void btn_cpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cpActionPerformed
    Double precio = Double.parseDouble(JOptionPane.showInputDialog("Ingrese Precio"));
    t_productos.setValueAt(precio, i, 3);
    subtotal();
    total();
    }//GEN-LAST:event_btn_cpActionPerformed

    private void btn_elActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_elActionPerformed
        detalle.removeRow(i); 
        subtotal();
        total();
    }//GEN-LAST:event_btn_elActionPerformed

    private void btn_cerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cerActionPerformed
        frm_ver_cotizacion cotizacion = new frm_ver_cotizacion();
        ven.llamar_ventana(cotizacion);
        this.dispose();
    }//GEN-LAST:event_btn_cerActionPerformed

    private void llenar () {
        cot.setFec_cot(ven.fechabase(txt_fec.getText()));
        per.setNro_doc(txt_doc.getText());
        usu.setNick(txt_user.getText());
    }
    
    private void btn_regActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_regActionPerformed
    llenar ();
    
    //insertar cotizacion
    try {
    Statement st = con.conexion();
    String insert_cot = "insert cotizacion Values (null, '"+cot.getFec_cot()+"', '"+cot.getEst_cot()+"', "
            + "'"+per.getNro_doc()+"', '"+usu.getNick()+"')";
    con.actualiza(st, insert_cot);
    con.cerrar(st);
    } catch (Exception ex) {
        System.out.print(ex);
    }
    //buscar ultima cotizacion 
    try {
    Statement st = con.conexion();
    String buscar_cot = "select idCotizacion from cotizacion where nick = '"+usu.getNick()+"' order by idCotizacion desc";
    ResultSet rs = con.consulta(st, buscar_cot);
    if (rs.next()) {
        cot.setId(rs.getInt("idCotizacion"));
    }
    con.cerrar(rs);
    con.cerrar(st);
    } catch (SQLException ex) {
       System.out.print(ex);
    }
    
    //insertar productos en cotizacion
        int filas = t_productos.getRowCount();
        for (int j = 0; j <= (filas-1); j++) { 
        String idPro = t_productos.getValueAt(j, 0).toString();
        Double cantidad = Double.parseDouble(t_productos.getValueAt(j, 4).toString());
        Double precio = Double.parseDouble(t_productos.getValueAt(j, 3).toString());
        try {
        Statement st = con.conexion();
        String ins_det_cot = "insert into Detalle_Cotizacion Values ('"+cot.getId()+"', "
                + "'"+idPro+"', '"+cantidad+"', '"+precio+"')";
        con.actualiza(st, ins_det_cot);
        con.cerrar(st);  
        System.out.print("insertando producto: id: " + idPro + " Cantidad: " + cantidad + " Precio: " + precio + "\n");
        } catch (Exception ex) {
            System.out.print(ex);
        }
        }    
        
    //mostrar cotizacion 
        /*Connection st = con.conx();
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("cot", cot.getId());
        
        try {
            JasperReport jasperReport;
            JasperPrint jasperPrint;
            jasperReport = JasperCompileManager.compileReport("Reports//rpt_cotizacion.jrxml");
            jasperPrint = JasperFillManager.fillReport(
            jasperReport, parametros, st);
            JasperExportManager.exportReportToPdfFile(
            jasperPrint, "Reports/rpt_cotizacion_"+cot.getId()+".pdf");

                try {
                    File file = new File("Reports/rpt_cotizacion_"+cot.getId()+".pdf");
                    Desktop.getDesktop().open(file);
                } catch(IOException e) {
                    System.out.print(e);
                    JOptionPane.showMessageDialog(null, e);
                }
                
        } catch (JRException ex) {
            System.out.print(ex);
            JOptionPane.showMessageDialog(null, ex);
            
        }*/

        btn_cer.doClick();
    }//GEN-LAST:event_btn_regActionPerformed

    private void btn_busActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_busActionPerformed
        frm_ver_cliente cliente = new frm_ver_cliente();
        cliente.ventana = "cotizacion";
        ven.llamar_ventana(cliente);
    }//GEN-LAST:event_btn_busActionPerformed

    private void btn_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_addActionPerformed
        frm_ver_productos productos = new frm_ver_productos();
        productos.ventana = "cotizacion";
        ven.llamar_ventana(productos);
        
    }//GEN-LAST:event_btn_addActionPerformed

    private void t_productosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_t_productosMousePressed
        i = t_productos.getSelectedRow();
        btn_cc.setEnabled(true);
        btn_cp.setEnabled(true);
        btn_el.setEnabled(true);
    }//GEN-LAST:event_t_productosMousePressed

    private void btn_addKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btn_addKeyPressed
        if (evt.getKeyCode()==KeyEvent.VK_ENTER) {
            btn_add.doClick();
        }
    }//GEN-LAST:event_btn_addKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JButton btn_add;
    private javax.swing.JButton btn_bus;
    private javax.swing.JButton btn_cc;
    private javax.swing.JButton btn_cer;
    private javax.swing.JButton btn_cp;
    private javax.swing.JButton btn_el;
    public static javax.swing.JButton btn_reg;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTable t_productos;
    public static javax.swing.JTextField txt_doc;
    private javax.swing.JFormattedTextField txt_fec;
    public static javax.swing.JTextField txt_igv;
    public static javax.swing.JTextField txt_nom;
    public static javax.swing.JTextField txt_sub;
    public static javax.swing.JTextField txt_tel;
    public static javax.swing.JTextField txt_tot;
    private javax.swing.JTextField txt_user;
    // End of variables declaration//GEN-END:variables
}
