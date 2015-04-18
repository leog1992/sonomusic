package Vistas;

import Clases.Cl_Conectar;
import Clases.Cl_Varios;
import Forms.frm_rpt_fechas;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author programmer
 */
public class frm_ver_empresa extends javax.swing.JInternalFrame {

    Cl_Conectar con = new Cl_Conectar();
    Cl_Varios ven = new Cl_Varios();
    DefaultTableModel modelo;
    int i;
    public static String ventana = "ver_almacen";

    public frm_ver_empresa() {
        initComponents();
        cargar_datos();        
    }

    void cargar_datos() {
        modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        try {
            Statement st = con.conexion();
            String sql = "select distinct(ruc),raz_soc from almacen ";
            modelo.addColumn("Ruc");
            modelo.addColumn("Razon Social");
            ResultSet rs = con.consulta(st, sql);
            Object[] dato = new Object[2];
            while (rs.next()) {
                dato[0] = rs.getObject("ruc");
                dato[1] = rs.getObject("raz_soc");
                modelo.addRow(dato);
            }
            t_empresa.setModel(modelo);
            t_empresa.getColumnModel().getColumn(0).setPreferredWidth(200);
            t_empresa.getColumnModel().getColumn(1).setPreferredWidth(300);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error " + e);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        t_empresa = new javax.swing.JTable();

        setClosable(true);
        setResizable(true);
        setTitle("Empresas");

        t_empresa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Title 1", "Title 2"
            }
        ));
        t_empresa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                t_empresaMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                t_empresaMousePressed(evt);
            }
        });
        t_empresa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                t_empresaKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(t_empresa);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 529, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void t_empresaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_t_empresaMousePressed
        i = t_empresa.getSelectedRow();
    }//GEN-LAST:event_t_empresaMousePressed

    private void t_empresaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_t_empresaKeyPressed
       
    }//GEN-LAST:event_t_empresaKeyPressed

    private void t_empresaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_t_empresaMouseClicked
        if (evt.getClickCount() == 2) {
            if (ventana.equals("compra_empresa")) {
                frm_rpt_fechas fec = new frm_rpt_fechas();
                fec.rpt = "compra_empresa";
                String id = t_empresa.getValueAt(i, 0).toString();
                fec.alm.setRuc(id);
                ven.llamar_ventana(fec);
                ventana = "";
                this.dispose();
            }
            //REPORTE GANANCIA EMPRESA
            if (ventana.equals("rpt_ganancia_almacen")) {
                Map<String, Object> parametros = new HashMap<>();
                String id = t_empresa.getValueAt(i, 0).toString();                
                parametros.put("ruc", id);
                ven.ver_reporte("rpt_utilidad_almacen", parametros);
                ventana="";
                this.dispose();
            }
             //VENTA TOTAL
            if (ventana.equals("venta_total")) {                
                frm_rpt_fechas fec =new frm_rpt_fechas();
                fec.rpt="venta_total";
                String id= t_empresa.getValueAt(i, 0).toString();
                fec.alm.setRuc(id);
                ven.llamar_ventana(fec);
                ventana="";
                this.dispose();
            }
        }
    }//GEN-LAST:event_t_empresaMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable t_empresa;
    // End of variables declaration//GEN-END:variables
}
