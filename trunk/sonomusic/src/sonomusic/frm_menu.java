package sonomusic;

import Clases.Cl_Almacen;
import Clases.Cl_Caja;
import Clases.Cl_Conectar;
import Clases.Cl_Hilo_Notificacion;
import Clases.Cl_Productos;
import Clases.Cl_Usuario;
import Vistas.frm_ver_ofertas;
import Clases.Cl_Varios;
import Clases.table_render;
import Forms.frm_conf_doc;
import Forms.frm_reg_deposito;
import Forms.frm_reg_empresa;
import Forms.frm_reg_venta;
import Forms.frm_rpt_fechas;
import Vistas.frm_banco;
import Vistas.frm_cargos;
import Vistas.frm_cuentas;
import Vistas.frm_metas;
import Vistas.frm_moneda;
import Vistas.frm_movimientos;
import Vistas.frm_und_medida;
import Vistas.frm_ver_almacen;
import Vistas.frm_ver_clasificacion;
import Vistas.frm_ver_cliente;
import Vistas.frm_ver_compras_prod;
import Vistas.frm_ver_compras_serv;
import Vistas.frm_ver_cotizacion;
import Vistas.frm_ver_empleado;
import Vistas.frm_ver_guias;
import Vistas.frm_ver_notas;
import Vistas.frm_ver_prod_alm;
import Vistas.frm_ver_prod_alm_det;
import Vistas.frm_ver_productos;
import Vistas.frm_ver_proveedores;
import Vistas.frm_ver_solicitudes;
import Vistas.frm_ver_usuarios;
import Vistas.frm_ver_venta;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class frm_menu extends javax.swing.JFrame {

    Cl_Varios ven = new Cl_Varios();
    Cl_Conectar con = new Cl_Conectar();
    Cl_Hilo_Notificacion noti = null;
    public static Cl_Almacen alm = new Cl_Almacen();
    public static Cl_Usuario usu = new Cl_Usuario();
    public static Cl_Caja caja = new Cl_Caja();

    public frm_menu() {
        initComponents();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        capt_nom_pc();
        ver_id_almacen();
        lbl_alm.setText(alm.getNom());
        lbl_ciudad.setText(alm.getCiudad());
        txt_ruc.setText(alm.getRuc());
        txt_raz.setText(alm.getRaz_soc());
        // carga todas las notificacion primera vez
        noti = new Cl_Hilo_Notificacion();
        noti.start();
    }

    private String leer_almacen() {
        String almacen = null;
        try {
            File Ffichero = new File("almacen.txt");

            /*Si existe el fichero*/
            if (Ffichero.exists()) {
                /*Abre un flujo de lectura a el fichero*/
                BufferedReader Flee = new BufferedReader(new FileReader(Ffichero));
                String Slinea;
                //System.out.println("**********Leyendo Fichero***********");  
                /*Lee el fichero linea a linea hasta llegar a la ultima*/
                while ((Slinea = Flee.readLine()) != null) {
                    /*Imprime la linea leida*/
                    almacen = Slinea;
                    // System.out.println(Slinea);                
                }
                //System.out.println("*********Fin Leer Fichero**********");  
                /*Cierra el flujo*/
                Flee.close();
                //return txt_alm;
            } else {
                System.out.println("Fichero No Existe");
            }
        } catch (IOException ex) {
            /*Captura un posible error y le imprime en pantalla*/
            System.out.println(ex.getMessage());
        }
        return almacen;
    }

    private void capt_nom_pc() {
        lbl_pc.setText(System.getProperty("user.name").toUpperCase());
    }

    private void ver_id_almacen() {
        try {
            Statement st = con.conexion();
            String ver_id = "select idAlmacen, nom_alm, dir_alm, ciudad, raz_soc, ruc from almacen where nom_alm = '" + leer_almacen() + "'";
            ResultSet rs = con.consulta(st, ver_id);
            if (rs.next()) {
                alm.setId(rs.getInt("idAlmacen"));
                alm.setNom(rs.getString("nom_alm"));
                alm.setCiudad(rs.getString("ciudad"));
                alm.setRuc(rs.getString("ruc"));
                alm.setRaz_soc(rs.getString("raz_soc"));
                alm.setDireccion(rs.getString("dir_alm"));
                caja.setId(caja.id_caja(alm.getId()));
            } else {
                alm.setId(0);
                alm.setNom("NO EXISTE");
                alm.setCiudad("SIN NOMBRE");
                alm.setRuc("SIN RUC");
                alm.setRaz_soc("SIN RAZON SOCIAL");
                JOptionPane.showMessageDialog(null, "Por Favor seleccione un almacen");
                frm_ver_almacen almacen = new frm_ver_almacen();
                ven.llamar_ventana(almacen);
            }
            con.cerrar(rs);
            con.cerrar(st);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getLocalizedMessage());
            System.out.print(ex);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        contenedor = new javax.swing.JDesktopPane();
        jToolBar1 = new javax.swing.JToolBar();
        jPanel2 = new javax.swing.JPanel();
        lbl_user = new javax.swing.JLabel();
        lbl_alm = new javax.swing.JLabel();
        lbl_ciudad = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jSeparator9 = new javax.swing.JSeparator();
        jSeparator10 = new javax.swing.JSeparator();
        jSeparator11 = new javax.swing.JSeparator();
        lbl_pc = new javax.swing.JLabel();
        jSeparator12 = new javax.swing.JSeparator();
        txt_ruc = new javax.swing.JLabel();
        txt_raz = new javax.swing.JLabel();
        jSeparator13 = new javax.swing.JSeparator();
        jSeparator14 = new javax.swing.JSeparator();
        jPanel1 = new javax.swing.JPanel();
        jp_ventas = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jp_compras = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jp_pedido = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jp_envio = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jp_productos = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jp_prod_alm = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jp_caja = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jp_salir = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lbl_nro = new javax.swing.JLabel();
        jp_metas = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        lbl_actual = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        lbl_total = new javax.swing.JLabel();
        lbl_msg = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem10 = new javax.swing.JMenuItem();
        jSeparator6 = new javax.swing.JPopupMenu.Separator();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        m_clientes = new javax.swing.JMenuItem();
        jMenuItem12 = new javax.swing.JMenuItem();
        jSeparator5 = new javax.swing.JPopupMenu.Separator();
        jMenuItem13 = new javax.swing.JMenuItem();
        m_ofertas = new javax.swing.JMenuItem();
        jMenuItem28 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem14 = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JPopupMenu.Separator();
        jMenuItem15 = new javax.swing.JMenuItem();
        jMenuItem21 = new javax.swing.JMenuItem();
        jSeparator15 = new javax.swing.JPopupMenu.Separator();
        jMenuItem31 = new javax.swing.JMenuItem();
        jMenuItem29 = new javax.swing.JMenuItem();
        jMenuItem30 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem22 = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jMenuItem16 = new javax.swing.JMenuItem();
        jMenuItem17 = new javax.swing.JMenuItem();
        jSeparator8 = new javax.swing.JPopupMenu.Separator();
        jMenuItem23 = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        jMenuItem27 = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem18 = new javax.swing.JMenuItem();
        jMenuItem19 = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        jMenuItem24 = new javax.swing.JMenuItem();
        jSeparator7 = new javax.swing.JPopupMenu.Separator();
        jMenuItem20 = new javax.swing.JMenuItem();
        m_notas = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem25 = new javax.swing.JMenuItem();
        jMenuItem26 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Menu - SONOMUSIC");

        contenedor.setBackground(new java.awt.Color(102, 102, 102));
        contenedor.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        jToolBar1.setBorder(null);
        jToolBar1.setFloatable(false);

        lbl_user.setText("Usuario");

        lbl_alm.setText("Almacen");

        lbl_ciudad.setText("Ciudad:");

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/computer.png"))); // NOI18N

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/user_thief_baldie.png"))); // NOI18N

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/house.png"))); // NOI18N

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/comment.png"))); // NOI18N

        jSeparator9.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jSeparator10.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jSeparator11.setOrientation(javax.swing.SwingConstants.VERTICAL);

        lbl_pc.setText("jLabel1");

        jSeparator12.setOrientation(javax.swing.SwingConstants.VERTICAL);

        txt_ruc.setText("RUC");

        txt_raz.setText("RAZON SOCIAL");

        jSeparator13.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jSeparator14.setOrientation(javax.swing.SwingConstants.VERTICAL);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_pc)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator12, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_ruc)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator14, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_raz)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator13, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 554, Short.MAX_VALUE)
                .addComponent(jSeparator11, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_ciudad)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator10, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_alm)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_user)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jSeparator13, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_alm, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_raz, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_ciudad, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_user, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator11, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator10, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_ruc, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_pc, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator12, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator14, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5))
        );

        jToolBar1.add(jPanel2);

        jp_ventas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jp_ventas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jp_ventasMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jp_ventasMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jp_ventasMousePressed(evt);
            }
        });

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Ventas");
        jLabel7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel4.setBackground(new java.awt.Color(204, 204, 204));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/cash_register.png"))); // NOI18N

        javax.swing.GroupLayout jp_ventasLayout = new javax.swing.GroupLayout(jp_ventas);
        jp_ventas.setLayout(jp_ventasLayout);
        jp_ventasLayout.setHorizontalGroup(
            jp_ventasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_ventasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jp_ventasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jp_ventasLayout.setVerticalGroup(
            jp_ventasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jp_ventasLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addContainerGap())
        );

        jp_compras.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jp_compras.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jp_comprasMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jp_comprasMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jp_comprasMousePressed(evt);
            }
        });

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Compras");
        jLabel8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel3.setBackground(new java.awt.Color(204, 204, 204));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/bag1.png"))); // NOI18N

        javax.swing.GroupLayout jp_comprasLayout = new javax.swing.GroupLayout(jp_compras);
        jp_compras.setLayout(jp_comprasLayout);
        jp_comprasLayout.setHorizontalGroup(
            jp_comprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_comprasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jp_comprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addContainerGap())
        );
        jp_comprasLayout.setVerticalGroup(
            jp_comprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jp_comprasLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addContainerGap())
        );

        jp_pedido.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jp_pedido.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jp_pedidoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jp_pedidoMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jp_pedidoMousePressed(evt);
            }
        });

        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Pedidos");
        jLabel12.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel5.setBackground(new java.awt.Color(204, 204, 204));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/ordering.png"))); // NOI18N

        javax.swing.GroupLayout jp_pedidoLayout = new javax.swing.GroupLayout(jp_pedido);
        jp_pedido.setLayout(jp_pedidoLayout);
        jp_pedidoLayout.setHorizontalGroup(
            jp_pedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_pedidoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jp_pedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jp_pedidoLayout.setVerticalGroup(
            jp_pedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jp_pedidoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel12)
                .addContainerGap())
        );

        jp_envio.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jp_envio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jp_envioMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jp_envioMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jp_envioMousePressed(evt);
            }
        });

        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Envios");
        jLabel13.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel18.setBackground(new java.awt.Color(204, 204, 204));
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/autoship.png"))); // NOI18N

        javax.swing.GroupLayout jp_envioLayout = new javax.swing.GroupLayout(jp_envio);
        jp_envio.setLayout(jp_envioLayout);
        jp_envioLayout.setHorizontalGroup(
            jp_envioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_envioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jp_envioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jp_envioLayout.setVerticalGroup(
            jp_envioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jp_envioLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel13)
                .addContainerGap())
        );

        jp_productos.setToolTipText("Mis Productos");
        jp_productos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jp_productos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jp_productosMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jp_productosMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jp_productosMousePressed(evt);
            }
        });

        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Productos");
        jLabel14.setToolTipText("Mis Productos");
        jLabel14.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel19.setBackground(new java.awt.Color(204, 204, 204));
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/guitar.png"))); // NOI18N

        javax.swing.GroupLayout jp_productosLayout = new javax.swing.GroupLayout(jp_productos);
        jp_productos.setLayout(jp_productosLayout);
        jp_productosLayout.setHorizontalGroup(
            jp_productosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_productosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jp_productosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jp_productosLayout.setVerticalGroup(
            jp_productosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jp_productosLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel14)
                .addContainerGap())
        );

        jp_prod_alm.setToolTipText("Productos en Tiendas");
        jp_prod_alm.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jp_prod_alm.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jp_prod_almMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jp_prod_almMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jp_prod_almMousePressed(evt);
            }
        });

        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("Productos");
        jLabel15.setToolTipText("Productos en Tiendas");
        jLabel15.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel20.setBackground(new java.awt.Color(204, 204, 204));
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/almacen.png"))); // NOI18N

        javax.swing.GroupLayout jp_prod_almLayout = new javax.swing.GroupLayout(jp_prod_alm);
        jp_prod_alm.setLayout(jp_prod_almLayout);
        jp_prod_almLayout.setHorizontalGroup(
            jp_prod_almLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_prod_almLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jp_prod_almLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jp_prod_almLayout.setVerticalGroup(
            jp_prod_almLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jp_prod_almLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel15)
                .addContainerGap())
        );

        jp_caja.setToolTipText("Caja Chica");
        jp_caja.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jp_caja.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jp_cajaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jp_cajaMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jp_cajaMousePressed(evt);
            }
        });

        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("Caja Chica");
        jLabel16.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel21.setBackground(new java.awt.Color(204, 204, 204));
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/coins.png"))); // NOI18N

        javax.swing.GroupLayout jp_cajaLayout = new javax.swing.GroupLayout(jp_caja);
        jp_caja.setLayout(jp_cajaLayout);
        jp_cajaLayout.setHorizontalGroup(
            jp_cajaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_cajaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jp_cajaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jp_cajaLayout.setVerticalGroup(
            jp_cajaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jp_cajaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel16)
                .addContainerGap())
        );

        jp_salir.setToolTipText("Salir");
        jp_salir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jp_salir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jp_salirMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jp_salirMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jp_salirMousePressed(evt);
            }
        });

        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("Salir");
        jLabel17.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel22.setBackground(new java.awt.Color(204, 204, 204));
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/shut_down.png"))); // NOI18N

        javax.swing.GroupLayout jp_salirLayout = new javax.swing.GroupLayout(jp_salir);
        jp_salir.setLayout(jp_salirLayout);
        jp_salirLayout.setHorizontalGroup(
            jp_salirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_salirLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jp_salirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jp_salirLayout.setVerticalGroup(
            jp_salirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jp_salirLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel17)
                .addContainerGap())
        );

        jLabel1.setText("Notificaciones:");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/asterisk_orange.png"))); // NOI18N
        jLabel2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });

        lbl_nro.setText("lbl_nro");
        lbl_nro.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbl_nro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lbl_nroMousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jp_ventas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jp_compras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jp_pedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jp_envio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jp_productos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jp_prod_alm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jp_caja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jp_salir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbl_nro)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jp_salir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jp_caja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jp_prod_alm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jp_productos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jp_ventas, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jp_compras, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jp_pedido, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jp_envio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(lbl_nro))
                .addContainerGap())
        );

        jp_metas.setBackground(new java.awt.Color(0, 0, 102));

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("Meta:");

        lbl_actual.setForeground(new java.awt.Color(255, 255, 255));
        lbl_actual.setText("lbl_actual");

        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText("de");

        lbl_total.setForeground(new java.awt.Color(255, 255, 255));
        lbl_total.setText("lbl_total");

        lbl_msg.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lbl_msg.setForeground(new java.awt.Color(255, 255, 255));
        lbl_msg.setText("lbl_msg");

        javax.swing.GroupLayout jp_metasLayout = new javax.swing.GroupLayout(jp_metas);
        jp_metas.setLayout(jp_metasLayout);
        jp_metasLayout.setHorizontalGroup(
            jp_metasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_metasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel23)
                .addGap(18, 18, 18)
                .addComponent(lbl_actual)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel25)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_total)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jp_metasLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbl_msg)
                .addContainerGap())
        );
        jp_metasLayout.setVerticalGroup(
            jp_metasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_metasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jp_metasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(lbl_actual)
                    .addComponent(jLabel25)
                    .addComponent(lbl_total))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_msg)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout contenedorLayout = new javax.swing.GroupLayout(contenedor);
        contenedor.setLayout(contenedorLayout);
        contenedorLayout.setHorizontalGroup(
            contenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, contenedorLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jp_metas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        contenedorLayout.setVerticalGroup(
            contenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, contenedorLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jp_metas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 322, Short.MAX_VALUE)
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        contenedor.setLayer(jToolBar1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        contenedor.setLayer(jPanel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        contenedor.setLayer(jp_metas, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jMenuBar1.setBorderPainted(false);

        jMenu1.setText("RR.HH.");
        jMenu1.setToolTipText("");

        jMenuItem7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/cargo.png"))); // NOI18N
        jMenuItem7.setText("Cargos");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem7);

        jMenuItem10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/meta.png"))); // NOI18N
        jMenuItem10.setText("Metas");
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem10);
        jMenu1.add(jSeparator6);

        jMenuItem8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/user_thief_baldie.png"))); // NOI18N
        jMenuItem8.setText("Colaborador");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem8);

        jMenuItem9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/adelanto.png"))); // NOI18N
        jMenuItem9.setText("Adelantos");
        jMenu1.add(jMenuItem9);

        jMenuItem11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/pagos.png"))); // NOI18N
        jMenuItem11.setText("Pago Colaborador");
        jMenu1.add(jMenuItem11);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Ventas");

        m_clientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/user.png"))); // NOI18N
        m_clientes.setText("Clientes");
        m_clientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m_clientesActionPerformed(evt);
            }
        });
        jMenu2.add(m_clientes);

        jMenuItem12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/cotizacion.png"))); // NOI18N
        jMenuItem12.setText("Cotizacion a Cliente");
        jMenuItem12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem12ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem12);
        jMenu2.add(jSeparator5);

        jMenuItem13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/compras.png"))); // NOI18N
        jMenuItem13.setText("Ventas");
        jMenuItem13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem13ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem13);

        m_ofertas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/basket.png"))); // NOI18N
        m_ofertas.setText("Ofertas");
        m_ofertas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m_ofertasActionPerformed(evt);
            }
        });
        jMenu2.add(m_ofertas);

        jMenuItem28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/clipboard_text.png"))); // NOI18N
        jMenuItem28.setText("Reporte de Venta por vendedor");
        jMenuItem28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem28ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem28);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Compras");

        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/proveedor.png"))); // NOI18N
        jMenuItem2.setText("Proveedor");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem2);

        jMenuItem14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/cotizacion.png"))); // NOI18N
        jMenuItem14.setText("Cotizacion a Proveedor");
        jMenu3.add(jMenuItem14);
        jMenu3.add(jSeparator4);

        jMenuItem15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/compra_producto.png"))); // NOI18N
        jMenuItem15.setText("Compras Productos");
        jMenuItem15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem15ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem15);

        jMenuItem21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/compra_servicio.png"))); // NOI18N
        jMenuItem21.setText("Compras Servicios");
        jMenuItem21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem21ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem21);
        jMenu3.add(jSeparator15);

        jMenuItem31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/clipboard_text.png"))); // NOI18N
        jMenuItem31.setText("Rpt. Compras por Fecha");
        jMenuItem31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem31ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem31);

        jMenuItem29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/clipboard_text.png"))); // NOI18N
        jMenuItem29.setText("Rpt. Compras por Producto");
        jMenuItem29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem29ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem29);

        jMenuItem30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/clipboard_text.png"))); // NOI18N
        jMenuItem30.setText("Rpt. Compras por Proveedor");
        jMenuItem30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem30ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem30);

        jMenuBar1.add(jMenu3);

        jMenu4.setText("Almacen");

        jMenuItem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/almacen.png"))); // NOI18N
        jMenuItem3.setText("Almacenes");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem3);

        jMenuItem22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/delivery.png"))); // NOI18N
        jMenuItem22.setText("Traslados");
        jMenuItem22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem22ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem22);
        jMenu4.add(jSeparator1);

        jMenuItem5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/articulos_almacen.png"))); // NOI18N
        jMenuItem5.setText("Articulos en Almacen");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem5);

        jMenuItem6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/house.png"))); // NOI18N
        jMenuItem6.setText("Articulos x Almacen");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem6);

        jMenuItem4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/articulos_general.png"))); // NOI18N
        jMenuItem4.setText("Articulos en General");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem4);
        jMenu4.add(jSeparator2);

        jMenuItem16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Classification.png"))); // NOI18N
        jMenuItem16.setText("Clasificacion");
        jMenuItem16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem16ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem16);

        jMenuItem17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/unidad_medida.png"))); // NOI18N
        jMenuItem17.setText("Und. Medida");
        jMenuItem17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem17ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem17);
        jMenu4.add(jSeparator8);

        jMenuItem23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/requerimiento.png"))); // NOI18N
        jMenuItem23.setText("Requerimiento de Articulos");
        jMenuItem23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem23ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem23);
        jMenu4.add(jSeparator3);

        jMenuItem27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/clipboard_text.png"))); // NOI18N
        jMenuItem27.setText("Rotulos por Almacen");
        jMenuItem27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem27ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem27);

        jMenuBar1.add(jMenu4);

        jMenu5.setText("Contabilidad");

        jMenuItem18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/book.png"))); // NOI18N
        jMenuItem18.setText("Conf. Documentos");
        jMenuItem18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem18ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem18);

        jMenuItem19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/caja_chica.png"))); // NOI18N
        jMenuItem19.setText("Caja Chica");
        jMenuItem19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem19ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem19);

        jMenuBar1.add(jMenu5);

        jMenu6.setText("Datos Generales");

        jMenuItem24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/banco.png"))); // NOI18N
        jMenuItem24.setText("Bancos");
        jMenuItem24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem24ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem24);
        jMenu6.add(jSeparator7);

        jMenuItem20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/business_users.png"))); // NOI18N
        jMenuItem20.setText("Usuarios");
        jMenuItem20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem20ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem20);

        m_notas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/notas.png"))); // NOI18N
        m_notas.setText("Notas");
        m_notas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m_notasActionPerformed(evt);
            }
        });
        jMenu6.add(m_notas);

        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/vcard.png"))); // NOI18N
        jMenuItem1.setText("Cuentas");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem1);

        jMenuItem25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/currency.png"))); // NOI18N
        jMenuItem25.setText("Moneda");
        jMenuItem25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem25ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem25);

        jMenuItem26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/deposito.png"))); // NOI18N
        jMenuItem26.setText("Deposito");
        jMenuItem26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem26ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem26);

        jMenuBar1.add(jMenu6);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(contenedor)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(contenedor)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void m_clientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m_clientesActionPerformed
        frm_ver_cliente clientes = new frm_ver_cliente();
        ven.llamar_ventana(clientes);
    }//GEN-LAST:event_m_clientesActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        frm_ver_proveedores prov = new frm_ver_proveedores();
        ven.llamar_ventana(prov);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        frm_ver_almacen alm = new frm_ver_almacen();
        ven.llamar_ventana(alm);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        frm_cargos car = new frm_cargos();
        ven.llamar_ventana(car);
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem17ActionPerformed
        frm_und_medida med = new frm_und_medida();
        ven.llamar_ventana(med);
    }//GEN-LAST:event_jMenuItem17ActionPerformed


    private void jMenuItem16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem16ActionPerformed
        frm_ver_clasificacion cla = new frm_ver_clasificacion();
        ven.llamar_ventana(cla);
    }//GEN-LAST:event_jMenuItem16ActionPerformed


    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        frm_ver_productos productos = new frm_ver_productos();
        ven.llamar_ventana(productos);
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem15ActionPerformed
        frm_ver_compras_prod com = new frm_ver_compras_prod();
        ven.llamar_ventana(com);
    }//GEN-LAST:event_jMenuItem15ActionPerformed

    private void jp_ventasMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jp_ventasMouseEntered
        jp_ventas.setBackground(new java.awt.Color(224, 224, 224));
    }//GEN-LAST:event_jp_ventasMouseEntered

    private void jp_ventasMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jp_ventasMouseExited
        jp_ventas.setBackground(new java.awt.Color(240, 240, 240));
    }//GEN-LAST:event_jp_ventasMouseExited

    private void jp_comprasMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jp_comprasMouseEntered
        jp_compras.setBackground(new java.awt.Color(224, 224, 224));
    }//GEN-LAST:event_jp_comprasMouseEntered

    private void jp_comprasMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jp_comprasMouseExited
        jp_compras.setBackground(new java.awt.Color(240, 240, 240));
    }//GEN-LAST:event_jp_comprasMouseExited

    private void jMenuItem21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem21ActionPerformed
        frm_ver_compras_serv compra = new frm_ver_compras_serv();
        ven.llamar_ventana(compra);
    }//GEN-LAST:event_jMenuItem21ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        frm_ver_empleado empleados = new frm_ver_empleado();
        ven.llamar_ventana(empleados);
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void jMenuItem20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem20ActionPerformed
        frm_ver_usuarios usu = new frm_ver_usuarios();
        ven.llamar_ventana(usu);
    }//GEN-LAST:event_jMenuItem20ActionPerformed

    private void jMenuItem19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem19ActionPerformed
        frm_movimientos movi = new frm_movimientos();
        ven.llamar_ventana(movi);
    }//GEN-LAST:event_jMenuItem19ActionPerformed

    private void jMenuItem12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem12ActionPerformed
        frm_ver_cotizacion coti = new frm_ver_cotizacion();
        ven.llamar_ventana(coti);
    }//GEN-LAST:event_jMenuItem12ActionPerformed

    private void m_ofertasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m_ofertasActionPerformed
        frm_ver_ofertas ofer = new frm_ver_ofertas();
        ven.llamar_ventana(ofer);
    }//GEN-LAST:event_m_ofertasActionPerformed

    private void m_notasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m_notasActionPerformed
        frm_ver_notas nota = new frm_ver_notas();
        ven.llamar_ventana(nota);
    }//GEN-LAST:event_m_notasActionPerformed

    private void jMenuItem22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem22ActionPerformed
        frm_ver_guias guia = new frm_ver_guias();
        ven.llamar_ventana(guia);
    }//GEN-LAST:event_jMenuItem22ActionPerformed

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
        frm_metas met = new frm_metas();
        ven.llamar_ventana(met);
    }//GEN-LAST:event_jMenuItem10ActionPerformed

    private void jMenuItem13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem13ActionPerformed
        frm_ver_venta ver_vent = new frm_ver_venta();
        ven.llamar_ventana(ver_vent);
    }//GEN-LAST:event_jMenuItem13ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        frm_ver_prod_alm_det pro_det_alm = new frm_ver_prod_alm_det();
        ven.llamar_ventana(pro_det_alm);
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem23ActionPerformed
        frm_ver_solicitudes solc = new frm_ver_solicitudes();
        ven.llamar_ventana(solc);
    }//GEN-LAST:event_jMenuItem23ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        Cl_Productos prod = new Cl_Productos();
        frm_ver_prod_alm mat = new frm_ver_prod_alm();
        String query = "select p.idProductos, p.desc_pro, p.modelo, p.serie, p.marca, pa.cant, p.cant_min, p.precio, p.costo_compra, p.estado, c.desc_clas, "
                + "u.desc_und, p.grado from producto_almacen as pa inner join productos as p on pa.idProductos=p.idProductos inner join clasificacion as "
                + "c on p.id_clas=c.id_clas inner join und_medida as u on p.idUnd_Medida=u.idUnd_Medida where pa.idAlmacen = '" + alm.getId() + "' "
                + "order by p.desc_pro asc";
        prod.mostrar_productos(query);
        mat.txt_ida.setText("" + alm.getId());
        mat.txt_noma.setText(alm.getNom());
        mat.t_productos.setDefaultRenderer(Object.class, new table_render());
        ven.llamar_ventana(mat);
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jp_pedidoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jp_pedidoMouseEntered
        jp_pedido.setBackground(new java.awt.Color(224, 224, 224));
    }//GEN-LAST:event_jp_pedidoMouseEntered

    private void jp_pedidoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jp_pedidoMouseExited
        jp_pedido.setBackground(new java.awt.Color(240, 240, 240));
    }//GEN-LAST:event_jp_pedidoMouseExited

    private void jp_envioMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jp_envioMouseEntered
        jp_envio.setBackground(new java.awt.Color(224, 224, 224));
    }//GEN-LAST:event_jp_envioMouseEntered

    private void jp_envioMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jp_envioMouseExited
        jp_envio.setBackground(new java.awt.Color(240, 240, 240));
    }//GEN-LAST:event_jp_envioMouseExited

    private void jp_productosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jp_productosMouseEntered
        jp_productos.setBackground(new java.awt.Color(224, 224, 224));
    }//GEN-LAST:event_jp_productosMouseEntered

    private void jp_productosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jp_productosMouseExited
        jp_productos.setBackground(new java.awt.Color(240, 240, 240));
    }//GEN-LAST:event_jp_productosMouseExited

    private void jp_prod_almMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jp_prod_almMouseEntered
        jp_prod_alm.setBackground(new java.awt.Color(224, 224, 224));
    }//GEN-LAST:event_jp_prod_almMouseEntered

    private void jp_prod_almMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jp_prod_almMouseExited
        jp_prod_alm.setBackground(new java.awt.Color(240, 240, 240));
    }//GEN-LAST:event_jp_prod_almMouseExited

    private void jp_cajaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jp_cajaMouseEntered
        jp_caja.setBackground(new java.awt.Color(224, 224, 224));
    }//GEN-LAST:event_jp_cajaMouseEntered

    private void jp_cajaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jp_cajaMouseExited
        jp_caja.setBackground(new java.awt.Color(240, 240, 240));
    }//GEN-LAST:event_jp_cajaMouseExited

    private void jp_salirMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jp_salirMouseEntered
        jp_salir.setBackground(new java.awt.Color(224, 224, 224));
    }//GEN-LAST:event_jp_salirMouseEntered

    private void jp_salirMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jp_salirMouseExited
        jp_salir.setBackground(new java.awt.Color(240, 240, 240));
    }//GEN-LAST:event_jp_salirMouseExited

    private void jp_salirMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jp_salirMousePressed
        System.exit(0);
    }//GEN-LAST:event_jp_salirMousePressed

    private void jp_cajaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jp_cajaMousePressed
        frm_movimientos movi = new frm_movimientos();
        ven.llamar_ventana(movi);
    }//GEN-LAST:event_jp_cajaMousePressed

    private void jp_ventasMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jp_ventasMousePressed
        frm_reg_venta vent = new frm_reg_venta();
        ven.llamar_ventana(vent);
    }//GEN-LAST:event_jp_ventasMousePressed

    private void jp_pedidoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jp_pedidoMousePressed
        frm_ver_solicitudes solc = new frm_ver_solicitudes();
        ven.llamar_ventana(solc);
    }//GEN-LAST:event_jp_pedidoMousePressed

    private void jp_envioMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jp_envioMousePressed
        frm_ver_guias guia = new frm_ver_guias();
        ven.llamar_ventana(guia);
    }//GEN-LAST:event_jp_envioMousePressed

    private void jp_prod_almMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jp_prod_almMousePressed
        frm_ver_prod_alm_det pro_det_alm = new frm_ver_prod_alm_det();
        ven.llamar_ventana(pro_det_alm);
    }//GEN-LAST:event_jp_prod_almMousePressed

    private void jp_productosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jp_productosMousePressed
        Cl_Productos prod = new Cl_Productos();
        frm_ver_prod_alm mat = new frm_ver_prod_alm();
        String query = "select p.idProductos, p.desc_pro, p.modelo, p.serie, p.marca, pa.cant, p.cant_min, pa.precio, p.estado, c.desc_clas, "
                + "u.desc_und, p.grado from producto_almacen as pa inner join productos as p on pa.idProductos=p.idProductos inner join clasificacion as "
                + "c on p.id_clas=c.id_clas inner join und_medida as u on p.idUnd_Medida=u.idUnd_Medida where pa.idAlmacen = '" + alm.getId() + "' "
                + "order by p.desc_pro asc";
        prod.mostrar_productos(query);
        mat.txt_ida.setText("" + alm.getId());
        mat.txt_noma.setText(alm.getNom());
        mat.t_productos.setDefaultRenderer(Object.class, new table_render());
        ven.llamar_ventana(mat);
    }//GEN-LAST:event_jp_productosMousePressed

    private void lbl_nroMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_nroMousePressed
        noti = new Cl_Hilo_Notificacion();
        noti.start();
    }//GEN-LAST:event_lbl_nroMousePressed

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        noti = new Cl_Hilo_Notificacion();
        noti.start();
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jMenuItem18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem18ActionPerformed
        frm_conf_doc conf = new frm_conf_doc();
        ven.llamar_ventana(conf);
    }//GEN-LAST:event_jMenuItem18ActionPerformed

    private void jp_comprasMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jp_comprasMousePressed
        frm_ver_compras_prod com = new frm_ver_compras_prod();
        ven.llamar_ventana(com);
    }//GEN-LAST:event_jp_comprasMousePressed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        frm_cuentas cue = new frm_cuentas();
        ven.llamar_ventana(cue);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem25ActionPerformed
        frm_moneda mon = new frm_moneda();
        ven.llamar_ventana(mon);
    }//GEN-LAST:event_jMenuItem25ActionPerformed

    private void jMenuItem24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem24ActionPerformed
        frm_banco ban = new frm_banco();
        ven.llamar_ventana(ban);
    }//GEN-LAST:event_jMenuItem24ActionPerformed

    private void jMenuItem26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem26ActionPerformed
        frm_reg_deposito dep = new frm_reg_deposito();
        ven.llamar_ventana(dep);
    }//GEN-LAST:event_jMenuItem26ActionPerformed

    private void jMenuItem27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem27ActionPerformed
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("idalmacen", alm.getId());
        ven.ver_reporte("rotulo_pro_alm", parametros);
        System.out.println("imprimiento " + "rotulo_pro_alm");
    }//GEN-LAST:event_jMenuItem27ActionPerformed

    private void jMenuItem28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem28ActionPerformed
        frm_rpt_fechas fec = new frm_rpt_fechas();
        fec.rpt = "venta_vendedor";
        ven.llamar_ventana(fec);
    }//GEN-LAST:event_jMenuItem28ActionPerformed

    private void jMenuItem29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem29ActionPerformed
        frm_ver_productos prod = new frm_ver_productos();
        prod.ventana = "compra_prod";
        ven.llamar_ventana(prod);
    }//GEN-LAST:event_jMenuItem29ActionPerformed

    private void jMenuItem31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem31ActionPerformed
        frm_rpt_fechas rpt_fec = new frm_rpt_fechas();
        rpt_fec.rpt = "compras";
        ven.llamar_ventana(rpt_fec);
    }//GEN-LAST:event_jMenuItem31ActionPerformed

    private void jMenuItem30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem30ActionPerformed
        frm_ver_proveedores prov = new frm_ver_proveedores();
        prov.funcion = "compras_prov";
        ven.llamar_ventana(prov);
    }//GEN-LAST:event_jMenuItem30ActionPerformed
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Metal".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frm_menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frm_menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frm_menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frm_menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frm_menu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JDesktopPane contenedor;
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
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem14;
    private javax.swing.JMenuItem jMenuItem15;
    private javax.swing.JMenuItem jMenuItem16;
    private javax.swing.JMenuItem jMenuItem17;
    private javax.swing.JMenuItem jMenuItem18;
    private javax.swing.JMenuItem jMenuItem19;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem20;
    private javax.swing.JMenuItem jMenuItem21;
    private javax.swing.JMenuItem jMenuItem22;
    private javax.swing.JMenuItem jMenuItem23;
    private javax.swing.JMenuItem jMenuItem24;
    private javax.swing.JMenuItem jMenuItem25;
    private javax.swing.JMenuItem jMenuItem26;
    private javax.swing.JMenuItem jMenuItem27;
    private javax.swing.JMenuItem jMenuItem28;
    private javax.swing.JMenuItem jMenuItem29;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem30;
    private javax.swing.JMenuItem jMenuItem31;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator13;
    private javax.swing.JSeparator jSeparator14;
    private javax.swing.JPopupMenu.Separator jSeparator15;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    private javax.swing.JPopupMenu.Separator jSeparator5;
    private javax.swing.JPopupMenu.Separator jSeparator6;
    private javax.swing.JPopupMenu.Separator jSeparator7;
    private javax.swing.JPopupMenu.Separator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JPanel jp_caja;
    private javax.swing.JPanel jp_compras;
    private javax.swing.JPanel jp_envio;
    private javax.swing.JPanel jp_metas;
    private javax.swing.JPanel jp_pedido;
    private javax.swing.JPanel jp_prod_alm;
    private javax.swing.JPanel jp_productos;
    private javax.swing.JPanel jp_salir;
    private javax.swing.JPanel jp_ventas;
    private javax.swing.JLabel lbl_actual;
    public static javax.swing.JLabel lbl_alm;
    private javax.swing.JLabel lbl_ciudad;
    private javax.swing.JLabel lbl_msg;
    public static javax.swing.JLabel lbl_nro;
    private javax.swing.JLabel lbl_pc;
    private javax.swing.JLabel lbl_total;
    public static javax.swing.JLabel lbl_user;
    private javax.swing.JMenuItem m_clientes;
    private javax.swing.JMenuItem m_notas;
    private javax.swing.JMenuItem m_ofertas;
    private javax.swing.JLabel txt_raz;
    private javax.swing.JLabel txt_ruc;
    // End of variables declaration//GEN-END:variables
}
