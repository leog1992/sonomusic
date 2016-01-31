/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

import Clases.Cl_Compra;
import Clases.Cl_Conectar;
import Clases.Cl_Varios;
import Vistas.frm_ver_cuota_compra;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Dereck
 */
public class frm_reg_cuota extends javax.swing.JInternalFrame {

    Cl_Conectar con = new Cl_Conectar();
    Cl_Varios ven = new Cl_Varios();
    public static Cl_Compra com = new Cl_Compra();
    public static String periodo;
    public double monto;
    public String fec_pag;
    public String fec_venc;
    public String est;
    public static int moneda;
    public static String ruc_emp;

    /**
     * Creates new form frm_reg_cuota
     */
    public frm_reg_cuota() {
        initComponents();
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
        txt_monto = new javax.swing.JTextField();
        txt_fec = new javax.swing.JFormattedTextField();
        btn_reg = new javax.swing.JButton();

        setBackground(new java.awt.Color(254, 254, 254));
        setClosable(true);
        setTitle("Registrar Cuota");

        jLabel1.setForeground(new java.awt.Color(255, 0, 0));
        jLabel1.setText("Fecha de Vencimiento");

        jLabel2.setForeground(new java.awt.Color(255, 0, 0));
        jLabel2.setText("Monto:");

        txt_monto.setEditable(false);
        txt_monto.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_monto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_montoKeyPressed(evt);
            }
        });

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
        });

        btn_reg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/add.png"))); // NOI18N
        btn_reg.setText("Agregar");
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txt_fec, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
                    .addComponent(txt_monto))
                .addContainerGap(23, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_reg)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_fec, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_monto, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_reg, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_fecKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_fecKeyPressed
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            if (txt_fec.getText().trim().length() == 10) {
                txt_monto.setEditable(true);
                txt_monto.requestFocus();
            }
        }
    }//GEN-LAST:event_txt_fecKeyPressed

    private void txt_montoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_montoKeyPressed
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            if (txt_monto.getText().length() > 1) {
                btn_reg.setEnabled(true);
                btn_reg.requestFocus();
            }
        }
    }//GEN-LAST:event_txt_montoKeyPressed

    private void llenar() {
        fec_venc = ven.fechabase(txt_fec.getText());
        monto = Double.parseDouble(txt_monto.getText());
    }

    private void btn_regActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_regActionPerformed
        llenar();
        try {
            Statement st = con.conexion();
            String ins_cuota = "insert into pago_compras Values (null, '" + periodo + "','" + com.getId() + "','"+ruc_emp+"',  '" + fec_venc + "', '" + monto + "','7000-01-01', "
                    + "'" + moneda + "', '0.0','0','0')";
            con.actualiza(st, ins_cuota);
            con.cerrar(st);
        } catch (Exception ex) {
            System.out.println(ex);
        }

        frm_ver_cuota_compra cuota = null;
        DefaultTableModel mostrar = null;
        try {
            mostrar = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int fila, int columna) {
                    return false;
                }
            };
            mostrar.addColumn("Nro Cuota");
            mostrar.addColumn("Fec. Venc.");
            mostrar.addColumn("Cuota");
            mostrar.addColumn("Fecha Pago");
            mostrar.addColumn("Moneda");
            mostrar.addColumn("Tipo Cambio");
            mostrar.addColumn("Monto");
            mostrar.addColumn("Estado");

            Statement st = con.conexion();
            String ver_cuotas = "select pc.idpago, pc.fec_venc, pc.fec_pago, pc.monto_cuota, m.simbolo, pc.tc, pc.monto, pc.estado from pago_compras as pc inner join moneda as m "
                    + "on pc.idmon = m.idmoneda where pc.idcompra = '" + com.getId() + "' and pc.periodo = '" + periodo + "' and pc.empresa = '"+ruc_emp+"'";
            ResultSet rs = con.consulta(st, ver_cuotas);
            Double tot_cuotas = 0.0;
            Double tot_monto = 0.0;
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
                mostrar.addRow(fila);
            }
            cuota.t_cuotas.setModel(mostrar);
            cuota.t_cuotas.updateUI();
            ven.derecha_celda(cuota.t_cuotas, 0);
            ven.centrar_celda(cuota.t_cuotas, 1);
            ven.derecha_celda(cuota.t_cuotas, 2);
            ven.centrar_celda(cuota.t_cuotas, 3);
            ven.derecha_celda(cuota.t_cuotas, 4);
            ven.derecha_celda(cuota.t_cuotas, 5);
            ven.derecha_celda(cuota.t_cuotas, 6);
            ven.centrar_celda(cuota.t_cuotas, 7);
            cuota.txt_dtot.setText(ven.formato_numero(com.getTotal()));
            cuota.txt_tot.setText(ven.formato_numero(tot_cuotas));
            cuota.txt_pen.setText(ven.formato_numero(com.getTotal() - tot_monto));
            cuota.txt_pag.setText(ven.formato_numero(tot_monto));
            if (tot_cuotas == com.getTotal()) {
                cuota.btn_addc.setEnabled(false);
            }
            con.cerrar(rs);
            con.cerrar(st);
        } catch (SQLException e) {
            System.out.println(e);
        }
        this.dispose();
    }//GEN-LAST:event_btn_regActionPerformed

    private void btn_regKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btn_regKeyPressed
//        if (evt.getKeyCode()==KeyEvent.VK_ENTER) {
//            btn_reg.doClick();
//        }
    }//GEN-LAST:event_btn_regKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_reg;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JFormattedTextField txt_fec;
    private javax.swing.JTextField txt_monto;
    // End of variables declaration//GEN-END:variables
}
