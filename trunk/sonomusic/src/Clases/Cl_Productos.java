package Clases;

import Vistas.frm_ver_prod_alm;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import sonomusic.frm_menu;

public class Cl_Productos {

    Cl_Conectar con = new Cl_Conectar();
    Cl_Varios ven = new Cl_Varios();
    Cl_Ofertas ofe = new Cl_Ofertas();
    private int id_pro;
    private String des_pro;
    private String mar_pro;
    private String mod_pro;
    private String ser_pro;
    private String gra_pro;
    private double cos_pro;
    private double pre_pro;
    private double can;
    private double can_act_pro;
    private double can_min_pro;
    private String est = "1";
    private double com_pro;
    private String img;

    private int tot_reg;

    public Cl_Productos() {
    }

    public int getId_pro() {
        return id_pro;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setId_pro(int id_pro) {
        this.id_pro = id_pro;
    }

    public String getDes_pro() {
        return des_pro;
    }

    public void setDes_pro(String des_pro) {
        this.des_pro = des_pro.toUpperCase();
    }

    public String getMar_pro() {
        return mar_pro;
    }

    public void setMar_pro(String mar_pro) {
        this.mar_pro = mar_pro.toUpperCase();
    }

    public String getMod_pro() {
        return mod_pro;
    }

    public void setMod_pro(String mod_pro) {
        this.mod_pro = mod_pro.toUpperCase();
    }

    public String getSer_pro() {
        return ser_pro;
    }

    public void setSer_pro(String ser_pro) {
        this.ser_pro = ser_pro.toUpperCase();
    }

    public String getGra_pro() {
        return gra_pro;
    }

    public void setGra_pro(String gra_pro) {
        this.gra_pro = gra_pro.toUpperCase();
    }

    public double getCos_pro() {
        return cos_pro;
    }

    public void setCos_pro(double cos_pro) {
        this.cos_pro = cos_pro;
    }

    public double getPre_pro() {
        return pre_pro;
    }

    public void setPre_pro(double pre_pro) {
        this.pre_pro = pre_pro;
    }

    public double getCan_act_pro() {
        return can_act_pro;
    }

    public void setCan_act_pro(double can_act_pro) {
        this.can_act_pro = can_act_pro;
    }

    public double getCan_min_pro() {
        return can_min_pro;
    }

    public void setCan_min_pro(double can_min_pro) {
        this.can_min_pro = can_min_pro;
    }

    public String getEst() {
        return est;
    }

    public void setEst(String est) {
        this.est = est;
    }

    public double getCom_pro() {
        return com_pro;
    }

    public void setCom_pro(double com_pro) {
        this.com_pro = com_pro;
    }

    public double getCan() {
        return can;
    }

    public void setCan(double can_act) {
        this.can = can_act;
    }

    public void mostrar_productos(String query, JTable tabla) {
        try {
            DefaultTableModel mostrar = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int fila, int columna) {
                    return false;
                }
            };
            Statement st = con.conexion();
            ResultSet rs = con.consulta(st, query);
            //Establecer como cabezeras el nombre de las colimnas
            mostrar.addColumn("Id");
            mostrar.addColumn("Descripcion");
            mostrar.addColumn("Marca");
            mostrar.addColumn("Cant. Act.");
            mostrar.addColumn("Und. Med.");
            mostrar.addColumn("Precio");
            mostrar.addColumn("Cant. Min.");
            mostrar.addColumn("Clasificacion");
            mostrar.addColumn("Tipo Existencia");
            mostrar.addColumn("Estado");

            //Creando las filas para el JTable
            Object[] fila = new Object[10];
            while (rs.next()) {

                fila[0] = rs.getObject("idProductos");
                fila[1] = rs.getObject("desc_pro") + " - " + rs.getObject("modelo") + " - " + rs.getObject("serie");
                fila[2] = rs.getObject("marca");
                fila[3] = rs.getObject("cant");
                fila[4] = rs.getObject("desc_und");
                if (ofe.precio_oferta(frm_menu.alm.getId(), rs.getInt("idProductos")) == 0.00) {
                    fila[5] = rs.getDouble("precio");
                } else {
                    fila[5] = ofe.precio_oferta(frm_menu.alm.getId(), rs.getInt("idProductos"));
                }
                fila[6] = rs.getObject("cant_min");
                fila[7] = rs.getObject("desc_clas");
                fila[8] = rs.getObject("grado");
                if (rs.getString("estado").equals("1")) {
                    if (rs.getDouble("cant") > rs.getDouble("cant_min")) {
                        fila[9] = "NORMAL";
                    }
                    if (rs.getDouble("cant") <= rs.getDouble("cant_min")) {
                        fila[9] = "POR TERMINAR";
                    }
                    if (rs.getDouble("cant") <= 0) {
                        fila[9] = "NO DISPONIBLE";
                    }
                } else {
                    fila[9] = "-";
                }

                mostrar.addRow(fila);
            }
            con.cerrar(st);
            con.cerrar(rs);
            tabla.setModel(mostrar);
            tabla.getColumnModel().getColumn(0).setPreferredWidth(30);
            tabla.getColumnModel().getColumn(1).setPreferredWidth(350);
            tabla.getColumnModel().getColumn(2).setPreferredWidth(80);
            tabla.getColumnModel().getColumn(3).setPreferredWidth(60);
            tabla.getColumnModel().getColumn(4).setPreferredWidth(100);
            tabla.getColumnModel().getColumn(5).setPreferredWidth(60);
            tabla.getColumnModel().getColumn(6).setPreferredWidth(50);
            tabla.getColumnModel().getColumn(7).setPreferredWidth(100);
            tabla.getColumnModel().getColumn(8).setPreferredWidth(80);
            tabla.getColumnModel().getColumn(9).setPreferredWidth(100);
            tabla.setDefaultRenderer(Object.class, new render_productos());
            mostrar.fireTableDataChanged();
            tabla.updateUI();

        } catch (SQLException e) {
            System.out.print(e);
        }

    }

    public int getTot_reg() {
        return tot_reg;
    }

    public void setTot_reg(int tot_reg) {
        this.tot_reg = tot_reg;
    }

}
